package batchJob.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.been.CpgTranDetailDo;
import batchJob.been.CpgTranMainDo;
import batchJob.been.CpgTranPostDo;
import batchJob.been.EventCRecvADo;
import batchJob.been.EventCRecvBDo;
import batchJob.been.EventCRecvCDo;
import batchJob.been.po.CpgSettingPo;
import batchJob.been.vo.cpg.CPGEnqueuePart2Vo;
import batchJob.been.vo.cpg.CpgTranPostVo;
import batchJob.been.vo.cpg.EventCRecvVo;
import batchJob.common.codegenerate.SendFlag;
import batchJob.common.exception.ApBusinessException;
import batchJob.common.file.FileCommand;
import batchJob.common.file.impl.FileCommandFacade;
import batchJob.common.reader.EcReader;
import batchJob.common.utils.FolderUtils;
import batchJob.common.xml.ContextFactory;
import batchJob.common.xml.impl.FileTxtContextFactory;
import batchJob.model.XdaoSessionManager;

import com.java.common.date.YYYYMMDDUtils;
import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.wcommons.ApContext;

public class CFGEnqueuePart2ServiceImpl implements CFGEnqueuePart2Service {
	
	private static String NOTE_FLG = ".flg";
	private static Logger LOGGER = LoggerFactory.getLogger("AP");
	private FileCommandFacade fileCommandFacade = new FileCommandFacade();
	private CpgSettingQuery cpgSettingQuery = new CpgSettingQueryImpl();

	@Override
	public void execute(final CPGEnqueuePart2Vo vo) {
		vo.setPendingDir(ApContext.getContext().getSetting("CPG_ENQ_PENDING_DIR"));
		vo.setErrDir(ApContext.getContext().getSetting("CPG_ENQ_ERR_DIR"));
		vo.setOkDir(ApContext.getContext().getSetting("CPG_ENQ_OK_DIR"));

		FolderUtils.createFolders(vo.getPendingDir());
		FolderUtils.createFolders(vo.getErrDir());
		FolderUtils.createFolders(vo.getOkDir());
		for (final File file : this.getPendingDir(vo)) {
			this.executeFile(file, vo);
		}

	}

	private List<File> getPendingDir(final CPGEnqueuePart2Vo vo) {
		final Set<String> mapFile = new HashSet<String>();
		final File[] pendingFiles = new File(vo.getPendingDir()).listFiles();

		for (final File file : pendingFiles) {
			if (file.getName().endsWith(NOTE_FLG)) {
				final String orgNameString = file.getName().replace(NOTE_FLG, StringUtils.EMPTY);
				mapFile.add(orgNameString);
			}
		}
		final List<File> files = new ArrayList<File>();
		for (final String fileName : mapFile) {
			for (final File file : pendingFiles) {
				if (fileName.equals(file.getName())) {
					files.add(file);
				}
			}
		}
		return files;
	}

	private void executeFile(final File file, final CPGEnqueuePart2Vo vo) {
		final FileCommand fileCommand = this.fileCommandFacade.getFileCommand();
		final DoXdaoSession xdaoSession = (DoXdaoSession) XdaoSessionManager.getXdaoSession();
		try {
			final ContextFactory factory = new FileTxtContextFactory(file);
			final EcReader reader = new EcReader(factory);
			final EventCRecvVo eventCRecvVo = (EventCRecvVo) reader.getXmlBook();
			eventCRecvVo.setFileName(file.getName());
			xdaoSession.beginTransaction();
			this.insertEventCRecv(xdaoSession, file, eventCRecvVo);// ??????ec??????
			final CpgTranPostVo cpgTranPostVo = this.toCpgTranDo(eventCRecvVo);// ????????????cpg_trans
			xdaoSession.insertPo(cpgTranPostVo.getCpgTranMainDo());
			xdaoSession.insertPo(cpgTranPostVo.getCpgTranDetailDos());
			xdaoSession.insertPo(cpgTranPostVo.getPostDo());
			fileCommand.delete(new File(file.getPath() + NOTE_FLG));
			fileCommand.renameTo(file, new File(vo.getOkDir(), file.getName()));
			xdaoSession.commit();
		} catch (final Exception e) {
			LOGGER.error("????????????" + file.getParent() + "??????", e);
			fileCommand.renameTo(file, new File(vo.getErrDir(), file.getName()));
			fileCommand.delete(new File(file.getPath() + NOTE_FLG));

		}

	}

	/**
	 * ???ec?????????????????????xml????????????
	 * 
	 * @param eventCRecvVo
	 * @return
	 */
	private CpgTranPostVo toCpgTranDo(final EventCRecvVo eventCRecvVo) {

		final CpgTranPostVo cpgTranPostVo = new CpgTranPostVo();
		CpgTranMainDo cpgTranMainDo = new CpgTranMainDo();
		cpgTranMainDo.setSendflag(SendFlag.P.name());
		cpgTranMainDo.setSendTime("");
		cpgTranMainDo.setMsgfunccode("9");
		cpgTranMainDo.setFilename(eventCRecvVo.getFileName());
		for (EventCRecvADo eventCRecvADo : eventCRecvVo.getEventCRecvADos()) {
			final CpgSettingPo cpgSettingDo = this.cpgSettingQuery.query(eventCRecvADo.getPostspecialaccount());
			cpgTranMainDo.setMessagetype(cpgSettingDo.getMessagetype());// ?????????
			cpgTranMainDo.setPostspecialaccount(eventCRecvADo.getPostspecialaccount());
		}
		cpgTranPostVo.setCpgTranMainDo(cpgTranMainDo);
		for (final EventCRecvADo eventCRecvADo : eventCRecvVo.getEventCRecvADos()) {
			final CpgTranPostDo cpgTranPostDo = new CpgTranPostDo();
			cpgTranPostDo.setFilename(eventCRecvVo.getFileName());// ??????
			cpgTranPostDo.setPostmblno(StringUtils.defaultString(eventCRecvADo.getPostmblno())); // ???????????????
			cpgTranPostDo.setOrircvfile(StringUtils.defaultString(eventCRecvADo.getOldfilename()));// ???????????????
			cpgTranPostDo.setFlightno(StringUtils.defaultString(eventCRecvADo.getTakeoffflight())); // ????????????
			cpgTranPostDo.setFlightdatetime(StringUtils.defaultString(eventCRecvADo.getSendtime())); // ????????????
			cpgTranPostDo.setPostspecialaccount(StringUtils.defaultString(eventCRecvADo.getPostspecialaccount())); // ????????????
			cpgTranPostDo.setPosttype(StringUtils.defaultString(eventCRecvADo.getPosttype())); // ????????????
			cpgTranPostDo.setType(StringUtils.defaultString(eventCRecvADo.getType())); // ??????
			cpgTranPostDo.setYear(StringUtils.defaultString(eventCRecvADo.getYear())); // ??????
			cpgTranPostDo.setCountrycode(eventCRecvADo.getCountrycode()); // ????????????
			cpgTranPostDo.setExchangeagency(eventCRecvADo.getExchangeagency()); // ????????????
			cpgTranPostDo.setTotalpackageno(eventCRecvADo.getTotalpackageno()); // ????????????
			cpgTranPostDo.setGoodtotalpackageyear(eventCRecvADo.getGoodtotalpackageyear()); // ???????????????
			cpgTranPostDo.setGoodtotalpackageno(eventCRecvADo.getGoodtotalpackageno()); // ???????????????
			cpgTranPostVo.getPostDo().add(cpgTranPostDo);

		}
		for (final EventCRecvCDo eventCRecvcDo : eventCRecvVo.getEventCRecvCDos()) {
			final CpgTranDetailDo cpgTranDetailDo = new CpgTranDetailDo();
			cpgTranDetailDo.setPostspecialaccount(StringUtils.defaultString(eventCRecvcDo.getPostspecialaccount())); // ????????????
			cpgTranDetailDo.setPosttype(StringUtils.defaultString(eventCRecvcDo.getPosttype()));// ????????????
			cpgTranDetailDo.setType(StringUtils.defaultString(eventCRecvcDo.getType())); // ??????
			cpgTranDetailDo.setYear(StringUtils.defaultString(eventCRecvcDo.getYear()));// ??????
			cpgTranDetailDo.setCountrycode(eventCRecvcDo.getCountrycode()); // ????????????
			cpgTranDetailDo.setExchangeagency(eventCRecvcDo.getExchangeagency());// ????????????
			cpgTranDetailDo.setTotalpackageno(eventCRecvcDo.getTotalpackageno()); // ????????????
			cpgTranDetailDo.setGoodtotalpackageyear(eventCRecvcDo.getGoodtotalpackageyear()); // ???????????????
			cpgTranDetailDo.setGoodtotalpackageno(eventCRecvcDo.getGoodtotalpackageno());// ???????????????
			cpgTranDetailDo.setFilename(eventCRecvVo.getFileName());
			cpgTranDetailDo.setPostno(eventCRecvcDo.getPostno()); // ????????????
			cpgTranPostVo.getCpgTranDetailDos().add(cpgTranDetailDo);
		}
		return cpgTranPostVo;

	}

	private void insertEventCRecv(//
			final DoXdaoSession xdaoSession,//
			final File file, //
			final EventCRecvVo eventCRecvVo) {//

		try {

			for (final EventCRecvADo eventCRecvADo : eventCRecvVo.getEventCRecvADos()) {
				eventCRecvADo.setCreatetime(YYYYMMDDUtils.getText());
				eventCRecvADo.setFilename(file.getName());

				xdaoSession.insertPo(eventCRecvADo);
			}
			for (final EventCRecvBDo eventCRecvBDo : eventCRecvVo.getEventCRecvBDos()) {
				eventCRecvBDo.setFilename(file.getName());
				xdaoSession.insertPo(eventCRecvBDo);
			}
			for (final EventCRecvCDo eventCRecvCDo : eventCRecvVo.getEventCRecvCDos()) {
				eventCRecvCDo.setFilename(file.getName());
				xdaoSession.insertPo(eventCRecvCDo);
			}
		} catch (final Exception e) {
			throw new ApBusinessException("????????????", e);
		}
	}

}

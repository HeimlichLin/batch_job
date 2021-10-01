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
			this.insertEventCRecv(xdaoSession, file, eventCRecvVo);// 寫入ec檔案
			final CpgTranPostVo cpgTranPostVo = this.toCpgTranDo(eventCRecvVo);// 檔案轉成cpg_trans
			xdaoSession.insertPo(cpgTranPostVo.getCpgTranMainDo());
			xdaoSession.insertPo(cpgTranPostVo.getCpgTranDetailDos());
			xdaoSession.insertPo(cpgTranPostVo.getPostDo());
			fileCommand.delete(new File(file.getPath() + NOTE_FLG));
			fileCommand.renameTo(file, new File(vo.getOkDir(), file.getName()));
			xdaoSession.commit();
		} catch (final Exception e) {
			LOGGER.error("執行檔案" + file.getParent() + "失敗", e);
			fileCommand.renameTo(file, new File(vo.getErrDir(), file.getName()));
			fileCommand.delete(new File(file.getPath() + NOTE_FLG));

		}

	}

	/**
	 * 從ec檔案格式轉換成xml輸出格式
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
			cpgTranMainDo.setMessagetype(cpgSettingDo.getMessagetype());// 異動別
			cpgTranMainDo.setPostspecialaccount(eventCRecvADo.getPostspecialaccount());
		}
		cpgTranPostVo.setCpgTranMainDo(cpgTranMainDo);
		for (final EventCRecvADo eventCRecvADo : eventCRecvVo.getEventCRecvADos()) {
			final CpgTranPostDo cpgTranPostDo = new CpgTranPostDo();
			cpgTranPostDo.setFilename(eventCRecvVo.getFileName());// 檔名
			cpgTranPostDo.setPostmblno(StringUtils.defaultString(eventCRecvADo.getPostmblno())); // 貨提單號碼
			cpgTranPostDo.setOrircvfile(StringUtils.defaultString(eventCRecvADo.getOldfilename()));// 原接收檔名
			cpgTranPostDo.setFlightno(StringUtils.defaultString(eventCRecvADo.getTakeoffflight())); // 起飛班機
			cpgTranPostDo.setFlightdatetime(StringUtils.defaultString(eventCRecvADo.getSendtime())); // 起飛時間
			cpgTranPostDo.setPostspecialaccount(StringUtils.defaultString(eventCRecvADo.getPostspecialaccount())); // 特約戶號
			cpgTranPostDo.setPosttype(StringUtils.defaultString(eventCRecvADo.getPosttype())); // 郵件類別
			cpgTranPostDo.setType(StringUtils.defaultString(eventCRecvADo.getType())); // 格式
			cpgTranPostDo.setYear(StringUtils.defaultString(eventCRecvADo.getYear())); // 年度
			cpgTranPostDo.setCountrycode(eventCRecvADo.getCountrycode()); // 國名代碼
			cpgTranPostDo.setExchangeagency(eventCRecvADo.getExchangeagency()); // 互換局名
			cpgTranPostDo.setTotalpackageno(eventCRecvADo.getTotalpackageno()); // 總包號碼
			cpgTranPostDo.setGoodtotalpackageyear(eventCRecvADo.getGoodtotalpackageyear()); // 貨總包年度
			cpgTranPostDo.setGoodtotalpackageno(eventCRecvADo.getGoodtotalpackageno()); // 貨總包號碼
			cpgTranPostVo.getPostDo().add(cpgTranPostDo);

		}
		for (final EventCRecvCDo eventCRecvcDo : eventCRecvVo.getEventCRecvCDos()) {
			final CpgTranDetailDo cpgTranDetailDo = new CpgTranDetailDo();
			cpgTranDetailDo.setPostspecialaccount(StringUtils.defaultString(eventCRecvcDo.getPostspecialaccount())); // 特約戶號
			cpgTranDetailDo.setPosttype(StringUtils.defaultString(eventCRecvcDo.getPosttype()));// 郵件類別
			cpgTranDetailDo.setType(StringUtils.defaultString(eventCRecvcDo.getType())); // 格式
			cpgTranDetailDo.setYear(StringUtils.defaultString(eventCRecvcDo.getYear()));// 年度
			cpgTranDetailDo.setCountrycode(eventCRecvcDo.getCountrycode()); // 國名代碼
			cpgTranDetailDo.setExchangeagency(eventCRecvcDo.getExchangeagency());// 互換局名
			cpgTranDetailDo.setTotalpackageno(eventCRecvcDo.getTotalpackageno()); // 總包號碼
			cpgTranDetailDo.setGoodtotalpackageyear(eventCRecvcDo.getGoodtotalpackageyear()); // 貨總包年度
			cpgTranDetailDo.setGoodtotalpackageno(eventCRecvcDo.getGoodtotalpackageno());// 貨總包號碼
			cpgTranDetailDo.setFilename(eventCRecvVo.getFileName());
			cpgTranDetailDo.setPostno(eventCRecvcDo.getPostno()); // 郵件號碼
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
			throw new ApBusinessException("處理失敗", e);
		}
	}

}

package batchJob.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.common.date.YYYYMMDDUtils;
import com.tradevan.common.db.DoSqlWhere;
import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.jmseqdq.core.QueService;
import com.tradevan.jmseqdq.core.QueTrans;
import com.tradevan.wcommons.ApContext;
import com.tradevan.wcommons.db.DbFactory;

import batchJob.been.CpgTranDetailDo;
import batchJob.been.CpgTranMainDo;
import batchJob.been.CpgTranPostDo;
import batchJob.been.po.CpgSettingPo;
import batchJob.been.vo.cpg.CPGEnqueuePart3CpgTranMainVo;
import batchJob.been.vo.cpg.CPGEnqueuePart3CpgTranVo;
import batchJob.been.vo.cpg.CPGEnqueuePart3Vo;
import batchJob.common.codegenerate.SendFlag;
import batchJob.common.exception.ApBusinessException;
import batchJob.common.queue.JmsConfig;
import batchJob.common.utils.FolderUtils;
import batchJob.common.utils.StringFormatUtils;
import batchJob.model.XdaoSessionManager;

public class CFGEnqueuePart3ServiceImpl implements CFGEnqueuePart3Service {
	
	private static Logger LOGGER = LoggerFactory.getLogger("AP");
	private CpgSettingQuery cpgSettingQuery = new CpgSettingQueryImpl();

	@Override
	public void execute(final CPGEnqueuePart3Vo vo) {
		LOGGER.trace("execute");
		this.initParmeter(vo);

		final DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
		final List<CpgTranMainDo> cpgTranDos = this.queryNoneSendList(doXdaoSession);// 取得未傳送的XML檔案
		for (final CpgTranMainDo cpgTranDo : cpgTranDos) {

			this.enq(cpgTranDo, vo);
		}
	}

	private List<CpgTranPostDo> queryCpgTranPostDosBy(CpgTranMainDo cpgTranMainDo) {
		final DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
		final DoSqlWhere<CpgTranPostDo.COLUMNS> doSqlWhere = new DoSqlWhere<CpgTranPostDo.COLUMNS>();
		doSqlWhere.add(CpgTranPostDo.COLUMNS.FILENAME, cpgTranMainDo.getFilename());
		return doXdaoSession.selectPo(CpgTranPostDo.class, doSqlWhere);
	}

	private List<CpgTranDetailDo> queryCpgTranDetailDoBy(CpgTranPostDo cpgTranPostDo) {
		final DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
		final DoSqlWhere<CpgTranDetailDo.COLUMNS> doSqlWhere = new DoSqlWhere<CpgTranDetailDo.COLUMNS>();
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.FILENAME, cpgTranPostDo.getFilename());
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.POSTTYPE, cpgTranPostDo.getPosttype());
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.POSTSPECIALACCOUNT, cpgTranPostDo.getPostspecialaccount());
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.POSTTYPE, cpgTranPostDo.getPosttype());
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.TYPE, "C");
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.YEAR, cpgTranPostDo.getYear());
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.COUNTRYCODE, cpgTranPostDo.getCountrycode());
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.EXCHANGEAGENCY, cpgTranPostDo.getExchangeagency());
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.TOTALPACKAGENO, cpgTranPostDo.getTotalpackageno());
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.GOODTOTALPACKAGEYEAR, cpgTranPostDo.getGoodtotalpackageyear());
		doSqlWhere.add(CpgTranDetailDo.COLUMNS.GOODTOTALPACKAGENO, cpgTranPostDo.getGoodtotalpackageno());

		return doXdaoSession.selectPo(CpgTranDetailDo.class, doSqlWhere);
	}

	private void enq(final CpgTranMainDo cpgTranDo, final CPGEnqueuePart3Vo vo) {

		final CPGEnqueuePart3CpgTranMainVo mainVo = new CPGEnqueuePart3CpgTranMainVo();
		final List<CPGEnqueuePart3CpgTranVo> tranVos = new ArrayList<CPGEnqueuePart3CpgTranVo>();
		mainVo.setCpgTranMainDo(cpgTranDo);
		mainVo.setTranVos(tranVos);
		final List<CpgTranPostDo> cpgTranDetailDos = this.queryCpgTranPostDosBy(cpgTranDo);
		for (CpgTranPostDo cpgTranPostDo : cpgTranDetailDos) {
			final List<CpgTranDetailDo> cpgDetailDos = this.queryCpgTranDetailDoBy(cpgTranPostDo);
			final CPGEnqueuePart3CpgTranVo tranVo = new CPGEnqueuePart3CpgTranVo();
			tranVo.setCpgTranPostDo(cpgTranPostDo);
			tranVo.setCpgTranDetailDos(cpgDetailDos);
			tranVos.add(tranVo);

		}
		vo.setTranMainVo(mainVo);

		final CpgSettingPo cpgSettingDo = this.cpgSettingQuery.query(cpgTranDo.getPostspecialaccount());
		if (cpgSettingDo == null) {
			throw new ApBusinessException("取得資料錯誤");
		}

		final DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
		doXdaoSession.beginTransaction();
		try {
			cpgTranDo.setSendTime(YYYYMMDDUtils.getText());
			cpgTranDo.setSendflag(SendFlag.S.name());
			final File file = this.createXML(vo.getTranMainVo());
			doXdaoSession.updatePo(cpgTranDo);

			vo.setFilename(file.getName());
			vo.setM_recvId(cpgSettingDo.getRcvId());
			this.enqueu(vo);
			doXdaoSession.commit();
		} catch (final Exception e) {
			cpgTranDo.setSendflag(SendFlag.F.name());
			doXdaoSession.updatePo(cpgTranDo);

		}
	}

	private void enqueu(final CPGEnqueuePart3Vo vo) throws Exception {

		final JmsConfig jmsConfig = new JmsConfig();
		final QueService qs = new QueService();
		qs.setSysCode(vo.getM_sysCode());
		qs.setHubType(vo.getM_hubType());
		qs.setEnqDir(vo.getM_enqDir_Enq(), vo.getM_bkDir(), vo.getM_errDir());
		qs.setFsIpPort(jmsConfig.getmFsip(), //
				jmsConfig.getmFsport());//
		qs.setJMSVASInfo(//
				jmsConfig.getVasSetUrl(),//
				jmsConfig.getVasJmsUsername(),//
				jmsConfig.getVasJmsPassword());//
		qs.setLgQueId(vo.getM_lgQueId(), vo.getM_lgQueId());
		Connection con;
		con = DbFactory.open();
		if (qs.openTrans(QueTrans.ENQ_MODE, con) == QueTrans.RET_OK) {
			LOGGER.debug("enqueu sendId:{} ,recvid:{} ", vo.getM_sendId(), vo.getM_recvId());
			if (qs.EnQue(vo.getM_sendId(), vo.getM_recvId(), vo.getM_docName(),
					vo.getM_oriDir() + "/" + vo.getFilename(), vo.getFilename()) != QueTrans.RET_OK) {
				LOGGER.info("Enq 失敗!!");
			} else {
			}
		}
		qs.closeTrans(QueTrans.ENQ_MODE);
		con.close();

	}

	private File createXML(final CPGEnqueuePart3CpgTranMainVo enqueuePart3CpgTranMainVo) {
		final Document doc = DocumentHelper.createDocument();

		final Element message = doc.addElement("Message");
		final Element messageBody = message.addElement("MessageBody");

		final CpgTranMainDo mainVo = enqueuePart3CpgTranMainVo.getCpgTranMainDo();

		final Element msgFuncCode = messageBody.addElement("MsgFuncCode");
		final Element messageType = messageBody.addElement("MessageType");
		final Element postSpecialAccount = messageBody.addElement("PostSpecialAccount");

		msgFuncCode.addText(StringUtils.defaultString(mainVo.getMsgfunccode()));
		messageType.addText(StringUtils.defaultString(mainVo.getMessagetype()));
		postSpecialAccount.addText(StringUtils.defaultString(mainVo.getPostspecialaccount()));

		for (CPGEnqueuePart3CpgTranVo vo : enqueuePart3CpgTranMainVo.getTranVos()) {
			final Element postMainInfo = messageBody.addElement("PostMainInfo");
			final Element postType = postMainInfo.addElement("PostType");
			final Element postMBLNo = postMainInfo.addElement("PostMBLNo");
			final Element oriRcvFile = postMainInfo.addElement("OriRcvFile");
			final Element flightNo = postMainInfo.addElement("FlightNo");
			final Element flightDateTime = postMainInfo.addElement("FlightDateTime");
			final CpgTranPostDo cpgTranPostDo = vo.getCpgTranPostDo();
			postType.addText(StringUtils.defaultString(cpgTranPostDo.getPosttype()));
			postMBLNo.addText(StringUtils.defaultString(cpgTranPostDo.getPostmblno()));
			flightNo.addText(StringUtils.defaultString(cpgTranPostDo.getFlightno()));
			oriRcvFile.addText(StringUtils.defaultString(cpgTranPostDo.getOrircvfile()));
			flightDateTime.addText(StringUtils.defaultString(cpgTranPostDo.getFlightdatetime()));

			final Element Items = postMainInfo.addElement("Items");
			for (final CpgTranDetailDo cpgTranDetailDo : vo.getCpgTranDetailDos()) {
				final Element postNo = Items.addElement("PostNo");
				postNo.addText(StringFormatUtils.toObjectString(cpgTranDetailDo.getPostno()));
			}

		}

		FileWriter fw;
		try {
			final String pendFolderString = "/PFTZC/TMP/SNDFIL/ECPG";
			final String fileName = enqueuePart3CpgTranMainVo.getXMLFileName();
			final File pendDir = new File(pendFolderString, fileName);

			fw = new FileWriter(pendDir);
			final OutputFormat of = new OutputFormat(); // 格式化XML
			of.setIndentSize(4); // 設定 Tab 為 4 個空白
			of.setNewlines(true);// 設定 自動換行
			final XMLWriter xw = new XMLWriter(fw, of);
			xw.write(doc);
			xw.close();
			return pendDir;
		} catch (final IOException e) {
			throw new ApBusinessException("產製XML失敗", e);
		}

	}

	private List<CpgTranMainDo> queryNoneSendList(final DoXdaoSession xdaoSession) {

		final DoSqlWhere<CpgTranMainDo.COLUMNS> sqlWhere = new DoSqlWhere<CpgTranMainDo.COLUMNS>();
		sqlWhere.add(CpgTranMainDo.COLUMNS.SENDFLAG, SendFlag.P.name());
		return xdaoSession.selectPo(CpgTranMainDo.class, sqlWhere);

	}

	private void initParmeter(final CPGEnqueuePart3Vo vo) {

		final String pendFolderString = ApContext.getContext().getSetting("CPG_ENQ_SEND_PENDING_DIR");
		FolderUtils.createFolders(pendFolderString);

	}

}

package batchJob.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.common.date.YYYYMMDDUtils;
import com.tradevan.wcommons.ApContext;

import batchJob.been.EventCTranADo;
import batchJob.been.EventCTranBDo;
import batchJob.been.EventCTranCDo;
import batchJob.been.EventCTranMainDo;
import batchJob.been.po.CpgSettingPo;
import batchJob.been.vo.cpg.CPGSend2FtpCPGVo;
import batchJob.common.codegenerate.SendFlag;
import batchJob.common.exception.ApBusinessException;
import batchJob.common.file.FileCommand;
import batchJob.common.file.impl.FileCommandFacade;
import batchJob.common.ftp.FtpClient;
import batchJob.common.ftp.FtpClientFactory;
import batchJob.common.ftp.FtpConfigSetting;
import batchJob.common.queue.Observer;
import batchJob.common.queue.Subject;
import batchJob.common.utils.FolderUtils;
import batchJob.common.utils.StringFormatUtils;

/**
 * 將 CTZLFPGProcess 產生的暫存檔.flg上傳至
 *
 *
 */
public class CPGSend2Ftp implements Observer {
	
	private static Logger LOGGER = LoggerFactory.getLogger("AP");
	final FileCommandFacade fileCommandFacade = new FileCommandFacade();
	final CPGService cpgService = new CPGServiceImpl();

	public static void main(String[] args) {
		CPGSend2Ftp send2Ftp = new CPGSend2Ftp();
		send2Ftp.execution();
	}
	
	public void execution() {
		try {
			final CPGSend2FtpCPGVo vo = new CPGSend2FtpCPGVo();
			final List<EventCTranMainDo> eventCTranMainDos = cpgService.queryEventCTranMainDos();// 待傳送
			for (final EventCTranMainDo eventCTranMainDo : eventCTranMainDos) {
				vo.setEventCTranMainDo(eventCTranMainDo);
				this.process(eventCTranMainDo, vo);
			}
		} catch (Exception e) {
			LOGGER.error("傳送檔案失敗", e);
			throw new ApBusinessException("傳送檔案失敗", e);
		}
	}

	/**
	 *
	 * @param fileile
	 *            正在處理之檔案
	 * @param vo
	 */
	private void process(final EventCTranMainDo eventCTranMainDo, //
			final CPGSend2FtpCPGVo vo) {//

		final List<EventCTranADo> eventCTranADos = this.cpgService.queryEventCTranADos(eventCTranMainDo.getFilename());
		final List<EventCTranBDo> eventCTranBDos = this.cpgService.queryEventCTranBDos(eventCTranMainDo.getFilename());
		final List<EventCTranCDo> eventCTranCDos = this.cpgService.queryEventCTranCDos(eventCTranMainDo.getFilename());

		final FileCommandFacade facade = new FileCommandFacade();

		final String ftpFileString = eventCTranMainDo.getFilename().replace(".xml", "")
				.replace("CPG.", StringUtils.EMPTY)
				+ ".txt";
		LOGGER.debug("File name:" + ftpFileString);

		final String pendFolder = this.getString("CPG_SEND_PENDING_DIR");
		final String errFolder = this.getString("CPG_SEND_ERR_DIR");
		final String okFolder = this.getString("CPG_SEND_OK_DIR");

		FolderUtils.createFolders(pendFolder);
		FolderUtils.createFolders(errFolder);
		FolderUtils.createFolders(okFolder);

		final File tmpFtpFile = new File(pendFolder, ftpFileString);

		LOGGER.debug("pendFolder:" + pendFolder);
		LOGGER.debug("errFolder:" + errFolder);
		LOGGER.debug("okFolder:" + okFolder);
		LOGGER.debug("tmpFtpFile:" + tmpFtpFile.getPath());
		final FileCommand dileCommand = facade.getFileCommand();
		FtpClient ftpClient = null;
		try {

			String postspecialaccount = "";
			for (EventCTranADo eventCTranADo : eventCTranADos) {
				postspecialaccount = eventCTranADo.getPostspecialaccount();
			}
			if (StringUtils.isBlank(postspecialaccount)) {
				throw new ApBusinessException(String.format("檔案：%s 特戶戶號空白", eventCTranMainDo.getFilename()));
			}
			final CpgSettingPo cpgSettingPo = FtpConfigSetting.lookup(postspecialaccount);
			ftpClient = FtpClientFactory.get(cpgSettingPo);

			eventCTranMainDo.setSendflag(SendFlag.S.name());// 成功
			eventCTranMainDo.setSendTime(YYYYMMDDUtils.getText());
			ftpClient.connect();
			final String gcontentList = this.getEcContet(eventCTranMainDo, eventCTranADos,// ec內容
					eventCTranBDos, eventCTranCDos);

			dileCommand.createFile(tmpFtpFile, Arrays.asList(gcontentList));// 建立待傳檔案
			dileCommand.send2Ftp(ftpClient, tmpFtpFile, cpgSettingPo.getSendFtpPath());// 傳送檔案
			LOGGER.debug("檔案完成上傳");
		} catch (final Exception e) {
			LOGGER.error("上傳檔案失敗!", e);
			eventCTranMainDo.setSendflag(SendFlag.F.name());// 失敗傳送
			// dileCommand.delete(new File(errFolder, ftpFileString));

		} finally {
			this.cpgService.updateEventCTranMainDo(eventCTranMainDo);
			if (ftpClient != null) {
				ftpClient.close();
			}

			LOGGER.debug("CPGSend2Ftp process finaish");
		}

	}

	private String getString(final String seetingName) {
		return ApContext.getContext().getSetting(seetingName);
	}

	private String getEcContet(final EventCTranMainDo eventCTranMainDo, List<EventCTranADo> eventCTranADos,
			List<EventCTranBDo> eventCTranBDos, List<EventCTranCDo> eventCTranCDos) {
		StringBuffer stringBuffer = new StringBuffer();

		for (EventCTranADo eventCTranADo : eventCTranADos) {
			// TYPE A
			List<String> contenListA = new ArrayList<String>();
			contenListA.add(eventCTranADo.getPostspecialaccount());
			contenListA.add(eventCTranADo.getPosttype());
			contenListA.add(eventCTranADo.getType());
			contenListA.add(eventCTranADo.getYear());
			contenListA.add(StringFormatUtils.toObjectString(eventCTranADo.getCountrycode()));
			contenListA.add(StringFormatUtils.toObjectString(eventCTranADo.getExchangeagency()));
			contenListA.add(StringFormatUtils.toObjectString(String.format("%04d",eventCTranADo.getTotalpackageno().intValue())));
			contenListA.add(eventCTranADo.getPostmblno());
			contenListA.add(eventCTranADo.getLastyeartotalpackageno());
			contenListA.add(eventCTranADo.getSealdatetime());
			contenListA.add(StringFormatUtils.toObjectString(eventCTranADo.getTotalqty()));
			contenListA.add(StringFormatUtils.toObjectString(eventCTranADo.getTotalpagecount()));
			contenListA.add(StringFormatUtils.toObjectString(eventCTranADo.getTotalbagnumber()));
			contenListA.add(StringFormatUtils.toObjectString(eventCTranADo.getTotalweight()));
			stringBuffer.append(StringUtils.join(contenListA, ",") + "\n");
		}

		for (EventCTranBDo ec : eventCTranBDos) {
			List<String> content = new ArrayList<String>();
			content.add(ec.getPostspecialaccount()); // 資料來源
			content.add(ec.getPosttype()); // 郵件類別
			content.add(ec.getType()); // 格 式
			content.add(ec.getYear()); // 年 度
			content.add(StringFormatUtils.toObjectString(ec.getCountrycode())); // 國名代碼
			content.add(StringFormatUtils.toObjectString(ec.getExchangeagency())); // 互換局名
			content.add(StringFormatUtils.toObjectString(String.format("%04d", ec.getTotalpackageno().intValue()))); // 總包號碼
			content.add(StringFormatUtils.toObjectString(ec.getBagno())); // 袋 序
			content.add(ec.getLastmark()); // 最後一袋
			content.add(StringFormatUtils.toObjectString(ec.getPageno())); // 該袋頁數
			content.add(StringFormatUtils.toObjectString(ec.getQty())); // 件 數
			content.add(StringFormatUtils.toObjectString(ec.getWeight())); // 重 量
			content.add(ec.getSealdatetime()); // 封發時間
			stringBuffer.append(StringUtils.join(content, ",") + "\n");

		}
		for (EventCTranCDo ec : eventCTranCDos) {
			List<String> content = new ArrayList<String>();
			content.add(ec.getPostspecialaccount()); // 資料來源
			content.add(ec.getPosttype()); // 郵件類別
			content.add(ec.getType()); // 格 式
			content.add(ec.getYear()); // 年 度
			content.add(StringFormatUtils.toObjectString(ec.getCountrycode())); // 國名代碼
			content.add(StringFormatUtils.toObjectString(ec.getExchangeagency())); // 互換局名
			content.add(StringFormatUtils.toObjectString(String.format("%04d", ec.getTotalpackageno().intValue()))); // 總包號碼

			if (ec.getPosttype().matches("(3|8|0)")) {// 平信
				content.add(ec.getPostno()); // 郵件號碼
			} else {
				content.add(StringFormatUtils.toObjectString(ec.getBagno())); // 袋 序
				content.add(StringFormatUtils.toObjectString(ec.getPageno())); // 頁 序
				content.add(StringFormatUtils.toObjectString(ec.getCellno())); // 格 次
				content.add(ec.getPostno()); // 郵件號碼
				content.add(StringFormatUtils.toObjectString(ec.getWeight())); // 重 量
				content.add(ec.getSealdatetime()); // 封發時間
				content.add(ec.getProvincetype()); // 省 別
				content.add(ec.getBoxtype()); // 箱 別
			}
			stringBuffer.append(StringUtils.join(content, ",") + "\n");

		}
		return stringBuffer.toString();

	}
	
	@Override
	public void update(final Subject subject) {
		this.execution();
	}

}

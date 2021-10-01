package batchJob.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tradevan.wcommons.ApContext;

import batchJob.been.po.CpgSettingPo;
import batchJob.been.vo.cpg.CPGEnqueuePart1Vo;
import batchJob.common.file.FileCommand;
import batchJob.common.file.impl.FileCommandFacade;
import batchJob.common.ftp.FtpClient;
import batchJob.common.ftp.FtpClientFactory;
import batchJob.common.job.batchJob.task.PermanentWork;

public class CpgFTPListenPermanentWork implements PermanentWork {
	
	private static String NOTE_FLG = ".flg";
	private static Logger LOGGER = LoggerFactory.getLogger("AP");
	
	private FileCommandFacade fileCommandFacade = new FileCommandFacade();
	
	private CpgSettingPo cpgSettingPo;
	
	public CpgFTPListenPermanentWork(CpgSettingPo cpgSettingPo) {
		this.cpgSettingPo = cpgSettingPo;
	}
	
	@Override
	public void work() {
		final CPGEnqueuePart1Vo vo = new CPGEnqueuePart1Vo();
		vo.setPendingDir(ApContext.getContext().getSetting("CPG_ENQ_PENDING_DIR"));
		vo.setErrDir(ApContext.getContext().getSetting("CPG_ENQ_ERR_DIR"));
		vo.setOkDir(ApContext.getContext().getSetting("CPG_ENQ_OK_DIR"));
		vo.setCpgSettingDo(this.cpgSettingPo);
		this.executeForCpgSetting(vo);

	}

	private void executeForCpgSetting(CPGEnqueuePart1Vo vo) {
		final CpgSettingPo cpgSettingDo = vo.getCpgSettingDo();
		final FileCommand fileCommand = this.fileCommandFacade.getFileCommand();
		final FtpClient ftpClient = FtpClientFactory.get(cpgSettingDo);

		try {
			ftpClient.connect();
			final List<String> filesList = ftpClient.searchAllFileName(cpgSettingDo.getGetFtpPath());// 所有要抓下來的檔名.
			LOGGER.debug("moveFolder:" + vo.getPendingDir() + File.separator);
			fileCommand.ftpMoveFiles2LocalFilesCommand(ftpClient, vo.getPendingDir() + File.separator,
					cpgSettingDo.getGetFtpPath());// 搬到local
			for (final String files : filesList) {
				fileCommand.createFile(new File(vo.getPendingDir(), files + NOTE_FLG), Arrays.asList("ok"));// 下載完成註記
			}
		} catch (final Exception e) {
			LOGGER.error("error:錯誤", e);
		} finally {
			ftpClient.close();
		}

	}
	
	@Override
	public String getId() {
		return cpgSettingPo.getPostspecialaccount() + "_PermanentJob";
	}

	@Override
	public String getName() {
		return cpgSettingPo.getPostspecialaccount()+ "_PermanentJob";
	}

}

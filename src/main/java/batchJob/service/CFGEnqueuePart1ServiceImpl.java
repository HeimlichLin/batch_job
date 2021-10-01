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
import batchJob.common.queue.QueueQueryComponent;
import batchJob.common.queue.QueueQueryComponentImpl;
import batchJob.common.utils.FolderUtils;

public class CFGEnqueuePart1ServiceImpl implements CFGEnqueuePart1Service {
	
	private static String NOTE_FLG = ".flg";
	private static Logger LOGGER = LoggerFactory.getLogger("AP");
	private QueueQueryComponent queueQueryComponent = new QueueQueryComponentImpl();
	private FileCommandFacade fileCommandFacade = new FileCommandFacade();

	@Override
	public void execute(final CPGEnqueuePart1Vo vo) {

		vo.setPendingDir(ApContext.getContext().getSetting("CPG_ENQ_PENDING_DIR"));
		vo.setErrDir(ApContext.getContext().getSetting("CPG_ENQ_ERR_DIR"));
		vo.setOkDir(ApContext.getContext().getSetting("CPG_ENQ_OK_DIR"));

		FolderUtils.createFolders(vo.getPendingDir());
		FolderUtils.createFolders(vo.getErrDir());
		FolderUtils.createFolders(vo.getOkDir());

		final File pendFile = new File(vo.getPendingDir());
		pendFile.mkdirs();

		final List<CpgSettingPo> cpgSettingDos = this.queueQueryComponent.getCpgSettingDos();
		for (final CpgSettingPo cpgSettingDo : cpgSettingDos) {
			LOGGER.debug("特約戶號:" + cpgSettingDo.getPostspecialaccount());
			vo.setCpgSettingDo(cpgSettingDo);
			this.executeForCpgSetting(vo);
		}

	}

	private void executeForCpgSetting(final CPGEnqueuePart1Vo vo) {
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

}

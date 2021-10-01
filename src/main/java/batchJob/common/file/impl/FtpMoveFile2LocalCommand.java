package batchJob.common.file.impl;

import java.io.File;

import batchJob.common.file.Command;
import batchJob.common.ftp.FtpClient;

/**
 * 指定FTP上某位置之檔案下載
 *
 *
 */

public class FtpMoveFile2LocalCommand implements Command {
	
	final String remoteFileName;//
	final String localPath;
	final String defalutPath;
	private FtpClient ftpClient;
	private File downloadFile;
	private boolean isAutoCreate = true;// 自動建立相關資料夾位置
	
	public FtpMoveFile2LocalCommand(//
			final String remoteFileName,//
			final String localPath, //
			final FtpClient ftpClient, final String defalutPath) {//
		super();
		this.remoteFileName = remoteFileName;
		this.localPath = localPath;
		this.ftpClient = ftpClient;
		this.defalutPath = defalutPath;
	}

	@Override
	public void execute() {
		this.downloadFile = this.ftpClient.moveFile2Local(this.remoteFileName, this.localPath, this.defalutPath);

	}

	@Override
	public void unio() {
		this.ftpClient.putFile(this.defalutPath, this.downloadFile);

	}

}

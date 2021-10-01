package batchJob.common.file.impl;

import java.io.File;

import batchJob.common.file.Command;
import batchJob.common.ftp.FtpClient;

/**
 * FTP傳遞檔案
 *
 *
 */
public class FileFtpSendCommand implements Command {
	
	private FtpClient ftpClient;
	final File localFile;
	final String defalutPath;

	public FileFtpSendCommand(final FtpClient ftpClient, final File localFile, final String path) {
		this.ftpClient = ftpClient;
		this.localFile = localFile;
		this.defalutPath = path;
	}

	@Override
	public void execute() {
		this.ftpClient.putFile(this.defalutPath, this.localFile);

	}

	@Override
	public void unio() {
		this.ftpClient.delFile(this.defalutPath, this.localFile.getName());
	}

}

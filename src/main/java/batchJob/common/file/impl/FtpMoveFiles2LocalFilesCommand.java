package batchJob.common.file.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import batchJob.common.exception.ApBusinessException;
import batchJob.common.file.Command;
import batchJob.common.ftp.FtpClient;
import batchJob.common.ftp.FtpClient.Filter;
import batchJob.common.ftp.impl.NoFilter;

/**
 * 下載整個folder檔案至localPath
 *
 *
 */
public class FtpMoveFiles2LocalFilesCommand implements Command {
	
	private FtpClient ftpClient;
	private String localPath;
	private List<File> files = new ArrayList<File>();
	private Filter filter = new NoFilter();
	final String defalutPath;

	public FtpMoveFiles2LocalFilesCommand(final FtpClient ftpClient, final String localPath, final String defalutPath) {
		this.localPath = localPath;
		this.ftpClient = ftpClient;
		this.defalutPath = defalutPath;
	}

	public FtpMoveFiles2LocalFilesCommand(final FtpClient ftpClient, final String localPath, final Filter filter,
			final String defalutPath) {
		this.localPath = localPath;
		this.filter = filter;
		this.ftpClient = ftpClient;
		this.defalutPath = defalutPath;
		if (filter == null) {
			throw new ApBusinessException("過濾器不得為null");
		}
	}
	
	@Override
	public void execute() {
		final List<File> files = this.ftpClient.moveFile2Local(//
				this.localPath,//
				this.filter, this.defalutPath);//
		this.files.addAll(files);

	}
	
	@Override
	public void unio() {
		Collections.reverse(this.files);
		for (final File file : this.files) {
			file.delete();
		}

	}

}

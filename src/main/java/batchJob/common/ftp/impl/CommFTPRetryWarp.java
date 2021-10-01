package batchJob.common.ftp.impl;

import java.io.File;
import java.util.List;

import batchJob.common.ftp.FtpClient;
import batchJob.common.ftp.impl.ReTryClient.ReTryWarpListener;

public class CommFTPRetryWarp implements FtpClient {
	
	private FtpClient ftpClient;
	protected static final int TRY_TIME = 5;//retry次數
	
	public CommFTPRetryWarp(final FtpClient ftpClient) {
		super();
		this.ftpClient = ftpClient;
	}
	
	@Override
	public void connect() {
		final ReTryClient<String> reTryClient = new ReTryClient<String>(TRY_TIME);
		reTryClient.update(new ReTryWarpListener<String>() {
			@Override
			public String myWokr() {
				CommFTPRetryWarp.this.ftpClient.connect();
				return "";
			}
		});

	}

	@Override
	public void putFile(final String ftpPath, final File localFile) {
		final ReTryClient<String> reTryClient = new ReTryClient<String>(TRY_TIME);
		reTryClient.update(new ReTryWarpListener<String>() {
			@Override
			public String myWokr() {
				CommFTPRetryWarp.this.ftpClient.putFile(ftpPath, localFile);
				return "";
			}
		});

	}

	@Override
	public File moveFile2Local(final String remoteFileName, final String localPath, final String path) {
		final ReTryClient<File> reTryClient = new ReTryClient<File>(TRY_TIME);
		return reTryClient.update(new ReTryWarpListener<File>() {
			@Override
			public File myWokr() {
				return CommFTPRetryWarp.this.ftpClient.moveFile2Local(remoteFileName, localPath, path);
			}
		});
	}

	@Override
	public List<File> moveFile2Local(final String localPath, final Filter filter, final String path) {
		final ReTryClient<List<File>> reTryClient = new ReTryClient<List<File>>(TRY_TIME);
		return reTryClient.update(new ReTryWarpListener<List<File>>() {
			@Override
			public List<File> myWokr() {
				return CommFTPRetryWarp.this.ftpClient.moveFile2Local(localPath, filter, path);
			}
		});

	}

	@Override
	public List<File> moveFile2Local(final String localPath, final String path) {
		final ReTryClient<List<File>> reTryClient = new ReTryClient<List<File>>(TRY_TIME);
		return reTryClient.update(new ReTryWarpListener<List<File>>() {
			@Override
			public List<File> myWokr() {
				return CommFTPRetryWarp.this.ftpClient.moveFile2Local(localPath, path);
			}
		});
	}

	@Override
	public List<String> searchAllFileName(final String path) {
		final ReTryClient<List<String>> reTryClient = new ReTryClient<List<String>>(TRY_TIME);
		return reTryClient.update(new ReTryWarpListener<List<String>>() {
			@Override
			public List<String> myWokr() {
				return CommFTPRetryWarp.this.ftpClient.searchAllFileName(path);
			}
		});
	}

	@Override
	public void delFile(final String ftpPath, final String remoteFileName) {
		final ReTryClient<String> reTryClient = new ReTryClient<String>(TRY_TIME);
		reTryClient.update(new ReTryWarpListener<String>() {
			@Override
			public String myWokr() {
				CommFTPRetryWarp.this.ftpClient.delFile(ftpPath, remoteFileName);
				return "";
			}
		});

	}

	@Override
	public void close() {
		final ReTryClient<String> reTryClient = new ReTryClient<String>(TRY_TIME);
		reTryClient.update(new ReTryWarpListener<String>() {
			@Override
			public String myWokr() {
				CommFTPRetryWarp.this.ftpClient.close();;
				return "";
			}
		});
	}

}

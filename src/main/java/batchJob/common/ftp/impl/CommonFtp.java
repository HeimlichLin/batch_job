package batchJob.common.ftp.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.exception.ApBusinessException;
import batchJob.common.ftp.FtpClient;
import batchJob.common.ftp.FtpSetting;

public class CommonFtp implements FtpClient {

	private static Logger LOGGER = LoggerFactory.getLogger(CommonFtp.class);
	protected FtpSetting ftpConfig;
	protected FTPClient deFtpClient;

	public CommonFtp(final FtpSetting ftpConfig) {
		if (ftpConfig == null) {
			throw new ApBusinessException("設定檔錯誤 ");
		}
		this.ftpConfig = ftpConfig;

	}

	@Override
	public void connect() {
		try {
			this.deFtpClient = new FTPClient();
			this.deFtpClient.enterLocalPassiveMode();
			this.deFtpClient.connect(this.ftpConfig.getHost());
			if (!this.deFtpClient.login(this.ftpConfig.getUserId(), this.ftpConfig.getPwd())) {
				throw new ApBusinessException("登入失敗");
			}
		} catch (final Exception e) {
			e.printStackTrace();
			LOGGER.error("連線失敗", e);
			throw new ApBusinessException("連接FTP發生異常！", e);
		}

	}

	@Override
	public void putFile(final String ftpPath, final File localFile) {
		// 設定檔案類型
		FileInputStream input = null;
		try {
			this.initFTP().setBufferSize(1024);
			this.initFTP().setFileType(FTP.BINARY_FILE_TYPE);
			// 設定傳檔的模式
			this.initFTP().enterLocalPassiveMode();
			// 切換到指定目錄
			this.initFTP().changeWorkingDirectory(ftpPath);
			// 開始上傳
			input = new FileInputStream(localFile);
			if (!this.initFTP().storeFile(localFile.getName(), input)) {
				LOGGER.warn("上傳失敗:{}/{}", ftpPath, localFile);
			}
		} catch (final Exception e) {
			LOGGER.error("上傳失敗", e);
			e.printStackTrace();
			throw new ApBusinessException("上傳檔案至FTP檔案異常!", e);
		} finally {
			IOUtils.closeQuietly(input);

		}

	}

	@Override
	public void close() {
		try {
			if (this.initFTP() != null) {
				this.initFTP().logout();
				this.initFTP().disconnect();
			}
		} catch (final Exception e) {
			e.printStackTrace();
			LOGGER.error("關閉失敗", e);
			throw new ApBusinessException("關閉FTP連接發生異常！", e);
		}
	}

	@Override
	public void delFile(final String ftpPath, final String remoteFileName) {
		// 切換到指定目錄
		try {
			this.initFTP().changeWorkingDirectory(ftpPath);
			if (!this.initFTP().deleteFile(remoteFileName)) {
				LOGGER.warn("刪除失敗:{}/{}", ftpPath, remoteFileName);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			LOGGER.error("刪除失敗", e);
			throw new ApBusinessException("刪除FTP上面檔案不成功！", e);
		}

	}

	@Override
	public File moveFile2Local(final String remoteFileName, final String localPath, final String path) {
		FileOutputStream os = null;
		try {
			// 設定檔案類型
			this.initFTP().setFileType(FTP.BINARY_FILE_TYPE);
			// 設定傳檔的模式
			this.initFTP().enterLocalPassiveMode();
			// 切換到指定目錄
			this.initFTP().changeWorkingDirectory(path);
			// 開始下載
			final File returnFile = new File(localPath, remoteFileName);
			if (returnFile.exists()) {
				LOGGER.warn("檔案:{}存在，刪除", returnFile.getPath());
				returnFile.delete();
			}
			if (!returnFile.getParentFile().exists()) {
				LOGGER.warn("上層資料夾:{}不存在，建立相關資料夾", returnFile.getParentFile());
				returnFile.getParentFile().mkdirs();
			}
			os = new FileOutputStream(returnFile);
			this.initFTP().retrieveFile(remoteFileName, os);
			IOUtils.closeQuietly(os);
			this.initFTP().deleteFile(remoteFileName);
			return returnFile;
		} catch (final Exception e) {
			e.printStackTrace();
			LOGGER.error("下載FTP資料失敗", e);
			throw new ApBusinessException("下載FTP資料失敗", e);
		}

	}

	@Override
	public List<File> moveFile2Local(final String localPath, final Filter filter, final String path) {
		final List<File> files = new ArrayList<File>();
		try {
			final String changePath = path;
			// 切換到指定目錄
			this.initFTP().changeWorkingDirectory(changePath);
			for (final FTPFile ftpFile : this.initFTP().listFiles()) {
				if (ftpFile.isFile()) {
					if (filter.accept(ftpFile.getName())) {
						final File file = this.moveFile2Local(ftpFile.getName(), localPath, path);
						files.add(file);
					}
				}

			}
		} catch (final Exception e) {
			e.printStackTrace();
			LOGGER.error("下載FTP資料失敗", e);
			throw new ApBusinessException("下載FTP資料失敗", e);
		}
		return files;
	}

	@Override
	public List<File> moveFile2Local(final String localPath, final String path) {
		return this.moveFile2Local(localPath, new NoFilter(), path);
	}

	@Override
	public List<String> searchAllFileName(final String path) {
		final List<String> files = new ArrayList<String>();
		try {
			final String changePath = path;
			// 切換到指定目錄
			this.initFTP().changeWorkingDirectory(changePath);
			for (final FTPFile ftpFile : this.initFTP().listFiles()) {
				if (ftpFile.isFile()) {
					files.add(ftpFile.getName());
				}

			}
		} catch (final Exception e) {
			e.printStackTrace();
			LOGGER.error("下載FTP上面檔案", e);
			throw new ApBusinessException("下載FTP上面檔案", e);
		}
		return files;
	}

	private FTPClient initFTP() {
		if (this.deFtpClient == null) {
			this.connect();
		}
		return this.deFtpClient;
	}
}


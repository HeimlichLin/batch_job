package batchJob.common.file;

import java.io.File;
import java.util.List;

import batchJob.common.ftp.FtpClient;
import batchJob.common.ftp.FtpClient.Filter;

public interface FileCommand {
	
	void rollback();

	void commit();

	/**
	 * 產生檔案
	 *
	 * @param f
	 * @param content
	 */
	void createFile(File f, List<String> content);

	/**
	 * 刪除
	 *
	 * @param f
	 */
	void delete(File f);

	/**
	 * 移動
	 *
	 * @param src
	 * @param tartge
	 */
	void renameTo(File src, File tartge);

	void send2Ftp(FtpClient ftpClient, File localFile,final String defalutPath);

	/**
	 * FTP檔案移動至LocalPath
	 *
	 * @param remotePath
	 * @param remoteFileName
	 * @param localPath
	 * @param ftpPath
	 * @param ftpClient
	 * @return
	 */
	void ftpmMoveFile2Local(String remoteFileName,//
			String localPath, //
			FtpClient ftpClient, final String defalutPath);

	void ftpMoveFiles2LocalFilesCommand(FtpClient ftpClient, String localPath, final String defalutPath);

	/**
	 * 過濾器
	 *
	 * @param remotePath
	 * @param localPath
	 * @param filter
	 */
	void ftpMoveFiles2LocalFilesCommand(FtpClient ftpClient, String localPath, Filter filter, final String defalutPath);

}
package batchJob.common.ftp;

import java.io.File;
import java.util.List;

public interface FtpClient {
	
	public interface Filter {
		boolean accept(String File);
	}

	/**
	 * 開始連線至FTP SERVER
	 */
	void connect();

	/**
	 * 上傳檔案至FTP SERVER
	 */
	void putFile(final String ftpPath, File localFile);

	// void putFile(File localFile);

	/**
	 * 下載檔案至local從ftp
	 *
	 * @param remotePath
	 * @param remoteFileName
	 * @param localPath
	 */
	File moveFile2Local(//
			String remoteFileName,//
			String localPath, final String path);//

	/**
	 * 下載所有FTP之檔案
	 *
	 * @param remotePath
	 *            ex "/ftp"
	 * @param localPath
	 *            :ex "/download/"
	 */
	List<File> moveFile2Local(//
			String localPath,//
			Filter filter, final String path);//

	List<File> moveFile2Local(//
			String localPath, final String path);//

	List<String> searchAllFileName(final String path);//
	//
	// /**
	// * 刪除在FTP上面之檔案
	// *
	// * @param ftpPath
	// * @param remoteFileName
	// */
	// void delFile(final String ftpPath, String remoteFileName);

	void delFile(String ftpPath, String remoteFileName);

	/**
	 * 關閉連線
	 */
	void close();//
}

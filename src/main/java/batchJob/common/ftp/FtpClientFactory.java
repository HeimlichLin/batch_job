package batchJob.common.ftp;

import batchJob.common.ftp.impl.CommFTPRetryWarp;
import batchJob.common.ftp.impl.CommonFtp;
import batchJob.common.ftp.impl.CommonFtps;
import batchJob.common.ftp.FtpClient;

public class FtpClientFactory {

	/***
	 * 取得FTP_CLIENT
	 * 
	 * @param postspecialaccount
	 * @param ftpType
	 * @return
	 */
	public final static FtpClient get(final String postspecialaccount) {
		final FtpSetting setting = FtpConfigSetting.lookup(postspecialaccount);
		return FtpClientFactory.get(setting);
	}

	/**
	 * 取得FTP_CLIENT
	 * 
	 * @param ftpSetting
	 * @return
	 */
	public final static FtpClient get(final FtpSetting ftpSetting) {
		FtpClient ftpClient = null;
		if (ftpSetting instanceof FtpsSetting) {
			ftpClient = new CommonFtps((FtpsSetting) ftpSetting);
		} else {
			ftpClient = new CommonFtp(ftpSetting);
		}

		return new CommFTPRetryWarp(ftpClient);

	}
}

package batchJob.common.ftp;

/**
 * 
 * FTPS環境設定
 */
public interface FtpsSetting extends FtpSetting {
	/**
	 * 是否隱性
	 * @return
	 */
	public boolean isImpicit();
}

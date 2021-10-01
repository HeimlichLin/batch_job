package batchJob.common.ftp.impl;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.exception.ApBusinessException;
import batchJob.common.ftp.FtpsSetting;

/**
 * FTPS連線模式
 * 
 */
public class CommonFtps extends CommonFtp {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CommonFtps.class);
	private FtpsSetting ftpsSetting;
	
	public CommonFtps(final FtpsSetting ftpConfig) {
		super(ftpConfig);
		this.ftpsSetting = ftpConfig;
	}
	
	public FtpsSetting getFtpsSetting() {
		return this.ftpsSetting;
	}

	public void setFtpsSetting(final FtpsSetting ftpsSetting) {
		this.ftpsSetting = ftpsSetting;
	}

	final TrustManager[] trustManager = new TrustManager[] { new X509TrustManager() {
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkClientTrusted(final X509Certificate[] certs, final String authType) {
		}

		@Override
		public void checkServerTrusted(final X509Certificate[] certs, final String authType) {
		}
	} };
	
	@Override
	public void connect() {
		try {

			SSLContext sslContext = null;
			sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, trustManager, new SecureRandom());

			this.deFtpClient = new FTPSClient(this.ftpsSetting.isImpicit(), sslContext);
			this.deFtpClient.connect(this.ftpConfig.getHost(), Integer.parseInt(this.ftpConfig.getPort()));

			// 確認是否登陸
			final int reply = this.deFtpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new ApBusinessException("連線失敗:" + reply);
			}
			if (this.deFtpClient instanceof FTPSClient) {
				((FTPSClient) this.deFtpClient).execPBSZ(0);
				((FTPSClient) this.deFtpClient).execPROT("P");
			}

			this.deFtpClient.login(this.ftpConfig.getUserId(), this.ftpConfig.getPwd());
			this.deFtpClient.enterLocalPassiveMode();

		} catch (final Exception e) {
			LOGGER.error("連線失敗", e);
			throw new ApBusinessException("連線失敗", e);
		}
	}

}

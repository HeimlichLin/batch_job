package batchJob.been.po;

import batchJob.common.ftp.FtpsSetting;

public class CpgSettingPo implements ICpgSettingPo, FtpsSetting {
	
	public enum COLUMNS {
		POSTSPECIALACCOUNT("特約號碼", true), //
		HOST("host", false), //
		USERID("帳號", false), //
		PWD("密碼", false), //
		PORT("PORT", false), //
		RCV_ID("接收郵箱", false), //
		BF_NO("監管編號", false), //
		MESSAGETYPE("(F：自轉郵；C：貨轉郵)", false), //
		SEND_FTP_PATH("FTP傳送ec路徑", false), //
		GET_FTP_PATH("FTP傳送Xml路徑", false), //
		;//
		final String chineseName;
		final boolean isPK;

		private COLUMNS(String chineseName, boolean isPK) {
			this.chineseName = chineseName;
			this.isPK = isPK;
		}

		public String getChineseName() {
			return chineseName;
		}

		public boolean isPK() {
			return isPK;
		}
	}

	private String postspecialaccount;// 特約號碼
	private String host;// host
	private String userid;// 帳號
	private String pwd;// 密碼
	private String port;// port
	private String rcvId;// 接收郵箱
	private String bfNo;// 監管編號
	private String messagetype;// (f：自轉郵；c：貨轉郵)
	private String sendFtpPath;// ftp傳送ec路徑
	private String getFtpPath;// ftp傳送xml路徑

	public String getPostspecialaccount() {
		return this.postspecialaccount;
	}

	public void setPostspecialaccount(String value) {
		this.postspecialaccount = value;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String value) {
		this.host = value;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String value) {
		this.userid = value;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String value) {
		this.pwd = value;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String value) {
		this.port = value;
	}

	public String getRcvId() {
		return this.rcvId;
	}

	public void setRcvId(String value) {
		this.rcvId = value;
	}

	public String getBfNo() {
		return this.bfNo;
	}

	public void setBfNo(String value) {
		this.bfNo = value;
	}

	public String getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(String value) {
		this.messagetype = value;
	}

	public String getSendFtpPath() {
		return this.sendFtpPath;
	}

	public void setSendFtpPath(String value) {
		this.sendFtpPath = value;
	}

	public String getGetFtpPath() {
		return this.getFtpPath;
	}

	public void setGetFtpPath(String value) {
		this.getFtpPath = value;
	}

	@Override
	public String getUserId() {
		return userid;
	}

	@Override
	public boolean isImpicit() {
		return false;
	}
}

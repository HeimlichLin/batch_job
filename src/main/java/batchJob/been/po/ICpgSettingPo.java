package batchJob.been.po;

public interface ICpgSettingPo {
	
	//get特約號碼
		public String getPostspecialaccount();

		//set特約號碼
		public void setPostspecialaccount(String postspecialaccount);
		//gethost
		public String getHost();

		//sethost
		public void setHost(String host);
		//get帳號
		public String getUserid();

		//set帳號
		public void setUserid(String userid);
		//get密碼
		public String getPwd();

		//set密碼
		public void setPwd(String pwd);
		//getPORT
		public String getPort();

		//setPORT
		public void setPort(String port);
		//get接收郵箱
		public String getRcvId();

		//set接收郵箱
		public void setRcvId(String rcvId);
		//get監管編號
		public String getBfNo();

		//set監管編號
		public void setBfNo(String bfNo);
		//get(F：自轉郵；C：貨轉郵)
		public String getMessagetype();

		//set(F：自轉郵；C：貨轉郵)
		public void setMessagetype(String messagetype);
		//getFTP傳送ec路徑
		public String getSendFtpPath();

		//setFTP傳送ec路徑
		public void setSendFtpPath(String sendFtpPath);
		//getFTP傳送Xml路徑
		public String getGetFtpPath();

		//setFTP傳送Xml路徑
		public void setGetFtpPath(String getFtpPath);

}

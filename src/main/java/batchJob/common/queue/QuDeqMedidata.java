package batchJob.common.queue;

import com.tradevan.wcommons.ApContext;

public enum QuDeqMedidata implements MyQueueConfig {
	
	CPG(//
			"CPG_DEQ_SYSCODE", // sysCode
			"CPG_DEQ_HUBTYPE", // hubType
			"CPG_DEQ_ERR_DIR", // errDir 收【錯誤】檔案資料夾
			"CPG_DEQ_OK_DIR", // bkDir 收【成功】檔案資料夾
			"CPG_DEQ_PENDING_DIR", // deqDir 收【待處理】檔案資料夾
			"CPG_DEQ_JMSUID", // jmsUID
			"CPG_DEQ_JMSPW", // jmsPW
			"CPG_DEQ_QUEID", // lgQueId
			"CPG_SEND_PENDING_DIR", // 送【待處理】檔案資料夾
			"CPG_SEND_OK_DIR", // 送【成功】檔案資料夾
			"CPG_SEND_ERR_DIR"), // 送【錯誤】檔案資料夾
			
			;//
			protected String sysCode;
			protected String hubType;
			protected String errDir;
			protected String okDir;
			protected String pendingDir;
			protected String jmsUID;
			protected String jmsPW;
			protected int lgQueId;
			protected String sendPendingDir;
			protected String sendOkDir;
			protected String sendErrDir;

			private QuDeqMedidata(//
					final String sysCode, // // sysCode
					final String hubType,// // hubType
					final String errDir, // / // errDir 收【錯誤】檔案資料夾
					final String bkDir, // // bkDir 收【成功】檔案資料夾
					final String deqDir,// // deqDir 收【待處理】檔案資料夾
					final String jmsUID,// // jmsUID
					final String jmsPW, // // jmsPW
					final String lgQueId,// // lgQueId
					final String sendPendingDir,// // 送【待處理】檔案資料夾
					final String sendOkDir,// // 送【成功】檔案資料夾
					final String sendErrDir) {// // 送【錯誤】檔案資料夾
				this.sysCode = this.getString(sysCode);
				this.hubType = this.getString(hubType);
				this.errDir = this.getString(errDir);
				this.okDir = this.getString(bkDir);
				this.pendingDir = this.getString(deqDir);
				this.jmsUID = this.getString(jmsUID);
				this.jmsPW = this.getString(jmsPW);
				this.lgQueId = Integer.parseInt(this.getString(lgQueId));
				this.sendPendingDir = this.getString(sendPendingDir);
				this.sendOkDir = this.getString(sendOkDir);
				this.sendErrDir = this.getString(sendErrDir);
			}

			private String getString(final String seetingName) {
				return ApContext.getContext().getSetting(seetingName);
			}

			@Override
			public String getSysCode() {
				return this.sysCode;
			}

			@Override
			public String getHubType() {
				return this.hubType;
			}

			@Override
			public String getErrDir() {
				return this.errDir;
			}

			@Override
			public String getOkDir() {
				return this.okDir;
			}

			@Override
			public String getPendingDir() {
				return this.pendingDir;
			}

			@Override
			public String getJmsUID() {
				return this.jmsUID;
			}

			@Override
			public String getJmsPW() {
				return this.jmsPW;
			}

			@Override
			public int getLgQueId() {
				return this.lgQueId;
			}

			@Override
			public JmsConfig getJmsConfig() {
				return new JmsConfig();
			}

}

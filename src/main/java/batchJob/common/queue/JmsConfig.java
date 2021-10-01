package batchJob.common.queue;

import com.tradevan.wcommons.ApContext;

public class JmsConfig {
	
	final String vasSetUrl;
	final String vasJmsUsername;
	final String vasJmsPassword;
	final int mFsport;
	final String mFsip;
	
	public JmsConfig() {
		this.vasSetUrl = this.getString("tsJMSIMP");
		this.vasJmsUsername = this.getString("VAS_JMS_USERNAME");
		this.vasJmsPassword = this.getString("VAS_JMS_PASSWORD");
		this.mFsport = Integer.parseInt(this.getString("fsport"));
		this.mFsip = this.getString("fsip"); // tnvanfs.intranet.com.tw
	}

	private String getString(final String seetingName) {
		return ApContext.getContext().getSetting(seetingName);
	}
	
	public String getVasSetUrl() {
		return this.vasSetUrl;
	}

	public String getVasJmsUsername() {
		return this.vasJmsUsername;
	}

	public String getVasJmsPassword() {
		return this.vasJmsPassword;
	}

	public int getmFsport() {
		return this.mFsport;
	}

	public String getmFsip() {
		return this.mFsip;
	}

}

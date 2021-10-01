package batchJob.been.vo.cpg;

import batchJob.been.po.CpgSettingPo;

public class CPGEnqueuePart1Vo {
	
	private String sysCode;
	private String hubType;
	private String errDir;
	private String okDir;
	private String pendingDir;
	private String jmsUID;
	private int lgQueId;
	private String procFlagFile;
	private CpgSettingPo cpgSettingDo;


	public CpgSettingPo getCpgSettingDo() {
		return cpgSettingDo;
	}

	public void setCpgSettingDo(CpgSettingPo cpgSettingDo) {
		this.cpgSettingDo = cpgSettingDo;
	}

	public void setSysCode(final String sysCode) {
		this.sysCode = sysCode;
	}

	public String getHubType() {
		return this.hubType;
	}

	public void setHubType(final String hubType) {
		this.hubType = hubType;
	}

	public String getErrDir() {
		return this.errDir;
	}

	public void setErrDir(final String errDir) {
		this.errDir = errDir;
	}

	public String getOkDir() {
		return this.okDir;
	}

	public void setOkDir(final String okDir) {
		this.okDir = okDir;
	}

	public String getPendingDir() {
		return this.pendingDir;
	}

	public void setPendingDir(final String pendingDir) {
		this.pendingDir = pendingDir;
	}

	public String getJmsUID() {
		return this.jmsUID;
	}

	public void setJmsUID(final String jmsUID) {
		this.jmsUID = jmsUID;
	}

	public void setLgQueId(final int lgQueId) {
		this.lgQueId = lgQueId;
	}

}

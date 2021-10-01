package batchJob.been.vo.cpg;

import batchJob.been.po.CpgSettingPo;
import batchJob.common.queue.EnqQueueConfig;
import batchJob.common.queue.EnqQueueContext;
import batchJob.common.queue.JmsConfig;
import batchJob.common.queue.MyQueueConfig;

public class CPGEnqueuePart2Vo implements EnqQueueConfig, MyQueueConfig, EnqQueueContext {

	private String sendId;
	private String recvId;
	private String docName;
	private String filename;
	private String clientfile;
	private String remotePath;
	private String localPath;
	private JmsConfig jmsConfig = new JmsConfig();
	private String sysCode;
	private String hubType;
	private String errDir;
	private String okDir;
	private String pendingDir;
	private String jmsUID;
	private int lgQueId;
	private CpgSettingPo cpgSettingDo;

	public CpgSettingPo getCpgSettingDo() {
		return cpgSettingDo;
	}

	public void setCpgSettingDo(CpgSettingPo cpgSettingDo) {
		this.cpgSettingDo = cpgSettingDo;
	}

	@Override
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(final String sendId) {
		this.sendId = sendId;
	}

	@Override
	public String getRecvId() {
		return this.recvId;
	}

	public void setRecvId(final String recvId) {
		this.recvId = recvId;
	}

	@Override
	public String getDocName() {
		return this.docName;
	}

	public void setDocName(final String docName) {
		this.docName = docName;
	}

	@Override
	public String getClientfile() {
		return this.clientfile;
	}

	public void setClientfile(final String clientfile) {
		this.clientfile = clientfile;
	}

	public String getRemotePath() {
		return this.remotePath;
	}

	public void setRemotePath(final String remotePath) {
		this.remotePath = remotePath;
	}

	public String getLocalPath() {
		return this.localPath;
	}

	public void setLocalPath(final String localPath) {
		this.localPath = localPath;
	}

	@Override
	public JmsConfig getJmsConfig() {
		return this.jmsConfig;
	}

	public void setJmsConfig(final JmsConfig jmsConfig) {
		this.jmsConfig = jmsConfig;
	}

	@Override
	public String getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(final String sysCode) {
		this.sysCode = sysCode;
	}

	@Override
	public String getHubType() {
		return this.hubType;
	}

	public void setHubType(final String hubType) {
		this.hubType = hubType;
	}

	@Override
	public String getErrDir() {
		return this.errDir;
	}

	public void setErrDir(final String errDir) {
		this.errDir = errDir;
	}

	@Override
	public String getOkDir() {
		return this.okDir;
	}

	public void setOkDir(final String okDir) {
		this.okDir = okDir;
	}

	@Override
	public String getPendingDir() {
		return this.pendingDir;
	}

	public void setPendingDir(final String pendingDir) {
		this.pendingDir = pendingDir;
	}

	@Override
	public String getJmsUID() {
		return this.jmsUID;
	}

	public void setJmsUID(final String jmsUID) {
		this.jmsUID = jmsUID;
	}

	@Override
	public int getLgQueId() {
		return this.lgQueId;
	}

	public void setLgQueId(final int lgQueId) {
		this.lgQueId = lgQueId;
	}

	@Override
	public String getJmsPW() {
		return this.jmsConfig.getVasJmsPassword();
	}

	@Override
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String filename) {
		this.filename = filename;
	}
	
}

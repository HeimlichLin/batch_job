package batchJob.common.queue;

public interface MyQueueConfig {
	
	public String getSysCode();// SysCode

	public String getHubType();// HubType

	public String getErrDir();// 錯誤的資料夾

	public String getOkDir();// 成功資料夾

	public String getPendingDir();// 待處理

	public String getJmsUID();

	public String getJmsPW();

	public JmsConfig getJmsConfig();// JMS共通

	public int getLgQueId();

}

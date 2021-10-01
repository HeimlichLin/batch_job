package batchJob.common.queue;

public interface EnqQueueConfig {
	
	public String getSendId();

	public String getRecvId();

	String getDocName();

	String getFilename();

	String getClientfile();

	public JmsConfig getJmsConfig();// JMS共通

}

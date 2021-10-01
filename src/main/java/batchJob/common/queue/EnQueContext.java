package batchJob.common.queue;

public interface EnQueContext {
	
	String getEnqDir();

	String getSendId();

	String getRecvId();

	String getDocName();

	String getFilename();

	String getClientfile();

}

package batchJob.common.job;

public interface JobDef {
	
	String getJobName();

	String getId();

	Job getJob();

	boolean isOnly();

}

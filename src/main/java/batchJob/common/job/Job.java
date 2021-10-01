package batchJob.common.job;

public interface Job {
	
	String getJobName();

	String getId();

	void work(IJobContext jobContext);

}

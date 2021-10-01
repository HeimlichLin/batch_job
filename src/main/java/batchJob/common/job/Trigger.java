package batchJob.common.job;

public interface Trigger {
	
	/**
	 * 執行Job
	 */
	void trigger() throws InterruptedException;

	void off() throws InterruptedException;

}

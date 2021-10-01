package batchJob.common.job.batchJob.task;

import java.util.List;

import batchJob.common.job.PermanentJobDef;

/**
 * 動態載入介面 實作介請加於 PermanentJobsLoaderSet
 * 
 */
public interface PermanentJobsLoader {
	
	List<PermanentJobDef> loadJobs();
	
}

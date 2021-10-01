package batchJob.common.job.batchJob.task;

import java.util.ArrayList;
import java.util.List;

import batchJob.common.job.PermanentJobDef;

/**
 * 動態Job 讀取Set
 * 
 */
public class PermanentJobsLoaderSet implements PermanentJobsLoader {

	private List<PermanentJobsLoader> loaders = new ArrayList<PermanentJobsLoader>();

	public PermanentJobsLoaderSet() {
		loaders.add(new CpgPermanentJobs());
	}
	
	@Override
	public List<PermanentJobDef> loadJobs() {
		List<PermanentJobDef> list = new ArrayList<PermanentJobDef>();
		for (PermanentJobsLoader permanentJobsLoader : loaders) {
			list.addAll(permanentJobsLoader.loadJobs());
		}
		return list;
	}
	
}

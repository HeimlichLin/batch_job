package batchJob.common.job.batchJob;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.exception.ApBusinessException;
import batchJob.common.job.JobQuery;
import batchJob.common.job.PermanentJobDef;
import batchJob.common.job.batchJob.task.PermanentJobs;
import batchJob.common.job.batchJob.task.PermanentJobsLoader;
import batchJob.common.job.batchJob.task.PermanentJobsLoaderSet;

/**
 * 持久JobQuery
 *
 */
public class PermanentJobQuery implements JobQuery<PermanentJobDef> {

	final List<PermanentJobDef> conutinuteJobs = new ArrayList<PermanentJobDef>();
	private PermanentJobsLoader dyPermanentJobs = new PermanentJobsLoaderSet();
	private static Logger LOGGER = LoggerFactory.getLogger(PermanentJobQuery.class);

	public PermanentJobQuery() {
		final EnumSet<PermanentJobs> jobs = EnumSet.allOf(PermanentJobs.class);
		final List<PermanentJobs> jobList = new ArrayList<PermanentJobs>();
		for (final PermanentJobs job : jobs) {
			jobList.add(job);
		}
		this.conutinuteJobs.addAll(this.dyPermanentJobs.loadJobs());
	}

	@Override
	public List<PermanentJobDef> getJobs() {
		return this.conutinuteJobs;
	}

	@Override
	public void done(final PermanentJobDef t) {
		LOGGER.debug("no keeping... !" + t.getId());
	}

	@Override
	public void add(final PermanentJobDef t) {
		throw new ApBusinessException("透過環境設定檔加入，非動態加入");

	}

}

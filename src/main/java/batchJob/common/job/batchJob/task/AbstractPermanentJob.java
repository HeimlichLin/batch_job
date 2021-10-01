package batchJob.common.job.batchJob.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.job.IJobContext;
import batchJob.common.job.Job;
import batchJob.common.job.PermanentJobDef;

public abstract class AbstractPermanentJob implements Job {

	private static Logger LOGGER = LoggerFactory.getLogger(AbstractPermanentJob.class);
	
	private String jobName;
	private String jobId;

	public AbstractPermanentJob(final PermanentJobDef permanentJobDef) {
		this.jobName = permanentJobDef.getJobName();
		this.jobId = permanentJobDef.getId();
	}
	
	@Override
	final public String getId() {
		return this.jobId;
	}

	@Override
	final public String getJobName() {
		return this.jobName;
	}
	
	@Override
	final public void work(final IJobContext jobContext) {
		try {
			this.mywork(jobContext);
		} catch (final Exception e) {
			LOGGER.error("PermanentJob 錯誤", e);
		}

	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(final String jobId) {
		this.jobId = jobId;
	}

	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}

	abstract protected void mywork(IJobContext jobContext);	
	
}

package batchJob.common.job.jobmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import batchJob.common.job.BatchContract;
import batchJob.common.job.IJobContext;
import batchJob.common.job.Job;
import batchJob.common.job.JobContextBuider;
import batchJob.common.job.JobDef;
import batchJob.common.utils.date.YYYYMMDDMMSSUtils;

public class BaseJob implements JobDef {

	private Map<String, Object> map = new HashMap<String, Object>();
	final JobDef jobDef;
	final String trasactionId;
	
	public BaseJob(final JobDef jobDef) {
		this.jobDef = jobDef;
		this.trasactionId = jobDef.getId() + '_' + YYYYMMDDMMSSUtils.getText() + "_" + UUID.randomUUID().toString();
	}

	public BaseJob(final JobDef jobDef, Object dto) {
		this(jobDef);
		map.put(BatchContract.DTO.name(), dto);

	}

	public void run() {
		final JobContextBuider jobContextBuider = new JobContextBuider();
		jobContextBuider.setMap(map);
		jobContextBuider.setTransactionId(trasactionId);
		final IJobContext context = jobContextBuider.buider();
		this.jobDef.getJob().work(context);
	}

	@Override
	public String getId() {
		return this.jobDef.getId();
	}
	
	@Override
	public String getJobName() {
		return this.jobDef.getJobName();
	}

	@Override
	public Job getJob() {
		return this.jobDef.getJob();
	}

	@Override
	public boolean isOnly() {
		return this.jobDef.isOnly();
	}

	@Override
	public String toString() {
		return "BaseJob [jobid=" + this.jobDef.getId() + ", trasactionId=" + this.trasactionId + "]";
	}
	
}

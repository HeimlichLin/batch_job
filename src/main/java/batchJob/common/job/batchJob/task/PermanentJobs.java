package batchJob.common.job.batchJob.task;

import batchJob.common.exception.ApBusinessException;
import batchJob.common.job.Job;
import batchJob.common.job.PermanentJobDef;

public enum PermanentJobs implements PermanentJobDef {
	
	// 自轉郵
//	ClrecvCPGJob(ClrecvCPGJob.class, "自轉郵收檔"), //
//	ClprocCPGJob(ClprocCPGJob.class, "自轉郵讀檔"), //
//	ClprocCPG2FTPJob(ClprocCPG2FTPJob.class, "自轉郵傳送FTP"), //
	//
	// // 自轉郵回訊
//	ClrecvECPGJob(ClrecvECPGJob.class, "自轉郵回訊收檔"), //
//	ClprocECPGJob(ClprocECPGJob.class, "自轉郵回訊讀檔"), //
//	ClprocECPG2SendJob(ClprocECPG2SendJob.class, "自轉郵回訊傳送至平台"),
//	PermanentJob(PermanentJob.class, "自轉郵回訊傳送至平台"),

	// 自轉郵回訊
	;//
	
	private <T extends Job> PermanentJobs(final Class<? extends Job> jobClass, final String name, final int periodTime) {
		this.isLoad = true;
		this.jobName = name;
		this.id = this.name();
		this.jobClass = jobClass;
		this.isOnly = true;
		this.periodTime = periodTime;
	}

	/**
	 * 預設下一次執行為3分鐘後
	 *
	 * @param job
	 */
	private PermanentJobs(final Class<? extends Job> jobClass, final String name) {
		this(jobClass, name, 1000 * 180);
	}

	final String jobName;
	final String id;
	final boolean isLoad;
	final boolean isOnly;
	final int periodTime;
	final Class<? extends Job> jobClass;

	@Override
	public String getJobName() {
		return this.jobName;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Job getJob() {
		try {
			return this.jobClass.newInstance();
		} catch (final Exception e) {
			throw new ApBusinessException("JOB create faill!", e);
		}
	}

	public boolean isLoad() {
		return this.isLoad;
	}

	@Override
	public boolean isOnly() {
		return this.isOnly;
	}

	@Override
	public int getPeriodTime() {
		return this.periodTime;
	}

}

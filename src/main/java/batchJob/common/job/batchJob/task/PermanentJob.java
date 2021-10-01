package batchJob.common.job.batchJob.task;

import batchJob.common.job.IJobContext;
import batchJob.common.job.Job;
import batchJob.common.job.PermanentJobDef;

public class PermanentJob implements Job, PermanentJobDef {
	
	protected final static int PERIODTIME = 1000 * 60;

	private static PermanentWork HELPER = new PermanentWork() {

		@Override
		public void work() {
			// 沒做事
		}

		@Override
		public String getId() {
			return "PERMANENTJOB_NO_ID";
		}

		@Override
		public String getName() {
			return "PERMANENTJOB_NO_NAME";
		}
	};

	private PermanentWork permanentWork = HELPER;

	public PermanentJob(PermanentWork permanentWork) {
		this.permanentWork = permanentWork;
	}

	@Override
	public String getJobName() {
		return permanentWork.getName();
	}

	@Override
	public String getId() {
		return permanentWork.getId();
	}

	@Override
	public void work(IJobContext jobContext) {
		permanentWork.work();
	}

	@Override
	public Job getJob() {
		return this;
	}

	@Override
	public boolean isOnly() {
		return false;
	}

	@Override
	public int getPeriodTime() {
		return PERIODTIME;
	}

}

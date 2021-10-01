package batchJob.common.job.jobmanager;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.job.IJobContext;
import batchJob.common.job.Job;
import batchJob.common.job.JobContextBuider;
import batchJob.common.job.JobDef;
import batchJob.common.job.JobQuery;
import batchJob.common.job.PermanentJobDef;
import batchJob.common.job.Trigger;
import batchJob.common.utils.date.YYYYMMDDMMSSUtils;

/**
 * 常駐型觸發器
 *
  */
public class PermanentTrigger implements Trigger {

	private static Logger LOGGER = LoggerFactory.getLogger(PermanentTrigger.class);

	final static int MAX_SIZE = 5;
	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(MAX_SIZE);

	private JobQuery<PermanentJobDef> jobQuery;

	public PermanentTrigger(final JobQuery<PermanentJobDef> jobQuery) {
		this.jobQuery = jobQuery;
	}

	@Override
	public void trigger() throws InterruptedException {
		LOGGER.debug("schedule call trigger");
		final List<PermanentJobDef> jobs = this.jobQuery.getJobs();
		for (final PermanentJobDef jobDef : jobs) {
			LOGGER.debug("schedule add Job id" + jobDef.getId());
			this.executorService.scheduleWithFixedDelay(new ContinuedJobTask(jobDef), 0, jobDef.getPeriodTime(),
					TimeUnit.MILLISECONDS);
		}

	}

	@Override
	public void off() throws InterruptedException {
		if (!this.executorService.isShutdown()) {
			this.executorService.shutdown();
		}

	}

	/**
	 * 連續執行Job
	 *	
	 */
	public class ContinuedJobTask extends TimerTask {
		private JobDef jobDef;

		public ContinuedJobTask(final JobDef jobDef) {
			this.jobDef = jobDef;
		}

		@Override
		public void run() {
			long start = System.currentTimeMillis();
			try {
				final JobContextBuider buider = new JobContextBuider();
				buider.setTransactionId(this.jobDef.getId() + YYYYMMDDMMSSUtils.getText());
				final IJobContext context = buider.buider();
				final Job job = this.jobDef.getJob();
				job.work(context);
			} catch (Exception e) {
				LOGGER.error(this.jobDef.getId() + "happend error message", e);
			} finally {
				LOGGER.debug(this.jobDef.getId() + " speed time:" + (System.currentTimeMillis() - start) + "ms");
			}

		}
	}
}

package batchJob.common.job.batchJob;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.job.Trigger;
import batchJob.common.job.jobmanager.PermanentJobManager;
import batchJob.common.job.jobmanager.PermanentTrigger;

public class PermanentBatchJobManager extends PermanentJobManager {

	private static PermanentBatchJobManager instance = new PermanentBatchJobManager();
	private static Logger LOGGER = LoggerFactory.getLogger(PermanentBatchJobManager.class);

	@Override
	public Trigger getTrigger() {
		return new PermanentTrigger(new PermanentJobQuery());
	}
	
	public static void main(final String[] args) {
		final PermanentBatchJobManager manager = PermanentBatchJobManager.instance;
		try {
			if (args == null || args.length == 0) {
				manager.on();
			} else {
				final String actionString = StringUtils.defaultIfEmpty(args[0], "ON").trim().toUpperCase();
				if (actionString.equals("ON")) {
					manager.on();
				} else if (actionString.equals("OFF")) {
					manager.off();
				} else {
					LOGGER.warn("action no define" + actionString);
				}
			}

		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}

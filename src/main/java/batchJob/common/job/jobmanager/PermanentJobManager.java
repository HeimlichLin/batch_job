package batchJob.common.job.jobmanager;

import batchJob.common.job.Trigger;

public abstract class PermanentJobManager {

	final Trigger trigger;
	final String locks = "";
	Boolean on = false;

	abstract public Trigger getTrigger();

	public PermanentJobManager() {
		this.trigger = this.getTrigger();
	}

	public void on() throws InterruptedException {
		synchronized (locks) {
			if (!on) {
				trigger.trigger();
				on = true;
			}

		}

	}

	public void off() throws InterruptedException {
		synchronized (locks) {
			if (on) {
				trigger.off();
				on = false;
			}

		}

	}
}
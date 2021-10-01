package batchJob.common.job;

public class JobKeyDomain implements JobKey {
	
	public static JobKey setDomain(final JobDef job) {
		final JobKey jobKey = new JobKeyDomain(job);
		return jobKey;
	}

	private final String id;

	public JobKeyDomain(final JobDef job) {
		this.id = job.getId();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof JobKey)) {
			return false;
		} else if ((obj instanceof JobKey)) {
			final JobKey k1 = (JobKey) obj;
			return k1.getId().equals(this.getId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}

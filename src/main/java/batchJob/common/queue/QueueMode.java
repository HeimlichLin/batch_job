package batchJob.common.queue;

public enum QueueMode {
	ENQ_MODE(true), //
	DEQ_MODE(false), //

	;

	final boolean mode;

	private QueueMode(final boolean mode) {
		this.mode = mode;
	}

	public boolean isMode() {
		return this.mode;
	}

}

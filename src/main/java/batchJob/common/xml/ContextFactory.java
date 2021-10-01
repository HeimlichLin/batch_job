package batchJob.common.xml;

/**
 * 內容工廠
 *
 *
 */
public abstract class ContextFactory {
	public final Book getContext() {
		return this.extraction();
	}

	protected abstract Book extraction();

}

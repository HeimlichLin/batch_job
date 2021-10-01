package batchJob.common.job;

import java.util.List;

public interface JobQuery<T extends JobDef> {
	/**
	 * 取得所有辦清單
	 *
	 * @return
	 */
	public List<T> getJobs();

	public void done(T t);

	public void add(T t);
	
}

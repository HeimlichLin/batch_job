package batchJob.common.queue;


/**
 * 觀察者
 *
 *
 */
public interface Observer {
	/**
	 * 通知異動
	 *
	 * @param subject
	 */
	void update(Subject subject);

}

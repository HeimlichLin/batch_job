package batchJob.common.queue;

/**
 * 主題
 *
 *
 */
public interface Subject {
	/**
	 * 訂閱
	 *
	 * @param ob
	 */
	void registered(Observer ob);

	/**
	 * 退訂
	 *
	 * @param ob
	 */
	void unRegistered(Observer ob);

	/**
	 * 異動
	 */
	void changeValue();

}

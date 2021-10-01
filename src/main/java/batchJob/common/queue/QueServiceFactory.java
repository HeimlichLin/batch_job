package batchJob.common.queue;

public class QueServiceFactory {
	
	public static IQueue getQueService(final MyQueueConfig config) {
		return new MyQueueClient(config);
	}

}

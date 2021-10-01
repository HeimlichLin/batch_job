package batchJob.common.job;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JobContextBuider {
	
	private Map<String, Object> map = new HashMap<String, Object>();
	private String transactionId;
	
	public IJobContext buider() {
		final IJobContext jobContext = new JobContext(this);
		return jobContext;
	}

	public Map<String, Object> getMap() {
		return this.map;
	}

	public void setMap(final Map<String, Object> map) {
		this.map = map;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(final String transactionId) {
		this.transactionId = transactionId;
	}
	
	private class JobContext implements IJobContext {
		final Map<String, Object> map;
		final String transactionId;

		public JobContext(final JobContextBuider jobContextBuider) {
			this.map = Collections.unmodifiableMap(jobContextBuider.getMap());
			this.transactionId = jobContextBuider.getTransactionId();
		}

		@Override
		public String getTransaction() {
			return this.transactionId;
		}

		@Override
		public Object getDTO() {
			if (!this.map.containsKey(BatchContract.DTO.name())) {
				return null;
			}
			return this.map.get(BatchContract.DTO.name());
		}

	}

}

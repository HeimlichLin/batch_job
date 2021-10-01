package batchJob.common.job;

public interface IJobContext {
	
	Object getDTO();// 參數

	String getTransaction();// 交易序號

}

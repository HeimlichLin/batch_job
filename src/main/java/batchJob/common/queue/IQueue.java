package batchJob.common.queue;

import java.sql.Connection;
import java.util.List;

import com.tradevan.jmseqdq.core.QueService;

public interface IQueue {
	
	int openTrans(QueueMode mode, Connection dbCon);

	int openDeqTrans(Connection dbCon);

	int openEnqTrans(Connection dbCon);

	List<String> deQue();

	int enQue(EnqQueueContext context, String filename);

	int enQue(MyQueueConfig config, EnQueContext enQueContext);

	void closeTrans();

	QueService getQueService();

}

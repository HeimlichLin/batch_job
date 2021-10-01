package batchJob.common.queue;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tradevan.jmseqdq.core.QueTrans;

public class FakeMyQueue extends MyQueueClient {
	
	private static Logger LOGGER = LoggerFactory.getLogger("AP");
	
	String fileLocal;
	
	public FakeMyQueue(String fileLocal) {

		super(QuDeqMedidata.CPG);
		this.fileLocal = fileLocal;
		LOGGER.debug("fake mqQueue");
	}
	
	@Override
	public int openTrans(final QueueMode mode, final Connection dbCon) {
		LOGGER.debug("fake mqQueue!openTrans");
		return QueTrans.RET_OK;
	}

	@Override
	public void closeTrans() {
		LOGGER.debug("fake mqQueue!closeTrans");
	}

	@Override
	public int openDeqTrans(final Connection dbCon) {
		LOGGER.debug("fake mqQueue!openDeqTrans");
		return QueTrans.RET_OK;
	}

	@Override
	public int openEnqTrans(final Connection dbCon) {
		return QueTrans.RET_OK;
	}

	private void openDeqTrans() {
		//

	}

	@Override
	public List<String> deQue() {
		return Arrays.asList("",//
				"",//
				"",//
				"",//
				"",//
				fileLocal);// 檔名
	}

}

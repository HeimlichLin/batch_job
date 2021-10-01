package batchJob.test;

import java.util.List;

import batchJob.been.CpgDetailDo;
import batchJob.been.EventCTranMainDo;
import batchJob.common.exception.ApBusinessException;
import batchJob.model.XdaoSessionManager;

import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.taurus.xdao.SqlWhere;
import com.tradevan.test.impl.GeneralTest;

/**
 * 自轉郵搜檔案後傳至中華郵政
 * 
 *
 */
public class ReceverCPGSendFTPTest extends GeneralTest {
	final String filenameString;

	public ReceverCPGSendFTPTest(String filenameString) {
		super(TestMedidatas.RECEVER_CPGSENDFTPTEST);
		this.filenameString = filenameString;
	}
	
	@Override
	public void before() {
		// TODO Auto-generated method stub

	}

	@Override
	public void test() {
		SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(CpgDetailDo.COLUMNS.FILENAME.name(), this.filenameString);

		DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
		List<EventCTranMainDo> eventCTranMainDos = doXdaoSession.selectPo(EventCTranMainDo.class, sqlWhere);

		for (EventCTranMainDo eventCTranMainDo : eventCTranMainDos) {
			if (!eventCTranMainDo.getSendflag().matches("S")) {
				throw new ApBusinessException("ftp檔案尚未傳遞完成");
			}
		}

	}

	@Override
	public void after() {
		// TODO Auto-generated method stub

	}

}

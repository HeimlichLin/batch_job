package batchJob.test;

import batchJob.been.CpgDetailDo;
import batchJob.common.exception.ApBusinessException;
import batchJob.model.XdaoSessionManager;
import batchJob.service.CTZLFPGProcess;

import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.taurus.xdao.SqlWhere;
import com.tradevan.taurus.xdao.XdaoException;
import com.tradevan.test.impl.GeneralTest;

public class ReceverProcCPGFullTest extends GeneralTest {
	final String filenameString;

	public ReceverProcCPGFullTest(final String filenameString) {
		super(TestMedidatas.RECEVER_PROCCPGFULLTEST);
		this.filenameString = filenameString;
	}

	@Override
	public void before() {
		final CTZLFPGProcess cTZLFPGProcess = new CTZLFPGProcess();
		callThread(new Runnable() {
			@Override
			public void run() {
				cTZLFPGProcess.loopExecution();
			}
		});
	}

	@Override
	public void test() {
		DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();

		SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(CpgDetailDo.COLUMNS.FILENAME.name(), this.filenameString);

		try {
			this.checkNullThrowMessage(doXdaoSession.selectOne("CPG_MAIN", sqlWhere), "CPG_MAIN");
			this.checkNullThrowMessage(doXdaoSession.selectOne("CPG_DETAIL", sqlWhere), "CPG_DETAIL");
			this.checkNullThrowMessage(doXdaoSession.selectOne("EVENT_C_TRAN_MAIN", sqlWhere), "EVENT_C_TRAN_MAIN");
			this.checkNullThrowMessage(doXdaoSession.selectOne("EVENT_C_TRAN_A", sqlWhere), "EVENT_C_TRAN_A");
			this.checkNullThrowMessage(doXdaoSession.selectOne("EVENT_C_TRAN_B", sqlWhere), "EVENT_C_TRAN_B");
			this.checkNullThrowMessage(doXdaoSession.selectOne("EVENT_C_TRAN_C", sqlWhere), "EVENT_C_TRAN_C");
		} catch (XdaoException e) {
			throw new ApBusinessException("", e);
		}

	}

	@Override
	public void after() {
		// TODO Auto-generated method stub

	}

}

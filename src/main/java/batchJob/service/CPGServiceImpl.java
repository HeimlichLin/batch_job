package batchJob.service;

import java.util.List;

import com.tradevan.common.db.DoSqlWhere;
import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.taurus.xdao.SqlWhere;
import com.tradevan.taurus.xdao.XdaoSession;

import batchJob.been.EventCTranADo;
import batchJob.been.EventCTranBDo;
import batchJob.been.EventCTranCDo;
import batchJob.been.EventCTranMainDo;
import batchJob.been.dao.EventCTranBDAO;
import batchJob.been.dao.EventCTranCDAO;
import batchJob.been.dao.EventCTranMainDAO;
import batchJob.common.codegenerate.SendFlag;
import batchJob.model.XdaoSessionManager;

public class CPGServiceImpl implements CPGService {
	
	@Override
	public List<EventCTranMainDo> queryEventCTranMainDos() {
		final DoXdaoSession xdaoSession = (DoXdaoSession) XdaoSessionManager.getXdaoSession();
		final DoSqlWhere<EventCTranMainDo.COLUMNS> sqlWhere = new DoSqlWhere<EventCTranMainDo.COLUMNS>();
		sqlWhere.add(EventCTranMainDo.COLUMNS.SENDFLAG, SendFlag.P.name());
		return xdaoSession.selectPo(EventCTranMainDo.class, sqlWhere);
	}
	
	@Override
	public List<EventCTranBDo> queryEventCTranBDos(final String fileName) {
		final XdaoSession xdaoSession = XdaoSessionManager.getXdaoSession();
		final EventCTranBDAO dao = new EventCTranBDAO();
		final SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(EventCTranBDo.COLUMNS.FILENAME.name(), fileName);
		return dao.select(xdaoSession, sqlWhere);
	}
	
	@Override
	public List<EventCTranCDo> queryEventCTranCDos(final String fileName) {
		final XdaoSession xdaoSession = XdaoSessionManager.getXdaoSession();
		final EventCTranCDAO dao = new EventCTranCDAO();
		final SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(EventCTranBDo.COLUMNS.FILENAME.name(), fileName);
		return dao.select(xdaoSession, sqlWhere);
	}
	
	@Override
	public void updateEventCTranMainDo(final EventCTranMainDo eventCTranMainDo) {
		final XdaoSession xdaoSession = XdaoSessionManager.getXdaoSession();
		final EventCTranMainDAO dao = EventCTranMainDAO.INSTANCE;
		dao.update(xdaoSession, eventCTranMainDo);

	}

	@Override
	public List<EventCTranADo> queryEventCTranADos(final String fileName) {
		final DoXdaoSession xdaoSession = (DoXdaoSession) XdaoSessionManager.getXdaoSession();
		final SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(EventCTranADo.COLUMNS.FILENAME.name(), fileName);
		return xdaoSession.selectPo(EventCTranADo.class, sqlWhere);
	}

}

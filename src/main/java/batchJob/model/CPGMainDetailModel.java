package batchJob.model;

import java.util.List;

import batchJob.been.CpgDetailDo;
import batchJob.been.CpgMainDo;
import batchJob.been.dao.CpgDetailDAO;
import batchJob.been.dao.CpgMainDAO;

import com.tradevan.framework.BaseModel;
import com.tradevan.framework.FrameworkContext;
import com.tradevan.taurus.xdao.XdaoFactory;
import com.tradevan.taurus.xdao.XdaoSession;

public class CPGMainDetailModel extends BaseModel implements
		ICPGMainDetailModel {
	
	public static final String CONN_ID = "apFtzcConn";
	protected static XdaoFactory xdaoFactory = XdaoFactory.getInstance();

	private com.tradevan.commons.logging.Logger logger = FrameworkContext
			.getContext().getLogger();
	
	@Override
	public void insertCpgDetialDos(XdaoSession con, List<CpgDetailDo> vos) {
		for (CpgDetailDo vo : vos) {
			CpgDetailDAO dao = new CpgDetailDAO();
			dao.insert(con, vo);

		}

	}

	@Override
	public void insertCpgMainDos(XdaoSession con, List<CpgMainDo> vos) {
		for (CpgMainDo vo : vos) {
			CpgMainDAO cpgMainDAO = new CpgMainDAO();
			cpgMainDAO.insert(con, vo);

		}
	}

}

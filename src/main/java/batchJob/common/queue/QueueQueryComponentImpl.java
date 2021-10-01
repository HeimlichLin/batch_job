package batchJob.common.queue;

import java.util.List;

import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.taurus.xdao.SqlWhere;

import batchJob.been.po.CpgSettingPo;
import batchJob.model.XdaoSessionManager;

public class QueueQueryComponentImpl implements QueueQueryComponent {
	
	@Override
	public List<CpgSettingPo> getCpgSettingDos() {
		final DoXdaoSession xdaoSession = XdaoSessionManager.getDoXdaoSession();
		final SqlWhere sqlWhere = new SqlWhere();
		return xdaoSession.selectPo(CpgSettingPo.class, sqlWhere);
	}

}

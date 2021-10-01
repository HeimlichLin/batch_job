package batchJob.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tradevan.common.db.DoSqlWhere;
import com.tradevan.common.db.DoXdaoSession;

import batchJob.been.po.CpgSettingPo;
import batchJob.common.exception.ApBusinessException;
import batchJob.model.XdaoSessionManager;

public class CpgSettingQueryImpl implements CpgSettingQuery {
	
	@Override
	public CpgSettingPo query(final String postspecialaccount) {

		final DoSqlWhere<CpgSettingPo.COLUMNS> doSqlWhere = new DoSqlWhere<CpgSettingPo.COLUMNS>();
		doSqlWhere.add(CpgSettingPo.COLUMNS.POSTSPECIALACCOUNT, postspecialaccount);

		final DoXdaoSession XdaoSession = XdaoSessionManager.getDoXdaoSession();
		final List<CpgSettingPo> cogCpgSettingDos = XdaoSession.selectPo(CpgSettingPo.class, doSqlWhere);
		if (CollectionUtils.isEmpty(cogCpgSettingDos)) {
			throw new ApBusinessException(String.format("此特約戶號%s，查無異動別", postspecialaccount));
		} else {
			return cogCpgSettingDos.get(0);
		}
	}

}

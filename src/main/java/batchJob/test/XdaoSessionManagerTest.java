package batchJob.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.been.po.CpgSettingPo;
import batchJob.model.XdaoSessionManager;

import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.taurus.xdao.SqlWhere;

public class XdaoSessionManagerTest {
	private static Logger LOGGER = LoggerFactory.getLogger(XdaoSessionManagerTest.class);

	public static void main(String[] args) {
		DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();

		SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(CpgSettingPo.COLUMNS.BF_NO.name(), "");

		List<CpgSettingPo> cpgSettingPos = doXdaoSession.selectPo(CpgSettingPo.class, sqlWhere);
		LOGGER.debug("{}", cpgSettingPos);

	}

}

package batchJob.common.utils.db;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import batchJob.been.CpgMainDo;
import batchJob.been.CpgTranDetailDo;
import batchJob.been.CpgTranMainDo;
import batchJob.been.CpgTranPostDo;
import batchJob.been.EventCRecvADo;
import batchJob.been.EventCRecvBDo;
import batchJob.been.EventCRecvCDo;
import batchJob.been.EventCTranADo;
import batchJob.been.EventCTranBDo;
import batchJob.been.EventCTranCDo;
import batchJob.been.EventCTranMainDo;
import batchJob.been.dao.CpgMainDAO;
import batchJob.been.dao.CpgSettingDAO;
import batchJob.been.dao.CpgTranDetailDAO;
import batchJob.been.dao.CpgTranMainDAO;
import batchJob.been.dao.CpgTranPostDAO;
import batchJob.been.dao.EventCRecvADAO;
import batchJob.been.dao.EventCRecvBDAO;
import batchJob.been.dao.EventCRecvCDAO;
import batchJob.been.dao.EventCTranADAO;
import batchJob.been.dao.EventCTranBDAO;
import batchJob.been.dao.EventCTranCDAO;
import batchJob.been.dao.EventCTranMainDAO;
import batchJob.been.po.CpgSettingPo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.dao.GeneralDAO;
import com.tradevan.common.db.utils.ObjectsUtils;

public enum TableMapper {
	
	CPG_MAIN(CpgMainDo.class, CpgMainDAO.class), //
	CPG_SETTING(CpgSettingPo.class, CpgSettingDAO.class), //

	EVENT_C_RECV_A(EventCRecvADo.class, EventCRecvADAO.class), //
	EVENT_C_RECV_B(EventCRecvBDo.class, EventCRecvBDAO.class), //
	EVENT_C_RECV_C(EventCRecvCDo.class, EventCRecvCDAO.class), //

	EVENT_C_TRAN_MAIN(EventCTranMainDo.class, EventCTranMainDAO.class), //
	EVENT_C_TRAN_A(EventCTranADo.class, EventCTranADAO.class), //
	EVENT_C_TRAN_B(EventCTranBDo.class, EventCTranBDAO.class), //
	EVENT_C_TRAN_C(EventCTranCDo.class, EventCTranCDAO.class), //

	CPG_TRAN_MAIN(CpgTranMainDo.class, CpgTranMainDAO.class), //
	CPG_TRAN_POST(CpgTranPostDo.class, CpgTranPostDAO.class), //
	CPG_TRAN_DETAIL(CpgTranDetailDo.class, CpgTranDetailDAO.class), //

	;//
	final String entityClas;
	final String daoClas;

	final static Map<String, TableMapper> MAP;

	static {
		final Map<String, TableMapper> map = new HashMap<String, TableMapper>();
		for (final TableMapper mapper : TableMapper.values()) {
			map.put(mapper.entityClas, mapper);
		}
		MAP = Collections.unmodifiableMap(map);
	}

	private static String lookupDAOClassName(final Object object) {
		if (!MAP.containsKey(object.getClass().getName())) {
			throw new ApBusinessException("此無物件無定義"
					+ object.getClass().getName());
		}

		return MAP.get(object.getClass().getName()).daoClas;
	}

	private static String lookupDAONameByClass(final Class<?> object) {
		if (!MAP.containsKey(object.getName())) {
			throw new ApBusinessException("此無物件無定義" + object.getName());
		}
		return MAP.get(object.getName()).daoClas;
	}

	@SuppressWarnings("unchecked")
	public static <PO> GeneralDAO<PO> lookupDAO(final Class<PO> object) {
		final String daoClass = lookupDAONameByClass(object);
		return (GeneralDAO<PO>) ObjectsUtils.newInstance(daoClass);
	}

	@SuppressWarnings("unchecked")
	public static <PO> GeneralDAO<PO> lookupDAO(final PO object) {
		final String daoClass = lookupDAOClassName(object);
		return (GeneralDAO<PO>) ObjectsUtils.newInstance(daoClass);
	}

	private <PO, T extends GeneralDAO<PO>> TableMapper(
			final Class<PO> entityClas, final Class<T> daoClas) {
		this.entityClas = entityClas.getName();
		this.daoClas = daoClas.getName();
	}

}
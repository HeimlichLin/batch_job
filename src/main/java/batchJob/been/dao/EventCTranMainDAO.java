package batchJob.been.dao;

import batchJob.been.EventCTranMainDo;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class EventCTranMainDAO extends GeneralDAOImpl<EventCTranMainDo> {
	public static final EventCTranMainDAO INSTANCE = new EventCTranMainDAO();

	public EventCTranMainDAO() {
		super("EVENT_C_TRAN_MAIN");
	}

	protected static MapConverter<EventCTranMainDo> CONVERTER = new MapConverter<EventCTranMainDo>() {

		@Override
		public EventCTranMainDo convert(final DataObject dataObject) {
			final EventCTranMainDo vo = new EventCTranMainDo();
			vo.setFilename(dataObject.getString("FILENAME"));
			vo.setSendflag(dataObject.getString("SENDFLAG"));
			vo.setSendTime(dataObject.getString("SEND_TIME"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final EventCTranMainDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(EventCTranMainDo.COLUMNS.FILENAME.name(), po.getFilename());
			dataObject.setValue(EventCTranMainDo.COLUMNS.SENDFLAG.name(), po.getSendflag());
			dataObject.setValue(EventCTranMainDo.COLUMNS.SEND_TIME.name(), po.getSendTime());
			return dataObject;
		}
	};

	@Override
	public MapConverter<EventCTranMainDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final EventCTranMainDo po) {
		final SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(EventCTranMainDo.COLUMNS.FILENAME.name(), po.getFilename());
		return sqlWhere;
	}
}

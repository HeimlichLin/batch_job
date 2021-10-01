package batchJob.been.dao;

import batchJob.been.CpgTranMainDo;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class CpgTranMainDAO extends GeneralDAOImpl<CpgTranMainDo> {
	public static final CpgTranMainDAO INSTANCE = new CpgTranMainDAO();

	public CpgTranMainDAO() {
		super("CPG_TRAN_MAIN");
	}

	protected static MapConverter<CpgTranMainDo> CONVERTER = new MapConverter<CpgTranMainDo>() {

		@Override
		public CpgTranMainDo convert(final DataObject dataObject) {
			final CpgTranMainDo vo = new CpgTranMainDo();
			vo.setFilename(dataObject.getString("FILENAME"));
			vo.setSendflag(dataObject.getString("SENDFLAG"));
			vo.setSendTime(dataObject.getString("SEND_TIME"));
			vo.setMsgfunccode(dataObject.getString("MSGFUNCCODE"));
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setMessagetype(dataObject.getString("MESSAGETYPE"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final CpgTranMainDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(CpgTranMainDo.COLUMNS.FILENAME.name(), po.getFilename());
			dataObject.setValue(CpgTranMainDo.COLUMNS.SENDFLAG.name(), po.getSendflag());
			dataObject.setValue(CpgTranMainDo.COLUMNS.SEND_TIME.name(), po.getSendTime());
			dataObject.setValue(CpgTranMainDo.COLUMNS.MSGFUNCCODE.name(), po.getMsgfunccode());
			dataObject.setValue(CpgTranMainDo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(CpgTranMainDo.COLUMNS.MESSAGETYPE.name(), po.getMessagetype());
			return dataObject;
		}
	};

	@Override
	public MapConverter<CpgTranMainDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final CpgTranMainDo po) {
		final SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(CpgTranMainDo.COLUMNS.FILENAME.name(), po.getFilename());
		return sqlWhere;
	}
}

package batchJob.been.dao;

import batchJob.been.EventCTranBDo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class EventCTranBDAO extends GeneralDAOImpl<EventCTranBDo> {
	public static final EventCTranBDAO INSTANCE = new EventCTranBDAO();

	public EventCTranBDAO() {
		super("EVENT_C_TRAN_B");
	}

	protected static MapConverter<EventCTranBDo> CONVERTER = new MapConverter<EventCTranBDo>() {

		@Override
		public EventCTranBDo convert(final DataObject dataObject) {
			final EventCTranBDo vo = new EventCTranBDo();
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setPosttype(dataObject.getString("POSTTYPE"));
			vo.setType(dataObject.getString("TYPE"));
			vo.setYear(dataObject.getString("YEAR"));
			vo.setCountrycode(dataObject.getString("COUNTRYCODE"));
			vo.setExchangeagency(dataObject.getString("EXCHANGEAGENCY"));
			vo.setTotalpackageno(BigDecimalUtils.formObj(dataObject.getValue("TOTALPACKAGENO")));
			vo.setBagno(BigDecimalUtils.formObj(dataObject.getValue("BAGNO")));
			vo.setLastmark(dataObject.getString("LASTMARK"));
			vo.setPageno(BigDecimalUtils.formObj(dataObject.getValue("PAGENO")));
			vo.setQty(BigDecimalUtils.formObj(dataObject.getValue("QTY")));
			vo.setWeight(BigDecimalUtils.formObj(dataObject.getValue("WEIGHT")));
			vo.setSealdatetime(dataObject.getString("SEALDATETIME"));
			vo.setFilename(dataObject.getString("FILENAME"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final EventCTranBDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(EventCTranBDo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(EventCTranBDo.COLUMNS.POSTTYPE.name(), po.getPosttype());
			dataObject.setValue(EventCTranBDo.COLUMNS.TYPE.name(), po.getType());
			dataObject.setValue(EventCTranBDo.COLUMNS.YEAR.name(), po.getYear());
			dataObject.setValue(EventCTranBDo.COLUMNS.COUNTRYCODE.name(), po.getCountrycode());
			dataObject.setValue(EventCTranBDo.COLUMNS.EXCHANGEAGENCY.name(), po.getExchangeagency());
			dataObject.setValue(EventCTranBDo.COLUMNS.TOTALPACKAGENO.name(), po.getTotalpackageno());
			dataObject.setValue(EventCTranBDo.COLUMNS.BAGNO.name(), po.getBagno());
			dataObject.setValue(EventCTranBDo.COLUMNS.LASTMARK.name(), po.getLastmark());
			dataObject.setValue(EventCTranBDo.COLUMNS.PAGENO.name(), po.getPageno());
			dataObject.setValue(EventCTranBDo.COLUMNS.QTY.name(), po.getQty());
			dataObject.setValue(EventCTranBDo.COLUMNS.WEIGHT.name(), po.getWeight());
			dataObject.setValue(EventCTranBDo.COLUMNS.SEALDATETIME.name(), po.getSealdatetime());
			dataObject.setValue(EventCTranBDo.COLUMNS.FILENAME.name(), po.getFilename());
			return dataObject;
		}
	};

	@Override
	public MapConverter<EventCTranBDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final EventCTranBDo po) {
		throw new ApBusinessException("無pk不支援");
	}
}

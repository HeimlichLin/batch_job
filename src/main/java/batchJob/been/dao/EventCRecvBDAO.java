package batchJob.been.dao;

import batchJob.been.EventCRecvBDo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class EventCRecvBDAO extends GeneralDAOImpl<EventCRecvBDo> {
	public static final EventCRecvBDAO INSTANCE = new EventCRecvBDAO();

	public EventCRecvBDAO() {
		super("EVENT_C_RECV_B");
	}

	protected static MapConverter<EventCRecvBDo> CONVERTER = new MapConverter<EventCRecvBDo>() {

		@Override
		public EventCRecvBDo convert(final DataObject dataObject) {
			final EventCRecvBDo vo = new EventCRecvBDo();
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setPosttype(dataObject.getString("POSTTYPE"));
			vo.setType(dataObject.getString("TYPE"));
			vo.setYear(dataObject.getString("YEAR"));
			vo.setCountrycode(dataObject.getString("COUNTRYCODE"));
			vo.setExchangeagency(dataObject.getString("EXCHANGEAGENCY"));
			vo.setTotalpackageno(BigDecimalUtils.formObj(dataObject.getValue("TOTALPACKAGENO")));
			vo.setGoodtotalpackageyear(dataObject.getString("GOODTOTALPACKAGEYEAR"));
			vo.setGoodtotalpackageno(dataObject.getString("GOODTOTALPACKAGENO"));
			vo.setBagno(BigDecimalUtils.formObj(dataObject.getValue("BAGNO")));
			vo.setLastmark(dataObject.getString("LASTMARK"));
			vo.setPageno(BigDecimalUtils.formObj(dataObject.getValue("PAGENO")));
			vo.setQty(BigDecimalUtils.formObj(dataObject.getValue("QTY")));
			vo.setWeight(BigDecimalUtils.formObj(dataObject.getValue("WEIGHT")));
			vo.setSealdatetime(dataObject.getString("SEALDATETIME"));
			vo.setUpu(dataObject.getString("UPU"));
			vo.setFilename(dataObject.getString("FILENAME"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final EventCRecvBDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(EventCRecvBDo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(EventCRecvBDo.COLUMNS.POSTTYPE.name(), po.getPosttype());
			dataObject.setValue(EventCRecvBDo.COLUMNS.TYPE.name(), po.getType());
			dataObject.setValue(EventCRecvBDo.COLUMNS.YEAR.name(), po.getYear());
			dataObject.setValue(EventCRecvBDo.COLUMNS.COUNTRYCODE.name(), po.getCountrycode());
			dataObject.setValue(EventCRecvBDo.COLUMNS.EXCHANGEAGENCY.name(), po.getExchangeagency());
			dataObject.setValue(EventCRecvBDo.COLUMNS.TOTALPACKAGENO.name(), po.getTotalpackageno());
			dataObject.setValue(EventCRecvBDo.COLUMNS.GOODTOTALPACKAGEYEAR.name(), po.getGoodtotalpackageyear());
			dataObject.setValue(EventCRecvBDo.COLUMNS.GOODTOTALPACKAGENO.name(), po.getGoodtotalpackageno());
			dataObject.setValue(EventCRecvBDo.COLUMNS.BAGNO.name(), po.getBagno());
			dataObject.setValue(EventCRecvBDo.COLUMNS.LASTMARK.name(), po.getLastmark());
			dataObject.setValue(EventCRecvBDo.COLUMNS.PAGENO.name(), po.getPageno());
			dataObject.setValue(EventCRecvBDo.COLUMNS.QTY.name(), po.getQty());
			dataObject.setValue(EventCRecvBDo.COLUMNS.WEIGHT.name(), po.getWeight());
			dataObject.setValue(EventCRecvBDo.COLUMNS.SEALDATETIME.name(), po.getSealdatetime());
			dataObject.setValue(EventCRecvBDo.COLUMNS.UPU.name(), po.getUpu());
			dataObject.setValue(EventCRecvBDo.COLUMNS.FILENAME.name(), po.getFilename());
			return dataObject;
		}
	};

	@Override
	public MapConverter<EventCRecvBDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final EventCRecvBDo po) {
		throw new ApBusinessException("無pk不支援");
	}
}

package batchJob.been.dao;

import batchJob.been.EventCRecvCDo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class EventCRecvCDAO extends GeneralDAOImpl<EventCRecvCDo> {
	public static final EventCRecvCDAO INSTANCE = new EventCRecvCDAO();

	public EventCRecvCDAO() {
		super("EVENT_C_RECV_C");
	}

	protected static MapConverter<EventCRecvCDo> CONVERTER = new MapConverter<EventCRecvCDo>() {

		@Override
		public EventCRecvCDo convert(final DataObject dataObject) {
			final EventCRecvCDo vo = new EventCRecvCDo();
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
			vo.setPageno(BigDecimalUtils.formObj(dataObject.getValue("PAGENO")));
			vo.setCellno(BigDecimalUtils.formObj(dataObject.getValue("CELLNO")));
			vo.setPostno(dataObject.getString("POSTNO"));
			vo.setWeight(BigDecimalUtils.formObj(dataObject.getValue("WEIGHT")));
			vo.setSealdatetime(dataObject.getString("SEALDATETIME"));
			vo.setProvincetype(dataObject.getString("PROVINCETYPE"));
			vo.setBoxtype(dataObject.getString("BOXTYPE"));
			vo.setFilename(dataObject.getString("FILENAME"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final EventCRecvCDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(EventCRecvCDo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(EventCRecvCDo.COLUMNS.POSTTYPE.name(), po.getPosttype());
			dataObject.setValue(EventCRecvCDo.COLUMNS.TYPE.name(), po.getType());
			dataObject.setValue(EventCRecvCDo.COLUMNS.YEAR.name(), po.getYear());
			dataObject.setValue(EventCRecvCDo.COLUMNS.COUNTRYCODE.name(), po.getCountrycode());
			dataObject.setValue(EventCRecvCDo.COLUMNS.EXCHANGEAGENCY.name(), po.getExchangeagency());
			dataObject.setValue(EventCRecvCDo.COLUMNS.TOTALPACKAGENO.name(), po.getTotalpackageno());
			dataObject.setValue(EventCRecvCDo.COLUMNS.GOODTOTALPACKAGEYEAR.name(), po.getGoodtotalpackageyear());
			dataObject.setValue(EventCRecvCDo.COLUMNS.GOODTOTALPACKAGENO.name(), po.getGoodtotalpackageno());
			dataObject.setValue(EventCRecvCDo.COLUMNS.BAGNO.name(), po.getBagno());
			dataObject.setValue(EventCRecvCDo.COLUMNS.PAGENO.name(), po.getPageno());
			dataObject.setValue(EventCRecvCDo.COLUMNS.CELLNO.name(), po.getCellno());
			dataObject.setValue(EventCRecvCDo.COLUMNS.POSTNO.name(), po.getPostno());
			dataObject.setValue(EventCRecvCDo.COLUMNS.WEIGHT.name(), po.getWeight());
			dataObject.setValue(EventCRecvCDo.COLUMNS.SEALDATETIME.name(), po.getSealdatetime());
			dataObject.setValue(EventCRecvCDo.COLUMNS.PROVINCETYPE.name(), po.getProvincetype());
			dataObject.setValue(EventCRecvCDo.COLUMNS.BOXTYPE.name(), po.getBoxtype());
			dataObject.setValue(EventCRecvCDo.COLUMNS.FILENAME.name(), po.getFilename());
			return dataObject;
		}
	};

	@Override
	public MapConverter<EventCRecvCDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final EventCRecvCDo po) {
		throw new ApBusinessException("無pk不支援");
	}
}

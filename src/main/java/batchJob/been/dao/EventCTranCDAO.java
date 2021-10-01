package batchJob.been.dao;

import batchJob.been.EventCTranCDo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class EventCTranCDAO extends GeneralDAOImpl<EventCTranCDo> {
	public static final EventCTranCDAO INSTANCE = new EventCTranCDAO();

	public EventCTranCDAO() {
		super("EVENT_C_TRAN_C");
	}

	protected static MapConverter<EventCTranCDo> CONVERTER = new MapConverter<EventCTranCDo>() {

		@Override
		public EventCTranCDo convert(final DataObject dataObject) {
			final EventCTranCDo vo = new EventCTranCDo();
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setPosttype(dataObject.getString("POSTTYPE"));
			vo.setType(dataObject.getString("TYPE"));
			vo.setYear(dataObject.getString("YEAR"));
			vo.setCountrycode(dataObject.getString("COUNTRYCODE"));
			vo.setExchangeagency(dataObject.getString("EXCHANGEAGENCY"));
			vo.setTotalpackageno(BigDecimalUtils.formObj(dataObject.getValue("TOTALPACKAGENO")));
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
		public DataObject toDataObject(final EventCTranCDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(EventCTranCDo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(EventCTranCDo.COLUMNS.POSTTYPE.name(), po.getPosttype());
			dataObject.setValue(EventCTranCDo.COLUMNS.TYPE.name(), po.getType());
			dataObject.setValue(EventCTranCDo.COLUMNS.YEAR.name(), po.getYear());
			dataObject.setValue(EventCTranCDo.COLUMNS.COUNTRYCODE.name(), po.getCountrycode());
			dataObject.setValue(EventCTranCDo.COLUMNS.EXCHANGEAGENCY.name(), po.getExchangeagency());
			dataObject.setValue(EventCTranCDo.COLUMNS.TOTALPACKAGENO.name(), po.getTotalpackageno());
			dataObject.setValue(EventCTranCDo.COLUMNS.BAGNO.name(), po.getBagno());
			dataObject.setValue(EventCTranCDo.COLUMNS.PAGENO.name(), po.getPageno());
			dataObject.setValue(EventCTranCDo.COLUMNS.CELLNO.name(), po.getCellno());
			dataObject.setValue(EventCTranCDo.COLUMNS.POSTNO.name(), po.getPostno());
			dataObject.setValue(EventCTranCDo.COLUMNS.WEIGHT.name(), po.getWeight());
			dataObject.setValue(EventCTranCDo.COLUMNS.SEALDATETIME.name(), po.getSealdatetime());
			dataObject.setValue(EventCTranCDo.COLUMNS.PROVINCETYPE.name(), po.getProvincetype());
			dataObject.setValue(EventCTranCDo.COLUMNS.BOXTYPE.name(), po.getBoxtype());
			dataObject.setValue(EventCTranCDo.COLUMNS.FILENAME.name(), po.getFilename());
			return dataObject;
		}
	};

	@Override
	public MapConverter<EventCTranCDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final EventCTranCDo po) {
		throw new ApBusinessException("無pk不支援");
	}
}

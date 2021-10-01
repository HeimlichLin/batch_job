package batchJob.been.dao;

import batchJob.been.EventCTranADo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class EventCTranADAO extends GeneralDAOImpl<EventCTranADo> {
	public static final EventCTranADAO INSTANCE = new EventCTranADAO();

	public EventCTranADAO() {
		super("EVENT_C_TRAN_A");
	}

	protected static MapConverter<EventCTranADo> CONVERTER = new MapConverter<EventCTranADo>() {

		@Override
		public EventCTranADo convert(final DataObject dataObject) {
			final EventCTranADo vo = new EventCTranADo();
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setPosttype(dataObject.getString("POSTTYPE"));
			vo.setType(dataObject.getString("TYPE"));
			vo.setYear(dataObject.getString("YEAR"));
			vo.setCountrycode(dataObject.getString("COUNTRYCODE"));
			vo.setExchangeagency(dataObject.getString("EXCHANGEAGENCY"));
			vo.setTotalpackageno(BigDecimalUtils.formObj(dataObject.getValue("TOTALPACKAGENO")));
			vo.setPostmblno(dataObject.getString("POSTMBLNO"));
			vo.setLastyeartotalpackageno(dataObject.getString("LASTYEARTOTALPACKAGENO"));
			vo.setSealdatetime(dataObject.getString("SEALDATETIME"));
			vo.setTotalqty(BigDecimalUtils.formObj(dataObject.getValue("TOTALQTY")));
			vo.setTotalpagecount(BigDecimalUtils.formObj(dataObject.getValue("TOTALPAGECOUNT")));
			vo.setTotalbagnumber(BigDecimalUtils.formObj(dataObject.getValue("TOTALBAGNUMBER")));
			vo.setTotalweight(BigDecimalUtils.formObj(dataObject.getValue("TOTALWEIGHT")));
			vo.setFilename(dataObject.getString("FILENAME"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final EventCTranADo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(EventCTranADo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(EventCTranADo.COLUMNS.POSTTYPE.name(), po.getPosttype());
			dataObject.setValue(EventCTranADo.COLUMNS.TYPE.name(), po.getType());
			dataObject.setValue(EventCTranADo.COLUMNS.YEAR.name(), po.getYear());
			dataObject.setValue(EventCTranADo.COLUMNS.COUNTRYCODE.name(), po.getCountrycode());
			dataObject.setValue(EventCTranADo.COLUMNS.EXCHANGEAGENCY.name(), po.getExchangeagency());
			dataObject.setValue(EventCTranADo.COLUMNS.TOTALPACKAGENO.name(), po.getTotalpackageno());
			dataObject.setValue(EventCTranADo.COLUMNS.POSTMBLNO.name(), po.getPostmblno());
			dataObject.setValue(EventCTranADo.COLUMNS.LASTYEARTOTALPACKAGENO.name(), po.getLastyeartotalpackageno());
			dataObject.setValue(EventCTranADo.COLUMNS.SEALDATETIME.name(), po.getSealdatetime());
			dataObject.setValue(EventCTranADo.COLUMNS.TOTALQTY.name(), po.getTotalqty());
			dataObject.setValue(EventCTranADo.COLUMNS.TOTALPAGECOUNT.name(), po.getTotalpagecount());
			dataObject.setValue(EventCTranADo.COLUMNS.TOTALBAGNUMBER.name(), po.getTotalbagnumber());
			dataObject.setValue(EventCTranADo.COLUMNS.TOTALWEIGHT.name(), po.getTotalweight());
			dataObject.setValue(EventCTranADo.COLUMNS.FILENAME.name(), po.getFilename());
			return dataObject;
		}
	};

	@Override
	public MapConverter<EventCTranADo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final EventCTranADo po) {
		throw new ApBusinessException("無pk不支援");
	}
}

package batchJob.been.dao;

import batchJob.been.EventCRecvADo;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class EventCRecvADAO extends GeneralDAOImpl<EventCRecvADo> {
	public static final EventCRecvADAO INSTANCE = new EventCRecvADAO();

	public EventCRecvADAO() {
		super("EVENT_C_RECV_A");
	}

	protected static MapConverter<EventCRecvADo> CONVERTER = new MapConverter<EventCRecvADo>() {

		@Override
		public EventCRecvADo convert(final DataObject dataObject) {
			final EventCRecvADo vo = new EventCRecvADo();
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setPosttype(dataObject.getString("POSTTYPE"));
			vo.setType(dataObject.getString("TYPE"));
			vo.setYear(dataObject.getString("YEAR"));
			vo.setCountrycode(dataObject.getString("COUNTRYCODE"));
			vo.setExchangeagency(dataObject.getString("EXCHANGEAGENCY"));
			vo.setTotalpackageno(BigDecimalUtils.formObj(dataObject.getValue("TOTALPACKAGENO")));
			vo.setPostmblno(dataObject.getString("POSTMBLNO"));
			vo.setSealdatetime(dataObject.getString("SEALDATETIME"));
			vo.setTotalqty(BigDecimalUtils.formObj(dataObject.getValue("TOTALQTY")));
			vo.setTotalpagecount(BigDecimalUtils.formObj(dataObject.getValue("TOTALPAGECOUNT")));
			vo.setTotalbagnumber(BigDecimalUtils.formObj(dataObject.getValue("TOTALBAGNUMBER")));
			vo.setTotalweight(BigDecimalUtils.formObj(dataObject.getValue("TOTALWEIGHT")));
			vo.setFilename(dataObject.getString("FILENAME"));
			vo.setGoodtotalpackageyear(dataObject.getString("GOODTOTALPACKAGEYEAR"));
			vo.setGoodtotalpackageno(dataObject.getString("GOODTOTALPACKAGENO"));
			vo.setOldfilename(dataObject.getString("OLDFILENAME"));
			vo.setTakeoffflight(dataObject.getString("TAKEOFFFLIGHT"));
			vo.setSendtime(dataObject.getString("SENDTIME"));
			vo.setArrivaltime(dataObject.getString("ARRIVALTIME"));
			vo.setCreatetime(dataObject.getString("CREATETIME"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final EventCRecvADo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(EventCRecvADo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(EventCRecvADo.COLUMNS.POSTTYPE.name(), po.getPosttype());
			dataObject.setValue(EventCRecvADo.COLUMNS.TYPE.name(), po.getType());
			dataObject.setValue(EventCRecvADo.COLUMNS.YEAR.name(), po.getYear());
			dataObject.setValue(EventCRecvADo.COLUMNS.COUNTRYCODE.name(), po.getCountrycode());
			dataObject.setValue(EventCRecvADo.COLUMNS.EXCHANGEAGENCY.name(), po.getExchangeagency());
			dataObject.setValue(EventCRecvADo.COLUMNS.TOTALPACKAGENO.name(), po.getTotalpackageno());
			dataObject.setValue(EventCRecvADo.COLUMNS.POSTMBLNO.name(), po.getPostmblno());
			dataObject.setValue(EventCRecvADo.COLUMNS.SEALDATETIME.name(), po.getSealdatetime());
			dataObject.setValue(EventCRecvADo.COLUMNS.TOTALQTY.name(), po.getTotalqty());
			dataObject.setValue(EventCRecvADo.COLUMNS.TOTALPAGECOUNT.name(), po.getTotalpagecount());
			dataObject.setValue(EventCRecvADo.COLUMNS.TOTALBAGNUMBER.name(), po.getTotalbagnumber());
			dataObject.setValue(EventCRecvADo.COLUMNS.TOTALWEIGHT.name(), po.getTotalweight());
			dataObject.setValue(EventCRecvADo.COLUMNS.FILENAME.name(), po.getFilename());
			dataObject.setValue(EventCRecvADo.COLUMNS.GOODTOTALPACKAGEYEAR.name(), po.getGoodtotalpackageyear());
			dataObject.setValue(EventCRecvADo.COLUMNS.GOODTOTALPACKAGENO.name(), po.getGoodtotalpackageno());
			dataObject.setValue(EventCRecvADo.COLUMNS.OLDFILENAME.name(), po.getOldfilename());
			dataObject.setValue(EventCRecvADo.COLUMNS.TAKEOFFFLIGHT.name(), po.getTakeoffflight());
			dataObject.setValue(EventCRecvADo.COLUMNS.SENDTIME.name(), po.getSendtime());
			dataObject.setValue(EventCRecvADo.COLUMNS.ARRIVALTIME.name(), po.getArrivaltime());
			dataObject.setValue(EventCRecvADo.COLUMNS.CREATETIME.name(), po.getCreatetime());
			return dataObject;
		}
	};

	@Override
	public MapConverter<EventCRecvADo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final EventCRecvADo po) {
		final SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(EventCRecvADo.COLUMNS.FILENAME.name(), po.getFilename());
		return sqlWhere;
	}
}

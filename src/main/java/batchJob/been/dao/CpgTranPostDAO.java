package batchJob.been.dao;

import batchJob.been.CpgTranPostDo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class CpgTranPostDAO extends GeneralDAOImpl<CpgTranPostDo> {
	public static final CpgTranPostDAO INSTANCE = new CpgTranPostDAO();

	public CpgTranPostDAO() {
		super("CPG_TRAN_POST");
	}

	protected static MapConverter<CpgTranPostDo> CONVERTER = new MapConverter<CpgTranPostDo>() {

		@Override
		public CpgTranPostDo convert(final DataObject dataObject) {
			final CpgTranPostDo vo = new CpgTranPostDo();
			vo.setFilename(dataObject.getString("FILENAME"));
			vo.setPostmblno(dataObject.getString("POSTMBLNO"));
			vo.setOrircvfile(dataObject.getString("ORIRCVFILE"));
			vo.setFlightno(dataObject.getString("FLIGHTNO"));
			vo.setFlightdatetime(dataObject.getString("FLIGHTDATETIME"));
			vo.setPosttype(dataObject.getString("POSTTYPE"));
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setType(dataObject.getString("TYPE"));
			vo.setYear(dataObject.getString("YEAR"));
			vo.setCountrycode(dataObject.getString("COUNTRYCODE"));
			vo.setExchangeagency(dataObject.getString("EXCHANGEAGENCY"));
			vo.setTotalpackageno(BigDecimalUtils.formObj(dataObject.getValue("TOTALPACKAGENO")));
			vo.setGoodtotalpackageyear(dataObject.getString("GOODTOTALPACKAGEYEAR"));
			vo.setGoodtotalpackageno(dataObject.getString("GOODTOTALPACKAGENO"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final CpgTranPostDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(CpgTranPostDo.COLUMNS.FILENAME.name(), po.getFilename());
			dataObject.setValue(CpgTranPostDo.COLUMNS.POSTMBLNO.name(), po.getPostmblno());
			dataObject.setValue(CpgTranPostDo.COLUMNS.ORIRCVFILE.name(), po.getOrircvfile());
			dataObject.setValue(CpgTranPostDo.COLUMNS.FLIGHTNO.name(), po.getFlightno());
			dataObject.setValue(CpgTranPostDo.COLUMNS.FLIGHTDATETIME.name(), po.getFlightdatetime());
			dataObject.setValue(CpgTranPostDo.COLUMNS.POSTTYPE.name(), po.getPosttype());
			dataObject.setValue(CpgTranPostDo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(CpgTranPostDo.COLUMNS.TYPE.name(), po.getType());
			dataObject.setValue(CpgTranPostDo.COLUMNS.YEAR.name(), po.getYear());
			dataObject.setValue(CpgTranPostDo.COLUMNS.COUNTRYCODE.name(), po.getCountrycode());
			dataObject.setValue(CpgTranPostDo.COLUMNS.EXCHANGEAGENCY.name(), po.getExchangeagency());
			dataObject.setValue(CpgTranPostDo.COLUMNS.TOTALPACKAGENO.name(), po.getTotalpackageno());
			dataObject.setValue(CpgTranPostDo.COLUMNS.GOODTOTALPACKAGEYEAR.name(), po.getGoodtotalpackageyear());
			dataObject.setValue(CpgTranPostDo.COLUMNS.GOODTOTALPACKAGENO.name(), po.getGoodtotalpackageno());
			return dataObject;
		}
	};

	@Override
	public MapConverter<CpgTranPostDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final CpgTranPostDo po) {
		throw new ApBusinessException("無pk不支援");
	}
}

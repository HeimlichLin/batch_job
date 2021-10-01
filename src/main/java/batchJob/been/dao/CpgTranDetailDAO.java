package batchJob.been.dao;

import batchJob.been.CpgTranDetailDo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class CpgTranDetailDAO extends GeneralDAOImpl<CpgTranDetailDo> {
	public static final CpgTranDetailDAO INSTANCE = new CpgTranDetailDAO();

	public CpgTranDetailDAO() {
		super("CPG_TRAN_DETAIL");
	}

	protected static MapConverter<CpgTranDetailDo> CONVERTER = new MapConverter<CpgTranDetailDo>() {

		@Override
		public CpgTranDetailDo convert(final DataObject dataObject) {
			final CpgTranDetailDo vo = new CpgTranDetailDo();
			vo.setFilename(dataObject.getString("FILENAME"));
			vo.setPostno(dataObject.getString("POSTNO"));
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setPosttype(dataObject.getString("POSTTYPE"));
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
		public DataObject toDataObject(final CpgTranDetailDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(CpgTranDetailDo.COLUMNS.FILENAME.name(), po.getFilename());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.POSTNO.name(), po.getPostno());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.POSTTYPE.name(), po.getPosttype());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.TYPE.name(), po.getType());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.YEAR.name(), po.getYear());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.COUNTRYCODE.name(), po.getCountrycode());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.EXCHANGEAGENCY.name(), po.getExchangeagency());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.TOTALPACKAGENO.name(), po.getTotalpackageno());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.GOODTOTALPACKAGEYEAR.name(), po.getGoodtotalpackageyear());
			dataObject.setValue(CpgTranDetailDo.COLUMNS.GOODTOTALPACKAGENO.name(), po.getGoodtotalpackageno());
			return dataObject;
		}
	};

	@Override
	public MapConverter<CpgTranDetailDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final CpgTranDetailDo po) {
		throw new ApBusinessException("無pk不支援");
	}
}

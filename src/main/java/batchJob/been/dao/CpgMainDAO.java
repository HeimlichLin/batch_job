package batchJob.been.dao;

import batchJob.been.CpgMainDo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class CpgMainDAO extends GeneralDAOImpl<CpgMainDo> {
	public static final CpgMainDAO INSTANCE = new CpgMainDAO();

	public CpgMainDAO() {
		super("CPG_MAIN");
	}

	protected static MapConverter<CpgMainDo> CONVERTER = new MapConverter<CpgMainDo>() {

		@Override
		public CpgMainDo convert(final DataObject dataObject) {
			final CpgMainDo vo = new CpgMainDo();
			vo.setMessageid(dataObject.getString("MESSAGEID"));
			vo.setMessagename(dataObject.getString("MESSAGENAME"));
			vo.setMessageversion(dataObject.getString("MESSAGEVERSION"));
			vo.setSenderid(dataObject.getString("SENDERID"));
			vo.setRecipienttype(dataObject.getString("RECIPIENTTYPE"));
			vo.setRecipientid(dataObject.getString("RECIPIENTID"));
			vo.setMsgfunccode(dataObject.getString("MSGFUNCCODE"));
			vo.setMessagetype(dataObject.getString("MESSAGETYPE"));
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setPosttype(dataObject.getString("POSTTYPE"));
			vo.setYear(dataObject.getString("YEAR"));
			vo.setCountrycode(dataObject.getString("COUNTRYCODE"));
			vo.setExchangeagency(dataObject.getString("EXCHANGEAGENCY"));
			vo.setTotalpackageno(BigDecimalUtils.formObj(dataObject.getValue("TOTALPACKAGENO")));
			vo.setSealdatetime(dataObject.getString("SEALDATETIME"));
			vo.setPostmblno(dataObject.getString("POSTMBLNO"));
			vo.setLastyeartotalpackageno(dataObject.getString("LASTYEARTOTALPACKAGENO"));
			vo.setTotalqty(BigDecimalUtils.formObj(dataObject.getValue("TOTALQTY")));
			vo.setTotalpagecount(BigDecimalUtils.formObj(dataObject.getValue("TOTALPAGECOUNT")));
			vo.setTotalbagnumber(BigDecimalUtils.formObj(dataObject.getValue("TOTALBAGNUMBER")));
			vo.setTotalweight(BigDecimalUtils.formObj(dataObject.getValue("TOTALWEIGHT")));
			vo.setOtherfields(dataObject.getString("OTHERFIELDS"));
			vo.setFilename(dataObject.getString("FILENAME"));
			vo.setCreatetime(dataObject.getString("CREATETIME"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final CpgMainDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(CpgMainDo.COLUMNS.MESSAGEID.name(), po.getMessageid());
			dataObject.setValue(CpgMainDo.COLUMNS.MESSAGENAME.name(), po.getMessagename());
			dataObject.setValue(CpgMainDo.COLUMNS.MESSAGEVERSION.name(), po.getMessageversion());
			dataObject.setValue(CpgMainDo.COLUMNS.SENDERID.name(), po.getSenderid());
			dataObject.setValue(CpgMainDo.COLUMNS.RECIPIENTTYPE.name(), po.getRecipienttype());
			dataObject.setValue(CpgMainDo.COLUMNS.RECIPIENTID.name(), po.getRecipientid());
			dataObject.setValue(CpgMainDo.COLUMNS.MSGFUNCCODE.name(), po.getMsgfunccode());
			dataObject.setValue(CpgMainDo.COLUMNS.MESSAGETYPE.name(), po.getMessagetype());
			dataObject.setValue(CpgMainDo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(CpgMainDo.COLUMNS.POSTTYPE.name(), po.getPosttype());
			dataObject.setValue(CpgMainDo.COLUMNS.YEAR.name(), po.getYear());
			dataObject.setValue(CpgMainDo.COLUMNS.COUNTRYCODE.name(), po.getCountrycode());
			dataObject.setValue(CpgMainDo.COLUMNS.EXCHANGEAGENCY.name(), po.getExchangeagency());
			dataObject.setValue(CpgMainDo.COLUMNS.TOTALPACKAGENO.name(), po.getTotalpackageno());
			dataObject.setValue(CpgMainDo.COLUMNS.SEALDATETIME.name(), po.getSealdatetime());
			dataObject.setValue(CpgMainDo.COLUMNS.POSTMBLNO.name(), po.getPostmblno());
			dataObject.setValue(CpgMainDo.COLUMNS.LASTYEARTOTALPACKAGENO.name(), po.getLastyeartotalpackageno());
			dataObject.setValue(CpgMainDo.COLUMNS.TOTALQTY.name(), po.getTotalqty());
			dataObject.setValue(CpgMainDo.COLUMNS.TOTALPAGECOUNT.name(), po.getTotalpagecount());
			dataObject.setValue(CpgMainDo.COLUMNS.TOTALBAGNUMBER.name(), po.getTotalbagnumber());
			dataObject.setValue(CpgMainDo.COLUMNS.TOTALWEIGHT.name(), po.getTotalweight());
			dataObject.setValue(CpgMainDo.COLUMNS.OTHERFIELDS.name(), po.getOtherfields());
			dataObject.setValue(CpgMainDo.COLUMNS.FILENAME.name(), po.getFilename());
			dataObject.setValue(CpgMainDo.COLUMNS.CREATETIME.name(), po.getCreatetime());
			return dataObject;
		}
	};

	@Override
	public MapConverter<CpgMainDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final CpgMainDo po) {
		throw new ApBusinessException("無pk不支援");
	}
}

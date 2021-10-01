package batchJob.been.dao;

import batchJob.been.po.CpgSettingPo;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class CpgSettingDAO extends GeneralDAOImpl<CpgSettingPo> {
	public static final CpgSettingDAO INSTANCE = new CpgSettingDAO();

	public CpgSettingDAO() {
		super("CPG_SETTING");
	}

	protected static MapConverter<CpgSettingPo> CONVERTER = new MapConverter<CpgSettingPo>() {

		@Override
		public CpgSettingPo convert(DataObject dataObject) {
			final CpgSettingPo vo = new CpgSettingPo();
			vo.setPostspecialaccount(dataObject.getString("POSTSPECIALACCOUNT"));
			vo.setHost(dataObject.getString("HOST"));
			vo.setUserid(dataObject.getString("USERID"));
			vo.setPwd(dataObject.getString("PWD"));
			vo.setPort(dataObject.getString("PORT"));
			vo.setRcvId(dataObject.getString("RCV_ID"));
			vo.setBfNo(dataObject.getString("BF_NO"));
			vo.setMessagetype(dataObject.getString("MESSAGETYPE"));
			vo.setSendFtpPath(dataObject.getString("SEND_FTP_PATH"));
			vo.setGetFtpPath(dataObject.getString("GET_FTP_PATH"));
			return vo;
		}

		@Override
		public DataObject toDataObject(CpgSettingPo po) {
			DataObject dataObject = new DataObject();
			dataObject.setValue(CpgSettingPo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
			dataObject.setValue(CpgSettingPo.COLUMNS.HOST.name(), po.getHost());
			dataObject.setValue(CpgSettingPo.COLUMNS.USERID.name(), po.getUserid());
			dataObject.setValue(CpgSettingPo.COLUMNS.PWD.name(), po.getPwd());
			dataObject.setValue(CpgSettingPo.COLUMNS.PORT.name(), po.getPort());
			dataObject.setValue(CpgSettingPo.COLUMNS.RCV_ID.name(), po.getRcvId());
			dataObject.setValue(CpgSettingPo.COLUMNS.BF_NO.name(), po.getBfNo());
			dataObject.setValue(CpgSettingPo.COLUMNS.MESSAGETYPE.name(), po.getMessagetype());
			dataObject.setValue(CpgSettingPo.COLUMNS.SEND_FTP_PATH.name(), po.getSendFtpPath());
			dataObject.setValue(CpgSettingPo.COLUMNS.GET_FTP_PATH.name(), po.getGetFtpPath());
			return dataObject;
		}
	};

	@Override
	public MapConverter<CpgSettingPo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(CpgSettingPo po) {
		SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(CpgSettingPo.COLUMNS.POSTSPECIALACCOUNT.name(), po.getPostspecialaccount());
		return sqlWhere;
	}
}

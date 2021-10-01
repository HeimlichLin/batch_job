package batchJob.been.dao;

import batchJob.been.CpgDetailDo;
import batchJob.common.exception.ApBusinessException;

import com.tradevan.common.db.MapConverter;
import com.tradevan.common.db.dao.impl.GeneralDAOImpl;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.SqlWhere;

public class CpgDetailDAO extends GeneralDAOImpl<CpgDetailDo> {
	public static final CpgDetailDAO INSTANCE = new CpgDetailDAO();

	public CpgDetailDAO() {
		super("CPG_DETAIL");
	}

	protected static MapConverter<CpgDetailDo> CONVERTER = new MapConverter<CpgDetailDo>() {

		@Override
		public CpgDetailDo convert(final DataObject dataObject) {
			final CpgDetailDo vo = new CpgDetailDo();
			vo.setBagsfieldname(dataObject.getString("BAGSFIELDNAME"));
			vo.setBagsfieldvalue(dataObject.getString("BAGSFIELDVALUE"));
			vo.setBagno(dataObject.getString("BAGNO"));
			vo.setBagslastmark(dataObject.getString("BAGSLASTMARK"));
			vo.setBagspageno(dataObject.getString("BAGSPAGENO"));
			vo.setBagsqty(BigDecimalUtils.formObj(dataObject.getValue("BAGSQTY")));
			vo.setBagsweight(BigDecimalUtils.formObj(dataObject.getValue("BAGSWEIGHT")));
			vo.setBagsotherfields(dataObject.getString("BAGSOTHERFIELDS"));
			vo.setItemsfieldname(dataObject.getString("ITEMSFIELDNAME"));
			vo.setItemsfieldvalue(dataObject.getString("ITEMSFIELDVALUE"));
			vo.setItemspageno(dataObject.getString("ITEMSPAGENO"));
			vo.setItemscellno(dataObject.getString("ITEMSCELLNO"));
			vo.setItemspostno(dataObject.getString("ITEMSPOSTNO"));
			vo.setItemsqty(dataObject.getString("ITEMSQTY"));
			vo.setItemsqtyunit(dataObject.getString("ITEMSQTYUNIT"));
			vo.setItemsweight(BigDecimalUtils.formObj(dataObject.getValue("ITEMSWEIGHT")));
			vo.setDeclarevalueamt(dataObject.getString("DECLAREVALUEAMT"));
			vo.setOrigincountry(dataObject.getString("ORIGINCOUNTRY"));
			vo.setProvincetype(dataObject.getString("PROVINCETYPE"));
			vo.setBoxtype(dataObject.getString("BOXTYPE"));
			vo.setDeliverdatetime(dataObject.getString("DELIVERDATETIME"));
			vo.setSendername(dataObject.getString("SENDERNAME"));
			vo.setSenderaddress(dataObject.getString("SENDERADDRESS"));
			vo.setReceivername(dataObject.getString("RECEIVERNAME"));
			vo.setReceiveraddress(dataObject.getString("RECEIVERADDRESS"));
			vo.setGoodsdescription(dataObject.getString("GOODSDESCRIPTION"));
			vo.setOtherfields(dataObject.getString("OTHERFIELDS"));
			vo.setFilename(dataObject.getString("FILENAME"));
			return vo;
		}

		@Override
		public DataObject toDataObject(final CpgDetailDo po) {
			final DataObject dataObject = new DataObject();
			dataObject.setValue(CpgDetailDo.COLUMNS.BAGSFIELDNAME.name(), po.getBagsfieldname());
			dataObject.setValue(CpgDetailDo.COLUMNS.BAGSFIELDVALUE.name(), po.getBagsfieldvalue());
			dataObject.setValue(CpgDetailDo.COLUMNS.BAGNO.name(), po.getBagno());
			dataObject.setValue(CpgDetailDo.COLUMNS.BAGSLASTMARK.name(), po.getBagslastmark());
			dataObject.setValue(CpgDetailDo.COLUMNS.BAGSPAGENO.name(), po.getBagspageno());
			dataObject.setValue(CpgDetailDo.COLUMNS.BAGSQTY.name(), po.getBagsqty());
			dataObject.setValue(CpgDetailDo.COLUMNS.BAGSWEIGHT.name(), po.getBagsweight());
			dataObject.setValue(CpgDetailDo.COLUMNS.BAGSOTHERFIELDS.name(), po.getBagsotherfields());
			dataObject.setValue(CpgDetailDo.COLUMNS.ITEMSFIELDNAME.name(), po.getItemsfieldname());
			dataObject.setValue(CpgDetailDo.COLUMNS.ITEMSFIELDVALUE.name(), po.getItemsfieldvalue());
			dataObject.setValue(CpgDetailDo.COLUMNS.ITEMSPAGENO.name(), po.getItemspageno());
			dataObject.setValue(CpgDetailDo.COLUMNS.ITEMSCELLNO.name(), po.getItemscellno());
			dataObject.setValue(CpgDetailDo.COLUMNS.ITEMSPOSTNO.name(), po.getItemspostno());
			dataObject.setValue(CpgDetailDo.COLUMNS.ITEMSQTY.name(), po.getItemsqty());
			dataObject.setValue(CpgDetailDo.COLUMNS.ITEMSQTYUNIT.name(), po.getItemsqtyunit());
			dataObject.setValue(CpgDetailDo.COLUMNS.ITEMSWEIGHT.name(), po.getItemsweight());
			dataObject.setValue(CpgDetailDo.COLUMNS.DECLAREVALUEAMT.name(), po.getDeclarevalueamt());
			dataObject.setValue(CpgDetailDo.COLUMNS.ORIGINCOUNTRY.name(), po.getOrigincountry());
			dataObject.setValue(CpgDetailDo.COLUMNS.PROVINCETYPE.name(), po.getProvincetype());
			dataObject.setValue(CpgDetailDo.COLUMNS.BOXTYPE.name(), po.getBoxtype());
			dataObject.setValue(CpgDetailDo.COLUMNS.DELIVERDATETIME.name(), po.getDeliverdatetime());
			dataObject.setValue(CpgDetailDo.COLUMNS.SENDERNAME.name(), po.getSendername());
			dataObject.setValue(CpgDetailDo.COLUMNS.SENDERADDRESS.name(), po.getSenderaddress());
			dataObject.setValue(CpgDetailDo.COLUMNS.RECEIVERNAME.name(), po.getReceivername());
			dataObject.setValue(CpgDetailDo.COLUMNS.RECEIVERADDRESS.name(), po.getReceiveraddress());
			dataObject.setValue(CpgDetailDo.COLUMNS.GOODSDESCRIPTION.name(), po.getGoodsdescription());
			dataObject.setValue(CpgDetailDo.COLUMNS.OTHERFIELDS.name(), po.getOtherfields());
			dataObject.setValue(CpgDetailDo.COLUMNS.FILENAME.name(), po.getFilename());
			return dataObject;
		}
	};

	@Override
	public MapConverter<CpgDetailDo> getConverter() {
		return CONVERTER;
	}

	@Override
	public SqlWhere getPkSqlWhere(final CpgDetailDo po) {
		throw new ApBusinessException("無pk不支援");
	}
}

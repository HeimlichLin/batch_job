package batchJob.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.java.common.date.YYYYMMDDUtils;
import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.common.db.utils.BigDecimalUtils;
import com.tradevan.taurus.xdao.XdaoException;

import batchJob.been.CpgDetailDo;
import batchJob.been.CpgMainDo;
import batchJob.been.EventCTranADo;
import batchJob.been.EventCTranBDo;
import batchJob.been.EventCTranCDo;
import batchJob.been.EventCTranMainDo;
import batchJob.been.vo.fpg.FPGMessageHeaderVo;
import batchJob.been.vo.fpg.FTZLFPGProcECVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlBagVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlBodyVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlItemVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlMessageVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlPostMainInfoVo;
import batchJob.common.codegenerate.MessageTy;
import batchJob.common.codegenerate.SendFlag;
import batchJob.common.exception.ApBusinessException;
import batchJob.model.CPGMainDetailModel;
import batchJob.model.ICPGMainDetailModel;
import batchJob.model.XdaoSessionManager;

public class CTZLFPGRunServiceImpl implements CTZLFPGRunService {
	
	private List<CpgDetailDo> toCpgDetialDos(final FTZLFPGcXmlMessageVo message) {
		final List<CpgDetailDo> fpgDetialDos = new ArrayList<CpgDetailDo>();
		for (final FTZLFPGcXmlBodyVo body : message.getBody()) {
			for (final FTZLFPGcXmlPostMainInfoVo vo : body.getPostMainInfoVos()) {
				for (final FTZLFPGcXmlBagVo bag : vo.getBags()) {
					for (final FTZLFPGcXmlItemVo item : bag.getItems()) {

						// Bag
						final CpgDetailDo fpgDetialDo = new CpgDetailDo();
						fpgDetialDo.setFilename(message.getFileNmae());
						fpgDetialDo.setBagsfieldname(bag.getFieldName());
						fpgDetialDo.setBagsfieldvalue(bag.getFieldValue());
						fpgDetialDo.setBagno(bag.getBagNo());
						fpgDetialDo.setBagslastmark(bag.getLastMark());
						fpgDetialDo.setBagspageno(bag.getPageNo());
						fpgDetialDo.setBagsqty(this.getBigDecimal(bag.getQty()));
						fpgDetialDo.setBagsweight(this.getBigDecimal(bag.getWeight()));
						fpgDetialDo.setBagsotherfields(bag.getOtherFields());

						// Item
						fpgDetialDo.setItemsfieldname(item.getFieldName());
						fpgDetialDo.setItemsfieldvalue(item.getFieldValue());
						fpgDetialDo.setItemspageno(item.getPageNo());
						fpgDetialDo.setItemscellno(item.getCellNo());
						fpgDetialDo.setItemspostno(item.getPostNo());
						fpgDetialDo.setItemsqty(item.getQty());
						fpgDetialDo.setItemsqtyunit(item.getQtyUnit());
						fpgDetialDo.setItemsweight(this.getBigDecimal(item.getWeight()));
						fpgDetialDo.setDeclarevalueamt(item.getDeclareValueAmt());
						fpgDetialDo.setOrigincountry(item.getOriginCountry());
						fpgDetialDo.setProvincetype(item.getProvinceType());
						fpgDetialDo.setBoxtype(item.getBoxType());
						fpgDetialDo.setDeliverdatetime(item.getDeliverDateTime());
						fpgDetialDo.setSendername(item.getSenderName());
						fpgDetialDo.setSenderaddress(item.getSenderAddress());
						fpgDetialDo.setReceivername(item.getReceiverName());
						fpgDetialDo.setReceiveraddress(item.getReceiverAddress());
						fpgDetialDo.setGoodsdescription(item.getGoodsDescription());
						fpgDetialDo.setOtherfields(item.getOtherFields());
						fpgDetialDos.add(fpgDetialDo);

					}

				}
			}

		}

		return fpgDetialDos;

	}

	private List<CpgMainDo> toCpgMainDo(final FTZLFPGcXmlMessageVo messageVo) {
		final List<CpgMainDo> fpgMainDos = new ArrayList<CpgMainDo>();

		for (final FTZLFPGcXmlBodyVo vo : messageVo.getBody()) {
			for (final FTZLFPGcXmlPostMainInfoVo postMain : vo.getPostMainInfoVos()) {
				final CpgMainDo fpgMainDo = new CpgMainDo();
				fpgMainDo.setFilename(messageVo.getFileNmae());//
				fpgMainDo.setMessageid("CPG");
				fpgMainDo.setRecipienttype("TVP");

				// HEADER
				for (final FPGMessageHeaderVo cfpMessageHeaderVo : messageVo.getHeaders()) {
					fpgMainDo.setMessageversion(cfpMessageHeaderVo.getMessageVersion());
					fpgMainDo.setSenderid(cfpMessageHeaderVo.getSenderID());
					fpgMainDo.setRecipientid(cfpMessageHeaderVo.getRecipientID());
				}

				// BODY
				fpgMainDo.setMessagetype(vo.getMessaggeType());
				fpgMainDo.setMsgfunccode(vo.getMsgFuncCode());
				final String messagename = StringUtils.equals(vo.getMessaggeType(), MessageTy.F.name()) ? //
				"自轉郵貨物清單"
						: "貨轉郵貨物清單";
				fpgMainDo.setMessagename(messagename);
				fpgMainDo.setMsgfunccode(vo.getMsgFuncCode());

				// POST_MAIN_INFO
				fpgMainDo.setPosttype(postMain.getPostType());
				fpgMainDo.setPostspecialaccount(postMain.getPostSpecialAccount());
				fpgMainDo.setYear(postMain.getYear());
				fpgMainDo.setCountrycode(postMain.getCountryCode());
				fpgMainDo.setExchangeagency(postMain.getExchangeAgency());
				fpgMainDo.setTotalpackageno(BigDecimalUtils.formObj(postMain.getTotalPackageNo()));
				fpgMainDo.setSealdatetime(postMain.getSealDatetime());
				fpgMainDo.setPostmblno(postMain.getPostMBLNo());
				fpgMainDo.setLastyeartotalpackageno(postMain.getLastYearTotalPackageNo());
				fpgMainDo.setTotalqty(this.getBigDecimal(postMain.getTotalQty()));
				fpgMainDo.setTotalpagecount(this.getBigDecimal(postMain.getTotalPageCount()));
				fpgMainDo.setTotalbagnumber(this.getBigDecimal(postMain.getTotalBagNumber()));
				fpgMainDo.setTotalweight(this.getBigDecimal(postMain.getTotalWeight()));
				fpgMainDo.setOtherfields(postMain.getOtherFields());
				fpgMainDo.setCreatetime(YYYYMMDDUtils.getText());
				fpgMainDos.add(fpgMainDo);
			}
		}

		return fpgMainDos;

	}

	private BigDecimal getBigDecimal(final String value) {
		if (value == null || !StringUtils.isNumeric(value)) {
			return null;
		} else {
			return new BigDecimal(value);
		}
	}
	
	@Override
	public void insertEcXml(final FTZLFPGcXmlMessageVo vo) {
		final DoXdaoSession xdaoSession = (DoXdaoSession) XdaoSessionManager.getXdaoSession();
		try {
			xdaoSession.beginTransaction();
			this.insertXml(xdaoSession, vo);
			this.insertEc(xdaoSession, vo);
			xdaoSession.commit();
		} catch (final XdaoException e) {
			throw new ApBusinessException("新增失敗!", e);
		}

	}

	/**
	 * 新增XML檔案
	 * 
	 * @param xdaoSession
	 * @param vo
	 */
	private void insertXml(final DoXdaoSession xdaoSession, final FTZLFPGcXmlMessageVo vo) {
		final List<CpgMainDo> maisDos = this.toCpgMainDo(vo);
		final List<CpgDetailDo> detailDos = this.toCpgDetialDos(vo);
		final ICPGMainDetailModel fpgMainDetailModel = new CPGMainDetailModel();
		fpgMainDetailModel.insertCpgDetialDos(xdaoSession, detailDos);
		fpgMainDetailModel.insertCpgMainDos(xdaoSession, maisDos);

	}

	/**
	 * 新增EC檔案
	 * 
	 * @param xdaoSession
	 * @param vo
	 */
	private void insertEc(final DoXdaoSession xdaoSession, final FTZLFPGcXmlMessageVo vo) {
		final FTZLFPGProcECVo ecVo = this.toEc(vo);
		xdaoSession.insertPo(ecVo.getEventCTranMainDo());
		xdaoSession.insertPo(ecVo.getEventCTranBDo());
		xdaoSession.insertPo(ecVo.getEventCTranCDo());
		xdaoSession.insertPo(ecVo.getEventCTranADo());
	}

	/**
	 * XML轉成EC檔案
	 * 
	 * @param vo
	 * @return
	 */
	private FTZLFPGProcECVo toEc(final FTZLFPGcXmlMessageVo vo) {
		final FTZLFPGProcECVo ecVo = new FTZLFPGProcECVo();
		for (final FTZLFPGcXmlBodyVo body : vo.getBody()) {
			final EventCTranMainDo eventCTranMainDo = new EventCTranMainDo();
			eventCTranMainDo.setFilename(vo.getFileNmae());
			eventCTranMainDo.setSendflag(SendFlag.P.name());
			eventCTranMainDo.setSendTime(StringUtils.EMPTY);// 尚未傳送
			ecVo.setEventCTranMainDo(eventCTranMainDo);

			for (final FTZLFPGcXmlPostMainInfoVo info : body.getPostMainInfoVos()) {
				final EventCTranADo eventCTranADo = new EventCTranADo();
				eventCTranADo.setPostspecialaccount(info.getPostSpecialAccount());
				eventCTranADo.setPosttype(info.getPostType());
				eventCTranADo.setType("A");
				eventCTranADo.setYear(info.getYear());
				eventCTranADo.setCountrycode(info.getCountryCode());
				eventCTranADo.setExchangeagency(info.getExchangeAgency());
				eventCTranADo.setTotalpackageno(BigDecimalUtils.formObj(info.getTotalPackageNo()));
				eventCTranADo.setPostmblno(info.getPostMBLNo());
				eventCTranADo.setLastyeartotalpackageno(info.getLastYearTotalPackageNo());
				eventCTranADo.setSealdatetime(info.getSealDatetime());
				eventCTranADo.setTotalqty(BigDecimalUtils.formObj(info.getTotalQty()));
				eventCTranADo.setTotalpagecount(BigDecimalUtils.formObj(info.getTotalPackageNo()));
				eventCTranADo.setTotalbagnumber(BigDecimalUtils.formObj(info.getTotalBagNumber()));
				eventCTranADo.setTotalweight(BigDecimalUtils.formObj(info.getTotalWeight()));
				eventCTranADo.setFilename(vo.getFileNmae());
				ecVo.getEventCTranADo().add(eventCTranADo);

				for (final FTZLFPGcXmlBagVo bagVo : info.getBags()) {
					final EventCTranBDo eventCTranBDo = new EventCTranBDo();
					eventCTranBDo.setPostspecialaccount(info.getPostSpecialAccount());
					eventCTranBDo.setPosttype(info.getPostType());
					eventCTranBDo.setType("B");
					eventCTranBDo.setYear(info.getYear());
					eventCTranBDo.setCountrycode(info.getCountryCode());
					eventCTranBDo.setExchangeagency(info.getExchangeAgency());
					eventCTranBDo.setTotalpackageno(BigDecimalUtils.formObj(info.getTotalPackageNo()));
					eventCTranBDo.setBagno(BigDecimalUtils.formObj(bagVo.getBagNo()));
					eventCTranBDo.setLastmark(bagVo.getLastMark());
					eventCTranBDo.setPageno(BigDecimalUtils.formObj(bagVo.getPageNo()));
					eventCTranBDo.setQty(BigDecimalUtils.formObj(bagVo.getQty()));
					eventCTranBDo.setWeight(BigDecimalUtils.formObj(bagVo.getWeight()));
					eventCTranBDo.setSealdatetime(info.getSealDatetime());
					eventCTranBDo.setFilename(vo.getFileNmae());
					ecVo.getEventCTranBDo().add(eventCTranBDo);

					for (final FTZLFPGcXmlItemVo item : bagVo.getItems()) {
						final EventCTranCDo eventCTranCDo = new EventCTranCDo();
						eventCTranCDo.setPostspecialaccount(info.getPostSpecialAccount());
						eventCTranCDo.setPosttype(info.getPostType());
						eventCTranCDo.setType("C");
						eventCTranCDo.setYear(info.getYear());
						eventCTranCDo.setCountrycode(info.getCountryCode());
						eventCTranCDo.setExchangeagency(info.getExchangeAgency());
						eventCTranCDo.setTotalpackageno(BigDecimalUtils.formObj(info.getTotalPackageNo()));
						eventCTranCDo.setBagno(BigDecimalUtils.formObj(bagVo.getBagNo()));
						eventCTranCDo.setPageno(BigDecimalUtils.formObj(item.getPageNo()));
						eventCTranCDo.setCellno(BigDecimalUtils.formObj(item.getCellNo()));
						eventCTranCDo.setPostno(item.getPostNo());
						eventCTranCDo.setWeight(BigDecimalUtils.formObj(item.getWeight()));
						eventCTranCDo.setSealdatetime(info.getSealDatetime());
						eventCTranCDo.setProvincetype(item.getProvinceType());
						eventCTranCDo.setBoxtype(item.getBoxType());
						eventCTranCDo.setFilename(vo.getFileNmae());
						ecVo.getEventCTranCDo().add(eventCTranCDo);
					}

				}

			}
		}
		return ecVo;
	}

}

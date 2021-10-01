package batchJob.common.reader;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import batchJob.been.vo.fpg.FPGMessageHeaderVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlBagVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlBodyVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlItemVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlMessageVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlPostMainInfoVo;
import batchJob.common.xml.Book;
import batchJob.common.xml.ContextFactory;
import batchJob.common.xml.XmlBookData;
import batchJob.common.xml.impl.AbstractReaderComponent;

public class CPGXmlReader extends AbstractReaderComponent {
	
	public CPGXmlReader(final ContextFactory contextFactory) {
		super(contextFactory);
	}
	
	@Override
	protected FTZLFPGcXmlMessageVo read(final Book context) {
		final XmlBookData xmlBookData = (XmlBookData) context.read();
		final Document doc = xmlBookData.getDocument();

		@SuppressWarnings("unchecked")
		final List<Node> messageHeader = doc.selectNodes("/Message/MessageHeader");

		final FTZLFPGcXmlMessageVo messageVo = new FTZLFPGcXmlMessageVo();
		for (final Node node : messageHeader) {
			final FPGMessageHeaderVo cfpMessageHeaderVo = this.readMessageHeader(node);
			messageVo.getHeaders().add(cfpMessageHeaderVo);
		}

		@SuppressWarnings("unchecked")
		final List<Node> messageBody = doc.selectNodes("/Message/MessageBody");
		final FTZLFPGcXmlBodyVo vo = new FTZLFPGcXmlBodyVo();
		for (final Node node : messageBody) {
			final String msgFuncCode = this.getText(node, "MsgFuncCode");
			final String messageType = this.getText(node, "MessageType");
			vo.setMsgFuncCode(msgFuncCode);
			vo.setMessaggeType(messageType);
			this.readMessageBody(vo, node);
		}
		messageVo.getBody().add(vo);
		return messageVo;
	}

	private FPGMessageHeaderVo readMessageHeader(final Node item) {
		final FPGMessageHeaderVo vo = new FPGMessageHeaderVo();
		final String messageID = this.getText(item, "MessageID");
		final String messageName = this.getText(item, "MessageName");
		final String messageVersion = this.getText(item, "MessageVersion");
		final String senderID = this.getText(item, "SenderID");
		final String recipientType = this.getText(item, "RecipientType");
		final String recipientID = this.getText(item, "RecipientID");

		vo.setMessageID(messageID);
		vo.setMessageName(messageName);
		vo.setMessageVersion(messageVersion);
		vo.setRecipientID(recipientID);
		vo.setRecipientType(recipientType);
		vo.setSenderID(senderID);
		return vo;
	}

	private void readMessageBody(final FTZLFPGcXmlBodyVo vo, final Node body) {

		@SuppressWarnings("unchecked")
		final List<Node> items = body.selectNodes("PostMainInfo");

		for (final Node item : items) {
			final String postType = this.getText(item, "PostType");
			final String postSpecialAccount = this.getText(item, "PostSpecialAccount");
			final String year = this.getText(item, "Year");
			final String countryCode = this.getText(item, "CountryCode");
			final String exchangeAgency = this.getText(item, "ExchangeAgency");
			final String totalPackageNo = this.getText(item, "TotalPackageNo");
			final String sealDatetime = this.getText(item, "SealDatetime");
			final String postMBLNo = this.getText(item, "PostMBLNo");
			final String lastYearTotalPackageNo = this.getText(item, "LastYearTotalPackageNo");
			final String totalQty = this.getText(item, "TotalQty");
			final String totalPageCount = this.getText(item, "TotalPageCount");
			final String totalBagNumber = this.getText(item, "TotalBagNumber");
			final String totalWeight = this.getText(item, "TotalWeight");
			final String otherFields = this.getText(item, "OtherFields");

			final FTZLFPGcXmlPostMainInfoVo postMian = new FTZLFPGcXmlPostMainInfoVo();

			postMian.setPostSpecialAccount(postSpecialAccount);
			postMian.setPostType(postType);
			postMian.setYear(year);
			postMian.setCountryCode(countryCode);
			postMian.setExchangeAgency(exchangeAgency);
			postMian.setTotalPackageNo(totalPackageNo);
			postMian.setSealDatetime(sealDatetime);
			postMian.setPostMBLNo(postMBLNo);
			postMian.setLastYearTotalPackageNo(lastYearTotalPackageNo);
			postMian.setTotalQty(totalQty);
			postMian.setTotalPageCount(totalPageCount);
			postMian.setTotalBagNumber(totalBagNumber);
			postMian.setTotalWeight(totalWeight);
			postMian.setOtherFields(otherFields);
			vo.getPostMainInfoVos().add(postMian);

			@SuppressWarnings("unchecked")
			final List<Node> bags = item.selectNodes("Bags");
			for (final Node bag : bags) {
				final FTZLFPGcXmlBagVo bagVo = this.readBag(bag);
				postMian.getBags().add(bagVo);
			}
		}

	}

	private FTZLFPGcXmlBagVo readBag(final Node node) {
		final String fieldName = this.getText(node, "FieldName");
		final String fieldValue = this.getText(node, "FieldValue");
		final String bagNo = this.getText(node, "BagNo");
		final String lastMark = this.getText(node, "LastMark");
		final String pageNo = this.getText(node, "PageNo");
		final String qty = this.getText(node, "Qty");
		final String weight = this.getText(node, "Weight");
		final String otherFields = this.getText(node, "OtherFields");
		final FTZLFPGcXmlBagVo bag = new FTZLFPGcXmlBagVo();
		bag.setBagNo(bagNo);
		bag.setLastMark(lastMark);
		bag.setPageNo(pageNo);
		bag.setQty(qty);
		bag.setWeight(weight);
		bag.setOtherFields(otherFields);
		bag.setFieldName(fieldName);
		bag.setFieldValue(fieldValue);
		bag.setOtherFields(otherFields);

		@SuppressWarnings("unchecked")
		final List<Node> items = node.selectNodes("Items");
		for (final Node item : items) {
			final FTZLFPGcXmlItemVo itemVo = this.readItem(item);
			bag.getItems().add(itemVo);
		}

		return bag;

	}

	private FTZLFPGcXmlItemVo readItem(final Node item) {
		final String fieldName = this.getText(item, "FieldName");
		final String fieldValue = this.getText(item, "FieldValue");
		final String pageNo = this.getText(item, "PageNo");
		final String cellNo = this.getText(item, "CellNo");
		final String postNo = this.getText(item, "PostNo");
		final String weight = this.getText(item, "Weight");
		final String provinceType = this.getText(item, "ProvinceType");
		final String boxType = this.getText(item, "BoxType");
		final String deliverDateTime = this.getText(item, "DeliverDateTime");
		final String senderName = this.getText(item, "SenderName");
		final String senderAddress = this.getText(item, "SenderAddress");
		final String receiverName = this.getText(item, "ReceiverName");
		final String receiverAddress = this.getText(item, "ReceiverAddress");
		final String goodsDescription = this.getText(item, "GoodsDescription");
		final String qty = this.getText(item, "Qty");
		final String qtyUnit = this.getText(item, "QtyUnit");
		final String declareValueAmt = this.getText(item, "DeclareValueAmt");
		final String originCountry = this.getText(item, "OriginCountry");
		final String otherFields = this.getText(item, "OtherFields");

		final FTZLFPGcXmlItemVo itemVo = new FTZLFPGcXmlItemVo();
		itemVo.setFieldName(fieldName);
		itemVo.setFieldValue(fieldValue);
		itemVo.setPageNo(pageNo);
		itemVo.setCellNo(cellNo);
		itemVo.setPostNo(postNo);
		itemVo.setWeight(weight);
		itemVo.setProvinceType(provinceType);
		itemVo.setBoxType(boxType);
		itemVo.setDeliverDateTime(deliverDateTime);
		itemVo.setSenderName(senderName);
		itemVo.setSenderAddress(senderAddress);
		itemVo.setReceiverName(receiverName);
		itemVo.setReceiverAddress(receiverAddress);
		itemVo.setGoodsDescription(goodsDescription);
		itemVo.setQty(qty);
		itemVo.setQtyUnit(qtyUnit);
		itemVo.setDeclareValueAmt(declareValueAmt);
		itemVo.setOriginCountry(originCountry);
		itemVo.setOtherFields(otherFields);
		return itemVo;

	}

	private String getText(final Node item, final String note) {
		final Node node = item.selectSingleNode(note);
		if (node == null) {
			return null;
		}
		return node.getText();
	}

}

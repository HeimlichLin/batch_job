package batchJob.been;

import java.math.BigDecimal;

//自轉郵貨物清單明細
public class CpgDetailDo {

	public enum COLUMNS {
		BAGSFIELDNAME("欄位名稱", false), //
		BAGSFIELDVALUE("欄位值", false), //
		BAGNO("袋序", false), //
		BAGSLASTMARK("最後一袋(F)", false), //
		BAGSPAGENO("該袋頁數", false), //
		BAGSQTY("件數", false), //
		BAGSWEIGHT("重量", false), //
		BAGSOTHERFIELDS("其他欄位", false), //
		ITEMSFIELDNAME("欄位名稱", false), //
		ITEMSFIELDVALUE("欄位值", false), //
		ITEMSPAGENO("頁序", false), //
		ITEMSCELLNO("格次", false), //
		ITEMSPOSTNO("郵件號碼", false), //
		ITEMSQTY("數量", false), //
		ITEMSQTYUNIT("單位", false), //
		ITEMSWEIGHT("重量(公斤)", false), //
		DECLAREVALUEAMT("申報價值(新臺幣)", false), //
		ORIGINCOUNTRY("原產地", false), //
		PROVINCETYPE("省別", false), //
		BOXTYPE("箱別", false), //
		DELIVERDATETIME("交寄日期", false), //
		SENDERNAME("原始寄件人名稱", false), //
		SENDERADDRESS("原始寄件人地址", false), //
		RECEIVERNAME("收件人名稱", false), //
		RECEIVERADDRESS("收件人地址", false), //
		GOODSDESCRIPTION("內裝物品", false), //
		OTHERFIELDS("其他欄位", false), //
		FILENAME("檔名", false), //
		;//
		final String chineseName;
		final boolean isPK;

		private COLUMNS(final String chineseName, final boolean isPK) {
			this.chineseName = chineseName;
			this.isPK = isPK;
		}

		public String getChineseName() {
			return this.chineseName;
		}

		public boolean isPK() {
			return this.isPK;
		}
	}

	private String bagsfieldname;// 欄位名稱
	private String bagsfieldvalue;// 欄位值
	private String bagno;// 袋序
	private String bagslastmark;// 最後一袋(f)
	private String bagspageno;// 該袋頁數
	private BigDecimal bagsqty;// 件數
	private BigDecimal bagsweight;// 重量
	private String bagsotherfields;// 其他欄位
	private String itemsfieldname;// 欄位名稱
	private String itemsfieldvalue;// 欄位值
	private String itemspageno;// 頁序
	private String itemscellno;// 格次
	private String itemspostno;// 郵件號碼
	private String itemsqty;// 數量
	private String itemsqtyunit;// 單位
	private BigDecimal itemsweight;// 重量(公斤)
	private String declarevalueamt;// 申報價值(新臺幣)
	private String origincountry;// 原產地
	private String provincetype;// 省別
	private String boxtype;// 箱別
	private String deliverdatetime;// 交寄日期
	private String sendername;// 原始寄件人名稱
	private String senderaddress;// 原始寄件人地址
	private String receivername;// 收件人名稱
	private String receiveraddress;// 收件人地址
	private String goodsdescription;// 內裝物品
	private String otherfields;// 其他欄位
	private String filename;// 檔名

	public String getBagsfieldname() {
		return this.bagsfieldname;
	}

	public void setBagsfieldname(final String value) {
		this.bagsfieldname = value;
	}

	public String getBagsfieldvalue() {
		return this.bagsfieldvalue;
	}

	public void setBagsfieldvalue(final String value) {
		this.bagsfieldvalue = value;
	}

	public String getBagno() {
		return this.bagno;
	}

	public void setBagno(final String value) {
		this.bagno = value;
	}

	public String getBagslastmark() {
		return this.bagslastmark;
	}

	public void setBagslastmark(final String value) {
		this.bagslastmark = value;
	}

	public String getBagspageno() {
		return this.bagspageno;
	}

	public void setBagspageno(final String value) {
		this.bagspageno = value;
	}

	public BigDecimal getBagsqty() {
		return this.bagsqty;
	}

	public void setBagsqty(final BigDecimal value) {
		this.bagsqty = value;
	}

	public BigDecimal getBagsweight() {
		return this.bagsweight;
	}

	public void setBagsweight(final BigDecimal value) {
		this.bagsweight = value;
	}

	public String getBagsotherfields() {
		return this.bagsotherfields;
	}

	public void setBagsotherfields(final String value) {
		this.bagsotherfields = value;
	}

	public String getItemsfieldname() {
		return this.itemsfieldname;
	}

	public void setItemsfieldname(final String value) {
		this.itemsfieldname = value;
	}

	public String getItemsfieldvalue() {
		return this.itemsfieldvalue;
	}

	public void setItemsfieldvalue(final String value) {
		this.itemsfieldvalue = value;
	}

	public String getItemspageno() {
		return this.itemspageno;
	}

	public void setItemspageno(final String value) {
		this.itemspageno = value;
	}

	public String getItemscellno() {
		return this.itemscellno;
	}

	public void setItemscellno(final String value) {
		this.itemscellno = value;
	}

	public String getItemspostno() {
		return this.itemspostno;
	}

	public void setItemspostno(final String value) {
		this.itemspostno = value;
	}

	public String getItemsqty() {
		return this.itemsqty;
	}

	public void setItemsqty(final String value) {
		this.itemsqty = value;
	}

	public String getItemsqtyunit() {
		return this.itemsqtyunit;
	}

	public void setItemsqtyunit(final String value) {
		this.itemsqtyunit = value;
	}

	public BigDecimal getItemsweight() {
		return this.itemsweight;
	}

	public void setItemsweight(final BigDecimal value) {
		this.itemsweight = value;
	}

	public String getDeclarevalueamt() {
		return this.declarevalueamt;
	}

	public void setDeclarevalueamt(final String value) {
		this.declarevalueamt = value;
	}

	public String getOrigincountry() {
		return this.origincountry;
	}

	public void setOrigincountry(final String value) {
		this.origincountry = value;
	}

	public String getProvincetype() {
		return this.provincetype;
	}

	public void setProvincetype(final String value) {
		this.provincetype = value;
	}

	public String getBoxtype() {
		return this.boxtype;
	}

	public void setBoxtype(final String value) {
		this.boxtype = value;
	}

	public String getDeliverdatetime() {
		return this.deliverdatetime;
	}

	public void setDeliverdatetime(final String value) {
		this.deliverdatetime = value;
	}

	public String getSendername() {
		return this.sendername;
	}

	public void setSendername(final String value) {
		this.sendername = value;
	}

	public String getSenderaddress() {
		return this.senderaddress;
	}

	public void setSenderaddress(final String value) {
		this.senderaddress = value;
	}

	public String getReceivername() {
		return this.receivername;
	}

	public void setReceivername(final String value) {
		this.receivername = value;
	}

	public String getReceiveraddress() {
		return this.receiveraddress;
	}

	public void setReceiveraddress(final String value) {
		this.receiveraddress = value;
	}

	public String getGoodsdescription() {
		return this.goodsdescription;
	}

	public void setGoodsdescription(final String value) {
		this.goodsdescription = value;
	}

	public String getOtherfields() {
		return this.otherfields;
	}

	public void setOtherfields(final String value) {
		this.otherfields = value;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String value) {
		this.filename = value;
	}
}

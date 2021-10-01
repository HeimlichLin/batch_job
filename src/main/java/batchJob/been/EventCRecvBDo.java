package batchJob.been;

import java.math.BigDecimal;

//自轉郵交寄Event_C_接收_B
public class EventCRecvBDo {

	public enum COLUMNS {
		POSTSPECIALACCOUNT("特約戶號", false), //
		POSTTYPE("郵件類別", false), //
		TYPE("格        式", false), //
		YEAR("年        度", false), //
		COUNTRYCODE("國名代碼", false), //
		EXCHANGEAGENCY("互換局名", false), //
		TOTALPACKAGENO("總包號碼", false), //
		GOODTOTALPACKAGEYEAR("貨總包年度", false), //
		GOODTOTALPACKAGENO("貨總包號碼", false), //
		BAGNO("袋        序", false), //
		LASTMARK("最後一袋", false), //
		PAGENO("該袋頁數", false), //
		QTY("件        數", false), //
		WEIGHT("重        量", false), //
		SEALDATETIME("封發時間", false), //
		UPU("UPU 29碼", false), //
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

	private String postspecialaccount;// 特約戶號
	private String posttype;// 郵件類別
	private String type;// 格 式
	private String year;// 年 度
	private String countrycode;// 國名代碼
	private String exchangeagency;// 互換局名
	private BigDecimal totalpackageno;// 總包號碼
	private String goodtotalpackageyear;// 貨總包年度
	private String goodtotalpackageno;// 貨總包號碼
	private BigDecimal bagno;// 袋 序
	private String lastmark;// 最後一袋
	private BigDecimal pageno;// 該袋頁數
	private BigDecimal qty;// 件 數
	private BigDecimal weight;// 重 量
	private String sealdatetime;// 封發時間
	private String upu;// upu 29碼
	private String filename;// 檔名

	public String getPostspecialaccount() {
		return this.postspecialaccount;
	}

	public void setPostspecialaccount(final String value) {
		this.postspecialaccount = value;
	}

	public String getPosttype() {
		return this.posttype;
	}

	public void setPosttype(final String value) {
		this.posttype = value;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String value) {
		this.type = value;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(final String value) {
		this.year = value;
	}

	public String getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(final String value) {
		this.countrycode = value;
	}

	public String getExchangeagency() {
		return this.exchangeagency;
	}

	public void setExchangeagency(final String value) {
		this.exchangeagency = value;
	}

	public BigDecimal getTotalpackageno() {
		return this.totalpackageno;
	}

	public void setTotalpackageno(final BigDecimal value) {
		this.totalpackageno = value;
	}

	public String getGoodtotalpackageyear() {
		return this.goodtotalpackageyear;
	}

	public void setGoodtotalpackageyear(final String value) {
		this.goodtotalpackageyear = value;
	}

	public String getGoodtotalpackageno() {
		return this.goodtotalpackageno;
	}

	public void setGoodtotalpackageno(final String value) {
		this.goodtotalpackageno = value;
	}

	public BigDecimal getBagno() {
		return this.bagno;
	}

	public void setBagno(final BigDecimal value) {
		this.bagno = value;
	}

	public String getLastmark() {
		return this.lastmark;
	}

	public void setLastmark(final String value) {
		this.lastmark = value;
	}

	public BigDecimal getPageno() {
		return this.pageno;
	}

	public void setPageno(final BigDecimal value) {
		this.pageno = value;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(final BigDecimal value) {
		this.qty = value;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(final BigDecimal value) {
		this.weight = value;
	}

	public String getSealdatetime() {
		return this.sealdatetime;
	}

	public void setSealdatetime(final String value) {
		this.sealdatetime = value;
	}

	public String getUpu() {
		return this.upu;
	}

	public void setUpu(final String value) {
		this.upu = value;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String value) {
		this.filename = value;
	}
}

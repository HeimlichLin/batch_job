package batchJob.been;

import java.math.BigDecimal;

public class CpgTranPostDo {

	public enum COLUMNS {
		FILENAME("檔名", false), //
		POSTMBLNO("貨提單號碼", false), //
		ORIRCVFILE("原接收檔名", false), //
		FLIGHTNO("起飛班機", false), //
		FLIGHTDATETIME("起飛時間", false), //
		POSTTYPE("郵件類別", false), //
		POSTSPECIALACCOUNT("特約戶號", false), //
		TYPE("格        式", false), //
		YEAR("年        度", false), //
		COUNTRYCODE("國名代碼", false), //
		EXCHANGEAGENCY("互換局名", false), //
		TOTALPACKAGENO("總包號碼", false), //
		GOODTOTALPACKAGEYEAR("貨總包年度", false), //
		GOODTOTALPACKAGENO("貨總包號碼", false), //
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

	private String filename;// 檔名
	private String postmblno;// 貨提單號碼
	private String orircvfile;// 原接收檔名
	private String flightno;// 起飛班機
	private String flightdatetime;// 起飛時間
	private String posttype;// 郵件類別
	private String postspecialaccount;// 特約戶號
	private String type;// 格 式
	private String year;// 年 度
	private String countrycode;// 國名代碼
	private String exchangeagency;// 互換局名
	private BigDecimal totalpackageno;// 總包號碼
	private String goodtotalpackageyear;// 貨總包年度
	private String goodtotalpackageno;// 貨總包號碼

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String value) {
		this.filename = value;
	}

	public String getPostmblno() {
		return this.postmblno;
	}

	public void setPostmblno(final String value) {
		this.postmblno = value;
	}

	public String getOrircvfile() {
		return this.orircvfile;
	}

	public void setOrircvfile(final String value) {
		this.orircvfile = value;
	}

	public String getFlightno() {
		return this.flightno;
	}

	public void setFlightno(final String value) {
		this.flightno = value;
	}

	public String getFlightdatetime() {
		return this.flightdatetime;
	}

	public void setFlightdatetime(final String value) {
		this.flightdatetime = value;
	}

	public String getPosttype() {
		return this.posttype;
	}

	public void setPosttype(final String value) {
		this.posttype = value;
	}

	public String getPostspecialaccount() {
		return this.postspecialaccount;
	}

	public void setPostspecialaccount(final String value) {
		this.postspecialaccount = value;
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
}

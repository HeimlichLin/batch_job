package batchJob.been;

import java.math.BigDecimal;

//自轉郵交寄Event_C_接收_A
public class EventCRecvADo {

	public enum COLUMNS {
		POSTSPECIALACCOUNT("特約戶號", false), //
		POSTTYPE("郵件類別", false), //
		TYPE("格        式", false), //
		YEAR("年        度", false), //
		COUNTRYCODE("國名代碼", false), //
		EXCHANGEAGENCY("互換局名", false), //
		TOTALPACKAGENO("總包號碼", false), //
		POSTMBLNO("貨提單號碼", false), //
		SEALDATETIME("封發時間", false), //
		TOTALQTY("總  件  數", false), //
		TOTALPAGECOUNT("總  頁  數", false), //
		TOTALBAGNUMBER("總  袋  數", false), //
		TOTALWEIGHT("總  重  量", false), //
		FILENAME("檔名", true), //
		GOODTOTALPACKAGEYEAR("貨總包年度", false), //
		GOODTOTALPACKAGENO("貨總包號碼", false), //
		OLDFILENAME("原接收檔名", false), //
		TAKEOFFFLIGHT("起飛班機", false), //
		SENDTIME("起飛時間", false), //
		ARRIVALTIME("預定到達時間", false), //
		CREATETIME("收檔時間(YYYYMMDDhhmmss)", false), //
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
	private String postmblno;// 貨提單號碼
	private String sealdatetime;// 封發時間
	private BigDecimal totalqty;// 總 件 數
	private BigDecimal totalpagecount;// 總 頁 數
	private BigDecimal totalbagnumber;// 總 袋 數
	private BigDecimal totalweight;// 總 重 量
	private String filename;// 檔名
	private String goodtotalpackageyear;// 貨總包年度
	private String goodtotalpackageno;// 貨總包號碼
	private String oldfilename;// 原接收檔名
	private String takeoffflight;// 起飛班機
	private String sendtime;// 起飛時間
	private String arrivaltime;// 預定到達時間
	private String createtime;// 收檔時間(yyyymmddhhmmss)

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

	public String getPostmblno() {
		return this.postmblno;
	}

	public void setPostmblno(final String value) {
		this.postmblno = value;
	}

	public String getSealdatetime() {
		return this.sealdatetime;
	}

	public void setSealdatetime(final String value) {
		this.sealdatetime = value;
	}

	public BigDecimal getTotalqty() {
		return this.totalqty;
	}

	public void setTotalqty(final BigDecimal value) {
		this.totalqty = value;
	}

	public BigDecimal getTotalpagecount() {
		return this.totalpagecount;
	}

	public void setTotalpagecount(final BigDecimal value) {
		this.totalpagecount = value;
	}

	public BigDecimal getTotalbagnumber() {
		return this.totalbagnumber;
	}

	public void setTotalbagnumber(final BigDecimal value) {
		this.totalbagnumber = value;
	}

	public BigDecimal getTotalweight() {
		return this.totalweight;
	}

	public void setTotalweight(final BigDecimal value) {
		this.totalweight = value;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String value) {
		this.filename = value;
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

	public String getOldfilename() {
		return this.oldfilename;
	}

	public void setOldfilename(final String value) {
		this.oldfilename = value;
	}

	public String getTakeoffflight() {
		return this.takeoffflight;
	}

	public void setTakeoffflight(final String value) {
		this.takeoffflight = value;
	}

	public String getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(final String value) {
		this.sendtime = value;
	}

	public String getArrivaltime() {
		return this.arrivaltime;
	}

	public void setArrivaltime(final String value) {
		this.arrivaltime = value;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(final String value) {
		this.createtime = value;
	}
}

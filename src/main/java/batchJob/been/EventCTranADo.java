package batchJob.been;

import java.math.BigDecimal;

//自轉郵交寄Event_C_傳送_A
public class EventCTranADo {

	public enum COLUMNS {
		POSTSPECIALACCOUNT("資料來源", false), //
		POSTTYPE("郵件類別", false), //
		TYPE("格        式", false), //
		YEAR("年        度", false), //
		COUNTRYCODE("國名代碼", false), //
		EXCHANGEAGENCY("互換局名", false), //
		TOTALPACKAGENO("總包號碼", false), //
		POSTMBLNO("提單號碼", false), //
		LASTYEARTOTALPACKAGENO("去年最後總包", false), //
		SEALDATETIME("封發時間", false), //
		TOTALQTY("總  件  數", false), //
		TOTALPAGECOUNT("總  頁  數", false), //
		TOTALBAGNUMBER("總  袋  數", false), //
		TOTALWEIGHT("總  重  量", false), //
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

	private String postspecialaccount;// 資料來源
	private String posttype;// 郵件類別
	private String type;// 格 式
	private String year;// 年 度
	private String countrycode;// 國名代碼
	private String exchangeagency;// 互換局名
	private BigDecimal totalpackageno;// 總包號碼
	private String postmblno;// 提單號碼
	private String lastyeartotalpackageno;// 去年最後總包
	private String sealdatetime;// 封發時間
	private BigDecimal totalqty;// 總 件 數
	private BigDecimal totalpagecount;// 總 頁 數
	private BigDecimal totalbagnumber;// 總 袋 數
	private BigDecimal totalweight;// 總 重 量
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

	public String getPostmblno() {
		return this.postmblno;
	}

	public void setPostmblno(final String value) {
		this.postmblno = value;
	}

	public String getLastyeartotalpackageno() {
		return this.lastyeartotalpackageno;
	}

	public void setLastyeartotalpackageno(final String value) {
		this.lastyeartotalpackageno = value;
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
}

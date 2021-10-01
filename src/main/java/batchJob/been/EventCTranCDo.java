package batchJob.been;

import java.math.BigDecimal;

//自轉郵交寄Event_C_傳送_C
public class EventCTranCDo {

	public enum COLUMNS {
		POSTSPECIALACCOUNT("資料來源", false), //
		POSTTYPE("郵件類別", false), //
		TYPE("格        式", false), //
		YEAR("年        度", false), //
		COUNTRYCODE("國名代碼", false), //
		EXCHANGEAGENCY("互換局名", false), //
		TOTALPACKAGENO("總包號碼", false), //
		BAGNO("袋        序", false), //
		PAGENO("頁        序", false), //
		CELLNO("格        次", false), //
		POSTNO("郵件號碼", false), //
		WEIGHT("重        量", false), //
		SEALDATETIME("封發時間", false), //
		PROVINCETYPE("省        別", false), //
		BOXTYPE("箱        別", false), //
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
	private BigDecimal bagno;// 袋 序
	private BigDecimal pageno;// 頁 序
	private BigDecimal cellno;// 格 次
	private String postno;// 郵件號碼
	private BigDecimal weight;// 重 量
	private String sealdatetime;// 封發時間
	private String provincetype;// 省 別
	private String boxtype;// 箱 別
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

	public BigDecimal getBagno() {
		return this.bagno;
	}

	public void setBagno(final BigDecimal value) {
		this.bagno = value;
	}

	public BigDecimal getPageno() {
		return this.pageno;
	}

	public void setPageno(final BigDecimal value) {
		this.pageno = value;
	}

	public BigDecimal getCellno() {
		return this.cellno;
	}

	public void setCellno(final BigDecimal value) {
		this.cellno = value;
	}

	public String getPostno() {
		return this.postno;
	}

	public void setPostno(final String value) {
		this.postno = value;
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

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String value) {
		this.filename = value;
	}
}


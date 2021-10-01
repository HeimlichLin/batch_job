package batchJob.been;

import java.math.BigDecimal;

//自轉郵貨物清單主檔
public class CpgMainDo {

	public enum COLUMNS {
		MESSAGEID("訊息代碼(CPG)", false), //
		MESSAGENAME("訊息名稱(自轉郵貨物清單)", false), //
		MESSAGEVERSION("訊息版本", false), //
		SENDERID("傳入者ID", false), //
		RECIPIENTTYPE("接收者代碼類型(TVP)", false), //
		RECIPIENTID("接收者ID", false), //
		MSGFUNCCODE("異動別(1：刪除5：修改9：新增)", false), //
		MESSAGETYPE("訊息別(F：自轉郵；C：貨轉郵)", false), //
		POSTSPECIALACCOUNT("特約戶號", false), //
		POSTTYPE("郵件類別(1：EMS2：掛號R3：平件M4：速遞空運5：ｅ小包6：速遞海運)", false), //
		YEAR("年度", false), //
		COUNTRYCODE("國名代碼", false), //
		EXCHANGEAGENCY("互換局名(FOCE)", false), //
		TOTALPACKAGENO("總包號碼", false), //
		SEALDATETIME("封發時間(YYYYMMDDhhmm)", false), //
		POSTMBLNO("提單號碼", false), //
		LASTYEARTOTALPACKAGENO("去年最後總包", false), //
		TOTALQTY("總件數", false), //
		TOTALPAGECOUNT("總頁數", false), //
		TOTALBAGNUMBER("總袋數", false), //
		TOTALWEIGHT("總重量", false), //
		OTHERFIELDS("其他欄位", false), //
		FILENAME("檔名", false), //
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

	private String messageid;// 訊息代碼(cpg)
	private String messagename;// 訊息名稱(自轉郵貨物清單)
	private String messageversion;// 訊息版本
	private String senderid;// 傳入者id
	private String recipienttype;// 接收者代碼類型(tvp)
	private String recipientid;// 接收者id
	private String msgfunccode;// 異動別(1：刪除5：修改9：新增)
	private String messagetype;// 訊息別(f：自轉郵；c：貨轉郵)
	private String postspecialaccount;// 特約戶號
	private String posttype;// 郵件類別(1：ems2：掛號r3：平件m4：速遞空運5：ｅ小包6：速遞海運)
	private String year;// 年度
	private String countrycode;// 國名代碼
	private String exchangeagency;// 互換局名(foce)
	private BigDecimal totalpackageno;// 總包號碼
	private String sealdatetime;// 封發時間(yyyymmddhhmm)
	private String postmblno;// 提單號碼
	private String lastyeartotalpackageno;// 去年最後總包
	private BigDecimal totalqty;// 總件數
	private BigDecimal totalpagecount;// 總頁數
	private BigDecimal totalbagnumber;// 總袋數
	private BigDecimal totalweight;// 總重量
	private String otherfields;// 其他欄位
	private String filename;// 檔名
	private String createtime;// 收檔時間(yyyymmddhhmmss)

	public String getMessageid() {
		return this.messageid;
	}

	public void setMessageid(final String value) {
		this.messageid = value;
	}

	public String getMessagename() {
		return this.messagename;
	}

	public void setMessagename(final String value) {
		this.messagename = value;
	}

	public String getMessageversion() {
		return this.messageversion;
	}

	public void setMessageversion(final String value) {
		this.messageversion = value;
	}

	public String getSenderid() {
		return this.senderid;
	}

	public void setSenderid(final String value) {
		this.senderid = value;
	}

	public String getRecipienttype() {
		return this.recipienttype;
	}

	public void setRecipienttype(final String value) {
		this.recipienttype = value;
	}

	public String getRecipientid() {
		return this.recipientid;
	}

	public void setRecipientid(final String value) {
		this.recipientid = value;
	}

	public String getMsgfunccode() {
		return this.msgfunccode;
	}

	public void setMsgfunccode(final String value) {
		this.msgfunccode = value;
	}

	public String getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(final String value) {
		this.messagetype = value;
	}

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

	public String getSealdatetime() {
		return this.sealdatetime;
	}

	public void setSealdatetime(final String value) {
		this.sealdatetime = value;
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

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(final String value) {
		this.createtime = value;
	}
}

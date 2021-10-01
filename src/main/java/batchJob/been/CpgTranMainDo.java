package batchJob.been;

public class CpgTranMainDo {

	public enum COLUMNS {
		FILENAME("檔名", true), //
		SENDFLAG("傳送註記", false), //
		SEND_TIME("傳送時間", false), //
		MSGFUNCCODE("異動別1：刪除5：修改9：新增", false), //
		POSTSPECIALACCOUNT("特約戶號", false), //
		MESSAGETYPE("訊息別(F：自轉郵；C：貨轉郵)", false), //
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
	private String sendflag;// 傳送註記
	private String sendTime;// 傳送時間
	private String msgfunccode;// 異動別1：刪除5：修改9：新增
	private String postspecialaccount;// 特約戶號
	private String messagetype;// 訊息別(f：自轉郵；c：貨轉郵)

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String value) {
		this.filename = value;
	}

	public String getSendflag() {
		return this.sendflag;
	}

	public void setSendflag(final String value) {
		this.sendflag = value;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(final String value) {
		this.sendTime = value;
	}

	public String getMsgfunccode() {
		return this.msgfunccode;
	}

	public void setMsgfunccode(final String value) {
		this.msgfunccode = value;
	}

	public String getPostspecialaccount() {
		return this.postspecialaccount;
	}

	public void setPostspecialaccount(final String value) {
		this.postspecialaccount = value;
	}

	public String getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(final String value) {
		this.messagetype = value;
	}
}

package batchJob.been;

public class EventCTranMainDo {

	public enum COLUMNS {
		FILENAME("檔名", true), //
		SENDFLAG("傳送註記", false), //
		SEND_TIME("傳送時間", false), //
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
}

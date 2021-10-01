package batchJob.been.vo.fpg;

public class FPGMessageHeaderVo {
	
	private String messageID; // 訊息代碼
	private String messageName; // 訊息名稱
	private String messageVersion; // 訊息版本
	private String senderID; // 傳入者ID
	private String recipientType; // 接收者代碼類型
	private String recipientID; // 接收者ID

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getMessageVersion() {
		return messageVersion;
	}

	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}

	public String getRecipientID() {
		return recipientID;
	}

	public void setRecipientID(String recipientID) {
		this.recipientID = recipientID;
	}

}

package batchJob.been.vo.fpg;

import java.util.ArrayList;
import java.util.List;

public class FTZLFPGcXmlBodyVo {

	private String msgFuncCode; // 異動別
	private String messaggeType = "";// 訊息別(F：自轉郵；C：貨轉郵)
	private List<FTZLFPGcXmlPostMainInfoVo> postMainInfoVos = new ArrayList<FTZLFPGcXmlPostMainInfoVo>();

	public List<FTZLFPGcXmlPostMainInfoVo> getPostMainInfoVos() {
		return postMainInfoVos;
	}

	public void setPostMainInfoVos(List<FTZLFPGcXmlPostMainInfoVo> postMainInfoVos) {
		this.postMainInfoVos = postMainInfoVos;
	}

	public String getMsgFuncCode() {
		return msgFuncCode;
	}

	public void setMsgFuncCode(String msgFuncCode) {
		this.msgFuncCode = msgFuncCode;
	}

	public String getMessaggeType() {
		return messaggeType;
	}

	public void setMessaggeType(String messaggeType) {
		this.messaggeType = messaggeType;
	}
	
}

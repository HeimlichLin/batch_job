package batchJob.been.vo.fpg;

import java.util.ArrayList;
import java.util.List;

import batchJob.common.xml.XmlOut;

/**
 * 讀取CPG檔案XML取得之XML訊息物件
 */
public class FTZLFPGcXmlMessageVo implements XmlOut {

	private List<FPGMessageHeaderVo> headers = new ArrayList<FPGMessageHeaderVo>();
	private List<FTZLFPGcXmlBodyVo> body = new ArrayList<FTZLFPGcXmlBodyVo>();
	private String fileNmae = "";

	public List<FPGMessageHeaderVo> getHeaders() {
		return this.headers;
	}

	public void setHeaders(List<FPGMessageHeaderVo> headers) {
		this.headers = headers;
	}

	public List<FTZLFPGcXmlBodyVo> getBody() {
		return this.body;
	}

	public void setBody(List<FTZLFPGcXmlBodyVo> body) {
		this.body = body;
	}

	public String getFileNmae() {
		return this.fileNmae;
	}

	public void setFileNmae(String fileNmae) {
		this.fileNmae = fileNmae;
	}

}

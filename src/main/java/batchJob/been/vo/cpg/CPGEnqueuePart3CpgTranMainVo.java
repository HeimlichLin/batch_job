package batchJob.been.vo.cpg;

import java.util.ArrayList;
import java.util.List;

import batchJob.been.CpgTranMainDo;

/**
 * 一個EC檔案裏面有包含 多組A類型的 A裡面又包含多組C
 * 
 *
 */
public class CPGEnqueuePart3CpgTranMainVo {
	
	private CpgTranMainDo cpgTranMainDo;
	private List<CPGEnqueuePart3CpgTranVo> tranVos = new ArrayList<CPGEnqueuePart3CpgTranVo>();

	public CpgTranMainDo getCpgTranMainDo() {
		return this.cpgTranMainDo;
	}

	public void setCpgTranMainDo(final CpgTranMainDo cpgTranMainDo) {
		this.cpgTranMainDo = cpgTranMainDo;
	}

	public List<CPGEnqueuePart3CpgTranVo> getTranVos() {
		return this.tranVos;
	}

	public void setTranVos(final List<CPGEnqueuePart3CpgTranVo> tranVos) {
		this.tranVos = tranVos;
	}

	public String getXMLFileName() {
		return "CPG." + this.cpgTranMainDo.getFilename().replace(".TXT", "").replace(".txt", "") + ".XML";
	}

}

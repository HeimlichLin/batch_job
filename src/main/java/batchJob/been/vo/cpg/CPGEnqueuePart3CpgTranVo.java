package batchJob.been.vo.cpg;

import java.util.ArrayList;
import java.util.List;

import batchJob.been.CpgTranDetailDo;
import batchJob.been.CpgTranPostDo;

public class CPGEnqueuePart3CpgTranVo {
	
	private CpgTranPostDo cpgTranPostDo = new CpgTranPostDo();
	private List<CpgTranDetailDo> cpgTranDetailDos = new ArrayList<CpgTranDetailDo>();

	public List<CpgTranDetailDo> getCpgTranDetailDos() {
		return this.cpgTranDetailDos;
	}

	public void setCpgTranDetailDos(final List<CpgTranDetailDo> cpgTranDetailDos) {
		this.cpgTranDetailDos = cpgTranDetailDos;
	}

	public CpgTranPostDo getCpgTranPostDo() {
		return this.cpgTranPostDo;
	}

	public void setCpgTranPostDo(final CpgTranPostDo cpgTranPostDo) {
		this.cpgTranPostDo = cpgTranPostDo;
	}

}

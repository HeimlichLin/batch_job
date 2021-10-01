package batchJob.been.vo.cpg;

import java.util.ArrayList;
import java.util.List;

import batchJob.been.CpgTranDetailDo;
import batchJob.been.CpgTranMainDo;
import batchJob.been.CpgTranPostDo;

public class CpgTranPostVo {
	
	private CpgTranMainDo cpgTranMainDo = new CpgTranMainDo();
	private List<CpgTranPostDo> postDo = new ArrayList<CpgTranPostDo>();
	private List<CpgTranDetailDo> cpgTranDetailDos = new ArrayList<CpgTranDetailDo>();

	public List<CpgTranDetailDo> getCpgTranDetailDos() {
		return this.cpgTranDetailDos;
	}

	public void setCpgTranDetailDos(final List<CpgTranDetailDo> cpgTranDetailDos) {
		this.cpgTranDetailDos = cpgTranDetailDos;
	}

	public CpgTranMainDo getCpgTranMainDo() {
		return this.cpgTranMainDo;
	}

	public void setCpgTranMainDo(final CpgTranMainDo cpgTranMainDo) {
		this.cpgTranMainDo = cpgTranMainDo;
	}

	public List<CpgTranPostDo> getPostDo() {
		return this.postDo;
	}

	public void setPostDo(final List<CpgTranPostDo> postDo) {
		this.postDo = postDo;
	}

}

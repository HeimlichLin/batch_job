package batchJob.been.vo.cpg;

public class CPGEnqueuePart3Vo {
	
	final String m_sysCode = "FCM"; //
	final String m_hubType = "FCMS02"; //
	final String m_enqDir_Enq = "/PNVANCOM/VASO/FCMS02";
	final String m_bkDir = "/PFTZC/TMP/SNDOK/ECPG/"; // /PCLMS/TMP/SNDOK/
	final String m_errDir = "/PFTZC/TMP/SNDERR/ECPG/"; // /PCLMS/TMP/SNDERR/
	final String m_oriDir = "/PFTZC/TMP/SNDFIL/ECPG";
	final String m_sendId = "TVCBSETWTPE00003-SES"; // TVCBSETWTPE00003-SES
	private String m_recvId = "";
	final String m_docName = "CPG";
	final int m_lgQueId = 812;
	private String filename = "";
	private CPGEnqueuePart3CpgTranMainVo tranMainVo = new CPGEnqueuePart3CpgTranMainVo();

	public String getM_recvId() {
		return this.m_recvId;
	}

	public void setM_recvId(final String m_recvId) {
		this.m_recvId = m_recvId;
	}

	public String getM_sysCode() {
		return this.m_sysCode;
	}

	public String getM_hubType() {
		return this.m_hubType;
	}

	public String getM_enqDir_Enq() {
		return this.m_enqDir_Enq;
	}

	public String getM_bkDir() {
		return this.m_bkDir;
	}

	public String getM_errDir() {
		return this.m_errDir;
	}

	public String getM_oriDir() {
		return this.m_oriDir;
	}

	public String getM_sendId() {
		return this.m_sendId;
	}

	public String getM_docName() {
		return this.m_docName;
	}

	public int getM_lgQueId() {
		return this.m_lgQueId;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String filename) {
		this.filename = filename;
	}

	public CPGEnqueuePart3CpgTranMainVo getTranMainVo() {
		return this.tranMainVo;
	}

	public void setTranMainVo(final CPGEnqueuePart3CpgTranMainVo tranMainVo) {
		this.tranMainVo = tranMainVo;
	}

}

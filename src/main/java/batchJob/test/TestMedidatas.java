package batchJob.test;

import com.tradevan.test.TestMedidata;

public enum TestMedidatas implements TestMedidata {

	// 收檔
	RECEVER_CPG_FULLTEST("自轉郵接收測試", "平信測試"), //
	RECEVER_PROCCPGFULLTEST("自轉郵處理測試", "平信測試"), //
	RECEVER_CPGSENDFTPTEST("自轉郵傳送FTP測試", "平信測試"), //

	// 回訊
	ReplyProcCPGFullTest("自轉郵回訊處理檔案", "測試")

	;//
	private TestMedidatas(String testCode, String memo) {
		this.memo = memo;
		this.testCode = testCode;
	}

	final String memo;
	final String testCode;

	public String getMemo() {
		return memo;
	}

	public String getTestCode() {
		return testCode;
	}

}

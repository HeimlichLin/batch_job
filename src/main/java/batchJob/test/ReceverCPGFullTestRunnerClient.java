package batchJob.test;

import com.tradevan.test.impl.BaseTestRunnerClient;

/**
 * 自轉郵測試[收檔完整測試]
 * 
 *
 */
public class ReceverCPGFullTestRunnerClient extends BaseTestRunnerClient {
	final String fileNameString = "CPG.PC00000000000020180905188F3.xml";

	public ReceverCPGFullTestRunnerClient() {
		fullTestRunner.add(new ReceverCPGFullTest(fileNameString));
		fullTestRunner.add(new ReceverProcCPGFullTest(fileNameString));
		fullTestRunner.add(new ReceverCPGSendFTPTest(fileNameString));
		;
		//
	}

	public static void main(String[] args) {
		BaseTestRunnerClient fullTestRunnerClient = new ReceverCPGFullTestRunnerClient();
		fullTestRunnerClient.test();
	}

}

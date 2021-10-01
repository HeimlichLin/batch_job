package batchJob.test;

import com.tradevan.test.impl.BaseTestRunnerClient;

/**
 * 回訊整合測試
 * 
 *
 */
public class ReplyCPGFullTestRunnerClient extends BaseTestRunnerClient {

	public static void main(String[] args) {
		BaseTestRunnerClient fullTestRunnerClient = new ReplyCPGFullTestRunnerClient();
		fullTestRunnerClient.test();
	}
	
}

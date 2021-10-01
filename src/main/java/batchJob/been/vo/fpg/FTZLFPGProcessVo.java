package batchJob.been.vo.fpg;

import java.io.File;

import batchJob.common.queue.MyQueueConfig;

public class FTZLFPGProcessVo {
	
	final String pendingDir;
	final String OkDir;
	final String errDir;

	public FTZLFPGProcessVo(MyQueueConfig config) {
		pendingDir = config.getPendingDir();
		OkDir = config.getOkDir();
		errDir = config.getErrDir();
	}

	public File getPendingDir() {
		return new File(pendingDir);
	}

	public File getFileOkDir() {
		return new File(OkDir);
	}

	public File getFileErrDir() {
		return new File(errDir);
	}

}

package batchJob.queue.enqueue;

import batchJob.been.vo.cpg.CPGEnqueuePart1Vo;
import batchJob.service.CFGEnqueuePart1Service;
import batchJob.service.CFGEnqueuePart1ServiceImpl;

public class CPGEnqueuePart1 {
	final CPGEnqueuePart1Vo vo = new CPGEnqueuePart1Vo();
	final CFGEnqueuePart1Service service = new CFGEnqueuePart1ServiceImpl();

	public static void main(final String[] args) {
		final CPGEnqueuePart1 cpgEnqueuePart1 = new CPGEnqueuePart1();
		cpgEnqueuePart1.execute();
	}

	public void execute() {
		this.service.execute(this.vo);
	}

}

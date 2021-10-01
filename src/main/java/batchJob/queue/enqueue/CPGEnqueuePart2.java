package batchJob.queue.enqueue;

import batchJob.been.vo.cpg.CPGEnqueuePart2Vo;
import batchJob.service.CFGEnqueuePart2Service;
import batchJob.service.CFGEnqueuePart2ServiceImpl;

public class CPGEnqueuePart2 {
	
	final CPGEnqueuePart2Vo vo = new CPGEnqueuePart2Vo();
	final CFGEnqueuePart2Service service = new CFGEnqueuePart2ServiceImpl();

	public static void main(final String[] args) {
		final CPGEnqueuePart2 cpgEnqueuePart2 = new CPGEnqueuePart2();
		cpgEnqueuePart2.execute();
	}
	
	public void execute() {
		this.service.execute(this.vo);
	}

}

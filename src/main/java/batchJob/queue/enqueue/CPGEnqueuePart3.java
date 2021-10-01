package batchJob.queue.enqueue;

import batchJob.been.vo.cpg.CPGEnqueuePart3Vo;
import batchJob.service.CFGEnqueuePart3Service;
import batchJob.service.CFGEnqueuePart3ServiceImpl;

public class CPGEnqueuePart3 {
	
	final CPGEnqueuePart3Vo vo = new CPGEnqueuePart3Vo();
	final CFGEnqueuePart3Service service = new CFGEnqueuePart3ServiceImpl();

	public static void main(final String[] args) {
		final CPGEnqueuePart3 cpgEnqueuePart3 = new CPGEnqueuePart3();
		cpgEnqueuePart3.execute();
	}
	
	public void execute() {
		this.service.execute(this.vo);
	}

}

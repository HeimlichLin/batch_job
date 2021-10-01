package batchJob.service;

import batchJob.been.vo.cpg.CPGEnqueuePart1Vo;

public interface CFGEnqueuePart1Service {
	
	/**
	 * 執行
	 *
	 * @param vo
	 */
	void execute(CPGEnqueuePart1Vo vo);

}

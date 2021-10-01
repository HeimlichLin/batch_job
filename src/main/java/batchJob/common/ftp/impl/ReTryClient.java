package batchJob.common.ftp.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.exception.ApBusinessException;

public class ReTryClient<RETURN> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReTryClient.class);
	private int retryMax = 3;
	private int useTime = 0;
	private int sleepTime = 30 * 1000;// 30秒後Retry
	
	public ReTryClient(int retryTime) {
		this.retryMax = retryTime;
	}
	
	public RETURN update(ReTryWarpListener<RETURN> reTryWarpListener) {
		try {
			return reTryWarpListener.myWokr();
		} catch (Exception e) {
			LOGGER.error("作業失敗", e);
			LOGGER.warn("[error try time:{}/{}] after waitting {} ms ... will try again ,", 1 + useTime, retryMax,
					sleepTime);
			if (useTime++ < retryMax) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				this.update(reTryWarpListener);
			} else {
				throw new ApBusinessException("error 　happend  times bigger than max stop action ", e);
			}

		}
		throw new ApBusinessException("retry got max_time ,stop retry");
	}
	
	interface ReTryWarpListener<RETURN> {
		RETURN myWokr();
	}

}

package batchJob.common.ftp.impl;

import batchJob.common.ftp.FtpClient.Filter;

public class NoFilter implements Filter {
	
	@Override
	public boolean accept(final String name) {
		return true;
	}

}

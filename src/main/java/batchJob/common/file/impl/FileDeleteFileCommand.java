package batchJob.common.file.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.exception.ApBusinessException;
import batchJob.common.file.Command;

public class FileDeleteFileCommand implements Command {
	
	private static Logger LOGGER = LoggerFactory.getLogger(FileDeleteFileCommand.class);
	private File file;

	public FileDeleteFileCommand(final File file) {
		super();
		this.file = file;
	}
	
	@Override
	public void execute() {
		if (!this.file.exists()) {
			LOGGER.error("檔案不存在，無法刪除檔案");
		} else {
			this.file.delete();
			LOGGER.debug("刪除完成{}", this.file.getPath());
		}

	}

	@Override
	public void unio() {
		throw new ApBusinessException("尚未實作檔案還原機制");
	}

}

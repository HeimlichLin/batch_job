package batchJob.common.file.impl;

import batchJob.common.file.FileCommand;

/**
 * 檔案處理元件
 *
 *
 */
public class FileCommandFacade {
	
	private static ThreadLocal<FileCommand> FILECOMMANDLOCAL = new ThreadLocal<FileCommand>();

	/**
	 * 及時commit
	 *
	 * @return
	 */
	public FileCommand getFileCommand() {
		FileCommand fileCommand = FILECOMMANDLOCAL.get();
		if (fileCommand == null) {
			fileCommand = new CommandFileCommand();
			FILECOMMANDLOCAL.set(fileCommand);
		}
		return fileCommand;
	}

	/**
	 * 延後commit
	 *
	 * @return
	 */
	public FileCommand getLazyFileCommand() {
		FileCommand fileCommand = FILECOMMANDLOCAL.get();
		if (fileCommand == null) {
			fileCommand = new CommandLazyFileCommand();
			FILECOMMANDLOCAL.set(fileCommand);
		}
		return fileCommand;
	}

}

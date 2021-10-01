package batchJob.common.file.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import batchJob.common.file.Command;
import batchJob.common.file.FileCommand;
import batchJob.common.ftp.FtpClient;
import batchJob.common.ftp.FtpClient.Filter;

public class CommandFileCommand implements FileCommand {
	
	private List<Command> commands = new ArrayList<Command>();

	@Override
	public void rollback() {
		Collections.reverse(this.commands);
		for (final Command command : this.commands) {
			command.unio();
		}
		this.cleanCommand();
	}
	
	@Override
	public void createFile(final File file, final List<String> contents) {
		this.addCommandAndExecute(new FileCreateFileCommand(file, contents));

	}

	private void addCommandAndExecute(final Command command) {
		this.commands.add(command);
		command.execute();
	}
	
	@Override
	public void delete(final File file) {
		this.addCommandAndExecute(new FileDeleteFileCommand(file));

	}

	private void cleanCommand() {
		this.commands.clear();
	}
	
	@Override
	public void commit() {
		this.cleanCommand();

	}
	
	@Override
	public void renameTo(final File src, final File tartge) {

		this.addCommandAndExecute(new FileMoveFileCommand(src, tartge));

	}

	@Override
	public void send2Ftp(final FtpClient ftpClient, final File localFile, final String path) {
		this.addCommandAndExecute(new FileFtpSendCommand(ftpClient, localFile, path));

	}

	@Override
	public void ftpmMoveFile2Local(final String remoteFileName, final String localPath, final FtpClient ftpClient,
			final String defalutPath) {
		this.addCommandAndExecute(new FtpMoveFile2LocalCommand(remoteFileName, localPath, ftpClient, defalutPath));

	}

	@Override
	public void ftpMoveFiles2LocalFilesCommand(final FtpClient ftpClient, final String localPath,
			final String defalutPath) {

		this.addCommandAndExecute(new FtpMoveFiles2LocalFilesCommand(ftpClient, localPath, defalutPath));

	}

	@Override
	public void ftpMoveFiles2LocalFilesCommand(final FtpClient ftpClient, final String localPath, final Filter filter,
			final String defalutPath) {
		this.addCommandAndExecute(new FtpMoveFiles2LocalFilesCommand(ftpClient, localPath, filter, defalutPath));
	}

}

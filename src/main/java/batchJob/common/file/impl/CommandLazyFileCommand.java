package batchJob.common.file.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.exception.ApBusinessException;
import batchJob.common.file.Command;
import batchJob.common.file.FileCommand;
import batchJob.common.ftp.FtpClient;
import batchJob.common.ftp.FtpClient.Filter;

public class CommandLazyFileCommand implements FileCommand {
	private static Logger LOGGER = LoggerFactory.getLogger("AP");
	private List<Command> commands = new ArrayList<Command>();
	final List<Command> sucessFullsCommands = new ArrayList<Command>();// 執行完commit清單
	private boolean isCommit;

	public CommandLazyFileCommand() {
		this.isCommit = false;
	}

	@Override
	public void rollback() {
		Collections.reverse(this.sucessFullsCommands);
		for (final Command command : this.sucessFullsCommands) {
			command.unio();
		}
	}

	@Override
	public void commit() {
		if (this.isCommit) {
			throw new ApBusinessException("已經執行commit!");
		}
		try {

			for (final Command command : this.commands) {
				command.execute();
				this.sucessFullsCommands.add(command);
			}
		} catch (final Exception e) {
			LOGGER.debug("執行錯誤，呼叫rollback!", e);
		} finally {
			this.isCommit = true;
			this.cleanCommand();
		}

	}

	@Override
	public void createFile(final File f, final List<String> content) {
		this.addCommand(new FileCreateFileCommand(f, content));
	}

	@Override
	public void delete(final File f) {
		this.addCommand(new FileDeleteFileCommand(f));
	}

	@Override
	public void renameTo(final File src, final File tartge) {
		this.addCommand(new FileMoveFileCommand(src, tartge));

	}

	private void cleanCommand() {
		this.commands.clear();
	}
	
	@Override
	public void send2Ftp(final FtpClient ftpClient, final File localFile, final String defalutPath) {
		this.addCommand(new FileFtpSendCommand(ftpClient, localFile, defalutPath));
	}

	@Override
	public void ftpMoveFiles2LocalFilesCommand(final FtpClient ftpClient, final String localPath,
			final String defalutPath) {
		this.addCommand(new FtpMoveFiles2LocalFilesCommand(ftpClient, localPath, defalutPath));
	}

	@Override
	public void ftpMoveFiles2LocalFilesCommand(final FtpClient ftpClient, final String localPath, final Filter filter,
			final String defalutPath) {
		this.addCommand(new FtpMoveFiles2LocalFilesCommand(ftpClient, localPath, filter, defalutPath));
	}

	@Override
	public void ftpmMoveFile2Local(final String remoteFileName, final String localPath, final FtpClient ftpClient,
			final String defalutPath) {
		this.addCommand(new FtpMoveFile2LocalCommand(remoteFileName, localPath, ftpClient, defalutPath));
	}

	private void addCommand(final Command command) {
		this.commands.add(command);
	}

}

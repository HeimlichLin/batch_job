package batchJob.service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.been.vo.fpg.FTZLFPGProcessVo;
import batchJob.common.file.FileCommand;
import batchJob.common.file.impl.FileCommandFacade;
import batchJob.common.queue.AbstractSubject;
import batchJob.common.queue.MyQueueConfig;
import batchJob.common.queue.Observer;
import batchJob.common.queue.QuDeqMedidata;
import batchJob.common.queue.Subject;
import batchJob.common.utils.FolderUtils;

public class CTZLFPGProcess extends AbstractSubject implements Observer {
	
	private static String NOTE_FLG = ".flg";
	private static final long SLEEP_TIME = 30 * 1000L;
	private static Logger LOGGER = LoggerFactory.getLogger("AP");

	final CPGSend2Ftp send2FtpCPG = new CPGSend2Ftp();// 傳送檔案至FTP
	
	private MyQueueConfig config;
	private CTZLFPGRun xmlParse;

	public CTZLFPGProcess() {
		this.config = QuDeqMedidata.CPG;
		this.xmlParse = new CTZLFPGRun();
		// this.registered(send2FtpCPG); TODO
	}
	
	public static void main(final String[] args) {
		final CTZLFPGProcess process = new CTZLFPGProcess();
		process.execution();
	}
	
	/**
	 * 重複執行
	 */
	public void loopExecution() {
		while (true) {
			this.execution();
		}
	}

	/**
	 * 讀檔案+送檔案
	 */
	public void execution() {
		try {
			this.work();
		} catch (final Exception e) {
			LOGGER.error("FTZLFPGProcess.execution 異常", e);
		}
	}

	private void work() throws InterruptedException {
		try {
			final FTZLFPGProcessVo vo = new FTZLFPGProcessVo(this.config);
			this.prepare(vo);
			final List<File> files = this.getPendingDir(vo);

			for (final File fileile : files) {
				LOGGER.error("CTZLFPGProcess.File:" + fileile.getPath());
				this.process(fileile, vo);
			}
			this.changeValue();// 呼叫觀察者
		} catch (final Exception e) {
			LOGGER.error("作業失敗", e);
		}
	}

	private void process(final File fileile, final FTZLFPGProcessVo vo) {
		final FileCommandFacade fileCommandFacade = new FileCommandFacade();
		final FileCommand fileCommand = fileCommandFacade.getFileCommand();
		try {
			try {
				this.executeContent(fileCommand, vo, fileile);
			} catch (final Exception e) {
				LOGGER.error("作業失敗", e);
				this.executeFail(fileCommand, vo, fileile, e);
			}
		} catch (final Exception e) {
			LOGGER.error("作業失敗", e);
		}

	}

	private void executeContent(//
			final FileCommand fileCommand, //
			final FTZLFPGProcessVo vo,//
			final File fileile) {//

		final File fileOkDir = vo.getFileOkDir();
		this.xmlParse.execution(fileCommand, fileile);
		fileCommand.renameTo(fileile, new File(fileOkDir, fileile.getName()));
		final File flgFile = new File(fileile.getPath() + NOTE_FLG);
		fileCommand.delete(flgFile);
	}

	private void executeFail(//
			final FileCommand fileCommand,//
			final FTZLFPGProcessVo vo,//
			final File fileile,//
			final Throwable e) {//
		final File fileErrDir = vo.getFileErrDir();
		fileCommand.renameTo(fileile, new File(fileErrDir, fileile.getName()));// 移動至錯誤資料夾
		fileCommand.delete(new File(fileile.getPath() + NOTE_FLG));
	}

	private List<File> getPendingDir(final FTZLFPGProcessVo vo) {
		// 過濾器 過濾出 L4檔案
		final FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(final File f) {
				if (!f.isFile()) {
					return false;
				}
				if (!f.getName().startsWith(QuDeqMedidata.CPG.name() + ".")) {
					return false;
				}
				return true;
			}
		};
		final Set<String> mapFile = new HashSet<String>();
		final File[] pendingFiles = vo.getPendingDir().listFiles(filter);

		for (final File file : pendingFiles) {
			if (file.getName().endsWith(NOTE_FLG)) {
				final String orgNameString = file.getName().replace(NOTE_FLG, StringUtils.EMPTY);
				mapFile.add(orgNameString);
			}
		}
		final List<File> files = new ArrayList<File>();
		for (final String fileName : mapFile) {
			for (final File file : pendingFiles) {
				if (fileName.equals(file.getName())) {
					files.add(file);
				}
			}
		}
		return files;
	}

	private void prepare(final FTZLFPGProcessVo vo) throws IOException {

		FolderUtils.createFolders(//
				vo.getPendingDir(), //
				vo.getFileErrDir()//
				);//
	}
	
	@Override
	public void update(final Subject subject) {
		LOGGER.debug("被通知該:" + subject.getClass().getName());
		this.execution();
	}

}

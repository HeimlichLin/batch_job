package batchJob.queue.dequeue;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tradevan.jmseqdq.core.QueTrans;
import com.tradevan.wcommons.ApContext;
import com.tradevan.wcommons.db.DbFactory;

import batchJob.common.exception.ApBusinessException;
import batchJob.common.file.FileCommand;
import batchJob.common.file.impl.FileCommandFacade;
import batchJob.common.queue.AbstractSubject;
import batchJob.common.queue.IQueue;
import batchJob.common.queue.MyQueueConfig;
import batchJob.common.queue.QuDeqMedidata;
import batchJob.common.queue.QueServiceFactory;

public class CPGDequeueFTZL extends AbstractSubject {
	
	private static Logger LOGGER = LoggerFactory.getLogger("AP");
	private final static String connectionId = ApContext.getContext().getSetting("pftzcConnectionId");
	private MyQueueConfig config;
	private IQueue queue;
	
	public CPGDequeueFTZL() {
		this.config = QuDeqMedidata.CPG;
		this.queue = QueServiceFactory.getQueService(this.config);

	}
	
	public static void main(final String[] args) {
		CPGDequeueFTZL cPGDequeueFTZL = new CPGDequeueFTZL();
		cPGDequeueFTZL.execution();
	}
	
	public void execution() {
		try {
			this.deqWithoutPropFile();
			this.changeValue();
		} catch (final SQLException e) {
			throw new ApBusinessException("CPGDequeueFTZL 錯誤!", e);
		}
	}

	private void deqWithoutPropFile() throws SQLException {
		final Connection con = DbFactory.open(connectionId);
		try {
			if (queue.openDeqTrans(con) == QueTrans.RET_OK) {
				this.exeDeq(queue);
			}
			queue.closeTrans();
			con.close();
		} catch (Exception e) {
			LOGGER.error("CPGDequeueFTZL.deqWithoutPropFile", e);
			throw new ApBusinessException("deqWithoutPropFile 失敗!", e);
		}

	}
	
	private void exeDeq(IQueue queue) {
		final List<String> contentStrings = queue.deQue();
		if (CollectionUtils.isEmpty(contentStrings)) {
			return;
		}
		for (final String string : contentStrings) {
			LOGGER.error("CPGDequeueFTZL.exeDeq:msg:" + string);
		}
		final String fileNameString = contentStrings.get(5);
		if (fileNameString.length() < 35) {
			LOGGER.error("檔名太短不處理:" + fileNameString);
			return;

		}
		final FileCommandFacade fileCommandFacade = new FileCommandFacade();
		final FileCommand fileCommand = fileCommandFacade.getFileCommand();
		final File pendFile = new File(fileNameString);
		final File newFile = new File(pendFile.getParent(), pendFile.getName().substring(0, 35));
		fileCommand.renameTo(pendFile, newFile);

		final File file = new File(newFile.getPath() + ".flg");
		fileCommand.createFile(file, Arrays.asList("ok"));

	}

	public IQueue getQueue() {
		return queue;
	}

	public void setQueue(IQueue queue) {
		this.queue = queue;
	}

}

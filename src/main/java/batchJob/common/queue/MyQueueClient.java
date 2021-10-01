package batchJob.common.queue;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.exception.ApBusinessException;

import com.tradevan.jmseqdq.core.QueService;
import com.tradevan.jmseqdq.core.QueTrans;

public class MyQueueClient implements IQueue {
	
	private static Logger LOGGER = LoggerFactory.getLogger("AP");
	final QueService queService;
	final MyQueueConfig config;
	private QueueMode mode;

	public MyQueueClient(final MyQueueConfig config) {
		this.config = config;
		final JmsConfig jmsConfig = config.getJmsConfig();
		final QueService qs = new QueService();
		qs.setSysCode(config.getSysCode());
		qs.setDeqDir(config.getPendingDir());
		qs.setFsIpPort(jmsConfig.getmFsip(), //
				jmsConfig.getmFsport());//
		qs.setJMSVASInfo(//
				jmsConfig.getVasSetUrl(),//
				jmsConfig.getVasJmsUsername(),//
				jmsConfig.getVasJmsPassword());//
		LOGGER.debug("SysCode:" + config.getSysCode());
		LOGGER.debug("DeqDir:" + config.getSysCode());
		LOGGER.debug("FsIpPort[ip:" + jmsConfig.getmFsip() + "port:" + jmsConfig.getmFsport() + "]");
		LOGGER.debug("JMSVASInfo[");

		LOGGER.debug("VasSetUrl:" + jmsConfig.getVasSetUrl());
		LOGGER.debug("vasJmsUsername:" + jmsConfig.getVasJmsUsername());
		LOGGER.debug("vasJmsPassword(:" + jmsConfig.getVasJmsPassword());
		LOGGER.debug("]");
		this.queService = qs;
	}

	@Override
	public int openTrans(final QueueMode mode, final Connection dbCon) {
		this.mode = mode;
		return this.queService.openTrans(mode.mode, dbCon);
	}

	@Override
	public List<String> deQue() {

		try {
			this.showDqParmeter();
			final String msg[] = new String[QueTrans.MSG_SIZE];
			if (this.queService.DeQue(this.config.getJmsUID(), this.config.getJmsPW(), this.config.getLgQueId(), msg) == QueTrans.RET_OK) {
				return Arrays.asList(msg);
			}
			return Arrays.asList();
		} catch (final FileNotFoundException e) {
			LOGGER.error("deQue fail", e);
			throw new ApBusinessException("deQue fail ", e);
		} catch (final IllegalArgumentException e) {
			LOGGER.error("deQue fail", e);
			throw new ApBusinessException("deQue fail ", e);
		}
	}

	private void showDqParmeter() {
		LOGGER.error("MyQueueClient run");
		LOGGER.error(String.format("userName:%s", this.config.getJmsUID()));
		LOGGER.error(String.format("passwd:%s", this.config.getJmsPW()));
		LOGGER.error(String.format("lgQueId:%d", this.config.getLgQueId()));
	}

	@Override
	public void closeTrans() {
		if (this.queService != null) {
			this.queService.closeTrans(this.mode.mode);
		}

	}

	@Override
	public int openDeqTrans(final Connection dbCon) {
		return this.openTrans(QueueMode.DEQ_MODE, dbCon);
	}

	@Override
	public int openEnqTrans(final Connection dbCon) {
		return this.openTrans(QueueMode.ENQ_MODE, dbCon);
	}

	@Override
	public int enQue(final EnqQueueContext context, final String fileName) {
		final JmsConfig jmsConfig = context.getJmsConfig();
		this.queService.setSysCode(context.getSysCode());
		this.queService.setHubType(context.getHubType());
		this.queService.setEnqDir(context.getPendingDir(), context.getOkDir(), context.getErrDir());
		this.queService.setFsIpPort(//
				context.getJmsConfig().getmFsip(), //
				context.getJmsConfig().getmFsport());
		this.queService.setLgQueId(context.getLgQueId(), context.getLgQueId());
		this.queService.setAutoSeq(true);
		this.queService.setJMSVASInfo(//
				jmsConfig.vasSetUrl, //
				jmsConfig.vasJmsUsername,//
				jmsConfig.vasJmsPassword);//
		try {
			return this.queService.EnQue(//
					context.getSendId(),//
					context.getRecvId(),//
					context.getDocName(),//
					context.getPendingDir() + fileName,//
					fileName);//
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public QueService getQueService() {
		return this.queService;
	}
	
	@Override
	public int enQue(final MyQueueConfig context, final EnQueContext enQueContext) {
		final JmsConfig jmsConfig = context.getJmsConfig();
		this.queService.setSysCode(context.getSysCode());
		this.queService.setHubType(context.getHubType());
		this.queService.setEnqDir(enQueContext.getEnqDir(), context.getOkDir(), context.getErrDir());
		this.queService.setFsIpPort(//
				context.getJmsConfig().getmFsip(), //
				context.getJmsConfig().getmFsport());
		this.queService.setLgQueId(context.getLgQueId(), context.getLgQueId());
		this.queService.setAutoSeq(true);
		this.queService.setJMSVASInfo(//
				jmsConfig.vasSetUrl, //
				jmsConfig.vasJmsUsername,//
				jmsConfig.vasJmsPassword);//
		LOGGER.error("setSysCode:" + context.getSysCode());
		LOGGER.error("setHubType:" + context.getHubType());
		LOGGER.error("setEnqDir:" + context.getPendingDir());
		LOGGER.error("setFsIpPort:" + context.getJmsConfig().getmFsip());
		LOGGER.error("setLgQueId:" + context.getLgQueId());
		try {
			LOGGER.error("queService.EnQue");
			LOGGER.error("getSendId:" + enQueContext.getSendId());
			LOGGER.error("getRecvId:" + enQueContext.getRecvId());
			LOGGER.error("getDocName：" + enQueContext.getDocName());
			LOGGER.error("getFilename:" + enQueContext.getFilename());
			LOGGER.error("getClientfile:" + enQueContext.getClientfile());
			return this.queService.EnQue(//
					enQueContext.getSendId(),//
					enQueContext.getRecvId(),//
					enQueContext.getDocName(),//
					enQueContext.getFilename(),//
					enQueContext.getClientfile());
		} catch (final Exception e) {
			throw new ApBusinessException("enQue錯誤!", e);
		}
	}

}

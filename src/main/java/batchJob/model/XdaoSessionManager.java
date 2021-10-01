package batchJob.model;

import batchJob.common.utils.db.PoDaoMapper;

import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.taurus.xdao.XdaoFactory;
import com.tradevan.taurus.xdao.XdaoSession;

public class XdaoSessionManager {
	
	private static final String CONN_ID = "apFtzcConn";

	protected static XdaoFactory xdaoFactory = XdaoFactory.getInstance();
	private static ThreadLocal<XdaoSession> sessions = new ThreadLocal<XdaoSession>();
	private static PoDaoMapper PODAOMAPPER = new PoDaoMapper();
	
	public static XdaoSession getXdaoSession() {
		XdaoSession session = (XdaoSession) sessions.get();
		if (session == null) {
			if (xdaoFactory == null) {
				init();
			}
			session = new DoXdaoSession(xdaoFactory.getXdaoSession(CONN_ID), PODAOMAPPER);
			sessions.set(session);
		}
		return session;

	}

	public static DoXdaoSession getDoXdaoSession() {
		return (DoXdaoSession) getXdaoSession();
	}

	private static void init() {
		try {
			xdaoFactory = null;
			xdaoFactory = XdaoFactory.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

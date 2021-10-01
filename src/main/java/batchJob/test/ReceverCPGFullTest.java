package batchJob.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import batchJob.been.CpgDetailDo;
import batchJob.common.exception.ApBusinessException;
import batchJob.model.XdaoSessionManager;

import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.taurus.xdao.SqlWhere;
import com.tradevan.taurus.xdao.XdaoException;
import com.tradevan.test.impl.GeneralTest;
import com.tradevan.wcommons.ApContext;

/**
 * 自轉郵接收測試
 * 
 *
 */
public class ReceverCPGFullTest extends GeneralTest {
	
	final String filenameString;

	public ReceverCPGFullTest(final String filenameString) {
		super(TestMedidatas.RECEVER_CPG_FULLTEST);
		this.filenameString = filenameString;
	}

	@Override
	public void before() {
		this.deletes(ApContext.getContext().getSetting("CPG_DEQ_PENDING_DIR"));
		this.deletes(ApContext.getContext().getSetting("CPG_DEQ_ERR_DIR"));
		this.deletes(ApContext.getContext().getSetting("CPG_DEQ_OK_DIR"));
		this.deleteTable();
		this.usetFakeDeQue();
	}

	private void usetFakeDeQue() {
		final String parent = ApContext.getContext().getSetting("CPG_DEQ_PENDING_DIR");

		File sorFile = new File("/PFTZC/TMP/TestSet", "CPG.PC00000000000020180905188F3.xml");

		File file = new File(parent, this.filenameString);
		File flgFile = new File(parent, this.filenameString + ".flg");
		file.delete();
		flgFile.delete();
		try {
			FileUtils.copyFile(sorFile, file);
			flgFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void deleteTable() {
		DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();

		SqlWhere sqlWhere = new SqlWhere();
		sqlWhere.add(CpgDetailDo.COLUMNS.FILENAME.name(), this.filenameString);
		try {
			doXdaoSession.delete("CPG_MAIN", sqlWhere);
			doXdaoSession.delete("CPG_DETAIL", sqlWhere);
			doXdaoSession.delete("EVENT_C_TRAN_MAIN", sqlWhere);
			doXdaoSession.delete("EVENT_C_TRAN_A", sqlWhere);
			doXdaoSession.delete("EVENT_C_TRAN_B", sqlWhere);
			doXdaoSession.delete("EVENT_C_TRAN_C", sqlWhere);
			doXdaoSession.delete("CPG_TRAN_MAIN", sqlWhere);
			doXdaoSession.delete("CPG_TRAN_POST", sqlWhere);
			doXdaoSession.delete("CPG_TRAN_DETAIL", sqlWhere);
		} catch (XdaoException e) {
			e.printStackTrace();
		}
	}

	private void deletes(String path) {
		File fold = new File(path);
		for (File file : fold.listFiles()) {
			if (file.isFile()) {
				file.delete();
			}
		}
	}

	@Override
	public void test() {
		File pendfile = new File(ApContext.getContext().getSetting("CPG_DEQ_PENDING_DIR"), filenameString);
		File errorFile = new File(ApContext.getContext().getSetting("CPG_DEQ_PENDING_DIR"), filenameString);
		File okfile = new File(ApContext.getContext().getSetting("CPG_DEQ_PENDING_DIR"), filenameString);
		if (!(pendfile.exists() || errorFile.exists() || okfile.exists())) {
			throw new ApBusinessException("檔案尚未抓取下來:" + filenameString);
		}
	}

	@Override
	public void after() {

	}

}

package batchJob.model;

import java.util.List;

import batchJob.been.CpgDetailDo;
import batchJob.been.CpgMainDo;

import com.tradevan.taurus.xdao.XdaoSession;

public interface ICPGMainDetailModel {
	/**
	 * 新增CPG明細
	 * 
	 * @param con
	 * @param vos
	 */
	public void insertCpgDetialDos(XdaoSession con, List<CpgDetailDo> vos);

	/**
	 * 新增CPG主檔
	 * 
	 * @param con
	 * @param vos
	 */
	public void insertCpgMainDos(XdaoSession con, List<CpgMainDo> vos);

}

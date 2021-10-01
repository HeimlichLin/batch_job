package batchJob.common.job.batchJob.task;

import java.util.ArrayList;
import java.util.List;

import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.taurus.xdao.SqlWhere;

import batchJob.been.po.CpgSettingPo;
import batchJob.common.job.PermanentJobDef;
import batchJob.model.XdaoSessionManager;
import batchJob.service.CpgFTPListenPermanentWork;

public class CpgPermanentJobs implements PermanentJobsLoader {
	
	public synchronized List<PermanentJobDef> loadJobs() {

		final DoXdaoSession xdaoSession = XdaoSessionManager.getDoXdaoSession();
		final List<CpgSettingPo> cpgSettingPos = xdaoSession.selectPo(CpgSettingPo.class, new SqlWhere());
		final List<PermanentJobDef> jobs = new ArrayList<PermanentJobDef>();
		for (final CpgSettingPo po : cpgSettingPos) {
			PermanentWork permanentWork = new CpgFTPListenPermanentWork(po);
			jobs.add(new PermanentJob(permanentWork));

		}
		return jobs;
	}

}

package batchJob.common.queue;

import java.util.List;

import batchJob.been.po.CpgSettingPo;

public interface QueueQueryComponent {
	
	public List<CpgSettingPo> getCpgSettingDos();// 取得傳送編號

}

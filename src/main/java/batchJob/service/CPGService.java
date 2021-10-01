package batchJob.service;

import java.util.List;

import batchJob.been.EventCTranADo;
import batchJob.been.EventCTranBDo;
import batchJob.been.EventCTranCDo;
import batchJob.been.EventCTranMainDo;

public interface CPGService {
	
	public List<EventCTranMainDo> queryEventCTranMainDos();

	public List<EventCTranADo> queryEventCTranADos(String fileName);

	public void updateEventCTranMainDo(EventCTranMainDo eventCTranMainDo);

	public List<EventCTranBDo> queryEventCTranBDos(String fileName);

	public List<EventCTranCDo> queryEventCTranCDos(String fileName);

}

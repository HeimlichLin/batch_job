package batchJob.been.vo.fpg;

import java.util.ArrayList;
import java.util.List;

import batchJob.been.EventCTranADo;
import batchJob.been.EventCTranBDo;
import batchJob.been.EventCTranCDo;
import batchJob.been.EventCTranMainDo;

public class FTZLFPGProcECVo {
	
	private EventCTranMainDo eventCTranMainDo;
	private List<EventCTranADo> eventCTranADo = new ArrayList<EventCTranADo>();
	private List<EventCTranBDo> eventCTranBDo = new ArrayList<EventCTranBDo>();
	private List<EventCTranCDo> eventCTranCDo = new ArrayList<EventCTranCDo>();
	
	public List<EventCTranADo> getEventCTranADo() {
		return eventCTranADo;
	}

	public void setEventCTranADo(List<EventCTranADo> eventCTranADo) {
		this.eventCTranADo = eventCTranADo;
	}

	public List<EventCTranBDo> getEventCTranBDo() {
		return eventCTranBDo;
	}

	public void setEventCTranBDo(List<EventCTranBDo> eventCTranBDo) {
		this.eventCTranBDo = eventCTranBDo;
	}

	public List<EventCTranCDo> getEventCTranCDo() {
		return eventCTranCDo;
	}

	public void setEventCTranCDo(List<EventCTranCDo> eventCTranCDo) {
		this.eventCTranCDo = eventCTranCDo;
	}

	public EventCTranMainDo getEventCTranMainDo() {
		return eventCTranMainDo;
	}

	public void setEventCTranMainDo(EventCTranMainDo eventCTranMainDo) {
		this.eventCTranMainDo = eventCTranMainDo;
	}

}

package batchJob.been.vo.cpg;

import java.util.ArrayList;
import java.util.List;

import batchJob.been.EventCTranADo;
import batchJob.been.EventCTranBDo;
import batchJob.been.EventCTranCDo;
import batchJob.been.EventCTranMainDo;

public class CPGSend2FtpCPGVo {
	
	private EventCTranMainDo eventCTranMainDo=new EventCTranMainDo();
	private List<EventCTranADo> eventCTranADos = new ArrayList<EventCTranADo>();
	private List<EventCTranBDo> eventCTranBDos = new ArrayList<EventCTranBDo>();
	private List<EventCTranCDo> eventCTranCDos = new ArrayList<EventCTranCDo>();

	public List<EventCTranADo> getEventCTranADos() {
		return eventCTranADos;
	}

	public void setEventCTranADos(List<EventCTranADo> eventCTranADos) {
		this.eventCTranADos = eventCTranADos;
	}

	public List<EventCTranBDo> getEventCTranBDos() {
		return eventCTranBDos;
	}

	public void setEventCTranBDos(List<EventCTranBDo> eventCTranBDos) {
		this.eventCTranBDos = eventCTranBDos;
	}

	public List<EventCTranCDo> getEventCTranCDos() {
		return eventCTranCDos;
	}

	public void setEventCTranCDos(List<EventCTranCDo> eventCTranCDos) {
		this.eventCTranCDos = eventCTranCDos;
	}

	public EventCTranMainDo getEventCTranMainDo() {
		return eventCTranMainDo;
	}

	public void setEventCTranMainDo(EventCTranMainDo eventCTranMainDo) {
		this.eventCTranMainDo = eventCTranMainDo;
	}
	
}

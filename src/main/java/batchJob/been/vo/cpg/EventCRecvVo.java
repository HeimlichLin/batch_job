package batchJob.been.vo.cpg;

import java.util.ArrayList;
import java.util.List;

import batchJob.been.EventCRecvADo;
import batchJob.been.EventCRecvBDo;
import batchJob.been.EventCRecvCDo;
import batchJob.common.xml.XmlOut;

public class EventCRecvVo implements XmlOut {

	private List<EventCRecvADo> eventCRecvADos = new ArrayList<EventCRecvADo>();
	private List<EventCRecvBDo> eventCRecvBDos = new ArrayList<EventCRecvBDo>();
	private List<EventCRecvCDo> eventCRecvCDos = new ArrayList<EventCRecvCDo>();
	private String fileName;

	public List<EventCRecvADo> getEventCRecvADos() {
		return this.eventCRecvADos;
	}

	public void setEventCRecvADos(final List<EventCRecvADo> eventCRecvADos) {
		this.eventCRecvADos = eventCRecvADos;
	}

	public List<EventCRecvBDo> getEventCRecvBDos() {
		return this.eventCRecvBDos;
	}

	public void setEventCRecvBDos(final List<EventCRecvBDo> eventCRecvBDos) {
		this.eventCRecvBDos = eventCRecvBDos;
	}

	public List<EventCRecvCDo> getEventCRecvCDos() {
		return this.eventCRecvCDos;
	}

	public void setEventCRecvCDos(final List<EventCRecvCDo> eventCRecvCDos) {
		this.eventCRecvCDos = eventCRecvCDos;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}
	
}

package batchJob.common.xml;

import java.util.List;

public class TxtBookData implements ContextData {
	
	final List<String> lines;

	public TxtBookData(final List<String> lines) {
		super();
		this.lines = lines;
	}

	public List<String> getLines() {
		return this.lines;
	}

}

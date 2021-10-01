package batchJob.common.xml;

import org.dom4j.Document;

public class XmlBookData implements ContextData {
	
	private Document document;

	public XmlBookData(final Document document) {
		super();
		this.document = document;
	}

	public Document getDocument() {
		return this.document;
	}

}

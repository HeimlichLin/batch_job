package batchJob.common.xml.impl;

import batchJob.common.xml.Book;
import batchJob.common.xml.ContextFactory;
import batchJob.common.xml.ContextReaderComponent;
import batchJob.common.xml.XmlOut;

public abstract class AbstractReaderComponent implements ContextReaderComponent {
	
	public ContextFactory contextFactory;

	public AbstractReaderComponent(ContextFactory contextFactory) {
		super();
		this.contextFactory = contextFactory;
	}


	@Override
	public final XmlOut getXmlBook() {
		return this.read(contextFactory.getContext());
	}

	abstract protected XmlOut read(Book context);

}

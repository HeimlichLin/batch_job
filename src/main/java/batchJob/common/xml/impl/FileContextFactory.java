package batchJob.common.xml.impl;

import java.io.File;

import batchJob.common.xml.Book;
import batchJob.common.xml.ContextFactory;

public class FileContextFactory extends ContextFactory {
	
	private File file;

	public FileContextFactory(File file) {
		super();
		this.file = file;
	}

	@Override
	protected Book extraction() {
		return new CommonFileXmlBook(file);
	}

}

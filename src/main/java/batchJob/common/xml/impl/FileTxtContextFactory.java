package batchJob.common.xml.impl;

import java.io.File;

import batchJob.common.xml.Book;
import batchJob.common.xml.ContextFactory;

public class FileTxtContextFactory extends ContextFactory {
	
	private File file;

	public FileTxtContextFactory(File file) {
		super();
		this.file = file;
	}

	@Override
	protected Book extraction() {
		return new CommonFileTxtBook(file);
	}

}

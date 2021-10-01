package batchJob.common.xml.impl;

import java.io.File;
import java.io.FileInputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.exception.ApBusinessException;
import batchJob.common.xml.Book;
import batchJob.common.xml.ContextData;
import batchJob.common.xml.XmlBookData;

/**
 * 共同讀取檔案
 * 
 *
 */
public class CommonFileXmlBook implements Book {
	
	private static Logger LOGGER = LoggerFactory.getLogger("AP");

	SAXReader reader;
	Document document;

	public CommonFileXmlBook(final File file) {
		LOGGER.error("XML file:" + file.getPath());
		this.reader = new SAXReader();
		this.document = this.getDocumentByFile(file);
		
	}

	private Document getDocumentByFile(File file) {
		try {
			LOGGER.debug("XML 讀取 file:" + file.getPath());
			return this.reader.read(new FileInputStream(file));
		} catch (final Exception e) {
			LOGGER.error("讀取錯誤", e);
			throw new ApBusinessException("讀取錯誤", e);
		}
	}

	@Override
	public ContextData read() {
		return new XmlBookData(this.document);
	}

}

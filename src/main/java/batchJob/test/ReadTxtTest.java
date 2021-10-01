package batchJob.test;

import java.io.File;

import batchJob.common.reader.EcReader;
import batchJob.common.xml.ContextFactory;
import batchJob.common.xml.impl.FileTxtContextFactory;

public class ReadTxtTest {
	
	public static void main(final String[] args) {
		final File f = new File("D:\\關貿\\設計文件\\自轉貨轉郵\\_自貿轉郵\\test-data\\sep2\\AC33003100001520180523019F3.TXT");
		final ContextFactory factory = new FileTxtContextFactory(f);
		final EcReader reader = new EcReader(factory);
		reader.getXmlBook();
		System.out.print("");
	}

}

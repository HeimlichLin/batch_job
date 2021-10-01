package batchJob.test;

import java.io.File;

import batchJob.been.vo.fpg.FTZLFPGcXmlMessageVo;
import batchJob.common.reader.CPGXmlReader;
import batchJob.common.xml.ContextFactory;
import batchJob.common.xml.impl.FileContextFactory;

public class ReadeTest {
	
	public static void main(final String[] args) {
		final File f = new File("D:\\PFTZC\\test\\CPG.PC12345678920180314001F1.xml");
		final ContextFactory factory = new FileContextFactory(f);
		final CPGXmlReader reader = new CPGXmlReader(factory);
		final FTZLFPGcXmlMessageVo vo = (FTZLFPGcXmlMessageVo) reader.getXmlBook();
		System.out.print(vo);
	}

}

package batchJob.test;

import java.io.File;

import batchJob.been.vo.fpg.FTZLFPGcXmlMessageVo;
import batchJob.common.reader.CPGXmlReader;
import batchJob.common.xml.ContextFactory;
import batchJob.common.xml.impl.FileContextFactory;

public class CPGXmlReadeTest {

	public static void main(final String[] args) {
		final File f = new File("D:/關貿/設計文件/自轉貨轉郵/包含PostMainInfo/PC00000000000020180611112F7.xml");
		final ContextFactory factory = new FileContextFactory(f);
		final CPGXmlReader reader = new CPGXmlReader(factory);
		final FTZLFPGcXmlMessageVo vo = (FTZLFPGcXmlMessageVo) reader.getXmlBook();
		System.out.print(vo);
	}
	
}

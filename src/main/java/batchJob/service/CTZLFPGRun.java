package batchJob.service;

import java.io.File;

import batchJob.been.vo.fpg.FTZLFPGcXmlBodyVo;
import batchJob.been.vo.fpg.FTZLFPGcXmlMessageVo;
import batchJob.common.codegenerate.MessageTy;
import batchJob.common.file.FileCommand;
import batchJob.common.reader.CPGXmlReader;
import batchJob.common.xml.ContextFactory;
import batchJob.common.xml.impl.FileContextFactory;

public class CTZLFPGRun {
	
	private CTZLFPGRunService service;

	public CTZLFPGRun() {
		this.service = new CTZLFPGRunServiceImpl();
	}

	public void execution(final FileCommand fileCommand, final File file) {
		final FTZLFPGcXmlMessageVo vo = this.readFile(file);
		for (final FTZLFPGcXmlBodyVo body : vo.getBody()) {
			body.setMessaggeType(this.getMessaggeType(file));// 訊息別
		}
		vo.setFileNmae(file.getName());
		this.service.insertEcXml(vo);

	}

	private String getMessaggeType(final File file) {
		if (file.getName().matches("CPG.PT.*")) {// 訊息別(F：自轉郵；C：貨轉郵) PT：貨轉郵
			return MessageTy.C.name();
		} else {
			return MessageTy.F.name();
		}
	}

	/**
	 * 讀取XML檔案
	 *
	 * @param file
	 * @return
	 */
	private FTZLFPGcXmlMessageVo readFile(final File file) {
		final ContextFactory factory = new FileContextFactory(file);
		final CPGXmlReader reader = new CPGXmlReader(factory);
		final FTZLFPGcXmlMessageVo vo = (FTZLFPGcXmlMessageVo) reader.getXmlBook();
		return vo;

	}

}

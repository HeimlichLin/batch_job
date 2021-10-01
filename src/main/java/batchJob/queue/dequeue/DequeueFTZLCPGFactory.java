package batchJob.queue.dequeue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import batchJob.common.queue.FakeMyQueue;
import batchJob.service.CTZLFPGProcess;

public class DequeueFTZLCPGFactory {
	
	public CPGDequeueFTZL getFakeJMS() {

		final String parent = "/PFTZC/TMP/REVFIL/CPG/";
		final String xmlFileName = "CPG.PC12345678920180314001F1.xml";
		final String path = parent + xmlFileName;

		File sorFile = new File("D:/關貿/設計文件/自轉貨轉郵/_自貿轉郵/test-data/sep1/CPG.PC00000000000020180611112F7.xml");
		new File(path).delete();
		new File(path+".flg").delete();
		try {
			FileUtils.copyFile(sorFile, new File(path));
			File flgFile=new File(path+".flg");
			flgFile.createNewFile();
			new File("/PFTZC/TMP/REVOK/CPG/"+xmlFileName).delete();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		final FakeMyQueue fakeMyQueue = new FakeMyQueue(path);
		final CPGDequeueFTZL subject = new CPGDequeueFTZL();// Dequeue

		// final CTZLFPGProcess ob = new CTZLFPGProcess();// 處理檔案
		// final CPGSend2Ftp sendFTP=new CPGSend2Ftp();
		// subject.registered(ob);
		// ob.registered(sendFTP);
		subject.setQueue(fakeMyQueue);

		return subject;
	}

	public CPGDequeueFTZL get() {
		final CPGDequeueFTZL subject = new CPGDequeueFTZL();
		final CTZLFPGProcess ob = new CTZLFPGProcess();
		subject.registered(ob);
		return subject;
	}

}

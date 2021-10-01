package batchJob.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.codegenerate.SystemDefine;
import batchJob.common.context.AppContext;

public class PropertiesTest {
	private static Logger LOGGER = LoggerFactory.getLogger(PropertiesTest.class);
	
	public static void main(String[] arg ) {
		PropertiesTest.propertiesTest();
	}
	
	public static void propertiesTest() {
		
		AppContext defaultProperties = new AppContext();
		
		LOGGER.info(defaultProperties.getValue("user"));
		LOGGER.info(defaultProperties.getPropertiesValue("user"));
		
		System.setProperty("SystemDefine", SystemDefine.PCLMS.name());

		AppContext pftzcProperties = new AppContext();
		
		LOGGER.info(pftzcProperties.getValue("user"));
		LOGGER.info(pftzcProperties.getPropertiesValue("user"));
		
		
		System.setProperty("SystemDefine", SystemDefine.PFTZC.name());

		AppContext pclmsProperties = new AppContext();
		
		LOGGER.info(pclmsProperties.getValue("user"));
		LOGGER.info(pclmsProperties.getPropertiesValue("user"));
		
		System.setProperty("SystemDefine", SystemDefine.PFOSP.name());

		AppContext pfospProperties = new AppContext();
		
		LOGGER.info(pfospProperties.getValue("user"));
		LOGGER.info(pfospProperties.getPropertiesValue("user"));
		
	}
	
	
	

}

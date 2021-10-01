package batchJob.common.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.xwork.StringUtils;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batchJob.common.codegenerate.SystemDefine;
import batchJob.common.exception.TxBusinessException;
import batchJob.common.utils.FolderUtils;

public class AppContext {
	private static Logger LOGGER = LoggerFactory.getLogger(AppContext.class);
	final static AppContext CONTEXT = new AppContext();
	private String config;
	private Properties properties = System.getProperties();
	private boolean load = false;
	final String systemCode;

	public AppContext() {
		super();
		this.systemCode = System.getProperty("SystemDefine");
		if (StringUtils.isBlank(systemCode)) {
			throw new TxBusinessException("系統代碼不得空白");
		}else {
			SystemDefine systemDefine = SystemDefine.valueOf(systemCode);
			this.config = systemDefine.getConfigFile();
		}		
	}
	
	public void loadConfig() {
		try {
//			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("/application.xml");
//			InputStream in = AppContext.class.getResourceAsStream("/application.xml");
//			props.load(in);	
			File file = FolderUtils.getResourcesFile(AppContext.class, config);
			this.properties.load(new FileInputStream(file));
			this.load = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	public String getValue(String key, String defaultValue) {
		return init().getProperty(key, defaultValue);
	}

	public String getValue(String key) {
		return init().getProperty(key);
	}

	private Properties init() {
		if (!load) {
			this.loadConfig();
		}
		return properties;
	}
	
	
	private static Properties loadProperties() {
		InputStream in = AppContext.class.getResourceAsStream("/application.xml");
		SAXReader reader = new SAXReader(); 		
//		try {
//			Document doc = reader.read(in);
//			Element rootElt = doc.getRootElement(); 
//			Element doElement = rootElt.element("application");			
//			Element aaaElement = doElement.element("settings");
//			Element bbbElement = aaaElement.element("set");
//			String valurString   = bbbElement.attributeValue("name");
//			bbbElement.attributeValue("value");
//			
//		} catch (FileNotFoundException e) {
//			LOGGER.debug("找不到檔案:", e);
//			e.printStackTrace();
//		}  catch (IOException e) {
//			LOGGER.debug("查詢xml失敗:", e);
//			e.printStackTrace();
//		}
		return null;
	}	
	
	public static String getPropertiesValue(String key, String defaultValue) {
		Properties props = loadProperties();
		return props.getProperty(key, defaultValue);
	}
	
	public static String getPropertiesValue(String key) {
		Properties props = loadProperties();
		return props.getProperty(key);
	}	

	public static AppContext get() {
		return CONTEXT;
	}

}

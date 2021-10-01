package batchJob.common.codegenerate;

public enum SystemDefine {
	PFOSP("pfosp/"), //
	PFTZC("pftzc/"), //
	PCLMS("pclms/") //

	;
	final String configFile;
	final static String CONFIG_FILE_DEFINE = "jdbc.properties";

	private SystemDefine(String parent) {
		this.configFile = parent+CONFIG_FILE_DEFINE;
	}

	public String getConfigFile() {
		return configFile;
	}

}
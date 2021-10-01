package batchJob.common.ftp.impl;

import java.util.EnumSet;
import java.util.List;

import batchJob.common.ftp.FtpsSetting;

public enum FtpsCheck implements FtpsSetting {

	F1("0000087142", "u6zi!njs", "輝進"), //
	F2("0000086297", "Ftz3995959", "華運(優西諾)"), //
	F3("0000041516", "F5x!DMiR", "迅揚(宏展)"), //

	;
	private String host;
	private String port;
	private String userId;
	private String pwd;
	private boolean impicit;
	private String label;

	private FtpsCheck(String userId, String pwd, final String label) {
		this.host = "fpp.post.gov.tw";
		this.port = "21";
		this.userId = userId;
		this.pwd = pwd;
		this.impicit = false;
		this.label = label;
	}
	public void check(){
		System.out.println("FTP:" + this.getLabel());
		CommonFtps commonFtps = new CommonFtps(this);
		commonFtps.connect();
		final List<String> filesList = commonFtps.searchAllFileName("/");// 所有要抓下來的檔名.
		System.out.println("====================");
		for (final String files : filesList) {
			System.out.println("file:" + files);
		}
		System.out.println("====================");
		System.out.println("CHECK ok!");
		commonFtps.close();
	}

	public static void main(String[] args) {
		final EnumSet<FtpsCheck> ftpsChecks = EnumSet.allOf(FtpsCheck.class);
		for (final FtpsCheck codes : ftpsChecks) {
			codes.check();
		}
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public String getPort() {
		return port;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public String getPwd() {
		return pwd;
	}

	@Override
	public boolean isImpicit() {
		return impicit;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}

package batchJob.common.ftp;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.tradevan.common.db.DoSqlWhere;
import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.wcommons.ApContext;

import batchJob.been.po.CpgSettingPo;
import batchJob.common.exception.ApBusinessException;
import batchJob.model.XdaoSessionManager;

public enum FtpConfigSetting implements FtpSetting {
	
	;//
	final String host;
	final String port;
	final String userId;
	final String pwd;
	final String defaultPath;
	
	final static Map<FtpConfigSetting, FtpSetting> MAP;
	
	private FtpConfigSetting(//
			final String host,//
			final String port, //
			final String userId,//
			final String pwd, final String defaultPath) {//
		this.host = this.getTest(host);
		this.port = this.getTest(port);
		this.userId = this.getTest(userId);
		this.pwd = this.getTest(pwd);
		this.defaultPath = this.getTest(defaultPath);
	}
	
	static {

		final Map<FtpConfigSetting, FtpSetting> map = new HashMap<FtpConfigSetting, FtpSetting>();
		final EnumSet<FtpConfigSetting> configSettings = EnumSet.allOf(FtpConfigSetting.class);
		for (final FtpConfigSetting configSetting : configSettings) {
			map.put(configSetting, configSetting);
		}
		MAP = Collections.unmodifiableMap(map);
	}
	
	@Override
	public String getHost() {
		return this.host;
	}

	@Override
	public String getPort() {
		return this.port;
	}

	@Override
	public String getUserId() {
		return this.userId;
	}

	@Override
	public String getPwd() {
		return this.pwd;
	}

	public static FtpSetting lookupByName(final FtpConfigSetting configSetting) {
		if (!MAP.containsKey(configSetting)) {
			throw new ApBusinessException("無定義此FTP");
		}
		return MAP.get(configSetting);
	}

	private String getTest(final String settingString) {
		return ApContext.getContext().getSetting(settingString);
	}

	public static CpgSettingPo lookup(final String postspecialaccount) {
		final DoXdaoSession xdaoSession = (DoXdaoSession) XdaoSessionManager.getXdaoSession();
		final DoSqlWhere<CpgSettingPo.COLUMNS> sqlWhere = new DoSqlWhere<CpgSettingPo.COLUMNS>();
		sqlWhere.add(CpgSettingPo.COLUMNS.POSTSPECIALACCOUNT, postspecialaccount);
		// sqlWhere.add(CpgSettingPo.COLUMNS.FTP_TYPE, ftpType);

		final List<CpgSettingPo> cpgSettingDos = xdaoSession.selectPo(CpgSettingPo.class, sqlWhere);
		if (CollectionUtils.isEmpty(cpgSettingDos)) {
			final String templteString = "查無此資料 特約號碼:%s";

			throw new ApBusinessException(String.format(templteString, postspecialaccount));
		}
		return cpgSettingDos.get(0);

	}
	
}

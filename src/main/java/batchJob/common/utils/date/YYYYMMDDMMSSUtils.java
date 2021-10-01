package batchJob.common.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import batchJob.common.exception.ApBusinessException;

public class YYYYMMDDMMSSUtils {
	
	protected static final SimpleDateFormat SDF = new SimpleDateFormat(
			"yyyyMMddmmss");

	public static String getText(Date date) {
		return SDF.format(date);
	}

	public static String getText() {
		return getText(new Date());
	}
	
	
	public static Date parse(String yyyyMMddmmss) {
		try {
			return SDF.parse(yyyyMMddmmss);
		} catch (ParseException e) {
			throw new ApBusinessException("日期格式錯誤:" + yyyyMMddmmss);
		}
	}
}


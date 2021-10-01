package batchJob.common.utils;

public class StringFormatUtils {
	
	public static <T> String toObjectString(T t) {
		if (t == null) {
			return "";
		}
		return t.toString();
	}

}

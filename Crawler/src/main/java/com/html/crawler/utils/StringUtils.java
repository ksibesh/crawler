package com.html.crawler.utils;

public class StringUtils {

	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String getNullSafeValue(String str) {
		return isEmpty(str) ? "" : str;
	}
}

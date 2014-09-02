package edu.nyu.poly.ai.tlj.tactictoe.utils;

public class StringUtil {

	public static String getNonNullString(String value) {
		if (value == null){
			value = "";
		}
		return value;
	}

	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}

	public static boolean isBlank(String value) {
		boolean blank = false;
		if (value == null){
			blank = true;
		} else if (value.trim().length() == 0){
			blank = true;
		}
		return blank;
	}

}

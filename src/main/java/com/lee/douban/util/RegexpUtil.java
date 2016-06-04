package com.lee.douban.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpUtil {

	public static final String COMMENT_PEOPLE = "(?:\\D*)(\\d*)(?:\\D*)";

	private static final String ACTOR = "((.*?\\d{4}-\\d{2}-\\d{2}.*?/)*)(.*)";
	
	public static String getActors(String content) {
		Matcher m = Pattern.compile(ACTOR).matcher(content);
		if (m.matches() && m.groupCount() >= 1) {
			return m.group(m.groupCount());
		}
		return "NULL";
	}

	public static String getStartDate(String content) {
		Matcher m = Pattern.compile(ACTOR).matcher(content);
		if (m.matches() && m.groupCount() >= 1) {
			return m.group(1);
		}
		return "NULL";
	}
	
	public static String getCommentPeople(String content){
		Matcher m = Pattern.compile(COMMENT_PEOPLE).matcher(content);
		if (m.matches() && m.groupCount() >= 1) {
			return "".equals(m.group(1)) ?"0":m.group(1);
		}
		return "0";
	}

	public RegexpUtil() {
	}

}

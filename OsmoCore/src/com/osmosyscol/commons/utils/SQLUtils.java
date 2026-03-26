package com.osmosyscol.commons.utils;

import org.apache.commons.lang.StringEscapeUtils;

public class SQLUtils {

	public static String escapeSql(Object sql) {
		if (sql == null) {
			return "";
		} else {
			return StringEscapeUtils.escapeSql(sql.toString());
		}
	}

}

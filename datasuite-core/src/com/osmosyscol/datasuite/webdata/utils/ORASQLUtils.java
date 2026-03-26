package com.osmosyscol.datasuite.webdata.utils;

public class ORASQLUtils {

	public static String addPaginacion(String sql, Integer numPagina, Integer tamPagina) {

		String sqlpag = "select * from ( select p_.*, rownum rn from (" + sql + ") p_ where rownum <=" + (numPagina * tamPagina) + " ) q_ where q_.rn > " + ((numPagina - 1) * tamPagina) + " order by q_.rn ";

		return sqlpag;

	}

}

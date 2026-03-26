package com.osmosyscol.datasuite.persistencia.dao.ibatis.typehandlers;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class StringBooleanTypeHandler implements TypeHandlerCallback {

	public Object valueOf(String value) {

		if (value.equals("S")) {

			return Boolean.TRUE;

		} else {

			return Boolean.FALSE;

		}

	}

	public void setParameter(ParameterSetter param, Object value)
			throws SQLException {

		Boolean bValue = (Boolean) value;

		param.setString(bValue.booleanValue() ? "S" : "N");

	}

	public Object getResult(ResultGetter rg) throws SQLException {

		return valueOf(rg.getString());

	}

}
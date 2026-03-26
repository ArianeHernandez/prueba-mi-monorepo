package com.osmosyscol.datasuite.webdata.logica.servicios.carga;

import java.math.BigDecimal;
import java.util.List;

public class OperacionesTablaControl {

	public static final String[] OPERACIONES = { "sumar", "contar" };

	public static BigDecimal operar(List<String> data, String operacion) {

		// --------------------------------------------------
		// --------------------------------------------------

		if ("sumar".equalsIgnoreCase(operacion)) {

			BigDecimal total = new BigDecimal(0);
			for (String d : data) {
				if (d != null && d.trim().length() > 0) {
					try {
						total = total.add(new BigDecimal(d));
					} catch (Exception e) {
					}
				}
			}
			return total;
		}

		// --------------------------------------------------
		// --------------------------------------------------

		if ("contar".equalsIgnoreCase(operacion)) {

			Integer cantidad = 0;
			for (String d : data) {
				if (d != null && d.trim().length() > 0) {
					cantidad++;
				}
			}
			return new BigDecimal(cantidad);
		}

		// --------------------------------------------------
		// --------------------------------------------------

		return null;
	}

}

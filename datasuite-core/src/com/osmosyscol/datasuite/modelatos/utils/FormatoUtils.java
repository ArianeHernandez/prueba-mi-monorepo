package com.osmosyscol.datasuite.modelatos.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;

public class FormatoUtils {

	/**
	 * Retorna el valor del campo con el formato definido en el FormatoCampo
	 * @param formatoCampo
	 * @param valor
	 * @return
	 */
	public static String obtenerValorPorFormato(FormatoCampo formatoCampo, Object valor) {

		if (valor == null) {
			return null;
		}

		if (StringUtils.isNotEmpty(formatoCampo.getFormato())) {

			try {

				if (valor instanceof Date) {

					return new SimpleDateFormat(formatoCampo.getFormato()).format((Date) valor).replace(" 00:00:00", "").trim();
				}

				if (valor instanceof BigDecimal) {
					NumberFormat formatter = new DecimalFormat(formatoCampo.getFormato());
					return formatter.format(valor);
				}

			} catch (Exception e) {
				SimpleLogger.setError("No se puede dar el formato del formato campo: " + formatoCampo + " - " + formatoCampo.getFormato() + ", para el valor:  " + valor);
			}
		}
		
		return com.osmosyscol.commons.utils.StringUtils.toStringFormat(valor);
	}

}

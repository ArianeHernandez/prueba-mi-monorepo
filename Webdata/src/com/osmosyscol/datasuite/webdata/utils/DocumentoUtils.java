package com.osmosyscol.datasuite.webdata.utils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class DocumentoUtils {

	/**
	 * determina si el valor ingresado es Nit
	 */
	public static boolean esNIT(Long identificacion) {
		
		return (identificacion !=null && identificacion > 800000000 && identificacion < 1000000000);
	}

	/**
	 *  
	 */
	public static Long ajustarNumeroDocumento(String numero) {
		String validarPropiedad = ParametrosInicio.getProperty("retiros.noQuitarCaracteresEspeciales");
		if( validarPropiedad == null || validarPropiedad.equals("false") ) {
			numero = StringUtils.quitarCaracteresEspeciales(numero, "1234567890", null);
		}

		if (StringUtils.esVacio(numero)) {
			return null;
		}

		// ----------

		long num_1 = Long.parseLong(numero.substring(0, numero.length() - 1));
		int num_2 = Integer.parseInt(numero.substring(numero.length() - 1));

		if (esNIT(num_1) && num_2 == StringUtils.calcularDigitoVerificacion(num_1 + "")) {
			return num_1;
		}

		return Long.parseLong(numero);
	}

	// -----------------------------------

	public static void main(String[] args) {

		Long numero = ajustarNumeroDocumento("'08300540540");
		SimpleLogger.setDebug(numero + ":" + (esNIT(numero)?"NIT": "CC"));
	}

}

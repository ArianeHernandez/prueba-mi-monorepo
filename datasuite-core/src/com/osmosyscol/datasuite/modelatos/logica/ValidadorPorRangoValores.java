package com.osmosyscol.datasuite.modelatos.logica;

import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;

public class ValidadorPorRangoValores implements IValidadorDeRegistrosDeCarga {

	public Boolean esRegistroValido(Map<String, Object> registro, List<Map<String, Object>> registros, ValidacionEstructura validacionEstructura) {

		try {
			Boolean esRegistroValido = true;

			List<ValidacionCampo> campos = validacionEstructura.getListaValidacionCampo();

			for (ValidacionCampo validacionCampo : campos) {

				Integer id_campo = validacionCampo.getId_campo();
				Double primer_valor = Double.parseDouble(validacionCampo.getPrimer_valor());
				Double segundo_valor = Double.parseDouble(validacionCampo.getSegundo_valor());

				if (registro.get("C" + id_campo) != null) {

					Double valorCampo = Double.parseDouble("" + Crypto.DF(registro.get("C" + id_campo)));

					// Se verifica si el valor del campo esta en el rango
					// establecido por el primer y segundo valor
					if (valorCampo < primer_valor || valorCampo > segundo_valor) {
						esRegistroValido = false;
					}
				}

			}

			return esRegistroValido;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;

	}

}

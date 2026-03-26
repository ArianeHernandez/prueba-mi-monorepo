package com.osmosyscol.datasuite.webdata.correval.cargueplano;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorUtils;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;
import com.osmosyscol.datasuite.webdata.utils.DocumentoUtils;

public class LectorArchivoPlanoDavivienda implements LectorArchivoPlano {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("DAVIVIENDA");
	}

	public List<Retiro> leerRetiros(String archivo) {

		try {

			Integer[] longitudes = new Integer[] { 2, 16, 16, 16, 2, 6, 18, 6, 2, 1, 4, 40, 18, 8, 4, 4, 7 };
			String[][] lineas = LectorArchivoPlanoUtils.leerArchivo(archivo, longitudes);

			if (lineas == null || lineas.length < 2) {
				return null;
			}

			List<Retiro> retiros = new ArrayList<Retiro>();

			for (int i = 1; i < lineas.length; i++) {
				if (lineas[i][0].equals("TR")) {
					Retiro retiro = convLineaARetiro(StringUtils.trim(lineas[i]));
					retiros.add(retiro);
				}
			}

			return retiros;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	private Retiro convLineaARetiro(String linea[]) {
		Retiro retiro = new Retiro();
		String nombre_beneficiario = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("BENEFICIARIO_ARCHIVO_DAVIVIENDA");
		retiro.setNombre_beneficiario(com.osmosyscol.commons.utils.StringUtils.trim(nombre_beneficiario));
		
		retiro.setIdentificacion_beneficiario(DocumentoUtils.ajustarNumeroDocumento(linea[1]));

		if (DocumentoUtils.esNIT(retiro.getIdentificacion_beneficiario())) {
			retiro.setTipo_documento_beneficiario(gu.getTipoDocumentoInterno("01")); // Nit
		} else {
			retiro.setTipo_documento_beneficiario(gu.getTipoDocumentoInterno("02")); // Cedula
		}

		String codBancoLeido = new Long(linea[5]).toString(); // limpiar 0s a la
																// izquierda
		String codBanco = gu.getCodBancoInterno(codBancoLeido);

		if (StringUtils.esNoVacio(codBanco)) {
			retiro.setCod_banco(codBanco);
		}

		if (retiro.getCod_banco() == null) {
			retiro.setCod_banco(codBancoLeido);
		}

		BigDecimal valor = LectorArchivoPlanoUtils.obtenerNumero2Decimales(linea[6]);
		retiro.setValor(valor);
		retiro.setCuenta_beneficiario(gu.ajustarLongitudCuenta(linea[3], retiro.getCod_banco()));

		// El ejemplo tiene como tipo de cuenta DP
		retiro.setTipo_cuenta_beneficiario_banco(linea[4].trim());

		String cuenta = gu.getTipoCuentaInterno(linea[4].trim());
		retiro.setTipo_cuenta_beneficiario(new Long(cuenta));

		String observacion = linea[15].trim();
		retiro.setObservacion(observacion);

		return retiro;
	}

}

package com.osmosyscol.datasuite.webdata.correval.cargueplano;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorUtils;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;
import com.osmosyscol.datasuite.webdata.utils.DocumentoUtils;

public class LectorArchivoPlanoOccidente implements LectorArchivoPlano {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("OCCIDENTE");
	}

	public List<Retiro> leerRetiros(String archivo) {

		try {

			Integer[] longitudes = new Integer[] { 1, 4, 16, 30, 11, 4, 8, 1, 15, 16, 12, 1, 0};
			String[][] lineas = LectorArchivoPlanoUtils.leerArchivo(archivo, longitudes);

			if (lineas == null || lineas.length < 2) {
				return null;
			}

			List<Retiro> retiros = new ArrayList<Retiro>();

			for (int i = 1; i < lineas.length - 1; i++) {
				Retiro retiro = convLineaARetiro(StringUtils.trim(lineas[i]));
				retiros.add(retiro);
			}

			return retiros;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	private Retiro convLineaARetiro(String linea[]) {
		Retiro retiro = new Retiro();

		retiro.setNombre_beneficiario(linea[3].trim());
		retiro.setIdentificacion_beneficiario(DocumentoUtils.ajustarNumeroDocumento(linea[4]));

		if (DocumentoUtils.esNIT(retiro.getIdentificacion_beneficiario())) {
			retiro.setTipo_documento_beneficiario(gu.getTipoDocumentoInterno("N")); // Nit
		} else {
			retiro.setTipo_documento_beneficiario(gu.getTipoDocumentoInterno("C")); // Cedula
		}

		String codBancoLeido = new Long(linea[5]).toString(); // limpiar 0s a la
															// izquierda
		String codBanco = gu.getCodBancoInterno(codBancoLeido);

		if (StringUtils.esNoVacio(codBanco)) {
			retiro.setCod_banco(codBanco);
		}
		
		if (retiro.getCod_banco() == null){
			retiro.setCod_banco(codBancoLeido);
		}

		BigDecimal valor = LectorArchivoPlanoUtils.obtenerNumero2Decimales(linea[8]);
		retiro.setValor(valor);
		retiro.setCuenta_beneficiario(linea[9]);

		retiro.setTipo_cuenta_beneficiario_banco(linea[11].trim());

		String cuenta = gu.getTipoCuentaInterno(linea[11].trim());
		retiro.setTipo_cuenta_beneficiario(new Long(cuenta));

		String observacion = linea[12].trim();
		retiro.setObservacion(observacion);
		
		return retiro;
	}

}

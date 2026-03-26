package com.osmosyscol.datasuite.webdata.correval.cargueplano;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorUtils;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;
import com.osmosyscol.datasuite.webdata.utils.DocumentoUtils;

public class LectorArchivoPlanoBancolombiaPAB implements LectorArchivoPlano {

	private String cod_banco = GeneradorUtils.getCodBancoByDesc("BANCOLOMBIA");
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("BANCOLOMBIA PAB");
	}

	public List<Retiro> leerRetiros(String archivo) {

		try {

			Integer[] longitudes = new Integer[] { 1, 15, 30, 9, 17, 1, 2, 17, 8, 21, 1, 5, 15, 80};
			String[][] lineas = LectorArchivoPlanoUtils.leerArchivo(archivo, longitudes);

			if (lineas == null || lineas.length < 2) {
				return null;
			}

			List<Retiro> retiros = new ArrayList<Retiro>();

			for (int i = 0; i < lineas.length; i++) {
				
				if(lineas[i][0].equals("6")){ // unicamente la linea de detalle de transaccion
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

		retiro.setNombre_beneficiario(linea[2].trim());
		retiro.setIdentificacion_beneficiario(DocumentoUtils.ajustarNumeroDocumento(linea[1]));

		if (DocumentoUtils.esNIT(retiro.getIdentificacion_beneficiario())) {
			retiro.setTipo_documento_beneficiario(gu.getTipoDocumentoInterno("N")); // Nit
		} else {
			retiro.setTipo_documento_beneficiario(gu.getTipoDocumentoInterno("C")); // Cedula
		}

		if (linea[3] != null) {
			String codBancoLeido = new Long(linea[3]).toString(); // limpiar 0s
																	// a la
																	// izquierda
			String codBanco = gu.getCodBancoInterno(codBancoLeido);

			if (StringUtils.esNoVacio(codBanco)) {
				retiro.setCod_banco(codBanco);
			} else {
				retiro.setCod_banco(codBancoLeido);
			}
		}

		BigDecimal valor = LectorArchivoPlanoUtils.obtenerNumero2Decimales(linea[7]);
		retiro.setValor(valor);
		retiro.setCuenta_beneficiario(gu.ajustarLongitudCuenta(linea[4], retiro.getCod_banco()));

		String observacion = linea[9].trim();
		retiro.setObservacion(observacion);

		String cuenta = null;
		String tipotransaccion = linea[6].trim();

		if ("27".equals(tipotransaccion)) {
			retiro.setTipo_cuenta_beneficiario_banco("D");
			cuenta = gu.getTipoCuentaInterno("D"); // cuenta corriente
		}

		if ("37".equals(tipotransaccion)) {
			retiro.setTipo_cuenta_beneficiario_banco("S");
			cuenta = gu.getTipoCuentaInterno("S"); // cuenta de ahorros
		}

		if (cuenta != null) {
			retiro.setTipo_cuenta_beneficiario(new Long(cuenta));
		}
		
		retiro.setCorreo_beneficiario(linea[13].trim());
		
		return retiro;
	}

}

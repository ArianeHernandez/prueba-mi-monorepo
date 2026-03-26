package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.LectorArchivoPlanoUtils;

public class InterpreteRespuestaBancolombiaPAB implements IInterpreteRespuestaBanco {

	private static String nombreBanco = "Bancolombia";

	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {

		try {

			Integer[] longitudes = new Integer[] { 13, 8, 2, 1, 15, 30, 9, 17, 1, 2, 18, 9, 13, 3, 8, 8 };
			String[][] lineas = LectorArchivoPlanoUtils.leerArchivo(rutaArchivo, longitudes);

			// Listado de respuestaBanco
			ArrayList<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();

			Set<String> retiros_encontrados = new HashSet<String>();

			for (short i = 0; i < lineas.length; i++) {

				Integer filaArchivo = (i + 1);

				String linea[] = StringUtils.trim(lineas[i]);

				try {

					String estado_respuesta = linea[13];

					String num_cuenta = linea[7];

					String num_doc = linea[4];

					String valor = new BigDecimal(linea[10].replace(",", ".")).setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace(".00", "");

					RegistroEstado registroestado = null;

					registroestado = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerIDRetiroParaActualizarRespuestaBanco(id_banco, null, num_doc, num_cuenta, valor, retiros_encontrados);

					String estadoBanco = estado_respuesta;

					listaRespuestaBancos.add(ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestaBanco(registroestado, estadoBanco, id_banco, filaArchivo, num_cuenta, num_doc, valor));

				} catch (Exception e) {
					SimpleLogger.setError("obtenerRespuestasPorBanco", e);
				}

			}

			return listaRespuestaBancos;
		} catch (Exception e) {
			SimpleLogger.setError("obtenerRespuestasPorBanco", e);
			return null;
		}

	}

	/**
	 * Validacion del Archivo
	 */
	public Boolean validarArchivo(String rutaArchivo, String id_banco) {
		try {
			// TODO: Falta validacion del archivo

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("validarArchivo", e);
			return false;
		}
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

}

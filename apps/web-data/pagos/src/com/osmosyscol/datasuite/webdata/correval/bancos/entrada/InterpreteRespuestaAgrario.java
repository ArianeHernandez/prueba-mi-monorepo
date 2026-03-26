package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.osmosyscol.commons.log.SimpleLogger;

public class InterpreteRespuestaAgrario implements IInterpreteRespuestaBanco {

	private static short COLUMNA_ESTADO_RESPUESTA = 8;

	private static short COLUMNA_NUM_DOC_BENEFICIARIO = 1;
	private static short COLUMNA_NUM_CUENTA_BENEFICIARIO = 3;
	private static short COLUMNA_VALOR = 6;

	private static short FILA_INICIO_RESPUESTAS = 1;

	// HEADER
	private static short FILA_HEADER = 0;
	private static String TITULO_ESTADO_RESPUESTA = "Estado";

	private static String nombreBanco = "Banco Agrario";

	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {

		try {

			InputStream inp = new FileInputStream(rutaArchivo);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			HSSFSheet hoja1 = wb.getSheetAt(0);

			// Listado de respuestaBanco
			ArrayList<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();

			Set<String> retiros_encontrados = new HashSet<String>();

			for (short i = FILA_INICIO_RESPUESTAS; i <= hoja1.getLastRowNum(); i++) {
				try {

					HSSFRow fila = hoja1.getRow(i);
					Integer filaArchivo = (i + 1);

					HSSFCell celdaEstadoRespuesta = fila.getCell(COLUMNA_ESTADO_RESPUESTA);
					String estado_respuesta = getValorCelda(celdaEstadoRespuesta);

					HSSFCell celdaCuenta = fila.getCell(COLUMNA_NUM_CUENTA_BENEFICIARIO);
					String num_cuenta = getValorCelda(celdaCuenta);

					HSSFCell celdaDocBeneficiario = fila.getCell(COLUMNA_NUM_DOC_BENEFICIARIO);
					String num_doc = getValorCelda(celdaDocBeneficiario);

					HSSFCell celdaValor = fila.getCell(COLUMNA_VALOR);
					String valor = getValorCelda(celdaValor);

					String estadoBanco = estado_respuesta;

					RegistroEstado registroestado = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerIDRetiroParaActualizarRespuestaBanco(id_banco, null, num_doc, num_cuenta, valor, retiros_encontrados);
					listaRespuestaBancos.add(ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestaBanco(registroestado, estadoBanco, id_banco, filaArchivo, num_cuenta, num_doc, valor));

				} catch (Exception e) {
				}

			}

			return listaRespuestaBancos;
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
			return null;
		}

	}

	private String getValorCelda(HSSFCell celda) {
		return ManejadorDeInterpretesRespuestaBanco.getValorCelda(celda);
	}

	public Boolean validarArchivo(String rutaArchivo, String id_banco) {
		try {
			Boolean esArchivoValido = false;

			InputStream inp = new FileInputStream(rutaArchivo);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			HSSFSheet hoja1 = wb.getSheetAt(0);

			// Se obtiene la fila del encabezado
			HSSFRow fila = hoja1.getRow(FILA_HEADER);

			HSSFCell celdaEstadoRespuesta = fila.getCell(COLUMNA_ESTADO_RESPUESTA);
			String titulo_estado_respuesta = getValorCelda(celdaEstadoRespuesta);

			if (titulo_estado_respuesta.trim().equals(TITULO_ESTADO_RESPUESTA)) {
				esArchivoValido = true;
			}

			return esArchivoValido;

		} catch (Exception e) {

			SimpleLogger.setError("Error", e);
			return false;
		}

	}

	public String getNombreBanco() {
		return nombreBanco;
	}
}

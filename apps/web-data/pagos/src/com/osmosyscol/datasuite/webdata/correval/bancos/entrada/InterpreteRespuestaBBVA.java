package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.osmosyscol.commons.log.SimpleLogger;

public class InterpreteRespuestaBBVA implements IInterpreteRespuestaBanco {

//	private static short COLUMNA_NUM_DOC_BENEFICIARIO = 2;
	private static short COLUMNA_NOMBRE_BENEFICIARIO= 1;
	private static short COLUMNA_NUM_CUENTA_BENEFICIARIO = 2;
	private static short COLUMNA_VALOR = 3;
	private static short COLUMNA_ESTADO_RESPUESTA = 4;
	private static short FILA_INICIO_RESPUESTAS = 21;

	// HEADER
	private static short FILA_HEADER = 20;
	private static String TITULO_ESTADO_RESPUESTA = "Motivo";

	private static String nombreBanco = "BBVA";

	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {

		try {

			Workbook wb = ManejadorDeInterpretesRespuestaBanco.getInstance().leerWorkbook(rutaArchivo);
			if(wb == null) {
				return null;
			}
			
			Sheet hoja1 = wb.getSheetAt(0);
			
			// Listado de respuestaBanco
			ArrayList<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();

			Set<String> retiros_encontrados = new HashSet<String>();

			for (short i = FILA_INICIO_RESPUESTAS; i <= hoja1.getLastRowNum(); i++) {
				try {

					Row fila = hoja1.getRow(i);
					Integer filaArchivo = (i + 1);

					String estado_respuesta = getValorCelda(fila.getCell(COLUMNA_ESTADO_RESPUESTA));
					String nombre_beneficiario = getValorCelda(fila.getCell(COLUMNA_NOMBRE_BENEFICIARIO));
					String num_cuenta = getValorCelda(fila.getCell(COLUMNA_NUM_CUENTA_BENEFICIARIO));
					num_cuenta.replace("'", "");

//					if (num_cuenta.length() >= 16 && num_cuenta.charAt(0)=='0') {
//						num_cuenta = num_cuenta.substring(1, num_cuenta.length()-1);
//					}

//					String num_doc = getValorCelda(fila.getCell(COLUMNA_NUM_DOC_BENEFICIARIO));
					String num_doc = null;

					String valor = getValorCelda(fila.getCell(COLUMNA_VALOR));

					String estadoBanco = estado_respuesta;

					RegistroEstado registroestado = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerIDRetiroParaActualizarRespuestaBanco(id_banco, null, num_doc, nombre_beneficiario, num_cuenta, valor, retiros_encontrados);
					listaRespuestaBancos.add(ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestaBanco(registroestado, estadoBanco, id_banco, filaArchivo, num_cuenta, num_doc, valor));

				} catch (Exception e) {
				}

			}
			wb.close();
			return listaRespuestaBancos;
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
			return null;
		}

	}

	private String getValorCelda(Cell cell) {
		return ManejadorDeInterpretesRespuestaBanco.getValorCelda(cell);
	}
	
	

	public Boolean validarArchivo(String rutaArchivo, String id_banco) {
		try {
			Boolean esArchivoValido = false;

			Workbook wb = ManejadorDeInterpretesRespuestaBanco.getInstance().leerWorkbook(rutaArchivo);
			if(wb == null) {
				return false;
			}
			
			Sheet hoja1 = wb.getSheetAt(0);
			
			// Se obtiene la fila del encabezado
			Row fila = hoja1.getRow(FILA_HEADER);
			
			String titulo_estado_respuesta = getValorCelda(fila.getCell(COLUMNA_ESTADO_RESPUESTA));

			if (titulo_estado_respuesta.trim().toUpperCase().equals(TITULO_ESTADO_RESPUESTA.trim().toUpperCase())) {
				esArchivoValido = true;
			}
			
			wb.close();
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

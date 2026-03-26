package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import com.osmosyscol.commons.utils.StringUtils;

import co.htsoft.commons.file.FileUtils;

public class InterpreteRespuestaDavivienda implements IInterpreteRespuestaBanco {

	public Boolean validarArchivo(String rutaArchivo, String id_banco) {

		Boolean valido = validarArchivoExcel(rutaArchivo, id_banco);

		if (!valido) {
			valido = validarArchivoExcel2(rutaArchivo, id_banco);
		}

		if (!valido) {
			valido = validarArchivoHTML(rutaArchivo, id_banco);
		}

		return valido;
	}

	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {
		if (validarArchivoExcel(rutaArchivo, id_banco)) {
			return obtenerRespuestasPorBancoExcel(rutaArchivo, id_banco);
		}

		if (validarArchivoExcel2(rutaArchivo, id_banco)) {
			return obtenerRespuestasPorBancoExcel2(rutaArchivo, id_banco);
		}

		if (validarArchivoHTML(rutaArchivo, id_banco)) {
			return obtenerRespuestasPorBancoHTML(rutaArchivo, id_banco);
		}

		return new ArrayList<RespuestaBanco>();

	}

	// ----------------------------------------------------------------------------

	private static short COLUMNA_ESTADO_RESPUESTA = 7;

	private static short COLUMNA_NUM_DOC_BENEFICIARIO = 0;
	private static short COLUMNA_NUM_CUENTA_BENEFICIARIO = 4;
	private static short COLUMNA_VALOR = 6;

	private static short FILA_INICIO_RESPUESTAS = 29;

	// HEADER
	private static short FILA_HEADER = 28;
	private static String TITULO_ESTADO_RESPUESTA = "Estado";

	private static String nombreBanco = "Davivienda";

	public List<RespuestaBanco> obtenerRespuestasPorBancoExcel(String rutaArchivo, String id_banco) {

		try {

			InputStream inp = new FileInputStream(rutaArchivo);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			HSSFSheet hoja1 = wb.getSheetAt(0);

			// Listado de respuestaBanco
			ArrayList<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();

			Set<String> retiros_encontrados = new HashSet<String>();

			String cod_proceso = StringUtils.trimToNull(getValorCelda(hoja1.getRow(5).getCell(1)));

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
					String valor = getValorCelda(celdaValor).replace("$", "").replace(" ", "").replace(".", "").replace(",", ".").replace(".00", "");

					String estadoBanco = estado_respuesta;

					if (!StringUtils.esVacio(estado_respuesta, num_cuenta, num_doc, valor)) {
						RegistroEstado registroestado = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerIDRetiroParaActualizarRespuestaBanco(id_banco, cod_proceso, num_doc, num_cuenta, valor, retiros_encontrados);
						listaRespuestaBancos.add(ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestaBanco(registroestado, estadoBanco, id_banco, filaArchivo, num_cuenta, num_doc, valor));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			wb.close();

			return listaRespuestaBancos;
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
			return null;
		}

	}

	private String getValorCelda(HSSFCell celda) {
		return ManejadorDeInterpretesRespuestaBanco.getValorCelda(celda);
	}

	public Boolean validarArchivoExcel(String rutaArchivo, String id_banco) {
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

	// ****************************************************************************************

	public Boolean validarArchivoHTML(String rutaArchivo, String id_banco) {
		try {
			File file = new File(rutaArchivo);
			List<String> lineas = new ArrayList<String>();
			lineas = FileUtils.readLines(file);
			for (String line : lineas) {
				if (line.contains("<STRONG>Motivo Rechazo</STRONG>")) {
					return true;
				}
			}
			return false;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public List<RespuestaBanco> obtenerRespuestasPorBancoHTML(String rutaArchivo, String id_banco) {
		File input = new File(rutaArchivo);
		ArrayList<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();
		List<String> info = formatearArchivo(input);

		Integer filaArchivo = 15;

		Set<String> retiros_encontrados = new HashSet<String>();

		for (int i = 0; i < info.size(); i++) {
			if (info.get(i).contains("INICIO")) {
				i++;
				String num_doc = StringUtils.trimToEmpty(info.get(i++));
				i++;
				i++;
				String num_cuenta = StringUtils.trimToEmpty(info.get(i++));
				i++;
				String valor = StringUtils.trimToEmpty(info.get(i++)).replace(",", ".").replace(".00", "");
				String estado_respuesta = StringUtils.trimToEmpty(info.get(i));
				String estadoBanco = estado_respuesta;

				System.out.println(estado_respuesta + "," + num_cuenta + "," + num_doc + "," + valor);

				if (!StringUtils.esVacio(estado_respuesta, num_cuenta, num_doc, valor)) {
					RegistroEstado registroestado = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerIDRetiroParaActualizarRespuestaBanco(id_banco, null, num_doc, num_cuenta, valor, retiros_encontrados);
					listaRespuestaBancos.add(ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestaBanco(registroestado, estadoBanco, id_banco, filaArchivo, num_cuenta, num_doc, valor));
				}

				filaArchivo++;
			}

		}

		return listaRespuestaBancos;
	}

	private static List<String> formatearArchivo(File file) {
		int inicio = 0;
		int lineafinal = 0;
		List<String> lineas = new ArrayList<String>();
		try {
			List<String> lines = FileUtils.readLines(file);
			for (String line : lines) {
				if (line.contains("Motivo Rechazo")) {
					inicio = lines.indexOf(line);
				}
				if (line.contains("</table>")) {
					lineafinal = lines.indexOf(line);
				}

			}

			for (int i = 0; i < 10; i++)
				lines.remove(lines.size() - 1);

			for (int i = 0; i <= inicio + 2; i++)
				lines.remove(0);

			for (String line : lines) {
				if (line.contains("<tr>"))
					line = line.replace("<tr>", "INICIO");
				if (line.contains("</tr>"))
					line = line.replace("</tr>", "FIN");
				if (line.contains("<td nowrap>"))
					line = line.replace("<td nowrap>", "");
				if (line.contains("<td>"))
					line = line.replace("<td>", "");
				if (line.contains("<td align=\"right\" nowrap>"))
					line = line.replace("<td align=\"right\" nowrap>", "");
				if (line.contains("</td>")) {
					line = line.replace("</td>", "");
				}

				lineas.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return lineas;
	}

	// *******************************************************************************************************

	public Boolean validarArchivoExcel2(String rutaArchivo, String id_banco) {
		try {
			Boolean esArchivoValido = false;

			InputStream inp = new FileInputStream(rutaArchivo);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			HSSFSheet hoja1 = wb.getSheetAt(0);

			// Se obtiene la fila del encabezado
			HSSFRow fila = hoja1.getRow(13); // fila encabezado

			HSSFCell celdaEstadoRespuesta = fila.getCell(6);
			String titulo_estado_respuesta = getValorCelda(celdaEstadoRespuesta);

			if (titulo_estado_respuesta.trim().equals("Estado")) {
				esArchivoValido = true;
			}

			wb.close();

			return esArchivoValido;

		} catch (Exception e) {

			SimpleLogger.setError("Error", e);
			return false;
		}
	}

	public List<RespuestaBanco> obtenerRespuestasPorBancoExcel2(String rutaArchivo, String id_banco) {

		try {

			InputStream inp = new FileInputStream(rutaArchivo);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			HSSFSheet hoja1 = wb.getSheetAt(0);

			// Listado de respuestaBanco
			ArrayList<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();

			Set<String> retiros_encontrados = new HashSet<String>();

			for (short i = 14; i <= hoja1.getLastRowNum(); i++) {
				try {

					HSSFRow fila = hoja1.getRow(i);
					Integer filaArchivo = (i + 1);

					HSSFCell celdaEstadoRespuesta = fila.getCell(6);
					String estado_respuesta = getValorCelda(celdaEstadoRespuesta);

					HSSFCell celdaCuenta = fila.getCell(3);
					String num_cuenta = getValorCelda(celdaCuenta);

					HSSFCell celdaDocBeneficiario = fila.getCell(0);
					String num_doc = getValorCelda(celdaDocBeneficiario);

					HSSFCell celdaValor = fila.getCell(5);
					String valor = getValorCelda(celdaValor).replace("$", "").replace(" ", "").replace(".", "").replace(",", ".").replace(".00", "");

					String estadoBanco = estado_respuesta;

					System.out.println(estado_respuesta + "," + num_cuenta + "," + num_doc + "," + valor);

					if (!StringUtils.esVacio(estado_respuesta, num_cuenta, num_doc, valor)) {
						RegistroEstado registroestado = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerIDRetiroParaActualizarRespuestaBanco(id_banco, null, num_doc, num_cuenta, valor, retiros_encontrados);
						listaRespuestaBancos.add(ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestaBanco(registroestado, estadoBanco, id_banco, filaArchivo, num_cuenta, num_doc, valor));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			wb.close();

			return listaRespuestaBancos;
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
			return null;
		}

	}

}

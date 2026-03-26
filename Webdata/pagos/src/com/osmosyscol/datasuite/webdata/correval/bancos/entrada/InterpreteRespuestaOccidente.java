package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.osmosyscol.commons.log.SimpleLogger;

public class InterpreteRespuestaOccidente implements IInterpreteRespuestaBanco {

	private Integer COLUMNA_ESTADO_RESPUESTA = null;
	private Integer FILA_INICIO_RESPUESTAS = null;

	private Integer COLUMNA_NUM_DOC_BENEFICIARIO = null;
	private Integer COLUMNA_NUM_CUENTA_BENEFICIARIO = null;
	private Integer COLUMNA_VALOR = null;

	// HEADER
	private Short FILA_HEADER = null;
	private static String TITULO_ESTADO_RESPUESTA = "Estado de Pago";
	private static String TITULO_DOC_BENEFICIARIO = "Nit Beneficiario";
	private static String TITULO_VALOR_PAGADO = "Vr. Pago";
	private static String TITULO_CUENTA_BENEFICIARIO = "No. Producto Destino";

	// VALIDAICON
	private static short NUMERO_CELDAS_PARA_VERIFIACION = 3;

	private static String nombreBanco = "Occidente";

	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {

		try {

			InputStream inp = new FileInputStream(rutaArchivo);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			HSSFSheet hoja1 = wb.getSheetAt(0);

			// Listado de respuestaBanco
			ArrayList<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();

			// Se busca la fila de inicio de respuestas
			FILA_HEADER = buscarFilaEncabezado(hoja1);

			if (FILA_HEADER != null) {

				FILA_INICIO_RESPUESTAS = (FILA_HEADER + 1);

				Set<String> retiros_encontrados = new HashSet<String>();

				for (short i = FILA_INICIO_RESPUESTAS.shortValue(); i <= hoja1.getLastRowNum(); i++) {
					try {

						HSSFRow fila = hoja1.getRow(i);
						Integer filaArchivo = (i + 1);
						// Se verifica que ningun valor sea null
						if (COLUMNA_ESTADO_RESPUESTA != null && COLUMNA_NUM_CUENTA_BENEFICIARIO != null && COLUMNA_NUM_DOC_BENEFICIARIO != null && COLUMNA_VALOR != null) {

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

						} else {
							SimpleLogger.setError("Alguno de los valores buscado es vacio");
						}

					} catch (Exception e) {

						SimpleLogger.setError("Ha ocurrrido un error", e);
					}

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

			// Se busca la fila de encabezado
			FILA_HEADER = buscarFilaEncabezado(hoja1);

			if (FILA_HEADER != null) {
				esArchivoValido = true;

			}

			wb.close();

			return esArchivoValido;

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	private Short buscarFilaEncabezado(HSSFSheet hoja) {

		Short filaEncabezado = null;
		Boolean filaEncontrada = false;

		for (short i = 0; i < hoja.getLastRowNum(); i++) {

			HSSFRow fila = hoja.getRow(i);

			Iterator iterador = fila.cellIterator();

			int contador = 0;
			while (iterador.hasNext()) {
				contador++;
				iterador.next();
			}

			if (contador >= NUMERO_CELDAS_PARA_VERIFIACION && !filaEncontrada) {

				COLUMNA_ESTADO_RESPUESTA = getNumeroCeldaPorTitulo(fila, TITULO_ESTADO_RESPUESTA);
				COLUMNA_NUM_CUENTA_BENEFICIARIO = getNumeroCeldaPorTitulo(fila, TITULO_CUENTA_BENEFICIARIO);
				COLUMNA_NUM_DOC_BENEFICIARIO = getNumeroCeldaPorTitulo(fila, TITULO_DOC_BENEFICIARIO);
				COLUMNA_VALOR = getNumeroCeldaPorTitulo(fila, TITULO_VALOR_PAGADO);

				// Se verifica que ningun valor sea null
				if (COLUMNA_ESTADO_RESPUESTA != null && COLUMNA_NUM_CUENTA_BENEFICIARIO != null && COLUMNA_NUM_DOC_BENEFICIARIO != null && COLUMNA_VALOR != null && !filaEncontrada) {

					SimpleLogger.setDebug("Fila encabezado = " + i);
					filaEncabezado = i;
					filaEncontrada = true;
				}
			}

		}

		return filaEncabezado;
	}

	@SuppressWarnings("rawtypes")
	private Integer getNumeroCeldaPorTitulo(HSSFRow fila, String titulo_celda) {

		Integer posicionCelda = null;

		Iterator iterador = fila.cellIterator();

		while (iterador.hasNext()) {
			HSSFCell celda = (HSSFCell) iterador.next();
			String titulo = getValorCelda(celda);

			if (titulo.trim().toUpperCase().equals(titulo_celda.trim().toUpperCase())) {

				posicionCelda = celda.getColumnIndex();
			}

		}

		return posicionCelda;

	}

	public String getNombreBanco() {
		return nombreBanco;
	}

}

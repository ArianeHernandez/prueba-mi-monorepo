package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.osmosyscol.commons.utils.StringUtils;

public class InterpreteRespuestaFalabella implements IInterpreteRespuestaBanco {

	private static String nombreBanco = "Falabella";

	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {

		// Listado de respuestaBanco
		List<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();

		Set<String> retiros_encontrados = new HashSet<String>();

		try {
			XSSFWorkbook a = new XSSFWorkbook(new File(rutaArchivo));
			XSSFSheet sheet = a.getSheetAt(0);

			for (short i = 8; i <= sheet.getLastRowNum(); i++) {
				XSSFRow r = sheet.getRow(i);
				Integer filaArchivo = (i + 1);

				String estadoBanco = getValorCelda(r.getCell(8));
				String num_doc = getValorCelda(r.getCell(6));
				String valor = getValorCelda(r.getCell(7));
				valor = valor.replace("$", "").replace(".", "").replace(",", ".").replace(" ", "").replace(".00", "");
				
				String motivoRechazo = getValorCelda(r.getCell(9));
				
				String num_cuenta = getValorCelda(r.getCell(2));
				
				if(!num_cuenta.isEmpty()){
					RegistroEstado registroestado = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerIDRetiroParaActualizarRespuestaBanco(id_banco, null, num_doc, num_cuenta, valor, retiros_encontrados);
					
					RespuestaBanco respuestaB = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestaBanco(registroestado, estadoBanco, id_banco, filaArchivo, num_cuenta, num_doc, valor);
					
					if ( respuestaB.getEstado_respuesta().toUpperCase().contains("RECHAZADO") && !motivoRechazo.isEmpty()) {
						respuestaB.setEstado_respuesta(respuestaB.getEstado_respuesta()+ ", " + motivoRechazo);
					}
					listaRespuestaBancos.add(respuestaB);
					
				}
			}
			
			a.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRespuestaBancos;
	}

	private String getValorCelda(XSSFCell xssfCell) {
		return ManejadorDeInterpretesRespuestaBanco.getValorCelda(xssfCell);
	}

	public Boolean validarArchivo(String rutaArchivo, String id_banco) {

		try {
			XSSFWorkbook a = new XSSFWorkbook(new File(rutaArchivo));

			try {
				XSSFSheet sheet = a.getSheetAt(0);

				// verifica la primera fila
				XSSFRow r = sheet.getRow(7);

				if (!StringUtils.trimToEmpty(r.getCell(0).getStringCellValue()).toLowerCase().equals("banco")) {
					return false;
				}

				if (!StringUtils.trimToEmpty(r.getCell(1).getStringCellValue()).toLowerCase().equals("tipo destino")) {
					return false;
				}

				if (!StringUtils.trimToEmpty(r.getCell(7).getStringCellValue()).toLowerCase().equals("valor")) {
					return false;
				}

				if (!StringUtils.trimToEmpty(r.getCell(8).getStringCellValue()).toLowerCase().equals("estado del pago")) {
					return false;
				}
				
				if (!StringUtils.trimToEmpty(r.getCell(9).getStringCellValue()).toLowerCase().equals("motivo de rechazo")) {
					return false;
				}
				
				return true;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				a.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}
}

package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;

public class InterpreteRespuestaBancoCajaSocial implements IInterpreteRespuestaBanco {
	private static short  COLUMNA_NOMBRE_TERCERO = 2;

	private static String nombreBanco = "Caja Social BCSC";

	private static short COLUMNA_NUM_DOC_BENEFICIARIO = 3;
	private static short COLUMNA_NUM_CUENTA_BENEFICIARIO = 6;
	private static short COLUMNA_VALOR = 7;
	private static short COLUMNA_ESTADO_RESPUESTA = 1;
	private static short FILA_INICIO_RESPUESTAS = 2;
	private static short FILA_HEADER = 1;
	// HEADER
	
	
	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {
		
		
		try {
			
			Workbook wb= ManejadorDeInterpretesRespuestaBanco.getInstance().leerWorkbook(rutaArchivo);
			
			if(wb==null) {
				return null;
			}
			
			Sheet sheet = wb.getSheetAt(0);
			ArrayList<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();

			Set<String> retiros_encontrados = new HashSet<String>();
		

			for(int i=FILA_INICIO_RESPUESTAS; i<=sheet.getLastRowNum();i++) {
				
				Row fila= sheet.getRow(i);
				Integer filaArchivo = (i + 1);
		
		
				String estado_respuesta = getValorCelda(fila.getCell(COLUMNA_ESTADO_RESPUESTA));
				String valor_cedula= getValorCelda(fila.getCell(COLUMNA_NUM_DOC_BENEFICIARIO));
				String num_cuenta= getValorCelda(fila.getCell(COLUMNA_NUM_CUENTA_BENEFICIARIO));
				
				String[] parts = valor_cedula.split(" ");
				String documento_beneficiario= "";
				if(parts.length>1) {
					documento_beneficiario=  parts[1];
				}
				String valor= getValorCelda(fila.getCell(COLUMNA_VALOR));
				String estadoBanco = estado_respuesta;
				
				RegistroEstado registroestado = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerIDRetiroParaActualizarRespuestaBanco(id_banco, null, documento_beneficiario , num_cuenta, valor, retiros_encontrados);
				listaRespuestaBancos.add(ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestaBanco(registroestado, estadoBanco, id_banco, filaArchivo, num_cuenta, documento_beneficiario, valor));
				}
			wb.close();
			return listaRespuestaBancos;
			
		} catch (Exception e) {
			SimpleLogger.setError("error en obtenerRespuestasPorBanco",e);
			return null;
		}
		
		
	}
	
	
	private String getValorCelda(Cell Cell) {
		return ManejadorDeInterpretesRespuestaBanco.getValorCelda(Cell);
	}
	
	
	
	public Boolean validarArchivo(String rutaArchivo, String id_banco) {
		
		try {
			Workbook wb = ManejadorDeInterpretesRespuestaBanco.getInstance().leerWorkbook(rutaArchivo);
		
			try {
				
				
				if(wb == null) {
					return false;
				}
				
				Sheet sheet = wb.getSheetAt(0);
	
				// verifica la primera fila
				Row r = sheet.getRow(FILA_HEADER);
			
				if (!StringUtils.trimToEmpty(r.getCell(COLUMNA_ESTADO_RESPUESTA).getStringCellValue()).toLowerCase().equals("estado")) {
					return false;
				}
				
				if (!StringUtils.trimToEmpty(r.getCell(COLUMNA_NUM_CUENTA_BENEFICIARIO).getStringCellValue()).toLowerCase().equals("numero cuenta")) {
					return false;
				}
				
		
				if (!StringUtils.trimToEmpty(r.getCell(COLUMNA_NUM_DOC_BENEFICIARIO).getStringCellValue()).toLowerCase().equals("identificacion tercero")) {
					return false;
				}
				
				
				if (!StringUtils.trimToEmpty(r.getCell(COLUMNA_VALOR).getStringCellValue()).toLowerCase().equals("valor")) {
					return false;
				}
				
				return true;
				
					
			  } catch (Exception e) {
					SimpleLogger.setError("Error", e);
			  }finally{
				wb.close();
			  };
		  
		}catch(Exception e) {
			SimpleLogger.setError("Error", e);
		}
		
		return false;
			
			
	}
	
	public String getNombreBanco() { 
		return nombreBanco;
	}
	
}

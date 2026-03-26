package com.osmosyscol.datasuite.webdata.correval.cargaexcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class CargaExcelServicio {

	private static CargaExcelServicio instance;
	
	private Integer idCargue = 1;
	
	private Map<Integer, Object> mapaRespuestas = new ConcurrentHashMap<Integer, Object>();
	
	public static String CARGUE_CLIENTES = "1";

	public static CargaExcelServicio getInstance() {
		if (instance == null) {
			instance = new CargaExcelServicio();
		}
		return instance;
	}
	
	
	public Integer iniciarCargaArchivo(String ruta, String idTipo){
		
		idCargue++;
		
		mapaRespuestas.put(idCargue, "Iniciando cargue de archivo");
		
		HiloCargueMasivo hilo = new HiloCargueMasivo(ruta, idCargue, idTipo);
		hilo.start();
		
		return idCargue;
	}
	
	private static List<Cargador> cargadores = new ArrayList<Cargador>();
	
	static{
		cargadores.add(new CargadorClientes());
		cargadores.add(new CargadorCuentas());
		cargadores.add(new CargadorProductos());
	}
	
	public void cargar(String ruta, Integer idCargue, String idTipo) {
		
		for (Cargador cargador : cargadores) {
			if (cargador.getCodigo().equals(idTipo)) {
				cargador.cargar(ruta, idCargue, mapaRespuestas);
				return;
			}
		}
		
	}
	
	public Object obtenerRespuestas(Integer idCargue){
		if (mapaRespuestas.get(idCargue) != null && mapaRespuestas.get(idCargue) instanceof Map) {
			return mapaRespuestas.remove(idCargue);
		}
		
		return mapaRespuestas.get(idCargue);
	}

	public String[][] excelToBuffer(HSSFSheet sheet, Integer columnas) {
		Integer filas = sheet.getLastRowNum();
		if (filas == 0) {
			return null;
		}
		String[][] buffer = new String[filas + 1][columnas];

		for (int i = 0; i <= filas; i++) {
			HSSFRow fila = sheet.getRow(i);
			if (fila != null) {
				for (int j = 0; j < columnas; j++) {
					HSSFCell celda = fila.getCell((short) j);
					if (celda != null) {
						Integer tipo = celda.getCellType();
						if(tipo == HSSFCell.CELL_TYPE_STRING){
							buffer[i][j] = celda.getRichStringCellValue().toString().trim();
						}
						else if (tipo == HSSFCell.CELL_TYPE_NUMERIC){
							buffer[i][j] = ""+ (long)celda.getNumericCellValue();
						}
					}
				}
			}
		}
		return buffer;
	}
	
	public static Integer leerTipoDocumento(String dato){
		if ("CEDULA CIUDADANIA".equals(dato)) {
			return 1;
		} 
		if ("NIT".equals(dato)) {
			return 10;
		} 
		if ("CEDULA EXTRANJERIA".equals(dato)) {
			return 3;
		}
		if ("PASAPORTE".equals(dato)) {
			return 4;
		}
		if ("TARJETA IDENTIDAD".equals(dato)) {
			return 2;
		}
		if ("OTRO".equals(dato)) {
			return 20;
		}
		return null;
	}

}

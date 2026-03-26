package com.osmosyscol.datasuite.logica.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.excel.Celda;
import com.osmosyscol.datasuite.logica.dto.excel.Excel;
import com.osmosyscol.datasuite.logica.dto.excel.Fila;
import com.osmosyscol.datasuite.logica.dto.excel.HojaExcel;

public class ExcelServicio {

	private static ExcelServicio excelServicio;

	public static ExcelServicio getInstance() {
		if (excelServicio == null)
			excelServicio = new ExcelServicio();
		return excelServicio;
	}

	// -----------------------------------------------------------

	public Excel crearExcel(String archivoRuta) {

		Excel hojaExcel = new Excel();

		try {
			InputStream inp = new FileInputStream(archivoRuta);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			hojaExcel.setNombre(new File(archivoRuta).getName());

			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				hojaExcel.getHojas().add(crearHoja(wb.getSheetName(i), wb.getSheetAt(i)));
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

		return hojaExcel;
	}

	private HojaExcel crearHoja(String nombre, HSSFSheet sheetAt) {

		HojaExcel hojaExcel = new HojaExcel();
		hojaExcel.setNombre(nombre);

		for (int i = 0; i < sheetAt.getLastRowNum(); i++) {
			hojaExcel.getFilas().add(crearFila(i + 1, sheetAt.getRow(i), sheetAt));
		}

		return hojaExcel;
	}

	private Fila crearFila(Integer numero, HSSFRow row, HSSFSheet sheetAt) {

		Fila fila = new Fila();
		fila.setNumero(numero);

		for (short i = 0; i < row.getLastCellNum(); i++) {

			if (!sheetAt.isColumnHidden(i)) {
				HSSFCell cell = row.getCell((short) (i));
				if (cell != null) {
					fila.getColumnas().add(crearCelda(i + 1, cell));
				}
			}
		}

		return fila;
	}

	private Celda crearCelda(Integer numero, HSSFCell cell) {

		Celda celda = new Celda();

		celda.setNumero(numero);

		Object valor = null;

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:

			valor = cell.getRichStringCellValue().toString();
			try {
				valor = new Double(cell.getNumericCellValue());
			} catch (Exception e) {
			}

			try {
				valor = new Boolean(cell.getBooleanCellValue());
			} catch (Exception e) {
			}

			break;

		case HSSFCell.CELL_TYPE_STRING:

			valor = cell.getRichStringCellValue().toString();
			break;

		case HSSFCell.CELL_TYPE_NUMERIC:
			Double a = new Double(cell.getNumericCellValue()); 
			DecimalFormat formateador = new DecimalFormat("#");
			valor = formateador.format(a);
			//valor = new Double(cell.getNumericCellValue());
			break;

		case HSSFCell.CELL_TYPE_BOOLEAN:

			valor = cell.getBooleanCellValue();
			break;

		case HSSFCell.CELL_TYPE_BLANK:
			break;
		default:
			break;
		}

		celda.setValor(valor);

		return celda;
	}

}

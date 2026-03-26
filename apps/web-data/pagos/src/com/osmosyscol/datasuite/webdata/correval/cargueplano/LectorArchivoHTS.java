package com.osmosyscol.datasuite.webdata.correval.cargueplano;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorUtils;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;
import com.osmosyscol.datasuite.webdata.utils.DocumentoUtils;

public class LectorArchivoHTS implements LectorArchivoPlano {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);

	boolean regitrosOk = true;

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("HTS");
	}

	public List<Retiro> leerRetiros(String archivo) {
		// archivo = "C:/Users/hts/Desktop/CargaMasivaTerceros.xlsx";
		try {
			File myExcel = new File(archivo);
			FileInputStream fist = new FileInputStream(myExcel);
			XSSFWorkbook wbook = new XSSFWorkbook(fist);
			XSSFSheet wsheet = wbook.getSheetAt(0);

			// int num = wsheet.getLastRowNum();

			List<Retiro> retiros = new ArrayList<Retiro>();

			Iterator<Row> iteraSheet = wsheet.iterator();
			Row currentRow = iteraSheet.next();

			while (iteraSheet.hasNext() && regitrosOk) {

				currentRow = iteraSheet.next();
				Retiro retiro = convRowARetiro(currentRow);
				if (retiro != null) {
					retiros.add(retiro);
				}
			}

			wbook.close();
			fist.close();

			if (regitrosOk) {
				return retiros;
			}

		} catch (FileNotFoundException e) {
			SimpleLogger.setError("Ha ocurrido un error al intentar leer el archivo", e);
			e.printStackTrace();
		} catch (IOException e) {
			SimpleLogger.setError("Ha ocurrido un error durante la lectura del archivo", e);
			e.printStackTrace();
		}

		return null;
	}

	private Retiro convRowARetiro(Row currentRow) {

		Retiro retiro = new Retiro();

		String tipoDocTitular = getValueCell(currentRow.getCell(0)).trim();
		String nroDocTitular = getValueCell(currentRow.getCell(1)).trim();
		String nombreTitular = getValueCell(currentRow.getCell(2)).trim();
		String tipoCuenta = getValueCell(currentRow.getCell(3)).trim();
		String codigoBanco = getValueCell(currentRow.getCell(4)).trim();
		String nroCuenta = getValueCell(currentRow.getCell(5)).trim();
		String valorGiro = getValueCell(currentRow.getCell(6)).trim();
		String concepto = getValueCell(currentRow.getCell(7)).trim();
		String correoElectronico = getValueCell(currentRow.getCell(8)).trim();

		// valida si toda la fila esta vacia
		if (StringUtils.isBlank(tipoDocTitular) //
				&& StringUtils.isBlank(nroDocTitular) //
				&& StringUtils.isBlank(nombreTitular) //
				&& StringUtils.isBlank(tipoCuenta) //
				&& StringUtils.isBlank(codigoBanco) //
				&& StringUtils.isBlank(nroCuenta) //
				&& StringUtils.isBlank(valorGiro) //
				&& StringUtils.isBlank(concepto) //
				&& StringUtils.isBlank(correoElectronico)) {

			return null;
		}

		// valida si la fila tiene todos los campos obligatorios.
		if (StringUtils.isBlank(tipoDocTitular) //
				|| StringUtils.isBlank(nroDocTitular) //
				|| StringUtils.isBlank(nombreTitular) //
				|| StringUtils.isBlank(tipoCuenta) //
				|| StringUtils.isBlank(codigoBanco) //
				|| StringUtils.isBlank(nroCuenta) //
				|| StringUtils.isBlank(valorGiro)) {

			regitrosOk = false;
			
			return null;
		}

		retiro.setNombre_beneficiario(nombreTitular);
		
		if(StringUtils.isNumeric(nroDocTitular)) {
			retiro.setIdentificacion_beneficiario(DocumentoUtils.ajustarNumeroDocumento(nroDocTitular));
		}else {
			retiro.setIdentificacion_beneficiario(null);
		}
		
		retiro.setTipo_documento_beneficiario(gu.getTipoDocumentoInterno(tipoDocTitular));

		if (codigoBanco != null && codigoBanco != "") {
			// limpiar 0s a la izquierda
			String codBancoLeido = new Long(codigoBanco).toString();

			String codBanco = gu.getCodBancoInterno(codBancoLeido);

			if (StringUtils.esNoVacio(codBanco)) {
				retiro.setCod_banco(codBanco);
			} else {
				retiro.setCod_banco(codBancoLeido);
			}
		}

		BigDecimal valor = new BigDecimal(valorGiro);
		retiro.setValor(valor);

		if(StringUtils.isNumeric(nroCuenta)) {
			retiro.setCuenta_beneficiario(gu.ajustarLongitudCuenta(nroCuenta, retiro.getCod_banco()));
		}else {
			retiro.setCuenta_beneficiario(null);
		}
		

		retiro.setObservacion(concepto);

		retiro.setTipo_cuenta_beneficiario_banco(tipoCuenta.trim());

		String tipoCuentaString = gu.getTipoCuentaInterno(tipoCuenta);
		Long tipoCuentaLong = (tipoCuentaString != null ? new Long(tipoCuentaString) : null);
		retiro.setTipo_cuenta_beneficiario(tipoCuentaLong);

		retiro.setCorreo_beneficiario(correoElectronico.trim());

		return retiro;
	}

	@SuppressWarnings("deprecation")
	public static String getValueCell(Cell cell) {
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				return "";
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() + "";
			case Cell.CELL_TYPE_ERROR:
				return cell.getErrorCellValue() + "";
			case Cell.CELL_TYPE_FORMULA:
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_NUMERIC:
				return new java.text.DecimalFormat("#.00").format(cell.getNumericCellValue()).replace(',', '.').replace(".00", "");
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			default:
				return "";
			}
		} else {
			return "";
		}
	}

}

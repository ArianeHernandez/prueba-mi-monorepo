package com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.SalidaCoreCorreval;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class GeneradorORIONSociedadComisionistaACH implements IGeneradorSalidaSistemaCorreval {

	private String tipo_archivo = "SCB-ACH-ORION";
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	

	// -----------------------------------------------------

	public String getTipo_arhivo() {
		 
		return tipo_archivo;
	}

	// -----------------------------------------------------

	public File generar(List<SalidaCoreCorreval> listaSalidaSistemaCorreval) {

		try{
			
			String nombrecarpeta = ParametrosInicio.getProperty("file.carpeta") + "/SALIDA_CORE";
			new File(nombrecarpeta).mkdirs();
			String nombrearchivo = nombrecarpeta + "/" +tipo_archivo+".xls";
			SimpleLogger.setInfo("Creando Archivo: " + nombrearchivo);
			
			
			//Si el archivo existe previamente se elimina el archivo
			File file_temp =  new File(nombrearchivo);
			if(file_temp.exists()){
				file_temp.delete();
			}
			
			
			HSSFWorkbook wb = new HSSFWorkbook();
			
			HSSFCellStyle styleCelda = wb.createCellStyle();
			styleCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleCelda.setWrapText(true);
			styleCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleCelda.setBorderBottom((short) 1);
			styleCelda.setBorderLeft((short) 1);
			styleCelda.setBorderRight((short) 1);
			styleCelda.setBorderTop((short) 1);
	
			SimpleLogger.setInfo("Creando hoja: ");
			HSSFSheet newsheet = wb.createSheet(tipo_archivo);
			newsheet.setDefaultColumnWidth((short) 10);
			
			//Se crea el encabezado del archivo
			HSSFRow rowc0 = newsheet.createRow((short) 0);
			rowc0.setHeight((short) 500);
	
			HSSFCell ccel0 = rowc0.createCell((short) 0);
			ccel0.setCellValue(new HSSFRichTextString("Descripción del Negocio"));
			ccel0.setCellStyle(styleCelda);
			
	
			HSSFCell ccel1 = rowc0.createCell((short) 1);
			ccel1.setCellValue(new HSSFRichTextString("Descripción del Producto"));
			ccel1.setCellStyle(styleCelda);
			
			HSSFCell ccel2 = rowc0.createCell((short) 2);
			ccel2.setCellValue(new HSSFRichTextString("Identificación del Cliente"));
			ccel2.setCellStyle(styleCelda);
			
			HSSFCell ccel3 = rowc0.createCell((short) 3);
			ccel3.setCellValue(new HSSFRichTextString("Identificación Digito de Verificación"));
			ccel3.setCellStyle(styleCelda);
			
			HSSFCell ccel4 = rowc0.createCell((short) 4);
			ccel4.setCellValue(new HSSFRichTextString("Nombre del Cliente"));
			ccel4.setCellStyle(styleCelda);
			
			HSSFCell ccel5 = rowc0.createCell((short) 5);
			ccel5.setCellValue(new HSSFRichTextString("Lote Origen"));
			ccel5.setCellStyle(styleCelda);
			
			HSSFCell ccel6 = rowc0.createCell((short) 6);
			ccel6.setCellValue(new HSSFRichTextString("Valor de Retiro"));
			ccel6.setCellStyle(styleCelda);
			
			HSSFCell ccel7 = rowc0.createCell((short) 7);
			ccel7.setCellValue(new HSSFRichTextString("Identificacion del Beneficiario"));
			ccel7.setCellStyle(styleCelda);
			
			HSSFCell ccel8 = rowc0.createCell((short) 8);
			ccel8.setCellValue(new HSSFRichTextString("Nombre del Beneficiario"));
			ccel8.setCellStyle(styleCelda);
			
			HSSFCell ccel9 = rowc0.createCell((short) 9);
			ccel9.setCellValue(new HSSFRichTextString("Nombre Banco Destino"));
			ccel9.setCellStyle(styleCelda);
			
			HSSFCell ccel10 = rowc0.createCell((short) 10);
			ccel10.setCellValue(new HSSFRichTextString("Numero Cuenta Destino"));
			ccel10.setCellStyle(styleCelda);
			
			HSSFCell ccel11 = rowc0.createCell((short) 11);
			ccel11.setCellValue(new HSSFRichTextString("Tipo Cuenta Destino"));
			ccel11.setCellStyle(styleCelda);
			
			HSSFCell ccel12 = rowc0.createCell((short) 12);
			ccel12.setCellValue(new HSSFRichTextString("Nombre Banco Origen"));
			ccel12.setCellStyle(styleCelda);
			
			HSSFCell ccel13 = rowc0.createCell((short) 13);
			ccel13.setCellValue(new HSSFRichTextString("Numero Cuenta Origen"));
			ccel13.setCellStyle(styleCelda);
			
			HSSFCell ccel14 = rowc0.createCell((short) 14);
			ccel14.setCellValue(new HSSFRichTextString("Tipo Cuenta Origen"));
			ccel14.setCellStyle(styleCelda);
			
			HSSFCell ccel15 = rowc0.createCell((short) 15);
			ccel15.setCellValue(new HSSFRichTextString("Código Contable Cuenta Origen"));
			ccel15.setCellStyle(styleCelda);
			
			HSSFCell ccel16 = rowc0.createCell((short) 16);
			ccel16.setCellValue(new HSSFRichTextString("Fecha de liberacion Lote Origen"));
			ccel16.setCellStyle(styleCelda);
			
			HSSFCell ccel17 = rowc0.createCell((short) 17);
			ccel17.setCellValue(new HSSFRichTextString("Nombre Usuario Correval que recibio el Lote"));
			ccel17.setCellStyle(styleCelda);
			
			HSSFCell ccel18 = rowc0.createCell((short) 18);
			ccel18.setCellValue(new HSSFRichTextString("Estado Respuesta Banco"));
			ccel18.setCellStyle(styleCelda);
			
			//Se crea el cuerpo del archivo
			int registro = 1;
			for (SalidaCoreCorreval ssc : listaSalidaSistemaCorreval) {
				
				HSSFRow row = newsheet.createRow((short) registro);
				
				HSSFCell cel0 = row.createCell((short) 0);
				cel0.setCellValue(new HSSFRichTextString(ssc.getDescripcion_negocio()));
				cel0.setCellStyle(styleCelda);
						
				HSSFCell cel1 = row.createCell((short) 1);
				cel1.setCellValue(new HSSFRichTextString(ssc.getDescripcion_producto()));
				cel1.setCellStyle(styleCelda);
				
				HSSFCell cel2 = row.createCell((short) 2);
				cel2.setCellValue(new HSSFRichTextString(ssc.getIdentificacion_cliente()));
				cel2.setCellStyle(styleCelda);
				
				HSSFCell cel3 = row.createCell((short) 3);
				cel3.setCellValue(ssc.getDigito_verificacion_identifiacion_cliente());
				cel3.setCellStyle(styleCelda);
				
				HSSFCell cel4 = row.createCell((short) 4);
				cel4.setCellValue(new HSSFRichTextString(ssc.getNombre_cliente()));
				cel4.setCellStyle(styleCelda);
				
				HSSFCell cel5 = row.createCell((short) 5);
				cel5.setCellValue(new HSSFRichTextString("Lote # "+ssc.getLote_origen()));
				cel5.setCellStyle(styleCelda);
				
				HSSFCell cel6 = row.createCell((short) 6);
				cel6.setCellValue(ssc.getValor_retiro().doubleValue());
				cel6.setCellStyle(styleCelda);
				
				HSSFCell cel7 = row.createCell((short) 7);
				cel7.setCellValue(ssc.getNum_identificacion_beneficiario());
				cel7.setCellStyle(styleCelda);
				
				HSSFCell cel8 = row.createCell((short) 8);
				cel8.setCellValue(new HSSFRichTextString(ssc.getNombre_beneficiario()));
				cel8.setCellStyle(styleCelda);
				
				HSSFCell cel9 = row.createCell((short) 9);
				cel9.setCellValue(new HSSFRichTextString(ssc.getNombre_banco_destino_beneficiario()));
				cel9.setCellStyle(styleCelda);
				
				HSSFCell cel10 = row.createCell((short) 10);
				cel10.setCellValue(new HSSFRichTextString(ssc.getNum_cuenta_destino_beneficiario()));
				cel10.setCellStyle(styleCelda);
				
				HSSFCell cel11 = row.createCell((short) 11);
				cel11.setCellValue(new HSSFRichTextString(ssc.getTipo_cuenta_destino_beneficiario()));
				cel11.setCellStyle(styleCelda);
				
				HSSFCell cel12 = row.createCell((short) 12);
				cel12.setCellValue(new HSSFRichTextString(ssc.getNombre_banco_origen_correval()));
				cel12.setCellStyle(styleCelda);
				
				HSSFCell cel13 = row.createCell((short) 13);
				cel13.setCellValue(new HSSFRichTextString(ssc.getNum_cuenta_origen_correval()));
				cel13.setCellStyle(styleCelda);
				
				HSSFCell cel14 = row.createCell((short) 14);
				cel14.setCellValue(new HSSFRichTextString(ssc.getTipo_cuenta_origen_correval()));
				cel14.setCellStyle(styleCelda);
				
				HSSFCell cel15 = row.createCell((short) 15);
				cel15.setCellValue(new HSSFRichTextString(ssc.getCodigo_contable_cuenta_origen_correval()));
				cel15.setCellStyle(styleCelda);
				
				HSSFCell cel16 = row.createCell((short) 16);
				cel16.setCellValue(new HSSFRichTextString(dateformat.format(ssc.getFecha_liberacion_lote_origen())));
				cel16.setCellStyle(styleCelda);
				
				HSSFCell cel17 = row.createCell((short) 17);
				cel17.setCellValue(new HSSFRichTextString(ssc.getNombre_agente_comercial()));
				cel17.setCellStyle(styleCelda);
				
				HSSFCell cel18 = row.createCell((short) 18);
				cel18.setCellValue(new HSSFRichTextString(ssc.getEstado_retiro()));
				cel18.setCellStyle(styleCelda);
				
				registro++;
			}

			
			FileOutputStream fileOut = new FileOutputStream(nombrearchivo);
	
			wb.write(fileOut);
			fileOut.close();
	
			File file = new File(nombrearchivo);
			
			return file;
		
		}catch (Exception e) {
			SimpleLogger.setError("Error", e);
			return null;
		}
		
	}

}

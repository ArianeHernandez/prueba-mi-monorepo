package com.osmosyscol.datasuite.webdata.correval.servicios.reporte_tipo_negocio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import co.htsoft.commons.lang.P;

import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.utils.ObjectJRDS;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReportePagosServicio.Filtro;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReportePagosServicio.Pago;
import com.osmosyscol.datasuite.webdata.logica.dto.Estado;
import com.osmosyscol.datasuite.webdata.logica.servicios.EstadoCargaServicio;
import com.osmosyscol.datasuite.webdata.servlet.DescargaArchivoServlet;

public class ReporteTipoNegocioService {
	
	public static List<TipoNegocio> consultar(Filtro filtro) {
		
		validarFiltro(filtro);
		
		String sql = "SELECT R.IDCARGA ID_ENCARGO, "
				+ "T.FECHA_LIBERACION FECHA, "
				+ "T.Nombre_cliente NOMBRE_CLIENTE, "
				+ "$PRODUCTO.NOMBRE$ PRODUCTO, "
				+ "$RETIROS.ID CUENTA$ ENCARGO, "
				+ "$RETIROS.VALOR$ SALDO, "
				+ "$LINEA DE PRODUCTO.NOMBRE$ TIPO_NEGOCIO "
				+ "FROM $RETIROS$ R JOIN TS01 T ON R.IDCARGA = T.ID_CARGA "
				+ "JOIN $PRODUCTO$ P ON $RETIROS.PRODUCTO$ = P.ID "
				+ "join $LINEA DE PRODUCTO$ ON $LINEA DE PRODUCTO$.ID = $PRODUCTO.LINEA DE PRODUCTO$ "
				+ "WHERE $LINEA DE PRODUCTO$.ID = NVL( $id_negocio$, $LINEA DE PRODUCTO$.ID ) "
				+ "AND $RETIROS.ID CLIENTE$ = NVL( #id_cliente#, $RETIROS.ID CLIENTE$) "
				+ "AND T.FECHA_LIBERACION IS NOT NULL "
				+ "AND ( T.FECHA_LIBERACION >= NVL( $fecha_desde$, T.FECHA_LIBERACION) and  T.FECHA_LIBERACION <= NVL($fecha_hasta$, T.FECHA_LIBERACION))";

		List<TipoNegocio> pagos = DS_SqlUtils.queryForList(TipoNegocio.class, sql, filtro);

		return pagos;
	}

	public static Integer generarReporte(List<TipoNegocio> tipos_de_negocio) {
		
		List<Estado> estados = EstadoCargaServicio.getInstance().obtenerEstados();
		HashMap<String, String> mapaEstados = new HashMap<String, String>();
		for (Estado estado : estados) {
			mapaEstados.put(estado.getEstado(), estado.getNombre_cliente());
		}
		XSSFWorkbook wb = new XSSFWorkbook();

		XSSFSheet hoja = wb.createSheet("Pagos");

		hoja.setDisplayGridlines(false);

		XSSFCellStyle hstyle = wb.createCellStyle();
		XSSFCellStyle rstyle = wb.createCellStyle();
		XSSFCellStyle datestyle = wb.createCellStyle();
		XSSFCellStyle numstyle = wb.createCellStyle();

		XSSFFont fbold = wb.createFont();
		fbold.setBold(true);

		{

			CreationHelper createHelper = wb.getCreationHelper();

			hstyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
			hstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			hstyle.setBorderBottom(BorderStyle.THIN);
			hstyle.setBorderRight(BorderStyle.THIN);
			hstyle.setBorderTop(BorderStyle.THIN);
			hstyle.setBorderLeft(BorderStyle.THIN);

			hstyle.setAlignment(HorizontalAlignment.CENTER);
			hstyle.setVerticalAlignment(VerticalAlignment.CENTER);
			hstyle.setFont(fbold);
			hstyle.setBorderBottom(BorderStyle.THIN);
			hstyle.setBorderRight(BorderStyle.THIN);
			hstyle.setBorderTop(BorderStyle.THIN);
			hstyle.setBorderLeft(BorderStyle.THIN);

			rstyle.setVerticalAlignment(VerticalAlignment.CENTER);
			rstyle.setBorderBottom(BorderStyle.THIN);
			rstyle.setBorderRight(BorderStyle.THIN);
			rstyle.setBorderTop(BorderStyle.THIN);
			rstyle.setBorderLeft(BorderStyle.THIN);

			datestyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm AM/PM"));
			datestyle.setBorderBottom(BorderStyle.THIN);
			datestyle.setBorderRight(BorderStyle.THIN);
			datestyle.setBorderTop(BorderStyle.THIN);
			datestyle.setBorderLeft(BorderStyle.THIN);

			numstyle.setDataFormat(createHelper.createDataFormat().getFormat("$#,##0.00"));
			numstyle.setBorderBottom(BorderStyle.THIN);
			numstyle.setBorderRight(BorderStyle.THIN);
			numstyle.setBorderTop(BorderStyle.THIN);
			numstyle.setBorderLeft(BorderStyle.THIN);
		}

		XSSFRow fila = hoja.createRow(0);
		XSSFCell c = null;
		{
			c = fila.createCell(0);
			c.setCellValue("ID Encargo");
			c.setCellStyle(hstyle);

			c = fila.createCell(1);
			c.setCellValue("Nombre Cliente");
			c.setCellStyle(hstyle);

			c = fila.createCell(2);
			c.setCellValue("Tipo Negocio");
			c.setCellStyle(hstyle);

			c = fila.createCell(3);
			c.setCellValue("Producto");
			c.setCellStyle(hstyle);

			c = fila.createCell(4);
			c.setCellValue("Encargo");
			c.setCellStyle(hstyle);

			c = fila.createCell(5);
			c.setCellValue("Saldo");
			c.setCellStyle(hstyle);

			c = fila.createCell(6);
			c.setCellValue("Fecha Liberacion");
			c.setCellStyle(hstyle);
			
		}
		// --

		int i = 1;
		for (TipoNegocio tipo_de_negocio : tipos_de_negocio) {

			fila = hoja.createRow(i);
			// fila.setRowStyle(rstyle);

			c = fila.createCell(0);
			c.setCellValue(tipo_de_negocio.getId_encargo());
			c.setCellStyle(rstyle);

			c = fila.createCell(1);
			c.setCellValue(tipo_de_negocio.getNombre_cliente());
			c.setCellStyle(datestyle);

			c = fila.createCell(2);
			c.setCellValue(tipo_de_negocio.getTipo_negocio());
			c.setCellStyle(rstyle);

			c = fila.createCell(3);
			c.setCellValue(tipo_de_negocio.getProducto());
			c.setCellStyle(rstyle);

			c = fila.createCell(4);
			c.setCellValue(tipo_de_negocio.getEncargo());
			c.setCellStyle(rstyle);

			c = fila.createCell(5);
			c.setCellValue(tipo_de_negocio.getSaldo().doubleValue());
			c.setCellStyle(numstyle);

			c = fila.createCell(6);
			c.setCellValue(tipo_de_negocio.getFecha());
			c.setCellStyle(datestyle);

			i++;
		}

		hoja.autoSizeColumn(0);
		hoja.autoSizeColumn(1);
		hoja.autoSizeColumn(2);
		hoja.autoSizeColumn(3);
		hoja.autoSizeColumn(4);
		hoja.autoSizeColumn(5);
		hoja.autoSizeColumn(6);

		try {
			File file = FileUtils.newFile("xlsx");
			FileOutputStream fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			wb.close();

			return DescargaArchivoServlet.almacenarArchivo(file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}
	
	public static void validarFiltro(Filtro filtro){
		if(filtro.getFecha_desde() != null && filtro.getFecha_desde().equals(""))
			filtro.setFecha_desde(null);
		if(filtro.getFecha_hasta() != null && filtro.getFecha_hasta().equals(""))
			filtro.setFecha_hasta(null);
		if(filtro.getId_cliente() != null && filtro.getId_cliente().equals(""))
			filtro.setId_cliente(null);
		if(filtro.getId_negocio() != null && filtro.getId_negocio().equals(""))
			filtro.setId_negocio(null);
		
	}
	

	public static class Filtro {

		private String id_negocio;
		private Integer id_cliente;
		private String fecha_desde;
		private String fecha_hasta;

		public String getFecha_desde() {
			return fecha_desde;
		}

		public void setFecha_desde(String fecha_desde) {
			this.fecha_desde = fecha_desde;
		}

		public String getFecha_hasta() {
			return fecha_hasta;
		}

		public void setFecha_hasta(String fecha_hasta) {
			this.fecha_hasta = fecha_hasta;
		}

		public String getId_negocio() {
			return id_negocio;
		}

		public void setId_negocio(String id_negocio) {
			this.id_negocio = id_negocio;
		}

		public Integer getId_cliente() {
			return id_cliente;
		}

		public void setId_cliente(Integer id_cliente) {
			this.id_cliente = id_cliente;
		}

	}
}



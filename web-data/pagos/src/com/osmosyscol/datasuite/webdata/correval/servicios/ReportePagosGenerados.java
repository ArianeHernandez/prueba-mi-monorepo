package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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

import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

import co.htsoft.commons.file.FileUtils;

public class ReportePagosGenerados {

	public static List<Pago> consultaPagos(Date fechaDesde, Date fechaHasta) {

		List<Banco> bancos = DS_SqlUtils.queryForList(Banco.class, "select id, $banco.nombre$ nombre from $banco$");

		Map<Integer, String> mapa_bancos = new HashMap<Integer, String>();

		for (Banco banco : bancos) {
			mapa_bancos.put(banco.id, banco.nombre);
		}

		// --

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fecha_desde", fechaDesde);
		params.put("fecha_hasta", fechaHasta);

		String sql = "select rr.IDCARGA, " //
				+ " $PRODUCTO.NOMBRE$ PRODUCTO, " // 
				+ " $RETIROS.ID CUENTA$ ENCARGO, " //
				+ " $GRUPO GIRO.FECHA PAGO$ FECHA, " //
				+ " $RETIROS.NUMERO ORDEN$ COMPROBANTE_EGRESO, " //
				+ " A.$ARCHIVO.NOMBRE$ ARCHIVO,  " //
				+ " $RETIROS.VALOR$ VALOR, " //
				+ " $RETIROS.ESTADO DE RESPUESTA BANCO$ ESTADO, " //
				+ " $RETIROS.ID CLIENTE$ ID_USUARIO, " //
				+ " $BENEFICIARIO.BANCO$ ID_BANCO_BENEFICIARIO, " //
				+ " $BENEFICIARIO.NUMERO DE CUENTA$ CUENTA_BENEFICIARIO, " //
				+ " $CUENTA - BANCO.CUENTA BANCARIA$ CUENTA_ORIGEN, " //
				+ " $CUENTA - BANCO.BANCO$ ID_BANCO_ORIGEN " //
				+ " from $GRUPO GIRO$ gg, $RETIROS$ rr, $ARCHIVO$ a, $BENEFICIARIO$ b, $CUENTA - BANCO$ cc, $PRODUCTO$ p" //
				+ " where $GRUPO GIRO.CODIGO REGISTRO$ = rr.id and a.id = $GRUPO GIRO.ARCHIVO$ " //
				+ " AND b.ID = $RETIROS.BENEFICIARIO$ and cc.id = $GRUPO GIRO.CUENTA$ and p.id = $RETIROS.PRODUCTO$" //
				+ " and ( $GRUPO GIRO.FECHA PAGO$ >= #fecha_desde# and  $GRUPO GIRO.FECHA PAGO$ <= #fecha_hasta#)" //
				+ " ORDER BY $GRUPO GIRO.FECHA PAGO$, A.ID, rr.id "; //

		List<Pago> pagos = DS_SqlUtils.queryForList(Pago.class, sql, params);

		if (pagos != null) {
			for (Pago pago : pagos) {
				try {
					Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(pago.getId_usuario());
					pago.setNombre_usuario(usuario.getNombre());
					pago.setDocumento_usuario(usuario.getIdentificacion());

					pago.setNombre_banco_beneficiario(mapa_bancos.get(pago.getId_banco_beneficiario()));
					pago.setNombre_banco_origen(mapa_bancos.get(pago.getId_banco_origen()));

					// --

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		return pagos;

	}

	public static File generarReporte(List<Pago> pagos) {

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

			datestyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
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
			c.setCellValue("ID Carga");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(1);
			c.setCellValue("Fecha");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(2);
			c.setCellValue("Comprobante de Egreso");
			c.setCellStyle(hstyle);

			c = fila.createCell(3);
			c.setCellValue("Producto");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(4);
			c.setCellValue("Encargo");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(5);
			c.setCellValue("Documento Cliente");
			c.setCellStyle(hstyle);

			c = fila.createCell(6);
			c.setCellValue("Nombre Cliente");
			c.setCellStyle(hstyle);

			c = fila.createCell(7);
			c.setCellValue("Banco Origen");
			c.setCellStyle(hstyle);

			c = fila.createCell(8);
			c.setCellValue("Cuenta Origen");
			c.setCellStyle(hstyle);

			c = fila.createCell(9);
			c.setCellValue("Banco Destino");
			c.setCellStyle(hstyle);

			c = fila.createCell(10);
			c.setCellValue("Cuenta Destino");
			c.setCellStyle(hstyle);

			c = fila.createCell(11);
			c.setCellValue("Valor");
			c.setCellStyle(hstyle);

			c = fila.createCell(12);
			c.setCellValue("Estado");
			c.setCellStyle(hstyle);
		}
		// --

		int i = 1;
		for (Pago pago : pagos) {

			fila = hoja.createRow(i);
			// fila.setRowStyle(rstyle);

			c = fila.createCell(0);
			if (pago.getIdcarga() != null){
				c.setCellValue(pago.getIdcarga() );
			}
			c.setCellStyle(rstyle);
			
			c = fila.createCell(1);
			if (pago.getFecha() != null){
				c.setCellValue(pago.getFecha());
			}
			c.setCellStyle(datestyle);
			
			c = fila.createCell(2);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getComprobante_egreso()));
			c.setCellStyle(rstyle);

			c = fila.createCell(3);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getProducto()));
			c.setCellStyle(rstyle);
			
			c = fila.createCell(4);
			if (pago.getEncargo() != null){
				c.setCellValue(pago.getEncargo() );
			}
			c.setCellStyle(rstyle);
			
			c = fila.createCell(5);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getDocumento_usuario()));
			c.setCellStyle(rstyle);

			c = fila.createCell(6);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getNombre_usuario()));
			c.setCellStyle(rstyle);

			c = fila.createCell(7);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getNombre_banco_origen()));
			c.setCellStyle(rstyle);

			c = fila.createCell(8);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getCuenta_origen()));
			c.setCellStyle(rstyle);

			c = fila.createCell(9);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getNombre_banco_beneficiario()));
			c.setCellStyle(rstyle);

			c = fila.createCell(10);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getCuenta_beneficiario()));
			c.setCellStyle(rstyle);

			c = fila.createCell(11);
			if(pago.getValor() != null){
				c.setCellValue(pago.getValor().doubleValue());
			}
			c.setCellStyle(numstyle);

			c = fila.createCell(12);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getEstado()));
			c.setCellStyle(rstyle);

			i++;
		}

		hoja.autoSizeColumn(0);
		hoja.autoSizeColumn(1);
		hoja.autoSizeColumn(2);
		hoja.autoSizeColumn(3);
		hoja.autoSizeColumn(4);
		hoja.autoSizeColumn(5);
		hoja.autoSizeColumn(6);
		hoja.autoSizeColumn(7);
		hoja.autoSizeColumn(8);
		hoja.autoSizeColumn(9);
		hoja.autoSizeColumn(10);
		hoja.autoSizeColumn(11);
		hoja.autoSizeColumn(12);

		try {
			File file = FileUtils.newFile("xlsx");
			FileOutputStream fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			wb.close();

			return file;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public static class Banco {
		private Integer id;
		private String nombre;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
	}

	public static class Pago {

		private Date fecha;
		private String archivo;
		private BigDecimal valor;
		private String estado;
		private Integer id_usuario;
		private Integer id_banco_beneficiario;
		private String cuenta_beneficiario;
		private Integer id_banco_origen;
		private String cuenta_origen;

		// --

		private String documento_usuario;
		private String nombre_usuario;
		private String nombre_banco_beneficiario;
		private String nombre_banco_origen;
		
		// --
		
		private String comprobante_egreso;
		private String producto;
		private Integer encargo;
		private Integer idcarga;
		
		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public String getArchivo() {
			return archivo;
		}

		public void setArchivo(String archivo) {
			this.archivo = archivo;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public Integer getId_usuario() {
			return id_usuario;
		}

		public void setId_usuario(Integer id_usuario) {
			this.id_usuario = id_usuario;
		}

		public Integer getId_banco_beneficiario() {
			return id_banco_beneficiario;
		}

		public void setId_banco_beneficiario(Integer id_banco_beneficiario) {
			this.id_banco_beneficiario = id_banco_beneficiario;
		}

		public String getCuenta_beneficiario() {
			return cuenta_beneficiario;
		}

		public void setCuenta_beneficiario(String cuenta_beneficiario) {
			this.cuenta_beneficiario = cuenta_beneficiario;
		}

		public Integer getId_banco_origen() {
			return id_banco_origen;
		}

		public void setId_banco_origen(Integer id_banco_origen) {
			this.id_banco_origen = id_banco_origen;
		}

		public String getCuenta_origen() {
			return cuenta_origen;
		}

		public void setCuenta_origen(String cuenta_origen) {
			this.cuenta_origen = cuenta_origen;
		}

		public String getDocumento_usuario() {
			return documento_usuario;
		}

		public void setDocumento_usuario(String documento_usuario) {
			this.documento_usuario = documento_usuario;
		}

		public String getNombre_usuario() {
			return nombre_usuario;
		}

		public void setNombre_usuario(String nombre_usuario) {
			this.nombre_usuario = nombre_usuario;
		}

		public String getNombre_banco_beneficiario() {
			return nombre_banco_beneficiario;
		}

		public void setNombre_banco_beneficiario(String nombre_banco_beneficiario) {
			this.nombre_banco_beneficiario = nombre_banco_beneficiario;
		}

		public String getNombre_banco_origen() {
			return nombre_banco_origen;
		}

		public void setNombre_banco_origen(String nombre_banco_origen) {
			this.nombre_banco_origen = nombre_banco_origen;
		}

		public String getComprobante_egreso() {
			return comprobante_egreso;
		}

		public void setComprobante_egreso(String comprobante_egreso) {
			this.comprobante_egreso = comprobante_egreso;
		}

		public String getProducto() {
			return producto;
		}

		public void setProducto(String producto) {
			this.producto = producto;
		}

		public Integer getIdcarga() {
			return idcarga;
		}

		public void setIdcarga(Integer idcarga) {
			this.idcarga = idcarga;
		}

		public Integer getEncargo() {
			return encargo;
		}

		public void setEncargo(Integer encargo) {
			this.encargo = encargo;
		}

	}

}

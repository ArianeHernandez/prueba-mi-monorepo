package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Estado;
import com.osmosyscol.datasuite.webdata.logica.servicios.EstadoCargaServicio;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.lang.P;

public class ReportePagosServicio {

	public static List<Pago> consultaPagos(Filtro filtro) {

		String sql = "SELECT R.IDCARGA ID_CARGA, " //
				+ " T.FECHA_LIBERACION FECHA_LIBERACION, " //
				+ " T.Nombre_cliente NOMBRE_CLIENTE, " //
				+ " $PRODUCTO.NOMBRE$ PRODUCTO, " //
				+ " $RETIROS.ID CUENTA$ ENCARGO, " //
				+ " $RETIROS.VALOR$ VALOR, " //
				+ " T.ESTADO ESTADO, " //
				+ " $RETIROS.ESTADO DE RESPUESTA BANCO$ RESPUESTA_BANCO, " //
				+ " $banco.nombre$ BANCO, " //
				+ " $tipo de cuenta.descripcion$ TIPO_DE_CUENTA, " //
				+ " $BENEFICIARIO.NUMERO DE CUENTA$ NUMERO_CUENTA, " //
				+ " $BENEFICIARIO.PRIMER NOMBRE$ primer_nombre, " //
				+ " $beneficiario.segundo nombre$ segundo_nombre, " //
				+ " $beneficiario.primer apellido$ primer_apellido, " //
				+ " $BENEFICIARIO.SEGUNDO APELLIDO$ segundo_apellido, " //
				+ " $BENEFICIARIO.NUMERO DE DOCUMENTO$ NUMERO_DOCUMENTO, " //
				+ " $BENEFICIARIO.CORREO ELECTRONICO$ CORREO_ELECTRONICO, " //
				+ " $TIPO DE DOCUMENTO.NOMBRE$ TIPO_DOCUMENTO " //
				+ " FROM $RETIROS$ R " //
				+ " LEFT JOIN TS01 T ON R.IDCARGA = T.ID_CARGA " //
				+ " LEFT JOIN $BENEFICIARIO$ B on $RETIROS.BENEFICIARIO$ = B.ID " //
				+ " LEFT JOIN $PRODUCTO$ P ON $RETIROS.PRODUCTO$ = P.ID " //
				+ " LEFT JOIN $BANCO$ BA ON $beneficiario.banco$ = BA.ID " //
				+ " LEFT JOIN $TIPO DE DOCUMENTO$ TD ON TD.ID = $BENEFICIARIO.TIPO DE DOCUMENTO$ " //
				+ " LEFT JOIN $TIPO DE CUENTA$ TC ON TC.ID = $BENEFICIARIO.TIPO DE CUENTA$ " //
				+ " WHERE " //
				+ " T.FECHA_LIBERACION IS NOT NULL " //
				+ " AND ( T.FECHA_LIBERACION >= NVL( $fecha_desde$, T.FECHA_LIBERACION)"
				+ " and  T.FECHA_LIBERACION <= NVL($fecha_hasta$, T.FECHA_LIBERACION)) ";

		if (filtro.getId_carga() != null) {
			sql += "AND R.IDCARGA = $id_carga$ ";//
		}
		if (filtro.getId_cliente() != null) {
			sql += "AND $RETIROS.ID CLIENTE$ = #id_cliente# ";//
		}
		//ordenar datos por id_carga
		sql += " ORDER BY R.IDCARGA ASC"; //
			
		P.println(filtro);
		List<Pago> pagos = DS_SqlUtils.queryForList(Pago.class, sql, filtro);

		return pagos;
	}

	public static File generarReporte(List<Pago> pagos) {

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
			c.setCellValue("ID Carga");
			c.setCellStyle(hstyle);

			c = fila.createCell(1);
			c.setCellValue("Fecha Liberacion");
			c.setCellStyle(hstyle);

			c = fila.createCell(2);
			c.setCellValue("Nombre Cliente");
			c.setCellStyle(hstyle);

			c = fila.createCell(3);
			c.setCellValue("Producto");
			c.setCellStyle(hstyle);

			c = fila.createCell(4);
			c.setCellValue("Encargo");
			c.setCellStyle(hstyle);

			c = fila.createCell(5);
			c.setCellValue("Valor");
			c.setCellStyle(hstyle);

			c = fila.createCell(6);
			c.setCellValue("Estado");
			c.setCellStyle(hstyle);

			c = fila.createCell(7);
			c.setCellValue("Respuesta Banco");
			c.setCellStyle(hstyle);

			c = fila.createCell(8);
			c.setCellValue("Banco");
			c.setCellStyle(hstyle);

			c = fila.createCell(9);
			c.setCellValue("Tipo Cuenta");
			c.setCellStyle(hstyle);

			c = fila.createCell(10);
			c.setCellValue("Numero Cuenta");
			c.setCellStyle(hstyle);

			c = fila.createCell(11);
			c.setCellValue("Nombre");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(12);
			c.setCellValue("Correo Electronico");
			c.setCellStyle(hstyle);

			c = fila.createCell(13);
			c.setCellValue("Numero Identificacion");
			c.setCellStyle(hstyle);

			c = fila.createCell(14);
			c.setCellValue("Tipo Identificacion");
			c.setCellStyle(hstyle);

		}
		// --

		int i = 1;
		for (Pago pago : pagos) {

			fila = hoja.createRow(i);
			// fila.setRowStyle(rstyle);

			c = fila.createCell(0);
			if (pago.getId_carga() != null) {
				c.setCellValue(pago.getId_carga());
			}
			c.setCellStyle(rstyle);

			c = fila.createCell(1);
			if (pago.getFecha_liberacion() != null) {
				c.setCellValue(pago.getFecha_liberacion());
			}
			c.setCellStyle(datestyle);

			c = fila.createCell(2);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getNombre_cliente()));
			c.setCellStyle(rstyle);

			c = fila.createCell(3);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getProducto()));
			c.setCellStyle(rstyle);

			c = fila.createCell(4);
			if (pago.getEncargo() != null) {
				c.setCellValue(pago.getEncargo());
			}
			c.setCellStyle(rstyle);

			c = fila.createCell(5);
			if (pago.getValor() != null) {
				c.setCellValue(pago.getValor().doubleValue());
			}
			c.setCellStyle(numstyle);

			c = fila.createCell(6);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(mapaEstados.get(pago.getEstado())));
			c.setCellStyle(rstyle);

			c = fila.createCell(7);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getRespuesta_banco()));
			c.setCellStyle(rstyle);

			c = fila.createCell(8);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getBanco()));
			c.setCellStyle(rstyle);

			c = fila.createCell(9);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getTipo_de_cuenta()));
			c.setCellStyle(rstyle);

			c = fila.createCell(10);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getNumero_cuenta()));
			c.setCellStyle(rstyle);

			c = fila.createCell(11);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getNombreBeneficiario()));
			c.setCellStyle(rstyle);
			
			c = fila.createCell(12);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getCorreo_electronico()));
			c.setCellStyle(rstyle);

			c = fila.createCell(13);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getNumero_documento()));
			c.setCellStyle(rstyle);

			c = fila.createCell(14);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(pago.getTipo_documento()));
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
		hoja.autoSizeColumn(13);
		hoja.autoSizeColumn(14);

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

	public static class Filtro {

		private Integer id_carga;
		private Integer id_cliente;
		private Date fecha_desde;
		private Date fecha_hasta;

		public Date getFecha_desde() {
			return fecha_desde;
		}

		public void setFecha_desde(Date fecha_desde) {
			this.fecha_desde = fecha_desde;
		}

		public Date getFecha_hasta() {
			return fecha_hasta;
		}

		public void setFecha_hasta(Date fecha_hasta) {
			this.fecha_hasta = fecha_hasta;
		}

		public Integer getId_carga() {
			return id_carga;
		}

		public void setId_carga(Integer id_carga) {
			this.id_carga = id_carga;
		}

		public Integer getId_cliente() {
			return id_cliente;
		}

		public void setId_cliente(Integer id_cliente) {
			this.id_cliente = id_cliente;
		}

	}

	public static class Pago {

		private Integer id_carga;
		private Date fecha_liberacion;
		private String nombre_cliente;
		private String producto;
		private Integer encargo;
		private BigDecimal valor;
		private String estado;
		private String respuesta_banco;
		private String banco;
		private String tipo_de_cuenta;
		private String numero_cuenta;
		private String primer_nombre;
		private String segundo_nombre;
		private String primer_apellido;
		private String segundo_apellido;
		private String numero_documento;
		private String tipo_documento;
		private String correo_electronico;

		public Integer getId_carga() {
			return id_carga;
		}

		public String getNombreBeneficiario() {
			String nombre = "";
			if (this.primer_nombre != null)
				nombre = nombre + " " + this.primer_nombre;

			if (this.segundo_nombre != null)
				nombre = nombre + " " + this.segundo_nombre;

			if (this.primer_apellido != null)
				nombre = nombre + " " + this.primer_apellido;

			if (this.segundo_apellido != null)
				nombre = nombre + " " + this.segundo_apellido;

			return nombre.trim();
		}

		
		
		public String getCorreo_electronico() {
			return correo_electronico;
		}

		public void setCorreo_electronico(String correo_electronico) {
			this.correo_electronico = correo_electronico;
		}

		public void setId_carga(Integer id_carga) {
			this.id_carga = id_carga;
		}

		public Date getFecha_liberacion() {
			return fecha_liberacion;
		}

		public void setFecha_liberacion(Date fecha_liberacion) {
			this.fecha_liberacion = fecha_liberacion;
		}

		public String getNombre_cliente() {
			return nombre_cliente;
		}

		public void setNombre_cliente(String nombre_cliente) {
			this.nombre_cliente = nombre_cliente;
		}

		public String getProducto() {
			return producto;
		}

		public void setProducto(String producto) {
			this.producto = producto;
		}

		public Integer getEncargo() {
			return encargo;
		}

		public void setEncargo(Integer encargo) {
			this.encargo = encargo;
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

		public String getRespuesta_banco() {
			return respuesta_banco;
		}

		public void setRespuesta_banco(String respuesta_banco) {
			this.respuesta_banco = respuesta_banco;
		}

		public String getBanco() {
			return banco;
		}

		public void setBanco(String banco) {
			this.banco = banco;
		}

		public String getTipo_de_cuenta() {
			return tipo_de_cuenta;
		}

		public void setTipo_de_cuenta(String tipo_de_cuenta) {
			this.tipo_de_cuenta = tipo_de_cuenta;
		}

		public String getNumero_cuenta() {
			return numero_cuenta;
		}

		public void setNumero_cuenta(String numero_cuenta) {
			this.numero_cuenta = numero_cuenta;
		}

		public String getPrimer_nombre() {
			return primer_nombre;
		}

		public void setPrimer_nombre(String primer_nombre) {
			this.primer_nombre = primer_nombre;
		}

		public String getSegundo_nombre() {
			return segundo_nombre;
		}

		public void setSegundo_nombre(String segundo_nombre) {
			this.segundo_nombre = segundo_nombre;
		}

		public String getPrimer_apellido() {
			return primer_apellido;
		}

		public void setPrimer_apellido(String primer_apellido) {
			this.primer_apellido = primer_apellido;
		}

		public String getSegundo_apellido() {
			return segundo_apellido;
		}

		public void setSegundo_apellido(String segundo_apellido) {
			this.segundo_apellido = segundo_apellido;
		}

		public String getNumero_documento() {
			return numero_documento;
		}

		public void setNumero_documento(String numero_documento) {
			this.numero_documento = numero_documento;
		}

		public String getTipo_documento() {
			return tipo_documento;
		}

		public void setTipo_documento(String tipo_documento) {
			this.tipo_documento = tipo_documento;
		}

	}
}

package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.Estado;
import com.osmosyscol.datasuite.webdata.logica.dto.ParametrosBusqCargas;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.EstadoCargaServicio;

import co.htsoft.commons.file.FileUtils;

public class ReporteTrasladosGenerados {


	public static List<Traslado> consultaTraslados(Date fechaDesde, Date fechaHasta) {

		List<Banco> bancos = DS_SqlUtils.queryForList(Banco.class, "select id, $banco.nombre$ nombre from $banco$");

		Map<Integer, String> mapa_bancos = new HashMap<Integer, String>();

		for (Banco banco : bancos) {
			mapa_bancos.put(banco.id, banco.nombre);
		}
		
		// --
		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd/MM/yy");
		String fecha_desde = dateformat2.format(fechaDesde).toString();
		String fecha_hasta = dateformat2.format(fechaHasta).toString();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fecha_desde", fechaDesde);
		params.put("fecha_hasta", fechaHasta);
				
		String sql = 	"SELECT r.IDCARGA IDCARGA, " //
					+	"r.ID ID, " //
					+	"c.FECHA_LIBERACION FECHA_LIBERACION," //
					+	"$RETIROS.TIPO DE RETIRO$ TIPO_RETIRO, " //
					+	"$RETIROS.ESTADO$ ESTADO, " //
					+	"$RETIROS.ID CLIENTE$ ID_USUARIO, " //
					+	"$RETIROS.VALOR$ VALOR, " //
					+	"(SELECT $PRODUCTO.NOMBRE$ FROM $PRODUCTO$ p WHERE p.ID = $RETIROS.PRODUCTO$) PRODUCTO, " //
					+	"$RETIROS.ID CUENTA$ ID_CUENTA, " //
					+	"$RETIROS.OBSERVACION$ OBSERVACION, " //
					+	"$RETIROS.NUMERO ORDEN$ COMPROBANTE_EGRESO " //
					+	"FROM $RETIROS$ r, TS01 c " //
					+	"WHERE r.IDCARGA = c.ID_CARGA AND "
					+ 	"$RETIROS.TIPO DE RETIRO$ = $S(TRASLADO)$ " //
					+	"AND  ( c.FECHA_LIBERACION >= \'"+ fecha_desde +"\' and  c.FECHA_LIBERACION <= \'" + fecha_hasta + "\') " //
					+	"ORDER BY IDCARGA,$RETIROS.FECHA TRANSACCION$ "; //

		List<Traslado> traslados = DS_SqlUtils.queryForList(Traslado.class, sql, null);
		Iterator<Traslado> it = traslados.iterator();
		Carga carga_actual;
		HashMap<Integer, Carga> cargas = new HashMap<Integer, Carga>();
		ParametrosBusqCargas param = new ParametrosBusqCargas();
		List<Estado> estados = EstadoCargaServicio.getInstance().obtenerEstados();
		HashMap<String, String> nombre_estado = new HashMap<String, String>();
		for(Estado e: estados) nombre_estado.put(e.getEstado(),e.getNombre_interno());
		
		if (traslados != null) {
			while(it.hasNext()) {
				Traslado traslado = it.next();
				try {
					Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(traslado.getId_usuario());
					traslado.setCliente(usuario.getNombre());

					if( cargas.containsKey(traslado.getIdcarga()) ) {
						carga_actual = cargas.get(traslado.getIdcarga());
					}else {
						param.setId_carga(traslado.getIdcarga());
						carga_actual = CargaServicio.getInstance().buscarCargas(param, 1).get(0);
						cargas.put(carga_actual.getId_carga(), carga_actual);
					}
					traslado.setTipo_solicitud(carga_actual.getId_tipocarga().toString());
					traslado.setFecha_carga(carga_actual.getFecha_carga());
					traslado.setFecha_liberacion(carga_actual.getFecha_liberacion());
					traslado.setFormato(carga_actual.getNombre_formato());
					if(carga_actual.getEstado().equals("S")) {
						it.remove();
					}else {
						traslado.setEstado(nombre_estado.get(carga_actual.getEstado()));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return traslados;

	}

	public static File generarReporte(List<Traslado> traslados) {

		XSSFWorkbook wb = new XSSFWorkbook();

		XSSFSheet hoja = wb.createSheet("Traslados");

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
			c.setCellValue("ID");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(1);
			c.setCellValue("Fecha Carga");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(2);
			c.setCellValue("Fecha Liberación");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(3);
			c.setCellValue("Estado");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(4);
			c.setCellValue("Tipo Solicitud");
			c.setCellStyle(hstyle);
			
			c = fila.createCell(5);
			c.setCellValue("Cliente");
			c.setCellStyle(hstyle);

			c = fila.createCell(6);
			c.setCellValue("Formato");
			c.setCellStyle(hstyle);

			c = fila.createCell(7);
			c.setCellValue("Valor");
			c.setCellStyle(hstyle);

			c = fila.createCell(8);
			c.setCellValue("Tipo de Retiro");
			c.setCellStyle(hstyle);

			c = fila.createCell(9);
			c.setCellValue("Producto");
			c.setCellStyle(hstyle);

			c = fila.createCell(10);
			c.setCellValue("ID Cuenta");
			c.setCellStyle(hstyle);

			c = fila.createCell(11);
			c.setCellValue("Observación");
			c.setCellStyle(hstyle);

			c = fila.createCell(12);
			c.setCellValue("Comprobante Egreso");
			c.setCellStyle(hstyle);
		}
		// --

		int i = 1;
		for (Traslado traslado : traslados) {

			fila = hoja.createRow(i);
			// fila.setRowStyle(rstyle);

			c = fila.createCell(0);
			if (traslado.getId() != null) {
				c.setCellValue(traslado.getIdcarga());
			}
			c.setCellStyle(rstyle);
			
			
			c = fila.createCell(1);
			if (traslado.getFecha_carga() != null){
				c.setCellValue(traslado.getFecha_carga());
			}
			c.setCellStyle(datestyle);
			
			c = fila.createCell(2);
			if (traslado.getFecha_liberacion() != null){
				c.setCellValue(traslado.getFecha_liberacion());
			}
			c.setCellStyle(datestyle);
			
			c = fila.createCell(3);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(traslado.getEstado()));
			c.setCellStyle(rstyle);
			
			c = fila.createCell(4);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(traslado.getTipo_solicitud()));
			c.setCellStyle(rstyle);

			c = fila.createCell(5);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(traslado.getCliente()));
			c.setCellStyle(rstyle);
			
			c = fila.createCell(6);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(traslado.getFormato()));
			c.setCellStyle(rstyle);

			c = fila.createCell(7);
			if(traslado.getValor() != null){
				c.setCellValue(traslado.getValor().doubleValue());
			}
			c.setCellStyle(rstyle);

			c = fila.createCell(8);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(traslado.getTipo_retiro()));
			c.setCellStyle(rstyle);

			c = fila.createCell(9);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(traslado.getProducto()));
			c.setCellStyle(rstyle);

			c = fila.createCell(10);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(traslado.getId_cuenta()));
			c.setCellStyle(rstyle);

			c = fila.createCell(11);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(traslado.getObservacion()));
			c.setCellStyle(numstyle);

			c = fila.createCell(12);
			c.setCellValue(com.osmosyscol.commons.utils.StringUtils.trim(traslado.getComprobante_egreso()));
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

	public static class Traslado {
		
		private Integer id;
		private Date fecha_transaccion;
		private Date fecha_carga;
		private Date fecha_liberacion;
		private String estado;
		private String tipo_solicitud;
		private String cliente;
		private String formato;
		private BigDecimal valor;
		private String tipo_retiro;
		private String producto;
		private String id_cuenta;
		private String observacion;
		private String comprobante_egreso;
		
		// --
		
		private Integer idcarga;
		private Integer id_usuario;
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Date getFecha_transaccion() {
			return fecha_transaccion;
		}

		public void setFecha_transaccion(Date fecha_transaccion) {
			this.fecha_transaccion = fecha_transaccion;
		}

		public Date getFecha_carga() {
			return fecha_carga;
		}

		public void setFecha_carga(Date fecha_carga) {
			this.fecha_carga = fecha_carga;
		}

		public Date getFecha_liberacion() {
			return fecha_liberacion;
		}

		public void setFecha_liberacion(Date fecha_liberacion) {
			this.fecha_liberacion = fecha_liberacion;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public String getTipo_solicitud() {
			return this.tipo_solicitud;
		}
		
		public void setTipo_solicitud(String tipo_solicitud) {
			if(tipo_solicitud.equals(Constantes.TIPO_SOLICITUD_INDIVIDUAL)) {
				this.tipo_solicitud = Constantes.INDIVIDUAL;
			}else if(tipo_solicitud.equals(Constantes.TIPO_SOLICITUD_MASIVO)) {
				this.tipo_solicitud = Constantes.MASIVO;
			}else if(tipo_solicitud.equals(Constantes.TIPO_SOLICITUD_ELECTRONICO)) {
				this.tipo_solicitud = Constantes.ELECTRONICO;
			}else if(tipo_solicitud.equals(Constantes.TIPO_SOLICITUD_ARCHIVO)) {
				this.tipo_solicitud = Constantes.SOLICITUD_ARCHIVO;
			}else {
				this.tipo_solicitud = tipo_solicitud;
			}
		}

		public String getCliente() {
			return cliente;
		}

		public void setCliente(String cliente) {
			this.cliente = cliente;
		}

		public String getFormato() {
			return formato;
		}

		public void setFormato(String formato) {
			this.formato = formato;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

		public String getTipo_retiro() {
			return tipo_retiro;
		}

		public void setTipo_retiro(String tipo_retiro) {
			this.tipo_retiro = tipo_retiro;
		}

		public String getProducto() {
			return producto;
		}

		public void setProducto(String producto) {
			this.producto = producto;
		}

		public String getId_cuenta() {
			return id_cuenta;
		}

		public void setId_cuenta(String id_cuenta) {
			this.id_cuenta = id_cuenta;
		}

		public String getObservacion() {
			return observacion;
		}

		public void setObservacion(String observacion) {
			this.observacion = observacion;
		}

		public String getComprobante_egreso() {
			return comprobante_egreso;
		}

		public void setComprobante_egreso(String comprobante_egreso) {
			this.comprobante_egreso = comprobante_egreso;
		}

		public Integer getIdcarga() {
			return idcarga;
		}

		public void setIdcarga(Integer idcarga) {
			this.idcarga = idcarga;
		}

		public Integer getId_usuario() {
			return id_usuario;
		}

		public void setId_usuario(Integer id_usuario) {
			this.id_usuario = id_usuario;
		}

	}


}

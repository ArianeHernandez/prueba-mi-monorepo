package com.osmosyscol.datasuite.webdata.correval.ordenespago;

import java.util.Date;
import java.util.List;

import com.osmosyscol.commons.utils.SQLUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class CargaClienteServicio {
	
	public static List<CargaCliente> consultaCargasPorCliente(Integer id_cliente) {
		
		String sql = "SELECT T.ESTADO, COUNT(1) CANTIDAD "
				    + " FROM TS01 T, $RETIROS$ R "
					+ " WHERE T.ID_CARGA = R.IDCARGA"
					+ " AND T.ESTADO IN ('C', 'S', 'R')"
					+ " AND TRUNC(T.FECHA_LIBERACION) = TRUNC(SYSDATE)"
					+ " AND $RETIROS.TIPO DE RETIRO$ = $S(ACH)$"
					+ " AND T.ID_CLIENTE = " + id_cliente 
					+ " GROUP BY T.ESTADO";
		
		List<CargaCliente> cargas = DS_SqlUtils.queryForList(CargaCliente.class, sql);
		
		return cargas;
	}
	
	public static List<String> consultaRespuestaBanco(Integer id_cliente) {
		
		String sql = "SELECT $RETIROS.ESTADO DE RESPUESTA BANCO$"
				    + " FROM TS01 T, $RETIROS$ R "
					+ " WHERE T.ID_CARGA = R.IDCARGA"
					+ " AND T.ESTADO IN ('C')"
					+ " AND TRUNC(T.FECHA_LIBERACION) = TRUNC(SYSDATE)"
					+ " AND $RETIROS.TIPO DE RETIRO$ = $S(ACH)$"
					+ " AND T.ID_CLIENTE = " + id_cliente;
		
		List<String> cargas = DS_SqlUtils.queryForList(String.class, sql);
		
		return cargas;
	}
	
	public static ReporteDiarioPagos obtenerReporteDiario(Integer id_cliente){
		
		ReporteDiarioPagos reporte = new ReporteDiarioPagos();
		
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_cliente);
		
		String nombre_usuario = "";

		if (usuario != null) {
			nombre_usuario = SQLUtils.escapeSql(StringUtils.trim(usuario.getNombre()) + " " + StringUtils.trim(usuario.getApellido()));
		}
		
		reporte.setNombre_cliente(nombre_usuario);
		reporte.setFecha(new Date());
		
		List<CargaCliente> cargas = consultaCargasPorCliente(id_cliente);
		
		Integer autorizadas = 0;
		Integer rechazadas = 0;
		Integer enProceso = 0;
		Integer aplicadasBanco = 0;
		Integer rechazadasBanco = 0;
		Integer pendientesBanco = 0;
		
		
		for (CargaCliente cargaCliente : cargas) {
			if(cargaCliente.getEstado().equals("S")){
				enProceso = cargaCliente.getCantidad();
			}
			if(cargaCliente.getEstado().equals("C")){
				autorizadas = cargaCliente.getCantidad();
			}
			if(cargaCliente.getEstado().equals("R")){
				rechazadas = cargaCliente.getCantidad();
			}
		}
		
		Integer totalPagados = enProceso + autorizadas + rechazadas;
		
		reporte.setAutorizadas(autorizadas);
		reporte.setRechazadas(rechazadas);
		reporte.setEnProceso(enProceso);
		
		List<String> respuestasBanco = consultaRespuestaBanco(id_cliente);
		
		for (String string : respuestasBanco) {
			if(string == null){
				pendientesBanco += 1;
				continue;
			}
			if(string.toLowerCase().contains("aplicado")){
				aplicadasBanco += 1;
			}
			if(string.toLowerCase().contains("rechazado")){
				rechazadasBanco += 1;
			}
		}
		
		reporte.setAplicadasBanco(aplicadasBanco);
		reporte.setPendientesBanco(pendientesBanco);
		reporte.setRechazadasBanco(rechazadasBanco);
		
		reporte.setTotalPagos(totalPagados);
		
		
		return reporte;
	}
	
	

	
}


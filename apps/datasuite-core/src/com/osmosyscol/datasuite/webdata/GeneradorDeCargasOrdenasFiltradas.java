package com.osmosyscol.datasuite.webdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.FiltroBandejaEntrada;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.ClienteCarga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;


public class GeneradorDeCargasOrdenasFiltradas {
	
	private static GeneradorDeCargasOrdenasFiltradas instancia;

	private GeneradorDeCargasOrdenasFiltradas() {
	}

	public static GeneradorDeCargasOrdenasFiltradas getInstance() {
		if (instancia == null) {
			instancia = new GeneradorDeCargasOrdenasFiltradas();
		}
		return instancia;
	}
	

	public void inicializarVariablesDeOrdenamientoFiltro(Request request){
		
		String nuevo_proceso = (String)request.getParameter("id_proceso_admin");
		CocoonUtils.osmSessionRequest(request, "id_proceso_admin");
		
		if(nuevo_proceso==null){
			//Si se mantiene el proceso se mantienen los paramtros
			//de ordenamiento y filtro en session
			
			CocoonUtils.osmSessionRequest(request, "id_proceso_admin");
			CocoonUtils.osmSessionRequest(request, "orden");
			CocoonUtils.osmSessionRequest(request, "tipo_orden");
			CocoonUtils.osmSessionRequest(request, "filtro_instancia_carga");
			CocoonUtils.osmSessionRequest(request, "filtro_tipo_carga");
			CocoonUtils.osmSessionRequest(request, "filtro_semaforo");
			CocoonUtils.osmSessionRequest(request, "filtro_cliente");
			CocoonUtils.osmSessionRequest(request, "filtro_orden_activo");
			
			
		}else{
			//Si cambia de proceso se deben inicializar los parametros de
			//de ordenamiento y filtro.
			
			request.getSession().setAttribute("var.orden", "");
			request.getSession().setAttribute("var.tipo_orden", "");
			request.getSession().setAttribute("var.filtro_instancia_carga", "");
			request.getSession().setAttribute("var.filtro_tipo_carga", "");
			request.getSession().setAttribute("var.filtro_semaforo", "");
			request.getSession().setAttribute("var.filtro_cliente", "");
			request.getSession().setAttribute("var.filtro_orden_activo", "");
			
		}
		
	}
	
	public Map<String, Object> obtenerCargasOrdenadasFiltradas(Session session, String instancia_carga, String cliente, String fecha_carga, String fecha_liberacion, Integer num_pagina, FiltroBandejaEntrada[] filtrosBandeja){
		
		Map<String, Object> objetos = new HashMap<String, Object>();
		
		try {
			String id_proceso_admin = (String)session.getAttribute("var.id_proceso_admin");
			Integer id_administrativo = (Integer)session.getAttribute("id_administrativo");
			
			//Ordenamiento de la consulta
			//String orden = (String)session.getAttribute("var.orden");
			//String tipo_orden = (String)session.getAttribute("var.tipo_orden");
				
			//Filtros de la consulta
			//String instancia_carga = (String)session.getAttribute("var.filtro_instancia_carga");
			//String tipo_carga = (String)session.getAttribute("var.filtro_tipo_carga");
			//String semaforo = (String)session.getAttribute("var.filtro_semaforo");
			//String cliente = (String)session.getAttribute("var.filtro_cliente");
			
			Map<String, Object> filtros = new HashMap<String, Object>();
			
			//Filtro de estado de carga
			if(instancia_carga!=null && !instancia_carga.equals("")){
				filtros.put("id_instancia", Integer.parseInt(instancia_carga));
			}
			
			//Filtro tipo de carga
//			if(tipo_carga!= null && !tipo_carga.equals("")){
//				filtros.put("id_tipocarga", Integer.parseInt(tipo_carga));
//			}
			
			//Filtro de semaforo
//			if(semaforo!=null && !semaforo.equals("")){
//				Integer porcentajeTiempoTranscurrido_desde = -1;
//				Integer porcentajeTiempoTranscurrido_hasta = -1;
//				//Si el filtro es 1=Semaforo verde, 2=Semaforo Amarillo, 3=Semaforo Rojo, 0=Semaforo gris
//				if(semaforo.equals("1")){
//					porcentajeTiempoTranscurrido_desde = 0;
//					porcentajeTiempoTranscurrido_hasta = 50;
//					filtros.put("porcentajeTiempoTranscurrido_desde", porcentajeTiempoTranscurrido_desde);
//					filtros.put("porcentajeTiempoTranscurrido_hasta", porcentajeTiempoTranscurrido_hasta);
//					
//					//Son cargas que estan en estado requiere adjunto
//					filtros.put("estado", Constantes.CARGA_ESTADO_SUBIDO);
//				}else if(semaforo.equals("2")){
//					porcentajeTiempoTranscurrido_desde = 50;
//					porcentajeTiempoTranscurrido_hasta = 100;
//					filtros.put("porcentajeTiempoTranscurrido_desde", porcentajeTiempoTranscurrido_desde);
//					filtros.put("porcentajeTiempoTranscurrido_hasta", porcentajeTiempoTranscurrido_hasta);
//				
//					//Son cargas que estan en estado requiere adjunto
//					filtros.put("estado", Constantes.CARGA_ESTADO_SUBIDO);
//				}else if(semaforo.equals("3")){
//					porcentajeTiempoTranscurrido_desde = 100;
//					filtros.put("porcentajeTiempoTranscurrido_desde", porcentajeTiempoTranscurrido_desde);
//					
//					//Son cargas que estan en estado requiere adjunto
//					filtros.put("estado", Constantes.CARGA_ESTADO_SUBIDO);
//				}else if(semaforo.equals("0")){
//					//Son cargas que estan en estado requiere adjunto
//					filtros.put("estado", Constantes.CARGA_ESTADO_REQUIERE_ADJUNTO);
//					
//					
//				}
//			}
			
			
			//Filtro de cliente
			if(cliente!=null&&!cliente.equals("")){
				filtros.put("id_usuario", Integer.parseInt(cliente));
			}
			
			Date fecha_c = null;
			if (StringUtils.esNoVacio(fecha_carga)) {
				fecha_c = StringUtils.toDate(fecha_carga);
				filtros.put("fecha_carga", fecha_c);
			}
			
			Date fecha_lib = null;
			if (StringUtils.esNoVacio(fecha_liberacion)) {
				fecha_lib = StringUtils.toDate(fecha_liberacion);
				filtros.put("fecha_liberacion", fecha_lib);
			}
			
			if (filtros.size() == 0 && (filtrosBandeja == null || filtrosBandeja.length > 0)) {
				filtros.put("ocultar_sin_filtro", true);
			}
			
			Integer id_formato_salida = FormatoServicio.getInstance().obtenerFormatoSalidaPorProceso(Integer.parseInt(id_proceso_admin));
			
			if (filtrosBandeja != null && filtrosBandeja.length > 0) {
				String sql_filtro_bandeja = CargaServicio.getInstance().obtenerConsultaFiltrosBandejaEntrada(id_formato_salida, filtrosBandeja);
				if (StringUtils.esNoVacio(sql_filtro_bandeja)) {
					filtros.put("filtrosBandejaEntrada", sql_filtro_bandeja);
				}
			}
			
			List<Carga> total_cargas = CargaServicio.getInstance().obtenerCargasPorProcesoAdmin(id_administrativo, Integer.parseInt(id_proceso_admin), null, null, filtros, null);

			if (total_cargas != null && total_cargas.size() > 0) {
				objetos.put("total", total_cargas.size());
				//Se consultan las cargas para el administrativo con los parametros para el ordenamiento y los filtros
				List<Carga> cargas = CargaServicio.getInstance().obtenerCargasPorProcesoAdmin( id_administrativo, Integer.parseInt(id_proceso_admin), null, null, filtros, num_pagina);
				
				objetos.put("cargas", cargas);
			}
			
			return objetos;
			
		}catch (Exception e) {
			SimpleLogger.setInfo("error", e);
			return null;
		}
		
		
	}
	
	public List<ClienteCarga> obtenerListoadoClientesPorListadoCargasFiltradasOrdenadas(List<Carga> cargasFiltradasOrdenadas){
		if(cargasFiltradasOrdenadas!=null && cargasFiltradasOrdenadas.size()>0){
			Map<Integer, Object> clientes = new HashMap<Integer, Object>();
			List<ClienteCarga> listadoClientes = new ArrayList<ClienteCarga>();
			//Creamos la lista de clientes con las que se pueden filtrar las cargas 
			for (Carga carga : cargasFiltradasOrdenadas) {
				
				if(!clientes.containsKey(carga.getId_usuario())){
					
					ClienteCarga clienteCarga = new ClienteCarga();
					clienteCarga.setApellido_usuario(carga.getApellido_usuario());
					clienteCarga.setNombre_usuario(carga.getNombre_usuario());
					clienteCarga.setId_carga(carga.getId_carga());
					clienteCarga.setId_usuario(carga.getId_usuario());
					clientes.put(carga.getId_usuario(), clienteCarga);
					listadoClientes.add(clienteCarga);
				}
				
			}
			
			return listadoClientes;
			
		}else{
			return null;
		}	
	}
	
	
	
	
}

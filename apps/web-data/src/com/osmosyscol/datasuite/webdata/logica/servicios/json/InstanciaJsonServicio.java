package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;

public class InstanciaJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public Boolean insertarInstancia(Integer id_instancia,String nombre,String aprobar,String rechazar,Integer tiempo,Integer posicion_x,Integer posicion_y,Integer id_proceso_admin, String inicial,Integer id_formato_salida, Integer id_horario, String aprobar_automaticamente, String rechazar_automaticamente, String solicitar_otp){
		return InstanciaServicio.getInstance().insertarInstancia(id_instancia, nombre, aprobar, rechazar, tiempo, posicion_x, posicion_y, id_proceso_admin, inicial, id_formato_salida, id_horario, aprobar_automaticamente, rechazar_automaticamente, solicitar_otp);
	}
	
	public Instancia obtenerInstancia(Integer id_instancia){
		return InstanciaServicio.getInstance().obtenerInstancia(id_instancia);
	}
	
	public List<Instancia> obtenerInstanciasPorProceso(Integer id_proceso_admin){
		return InstanciaServicio.getInstance().obtenerInstanciasPorProceso(id_proceso_admin);
	}
	
	public Map<Integer, Instancia> obtenerInstanciasPorProcesoMap(Integer id_proceso_admin) {
		return InstanciaServicio.getInstance().obtenerInstanciasPorProcesoMap(id_proceso_admin);
	}
	
	public Integer obtenerSiguienteId(){
		return InstanciaServicio.getInstance().obtenerSiguienteId();
	}
	
	public Boolean borrarInstancia(Integer id_instancia){
		return InstanciaServicio.getInstance().borrarInstancia(id_instancia);
	}
	
	public Boolean actualizarPermisosInstancia(Integer id_instancia, String aprobar, String rechazar, String inicial, String adicionar_docs, String gestionar_docs, String aprobar_automaticamente, String rechazar_automaticamente, String solicitar_otp, String ocultar_cargas_sin_filtro){
		return InstanciaServicio.getInstance().actualizarPermisosInstancia(id_instancia, aprobar, rechazar, inicial, adicionar_docs, gestionar_docs, aprobar_automaticamente, rechazar_automaticamente, solicitar_otp, ocultar_cargas_sin_filtro);
	}
	
	public Boolean actualizarTiempo(Integer id_instancia, Integer tiempo){
		return InstanciaServicio.getInstance().actualizarTiempo(id_instancia, tiempo);
	}
	
	public Boolean actualizarPosicionesXY(Integer id_instancia, Integer posicion_x, Integer posicion_y){
		return InstanciaServicio.getInstance().actualizarPosicionInstancia(id_instancia, posicion_x, posicion_y);
	}
	
	public Boolean actualizarNombre(Integer id_instancia, String nombre){
		return InstanciaServicio.getInstance().actualizarNombre(id_instancia, nombre);
	}
	
	public Boolean actualizarFormatoSalida(Integer id_instancia, Integer id_formato_salida){
		return InstanciaServicio.getInstance().actualizarFormatoSalida(id_instancia, id_formato_salida);
	}
	
	public Boolean actualizarHorarioInstancia(Integer id_instancia, String franjas){
		return InstanciaServicio.getInstance().actualizarHorarioInstancia(id_instancia, franjas);
	}
	
	public Boolean insertarRolInstancia(Integer id_rol,Integer id_instancia){
		return InstanciaServicio.getInstance().insertarRolInstancia(id_rol, id_instancia);
	}
	
	public Rol obtenerRolInstancia(Integer id_rol,Integer id_instancia){
		return InstanciaServicio.getInstance().obtenerRolInstancia(id_rol, id_instancia);
	}
	
	public List<Rol> obtenerRolesInstancia(Integer id_instancia){
		return InstanciaServicio.getInstance().obtenerRolesInstancia(id_instancia);
	}
	
	public Boolean borrarRolInstancia(Integer id_rol,Integer id_instancia){
		return InstanciaServicio.getInstance().borrarRolInstancia(id_rol, id_instancia);
	}
	
	public List<Rol> obtenerRolesDispParaAsignar(Integer id_instancia){
		return InstanciaServicio.getInstance().obtenerRolesDispParaAsignar(id_instancia);
	}
	
	public List<Formato> obtenerFormatosSalidaPorNegocio(Integer id_negocio){
		return FormatoServicio.getInstance().obtenerFormatosSalidaPorNegocio(id_negocio);
	}
	
	//Se revisa si la accion se puede ejecutar
	public List<Instancia> obtenerInstanciasPreviasConCargaActualPendiente(Integer id_instancia_actual, Integer id_carga){
		
		return InstanciaServicio.getInstance().obtenerInstanciasPreviasConCargaActualPendiente(id_instancia_actual, id_carga);
			
	}

	public Boolean actualizarInstancia (Instancia instancia) {
		return InstanciaServicio.getInstance().actualizarInstancia(instancia);
	}
	
	
}

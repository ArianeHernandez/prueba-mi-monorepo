package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.Rol;


public interface InstanciaDao {

	public Boolean insertarInstancia(Integer id_instancia,String nombre,String aprobar,String rechazar,Integer tiempo,Integer posicion_x,Integer posicion_y,Integer id_proceso_admin, String inicial,Integer id_formato_salida, Integer id_horario, String aprobar_automaticamente, String rechazar_automaticamente, String solicitar_otp);
	
	public Instancia obtenerInstancia(Integer id_instancia);
	
	public List<Instancia> obtenerInstanciasPorProceso(Integer id_proceso_admin);
	
	public Integer obtenerSiguienteId();
	
	public Boolean borrarInstancia(Integer id_instancia);
	
	public Boolean actualizarPosicionInstancia(Integer id_instancia, Integer posicion_x, Integer posicion_y);
	
	public Boolean actualizarPermisosInstancia(Integer id_instancia, String aprobar, String rechazar, String inicial, String aprobar_automaticamente, String rechazar_automaticamente, String solicitar_otp, String ocultar_cargas_sin_filtro);
	
	public Boolean actualizarTiempo(Integer id_instancia, Integer tiempo);
	
	public Boolean actualizarHorario(Integer id_instancia, Integer id_horario);
	
	public Boolean actualizarNombre(Integer id_instancia, String nombre);
	
	public Boolean actualizarFormatoSalida(Integer id_instancia, Integer id_formato_salida);
	
	public Boolean insertarRolInstancia(Integer id_rol,Integer id_instancia);
	
	public Rol obtenerRolInstancia(Integer id_rol,Integer id_instancia);
	
	public List<Rol> obtenerRolesInstancia(Integer id_instancia);
	
	public Boolean borrarRolInstancia(Integer id_rol,Integer id_instancia);
	
	public List<Rol> obtenerRolesDispParaAsignar(Integer id_instancia);
	
	public Instancia obtenerInstanciaDelProcesoPorCarga(Integer id_carga, Integer id_instancia);
	
	public List<Instancia> obtenerInstanciasInicialesPorProceso(Integer id_proceso_admin);
	
	public List<Instancia> obtenerInstanciasDelProcesoPorAdministrativo(Integer id_administrativo, Integer id_proceso_admin);
	
	public List<Instancia> obtenerInstanciasDelProcesoParaRolesHijo(Integer id_proceso_admin, Integer id_administrativo_padre);
	
	public List<Instancia> obtenerInstanciasPreviasConCargaActualPendiente (Integer id_instancia_actual, Integer id_carga);
	
	public Boolean actualizarAdicionarDocs(Integer id_instancia, String adiciona);
	
	public Boolean actualizarGestionarDocs(Integer id_instancia, String gestiona);

	public Instancia obtenerInstanciaActual(Integer id_carga);
	
	public Boolean actualizarInstancia(Instancia instancia);
	
}


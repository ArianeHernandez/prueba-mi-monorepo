package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;

public interface ProcesoAdminDao {

	public Boolean insertarProcesoAdmin(Integer id_proceso_admin, Integer id_negocio, Integer id_formato_entrada, String nombre, Integer id_tipo_proceso);

	public List<ProcesoAdmin> obtenerProcesosAdminPorNegocio(Integer id_negocio);
	
	public ProcesoAdmin obtenerProcesoAdmin(Integer id_proceso_admin);
	
	public Integer obtenerSiguienteId();
	
	public Boolean borrarProcesoAdmin(Integer id_proceso_admin);
	
	public Boolean actualizarProcesoAdmin(Integer id_proceso_admin, Integer id_negocio, Integer id_formato_entrada,String nombre, Integer id_tipo_proceso);
	
	public List<ProcesoAdmin> obtenerProcesosAdmin();
	
	public Boolean verificarExistencia(Integer id_negocio, Integer id_formato_entrada, Integer id_proceso_admin);
	
	public List<ProcesoAdmin> obtenerProcesosPorAdministrativo(Integer id_administrativo);
	
	public ProcesoAdmin obtenerProcesoParaAsociarCarga(Integer id_carga);
	
	public List<ProcesoAdmin> obtenerProcesosAdminPorTipoProceso(Integer id_tipo_proceso);
	
	public List<ProcesoAdmin> obtenerProcesosAdminPorTipoProcesoAdministrativo(Integer id_tipo_proceso, Integer id_administrativo);

}

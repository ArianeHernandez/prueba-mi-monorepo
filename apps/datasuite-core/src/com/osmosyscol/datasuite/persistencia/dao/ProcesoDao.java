package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;

public interface ProcesoDao {

	public void cambiarEstado(Integer id_proceso, String estado);

	public List<Proceso> listarProcesos(Integer id_usuario, Integer id_negocio);
	
	public List<Proceso> listarProcesosDefectoTipoSolicitante(Integer tipo_solicitante);
	
	public List<Proceso> listarProcesosPorTipoProceso(Integer id_usuario, Integer id_tipo_proceso);

	public Integer crearProceso(Integer id_usuario, Integer id_negocio, String nombre, Integer id_tipo_negocio);

	public void actualizarProceso(Proceso proceso);
	
	public Proceso obtenerProceso(Integer id_proceso);
	
	public List<Proceso> obtenerProcesosPorLiberador(Integer id_liberador);
	public List<Proceso> listarProcesosPreparador(Integer idUsuario, Integer idNegocio, Integer idPersona, Integer pagina);
	public List<Proceso> listarProcesosPreparadorContar(Integer idUsuario, Integer idNegocio, Integer idPersona, Integer pagina);

	public Integer contarProcesosPreparador(Integer idUsuario, Integer idNegocio, Integer idPersona);
	
	public List<Proceso> listarProcesosLiberador(Integer idUsuario, Integer idNegocio, Integer idPersona, Integer pagina);

	public Integer contarProcesosLiberador(Integer idUsuario, Integer idNegocio, Integer idPersona);

	public Boolean agregarFormato(Integer id_formato, Integer id_proceso);

	public Boolean eliminarFormato(Integer id_formato, Integer id_proceso);

	public List<Proceso> obtenerProcesosPorCliente(Integer id_usuario);
	
	public Integer obtenerCargasPorProcesoClienteEnTransito(Integer id_proceso);
	
	public Boolean eliminarProceso(Integer id_proceso);
	
	public Boolean permiteCrearProceso(Integer id_usuario, Integer id_tipo_proceso);

	public TipoProceso obtenerTipoProcesoPorIdTipoProceso(Integer id_tipo_proceso);
	
	public Boolean permiteRevisorMultiplesInstancias(Integer id_tipo_proceso);
}

package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;
import com.osmosyscol.datasuite.persistencia.dao.ProcesoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ProcesoDaoImp extends BaseSqlMapDao implements ProcesoDao {

	public ProcesoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public void cambiarEstado(Integer id_proceso, String estado) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_proceso", id_proceso);
		map.put("estado", estado);

		update("Proceso.cambiarEstado", map);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Proceso> listarProcesos(Integer id_usuario, Integer id_negocio) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_negocio", id_negocio);

		return queryForList("Proceso.listarProcesos", map);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Proceso> listarProcesosDefectoTipoSolicitante(Integer tipo_solicitante) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("tipo_solicitante", tipo_solicitante);

		return queryForList("Proceso.listarProcesosDefectoTipoSolicitante", map);
	}
	
	// --------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	public List<Proceso> listarProcesosPorTipoProceso(Integer id_usuario, Integer id_tipo_proceso){
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_tipo_proceso", id_tipo_proceso);

		return queryForList("Proceso.listarProcesosPorTipoProceso", map);
	}

	// --------------------------------------------------------

	public Integer crearProceso(Integer id_usuario, Integer id_negocio, String nombre, Integer id_tipo_proceso) {

		Integer id_proceso = (Integer) queryForObject("Proceso.siguienteId", null);

		Proceso proceso = new Proceso(id_proceso, id_usuario, id_negocio, nombre, id_tipo_proceso);

		insert("Proceso.crearProceso", proceso);

		return id_proceso;

	}

	// --------------------------------------------------------

	public void actualizarProceso(Proceso proceso) {
		update("Proceso.actualizarProceso", proceso);
	}

	// --------------------------------------------------------

	public Proceso obtenerProceso(Integer id_proceso) {

		Proceso proceso = (Proceso) queryForObject("Proceso.obtenerProceso", id_proceso);

		return proceso;

	}

	@SuppressWarnings("unchecked")
	public List<Proceso> listarProcesosPreparador(Integer idUsuario, Integer idNegocio, Integer idPersona, Integer pagina) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", idUsuario);
		map.put("id_negocio", idNegocio);
		map.put("id_persona", idPersona);

		if (pagina == null) {
			return queryForList("Proceso.listarProcesosPreparador", map);
		} else {

			return queryForListPag("Proceso.listarProcesosPreparador", map, pagina, Constantes.PAGINACIONLISTADO);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Proceso> listarProcesosPreparadorContar(Integer idUsuario, Integer idNegocio, Integer idPersona, Integer pagina) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", idUsuario);
		map.put("id_negocio", idNegocio);
		map.put("id_persona", idPersona);

		if (pagina == null) {
			return queryForList("Proceso.listarProcesosPreparadorContar", map);
		} else {

			return queryForListPag("Proceso.listarProcesosPreparadorContar", map, pagina, Constantes.PAGINACIONLISTADO);
		}
	}

	public Integer contarProcesosPreparador(Integer idUsuario, Integer idNegocio, Integer idPersona) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", idUsuario);
		map.put("id_negocio", idNegocio);
		map.put("id_persona", idPersona);

		return (Integer) queryForObject("Proceso.contarProcesosPreparador", map);
	}

	@SuppressWarnings("unchecked")
	public List<Proceso> listarProcesosLiberador(Integer idUsuario, Integer idNegocio, Integer idPersona, Integer pagina) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", idUsuario);
		map.put("id_negocio", idNegocio);
		map.put("id_persona", idPersona);

		return queryForListPag("Proceso.listarProcesosLiberador", map, pagina, Constantes.PAGINACIONLISTADO);
	}

	public Integer contarProcesosLiberador(Integer idUsuario, Integer idNegocio, Integer idPersona) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", idUsuario);
		map.put("id_negocio", idNegocio);
		map.put("id_persona", idPersona);

		return (Integer) queryForObject("Proceso.contarProcesosLiberador", map);
	}

	public Boolean agregarFormato(Integer id_formato, Integer id_proceso) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_formato", id_formato);
		map.put("id_proceso", id_proceso);

		insert("Proceso.agregarFormato", map);
		return true;
	}

	public Boolean eliminarFormato(Integer id_formato, Integer id_proceso) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_formato", id_formato);
		map.put("id_proceso", id_proceso);

		delete("Proceso.eliminarFormato", map);

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Proceso> obtenerProcesosPorCliente(Integer id_usuario) {

		return queryForList("Proceso.obtenerProcesosPorCliente", id_usuario);
	}

	public Integer obtenerCargasPorProcesoClienteEnTransito(Integer id_proceso) {
		return (Integer)queryForObject("Proceso.obtenerCargasPorProcesoClienteEnTransito", id_proceso);
	}

	@SuppressWarnings("unchecked")
	public List<Proceso> obtenerProcesosPorLiberador(Integer id_liberador) {
		return queryForList("Proceso.obtenerProcesosPorLiberador", id_liberador);
	}
	
	public Boolean eliminarProceso(Integer id_proceso) {
		update("Proceso.eliminarProceso", id_proceso);
		
		return true;
	}
	
	public Boolean permiteCrearProceso(Integer id_usuario, Integer id_tipo_proceso){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_usuario", id_usuario);
		map.put("id_tipo_proceso", id_tipo_proceso);
		
		Integer resp = (Integer) queryForObject("Proceso.numeroProcesosPermitidosTipoProceso", map);
		
		return (resp>0);
	}

	public TipoProceso obtenerTipoProcesoPorIdTipoProceso(Integer id_tipo_proceso) {
		return (TipoProceso) queryForObject("Proceso.obtenerTipoProcesoPorIdTipoProceso",id_tipo_proceso);
	}
	
	public Boolean permiteRevisorMultiplesInstancias(Integer id_tipo_proceso){
		return StringUtils.esVerdad((String) queryForObject("Proceso.permiteRevisorMultiplesInstancias", id_tipo_proceso));
	}
}

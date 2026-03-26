package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Liberador;
import com.osmosyscol.datasuite.logica.dto.LiberadorTipoProceso;
import com.osmosyscol.datasuite.persistencia.dao.LiberadorDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class LiberadorDaoImp extends BaseSqlMapDao implements LiberadorDao {

	public LiberadorDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public void asociarLiberador(Integer id_proceso, Integer id_liberador) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_proceso", id_proceso);
		map.put("id_liberador", id_liberador);

		insert("Liberador.asociarLiberador", map);
	}

	// --------------------------------------------------------

	public void desasociarLiberador(Integer id_liberador) {
		delete("Liberador.desasociarLiberador", id_liberador);
	}
	
	
	public void desasociarLiberadorPorProceso(Integer id_liberador,Integer id_proceso) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_liberador", id_liberador);
		map.put("id_proceso", id_proceso);
		
		delete("Liberador.desasociarLiberadorPorProceso", map);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Liberador> obtenerLiberadores(Integer id_usuario, Integer id_tipo_proceso) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id_usuario", id_usuario);
		map.put("id_tipo_proceso", id_tipo_proceso);
		
		return queryForList("Liberador.obtenerLiberadores", map);
	}

	// --------------------------------------------------------

	private List<Liberador> addTipoProceso(List<Liberador> listado){
		if(listado!=null){
			
			for (Liberador liberador : listado) {
				addTipoProceso(liberador);
			}
			
		}
		
		return listado;
	}
	
	@SuppressWarnings("unchecked")
	private Liberador addTipoProceso(Liberador liberador){
		if(liberador!=null){
			liberador.setListadoLiberadorTipoProceso(queryForList("Liberador.obtenerListadoLiberadorTipoProceso", liberador.getId_liberador()));
		}
		
		return liberador;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Liberador> obtenerLiberadoresPorProceso(Integer id_proceso) {
		return addTipoProceso(queryForList("Liberador.obtenerLiberadoresPorProceso", id_proceso));
	}

	// --------------------------------------------------------

	public Liberador obtenerLiberadorPorIdentificacion(Integer id_usuario, String tipo_persona, String identificacion, Integer tipoDocumento) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("tipo_persona", tipo_persona);
		map.put("identificacion", identificacion);
		map.put("tipoDocumento", tipoDocumento);

		return  addTipoProceso( (Liberador) queryForObject("Liberador.obtenerLiberadorPorIdentificacion", map));
		
	}

	// --------------------------------------------------------
	
	public Liberador obtenerLiberadorPorId(Integer id_liberador) {
		return addTipoProceso( (Liberador)queryForObject("Liberador.obtenerLiberadorPorId", id_liberador));
	}
	
	public Liberador obtenerLiberadorPorPersonaUsuario(Integer id_persona, Integer id_usuario){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);
		
		return addTipoProceso((Liberador) queryForObject("Liberador.obtenerLiberadorPorPersonaUsuario", map));
	}

	public Boolean actualizarLiberadorTipoProceso(LiberadorTipoProceso liberadorTipoProceso){
		Integer cant = update("Liberador.actualizarLiberadorTipoProceso", liberadorTipoProceso);
		return cant > 0;
		
	}

	public LiberadorTipoProceso obtenerLiberadorTipoProceso(Integer id_liberador, Integer id_tipo_proceso) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_liberador", id_liberador);
		map.put("id_tipo_proceso", id_tipo_proceso);
		
		return (LiberadorTipoProceso) queryForObject("Liberador.obtenerLiberadorTipoProceso", map);
	}
}

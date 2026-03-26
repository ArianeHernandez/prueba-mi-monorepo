package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.ParametroAccionDao;
import com.osmosyscol.datasuite.reportedim.dto.ParametroAccion;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ParametroAccionDaoImp extends BaseSqlMapDao implements ParametroAccionDao {

	public ParametroAccionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public ParametroAccion obtenerParametroAccion(Integer id_parametro) {
		return (ParametroAccion) queryForObject("ParametroAccion.obtenerParametroAccion", id_parametro);
	}

	public Integer totalParametroAccion() {
		return (Integer) queryForObject("ParametroAccion.totalParametrosAccion");
	}

	public Boolean crearParametroAccion(Integer id_parametro_accion, String nombre, String tipo, String encriptado, String valor, String id_reporte, Integer id_navegacion) {

		try {
			if(id_reporte == null &&  id_navegacion == null)
				throw new Exception("Falta algun id: \nid_reporte: " + id_reporte + " id_navegacion: " + id_navegacion + "\n");
			
			if(!encriptado.matches("S|N"))
				throw new Exception("El valor del atributo encriptado debe ser S o N: \nencriptado: " + encriptado + "\n");
			
			if(!tipo.matches("string|integer|date|float|double|boolean|money"))
				throw new Exception("El valor del atributo tipo debe ser string|integer|date|float|double|boolean|money: \ntipo: " + tipo + "\n");
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id_parametro_accion",id_parametro_accion);
			map.put("nombre", nombre);
			map.put("tipo", tipo);
			map.put("encriptado", encriptado);
			map.put("valor", valor);
			map.put("id_reporte", id_reporte);
			map.put("id_navegacion", id_navegacion);
		
			insert("ParametroAccion.crearParametroAccion", map);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroAccionDaoImp.crearParametroAccion", e);
			return false;
		}
		return true;
	}

	public Boolean actualizarParametroAccion(ParametroAccion parametroAccion) {
		Integer cantidad = update("ParametroAccion.actualizarParametroAccion", parametroAccion) ;
		return cantidad > 0;
	}

	public Boolean eliminarParametroAccion(Integer id_parametro) {
		delete("ParametroAccion.eliminarParametroAccion", id_parametro);
		return true;
	}

	public List<ParametroAccion> obtenerParametrosByAccion(String id_reporte) {
		return queryForList("ParametroAccion.obtenerParametrosByAccion", id_reporte);
	}
	
	public List<ParametroAccion> obtenerParametrosByNavegacion(Integer id_navegacion){
		return queryForList("ParametroAccion.obtenerParametrosByNavegacion", id_navegacion);
	}
		
	public Integer obtenerSiguienteParametroAccionId(){
		return (Integer) queryForObject("ParametroAccion.obtenerSiguienteParametroAccionId");
	}
}

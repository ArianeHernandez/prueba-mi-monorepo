package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.NavegacionDao;
import com.osmosyscol.datasuite.reportedim.dto.Navegacion;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class NavegacionDaoImp extends BaseSqlMapDao implements NavegacionDao {

	public NavegacionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Navegacion obtenerNavegacion(Integer id_navegacion) {
		return (Navegacion) queryForObject("Navegacion.obtenerNavegacion", id_navegacion);
	}

	public Integer totalNavegaciones() {
		return (Integer) queryForObject("Navegacion.totalNavegaciones");
	}

	public Boolean crearNavegacion(Integer id_navegacion, String nombre, String destino, String id_reporte) {
		
		try {			
			if(id_reporte == null || id_navegacion == null)
				throw new Exception("Un id de la Navegacion es nulo:\n id_navegacion: " + id_navegacion + " id_reporte: " + id_reporte);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id_navegacion",id_navegacion);
			map.put("destino", destino);
			map.put("nombre", nombre);
			map.put("id_reporte", id_reporte);
		
			insert("Navegacion.crearNavegacion", map);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NavegacionDaoImp.crearNavegacion", e);
			return false;
		}
		return true;
	}

	public Boolean actualizarNavegacion(Navegacion navegacion) {
		Integer cantidad = update("Navegacion.actualizarNavegacion", navegacion) ;
		return cantidad > 0;
	}

	public Boolean eliminarNavegacion(Integer id_navegacion) {
		delete("Navegacion.eliminarNavegacion", id_navegacion);
		return true;
	}
	
	public List<Navegacion> obtenerNavegacionesByReporte(String id_reporte){
		return queryForList("Navegacion.obtenerNavegacionesByReporte", id_reporte);
	}
		
	public Navegacion obtenerNavegacionByNavegacion(Navegacion navegacion){
		return (Navegacion) queryForObject("Navegacion.obtenerNavegacionByNombreReporteDestino", navegacion);
	}
	
	public Integer obtenerSiguienteNavegacionId(){
		return (Integer) queryForObject("Navegacion.obtenerSiguienteNavegacionId");
	}
	
}

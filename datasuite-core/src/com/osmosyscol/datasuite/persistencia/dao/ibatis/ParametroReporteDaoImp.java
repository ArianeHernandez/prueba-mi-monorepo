package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.ParametroReporteDao;
import com.osmosyscol.datasuite.reportedim.dto.ParametroReporte;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ParametroReporteDaoImp extends BaseSqlMapDao implements ParametroReporteDao {

	public ParametroReporteDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public ParametroReporte obtenerParametroReporte(Integer id_parametro) {
		return (ParametroReporte) queryForObject("ParametroReporte.obtenerParametroReporte", id_parametro);
	}

	public Integer totalParametroReporte() {
		return (Integer) queryForObject("ParametroReporte.totalParametrosReporte");
	}

	public Boolean crearParametroReporte(Integer id_parametro, String nombre, String tipo, String encriptado, String filtro, String titulo, Integer orden, String id_reporte) {
		try {
	
			if(id_reporte == null || id_parametro == null)
				throw new Exception("Los atributos id_parametro y id_reporte son obligatorios:\nid_parametro: " + id_parametro + " id_reporte: " + id_reporte);
			
			if(!encriptado.matches("S|N") || !filtro.matches("S|N"))
				throw new Exception("Los atributos encriptado y filtro deben ser de la forma S|N:\nencriptado: " + encriptado + " filtro: " + filtro);
			
			if(!tipo.matches("string|integer|date|float|double|boolean|money"))
				throw new Exception("El atributo tipo debe ser uno de string|integer|date|float|double|boolean|money :\ntipo" + tipo);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id_parametro", id_parametro);
			map.put("nombre", nombre);
			map.put("tipo", tipo);
			map.put("encriptado", encriptado);
			map.put("filtro", filtro);
			map.put("titulo", titulo);
			map.put("orden", orden);
			map.put("id_reporte", id_reporte);
		
			insert("ParametroReporte.crearParametroReporte", map);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroReporteDaoImp.crearParametroReporte", e);
			return false;
		}
		return true;
	}
	
	public Boolean crearParametroReporte(Integer id_parametro, String nombre, String tipo, String encriptado, Boolean filtro, String titulo, Integer orden, String id_reporte) {
		try {
	
			if(id_reporte == null || id_parametro == null)
				throw new Exception("Los atributos id_parametro y id_reporte son obligatorios:\nid_parametro: " + id_parametro + " id_reporte: " + id_reporte);
			
			if(!encriptado.matches("S|N") || filtro == null)
				throw new Exception("El atributos encriptado debe ser de la forma S|N y filtro no puede ser null:\nencriptado: " + encriptado + " filtro: " + filtro);
			
			if(!tipo.matches("string|integer|date|float|double|boolean|money"))
				throw new Exception("El atributo tipo debe ser uno de string|integer|date|float|double|boolean|money :\ntipo" + tipo);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id_parametro", id_parametro);
			map.put("nombre", nombre);
			map.put("tipo", tipo);
			map.put("encriptado", encriptado);
			map.put("filtro", filtro?"S":"N");
			map.put("titulo", titulo);
			map.put("orden", orden);
			map.put("id_reporte", id_reporte);
		
			insert("ParametroReporte.crearParametroReporte", map);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroReporteDaoImp.crearParametroReporte", e);
			return false;
		}
		return true;
	}

	public Boolean actualizarParametroReporte(ParametroReporte parametroReporte) {
		Integer cantidad = update("ParametroReporte.actualizarParametroReporte", parametroReporte) ;
		return cantidad > 0;
	}

	public Boolean eliminarParametroReporte(Integer id_parametro) {
		delete("ParametroReporte.eliminarParametroReporte", id_parametro);
		return true;
	}

	public List<ParametroReporte> obtenerParametrosByReporte(String id_reporte){
		return queryForList("ParametroReporte.obtenerParametrosByReporte", id_reporte);
	}
	
	public Integer obtenerSiguienteParametroReporteId(){
		return (Integer) queryForObject("ParametroReporte.obtenerSiguienteParametroReporteId");
	}
}

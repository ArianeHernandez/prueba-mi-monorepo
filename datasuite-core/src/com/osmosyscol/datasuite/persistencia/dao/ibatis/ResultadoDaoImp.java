package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.ResultadoDao;
import com.osmosyscol.datasuite.reportedim.dto.Resultado;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ResultadoDaoImp extends BaseSqlMapDao implements ResultadoDao {

	public ResultadoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Resultado obtenerResultado(Integer id_resultado) {
		return (Resultado) queryForObject("Resultado.obtenerResultado", id_resultado);
	}

	public Integer totalResultados() {
		return (Integer) queryForObject("Resultado.totalResultados");
	}

	public Boolean crearResultado(Integer id_resultado, String titulo, String nombre, String tipo, Integer orden, String encriptado, String oculto, String id_reporte) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			
			if(id_reporte == null || id_resultado == null)
				throw new Exception("Un id de Reporte es nulo:\n id_reporte: " + id_reporte + " id_resultado: " + id_resultado);
			
			if(!encriptado.matches("S|N") || !oculto.matches("S|N"))
				throw new Exception("Los atributos encritpado y oculto de Reporte deben ser S|N:\n encriptado: " + encriptado + " oculto: " + oculto);
			
			if(!tipo.matches("string|integer|date|float|double|boolean|money"))
				throw new Exception("El atributo tipo debe ser uno de estos string|integer|date|float|double|boolean|money:\n tipo: " + tipo);
			
			map.put("id_resultado", id_resultado);
			map.put("titulo", titulo);
			map.put("nombre", nombre);
			map.put("tipo", tipo);
			map.put("orden", orden);
			map.put("encriptado", encriptado);
			map.put("oculto", oculto);
			map.put("id_reporte", id_reporte);
		
			insert("Resultado.crearResultado", map);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ResultadoDaoImp.crearResultado", e);
			return false;
		}
		return true;
	}

	public Boolean actualizarResultado(Resultado resultado) {
		Integer cantidad = update("Resultado.actualizarResultado", resultado) ;
		return cantidad > 0;
	}

	public Boolean eliminarResultado(Integer id_resultado) {
		delete("Resultado.eliminarResultado", id_resultado);
		return true;
	}
	
	public List<Resultado> obtenerResultadosByReporte(String id_reporte){
		return queryForList("Resultado.obtenerResultadosByReporte", id_reporte);
	}

	public Integer obtenerSiguienteResultadoId(){
		return (Integer) queryForObject("Resultado.obtenerSiguienteResultadoId");
	}
	
}

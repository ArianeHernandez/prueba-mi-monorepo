package com.osmosyscol.datasuite.persistencia.dao.ibatis;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Ipprivada;
import com.osmosyscol.datasuite.logica.dto.Ippublica;
import com.osmosyscol.datasuite.persistencia.dao.IpDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class IpDaoImp extends BaseSqlMapDao implements IpDao {

	public IpDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<Ippublica> obtenerIPPublicas(Integer id_usuario){
		
		return queryForList("Ip.obtenerIPPublicas", id_usuario);
	}
	
	@SuppressWarnings("unchecked")
	public List<Ippublica> obtenerIPPublicasPorEstado(Integer id_usuario, String estado){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("estado", estado);

		return queryForList("Ip.obtenerIPPublicasPorEstado", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Ipprivada> obtenerIPPrivadas(Integer id_ippublica){
		
		return queryForList("Ip.obtenerIPPrivadas", id_ippublica);
		
	}

	public Boolean eliminarIPPrivada(Integer idIpprivada) {
		delete("Ip.eliminarIPPrivada", idIpprivada);
		return true;
	}

	public Boolean eliminarIPPublica(Integer idIppublica) {
		
		delete("Ip.eliminarIPsPrivadas", idIppublica);
		delete("Ip.eliminarIPPublica", idIppublica);
		
		return true;
	}

	public Integer guardarIPPrivada(Ipprivada ipprivada) {
		Integer id = ipprivada.getId_ipprivada();
		if (ipprivada.getId_ipprivada() == null) {
			id = obtenerSiguienteIdIPPrivada();
			ipprivada.setId_ipprivada(id);
			insert("Ip.insertarIPPrivada", ipprivada);
		}
		else {
			update("Ip.actualizarIPPrivada", ipprivada);
		}
		
		return id;
	}

	public Integer guardarIPPublica(Ippublica ippublica) {
		Integer id = ippublica.getId_ippublica();
		if (ippublica.getId_ippublica() == null) {
			id = obtenerSiguienteIdIPPublica();
			ippublica.setId_ippublica(id);
			insert("Ip.insertarIPPublica", ippublica);
		}
		else {
			update("Ip.actualizarIPPublica", ippublica);
		}
		return id;
		
	}

	public Integer obtenerSiguienteIdIPPrivada() {
		return (Integer)queryForObject("Ip.obtenerSiguienteIdIPPrivada", null);
	}

	public Integer obtenerSiguienteIdIPPublica() {
		return (Integer)queryForObject("Ip.obtenerSiguienteIdIPPublica", null);
	}
	
	public List<Ipprivada> obtenerIPPrivPorUsuario(Integer id_usuario){
		return queryForList("Ip.obtenerIPPrivPorUsuario", id_usuario);
	}
	
}

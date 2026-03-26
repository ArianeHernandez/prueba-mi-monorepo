package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.RegistroConfiguracion;
import com.osmosyscol.datasuite.persistencia.dao.ConfiguracionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ConfiguracionDaoImp extends BaseSqlMapDao implements ConfiguracionDao {

	public ConfiguracionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}
	
	public RegistroConfiguracion obtenerRegistroConfiguracionByEtiqueta(
			String etiqueta) {
		return (RegistroConfiguracion)queryForObject("Configuracion.obtenerRegistroConfiguracionByEtiqueta", etiqueta);
	}
	@SuppressWarnings("unchecked")
	public List<RegistroConfiguracion> obtenerRegistrosConfiguracionByServicio(
			String servicio) {
		return queryForList("Configuracion.obtenerRegistroConfiguracionByServicio", servicio) ;
	}

	@SuppressWarnings("unchecked")
	public List<RegistroConfiguracion> obtenerTodosRegistrosConfiguracion() {
		return queryForList("Configuracion.obtenerTodosRegistroConfiguracion") ;
	}

	
}

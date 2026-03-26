package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.MapCache;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.TipoCampoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class TipoCampoServicio {

	private static TipoCampoServicio tipoCampoServicio;
	
	//Guarda en caceh los tipos de campo
	private MapCache<Integer, TipoCampo> cacheTiposCampo;

	private TipoCampoServicio() {
		cacheTiposCampo = new MapCache<Integer, TipoCampo>(20);
	}

	public static TipoCampoServicio getInstance() {
		if (tipoCampoServicio == null) {
			tipoCampoServicio = new TipoCampoServicio();
		}
		return tipoCampoServicio;
	}

	// ------------------------------

	public List<TipoCampo> obtenerTipoCampos() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			TipoCampoDao tipocampoDao = (TipoCampoDao) daoManager.getDao(TipoCampoDao.class);

			List<TipoCampo> tiposCampo = tipocampoDao.obtenerTipoCampos();
			
			if(CollectionUtils.isNotEmpty(tiposCampo)){
				for (TipoCampo tipoCampo : tiposCampo) {
					cacheTiposCampo.put(tipoCampo.getId_tipocampo(), tipoCampo);
				}
			}
			
			return tiposCampo;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public TipoCampo obtenerTipoCampo(Integer id_tipocampo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			TipoCampoDao tipocampoDao = (TipoCampoDao) daoManager.getDao(TipoCampoDao.class);

			TipoCampo tipoCampo = null;
			
			if(cacheTiposCampo.contains(id_tipocampo)){
				tipoCampo = cacheTiposCampo.get(id_tipocampo);
			}else{
				tipoCampo = tipocampoDao.obtenerTipoCampo(id_tipocampo);
				cacheTiposCampo.put(tipoCampo.getId_tipocampo(), tipoCampo);
			}
			
			return tipoCampo;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

}

package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Uri;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.UriDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;


public class UriDaoImp extends BaseSqlMapDao implements UriDao {

	public UriDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Uri obtenerUri(Integer id_uri) {
		Uri uri = (Uri) queryForObject("Uri.obtenerUri", id_uri);
		return uri;
	}

	@SuppressWarnings("unchecked")
	public List<Uri> obtenerTodasUri(Integer numero_pagina) {
		
		
		
		List<Uri> uris = queryForListPag("Uri.obtenerTodasUri", null,numero_pagina, Constantes.PAGINACIONLISTADO);
		return uris;
		
	}

	public Integer totalUris() {
		return (Integer) queryForObject("Uri.totalUris", null);
	}

	public Boolean guardarUri(Uri uri) {
		if (uri.getId_uri() == null) {
			insert("Uri.guardarUri", uri);
		} else {
			update("Uri.actualizarUri", uri);
		}

		return true;
	}

	public Boolean eliminarUri(Integer id_uri) {
		Integer res = (Integer) delete("Uri.eliminarUri", id_uri);
		if(res>0){
			return true;
		}else
			{
			return false;
		}
	}

	// --------------------------------------------------------


}

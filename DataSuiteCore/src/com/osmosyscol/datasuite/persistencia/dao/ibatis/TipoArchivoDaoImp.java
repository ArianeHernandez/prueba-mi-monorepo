package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.TipoArchivo;
import com.osmosyscol.datasuite.persistencia.dao.TipoArchivoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class TipoArchivoDaoImp extends BaseSqlMapDao implements TipoArchivoDao {

	public TipoArchivoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@Override
	public Boolean adicionarTipoArchivoAdjunto(Integer id_tipo_adjunto, Integer id_archivo_adjunto) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id_tipo_adjunto", id_tipo_adjunto);
			params.put("id_archivo_adjunto", id_archivo_adjunto);
			insert("TipoArchivo.adicionarTipoArchivoAdjunto", params);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TipoArchivo> obtenerTiposArchivo() {
		try {
			return queryForList("TipoArchivo.obtenerTiposArchivo");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoArchivo> obtenerTodoTiposArchivo() {
		try {
			return queryForList("TipoArchivo.obtenerTodoTiposArchivo");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TipoArchivo actualizarTipoArchivo(TipoArchivo tipoArchivo) {
		if(tipoArchivo == null){
			return null;
		}
		update("TipoArchivo.actualizarTipoArchivo", tipoArchivo);
		return tipoArchivo;
	}

	@Override
	public TipoArchivo guardarTipoArchivo(TipoArchivo tipoArchivo) {
		if(tipoArchivo == null){
			return null;
		}
		update("TipoArchivo.guardarTipoArchivo", tipoArchivo);
		return tipoArchivo;
	}

	@Override
	public Boolean eliminarTipoArchivo(TipoArchivo tipoArchivo) {
		delete("TipoArchivo.eliminarTipoArchivo", tipoArchivo);
		return true;
	}

	public TipoArchivo obtenerTipoArchivo(Integer id_tipo_archivo) {
		return (TipoArchivo)queryForObject("TipoArchivo.obtenerTipoArchivo", id_tipo_archivo);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> obtenerExtensionesPorTipoArchivo(Integer id_tipo_archivo) {
		return queryForList("TipoArchivo.obtenerExtensionesPorTipoArchivo", id_tipo_archivo);
	}
}
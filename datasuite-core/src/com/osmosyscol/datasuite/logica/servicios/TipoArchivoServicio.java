package com.osmosyscol.datasuite.logica.servicios;
import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.TipoArchivo;
import com.osmosyscol.datasuite.persistencia.dao.TipoArchivoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

import java.util.List;

public class TipoArchivoServicio {
	private static TipoArchivoServicio tipoArchivoServicio;

	private TipoArchivoServicio() {
	}
	
	public static TipoArchivoServicio getInstance() {
		if (tipoArchivoServicio == null) {
			tipoArchivoServicio = new TipoArchivoServicio();
		}
		return tipoArchivoServicio;
	}
	
	
	public List<TipoArchivo> obtenerTiposArchivo(){
		try {
			TipoArchivoDao TipoArchivoDao = (TipoArchivoDao) DaoConfig.getDao(TipoArchivoDao.class);
			List<TipoArchivo> listadoTipoArchivo = TipoArchivoDao.obtenerTiposArchivo();
			return listadoTipoArchivo;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public List<TipoArchivo> obtenerTodoTiposArchivo(){
		try {
			TipoArchivoDao TipoArchivoDao = (TipoArchivoDao) DaoConfig.getDao(TipoArchivoDao.class);
			List<TipoArchivo> listadoTipoArchivo = TipoArchivoDao.obtenerTodoTiposArchivo();
			return listadoTipoArchivo;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public TipoArchivo guardarTipoArchivo(TipoArchivo tipoArchivo, DaoManager daoManager) {

		try {

			if (!validarTipoArchivo(tipoArchivo)) {
				SimpleLogger.setError("Tipo de archivo no valido");
				return null;
			}
			if (daoManager == null) {
				daoManager = DaoConfig.getDaoManager();
			}

			TipoArchivoDao tipoArchivoDao = (TipoArchivoDao) daoManager.getDao(TipoArchivoDao.class);

			return tipoArchivoDao.guardarTipoArchivo(tipoArchivo);

		} catch (Exception e) {
			SimpleLogger.setError("Error al actualizar el tipo de archivo", e);
		}

		return null;
	}
	
	public TipoArchivo actualizarTipoArchivo(TipoArchivo tipoArchivo, DaoManager daoManager) {

		try {

			if (!validarTipoArchivo(tipoArchivo)) {
				SimpleLogger.setError("Tipo de archivo no valido");
				return null;
			}
			if (daoManager == null) {
				daoManager = DaoConfig.getDaoManager();
			}

			TipoArchivoDao tipoArchivoDao = (TipoArchivoDao) daoManager.getDao(TipoArchivoDao.class);

			return tipoArchivoDao.actualizarTipoArchivo(tipoArchivo);

		} catch (Exception e) {
			SimpleLogger.setError("Error al actualizar el tipo de archivo", e);
		}

		return null;
	}

	public Boolean validarTipoArchivo(TipoArchivo tipoArchivo) {
		if(tipoArchivo ==null){
			return false;
		}
		if(tipoArchivo.getId_tipo_archivo() == null){
			return false;
		}
		if (StringUtils.esVacio(tipoArchivo.getNombre())){
			return false;
		}
		if (StringUtils.esVacio(tipoArchivo.getActivo())){
			return false;
		}
		return true;
	}

	public TipoArchivo obtenerTipoArchivo(Integer id_tipo_archivo){
		try {
			TipoArchivoDao TipoArchivoDao = (TipoArchivoDao) DaoConfig.getDao(TipoArchivoDao.class);
			TipoArchivo tipoArchivo = TipoArchivoDao.obtenerTipoArchivo(id_tipo_archivo);
			return tipoArchivo;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public List<String> obtenerExtensionesPorTipoArchivo (Integer id_tipo_archivo) {
		try {
			TipoArchivoDao tipoArchivoDao = (TipoArchivoDao) DaoConfig.getDao(TipoArchivoDao.class);
			return tipoArchivoDao.obtenerExtensionesPorTipoArchivo(id_tipo_archivo);
		} catch (Exception e) {
			SimpleLogger.setError("TipoArchivoServicio.obtenerExtensionesPorTipoArchivo: Ha ocurrido un error", e);
		}
		return null;
	}
	
}

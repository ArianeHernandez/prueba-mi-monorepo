package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.GrupoArchivoAdjunto;
import com.osmosyscol.datasuite.persistencia.dao.ArchivoAdjuntoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ArchivoAdjuntoDaoImp extends BaseSqlMapDao implements ArchivoAdjuntoDao {

	public ArchivoAdjuntoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean agregarArchivoAdjunto(ArchivoAdjunto adjunto) {

		Boolean rta = false;

		try {
			insert("ArchivoAdjunto.agregarArchivoAdjunto", adjunto);
			rta = true;
		} catch (Exception e) {
			SimpleLogger.setError("Error guardando archivo ", e);
			rta = false;
		}

		return rta;
	}
	
	@Override
	public Long contarArchivosAdjuntosPorCarga(Integer id_carga,
			Boolean internas, Integer id_tipo_archivo) {
		if (internas == null) {
			internas = true;
		}

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("id_carga", id_carga);
		params.put("internas", internas ? "S" : "N");
		params.put("id_tipo_archivo", id_tipo_archivo);
		
		return (Long)queryForObject("ArchivoAdjunto.contarArchivosAdjuntosPorCarga",params);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ArchivoAdjunto> obtenerArchivosAdjuntosPorCarga(Integer id_carga, Boolean internas, Integer id_tipo_archivo) {

		if (internas == null) {
			internas = true;
		}

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("id_carga", id_carga);
		params.put("internas", internas ? "S" : "N");
		params.put("id_tipo_archivo", id_tipo_archivo);

		return queryForList("ArchivoAdjunto.obtenerArchivosAdjuntosPorCarga", params);
	}

	public Integer obtenerSiguiente() {
		return (Integer) queryForObject("ArchivoAdjunto.obtenerSiguiente", null);
	}

	public ArchivoAdjunto obtenerArchivoAdjunto(Integer id_archivo_adjunto) {
		return (ArchivoAdjunto) queryForObject("ArchivoAdjunto.obtenerArchivoAdjunto", id_archivo_adjunto);
	}

	@Override
	public Boolean actualizarArchivoAdjunto(ArchivoAdjunto adjunto) {
		return update("ArchivoAdjunto.actualizarArchivoAdjunto", adjunto) == 1 ? true : false ;
	}
	
	@Override
	public Boolean actualizarEstado(String estado, Integer id_archivo_adjunto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("estado", estado);
		map.put("id_archivo_adjunto", id_archivo_adjunto);
		return update("ArchivoAdjunto.actualizarEstado", map) == 1 ? true : false ;
	}

	@Override
	public Boolean actualizarDescripcion(Integer id_archivo_adjunto, String descripcion) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("descripcion", descripcion);
		map.put("id_archivo_adjunto", id_archivo_adjunto);
		return update("ArchivoAdjunto.actualizarDescripcion", map) == 1 ? true : false ;
	}
	
	@Override
	public Boolean borrarArchivo(Integer id_archivo_adjunto) {
		Integer cant = delete("ArchivoAdjunto.borrarArchivo", id_archivo_adjunto);
		return cant>0;
	}
	
	public Boolean borrarAccion(Integer id_accion){
		Integer cant = delete("Accion.borrarAccion", id_accion);
		return cant>0;
	}
	
	@Override
	public Boolean actualizarRadicado(Integer id_archivo_adjunto, String radicado, String estado) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("radicado", radicado);
		map.put("estado", estado);
		map.put("id_archivo_adjunto", id_archivo_adjunto);
		return update("ArchivoAdjunto.actualizarRadicado", map) == 1 ? true : false ;
	}

	@Override
	public Boolean actualizarRadicadoAdjuntosSegunTipologia(Integer id_carga, String radicado, String estado, Boolean filterInterno, Integer filterIdTipoArchivo) {
		if (filterInterno == null) {
			filterInterno = true;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("radicado", radicado);
		map.put("estado", estado);
		map.put("internas", filterInterno ? "S" : "N");
		map.put("id_tipo_archivo", filterIdTipoArchivo);
		
		return update("ArchivoAdjunto.actualizarRadicadoAdjuntosSegunTipologia", map) >= 1 ? true : false ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArchivoAdjunto> obtenerAdjuntosSegunTipologia(Integer id_carga, String estado, Boolean filterInterno, Integer filterIdTipoArchivo) {
		if (filterInterno == null) {
			filterInterno = true;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("estado", estado);
		map.put("internas", filterInterno ? "S" : "N");
		map.put("id_tipo_archivo", filterIdTipoArchivo);
		
		return queryForList("ArchivoAdjunto.obtenerAdjuntosSegunTipologia", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> obtenerRadicadoAdjuntosSegunTipologia(Integer id_carga,	String estado, Boolean filterInterno, Integer filterIdTipoArchivo) {
		if (filterInterno == null) {
			filterInterno = true;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("estado", estado);
		map.put("internas", filterInterno ? "S" : "N");
		map.put("id_tipo_archivo", filterIdTipoArchivo);
		
		return queryForList("ArchivoAdjunto.obtenerRadicadoAdjuntosSegunTipologia", map);
	}

	@SuppressWarnings("unchecked")
	public List<ArchivoAdjunto> obtenerArchivosAdjuntosPorCargaTipo(Integer id_carga, Integer id_tipo_archivo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_carga", id_carga);
		map.put("id_tipo_archivo", id_tipo_archivo);
		
		return queryForList("ArchivoAdjunto.obtenerArchivosAdjuntosPorCargaTipo", map);
	}

	@Override
	public Boolean actualizarResumenHash(Integer id_archivo_adjunto,
			String resumen_hash) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_archivo_adjunto", id_archivo_adjunto);
		map.put("resumen_hash", resumen_hash);
		
		Integer cant = update("ArchivoAdjunto.actualizarResumenHash", map);
		
		return cant > 0;
		
	}

	@Override
	public Boolean borrarAdjuntos(Integer id_carga) {
		return delete("ArchivoAdjunto.borrarAdjuntos", id_carga) >= 0;
	}

	@Override
	public Boolean actualizarTipoArchivo(Integer id_carga,
			Integer id_tipo_archivo_actual, Integer id_tipo_archivo_nuevo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_tipo_archivo_actual", id_tipo_archivo_actual);
		map.put("id_tipo_archivo_nuevo", id_tipo_archivo_nuevo);
		return update("ArchivoAdjunto.actualizarTipoArchivo", map) > 0;
	}
	
	@Override
	public Integer actualizarDocumentosSalidaInterno(Date fecha) {
		Map<String, Object> map = new HashMap<>();
		map.put("fecha", fecha);
		
		return update("ArchivoAdjunto.actualizarDocumentosSalidaInterno", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GrupoArchivoAdjunto> obtenerGruposArchivoPorCarga(
			Integer id_carga) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_carga", id_carga);
		
		return queryForList("ArchivoAdjunto.obtenerGruposArchivoPorCarga", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArchivoAdjunto> obtenerArchivosPorGrupoCarga(Integer id_carga,
			Integer id_grupo_archivo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_carga", id_carga);
		map.put("id_grupo_archivo", id_grupo_archivo);
		
		return queryForList("ArchivoAdjunto.obtenerArchivosPorGrupoCarga", map);
	}
	
}

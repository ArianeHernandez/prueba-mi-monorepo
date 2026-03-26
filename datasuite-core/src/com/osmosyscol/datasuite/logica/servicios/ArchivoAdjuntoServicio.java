package com.osmosyscol.datasuite.logica.servicios;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.GrupoArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.Nodo;
import com.osmosyscol.datasuite.logica.dto.TipoArchivo;
import com.osmosyscol.datasuite.persistencia.dao.ArchivoAdjuntoDao;
import com.osmosyscol.datasuite.persistencia.dao.TipoArchivoDao;
import com.osmosyscol.datasuite.persistencia.dao.ibatis.TipoArchivoDaoImp;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ArchivoAdjuntoServicio {

	private static ArchivoAdjuntoServicio instance;

	private ArchivoAdjuntoServicio() {
	}

	public static ArchivoAdjuntoServicio getInstance() {
		if (instance == null) {
			instance = new ArchivoAdjuntoServicio();
		}
		return instance;
	}

	public Boolean agregarArchivoAdjunto(ArchivoAdjunto adjunto) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);

			return archivoAdjuntoDao.agregarArchivoAdjunto(adjunto);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Boolean agregar(File file, Integer id_carga, String descripcion, Boolean interno) {

		if (file == null) {
			return true;
		}

		Long checkSum;
		try {

			String nombre = FilenameUtils.getBaseName(file.getName());
			String extension = FilenameUtils.getExtension(file.getName());

			checkSum = FileUtils.checksumCRC32(file);
			ArchivoAdjunto adjunto = new ArchivoAdjunto();
			adjunto.setDescripcion(descripcion);
			adjunto.setInterno(interno?"S":"N");
			adjunto.setExtension_archivo(extension);
			adjunto.setFecha_subida(HorarioServicio.getInstance().obtenerFechaActual());
			adjunto.setId_archivo_adjunto(ArchivoAdjuntoServicio.getInstance().obtenerSiguiente());
			adjunto.setId_carga(id_carga);
			adjunto.setLong_bytes(file.length() + "");
			adjunto.setNombre_archivo(nombre);
			adjunto.setResumen_hash(checkSum.toString());
			adjunto.setRuta(file.getAbsolutePath());

			return ArchivoAdjuntoServicio.getInstance().agregarArchivoAdjunto(adjunto);

		} catch (Exception e) {
			SimpleLogger.setError("agregando Archivo", e);
		}

		return false;
	}

	public Integer obtenerSiguiente() {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);

			return archivoAdjuntoDao.obtenerSiguiente();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}

	public List<ArchivoAdjunto> obtenerArchivosAdjuntosPorCarga(Integer id_carga, Boolean internas, Integer id_tipo_archivo) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);

			return archivoAdjuntoDao.obtenerArchivosAdjuntosPorCarga(id_carga, internas, id_tipo_archivo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}
	
	public Long contarArchivosAdjuntosPorCarga(Integer id_carga, Boolean internas, Integer id_tipo_archivo) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);

			return archivoAdjuntoDao.contarArchivosAdjuntosPorCarga(id_carga, internas, id_tipo_archivo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}
	

	public ArchivoAdjunto obtenerArchivoAdjunto(Integer id_archivo_adjunto) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);

			return archivoAdjuntoDao.obtenerArchivoAdjunto(id_archivo_adjunto);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}
	
	public Boolean actualizarArchivoAdjunto (ArchivoAdjunto adjunto) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);

			return archivoAdjuntoDao.actualizarArchivoAdjunto(adjunto);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public Boolean actualizarEstado(String estado, Integer id_archivo_adjunto) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);

			return archivoAdjuntoDao.actualizarEstado(estado, id_archivo_adjunto);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public Boolean copiarArchivosAdjuntos(Integer id_carga_origen, Integer id_carga_destino){
		try {
			boolean exito = true;
			List<ArchivoAdjunto> archivos = obtenerArchivosAdjuntosPorCarga(id_carga_origen, true, null);
			
			if(archivos != null){
				
				for (ArchivoAdjunto archivo : archivos) {
					archivo.setId_archivo_adjunto(obtenerSiguiente());
					archivo.setId_carga(id_carga_destino);
					exito = exito && agregarArchivoAdjunto(archivo);
				}
			}
			return exito;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Boolean actualizarDescripcion(Integer id_archivo_adjunto, String descripcion ) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.actualizarDescripcion(id_archivo_adjunto, descripcion);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public Boolean actualizarRadicado(Integer id_archivo_adjunto, String radicado, String estado ) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.actualizarRadicado(id_archivo_adjunto, radicado, estado);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public List<TipoArchivo> obtenerTiposArchivo() {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			TipoArchivoDao tipoArchivoDao = (TipoArchivoDao) daoManager.getDao(TipoArchivoDao.class);

			return tipoArchivoDao.obtenerTiposArchivo();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}
	
	public Boolean actualizarRadicadoAdjuntosSegunTipologia(Integer id_carga, String radicado, String estado, Boolean filterInterno, Integer filterIdTipoArchivo ) {
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.actualizarRadicadoAdjuntosSegunTipologia(id_carga, radicado, estado, filterInterno, filterIdTipoArchivo );
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public List<ArchivoAdjunto> obtenerAdjuntosSegunTipologia(Integer id_carga, String estado, Boolean filterInterno, Integer filterIdTipoArchivo ) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);

			return archivoAdjuntoDao.obtenerAdjuntosSegunTipologia(id_carga, estado, filterInterno, filterIdTipoArchivo); 

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}
	
	public List<String> obtenerRadicadoAdjuntosSegunTipologia(Integer id_carga, String estado, Boolean filterInterno, Integer filterIdTipoArchivo ) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);

			return archivoAdjuntoDao.obtenerRadicadoAdjuntosSegunTipologia(id_carga, estado, filterInterno, filterIdTipoArchivo); 

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}
	
	public Boolean borrarArchivo(Integer id_archivo_adjunto) {
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.borrarArchivo(id_archivo_adjunto);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public Boolean borrarAdjuntos(Integer id_carga) {
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.borrarAdjuntos(id_carga);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public List<ArchivoAdjunto> obtenerArchivosAdjuntosPorCargaTipo (Integer id_carga, Integer id_tipo_archivo) {
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.obtenerArchivosAdjuntosPorCargaTipo(id_carga, id_tipo_archivo);
			
		} catch ( Exception e ) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
		
	}
	
	public Boolean actualizarResumenHash (Integer id_archivo_adjunto, String resumen_hash) {
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.actualizarResumenHash(id_archivo_adjunto, resumen_hash);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return false;
		}
		
	}
	
	public Boolean actualizarDocumento (ArchivoAdjunto archivo_adjunto, File file) {
		if (file != null) {
			
			if (S3Servicio.getInstance().esAlmacenamientoS3() && archivo_adjunto.getRuta().startsWith("s3")) {
				return S3Servicio.getInstance().cargarArchivo(file, archivo_adjunto);
				
			} else {
				File output = new File(archivo_adjunto.getRuta());
				try {
					FileUtils.copy(file, output );
					Long checkSum = FileUtils.checksumCRC32(file);
					Integer id_archivo_adjunto = archivo_adjunto.getId_archivo_adjunto();
					ArchivoAdjuntoServicio.getInstance().actualizarResumenHash(id_archivo_adjunto, checkSum.toString());
					return true;
				} catch (Exception e) {
					SimpleLogger.setError("Error actualizando el PDF formulario con sticker para la solicitud " + archivo_adjunto.getId_carga(), e);
				}
			}
			
		}
		return false;
	}
	
	public Boolean actualizarTipoArchivo (Integer id_carga, Integer id_tipo_archivo_actual, Integer id_tipo_archivo_nuevo) {
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.actualizarTipoArchivo(id_carga, id_tipo_archivo_actual, id_tipo_archivo_nuevo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return false;
		}
	}
	
	public Integer actualizarDocumentosSalidaInterno(Date fecha) {
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.actualizarDocumentosSalidaInterno(fecha);
			
		} catch ( Exception e ) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
		
	}
	
	public Map<String, Object> obtenerAdjuntosPorCargaNodo (Integer id_carga, Integer id_tipo_archivo) {
		Map<String,Object> map = null;
		
		try {
			
			if (contarArchivosAdjuntosPorCarga(id_carga, false, id_tipo_archivo) > 0 ) {
				map = new HashMap<>();
				map.put("@name", "Documentos Adicionales");
				map.put("@type", "object");
				map.put("@ID", id_carga);
				List<Object> nodos = new ArrayList<>();
				
				List<ArchivoAdjunto> adjuntos = obtenerArchivosAdjuntosPorCarga(id_carga, false, id_tipo_archivo);
				for (ArchivoAdjunto adjunto: adjuntos) {
					Map<String, Object> nodo = new HashMap<String, Object>();
					TipoArchivo tipoArchivo = TipoArchivoServicio.getInstance().obtenerTipoArchivo(adjunto.getId_tipo_archivo());
					String nombre = tipoArchivo != null? tipoArchivo.getNombre(): adjunto.getDescripcion();
					nodo.put("@name", nombre);
					nodo.put("@value", adjunto.getNombre_archivo() + "." + adjunto.getExtension_archivo());
					nodos.add(nodo);
				}
				map.put("#nodo", nodos);
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("ArchivoAdjuntoServicio.obtenerAdjuntosPorCargaNodo: Error ", e);
		}
		
		return map;
	}
	
	public Nodo obtenerNodoAdjuntosPorCarga (Integer id_carga) {
		Nodo seccionAdjuntos = null;
		
		try {
			
			if (contarArchivosAdjuntosPorCarga(id_carga, false, null) > 0 ) {
				seccionAdjuntos = new Nodo();
				seccionAdjuntos.setname("Adjuntos Cargados");
				List<Nodo> nodos = new ArrayList<>();
				
				List<ArchivoAdjunto> adjuntos = obtenerArchivosAdjuntosPorCarga(id_carga, false, null);
				for (ArchivoAdjunto adjunto: adjuntos) {
					Nodo nodoAdjunto = new Nodo();
					TipoArchivo tipoArchivo = TipoArchivoServicio.getInstance().obtenerTipoArchivo(adjunto.getId_tipo_archivo());
					String nombre = StringUtils.isNotBlank(adjunto.getDescripcion())? adjunto.getDescripcion(): tipoArchivo.getNombre();
					nodoAdjunto.setname(nombre);
					nodoAdjunto.setvalue(adjunto.getNombre_archivo() + "." + adjunto.getExtension_archivo());
					nodos.add(nodoAdjunto);
				}
				seccionAdjuntos.setNodo(nodos);
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("ArchivoAdjuntoServicio.obtenerAdjuntosPorCargaNodo: Error ", e);
		}
		
		return seccionAdjuntos;
	}
	
	public File obtenerArchivo (String ruta) {
		
		if (S3Servicio.getInstance().esAlmacenamientoS3() && ruta.startsWith("s3")) {
			return S3Servicio.getInstance().descargarArchivo(ruta);
		} else {
			return new File(ruta);
		}
	}
	
	public List<GrupoArchivoAdjunto> obtenerGruposArchivoPorCarga (Integer id_carga) {
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
			
			return archivoAdjuntoDao.obtenerGruposArchivoPorCarga(id_carga);
			
		} catch ( Exception e ) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
		
	}
	
	public List<ArchivoAdjunto> obtenerArchivosPorGrupoCarga (Integer id_carga, Integer id_grupo_archivo) {
	
	try {
		
		DaoManager daoManager = DaoConfig.getDaoManager();
		ArchivoAdjuntoDao archivoAdjuntoDao = (ArchivoAdjuntoDao) daoManager.getDao(ArchivoAdjuntoDao.class);
		
		return archivoAdjuntoDao.obtenerArchivosPorGrupoCarga(id_carga, id_grupo_archivo);
		
	} catch ( Exception e ) {
		SimpleLogger.setError("Ha ocurrido un error ", e);
		return null;
	}
	
}
	
}

package com.osmosyscol.datasuite.persistencia.dao;

import java.util.Date;
import java.util.List;

import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.GrupoArchivoAdjunto;

public interface ArchivoAdjuntoDao {

	public Boolean agregarArchivoAdjunto(ArchivoAdjunto adjunto);

	public Integer obtenerSiguiente();

	public List<ArchivoAdjunto> obtenerArchivosAdjuntosPorCarga(Integer id_carga, Boolean internas, Integer id_tipo_archivo);
	
	Long contarArchivosAdjuntosPorCarga(Integer id_carga, Boolean internas, Integer id_tipo_archivo);
	
	public ArchivoAdjunto obtenerArchivoAdjunto(Integer id_archivo_adjunto);

	public Boolean actualizarArchivoAdjunto(ArchivoAdjunto adjunto);
	
	public Boolean actualizarEstado(String estado, Integer id_archivo_adjunto);

	public Boolean actualizarDescripcion(Integer id_archivo_adjunto, String descripcion);

	public Boolean actualizarRadicado(Integer id_archivo_adjunto, String radicado, String estado) ;

	public Boolean actualizarRadicadoAdjuntosSegunTipologia(Integer id_carga, String radicado, String estado, Boolean filterInterno, Integer filterIdTipoArchivo);

	public List<ArchivoAdjunto> obtenerAdjuntosSegunTipologia(Integer id_carga,	String estado, Boolean filterInterno, Integer filterIdTipoArchivo);

	public List<String> obtenerRadicadoAdjuntosSegunTipologia(Integer id_carga,	String estado, Boolean filterInterno, Integer filterIdTipoArchivo);
	
	public Boolean borrarArchivo(Integer id_archivo_adjunto);
	
	public Boolean borrarAdjuntos(Integer id_carga);
	
	public List<ArchivoAdjunto> obtenerArchivosAdjuntosPorCargaTipo (Integer id_carga, Integer id_tipo_archivo);
	
	public Boolean actualizarResumenHash (Integer id_archivo_adjunto, String resumen_hash);
	
	public Boolean actualizarTipoArchivo (Integer id_carga, Integer id_tipo_archivo_actual, Integer id_tipo_archivo_nuevo);
	
	public Integer actualizarDocumentosSalidaInterno (Date fecha);
	
	public List<GrupoArchivoAdjunto> obtenerGruposArchivoPorCarga (Integer id_carga);
	
	public List<ArchivoAdjunto> obtenerArchivosPorGrupoCarga (Integer id_carga, Integer id_grupo_archivo);
}

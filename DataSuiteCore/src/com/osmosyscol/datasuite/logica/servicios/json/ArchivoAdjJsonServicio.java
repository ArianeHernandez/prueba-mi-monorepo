package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.GrupoArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.TipoArchivo;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;

public class ArchivoAdjJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public List<ArchivoAdjunto> obtenerArchivosAdjuntosPorCarga(Integer id_carga) {

		Integer id_usuario = (Integer) session.getAttribute("id_usuario");

		return ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCarga(id_carga, id_usuario == null, null);
	}

	public Integer obtenerSiguiente() {
		return ArchivoAdjuntoServicio.getInstance().obtenerSiguiente();
	}

	public ArchivoAdjunto obtenerArchivoAdjunto(Integer id_archivo_adjunto) {
		return ArchivoAdjuntoServicio.getInstance().obtenerArchivoAdjunto(id_archivo_adjunto);
	}

	public Boolean aprobarAdjunto(Integer id_archivo_adjunto) {

		ArchivoAdjunto adjunto = ArchivoAdjuntoServicio.getInstance().obtenerArchivoAdjunto(id_archivo_adjunto);

		if (adjunto == null || !"C".equals(adjunto.getEstado())) {
			return false;
		}

		return ArchivoAdjuntoServicio.getInstance().actualizarEstado("A", id_archivo_adjunto);
	}

	public Boolean descartarAdjunto(Integer id_archivo_adjunto) {

		ArchivoAdjunto adjunto = ArchivoAdjuntoServicio.getInstance().obtenerArchivoAdjunto(id_archivo_adjunto);

		if (adjunto == null || !"C".equals(adjunto.getEstado())) {
			return false;
		}

		return ArchivoAdjuntoServicio.getInstance().actualizarEstado("D", id_archivo_adjunto);
	}
	
	public Boolean actualizarDescripcion(Integer id_archivo_adjunto, String descripcion) {
		return ArchivoAdjuntoServicio.getInstance().actualizarDescripcion(id_archivo_adjunto, descripcion);
	}
	
	public List<TipoArchivo> obtenerTiposArchivo() {
		return ArchivoAdjuntoServicio.getInstance().obtenerTiposArchivo();
	}
	
	public Boolean borrarAdjunto(Integer id_archivo_adjunto) {
		return ArchivoAdjuntoServicio.getInstance().borrarArchivo(id_archivo_adjunto);
	}
	
	public Boolean borrarAdjuntos(Integer id_carga) {
		return ArchivoAdjuntoServicio.getInstance().borrarAdjuntos(id_carga);
	}

	public List<GrupoArchivoAdjunto> obtenerGruposArchivoPorCarga(Integer id_carga) {

		return ArchivoAdjuntoServicio.getInstance().obtenerGruposArchivoPorCarga(id_carga);
	}
	
	public List<ArchivoAdjunto> obtenerArchivosPorGrupoCarga(Integer id_carga, Integer id_grupo_archivo) {

		return ArchivoAdjuntoServicio.getInstance().obtenerArchivosPorGrupoCarga(id_carga, id_grupo_archivo);
	}
}

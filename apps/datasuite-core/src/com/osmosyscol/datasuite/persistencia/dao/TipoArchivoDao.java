package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.TipoArchivo;

public interface TipoArchivoDao {

	public Boolean adicionarTipoArchivoAdjunto(Integer id_tipo_adjunto, Integer id_archivo_adjunto);

	public List<TipoArchivo> obtenerTiposArchivo();
	
	public List<TipoArchivo> obtenerTodoTiposArchivo();
	
	public TipoArchivo actualizarTipoArchivo(TipoArchivo tipoArchivo);
	
	public TipoArchivo guardarTipoArchivo(TipoArchivo tipoArchivo);
	
	public Boolean eliminarTipoArchivo(TipoArchivo tipoArchivo);
	
	public TipoArchivo obtenerTipoArchivo (Integer id_tipo_archivo);
	
	public List<String> obtenerExtensionesPorTipoArchivo (Integer id_tipo_archivo);

}

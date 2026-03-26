package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.TipoArchivo;
import com.osmosyscol.datasuite.logica.servicios.TipoArchivoServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.persistencia.dao.TipoArchivoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class TipoArchivoJsonServicio implements JsonService {
	private Session session;
	
	public List<TipoArchivo> obtenerTipoArchivo() {
		return TipoArchivoServicio.getInstance().obtenerTiposArchivo();
	}
	
	public List<TipoArchivo> obtenerTodoTipoArchivo() {
		return TipoArchivoServicio.getInstance().obtenerTodoTiposArchivo();
	}
	
	public TipoArchivo guardarTipoArchivo(TipoArchivo tipoArchivo) {
		return TipoArchivoServicio.getInstance().guardarTipoArchivo(tipoArchivo, null);
	}
	
	public TipoArchivo actualizarTipoArchivo(TipoArchivo tipoArchivo) {
		return TipoArchivoServicio.getInstance().actualizarTipoArchivo(tipoArchivo, null);
	}
	
	public List<String> obtenerExtensionesPorIdCampo(Integer id_campo) {
		Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);
		
		if (campo != null && campo.getId_tipo_archivo() != null) {
			return TipoArchivoServicio.getInstance().obtenerExtensionesPorTipoArchivo(campo.getId_tipo_archivo());			
		} else {
			return null;
		}
		
	}
	
	public List<String> obtenerExtensionesPorTipoArchivo(Integer id_tipo_archivo) {
		return TipoArchivoServicio.getInstance().obtenerExtensionesPorTipoArchivo(id_tipo_archivo);
	}

	public void setSession(Session session) {
		this.session = session;
	}

}

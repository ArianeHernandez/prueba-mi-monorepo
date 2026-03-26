package com.osmosyscol.datasuite.near.servicios;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.htsoft.commons.lang.StringUtils;

import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class InfoRespuestaRequerimientoLocalServicio {
	private static InfoRespuestaRequerimientoLocalServicio _instance = new InfoRespuestaRequerimientoLocalServicio(); 
	private InfoRespuestaRequerimientoLocalServicio(){}
	public static InfoRespuestaRequerimientoLocalServicio getInstance() {
		return _instance;
	}
	
	static final Logger logger = LoggerFactory.getLogger(InfoRespuestaRequerimientoLocalServicio.class);
	
	public RespuestaRequerimiento buscarInfoLocal(Integer idCarga) {
		RespuestaRequerimiento info = DS_SqlUtils.queryForObject(RespuestaRequerimiento.class, 
				"select * " + 
				"  from $respuesta requerimiento$ " +
				" where idcarga = " + idCarga);
		
		return info;
	}

	public String buscarInfoLocal_RadicadoPostal(Integer idCarga) {
		String radicadoPostal = DS_SqlUtils.queryForObject(String.class, 
				"select $respuesta requerimiento.numero radicado postal$ " + 
				"  from $respuesta requerimiento$ " +
				" where idcarga = " + idCarga);
		
		return radicadoPostal;
	}
	
	public Boolean sincronizarRequerimientoLocalConRadicadoPostal(Integer idCarga, String idRadicadoPostal) {
		
		RDServicio.verificarData();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("radicado_postal", StringUtils.trimToNull(idRadicadoPostal));

		Boolean updated = DS_SqlUtils.update(
				"update $respuesta requerimiento$" +
				" set $respuesta requerimiento.numero radicado postal$=#radicado_postal#" +
				" WHERE IDCARGA = " + idCarga, params );
		
		logger.info("SYNC: sincronizarRequerimientoLocal (end) {} ", updated );
		
		return updated;
	}
	

}

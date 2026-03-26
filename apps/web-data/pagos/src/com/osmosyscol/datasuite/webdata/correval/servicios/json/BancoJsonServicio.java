package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.webdata.correval.beneficiarios.BeneficiarioServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.BancoServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.RetiroServicio;

public class BancoJsonServicio  implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public List<Map<String,Object>> obtenerRespuestasBanco(Integer bancoGirador) {		
		return BancoServicio.getInstance().obtenerRespuestasBanco(bancoGirador);
	}

}

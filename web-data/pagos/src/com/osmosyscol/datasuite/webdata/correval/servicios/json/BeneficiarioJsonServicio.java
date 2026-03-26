package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.webdata.correval.beneficiarios.BeneficiarioServicio;

public class BeneficiarioJsonServicio  implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public Boolean eliminarBeneficiario(Integer registro){
		Integer id_usuario = (Integer)session.getAttribute("id_usuario"); 
		return BeneficiarioServicio.getInstance().eliminarBeneficiario(registro, id_usuario);
	}

	
}

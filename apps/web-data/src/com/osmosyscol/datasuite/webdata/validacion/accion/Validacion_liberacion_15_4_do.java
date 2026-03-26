package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.ArrayList;
import java.util.List;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.servicios.ParametroInicioServicio;
import com.osmosyscol.datasuite.utils.ValidacionUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorListaDinamicaCampo;
import com.osmosyscol.datasuite.webdata.logica.servicios.FlujoCargaServicio;

public class Validacion_liberacion_15_4_do implements ValidacionAccion{
	
	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
		try {
			if(ParametroInicioServicio.getInstance().validarSaldo()) {
				String id_carga =  (String) request.getSession().getAttribute("var.id_carga");
				List<ValorListaDinamicaCampo> listaValoresDinamicos = ValidacionUtils.createListObjectRequest("listaValoresDinamicos", ValorListaDinamicaCampo.class, request);
				if(id_carga != null && listaValoresDinamicos != null) {
					if(!FlujoCargaServicio.getInstance().validarSaldo(Integer.parseInt(id_carga), listaValoresDinamicos)) {
						resultadoValidacion.setPermitido(false);
						resultadoValidacion.setSiguientePagina("/liberacion/15.3.do");
					}
				}
			}
		}catch (Exception e) {
			SimpleLogger.setError("Error en liberacion al validar saldo: ", e);
			resultadoValidacion.setPermitido(false);
			resultadoValidacion.setSiguientePagina("/liberacion/15.3.do");
		}
	
		return resultadoValidacion;
	}


}

package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.ArrayList;
import java.util.List;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.google.gson.Gson;
import com.osmosyscol.commons.validacion.DefaultBaseAccion;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Atributo;
import com.osmosyscol.datasuite.logica.dto.ValorAtributo;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.logica.servicios.AtributoServicio;
import com.osmosyscol.datasuite.logica.servicios.ValorAtributoServicio;

public class Validacion_info_administrativo_38_do implements ValidacionAccion, DefaultBaseAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		Session session = request.getSession();
		Integer idUsuario = (Integer) session.getAttribute("id_usuario");
//		String tipoPersona = (String) session.getAttribute("tipo_persona");
//		String identificacion = (String) session.getAttribute("identificacion");
//		Integer tipoDocumento = (Integer) session.getAttribute("tipo_documento");

		List<Atributo> listaAtributos = AtributoServicio.getInstance().obtenerAtributosUsuario(idUsuario);
		
		List<Administrativo> listaAdminis = AdministrativoServicio.getInstance().obtenerAdministrativosPor(idUsuario);
		
		for (Atributo atributo : listaAtributos) {
			
			List<Administrativo> listaAux = AdministrativoServicio.getInstance().obtenerAdministrativosPor(idUsuario);
			
			for (Administrativo administrativo : listaAux) {
				ValorAtributo valorAtributo = new ValorAtributo();
				valorAtributo.setId_atributo(atributo.getId_atributo());
				valorAtributo.setId_administrativo(administrativo.getId_administrativo());
				
				List<ValorAtributo> listaVals = new ArrayList<ValorAtributo>(administrativo.getListValores());
				listaVals.addAll(ValorAtributoServicio.getInstance().obtenerValorAtributo(valorAtributo));
				
				administrativo.setListValores(listaVals);
			}
			atributo.setListAdministrativos(listaAux);
		}
		
		request.setAttribute("listaAtributos", new Gson().toJson(listaAtributos));
		request.setAttribute("listaAdminis", new Gson().toJson(listaAdminis));
		
		return resultadoValidacion;
	}
}

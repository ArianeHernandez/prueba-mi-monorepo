package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;
import org.apache.commons.lang3.BooleanUtils;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoAdminServicio;
import com.osmosyscol.datasuite.webdata.GeneradorDeCargasOrdenasFiltradas;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.ClienteCarga;

public class Validacion_administracion_carga_proceso_22_1_do implements ValidacionAccion{

	@SuppressWarnings("unchecked")
	public ResultadoValidacion validar(Request request) {
		
		
		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
		
		Session session = request.getSession();

		Integer id_persona = (Integer) session.getAttribute("id_persona");
		
		
		Boolean rta = PersonaServicio.getInstance().esPersonaRolActiva(id_persona, Constantes.ROL_ADMINISTRATIVO );
		
		if(!rta){
			resultadoValidacion.setPermitido(rta);
			
			resultadoValidacion.setSiguientePagina("/inicio/0.do?error=1");
		}
		
		//Generacion de cargas ordenadas y filtradas
		GeneradorDeCargasOrdenasFiltradas generadorDeCargasOrdenasFiltradas = GeneradorDeCargasOrdenasFiltradas.getInstance();
		
		generadorDeCargasOrdenasFiltradas.inicializarVariablesDeOrdenamientoFiltro(request);
		
		 Map<String, Object> resultado = generadorDeCargasOrdenasFiltradas.obtenerCargasOrdenadasFiltradas(request.getSession(), null, null, null, null, null, null);
		 List<Carga> cargas = (List<Carga>)resultado.get("cargas");
		 
		if(cargas!=null && cargas.size()>0){
			request.setAttribute("cargas", cargas);
			
			List<ClienteCarga> listadoClientes = generadorDeCargasOrdenasFiltradas.obtenerListoadoClientesPorListadoCargasFiltradasOrdenadas(cargas);
			
			request.setAttribute("clientesPorCarga", listadoClientes);
		}	
		
		
		//Se cargan las instancias del proceso para ese administrativo con las que se pueden
		//filtrar las cargas
		String id_proceso_admin = (String)request.getSession().getAttribute("var.id_proceso_admin");		
		Integer id_administrativo = (Integer)request.getSession().getAttribute("id_administrativo");
		
		InstanciaServicio instanciaServicio = InstanciaServicio.getInstance();
		List<Instancia> instanciasPorAdministrativo = instanciaServicio.obtenerInstanciasDelProcesoPorAdministrativo(id_administrativo, Integer.parseInt(id_proceso_admin));
		
		if(instanciasPorAdministrativo!=null && instanciasPorAdministrativo.size()>0){
			request.setAttribute("instanciasPorAdministrativo", instanciasPorAdministrativo);
		}
		
		Integer id_formato_salida = FormatoServicio.getInstance().obtenerFormatoSalidaPorProceso(Integer.parseInt(id_proceso_admin));
		List<FormatoCampo> formatoCampos = FormatoServicio.getInstance().obtenerFormatoCampoBandejaEntrada(id_formato_salida);
		
		if (formatoCampos != null && formatoCampos.size()>0) {
			request.setAttribute("camposBandejaEntrada", formatoCampos);
		}
		
		List<FormatoCampo> formatoCamposFiltro = FormatoServicio.getInstance().obtenerFormatoCampoFiltroBandejaEntrada(id_formato_salida);
		
		if (formatoCamposFiltro != null && formatoCamposFiltro.size()>0) {
			request.setAttribute("camposFiltroBandejaEntrada", formatoCamposFiltro);
		}
		
		List<Object> formatoCamposCompletos = FormatoServicio.getInstance().obtenerFormatoCampoCompleto(id_formato_salida, session);
		if (formatoCamposCompletos != null) {
			request.setAttribute("formatoCamposCompletos", formatoCamposCompletos);
		}
		
		String mostrarPendientesAdministrativo = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("PESTANA_PENDIENTES_ADMINISTRATIVOS");
		if (mostrarPendientesAdministrativo != null && BooleanUtils.toBoolean(mostrarPendientesAdministrativo) == false){
			request.setAttribute("mostrarPendientesAdministrativo", false);
		} else {
			request.setAttribute("mostrarPendientesAdministrativo", true);
		}
		
		return resultadoValidacion;
	}
	

	
}

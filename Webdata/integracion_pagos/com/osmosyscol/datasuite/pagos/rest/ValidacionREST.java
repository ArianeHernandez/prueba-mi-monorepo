package com.osmosyscol.datasuite.pagos.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.ListaDinamicaCampoServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.CalculoCategoriaTamanoServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampoValor;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoRequest;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoRequestData;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoResponse;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoResponseData;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

import co.htsoft.commons.net.POST;
import co.htsoft.commons.net.RestService;

@SuppressWarnings("serial")
@WebServlet("/testApi/*")
public class ValidacionREST extends RestService {
	
	Float smmlv = (float) 877803;//TODO modificar por consulta a estructura
	
	@POST("/testApi/method")
	public ValidacionFormatoResponse metodo(ValidacionFormatoRequest entradaValidacion) {

		ValidacionFormatoResponse resp = new ValidacionFormatoResponse();
		
		ValidacionFormatoResponseData response = new ValidacionFormatoResponseData();
		
		List<FormatoCampoValor> campos = entradaValidacion.getData().getListadoCampos();
		
		try {
			
			if (campos != null && !campos.isEmpty()){
				
				FormatoCampoValor campoNumero = null;
				FormatoCampoValor campoFecha = null;
				
				for (FormatoCampoValor campo : campos){
					if ("Numero".equals(campo.getNombre_campo())){
						campoNumero = campo;
					}else if ("Fecha".equals(campo.getNombre_campo())){
						campoFecha = campo;
					}
				}
				
				if (campoNumero == null || StringUtils.esVacio(campoNumero.getValor())){
					throw new Exception("El campo " + campoNumero.getTitulo() + " es obligatorio");
				}else if (campoFecha == null || StringUtils.esVacio(campoFecha.getValor())){
					throw new Exception("El campo " + campoFecha.getTitulo() + " es obligatorio");
				}else {
					Integer a = 10;
					Integer numero = null;
					try{
						numero = Integer.parseInt(campoNumero.getValor(), 10);
					}catch (Exception e) {
						throw new Exception("El campo " + campoNumero.getTitulo() + " no tiene un valor valido");
					}
					Date b = StringUtils.toDate("25/11/2020");
					Date fecha = StringUtils.toDate(campoFecha.getValor());
					if (fecha == null){
						throw new Exception("El campo " + campoFecha.getTitulo() + " no tiene un valor valido");
					}else {
						if (numero > a && !fecha.after(b)){
							throw new Exception("Si el campo " + campoNumero.getTitulo() + " es mayor a " + a + " la " + campoFecha.getTitulo() + " debe ser mayor a " + StringUtils.toStringFormat(b) );
						}else if (numero < a && !fecha.before(b)){
							throw new Exception("Si el campo " + campoNumero.getTitulo() + " es menor o igual a  " + a + " la " + campoFecha.getTitulo() + " debe ser menor a " + StringUtils.toStringFormat(b));
						}else if (numero == a && !StringUtils.toStringFormat(b).equals(StringUtils.toStringFormat(fecha))){
							throw new Exception("Si el campo " + campoNumero.getTitulo() + " es igual a  " + a + " la " + campoFecha.getTitulo() + " debe ser " + StringUtils.toStringFormat(b));
						}
					}
				}
				
			}else {
				throw new Exception("No se encontraron campos para validar");
			}
			
			response.setValido(true);
			response.setMensaje_validacion("Validacion Exitosa");
			
		}catch (Exception e){
			response.setValido(false);
			response.setMensaje_validacion(e.getMessage());
		}
		
		resp.setData(response);

		return resp;
	}
	
	@POST("/testApi/methodFecha")
	public ValidacionFormatoResponse metodoFecha(ValidacionFormatoRequest entradaValidacion) {

		ValidacionFormatoResponse resp = new ValidacionFormatoResponse();
		
		ValidacionFormatoResponseData response = new ValidacionFormatoResponseData();
		
		List<FormatoCampoValor> campos = entradaValidacion.getData().getListadoCampos();
		
		try {
			
			if (campos != null && !campos.isEmpty()){
				
				FormatoCampoValor campoFecha = null;
				
				for (FormatoCampoValor campo : campos){
					campoFecha = campo;
				}
				
				if (campoFecha == null || StringUtils.esVacio(campoFecha.getValor())){
					throw new Exception("El campo " + campoFecha.getTitulo() + " es obligatorio");
				}else {
					Date b = StringUtils.toDate("25/11/2020");
					Date fecha = StringUtils.toDate(campoFecha.getValor());
					if (fecha == null){
						throw new Exception("El campo " + campoFecha.getTitulo() + " no tiene un valor valido");
					}else {
						if (fecha.before(b)){
							throw new Exception("El campo " + campoFecha.getTitulo() + " debe ser mayor a " + StringUtils.toStringFormat(b) );
						}
					}
				}
				
			}else {
				throw new Exception("No se encontraron campos para validar");
			}
			
			response.setValido(true);
			response.setMensaje_validacion("Validacion Exitosa");
			
		}catch (Exception e){
			response.setValido(false);
			response.setMensaje_validacion(e.getMessage());
		}
		
		resp.setData(response);

		return resp;
	}
	
	@POST("/testApi/methodActivo")
	public ValidacionFormatoResponse metodoActivo(ValidacionFormatoRequest entradaValidacion) {

		ValidacionFormatoResponse resp = new ValidacionFormatoResponse();
		
		ValidacionFormatoResponseData response = new ValidacionFormatoResponseData();
		
		List<FormatoCampoValor> campos = entradaValidacion.getData().getListadoCampos();
		
		try {
			
			if (campos != null && !campos.isEmpty()){
				
				FormatoCampoValor campoActivos = null;
				FormatoCampoValor campoPasivos = null;
				FormatoCampoValor campoPatrimonio = null;
				
				for (FormatoCampoValor campo : campos){
					if ("Valor total activos".equals(campo.getTitulo())){
						campoActivos= campo;
					}else if ("Valor total pasivos".equals(campo.getTitulo())){
						campoPasivos = campo;
					}else if ("Valor total patrimonio".equals(campo.getTitulo())){
						campoPatrimonio = campo;
					}
				}
				
				if (campoActivos == null || StringUtils.esVacio(campoActivos.getValor())){
					throw new Exception("El campo " + campoActivos.getTitulo() + " es obligatorio");
				}else if (campoPasivos == null || StringUtils.esVacio(campoPasivos.getValor())){
					throw new Exception("El campo " + campoPasivos.getTitulo() + " es obligatorio");
				}else if (campoPatrimonio == null || StringUtils.esVacio(campoPatrimonio.getValor())){
					throw new Exception("El campo " + campoPatrimonio.getTitulo() + " es obligatorio");
				}else {
					
					Float activos = null;
					try{
						activos = Float.parseFloat(campoActivos.getValor());
					}catch (Exception e) {
						throw new Exception("El campo " + campoActivos.getTitulo() + " no tiene un valor valido");
					}
					Float pasivos = null;
					try{
						pasivos = Float.parseFloat(campoPasivos.getValor());
					}catch (Exception e) {
						throw new Exception("El campo " + campoPasivos.getTitulo() + " no tiene un valor valido");
					}
					Float patrimonio = null;
					try{
						patrimonio = Float.parseFloat(campoPatrimonio.getValor());
					}catch (Exception e) {
						throw new Exception("El campo " + campoPatrimonio.getTitulo() + " no tiene un valor valido");
					}
					
					if (activos != (pasivos + patrimonio)){
						throw new Exception("Los activos deben ser igual a pasivos + patrimonio");
					}
				}
				
			}else {
				throw new Exception("No se encontraron campos para validar");
			}
			
			response.setValido(true);
			response.setMensaje_validacion("Validacion Exitosa");
			
		}catch (Exception e){
			response.setValido(false);
			response.setMensaje_validacion(e.getMessage());
		}
		
		resp.setData(response);

		return resp;
	}
	
	@POST("/testApi/methodActivoSuperiorSMMLV")
	public ValidacionFormatoResponse metodoActivoSuperiorSMMLV(ValidacionFormatoRequest entradaValidacion) {

		ValidacionFormatoResponse resp = new ValidacionFormatoResponse();
		
		ValidacionFormatoResponseData response = new ValidacionFormatoResponseData();
		
		List<FormatoCampoValor> campos = entradaValidacion.getData().getListadoCampos();
		
		try {
			
			if (campos != null && !campos.isEmpty()){
				
				FormatoCampoValor campoActivos = campos.get(0);
				
				if (campoActivos == null || StringUtils.esVacio(campoActivos.getValor())){
					throw new Exception("El campo " + campoActivos.getTitulo() + " es obligatorio");
				}else {
					
					BigDecimal activos = null;
					BigDecimal minimo = CalculoCategoriaTamanoServicio.getInstance().obtenerSalarioMinimo();
					
					try{
						activos = new BigDecimal(campoActivos.getValor());
						
						if (activos.compareTo(BigDecimal.ZERO) < 0) {
							throw new Exception("Campo menor que cero");
						}
						
						minimo = minimo.multiply(new BigDecimal(5000));
						
						if (activos.compareTo(minimo) <= 0){
							response.setValido(false);
							response.setMensaje_validacion("<div class=\"alert alert-danger\">El tipo de solicitud seleccionada <b>NO</b> corresponde "
									+ "con el monto de activos reportados por el deudor. Seleccione en el menú \"Radicar Solicitud\" el tipo de solicitud que "
									+ "corresponda a los activos del deudor. </div>");
							response.setBloquear_formulario(false);
						} else {
							response.setValido(true);
							response.setMensaje_validacion("Validacion Exitosa");						
						}
					}catch (Exception e) {
						SimpleLogger.setError("El campo " + campoActivos.getTitulo() + " no tiene un valor valido", e);
						response.setValido(false);
						response.setMensaje_validacion("<div class=\"alert alert-danger\">El campo " + campoActivos.getTitulo() + " no tiene un valor valido.</div>");
						
					}
				}
				
			}else {
				throw new Exception("No se encontraron campos para validar");
			}
			
			
		}catch (Exception e){
			response.setValido(false);
			response.setMensaje_validacion(e.getMessage());
		}
		
		resp.setData(response);

		return resp;
	}
	
	@POST("/testApi/methodActivoInferiorSMMLV")
	public ValidacionFormatoResponse metodoActivoInferiorSMMLV(ValidacionFormatoRequest entradaValidacion) {

		ValidacionFormatoResponse resp = new ValidacionFormatoResponse();
		
		ValidacionFormatoResponseData response = new ValidacionFormatoResponseData();
		
		List<FormatoCampoValor> campos = entradaValidacion.getData().getListadoCampos();
		
		try {
			
			if (campos != null && !campos.isEmpty()){
				
				FormatoCampoValor campoActivos = campos.get(0);
				
				if (campoActivos == null || StringUtils.esVacio(campoActivos.getValor())){
					throw new Exception("El campo " + campoActivos.getTitulo() + " es obligatorio");
				}else {
					
					BigDecimal activos = null;
					BigDecimal minimo = CalculoCategoriaTamanoServicio.getInstance().obtenerSalarioMinimo();
					
					try{
						activos = new BigDecimal(campoActivos.getValor());
						
						if (activos.compareTo(BigDecimal.ZERO) < 0) {
							throw new Exception("Campo menor que cero");
						}
						
						minimo = minimo.multiply(new BigDecimal(5000));
						
						if (activos.compareTo(minimo) > 0){
							response.setValido(false);
							response.setMensaje_validacion("<div class=\"alert alert-danger\">El tipo de solicitud seleccionada <b>NO</b> corresponde "
									+ "con el monto de activos reportados por el deudor. Seleccione en el menú \"Radicar Solicitud\" el tipo de solicitud que "
									+ "corresponda a los activos del deudor. </div>");
							response.setBloquear_formulario(false);
						} else {
							response.setValido(true);
							response.setMensaje_validacion("Validacion Exitosa");						
						}
					}catch (Exception e) {
						SimpleLogger.setError("El campo " + campoActivos.getTitulo() + " no tiene un valor valido", e);
						response.setValido(false);
						response.setMensaje_validacion("<div class=\"alert alert-danger\">El campo " + campoActivos.getTitulo() + " no tiene un valor valido.</div>");
					}
										
				}
				
			}else {
				throw new Exception("No se encontraron campos para validar");
			}
			
			
		}catch (Exception e){
			response.setValido(false);
			response.setMensaje_validacion(e.getMessage());
		}
		
		resp.setData(response);

		return resp;
	}


	@POST("/testApi/methodValorPositivo")
	public ValidacionFormatoResponse methodValorPositivo(ValidacionFormatoRequest entradaValidacion) {

		ValidacionFormatoResponse resp = new ValidacionFormatoResponse();
		
		ValidacionFormatoResponseData response = new ValidacionFormatoResponseData();
		
		List<FormatoCampoValor> campos = entradaValidacion.getData().getListadoCampos();
		
		try {
			
			if (campos != null && !campos.isEmpty()){
				
				FormatoCampoValor campo = campos.get(0);
				
				if (campo == null || StringUtils.esVacio(campo.getValor())){
					throw new Exception("El campo " + campo.getTitulo() + " es obligatorio");
				}else {
					
					BigDecimal pasivos = null;
					
					try {
						pasivos = new BigDecimal(campo.getValor());						
					} catch (Exception e) {
						response.setValido(false);
						response.setMensaje_validacion("<div class=\"alert alert-danger\">El campo " + campo.getTitulo() + " no tiene un valor valido.</div>");
					}
						
					if (pasivos != null) {
						if (pasivos.compareTo(BigDecimal.ZERO) < 0) {
							response.setValido(false);
							response.setMensaje_validacion("<div class=\"alert alert-danger\">El campo " + campo.getTitulo() + " no tiene un valor valido.</div>");
						} else {
							response.setValido(true);
							response.setMensaje_validacion("Validacion Exitosa");	
						}
					}
				}
				
			}else {
				throw new Exception("No se encontraron campos para validar");
			}
			
			
		}catch (Exception e){
			response.setValido(false);
			response.setMensaje_validacion(e.getMessage());
		}
		
		resp.setData(response);

		return resp;
	}
	
	@POST("/testApi/metodoCampo")
	public ValidacionFormatoResponse metodoCampo(ValidacionFormatoRequest entradaValidacion) {
		
		ValidacionFormatoResponse resp = new ValidacionFormatoResponse();
		
		ValidacionFormatoResponseData response = new ValidacionFormatoResponseData();
		
		response.setValido(false);
		response.setMensaje_validacion("Valor de Campo invalido");
		response.setBloquear_formulario(true);
		
		resp.setData(response);

		return resp;
		
	}

	@POST("/testApi/methodMensajeSupuestoAdmisibilidad")
	public ValidacionFormatoResponse metodoMensajeSupuestoAdmisibilidad(ValidacionFormatoRequest entradaValidacion) {
		ValidacionFormatoResponse resp = new ValidacionFormatoResponse();
		
		ValidacionFormatoResponseData response = new ValidacionFormatoResponseData();
		
		List<FormatoCampoValor> campos = entradaValidacion.getData().getListadoCampos();
		
		Carga carga = CargaServicio.getInstance().obtenerCarga(entradaValidacion.getData().getIdCarga());
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(carga.getId_usuario());
		String identificacion = usuario.getIdentificacion();
		String sql = "select distinct e.$enrolamiento cliente.tipo formulario$ from "
				+ "$enrolamiento cliente$ e, $tipo solicitante$ t where "
				+ "e.$enrolamiento cliente.tipo solicitante$ = t.id and e.$enrolamiento cliente.tipo formulario$ is not null "
				+ "and e.idcarga <> 0 and e.$enrolamiento cliente.numero identificacion$ = $S(" + identificacion + ")$";
		List<Integer> tipo_solicitante = DS_SqlUtils.queryForList(Integer.class, sql);
		
		
		try {
			
			if (campos != null && !campos.isEmpty()){
				
				FormatoCampoValor campo = campos.get(0);
				
				response.setValido(true);
				response.setMensaje_validacion("Validacion Exitosa");
				
				if (Constantes.LISTA_VALORES_SUPUESTOS_INCAPACIDAD_PAGO.equals(campo.getValor()) && Constantes.TIPO_SOLICITANTE_PNC.equals(tipo_solicitante.get(0))) {
//				if (Constantes.LISTA_VALORES_SUPUESTOS_INCAPACIDAD_PAGO.equals(campo.getValor())) {
					response.setValido(false);
					response.setMensaje_validacion("<div class=\"alert alert-danger\">Se pone de presente que, en virtud del Decreto 560 de 15 de abril de 2020, el supuesto denominado incapacidad de pago "
							+ "inminente previsto en el artï¿½culo 9 de la Ley 1116 de 2006, para el proceso de reorganizaciï¿½n se encuentra suspendido.</div>");
					response.setBloquear_formulario(false);
				} else {
					response.setValido(true);
					response.setMensaje_validacion("Validacion Exitosa");
				}
				
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Ocurrio un error ejecutando el servicio methodMensajeSupuestoAdmisibilidad ", e);
			response.setValido(false);
			response.setMensaje_validacion("Error en la ejecucion de la validacion");
		}
		
		
		resp.setData(response);

		return resp;
	}
	
}

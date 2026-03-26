package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.auditoria.dto.Auditoria;
import com.osmosyscol.datasuite.logica.servicios.AuditoriaServicio;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.RespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.consultaretiro.RetiroConsultaServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.RetiroServicio;

public class RetirosJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	// ----------------------------------------------------------------
	// ----------------------------------------------------------------

	public List<Map<String, Object>> obtenerRetirosMultiplesParametros(String identifica_cliente, Integer idcarga, Integer id_banco, Integer tamanoPagina, Integer numeroPagina) {
		return RetiroServicio.getInstance().obtenerRetirosMultiplesParametros(identifica_cliente, idcarga, id_banco, tamanoPagina, numeroPagina);
	}

	public Integer obtenerCantidadRetirosMultiplesParametros(String identifica_cliente, Integer idcarga, Integer id_banco) {
		return RetiroServicio.getInstance().obtenerCantidadRetirosMultiplesParametros(identifica_cliente, idcarga, id_banco);
	}

	public Boolean actualizarRetiroPorRespuestaBanco(String nuevaRespuesta, String idretiro) {

		RespuestaBanco respuestaBanco = new RespuestaBanco();
		respuestaBanco.setCodigo_referencia(idretiro);
		respuestaBanco.setEstado_respuesta(nuevaRespuesta);

		boolean exitoso = (RetiroServicio.getInstance().actualizarRetiroPorRespuestaBanco(respuestaBanco, "Manual") != null);

		// -------------------------------------------

		HttpServletRequest request = (HttpServletRequest) session.getAttribute("HttpServletRequest");
		AuditoriaServicio.getInstance().insertarAuditoria(new Integer(idretiro), "AMRERB", request, exitoso, nuevaRespuesta);

		return exitoso;
	}

	// ----------------------------------------------------------------
	// ----------------------------------------------------------------

	public Integer obtenerTotalRetirosRespuesta(Integer id_carga, Integer id_banco_destino, Integer id_beneficiario, Integer id_banco_girador, String respuesta_banco, String tipo_actualizacion, String valor, String fecha_giro, String conArchivo) {

		try {
			BigDecimal valorbd = null;

			if (StringUtils.isNotBlank(valor)) {
				valorbd = new BigDecimal(valor);
			}

			Date fecha_giro_d = null;

			if (StringUtils.isNotBlank(fecha_giro)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				fecha_giro_d = sdf.parse(fecha_giro);
			}

			return RetiroConsultaServicio.getInstance().obtenerTotalRetirosRespuesta(id_carga, id_banco_destino, id_beneficiario, id_banco_girador, respuesta_banco, tipo_actualizacion, valorbd, fecha_giro_d, conArchivo);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return 0;

	}

	public List<Map<String, Object>> obtenerRetirosRespuesta(Integer id_carga, Integer id_banco_destino, Integer id_beneficiario, Integer id_banco_girador, String respuesta_banco, String tipo_actualizacion, String valor, String fecha_giro, String ordenado_por, String conArchivo, Integer tamano_pagina, Integer pagina_actual) {

		try {

			BigDecimal valorbd = null;

			if (StringUtils.isNotBlank(valor)) {
				valorbd = new BigDecimal(valor);
			}

			Date fecha_giro_d = null;

			if (StringUtils.isNotBlank(fecha_giro)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				fecha_giro_d = sdf.parse(fecha_giro);
			}

			session.setAttribute("RetiroConsulta.id_carga", id_carga);
			session.setAttribute("RetiroConsulta.id_banco_destino", id_banco_destino);
			session.setAttribute("RetiroConsulta.id_beneficiario", id_beneficiario);
			session.setAttribute("RetiroConsulta.id_banco_girador", id_banco_girador);
			session.setAttribute("RetiroConsulta.respuesta_banco", respuesta_banco);
			session.setAttribute("RetiroConsulta.tipo_actualizacion", tipo_actualizacion);
			session.setAttribute("RetiroConsulta.valor", valorbd);
			session.setAttribute("RetiroConsulta.fecha_giro", fecha_giro_d);
			session.setAttribute("RetiroConsulta.conArchivo", conArchivo);

			return RetiroConsultaServicio.getInstance().obtenerRetirosRespuesta(id_carga, id_banco_destino, id_beneficiario, id_banco_girador, respuesta_banco, tipo_actualizacion, valorbd, fecha_giro_d, ordenado_por, conArchivo, tamano_pagina, pagina_actual);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return null;

	}

	public List<Auditoria> obtenerHistorialRetiro(Integer idRetiro) {
		return RetiroServicio.getInstance().obtenerHistorialRetiro(idRetiro);
	}

}

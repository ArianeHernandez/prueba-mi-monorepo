package com.osmosyscol.datasuite.mein.servicios.report;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import co.htsoft.commons.lang.StringUtils;

import com.osmosyscol.datasuite.logica.dto.Nodo;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;

public class SolicitudNearSociedadReport {
	
	private static SolicitudNearSociedadReport instance = new SolicitudNearSociedadReport();
	private static NumberFormat formato_porcentaje = new DecimalFormat("#,##0.00 '%'");
	private static NumberFormat formato_moneda = new DecimalFormat("$ #,##0.00");
	
	private SolicitudNearSociedadReport() {
	}

	public static SolicitudNearSociedadReport getInstance() {
		return instance;
	}
	
	public int obtenerAnio (int restarAnios) {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR) - restarAnios;
	}
	
	public List<Nodo> obtenerDetalleCarga (Integer id_carga) {
		List<Nodo> nodosPadre = new ArrayList<>();
		
		SolicitudNearSociedad solicitud = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCarga(id_carga);
		Map<Integer, String> adjuntos = SolicitudNearSociedadServicio.getInstance().obtenerMapaAdjuntosPorCarga(id_carga, null);
		Map<Integer, String> adjuntosAdicionales = SolicitudNearSociedadServicio.getInstance().obtenerMapaAdjuntosAdicionalesPorCarga(id_carga, 1);
		
		Nodo seccionInicial = obtenerSeccionInicial(solicitud);
		if (seccionInicial.getNodo() != null) nodosPadre.add(seccionInicial);
		
		Nodo seccionDeudor = obtenerSeccionDeudor(solicitud, adjuntos);
		if (seccionDeudor.getNodo() != null) nodosPadre.add(seccionDeudor);
		
		Nodo seccionProfesionales = obtenerSeccionProfesionales(solicitud, adjuntos);
		if (seccionProfesionales.getNodo() != null) nodosPadre.add(seccionProfesionales);
		
		Nodo seccionMemoria = obtenerSeccionMemoria(solicitud, adjuntos);
		if (seccionMemoria.getNodo() != null) nodosPadre.add(seccionMemoria);
		
		Nodo seccionSupuestos = obtenerSeccionSupuestos(solicitud, adjuntos);
		if (seccionSupuestos.getNodo() != null) nodosPadre.add(seccionSupuestos);
		
		Nodo seccionControlante = obtenerSeccionCondicion(solicitud, adjuntos);
		if (seccionControlante.getNodo() != null) nodosPadre.add(seccionControlante);
		
		Nodo seccionRelacion = obtenerSeccionRelacion(solicitud, adjuntos);
		if (seccionRelacion.getNodo() != null) nodosPadre.add(seccionRelacion);
		
		Nodo seccionBienes = obtenerSeccionBienesAcreedores(solicitud, adjuntos);
		if (seccionBienes.getNodo() != null) nodosPadre.add(seccionBienes);
		
		Nodo seccionPropuesta = obtenerSeccionPropuestaNegociacion(solicitud, adjuntos);
		if (seccionPropuesta.getNodo() != null) nodosPadre.add(seccionPropuesta);
		
		Nodo seccionInfoMes = obtenerSeccionInfoMes(solicitud, adjuntos);
		if (seccionInfoMes.getNodo() != null) nodosPadre.add(seccionInfoMes);
		
		Nodo seccionInfoAnual = obtenerSeccionInfoAnual(solicitud);
		if (seccionInfoAnual.getNodo() != null) nodosPadre.add(seccionInfoAnual);
		
		Nodo seccionConjunto = obtenerSeccionConjunto(solicitud, adjuntos);
		if (seccionConjunto.getNodo() != null) nodosPadre.add(seccionConjunto);
		
		Nodo seccionInventario = obtenerSeccionInventario(solicitud, adjuntos);
		if (seccionInventario.getNodo() != null) nodosPadre.add(seccionInventario);
		
		Nodo seccionOtro = obtenerSeccionOtros(solicitud, adjuntos);
		if (seccionOtro.getNodo() != null) nodosPadre.add(seccionOtro);
		
		Nodo seccionPlanNegocios = obtenerSeccionPlanNegocios(solicitud, adjuntos);
		if (seccionPlanNegocios.getNodo() != null) nodosPadre.add(seccionPlanNegocios);
		
		Nodo seccionFlujoCaja = obtenerSeccionFlujoCaja(solicitud, adjuntos);
		if (seccionFlujoCaja.getNodo() != null) nodosPadre.add(seccionFlujoCaja);
		
		Nodo seccionProyectosCalificacion = obtenerSeccionProyectosCalificacion(solicitud, adjuntos);
		if (seccionProyectosCalificacion.getNodo() != null) nodosPadre.add(seccionProyectosCalificacion);
		
		Nodo seccionReporte = obtenerSeccionReporte(solicitud, adjuntos);
		if (seccionReporte.getNodo() != null) nodosPadre.add(seccionReporte);
		
		Nodo seccionDocumentosAdicionales = obtenerSeccionDocumentosAdicionales(solicitud, adjuntosAdicionales);
	    if (seccionDocumentosAdicionales.getNodo() != null) nodosPadre.add(seccionDocumentosAdicionales);

		
		return nodosPadre;
	}
	
	private Nodo obtenerSeccionInicial (SolicitudNearSociedad solicitud) {
		Nodo seccionInicial = new Nodo();
		seccionInicial.setname("Tipo de solicitud de insolvencia");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Tipo de Solicitante");
		nodoHijo.setvalue(solicitud.getTipo_solicitante_obj().getNombre());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Tipo de Solicitud");
		nodoHijo.setvalue(solicitud.getTipo_solicitud().getTipo_solicitud_obj().getNombre());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Norma Aplicable");
		nodoHijo.setvalue("DECRETO 560 DEL 15 DE ABRIL DE 2020. Art. 8");
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionInicial.setNodo(nodosHijo);
		
		return seccionInicial;
	}
	
	private Nodo obtenerSeccionDeudor (SolicitudNearSociedad solicitud, Map<Integer,String> adjuntos) {
		Nodo seccionDeudor = new Nodo();
		seccionDeudor.setname("Identificación del deudor");
		seccionDeudor.setHelpMessage("Artículo 2, Ley 1116 de 2006");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Tipo de identificación");
		nodoHijo.setvalue(solicitud.getDeudor().getDatos_basicos().getTipo_identificacion_nombre());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		if (Constantes.TIPO_SOLICITANTE_PNC.equals(solicitud.getTipo_solicitante()) || Constantes.TIPO_SOLICITANTE_PNNC.equals(solicitud.getTipo_solicitante())) {
			nodoHijo.setname("Cédula del deudor solicitante");
		} else {
			nodoHijo.setname("NIT del deudor solicitante");						
		}
		nodoHijo.setvalue(solicitud.getDeudor().getDatos_basicos().getIdentificacion());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		if (Constantes.TIPO_SOLICITANTE_PNC.equals(solicitud.getTipo_solicitante()) || Constantes.TIPO_SOLICITANTE_PNNC.equals(solicitud.getTipo_solicitante())) {
			nodoHijo.setname("Nombre del Deudor Solicitante");
		} else {
			nodoHijo.setname("Razón Social del Deudor Solicitante");
		}
		nodoHijo.setvalue(solicitud.getDeudor().getDatos_basicos().getNombre_completo());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Código CIIU");
		nodoHijo.setvalue(solicitud.getDeudor().getActividad_economica_nombre());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Macrosector");
		if (solicitud.getDeudor().getMacrosectorObj() != null) {
			nodoHijo.setvalue(solicitud.getDeudor().getMacrosectorObj().getNombre());			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("País de domicilio");
		nodoHijo.setvalue(solicitud.getDeudor().getPais_dane_obj().getNombre());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Departamento de domicilio");
		nodoHijo.setvalue(solicitud.getDeudor().getDepartamentoObj().getNombre_departamento());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Municipio de domicilio");
		nodoHijo.setvalue(solicitud.getDeudor().getMunicipioObj().getNombre_ciudad());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Dirección de notificación");
		nodoHijo.setvalue(solicitud.getDeudor().getDatos_basicos().getDireccion());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Correo electrónico de notificación");
		nodoHijo.setvalue(solicitud.getDeudor().getDatos_basicos().getCorreo());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Teléfono");
		nodoHijo.setvalue(solicitud.getDeudor().getDatos_basicos().getTelefono());
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Naturaleza");
		if (solicitud.getDeudor().getNaturalezaObj() != null) {
			nodoHijo.setvalue(solicitud.getDeudor().getNaturalezaObj().getNombre());			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Porcentaje participación estatal");
		if (StringUtils.isNotBlank(solicitud.getDeudor().getPorcentaje_participacion())) {
			BigDecimal porcentaje = new BigDecimal(solicitud.getDeudor().getPorcentaje_participacion());
			String value = formato_porcentaje.format(porcentaje);
			nodoHijo.setvalue(value);
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Número de empleadas mujeres");
		if (solicitud.getDeudor().getTrabajadores_mujeres() != null) {
			nodoHijo.setvalue(solicitud.getDeudor().getTrabajadores_mujeres().toString());			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Número de empleados hombres");
		if (solicitud.getDeudor().getTrabajadores_hombres() != null) {
			nodoHijo.setvalue(solicitud.getDeudor().getTrabajadores_hombres().toString());			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Representante legal");
		if (solicitud.getDeudor().getRepresentante_legal() != null) {
			nodoHijo.setvalue(solicitud.getDeudor().getRepresentante_legal().getNombre_completo() 
				+ " - " + solicitud.getDeudor().getRepresentante_legal().getIdentificacion());
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificado de Existencia y Representacion Legal");
		if (!Constantes.TIPO_SOLICITANTE_PNNC.equals(solicitud.getTipo_solicitante()) && adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICADO_EXISTENCIA)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICADO_EXISTENCIA));
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificado de Matrícula Mercantil");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICADO_MATRICULA_MERCANTIL)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICADO_MATRICULA_MERCANTIL));
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("El Representante Legal tiene alguna limitación para presentar el proceso");
		if (solicitud.getDeudor().getReplegal_tiene_limitacionObj() != null) {
			nodoHijo.setvalue(solicitud.getDeudor().getReplegal_tiene_limitacionObj().getNombre());			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Documento mediante el cuál el máximo órgano competente autoriza al representante legal");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_DOCUMENTO_AUTORIZACION)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_DOCUMENTO_AUTORIZACION));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionDeudor.setNodo(nodosHijo);
		
		return seccionDeudor;
	}
	
	private Nodo obtenerSeccionProfesionales (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionProfesionales = new Nodo();
		seccionProfesionales.setname("Información de profesionales asociados al deudor");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Contador");
		if (solicitud.getDeudor().getContador() != null) {
			nodoHijo.setvalue(solicitud.getDeudor().getContador().getNombre_completo() + 
					" - " + solicitud.getDeudor().getContador().getIdentificacion());
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Revisor fiscal (Si aplica)");
		if (solicitud.getDeudor().getRevisor_fiscal() != null) {
			nodoHijo.setvalue(solicitud.getDeudor().getRevisor_fiscal().getNombre_completo() +
					" - " + solicitud.getDeudor().getRevisor_fiscal().getIdentificacion());			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Apoderado (Opcional)");
		if (solicitud.getDeudor().getApoderado() != null) {
			nodoHijo.setvalue(solicitud.getDeudor().getApoderado().getNombre_completo() +
					" - " + solicitud.getDeudor().getApoderado().getIdentificacion());			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Poder al abogado para realizar la solicitud");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_PODER_ABOGADO)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_PODER_ABOGADO));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionProfesionales.setNodo(nodosHijo);
		
		return seccionProfesionales;
	}
	
	private Nodo obtenerSeccionMemoria (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionMemoria = new Nodo();
		seccionMemoria.setname("Memoria Explicativa de las Causas de Insolvencia");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("\"Manifiesto que, de conformidad con el artículo 1 del Decreto 560 de 15 de abril de 2020, las causas que dan "
				+ "inicio a este proceso de Negociación de Emergencia de un Acuerdo de Reorganización son consecuencia de los hechos que "
				+ "dieron lugar a la Emergencia Económica, Social y Ecológica declarada mediante el Decreto 417 de 17 de marzo de 2020.\"");
		
		nodoHijo.setvalue("Si"); 
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Memoria explicativa de las causas de insolvencia");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_MEMORIA_EXPLICATIVA)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_MEMORIA_EXPLICATIVA));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionMemoria.setNodo(nodosHijo);
		
		return seccionMemoria;
	}
	
	private Nodo obtenerSeccionSupuestos (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionSupuestos = new Nodo();
		seccionSupuestos.setname("Supuestos de admisibilidad");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		
		if (Constantes.TIPO_SOLICITANTE_PNC.equals(solicitud.getTipo_solicitante())) {
			nodoHijo.setname("La persona natural comerciante se encuentra en el supuesto de");
		} else if (Constantes.TIPO_SOLICITANTE_PNNC.equals(solicitud.getTipo_solicitante())) {
			nodoHijo.setname("La persona natural no comerciante se encuentra en el supuesto de");
		}else {
			nodoHijo.setname("Certifico que la actividad que desarrollo se encuentra en el supuesto de");			
		}
		if (solicitud.getSituacion_presentada_obj() != null) {
			nodoHijo.setvalue(solicitud.getSituacion_presentada_obj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación que acredite la cesación de pagos");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_CESACION_PAGOS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_CESACION_PAGOS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Anexo de la certificación de cesación de pagos");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_ANEXO_CESACION_PAGOS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_ANEXO_CESACION_PAGOS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certifico que la negociación de emergencia del acuerdo de reorganización se adelanta con");
		if (solicitud.getAdel_acreedores_obj() != null) {
			nodoHijo.setvalue(solicitud.getAdel_acreedores_obj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionSupuestos.setNodo(nodosHijo);
		
		return seccionSupuestos;
	}
	
	private Nodo obtenerSeccionCondicion (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionCondicion = new Nodo();
		seccionCondicion.setname("Condición de controlante");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("¿El solicitante es controlante de una sociedad que se encuentra en reorganización?");
		if (solicitud.getChecklist_solicitud_obj() != null && solicitud.getChecklist_solicitud_obj().getSolicitante_controlanteObj() != null) {
			nodoHijo.setvalue(solicitud.getChecklist_solicitud_obj().getSolicitante_controlanteObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("NIT");
		if (solicitud.getDeudor() != null && StringUtils.isNotBlank(solicitud.getDeudor().getNit_sociedad_controlada())) {
			nodoHijo.setvalue(solicitud.getDeudor().getNit_sociedad_controlada()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Nombre de la sociedad");
		if (solicitud.getDeudor() != null && StringUtils.isNotBlank(solicitud.getDeudor().getName_sociedad_controlada())) {
			nodoHijo.setvalue(solicitud.getDeudor().getName_sociedad_controlada()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Radicado solicitud o auto");
		if (solicitud.getDeudor() != null && StringUtils.isNotBlank(solicitud.getDeudor().getRadicado_sociedad_controlada())) {
			nodoHijo.setvalue(solicitud.getDeudor().getRadicado_sociedad_controlada()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación en la que se indique que el deudor es controlante de la sociedad");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_DEUDOR_CONTROLANTE)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_DEUDOR_CONTROLANTE));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Composición del capital de la sociedad controlada");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_COMPOSICION_CAPITAL)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_COMPOSICION_CAPITAL));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificado de Existencia y Representación Legal de la Sociedad");
		if (Constantes.TIPO_SOLICITANTE_PNNC.equals(solicitud.getTipo_solicitante()) && adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICADO_EXISTENCIA)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICADO_EXISTENCIA));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Prueba de ser controlante en sociedad admitida");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_PRUEBA_CONTROLANTE)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_PRUEBA_CONTROLANTE));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionCondicion.setNodo(nodosHijo);
		
		return seccionCondicion;
	}
	
	private Nodo obtenerSeccionRelacion (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionRelacion = new Nodo();
		seccionRelacion.setname("Relación de Pasivos");
		seccionRelacion.setHelpMessage("Artículo 32, Ley 1429 de 2010");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("¿Tiene pasivos por retenciones de carácter obligatorio a favor de autoridades fiscales?");
		if (solicitud.getRelacion_de_pasivos() != null && solicitud.getRelacion_de_pasivos().getPasivos_por_retencionesObj() != null) {
			nodoHijo.setvalue(solicitud.getRelacion_de_pasivos().getPasivos_por_retencionesObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de tener o no pasivos por retenciones obligatorias con el fisco");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_PASIVOS_RETENCIONES_FISCO)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_PASIVOS_RETENCIONES_FISCO));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Plan para la atención de pasivos por retenciones obligatorias con el fisco");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_PLAN_PASIVOS_RETENCIONES_FISCO)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_PLAN_PASIVOS_RETENCIONES_FISCO));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("¿Tiene pasivos por descuentos efectuados a trabajadores?");
		if (solicitud.getRelacion_de_pasivos() != null && solicitud.getRelacion_de_pasivos().getPasivos_por_descuentosObj() != null) {
			nodoHijo.setvalue(solicitud.getRelacion_de_pasivos().getPasivos_por_descuentosObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de tener o no tener pasivos por descuentos efectuados a trabajadores");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_PASIVOS_DESCUENTOS_TRABAJADORES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_PASIVOS_DESCUENTOS_TRABAJADORES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Plan para la atención de pasivos por descuentos efectuados a trabajadores");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_PLAN_PASIVOS_DESCUENTOS_TRABAJADORES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_PLAN_PASIVOS_DESCUENTOS_TRABAJADORES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("¿Tiene pasivos por aportes al sistema de seguridad social?");
		if (solicitud.getRelacion_de_pasivos() != null && solicitud.getRelacion_de_pasivos().getPasivos_por_aportesObj() != null) {
			nodoHijo.setvalue(solicitud.getRelacion_de_pasivos().getPasivos_por_aportesObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de tener o no pasivos por aportes al sistema de seguridad social");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_PASIVOS_APORTES_SSS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_PASIVOS_APORTES_SSS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Plan para la atención de pasivos por aportes al sistema de seguridad social");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_PLAN_PASIVOS_APORTES_SSS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_PLAN_PASIVOS_APORTES_SSS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionRelacion.setNodo(nodosHijo);
		
		return seccionRelacion;
	}
	
	private Nodo obtenerSeccionBienesAcreedores (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionBienesAcreedores = new Nodo();
		seccionBienesAcreedores.setname("Relación completa y detallada de sus bienes y acreedores");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Fecha de la relación de bienes y acreedores");
		if (solicitud.getInformacion_financiera() != null && StringUtils.isNotBlank(solicitud.getInformacion_financiera().getFecha_r_bienes_acreedores())) {
			nodoHijo.setvalue(solicitud.getInformacion_financiera().getFecha_r_bienes_acreedores()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Detalle de bienes");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_DETALLE_BIENES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_DETALLE_BIENES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Relación de los acreedores");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_RELACION_ACREEDORES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_RELACION_ACREEDORES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionBienesAcreedores.setNodo(nodosHijo);
		
		return seccionBienesAcreedores;
	}
	
	private Nodo obtenerSeccionPropuestaNegociacion (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionPropuesta = new Nodo();
		seccionPropuesta.setname("Propuesta para la negociación de deudas");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Propuesta para la negociación de deudas");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_PROPUESTA_NEGOCIACION)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_PROPUESTA_NEGOCIACION));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionPropuesta.setNodo(nodosHijo);
		
		return seccionPropuesta;
	}
	
	private Nodo obtenerSeccionInfoMes (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionInfoMes = new Nodo();
		seccionInfoMes.setname("Información financiera con corte al último día calendario del mes anterior");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Fecha de los estados financieros mes anterior");
		if (solicitud.getInformacion_financiera() != null && StringUtils.isNotBlank(solicitud.getInformacion_financiera().getFecha_estados_financieros())) {
			nodoHijo.setvalue(solicitud.getInformacion_financiera().getFecha_estados_financieros()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor total activos mes anterior");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getValor_activos() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getValor_activos());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor total pasivos mes anterior");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getValor_pasivos() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getValor_pasivos());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor total patrimonio mes anterior");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getValor_patrimonio() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getValor_patrimonio());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Grupo de preparadores de información");
		if (solicitud.getTipo_solicitud() != null && solicitud.getTipo_solicitud().getGrupo_niif_obj() != null) {
			nodoHijo.setvalue(solicitud.getTipo_solicitud().getGrupo_niif_obj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Estados financieros mes anterior (ESF, ERI, EFE, ORI, ECP)");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_ESTADOS_FINANCIEROS_MES_ANTERIOR)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_ESTADOS_FINANCIEROS_MES_ANTERIOR));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Notas y certificación a los estados financieros del mes anterior");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_NOTAS_MES_ANTERIOR)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_NOTAS_MES_ANTERIOR));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionInfoMes.setNodo(nodosHijo);
		
		return seccionInfoMes;
	}

	private Nodo obtenerSeccionInfoAnual (SolicitudNearSociedad solicitud) {
		Nodo seccionInfoAnual = new Nodo();
		seccionInfoAnual.setname("Información financiera con corte al último año anterior");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Fecha de los estados financieros año anterior");
		if (solicitud.getInformacion_financiera() != null && StringUtils.isNotBlank(solicitud.getInformacion_financiera().getFecha_eeff_anio_anterior())) {
			nodoHijo.setvalue(solicitud.getInformacion_financiera().getFecha_eeff_anio_anterior()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor total activos año anterior");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getValor_activos_ultimoanio() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getValor_activos_ultimoanio());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor total pasivos año anterior");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getValor_pasivos_ultimoanio() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getValor_pasivos_ultimoanio());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor total ingresos ordinarios año anterior");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getTotal_ingresos_ordinarios() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getTotal_ingresos_ordinarios());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor total otros ingresos año anterior");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getTotal_otros_ingresos() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getTotal_otros_ingresos());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("¿Tiene inversiones en subsidiarias, negocios conjuntos, asociadas u otras inversiones?");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getTiene_inversionesObj() != null) {
			nodoHijo.setvalue(solicitud.getInformacion_financiera().getTiene_inversionesObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor Total Participación en ganancias (pérdidas) por medición al método de participación");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getValor_p_participacion() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getValor_p_participacion());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor Total Participación en ganancias (pérdidas) por medición al método al costo");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getValor_p_costo() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getValor_p_costo());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Valor Total Participación en ganancias (pérdidas) por medición al método al valor razonable");
		if (solicitud.getInformacion_financiera() != null && solicitud.getInformacion_financiera().getValor_p_razonable() != null) {
			String value = formato_moneda.format(solicitud.getInformacion_financiera().getValor_p_razonable());
			nodoHijo.setvalue(value); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionInfoAnual.setNodo(nodosHijo);
		
		return seccionInfoAnual;
	}

	private Nodo obtenerSeccionConjunto (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionConjunto = new Nodo();
		seccionConjunto.setname("Conjunto completo de estados financieros comparativos");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("¿Usted ha remitido previamente información financiera a la Superintendencia de sociedades?");
		if (solicitud.getChecklist_solicitud_obj() != null && solicitud.getChecklist_solicitud_obj().getRemision_previa_infoObj() != null) {
			nodoHijo.setvalue(solicitud.getChecklist_solicitud_obj().getRemision_previa_infoObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Radicado mediante el cual remitió los estados financieros, notas, certificación y dictamen al 31 de diciembre del último año");
		if (solicitud.getInformacion_financiera() != null && StringUtils.isNotBlank(solicitud.getInformacion_financiera().getUltimo_radicado_dictamen())) {
			nodoHijo.setvalue(solicitud.getInformacion_financiera().getUltimo_radicado_dictamen()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Radicado mediante el cual remitió los estados financieros, notas, certificación y dictamen al 31 de diciembre del penúltimo año");
		if (solicitud.getInformacion_financiera() != null && StringUtils.isNotBlank(solicitud.getInformacion_financiera().getPenultimo_radicado_dictamen())) {
			nodoHijo.setvalue(solicitud.getInformacion_financiera().getPenultimo_radicado_dictamen()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Radicado mediante el cual remitió los estados financieros, notas, certificación y dictamen al 31 de diciembre del antepenúltimo año");
		if (solicitud.getInformacion_financiera() != null && StringUtils.isNotBlank(solicitud.getInformacion_financiera().getAntepenultimo_radicado_dictamen())) {
			nodoHijo.setvalue(solicitud.getInformacion_financiera().getAntepenultimo_radicado_dictamen()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Estados financieros del último año (" + obtenerAnio(1) + ")");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_ESTADOS_FINANCIEROS_ANIO_ANTERIOR)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_ESTADOS_FINANCIEROS_ANIO_ANTERIOR));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Notas y certificación a los estados financieros del último año (" + obtenerAnio(1) + ")");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_NOTAS_ANIO_ANTERIOR)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_NOTAS_ANIO_ANTERIOR));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Dictamen del revisor fiscal del último año (" + obtenerAnio(1) + ")");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_DICTAMEN_ANIO_ANTERIOR)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_DICTAMEN_ANIO_ANTERIOR));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Estados financieros del penúltimo año (" + obtenerAnio(2) + ")");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_ESTADOS_FINANCIEROS_PENULTIMO_ANIO)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_ESTADOS_FINANCIEROS_PENULTIMO_ANIO));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Notas y certificación a los estados financieros del penúltimo año (" + obtenerAnio(2) + ")");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_NOTAS_PENULTIMO_ANIO)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_NOTAS_PENULTIMO_ANIO));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Dictamen del revisor fiscal del penúltimo año (" + obtenerAnio(2) + ")");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_DICTAMEN_PENULTIMO_ANIO)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_DICTAMEN_PENULTIMO_ANIO));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Estados financieros del antepenúltimo año (" + obtenerAnio(3) + ")");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_ESTADOS_FINANCIEROS_ANTEPENULTIMO_ANIO)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_ESTADOS_FINANCIEROS_ANTEPENULTIMO_ANIO));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Notas y certificación a los estados financieros del antepenúltimo año (" + obtenerAnio(3) + ")");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_NOTAS_ANTEPENULTIMO_ANIO)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_NOTAS_ANTEPENULTIMO_ANIO));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Dictamen del revisor fiscal del antepenúltimo año (" + obtenerAnio(3) + ")");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_DICTAMEN_ANTEPENULTIMO_ANIO)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_DICTAMEN_ANTEPENULTIMO_ANIO));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionConjunto.setNodo(nodosHijo);
		
		return seccionConjunto;
	}

	private Nodo obtenerSeccionInventario(SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionInventario = new Nodo();
		seccionInventario.setname("Inventario de activos y pasivos con corte al último día del mes anterior");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Inventario de activos del mes anterior");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_INVENTARIO_ACTIVOS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_INVENTARIO_ACTIVOS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Inventario de pasivos del mes anterior");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_INVENTARIO_PASIVOS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_INVENTARIO_PASIVOS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionInventario.setNodo(nodosHijo);
		
		return seccionInventario;
	}
	
	private Nodo obtenerSeccionOtros (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionOtros = new Nodo();
		seccionOtros.setname("Otros documentos requeridos");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de llevar la contabilidad regular");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_CONTABILIDAD_REGULAR)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_CONTABILIDAD_REGULAR));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Documento de la composición accionaria");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_DOCUMENTO_COMPOSICION_ACCIONARIA)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_DOCUMENTO_COMPOSICION_ACCIONARIA));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);

		nodoHijo = new Nodo();
		nodoHijo.setname("¿Se encuentra en causal de disolución?");
		if (solicitud.getChecklist_solicitud_obj() != null && solicitud.getChecklist_solicitud_obj().getSociedad_en_disolucionObj() != null) {
			nodoHijo.setvalue(solicitud.getChecklist_solicitud_obj().getSociedad_en_disolucionObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación causal de disolución");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_CAUSAL_DISOLUCION)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_CAUSAL_DISOLUCION));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de no encontrarse en causal de disolución");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_NO_CAUSAL_DISOLUCION)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_NO_CAUSAL_DISOLUCION));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("¿Tiene pasivos pensionales a cargo?");
		if (solicitud.getChecklist_solicitud_obj() != null && solicitud.getChecklist_solicitud_obj().getPasivos_pensionalesObj() != null) {
			nodoHijo.setvalue(solicitud.getChecklist_solicitud_obj().getPasivos_pensionalesObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("¿Tiene pasivos pensionales a cargo?");
		if (solicitud.getChecklist_solicitud_obj() != null && solicitud.getChecklist_solicitud_obj().getSociedad_pasivos_pensionaObj() != null) {
			nodoHijo.setvalue(solicitud.getChecklist_solicitud_obj().getSociedad_pasivos_pensionaObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación del monto de las obligaciones causadas por cálculo actuarial");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_MONTO_OBLIGACIONES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_MONTO_OBLIGACIONES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Documento que acredita el cálculo actuarial");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_DOCUMENTO_CALCULO_ACTUARIAL)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_DOCUMENTO_CALCULO_ACTUARIAL));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Aprobación del cálculo actuarial");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_APROBACION_CALCULO_ACTUARIAL)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_APROBACION_CALCULO_ACTUARIAL));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de encontrarse al día en mesadas pensionales, bonos y títulos pensionales");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_MESADAS_PENSIONALES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_MESADAS_PENSIONALES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("¿Es garante, avalista o codeudor de terceros?");
		if (solicitud.getChecklist_solicitud_obj() != null && solicitud.getChecklist_solicitud_obj().getSociedad_garante_codeudorObj() != null) {
			nodoHijo.setvalue(solicitud.getChecklist_solicitud_obj().getSociedad_garante_codeudorObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación en la que se relacione los terceros a los cuales el deudor es (avalista, garante o codeudor de terceros)");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_RELACION_TERCEROS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_RELACION_TERCEROS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de tener o no tener procesos judiciales y relación de los procesos judiciales y de cualquier procedimiento o actuación administrativa de carácter patrimonial");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICADO_PROCESOS_JUDICIALES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICADO_PROCESOS_JUDICIALES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de los ingresos del deudor expedida por su empleador o declaración juramentada de los mismos en caso de ser trabajador independiente");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_INGRESOS_DEUDOR)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_INGRESOS_DEUDOR));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de recursos disponibles para el pago de las obligaciones");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICADO_RECURSOS_DISPONIBLES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICADO_RECURSOS_DISPONIBLES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de tener o no sociedad conyugal o patrimonial vigente");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICADO_SOCIEDAD_CONYUGAL)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICADO_SOCIEDAD_CONYUGAL));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de tener o no obligaciones alimentarias a su cargo con la discriminación de las obligaciones alimentarias a su cargo");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICADO_OBLIGACIONES_ALIMENTARIAS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICADO_OBLIGACIONES_ALIMENTARIAS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Declaración de renta año anterior");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_DECLARACION_RENTA_ANIO_ANTERIOR)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_DECLARACION_RENTA_ANIO_ANTERIOR));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionOtros.setNodo(nodosHijo);
		
		return seccionOtros;
	}

	private Nodo obtenerSeccionPlanNegocios (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionPlan = new Nodo();
		seccionPlan.setname("Plan de negocios");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Plan de negocios de reorganización");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_PLAN_NEGOCIOS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_PLAN_NEGOCIOS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionPlan.setNodo(nodosHijo);
		
		return seccionPlan;
	}
	
	private Nodo obtenerSeccionFlujoCaja (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionFlujoCaja = new Nodo();
		seccionFlujoCaja.setname("Flujo de caja");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Flujo de caja proyectado");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_FLUJO_CAJA)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_FLUJO_CAJA));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionFlujoCaja.setNodo(nodosHijo);
		
		return seccionFlujoCaja;
	}
	
	private Nodo obtenerSeccionProyectosCalificacion (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionProyectosCalificacion = new Nodo();
		seccionProyectosCalificacion.setname("Proyectos de calificación y graduación de créditos y derechos de voto");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("Proyecto de calificación y graduación de créditos y proyecto de determinación de derechos de voto");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_PROYECTOS_CALIFICACION)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_PROYECTOS_CALIFICACION));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionProyectosCalificacion.setNodo(nodosHijo);
		
		return seccionProyectosCalificacion;
	}
	
	private Nodo obtenerSeccionReporte (SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
		Nodo seccionReporte = new Nodo();
		seccionReporte.setname("Reporte de bienes sujetos a garantías mobiliarias");
		
		List<Nodo> nodosHijo = new ArrayList<>();
		
		Nodo nodoHijo = new Nodo();
		nodoHijo.setname("¿Tiene bienes sujetos a garantías mobiliarias?");
		if (solicitud.getChecklist_solicitud_obj() != null && solicitud.getChecklist_solicitud_obj().getGarantias_mobiliariasObj() != null) {
			nodoHijo.setvalue(solicitud.getChecklist_solicitud_obj().getGarantias_mobiliariasObj().getNombre()); 			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación de bienes sujetos a garantías mobiliarias e indicar cuáles de los mismos son necesarios para la actividad del deudor");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_BIENES_GARANTIAS)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_BIENES_GARANTIAS));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Avalúo de los bienes");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_AVALUO_BIENES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_AVALUO_BIENES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		nodoHijo = new Nodo();
		nodoHijo.setname("Certificación que relacione las obligaciones que surgen de la adquisición del bien sujeto a garantía");
		if (adjuntos.containsKey(Constantes.TIPO_ARCHIVO_CERTIFICACION_OBLIGACIONES)) {
			nodoHijo.setvalue(adjuntos.get(Constantes.TIPO_ARCHIVO_CERTIFICACION_OBLIGACIONES));			
		}
		if (nodoHijo.getvalue() != null) nodosHijo.add(nodoHijo);
		
		if(nodosHijo.size() > 0) seccionReporte.setNodo(nodosHijo);
		
		return seccionReporte;
	}
	
	private Nodo obtenerSeccionDocumentosAdicionales(SolicitudNearSociedad solicitud, Map<Integer, String> adjuntos) {
	    Nodo seccionDocumentosAdicionales = new Nodo();
	    seccionDocumentosAdicionales.setname("Documentos Adicionales");

	    List<Nodo> nodosHijo = new ArrayList<>();

	    for (Map.Entry<Integer, String> entry : adjuntos.entrySet()) {
	        Nodo nodoHijo = new Nodo();
	        nodoHijo.setname("Otro documento");
	        nodoHijo.setvalue(entry.getValue());

	        if (nodoHijo.getvalue() != null && !nodoHijo.getvalue().isEmpty()) {  
	            nodosHijo.add(nodoHijo); 
	        }
	    }

	    if (!nodosHijo.isEmpty()) {
	        seccionDocumentosAdicionales.setNodo(nodosHijo);  
	    }

	    return seccionDocumentosAdicionales;
	}

}
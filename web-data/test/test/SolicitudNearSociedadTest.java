package test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.htsoft.commons.lang.P;

import com.google.gson.Gson;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.mein.acciones.ValidarEstadoRequerimientoOP;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.TipoSolicitud;
import com.osmosyscol.datasuite.test.TestUtils;

public class SolicitudNearSociedadTest {
	public static void main(String[] args) throws ParseException {
		TestUtils.startUp();
		String json = "{\r\n  \"deudor\": {\r\n    \"datos_basicos\": {\r\n      \"tipo_identificacion\": \"N\",\r\n      \"identificacion\": \"1111111111\",\r\n      \"fecha_expedicion_doc\": \"May 5, 2020 4:38:51 PM\",\r\n      \"direccion\": \"Calle falsa 1111111\",\r\n      \"nombre_completo\": \"Jennifer Alexandra Hurtado Guatava\",\r\n      \"correo\": \"prueba@htsoft.co\",\r\n      \"celular\": \"1111111111\",\r\n      \"telefono\": \"1111111\",\r\n      \"tarjeta_profesional\": \"Datos basicos 1111111\"\r\n    },\r\n    \"representante_legal\": {\r\n      \"tipo_identificacion\": \"N\",\r\n      \"identificacion\": \"2222222222\",\r\n      \"fecha_expedicion_doc\": \"May 5, 2020 4:38:51 PM\",\r\n      \"direccion\": \"Calle falsa 2222222\",\r\n      \"nombre_completo\": \"Jennifer Alexandra Hurtado Guatava\",\r\n      \"correo\": \"prueba@htsoft.co\",\r\n      \"celular\": \"2222222222\",\r\n      \"telefono\": \"2222222\",\r\n      \"tarjeta_profesional\": \"Representante 2222222\"\r\n    },\r\n    \"contador\": {\r\n      \"tipo_identificacion\": \"N\",\r\n      \"identificacion\": \"3333333333\",\r\n      \"fecha_expedicion_doc\": \"May 5, 2020 4:38:51 PM\",\r\n      \"direccion\": \"Calle falsa 3333333\",\r\n      \"nombre_completo\": \"Jennifer Alexandra Hurtado Guatava\",\r\n      \"correo\": \"prueba@htsoft.co\",\r\n      \"celular\": \"3333333333\",\r\n      \"telefono\": \"3333333\",\r\n      \"tarjeta_profesional\": \"Contador 3333333\"\r\n    },\r\n    \"revisor_fiscal\": {\r\n      \"tipo_identificacion\": \"N\",\r\n      \"identificacion\": \"4444444444\",\r\n      \"fecha_expedicion_doc\": \"May 5, 2020 4:38:51 PM\",\r\n      \"direccion\": \"Calle falsa 4444444\",\r\n      \"nombre_completo\": \"Jennifer Alexandra Hurtado Guatava\",\r\n      \"correo\": \"prueba@htsoft.co\",\r\n      \"celular\": \"4444444444\",\r\n      \"telefono\": \"4444444\",\r\n      \"tarjeta_profesional\": \"revisor_fiscal 4444444\"\r\n    },\r\n    \"porcentaje_participacion\": 0.5,\r\n    \"apoderado\": {\r\n      \"tipo_identificacion\": \"N\",\r\n      \"identificacion\": \"5555555555\",\r\n      \"fecha_expedicion_doc\": \"May 5, 2020 4:38:51 PM\",\r\n      \"direccion\": \"Calle falsa 5555555\",\r\n      \"nombre_completo\": \"Jennifer Alexandra Hurtado Guatava\",\r\n      \"correo\": \"prueba@htsoft.co\",\r\n      \"celular\": \"5555555555\",\r\n      \"telefono\": \"5555555\",\r\n      \"tarjeta_profesional\": \"apoderado 5555555\"\r\n    },\r\n    \"objeto_social\": \"Objeto social deudor\",\r\n    \"naturaleza\": 1\r\n  },\r\n  \"situacion_presentada\": 2,\r\n  \"emergencia_economica\": 1,\r\n  \"informacion_financiera\": {\r\n    \"valor_activos\": 123123123,\r\n    \"valor_pasivos\": 123123123,\r\n    \"valor_patrimonio\": 123123123\r\n  },\r\n  \"certificacion_representan\": {\r\n    \"cuenta_con_autorizacion\": 1,\r\n    \"cuenta_con_autorizacion_o\": \"obs 1\",\r\n    \"contabilidad_regular\": 1,\r\n    \"marco_contabilidad_obs\": \"obs 2\",\r\n    \"ley_222\": 1,\r\n    \"contabilidad_regular_obs\": \"obs 5\",\r\n    \"pasivos_pensionales\": 1,\r\n    \"pasivos_pensionales_obs\": \"obs 4\",\r\n    \"marco_contabilidad\": 1,\r\n    \"ley_222_obs\": \"obs 3\",\r\n    \"decreto_1074\": 0,\r\n    \"decreto_1074_obs\": \"obs 6\"\r\n  },\r\n  \"memoria_explicativa\": \"Memoria explicativa 2335\",\r\n  \"relacion_de_pasivos\": {\r\n    \"pasivos_por_retenciones\": 1,\r\n    \"pasivos_por_descuentos\": 1,\r\n    \"pasivos_por_aportes\": 1,\r\n    \"pasivos\": [\r\n      {\r\n        \"tipo_pasivo\": 2,\r\n        \"valor\": 34343,\r\n        \"persona\": {\r\n          \"tipo_identificacion\": \"N\",\r\n          \"identificacion\": \"6666666666\",\r\n          \"fecha_expedicion_doc\": \"May 5, 2020 4:38:51 PM\",\r\n          \"direccion\": \"Calle falsa 6666666\",\r\n          \"nombre_completo\": \"Jennifer Alexandra Hurtado Guatava\",\r\n          \"correo\": \"prueba@htsoft.co\",\r\n          \"celular\": \"6666666666\",\r\n          \"telefono\": \"6666666\",\r\n          \"tarjeta_profesional\": \"persona_pasivo 6666666\"\r\n        }\r\n      }\r\n    ]\r\n  },\r\n  \"proyecto_de_calificacion\": {\r\n    \"categoria\": 2,\r\n    \"datos_basicos\": {\r\n      \"tipo_identificacion\": \"N\",\r\n      \"identificacion\": \"7777777777\",\r\n      \"fecha_expedicion_doc\": \"May 5, 2020 4:38:51 PM\",\r\n      \"direccion\": \"Calle falsa 7777777\",\r\n      \"nombre_completo\": \"Jennifer Alexandra Hurtado Guatava\",\r\n      \"correo\": \"prueba@htsoft.co\",\r\n      \"celular\": \"7777777777\",\r\n      \"telefono\": \"7777777\",\r\n      \"tarjeta_profesional\": \"datos_basicos_proyecto 7777777\"\r\n    },\r\n    \"acreencia\": {\r\n      \"clase\": 1,\r\n      \"valor_indexado\": 10.0,\r\n      \"documento_soporte\": \"Docuemnto\",\r\n      \"derechos_de_voto\": 12.0,\r\n      \"fecha_vencimiento\": \"May 5, 2020 7:12:28 PM\",\r\n      \"fecha_corte\": \"May 5, 2020 7:12:28 PM\",\r\n      \"obligacion_vencida\": 1,\r\n      \"dias_vencidos\": 2,\r\n      \"porcentaje_participacion\": 12.0,\r\n      \"garantia\": {\r\n        \"obligacion_cubierta\": 1,\r\n        \"identificaci\u00F3n\": \"23\",\r\n        \"valor_del_bien\": 1235,\r\n        \"valor_maximo\": 245,\r\n        \"bien_necesario\": 1\r\n      }\r\n    }\r\n  },\r\n  \"procesos_ejecutivos\": {\r\n    \"numero_proceso\": \"90450938-2\",\r\n    \"juzgado\": \"Juzgado 234\",\r\n    \"nombre_demandante\": \"Michael Schumaher\",\r\n    \"correo_notificacion\": \"michael@shumaher.com\",\r\n    \"valor\": 3123233\r\n  },\r\n  \"certificacion_revisor_fis\": {\r\n    \"cuenta_con_autorizacion\": 1,\r\n    \"cuenta_con_autorizacion_o\": \"obs 123\",\r\n    \"contabilidad_regular\": 1,\r\n    \"marco_contabilidad_obs\": \"obs 222\",\r\n    \"contabilidad_regular_obs\": \"obs 222\",\r\n    \"pasivos_pensionales\": 1,\r\n    \"pasivos_pensionales_obs\": \"obs 455\",\r\n    \"marco_contabilidad\": 1\r\n  },\r\n  \"certificacion_contador\": {\r\n    \"cuenta_con_autorizacion\": 0,\r\n    \"cuenta_con_autorizacion_o\": \"obs 1\",\r\n    \"contabilidad_regular\": 0,\r\n    \"marco_contabilidad_obs\": \"obs 1\",\r\n    \"contabilidad_regular_obs\": \"obs 2\",\r\n    \"pasivos_pensionales\": 0,\r\n    \"pasivos_pensionales_obs\": \"pbs 4\",\r\n    \"marco_contabilidad\": 1\r\n  },\r\n  \"numero_radicado_postal\": \"456444\",\r\n  \"checklist_solicitud\": {\r\n    \"existencia_cerl\": true,\r\n    \"vigencia_cerl\": false,\r\n    \"matricula_vigente\": true,\r\n    \"apoderado_valido\": false,\r\n    \"notificaciones\": true,\r\n    \"coherencia_solicitud\": false,\r\n    \"supervisada_ssoc\": true,\r\n    \"solicitud_por_representan\": false,\r\n    \"apoderado_existente\": true,\r\n    \"no_pasivos_ss\": false,\r\n    \"plan_pagos_pasivos\": true,\r\n    \"no_pasivos_retenciones\": false,\r\n    \"rev_afirma_contabilidad\": true,\r\n    \"npr_rev_fis\": false,\r\n    \"npr_rep\": true,\r\n    \"no_pasivos_trabajadores\": false,\r\n    \"cesacion_de_pagos\": false,\r\n    \"obligaciones_vencidas\": false,\r\n    \"contabilidad_regular\": true,\r\n    \"repleg_afirma_contabilida\": false,\r\n    \"contador_afirma_contabili\": true\r\n  }\r\n}";
		SolicitudNearSociedad solicitud = new Gson().fromJson(json, SolicitudNearSociedad.class);

		solicitud.getInformacion_financiera().setTotal_ingresos_ordinarios(new BigDecimal(1234));
		solicitud.getInformacion_financiera().setTotal_otros_ingresos(new BigDecimal(1234));
		
		solicitud.getDeudor().setTrabajadores_hombres(50);
		solicitud.getDeudor().setTrabajadores_mujeres(50);
		solicitud.getDeudor().setRepresentante_legal(null);
		solicitud.getRelacion_de_pasivos().setPasivos(null);
		solicitud.setProyecto_de_calificacion(null);
		solicitud.setProcesos_ejecutivos(null);
//		P.println(solicitud);
		
//		solicitud.getDeudor().setCiudad(1234);
		solicitud.getDeudor().setMacrosector(2);
		solicitud.getDeudor().setActividad_economica("2592");
//		solicitud.getDeudor().setTamano_empresa(123);
		TipoSolicitud tipo_solicitud = new TipoSolicitud();
		tipo_solicitud.setGrupo_niif(1);
		tipo_solicitud.setMetodo(1);
//		solicitud.setTipo_solicitud(tipo_solicitud );
		
//		SolicitudNearSociedadServicio.getInstance().crearSolicitud(solicitud, 53843, 49318, 45658);
//		Integer id_usuario = 49318;
//		P.println( SolicitudNearSociedadServicio.getInstance().obtenerListaApoderado(id_usuario ) );
//		P.println( SolicitudNearSociedadServicio.getInstance().obtenerListaRepresentanteLegal(id_usuario ) );
//		P.println( SolicitudNearSociedadServicio.getInstance().obtenerListaContador(id_usuario ) );
//		P.println( SolicitudNearSociedadServicio.getInstance().obtenerListaRevisor(id_usuario ) );
		
//		List<ArchivoAdjunto> archivos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCarga( 109750, true );
//		P.println(archivos);
//		SignAppServicio.getInstance().validarToken("1234", 53843);
//		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
//	    Date d1 = sdformat.parse("2020-06-05");
//		
//		ValidarEstadoRequerimientoOP o = new ValidarEstadoRequerimientoOP();
//		P.println(o.expiroFechaVencimiento(d1) );
		
		P.println(ArchivoAdjuntoServicio.getInstance().obtenerTiposArchivo());
		
		System.exit(0);
	}
}

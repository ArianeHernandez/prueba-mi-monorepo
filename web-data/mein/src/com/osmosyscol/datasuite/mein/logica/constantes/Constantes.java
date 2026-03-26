package com.osmosyscol.datasuite.mein.logica.constantes;

import java.util.HashMap;
import java.util.Map;

public class Constantes {

	public static final String MENSAJE_SIN_DATOS_RUES = "Los datos no coinciden en Rues";
	
	public static final Integer TIPO_SOLICITANTE_SOCIEDAD = 1;
	public static final Integer TIPO_SOLICITANTE_PNC = 2;
	public static final Integer TIPO_SOLICITANTE_PNNC = 3;
	public static final Integer TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS = 5;
	
	public static final Integer TIPO_SOLICITUD_NEAR = 1;
	public static final Integer TIPO_SOLICITUD_REORG_ORDINARIA = 2;
	public static final Integer TIPO_SOLICITUD_REORG_ABREVIADA = 3;
	public static final Integer TIPO_SOLICITUD_LIQ_JUDICIAL= 4;
	public static final Integer TIPO_SOLICITUD_LIQ_SIMPLIFICADA = 5;
	public static final Integer TIPO_SOLICITUD_VAL_JUDICIAL = 6;
	
	public static final String PASANTE_SUCCESS_CODE = "0";
	public static final String PASANTE_DATA_ALREADY_CREATED = "2";
	public static final Integer MI_CAMPO_SI = 1;
	public static final Integer MI_CAMPO_NO = 0;
	public static final String PASANTE_CAMPO_SI = "Si";
	public static final String PASANTE_CAMPO_NO = "No";
	
	public static final String VENCIDO = "Vencido";
	public static final String CREADO = "Creado";
	
	public static final String SALARIO = "SMMLV";
	public static final String UNIDAD_V_TRIBUTARIO = "UVT";
	
	public static final Integer ID_FORMATO_SOLICITUD_NEAR = -503;
	public static final Integer ID_NEGOCIO_SOLICITUD_NEAR = 1;
	public static final String ESTRUCTURA_NEAR_SOCIEDAD = "SOLICITUD NEAR SOCIEDAD";
	public static final String ESTRUCTURA_REGIMEN_INSOLVENCIA = "REGIMEN DE INSOLVENCIA";
	public static final String ESTRUCTURA_RESPUESTA_REQUERIMIENTO = "RESPUESTA REQUERIMIENTO";
	
	public static final String LISTA_VALORES_SUPUESTOS_CESACION_PAGO = "1";
	public static final String LISTA_VALORES_SUPUESTOS_INCAPACIDAD_PAGO = "2";
	
	public static final Integer LISTA_VALORES_SITUACION_PRESENTADA_CESACION = 1;
	public static final Integer LISTA_VALORES_SITUACION_PRESENTADA_INCAPACIDAD = 2;
	
	public static final Integer TIPO_RADICADO_AUTO = 1;
	public static final Integer TIPO_RADICADO_OFICIO = 2;
	
	public static final Integer GENERACION_STICKER_FORMATO_REQUERIDO = 1;
	
	public static final Integer TIPO_ARCHIVO_INADMISION = 3;
	public static final Integer TIPO_ARCHIVO_ADMISION = 4;
	public static final Integer TIPO_ARCHIVO_RECHAZO = 5;
	public static final Integer TIPO_ARCHIVO_CERTIFICADO_EXISTENCIA = 11;
	public static final Integer TIPO_ARCHIVO_PODER_ABOGADO = 12;
	public static final Integer TIPO_ARCHIVO_MEMORIA_EXPLICATIVA = 13;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_CESACION_PAGOS = 14;
	public static final Integer TIPO_ARCHIVO_ANEXO_CESACION_PAGOS = 15;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_PASIVOS_RETENCIONES_FISCO = 17;
	public static final Integer TIPO_ARCHIVO_PLAN_PASIVOS_RETENCIONES_FISCO = 18;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_PASIVOS_DESCUENTOS_TRABAJADORES = 19;
	public static final Integer TIPO_ARCHIVO_PLAN_PASIVOS_DESCUENTOS_TRABAJADORES = 20;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_PASIVOS_APORTES_SSS = 21;
	public static final Integer TIPO_ARCHIVO_PLAN_PASIVOS_APORTES_SSS = 22;
	public static final Integer TIPO_ARCHIVO_ESTADOS_FINANCIEROS_MES_ANTERIOR = 23;
	public static final Integer TIPO_ARCHIVO_NOTAS_MES_ANTERIOR = 24;
	public static final Integer TIPO_ARCHIVO_ESTADOS_FINANCIEROS_ANIO_ANTERIOR = 25;
	public static final Integer TIPO_ARCHIVO_NOTAS_ANIO_ANTERIOR = 26;
	public static final Integer TIPO_ARCHIVO_DICTAMEN_ANIO_ANTERIOR = 27;
	public static final Integer TIPO_ARCHIVO_ESTADOS_FINANCIEROS_PENULTIMO_ANIO = 28;
	public static final Integer TIPO_ARCHIVO_NOTAS_PENULTIMO_ANIO = 29;
	public static final Integer TIPO_ARCHIVO_DICTAMEN_PENULTIMO_ANIO = 30;
	public static final Integer TIPO_ARCHIVO_ESTADOS_FINANCIEROS_ANTEPENULTIMO_ANIO = 31;
	public static final Integer TIPO_ARCHIVO_NOTAS_ANTEPENULTIMO_ANIO = 32;
	public static final Integer TIPO_ARCHIVO_DICTAMEN_ANTEPENULTIMO_ANIO = 33;
	public static final Integer TIPO_ARCHIVO_INVENTARIO_ACTIVOS = 34;
	public static final Integer TIPO_ARCHIVO_INVENTARIO_PASIVOS = 35;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_CONTABILIDAD_REGULAR = 36;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_CAUSAL_DISOLUCION = 37;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_NO_CAUSAL_DISOLUCION = 38;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_MONTO_OBLIGACIONES = 39;
	public static final Integer TIPO_ARCHIVO_DOCUMENTO_CALCULO_ACTUARIAL = 40;
	public static final Integer TIPO_ARCHIVO_APROBACION_CALCULO_ACTUARIAL = 41;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_MESADAS_PENSIONALES = 42;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_RELACION_TERCEROS = 43;
	public static final Integer TIPO_ARCHIVO_PLAN_NEGOCIOS = 44;
	public static final Integer TIPO_ARCHIVO_FLUJO_CAJA = 45;
	public static final Integer TIPO_ARCHIVO_PROYECTOS_CALIFICACION = 46;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_BIENES_GARANTIAS = 47;
	public static final Integer TIPO_ARCHIVO_AVALUO_BIENES = 48;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_OBLIGACIONES = 49;
	public static final Integer TIPO_ARCHIVO_CERTIFICACION_DEUDOR_CONTROLANTE = 50;
	public static final Integer TIPO_ARCHIVO_COMPOSICION_CAPITAL = 51;
	public static final Integer TIPO_ARCHIVO_PRUEBA_CONTROLANTE = 52;
	public static final Integer TIPO_ARCHIVO_DETALLE_BIENES = 53;
	public static final Integer TIPO_ARCHIVO_RELACION_ACREEDORES = 54;
	public static final Integer TIPO_ARCHIVO_PROPUESTA_NEGOCIACION = 55;
	public static final Integer TIPO_ARCHIVO_INGRESOS_DEUDOR = 56;
	public static final Integer TIPO_ARCHIVO_CERTIFICADO_RECURSOS_DISPONIBLES = 57;
	public static final Integer TIPO_ARCHIVO_CERTIFICADO_SOCIEDAD_CONYUGAL = 58;
	public static final Integer TIPO_ARCHIVO_CERTIFICADO_OBLIGACIONES_ALIMENTARIAS = 59;
	public static final Integer TIPO_ARCHIVO_DECLARACION_RENTA_ANIO_ANTERIOR = 60;
	public static final Integer TIPO_ARCHIVO_PDF_FORMULARIO = 61;
	public static final Integer TIPO_ARCHIVO_BORRADOR_FORMULARIO = 62;
	public static final Integer TIPO_ARCHIVO_PDF_AUTO_OFICIO = 63;
	public static final Integer TIPO_ARCHIVO_CERTIFICADO_MATRICULA_MERCANTIL = 69;
	public static final Integer TIPO_ARCHIVO_CERTIFICADO_PROCESOS_JUDICIALES = 70;
	public static final Integer TIPO_ARCHIVO_BORRADOR_DEVUELTO = 88;
	public static final Integer TIPO_ARCHIVO_DOCUMENTO_AUTORIZACION = 91;
	public static final Integer TIPO_ARCHIVO_DOCUMENTO_COMPOSICION_ACCIONARIA = 92;
	public static final Integer TIPO_ARCHIVO_DOCUMENTO_ANULADO = 93;
	
	public static final String DEPENDENCIA_GRUPO_ADMISIONES = "460";
	public static final String DEPENDENCIA_ARCHIVO_APOYO_JUDICIAL = "4151";
	
	public static final Integer TRAMITE_RTA_INADMISION = 1;
	
	public static final Integer ID_PROCESOS_CLASES_NEAR = 24;
	public static final Integer ID_PROCESOS_CLASES_REO_ABREVIADA = 23;
	public static final Integer ID_PROCESOS_CLASES_LIQ_SIMPLIFICADA = 26;
	public static final Integer ID_PROCESOS_CLASES_VAL_JUDICIAL = 14;
	
	public static final Long ID_MEDIO_ENVIO_PORTAL_WEB = 1546020L;
	public static final Long ID_TIPO_SEGURIDAD_JERARQUICA = 186736283L;	
	
	public static final Integer TIPO_BORRADOR_ADMISION = 0;
	public static final Integer TIPO_BORRADOR_RECHAZO = 1;
	public static final Integer TIPO_BORRADOR_INADMISION = 2;
	public static final Integer TIPO_BORRADOR_RECHAZO_TERMINO = 3;
	
	public static final String BPM_TIPO_DOCUMENTO_ENTRADA = "Entrada";
	public static final String BPM_TIPO_DOCUMENTO_SALIDA = "Salida";
	
	@SuppressWarnings("serial")
	public static final Map<Integer,String> TIPOS_BORRADOR = new HashMap<Integer,String>(){{
		put(TIPO_BORRADOR_ADMISION, "auto_admisión");
		put(TIPO_BORRADOR_RECHAZO, "auto_rechazo");
		put(TIPO_BORRADOR_INADMISION, "oficio_inadmisión");
		put(TIPO_BORRADOR_RECHAZO_TERMINO, "auto_rechazo_por_termino");
	}};
	
	public static final String WS_INTEGRACION_SIGS_CONSULTA = "Consultar Persona Natural/Juridica";
	public static final String WS_INTEGRACION_SIGS_REGISTRO = "Registrar Persona Natural/Juridica";
	public static final String WS_INTEGRACION_SIGS_ACTUALIZACION = "Actualizar Persona Natural/Juridica";
	public static final String WS_INTEGRACION_POSTAL_RADICACION_ENTRADA = "Radicar Solicitud (Entrada)";
	public static final String WS_INTEGRACION_NUMERO_PROCESO = "Generar Número de Proceso";
	public static final String WS_INTEGRACION_STICKER = "Generar Sticker";
	public static final String WS_INTEGRACION_BPM_SOLICITUD_INICIAL = "Enviar Datos de Solicitud Inicial";
	public static final String WS_INTEGRACION_BPM_RTA_REQ = "Enviar Datos de Solicitud de Respuesta Requerimiento (Otros Procesos)";
	public static final String WS_RESPUESTA_EXITOSA = "Exitoso";
	public static final String WS_RESPUESTA_FALLIDA = "Fallido";
	
	public static final String SSOC_SISTEMA_BPM = "BPM";
	public static final String SSOC_SISTEMA_POSTAL = "Gedes";
	public static final String SSOC_SISTEMA_SIGS = "SIGS";
	public static final String SISTEMA_RUES = "RUES";
	
	public static final String RESPUESTA_EXITOSA_ENVIO_BPM = "Transacción Exitosa!";
	
	public static final String OPERACION_INTERNA_EXITOSA = "Operacion Exitosa";
	
	public static final String JSON_RESPONSE_MSG_OK = "OK";
	public static final String JSON_RESPONSE_STATUS_SUCCESS = "success";
	public static final String JSON_RESPONSE_STATUS_FAILED = "failed";
}

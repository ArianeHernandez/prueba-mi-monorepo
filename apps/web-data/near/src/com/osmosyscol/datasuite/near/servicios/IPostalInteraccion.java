package com.osmosyscol.datasuite.near.servicios;

public interface IPostalInteraccion {
	String CODIGO_ERROR_RADICADO = "-1";

	String PARAM_PUNTOINTERACCION = "PARAM_PUNTOINTERACCION";
	String PARAM_COUNT_ADJUNTOS = "PARAM_COUNT_ADJUNTOS";
	String PARAM_SOLICITUD = "PARAM_SOLICITUD";
	String PARAM_RADICACIONANTERIORNUMERO = "PARAM_RADICACIONANTERIORNUMERO";
	String PARAM_CORRELATION_ID1 = "PARAM_CORRELATION_ID1";
	String PARAM_SOLICITUD_PADRE = "PARAM_SOLICITUD_PADRE";
	

	String PARAM_ADJUNTOS_ESTRUCTURA = "PARAM_ADJUNTOS_ESTRUCTURA";
	String PARAM_ADJUNTOS_LIST = "PARAM_ADJUNTOS_LIST";
	String PARAM_ADJUNTOS_RADICADO_POSTAL = "PARAM_ADJUNTOS_RADICADO_POSTAL";
	String PARAM_ADJUNTOS_CORRELATION_ID1 = "PARAM_ADJUNTOS_CORRELATION_ID1";

	String PUNTOINTERACCION_RADICACION = "postal.puntointeraccion.radicacion";
	String PUNTOINTERACCION_RESPUESTA_REQUERIMIENTO = "postal.puntointeraccion.respuesta_requerimiento";
	String PUNTOINTERACCION_AUTOADMISION = "postal.puntointeraccion.auto_admision";
	String PUNTOINTERACCION_AUTORECHAZO = "postal.puntointeraccion.auto_rechazo";
	String PUNTOINTERACCION_OFICIOINADMISION = "postal.puntointeraccion.oficio_inadmision";
	String PUNTOINTERACCION_OFICIOINADMISION_TEST_MORAPA = "postal.puntointeraccion.oficio_inadmision.funcionario.nombre";
	String PUNTOINTERACCION_RADICACION_AUTOOFICIO = "postal.puntointeraccion.radicacion.autooficio";

	String ENTIDAD_RADICACION = "$solicitud near sociedad$";
	String ENTIDAD_RESPUESTA_REQUERIMIENTO = "$respuesta requerimiento$";
	String ENTIDAD_AUTOADMISION = "$archivo adjunto$";

	int TIPOARCHIVO_OTRODOCUMENTO = 1;
	int TIPOARCHIVO_RADICACION = TIPOARCHIVO_OTRODOCUMENTO;
	int TIPOARCHIVO_RESPUESTA_REQUERIMIENTO = TIPOARCHIVO_OTRODOCUMENTO;
	int TIPOARCHIVO_OFICIOADMISION = 2;
	int TIPOARCHIVO_OFICIOINADMISION = 3;
	int TIPOARCHIVO_AUTOADMISION = 4;
	int TIPOARCHIVO_AUTOINADMISION = 4;

	String SVC_TIPO = "tipo";
	String SVC_TIPODOCUMENTAL_CODIGO = "TIPODOCUMENTAL.codigo";
	String SVC_TIPODOCUMENTAL_ID = "TIPODOCUMENTAL.id";
	String SVC_TIPODOCUMENTAL_NOMBRE = "TIPODOCUMENTAL.nombre";

	String SVC_MEDIODEENVIO_CODIGO = "MEDIODEENVIO.codigo";
	String SVC_MEDIODEENVIO_ID = "MEDIODEENVIO.id";
	String SVC_MEDIODEENVIO_NOMBRE = "MEDIODEENVIO.nombre";

	String SVC_INTERNO_ENTIDAD = "interno.entidad";
	String SVC_INTERNO_TIPOARCHIVO = "interno.tipoArchivo";

	String SVC_TERMINODIAS = "terminoDias";
	String SVC_TERMINODIAS_DOS = "terminoDias.dos";

	String SVC_TRAMITE_CODIGO = "tramite.codigo";
	String SVC_TRAMITE_CODIGO__NEAR = "tramite.codigo__near";
	String SVC_TRAMITE_ID = "tramite.id";
	String SVC_TRAMITE_ID__NEAR = "tramite.id__near";
	String SVC_TRAMITE_NOMBRE = "tramite.nombre";
	String SVC_TRAMITE_NOMBRE__NEAR = "tramite.nombre__near";
	
	String SVC_TRAMITE_PROCESO_CODIGO__NEAR = "tramite.proceso.codigo__near";
	String SVC_TRAMITE_PROCESO_ID__NEAR = "tramite.proceso.id__near";
	String SVC_TRAMITE_PROCESO_CODIGO__RABREVIADA = "tramite.proceso.codigo__rabreviada";
	String SVC_TRAMITE_PROCESO_CODIGO__LSIMPLIFICADA = "tramite.proceso.codigo__lsimplificada";

	String SVC_FUNCIONARIO_ID = "funcionario.id";
	String SVC_FUNCIONARIO_NOMBRE = "funcionario.nombre";

	String SVC_TIPOSEGURIDAD_CODIGO = "TIPOSEGURIDAD.codigo";
	String SVC_TIPOSEGURIDAD_ID = "TIPOSEGURIDAD.id";
	String SVC_TIPOSEGURIDAD_NOMBRE = "TIPOSEGURIDAD.nombre";

	String SVC_DEPENDENCIA_ID = "dependencia.id";
	String SVC_DEPENDENCIA_NOMBRE = "dependencia.nombre";
	String SVC_DEPENDENCIA_ASIGNADA_ID = "dependenciaAsignada.id";
	String SVC_DEPENDENCIA_ASIGNADA_NOMBRE = "dependenciaAsignada.nombre";
	String SVC_CORRESPONSAL_DEPENDENCIA_ID = "corresponsal.dependencia.id";
	String SVC_CORRESPONSAL_DEPENDENCIA_NOMBRE = "corresponsal.dependencia.nombre";

	String SVC_DOCUMENTO_TIPOSEGURIDAD_CODIGO = "documento.TIPOSEGURIDAD.codigo";
	String SVC_DOCUMENTO_TIPOSEGURIDAD_ID = "documento.TIPOSEGURIDAD.id";
	String SVC_DOCUMENTO_TIPOSEGURIDAD_NOMBRE = "documento.TIPOSEGURIDAD.nombre";

	String CONFIGPARAM__INTEGRACION_POSTAL_ENDPOINT = "integracion-postal.endpoint";
	String CONFIGPARAM__INTEGRACION_POSTAL_USUARIO = "integracion-postal.usuario";
	String INTEGRADORPOSTAL_ENDPOINT = "integradorpostal.documentos.endpoint";

	String INTEGRADORPOSTAL_TIPODOC_PRINCIPAL = "integradorpostal.documentos.TIPODOC_PRINCIPAL";
	String INTEGRADORPOSTAL_TIPODOC_ANEXO = "integradorpostal.documentos.TIPODOC_ANEXO";

	String SVC_TIPOCUADERNO_CODIGO = "cuaderno.codigo";
	String SVC_TIPOCUADERNO_ID = "cuaderno.id";
	String SVC_TIPOCUADERNO_NOMBRE = "cuaderno.nombre";
	String SVC_TIPOACTAUCION = "tipoActuacion";
	
	String SVC_TELEFONO = "telefono";
	
	String SVC_TERMINO_DIAS = "telefono";
	
}

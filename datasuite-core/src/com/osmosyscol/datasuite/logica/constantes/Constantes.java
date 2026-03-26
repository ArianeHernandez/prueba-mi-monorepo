package com.osmosyscol.datasuite.logica.constantes;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.bidimap.TreeBidiMap;

public class Constantes {

	
	// --------------------------------------------------
	//Nombres de aplicacion
	// --------------------------------------------------
	public static String NOMBRE_APLICACION_MODELATOS = "modelatos";
	public static String NOMBRE_APLICACION_WEBDATA = "webdata";
	// --------------------------------------------------
	public static final String SEPARADORLISTAVALORES="-";
	
	// --------------------------------------------------
	public static final String FORMATO_FECHA_EXCEL="dd/mm/yyyy";
	// --------------------------------------------------

	public static final String SI = "S";
	public static final String NO = "N";

	// --------------------------------------------------
	
	/** Atributo de sesion con el listado de negocios */
	public static final String NEGOCIOS_USUARIO = "negocios_usuario";

	public static final int CARGA_SIN_NEGOCIO = 0;
	
	public static final Integer INTERVALOS_SEMAFORO_PROCESOS = 3;
	public static Integer TAMANIO_AREA_INTERVALOS=100; 
	
	//---------------------------------------------------
	
	public static final String EXCEPCION_TIPORESTRICCION = "R";
	public static final String EXCEPCION_TIPOADICION = "A";
	
	
	//---------------------------------------------------
	public static final String TIPO_NOTIFICACION_DESTINO = "D";
	public static final String TIPO_NOTIFICACION_ADMINISTRATIVO = "A";
	public static final String TIPO_NOTIFICACION_CORREO = "C";
	public static final String TIPO_NOTIFICACION_LIBERADOR = "L";
	public static final String TIPO_NOTIFICACION_ASESOR_COMERCIAL = "Z";
	
	
	//---------------------------------------------------
	public static final int TIPODOCUMENTO_CEDULA = 1;
	public static final int TIPODOCUMENTO_TIDENTIDAD = 2;
	public static final int TIPODOCUMENTO_CEXTRANJERIA = 3;
	public static final int TIPODOCUMENTO_PASAPORTE = 4;
	public static final int TIPODOCUMENTO_NIT = 10;
	
	// --------------------------------------------------

	public static final int CAMPO_TIPOCAMPO_ESTRUCTURA = 0;
	public static final int CAMPO_TIPOCAMPO_LISTAVALORES = -1;
	public static final int CAMPO_TIPOCAMPO_BOOLEANO = 2;
	public static final int CAMPO_TIPOCAMPO_FECHA = 4;
	public static final int CAMPO_TIPOCAMPO_ENTERO = 5;
	public static final int CAMPO_TIPOCAMPO_FLOTANTE = 7;

	// --------------------------------------------------

	public static final String CAMPO_MULTIPLICIDAD_UNICO = "1...1";
	public static final String CAMPO_MULTIPLICIDAD_MULTIPLE = "1...N";

	// --------------------------------------------------

	public static final String CARGA_ESTADO_BORRADOR = "B";
	public static final String CARGA_ESTADO_REVISION = "V";
	public static final String CARGA_ESTADO_LIBERACION = "L";
	public static final String CARGA_ESTADO_ENVIO = "N";
	public static final String CARGA_ESTADO_SUBIDO = "S";
	public static final String CARGA_ESTADO_APROBADO = "C";
	public static final String CARGA_ESTADO_APLICADA = "A";
	public static final String CARGA_ESTADO_EJECUTADO = "J";
	public static final String CARGA_ESTADO_REQUIERE_ADJUNTO = "W";

	public static final String CARGA_ESTADO_RECHAZADO_CLIENTE = "T";
	public static final String CARGA_ESTADO_ELIMINADO = "E";
	public static final String CARGA_ESTADO_RECHAZADO = "R";
	public static final String CARGA_ESTADO_VALIDACION_ODG = "U";  //Validación ordenador de gasto
	
	// --------------------------------------------------

	public static final String ELEMENTOCARGA_ESTADO_ACTIVO = "A";
	public static final String ELEMENTOCARGA_ESTADO_ELIMINADO = "E";

	// --------------------------------------------------
	// Tipo de operacion para la Tabla de control

	public static final int OPERACION_SUMA = 2;
	public static final int OPERACION_CONTAR = 1;

	// --------------------------------------------------
	// Estados Estructura
	public static final String ESTRUCTURA_ESTADO_ELIMINADA = "E";
	public static final String ESTRUCTURA_ESTADO_ACTIVA = "A";

	// --------------------------------------------------
	// Tipo de Formato

	public static final String FORMATO_SALIDA = "S";
	public static final String FORMATO_ENTRADA = "E";

	// --------------------------------------------------

	public static final int TIPOCARGA_INTERACTIVO = 1;
	public static final int TIPOCARGA_LOTES = 2;
	public static final int TIPOCARGA_ELECTRONICO = 3;
	public static final int TIPOCARGA_ARCHIVO = 4;

	// --------------------------------------------------
	// Roles
	public static final int ROL_ADMIN_WEBDATA = 2;
	public static final int ROL_ADMIN_MODELATOS = 1;
	public static final int ROL_SUP_ADMIN_MODELATOS = 4;
	public static final int ROL_CLIENTE = 3;

	// --------------------------------------------------
	// Variables

	public static final Integer VARIABLE_NOMBRE = -1;
	public static final Integer VARIABLE_APELLIDO = -2;
	public static final Integer VARIABLE_IDENTIFICACION = -3;
	public static final Integer VARIABLE_TELEFONO = -4;
	public static final Integer VARIABLE_DIRECCION = -5;
	public static final Integer VARIABLE_CORREO = -6;
	public static final Integer VARIABLE_LOGIN = -7;
	public static final Integer VARIABLE_FECHA = -8;

	public static final String ROL_ADMINISTRATIVOCL = "ADC";
	public static final String ROL_ADMINISTRATIVO = "ADV";
	public static final String ROL_ADMIN_CLIENTE = "AC";
	public static final String ROL_LIBERADOR = "LIB";
	public static final String ROL_PREPARADOR = "PRE";
	public static final String ROL_REVISOR = "REV";
	public static final String ROL_DIRECTOR_NEGOCIO = "DN";
	public static final String ROL_CLIENTE_NATURAL = "CN";
	
	//---------------------------------------------------
	// Tipo de solicitud
	
	public static final String TIPO_SOLICITUD_INDIVIDUAL = "1";
	public static final String TIPO_SOLICITUD_MASIVO = "2";
	public static final String TIPO_SOLICITUD_ELECTRONICO = "3";
	public static final String TIPO_SOLICITUD_ARCHIVO = "4";
	public static final String INDIVIDUAL = "Individual";
	public static final String MASIVO = "Masivo";
	public static final String ELECTRONICO = "Electronico";
	public static final String SOLICITUD_ARCHIVO = "Archivo";
	
	//-----------------------------------------------------
	
	public static Map<String, String> MAP_TABLAS_ROL = new HashMap<String, String>();

	public static TreeBidiMap MAP_TABLAS_ROLNUM = new TreeBidiMap();

	public static Map<String, String> MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO = new HashMap<String, String>();

	
	static {

		MAP_TABLAS_ROL.put(ROL_ADMIN_CLIENTE, "admincliente");
		MAP_TABLAS_ROL.put(ROL_LIBERADOR, "liberador");
		MAP_TABLAS_ROL.put(ROL_PREPARADOR, "preparador");
		MAP_TABLAS_ROL.put(ROL_REVISOR, "revisor");
		MAP_TABLAS_ROL.put(ROL_DIRECTOR_NEGOCIO, "administrador");
		MAP_TABLAS_ROL.put(ROL_ADMINISTRATIVO, "administrativo");
		MAP_TABLAS_ROL.put(ROL_ADMINISTRATIVOCL, "administrativo");
		
		MAP_TABLAS_ROLNUM.put(ROL_ADMIN_CLIENTE, 3);
		MAP_TABLAS_ROLNUM.put(ROL_DIRECTOR_NEGOCIO, 2); // DIRECTOR NEGOCIO
		MAP_TABLAS_ROLNUM.put(ROL_PREPARADOR, 4);
		MAP_TABLAS_ROLNUM.put(ROL_REVISOR, 5);
		MAP_TABLAS_ROLNUM.put(ROL_LIBERADOR, 6);
		MAP_TABLAS_ROLNUM.put(ROL_CLIENTE_NATURAL, 7); // Cliente Natural
		MAP_TABLAS_ROLNUM.put(ROL_ADMINISTRATIVO, 8); // Administrativo Entidad
		MAP_TABLAS_ROLNUM.put(ROL_ADMINISTRATIVOCL, 9); // Administrativo Cliente
		

		MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.put("APT-ACH-ORION", "ORION");
		MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.put("APT-FISICO-ORION", "ORION");
		MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.put("SCB-ACH-ORION", "ORION");
		MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.put("SCB-FISICO-ORION", "ORION");
		MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.put("APT-ACH-SIF", "SIF");
		MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.put("APT-FISICO-SIF", "SIF");
		MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.put("CC-ACH-SIF", "SIF");
		MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.put("CC-FISICO-SIF", "SIF");
		
		
	}
	
	
	public static final String SEPARADOR_LOGIN = "#";

	public static final String CREDENCIAL_ESTADO_CREADO = "C";

	public static final String CREDENCIAL_ESTADO_PENDIENTE_APROBACION = "P";

	public static final String CREDENCIAL_ESTADO_ACTIVO = "A";
	public static final String CREDENCIAL_ESTADO_INVALIDO = "I";
	public static final String CREDENCIAL_ESTADO_ELIMINADO = "E";

	public static final Integer TAREA_INTERVALO_MINUTOS = Calendar.MINUTE;
	public static final Integer TAREA_INTERVALO_HORAS = Calendar.HOUR_OF_DAY;
	public static final Integer TAREA_INTERVALO_DIAS = Calendar.DAY_OF_MONTH;
	public static final Integer TAREA_INTERVALO_SEMANAS = Calendar.WEEK_OF_YEAR;
	public static final Integer TAREA_INTERVALO_MESES = Calendar.MONTH;
	
	public static Map<Integer, String> MAP_TAREAS_INTERVALOS = new HashMap<Integer, String>();
	
	static{
		MAP_TAREAS_INTERVALOS.put(TAREA_INTERVALO_MINUTOS, "Minutos");
		MAP_TAREAS_INTERVALOS.put(TAREA_INTERVALO_HORAS,"Horas");
		MAP_TAREAS_INTERVALOS.put(TAREA_INTERVALO_DIAS,"Días");
		MAP_TAREAS_INTERVALOS.put(TAREA_INTERVALO_SEMANAS,"Semanas");
		MAP_TAREAS_INTERVALOS.put(TAREA_INTERVALO_MESES,"Meses");
	}

	// Tiempo de espera en secundos del hilo
	public static final Integer TAREA_TIEMPO_SLEEP = 30;
	
	public static final String PERSONA_NATURAL = "N";
	
	public static final String PERSONA_JURIDICA = "J";
	public static final Integer VARIABLE_COMERCIO = 100;
	
	public static final Integer ID_PERSONA_APROB_AUTO = -100;
	
	public static final Integer ID_TIPO_REVISOR_CONTADOR = 1;
	public static final Integer ID_TIPO_REVISOR_REVISOR_F = 2;
	
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";
	public static final String ACCEPT = "Accept";
}

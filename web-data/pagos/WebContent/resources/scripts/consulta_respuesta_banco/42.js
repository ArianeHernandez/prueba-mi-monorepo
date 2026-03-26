var PAGINA_ACTUAL = 1;
var TAMANO_PAGINA = 10;
var NUMERO_REGISTROS = null;
var CANTIDAD_PAGINAS = null;

function buscar() {

	var id_carga = osm_getValor("id_carga");
	var id_beneficiario = osm_getValor("id_beneficiario");
	var valor = osm_getValor("valor");
	var fecha_giro = osm_getValor("fecha_giro");
	
	
	if(!osm_esVacio(id_carga) && !osm_validarNumero(id_carga)){
		osm_alert("El identificador de Carga ingreasado no es valido.");
		osm_setFoco("id_carga");
		return;
	}
	
	if(!osm_esVacio(id_beneficiario) && !osm_validarNumero(id_beneficiario)){
		osm_alert("El numero de identificaci\u00f3n ingresado no es valido.");
		osm_setFoco("id_beneficiario");
		return;
	}

	if(!osm_esVacio(fecha_giro) && !osm_validarFormatoFecha(fecha_giro)){
		osm_alert("La fecha de giro ingresada no es valido.");
		osm_setFoco("fecha_giro");
		return;
	}

	// ---------------------------------
	
	PAGINA_ACTUAL = 1;
	buscarRetiros();

}

function buscarRetiros() {

	osm_block_window();
	osm_verLoader();

	var id_carga = osm_getValor("id_carga");
	var id_banco_destino = osm_getValor("id_banco_destino");
	var id_beneficiario = osm_getValor("id_beneficiario");
	var id_banco_girador = osm_getValor("id_banco_girador");
	var respuesta_banco = osm_getValor("respuesta_banco");
	var tipo_actualizacion = osm_getValor("tipo_actualizacion");
	
	var conArchivo = null;
	
	if(respuesta_banco == "_SR"){
		conArchivo= "N";
		respuesta_banco = "";
	}
	
	var valor = osm_getValor("valor");
	var fecha_giro = osm_getValor("fecha_giro");

	jsonrpc._("retirosServicio.obtenerTotalRetirosRespuesta")(respuestaTotalObtenerRetirosRespuesta, id_carga, id_banco_destino, id_beneficiario, id_banco_girador, respuesta_banco, tipo_actualizacion, valor, fecha_giro, conArchivo);

	osm_setValor('texto pagina', 'Cargando...');
	
	$("#bloque_retiros_encontrados").hide();
	$("#sin_resultados").hide();
}

function respuestaTotalObtenerRetirosRespuesta(res) {

	var id_carga = osm_getValor("id_carga");
	var id_banco_destino = osm_getValor("id_banco_destino");
	var id_beneficiario = osm_getValor("id_beneficiario");
	var id_banco_girador = osm_getValor("id_banco_girador");
	var respuesta_banco = osm_getValor("respuesta_banco");
	var tipo_actualizacion = osm_getValor("tipo_actualizacion");
	var valor = osm_getValor("valor");
	var fecha_giro = osm_getValor("fecha_giro");
	var ordenado_por = osm_getValor("ordenado_por");
	var conArchivo = osm_getValor("conArchivo");

	var conArchivo = null;
	
	if(respuesta_banco == "_SR"){
		conArchivo= "N";
		respuesta_banco = "";
	}
	
	TAMANO_PAGINA = osm_getValorEntero("paginacion");

	NUMERO_REGISTROS = res;

	if (NUMERO_REGISTROS == null) {
		NUMERO_REGISTROS = 0;
	}

	CANTIDAD_PAGINAS = 1 + parseInt((NUMERO_REGISTROS - 1) / TAMANO_PAGINA);

	osm_setValor('texto pagina', 'P\u00e1gina ' + PAGINA_ACTUAL + ' de ' + CANTIDAD_PAGINAS)

	jsonrpc._("retirosServicio.obtenerRetirosRespuesta")(respuestaObtenerRetirosRespuesta, id_carga, id_banco_destino, id_beneficiario, id_banco_girador, respuesta_banco, tipo_actualizacion, valor, fecha_giro, ordenado_por, conArchivo,TAMANO_PAGINA, PAGINA_ACTUAL);
}

function respuestaObtenerRetirosRespuesta(respuesta) {

	$("#cont_retiros").empty();

	if (respuesta != null) {
		var retiros = respuesta.list;

		for ( var i = 0; i < retiros.length; i++) {
			var m = retiros[i].map;
			osm_construirHTML("cont_retiros", "PLANTILLA_FILA", [m.ID_REGISTRO, m.ID_CARGA, m.NOMBRE_CLIENTE, m.ID_BENEFICIARIO, m.NOMBRE_BENEFICIARIO, m.BANCO_DESTINO, m.CUENTA_DESTINO, m.FECHA_GIRO, m.VALOR, m.RESPUESTA_BANCO, m.TIPO_ACTUALIZACION, m.BANCO_GIRADOR ], false);
			
			if(CMP_VISIBLE(m.ID_REGISTRO)){
				// $("#cmp_" + m.ID_REGISTRO ).show(); // TODO Descomentar Para activar
			}
		}
		
		if(retiros.length > 0){
			$("#bloque_retiros_encontrados:hidden").show();
		}else{
			$("#sin_resultados:hidden").show();
		}
		
	}

	osm_unblock_window();
}

/* ****************************************************************** */

function paginacion_inicio() {
	if (PAGINA_ACTUAL > 1) {
		PAGINA_ACTUAL = 1;
		buscarRetiros();
	}
}

function paginacion_final() {
	if (PAGINA_ACTUAL < CANTIDAD_PAGINAS) {
		PAGINA_ACTUAL = CANTIDAD_PAGINAS;
		buscarRetiros();
	}
}

function paginacion_anterior() {
	if (PAGINA_ACTUAL > 1) {
		PAGINA_ACTUAL--;
		buscarRetiros();
	}
}

function paginacion_siguiente() {
	if (PAGINA_ACTUAL < CANTIDAD_PAGINAS) {
		PAGINA_ACTUAL++;
		buscarRetiros();
	}
}

/* ****************************************************************** */

function verVentanaHistorial() {

	osm_setVisible("vn_historialRegistro", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaHistorial() {
	osm_setVisible("vn_historialRegistro", false);
	osm_mostrarSelects("bodyContent");
}

/* ****************************************************************** */

function verDetalle(idRetiro){
	
	respuesta = jsonrpc._("retirosServicio.obtenerHistorialRetiro")(respuestaObtenerDetalleRetiro, idRetiro);

}

function respuestaObtenerDetalleRetiro(respuesta) {

	$("#cont_historial").empty();

	if (respuesta != null) {
		var historiales = respuesta.list;

		for ( var i = 0; i < historiales.length; i++) {
			var m = historiales[i];
			if(m.estado == "Y")
				osm_construirHTML("cont_historial", "PLANTILLA_FILA_HISTORIAL", [m.nuevo_valor, m.fecha_accion_str, m.descripcion, m.nombre_usuario + " [" + m.usuario + "]"], false);
		}
	}
	
	verVentanaHistorial();

//	$("#bloque_retiros_encontrados:hidden").show();
//
//	osm_unblock_window();
}


function dc_ajustar() {
	var pos = osm_getWindowSize();
	$("#iframe_block").css("height", (pos[1] - 220) + "px");
}

osm_listen("resize", window, dc_ajustar);
osm_listen("load", window, dc_ajustar);

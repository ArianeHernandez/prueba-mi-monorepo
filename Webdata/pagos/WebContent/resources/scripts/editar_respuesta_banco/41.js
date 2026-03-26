var PAGINA_ACTUAL = 1;
var TAMANO_PAGINA = 15;
var NUMERO_REGISTROS = null;
var CANTIDAD_PAGINAS = null;


function buscar(){
	
	$("#bloque_retiros_encontrados").hide();
	
	buscarRetiros();
	
}

function buscarRetiros() {

	osm_block_window();
	osm_verLoader();
	
	var identifica_cliente = osm_getValor("identifica_cliente");
	var idcarga = osm_getValor("id_carga");
	var bancoGirador = osm_getValor("id_bancoGirador");
	
	jsonrpc._("retirosServicio.obtenerCantidadRetirosMultiplesParametros")(respuestaObtenerCantidadRetirosMultiplesParametros, identifica_cliente, idcarga, bancoGirador);

	osm_setValor('texto pagina', 'Cargando...')
}

function respuestaObtenerCantidadRetirosMultiplesParametros(res){
	
	var identifica_cliente = osm_getValor("identifica_cliente");
	var idcarga = osm_getValor("id_carga");
	var bancoGirador = osm_getValor("id_bancoGirador");
	
	NUMERO_REGISTROS = res;
	
	if (NUMERO_REGISTROS == null) {
		NUMERO_REGISTROS = 0;
	}

	CANTIDAD_PAGINAS = 1 + parseInt( (NUMERO_REGISTROS - 1) / TAMANO_PAGINA);

	osm_setValor('texto pagina', 'P\u00e1gina ' + PAGINA_ACTUAL + ' de ' + CANTIDAD_PAGINAS)
	
	jsonrpc._("retirosServicio.obtenerRetirosMultiplesParametros")(respuestaObtenerRetirosMultiplesParametros, identifica_cliente, idcarga, bancoGirador, TAMANO_PAGINA, PAGINA_ACTUAL);
}

function respuestaObtenerRetirosMultiplesParametros(respuesta){
	
	$("#cont_retiros").empty();
	
	if (respuesta != null) {
		var retiros = respuesta.list;

		for ( var i = 0; i < retiros.length; i++) {
			var m = retiros[i].map;
			osm_construirHTML("cont_retiros", "PLANTILLA_FILA", [ m.ID_RETIRO, m.ID_CARGA, m.CLIENTE, m.TIPO_RETIRO, m.IDENTIFICACION_BENEFICIARIO, m.BANCO_DESTINO, m.CUENTA_DESTINO, m.FECHA_GIRO, m.BANCO_GIRADOR, m.COD_BANCO_GIRADOR, m.VALOR, m.RESPUESTA_BANCO ], false);
		}
	}

	$("#bloque_retiros_encontrados:hidden").show();
	
	osm_unblock_window();
}

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

function validarEdicion(id_retiro, id_carga, cliente, tipo_retiro, identificacion_beneficiario, banco_destino, cuenta_destino, fecha_giro, banco_girador, cod_banco_girador, valor, respuesta_banco) {
	
	
	osm_setValor("m_id_retiro", id_retiro);
	osm_setValor("m_id_carga", id_carga);
	osm_setValor("m_cliente", cliente);
	osm_setValor("m_tipo_retiro", tipo_retiro);
	osm_setValor("m_identificacion_beneficiario", identificacion_beneficiario);
	osm_setValor("m_banco_destino", banco_destino);
	osm_setValor("m_cuenta_destino", cuenta_destino);
	osm_setValor("m_fecha_giro", fecha_giro);
	osm_setValor("m_banco_girador", banco_girador);
	osm_setValor("m_cod_banco_girador", cod_banco_girador);
	osm_setValor("m_valor", valor);
	osm_setValor("m_respuesta_banco", respuesta_banco);
	
	construirSelector(cod_banco_girador);

	verVentanaValidacion();
}

function construirSelector(banco) {
	
	var respuestas = jsonrpc._("bancosServicio.obtenerRespuestasBanco")(banco);
	if (respuestas != null) {
		respuestas = respuestas.list;
		$("#selector_respuestas_banco").empty();
		for ( var i = 0; i < respuestas.length; i++) {
			addOption('selector_respuestas_banco', respuestas[i].map.RESPUESTA, respuestas[i].map.RESPUESTA);
		}
	}

}

function addOption(selectbox, text, value) {
	var optn = document.createElement("OPTION");
	optn.text = text;
	optn.value = value;
	document.getElementById(selectbox).options.add(optn);
}

function verVentanaValidacion() {

	osm_setVisible("vn_confirmacion", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaValidacion() {
	osm_setVisible("vn_confirmacion", false);
	osm_mostrarSelects("bodyContent");
}

function actualizarRespuestaBancoRetiro() {
	var nuevaRespuesta = osm_strlimpiar(osm_getValor('selector_respuestas_banco'));

	var retiro = osm_getValor("m_id_retiro");
	
	var actualizado = jsonrpc._("retirosServicio.actualizarRetiroPorRespuestaBanco")(nuevaRespuesta, retiro);
	if (actualizado) {
		alert('Se ha actualizado correctamente el retiro')
	} else {
		alert('No se ha podido actualizar correctamente el retiro')
	}
	
	cerrarVentanaValidacion();
	buscarRetiros();
	
}

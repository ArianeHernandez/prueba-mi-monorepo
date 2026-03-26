var PAGINA_ACTUAL = 1;
var TAMANO_PAGINA = 10;
var CANTIDAD_PAGINAS = null;
var NUMERO_REGISTROS = null;


function contarArchivos(){
	osm_verLoader();
	osm_block_window();
	
	$("#div_no_resultados").hide();
	$("#area_resultado").hide("fast");
	
	$("#archivos_obtenidos").empty();
	
	var id_ftp_usuario_archivo = osm_getValor("id_ftp_usuario_archivo");
	var id_formato = osm_getValor("id_formato");
	var id_carga = osm_getValor("id_carga");
	var fecha_inicial = osm_getValor("fecha_inicial");
	var fecha_final = osm_getValor("fecha_final");
	var nombre = osm_getValor("nombre");
	var estado = osm_getValor("estado");
	var id_usuario = osm_getValor("id_usuario");
	var estado_carga = osm_getValor("estado_carga");
	
	jsonrpc._("ordenesPagoServicio.contarBuscarArchivos")(buscarArchivos, id_usuario, id_ftp_usuario_archivo, id_formato, id_carga, fecha_inicial, fecha_final, nombre, estado, estado_carga);
}

function buscarArchivos(total){
	if (total != null){
		NUMERO_REGISTROS = total;
		
		texto_paginacion();
		
		var id_ftp_usuario_archivo = osm_getValor("id_ftp_usuario_archivo");
		var id_formato = osm_getValor("id_formato");
		var id_carga = osm_getValor("id_carga");
		var fecha_inicial = osm_getValor("fecha_inicial");
		var fecha_final = osm_getValor("fecha_final");
		var nombre = osm_getValor("nombre");
		var estado = osm_getValor("estado");
		var id_usuario = osm_getValor("id_usuario");
		var estado_carga = osm_getValor("estado_carga");
		
		jsonrpc._("ordenesPagoServicio.buscarArchivos")(mostrarArchivos, id_usuario, id_ftp_usuario_archivo, id_formato, id_carga, fecha_inicial, fecha_final, nombre, estado, estado_carga, PAGINA_ACTUAL, TAMANO_PAGINA);
	}else {
		osm_alert("Ocurri\u00f3 un error realizando la b\u00fasqueda");
	}
}

function mostrarArchivos(respuesta){
	if (respuesta != null){
		var archivos = respuesta.list;
		
		for ( var i = 0; i < archivos.length; i++) {
			var reg = archivos[i];
			
			var valor_total = (reg.valor_total != null) ? osm_formatoMoneda(reg.valor_total) : "$ --";
			
			var duplicado = (reg.duplicado == 'S') ? "_duplicado" : "";
			
			var estado_carga = (reg.estado_carga != null) ? osm_getValor("estado_" + reg.estado_carga) : "--";
			
			var id_lote = (reg.id_carga != null) ? reg.id_carga : "--";
			
			var total_registros = (reg.total_registros != null) ? reg.total_registros : "--";
			
			osm_construirHTML("archivos_obtenidos", "PLANTILLA_ARCHIVOS", [reg.id_ftp_usuario_archivo, id_lote, reg.nombre, reg.fecha_cargue_formato,
			                                                               reg.nombre_formato, total_registros, valor_total,
			                                                               reg.estado_formato, reg.caracteristica, duplicado, reg.nombre_usuario,
			                                                               estado_carga]);
			if (reg.duplicado == 'S'){
				$("#boton_duplicados_" + reg.id_ftp_usuario_archivo).css("display", "block");
			}
		}
		
	}else {
		osm_alert("Ocurri\u00f3 un error realizando la b\u00fasqueda");
	}
	
	osm_unblock_window();
}

function limpiarFiltros(){
	osm_setValor("id_ftp_usuario_archivo", "");
	osm_setValor("id_formato", "");
	osm_setValor("id_carga", "");
	osm_setValor("fecha_inicial", "");
	osm_setValor("fecha_final", "");
	osm_setValor("nombre", "");
	osm_setValor("estado", "");
	osm_setValor("id_usuario", "");
	osm_setValor("estado_carga", "");
	
	
	$("#sel_cliente").empty();
	
	$("#div_no_resultados").hide();
	$("#area_resultado").hide();
	
}

function numeroElementos(valor){
	if (valor != null){
		TAMANO_PAGINA = valor;
		PAGINA_ACTUAL = 1;
	}
	contarArchivos();
}

function descargarReporte() {
	
	var id_ftp_usuario_archivo = osm_getValor("id_ftp_usuario_archivo");
	var id_formato = osm_getValor("id_formato");
	var id_carga = osm_getValor("id_carga");
	var fecha_inicial = osm_getValor("fecha_inicial");
	var fecha_final = osm_getValor("fecha_final");
	var nombre = osm_getValor("nombre");
	var estado = osm_getValor("estado");
	var id_usuario = osm_getValor("id_usuario");
	var estado_carga = osm_getValor("estado_carga");

	osm_ocultarSelects("bodyContent");
	$("#vn_reporte .bloqueo_ventana_base").hide();
	
	$("#vn_reporte").fadeIn(300, function() {
		$("#vn_reporte .bloqueo_ventana_base").fadeIn(300);
	});
	
	jsonrpc._("ordenesPagoServicio.generarArchivoExcelAdministrativo")(mostrarVentanaReporte, id_usuario, id_ftp_usuario_archivo, id_formato, id_carga, fecha_inicial, fecha_final, nombre, estado, estado_carga);

}

function mostrarVentanaReporte(idArchivo){
	
	if (idArchivo == null){
		alert("ocurri\u00f3 un error generando el reporte");
	}else {
		window.location.assign("../EXCEL?id=" + idArchivo);
	}
	
	$("#vn_reporte .bloqueo_ventana_base").fadeOut(300, function() {
		$("#vn_reporte").fadeOut(300);
	});
	
	osm_mostrarSelects("bodyContent");

}

function verDuplicados(id_ftp_usuario_archivo){
	
	$("#area_duplicados").empty();
	
	osm_ocultarSelects("bodyContent");
	$("#vn_duplicados .bloqueo_ventana_base").hide();
	
	$("#vn_duplicados").fadeIn(300, function() {
		$("#vn_duplicados .bloqueo_ventana_base").fadeIn(300);
	});
	
	jsonrpc._("ordenesPagoServicio.obtenerDuplicados")(mostrarDuplicados, id_ftp_usuario_archivo);
}

function mostrarDuplicados(respuesta){
	if (respuesta != null){
		var archivos = respuesta.list;
		
		for ( var i = 0; i < archivos.length; i++) {
			var reg = archivos[i];
			
			osm_construirHTML("area_duplicados", "PLANTILLA_REG_DUPLICADO", [reg.nombre, reg.fecha_cargue_formato + " - " + reg.md5]);
		}
	}
}

function cerrarVentana(){
	
	$("#vn_duplicados .bloqueo_ventana_base").fadeOut(300, function() {
		$("#vn_duplicados").fadeOut(300);
	});
	
	osm_mostrarSelects("bodyContent");
	
}

function texto_paginacion(){
	
	if (NUMERO_REGISTROS == null) {
		NUMERO_REGISTROS = 0;
	}
	
	if (NUMERO_REGISTROS == 0){
		$("#div_no_resultados").show();
		$("#area_resultado").hide();
	}else {
		$("#div_no_resultados").hide();
		$("#area_resultado").show();
	}

	CANTIDAD_PAGINAS = 1 + parseInt((NUMERO_REGISTROS - 1) / TAMANO_PAGINA);

	osm_setValor('texto pagina', 'P\u00e1gina ' + PAGINA_ACTUAL + ' de ' + CANTIDAD_PAGINAS);
	osm_setValor('texto registros', 'Total: ' + NUMERO_REGISTROS + ' registros ');
	
}

/* ****************************************************************** */

function paginacion_inicio() {
	if (PAGINA_ACTUAL > 1) {
		PAGINA_ACTUAL = 1;
		contarArchivos();
	}
}

function paginacion_final() {
	if (PAGINA_ACTUAL < CANTIDAD_PAGINAS) {
		PAGINA_ACTUAL = CANTIDAD_PAGINAS;
		contarArchivos();
	}
}

function paginacion_anterior() {
	if (PAGINA_ACTUAL > 1) {
		PAGINA_ACTUAL--;
		contarArchivos();
	}
}

function paginacion_siguiente() {
	if (PAGINA_ACTUAL < CANTIDAD_PAGINAS) {
		PAGINA_ACTUAL++;
		contarArchivos();
	}
}

osm_listen("load", window, function(){
	NUMERO_REGISTROS = osm_getValorEntero("totalArchivos");
	texto_paginacion();
});

function rc_ajusteVentana() {
	var pos = osm_getWindowSize();
	$("#area_configuracion").css("height", (pos[1] - 300) + "px").css("overflow", "auto");
}

osm_listen("resize", window, rc_ajusteVentana);
osm_listen("load", window, rc_ajusteVentana);

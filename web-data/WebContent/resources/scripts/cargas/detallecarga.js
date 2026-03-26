var NUMERO_PAGINA = 1;
var URL_DETALLE = null;
var CONJUNTO_FORMATOS;// Formato padre y subformatos
var FORMATO_SELECCIONADO;
var ID_FORMATO_SALIDA;
var CACHE_PESTANAS = new Array();
var CARGANDO_DETALLE_INFO = false;
var URL_DETALLE;
var VALORES_FILTROS = new Array();
var ID_CARGA_DETALLE = null;
var ID_FILE_DETALLE = null;

$(function() {
	osm_timeout(inicializarDetalle, 400);
});

var DC_FILTROS = "";

var TIPOS_CAMPO = null;

var VALIDACION_DETALLE = true;

function dc_filtrar() {

	if (TIPOS_CAMPO == null) {
		TIPOS_CAMPO = jsonrpc._("cargaServicio.obtenerTipoCampos")().list;
	}

	DC_FILTROS = "";
	$("#dc_filtrar input, #dc_filtrar select").each(function() {
		DC_FILTROS = DC_FILTROS + "&" + this.name + "=" + escape(this.value);
		VALORES_FILTROS[this.name] = this.value;
	});

	VALIDACION_DETALLE = true;

	$("#dc_filtrar input").each(function() {

		try {
			var id_tipo_campo = osm_getValor(this.id + "_id_tipocampo");

			if (id_tipo_campo != null && !osm_esVacio(this.value)) {

				id_tipo_campo = parseInt(id_tipo_campo);

				for ( var i = 0; i < TIPOS_CAMPO.length; i++) {
					var tipo_campo = TIPOS_CAMPO[i];

					if (tipo_campo.id_tipocampo == id_tipo_campo) {

						if (!osm_regexTest(this.value, tipo_campo.patron_validacion)) {
							osm_alert("El valor del campo " + osm_getValor(this.id + "_nombre") + " no es valido.");
							VALIDACION_DETALLE = false;
						}
					}
				}
			}
		} catch (e) {
		}

	});

	if (VALIDACION_DETALLE) {
		NUMERO_PAGINA = 1;
		verdetalle();
	}
}

var VALORES_LISTADINAMICA_DETALLE = new Array();
function obtenerValoresListaDinamicaFiltro(id_listadinamica, valor) {

	if (!VALORES_LISTADINAMICA_DETALLE["L" + id_listadinamica]) {
		VALORES_LISTADINAMICA_DETALLE["L" + id_listadinamica] = jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamica")(id_listadinamica, valor);
	}

	return VALORES_LISTADINAMICA_DETALLE["L" + id_listadinamica];

}

function actualizarValoresFiltro() {

	$("#dc_filtrar select").each(function() {

		var lista = obtenerValoresListaDinamicaFiltro(osm_getValorEntero(this.id + "_id_listadinamica"), null);

		var options = this.options;
		if (lista != null) {
			lista = lista.list;
			options.length = 0;
			options[options.length] = new Option("--Todos--", "");
			for ( var i = 0; i < lista.length; i++) {
				var reg = lista[i];
				options[options.length] = new Option(reg.nombre, reg.id);
			}

		}

	});

	$("#dc_filtrar input, #dc_filtrar select").each(function() {
		if (VALORES_FILTROS[this.name]) {
			this.value = VALORES_FILTROS[this.name];
		}
	});
}

function inicializarDetalle() {
	ID_FORMATO_SALIDA = osm_getValor('id_formato_salida');
	jsonrpc._("formatoBaseServicio.obtenerIdsFormatosPorFamilia")(res_inicializarDetalle, ID_FORMATO_SALIDA);
}

function res_inicializarDetalle(data) {
	CONJUNTO_FORMATOS = data.list ? data.list : null;
	FORMATO_SELECCIONADO = ID_FORMATO_SALIDA;

	osm_timeout(verdetalle, 400);
}

function cambiarPestana(id_pestana) {

	if (!CARGANDO_DETALLE_INFO) {
		if (CONJUNTO_FORMATOS != null) {
			osm_setClassname('tab_' + ID_FORMATO_SALIDA, 'bloque_pestanas_pestana');
			for ( var i = 0; i < CONJUNTO_FORMATOS.length; i++) {
				osm_setClassname('tab_' + CONJUNTO_FORMATOS[i], 'bloque_pestanas_pestana');
			}

			osm_setClassname(id_pestana, 'bloque_pestanas_pestana_over');

			FORMATO_SELECCIONADO = parseInt(id_pestana.replace('tab_', ''));
			NUMERO_PAGINA = 1;
			verdetalle();
		}
	}
}

function verdetalle() {

	if (!CARGANDO_DETALLE_INFO) {

		URL_DETALLE = CONTEXTPATH + "/carga/simple_detalle_carga.do?id_formato_salida=" + FORMATO_SELECCIONADO + "&pagina=" + NUMERO_PAGINA + DC_FILTROS

		var iframe = document.getElementById("IFRAME_DETALLE_CARGA");

		if (CACHE_PESTANAS[URL_DETALLE] == null) {
			bloquear_detalle();
			
			try{
				iframe.contentWindow.location.href = URL_DETALLE + '&againstCache=' + Math.random();
			}catch (e) {
				alert(e);
			}

		} else {
			osm_setValor('CONTENIDO_DETALLE', CACHE_PESTANAS[URL_DETALLE]);
			osm_setValor('texto_pagina', 'Pagina ' + NUMERO_PAGINA + ' de ' + osm_getValorEntero('NUMERO_PAGINAS'));

			actualizarValoresFiltro();
		}
	}

}

function bloquear_detalle() {
	CARGANDO_DETALLE_INFO = true;
	$("#CONTENIDO_DETALLE, #paginacion_detalle_carga:visible").fadeOut(400);

}

function desbloquear_detalle() {
	CARGANDO_DETALLE_INFO = false;
	$("#CONTENIDO_DETALLE, #paginacion_detalle_carga").fadeIn(400);
}

function onloadframe(obj) {
	try {

		var iframe = document.getElementById("IFRAME_DETALLE_CARGA");

		var contenido_html = obj.contentWindow.document.getElementById('CONTENIDO_HTML').innerHTML;
		osm_setValor('CONTENIDO_DETALLE', contenido_html);

		try {

			$(".reg_detalle").each(function() {
				var ids = "c" + this.id;
				var id_regi = parseInt(ids.substring(5));

				var visible = true;
				try {
					visible = CMP_VISIBLE(id_regi); // validacion realizada por
					// el componente asociado al
					// formato
				} catch (e) {
				}

				if (visible) {
					$(this).show();
				}

			});

		} catch (e) {
			alert(e);
		}

		CACHE_PESTANAS[URL_DETALLE] = osm_getValor('CONTENIDO_DETALLE');

		desbloquear_detalle();

		osm_setValor('texto_pagina', 'Pagina ' + NUMERO_PAGINA + ' de ' + osm_getValorEntero('NUMERO_PAGINAS'));

		if (osm_getValorEntero('NUMERO_PAGINAS') > 1) {
			$('#paginacion_detalle_carga').show();
		} else {
			$('#paginacion_detalle_carga').hide();
		}

		$('#NOTA_CARGA_DETALLE').hide();

		// -- obtiene los valores de los filtros

		actualizarValoresFiltro();

	} catch (e) {
	}

}

function paginacion_inicio() {

	if (!CARGANDO_DETALLE_INFO) {
		NUMERO_PAGINA = 1;
		verdetalle();
	}
}

function paginacion_final() {
	if (!CARGANDO_DETALLE_INFO) {
		NUMERO_PAGINA = osm_getValorEntero('NUMERO_PAGINAS');
		verdetalle();
	}
}

function paginacion_anterior() {
	if (!CARGANDO_DETALLE_INFO) {
		if (NUMERO_PAGINA > 1) {
			NUMERO_PAGINA--;
			verdetalle();
		}
	}
}

function paginacion_siguiente() {
	if (!CARGANDO_DETALLE_INFO) {
		if (osm_getValorEntero('NUMERO_PAGINAS') > NUMERO_PAGINA) {
			NUMERO_PAGINA++;
			verdetalle();
		}
	}
}

// -------------------------------------------------------------------

var TIEMPO_INICIAL = null;

function verDetalleExcel(id_carga, id_formato) {

	$("#area_botones_descargaexcel").hide();
	$("#msg_barra_progreso_excel").hide();
	$("#barra_progreso_excel").show();
	
	TIEMPO_INICIAL = new Date();
	
	$("#detallecarga_ventana_progresoExcel").show();

	ID_CARGA_DETALLE = id_carga;
	ID_FILE_DETALLE = jsonrpc._("cargaServicio.generarArchivoExcel")(id_carga, id_formato);

	verificarEstadoDetalleExcel();
}

function verificarEstadoDetalleExcel() {

	if (ID_FILE_DETALLE != null) {

		var porcentaje = jsonrpc._("cargaServicio.verificarAvanceDetalleExcel")(ID_FILE_DETALLE);
		
		
		$("#progresoexcel").css("width", (porcentaje*2) + "px");
		
		if(porcentaje>=5){
			osm_setValor("tiempo_transcurridoexcel", osm_tiempofaltantetexto(TIEMPO_INICIAL , porcentaje));
		}
		
		if (porcentaje == 100) {
			
			$("#barra_progreso_excel").hide();
			$("#area_botones_descargaexcel").show();
			$("#msg_barra_progreso_excel").show();
			
		} else {
			osm_timeout(verificarEstadoDetalleExcel, 1000);
		}

	}
}

function descargarArchivoExcel(){
	
	$("#detallecarga_ventana_progresoExcel").hide();
	
	window.open(CONTEXTPATH + '/C' + ID_CARGA_DETALLE + '.downloadexcel?idfile=' + ID_FILE_DETALLE + "&id_carga=" + ID_CARGA_DETALLE, 'file');
	ID_FILE_DETALLE = null;
	
}


// -------------------------------------------------------------------
// Agrega cantidad de registros por pestana

osm_listen("load", window, function(e) {

	$('.valor_pestana').each(function(i) {

		var id_formato = parseInt(this.id.substring(4));

		jsonrpc._("cargaServicio.contarRegistros")(new Function("num_reg", " $('#Pes_" + id_formato + "').html(num_reg); "), id_formato);

	});

});

function dc_ajustar() {
	var pos = osm_getWindowSize();
	$("#iframe_block").css("height", (pos[1] - 220) + "px");
}

osm_listen("resize", window, dc_ajustar);
osm_listen("load", window, dc_ajustar);

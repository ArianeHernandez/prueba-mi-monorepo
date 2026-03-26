var IDS_REPORTES = [ "gruposgiro_registros", "gruposgiro_bancos", "gruposgiro_cuentas", "gruposgiro_carga" ]
var REPORTES = new Array();
var CAMPOS_GRUPO_GIRO = [ "identificacion", "valor", "banco", "cuenta", "id_registro", "producto" ];
var CAMPOS_EDITABLES = [ "cuenta", "fecha" ];

var IDS_CARGAS = new Array();
var VALORES_CARGAS = new Array();

var FECHA_HOY = null;

var TOTAL_REGISTROS = new Array();

var PAGINACION = new Array();

var OBJETOS = new Array();

// Numero maximo de registros a pintar en el detalle de la carga

var MAX_REGISTROS_PINTADO = 10;

var GG_PENDIENTES = 0;

function iniciarGruposGiro() {

	FECHA_HOY = osm_getFecha(new Date().getTime() / 1000);
	
	try {
		// --

		var cargas = $("[name='carga']");
		for ( var i = 0; i < cargas.length; i++) {
			IDS_CARGAS[i] = cargas[i].value;
			VALORES_CARGAS[cargas[i].value] = new Object();
			VALORES_CARGAS[cargas[i].value].registros = new Array();
			VALORES_CARGAS[cargas[i].value].ids_registros = new Array();
		}

		// Crear reportes
		for ( var i = 0; i < IDS_REPORTES.length; i++) {
			REPORTES[i] = jsonrpc._("reporteServicio.crearSubReporte")(IDS_REPORTES[i], null);
		}

		osm_setValor("idReporteGruposGiro", REPORTES[0].idReporte);
		
		// Mostrar datos de cada carga interactiva
		var lista = $("[name='carga_inter']");
		for ( var i = 0; i < lista.length; i++) {
			var id_carga = lista[i].value;
			var params = "id_carga:" + id_carga + ",estado_nuevo:E,inicio:0,registros:1";

			//var func = new Function("rta", "mostrarDatosCarga(rta, '"+id_carga+"')");
			//jsonrpc._("reporteServicio.obtenerDatosReporte")(func,REPORTES[0].idReporte, params);
			
			GG_PENDIENTES++;

			var rta = jsonrpc._("reporteServicio.obtenerDatosReporte")(REPORTES[0].idReporte, params);
			mostrarDatosCarga(rta, id_carga)
		}

		// Mostrar datos de cada carga por lotes
		lista = $("[name='carga_lotes']");
		for ( var i = 0; i < lista.length; i++) {
			var id_carga = lista[i].value;
			var params = "id_carga:" + id_carga;
			
			// var func = new Function("rta", "mostrarDatosCargaLotes(rta, '"+id_carga+"')");
			// jsonrpc._("reporteServicio.obtenerDatosReporte")(func,REPORTES[3].idReporte, params);
			
			GG_PENDIENTES++;

			var rta = jsonrpc._("reporteServicio.obtenerDatosReporte")(REPORTES[3].idReporte, params);
			mostrarDatosCargaLotes(rta, id_carga)
		}

		// link para mostrar bancos
		$("[name='link_ver']").clicktoggle(mostrarRegistrosCarga, ocultarRegistrosCarga);

		// seleccion banco
		$("[name='sel_banco']").click(clickCajaBanco);

		// Cierra listado de bancos
		$("[name='cerrar_sel_banco']").click(clickCerrar);

		if (GG_PENDIENTES==0) {
			finCargaGG();
		}

		$(".date-pick-now").datepicker({
			startDate : FECHA_HOY,
			dateFormat : 'dd/mm/yy',
			dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa' ],
			monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
					'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
			changeMonth : true,
			changeYear : true,
			buttonImage: "../images/general/bg-go.png",
			buttonImageOnly: true,
			showOn: "both"
		});

	} catch (e) {
		alert("Error al obtener datos: " + e);
	}

}

$(function(){
	
	osm_timeout(iniciarGruposGiro, 500);
	
});

var ORDEN_COMANDO = 1;
function seleccionCampoCarga(id_carga, campo, valor, texto, actualizaHijos, actComando) {

	var valor_carga = VALORES_CARGAS[id_carga];

	if (actualizaHijos) {
		var ids_regs = VALORES_CARGAS[id_carga].ids_registros;
		var i;
		for (i = 0; i < ids_regs.length; i++) {
			seleccionCampoRegistro(id_carga, ids_regs[i], campo, valor, texto, true);
		}
	}

	if (campo == "cuenta") {
		osm_setValor("cuenta_pago2_" + id_carga, valor);
	}

	osm_setValor(campo + "_pago_" + id_carga, texto);

	var sel = osm_getObjeto("select_bancos_" + id_carga);
	sel.selectedIndex = 0;
	sel = osm_getObjeto("select_cuenta_" + id_carga);
	sel.selectedIndex = 0;
	var id = "cerrar_sel_banco_" + id_carga;
	setTimeout("$('" + id + "').hide();", 50);

	$('#' + id).click();

	if (actComando) {
		crearComando(id_carga, null, ORDEN_COMANDO++, campo, valor);

		valor_carga[campo] = new Object();
		valor_carga[campo].valor = valor;
		valor_carga[campo].texto = texto;
	}

}

function seleccionCampoRegistro(id_carga, id_registro, campo, valor, texto, nuevo, actComando) {
	if (!VALORES_CARGAS[id_carga] || !VALORES_CARGAS[id_carga].registros[id_registro]) {
		return;
	}

	var registro = VALORES_CARGAS[id_carga].registros[id_registro];

	registro[campo] = new Object();
	registro[campo].valor = valor;
	registro[campo].texto = texto;

	var ob = document.getElementById(campo + "_pago_" + id_carga + "_" + id_registro);
	if (ob.tagName == "INPUT") {
		ob.value = texto;
	} else {
		ob.innerHTML = texto;
	}

	if (campo == "cuenta") {
		ob = document.getElementById("cuenta_pago2_" + id_carga + "_" + id_registro)
		if (ob.tagName == "INPUT") {
			ob.value = valor;
		} else {
			ob.innerHTML = valor;
		}
	}

	if (!nuevo) {

		var sel = osm_getObjeto("select_bancos_" + id_carga + "_" + id_registro);
		sel.selectedIndex = 0;
		sel = osm_getObjeto("select_cuenta_" + id_carga + "_" + id_registro);
		sel.selectedIndex = 0;
		var id = "#cont_sel_banco_" + id_carga + "_" + id_registro;
		setTimeout("$('" + id + "').hide();", 50);
	}

	if (actComando) {
		crearComando(id_carga, id_registro, ORDEN_COMANDO++, campo, valor);

	}
}

function crearComando(id_carga, codigo_registro, orden, campo, valor) {

	if (osm_getObjeto("comando_" + id_carga + "_" + codigo_registro)) {

		var valor_carga = VALORES_CARGAS[id_carga];

		if (osm_esVacio(osm_getValor("comando_fecha_" + id_carga + "_" + codigo_registro)) && valor_carga["fecha"]) {
			osm_setValor("comando_fecha_" + id_carga + "_" + codigo_registro, valor_carga["fecha"].valor);
		}
		if (osm_esVacio(osm_getValor("comando_cuenta_" + id_carga + "_" + codigo_registro)) && valor_carga["cuenta"]) {
			osm_setValor("comando_cuenta_" + id_carga + "_" + codigo_registro, valor_carga["cuenta"].valor);
		}

		osm_setValor("comando_" + campo + "_" + id_carga + "_" + codigo_registro, valor);
		osm_setValor("comando_orden_" + id_carga + "_" + codigo_registro, orden);

	} else {

		var div = $("#div_comandos");

		var fecha = campo == "fecha" ? valor : osm_getValor("fecha_pago_" + id_carga + "_" + codigo_registro);

		var cuenta = campo == "cuenta" ? valor : osm_getValor("cuenta_pago2_" + id_carga + "_" + codigo_registro);

		construirHTML(div, "PLANTILLA_COMANDO", [ id_carga, codigo_registro, orden, cuenta, fecha ]);
	}
}

function mostrarDatosCargaLotes(resultados, id_carga) {

	if (resultados != null) {
		var datos = resultados.datos.list;
		if (datos.length == 1) {
			var reg = datos[0].map;
			osm_setValor("total_reg_" + id_carga, "(" + reg["total"] + ")")
			TOTAL_REGISTROS[id_carga] = reg["total"];
			osm_setValor("producto_" + id_carga, reg["producto"])
			if (!osm_esVacio(reg["cuenta_defecto"])) {
				osm_setValor("cuenta_pago_" + id_carga, reg["banco"] + " - " + reg["cuenta_defecto"])
			}
			osm_setValor("cuenta_pago2_" + id_carga, reg["cuenta_id_defecto"])

			PAGINACION[id_carga] = new Object();
			PAGINACION[id_carga].total_paginas = Math.ceil(TOTAL_REGISTROS[id_carga] / MAX_REGISTROS_PINTADO);
			PAGINACION[id_carga].pagina_actual = 1;
		}
	}
	
	finCargaGG();

}

function mostrarDatosCarga(resultados, id_carga) {

	if (resultados != null) {
		var datos = resultados.datos.list;

		for ( var i = 0; i < datos.length; i++) {
			var fecha = "";
			var reg = datos[i].map;

			for ( var j = 0; j < CAMPOS_GRUPO_GIRO.length; j++) {
				osm_setValor(CAMPOS_GRUPO_GIRO[j] + "_" + id_carga, reg[CAMPOS_GRUPO_GIRO[j]]);
			}
			if (reg["id_grupo_giro"]) {
				fecha = osm_getFecha(reg["fecha_pago"].time)
				osm_setValor("fecha_pago_" + id_carga, fecha);
				osm_setValor("estado_" + id_carga, reg["estado"]);
				osm_setValor("cuenta_pago_" + id_carga, reg["banco_pago"] + " - " + reg["cuenta_pago"]);
			} else {
				var idCuentaPred = reg["cuenta_id_defecto"];
				if (!osm_esVacio(idCuentaPred)) {
					osm_setValor("cuenta_pago_" + id_carga, reg["banco_pago"] + " - " + reg["cuenta_defecto"]);
					osm_setValor("cuenta_pago2_" + id_carga, reg["cuenta_id_defecto"]);
				}
			}

			if (!reg["id_archivo"]) {
				$("#fecha_pago_" + id_carga).datepicker({
					startDate : FECHA_HOY,
					dateFormat : 'dd/mm/yy',
					dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa' ],
					monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
							'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
					changeMonth : true,
					changeYear : true,
					buttonImage: "../images/general/bg-go.png",
					buttonImageOnly: true,
					showOn: "both"
				});

				// seleccion banco
				$("#sel_banco_" + id_carga).click(clickCajaBanco);

				VALORES_CARGAS[id_carga].ids_registros[i] = reg.id_registro;
				if (!VALORES_CARGAS[id_carga].registros[reg.id_registro]) {
					VALORES_CARGAS[id_carga].registros[reg.id_registro] = new Object();
				}
			}

		}
	}

	finCargaGG();
}

function finCargaGG(){
	GG_PENDIENTES--;
	if (GG_PENDIENTES <= 0) {
		$("#cont_grupo_giro").show();
		$("#mensaje_cargando").hide();
	}
}

function ocultarRegistrosCarga() {
	
	console.log("ocultarRegistrosCarga");
	
	var id_carga = this.id;
	id_carga = id_carga.substring("link_ver_".length);
	$("#contenido_carga_" + id_carga).hide();
}

// Mostrar registros de carga masivas
function mostrarRegistrosCarga() {
	
	console.log("mostrarRegistrosCarga");
	
	var id_carga = this.id;
	id_carga = id_carga.substring("link_ver_".length);
	$("#contenido_carga_" + id_carga).show();
	if (!VALORES_CARGAS[id_carga].cargado) {
		$("#loading_reg_" + id_carga).show();
		obtenerRegistros(id_carga);
	}
}

function obtenerRegistros(id_carga) {
	PAGINACION[id_carga].cargando = true;
	var inicio = (PAGINACION[id_carga].pagina_actual - 1) * MAX_REGISTROS_PINTADO;

	var params = "id_carga:" + id_carga + ",estado_nuevo:E,inicio:" + inicio + ",registros:" + MAX_REGISTROS_PINTADO;

	var rta = jsonrpc._("reporteServicio.obtenerDatosReporte")(REPORTES[0].idReporte, params);
	
	mostrarDetalleCarga(rta, id_carga , inicio);
}



// Pinta registros de la carga masiva
function mostrarDetalleCarga(resultados, id_carga, inicio) {

	var cont = $("#registros_carga_" + id_carga);
	var cont_buffer = $("#registros_carga_buffer_" + id_carga);

	var div = $("#cont_scroll_" + id_carga);
	if (resultados != null) {

		var datos = resultados.datos.list;
		VALORES_CARGAS[id_carga].cargado = true;

		if (datos.length > 10) {
			div.addClass("cont_scroll");
		}

		if (!VALORES_CARGAS[id_carga].registros) {
			VALORES_CARGAS[id_carga].registros = new Array();
			VALORES_CARGAS[id_carga].ids_registros = new Array();
		}

		var numero = 0;

		for ( var j = 0; j < datos.length; j++) {

			var reg = datos[j].map;

			if (!reg["id_archivo"]) {
				var i = inicio + numero;

				numero += 1;

				var fecha = "";
				var cuenta = "";
				var estado = "";
				var id_cuenta = "";
				if (reg["id_grupo_giro"]) {
					id_cuenta = reg["id_cuenta"];
					fecha = osm_getFecha(reg["fecha_pago"].time);
					cuenta = reg["banco_pago"] + " - " + reg["cuenta_pago"];
					estado = reg["estado"];
				}

				construirHTML(cont_buffer, "PLANTILLA_REGISTRO", [ reg.id_registro, i + 1, reg.identificacion, reg.valor, reg.banco == null ? "" : reg.banco, reg.cuenta, id_carga, (i % 2) + 1, cuenta, fecha, estado, id_cuenta ]);

				if (!reg["id_archivo"]) {

					osm_timeout(function(id_carga, id_registro) {

						osm_listen("click", osm_getObjeto("sel_banco_" + id_carga + "_" + id_registro), function(ev, robj) {
							robj.clickCajaBanco = clickCajaBanco;
							robj.clickCajaBanco();
						})

						osm_listen("click", osm_getObjeto("cerrar_sel_banco_" + id_carga + "_" + id_registro), function(ev, robj) {
							robj.clickCerrar = clickCerrar;
							robj.clickCerrar();
						})

						$('.date-pick_' + id_carga + '_' + id_registro).datepicker({
							startDate : FECHA_HOY,
							dateFormat : 'dd/mm/yy',
							dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa' ],
							monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
									'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
							changeMonth : true,
							changeYear : true,
							buttonImage: "../images/general/bg-go.png",
							buttonImageOnly: true,
							showOn: "both"
						});

					}, 1, [ id_carga, reg.id_registro ]);

					VALORES_CARGAS[id_carga].ids_registros[i] = reg.id_registro;
					if (!VALORES_CARGAS[id_carga].registros[reg.id_registro]) {
						VALORES_CARGAS[id_carga].registros[reg.id_registro] = new Object();
					}

				}
				var idCuentaPred = reg["cuenta_id_defecto"];
				if (!reg.id_grupo_giro && !osm_esVacio(idCuentaPred)) {
					document.getElementById("cuenta_pago_" + id_carga + "_" + reg.id_registro).innerHTML = reg["banco_pago"] + " - " + reg["cuenta_defecto"];
					document.getElementById("cuenta_pago2_" + id_carga + "_" + reg.id_registro).value = idCuentaPred;

					id_cuenta = idCuentaPred;
					cuenta = reg["banco_pago"] + " - " + reg["cuenta_defecto"];
				}

				ejecutarComandos(id_carga, reg.id_registro);
				var registro = VALORES_CARGAS[id_carga].registros[reg.id_registro];
				if (registro) {

					if (!osm_esVacio(id_cuenta) && !registro["cuenta"]) {
						registro["cuenta"] = new Object();
						registro["cuenta"].valor = id_cuenta;
						registro["cuenta"].texto = cuenta;
					}
					if (!osm_esVacio(fecha) && !registro["fecha"]) {
						registro["fecha"] = new Object();
						registro["fecha"].valor = fecha;
						registro["fecha"].texto = fecha;
					}
				}

				document.getElementById("producto_" + id_carga + "_" + reg.id_registro).value = reg.producto;

			}
		}

		cont.children("tbody").empty();
		cont_buffer.find("tr").appendTo(cont.children("tbody"));

		osm_setValor("texto_paginacion_" + id_carga, "P\u00e1gina " + PAGINACION[id_carga].pagina_actual + " de " + PAGINACION[id_carga].total_paginas);

		$("#loading_reg_" + id_carga).hide();
		div.show();
		PAGINACION[id_carga].cargando = false;
	}
}

function ejecutarComandos(id_carga, id_registro) {

	var comando_reg = $("#comando_" + id_carga + "_" + id_registro);

	var comando_carga = $("#comando_" + id_carga + "_null");

	// Por defecto
	var comando = comando_carga;

	// Se toma el ultimos comando si hay dos
	if (comando_reg.length && comando_carga.length) {
		var orden_reg = $("#comando_orden_" + id_carga + "_" + id_registro).val();
		var orden_carga = $("#comando_orden_" + id_carga + "_null").val();

		if (orden_carga > orden_reg) {
			ejecutarComando(id_carga, id_registro, comando_reg);
			ejecutarComando(id_carga, id_registro, comando_carga, true);
		} else {
			ejecutarComando(id_carga, id_registro, comando_carga, true);
			ejecutarComando(id_carga, id_registro, comando_reg);
		}
	} else if (comando_reg.length) {
		ejecutarComando(id_carga, id_registro, comando_reg);
	} else {
		ejecutarComando(id_carga, id_registro, comando_carga, true);
	}
}

function ejecutarComando(id_carga, id_registro, comando, esComandoCarga) {
	if (comando.length) {

		var fecha = null;

		var cuenta = null;

		if (esComandoCarga) {
			var valor_carga = VALORES_CARGAS[id_carga];
			fecha = valor_carga["fecha"] ? valor_carga["fecha"].texto : null;
			cuenta = valor_carga["cuenta"] ? valor_carga["cuenta"].texto : null;
		} else {
			var registro = VALORES_CARGAS[id_carga].registros[id_registro];
			fecha = registro["fecha"] ? registro["fecha"].texto : null;
			cuenta = registro["cuenta"] ? registro["cuenta"].texto : null;
		}
		if (fecha != null) {
			osm_setValor("fecha_pago_" + id_carga + "_" + id_registro, fecha);
		}
		if (cuenta != null) {
			osm_setValor("cuenta_pago_" + id_carga + "_" + id_registro, cuenta);
		}
	}
}

function irPagina(id_carga) {
	osm_setValor("texto_paginacion_" + id_carga, "Cargando ...");
	obtenerRegistros(id_carga);

}

function paginacion_inicio(id_carga) {
	if (PAGINACION[id_carga].cargando) {
		return;
	}
	if (PAGINACION[id_carga].pagina_actual > 1) {
		PAGINACION[id_carga].pagina_actual = 1;
		irPagina(id_carga);
	}
}
function paginacion_anterior(id_carga) {
	if (PAGINACION[id_carga].cargando) {
		return;
	}
	if (PAGINACION[id_carga].pagina_actual > 1) {
		PAGINACION[id_carga].pagina_actual--;
		irPagina(id_carga);
	}
}

function paginacion_siguiente(id_carga) {
	if (PAGINACION[id_carga].cargando) {
		return;
	}
	if (PAGINACION[id_carga].pagina_actual < PAGINACION[id_carga].total_paginas) {
		PAGINACION[id_carga].pagina_actual++;
		irPagina(id_carga);
	}
}
function paginacion_final(id_carga) {
	if (PAGINACION[id_carga].cargando) {
		return;
	}
	if (PAGINACION[id_carga].pagina_actual < PAGINACION[id_carga].total_paginas) {
		PAGINACION[id_carga].pagina_actual = PAGINACION[id_carga].total_paginas;
		irPagina(id_carga);
	}
}

function clickCerrar() {
	var id = '#cont_sel_banco_' + $(this).attr("value");
	setTimeout("$('" + id + "').hide();", 50);

	var sel = osm_getObjeto("select_bancos_" + $(this).attr("value"));
	sel.selectedIndex = 0;
	sel = osm_getObjeto("select_cuenta_" + $(this).attr("value"));
	sel.selectedIndex = 0;

	return false;
}

function clickCajaBanco() {

	var id = $(this).attr("value");

	var conti = true;
	if ($("#select_bancos_" + id)[0].options.length <= 1) {
		conti = obtenerBancos(id)
	}

	var cajas = $("[name='cont_sel_banco']");

	var span = $("#cont_sel_banco_" + id);

	for ( var i = 0; i < cajas.length; i++) {
		if (cajas[i] != span[0]) {
			$(cajas[i]).hide();
		}
	}

	if (conti) {
		if (span.is(":hidden")) {
			span.show();
		}
	}
}

function obtenerBancos(id) {
	var producto = osm_getValor("producto_" + id);
	var params = "producto:" + producto;

	if (osm_esVacio(producto)) {
		alert("Lo sentimos, \n\nNo se puede obtener el listado de bancos. \nNO se tiene PRODUCTO definido.");
		return false;
	}

	// Obtener bancos
	jsonrpc._("reporteServicio.obtenerDatosReporte")(new Function("lista", "listarBancos(lista, '" + id + "')"), REPORTES[1].idReporte, params);

	return true;
}

function listarBancos(resultados, id) {

	if (resultados != null) {
		var datos = resultados.datos.list;
		var select = $("#select_bancos_" + id)[0];

		var options = select.options;
		options.length = 1;
		for ( var i = 0; i < datos.length; i++) {
			var reg = datos[i].map;
			var opt = new Option("(" + reg.codigo + ") " + reg.nombre, reg.id_registro);
			options[options.length] = opt;
		}

	}

}
function mostrarSelectCuentas(id, idSelBancos) {
	var valor = osm_getValor(idSelBancos);
	var select = osm_getObjeto("select_cuenta_" + id);
	var producto = osm_getValor("producto_" + id);
	select.options.length = 1;
	if (!osm_esVacio(valor)) {

		var params = "id_banco:" + valor + ",producto:" + producto;
		var rta = jsonrpc._("reporteServicio.obtenerDatosReporte")(new Function("rta", "mostrarCuentas(rta, '" + id + "')"), REPORTES[2].idReporte, params);

	}

}

function mostrarCuentas(resultados, id) {

	if (resultados != null) {
		var datos = resultados.datos.list;
		var select = osm_getObjeto("select_cuenta_" + id);

		var options = select.options;
		for ( var i = 0; i < datos.length; i++) {
			var reg = datos[i].map;
			var opt = new Option("(" + reg.codigo + ") " + reg.numero_cuenta, reg.id_registro);
			options[options.length] = opt;
		}

	}
}

function construirHTML(objpadre, id_plantilla, parametros) {

	var htmlplantilla = PLANTILLAS[id_plantilla];

	if (!htmlplantilla) {
		htmlplantilla = osm_getValor(id_plantilla);
		PLANTILLAS[id_plantilla] = osm_getValor(id_plantilla);
		$("#" + id_plantilla).empty();
	}

	for ( var j = 0; j < parametros.length; j++) {
		htmlplantilla = osm_remplazar(htmlplantilla, '[ ' + (j + 1) + ' ]', parametros[j]);
	}

	objpadre.append(htmlplantilla);
}

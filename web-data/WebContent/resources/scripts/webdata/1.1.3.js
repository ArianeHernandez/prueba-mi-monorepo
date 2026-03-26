// --------------------------------------------------------

function seleccionBusquedaVacia() {
	seleccionarElementoBusqueda("", "--");
}

// --------------------------------------------------------

function seleccionarElementoBusqueda(idseleccion, titulo) {

	osm_setValor(osm_getValor("formatoDatoName") + ".valor", idseleccion);
	osm_setValor(osm_getValor("formatoDatoName") + ".valorVisible", titulo);

	cerrarVentanaBusqueda();
}

// --------------------------------------------------------

function realizarBusqueda() {
	osm_setValor("busqueda_mensaje", "Realizando busqueda.");
	window.setTimeout("realizarBusquedaAccion()", 100);
}

function realizarBusquedaAccion() {
	var id_campo = osm_getValor("vnb_criterioBusqueda");
	var valor_busqueda = osm_getValor("vnb_valorBusqueda");
	var id_estructura = osm_getValor("id_estructura_busqueda");
	var id_persona = osm_getValor("id_persona");

	var info = null;

	try {
		info = jsonrpc._("datosServicio.obtenerdatos")(id_persona, id_estructura, id_campo, valor_busqueda);
	} catch (_e) {
	}

	var area_resultado = osm_getObjetoVacio("area_resultado");

	var innertext = "";

	var i = 0;
	if (info == null) {
		osm_setValor("busqueda_mensaje", "Ha ocurrido un error al realizar la operacion.")
	} else if (info.datos.length == 0) {
		osm_setValor("busqueda_mensaje", "No se encontraron resultados.")
	} else {
		if (info.datos.length == 1) {
			osm_setValor("busqueda_mensaje", "Se encontro un resultado.");
		} else {
			osm_setValor("busqueda_mensaje", "Se encontraron " + info.datos.length + " resultados");
		}

		var datos = info.datos
		for (i = 0; i < info.datos.length; i++) {
			var plantilla_resultado = osm_getValor("plantilla_resultado");
			var registro = info.datos[i];

			if (registro != null) {
				var titulo = null;
				var j = null;
				for (j = 2; j < registro.dato.length; j++) {
					if (titulo == null) {
						titulo = registro.dato[j];
					} else {
						titulo = titulo + " | " + registro.dato[j];
					}
				}
				plantilla_resultado = osm_remplazar(plantilla_resultado, "TITULO", titulo);
				plantilla_resultado = osm_remplazar(plantilla_resultado, "MENSAJE", registro.dato[1]);
				plantilla_resultado = osm_remplazar(plantilla_resultado, "IDSELECCION", registro.dato[0]);

				innertext = innertext + plantilla_resultado
			}
		}

		osm_setValor("area_resultado", innertext);
	}
}

// --------------------------------------------------------

function verVentanaBusqueda(id_estructura, formatoDatoName) {
	osm_setValor("id_estructura_busqueda", id_estructura);
	osm_setValor("formatoDatoName", formatoDatoName);
	osm_getObjetoVacio("area_resultado");
	osm_getObjetoVacio("vnb_valorBusqueda");
	osm_getObjetoVacio("busqueda_mensaje");

	osm_setVisible("vn_busqueda", true, true);
	osm_ocultarSelects("bodyContent");

	if (osm_esVacio(osm_getValor(formatoDatoName + ".valor"))) {
		osm_setVisible("btn_busquedaVacia", false);
	} else {
		osm_setVisible("btn_busquedaVacia", true, true);
	}

	var selectorCriterio = osm_getObjetoVacio("vnb_criterioBusqueda");

	var campos = jsonrpc._("formatoServicio.obtenerCamposPorEstructura")(id_estructura);

	var i = 0;
	for (i = 0; i < campos.length; i++) {
		var campo = campos[i];
		if (campo.visualizacion == 'S') {
			selectorCriterio.options[selectorCriterio.length] = new Option(campo.nombre, campo.id_campo);
		}
	}
}

function cerrarVentanaBusqueda() {
	osm_setVisible("vn_busqueda", false);
	osm_mostrarSelects("bodyContent");
}

function eliminarElemento(objid) {
	osm_eliminarObjeto(objid);
}

// --------------------------------------------------------

function adicionarElemento(idf, formatodatoname) {

	var bloque_contenido = osm_getObjeto("CONTENIDO_" + idf + "N_" + formatodatoname + "F");

	var cantidad_contenido = osm_getValorEntero("CANTIDAD_" + idf + "N_" + formatodatoname + "F");
	var plantilla = osm_getValor("PLANTILLA_" + idf + "N_" + formatodatoname + "F");

	var nombre_elemento = formatodatoname + ".formatoDatoList:" + cantidad_contenido;
	var divcontent = document.createElement("DIV");
	divcontent.id = "DD" + nombre_elemento + "DD";

	plantilla = osm_remplazar(plantilla, "PLANTILLA_" + idf + "V_" + formatodatoname + "F", nombre_elemento);
	plantilla = osm_remplazar(plantilla, "IDELIMINAR", divcontent.id);

	divcontent.innerHTML = plantilla;
	bloque_contenido.appendChild(divcontent);
	osm_setValor("CANTIDAD_" + idf + "N_" + formatodatoname + "F", cantidad_contenido + 1);
}

// --------------------------------------------------------

function validacion_carga_interactiva() {

	var formulario = osm_getObjeto("form_cargainteractiva");

	var inputs = formulario.getElementsByTagName("input");

	for ( var ff = 0; ff < inputs.length; ff++) {
		var inputelement = inputs[ff];
		var idelement = inputelement.id;
		if (idelement.indexOf("FormatoDato.") == 0 && idelement.indexOf("patron_validacion") > 0) {
			var idobjeto = idelement.substring(0, idelement.indexOf("patron_validacion") - 1);
			var valorobjeto = osm_getValor(idobjeto + ".valor");
			var es_obligatorio = osm_getValor(idobjeto + ".es_obligatorio");
			var patron_validacion = osm_getValor(idelement);

			var validarpatron = false;
			var regexp = new RegExp(patron_validacion);
			if (osm_existeElemento(idobjeto + ".valor") && es_obligatorio == "S") {

				if (osm_esVacio(valorobjeto)) {
					alert("El campo " + osm_getValor(idobjeto + ".titulo") + " es obligatorio.");
					osm_setFoco(idobjeto + ".valor");
					return false;
				}

				validarpatron = true;

			} else {
				if (!osm_esVacio(valorobjeto)) {
					validarpatron = true;
				}
			}

			if (validarpatron) {

				if (!regexp.test(valorobjeto)) {
					alert("El campo " + osm_getValor(idobjeto + ".titulo") + " debe ser " + osm_getValor(idobjeto + ".tipo_dato_nombre"));
					osm_setFoco(idobjeto + ".valor");
					return false;
				}
			}

		}
	}

	return osm_comfirm("\u00bfEst\u00e1 seguro de guardar esta informaci\u00f3n?");
}

// --------------------------------------------------------

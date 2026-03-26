$(init92)

function init92() {

	$(".liberadorTipoProceso_valor_max_individual").each(function() {
		var id = $(this).attr("id");
		mostrarDoubleConFormato(id);
	});

	$(".liberadorTipoProceso_valor_max_carga").each(function() {
		var id = $(this).attr("id");
		mostrarDoubleConFormato(id);
	});

}

function probarNuevoPesoLiberador(id_liberador, id_tipo_proceso) {

	if (id_liberador == null || id_liberador == "") {
		return;
	}

	var nuevoPeso = osm_getValor("Liberador.peso");

	var listaProcesos = jsonrpc._("procesoJsonServicio.listarProcesosAfectadosLiberador")(id_liberador, nuevoPeso, id_tipo_proceso).list;
	var mensaje = "Al cambiar el peso de este liberador, las operaciones relacionadas con los siguientes procesos no podran ser liberadas: \n"
	for (i = 0; i < listaProcesos.length; i++) {
		mensaje += listaProcesos[i].nombre + "\n";
	}
	if (listaProcesos.length > 0) {
		alert(mensaje);
	}
}

function mostrarDoubleSinFormato(formatoDato) {

	// Se oculta el div con formato
	var div = osm_getObjeto('div_formato_double_' + formatoDato);
	$(div).hide();

	// Se muestra el campo con el valor sin formato
	var div = osm_getObjeto('div_sinformato_double_' + formatoDato);
	$(div).show();

	// Se obtiene el foco
	osm_setFoco(formatoDato);

}

function mostrarDoubleConFormato(formatoDato) {

	// Se oculta el campo
	var div = osm_getObjeto('div_sinformato_double_' + formatoDato);
	$(div).hide();

	// Se muestra el div con el dato formatiado
	var valor = osm_getValor(formatoDato);

	if (!isNaN(valor)) {
		valor = (valor == "" || valor == null) ? "&nbsp;" : osm_formatoMoneda(valor);

		osm_setValor('div_formato_double_' + formatoDato, valor);
	} else {
		osm_setValor('div_formato_double_' + formatoDato, "Error");
	}

	var div = osm_getObjeto('div_formato_double_' + formatoDato);
	$(div).show();

}

function ver_ayuda_campo(ref_formato_campo) {

	try {
		var ico_obj = osm_getObjeto(ref_formato_campo + ".ico_ayuda");

		$(".area_ayuda").hide();

		if (ico_obj == null) {
			$(".ico_ayuda").fadeTo(200, 0.4);
		} else {
			$(".ico_ayuda").not(ico_obj).fadeTo(200, 0.4);
			$(ico_obj).fadeTo(200, 1);
			$(osm_getObjeto(ref_formato_campo + ".area_ayuda")).show();
		}
	} catch (e) {
	}
}

function validacionDesactivarTipoProceso(id) {
	var valor = osm_getValor("TipoProcesoRol:" + id + ".id_tipo_proceso");

	if (valor != "") {
		return osm_comfirm("\u00bfEst\u00e1 seguro de quitar el tipo de " + osm_getValor("nombre_rol") + "?, se eliminar\u00e1 al usuario de todos los procesos de ese tipo al que pertenezca");
	}

	return true;

}

function cambiarEstadoTipoProceso(id) {
	var valor = osm_getValor("TipoProcesoRol:" + id + ".id_tipo_proceso");

	if (osm_esVacio(valor)) {
		$("#info_lib_" + id).hide();
	} else {
		$("#info_lib_" + id).show();
	}

}

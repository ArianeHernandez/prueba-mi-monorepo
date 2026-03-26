let idProcesoAdmin = 0;
let idNegocio;

function construirSelectores() {
	construirSelectorNegocio();
	construirSelectorFormato();
	cargarValores();
}

function crearOption(id, text, value) {
	var option = document.createElement("OPTION");
	option.id = id;
	option.text = text;
	option.value = value;
	return option;
}

function construirSelectorNegocio() {
	var negocios = jsonrpc._("procesoAdminJsonServicio.obtenerListadoNegociosActivos")().list;

	var selectNegocio = document.createElement('SELECT')
	selectNegocio.className = "input_proceso form-control";
	selectNegocio.id = "select_negocio_proceso_admin";
	selectNegocio.onchange = new Function('actualizarNegocio()');

	osm_getObjeto('divsel_negocio_proceso_admin').appendChild(selectNegocio);

	for (var i = 0; i < negocios.length; i++) {
		selectNegocio.appendChild(crearOption('option_negocio_' + negocios[i].id_negocio, negocios[i].nombre, negocios[i].id_negocio));
	}
}

function construirSelectorFormato() {
	var formatos = jsonrpc._("procesoAdminJsonServicio.obtenerFormatosEntradaPorNegocio")(idNegocio).list;

	var selectFormato = document.createElement('SELECT')
	selectFormato.className = "input_proceso form-control";
	selectFormato.id = "select_formato_proceso_admin";
	selectFormato.onchange = new Function('actualizarFormato();');

	osm_setValor('divsel_formato_proceso_admin', '');
	osm_getObjeto('divsel_formato_proceso_admin').appendChild(selectFormato);
	
	selectFormato.appendChild(crearOption('option_formato_ninguno', '-- ninguno --', ''));

	for (var i = 0; i < formatos.length; i++) {
		selectFormato.appendChild(crearOption('option_formato_' + formatos[i].id_formato, formatos[i].nombre, formatos[i].id_formato));
	}
}

function cargarValores() {
	var procesoAdmin = jsonrpc._("procesoAdminJsonServicio.obtenerProcesoAdmin")(idProcesoAdmin);

	osm_setValor('cajatex_nombre_proceso_admin', procesoAdmin.nombre);

	var selectNegocio = osm_getObjeto('select_negocio_proceso_admin');
	for ( var i = 0; i < selectNegocio.length; i++) {
		if (selectNegocio.options[i].value == procesoAdmin.id_negocio) {
			selectNegocio.options[i].selected = true;
		}
	}

	var selectFormato = osm_getObjeto('select_formato_proceso_admin');
	for ( var i = 0; i < selectFormato.length; i++) {
		if (selectFormato.options[i].value == procesoAdmin.id_formato_entrada) {
			selectFormato.options[i].selected = true;
		}
	}
}


function actualizarNegocio() {
	osm_setValor("select_formato_proceso_admin", "");
	actualizarProceso();
	construirSelectorFormato();
	consultarFormatosSalida();
}

function actualizarFormato() {
	if (!verificarProcesosPorNegocioFormato()) {
		actualizarProceso();
	} else {
		mostrarVentanaActualiza('Ya existe un proceso asociado a este negocio y formato');
		osm_setValor("select_formato_proceso_admin", "");
	}
}

function obtenerInformacionProceso() {
	return {
		nombre: osm_getValor("cajatex_nombre_proceso_admin"),
		negocio: osm_getValor("select_negocio_proceso_admin"),
		formatoEntrada: osm_getValor("select_formato_proceso_admin")
	};
}

function verificarProcesosPorNegocioFormato() {
	var informacionProceso = obtenerInformacionProceso();
	return jsonrpc._("procesoAdminJsonServicio.verificarExistencia")(informacionProceso.negocio, informacionProceso.formatoEntrada, idProcesoAdmin);
}

function actualizarProceso() {
	var informacionProceso = obtenerInformacionProceso();

	if (osm_esVacio(informacionProceso.nombre)) {
		mostrarVentanaActualiza('El nombre no puede estar vacio');
		return;
	}
	
	var exitoActualizar = jsonrpc._("procesoAdminJsonServicio.actualizarProcesoAdmin")
							(idProcesoAdmin, informacionProceso.negocio, informacionProceso.formatoEntrada, informacionProceso.nombre);
	
	if (!exitoActualizar) {
		mostrarVentanaActualiza('No se pudo actualizar el proceso.');
		osm_go('proceso_administracion/20.1.do');
	} else {
		idNegocio = informacionProceso.negocio;
	}
}
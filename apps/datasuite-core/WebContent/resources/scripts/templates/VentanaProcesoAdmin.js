function construirSelects(){
	
	construirSelectNegocio();
	actualizarSelectFormato();
}

function construirSelectNegocio(){
	
	var negocios= jsonrpc._("procesoAdminJsonServicio.obtenerListadoNegociosActivos")();
	var negList = negocios.list;
	
	
	var selectNegocio = document.createElement('SELECT')
	selectNegocio.className = "cajatextoselector";
	selectNegocio.id = "select_negocio_proceso_admin";
	
	selectNegocio.onchange = new Function("actualizarSelectFormato()");
	
	osm_setValor('divsel_negocio_proceso_admin','');
	osm_getObjeto('divsel_negocio_proceso_admin').appendChild(selectNegocio);
	
	osm_setValor('select_negocio_proceso_admin', '');
			
	for ( var i = 0; i < negList.length; i++) {
		
		var option = document.createElement("OPTION");

		var negocio=negList[i];
		
		option.value = negocio.id_negocio;
		option.id='option_negocio_'+negocio.id_negocio;
		option.innerHTML = negocio.nombre;
		
		selectNegocio.appendChild(option);
		
	}
	
}

function actualizarSelectFormato(){
	
	var selectNegocio = osm_getObjeto('select_negocio_proceso_admin');
	var indiceSeleccionado = selectNegocio.selectedIndex;
	var opcionSeleccionada = selectNegocio.options[indiceSeleccionado];
	 
	var id_negocio = opcionSeleccionada.value;
	
	construirSelectFormato(id_negocio);
}

function construirSelectFormato(id_negocio){
	
	var formatos = jsonrpc._("procesoAdminJsonServicio.obtenerFormatosEntradaPorNegocio")(id_negocio);
	var formList = formatos.list;
	
	var selectFormato = document.createElement('SELECT')
	selectFormato.className = "cajatextoselector";
	selectFormato.id = "select_formato_proceso_admin";
	
	osm_setValor('divsel_formato_proceso_admin','');
	osm_getObjeto('divsel_formato_proceso_admin').appendChild(selectFormato);
	
	osm_setValor('select_formato_proceso_admin', '');
	
	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = null;
	option_ninguno.id='option_formato_ninguno';
	option_ninguno.innerHTML = '-- ninguno --';
	selectFormato.appendChild(option_ninguno);
	
	for ( var i = 0; i < formList.length; i++) {
		
		var option = document.createElement("OPTION");

		var formato=formList[i];
		
		option.value = formato.id_formato;
		option.id='option_formato_'+formato.id_formato;
		option.innerHTML = formato.nombre;
		
		selectFormato.appendChild(option);
		
	}
	
}

function verificaValores(id_proceso_admin){
	
	var nombre = osm_getObjeto('cajatex_nombre_proceso_admin').value;
	if(osm_esVacio(nombre)){
		alert('El nombre no puede estar vacio');
		return false;
	}
	
	var selectNegocio = osm_getObjeto('select_negocio_proceso_admin');
	var indiceSeleccionadoNeg = selectNegocio.selectedIndex;
	var opcionSeleccionadaNeg = selectNegocio.options[indiceSeleccionadoNeg];
	var id_negocio = opcionSeleccionadaNeg.value;
	
	var selectFormato = osm_getObjeto('select_formato_proceso_admin');
	var indiceSeleccionadoForm = selectFormato.selectedIndex;
	var opcionSeleccionadaForm = selectFormato.options[indiceSeleccionadoForm];
	var id_formato_entrada = opcionSeleccionadaForm.value;
	
	var existe = jsonrpc._("procesoAdminJsonServicio.verificarExistencia")(id_negocio,id_formato_entrada,id_proceso_admin);
	if(existe){
		alert('Ya existe un proceso asociado a este negocio y formato');
		return false;
	}
	
	return true;
}

//-----------------------------------------------------------------




function cancelarProcesoAdmin(){
	osm_setVisible('ventana_proceso_admin', false);
	osm_mostrarSelects("bodyContent");
	limpiarValores();
}

function guardarProcesoAdmin(){
	
	if(!verificaValores(null)){	return;}
	crearProcesoAdmin();
		
	osm_setVisible('ventana_proceso_admin', false);
	osm_ocultarSelects("bodyContent");
	
	limpiarValores();
}

function mostrarVentanaProcesoAdmin(){
	
	construirSelects();
	
	osm_setVisible('ventana_proceso_admin', true, true);
	osm_ocultarSelects("bodyContent");

}

function limpiarValores(){
	osm_setValor('cajatex_nombre_proceso_admin','');
	osm_setValor('divsel_negocio_proceso_admin','');
	osm_setValor('divsel_formato_proceso_admin','');
}

function cerrarVentanaActualiza(){
	osm_setVisible('ventana_actualiza_proceso_admin', false);
	osm_ocultarSelects("bodyContent");
}

function mostrarVentanaActualiza(mensaje){
	
	osm_setValor('parr_mensaje_actualiza',mensaje);
	osm_setVisible('ventana_actualiza_proceso_admin', true, true);
	osm_ocultarSelects("bodyContent");

}

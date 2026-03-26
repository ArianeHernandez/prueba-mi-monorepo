

function verVentana(){
	
	osm_setVisible("ventanaImport", true, true);
	osm_ocultarSelects("bodyContent");
	osm_setFoco("identificacion");
}

function cerrarVentana(){
	osm_setVisible("ventanaImport", false, false);
	osm_mostrarSelects("bodyContent");
}


function validarIdentificacion(){
	
	if( osm_esVacio(osm_getValor("identificacion")) ){
		osm_alert("El numero de identificacion no puede ser vacio");
		osm_setFoco("identificacion");
		return false;
	}
	
	cerrarVentana();
	return true;
}

function desasociar(id_persona, nombre_persona, nombre_negocio){

	if(osm_comfirm("Esta seguro que desea desasociar la Persona '"+nombre_persona+ "' del Negocio '" + nombre_negocio + "'.")){
		osm_setValor("id_persona_des", id_persona);
		osm_enviarFormulario("form_desasociar");
	}
	
}


function enviarFormulario(id_persona){
	osm_setValor("id_persona", id_persona );
	osm_enviarFormulario("form_siguiente");
}


function p_load(){

}

osm_listen("load", window, p_load);


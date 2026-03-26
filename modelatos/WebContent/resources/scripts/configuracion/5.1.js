


function page_validarGuardar(){
	
	
	// Se verifica que haya ingresado clave actual
	if(osm_esVacio( osm_getValor("auth_password") )){
		osm_alert("Por favor ingrese su contraseña actual.");
		osm_setFoco("auth_password");
		return false;
	}
	
	// Verificamos si la clave actual es correcta
	var claveCorrecta = jsonrpc._("personaServicio.esClaveActualCorrecta")(osm_getValor("auth_password"));
	
	if(!claveCorrecta){
		osm_alert("Por favor ingrese correctamente la contrase\u00F1a actual.");
		osm_setFoco("auth_password");
		return false;
	}
	
	// Se verifica si ha ingresado nueva contraseña
	if(osm_esVacio( osm_getValor("new_password") )){
		osm_alert("Por favor ingrese su nueva contraseña.");
		osm_setFoco("new_password");
		return false;
	}
	
	//data-pattern="^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,}$"
	var RegExPattern = jsonrpc._("contenido.obtenerValorEtiqueta")("EXPRESION_CONTRASENA");
	var msgContrasena = jsonrpc._("contenido.obtenerValorEtiqueta")("DESCRIPCION_CONTRASENA");
//	var RegExPattern = json;
	
	// Se verifica si la nueva contraseña tiene un minimo de 6 caracteres
	if(!(osm_getValor("new_password").match(RegExPattern))){
		osm_alert(msgContrasena);
//		osm_setValor("new_password", "");
		osm_setValor("new_password2", "");
		osm_setFoco("new_password");
		return false;
	}
	
	// Se verifica si la contraseña nueva es igual a la confirmacion de
	// contraseña
	if( osm_getValor("new_password") != osm_getValor("new_password2") ){
		osm_alert("La confirmacion de la clave de la clave de ingreso es incorrecta.");
		osm_setValor("new_password2", "");
		osm_setFoco("new_password");
		return false;
	}
	
	// Verificamos si la clave ya ha sido creada en ocasiones anteriores
	var existeHistorial = jsonrpc._("personaServicio.existeHistorialLoginClave")( osm_getValor("new_password"));
		
	if(existeHistorial){
		osm_alert("Por favor ingrese una contraseña que no haya utilizado previamente.");
		osm_setValor("new_password2", "");
		osm_setFoco("new_password");
		return false;
	}
	
	
	return true;
}


function page_validarInfoGuardar(){
	
	if(osm_esVacio( osm_getValor("Persona.nombre") )){
		osm_alert("Por favor ingrese su nombre.");
		osm_setFoco("Persona.nombre");
		return false;
	}
	
	
	if(osm_esVacio( osm_getValor("Persona.correo") )){
		osm_alert("Por favor ingrese su correo electronico.");
		osm_setFoco("Persona.correo");
		return false;
	}
	
	return true;
}

// ---------------------------

function p_load(){

}

osm_listen("load", window, p_load);


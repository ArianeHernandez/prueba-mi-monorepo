$(init51)

function init51(){
	var a=$("#valMaxIndv");
	var b=a.text();
	a.text(osm_formatoMoneda(b));
	
	var a2=$("#valMaxCarga");
	var b2=a2.text();
	a2.text(osm_formatoMoneda(b2));	
	
	
}


function page_validarGuardar(){
	
	
	//Se verifica que haya ingresado clave actual
	if(osm_esVacio( osm_getValor("auth_password") )){
		osm_alert("Por favor ingrese su contraseña actual.");
		osm_setFoco("auth_password");
		return false;
	}
	
	//Verificamos si la clave actual es correcta
	var claveCorrecta = jsonrpc._('personaServicio.esClaveActualCorrecta')(secure_encrypt(osm_getValor("auth_password")));
	
	if(!claveCorrecta){
		osm_alert("Por favor ingrese correctamente la contrase\u00F1a actual.");
		osm_setFoco("auth_password");
		return false;
	}
	
	//Se verifica si ha ingresado nueva contraseña
	if(osm_esVacio( osm_getValor("new_password") )){
		osm_alert("Por favor ingrese su nueva contraseña.");
		osm_setFoco("new_password");
		return false;
	}
	
	//Se verifica que la contraseña es alfanumerica
	
	//var RegExPattern = /(?!^[0-9]*$)(?!^[a-zA-Z#$%\/.*:_-]*$)^([0-9a-zA-Z#$%\/.*:_-]{6,50})$/;
	var expresionContrasena = jsonrpc._("contenido.obtenerValorEtiqueta")("EXPRESION_CONTRASENA");
	
	var RegExPattern = /(?!^[0-9]*$)(?!^[a-zA-Z#$%\/.*:_-]*$)^([0-9a-zA-Z#$%\/.*:_-]{6,50})$/;
	var msgContrasena = "La nueva clave debe tener por lo menos un letra y un valor alfanum\u00e9rico.";
	
	if(expresionContrasena != null){
		RegExPattern = new RegExp(RegExPattern);
		msgContrasena = jsonrpc._("contenido.obtenerValorEtiqueta")("DESCRIPCION_CONTRASENA");
	} else {
		if(osm_getValor("new_password").length < 6){
			osm_alert("La nueva clave debe tener por lo menos un minimo de 6 caracteres. Por favor verifique.");
			osm_setFoco("new_password");
			osm_setValor("new_password2", "");
			return false;
		}
	}
	
	//Se verifica si la nueva contraseña tiene un minimo de 6 caracteres
	if(!(osm_getValor("new_password").match(RegExPattern))){
		osm_alert(msgContrasena);
		osm_setFoco("new_password");
		osm_setValor("new_password2", "");
		return false;
	}
	
	//Se verifica si la contraseña nueva es igual a la confirmacion de contraseña
	if( osm_getValor("new_password") != osm_getValor("new_password2") ){
		osm_alert("La clave y la confirmación de la clave no coinciden.");
		osm_setValor("new_password2", "");
		osm_setFoco("new_password");
		return false;
	}
	
	//Verificamos si la clave ya ha sido creada en ocasiones anteriores
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
	
	if(osm_esVacio( osm_getValor("Persona.apellido") )){
		osm_alert("Por favor ingrese su apellido.");
		osm_setFoco("Persona.apellido");
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


function aceptaTerminos(login, sinotp){

	if (login != null) {
	
		var exitoso = jsonrpc._("cuentaJSONServicio.aceptaTerminosCondiciones")(login);
		
		if (exitoso) {
			$('#area_enviar').show();
			$('#botones_acepto').hide();
			$('#vn_ingreso_token').fadeOut();
			
			return false;
		}
		else {
			osm_alert("No se pudo realizar la operaci\u00f3n. Intente nuevamente");
			return false;
		}
	}else{
		osm_alert("Problemas con la informacion del usuario. Por favor intente nuevamente");
		return false;
	}
}


function validar_login(){

	//Se verifica si ha ingresado nueva contraseña
	if(osm_esVacio( osm_getValor("new_password") )){
		osm_alert("Por favor ingrese su nueva contraseña.");
		osm_setFoco("new_password");
		return false;
	}
	
	//Se verifica si la contraseña nueva es igual a la confirmacion de contraseña
	if( osm_getValor("new_password") != osm_getValor("new_password2") ){
		osm_alert("La clave y la confirmación de la clave no coinciden.");
		osm_setValor("new_password2", "");
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
			osm_alert(msgContrasena);
			osm_setFoco("new_password2");
			osm_setValor("new_password", "");
			return false;
		}
	}
	
	if (!(osm_getValor("new_password").match(RegExPattern))) {  
		osm_alert("La nueva clave debe tener por lo menos un letra y un valor alfanumerico.");  
		osm_setValor("new_password2", "");
		osm_setFoco("new_password");
		return false;
	}
	return true;
	
}

//------------------------------------------------

function p_load()
{
	osm_setValor("new_password","");
	osm_setValor("new_password2","");
	osm_setFoco("new_password");
	
	window.setTimeout(function(){
		$("#reload_page").click();
	}, 1000);
	
}

function validarToken(login){
	try {
		var token = osm_getValor("token_signapp");
		var resp =  jsonrpc._("solicitudEnrolamiento.validarTokenSinSesionPorCredencial")(login, token);
		console.log(resp);
		
		if(osm_esVacio(token) || resp == undefined){
			osm_alert("El token de SignApp es un campo obligatorio.");
			return false;
		}else{
			if(!resp.valid){
				if(resp.msg.includes("Token invalido")){
					alert("Token inválido");
				}else{
					alert(resp.msg);
				}
				return false;
			}else{
				return true;
			}
		}
	} catch (e) {
		console.error(e);
		return false;
	}
}

function aceptarTerminosValidarOTP(login, sinotp){
	if(sinotp || validarToken(login)){
		aceptaTerminos(login, sinotp);
	}
}

function validarUsoToken(login) {
	try {
		
		var usoToken = jsonrpc._("cuentaJSONServicio.validarUsoToken")(login);
		
		if (usoToken) {
			$('#vn_ingreso_token').fadeIn();
		} else {
			aceptaTerminos(login);
		}
		
	} catch (e) {
		console.log("Error al intentar validar el uso de token SignApp");
		return false;
	}
}


osm_listen("load", window, p_load);

var RESPUESTA_SOLICITUD = null;
var b_login = false;
$(function() {

	osm_validarInput("Tipo de Documento", "#tipo_documento", "string", [ 1, 20 ], true);
	osm_validarInput("Numero de Identificacion", "#numero_identificacion", "int", [ 1, 20 ], true);
	osm_validarInput("Fecha de Expedicion", "#fecha_expedicion", "string", [ 1, 10 ], true);
	osm_validarInput("Correo Electronico", "#correo", "string", [ 1, 150 ], true);
	osm_validarInput("Numero de Celular", "#celular", "int", [ 1, 10 ], true);
	if (b_login) {
		osm_validarInput("Nombre de usuario", "#nombre_usuario", "string", [ 1, 20 ], true);
	}
	

	osm_validarInput("codigo_confirmacion", "#codigo_confirmacion", "int", [ 6, 6 ], true);
	osm_validarInput("clave", "#clave", "int", [ 4, 4 ], true);
	osm_validarInput("clave2", "#clave", "int", [ 4, 4 ], true);

	$("#btn_continuar").click(function() {

		if (osm_realizarValidacion("form_area")) {

			if(!validar_captcha() ){
				return false;
			}
			
			var f = function(t) {
				return osm_trimToEmpty($(t).val());
			}
			
			var login = f("#nombre_usuario");

			osm_block_window();
			osm_verLoader();

			jsonrpc._("cambioPin.registroCambioPin")(function(resp) {

				osm_unblock_window();

				// si no se puede registrar presenta el error..
				if (!resp.exitoso) {
					s_alert("Cambio de PIN", resp.mensaje, function() {
						if(resp.estado == "89")
						// se dirige a la pagina de login..
						osm_go("");
					});
					return;
				}

				RESPUESTA_SOLICITUD = resp.info;

				console.log(RESPUESTA_SOLICITUD);

				validacionPIN();

			}, login);

		}

	});

});

function validacionPIN() {

	$("#area_registro_1").hide();
	$("#area_registro_2").show();

	$("#area_pin").show();

	$("#btn_finalizar").click(function() {

		// obtiene los valores ingresados..

		var codigoConfirmacion = osm_trimToNull($("#codigo_confirmacion").val())
		var nombreUsuario = osm_trimToNull($("#nombre_usuario").val());
		var pinAcceso = osm_trimToNull($("#new_password").val());
		
		if(osm_esVacio( codigoConfirmacion )){
			osm_alert_dangerE("Por favor ingrese el c\u00F3digo de confirmaci\u00F3n.", "", function() { });
			osm_setFoco("new_password");
			return false;
		}
		
		if( !validar_claves() ){
			return false;
		}

		osm_block_window();
		osm_verLoader();

		jsonrpc._("cambioPin.finalizarCambioPin")(function(resp) {

			osm_unblock_window();

			// si no se puede registrar presenta el error..
			if (resp.exitoso) {
				s_alert("Cambio de contrase\u00F1a", "El cambio de contrase\u00F1a se ha realizado exitosamente.", function() {
					// se dirige a la pagina de login..
					osm_go("");
				});
				return;

			} else {
				s_alert("Cambio de contrase\u00F1a", resp.mensaje);
				return;
			}

		}, RESPUESTA_SOLICITUD.pinSolicitud, codigoConfirmacion, pinAcceso);

	});

}


function validar_claves(){

	//Se verifica si ha ingresado nueva contraseña
	if(osm_esVacio( osm_getValor("new_password") )){
		osm_alert_dangerE("Por favor ingrese su nueva contrase\u00F1a.", "", function() { });
		osm_setFoco("new_password");
		return false;
	}
	
	//Se verifica si la contraseña nueva es igual a la confirmacion de contraseña
	if( osm_getValor("new_password") != osm_getValor("new_password2") ){
		osm_alert_dangerE("La confirmaci\u00F3n de la contraseña es incorrecta.", "", function() { });
		osm_setValor("new_password2", "");
		osm_setFoco("new_password");
		return false;
	}
	
	//Se verifica que la contraseña es alfanumerica
	//var RegExPattern = /(?!^[0-9]*$)(?!^[a-zA-Z#$%\/.*:_-]*$)^([0-9a-zA-Z#$%\/.*:_-]{6,50})$/;
	var expresionContrasena = jsonrpc._("contenido.obtenerValorEtiqueta")("EXPRESION_CONTRASENA");
	
	var RegExPattern = /(?!^[0-9]*$)(?!^[a-zA-Z#$%\/.*:_-]*$)^([0-9a-zA-Z#$%\/.*:_-]{6,50})$/;
	var msgContrasena = "La nueva contrase\u00F1a debe tener por lo menos un letra y un valor alfanum\u00E9rico.";
	
	if(expresionContrasena != null){
		RegExPattern = new RegExp(RegExPattern);
		msgContrasena = jsonrpc._("contenido.obtenerValorEtiqueta")("DESCRIPCION_CONTRASENA");
	} else {
		if(osm_getValor("new_password").length < 6){
			osm_alert_dangerE(msgContrasena, "", function() { });
			osm_setFoco("new_password2");
			osm_setValor("new_password", "");
			return false;
		}
	}
	
	if (!(osm_getValor("new_password").match(RegExPattern))) {  
		osm_alert_dangerE("La confirmaci\u00F3n de la contraseña de es incorrecta.", "", function() { });
		osm_setValor("new_password2", "");
		osm_setFoco("new_password");
		return false;
	}
	return true;
	
}

function validar_captcha(){
	var existecaptcha = $('[name=g-recaptcha-response]').length > 0;
	
	var rcp = osm_trimToNull( $('[name=g-recaptcha-response]').val() );
	
	if(existecaptcha && rcp == null ){
		
		osm_alert_dangerE("Marque la casilla de 'No soy un robot'", "", function() {
		});
		osm_unblock_window();
		osm_ocultarLoader();
		return false;
	}	
	return true;
}

function mostrarDetalleCarga(id_carga) {
	osm_setValor('id_carga', id_carga);
	osm_enviarFormulario('form_carga');
}

//--------------------------------------------------------

function verVentanaAprobarCarga(id_horario_liberacion, id_horario_formato) {
	
	var horarioAtencionEstricto= osm_getValor('horarioAtencionEstricto');
	
	
	var mostrarMensaje =false
	//se valida el horario de liberacion de la paramtrizacion del cliente
	if(!osm_esVacio(id_horario_liberacion) &&  !cumpleHorarioLiberacion(id_horario_liberacion)){
		osm_alert('No puede realizar la operaci\u00f3n. La transacci\u00f3n solicitada no se puede enviar debido a que se encuentra fuera del horario de liberaci\u00f3n ESTABLECIDO POR SU EMPRESA');
		return;
		
	}
	
	//se valida el horario del formato
	if(!osm_esVacio(id_horario_formato) &&  !cumpleHorarioFormato(id_horario_formato) ){
		
		if (horarioAtencionEstricto == 'S') {
			osm_alert('No puede realizar la operaci\u00f3n. Aun no se encuentra en el horario de atenci\u00f3n especificado para la transacci\u00f3n');
			return;
		}
		
	}
		
	
	if(osm_getValor('accion_actualizar_negocio')=='S'){
		var selectNegocio = osm_getObjeto('select_negocios_libera');
		
		if(selectNegocio==null){
			osm_alert("Lo sentimos, No se puede realizar la operacion sin tener negocios.");
			return null;
		}
		
		var id_negocio = selectNegocio.value;
		
		if(!osm_esVacio(id_negocio)){
			osm_setValor('negocio_seleccionado',id_negocio);
		}else{
			osm_alert('Debe seleccionar un negocio.');
			return;
		}
	}
	
	
	
	osm_setVisible("vn_aprobarcarga", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaAprobarCarga() {
	osm_setVisible("vn_aprobarcarga", false);
	osm_mostrarSelects("bodyContent");
}

// --------------------------------------------------------

function verVentanaRechazarCarga() {
	osm_setVisible("vn_rechazarcarga", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaRechazarCarga() {
	osm_setVisible("vn_rechazarcarga", false);
	osm_mostrarSelects("bodyContent");
}

function cumpleHorarioFormato(id_horario){
	var estaEnHorario = jsonrpc._("parametrizacionClienteServicio.cumpleHorarioFormato")(id_horario);
	
	return estaEnHorario;
}


function cumpleHorarioLiberacion(id_horario){
	var estaEnHorario = jsonrpc._("parametrizacionClienteServicio.cumpleHorarioLiberarion")(id_horario);
	
	return estaEnHorario;
}

//Validacion de la carga
function validarCarga(){
	try {
		//Funcion para validaciones especificas, se utiliza en el liberador
		if(validarCargaAprobar){
			var validar_carga = validarCargaAprobar();  
			if(validar_carga && jsonrpc._("parametroJsonServicio.validarSaldo")()){
				var producto = parseInt($(".campo_adicional input[type='hidden'][value='Producto']").parent().find("select").val());
				var id_cuenta = parseInt($(".campo_adicional input[type='hidden'][value='Id cuenta']").parent().find("select").val());
				var saldo = jsonrpc._("cuentaServicio.saldo")(producto, id_cuenta);
				var pagos = osm_round(osm_getValor("valor_total_carga_str"),2);
				if( saldo < pagos){
					alert("Valor del pago en proceso excede saldo disponible de la cuenta seleccionada.");
					return false;
				}
			} else {
				return validar_carga;
			}
		}
	} catch (e) {
	}
	return true;
}

function validarToken(){
	var token = osm_getValor("token_signapp");
	
	if(!osm_esVacio(token)){
		jsonrpc._("signAppServicio.validarToken")(manejarRespuestaValidarToken, token);
		
		osm_verLoader();
		osm_block_window();
	}else{
		osm_alert("El token de SignApp es un campo obligatorio.");
		$('#token_signapp').addClass('ERR_VALIDAR');
	}
}

function manejarRespuestaCorreo(respuesta){
	console.log(respuesta);
	osm_ocultarLoader();
	osm_unblock_window();
	osm_enviarFormulario('frm_aprobar');
	
	cerrarVentanaAprobarCarga();
}

function manejarRespuestaValidarToken(respuesta){
	console.log(respuesta);
	if(respuesta == undefined || respuesta == null){
		osm_alert("No se pudo validar el token de SignApp.");
		osm_ocultarLoader();
		osm_unblock_window();
	}else{
		if(!respuesta.valid){
			alert(respuesta.msg);
			osm_ocultarLoader();
			osm_unblock_window();
		}else{
			
			if(osm_getValor("nombreFaseProceso")== "REVISION"){
				jsonrpc._("correoServicio.enviarCorreoRevision")(manejarRespuestaCorreo, osm_getValor("id_carga_adj"));
			}else if(osm_getValor("nombreFaseProceso")== "LIBERACION"){
				jsonrpc._("correoServicio.enviarCorreoLiberacion")(manejarRespuestaCorreo, osm_getValor("id_carga_adj"));
			}else{
				osm_ocultarLoader();
				osm_unblock_window();
				osm_enviarFormulario('frm_aprobar');
				
				cerrarVentanaAprobarCarga();
			}
			
		}
	}
}

function enviarFormularioAprobacion(){
	validarToken();
}

function rc_ajusteVentanaAprobacionCarga() {
	var pos = osm_getWindowSize();
	$('#vn_aprobarcarga .bloqueo_ventana_base').css("height", (pos[1] - 150) + "px").css("overflow", "auto");
}

osm_listen("resize", window, rc_ajusteVentanaAprobacionCarga);
osm_listen("load", window, rc_ajusteVentanaAprobacionCarga);
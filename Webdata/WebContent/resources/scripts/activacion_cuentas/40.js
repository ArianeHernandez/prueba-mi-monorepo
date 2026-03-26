
var LOGIN_USUARIO_ACTIVACION = null;

function validarActivacion(p){
	LOGIN_USUARIO_ACTIVACION = p.id_login;
	osm_setValor("login_usuario_conf", p.id_login);
	verVentanaValidacion();
}

function activarCuenta(){
	
	var acti = false;
	
	try{ acti = jsonrpc._("credencialJsonServicio.activarCredencial")(LOGIN_USUARIO_ACTIVACION); }catch (e) {}
	
	cerrarVentanaValidacion();
	
	if(acti){
		osm_alert("El usuario " + LOGIN_USUARIO_ACTIVACION + " ha sido activada exisosamente"); 
	}else{
		osm_alert("Lo sentimos, El usuario " + LOGIN_USUARIO_ACTIVACION + " no se puede activar.");
	}
	
	osm_go("activacion_cuentas/40.do");
}

function verVentanaValidacion() {
	
	osm_setVisible("vn_confirmacion", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaValidacion() {
	osm_setVisible("vn_confirmacion", false);
	osm_mostrarSelects("bodyContent");
}

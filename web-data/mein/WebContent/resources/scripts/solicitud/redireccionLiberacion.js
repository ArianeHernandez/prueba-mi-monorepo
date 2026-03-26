var tiempo_restante = 10;
var plantilla_mensaje = "Redirigiendo automáticamente en #tiempo restante# segundos.";
var mensaje_inicial = "";

function validar_redireccion() {
	
	var form = osm_getObjeto("form_carga");
	
	if (form) {
		mensaje_inicial = osm_getValor("mensaje_exitoso");
		redireccionar_liberacion();	
	}
	
}	

function redireccionar_liberacion() {
	
	if (tiempo_restante == 0) {
		osm_enviarFormulario('form_carga');
	} else {
		var nuevo_mensaje = mensaje_inicial + " " + plantilla_mensaje.replace("#tiempo restante#", tiempo_restante);
		osm_setValor("mensaje_exitoso", nuevo_mensaje);
		tiempo_restante--;
		window.setTimeout("redireccionar_liberacion()", 1000);
	}
	
}
osm_listen("load", window, function(e){
	  validarEstado();
});

function validarEstado(){
	var estado = null;
	try {
		var a = osm_getObjeto("appletSobreflex");
		var estado = a.getEstado();
		
		if(estado == "INICIADO"){
			osm_setValor('estado_applet', 'Esperando aceptaci\u00f3n del certificado.')
			
			window.setTimeout("validarEstado()", 3000);
			return;
		}
		
		if(estado == "ERROR"){
			osm_setValor('estado_applet', 'No es posible generar el SOBFREFLEX. Por favor verifique que se acepte el certificado.')
			return;
		}
		
		if(estado == "OK"){
			osm_setValor('estado_applet', 'Se envio el sobreflex a la impresora predeterminada.');
			
			jsonrpc._("personaServicio.enviarCorreoSobreflex")(osm_getValor("login_usuario"));
			
			return;
			
		}
		
		if(estado == "FALLO"){
			osm_setValor('estado_applet', 'No es posible generar el SOBFREFLEX. Error tratando de enviar el archivo a la impresora');
			return;
		}
		

	} catch (e) {
		window.setTimeout("validarEstado()", 100);
	}
	 
}

osm_listen("load", window, function(e){
	  validarEstado();
});

function validarEstado(){
	var estado = null;
	try {
		var a = osm_getObjeto("appletValidacion");
		var estado = a.getEstado();
		
		if(estado == "NO"){
			osm_setValor('estado_ip', 'Esperando aceptaci\u00f3n del certificado.')
			
			window.setTimeout("validarEstado()", 100);
			return;
		}
		
		if(estado == "ERROR"){
			osm_setValor('estado_ip', 'No se he podido realizar la validacion de su ubicaci\u00f3n actual. Se finalizara la sesion.')
			window.setTimeout("osm_go('inicio/0.pub?error');", 3000);
			return;
		}
		
		if(estado == "OK"){
			osm_setValor('estado_ip', 'Validando IP.');
			
			jsonrpc._("validarIPJsonServicio.validarIP")(function(res, err){
				
				if(err){
					osm_setValor('estado_ip', 'Error de conexion con el servidor al realizar la validaci\u00f3n de IP. Se finalizara la sesion.')
					window.setTimeout("osm_go('inicio/0.pub?error');", 3000);
					return;
				}
				
				osm_setValor('estado_ip', 'Validaci\u00f3n Finalizada.');
				window.setTimeout("osm_go('inicio/0.do');", 500);
			
			},a.getIps(), a.getMacs());
			
		}
		
		

	} catch (e) {
		window.setTimeout("validarEstado()", 100);
	}
	 
}

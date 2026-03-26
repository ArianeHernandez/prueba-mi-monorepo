function crearAdministrativoCliente(){
	var id=osm_getValor('id_usuario');
	if(osm_esVacio(id)){
		var tipo_usuario = jsonrpc._("contenido.obtenerContenido")("General", "CLIENTE");
		
		if(tipo_usuario == null){
			tipo_usuario = "Cliente";
		}
		
		alert("Por favor seleccione el " + tipo_usuario +" para el cual desea crear el administrador");
		return;
		
	}else{
		
		//Esta funcion se encuentra en el archivo ventana_buscarpersona.js
		mostrar_buscarpersona();
	}
	
	
}

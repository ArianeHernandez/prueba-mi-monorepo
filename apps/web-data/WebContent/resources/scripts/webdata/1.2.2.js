$(init_carga);
$(function(){
	$("#volver").hide();
})
function init_carga() {
	var mensaje = jsonrpc._("cargaServicio.obtenerMensajeCarga")();
	
	if(mensaje != null && mensaje.estado != "F"){
	
		var msj = mensaje.mensaje;
		if(mensaje.estado == "C" && mensaje.info != null){
			msj = msj + " " + osm_trim(mensaje.info);
		}
		osm_setValor("mensaje", msj);
		setTimeout("init_carga()",1000);
	}
	
	if(mensaje != null && mensaje.estado == "F"){
		$("#mensaje").hide();
		if(mensaje.exitoso){
			$("#msj_exito").show();
		}
		else{
			$("#msj_fallo").show();
		}
		$("#volver").show();
	}
}

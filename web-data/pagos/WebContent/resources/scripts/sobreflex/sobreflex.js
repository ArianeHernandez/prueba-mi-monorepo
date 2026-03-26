function crearSobre(){
	
	
	if (confirm("¿Esta seguro de enviar SOBREFLEX?")) {

		osm_enviarFormulario("form_sobreflex");
		
	}else{
		return;
	}
	
}

function p_load(){

}

osm_listen("load", window, p_load);


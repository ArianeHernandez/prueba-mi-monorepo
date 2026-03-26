function activarPestana(){
	
	$("div:contains('Valores')").find(":parent").find(".bloque_pestanas_pestana").click();
	
}

function validar_archivo(){
	
	
	if(osm_esVacio(osm_getValor("filename"))){
		
		osm_alert("Seleccione el archivo a Subir.");
		
		return false;
	}
	
	
	return true;
}


function p_load(){

}

osm_listen("load", window, p_load);


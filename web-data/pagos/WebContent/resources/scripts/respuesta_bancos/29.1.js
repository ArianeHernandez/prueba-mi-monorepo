//$(document).ready(cargarSemaforo);

function validar_archivo(){
	
	if(osm_esVacio(osm_getValor("id_banco"))){
		osm_alert("Seleccione el tipo de banco para el cual subirá el archivo.");
		
		return false;
		
	}
	
	if(osm_esVacio(osm_getValor("filename"))){
		
		osm_alert("Seleccione el archivo a Subir.");
		
		return false;
	}
	
	
	return true;
}


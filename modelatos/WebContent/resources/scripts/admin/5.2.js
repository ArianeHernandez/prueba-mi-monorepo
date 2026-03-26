
function page_validarGuardar(){

	if (osm_esVacio(osm_getValor("Variable.nombre_variable")))
	{
		osm_alert("El nombre de la Variable no puede ser vacío");
		osm_setFoco("Variable.nombre_variable");
		return false;
	}
	
	return true;
}

// ------------------------------------

function p_load(){

}

osm_listen("load", window, p_load);


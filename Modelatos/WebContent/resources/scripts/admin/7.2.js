
function page_validarGuardar(){

	if (osm_esVacio(osm_getValor("Uri.nombre")))
	{
		osm_alert("El nombre de la Uri no puede ser vacío");
		osm_setFoco("Uri.nombre");
		return false;
	}
	
	if (osm_esVacio(osm_getValor("Uri.endPoint")))
	{
		osm_alert("El endpoint de la url no puede ser vacío");
		osm_setFoco("Uri.endPoint");
		return false;
	}
	
	return true;
}

// ------------------------------------

function p_load(){

}

osm_listen("load", window, p_load);


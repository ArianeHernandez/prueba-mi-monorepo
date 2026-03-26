function page_validarGuardar()
{
	// Verifica que el nombre no se vacio
	if (osm_esVacio(osm_getValor("grupoFormato.nombre")))
	{
		osm_alert("El nombre del grupo no puede ser vacio");
		osm_setFoco("grupoFormato.nombre");
		return false;
	}
	
	if (osm_esVacio(osm_getValor("grupoFormato.descripcion")))
	{
		osm_alert("La descripción del grupo no puede ser vacio");
		osm_setFoco("grupoFormato.descripcion");
		return false;
	}
	
	if (osm_esVacio(osm_getValor("grupoFormato.orden")))
	{
		osm_alert("el orden del grupo no puede ser vacio");
		osm_setFoco("grupoFormato.orden");
		return false;
	}
	
	return true;
}


function page_validarEliminar()
{
	
	return true;
}

function page_validarGuardar()
{
	// Verifica que el nombre no se vacio
	if (osm_esVacio(osm_getValor("Rol.nombre_rol")))
	{
		osm_alert("El nombre del Rol no puede ser vacio");
		osm_setFoco("Rol.nombre_rol");
		return false;
	}
	
	return true;
}


function page_validarEliminar()
{
	if(confirm("¿Está seguro que desea eliminar el Rol?")){
		return true;	
	}else{
		return false;
	}

	
	
}

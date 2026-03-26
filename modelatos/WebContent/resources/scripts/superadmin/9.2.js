

function activarAdministrador(id_administrador)
{
	osm_setValor("activo", "S");
	osm_setValor("id_administrador", id_administrador);
	osm_enviarFormulario("form_accion");
}


function desactivarAdministrador(id_administrador)
{	
	osm_setValor("activo", "N");
	osm_setValor("id_administrador", id_administrador);
	osm_enviarFormulario("form_accion");
}


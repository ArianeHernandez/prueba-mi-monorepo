function ejecutarAccion(id_accion){
	
	osm_setValor("id_accion", id_accion);
	osm_setValor("InstanciaAccion.id_accion", id_accion);
	
	osm_enviarFormulario("frm_accion");
	
}
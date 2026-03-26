
function editarVariable(id_variable){
	osm_setValor("id_variable", id_variable );
	osm_enviarFormulario("form_variable");
}

// ------------------------------------

function p_load(){

}

osm_listen("load", window, p_load);


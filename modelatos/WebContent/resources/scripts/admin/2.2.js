
function page_validarGuardar(){
	
	// Verifica que el nombre no se vacio
	
	if(osm_esVacio( osm_getValor("Grupo.nombre") )){
		osm_alert("El nombre del Grupo no puede ser vacio");
		osm_setFoco("Grupo.nombre");
		return false;
	}
	
	return true;
}

function nombreUnico(){
	
	
	var obj = osm_getObjeto("Grupo.nombre");
	var nombreGrupo = osm_getValor("Grupo.nombre");
	var id_modelo_activo = osm_getValor("id_modelo_activo");
	
	if(!osm_getValor("Grupo.id_grupo")){
		var grupo = jsonrpc._("grupoServicio.obtenerGrupoPorNombre")(nombreGrupo, id_modelo_activo);
		if (grupo != null){ 
			osm_alert("El nombre '" + nombreGrupo + "' ya existe, por favor digite otro.");
			osm_setValor("Grupo.nombre", "");
			osm_setFoco("Grupo.nombre");
		}
	}	
}


function p_load(){
	

	try{
		var jj = 1;
		var eliminar = osm_getObjeto("eliminar.GrpEst_" + jj);
		while( eliminar!=null )
		{
			eliminar.valor = jj;
			eliminar.onclick = function(){
				osm_setValor("id_estructura", osm_getValor("id_estructura_" + this.valor) );
				osm_enviarFormulario("form_disociar");
			}
			
			jj++;
			eliminar = osm_getObjeto("eliminar.GrpEst_" + jj);
		}
	}catch(_e){}
	
	//-------
	
	osm_listen("blur", "Grupo.nombre", nombreUnico);
}

osm_listen("load", window, p_load);


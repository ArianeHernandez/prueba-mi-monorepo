function ver_tarea(id_tarea){
	
	osm_setValor("id_tarea",id_tarea);
	osm_enviarFormulario("formVerTarea");
	
}

function eliminar(id_tarea){
	if(!confirm("\u00bfDesea eliminar la tarea?")){
		return;
	}
	
	try{
		var rta = jsonrpc._("tareaServicio.eliminarTarea")(id_tarea);
		if(rta){
			$("#tarea_"+id_tarea).remove();
			return true;
		}
	}catch(e){
		alert(e);
	}
	alert("No fue posible eliminar la tarea");	
} 

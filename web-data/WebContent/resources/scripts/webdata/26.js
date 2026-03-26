function cargarRoles(id_servicio){
	if(osm_esVacio(id_servicio)){
		return;
	}
	var lista = jsonrpc._("accesoRolServicio.obtenerRolesPorServicio")(id_servicio);
	mostrarRoles(lista, id_servicio);
}

function mostrarRoles(lista, id_servicio){
	if(lista != null){
		lista = lista.list;
		$("[name='fila_rol_" + id_servicio + "']").remove();
		for ( var i = 0; i < lista.length; i++) {
			osm_construirHTML("div_servicio_" + id_servicio, "ROL_PLANTILLA", 
					[lista[i].id_rol,
					 lista[i].nombre_rol,
					 id_servicio
					 ]
			);
		}
	}
	else {
		alert("No hay datos");
	}
}

function agregarRol(id_servicio, id_rol){
	if(osm_esVacio(id_rol)){
		return;
	}
	cargarRoles(id_servicio);
	var fila = $("#fila_"+id_servicio + "_"+id_rol);
	if(fila.length > 0){
		fila.css("background-color","#CBCBCB");
		fila.animate({"background-color":"#F1F1F1"}, 800);
		return;
	}
	var rta = jsonrpc._("accesoRolServicio.guardarAccesoRol")(id_servicio, id_rol);
	if(rta){
		var txt = osm_getValorText("select_rol_"+id_servicio);
		osm_construirHTML("div_servicio_" + id_servicio, "ROL_PLANTILLA", 
				[id_rol,
				 txt,
				 id_servicio
				 ]
		);
	}else{
		alert("No es posible asociar el rol.");
	}
}
function eliminarRol(id_servicio, id_rol){
	var rta = jsonrpc._("accesoRolServicio.eliminarAccesoRol")(id_servicio, id_rol);
	if(rta){
		$("#fila_" + id_servicio + "_" + id_rol).remove();
	}
	else{
		alert("No es posible eliminar el rol");
	}
}

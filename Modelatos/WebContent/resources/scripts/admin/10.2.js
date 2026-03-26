function guardar(){

	if(osm_esVacio(osm_getValor("ListaDinamica.nombre"))){
		alert("Ingrese el nombre de la lista dinamica.");
		return;
	}
	
	if(osm_esVacio(osm_getValor("ListaDinamica.c_id"))){
		alert("Ingrese el valor de 'Identificacion'.");
		return;
	}
	
	if(osm_esVacio(osm_getValor("ListaDinamica.c_nombre"))){
		alert("Ingrese el valor de 'Etiqueta'.");
		return;
	}
	
	if(osm_esVacio(osm_getValor("ListaDinamica.consulta"))){
		alert("Ingrese el valor de 'Fuente'.");
		return;
	}
	
	osm_enviarFormulario("form_edicion");
}

function eliminar(){
	var respuesta = confirm ("¿Está seguro de querer eliminar la lista dinámica?")
	if (respuesta)
		osm_enviarFormulario("form_desactivar");
}

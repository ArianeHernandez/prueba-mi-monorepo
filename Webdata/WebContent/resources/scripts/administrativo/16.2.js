function eliminarRol(id_rol){
	
	$("#fila_"+id_rol).remove();

} 

function agregarRol(obj){
	
	var id_rol = obj.value;
	
	if(!osm_esVacio(id_rol))
	{
	
		if (osm_getObjeto("fila_"+id_rol) != null)  {
			var fila = $("#fila_"+id_rol);
			fila.css("background-color","#CBCBCB");
			fila.animate({"background-color":"#fff"}, 800);
			return;	
		}
		
		var num_rol = parseInt(osm_getValor("n_roles")) + 1;
		
		var nombre_rol = osm_getValor("nombre_"+id_rol);
		var desc_rol = osm_getValor("descrip_"+id_rol);
		
		if(!osm_esVacio(desc_rol)){
			nombre_rol = nombre_rol +" - " + desc_rol;
		}
		if(nombre_rol.length>59){
			nombre_rol=nombre_rol.substr(0,59);
		}
		osm_construirHTML("div_roles", "rol_plantilla", [id_rol, nombre_rol, num_rol]);
		osm_setValor("n_roles", num_rol);
	}
	
	obj.value = "";
	
}

function validar_roles(){
	roles = $("[name='fila_rol']");
	if (roles.length == 0) { 
		return confirm("No ha seleccionado ningún rol, ¿desea continuar?");
	}
	return true;
}

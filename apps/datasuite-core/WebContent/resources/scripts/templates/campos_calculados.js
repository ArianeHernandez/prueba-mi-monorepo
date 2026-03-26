var ID_FORMATO_OPERACION = 1;

function agregarCampoCalculado(){
	
	var id = "FO" + ID_FORMATO_OPERACION++;
	
	var id_campo = osm_getValor("sel_campo_calculado");
	var campo = osm_getValorText("sel_campo_calculado");
	
	var id_lista_din = osm_getValor("sel_lista_din_calculado"); 
	var id_operacion = osm_getValor("sel_var_operacion");
	
	osm_construirHTML("CAMPOS_CALCULADOS", "PLANTILLA_CAMPO_CALCULADO", [id ,id_campo, campo]);
	
	osm_setValor("Formato.operaciones:" + id + ".id_listadinamica", id_lista_din);
	osm_setValor("Formato.operaciones:" + id + ".id_operacion", id_operacion);
	
		
}

function eliminarVariableCampo(id_formato_operacion){
	$("#formato_operacion_"+id_formato_operacion).remove()
}

function validarCamposCalculados(){
	var selects = $("#CAMPOS_CALCULADOS").find("select");
	for ( var i = 0; i < selects.length; i++) {
		if(osm_esVacio(selects[i].value)){
			alert("Debe seleccionar un valor para crear el campo calculado");
			$(selects[i]).focus();
			return false;
		}
	}
	return true;
}

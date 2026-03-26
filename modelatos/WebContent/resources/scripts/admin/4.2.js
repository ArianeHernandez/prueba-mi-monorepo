$(document).ready(iniciar);

function iniciar(){
	
	//Se actualizan las listas de variables segun la estructura del formato
	
	id_estructura = osm_getValor('Formato.id_estructura');
	if (!osm_esVacio(id_estructura)) {
		var campos = jsonrpc._("formatoServicio.obtenerCamposPorEstructura")(id_estructura);
		
		//Actualiza los campos de variables de liberacioin - ver template
		actualizarListaVariablesLiberacion(campos);
	}
	
	
	
	//Si tiene activo cargas relacionadas se muestra el bloque correspondiente
	
	obj_cargas_relacionadas = osm_getObjeto('Formato.cargas_relacionadas');
	if (obj_cargas_relacionadas!=null && obj_cargas_relacionadas.checked) {
		$("#bloque_cargas_relacionadas").show();
		
	}else{
		$("#bloque_cargas_relacionadas").hide();
		
	}
		
	
}

function page_validarGuardar()
{

	var i = 0;

	// Verifica que el nombre no se vacio

	if (osm_esVacio(osm_getValor("Formato.nombre")))
	{
		osm_alert("El nombre del Formato no puede ser vacio");
		osm_setFoco("Formato.nombre");
		return false;
	}

	if (osm_esVacio(osm_getValor("Formato.registrosporcarga")))
	{
		osm_alert("El numero de Registros por carga no puede ser vacio");
		osm_setFoco("Formato.registrosporcarga");
		return false;
	}


	var inputs = document.getElementsByTagName("INPUT");
	for (i = 0; i < inputs.length; i++)
	{
		var inputItem = inputs[i];
		var idd = inputItem.id + "]";

		if (idd.indexOf(".titulo]") > 0 && inputItem.value.length == 0)
		{
			
			var continuar = true;
			
			var sidd = idd.substring(0, idd.length - 7 ) + "agregarFormato";
			var obagregar = osm_getObjeto(sidd);
			if(obagregar!=null){
				if(!obagregar.checked){
					continuar = false;
				}
			}
			
			if(continuar)
			{
				osm_alert("El Titulo no puede ser vacio");
				osm_setFoco(inputItem.id);
				
				return false;
			}
			
		}

	}

	osm_setValor("franjas",VALOR_CRITERIO_HORARIO_CP);
	
	//Si NO tiene activo cargas relacionadas se muestra el bloque correspondiente
	
	obj_cargas_relacionadas = osm_getObjeto('Formato.cargas_relacionadas');
	if (obj_cargas_relacionadas!=null && !obj_cargas_relacionadas.checked) {
		osm_setValor("Formato.diasvigencia_carga_relacionada",'');
		osm_setValor("Formato.idformato_carga_relacionada",'');
			
		
	}
	
	var ok = false;
	
	if(validarCamposCalculados){
		ok = validarCamposCalculados();
	}
	
	if (ok && validarEstilos){
		return validarEstilos();
	}
	
	return true;
}

function p_load()
{
	osm_setFoco("Formato.nombre");
	
}

//---------------------------------------------------------------------------------
//funcion para agregar estructuras que se aplicaran automaticamente

function agregarEstructura(obj){
	var id_estructura = obj.value;
	
	if(!osm_esVacio(id_estructura))
	{
	
		if (osm_getObjeto("fila_"+id_estructura) != null)  {
			var fila = $("#fila_"+id_estructura);
			fila.css("background-color","#CBCBCB");
			fila.animate({"background-color":"#fff"}, 800);
			return;	
		}
		
		var total = parseInt(osm_getValor("n_estructuras")) + 1;
		
		
		var nombre_negocio = obj.options[obj.selectedIndex].text;
		var desc_negocio = osm_getValor("descrip_"+id_estructura);
		
		osm_construirHTML("estructuras_aplicar", "negocio_plantilla", [id_estructura, nombre_negocio, total]);
		osm_setValor("n_estructuras", total);
	}
	
	obj.value = "";
	
}


//---------------------------------------------------------------------------------
//funcion que permite eliminar estructuras que se aplicaran atumaticamente
function eliminarEstructura(id_estructura){
	
	$("#fila_"+id_estructura).remove();

}

//---------------------------------------------------------------------------------
function mostrarEdicionEstructuraFormato(){
	osm_enviarFormulario('form_edicion_estructura');
}

//---------------------------------------------------------------------------------
function toggleCargasRelacionadas(){
	$('#bloque_cargas_relacionadas').toggle();
	

}

function toggleAdjuntosFormulario() {
	$("#usa_adjuntos_formulario_block").toggle();
}

function toggleAdjuntosAdicionalesFormulario() {
	$("#usa_adjuntos_adicionales_block").toggle();
}

osm_listen("load", window, p_load);

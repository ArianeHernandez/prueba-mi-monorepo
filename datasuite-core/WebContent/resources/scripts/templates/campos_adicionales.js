$(function(){
			$('.date-pick').datepicker({minDate: 0});
			iniciarCamposDinamicos();
			});
					
/***
 * Funcion de validacion de campos adicionales obligatorios,
 * se llama en los botones de aprobar o guardar.
 * */

function validarCargaAprobar(){
	var selects = $(".obligatorio");
	
	for(var i = 0; i < selects.length ; i++){
		if(osm_esVacio(selects[i].value)){
			selects[i].focus();
			alert("Por favor, seleccione el valor para el campo " + osm_getValor(selects[i].id + "_name"));
			return false;
		}	
	}
	return true;
}


function iniciarCamposDinamicos(){
	$("input[name='campo_actualizable']").each(
	function(){
		//Si es negocio
		var fun = new Function("changeCampoDinamico(this.value," + this.value + ")");
		var id_campo_padre = osm_getValor("id_campo_padre_" + this.value);
		if(id_campo_padre == '-1'){
			$("#select_negocios_libera").change(fun);
			$("#select_negocios_libera").change();
		}else if(id_campo_padre == ''){
			var fun = new Function("changeCampoDinamico(this.value,'')");
			$("#select_negocios_libera").change(fun);
			$("#select_negocios_libera").change();
		}else{
			$("#select_campo_"+id_campo_padre).change(fun);	
		}
	});
}

function changeCampoDinamico(valor, id_campo){
	var id_lista_dinamica = osm_getValor("id_lista_dinamica_" + id_campo);
	var select = osm_getObjeto("select_campo_"+id_campo);
	var options = select.options;
	options.length = 0;
	options[0] = new Option("Actualizando...");
	
	$("[name='campo_hijo_" + id_campo+"']").each(function(){
		$("#select_campo_"+this.value).find("option").remove();
	});
	
	var lista = jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamica")(id_lista_dinamica, valor);
	actualizarCampo(id_campo, lista);
	
		
}

function actualizarCampo(id_campo, lista){
	var select = osm_getObjeto("select_campo_"+id_campo);
	var options = select.options;
	if(lista != null){
		lista = lista.list;
		options.length = 0;
		options[options.length] = new Option("--Seleccione--","");
		for(var i = 0 ; i < lista.length; i++){
			var reg = lista[i];
			options[options.length] = new Option(reg.nombre, reg.id);
		}
		if(lista.length > 0){
			return;
		}
	}
}

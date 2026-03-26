//---------------------------------------------------------
$(function(){
			iniciarCamposDinamicos();
			});

$(function(){
	var id_formato = osm_getValor("Variable.id_formato");
	
	var text_html_preparacion = jsonrpc._("formatoWDJsonServicio.obtenerHTMLPreparacion")(id_formato);
	
	if (text_html_preparacion != null) {
		$("#div_html_preparacion").html(text_html_preparacion);
	}
});
					
function iniciarCamposDinamicos(){
	$("input[name='campo_actualizable']").each(
	function(){
		//Si es negocio
		var fun = new Function("changeCampoDinamico(this.value," + this.value + ")");
		var id_campo_padre = osm_getValor("id_campo_padre_" + this.value);
		
		if(id_campo_padre == '-1'){
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
	
	var lista = jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamica")(new Function("lista", "actualizarCampo('" +id_campo+ "', lista)"),
	 id_lista_dinamica, valor);
	$("[name='campo_hijo_" + id_campo+"']").each(function(){
		$("#select_campo_"+this.value).find("option").remove();
	});	
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

function validacion_carga_masiva() {
	
	var existen_archivos = osm_getValor('existen_archivos_adj');
	var adjunto_obligatorio = osm_getValor("adjunto_obligatorio");
	
	if(existen_archivos == "false" && adjunto_obligatorio == "S"){
		alert("Debe adjuntar archivos");
		return false;
	}
	
	return true;
}


function enviar_carga(){
	if(validarEnvioCarga()){
		var tipo_persona_cliente=$('#tipo_persona_cliente').val();
		var mensaje_preparacion = osm_getValor("mensaje_preparacion");
		if(tipo_persona_cliente=="true"){
			verVentanaAprobarCarga();
		}else if(tipo_persona_cliente==""){
			//si el usuario es persona juridica entonces es el preparador y pregunta
			
			if(mensaje_preparacion != ""){
				mensaje_preparacion = "<b>" + mensaje_preparacion + "</b><br><br>";
			}
			
			
			s_accept("\u00BFEsta seguro de enviar la carga?", 
					 mensaje_preparacion, 
					 "Enviar", function(r){
				 if(r==true){
					 osm_enviarFormulario('form_enviar');
				}
			 })
			
	
		}else{
			return;
		}
	}
}

function validarEnvioCarga(){
	var validar = osm_getValor("validacionActiva");
	if(validar=="S"){
		
		var ii = 1;
		var obj = osm_getObjeto("campocontrol_" + ii);
		var nomobj = osm_getObjeto("nombrecampocontrol_" + ii);
		while( obj != null ){
			
			var valorControl= osm_getValor("campocontrol_" + ii);
			var valorUsuario = osm_getValor("campouser_"+ii);
			
			//Valida que el valor no sea vacio
			if(osm_esVacio(valorUsuario)){
				osm_alert("El campo " +nomobj.value+" no puede ser vac\u00edo");
				return false;
			}
			
			if(parseFloat(valorControl)!=parseFloat(valorUsuario)){
				osm_alert("Fallo validaci\u00f3n en el campo '"+nomobj.value+"'");
				return false;
			}
			
			ii++;
			obj = osm_getObjeto("campocontrol_" + ii);
			nomobj = osm_getObjeto("nombrecampocontrol_" + ii);
		}
		
	}
	
	
	var validacionCamposDinamicos=false;
	try {
		//Funcion para validaciones especificas, se utiliza en el liberador
		if(validarCargaAprobar){
			validacionCamposDinamicos = validarCargaAprobar();
		}
	} catch (e) {
		validacionCamposDinamicos=true;
	}
	
	if(!validacionCamposDinamicos){
		return false;
	}
	
	return true;
}

//-----------------------------------------------

function mostrarDoubleConFormato(id){
	
	//Se oculta el campo
	var div = osm_getObjeto('div_sinformato_double_'+id);
	$(div).hide();
	
	//Se muestra el div con el dato formatiado
	var valor = osm_getValor("campouser_"+id);
	
	if(!isNaN(valor)){
		valor = osm_formatoMoneda(valor);
	
		osm_setValor('div_formato_double_'+id,valor);
	}else{
		osm_setValor('div_formato_double_'+id,"Error");
	
	}
	
	
	var div = osm_getObjeto('div_formato_double_'+id);
	$(div).show();
	
}

function mostrarDoubleSinFormato(id){
	
	//Se oculta el div con formato
	var div = osm_getObjeto('div_formato_double_'+id);
	$(div).hide();
	
	//Se muestra el campo con el valor sin formato
	var div = osm_getObjeto('div_sinformato_double_'+id);
	$(div).show();
	
	//Se obtiene el foco
	osm_setFoco("campouser_"+id);
	
}




//------------------------------------------------

function p_load()
{
}





osm_listen("load", window, p_load);


function verVentanaAprobarCarga() {
	osm_setVisible("vn_aprobarcarga", true, true);
	osm_ocultarSelects("bodyContent");
}

function enviarDefinitivamente(){
	osm_enviarFormulario('form_enviar');
}

function cerrarVentanaAprobarCarga() {
	osm_setVisible("vn_aprobarcarga", false);
	osm_mostrarSelects("bodyContent");
}



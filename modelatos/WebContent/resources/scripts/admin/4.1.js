
function cambiarNegocio(id){
	
	if (osm_getValor(id) != "") {
		osm_enviarFormulario('form_negocio');
	}
	
}

function activarUsuario(id_formato){
	var cc_id = "cajachequeo_au_" + id_formato;
	
	var valor = osm_getValor(cc_id);
	
	var activo = false;
	if(valor=="S"){
		activo = true;
	}
	
	try{
		var ejecutado = jsonrpc._("formatoBaseServicio.activarFormatoUsuario")( id_formato, activo);
		
		if(ejecutado==null || !ejecutado)
		{
			osm_alert("Ha ocurrido un problema al realizar la operacion. Por favor intente nuevamente.");
			osm_setMarca(cc_id, !osm_getMarca(cc_id));
		}
	}catch(_e){
		osm_alert("Ha ocurrido un problema al realizar la operacion. Por favor intente nuevamente.");
		osm_setMarca(cc_id, !osm_getMarca(cc_id));
	}
	
}



function disponibleFormatoNegocio(id_negocio, sttodos){
	
	var id_formato = osm_getValor("id_formato_DE");
	var accion = osm_getValor("accion_DE");
	
	var activo = false;
	if(accion=="S"){
		activo = true;
	}
	
	var todos = false;
	if(sttodos=="S"){
		todos = true;
	}
	
	try{
		var ejecutado = jsonrpc._("formatoBaseServicio.disponibleFormatoNegocio")( id_formato, id_negocio, activo, todos);
		
		if(ejecutado==null || !ejecutado)
		{
			osm_alert("Ha ocurrido un problema al realizar la operacion. Por favor intente nuevamente.");
			osm_setMarca(cc_id, !osm_getMarca(cc_id));
		}
	}catch(_e){
		osm_alert("Ha ocurrido un problema al realizar la operacion. Por favor intente nuevamente.");
		osm_setMarca(cc_id, !osm_getMarca(cc_id));
	}
	
}

// -------------------------------------------------

function accionDisponibleEmpresa(id_formato){
	if(osm_esVacio(osm_getValor('_id_negocio'))){
		alert('Seleccione un negocio');
		return false;
	}
	if( osm_getMarca("cajachequeo_de_" + id_formato) ){
		osm_setValor("id_formato_DE", id_formato);
		osm_setValor("accion_DE", "S");
		osm_setValor("mensaje_disponible_empresa", "\u00bfDesea que el formato este disponible para todos los Negocios?");
		verVentana("vn_cambio_estado_activo");
		
	}else{
		osm_setValor("id_formato_DE", id_formato);
		osm_setValor("accion_DE", "N");
		osm_setValor("mensaje_disponible_empresa", "\u00bfDesea que el formato NO este disponible para todos los Negocios?");
		verVentana("vn_cambio_estado_activo");
	}
}

function cancelar_DE(){
	var cc_id = "cajachequeo_de_" + osm_getValor("id_formato_DE");
	osm_setMarca(cc_id, !osm_getMarca(cc_id));
	cerrarVentana('vn_cambio_estado_activo')
}

// -------------------------------------------------

function verVentana(idv) {
	osm_setVisible(idv, true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentana(idv) {
	osm_setVisible(idv, false);
	osm_mostrarSelects("bodyContent");
}

// -------------------------------------------------

function seleccionarFormato(id_formato, tipoformato, bloqueado){
	
	if(bloqueado =='S'){
		
		osm_alert("Lo sentimos, no se puede realizar la edici\u00f3n del Formato.");
		
		return false;
	}
	
	osm_setValor("id_formato", id_formato );

	if(tipoformato=='S'){
		osm_setDestinoFormulario("form_formatos", "4.8.do");
	}
	
	osm_enviarFormulario("form_formatos");
}


function p_load(){
	

	try{
		$("#filtro").focus();
		$("#filtro").change(function(){
			osm_enviarFormulario("form_filtro");
		});
		
		$('#filtro').keydown(function(e){
		    if (e.keyCode==13) { //Enter keycode
		    	osm_enviarFormulario("form_filtro");
		    }
		});
		
		var jj = 1;
		var fila = osm_getObjeto("fila_formato_" + jj);
		while( fila!=null )
		{
			fila.valor = jj;
			fila.onclick = function(){
				
				osm_setValor("id_formato", osm_getValor("id_formato_" + this.valor) );
				var tipo=osm_getValor("tipof_"+this.valor);
				if(tipo=='S'){
					document.getElementById("form_formatos").action="4.8.do";
				}
				osm_enviarFormulario("form_formatos");
			}
			
			jj++;
			fila = osm_getObjeto("fila_formato_" + jj);
		}
	}catch(_e){}
}

osm_listen("load", window, p_load);


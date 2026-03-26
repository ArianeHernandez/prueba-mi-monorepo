//---------
osm_listen('load', window, function(){
	
	osm_listen('change', 'Proceso.id_proceso', guardarProceso);
	osm_listen('change', 'Proceso.nombre', guardarProceso);
	osm_listen('change', 'Proceso.estado', guardarProceso);
	osm_listen('change', 'Proceso.id_formato_salida', guardarProceso);
	osm_listen('change', 'Proceso.id_grupoformato', listenerSelectGrupoFormato);
	
});

//---------

function eliminarFormato(id_formato){
	var rta = jsonrpc._("procesoJsonServicio.eliminarFormato")(id_formato);
	if(rta){
		$("#fila_"+id_formato).remove();
	}else{
		alert("Error al eliminar el formato");
	}
}

function agregarFormato(id_select){
	var id_formato = osm_getValor(id_select);
	
	if(osm_esVacio(id_formato)){
		return;
	}
	
	var fila = $("#fila_"+id_formato);
	if(fila.length){
		fila.css("background-color","#CBCBCB");
		osm_getObjeto(id_select).selectedIndex = 0;
		fila.animate({"background-color":"#fff"}, 800);
		return;
	}
	
	var rta = jsonrpc._("procesoJsonServicio.agregarFormato")(id_formato);
	if(rta){
		var nombre = osm_getValorText(id_select);
		osm_construirHTML("div_formatosE", "TEMPLATE_FILA_FORMATO", [id_formato, nombre]);
	}else{
		alert("Error al agregar el formato");
	}
	
	osm_getObjeto(id_select).selectedIndex = 0;
}

function guardarProceso(){
	
	try {
		
		var nombre = osm_getValor("Proceso.nombre");
		var estado = osm_esSeleccionado("Proceso.estado")?"A":"I";
		var id_formato_entrada = osm_getValor("Proceso.id_formato_entrada");
		var id_formato_salida = osm_getValor("Proceso.id_formato_salida");
		var id_grupoformato = osm_getValor("Proceso.id_grupoformato");
		
		var ok = jsonrpc._("procesoJsonServicio.actualizarProceso")(nombre, estado, id_formato_salida, id_grupoformato, 0);
		
		if(!ok){
			alert("No se puede realizar la operaci\u00f3n.");
			osm_go('crear_proceso/10.2.do');
		}
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n..");
		osm_go('crear_proceso/10.2.do');
	}
	
}

// ---------------------

function eliminarRevision(id_revision){
	
	try {
		var ok = jsonrpc._("procesoJsonServicio.eliminarRevision")(id_revision);

		if(ok){
			$("#box_revision_" + id_revision).slideUp(400, function(){ $(this).remove(); });
		}else{
			alert("No se puede eliminar la revision.");
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
}

// ---------------------

function agregarRevision(id_revision_anterior, id_caja){
	
	var nombre = prompt("Introduzca el nombre de la revisión.");		
	
	if(!osm_esVacio(nombre)){
		
		try {
			var id_revisor = jsonrpc._("procesoJsonServicio.agregarRevision")(id_revision_anterior, nombre);
	
			if(id_revisor){
				var PL = osm_getValor('PLANTILLA_REVISION');
				
				PL = osm_remplazar(PL, '[ID_REV]', id_revisor);
				PL = osm_remplazar(PL, '[NOMBRE]', nombre);
				
				$('#' + id_caja).after(PL);
				
				$('#box_revision_' + id_revisor).slideDown(400);
			}else{
				alert("No se puede asociar el revisor.");
			}
			
		} catch (e) {
			alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
			osm_go('crear_proceso/10.2.do');
		}
	}
	
}

// -------------------

function actualizarNombreRevision(id_revision, nombre){
	try {
		var ok = jsonrpc._("procesoJsonServicio.actualizarNombreRevision")(id_revision, nombre);

		if(!ok){
			alert("No cambiar el nombre de la revisión.");
			osm_go('crear_proceso/10.2.do');
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
}


//--------------------

function listenerSelectGrupoFormato(){
	//Se eliman todos los formatos de salida asociados
	$(".eliminarFormatosEntrada").click();
	
	//Se limpia el campo formato de salida
	osm_setValor("Proceso.id_formato_salida","");
	
	guardarProceso();
	
	try {
		var id_grupoformato = osm_getValor("Proceso.id_grupoformato");
		jsonrpc._("formatoBaseServicio.obtenerFormatosPorGrupoFormato")(pintarSelectoresDeFormatos, id_grupoformato);
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
	
	
}

//--------------------
function pintarSelectoresDeFormatos(formatos){

	if(formatos!=null && formatos.list.length>0){
		//Se cargan las lista
		var j = 0;
		var k = 0;
		
		formatosEntrada = new Array();
		formatosSalida = new Array(); 
		 
		for (var i = 0; i < formatos.list.length; i++){
			if(formatos.list[i].tipoformato == "E"){
				formatosEntrada[j++] = formatos.list[i]; 	
			}else{
				formatosSalida[k++] = formatos.list[i];
			}
		}
		
		crearSelectorDeFormatosDeEntrada(formatosEntrada);
		
		//Se cargan los clientes
		
		crearSelectorDeFormatosDeSalida(formatosSalida);
		
	}
}

//--------------------

function crearSelectorDeFormatosDeEntrada(formatos){
	//Se crean las opciones
	if (formatos != null) {
		
		var selector = osm_getObjeto("select_formatoE");
		$(selector).empty();
		
		//Creamos la opcion por defecto
		selector.options[selector.options.length] = new Option("--seleccione--", "");
		
		var i = 0;
		while (i < formatos.length) {
			formato = formatos[i];
			
			selector.options[selector.options.length] = new Option(formato.nombre, formato.id_formato);
			
			//Se incrementa el acumulador
			i++;
		}
		
	}
	
}

//--------------------

function crearSelectorDeFormatosDeSalida(formatos){
	//Se crean las opciones
	if (formatos != null) {
		
		var selector = osm_getObjeto("Proceso.id_formato_salida");
		$(selector).empty();
		
		//Creamos la opcion por defecto
		selector.options[selector.options.length] = new Option("--seleccione--", "");
		
		var i = 0;
		while (i < formatos.length) {
			formato = formatos[i];
			
			selector.options[selector.options.length] = new Option(formato.nombre, formato.id_formato);
			
			//Se incrementa el acumulador
			i++;
		}
		
	}
	
}





$(init_estructuras);

function init_estructuras(){
//	$("#filtro").focus();
//	$("#filtro").change(function(){
//		osm_enviarFormulario("form_filtro");
//	});
//	
	$('#filtro').keydown(function(e){
	    if (e.keyCode==13) { //Enter keycode
	    	osm_enviarFormulario("form_filtro");
	    }
	});
	buscarEstructura();
}

function buscarEstructura(){
	$("#filtro").autocomplete(
			{
				search : function(event, ui) {
					$(this).addClass("ui-autocomplete-loading");
					
					var filtro = event.target.value;
					
					//JSON A CONSULTAR
					var datos = jsonrpc._("estructuraServicio.buscarNombresEstructuras")(filtro);
					
					var listado = new Array();
					
					if (datos != null) {
						datos = datos.list;
						for ( var i = 0; i < datos.length; i++) {
							listado[i] = new Object();
							listado[i].label = datos[i];
							listado[i].value = datos[i];
						}
					}
					
					$(this).autocomplete("option", "source", listado);
					$(this).removeClass("ui-autocomplete-loading");

				},
				minLength : 1,
				source: [],
				close: function(){	
					osm_enviarFormulario("form_filtro");
				}
			});
}

function verVentana(){
	osm_setVisible("ventanaImport", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentana(){
	osm_setVisible("ventanaImport", false, false);
	osm_mostrarSelects("bodyContent");
}

function verEstructura(id){
	osm_setValor("id_estructura", id);
	osm_setDestinoFormulario("form_estructura",CONTEXTPATH + "/estructuras/1.2.do");
	osm_enviarFormulario("form_estructura");
}

function ordenarCampos(id){
	osm_setValor("id_estructura", id);
	osm_setDestinoFormulario("form_estructura",CONTEXTPATH + "/estructuras/11.do");
	osm_enviarFormulario("form_estructura");
}

function editarDatos(id){
	osm_setValor("id_estructura", id);
	osm_setDestinoFormulario("form_estructura",CONTEXTPATH + "/datosestructura/12.1.do");
	osm_enviarFormulario("form_estructura");
}

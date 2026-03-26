/****************
 * Modo edicion	*
 ****************/
function eliminar_negocio(id_negocio){
	if (confirm("\u00bfDesea eliminar el negocio?")) {
		var rta = jsonrpc._("negocioServicio.eliminarNegocio")(id_negocio);
		if (rta) {
			$("#negocio_"+id_negocio).remove();
		}
		else {
			alert("No es posible eliminar el negocio!");
		}
	}
	
}
function agregarNegocio(){
	osm_setMarca("activo",true);
	mostrar_Ventana();
}
function actualizar_estado(id_negocio){
	var id = "activo_"+id_negocio;
	var rta = jsonrpc._("negocioServicio.actualizarActivoNegocio")(id_negocio, osm_getMarca(id) ? "S": "N");
	if (!rta) {
		alert("No es posible actualizar es estado del negocio!");
		osm_setMarca(id, !osm_getMarca(id));
	}
}
function validarNegocio(){
	if (osm_esVacio(osm_getValor("id_negocio"))) {
		var existe = jsonrpc._("negocioServicio.obtenerNegocioPorCodigo")(osm_getValor("cod_negocio"));
		if (existe != null) {
			alert("El c\u00f3digo ya existe!");
			osm_setValor("cod_negocio","");
			return false;
		}
	}
	return true;
}
function guardarNegocio(){
	
	var negocio = jsonrpc._("negocioServicio.guardarNegocio")(
			osm_getMarca("activo") ? "S": "N", osm_getValor("cod_negocio"), osm_getValor("descripcion"),
			osm_getValor("id_modelo"), osm_getValor("id_negocio"), osm_getValor("nombre")
			);
	
	if (negocio == null || negocio.id_negocio == null) {
		alert("No es posible guardar la informaci\u00f3n del negocio!");
		cerrar_Ventana();
		return;
	}
	
//	else {
		
//		if (osm_getObjeto("cod_negocio_" + negocio.id_negocio) == null) {
//			
//			var fila = $("#negocio_template").clone().insertAfter("#tabla_negocios tr:last-child");
//			var id = "negocio_"+negocio.id_negocio;
//			fila.attr("id",id);
//			
//			var parametros = [negocio.id_negocio];
//
//			var htmlplantilla = osm_getObjeto(id).innerHTML;
//			
//			for(var j=0; j<parametros.length; j++){
//				htmlplantilla = osm_remplazar(htmlplantilla, '[' +(j+1) + ']', parametros[j]);
//			}
//			
//			osm_getObjeto(id).innerHTML = htmlplantilla;
//			
//			osm_setValor("cod_negocio_" + negocio.id_negocio, negocio.cod_negocio);
//			osm_setValor("descripcion_" + negocio.id_negocio, negocio.descripcion);
//			osm_setValor("id_modelo_" + negocio.id_negocio, negocio.id_modelo);
//			osm_setValor("nombre_" + negocio.id_negocio, negocio.nombre);
//			osm_setMarca("activo_" + negocio.id_negocio, (negocio.activo == "S"));
//		}
//		
//		else {
//			osm_setMarca("activo_" + negocio.id_negocio, (negocio.activo == "S"));
//			osm_setValor("cod_negocio_" + negocio.id_negocio, negocio.cod_negocio);
//			osm_setValor("descripcion_" + negocio.id_negocio, negocio.descripcion);
//			osm_setValor("id_modelo_" + negocio.id_negocio, negocio.id_modelo);
//			osm_setValor("nombre_" + negocio.id_negocio, negocio.nombre);
//		}
		
//	}
	
	cerrar_Ventana();
	osm_go('negociosvigentes/9.1.do');
}

function cerrar_Ventana(){
	
	osm_setVisible('vn_editarNegocio', false);
	
	var ids =["cod_negocio", "descripcion", "id_modelo", "nombre", "id_negocio"];
	for ( var i = 0; i < ids.length; i++) {
		osm_setValor(ids[i], "");
	}
	if (!osm_getMarca("activo")) {
		osm_setMarca("activo",!osm_getMarca("activo"));
	}
	osm_getObjeto("cod_negocio").readOnly = false;
	osm_mostrarSelects("bodyContent");
}

//osm_setMarca(id, !osm_getMarca(id));

function mostrar_editarNegocio(id_negocio){
	
	var ids =["cod_negocio", "descripcion", "id_modelo", "nombre"];
	
	for ( var i = 0; i < ids.length; i++) {
		osm_setValor(ids[i], osm_getValor(ids[i]+"_"+id_negocio));
	}
	
	osm_setMarca("activo", osm_getMarca("activo_"+id_negocio));
	osm_setValor("id_negocio",id_negocio);
	osm_getObjeto("cod_negocio").readOnly = true;
	
	mostrar_Ventana();
	
}

function mostrar_Ventana(){
	osm_setVisible('vn_editarNegocio', true, true);
	osm_ocultarSelects("bodyContent");
}

function enviarFormularioNeg(id_negocio)
{
	osm_setValor("negocio", id_negocio);
	osm_enviarFormulario("form_negocios");
}
/****************
 * Modo vista	*
 ****************/
function enviarFormulario(id_negocio)
{
	osm_setValor("id_negocio", id_negocio);
	osm_enviarFormulario("form_negocios");
}


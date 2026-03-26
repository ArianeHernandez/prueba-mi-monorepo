CANTIDAD_REPORTES_ADMIN = 0;

function realizarCreacionReporteAdmin(){	
	osm_setValor("id_reporte", '' );
	osm_enviarFormulario("form_edicion_reporte_admin");
}

function realizarEdicionReporteAdmin(id_repo){
	osm_setValor("id_reporte", id_repo );
	osm_enviarFormulario("form_edicion_reporte_admin");
}

function listarReportesAdmin(){
	
	var servicioReporteDim = jsonrpc._("reporteAdminJsonServicio.obtenerReportesAdmin")();
	var servicioReporteDimList = servicioReporteDim.list;

	if(servicioReporteDimList.length>0){
		
		osm_setValor('lista_reportes_admin', '');
		osm_construirHTML('lista_reportes_admin', 'PLANTILLA_LISTA_REPORTES_ADMIN');
		osm_setValor('contenido_lista_reportes_admin', '');
		
		for(var i=0; i< servicioReporteDimList.length; i++){
			
			var  servicioReporteDim = servicioReporteDimList[i];

			// mira si se ha cargado el servicio
			var cargado = jsonrpc._("reporteAdminJsonServicio.obtenerSiCargado")(servicioReporteDim.id);
			
			var parametros = [servicioReporteDim.id, servicioReporteDim.nombre, cargado];
			
			pintarReporteAdmin('contenido_lista_reportes_admin', parametros);
			
			osm_setValor('cargado_'+servicioReporteDim.id, cargado);
			document.getElementById('cargado_'+servicioReporteDim.id).checked = (osm_esVacio(cargado))?false:true;
		}
	}
	
	CANTIDAD_REPORTES_ADMIN = servicioReporteDimList.length;
	
}

function pintarReporteAdmin(id_padre, parametros){
	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_REPORTE_ADMIN', parametros);
}

function eliminarReporteAdmin(id_reporte){
	
	// mensaje advertencia
	if(!confirm("Esta acci\u00f3n borrar\u00e1 todas las asignaciones del reporte.\n\u00bfDesea continuar?"))
		return false
	
	var exitoEliminar = jsonrpc._("reporteAdminJsonServicio.borrarReporteAdmin")(id_reporte);
	
	if(exitoEliminar){
		
		var objItemProcesoAdmin = osm_getObjeto('item_reporte_admin_'+id_reporte);
		var objPadre = osm_getObjeto('contenido_lista_reportes_admin');
		objPadre.removeChild(objItemProcesoAdmin);
		
		CANTIDAD_REPORTES_ADMIN--;
		
		var exitoActualizarLista = jsonrpc._("reporteAdminJsonServicio.QuitarListaServicios")(id_reporte);
		
	}else{
		alert('Ha ocurrido un error al eliminar el reporte.');
	}

	if(CANTIDAD_REPORTES_ADMIN==0){
		osm_setValor('lista_reportes_admin', 'No existen reportes');
	}
		
}

// ACCIONES
function check(id){
	var exitoActualizarLista = false;
	
	var res = osm_getValor('cargado_'+id);
	if(osm_esVacio(res)){
		// con chulito
		exitoActualizarLista = jsonrpc._("reporteAdminJsonServicio.AgregarListaServicios")(id);
		osm_setValor('cargado_'+id, 'true');
	}
	else{
		// sin chulito
		exitoActualizarLista = jsonrpc._("reporteAdminJsonServicio.QuitarListaServicios")(id);
		osm_setValor('cargado_'+id, '');
	}
}

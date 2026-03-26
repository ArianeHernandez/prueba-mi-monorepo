CANTIDAD_ASIGNACIONES = 0;

function listarReportesAsignados(){
	
	var asignacionesDim = jsonrpc._("asignacionReporteJsonServicio.obtenerReportesAsignados")();
	var asignacionesDimList = asignacionesDim.list;
	
	CANTIDAD_ASIGNACIONES = asignacionesDimList.length;

	if(CANTIDAD_ASIGNACIONES > 0){
		
		osm_setValor('lista_reportes_admin', '');
		osm_construirHTML('lista_reportes_admin', 'PLANTILLA_LISTA_REPORTES_ADMIN');
		osm_setValor('contenido_lista_reportes_admin', '');
		
		for(var i=0; i< CANTIDAD_ASIGNACIONES; i++){
			
			var  asignacion = asignacionesDimList[i];
			
			var parametros = [asignacion.id_asignacion, asignacion.titulo];
			
			pintarAsignacion('contenido_lista_reportes_admin', parametros);
		}
	}
}

function pintarAsignacion(id_padre, parametros){
	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_REPORTE_ADMIN', parametros);
}

function actualizarAsignacion(id_asignacion){
	osm_setValor("id_asignacion",id_asignacion);
	osm_enviarFormulario("form_asignacion_reporte")
}

function eliminarAsignacion(id_asignacion){
	var exitoEliminar = jsonrpc._("asignacionReporteJsonServicio.borrarAsignacionReporte")(id_asignacion);
	
	if(exitoEliminar){
		
		var objItemProcesoAdmin = osm_getObjeto('item_reporte_admin_'+id_asignacion);
		var objPadre = osm_getObjeto('contenido_lista_reportes_admin');
		objPadre.removeChild(objItemProcesoAdmin);
		
		CANTIDAD_ASIGNACIONES--;
		
	}else{
		alert('Ha ocurrido un error al eliminar el reporte.');
	}

	if(CANTIDAD_ASIGNACIONES==0){
		osm_setValor('lista_reportes_admin', 'No existen reportes asignados');
	}
}

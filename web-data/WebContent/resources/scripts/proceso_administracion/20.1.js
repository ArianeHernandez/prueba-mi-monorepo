
CANTIDAD_PROCESOS_ADMIN = 0;

function realizarCreacionProcesoAdmin(){
	mostrarVentanaProcesoAdmin();
}

function realizarEdicionProcesoAdmin(id_proceso_admin, id_negocio){
	osm_setValor("id_proceso_admin_es", id_proceso_admin );
	osm_setValor("id_negocio_es", id_negocio );
	osm_enviarFormulario("form_edicion_proceso_admin");
}

function listarProcesosAdmin(){
	
	var procesosAdmin = jsonrpc._("procesoAdminJsonServicio.obtenerProcesosAdmin")();
	var procAdminList = procesosAdmin.list;

	if(procAdminList.length>0){
		
		osm_setValor('lista_procesos_admin', '');
		osm_construirHTML('lista_procesos_admin', 'PLANTILLA_LISTA_PROCESOS_ADMIN');
		osm_setValor('contenido_lista_procesos_admin', '');
		
		for(var i=0; i< procAdminList.length; i++){
			
			var  procesoAdmin = procAdminList[i];

			var parametros = [procesoAdmin.id_proceso_admin, procesoAdmin.nombre, procesoAdmin.id_negocio];
			
			pintarProcesoAdmin('contenido_lista_procesos_admin', parametros);
		}
	}
	
	CANTIDAD_PROCESOS_ADMIN = procAdminList.length;
	
}

function pintarProcesoAdmin(id_padre, parametros){
	
	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_PROCESO_ADMIN', parametros);
	
}

function crearProcesoAdmin(){
	
	var id_proceso_admin = jsonrpc._("procesoAdminJsonServicio.obtenerSiguienteId")();
	
	var selectNegocio = osm_getObjeto('select_negocio_proceso_admin');
	var indiceSeleccionadoNeg = selectNegocio.selectedIndex;
	var opcionSeleccionadaNeg = selectNegocio.options[indiceSeleccionadoNeg];
	var id_negocio = opcionSeleccionadaNeg.value;
	
	var selectFormato = osm_getObjeto('select_formato_proceso_admin');
	var indiceSeleccionadoForm = selectFormato.selectedIndex;
	var opcionSeleccionadaForm = selectFormato.options[indiceSeleccionadoForm];
	var id_formato_entrada = opcionSeleccionadaForm.value;
	
	var nombre = osm_getObjeto('cajatex_nombre_proceso_admin').value;
	
	var exitoCrear = jsonrpc._("procesoAdminJsonServicio.insertarProcesoAdmin")(id_proceso_admin, id_negocio, id_formato_entrada, nombre);

	if(exitoCrear){
		
		if(CANTIDAD_PROCESOS_ADMIN==0){
			osm_setValor('lista_procesos_admin', '');
			osm_construirHTML('lista_procesos_admin', 'PLANTILLA_LISTA_PROCESOS_ADMIN');
			osm_setValor('contenido_lista_procesos_admin', '');
		}
		
		var parametros = [id_proceso_admin, nombre, id_negocio];
		
		pintarProcesoAdmin('contenido_lista_procesos_admin', parametros);
		CANTIDAD_PROCESOS_ADMIN++;
		
	}else{
		alert('Ha ocurrido un error al crear el proceso.');
	}
	
}

function eliminarProcesoAdmin(id_proceso_admin){
	
	if (osm_comfirm("\u00BFEst\u00E1 seguro que desea eliminar el proceso?")){
		
		var exitoEliminar = jsonrpc._("procesoAdminJsonServicio.borrarProcesoAdmin")(id_proceso_admin);
		
		if(exitoEliminar){
			
			var objItemProcesoAdmin = osm_getObjeto('item_proceso_admin_'+id_proceso_admin);
			var objPadre = osm_getObjeto('contenido_lista_procesos_admin');
			objPadre.removeChild(objItemProcesoAdmin);
			
			CANTIDAD_PROCESOS_ADMIN--;
		}else{
			alert('Ha ocurrido un error al eliminar el proceso.');
		}
		
		if(CANTIDAD_PROCESOS_ADMIN==0){
			osm_setValor('lista_procesos_admin', 'No existen procesos');
		}
	}
		
}

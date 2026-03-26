$(document).ready(inicio);

INTERVALO_RECARGA = 60000;
function verDetalleCarga(id_carga){
	
	osm_setValor("id_carga", id_carga);
	osm_enviarFormulario("form_siguiente");
	
}

function regarcarPagina(){
	//osm_go("administracion_carga/proceso/22.1.1.do");
	
}

function inicio(){
	setInterval("regarcarPagina()", INTERVALO_RECARGA);
}



function mostrarPaginaMisPendientes(){
	osm_go("administracion_carga/proceso/22.1.do");
}


function guardarInstanciaParaListarAdministrativosHijo(id_instancia){
	
	//Guardamos la instancia seleccionada en session
	jsonrpc._("procesoAdminJsonServicio.guardarIdInstanciaParaListarAdministrativosHijo")(id_instancia);
	
	$("#select_instancia_hija").attr("disable",true);
	/*
	 * Esta funcion pertenece al archivo semaforoCargasPendientesPorAdminHijo.js
	 * Cargamos los semaforos de los administrativos hijo 
	 */
	cargarSemaforoAdminHijo();
	
	$("#select_instancia_hija").attr("disable",false);
		
}


function validarPermisosCargaPorInstanciaAdminHijo(id_carga, id_instancia){

	if (!osm_esVacio(id_carga) && !osm_esVacio(id_instancia)) {
	
		listaInstanciasPendientes = jsonrpc._("instanciaJsonServicio.obtenerInstanciasPreviasConCargaActualPendiente")(id_instancia, id_carga);
		
		instanciasPendientes = listaInstanciasPendientes.list;
		
		if(instanciasPendientes!= null && instanciasPendientes.length>0){
			
			var mensaje = "No se puede realizar ninguna acci\u00f3n sobre la transacci\u00f3n mientras las siguientes instancias del proceso no hayan dado un concepto sobre \u00e9sta. Instancias: "
			for (var i=0; i<instanciasPendientes.length; i++) {
				mensaje = mensaje +" - "+instanciasPendientes[i].nombre;
			};
			
			osm_alert(mensaje);
			
			
			return false;
			
		}else if(instanciasPendientes!= null && instanciasPendientes.length==0){
			return true;
			
		}else{
			osm_alert("Error realizando la validacion. Por favor intentelo nuevamente");
			return false;
		}
		
	}else{
		osm_alert("Error realizando la validacion. Por favor intentelo nuevamente");
		return false;
	}
	
}

function ejecutarAccionAdminHijo(id_carga, id_accion, id_instancia, nombre_instancia){
	
	
	
	validacionExitosa = validarPermisosCargaPorInstanciaAdminHijo(id_carga, id_instancia );
	
	if (validacionExitosa) {
		
		if (confirm('\u00BFEstas seguro de ejecutar la accion?')) {
		
		
			jsonrpc._("accionJsonServicio.inicializarVariablesSessionParaAdminHijo")(id_carga);
			
			var id_administrativo = osm_getValor("id_administrativo");
			var id_proceso_admin = osm_getValor("id_proceso_carga");
			
			osm_setValor("id_accion_admin_hijo", id_accion);
			osm_setValor("InstanciaAccion.id_instancia", id_instancia);
			osm_setValor("InstanciaAccion.id_accion", id_accion);
			osm_setValor("InstanciaAccion.id_proceso_admin", id_proceso_admin);
			osm_setValor("InstanciaAccion.id_carga", id_carga);
			osm_setValor("InstanciaAccion.id_administrativo", id_administrativo);
			
			
			osm_enviarFormulario('frm_accion_admin_hijo');
			
		}
	}
	
}

function aprobarCargaAdminHijo(id_carga, id_instancia){
	
	
	validacionExitosa = validarPermisosCargaPorInstanciaAdminHijo(id_carga, id_instancia );
	
	if (validacionExitosa) {
		
		if (confirm('\u00BFEstas seguro que desea aprobar la transacci\u00F3n?')) {
		
		
			jsonrpc._("accionJsonServicio.inicializarVariablesSessionParaAdminHijo")(id_carga);
			osm_enviarFormulario('frm_aprobar_admin_hijo');
		}
	}
}

function rechazarCargaAdminHijo(id_carga, id_instancia){
	
	validacionExitosa = validarPermisosCargaPorInstanciaAdminHijo(id_carga, id_instancia );
	
	if (validacionExitosa) {
	
		if (confirm('\u00BFEstas seguro que desea rechazar la transacci\u00F3n?')) {
		
		
			jsonrpc._("accionJsonServicio.inicializarVariablesSessionParaAdminHijo")(id_carga);
			osm_enviarFormulario('frm_rechazar_admin_hijo');
			
		}
		
	}
}


function crearSelectorDeClientes(clientes){
	//Se crean las opciones
	if (clientes != null) {
		
		var selector = osm_getObjeto("select_filtro_cliente");
		$(selector).empty()
		
		//Creamos la opcion por defecto
		selector.options[selector.options.length] = new Option("--Todos--", "");
		
		lista = clientes;
		
		var i = 0;
		while (i < lista.length) {
			cliente = lista[i];
			
			nombreUsuario = !cliente.nombre_usuario?'':cliente.nombre_usuario;
			apellidoUsuario = !cliente.apellido_usuario?'':cliente.apellido_usuario;  
		
			selector.options[selector.options.length] = new Option(nombreUsuario + ' ' +apellidoUsuario, cliente.id_usuario);
			
			//Se incrementa el acumulador
			i++;
		}
		
		
		filtro_cliente = osm_getValor("filtro_cliente");
		//Se establece el valor ya elegido
		if (!osm_esVacio(filtro_cliente)) {
			
			obj = osm_getObjeto("select_filtro_cliente");
			$(obj).val(filtro_cliente);
		}
		
	}
	
}



function ordenar_filtrar_cargas(){
	
	//parametros de ordenamiento
	var parametro_ordenamiento = osm_getValor("selector_orden");
	var tipo_ordenamiento = osm_getValor("selector_tipo_orden");
	osm_setValor("orden", parametro_ordenamiento);
	osm_setValor("tipo_orden", tipo_ordenamiento);
	
	//parametros de filtro
	var filtro_instancia_carga = osm_getValor("select_filtro_instancia_carga");
	var filtro_tipo_carga = osm_getValor("select_filtro_tipo_carga");
	var filtro_semaforo = osm_getValor("select_filtro_semaforo");
	var filtro_cliente = osm_getValor("select_filtro_cliente");
	
	osm_setValor("filtro_instancia_carga", filtro_instancia_carga);
	osm_setValor("filtro_tipo_carga", filtro_tipo_carga);
	osm_setValor("filtro_semaforo", filtro_semaforo);
	osm_setValor("filtro_cliente", filtro_cliente);
	
	var filtro_orden_activo = osm_getValor("filtro_orden_activo");
	osm_enviarFormulario("form_orden_filtro");
	
}

function mostrarPendientesAdminHijos(){
	
	osm_go("administracion_carga/proceso/22.1.1.do");
}



function validarPermisosCargaPorInstancia(id_carga, id_instancia){

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

function ejecutarAccionCarga(id_carga, id_accion, id_instancia, nombre_instancia){
	
	
	validacionExitosa = validarPermisosCargaPorInstancia(id_carga, id_instancia );
	
	if (validacionExitosa) {
		
		if (confirm("\u00bfEstas seguro de ejecutar la accion '" + nombre_instancia + "'?")){
       
    
			jsonrpc._("accionJsonServicio.inicializarVariablesSession")(id_carga, id_instancia);
			
			var id_administrativo = osm_getValor("id_administrativo");
			var id_proceso_admin = osm_getValor("id_proceso_carga");
			
			osm_setValor("id_accion", id_accion );
			osm_setValor("InstanciaAccion.id_instancia", id_instancia);
			osm_setValor("InstanciaAccion.id_accion", id_accion);
			osm_setValor("InstanciaAccion.id_proceso_admin", id_proceso_admin);
			osm_setValor("InstanciaAccion.id_carga", id_carga);
			osm_setValor("InstanciaAccion.id_administrativo", id_administrativo);
			
			
			osm_enviarFormulario('frm_accion');
		}
	
		
	}
	
}

function aprobarCarga(id_carga, id_instancia){
	
	validacionExitosa = validarPermisosCargaPorInstancia(id_carga, id_instancia );
	
	if (validacionExitosa) {
		if (confirm('\u00BFEstas seguro que desea aprobar la transacci\u00F3n?')) {
		
		
			jsonrpc._("accionJsonServicio.inicializarVariablesSession")(id_carga, id_instancia);
			osm_enviarFormulario('frm_aprobar');
			
		}
	}
}

function rechazarCarga(id_carga, id_instancia){
	
	validacionExitosa = validarPermisosCargaPorInstancia(id_carga, id_instancia );
	
	if (validacionExitosa) {
	
		s_accept("\u00BFEstas seguro que desea rechazar la transacci\u00F3n?", "Al rechazar la transacci\u00F3n se finalizar\u00E1 el proceso.", "Rechazar", function(ac){
			
			
			if(ac){
				s_prompt("Motivo del Rechazo", "Ingrese el motivo del rechazo", function(ok, motivo){
					
					if(ok){
						jsonrpc._("accionJsonServicio.inicializarVariablesSession")(id_carga, id_instancia);
						$("#motivorechazo").val(motivo);
						
						osm_enviarFormulario('frm_rechazar');
					}else{
						s_alert_ok("Rechazo Cancelado", "El rechazo de la solicitud a sido cancelado.");
					}
				})
			
			
			}
			
			
		})
		
		
	}
}

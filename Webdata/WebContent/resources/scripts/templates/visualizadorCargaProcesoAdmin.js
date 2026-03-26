
function pintarCarga(carga, id_contenedor_cargas, listaInstanciasAdministrativo){
	//Segun el porcentanje trasncurrido en la carga se establece el color a mostrar
	var estiloSemaforo = "semaforo_error";
	
	if (carga.estado == "S") {
		if (carga.porcentajeTiempoTranscurrido >= 100) {
			estiloSemaforo = "semaforo_rojo";
		
		}
		else if (carga.porcentajeTiempoTranscurrido >= 50 && carga.porcentajeTiempoTranscurrido < 100) {
				estiloSemaforo = "semaforo_amarillo";
			
		}
		else if (carga.porcentajeTiempoTranscurrido >= 0 && carga.porcentajeTiempoTranscurrido < 50) {
					estiloSemaforo = "semaforo_verde";
				
		}
		
	}else if(carga.estado == "W"){
		//Si la carga aun requiere archivos adjuntos
		estiloSemaforo = "semaforo_gris";
	
	}
	
	//Segun el tipo de carga se establece el estilo correspondiente
	var estiloTipoCarga = "";
	if(carga.id_tipocarga==1){
		estiloTipoCarga="tipo_carga_interactivo";
	}else{
		estiloTipoCarga="tipo_carga_lotes";
	}
	
	//Se estable si el documento tiene firma
	var estiloDocFirmado = "";
	
	var tieneFirmaDoc = jsonrpc._("cargaServicio.tieneDocumentoFirmadoPorFasePreparacion")(carga.id_carga);
	
	if(tieneFirmaDoc){
		estiloDocFirmado = "icono_documento_firmado";
	}
	
	
	//Se establece si la transaccion tiene firma
	var estiloTransaccionFirmada = "";
	
	var tieneFirmaTran = jsonrpc._("cargaServicio.tieneFormularioFirmadoPorFaseLiberacion")(carga.id_carga);
	
	if(tieneFirmaTran){
		estiloTransaccionFirmada = "icono_transaccion_firmada";
	}
			
	
	
	
	osm_construirHTML(id_contenedor_cargas, 'PLANTILLA_CARGA', [carga.id_carga, // 1
														 carga.id_instancia,  // 2
														 estiloSemaforo, // 3
														 carga.tiemporestante, // 4 
														 carga.fecha_subida_string, // 5 
														 estiloTipoCarga, // 6
														 carga.nombre_usuario, // 7 
														 !carga.apellido_usuario?'':carga.apellido_usuario, // 8 
														 !carga.valor_total_bigdecimal?'N/A':osm_formatoMoneda(carga.valor_total_bigdecimal), // 9 
														 carga.nombre_instancia, // 10
														 carga.numero_registros_bigdecimal, // 11
														 estiloDocFirmado, // 12
														 estiloTransaccionFirmada // 13
														 ]);
	
	//Se pintan las acciones solo si la carga se ha subido completamente
	//las cargas que requieren archivo (estado W no se pintan acciones)
	if (carga.estado == "S") {
	
		pintarAccionesPorCarga(carga, listaInstanciasAdministrativo);
	}
	
	pintarVariablesLiberacionPorCarga(carga);	
	
}

function pintarVariablesLiberacionPorCarga(carga){
	//Se consulta las variables de liberacion por carga
	var variables = jsonrpc._("cargaServicio.obtenerVariablesLiberacionPorCarga")(carga.id_carga);
			
	if(variables!=null){
		var variablesCarga = variables.list; 
		
		for (var j = 0; j < variablesCarga.length; j++) {
		
			osm_construirHTML('div_area_variables_liberacion_' + carga.id_carga, 'PLANTILLA_VARIABLE_LIBERACION_CARGA', [carga.id_carga, variablesCarga[j].id_campo, variablesCarga[j].nombreVariableLiberacion, variablesCarga[j].valor]);
			
		}
				
	}
	
}


function pintarAccionesPorCarga(carga, listaInstanciasAdministrativo){
	
	//Se consultan las acciones asociadas a la carga
	var instancias = listaInstanciasAdministrativo.list;
	var accionesPorInstancia = null;
	var instanciaCarga = null;
	
	for (var i = 0; i < instancias.length; i++) {
		if(instancias[i].id_instancia == carga.id_instancia){
			instanciaCarga = instancias[i];
			accionesPorInstancia = instancias[i].accionesPorInstancia;
		}
	}
	
	//Se recorresn las acciones por instancia de la carga
	if (accionesPorInstancia != null) {
		var accionesCarga = accionesPorInstancia.list;
		for (var j = 0; j < accionesCarga.length; j++) {
		
			//Solo se pintan las acciones que sean visibles
			if (accionesCarga[j].oculto != 'S') {
			
				osm_construirHTML('div_acciones_carga_' + carga.id_instancia+'_'+carga.id_carga, 'PLANTILLA_ACCION_CARGA', [accionesCarga[j].id_accion, accionesCarga[j].nombre, accionesCarga[j].id_instancia, carga.id_carga]);
				
			}
		}	
		
	}
	
	//Si crean los botones de aprobar o rechzar
	if(instanciaCarga.aprobar=='S'){
		osm_construirHTML('div_acciones_carga_' + carga.id_instancia+'_'+carga.id_carga, 'PLANTILLA_APROBAR_CARGA', [carga.id_carga, carga.id_instancia ]);
	}
	
	if(instanciaCarga.rechazar=='S'){
		osm_construirHTML('div_acciones_carga_' + carga.id_instancia+'_'+carga.id_carga, 'PLANTILLA_RECHAZAR_CARGA', [carga.id_carga, carga.id_instancia ]);	
	}
	
}

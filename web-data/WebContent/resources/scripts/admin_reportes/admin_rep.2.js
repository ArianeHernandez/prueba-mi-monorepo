var PARAMETROS = 0;
var RESULTADOS = 1;
var NAVEGACIONES = 2;
var ACCIONES = 3;
var ATRIBUTOS_REPORTE = [0,0,0,0];

var ID_UNICO = 0

function recuperarReporte(){
	
	var reporte = null;
	
	if(!osm_esVacio(osm_trim(osm_getValor("id_reporte"))))	
		reporte = jsonrpc._("reporteAdminJsonServicio.obtenerReporte")(osm_trim(osm_getValor("id_reporte")));
	
	if(reporte != null){
		osm_setValor('Reporte.nombre', reporte.nombre);
		osm_setValor('Reporte.consulta', reporte.consulta);
		osm_setValor('Reporte.id', reporte.id);
		osm_setValor('Reporte.nombre_conexion', reporte.nombre_conexion);
			
		listarParametrosReporte(reporte.parametros);
		listarNavegacionesReporte(reporte.navegacion);
		listarResultadosReporte(reporte.resultados);
		listarAccionReporte(reporte.accionfila);
	}
}

function listarParametrosReporte(parametrosReporte){
	
	var listaParametrosReporte = parametrosReporte.list;
	ATRIBUTOS_REPORTE[PARAMETROS] = listaParametrosReporte.length; 
	
	if(ATRIBUTOS_REPORTE[PARAMETROS] > 0){

		osm_setValor('contenido_lista_parametro', '');
		
		for(var i=0; i< ATRIBUTOS_REPORTE[PARAMETROS]; i++){
			
			var parametroReporte = listaParametrosReporte[i];
	
			var parametros = [parametroReporte.id_parametro, parametroReporte.nombre,
			                  parametroReporte.tipo, parametroReporte.encriptado,
			                  parametroReporte.filtro, parametroReporte.titulo,
			                  parametroReporte.orden];

			osm_construirHTML('contenido_lista_parametro', 'PLANTILLA_PARAMETRO', parametros);
			
			osm_setValor('Reporte.parametros:' + parametroReporte.id_parametro + '.filtro', parametroReporte.filtro);
			osm_setValor('Reporte.parametros:' + parametroReporte.id_parametro + '.encriptado', parametroReporte.encriptado);
			osm_setValor('Reporte.parametros:' + parametroReporte.id_parametro + '.tipo', parametroReporte.tipo);
			
		}
	
	}
	
}

function listarResultadosReporte(resultadosReporte){
	
	var listaResultadosReporte = resultadosReporte.list;
	ATRIBUTOS_REPORTE[RESULTADOS] = listaResultadosReporte.length; 
	
	if(ATRIBUTOS_REPORTE[RESULTADOS] > 0){

		osm_setValor('contenido_lista_resultado', '');
		
		for(var i=0; i< ATRIBUTOS_REPORTE[RESULTADOS]; i++){
			
			var resultadoReporte = listaResultadosReporte[i];
	
			var parametros = [resultadoReporte.id_resultado, resultadoReporte.titulo, resultadoReporte.nombre,
			                  resultadoReporte.tipo, resultadoReporte.encriptado,
			                  resultadoReporte.oculto, resultadoReporte.orden];

			osm_construirHTML('contenido_lista_resultado', 'PLANTILLA_RESULTADO', parametros);
			
			osm_setValor('Reporte.resultados:' + resultadoReporte.id_resultado + '.oculto', resultadoReporte.oculto);
			osm_setValor('Reporte.resultados:' + resultadoReporte.id_resultado + '.encriptado', resultadoReporte.encriptado);
			osm_setValor('Reporte.resultados:' + resultadoReporte.id_resultado + '.tipo', resultadoReporte.tipo);
			
		}
	
	}
	
}

function listarNavegacionesReporte(navegacionesReporte){
	
	var listaNavegacionesReporte = navegacionesReporte.list;
	ATRIBUTOS_REPORTE[NAVEGACIONES] = listaNavegacionesReporte.length; 
	
	if(ATRIBUTOS_REPORTE[NAVEGACIONES] > 0){

		osm_setValor('contenido_lista_navegacion', '');
		
		for(var i=0; i< ATRIBUTOS_REPORTE[NAVEGACIONES]; i++){
			
			// Navegacion
			var navegacionReporte = listaNavegacionesReporte[i];
	
			var parametros = [navegacionReporte.id_navegacion, navegacionReporte.nombre,
			                  navegacionReporte.destino];

			osm_construirHTML('contenido_lista_navegacion', 'PLANTILLA_NAVEGACION', parametros);
			
			// Parametros Navegacion
			listaNavegacionParametros = navegacionReporte.parametros.list;
			//alert("parametros len: " + listaNavegacionParametros.length);
			
			for(var j=0; j < listaNavegacionParametros.length; j++){
				
				var navegacionParametro = listaNavegacionParametros[j];
								
				parametros = [navegacionParametro.id_navegacion, navegacionParametro.id_parametro_accion, navegacionParametro.nombre,
				              navegacionParametro.tipo, navegacionParametro.encriptado,
				              navegacionParametro.valor];
				
				osm_construirHTML('contenido_parametros_navegacion_' + navegacionParametro.id_navegacion, 'PLANTILLA_PARAMETROS_NAVEGACION', parametros);
				
				osm_setValor('Reporte.navegacion:' + navegacionParametro.id_navegacion + '.parametros:' + navegacionParametro.id_parametro_accion + '.encriptado', navegacionParametro.encriptado);
				osm_setValor('Reporte.navegacion:' + navegacionParametro.id_navegacion + '.parametros:' + navegacionParametro.id_parametro_accion + '.tipo', navegacionParametro.tipo);
			}
			
		}
	
	}
	
}

function listarAccionReporte(accionFila){
	
	ID_UNICO++;
	
	if(!osm_esVacio(accionFila)){
		osm_setValor('contenido_lista_accion', '');
		
		var parametros = [accionFila.id_reporte, accionFila.subreporte,
		                  accionFila.destino, ID_UNICO];

		osm_construirHTML('contenido_lista_accion', 'PLANTILLA_ACCION', parametros);
		
		// seleccionar el tipo de reporte a mostrar segun valores de la BD
		if(accionFila.subreporte == null){
			$('#radio_reporte').attr('checked','true')
			radioTipo_ReporteClicked('radio_reporte')
		}
		
		osm_setValor('Reporte.accionfila.subreporte', accionFila.subreporte)
		osm_setValor('Reporte.accionfila.destino', accionFila.destino)
		
		ATRIBUTOS_REPORTE[ACCIONES]++;
		
		// Colocar parametros de accionfila
		var parametrosAccion = accionFila.parametros.list;
		
		//alert('len: '+parametrosAccion.length)
		
		for(var i = 0; i < parametrosAccion.length; i++){
			par = parametrosAccion[i];
			
			parametros = [par.id_reporte, par.id_parametro_accion, par.nombre,
			              par.tipo, par.encriptado,
			              par.valor];
			
			//alert(parametros);
			
			osm_construirHTML('contenido_parametros_accion_' + ID_UNICO, 'PLANTILLA_PARAMETROS_ACCION', parametros);
			
			osm_setValor('Reporte.accionfila.parametros:' + par.id_parametro_accion + '.encriptado', par.encriptado);
			osm_setValor('Reporte.accionfila.parametros:' + par.id_parametro_accion + '.tipo', par.tipo);
		}
		
	}
	
}

// ACCIONES

function page_agregar(tipo, num){
	
	if(num == ACCIONES && ATRIBUTOS_REPORTE[num] == 1){
		alert('Solamente puede tener una acci\u00f3n con cero o m\u00e1s par\u00e1metros por reporte...');
		return false;
	}
		
	ATRIBUTOS_REPORTE[num]++;
	ID_UNICO++;
	
	switch(num){
		case PARAMETROS:
			var parametros = ['nuevo_' + tipo + '_' + ID_UNICO, '',
	                  null, null,
	                  null, '',
	                  ATRIBUTOS_REPORTE[num]];
			break;
		case NAVEGACIONES:
			var parametros = ['nuevo_' + tipo + '_' + ID_UNICO, '',
			                 ''];
			break;
		case RESULTADOS:
			var parametros = ['nuevo_' + tipo + '_' + ID_UNICO, '', '',
			                  null, null, null, ATRIBUTOS_REPORTE[num]];
			break;
		case ACCIONES:
			var parametros = ['nuevo_accion_' + ID_UNICO, '','', ID_UNICO];
			break;
		default:
			break;
	}
	
	osm_construirHTML('contenido_lista_' + tipo, 'PLANTILLA_' + tipo.toUpperCase(), parametros);
}

function page_quitar(tipo, num, id_parametro){
	
	osm_setValor(tipo + '_' + id_parametro, '');
	
	ATRIBUTOS_REPORTE[num]--;
}


function agregar_navegacion_param(id){
	
	ID_UNICO++;
	
	var parametros = [id, 'nuevo_parametro_navegacion_' + id + '_' + ID_UNICO, '',
	                  'string', 'S',
	                  'nuevo valor'];
	
	osm_construirHTML('contenido_parametros_navegacion_' + id, 'PLANTILLA_PARAMETROS_NAVEGACION', parametros);
}

function agregar_accion_param(id){
	
	ID_UNICO++;
	
	var parametros = [id, 'nuevo_parametro_accion_' + id + '_' + ID_UNICO, '',
	                  'string', 'S',
	                  'nuevo valor'];
	
	osm_construirHTML('contenido_parametros_accion_' + id, 'PLANTILLA_PARAMETROS_ACCION', parametros);
}

function quitar_navegacion_param(id_param){
	osm_setValor('parametro_navegacion_' + id_param, '');
}

function quitar_accion_param(id_param){
	osm_setValor('parametro_accion_' + id_param, '');
}

// VALIDACIONES

function page_validarGuardar(){	
	
	if(!nombreValido()){
		alert("El Nombre del Reporte es un campo OBLIGATORIO");
		return false;
	}
		
	// verifica si se esta editando un reporte o si es uno nuevo
	var id = osm_getValor("Reporte.id")
	if (osm_esVacio(id)){
		id = jsonrpc._("reporteAdminJsonServicio.obtenerSiguienteReporteId")();
		osm_setValor("Reporte.id", id)
		osm_setValor("nuevo", "true")
	}
	
	// verificaciones si tiene accion fila
	/*var accionFilaCont = $('#contenido_lista_accion');
	if(accionFilaCont[0].textContent != ''){*/
	s = osm_getValor('Reporte.accionfila.subreporte')
	d = osm_getValor('Reporte.accionfila.destino')
	if(s != '' || d != ''){
		// verifica que se selecciono un reporte
		var radio = $('#radio_reporte') 
		if(radio[0].checked == true){
			var input = osm_getValor('Reporte.accionfila.destino') 
			if(input == ''){
				alert("Debe seleccionar un Reporte de la lista desplegable ...");
				return false;
			}
		}
		var radio = $('#radio_subreporte')	
		if(radio[0].checked == true){
			var input = osm_getValor('Reporte.accionfila.subreporte') 
			if(input == ''){
				alert("Debe seleccionar un SubReporte de la lista desplegable ...");
				return false;
			}
		}
		
		// verifica que se mande un parametro
		var parametros = $("input[id^='Reporte.accionfila.parametros'][id!='\s'][id$='nombre']")
		var valores = $("input[id^='Reporte.accionfila.parametros'][id!='\s'][id$='valor']")
		var withParam = false
		var specialParam = false
		for(i = 0; i < parametros.length; i++){
			if(parametros[i].value == ''){
				alert("Todos los Parametros deben tener un nombre... ");
				return false;
			}
			if(valores[i].value.charAt(0) == '#' && valores[i].value.charAt(valores[i].value.length-1) == '#'){
				specialParam = true
			}
			if(parametros[i].value.indexOf('[') == -1){
				withParam = true
			}
		}
		if(!withParam){
			alert("Debe haber por lo menos un Parametro...");
			return false;
		}
		if(!specialParam){
			alert("Por lo menos uno de los parametros debe tener como valor una variable con formato: #valor_variable# ");
			return false;
		}
	}
	
	var confirmado = confirm("\u00bfEst\u00e1 seguro que desea guardar el reporte?");
	
	if(confirmado){
		osm_setValor('PLANTILLA_PARAMETRO', '');
		osm_setValor('PLANTILLA_NAVEGACION', '');
		osm_setValor('PLANTILLA_RESULTADO', '');
		osm_setValor('PLANTILLA_ACCION', '');
		osm_setValor('PLANTILLA_PARAMETROS_NAVEGACION', '');
		osm_setValor('PLANTILLA_PARAMETROS_ACCION', '');
		
		exitoActualizarLista = jsonrpc._("reporteAdminJsonServicio.QuitarListaServicios")(id);
		
		return true;
	}else{
		return false;
	}
	
}

function nombreValido(){
	var nom = osm_getValorText('Reporte.nombre');
	return (!osm_esVacio(nom));
}

function radioTipo_ReporteClicked(id){
	if(id == 'radio_reporte'){
		$('#input_reporte').css("display","none");
		$('#input_destino').css("display","block");
		osm_setValor('Reporte.accionfila.subreporte','')
	}

	if(id == 'radio_subreporte'){
		$('#input_destino').css("display","none");
		$('#input_reporte').css("display","block");
		osm_setValor('Reporte.accionfila.destino','')
	}
	
}

/*
function p_load(){
	var id = osm_getValor("id_reporte")
	
	alert(id)
}

osm_listen("load", window, p_load);
*/

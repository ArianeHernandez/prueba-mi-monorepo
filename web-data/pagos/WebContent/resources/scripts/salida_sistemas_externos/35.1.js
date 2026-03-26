
var PAGINACION = new Object();
$(iniciar35);


var ORDEN_COMANDO = 1;
function iniciar35(){
	MAX_REGISTROS_PINTADO = osm_getValor("tamano_pagina");
}

function listarRegistros(){
	var tipo_archivo = osm_getValor("select_tipo_archivo");
	
	$("#mensaje_error").hide();
	$("#paginacion").hide();
	$("#area_botones").hide();
	
	osm_setValor("LISTA_REGISTROS", "Cargando informaci\u00f3n ...");
	
	var fun = new Function("total", "respuestaContarRetiros(total, '"  +tipo_archivo + "')");
	
	osm_verLoader();
	jsonrpc._("salidaSistemaExterno.contarRetirosPorTipoArchivoSinDescargar")(fun, tipo_archivo);
		
}

function respuestaContarRetiros(total, tipo_archivo){
	
	PAGINACION.pagina_actual = 1;
	PAGINACION.tipo_archivo = tipo_archivo;
	
	PAGINACION.total_paginas = Math.ceil(total / MAX_REGISTROS_PINTADO);
	actualizarPagina(true);
	
	osm_ocultarLoader();
	
}

function actualizarPagina(crearEncabezado){
	
	$("#select_tipo_archivo").attr("disable",true);
	
	registros = jsonrpc._("salidaSistemaExterno.obtenerRetirosPorTipoArchivoSinDescargar")(PAGINACION.tipo_archivo, PAGINACION.pagina_actual);
	
	SALIDA_CORE_CORREVAL = registros;
	
	$("#mensaje_error").hide();
	$("#contenido_dinamico").empty();
	$("#area_botones").hide();
	if(crearEncabezado){
		$("#LISTA_REGISTROS").empty();
	}
	
	if(registros!=null){
		listaRegistros = registros.list;
		totalRegistros = listaRegistros.length;
		
		if(totalRegistros>0){
			//Se construye el encabezado de la lista
			if(crearEncabezado){
				construirHTML("LISTA_REGISTROS", "PLANTILLA_ENCABEZADO_TABLA");
			}
			//Se construye los registros de la lista
			for (var i= 0; i< totalRegistros; i++){
				var registro = listaRegistros[i];
				
				construirHTML("contenido_dinamico", "PLANTILLA_FILA_TABLA", 
				[ registro.id_retiro,
				  registro.nombre_beneficiario,
				  registro.nombre_banco_origen_correval,
				  registro.nombre_banco_destino_beneficiario,
				  registro.tipo_de_medio_fisico,
				  osm_formatoMoneda(registro.valor_retiro) ]);
				
				var comando_reg = $("#comando_agregar_" + registro.id_retiro);
				
				var comando_todos = $("#comando_agregar_null");
				
				//Por defecto
				var comando = comando_todos;
				
				//Se toma el ultimo comando si hay dos
				if(comando_reg.length && comando_todos.length){
						var orden_todos = $("#comando_orden_null").val();
						var orden_reg = $("#comando_orden_" + registro.id_retiro).val();
						comando = orden_todos > orden_reg ? comando_todos : comando_reg;
				}
				else if (comando_reg.length){
					comando = comando_reg;
				}
				
				if(comando.length && comando.val() != "false"){
					
				 	$("#retiro_"+registro.id_retiro)[0].checked = true;
				 
				}
			}
			
			
			
			//Se muestra el boton para generar archiv
			$("#area_botones").show();
			
			osm_setValor("texto_paginacion", "P\u00e1gina " + PAGINACION.pagina_actual  + " de " + PAGINACION.total_paginas);
			PAGINACION.cargando = false;
			$("#paginacion").show();
			
			
		}else{
			$("#mensaje_error").show();
		}
		
	}
	
	$("#select_tipo_archivo").attr("disable",false);
	
}
function irPagina(){
	osm_setValor("texto_paginacion", "Cargando ...");
	actualizarPagina();
	
}

function paginacion_inicio(id_){
	if (PAGINACION.cargando) {
		return;
	}
	if(PAGINACION.pagina_actual > 1){
		PAGINACION.pagina_actual = 1;
		irPagina(id_);
	}
}
function paginacion_anterior(){
	if (PAGINACION.cargando) {
		return;
	}
	if(PAGINACION.pagina_actual > 1){
		PAGINACION.pagina_actual--; 
		irPagina();
	}
}

function paginacion_siguiente(){
	if (PAGINACION.cargando) {
		return;
	}
	if(PAGINACION.pagina_actual < PAGINACION.total_paginas){
		PAGINACION.pagina_actual++;
		irPagina();
	}
}
function paginacion_final(){
	if (PAGINACION.cargando) {
		return;
	}
	if (PAGINACION.pagina_actual < PAGINACION.total_paginas) {
		PAGINACION.pagina_actual = PAGINACION.total_paginas;
		irPagina();
	}
}


function generarArchivo(){
	
	var seleccionados = $("#contenido_dinamico input:checkbox:checked").length;
	
	if (seleccionados > 0) {
	
		var tipo_archivo = osm_getValor("select_tipo_archivo");
		osm_setValor("tipo_archivo", tipo_archivo);
		osm_enviarFormulario("form_generar_archivo");
		
	}else{
		osm_alert("Debe seleccionar por lo menos un registro para poder generar el archivo");
	}
	
}


function toogleSeleccionarTodos(){
	
		
	$("#LISTA_REGISTROS input:checkbox").each(function(i){
		
		$(this).attr("checked", osm_getObjeto("check_todos").checked);
	});	
	
	crearComando(null, ORDEN_COMANDO++, osm_getObjeto("check_todos").checked);
	
}

function seleccionRegistro(id_registro, checked){
	crearComando(id_registro, ORDEN_COMANDO++, checked);
}

function crearComando(id_registro, orden, agregar){
	
	if(!$("#comando_"+id_registro).length){
		construirHTML("div_comandos", "PLANTILLA_COMANDO", [id_registro, orden, agregar]);	
	}
	else{
		osm_setValor("comando_orden_" +id_registro, orden);
		osm_setValor("comando_agregar_" +id_registro, agregar);
	}
}



function construirHTML(id_padre, id_plantilla, parametros, antes){
	
	var htmlplantilla = PLANTILLAS[id_plantilla];
	if(htmlplantilla==null){
		htmlplantilla = osm_getValor(id_plantilla);
		PLANTILLAS[id_plantilla] = osm_getValor(id_plantilla);
		$("#" + id_plantilla).empty();
	}
	
	if(parametros && parametros!=null){
		
		for(var j=0; j<parametros.length; j++){
			var parametro;
			parametro = parametros[j];
			htmlplantilla = osm_remplazar(htmlplantilla, '[ ' +(j+1) + ' ]', parametro);
		}
	}
	if(antes)
	{
		$("#" + id_padre).prepend(htmlplantilla);
	}else{
		$("#" + id_padre).append(htmlplantilla);
	}
}


var NUM_PAGINA = 0;
var total_registros = 0;
var cantidad_pagina = 0;

function listarRegistros(){
	
	$("#pag_info_carga").show();
    $("#pag_info_pags").hide();
	
	var tipo_archivo = osm_getValor("select_tipo_archivo");
	
	registros = jsonrpc._("salidaSistemaExterno.obtenerArchivoSalidaCoreDescargadosPorTipoArchivo")(tipo_archivo, NUM_PAGINA+1);
	
	actualizarPaginacion(tipo_archivo);
   	
	SALIDA_CORE_CORREVAL = registros;
	
	$("#LISTA_REGISTROS").empty();
	$("#mensaje_error").hide();
	
	
	if(registros!=null){
		$("#div_paginacion").show();
		listaRegistros = registros.list;
		totalRegistros = listaRegistros.length;
		
		if(totalRegistros>0){
			//Se construye el encabezado de la lista
			osm_construirHTML("LISTA_REGISTROS", "PLANTILLA_ENCABEZADO_TABLA");
			
			//Se construye los registros de la lista
			for (var i= 0; i< totalRegistros; i++){
				var registro = listaRegistros[i];
				
				var fecha = "";
				if(!osm_esVacio(registro.fechaCreacion)){
					fecha = osm_getFecha(registro.fechaCreacion.time)+" "+osm_getHora(registro.fechaCreacion.time);
				}
				
				osm_construirHTML("contenido_dinamico", "PLANTILLA_FILA_TABLA", 
				[ registro.id_archivo,
				  registro.tipoArchivo,
				  fecha,
				  registro.nombreGenerador ]);
				
			}
			
			
		}else{
			$("#mensaje_error").show();
			
		}
		
	}
	
}

function generarArchivo( id_archivo ){
	
	
	if (!osm_esVacio(id_archivo)) {
		
		var tipo_archivo = osm_getValor("select_tipo_archivo");
		osm_setValor("archivoSalidaCore.tipoArchivo", tipo_archivo);
		osm_setValor("archivoSalidaCore.id_archivo", id_archivo);
			
		osm_enviarFormulario("form_generar_archivo");
		
	}else{
		osm_alert("No se puede generar el archivo, intentelo nuevamente");
	}
	
}



function p_load(){

}


//PAGINACION

function actualizarPaginacion(tipo_archivo){
	
	$("#pag_info_carga").hide();
    $("#pag_info_pags").show();
	
	total_registros = jsonrpc._("salidaSistemaExterno.obtenerTotalArchivosDescargadosPorTipoArchivo")(tipo_archivo);
    cantidad_pagina = jsonrpc._("salidaSistemaExterno.totalRegistrosPorPagina")();
    
	//Info
    var desde = total_registros == 0 ? 0 : ((NUM_PAGINA) * cantidad_pagina) + 1;
    var hasta = desde + cantidad_pagina - 1;
    hasta = hasta > total_registros ? total_registros : hasta;
    osm_setValor("pag_total", total_registros);
    osm_setValor("pag_desde", desde);
    osm_setValor("pag_hasta", hasta);
    
    if (NUM_PAGINA < 1) {
        $("#pag_primero").hide();
        $("#pag_atras").hide();
    }
    else {
        $("#pag_primero").show();
        $("#pag_atras").show();
    }
    if (hasta == total_registros) {
        $("#pag_siguiente").hide();
        $("#pag_ultimo").hide();
    }
    else {
        $("#pag_siguiente").show();
        $("#pag_ultimo").show();
    }
    
}


function ir_primero(){
    NUM_PAGINA = 0;
    listarRegistros();
}

function ir_atras(){
    NUM_PAGINA = NUM_PAGINA - 1;
    listarRegistros();
}

function ir_siguiente(){
    NUM_PAGINA = NUM_PAGINA + 1;
    listarRegistros();
}

function ir_ultimo(){
    NUM_PAGINA = parseInt((total_registros - 1) / cantidad_pagina);
    listarRegistros();
}

osm_listen("load", window, p_load);


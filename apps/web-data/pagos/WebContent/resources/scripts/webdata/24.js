var IDS_REPORTES = ["agrupar_banco", "agrupar_registros", "agrupar_registros_simple_contar"];
var ESTRUCTURAS = ["RETIROS"];
var REPORTES = new Array();
var ID_BANCO = false;

var PAGINACION = new Array();
var MAX_REGISTROS_PINTADO = 10;

function iniciarAgruparGiros(){

    //Crear los reportes
    for (var i = 0; i < IDS_REPORTES.length; i++) {
        REPORTES[i] = jsonrpc._("reporteServicio.crearSubReporte")(IDS_REPORTES[i], null);
    }
    
    
    osm_setValor("cont_bancos", "Cargando Grupos de Bancos...");
    
    var params = "";
    jsonrpc._("reporteServicio.obtenerDatosReporte")(mostrarBancos, REPORTES[0].idReporte, params);
    
}

$(function(){   
	osm_setValor("cont_bancos", "Cargando Componentes..");
	window.setTimeout("iniciarAgruparGiros();", 300);
});

function mostrarBancos(resultados){
	
	osm_setValor("cont_bancos", "");
	
    var hayDatos = false;
    if (resultados != null) {
        var datos = resultados.datos.list;
        hayDatos = datos.length > 0;
        for (var i = 0; i < datos.length; i++) {
            var reg = datos[i].map;
            osm_construirHTML("cont_bancos", "PLANTILLA_FILA", [reg.id_banco, reg.banco]);
            
			PAGINACION[reg.id_banco] = new Object();
			PAGINACION[reg.id_banco].total_paginas = null;
			PAGINACION[reg.id_banco].pagina_actual = 1;
        }
    }
    
    if (!hayDatos) {
        $("#giros_no_datos").show();
        $("#giros_contenido").hide();
        $("#btn_guardar").hide();
    }
    
}

function mostrarOcultarCont(id_banco){
    AR = $("#contBanco_" + id_banco + ":visible");
    if (AR.length) {
        ocultarContBanco(id_banco);
    }
    else {
        mostrarContBanco(id_banco);
       
    }
}

function mostrarContBanco(id_banco){
	
	PAGINACION[id_banco].cargando = true;
	var inicio = (PAGINACION[id_banco].pagina_actual - 1 ) * MAX_REGISTROS_PINTADO;

    $("#enc_" + id_banco).addClass("loading_encabezado");
    params = "id_banco:" + id_banco + ",inicio:" + inicio +",registros:"+MAX_REGISTROS_PINTADO;
    var func = new Function("lista", "mostrarRegistros(lista, '" + id_banco + "')");
    jsonrpc._("reporteServicio.obtenerDatosReporte")(func, REPORTES[1].idReporte, params);
}

function ocultarContBanco(id_banco){
    $("#contBanco_" + id_banco).hide();
}

function mostrarRegistros(resultados, id_banco){

	
    
	var params = "id_banco:" + id_banco ;
	if(PAGINACION[id_banco].total_paginas == null){
		var rta_contar = jsonrpc._("reporteServicio.obtenerDatosReporte")(REPORTES[2].idReporte, params);
		if(rta_contar  != null){
			var paginas = Math.ceil(rta_contar.datos.list[0].map.total / MAX_REGISTROS_PINTADO)
			PAGINACION[id_banco].total_paginas = paginas;
		}
	}
	
    if (resultados != null ) {
    	
        var datos = resultados.datos.list;
		
		$("#registrosBanco_" + id_banco).empty();
		
        for (var i = 0; i < datos.length; i++) {
            var reg = datos[i].map;
            osm_construirHTML("registrosBanco_" + id_banco, "PLANTILLA_REGISTRO", 
			[reg.id_grupo_giro,
			i + 1, 
			reg.identificacion, 
			reg.valor, 
			reg.banco == null ? "" : reg.banco, 
			reg.cuenta, 
			osm_getFecha(reg.fecha_pago.time), 
			reg.estado, 
			reg.cuenta_pago, 
			reg.cuenta_pago_id, 
			reg.fecha_pago.time,
			id_banco]);
			
			var comando_reg = $("#comando_agregar__" + reg.id_grupo_giro);
			
			var comando_banco = $("#comando_agregar_" + id_banco+"_");
			
			//Por defecto
			var comando = comando_banco;
			
			//Se toma el ultimos comando si hay dos
			if(comando_reg.length && comando_banco.length){
					var orden_reg = $("#comando_orden__" + reg.id_grupo_giro).val();
					var orden_banco = $("#comando_orden_" + id_banco+"_").val();
					comando = orden_banco > orden_reg ? comando_banco : comando_reg;
			}
			else if (comando_reg.length){
				comando = comando_reg;
			}
			
			if(comando.length && comando.val() == "true"){
				
			 	$("#grupogiro_"+reg.id_grupo_giro)[0].checked = true;
			 
			}
            $("#fila_" + reg.id_grupo_giro).hover(function(){
                $(this).addClass("tabla_fila_over");
            }, function(){
                $(this).removeClass("tabla_fila_over");
            });
        }
		
        osm_setValor("texto_paginacion_"+id_banco, "P\u00e1gina " + PAGINACION[id_banco].pagina_actual + " de " + PAGINACION[id_banco].total_paginas);
        
    }
    else{
    	osm_setValor("texto_paginacion_"+id_banco, "No hay datos");
    }
	PAGINACION[id_banco].cargando = false;
	
    $("#enc_" + id_banco).removeClass("loading_encabezado");
    $("#contBanco_" + id_banco).show();
}

function irPagina(id_banco){
	osm_setValor("texto_paginacion_"+id_banco, "Cargando ...");
	mostrarContBanco(id_banco);
	
}

function paginacion_inicio(id_banco){
	if (PAGINACION[id_banco].cargando) {
		return;
	}
	if(PAGINACION[id_banco].pagina_actual > 1){
		PAGINACION[id_banco].pagina_actual = 1;
		irPagina(id_banco);
	}
}
function paginacion_anterior(id_banco ){
	if (PAGINACION[id_banco].cargando) {
		return;
	}
	if(PAGINACION[id_banco].pagina_actual > 1){
		PAGINACION[id_banco].pagina_actual--; 
		irPagina(id_banco);
	}
}

function paginacion_siguiente(id_banco ){
	if (PAGINACION[id_banco].cargando) {
		return;
	}
	if(PAGINACION[id_banco].pagina_actual < PAGINACION[id_banco].total_paginas){
		PAGINACION[id_banco].pagina_actual++;
		irPagina(id_banco);
	}
}
function paginacion_final(id_banco ){
	if (PAGINACION[id_banco].cargando) {
		return;
	}
	if (PAGINACION[id_banco].pagina_actual < PAGINACION[id_banco].total_paginas) {
		PAGINACION[id_banco].pagina_actual = PAGINACION[id_banco].total_paginas;
		irPagina(id_banco);
	}
}

var ORDEN_COMANDO = 1;
function clickFila(id_banco){
    var chk = $("#grupogiro_" + id_banco)[0];
    chk.checked = !chk.checked;
	crearComando(id_banco, '', ORDEN_COMANDO++, chk.checked);
}

function seleccionTodas(id_banco, checked){
    var lista = $("#registrosBanco_" + id_banco).find("input[type='checkbox']");
    for (var i = 0; i < lista.length; i++) {
        lista[i].checked = checked;
    }
	crearComando(id_banco, '', ORDEN_COMANDO++, checked);
}

function seleccionRegistro(codigo_registro, checked){
	
	crearComando('', codigo_registro, ORDEN_COMANDO++, checked);
}

function crearComando(id_banco, codigo_registro, orden, agregar){
	
	if(!$("#comando_"+id_banco + "_" + codigo_registro).length){
		osm_construirHTML("div_comandos", "PLANTILLA_COMANDO", [id_banco, codigo_registro, orden, agregar]);	
	}
	else{
		osm_setValor("comando_orden_" +id_banco + "_" + codigo_registro, orden);
		osm_setValor("comando_agregar_" +id_banco + "_" + codigo_registro, agregar);
	}
}

var ACTUALIZAR_ESTADO = false;

function enviar(){
	
	var options = {
		success : fin
	};
	
	var id_check = $(".check_registro:checked").attr("id");
	ID_BANCO = $("#" + id_check + "_banco").val();
	
	var nombre = osm_getValor("linkbanco_" + ID_BANCO);
		
    if (osm_esVacio(nombre)) {
        alert("Error al crear nombre de archivo, no se puede establecer el nombre del banco");
        return false;
    }
	
	osm_setValor('nombreArchivo', nombre);
	
	osm_block_window();
	
	
	$('#form_giros').ajaxSubmit(options);
	ACTUALIZAR_ESTADO = true;
	setTimeout("obtenerEstado();", 100);
	
}

var ACTUALIZAR_ESTADO = true;

function obtenerEstado(){
	if(ACTUALIZAR_ESTADO){
		
		$("#giros_contenido").hide();
		$("#area_cargando").show();
		
		osm_unblock_window();
		
		var mensaje = jsonrpc._("grupoGiroServicio.obtenerMensajeCreacionArchivo")();
		if(mensaje!= null && (!mensaje.exitoso) && mensaje.estado == "F"){
			alert(mensaje.mensaje);
		}

		if(mensaje !=null && (mensaje.porcentaje*2) > 10){
			$("#progreso").css("width", (mensaje.porcentaje*2) + "px");
		}
		
		if(mensaje != null && (mensaje.exitoso) && mensaje.estado != "V"){
			osm_go("agrupar_giros/24.1.do");			
		}
		
		if(mensaje == null || mensaje.estado != "F")
		{
			setTimeout("obtenerEstado()", 100);
		}
	}
}

function fin(){
	osm_unblock_window();
}


function validarIguales(valores){
    	
    var iguales = false;
    var valor = false;
    for (var i = 0; i < valores.length; i++) {
        if (!valor) {
            valor = valores[i].value;
            iguales = true;
        }
        else if (valor != valores[i].value) {
			
			return false;
        }
    }
	return iguales;
}


$(inicioBuscar)
var NUM_PAGINA = 1;
var TOTAL_REGISTROS = 0;
var CONSULTA = new Object();
var CONSULTANDO = false;
var FILTRO_CLIENTE = false;
var ID_ADMINISTRADOR = false;
function inicioBuscar(){
	$("#div_no_resultados").hide();
	
	var filtro_cliente = osm_getValor("filtro_cliente");
	if(!osm_esVacio(filtro_cliente)){
		FILTRO_CLIENTE = true;
	}
	var id_administrador = osm_getValor("id_administrador");
	if(!osm_esVacio(id_administrador)){
		ID_ADMINISTRADOR = true;
	}
	
	var existe_busqueda = jsonrpc._("cargaServicio.inicioBusqueda")();
	
	if (existe_busqueda == true){
		jsonrpc._("cargaServicio.buscarCargasInicio")(mostrarResultados);
	}
	
}

function verDetalleHistoria(id_carga){
	if(FILTRO_CLIENTE && !ID_ADMINISTRADOR){
		jsonrpc._("cargaServicio.obtenerHistorialCargaInstancia")(mostrarHistCargaIns, id_carga);
	}
	else{
		jsonrpc._("cargaServicio.obtenerHistorialCarga")(mostrarHistCargaCliente, id_carga);
	}
}

function verDetalleCarga(id_carga){
	
	var id_formato_salida = jsonrpc._("cargaServicio.obtenerFormatoSalidaCarga")(id_carga);
	
	if(id_formato_salida == null){
		
		osm_alert("Lo sentimos, no se encuentra un formato para consulta.");
		return;
	}
	
	osm_setValor("id_carga_detalle", id_carga);
	osm_setValor("id_formato_salida_detalle", id_formato_salida);
	
	osm_enviarFormulario("form_detallecarga");
}

function mostrarHistCargaIns(listado){
	$("tr[name='reg_hist_instancia']").remove();
	if(listado != null){
		listado = listado.list;
		for ( var i = 0; i < listado.length; i++) {
			var reg = listado[i];
			
			var fechaLlegada = reg.fecha_llegada;
			fechaLlegada = fechaLlegada == null || fechaLlegada.time == null ? "" : osm_getFecha(fechaLlegada.time) + " " + osm_getHora(fechaLlegada.time);
			
			var fechaSalida = reg.fecha_salida;
			fechaSalida = fechaSalida == null || fechaSalida.time == null ? "" : osm_getFecha(fechaSalida.time) + " " + osm_getHora(fechaSalida.time);
			
			var persona = osm_esVacio(reg.nombre_persona) ? "" : reg.nombre_persona + " ";
			persona += osm_esVacio(reg.apellido_persona) ? "" : reg.apellido_persona;
			
			osm_construirHTML("cont_hist_instancias", "PLANTILLA_HIST_INSTANCIAS",
					[reg.nombre_instancia,
					 persona,
					 fechaLlegada,
					 fechaSalida,
					 "reg_hist_instancia"
					 ]
			);
					
		}
		$("#div_hist_instancias").show();
		osm_setVisible("vn_historialcarga", true, true);
		osm_ocultarSelects("bodyContent");
	}
}


function mostrarHistCargaCliente(listado){
	$("tr[name='reg_hist_cliente']").remove();
	if(listado != null){
		listado = listado.list;
		for ( var i = 0; i < listado.length; i++) {
			
			var reg = listado[i];
			
			var fecha = reg.fecha;
			fecha = fecha == null || fecha.time == null ? "" : osm_getFecha(fecha.time) + " " + osm_getHora(fecha.time);
			
			var persona = osm_esVacio(reg.nombres_persona) ? "" : reg.nombres_persona + " ";
			persona += osm_esVacio(reg.apellidos_persona) ? "" : reg.apellidos_persona;

			osm_construirHTML("cont_hist_cliente", "PLANTILLA_HIST_CLIENTES",
					[estado(reg.estado, reg.nombre_estado),
					 persona,
					 fecha,
					 "reg_hist_cliente",
					 (reg.peso!=null)?("<b>Peso: </b>" + reg.peso):""
					 ]
			);
					
		}
		$("#div_hist_cliente").show();
		osm_setVisible("vn_historialcarga", true, true);
		osm_ocultarSelects("bodyContent");
	}
}

function cerrarVentanaHistorial(){
	osm_mostrarSelects("bodyContent");
	osm_setVisible("vn_historialcarga", false, true);
}

function consultar(){
	
	if(CONSULTANDO){
		return;
	}
	
	CONSULTANDO = true;
	
	$("#div_resultados").fadeTo("fast",0.2);
	
	jsonrpc._("cargaServicio.buscarCargas")(mostrarResultados, CONSULTA.estados, CONSULTA.fecha_final, CONSULTA.fecha_inicial, CONSULTA.id_formato, CONSULTA.id_negocio,
			CONSULTA.tipo_carga, CONSULTA.id_cliente, CONSULTA.ordenarPor, NUM_PAGINA, CONSULTA.fecha_final_lib, CONSULTA.fecha_inicial_lib, CONSULTA.id_carga);
	
	$("#pag_info_carga").show();
	$("#pag_info_pags").hide();
	$("#div_no_resultados").hide();
}

function buscarCargas(){
	var opts = $("[name='estados']");
	var estados = "";
	var todosEstados = "";
	var todos_check = true;
	for ( var i = 0; i < opts.length; i++) {
		todos_check = todos_check && opts[i].checked; 
		if(opts[i].checked){
			estados += opts[i].value;
		}
		todosEstados += opts[i].value;
	}
	osm_getObjeto("check_todos").checked = todos_check;
	
	CONSULTA.estados = estados;
	if(osm_esVacio(estados)){
		CONSULTA.estados = todosEstados;
	}
	CONSULTA.fecha_final = osm_getValor("fecha_final");
	CONSULTA.fecha_inicial = osm_getValor("fecha_inicial");
	
	CONSULTA.id_formato = osm_getValor("buscadorCargas.id_formato");
	CONSULTA.id_negocio = osm_getValor("select_negocio");
	CONSULTA.tipo_carga = osm_getValor("tipo_carga");
	CONSULTA.id_cliente = osm_getValor("id_usuario");
	CONSULTA.ordenarPor = osm_getValor("ordenar");
	
	CONSULTA.fecha_final_lib = osm_getValor("fecha_final_lib");
	CONSULTA.fecha_inicial_lib = osm_getValor("fecha_inicial_lib");
	
	CONSULTA.fecha_inicial = osm_esVacio(CONSULTA.fecha_inicial) ? null : CONSULTA.fecha_inicial;
	CONSULTA.fecha_final = osm_esVacio(CONSULTA.fecha_final) ? null : CONSULTA.fecha_final;
	
	CONSULTA.fecha_inicial_lib = osm_esVacio(CONSULTA.fecha_inicial_lib) ? null : CONSULTA.fecha_inicial_lib;
	CONSULTA.fecha_final_lib = osm_esVacio(CONSULTA.fecha_final_lib) ? null : CONSULTA.fecha_final_lib;
	
	CONSULTA.id_carga = osm_getValor("id_carga");
	
	NUM_PAGINA = 1;
	consultar();
}

$(function(){ buscarCargas();});

function mostrarResultados(rtaCargas, e){

	CONSULTANDO = false;
	$("[name='fila_carga']").remove();
	
	
	if(rtaCargas != null && rtaCargas.map.total){
		if (rtaCargas.map.pagina != null){
			NUM_PAGINA = rtaCargas.map.pagina;
			cargarParametros(rtaCargas.map.parametros);
		}
		actualizarPaginacion(rtaCargas.map);
		var listadoCargas = rtaCargas.map.cargas.list;
		for ( var i = 0; i < listadoCargas.length; i++) {
			var reg = listadoCargas[i];
			var fechaSub = reg.fecha_subida;
			fechaSub = fechaSub == null || fechaSub.time == null ? "" : osm_getFecha(fechaSub.time) + " " + osm_getHora(fechaSub.time);
			
			var fechaLib = reg.fecha_liberacion;
			fechaLib = fechaLib == null || fechaLib.time == null ? "" : osm_getFecha(fechaLib.time) + " " + osm_getHora(fechaLib.time);
			
			var persona = "";
			
			if(FILTRO_CLIENTE){
				persona = osm_esVacio(reg.nombre_usuario) ? "" : reg.nombre_usuario;
				persona += osm_esVacio(reg.apellido_usuario) ? "" : " " + reg.apellido_usuario ;
			}else{
				persona = osm_esVacio(reg.nombre_persona) ? "" : reg.nombre_persona;
				persona += " " + (osm_esVacio(reg.apellido_persona) ? "" : reg.apellido_persona);
			}
			
			var valor = (reg.valor_total_bigdecimal != null) ? osm_formatoMoneda(reg.valor_total_bigdecimal) : "$ --";
			
			osm_construirHTML("lista_cargas", "PLANTILLA_FILA_CARGA",
					[reg.id_carga,
					 reg.nombre_usuario == reg.nombre_persona? reg.nombre_usuario+" "+reg.apellido_persona: reg.nombre_usuario,
					 fechaSub,
					 estado(reg.estado, reg.nombre_estado),
					 tipoCarga(reg.id_tipocarga),
					 persona,
					 "fila_carga",
					 reg.nombre_formato,
					 reg.numero_registros_bigdecimal,
					 valor,
					 reg.motivo_estado,
					 reg.estado,
					 fechaLib,
					 reg.numero_proceso,
					 reg.radicado
					 ]
			);
		}
		$("#div_resultados").fadeTo("slow",1);
	}
	else{
		$("#div_no_resultados").show();
		$("#div_resultados").hide();
	}
	$("#pag_info_carga").hide();
	$("#pag_info_pags").show();
}

function cargarParametros(parametros){
	if (parametros != null){
		osm_setValor("fecha_final", parametros.fecha_final_formato);
		osm_setValor("fecha_inicial", parametros.fecha_inicial_formato);
		osm_setValor("buscadorCargas.id_formato", parametros.id_formato);
		osm_setValor("select_negocio", parametros.id_negocio);
		osm_setValor("tipo_carga", parametros.id_tipocarga);
		osm_setValor("id_usuario", parametros.id_usuario);
		osm_setValor("ordenar", parametros.ordenarPor);
		
		osm_setValor("fecha_final_lib", parametros.fecha_final_lib_formato);
		osm_setValor("fecha_inicial_lib", parametros.fecha_inicial_lib_formato);
		
		osm_setValor("id_carga", parametros.id_carga);
	}
}

function actualizarPaginacion(resultadoConsulta){
	TOTAL_REGISTROS = resultadoConsulta.total;
    var cantidad_pagina = parseInt(osm_getValor("cantidad_pagina"));
    //Info
    var desde = TOTAL_REGISTROS == 0 ? 0 : ((NUM_PAGINA - 1) * cantidad_pagina) + 1;
    var hasta = desde + cantidad_pagina - 1;
    hasta = hasta > TOTAL_REGISTROS ? TOTAL_REGISTROS : hasta;
    osm_setValor("pag_total", TOTAL_REGISTROS);
    osm_setValor("pag_desde", desde);
    osm_setValor("pag_hasta", hasta);
    
    if (NUM_PAGINA <= 1) {
        $("#pag_primero").hide();
        $("#pag_atras").hide();
    }
    else {
        $("#pag_primero").show();
        $("#pag_atras").show();
    }
    if (hasta == TOTAL_REGISTROS) {
        $("#pag_siguiente").hide();
        $("#pag_ultimo").hide();
    }
    else {
        $("#pag_siguiente").show();
        $("#pag_ultimo").show();
    }
    
}

//Retona el texto del tipo de carga
function tipoCarga(id){
	if(id == "1"){
		return "Individual"
	}
	if(id == "2"){
		return "Masiva"
	}
	return "";
}
//Retorna el texto del estado
function estado(id, nombre_estado){
	
	if( !osm_esVacio(nombre_estado)){
		return nombre_estado;
	}
	
	return ESTADOS_CARGA[id];
}


function selTodosEstados(check){
	var opts = $("[name='estados']");
	for ( var i = 0; i < opts.length; i++) {
		opts[i].checked = check.checked;
	}	
}

function ir_primero(){
    NUM_PAGINA = 1;
    consultar();
}

function ir_atras(){
    NUM_PAGINA = NUM_PAGINA - 1;
    consultar();
}

function ir_siguiente(){
    NUM_PAGINA = NUM_PAGINA + 1;
    consultar();
}

function ir_ultimo(){
    NUM_PAGINA = parseInt((TOTAL_REGISTROS - 1) / parseInt(osm_getValor("cantidad_pagina"))) + 1;
    consultar();
}

function rc_ajusteVentana() {
	var pos = osm_getWindowSize();
	$("#div_hist_instancias").css("height", (pos[1] - 300) + "px").css("overflow", "auto");
}

osm_listen("resize", window, rc_ajusteVentana);
osm_listen("load", window, rc_ajusteVentana);
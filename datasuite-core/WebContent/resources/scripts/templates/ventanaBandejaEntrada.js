var NUM_PAGINA = 1;
var TOTAL_REGISTROS = 0;
INTERVALO_RECARGA = 60000;
var CONSULTA = new Object();
var CONSULTANDO = false;
var FILTROS_BANDEJA = new Array();

$(document).ready(inicio);

function recargarPagina(){
	
	//jsonrpc._("procesoAdminJsonServicio.obtenerCargasFiltradas")(listarCargasOrdenadasFiltradas, CONSULTA.instancia_carga, CONSULTA.cliente, CONSULTA.fecha_carga, CONSULTA.fecha_liberacion, NUM_PAGINA, CONSULTA.filtros);
	consultar();
	
}

function inicio(){
	//jsonrpc._("procesoAdminJsonServicio.obtenerCargasFiltradas")(listarCargasOrdenadasFiltradas, null, null, null, null, NUM_PAGINA, null);
	consultar();
	
	//setInterval("recargarPagina()", INTERVALO_RECARGA);
}

function listarCargasOrdenadasFiltradas(objs){
	
	INSTANCIAS_ADMINISTRATIVO = null;
	
	if(objs!=null && objs.map && objs.map.total && objs.map.cargas.list){
		//Se cargan las acciones por instancia
		INSTANCIAS_ADMINISTRATIVO = objs.map.instanciaPorAdministrativo.list;
		
		TOTAL_REGISTROS = objs.map.total;
		
		//Se cargan las cargas
		var cargas = objs.map.cargas.list;
		mostrarCargas(cargas, INSTANCIAS_ADMINISTRATIVO);
		
		//Se cargan los clientes
		//var clientes = objs.map.cliente; 
		//crearSelectorDeClientes(clientes);
		
		
		
	} else {
		CONSULTANDO = false;
		$("#mensaje_sin_cargas").show();
		$("#NOTA_CARGA_DETALLE").hide();
		osm_unblock_window();
	}
	
	
}

function generar_tabla(){
	var columnas = ["uno", "dos", "tres"];
}

function buscar_cargas() {
	validarFiltrosBandejaEntrada();
	CONSULTA.instancia_carga = osm_getValor("select_filtro_instancia_carga");
	//CONSULTA.cliente = osm_getValor("select_filtro_cliente");
	CONSULTA.cliente = null;
	CONSULTA.fecha_carga = osm_getValor("fecha_carga");
	CONSULTA.fecha_liberacion = osm_getValor("fecha_liberacion");
	
	CONSULTA.fecha_carga = osm_esVacio(CONSULTA.fecha_carga) ? null : CONSULTA.fecha_carga;
	CONSULTA.fecha_liberacion = osm_esVacio(CONSULTA.fecha_liberacion) ? null : CONSULTA.fecha_liberacion;
	
	CONSULTA.filtros = FILTROS_BANDEJA;
	
	NUM_PAGINA = 1;
	consultar();
}

function validarFiltrosBandejaEntrada() {
	FILTROS_BANDEJA = new Array();
	$(".filtroBandeja").each(function (){
		var obj = {};
		var id_formato_campo = $( this ).val();
		var filtro = osm_getValor("filtro_" + id_formato_campo);
		if (filtro != null && filtro != ""){
			obj.id_formato_campo =id_formato_campo;
			obj.filtro = filtro;
			FILTROS_BANDEJA[FILTROS_BANDEJA.length] = obj;
		}
	});
}

function consultar(){
	
	if(CONSULTANDO){
		return;
	}
	
	CONSULTANDO = true;
	jsonrpc._("procesoAdminJsonServicio.obtenerCargasFiltradas")(listarCargasOrdenadasFiltradas, CONSULTA.instancia_carga, CONSULTA.cliente, CONSULTA.fecha_carga, CONSULTA.fecha_liberacion, NUM_PAGINA, CONSULTA.filtros);
	
	$("#div_resultados").hide();
	$("#pag_info_carga").show();
	$("#pag_info_pags").hide();
	$("#div_no_resultados").hide();
	$("#NOTA_CARGA_DETALLE").show();
	$("#mensaje_sin_cargas").hide();
	osm_block_window();
}

function mostrarCargas(lista, listaInstanciasAdministrativo){
	CONSULTANDO = false;
	var totalCargas= 0;
	
	$("#mensaje_sin_cargas").hide();
	
	
	if (lista != null && lista.length > 0) {
		
		$("#div_resultados").hide();
		$("#NOTA_CARGA_DETALLE").show();
		
		totalCargas = lista.length;
		actualizarPaginacion();
		//Construimos la informacion de la pagina
		$("[name='fila_carga']").remove();
		
		var formato_salida = jsonrpc._("formatoBaseServicio.obtenerFormatoSalidaPorProceso")();
		
		var formatoCampos = jsonrpc._("formatoBaseServicio.obtenerFormatoCampoBandejaEntrada")(formato_salida);
		for (var i = 0; i < lista.length; i++) {
			var carga = lista[i];
			
			try {
				mostrarCargaBandejaEntrada(carga, formato_salida, formatoCampos);
			} catch (e) {
				console.error(e);
				alert('Problema para mostrar la informacion. Intentelo nuevamente');
			}

		}
		
		$("#div_resultados").fadeTo("slow",1);
		$("#table_resultados").scrollTop(0);
		$("#pag_info_pags").show();
	}else{
		$("#mensaje_sin_cargas").show();
	}	
	$("#NOTA_CARGA_DETALLE").hide();
	osm_unblock_window();
}

function mostrarCargaBandejaEntrada(carga, id_formato, formatoCampos){
	var datos = [carga.id_carga,
			 	carga.fecha_subida_string,
			 	carga.fecha_liberacion_string,
			 	carga.nombre_instancia,
			 	"fila_carga",
			 	carga.id_instancia];
	
	if (formatoCampos) {
		
		var campos = formatoCampos.list;
		
		if (campos && campos.length > 0) {
			var datos_carga = jsonrpc._("cargaServicio.obtenerDatosBandejaEntrada")(id_formato, carga.id_carga).map;
			for (var i = 0; i < campos.length; i++) {
				datos.push(datos_carga["FC" + campos[i].id_formato_campo]);
			}	
		}
	}
			
	osm_construirHTML("lista_cargas", "PLANTILLA_FILA_CARGA",
			datos
	);
		
	$("#pag_info_carga").hide();
}

function verDetalleCarga(id_carga, id_instancia){
	
	osm_setValor("id_carga", id_carga);
	osm_setValor("id_instancia", id_instancia);
	osm_enviarFormulario("form_siguiente");
	
}

function verDetalleHistoria(id_carga){
	jsonrpc._("cargaServicio.obtenerHistorialCargaInstancia")(mostrarHistCargaIns, id_carga);
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

function cerrarVentanaHistorial(){
	osm_mostrarSelects("bodyContent");
	osm_setVisible("vn_historialcarga", false, true);
}

// --- Paginacion ----

function actualizarPaginacion(){
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

// ------------------

function rc_ajusteVentana() {
	var pos = osm_getWindowSize();
	$("#div_hist_instancias").css("height", (pos[1] - 300) + "px").css("overflow", "auto");
}

osm_listen("resize", window, rc_ajusteVentana);
osm_listen("load", window, rc_ajusteVentana);

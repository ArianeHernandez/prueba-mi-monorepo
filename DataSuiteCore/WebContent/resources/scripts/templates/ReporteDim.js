$(initReportes);

// -----------
var ID_REPORTE;
var NUM_PAGINA = 0;
var total_registros = 0;
var cantidad_pagina = 0
var REPORTES = new Array();
var PADRES = new Array();
var RESULTADOS = new Array();
var ACTUALIZABLES = new Array();
var PENDIENTES = new Array();
var INTERVALO_ACT = 90000;
var callBackClickFila;
var callBackActualizar;
// -----------

function initReportes(){

    ID_REPORTE = osm_getValor("idReporte");
    
    $("#pag_info_carga").show();
    $("#pag_info_pags").hide();
    
    consultar();
    
    $("#pag_info_carga").hide();
    $("#pag_info_pags").show();
    
    $("#div_reporte").fadeIn("slow");
    
    try {
        INTERVALO_ACT = parseInt(osm_getValor("intervalo_act"));
    } 
    catch (e) {
    }
    if(INTERVALO_ACT){
    	setTimeout("actualizar()", INTERVALO_ACT);
	}
}

function actualizar(){

    try {
    
        $("#pag_info_carga").show();
        $("#pag_info_pags").hide();
        
        PENDIENTES = ACTUALIZABLES;
        ACTUALIZABLES = new Array();
        
        
        if (PENDIENTES.length == 0) {
            osm_go("inicio/0.pub");
        }
        consultar();
        
        if (PENDIENTES.length > 1) {
            actualizarSiguiente();
        }
        else {
            finActualizar();
        }
        
    } 
    catch (e) {
        alert("Ha ocurrido un error en la actualizaci\u00f3n, se desactiva la actualizaci\u00f3n autom\u00e1tica." + e);
    }
}

function finActualizar(){
    $("#pag_info_carga").hide();
    $("#pag_info_pags").show();
    
    setTimeout("actualizar()", INTERVALO_ACT);
	
	try{
		if(callBackActualizar){
			callBackActualizar();
		}
	}
	catch(e){
		alert(e);		
	}
}

function actualizarSiguiente(){

    var idReporte = PENDIENTES.shift();
    
    if (!idReporte) {
        finActualizar();
        return;
    }
    
    if (actualizarReporte(idReporte, "actualizarSiguiente();")) {
        return;
    }
    
    actualizarSiguiente();
    
}

function actualizarReporte(idReporte, callback){

    if (PADRES[idReporte]) {
        var reportePadre = PADRES[idReporte].idReportePadre;
        var params_sub = obtenerParametros(reportePadre, PADRES[idReporte].idRegistro);
        if (params_sub != null) {
            jsonrpc._("reporteServicio.obtenerDatosReporte")(new Function("lista", "mostrarValoresSubReporte(lista, '" + idReporte + "'); " + callback), idReporte, params_sub);
            
            return true;
        }
    }
    return false;
}

function consultar(){

    try {
    
        reporte = jsonrpc._("reporteServicio.obtenerReporteDinamico")(ID_REPORTE);
        
        crearEncSubReporte(reporte);
        
        //TODO, debe ser asincrono
        var resultadoConsulta = jsonrpc._("reporteServicio.obtenerDatosReporte")(ID_REPORTE, NUM_PAGINA + 1);
        
        if (resultadoConsulta != null && resultadoConsulta) {
        
            mostrarValoresSubReporte(resultadoConsulta, ID_REPORTE);
            
            actualizarPaginacion(resultadoConsulta);
        }
        
        definirValoresNavegacion();
        
    } 
    catch (e) {
    }
    
}

function definirValoresNavegacion(){
	
    var totalNavegaciones = osm_getValor("totalNavegaciones");
	
    for (var i = 1; i <= totalNavegaciones; i++) {
        listaVars = $("input[id^='nav_para_" + (i) + "']");
        for (var j = 0; j < listaVars.length; j++) {
            if (listaVars[j].value.startsWith("#") &&
            listaVars[j].value.endsWith("#")) {
                var valor = osm_remplazar(listaVars[j].value, "#", "");
                valor = osm_getValor("param_" + valor);
                listaVars[j].value = valor;
            }
        }
    }
    
}

function clicFila(){

    var idReporte = $(this).attr("idReporte");
    var idRegistro = $(this).attr("idRegistro");
    
	try{
		if(callBackClickFila){
			callBackClickFila(this);
		}
	}catch(e){
		alert(e);
	}
	
    mostrarReporte(idReporte, idRegistro);
    
}

function mostrarReporte(idReporte, idRegistro){

    var id = "fila_" + idReporte + "_" + idRegistro;
    var obj = $("#" + id);
    
    if (!REPORTES[idReporte].accionfila) {
        return;
    }
    
    var idRegistro = obj.attr("idRegistro");
    var subreporte = REPORTES[idReporte].accionfila.subreporte;
    
    var consultado = obj.attr("consultado");
    
    if (consultado) {
    	
    	if($("#cont" + id).is(":visible")){
    		$("#cont" + id).fadeOut(100);
    	}else{
    		$("#cont" + id).fadeIn(100);
    	}
    	
        var idReporteHijo = obj.attr("idHijo");
        actualizarReporte(idReporteHijo);
        return;
    }
    
   
    
    if (!osm_esVacio(subreporte)) {
        var enviar = false;
        var params_sub = obtenerParametros(idReporte, idRegistro);
        
        if (!osm_esVacio(params_sub)) {
        
            jsonrpc._("reporteServicio.crearSubReporte")(new Function("reporte", "pintarEncSubReporte(reporte, '" + id + "', '" + idReporte + "','" + idRegistro + "')"), subreporte, params_sub);
            
            obj.attr("consultado", true);
        }
        return;
    }
    var accion = REPORTES[idReporte].accionfila;
    
    if (!osm_esVacio(accion.destino)) {
    
        var params = accion.parametros.list;
        var enviar = false;
        var resultado = RESULTADOS[idReporte][idRegistro];
        var frm = $("#form_fila");
        
        for (var i = 0; i < params.length; i++) {
            var parametro = params[i].valor;
            if (params[i].valor.startsWith("#") && params[i].valor.endsWith("#")) {
                parametro = resultado.map[osm_remplazar(params[i].valor, "#", "")];
                if (parametro == undefined) {
                    parametro = osm_getValor("param_" + params[i].nombre);
                }
                enviar = true;
            }
            
            var input_param = $("#hidden_template").clone();
            input_param.attr("id", params[i].nombre);
            input_param.attr("name", params[i].nombre);
            input_param.attr("value", parametro);
            input_param.appendTo(frm);
            
        }
        
        if (enviar) {
            accion = CONTEXTPATH + "/" + accion.destino;
            frm.attr("action", accion);
            $("#form_fila").submit();
        }
    }
    
    if (!osm_esVacio(accion.javascript)) {
        
        var params = accion.parametros.list;
        var enviar = false;
        var resultado = RESULTADOS[idReporte][idRegistro];
        
        var oper_param = new Object();
        
        for (var i = 0; i < params.length; i++) {
            var parametro = params[i].valor;
            if (params[i].valor.startsWith("#") && params[i].valor.endsWith("#")) {
                parametro = resultado.map[osm_remplazar(params[i].valor, "#", "")];
                if (parametro == undefined) {
                    parametro = osm_getValor("param_" + params[i].nombre);
                }
            }
            var tmpp = parametro;
            eval("oper_param." + params[i].nombre + " = tmpp;");
        }
        
    	var f = new Function("p", accion.javascript + "(p);");
    	f(oper_param);
    }
}

function obtenerParametros(idReporte, idRegistro){

    var params_sub = "";
    
    if (REPORTES[idReporte].accionfila == null) {
        return;
    }
    
    var params = REPORTES[idReporte].accionfila.parametros.list;
    var resultado = RESULTADOS[idReporte][idRegistro];
    
    if (!resultado) {
        return null;
    }
    
    for (var i = 0; i < params.length; i++) {
        var parametro = params[i].valor;
        if (params[i].valor.startsWith("#") && params[i].valor.endsWith("#")) {
            parametro = resultado.map[osm_remplazar(params[i].valor, "#", "")];
            if (parametro == undefined) {
                parametro = osm_getValor("param_" + params[i].nombre);
            }
            enviar = true;
        }
        params_sub = params_sub + params[i].nombre + ":" + parametro + ",";
    }
    return params_sub;
}

// se pinta el encabezado
function pintarEncSubReporte(reporte, id_fila, idReportePadre, idRegistro){
	
    var subreporte = $("#fila_template").clone();
    var id = "contfila_" + idReportePadre + "_" + idRegistro;
    var fila = $("#" + id_fila);
    var nivel = 2;
    if(PADRES[idReportePadre]){
		nivel = PADRES[idReportePadre].nivel + 1;
	}
    subreporte.attr("id", id);
    subreporte.addClass("contfila");
    subreporte.addClass("contfila" + (nivel -1))
    subreporte.insertAfter(fila);
    subreporte.hide();
    
    var totalCamposPadre = REPORTES[idReportePadre].resultados.list.length;
    
    PADRES[reporte.idReporte] = new Object();
    PADRES[reporte.idReporte].idReportePadre = idReportePadre;
    PADRES[reporte.idReporte].idRegistro = idRegistro;
	
    PADRES[reporte.idReporte].nivel = nivel;
    fila.attr("idHijo", reporte.idReporte);
	
    osm_construirHTML(id, "subreporte_template", [reporte.idReporte, totalCamposPadre, nivel]);
    
    crearEncSubReporte(reporte);
    
    
    jsonrpc._("reporteServicio.obtenerDatosReporte")(new Function("lista", "mostrarValoresSubReporte(lista , '" + reporte.idReporte + "', '" + id + "')"), reporte.idReporte);
    
}

// Se agrega el contenido encabezado del reporte
function crearEncSubReporte(reporte){

    if (reporte == null) {
        alert("Error cargando el reporte.");
        return;
    }
    
    var id = reporte.idReporte;
    
    REPORTES[id] = reporte;
    
    var resultados = reporte.resultados.list;
    resultados.sort(sortResultado);
    
    for (var i = 0; i < resultados.length; i++) {
    
        var id_enc = "enc_" + reporte.idReporte + "_" + i;
        
        if ($("#" + id_enc).length == 0) {
        
            var valor = $("#registro_template").clone();
            valor.attr("id", id_enc);
            valor.attr("name", id_enc);
            
            valor.appendTo($("#titulo_" + id));
            
            valor.addClass("enc_" + i);
            
            var valor_campo = "";
            valor_campo = resultados[i].titulo;
            valor.html(valor_campo);
            var oculto = resultados[i].oculto;
            if (oculto == "S") {
                valor.hide();
            }
			else{
				valor.show();
			}
        }
    }
}

// Se pinta cada fila y sus valores
function mostrarValoresSubReporte(resultadoConsulta, idReporte, id_contenido){

    if (resultadoConsulta == null) {
        return;
    }
    
    ACTUALIZABLES.push(idReporte);
    
    var nivel = 1;
    if(PADRES[idReporte]){
		nivel = PADRES[idReporte].nivel
	}
    
    var campos = REPORTES[idReporte].resultados.list;
    var lista = resultadoConsulta.datos.list;
    RESULTADOS[idReporte] = lista;
    var i = 0;
    for (i; i < lista.length; i++) {
    
        var id_fila = "fila_" + idReporte + "_" + i;
        
        var fila = $("#" + id_fila);
        
        if (fila.length == 0) {
            fila = $("#fila_template").clone();
            fila.appendTo("#contenido_" + idReporte);
            fila.attr("id", id_fila);
            fila.attr("idReporte", idReporte);
            fila.attr("idRegistro", i);
            fila.attr("name", "fila_" + idReporte);
            fila.addClass("fila_"+nivel);
            fila.click(clicFila);
        }
        else {
            $("[name='valor_" + id_fila + "']").remove();
        }
        
        for (var j = 0; j < campos.length; j++) {
            var valor = $("#registro_template").clone();
            
            
            valor.appendTo(fila);
            valor.attr("name", "valor_" + id_fila)
            var valor_campo = "";
            valor_campo = lista[i].map[campos[j].nombre];
            
            var tipo = campos[j].tipo;

            valor.addClass("col_" + j);
            
            if (tipo == "date") {
                valor_campo = valor_campo == null || osm_esVacio(valor_campo.time) ? "" : osm_getFecha(valor_campo.time);
            }
			if (tipo == "money") {
				valor.addClass("money");
			}
			if (tipo == "boolean") {
				//TODO probar este tipo de filtro en subreportes (boolean)
				valor.attr('value', valor_campo);
			}else{
				valor.html(valor_campo);
			}
            var oculto = campos[j].oculto;
            if (oculto == "S") {
                valor.hide();
            }
			else{
                valor.show();
            }
        }
    }
    
    while (i) {
        var id_fila = "fila_" + idReporte + "_" + i;
        var fila = $("#" + id_fila);
        var cont = $("#cont" + id_fila);
        if (fila.length > 0) {
            fila.remove();
            i++;
            if (cont.length > 0) {
                cont.remove();
            }
        }
        else {
            i = false;
        }
    }
	if(id_contenido){
		$("#"+ id_contenido).show();
	}
}

function sortResultado(resultado1, resultado2){
    return resultado1.orden - resultado2.orden;
}

String.prototype.startsWith = function(str){
    return (this.match("^" + str) == str)
}

String.prototype.endsWith = function(str){
    return (this.match(str + "$") == str)
}

function actualizarPaginacion(resultadoConsulta){
    total_registros = resultadoConsulta.total_registros;
    cantidad_pagina = resultadoConsulta.cantidad_pagina;
    // Info
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
    NUM_PAGINA = parseInt((total_registros - 1) / cantidad_pagina);
    consultar();
}

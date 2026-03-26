// jsonrpc._("ipServicio.guardarIPPublica")
// jsonrpc._("ipServicio.obtenerIPPublicas")
// jsonrpc._("ipServicio.eliminarIPPublica")

// jsonrpc._("ipServicio.obtenerIPPrivadas")
// jsonrpc._("ipServicio.eliminarIPPrivada")
// jsonrpc._("ipServicio.guardarIPPrivada")

var ID_USUARIO = null;

var WLOCATION = window.location + "";
var SERVIDOR = WLOCATION.substring(0, WLOCATION.indexOf('/', 8));

var IP_PRIVADA_EDICION = null;
var IP_PUBLICA_EDICION = null;
var TIPO_EDICION = null;
// ------------

function adicionarIPPublica(tipo) {
	
	var desde = '0.0.0.0';
	var hasta = '255.255.255.255';
	
	if(tipo=='N'){
		hasta = desde;
	}
	
	var id_publica = jsonrpc._("ipServicio.guardarIPPublica")(null, desde, hasta, tipo);

	pintarIPPublica(id_publica, desde, hasta, tipo);
	
}

function eliminarIPPublica(id_publica) {
	jsonrpc._("ipServicio.eliminarIPPublica")(id_publica);

	
	$("#ip_publica_" + id_publica).fadeOut(300, function(){
		
		osm_eliminarObjeto(this.id);
		
		if($(".dv_ip_publica").length ==0 ){
			$("#dv_publicas_final_" + this.tipo).hide(300);
		}
		
		validarVerFinalPublico();
	});
	
	
}

function validarVerFinalPublico(){
	
	$(".dv_redes_publicas").each(function(i){
		if($(this).find(".dv_ip_publica").length==0){
			var id = ("" + this.id);
			var tipo = id.replace("dv_redes_publicas_", "");
			
			$("#dv_publicas_final_" + tipo).show(300);
		}
	});
}

function pintarIPPublica(id_publica, desde, hasta, tipo) {
	
	$("#dv_publicas_final_" + tipo).hide();
	
	var res = osm_construirHTML("dv_redes_publicas_" + tipo, "TEMPLATE_PUBLICA", [ id_publica, desde, hasta, tipo ], true);
	
	var obj = osm_getObjeto("ip_publica_" + id_publica);
	obj.tipo = tipo;
	
	$(obj).hide().show(500);
	
	osm_mover("ultimo_" + tipo, "dv_redes_publicas_" + tipo);
	osm_setValor("dv_redes_privadas_" + id_publica, "");
	
	
	jsonrpc._("ipServicio.obtenerIPPrivadas")( function(ips_listado) {

		var ips = ips_listado.list;

		for ( var i = 0; i < ips.length; i++) {
			var ipp = ips[i];
			pintarIPPrivada(ipp.id_ipprivada, ipp.id_ippublica, ipp.desde, ipp.hasta);
		}

	}, id_publica);
	
}

function cerrarVentanaIPPublica() {
	osm_setVisible('vn_editar_ippublica', false);
	osm_mostrarSelects("bodyContent");
}

function actualizarIpPublica(id_publica, tipo) {

	var valor = "";

	var desde = osm_strlimpiar(osm_getValor("dv_rango_ip_publica_desde_" + id_publica));
	var hasta = osm_strlimpiar(osm_getValor("dv_rango_ip_publica_hasta_" + id_publica));

	if(hasta == ""){
		hasta = desde;
	}
	
	jsonrpc._("ipServicio.guardarIPPublica")(id_publica, desde, hasta, tipo);

	osm_setValor("dv_rango_ip_publica_desde_" + IP_PUBLICA_EDICION, desde);
	osm_setValor("dv_rango_ip_publica_hasta_" + IP_PUBLICA_EDICION, hasta);
	
	cerrarVentanaIPPublica();
}


// ------------

function adicionarIPPrivada(ip_publica, tipo) {
	
	var desde = '0.0.0.0';
	var hasta = '255.255.255.255';
	
	if(tipo=='N'){
		hasta = desde;
	}
	
	var id_privada = jsonrpc._("ipServicio.guardarIPPrivada")(null, ip_publica, desde, hasta);

	pintarIPPrivada(id_privada, ip_publica, desde, hasta);
}

function pintarIPPrivada(id_privada, id_publica, desde, hasta) {
	var res = osm_construirHTML("dv_redes_privadas_" + id_publica, "TEMPLATE_PRIVADA", [ id_privada, desde, hasta, id_publica ], true);
	
	var valor = "";
	
	if (desde == hasta) {
		valor = desde;
	} else {
		valor = desde + " - " + hasta;
	}

	osm_setValor("dv_rango_ip_privada_" + id_privada, valor);
	
	$("#ip_publica_" + id_publica).removeClass("dv_ip_publica_simple");
}


function eliminarIPPrivada(id_privada) {
	jsonrpc._("ipServicio.eliminarIPPrivada")(id_privada);

	$("#ip_privada_" + id_privada).fadeOut(300, function(){ 
		osm_eliminarObjeto(this.id);
		validarVerFinalPrivado();
	});
}

function validarVerFinalPrivado(){
	
	$(".dv_redes_privadas").each(function(i){
		if($(this).find(".dv_ip_privada").length==0){
			var id = ("" + this.id);
			var ip_publica = id.replace("dv_redes_privadas_", "");
			
			$("#ip_publica_" + ip_publica).addClass("dv_ip_publica_simple");
		}
	});
}

function editarIPPrivada(id_privada, id_publica) {

	IP_PRIVADA_EDICION = id_privada;
	IP_PUBLICA_EDICION = id_publica;

	var desde = osm_getValor("dv_rango_ip_privada_desde_" + id_privada);
	var hasta = osm_getValor("dv_rango_ip_privada_hasta_" + id_privada);

	osm_setValor("vp_edit_desde_pri", desde);
	osm_setValor("vp_edit_hasta_pri", hasta);

	osm_setVisible('vn_editar_ipprivada', true, true);
	osm_ocultarSelects("bodyContent");
}

function actualizarIpPrivada(id_privada, id_publica) {

	var valor = "";

	var desde = osm_strlimpiar(osm_getValor("dv_rango_ip_privada_desde_" + id_privada));
	var hasta = osm_strlimpiar(osm_getValor("dv_rango_ip_privada_hasta_" + id_privada));

	if(hasta == ""){
		hasta = desde;
	}
	
	jsonrpc._("ipServicio.guardarIPPrivada")(id_privada, id_publica, desde, hasta);

	osm_setValor("dv_rango_ip_privada_desde_" + IP_PRIVADA_EDICION, desde);
	osm_setValor("dv_rango_ip_privada_hasta_" + IP_PRIVADA_EDICION, hasta);
	
	if (desde == hasta) {
		valor = desde;
	} else {
		valor = desde + " - " + hasta;
	}

	osm_setValor("dv_rango_ip_privada_" + IP_PRIVADA_EDICION, valor);

	cerrarVentanaIPPrivada();
}

function cerrarVentanaIPPrivada() {
	osm_setVisible('vn_editar_ipprivada', false);
	osm_mostrarSelects("bodyContent");
}

// ------------

osm_listen("load", window, function() {

	osm_setValor("dv_redes_publicas_B","");
	osm_setValor("dv_redes_publicas_N","");
	
	jsonrpc._("ipServicio.obtenerIPPublicas")( function(ips_listado) {

		var ips = ips_listado.list;

		for ( var i = 0; i < ips.length; i++) {
			var ipp = ips[i];
			pintarIPPublica(ipp.id_ippublica, ipp.desde, ipp.hasta, ipp.estado);
		}

	}, ID_USUARIO);
	
});

var CMP_IDREGISTRO = null;
// -----------------------------------------

function CMP_VISIBLE(id_registro) {
	var registrado = jsonrpc._("soportePagoServicio.esRegistroAplicado")(id_registro);

	return(registrado);
}

// -------------------------------------------

function CMP_ACCION(id_registro) {

	var iframe  = osm_getObjeto("iframe_block");
	iframe.src = CONTEXTPATH + "/soporte_pago/soporte_pago.do?id_retiro=" + id_registro;
	$("#detallecarga_ventana").show();
	
	// -- adiciona los botones adicionales..
	
	 $('#iframe_botones').append("<a id='ibtn_ver' style='display:none' class='btn btn-default' onclick=\"cVer(); osm_getObjeto('iframe_block').src='"+ CONTEXTPATH +"/soporte_pago/soporte_pago.do?id_retiro="+ id_registro +"'; \">Ver soporte de pago</a>");
	 $('#iframe_botones').append("<a id='ibtn_enviar' class='btn btn-default' onclick=\" cEnviar(); osm_getObjeto('iframe_block').src='"+ CONTEXTPATH +"/soporte_pago/mail_soporte_pago.do?id_retiro="+ id_registro +"'; \">Enviar al correo</a>");
	 $('#iframe_botones').append("<a id='ibtn_descargar' class='btn btn-default' onclick=\"osm_getObjeto('iframe_block').src='"+ CONTEXTPATH +"/soporte_pago/f.soportepago?id_retiro="+ id_registro +"'; \">Descargar</a>");
	 $('#iframe_botones').append("<a id='ibtn_imprimir' class='btn btn-default' onclick=\"iframe_block.focus(); iframe_block.print(); \">Imprimir</a>");

}

function cVer(){
	$('#ibtn_ver').hide();
	$('#ibtn_enviar').show();
	$('#ibtn_imprimir').show();
}

function cEnviar(){
	$('#ibtn_enviar').hide();
	$('#ibtn_ver').show();
	$('#ibtn_imprimir').hide();
}

$(function(){
	$("#titulo_detallecarga_ventana").html("SOPORTE DE PAGO");
})

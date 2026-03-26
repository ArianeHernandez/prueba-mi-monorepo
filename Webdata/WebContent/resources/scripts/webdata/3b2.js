$(init_3b2);

function init_3b2(){
	$("#btn_reporte_buscar").hide();
	$(".selectboolean").change(buscarReporte3b);
}

function buscarReporte3b(){
	osm_enviarFormulario("form_buscar");
}

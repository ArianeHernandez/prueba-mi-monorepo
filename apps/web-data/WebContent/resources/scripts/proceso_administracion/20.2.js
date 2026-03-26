$(document).ready(setup);

osm_listen("resize", window, ajustarContenidoProceso);
osm_listen("load", window, ajustarContenidoProceso);

function setup(){
	
	idProcesoAdmin = osm_getValor("id_proceso_admin");
	idNegocio = osm_getValor("id_negocio_proceso_admin");
	
	construirSelectores();
	setTimeout(cargarProceso, 200);
	osm_block_window();
	osm_verLoader();
}

function cargarProceso() {
	consultarFormatosSalida();
	cargarInstanciasAcciones();
	construirSeccionDetalleInstancia();
	listarInstanciasProcesoAdmin();
	pintarLineasInstanciasDestino();
	mostrarAreaProceso();
}

function ajustarContenidoProceso(){
	var pos = osm_getWindowSize();
	
	$("#contenedor_general").css("height", (pos[1] - 140)+"px");
	
	// Se ajusta el contenido del area de dibujo
	$("#AREA_PROCESO").css("height", (pos[1] - 284)+"px");
	$("#AREA_PROCESO").css("width", (pos[0] - pos[0]*0.06 - 271)+"px");
	
	// Se ajusta el tamano para la informacion de la instancia
	$("#lista_inst_proceadmin").css("height", (pos[1] - 284)+"px");
	
	ajustarContenidoInstancia(pos[1]);
}

function ajustarContenidoInstancia(windowHeight) {
	var tituloHeight = $(".titulo_info_instancia").height();
	$(".area_contenido_inst").css("height", (windowHeight - 415 - tituloHeight)+"px");		
}

function mostrarAreaProceso() {
	$("#alerta_inicio").hide();
	$("#area_dibujo").fadeIn(1000);
	$("#lista_inst_proceadmin").fadeIn(1000);
	osm_unblock_window();
}

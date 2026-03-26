
$(initBuscar);
/**
 * Nota:
 * se puede definir la funcion 
 * callbackSeleccionCliente(cliente)
 * donde se pasa como parametro el objeto cliente cuando sea seleccionado
 */

var CLIENTES;

function initBuscar(){
	$(document).click(clickDocumento);
}

function clickDocumento(e){
	var cant = $(e.target).parents(".caja_cliente");
	if(!cant.length && !$(e.target).hasClass("caja_cliente")){
		cerrarBusqueda();
	}
}

function cerrarBusqueda(){
	$("#div_buscar").hide();
}

function mostrarBusqueda(){
	$("#div_buscar").show();
	$("#nombre_busqueda").focus();
	return false;
}

var valores;
var seleccionado = -1;
var valor_buscar = "";
var ultimo_valor = new Date();
var tiempoMin = 300;
//Funcion para buscar clientes, se puede modificar para buscar el cliente con otra funcion json
var BUSCAR_CLIENTE_JSON = null;

function iniciarBusqueda(obj){
	var valor = $(obj).val().toLowerCase();
	if(valor != valor_buscar ){
		valor_buscar = valor;
		setTimeout("buscarCliente();",tiempoMin);
	}
}

function buscarCliente(){
	
	var max = 0;
	var dif = (new Date().getTime() - ultimo_valor.getTime());
	
	if(osm_esVacio(valor_buscar) || dif < tiempoMin){
		return;
	}
	
	ultimo_valor = new Date();
	var tipo = osm_trim(osm_getValor("tipo_cliente"));
	
	
	//Si no existe el tipo se consultan todos los tipos de clientes
	if(tipo == null){
		tipo = 'T';
	}
		
	
	if(BUSCAR_CLIENTE_JSON == null){
		BUSCAR_CLIENTE_JSON = jsonrpc._("usuarioServicio.buscarUsuariosActivos")
	}
	BUSCAR_CLIENTE_JSON(mostrarResultadosBusqueda, valor_buscar, tipo) ;
	
}


function mostrarResultadosBusqueda(lista){
	
	$("[name='fila_cliente']").remove();	
	
	if(lista == null ){
		return;
	}
	lista = lista.list;
	CLIENTES = new Array();
	for(var i = 0; i < lista.length && i < 10; i++){
		var obj = lista[i];
		CLIENTES[obj.id_usuario] = obj;
		var nombres  = obj.nombre;
		if (!osm_esVacio(obj.apellido)) {
			nombres += " " + obj.apellido;
		}
		osm_construirHTML("resultados_clientes", "plantilla_cliente", 
			[	obj.id_usuario,
				obj.identificacion,
				nombres,
				"fila_cliente"
			]);
		
	}
	
	if(lista.length <= 10){
		$("#cuadro_info_footer").hide();
	}
	else{
		$("#cuadro_info_footer").show();
	}
	
	valores = $("[name='fila_cliente']");
	valores.bind("mouseenter", hover);
	
	seleccionado = -1;
	
}
function hover(){
	valores.removeClass("cuadro_fila_hover");
	$(this).addClass("cuadro_fila_hover");
}

function actualizar(){
	valores.removeClass("cuadro_fila_hover");
	$(valores[seleccionado]).addClass("cuadro_fila_hover");
}
function arriba(){
	if (seleccionado) { 
		seleccionado--;
	}
	actualizar();
}
function abajo(){
	if (seleccionado < valores.length -1 ) { 
		seleccionado++;
	}
	actualizar();
}
function seleccionar(){
	var id = $(".cuadro_fila_hover").attr("id");
	if(id!=null){
		id = id.substring("fila_".length);
		seleccionCliente(id);
	}
}

osm_listen("keypress", document, function(e){
	try {
	
		var obj = e.target;
		
		if(e.keyCode == 27 ){
			cerrarBusqueda();	
			return false;
		}
		if(e.keyCode == 38 ){
			arriba();	
			return false;
		}
		
		if(e.keyCode == 40 ){
			abajo();	
			return false;
		}
		
		if(e.keyCode == 13 ){
			seleccionar();	
			return false;
		}
	} catch (e) {
	}
});

function seleccionCliente(id){
	try {
		if(callbackSeleccionCliente){
			var cliente = CLIENTES[id].nombre;
			if(CLIENTES[id].apellido){
				cliente += CLIENTES[id].apellido;
			}
			osm_setValor("sel_cliente" + ID_BUSCAR, cliente);
			callbackSeleccionCliente(CLIENTES[id], ID_BUSCAR);
			cerrarBusqueda();
		}
		return;
	} catch (e) {
	}
	osm_setValor("id_usuario",id);
	cerrarBusqueda();
	var dest = osm_getValor("cliente_destino");
	if(!osm_esVacio(dest)){
		osm_enviarFormulario('form_cliente');
	}else {
		var cliente = osm_getValor('cont_cliente_' + id);
		osm_setValor("sel_cliente",cliente);
		osm_setValor("nombre_busqueda","");
		$("[name='fila_cliente']").remove();
	}
}

//------------------------------------------------------------------
var ID_BUSCAR = "";
function mostrarBusquedaUsuario(id, obj){
	if(obj){
		var pos = osm_getElementPosition(obj);
		//$("#div_buscar").css("left",(pos[0] - 120) + "px");
		//$("#div_buscar").css("top",(pos[1] ) + "px");
	}	
	ID_BUSCAR = id;
	mostrarBusqueda();
	
}


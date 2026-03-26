
var app = angular.module('app', []);

app.controller('appController', function($scope) {

	$scope.evaluarMostrar = function(id_carga, valor_total, numero_registros) {
		$scope.listaDuplicados = jsonrpc._("cargaServicio.listarCargasDuplicadas")(id_carga, valor_total, numero_registros).list;
		console.log($scope.listaDuplicados);

	};

});


function verVentana(idv) {
	osm_setVisible(idv, true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentana(idv) {
	osm_setVisible(idv, false);
	osm_mostrarSelects("bodyContent");
}

function enviar_carga(id_carga, archivo){
	
	osm_setValor("id_carga", id_carga);
	osm_enviarFormulario("frm_enviar");
}

function guardarCargaRelaciona(id_carga){
	var respuesta = jsonrpc._("cargaRelacionada.guardarCargaRelacionada")(id_carga);
	
	if(!respuesta){
		alert('No se pudo guardar la carga relacionada en la session');
	}
	
}

//------------------------------------------------

function validar_archivo(){
	
	if(osm_esVacio(osm_getValor("filename"))){
		
		osm_alert("Seleccione el archivo a Subir.");
		
		return false;
	}
	
	return true;
}

//------------------------------------------------

function eliminar_carga(id_carga){
	
	if(osm_comfirm("\u00bfEst\u00e1 seguro de eliminar la carga?"))
	{
		osm_setValor("id_carga_eliminar", id_carga);
		osm_enviarFormulario("frm_eliminar");
	}
}

//------------------------------------------------

function p_load()
{
	var respuesta = osm_getValor("respuesta");
	if(osm_esVacio(respuesta)){
		return;
	}
	
	if(respuesta == "2"){
		osm_setValor("mensaje", "El formato que est\u00e1 intentando cargar no corresponde con el formato actual del sistema.");
	}
	else if(respuesta == "3"){
		osm_setValor("mensaje", "Debe cargar un archivo excel.");
	}
	else if(respuesta == "5"){
		
	}
	else if(respuesta != "1"){
		osm_setValor("mensaje", "El archivo no se ha podido subir al servidor. " +
		"Por favor intente nuevamente, si el problema continua consulte con el administrador.")
	}
	
	if(respuesta == "1"){ 
		verVentana("vn_confirmacion");
	}
	else{
		verVentana("vn_error"); 
	}
}


osm_listen("load", window, p_load);
function descargar_excel(){
	osm_setValor("btn_descargar_excel","Descargando...");
	osm_getObjeto("btn_descargar_excel").onclick = null;
}

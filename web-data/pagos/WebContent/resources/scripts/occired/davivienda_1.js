
var NUMERO_PAGINA = 1;
var TAMANO_PAGINA = 15;
var NUMERO_PAGINAS = null;

var app = angular.module('app', []);

app.controller('appController', function($scope) {
	
	$scope.duplicados = 0;
	$scope.evaluarMostrar = function(valortotal, num_retiros) {
		$scope.listaDuplicados = jsonrpc._("cargaServicio.listarCargasDuplicadasE")(valortotal, num_retiros).list;
		console.log($scope.listaDuplicados);
		$scope.duplicados = $scope.listaDuplicados.length;
		console.log($scope.duplicados);
	};
});

function verdetalle() {
	
	try{
		var tabla = document.getElementById("tabla_detalle");
		var tbody_detalle = tabla.getElementsByTagName("tbody")[0];
		
		jsonrpc._("progresoArchivoExterno.obtenerRetirosPaginado")(pintardetalle, NUMERO_PAGINA);
		
	}catch (e) {
	}
	
}

function paginacion_inicio(){
	NUMERO_PAGINA = 1;
	verdetalle();
}

function paginacion_final(){
	NUMERO_PAGINA = NUMERO_PAGINAS;
	verdetalle();
}

function paginacion_anterior(){
	if(NUMERO_PAGINA>1)
	{
		NUMERO_PAGINA--;
		verdetalle();
	}
}

function paginacion_siguiente(){
	if(NUMERO_PAGINA<NUMERO_PAGINAS)
	{
		NUMERO_PAGINA++;
		verdetalle();
	}
}



function pintardetalle(respuesta, e){
	
	if(respuesta!=null)
	{
	
		var listado = respuesta.list;
		
		var tabla = document.getElementById("tabla_detalle");
	
		var tbody_detalle = tabla.getElementsByTagName("tbody")[0];
		
		$(tbody_detalle).find(".tabla_fila").remove();
		$(tbody_detalle).find(".tabla_fila_error").remove();
		
		for (var i = 0; i < listado.length ; i++){
			var con_error = false;
			
			var retiro = listado[i];
			
			var tipocuenta = "";
			
			if(retiro.tipo_cuenta_beneficiario_banco == "CA"){
				tipocuenta = "Ahorros";
			}
			
			if(retiro.tipo_cuenta_beneficiario_banco == "CC"){
				tipocuenta = "Corriente";
			}
			
			var nombre_banco = osm_getValor("banco_" + retiro.cod_banco);
			
			if(nombre_banco == null){
				nombre_banco = retiro.cod_banco;
			}

			if((retiro.identificacion_beneficiario + "") == osm_getValor("IDENTIFICACION_CLIENTE")  ){
				con_error = true;
			}
			
			if( retiro.valor == null || retiro.valor<0 ){
				con_error = true;
			}
			
			if(/\d+/.test(nombre_banco)){
				con_error = true;
			}
			
			if(osm_esVacio(retiro.cuenta_beneficiario)){
				con_error = true;
				retiro.cuenta_beneficiario = "Error!!";
			}
			
			if(osm_esVacio(tipocuenta)){
				con_error = true;
				tipocuenta = "Error!!";
			}
			
			construirHTML( $(tbody_detalle) , "PLANTILLA_REGISTRO", con_error,
			[ retiro.nombre_beneficiario, retiro.identificacion_beneficiario, nombre_banco, retiro.cuenta_beneficiario, tipocuenta, osm_formatoMoneda(retiro.valor), retiro.observacion ]);
		}
	}
	
	osm_setValor("texto pagina", "Pagina " + NUMERO_PAGINA + " de " + NUMERO_PAGINAS);
}

// -----------

osm_listen("load", window, function(){
	
	TAMANO_PAGINA = osm_getValorEntero("TAMANO_PAGINA"); 
	NUMERO_PAGINAS = 1 + parseInt((osm_getValorEntero("TOTAL_REGISTROS")-1) / TAMANO_PAGINA);
	
	NUMERO_PAGINA = 1;
	verdetalle();
	
});

function construirHTML(objpadre, id_plantilla, erroneo, parametros){
	
	var htmlplantilla = PLANTILLAS[id_plantilla];
	
	if(!htmlplantilla){
		htmlplantilla = osm_getValor(id_plantilla);
		PLANTILLAS[id_plantilla] = osm_getValor(id_plantilla);
		$("#" + id_plantilla).empty();
	}
	
	for(var j=0; j<parametros.length; j++){
		htmlplantilla = osm_remplazar(htmlplantilla, '[ ' +(j+1) + ' ]', parametros[j]);
	}
	
	if( erroneo == true ) htmlplantilla = htmlplantilla.replace("tabla_fila","tabla_fila_error");
	
	objpadre.append(htmlplantilla);
}

function validacion_carga_archivo(){
	
	try{
		var validacion = validarCargaAprobar();
		if(!validacion){
			return false;
		}
	}
	catch (e) {
	}
	
	return true;
}

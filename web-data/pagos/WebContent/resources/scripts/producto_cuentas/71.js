
var app = angular.module('app', []);

app.controller('appController', function($scope) {
	
	$scope.listaTiposDocumento = jsonrpc._("productoCuentas.listarTiposDocumento")().list;
	$scope.tipoDocumento = null;
	$scope.numeroDocumento = null;
	$scope.visible = false;
	$scope.persona = null;

	
	$scope.buscar = function(){
		$scope.existeCuentas = false;
		$scope.existePersona = false;
		$scope.visible = true;
		$scope.persona = jsonrpc._("productoCuentas.obtenerPersona")($scope.numeroDocumento, $scope.tipoDocumento);
		if($scope.persona != null){
			$scope.existePersona = true;
			$scope.visible = false;
		} else {
			$scope.existePersona = false;
		}
		$scope.listaProductoCuenta = jsonrpc._("productoCuentas.obtenerProductosCuentas")($scope.numeroDocumento, $scope.tipoDocumento).list;
		console.log($scope.listaProductoCuenta);
		if($scope.listaProductoCuenta != null){
			$scope.existeCuentas = true;
		} else {
			$scope.existeCuentas = false;
		}
	}

	
});


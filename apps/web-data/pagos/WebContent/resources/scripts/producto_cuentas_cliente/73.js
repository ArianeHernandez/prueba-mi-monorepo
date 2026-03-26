
var app = angular.module('app', []);

app.controller('appController', function($scope) {
	
	$scope.visible = false;
	$scope.persona = null;

	
	$scope.buscar = function(){
		$scope.existeCuentas = false;
		$scope.existePersona = false;
		$scope.visible = true;
		
		$scope.persona = jsonrpc._("productoCuentas.obtenerPersonaCliente")();
		if($scope.persona != null){
			$scope.existePersona = true;
			$scope.visible = false;
		} else {
			$scope.existePersona = false;
		}
		$scope.listaProductoCuenta = jsonrpc._("productoCuentas.obtenerProductosCuentasCliente")().list;
		console.log($scope.listaProductoCuenta);
		if($scope.listaProductoCuenta != null && $scope.listaProductoCuenta.length > 0){
			$scope.existeCuentas = true;
		} else {
			$scope.existeCuentas = false;
		}
	}
	
	$scope.buscar();

	
});


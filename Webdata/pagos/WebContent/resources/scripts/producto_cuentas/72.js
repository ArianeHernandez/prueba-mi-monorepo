
var app = angular.module('app', []);

app.controller('appController', function($scope) {
	
	$scope.listaProductos = [{}];
	$scope.listaProductoCuentaDefecto = [];
	
	$scope.convenio = null;
	
	$scope.cargarProductosCuentas = function(){
		$scope.listaProductos = jsonrpc._("confCuentaACH.obtenereProductosCuentas")().list;
		for ( var pr in $scope.listaProductos) {
			var productos = $scope.listaProductos[pr];
			for ( var cu in productos.cuentas.list) {
				var cuenta = productos.cuentas.list[cu];
				if (cuenta.porDefecto) {
					$scope.listaProductos[pr].producto.cuentaDefecto = cuenta;
					break;
					console.log(cuenta);
				}
			}
		}
	}
	
	$scope.cargarProductosCuentas();
	
	$scope.actualizarCuentasPorDefault = function(producto) {
		console.log(producto);
	}
	
	$scope.guardarCuentasPorDefault = function() {
		console.log("guardando");
		$scope.listaProductoCuentaDefecto = [];
		
		for ( var pr in $scope.listaProductos) {
			
			if ($scope.listaProductos[pr].producto != null && $scope.listaProductos[pr].producto.cuentaDefecto != null) {
				$scope.listaProductoCuentaDefecto.push({
					idProducto : $scope.listaProductos[pr].producto.id,
					idCuenta : $scope.listaProductos[pr].producto.cuentaDefecto.id,
				});
			}
		}
		
		var actualizado = jsonrpc._("confCuentaACH.actualizarCuentasPorDefecto")($scope.listaProductoCuentaDefecto)
//		console.log($scope.listaProductoCuentaDefecto);
		if (actualizado != null && actualizado) {
			s_alert("Guardado Exitoso", "Se han actualizado las cuentas por defecto");
		} else {
			s_alert("Ops..", "No fue posible actualizar las cuentas por defecto. Intentelo nuevamente.");
		}
		jsonrpc._("confCuentaACH.obtenereProductosCuentas")()
	}
	
	$scope.cancelar = function() {
		osm_go("inicio/0.do");
	};
	
});


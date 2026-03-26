var filt = {
	id_administrativo : null,
	id_atributo : null,
	valor : null
};

var app = angular.module('app', []);

app.controller('appController', function($scope) {

	$scope.listaAtributo = listaAtributos;
	$scope.listaAdmin = listaAdminis;
	
	$scope.atributoActual = null;
	
	$scope.listadoActual = [{}];
	
	// ------------------------------------------------------------
	$scope.eliminarAtributo = function() {
		
//		if (jsonrpc._("atributoJsonServicio.eliminarAtributo")($scope.atributoActual.id_atributo)) {
//			osm_alert("El atributo se ha eliminado correctamente ...");
//			for ( var a in $scope.listaAtributo) {
//				if ($scope.listaAtributo[a].id_atributo == $scope.atributoActual.id_atributo){
//					$scope.listaAtributo.splice(a, 1);
//					break;
//				}
//			}
//		}else{
//			osm_alert("El atributo no se ha podido eliminar, intentelo mas tarde...");
//		}
		
		s_accept("Desea eliminar el atributo " + $scope.atributoActual.nombre + "?", "Al eliminar un atributo se perdera la informacion de los valores relacionados con este ...", "Eliminar", function(eliminar) {
			
			if(eliminar){
				
				if (jsonrpc._("atributoJsonServicio.eliminarAtributo")($scope.atributoActual.id_atributo)) {
					osm_alert("El atributo se ha eliminado correctamente ...");
					
					for ( var a in $scope.listaAtributo) {
						if ($scope.listaAtributo[a].id_atributo == $scope.atributoActual.id_atributo) {
							$scope.listaAtributo.splice(a, 1);
							break;
						}
					}
				}else{
					osm_alert("El atributo no se ha podido eliminar, intentelo mas tarde...");
				}
				
				$scope.$apply();
			}
		});
	};
	// ------------------------------------------------------------
	$scope.crearAtributo = function() {
		s_prompt("Agregar Nuevo Elemento", "Se agregarar un nuevo elemento en el listado de atributos",  function(estado, dato) {
			
			$scope.nuevoAtributo.nombre = dato;
			if (!estado || $scope.nuevoAtributo.nombre == null) {
				return;
			}
			
			for ( var a in $scope.listaAtributo) {
				if ($scope.listaAtributo[a].nombre == $scope.nuevoAtributo.nombre) {
					osm_alert("Error, el Atributo que intenta crear ya existe ...");
					return;
				}
			}
			var resp = jsonrpc._("atributoJsonServicio.crearAtributo")($scope.nuevoAtributo);
			if(resp != null){
				$scope.nuevoAtributo.id_atributo = resp;
				$scope.nuevoAtributo.listAdministrativos = [$scope.listaAdmin];
				$scope.listaAtributo.push($scope.nuevoAtributo);
				$scope.atributoActual = $scope.nuevoAtributo;
				$scope.traerListaValores();
				$scope.limpiarNuevoAtributo();
			}
			$scope.$apply();
		});
		
	};
	// ------------------------------------------------------------
	$scope.crearValorAtributoAdminst = function(id_admin, indice) {
		
		$scope.nuevoValor.id_atributo = $scope.atributoActual.id_atributo;
		$scope.nuevoValor.id_administrativo = id_admin;//$scope.atributoActual.id_administrativo;
		$scope.nuevoValor.valor = $scope.nuevoValor.valor[indice];
		
		var listaV = jsonrpc._("valorAtributoJsonServicio.obtenerValorAtributo")($scope.nuevoValor);
		if (listaV.length > 0) {
			osm_alert("Error, el valor que intenta crear ya existe ...");
			return;
		}
		
		if (jsonrpc._("valorAtributoJsonServicio.crearValorAtributo")($scope.nuevoValor)) {
			$scope.traerListaValores();
			$scope.limpiarNuevoValor();
			return;
		}
		
		return;
		
	};
	// ------------------------------------------------------------
	$scope.eliminarValorAtributo = function(valor) {
		
		if(jsonrpc._("valorAtributoJsonServicio.eliminarValorAtributoAdminst")(valor)){
//			osm_alert("Se ha eliminado correctamente el valor del atributo...");
			$scope.traerListaValores();
		}else{
			osm_alert("Error, se produjo un error al intentar eliminar el valor, intentelo mas tarde...");
		}
		
//		var index = $scope.listaAtributo.indexOf(valor);
//		if (index > -1) {
//			$scope.listaAtributo.splice(index, 1);
////			jsonrpc._("valorAtributoJsonServicio.actualizarListaValorAtributo")(valor)
//			if(jsonrpc._("valorAtributoJsonServicio.eliminarValorAtributoAdminst")(valor)){
//				osm_alert("Se ha eliminado correctamente el valor del atributo...");
//			}else{
//				osm_alert("Error, se produjo un error al intentar eliminar el valor, intentelo mas tarde...");
//			}
//		}
	};
	// ------------------------------------------------------------
	$scope.traerListaValores = function() {

		if ($scope.listaAdmin == null || $scope.listaAdmin.length < 1) {
			return;
		}
		
		$scope.listadoActual = [{}];
		$scope.filtro.id_atributo = $scope.atributoActual.id_atributo;
		
		for ( var ad in $scope.listaAdmin) {
			$scope.filtro.id_administrativo = $scope.listaAdmin[ad].id_administrativo;
			$scope.listaAdmin[ad].listValores = [{}];
			var resp = jsonrpc._("valorAtributoJsonServicio.obtenerValorAtributo")($scope.filtro);
			if (resp.length > 0) {
//				$scope.listadoActual.push(resp);
				$scope.listaAdmin[ad].listValores = resp;
			}
		}
		
		$scope.listadoActual = $scope.listaAdmin;
//		console.log($scope.listadoActual);
//		var resp = jsonrpc._("obtenerAtributosUsuario.obtenerAtributosUsuario")($scope.atributoActual.id_usuario);

		if (resp.length == 0) {
			return;
		}
	}
	// ------------------------------------------------------------
	$scope.limpiarConsulta = function() {
		$scope.filtro = {
				id_administrativo : null,
				id_atributo : null,
				valor : null
			}
	};
	$scope.limpiarConsulta();
	// ------------------------------------------------------------
	$scope.limpiarNuevoAtributo = function() {
		$scope.nuevoAtributo = {
				nombre : null
		}
	};
	$scope.limpiarNuevoAtributo();
	// ------------------------------------------------------------
	$scope.limpiarNuevoValor= function() {
		$scope.nuevoValor = {
				valor : null
		}
	};
	$scope.limpiarNuevoValor();
//	// ------------------------------------------------------------
//	// ------------------------------------------------------------
	
});

//// ------------------------------------------------------------
//
//function scroll_page(total, avance, porcentaje, direccion) {
//	if (direccion > 0 && total - avance < 400) {
//	}
//}
//
//$(function() {
//	// ------------------------------------------------------------
//	// envia la solicitud de consulta con los para metros de busuqeda escogidos
//	filtro.id_convenio = osm_getValor("id_convenio");
//
//	$("#btn_Busqueda").click();
//
//});

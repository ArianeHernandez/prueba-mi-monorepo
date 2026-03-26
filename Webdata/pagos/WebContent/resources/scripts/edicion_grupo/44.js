var app = angular.module('app', []);

var $$scope = null;

app.controller('appController', function($scope) {

	$scope.listado = [];

	$scope.archivo = null;
	$scope.grupos_giro = [];

	$scope.actualizarListado = function() {
		$scope.listado = jsonrpc._("edicionGrupo.listarArchivosGenerados")().list;
	};

	$scope.formatoFecha = function(fecha) {
		return osm_getFecha(fecha);
	}

	$scope.formatoMoneda = function(val) {
		return osm_formatoMoneda(val);
	}

	$scope.cancelarSeleccion = function() {
		$scope.archivo = null;
		$scope.grupos_giro = [];
	};

	$scope.seleccionarArchivo = function(archivo) {
		$scope.archivo = archivo;
		$scope.grupos_giro = jsonrpc._("edicionGrupo.listarGruposGiro")(archivo.codigo).list;
	}
	
	$scope.generarArchivo = function(archivo){
		osm_setValor("id_archivo", archivo.codigo);
		osm_setValor("nombre_archivo_d", archivo.nombre);
		
		osm_enviarFormulario("generarArchivoForm");
		osm_unblock_window();
	}

	$scope.alertaEliminar = function(gg) {

		$$scope = $scope;

		s_accept("\u00BFDesea rechazar el pago de " + osm_formatoMoneda(gg.valor) + " a " + gg.nombre_cliente + " en la cuenta " + gg.cuenta_destino + " del banco " + gg.nombre_banco_destino + "?", "Al rechazarlo este se retira del grupo de giro y se marcara como rechazado.", "Rechazar", function(event) {
			if (event) {

				var resp = jsonrpc._("edicionGrupo.rechazarGrupoGiro")(gg.id);
				$$scope.actualizarListado();
				$$scope.seleccionarArchivo($$scope.archivo);
				

				if (resp) {
					$$scope.archivo.cantidad = $$scope.archivo.cantidad - 1;
					s_alert_info("Rechazo Exitoso", "El rechazo se ha realizado exitosamente.");
				} else {
					s_alert_info("Rechazo Fallido", "Lo sentimos, no es posible realizar la operacion en el momento.");
				}
				$$scope.$apply();
			}
		});

	}

	$scope.actualizarListado();

});

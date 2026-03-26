var app = angular.module('app', []);

app
		.controller(
				'enrolamiento',
				function($scope) {

					// $scope.mensaje = "mensaje texto";

					$scope.init = function() {

						$scope.mensaje_error = 'No es posible procesar la solicitud';

						$("#principal").show();
						$("#boton_enviar").show();
						$("#area_botones").show();
						$("#div_exito").hide();
						$("#div_error").hide();
						$("#nota_error").hide();
						$("#nota_info").hide();
						$("#vn_advertencia").show();


						
						/*$scope.solicitud = {
								tipo_identificacion:null,
								tipo_identificacion_nombre:null,	
								numero_identificacion:null,
								correo_electronico:null,
								otp: null,
								login: null,
								datos_representante:{
									tipo_identificacion:null,
									tipo_identificacion_nombre:null,
									numero_identificacion:null,
									correo_electronico:null,
									relacion_sociedad:null
								}
						}*/

					};

					$scope.init();

					
					
					$scope.enviar = function() {

						if ($scope.validarCampos()) {
							// TODO: SSOC hacer request y esperar por respuesta
							/*var response = jsonrpc._("solicitudEnrolamiento.crearSolicitud")($scope.solicitud);
							console.log(response);
							
							if (response.exitoso) {

								$("#div_exito").show();

							} else {
								if (response.mensaje) {
									$("#div_error").empty();
									$("#div_error")
											.append(
													"<div class='alert alert-danger'><i aria-hidden='true' class='fa fa-exclamation-triangle'></i><span class='sr-only'>Info:</span><p>"
															+ response.mensaje
															+ "</p></div>")
								}
								$("#div_error").show();
							}

							$("#boton_enviar").hide();*/
						}

					};


					$scope.validarCampos = function() {
						// validar campos
						
						
						/*$scope.solicitud.tipo_identificacion = 10; // NIT SIEMPRE POR AHORA
						$scope.solicitud.tipo_identificacion_nombre = "NIT";
						$scope.solicitud.numero_identificacion = osm_getValor("nit");
						$scope.solicitud.correo_electronico = osm_getValor("correo");
						$scope.solicitud.otp = osm_getValor("otp");
						$scope.solicitud.login = osm_getValor("login");
						
						$scope.solicitud.datos_representante.tipo_identificacion = osm_getValor("tipo_documento").replace("number:", "");
						$scope.solicitud.datos_representante.tipo_identificacion_nombre = osm_getValor("tipo_documento").replace("number:", "");
						$scope.solicitud.datos_representante.numero_identificacion = osm_getValor("numero_documento");
						$scope.solicitud.datos_representante.correo_electronico = osm_getValor("correo");
						$scope.solicitud.datos_representante.relacion_sociedad = osm_getValor("relacion").replace("string:", "");
						
						console.log($scope.solicitud);*/
						skin_init_validar();
						skin_validar("autorizacion", "TEXTO", true);
						skin_validar("autorizacion_observaciones", "TEXTO", true);
						skin_validar("contabilidad", "TEXTO", true);
						skin_validar("contabilidad_observaciones", "TEXTO", true);
						skin_validar("pasivo_pensional", "TEXTO", true);
						skin_validar("pasivo_pensional_observaciones", "TEXTO", true);
						skin_validar("marco_legal", "TEXTO", true);
						skin_validar("marco_legal_observaciones", "TEXTO", true);
						skin_validar("grupo_empresarial_95", "TEXTO", true);
						skin_validar("grupo_empresarial_95_observaciones", "TEXTO", true);
						skin_validar("grupo_empresarial_2015", "TEXTO", true);
						skin_validar("grupo_empresarial_2015_observaciones", "TEXTO", true);
						
						
						
						if (SK_ERROR_VALIDACION) {
							osm_alert("Existen datos sin diligenciar o inv\u00E1lidos.\n\nPor favor, verifique los campos resaltados."
									+ mensaje);
							$(".put").removeClass("on");
							$(".ERR_VALIDAR").addClass("on");
							return false;
						}
							
						return true;
					}

					/*$scope.obtenerTipoIdentificacion = function(
							tipo_identificacion) {
						for (i = 0; i < $scope.tipos_documento.length; i++) {
							if ($scope.tipos_documento[i].prefijo == tipo_identificacion) {
								return $scope.tipos_documento[i];
							}
						}
						return null;
					};*/

				});
function cerrarVentana() {
	console.log("cerrar");
	$("#vn_advertencia").hide();
}


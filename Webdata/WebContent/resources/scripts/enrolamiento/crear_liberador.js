var app = angular.module('app', []);


app.controller('enrolamiento', function($scope) {
	
//	$scope.mensaje = "mensaje texto";
	
	$scope.init = function (){
		
		$("#div_exito").hide();
		$("#div_error").hide();
		
		$scope.tipo_documento = null;
		$scope.numero_documento = null;
		
		$scope.solicitud = {
			nombres:null,
			apellidos:null,
			razon_social:null,
			tipo_identificacion:null,
			tipo_identificacion_nombre:null,	
			numero_identificacion:null,	
			fecha_de_expedicion:null,	
			fecha_de_expedicion_string:null,	
			direccion:null,
			telefono:null,
			pais:null,
			ciudad:null,
			correo_electronico:null,	
			celular:null,
			login:null,
			valor_individual:null,
			valor_lote:null,
			datos_usuario:{
				tipo_usuario:null,
				nombres:null,
				apellidos:null,
				tipo_de_documento:null,
				tipo_de_documento_nombre:null,
				numero_de_documento:null,
				fecha_de_nacimiento:null,
				fecha_de_nacimiento_string:null,
				cargo:null,
				direccion_oficina:null,
				telefono_oficina:null,
				celular:null,
				correo_electronico:null,
				celular:null,
				login:null
			},
			datos_representante:{
				nombres:null,
				apellidos:null,
				tipo_identificacion:null,
				tipo_identificacion_nombre:null,
				numero_identificacion:null,
				correo_electronico:null
			}
		};
	};
	
	$scope.init();
	
	$scope.tipos_documento = jsonrpc._("solicitudEnrolamiento.obtenerTiposDocumento")().list;
	$scope.paises = jsonrpc._("solicitudEnrolamiento.obtenerPaises")().list;
	$scope.ciudades = [];
	
	$scope.persona = jsonrpc._("solicitudEnrolamientoLiberador.obtenerInformacionUsuario")();

	$scope.solicitud.razon_social = $scope.persona.nombre;
	$scope.solicitud.correo_electronico = $scope.persona.correo;
	$scope.solicitud.direccion = $scope.persona.direccion;
	$scope.solicitud.numero_identificacion = $scope.persona.identificacion;
	$scope.solicitud.tipo_identificacion = 10;
	$scope.solicitud.tipo_identificacion_nombre = 'Nit';
	$scope.solicitud.telefono = $scope.persona.telefono;

	$scope.enviar = function (){
		
		if( $scope.validarCampos() ){
			if($scope.solicitud.fecha_de_expedicion){
				var parts = $scope.solicitud.fecha_de_expedicion.split('/');
				$scope.solicitud.fecha_de_expedicion = JSON.stringify( new Date(parts[2], parts[1] - 1, parts[0]) ); 
			}
			if($scope.solicitud.datos_usuario && $scope.solicitud.datos_usuario.fecha_de_nacimiento ){
				var parts = $scope.solicitud.datos_usuario.fecha_de_nacimiento.split('/');
				$scope.solicitud.datos_usuario.fecha_de_nacimiento = JSON.stringify( new Date(parts[2], parts[1] - 1, parts[0]) ); 
			}
			
			var existeUsuario = jsonrpc._("solicitudEnrolamientoLiberador.existeLiberadorEnUsuario")($scope.solicitud);
			
			if(existeUsuario){
				osm_alert("El liberador que esta intentando enrolar ya existe.")
				return;
			}
			
			console.log($scope.solicitud);
			
			var response = jsonrpc._("solicitudEnrolamientoLiberador.crearSolicitud")($scope.solicitud);
			console.log(response);
			
			$("#formulario_pj").hide();
			$("#formulario_pn").hide();
			
			if(response.exitoso){
				$("#div_exito").show();
			}else{
				if(response.mensaje){
					$("#div_error").empty();
					$("#div_error").append("<div class='alert alert-danger'><i aria-hidden='true' class='fa fa-exclamation-triangle'></i><span class='sr-only'>Info:</span><p>"+response.mensaje+"</p></div>")
				}
				$("#div_error").show();
			}
			
			$("#boton_enviar").hide();
		}

	};
	
	$scope.validar = function (){
		
		if( !$scope.tipo_documento || !$scope.numero_documento){
			alert("Diligencie todos los campos.");
			return;
		}
		
		osm_verLoader();
		osm_block_window();
		
		var jsonrpc_crpvc = new JSONRPC_HTS(CONTEXTPATH + "/CldXmn/CentralRecaudos/JSON-RPC");
		
		jsonrpc_crpvc._("sincronizacion.tieneProductosCliente")(function(data){
			osm_unblock_window();
			console.log(data);
			if (data) {
				var valido = data.info;
				console.log($scope.tipo_documento);
				if(valido){
					
					$scope.solicitud.numero_identificacion = valido.numero_identificacion;
					$scope.solicitud.razon_social = valido.nombres;
					var tipo_identificacion = $scope.obtenerTipoIdentificacion(valido.tipo_identificacion);
					$scope.solicitud.tipo_identificacion = tipo_identificacion.id;
					$scope.solicitud.tipo_identificacion_nombre = tipo_identificacion.nombre;
						
					$("#area_botones").show();
					
				}else{
					$scope.mensaje_error = 'No es posible crear usuarios para el Titular seleccionado, por favor verifique la informaci\u00F3n o comun\u00EDquese con su Asesor Comercial.';
					$("#nota_info").hide();
					$("#nota_error").show();
					
				}
				$scope.$apply();
			}else{
				s_alert_simple("Lo sentimos,", "Ha ocurrido un error en el proceso", function() {
					return;
				});
			}
			
		}, $scope.numero_documento, $scope.tipo_documento);
		
	};
	
	$scope.actualizarCiudades = function (){
		$scope.ciudades = [];
		$scope.ciudades = jsonrpc._("solicitudEnrolamiento.obtenerCiudades")($scope.solicitud.pais).list;		
	}
	
	$scope.validarCampos = function (){
		return $scope.validarCamposPJ();
	}
	
	$scope.obtenerTipoIdentificacion = function (tipo_identificacion){
		for (i=0;i<$scope.tipos_documento.length;i++){
			if($scope.tipos_documento[i].prefijo == tipo_identificacion){
				return $scope.tipos_documento[i];
			}
		}
		return null;
	};
	
	$scope.validarCamposPN = function (){
		var mensaje = "";
		
		skin_init_validar();

		skin_validar("pn_nombres", "TEXTO", true);
		skin_validar("pn_apellidos", "TEXTO", true);
		skin_validar("pn_tipo_documento", "TEXTO", true);
		skin_validar("pn_numero_documento", "NUMERO", true);
		skin_validar("pn_fecha_expedicion", "TEXTO", true);
		skin_validar("pn_direccion", "TEXTO", true);
		skin_validar("pn_telefono", "TEXTO", true);
		skin_validar("pn_pais", "TEXTO", true);
		skin_validar("pn_ciudad", "TEXTO", true);
		skin_validar("pn_correo", "CORREO", true);
		skin_validar("pn_celular", "NUMERO", true);
		skin_validar("pn_login", "TEXTO", true);
		skin_validar("pj_valor_individual", "NUMERO", true);
		skin_validar("pj_valor_lote", "NUMERO", true);
		
		var login_disponible = jsonrpc._("solicitudEnrolamiento.loginDisponible")($scope.solicitud.login);
		
		if(!login_disponible){
			skin_error("pn_login");
			mensaje = "\n Login no disponible.";
		}
		
		if (SK_ERROR_VALIDACION) {
			osm_alert("Existen datos sin diligenciar o inv\u00E1lidos.\n\nPor favor, verifique los campos resaltados." + mensaje);
			$(".put").removeClass("on");
			$(".ERR_VALIDAR").addClass("on");
			return false;
		}
		return true;
		
	};
	
	$scope.validarCamposPJ = function (){
		var mensaje = "";
		skin_init_validar();

		skin_validar("pj_razon_social", "TEXTO", true);
		skin_validar("pj_tipo_documento", "TEXTO", true);
		skin_validar("pj_numero_documento", "TEXTO", true);
		skin_validar("pj_direccion", "TEXTO", true);
		skin_validar("pj_telefono", "TEXTO", true);
		skin_validar("pj_pais", "TEXTO", true);
		skin_validar("pj_ciudad", "TEXTO", true);
		skin_validar("pj_usuario_nombres", "TEXTO", true);
		skin_validar("pj_usuario_apellidos", "TEXTO", true);
		skin_validar("pj_usuario_tipo_documento", "TEXTO", true);
		skin_validar("pj_usuario_numero_documento", "NUMERO", true);
		skin_validar("pj_usuario_fecha", "TEXTO", true);
		skin_validar("pj_cargo", "TEXTO", true);
		skin_validar("pj_usuario_direccion", "TEXTO", true);
		skin_validar("pj_usuario_telefono", "NUMERO", true);
		skin_validar("pj_usuario_celular", "NUMERO", true);
		if( !skin_validar("pj_usuario_correo", "CORREO", true) ){
			mensaje = mensaje + "\n El formato de correo electr\u00F3nico del liberador no es v\u00E1lido, por favor verifique.";
		}
		
		skin_validar("pj_representante_nombres", "TEXTO", true);
		skin_validar("pj_representante_apellidos", "TEXTO", true);
		skin_validar("pj_representante_tipo_documento", "TEXTO", true);
		skin_validar("pj_representante_numero_documento", "NUMERO", true);

		if( !skin_validar("pj_representante_correo", "CORREO", true) ){
			mensaje = mensaje + "\n El formato de correo electr\u00F3nico del representante legal no es v\u00E1lido, por favor verifique.";
		}
		
		if(skin_validar("pj_login", "TEXTO", true)){
			var login_disponible = jsonrpc._("solicitudEnrolamiento.loginDisponible")($scope.solicitud.login);
			
			if(!login_disponible){
				skin_error("pj_login");
				mensaje = mensaje + "\n Login no disponible.";
			}
		}
		
		if (SK_ERROR_VALIDACION) {
			osm_alert("Existen datos sin diligenciar o inv\u00E1lidos.\n\nPor favor, verifique los campos resaltados." + mensaje);
			$(".put").removeClass("on");
			$(".ERR_VALIDAR").addClass("on");
			return false;
		}
		return true;
		
	};

	} );

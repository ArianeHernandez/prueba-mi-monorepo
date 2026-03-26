var app = angular.module("app", []);

app.controller("enrolamiento", function ($scope) {
	$scope.mensaje = "";
	$scope.mensajelogin = "";
	$scope.mensajecorreo = "";
	$scope.mensajenit = "";
	

	$scope.init = function () {
		$scope.mensaje_error = "No es posible procesar la solicitud";

		$("#principal").show();
		$("#boton_enviar").show();
		$("#area_botones").hide();
		$("#div_exito").hide();
		$("#div_error").hide();
		$("#nota_error").hide();
		$("#nota_info_soc").show();
		$("#nota_info_pnc").hide();
		$("#nota_info_pnnoc").hide();
		$("#id_tipo_archivo").val(11);

		$scope.tiposSolicitud = [];
		
		var listaTiposSolicitante = jsonrpc._("solicitudEnrolamiento.obtenerTiposSolicitante")();
		
		if(listaTiposSolicitante && listaTiposSolicitante.list){
			listaTiposSolicitante.list.forEach(function(i) {
				$scope.tiposSolicitud.push({nombre:i.nombre,valor:i.id});
			});
		}

		$scope.solicitud = {
			tipo_formulario: 1,
			tipo_identificacion: null,
			tipo_identificacion_nombre: null,
			numero_identificacion: null,
			correo_electronico: null,
			otp: null,
			login: null,
			datos_representante: {
				tipo_identificacion: null,
				tipo_identificacion_nombre: null,
				numero_identificacion: null,
				correo_electronico: null,
				relacion_sociedad: null,
				nombres: null,
				apellidos: null,
				celular: null,
				nit_sociedad: null,
			},
			ciudad: null,
			pais: 169,
			direccion: null,
			sociedad:{
				nombre:null,
				nit:null
			}
		};
		
		extensionesAceptadas();

		$("#caja_archivo_adjunto").change(function (e) {
			var id_campo = $(this).attr("id");
			var file = e.target.files[0];
			if (!file) {
				$scope.removeFile('formulario_archivo');
				return;
			}
			
			var esValido = validarExtensiones(file);
			
			if (esValido) {
				var fileName = file.name;
				osm_setValor("label_formulario_archivo", fileName);
				$("#trash_formulario_archivo").removeClass("ic-hide");				
			} else {
				$scope.removeFile('formulario_archivo');
			}
		});
	};
	
	function validarExtensiones (file) {
		
		var id_tipo_archivo = osm_getValor("id_tipo_archivo"); 
		
		var extensiones = jsonrpc._("solicitudEnrolamiento.obtenerExtensionesPorTipoArchivo")(parseInt(id_tipo_archivo));
		
		if(extensiones.list && extensiones.list.length > 0){
			
			var valid = false;
			
			var filename = file.name.split(/\./g);
			var extension = filename.length > 1 ? filename.pop(): "";
			var textoExtensiones = "";
			
			extensiones = extensiones.list[0].split(", ");
			for (var i = 0; i < extensiones.length; i++) {
			    if (extensiones[i].toLowerCase() == extension.toLowerCase()) {
			    	valid = true;
			    	break;
			    }
			    
			    textoExtensiones += extensiones[i].toLowerCase();
			    if (i != extensiones.length - 1) textoExtensiones += ", ";  
			}
			
			if (!valid) {
				s_alert_info('Formato del archivo inv\u00e1lido', 'Debes adjuntar un archivo con formato ' + textoExtensiones + ' antes de continuar');
				return false;
			}
		}
		
		return true;
		
	}
	
	function extensionesAceptadas () {
		var id_tipo_archivo = osm_getValor("id_tipo_archivo"); 
		
		var extensiones = jsonrpc._("solicitudEnrolamiento.obtenerExtensionesPorTipoArchivo")(parseInt(id_tipo_archivo));
		
		var campoArchivo = document.getElementById("caja_archivo_adjunto"); 
		
		campoArchivo.accept = extensiones.list[0]?.replace(/(\w+)/g, ".$1").toLowerCase(); 
	}

	$scope.init();

	var tiposDoc = _.filter(
		jsonrpc._("solicitudEnrolamiento.obtenerTiposDocumento")().list,
		function (o) {
			return o.id == 1 || o.id == 3 || o.id == 4;
		}
	);
	
	var pais = jsonrpc._("solicitudEnrolamiento.obtenerPaisPorCodigo")("169");//colombia
	$scope.solicitud.pais = pais.id;
	
	$scope.tipos_documento = tiposDoc;

	//TODO: SSOC INTEGRAR CON ListaValoresServicio
	$scope.relaciones_sociedad = jsonrpc._(
		"solicitudEnrolamiento.obtenerRelacionesSociedad"
	)().list;
	
	$scope.abrir_advertencia = function () {
		osm_setVisible("vn_advertencia_PNC_PNNC", true, true);
		osm_ocultarSelects("bodyContent");
		$("body").css("overflow", "hidden");
		$('html, body').animate({
			 scrollTop: $("#bs-example-navbar-collapse-1").offset().top
			 }, 0);
	}
	
	$scope.cerrar_advertencia = function () {
		osm_setVisible("vn_advertencia_PNC_PNNC", false);
		osm_mostrarSelects("bodyContent");
		$("body").css("overflow", "auto");
	}
	
	$scope.abrir_modal_cargue = function () {
		osm_setVisible("vn_creacion_solicitud", true, true);
		osm_ocultarSelects("bodyContent");
		$("body").css("overflow", "hidden");
		$('html, body').animate({
			 scrollTop: $("#bs-example-navbar-collapse-1").offset().top
			 }, 0);
	}
	
	$scope.cerrar_modal_cargue = function () {
		osm_setVisible("vn_creacion_solicitud", false);
		osm_mostrarSelects("bodyContent");
		$("body").css("overflow", "auto");
	}
	
	$scope.abrir_modal_reintento = function () {
		osm_setVisible("vn_error_cargue", true, true);
		osm_ocultarSelects("bodyContent");
		$("body").css("overflow", "hidden");
		$('html, body').animate({
			 scrollTop: $("#bs-example-navbar-collapse-1").offset().top
			 }, 0);
	}
	
	$scope.cerrar_modal_reintento = function () {
		osm_setVisible("vn_error_cargue", false);
		osm_mostrarSelects("bodyContent");
		$("body").css("overflow", "auto");
	}
	
	$scope.cancelar_cambio_ts = function () {
		osm_setClassname("tipoFormulario", "form-input ng-pristine ng-untouched ng-valid ng-not-empty select-focused");
		$scope.cerrar_advertencia(); 
	}
	
	$("#btn_aceptar_vn_ad").click(function() {
		$scope.cerrar_advertencia(); 
	} );
	$("#btn_cancelar_vn_ad").click(function() {
		$scope.cancelar_cambio_ts(); 
	} );
	
	$("#btn_reintentar_vn").click(function() {
		$scope.cerrar_modal_reintento();
		$scope.abrir_modal_cargue(); 
		
		try {
			
			var carga = osm_getValor("id_carga");
			
			if (!carga) {
				jsonrpc._("solicitudEnrolamiento.crearCarga")($scope.cargarArchivo,false);
			} else {
				$scope.cargarArchivo(carga);						
			}
		} catch (e) {
			alert("Error al crear carga!");
			osm_unblock_window();
			osm_ocultarLoader();
		}
		
	} );
	$("#btn_cancelar_reintento_vn").click(function() {
		$scope.cerrar_modal_reintento();
		osm_unblock_window();
		osm_ocultarLoader();
	} );
	
	$scope.abrir_alerta_reintento = function () {
		s_accept("Registro Solicitante", 
				"<parrafo>Señor usuario, el documento soporte de la solicitud no se ha podido cargar.</parrafo> " +
				"<parrafo>Para poder realizar el envío de su solicitud reintente el cargue del documento " +
						" utilizando el botón 'Reintentar', en caso de querer reemplazar la información del registro " +  
						" seleccione el botón 'Cancelar'.</parrafo>", 
						"Reintentar", $scope.reintentarCargue);
	}
	
	$scope.abrir_alerta_cargue = function () {
		s_wait("Registro Solicitante", '<div class="row-box form-horizontal"><parrafo>El sistema está cargando la información. Por favor espere.</parrafo></div>');
		$("#sAlert").find(".modal-dialog").show();
	}
	
	$scope.cerrar_alerta = function () {
		osm_unblock_window();
		osm_ocultarLoader();
		s_alert_hide();
	}
	
	$scope.reintentarCargue = function (value) {
		
		if (!value) {
			$scope.cerrar_alerta();	
			return;
		}
		$scope.abrir_alerta_cargue(); 
		
		try {
			
			var carga = osm_getValor("id_carga");
			
			if (!carga) {
				jsonrpc._("solicitudEnrolamiento.crearCarga")($scope.cargarArchivo,false);
			} else {
				$scope.cargarArchivo(carga);						
			}
		} catch (e) {
			alert("Error al crear carga!");
			$scope.cerrar_alerta();
		}
	}
	
	$scope.enviar = function () {
		osm_block_window();
		osm_verLoader();

		if ($scope.validarCampos()) {
			var valida_token = jsonrpc._(
				"solicitudEnrolamiento.validarTokenSinSesion"
			)(
				$scope.solicitud.datos_representante.numero_identificacion,
				$scope.solicitud.datos_representante.tipo_identificacion,
				$scope.solicitud.otp
			);

			var existecaptcha = $('[name=g-recaptcha-response]').length > 0;
			var rcp = osm_trimToNull($('[name=g-recaptcha-response]').val());

			if (existecaptcha && rcp == null) {
				osm_alert_dangerE("Marque la casilla de 'No soy un robot'", "", function () {
				});
				osm_unblock_window();
				osm_ocultarLoader();
				return false;
			}


			if (valida_token.valid) {
				$("#otp").removeClass("ERR_VALIDAR");
				var jsonrpc_crpvc = new JSONRPC_HTS(
					CONTEXTPATH + "/CldXmn/CentralRecaudos/JSON-RPC"
				);

				$scope.abrir_alerta_cargue(); 
				
				try {
					
					var carga = osm_getValor("id_carga");
					
					if (!carga) {
						jsonrpc._("solicitudEnrolamiento.crearCarga")($scope.cargarArchivo,false);
					} else {
						$scope.cargarArchivo(carga);						
					}
				} catch (e) {
					alert("Error al crear carga!");
					$scope.cerrar_alerta();
				}
			} else {
				alert("El OTP ingresado es inv\u00e1lido.");
				$("#otp").addClass("ERR_VALIDAR");
				osm_unblock_window();
				osm_ocultarLoader();
			}
		}
	};

	// TERMINAR
	
	$scope.crearSolicitud = function () {

		try {
			$scope.solicitud.departamento_dane = parseInt(osm_getValor("deudor_departamento"), 10);
			$scope.solicitud.municipio_dane = parseInt(osm_getValor("deudor_ciudad"), 10);
			console.log("solicitud");
			console.log($scope.solicitud);
			jsonrpc._("solicitudEnrolamiento.crearSolicitud")(function (
				response
			) {
				console.log(response);
	
				$scope.cerrar_alerta();
				
				if (response && response.exitoso) {
					$("#formulario_solicitud").hide();
					$("#botones_form").hide();
					$("#div_exito").show();
					$("#area_botones").show();
				} else {
					if (response.mensaje) {
						$("#div_error").empty();
						$("#div_error").append(
							"<div class='alert alert-danger'><i aria-hidden='true' class='fa fa-exclamation-triangle'></i><span class='sr-only'>Info:</span><p>" +
							response.mensaje +
							"</p></div>"
						);
					}
					$("#div_error").show();
					$("#area_botones").show();
				}
	
				$("#boton_enviar").hide();
	
				osm_unblock_window();
				osm_ocultarLoader();
			},
				$scope.solicitud);
		} catch (e) {
			alert("Error al crear solicitud!");
			$scope.cerrar_alerta();
		}
		
	}
	
	$scope.validarCampos = function () {
		var numeros = /^[0-9]+$/;
		// validar campos
		$scope.solicitud.tipo_identificacion = 10; // NIT SIEMPRE POR AHORA
		$scope.solicitud.tipo_identificacion_nombre = "NIT";
		$scope.solicitud.numero_identificacion = $scope.solicitud.tipo_formulario == 1 || $scope.solicitud.tipo_formulario == 5 ? osm_getValor("nit") : osm_getValor("numero_documento");
		$scope.solicitud.correo_electronico = osm_getValor("correo");
		$scope.correo_electronico_confirmar = osm_getValor("correo_confirmacion");
		$scope.solicitud.otp = osm_getValor("otp");
		$scope.solicitud.login = osm_getValor("login");
//		$scope.solicitud.departamento_dane = osm_getValor("deudor_departamento");
//		$scope.solicitud.municipio_dane = osm_getValor("deudor_ciudad");

		//						$scope.solicitud.datos_representante.tipo_identificacion = parseInt(osm_getValor("tipo_documento").replace("number:", ""));
		$scope.solicitud.datos_representante.tipo_identificacion = osm_getValor("tipo_documento").replace("number:", "");
		$scope.solicitud.datos_representante.tipo_identificacion_nombre = osm_getValor("tipo_documento").replace("number:", "");
		$scope.solicitud.datos_representante.numero_identificacion = osm_getValor("numero_documento");
		$scope.solicitud.datos_representante.correo_electronico = osm_getValor("correo");
		//						$scope.solicitud.datos_representante.nombres = osm_getValor("numero_documento");
		//						$scope.solicitud.datos_representante.apellidos = osm_getValor("numero_documento"); 
		$scope.solicitud.datos_representante.relacion_sociedad = 1; //Representante legal

		console.log($scope.solicitud);

		skin_init_validar();
		if (osm_getValor("tipoFormulario") == 'number:5' || osm_getValor("tipoFormulario") == 'number:1') {
		    if(skin_validar("nit", "NUMERO", true)){
		        var nit_valor = osm_getValor("nit");
		        var numeros = /^[0-9]+$/; // Expresión regular para validar solo números
		        
		        // Validar longitud exacta de 9 dígitos, solo caracteres numéricos y rango entre 80000000 y 999999999
		        if(nit_valor.length != 9 || !numeros.test(nit_valor) || 
		           parseInt(nit_valor) < 80000000 || parseInt(nit_valor) > 999999999) {
		            $("#nit").addClass("ERR_VALIDAR");
		            SK_ERROR_VALIDACION = true;
		            $scope.mensajenit = "El NIT debe ser numérico, contener exactamente 9 dígitos y estar en el rango 80000000 a 999999999";
		        } else {
		            $scope.mensajenit = "";
		        }
		    }
		}
		var tipo_doc = osm_getValor("tipo_documento");
		var num_doc = osm_getValor("numero_documento");
		var numeros = /^[0-9]+$/;
		var alfanumerico = /^[a-zA-Z0-9]+$/;
		
		
		if (!tipo_doc || tipo_doc === "" || tipo_doc === "null") {
		    $("#tipo_documento_autocomplete").addClass("ERR_VALIDAR");
		    SK_ERROR_VALIDACION = true;
		} else {
		    $("#tipo_documento_autocomplete").removeClass("ERR_VALIDAR");
		}
		
		if (!num_doc || num_doc === "null" || num_doc === "") {
		    $("#numero_documento").addClass("ERR_VALIDAR");
		    SK_ERROR_VALIDACION = true;
		} else {
		    $("#numero_documento").removeClass("ERR_VALIDAR");
		}


		if (tipo_doc == 'number:1') {
		    if (skin_validar("numero_documento", "NUMERO", true)) {
		        if (!numeros.test(num_doc) || num_doc.length < 3 || num_doc.length > 11) {
		            $("#numero_documento").addClass("ERR_VALIDAR");
		            SK_ERROR_VALIDACION = true;
		            $scope.mensajedocumento = "La número de documento debe ser numérica y tener entre 3 y 11 dígitos";
		        }
		    }
		} 
		else if (tipo_doc == 'number:4' || tipo_doc == 'number:3') { // Cédula Extranjería o Pasaporte
		    if (skin_validar("numero_documento", "TEXTO", true)) {
		        if (!alfanumerico.test(num_doc) || num_doc.length < 3 || num_doc.length > 20) {
		            $("#numero_documento").addClass("ERR_VALIDAR");
		            SK_ERROR_VALIDACION = true;
		            $scope.mensajedocumento = "El número de documento debe ser alfanumérico y tener entre 3 y 20 caracteres";
		        }
		    }
		}
		if(skin_validar("correo", "TEXTO", true)){
		    var correo_valor = osm_getValor("correo");
		    var correo_valido = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		    
		    if(!correo_valido.test(correo_valor)){
		        $("#correo").addClass("ERR_VALIDAR");
		        SK_ERROR_VALIDACION = true;
		        $scope.mensajecorreo = "El correo electrónico contiene caracteres no válidos o formato incorrecto";
		    }
		}

		if(skin_validar("correo_confirmacion", "TEXTO", true)){
		    var correo_conf_valor = osm_getValor("correo_confirmacion");
		    var correo_valor = osm_getValor("correo");
		    
		    if(!correo_valido.test(correo_conf_valor)){
		        $("#correo_confirmacion").addClass("ERR_VALIDAR");
		        SK_ERROR_VALIDACION = true;
		        $scope.mensajecorreo = "El correo de confirmación contiene caracteres no válidos o formato incorrecto";
		    } else if(correo_conf_valor !== correo_valor){
		        $("#correo_confirmacion").addClass("ERR_VALIDAR");
		        SK_ERROR_VALIDACION = true;
		        $scope.mensajecorreo = "Los correos electrónicos no coinciden";
		    }
		}
		if(skin_validar("otp", "TEXTO", true)){
			var tokenValido = /^[a-zA-Z0-9]+$/;
			var token = osm_getValor("otp");
			if (!tokenValido.test(token)){
				$("#otp").addClass("ERR_VALIDAR");
				SK_ERROR_VALIDACION = true;
				$scope.mensajetoken = "\n El Token deberá corresponder al generado en Signapp";
			}
		}
		if (skin_validar("numero_telefono", "NUMERO", true)) {
		    var tel = osm_getValor("numero_telefono");
		    var soloNumeros = /^[0-9]+$/; 
		    
		    if (!soloNumeros.test(tel) || tel.length < 7 || tel.length > 10) {
		        $("#numero_telefono").addClass("ERR_VALIDAR");
		        SK_ERROR_VALIDACION = true;
		        $scope.mensajetelefono = "El Teléfono debe ser numérico, sin espacios ni caracteres especiales, y tener entre 7 y 10 dígitos";
		    } else {
		        $scope.mensajetelefono = ""; 
		    }
		}
		if(skin_validar("pj_usuario_direccion_notificacion", "TEXTO", true)){
		    var direccion = osm_getValor("pj_usuario_direccion_notificacion");
		    var caracteresValidos = /^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\s\.,#\-]+$/;
		    
		    if(direccion.length < 6 || direccion.length > 255){
		        $("#pj_usuario_direccion_notificacion").addClass("ERR_VALIDAR");
		        SK_ERROR_VALIDACION = true;
		        $scope.mensajedireccion = "La dirección debe tener entre 6 y 255 caracteres";
		    } else if(!caracteresValidos.test(direccion)){
		        $("#pj_usuario_direccion_notificacion").addClass("ERR_VALIDAR");
		        SK_ERROR_VALIDACION = true;
		        $scope.mensajedireccion = "La dirección contiene caracteres no válidos";
		    }
		}
		if(skin_validar("pj_usuario_nombre", "TEXTO", true)){
		    var nombres = osm_getValor("pj_usuario_nombre");
		    var caracteresValidos = /^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\s']+$/;

		    if(!caracteresValidos.test(nombres) || nombres.length < 3 || nombres.length > 120){
		        $("#pj_usuario_nombre").addClass("ERR_VALIDAR");
		        SK_ERROR_VALIDACION = true;
		        $scope.mensajenombres = "EL nombre debe ser alfanumérico y tener entre 3 y 120 caracteres";
		    }
		}

		if(skin_validar("pj_usuario_apellidos", "TEXTO", true)){
		    var apellidos = osm_getValor("pj_usuario_apellidos");
		    var caracteresValidos = /^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\s']+$/;

		    if(!caracteresValidos.test(apellidos) || apellidos.length < 3 || apellidos.length > 120){
		        $("#pj_usuario_apellidos").addClass("ERR_VALIDAR");
		        SK_ERROR_VALIDACION = true;
		        $scope.mensajeapellidos = "El apellido debe ser alfanumérico y tener entre 3 y 120 caracteres";
		    }
		}
		if ($scope.solicitud.tipo_formulario == null || $scope.solicitud.tipo_formulario == '') {
			SK_ERROR_VALIDACION = true;
			$("#tipoFormulario").addClass("ERR_VALIDAR");
		}
		skin_validar("tipo_documento", "TEXTO", true);
		skin_validar("deudor_departamento_autocomplete", "TEXTO", true);
		skin_validar("deudor_ciudad", "TEXTO", true);
		skin_validar("numero_documento", "TEXTO", true);
		skin_validar("deudor_ciudad_autocomplete", "TEXTO", true);
		skin_validar("pj_usuario_nombre", "TEXTO", true);
		skin_validar("pj_usuario_apellidos", "TEXTO", true);
		
		skin_validar("tipo_documento", "TEXTO", true);
		if ($("#formulario_archivo").find("#caja_archivo_adjunto").first()[0].files[0] == undefined) {
			SK_ERROR_VALIDACION = true;
			$("#label_formulario_archivo").addClass("ERR_VALIDAR");
		}
		//validar datos con ng-class
		$scope.mensaje = "";
		if (skin_validar("login", "TEXTO", true)) {
			var login = osm_getValor("login");
			var regex = /^[a-zA-Z0-9]{5,50}$|^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
			
			if (!regex.test(login)) {
				SK_ERROR_VALIDACION = true;
				$("#login").addClass("ERR_VALIDAR");
				$scope.mensajelogin = "\n El usuario debe utilizar como m\u00EDnimo 5 caracteres y como m\u00E1ximo 50 caracteres y no puede contener caracteres especiales";
			} else {
				var login_disponible = jsonrpc._("solicitudEnrolamiento.loginDisponible")(
					osm_getValor("login")
				);

				if (!login_disponible) {
					SK_ERROR_VALIDACION = true;
					$("#login").addClass("ERR_VALIDAR");
//					$scope.mensajelogin = "\n Usuario no disponible.";
				} else {
					$scope.mensajelogin = "";
				}

			}
		}
		if (SK_ERROR_VALIDACION) {
			osm_alert(
				"Error al Validar Datos.\n\nExisten datos sin diligenciar o inv\u00E1lidos. Por favor, verifique los campos resaltados." +
				$scope.mensaje
			);
			$(".put").removeClass("on");
			$(".ERR_VALIDAR").addClass("on");
			osm_unblock_window();
			osm_ocultarLoader();
			return false;
		}

		return true;
	};

	$scope.obtenerTipoIdentificacion = function (tipo_identificacion) {
		for (i = 0; i < $scope.tipos_documento.length; i++) {
			if ($scope.tipos_documento[i].prefijo == tipo_identificacion) {
				return $scope.tipos_documento[i];
			}
		}
		return null;
	};

	$scope.cargarArchivo = function (id_carga) {
		osm_setValor("id_carga", id_carga);
		$scope.solicitud.idcarga = id_carga;
		
		var caja = osm_getValor("caja_archivo_adjunto");
		caja = jQuery.trim(caja);
		if (osm_esVacio(caja)) {
			s_alert_info(
				"Falta seleccionar el archivo",
				"Debe seleccionar el archivo a adjuntar."
			);
			return;
		}
		var id_archivo_adjunto = jsonrpc._(
			"solicitudEnrolamiento.obtenerSiguiente"
		)();
		osm_setValor("id_archivo_adjunto", id_archivo_adjunto);
		osm_setValor("id_carga", id_carga);
		var options = {};

		$("#bloque_form_adjuntar_archivos").fadeOut(100);
		$("#loading_form_adjuntar_archivos").fadeIn(100);

		var tipo_archivo = osm_getValor("id_tipo_archivo");
		
		if (validarS3Adjuntos()) {
			var file = $("#caja_archivo_adjunto")[0].files[0];
			var descripcion = osm_getValor("descripcion_archivo_adj");
			var tipo_archivo = osm_getValor("id_tipo_archivo");
			descripcion = encodeURIComponent(descripcion);
			var adjunto = {
					id_archivo_adjunto: id_archivo_adjunto,
					id_carga: id_carga,
					descripcion: descripcion,
					interno: "N",
					estado: "A",
					id_tipo_archivo: tipo_archivo 
			};
			
			enviarAdjuntoS3(file, adjunto, $scope.validarCargue, $scope.validarCargue);
		} else {
			var form_data = new FormData();
			form_data.append(
				"caja_archivo_adjunto",
				$("#caja_archivo_adjunto")[0].files[0]
			);
			form_data.append("id_archivo_adjunto", id_archivo_adjunto);
			form_data.append("id_carga", id_carga);
			form_data.append(
				"descripcion_archivo_adj",
				osm_getValor("descripcion_archivo_adj")
			);
			form_data.append("id_tipo_archivo", tipo_archivo);
	
			$.ajax({
				method: "POST",
				url: "../adjunto.subirarchivo",
				contentType: "application/json;charset=iso-8859-1",
				data: form_data,
				processData: false,
				contentType: false,
				cache: false,
			}).done(function (msg) {
				console.log(msg);
				jsonrpc._("solicitudEnrolamiento.actualizarDescripcion")(
					id_archivo_adjunto,
					"Certificado de existencia y representaci\u00F3n legal"
				);
				$scope.validarCargue();
			}).fail(function (msg) {
				console.log(msg);
				$scope.validarCargue();
			});
		}
	};

	$scope.validarCargue = function() {
		
		var idcarga = osm_getValor("id_carga");
		var adjuntos = jsonrpc._("solicitudEnrolamiento.obtenerArchivosAdjuntosPorCarga")(idcarga).list;
		
		if (adjuntos && adjuntos.length > 0) {
			$scope.crearSolicitud();
		} else {
			$scope.abrir_alerta_reintento();
		}
	}
	
	$scope.loginChange = function () {
		var login = osm_getValor("login");
		var regex = /^[a-zA-Z0-9]{5,50}$|^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
		
		if (!regex.test(login)) {
			$scope.mensajelogin = "\n El usuario debe utilizar como m\u00EDnimo 5 caracteres y como m\u00E1ximo 50 caracteres y no debe contener caracteres especiales";
			
		} else {
			
			var login_disponible = jsonrpc._("solicitudEnrolamiento.loginDisponible")(
					login
			);
			
			if (!login_disponible) {
				$scope.mensajelogin = "\n Usuario no disponible.";
			} else {
				$scope.mensajelogin = "";
			}
		}
		

	};
	
	$scope.nitChange = function () {
	    var nit_valor = osm_getValor("nit");
	    var expresionRegular = /^[0-9]+$/; // Solo permite números
	    
	    // Validación: 9 dígitos, solo números y dentro del rango 800000000-999999999
	    if (nit_valor.length !== 9 || !expresionRegular.test(nit_valor)) {
	        $scope.mensajenit = "El NIT debe ser numérico y contener exactamente 9 dígitos";
	    } 
	    else if (parseInt(nit_valor) < 800000000 || parseInt(nit_valor) > 999999999) {
	        $scope.mensajenit = "El NIT debe estar en el rango 800000000 - 999999999";
	    }
	    else {
	        $scope.mensajenit = ""; // Si pasa todas las validaciones, limpia el mensaje
	    }
	};
	
	
	$scope.correoChange = function() {
	    var correo = osm_getValor("correo");
	    var correo_conf = osm_getValor("correo_confirmacion");
	    var correo_valido = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	    
	    if(correo && correo_conf && correo !== correo_conf){
	        $scope.mensajecorreo = "Los correos electrónicos no coinciden";
	    } else if(correo && !correo_valido.test(correo)){
	        $scope.mensajecorreo = "El correo electrónico contiene caracteres no válidos";
	    } else {
	        $scope.mensajecorreo = "";
	    }
	};

	$scope.correoBlur = function() {
	    var correo = osm_getValor("correo");
	    var correo_conf = osm_getValor("correo_confirmacion");
	    var correo_valido = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	    
	    if(!correo_valido.test(correo)){
	        $scope.mensajecorreo = "El correo electrónico contiene caracteres no válidos";
	    } else if(correo_conf && !correo_valido.test(correo_conf)){
	        $scope.mensajecorreo = "El correo de confirmación contiene caracteres no válidos";
	    } else if(correo_conf && correo !== correo_conf){
	        $scope.mensajecorreo = "Los correos electrónicos no coinciden";
	    } else {
	        $scope.mensajecorreo = "";
	    }
	};
	
	$scope.documentoChange = function () {
	    var tipo_doc = osm_getValor("tipo_documento");
	    var num_doc = osm_getValor("numero_documento");
	    var numeros = /^[0-9]+$/;
	    var alfanumerico = /^[a-zA-Z0-9]+$/;
	    
	    if (tipo_doc == 'number:1') { // Cédula de Ciudadanía
	        if (!numeros.test(num_doc) || num_doc.length < 3 || num_doc.length > 11) {
	            $scope.mensajedocumento = "El número de documento debe ser numérico y tener entre 3 y 11 dígitos";
	        } else {
	            $scope.mensajedocumento = "";
	        }
	    } 
	    else if (tipo_doc == 'number:4' || tipo_doc == 'number:3') { // Cédula Extranjería o Pasaporte
	        if (!alfanumerico.test(num_doc) || num_doc.length < 3 || num_doc.length > 20) {
	            $scope.mensajedocumento = "El número de documento debe ser alfanumérico y tener entre 3 y 20 caracteres";
	        } else {
	            $scope.mensajedocumento = "";
	        }
	    }
	};
	
	$scope.telefonoChange = function () {
	    var tel = osm_getValor("numero_telefono");
	    var soloNumeros = /^[0-9]+$/;  // Solo permite dígitos del 0-9 (sin espacios ni caracteres especiales)

	    if (!soloNumeros.test(tel)) {
	        $scope.mensajetelefono = "El teléfono debe ser numérico (sin espacios, guiones u otros caracteres)";
	    } 
	    else if (tel.length < 7 || tel.length > 10) {
	        $scope.mensajetelefono = "El teléfono debe tener entre 7 y 10 dígitos";
	    }
	    else {
	        $scope.mensajetelefono = "";  // Campo válido - limpia el mensaje de error
	    }
	};
	
	$scope.direccionChange = function () {
	    var direccion = osm_getValor("pj_usuario_direccion_notificacion");
	    var caracteresValidos = /^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\s\.,#\-]+$/;
	    
	    if(direccion.length < 6 || direccion.length > 255){
	        $scope.mensajedireccion = "La dirección debe tener entre 6 y 255 caracteres";
	    } else if(!caracteresValidos.test(direccion)){
	        $scope.mensajedireccion = "La dirección contiene caracteres no válidos";
	    } else {
	        $scope.mensajedireccion = "";
	    }
	};
	
	$scope.nombreChange = function() {
	    var nombres = osm_getValor("pj_usuario_nombre");
	    var caracteresValidos = /^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s']+$/;
	    
	    if(nombres.length < 3 || nombres.length > 120){
	        $scope.mensajenombres = "El nombre debe ser alfanumérico, tener entre 3 y 120 caracteres";
	    /*} else if(!caracteresValidos.test(nombres)){
	        $scope.mensajenombres = "El nombre solo puede contener letras y espacios";*/
	    } else {
	        $scope.mensajenombres = "";
	    }
	};
	
	$scope.apellidoChange = function() {
	    var apellidos = osm_getValor("pj_usuario_apellidos");
	    var caracteresValidos = /^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s']+$/;
	    
	    if(apellidos.length < 3 || apellidos.length > 120){
	        $scope.mensajeapellidos = "El apellido debe ser alfanumérico, tener entre 3 y 120 caracteres";
	   /* } else if(!caracteresValidos.test(apellidos)){
	        $scope.mensajeapellidos = "El apellido solo puede contener letras y espacios";*/
	    } else {
	        $scope.mensajeapellidos = "";
	    }
	};
	
	$scope.tokenChange = function () {
		var tokenValido = /^[a-zA-Z0-9]+$/;
		var token = osm_getValor("otp");
		if (!tokenValido.test(token)){
			$scope.mensajetoken = "\n El Token deberá corresponder al generado en Signapp";
		} else {
			$scope.mensajetoken = "";
		}
	}

	$scope.removeFile = function (id_file) {
		$("#" + id_file).find("#caja_archivo_adjunto").val(null);
		$("#trash_" + id_file).addClass("ic-hide");
		osm_setValor("label_" + id_file, "");
	}
	$scope.setTipoUsuario = function (tipoUsuario) {
		$scope.removeFile('formulario_archivo')
		osm_setClassname("tipoFormulario", "select-default form-input ng-pristine ng-untouched ng-valid ng-not-empty");
		if (tipoUsuario == 1) {//sociedad
			$("#nota_info_soc").show();
			$("#nota_info_pnc").hide();
			$("#nota_info_pnnoc").hide();
			$("#formNIT").show();
			$("#archivo_label").text("Certificado de existencia y representación legal:");
			$("#id_tipo_archivo").val(11);
			$("#descripcion_archivo_adj").val("Certificado de Existencia y Representación Legal.");
			$("#empresaSucursales").hide();
			$("#mensaje_footer_pnnoc").hide();
			extensionesAceptadas()
		} else if (tipoUsuario == 2) {//persona natural comerciante
			$("#nota_info_soc").hide();
			$("#nota_info_pnc").show();
			$("#nota_info_pnnoc").hide();
			$("#formNIT").hide();
			$("#archivo_label").text("Certificado de Matrícula Mercantil:");
			$("#id_tipo_archivo").val(69);
			$("#descripcion_archivo_adj").val("Certificado de Matricula Mercantil");
			$("#empresaSucursales").hide();
			$("#mensaje_footer_pnnoc").hide();
			extensionesAceptadas()
			$("#msg_PNC").show();
			osm_setValor("t_form_advertencia", "Persona Natural Comerciante");
			osm_setValor("t_form_final", "Persona Natural Comerciante");
			$scope.abrir_advertencia();
		} else if (tipoUsuario == 3) {//persona natural no comerciante
			$("#nota_info_soc").hide();
			$("#nota_info_pnc").hide();
			$("#nota_info_pnnoc").show();
			$("#formNIT").hide();
			$("#archivo_label").text("Prueba de ser controlante en sociedad admitida:");
			$("#id_tipo_archivo").val(52);
			$("#descripcion_archivo_adj").val("Prueba de ser controlante en sociedad admitida.");
			$("#mensaje_footer_pnnoc").show();
			extensionesAceptadas()
			$("#msg_PNC").hide();
			osm_setValor("t_form_advertencia", "Persona Natural NO Comerciante");
			osm_setValor("t_form_final", "Persona Natural NO Comerciante");
			$scope.abrir_advertencia();
		} else if(tipoUsuario == 5){
			$("#archivo_label").text("Certificado de existencia y representación legal:");
			$("#id_tipo_archivo").val(11);
			$("#descripcion_archivo_adj").val("Certificado de existencia y representación legal.");
			$("#empresaSucursales").hide();
			$("#formNIT").show();
			$("#mensaje_footer_pnnoc").hide();
			extensionesAceptadas()
		} else {
			$("#mensaje_footer_pnnoc").show();
			$("#label_identificador_deudor").text("Representante Legal");
		}
	}
});

$(document)
	.ready(() =>{
		var sino = [
			{
			  "id": "0",
			  "nombre": "No",
			  "estado": "A",
			  "valueid": 0,
			  "colorletra": "#000000",
			  "colorfondo": "#FFFFFF",
			  "javaClass": "com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista",
			  "posicion": null
			},
			{
			  "id": "1",
			  "nombre": "Si",
			  "estado": "A",
			  "valueid": 1,
			  "colorletra": "#000000",
			  "colorfondo": "#FFFFFF",
			  "javaClass": "com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista",
			  "posicion": null
			}
		  ];
			// var sino = jsonrpc._(
			// 	"solicitudNearSociedadServicio.obtenerListaSiNO")().list;

			sino
				.forEach(function (i) {
					setButtonOptions(i, "solicitante_controlante_sociedad_reorganizacio");
				});
			consultarDepartamentos ();
});

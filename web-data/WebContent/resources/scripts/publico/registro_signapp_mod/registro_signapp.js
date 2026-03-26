var TOTAL_ARCHIVOS_ADJUNTOS = 0;
var ARCHIVOS_ADJUNTADOS = 0;
function adjuntarArchivos() {

    var options = {
        success: fin_carga_arcihvo,
        async: true,
    };

    $("#formulario_completo")
        .find(".formulario_archivo")
        .each(
            function (index, value) {
                if ($(value).find("#caja_archivo_adjunto").first()[0].files[0] != undefined) {
                    // setTimeout(actualizarProgreso(), 2000)
                    $(value).ajaxSubmit(options);
                }
            });

    $("#formulario_completo").find(".formulario_archivo").each(
        function (index, value) {
            var actualizar_id = $(value).find("#id_archivo_adjunto").val();
            var actualizar_descr = $(value)
                .find("#descripcion_archivo_adj").val();

            var actualizar_encoding = jsonrpc._(
                "archivosAdjJsonServicio.actualizarDescripcion")(
                    Number(actualizar_id), actualizar_descr.toString());
        });
}

function removeFile(id_file) {
    $("#" + id_file).find("#caja_archivo_adjunto").val(null);
    $("#trash_" + id_file).addClass("ic-hide");
    osm_setValor("label_" + id_file, "");
}
$(document).ready(function () {
    $("#div_exito").hide();
    $("#div_error").hide();
    $("#area_botones").hide();
    $("#nota_info").hide();
    $("#nota_error").hide();
    
    $("#nombres").on("input", function(){
    	var value = $(this).val();
    	value = value.normalize("NFD").replace(/[\u0300-\u0302\u0304-\u036f]|[^a-zA-Z\s\u0303]/g, "");
    	$(this).val(value);
    });
    
    $("#apellidos").on("input", function(){
    	var value = $(this).val();
    	value = value.normalize("NFD").replace(/[\u0300-\u0302\u0304-\u036f]|[^a-zA-Z\s\u0303]/g, "");
    	$(this).val(value);
    });
    
    $("#archivo_documento_frontal").find(
        "#caja_archivo_adjunto").change(function (e) {
        	try {
        		var fileName = e.target.files[0].name;
        		var extension = fileName.split(/\./g).pop().toLowerCase();
                if (validarExtensionArchivo(extension)) {
                	osm_setValor("label_archivo_documento_frontal", fileName);
                	$("#trash_archivo_documento_frontal").removeClass("ic-hide");            	
                } else {
                	osm_setValor("label_archivo_documento_frontal", "");
                	$("#trash_archivo_documento_frontal").addClass("ic-hide");
                	osm_alert_danger("Acaba de ingresar un archivo con extensi\u00f3n no v\u00e1lida ' ." + extension + " ' \n\n" +
            				"Por favor, ingrese el archivo en formato .jpg, .jpeg o .png", "Archivo no v\u00e1lido", 
            			function(){
                			$("#archivo_documento_frontal").find("#caja_archivo_adjunto").val("");
                		}
                	);
                }
        	} catch (e) {}
        });
    $("#archivo_documento_posterior").find(
        "#caja_archivo_adjunto").change(function (e) {
        	try {
	            var fileName = e.target.files[0].name;
	            var extension = fileName.split(/\./g).pop().toLowerCase();
	            if (validarExtensionArchivo(extension)) {
	            	osm_setValor("label_archivo_documento_posterior", fileName);
	            	$("#trash_archivo_documento_posterior").removeClass("ic-hide");
	            } else {
	            	osm_setValor("label_archivo_documento_posterior", "");
	            	$("#trash_archivo_documento_posterior").addClass("ic-hide");
	            	osm_alert_danger("Acaba de ingresar un archivo con extensi\u00f3n no v\u00e1lida ' ." + extension + " ' \n\n" +
            				"Por favor, ingrese el archivo en formato .jpg, .jpeg o .png", "Archivo no v\u00e1lido", 
            			function(){
	            			$("#archivo_documento_posterior").find("#caja_archivo_adjunto").val("");
                		}
                	);
	            }
        	} catch (e) {}
        });
    $("#archivo_foto_selfie").find(
    	"#caja_archivo_adjunto").change(function (e) {
    		try {
		        var fileName = e.target.files[0].name;
		        var extension = fileName.split(/\./g).pop().toLowerCase();
		        if (validarExtensionArchivo(extension)) {
		        	osm_setValor("label_archivo_foto_selfie", fileName);
		        	$("#trash_archivo_foto_selfie").removeClass("ic-hide");
		        } else {
		        	osm_setValor("label_archivo_foto_selfie", "");
		        	$("#trash_archivo_foto_selfie").addClass("ic-hide");
		        	osm_alert_danger("Acaba de ingresar un archivo con extensi\u00f3n no v\u00e1lida ' ." + extension + " ' \n\n" +
            				"Por favor, ingrese el archivo en formato .jpg, .jpeg o .png", "Archivo no v\u00e1lido", 
            			function(){
		        			$("#archivo_foto_selfie").find("#caja_archivo_adjunto").val("");
                		}
                	);
		        }
    		} catch (e) {}
    });
    
    
    
    var tiposDoc = _.filter(
        jsonrpc._("solicitudEnrolamiento.obtenerTiposDocumento")().list,
        function (o) {
            return o.id == 1 || o.id == 3 || o.id == 4;
        }
    );
    tiposDoc.forEach(function (i) {
        $("#tipoDocumento").append($("<option>", {
            value: i.id,
            text: i.nombre,
        }));
    });
    
    $("#btn_reintentar_vn").click(function() {
    	cerrar_modal_reintento();
    	
    	try {
    		
    		var carga = osm_getValor("id_carga_base");
    		
    		$("#seccion_carga").show();
    		$("#formulario_solicitud").hide();
    		
    		cargarArchivos(carga);						
    	} catch (e) {
    		alert("Error al reintentar carga!");
    		osm_unblock_window();
    		osm_ocultarLoader();
    	}
    	
    } );
    $("#btn_cancelar_reintento_vn").click(function() {
    	cerrar_modal_reintento();
    	osm_unblock_window();
    	osm_ocultarLoader();
    } );
});


function validarSolicitud(solicitud) {
    // validar campos

    console.log(solicitud);

    skin_init_validar();
    skin_validar("nombres", "TEXTO", true);
    skin_validar("apellidos", "TEXTO", true);
    skin_validar("tipoDocumento", "TEXTO", true);
    skin_validar("numeroDocumento", "TEXTO", true);
    skin_validar("fechaExpedicion", "TEXTO", true);
    skin_validar("sexo", "TEXTO", true);
    skin_validar("fechaNacimiento", "TEXTO", true);
    skin_validar("correo", "TEXTO", true);
    skin_validar("correo_confirmar", "TEXTO", true);
    skin_validar("login", "TEXTO", true);
    skin_validar("celular", "TEXTO", true);
    skin_validar("celular_confirmar", "TEXTO", true);
    if(validarCelular()==false){
    	SK_ERROR_VALIDACION = true;
    }
    if(validarCorreo()==false){
    	SK_ERROR_VALIDACION = true;
    }
    if(validarLogin(osm_getValor("login"))==false){
    	SK_ERROR_VALIDACION = true;
    }    
    if ($("#archivo_documento_frontal").find("#caja_archivo_adjunto").first()[0].files[0] == undefined) {
        SK_ERROR_VALIDACION = true;
        $("#label_archivo_documento_frontal").addClass("ERR_VALIDAR");
    }
    if ($("#archivo_documento_posterior").find("#caja_archivo_adjunto").first()[0].files[0] == undefined) {
        SK_ERROR_VALIDACION = true;
        $("#label_archivo_documento_posterior").addClass("ERR_VALIDAR");
    }
    if ($("#archivo_foto_selfie").find("#caja_archivo_adjunto").first()[0].files[0] == undefined) {
        SK_ERROR_VALIDACION = true;
        $("#label_archivo_foto_selfie").addClass("ERR_VALIDAR");
    }
    
    if (SK_ERROR_VALIDACION) {
        osm_alert(
            "Existen datos sin diligenciar o inv\u00E1lidos.\n\nPor favor, verifique los campos resaltados."
        );
        $(".put").removeClass("on");
        $(".ERR_VALIDAR").addClass("on");
        osm_unblock_window();
        osm_ocultarLoader();
        return false;
    }
    
    TOTAL_ARCHIVOS_ADJUNTOS = 0;
    
	$("#formulario_solicitud")
		.find(".formulario_archivo")
		.each(function(index, value) {
			if ($(value).find("#caja_archivo_adjunto").first()[0].files[0] != undefined) {

					TOTAL_ARCHIVOS_ADJUNTOS++;
				}
			});
	$("#total_adjuntos").text(TOTAL_ARCHIVOS_ADJUNTOS);
	$("#seccion_carga").show();
	$("#formulario_solicitud").hide();

    return true;
};
function generarSolicitud() {
    var solicitud = {
    	idcarga: osm_getValor("id_carga_base"),
		tipo_identificacion: 10,
//		tipo_identificacion_nombre: "NIT",
//		numero_identificacion: osm_getValor("nit"),
		login : osm_getValor("login"),
		correo_electronico: osm_getValor("correo"),
		datos_representante:{
			relacion_sociedad: 1,
			tipo_identificacion: Number(osm_getValor("tipoDocumento")),
			tipo_identificacion_nombre: osm_getValor("tipoDocumento"),
			numero_identificacion: osm_getValor("numeroDocumento"),
			correo_electronico: osm_getValor("correo"),
			nombres: osm_getValor("nombres"),
	        apellidos: osm_getValor("apellidos"),
	        fecha_expedicion: osm_getValor("fechaExpedicion"),
	        sexo: osm_getValor("sexo"),
	        lugar_nacimiento: "No Aplica",
	        fecha_nacimiento: osm_getValor("fechaNacimiento"),
	        celular: osm_getValor("celular")
		}
    }
    return solicitud;
}

function enviarSolicitud() {
    if (validarSolicitud()) {
        console.log("solicitud validada")
        var existecaptcha = $('[name=g-recaptcha-response]').length > 0;
							
        var rcp = osm_trimToNull( $('[name=g-recaptcha-response]').val() );
        
        if(existecaptcha && rcp == null ){
        	osm_alert_dangerE("Marque la casilla de 'No soy un robot'", "", function() {
			});
        	return false;
        }
        
        //consumir servicio para enviar datos
        try {
        	
        	var carga = osm_getValor("id_carga_base");
        	
			if (!carga) {
				jsonrpc._("solicitudEnrolamiento.crearCarga")(cargarArchivos, true);
			} else {
				cargarArchivos(carga);				
			}
        	
        }catch (e) {
        	console.log("ha ocurrido un error");
        	console.log(e);
        	alert("Error al consulta datos!");
        	$("#div_error").show();
		}
		
    }
}

function cargarArchivos(id_carga) {
	try {
		osm_setValor("id_carga_base", id_carga);
		
		jsonrpc._("solicitudEnrolamiento.borrarAdjuntos")(id_carga);
		resetBarraCarga();
		
		enviarArchivoFormulario("archivo_foto_selfie", id_carga, 6);
		enviarArchivoFormulario("archivo_documento_frontal", id_carga, 7);
		enviarArchivoFormulario("archivo_documento_posterior", id_carga, 8);		
	} catch (e) {
		console.log("ha ocurrido un error");
    	console.log(e);
    	alert("Error al cargar archivos!");
    	$("#div_error").show();
	}
}

function crearSolicitud() {
	
	try {
		
		var solicitud = generarSolicitud();
        console.log("solicitud");
        console.log(solicitud);
		
		jsonrpc._("solicitudEnrolamiento.crearSolicitud")(
			function(respuesta){
				console.log("rta solicitud");
				console.log(respuesta);//rta
				if(respuesta && respuesta.exitoso){
					console.log("respuesta solicitud");
					console.log(respuesta.info);//id_carga
					
					document.getElementById("textBar").innerHTML = "Carga exitosa!";
					$("#div_exito").show();
					$("#area_botones").show();
					
				}
			}, solicitud);
		
	} catch (e) {
		console.log("ha ocurrido un error");
    	console.log(e);
    	alert("Error al consulta datos!");
    	$("#div_error").show();
	}
	
}

function validarCorreo(){
	var correo=osm_getValor("correo");
	var correo_confirmar=osm_getValor("correo_confirmar");
	if(correo == correo_confirmar){
		$("#div_confirmar_correo").hide();
		return true;
	}else{
		$("#div_confirmar_correo").show();
		return false;
	}
}
function validarCelular(){
	var celular=osm_getValor("celular");
	var celular_confirmar=osm_getValor("celular_confirmar");
	if(celular == celular_confirmar){
		$("#div_confirmar_celular").hide();
		return true;
	}else{
		$("#div_confirmar_celular").show();
		return false;
	}
}
function loginOnChange(){
	if(validarLogin(osm_getValor("login"))==false){
		$("#login_error").show();
	}else{
		$("#login_error").hide();
	}
}
function validarLogin(login){
	var login_disponible = jsonrpc._("solicitudEnrolamiento.loginDisponible")(login);
	
	if(!login_disponible){
		return false;
	}
	return true;
}

function validarExtensionArchivo(extension) {
	if (extension == 'jpg' || extension == 'jpeg' || extension == 'png') {
		return true;
	} else {
		return false;
	}
	
}

function enviarArchivoFormulario(id, id_carga, id_tipo_archivo){
	var id_archivo_adjunto = jsonrpc._(
			"solicitudEnrolamiento.obtenerSiguiente"
	)();
	if (validarS3Adjuntos()) {
		var file = $("#"+id).find("#caja_archivo_adjunto")[0].files[0];
		var descripcion = $("#"+id).find("#descripcion_archivo_adj").val();
		descripcion = encodeURIComponent(descripcion);
		var adjunto = {
				id_archivo_adjunto: id_archivo_adjunto,
				id_carga: id_carga,
				descripcion: descripcion,
				interno: "N",
				estado: "A",
				id_tipo_archivo: id_tipo_archivo 
		};
		
		enviarAdjuntoS3(file, adjunto, actualizarBarraCarga, function () {});
	} else {
		$("#"+id).find("#id_archivo_adjunto").val(id_archivo_adjunto);
		$("#"+id).find("#id_carga").val(id_carga);
		var form_data = new FormData();
		form_data.append(
				"caja_archivo_adjunto",
				$("#"+id).find("#caja_archivo_adjunto")[0].files[0]
		);
		form_data.append("id_archivo_adjunto", id_archivo_adjunto);
		form_data.append("id_carga", id_carga);
		form_data.append("id_tipo_archivo", id_tipo_archivo);
		form_data.append(
				"descripcion_archivo_adj",
				$("#"+id).find("#descripcion_archivo_adj").val()
		);
		
	    $.ajax({
			method : "POST",
			url : "../adjunto.subirarchivo",
			contentType : "application/json;charset=iso-8859-1",
			data : form_data,
			processData: false,
			contentType: false,
			cache: false,
		}).done(function(msg) {
			console.log(msg);
			actualizarBarraCarga();
		});
	}
}

function actualizarBarraCarga() {
	var elem = document.getElementById("barra_contenido");
	ARCHIVOS_ADJUNTADOS++;
	var num = ARCHIVOS_ADJUNTADOS * 100 / TOTAL_ARCHIVOS_ADJUNTOS;
	num = num.toFixed(0)
	elem.style.width = num + '%';
	document.getElementById("archivos_adjuntados").innerHTML = ARCHIVOS_ADJUNTADOS;
	
	if (num >= 100) {
		validarCargueArchivos();
	}
}

function validarCargueArchivos() {
	var idcarga = osm_getValor("id_carga_base");
	var adjuntos = jsonrpc._("solicitudEnrolamiento.obtenerArchivosAdjuntosPorCarga")(idcarga).list;
	
	if (adjuntos && adjuntos.length == 3) {
		crearSolicitud();
	} else {
		$("#seccion_carga").hide();
		$("#formulario_solicitud").show();
		abrir_modal_reintento();
	}
}

function abrir_modal_cargue() {
	osm_setVisible("vn_creacion_solicitud", true, true);
	osm_ocultarSelects("bodyContent");
	$("body").css("overflow", "hidden");
	$('html, body').animate({
		 scrollTop: $("#bs-example-navbar-collapse-1").offset().top
		 }, 0);
}

function cerrar_modal_cargue() {
	osm_setVisible("vn_creacion_solicitud", false);
	osm_mostrarSelects("bodyContent");
	$("body").css("overflow", "auto");
}

function abrir_modal_reintento() {
	osm_setVisible("vn_error_cargue", true, true);
	osm_ocultarSelects("bodyContent");
	$("body").css("overflow", "hidden");
	$('html, body').animate({
		 scrollTop: $("#bs-example-navbar-collapse-1").offset().top
		 }, 0);
}

function cerrar_modal_reintento() {
	osm_setVisible("vn_error_cargue", false);
	osm_mostrarSelects("bodyContent");
	$("body").css("overflow", "auto");
}

function resetBarraCarga() {
	var elem = document.getElementById("barra_contenido");
	ARCHIVOS_ADJUNTADOS = 0;
	elem.style.width = '0%';
	document.getElementById("archivos_adjuntados").innerHTML = ARCHIVOS_ADJUNTADOS;
}
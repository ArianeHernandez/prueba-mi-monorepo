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
    
    $("#archivo_documento_frontal").find(
        "#caja_archivo_adjunto").change(function (e) {
            var fileName = e.target.files[0].name;
            osm_setValor("label_archivo_documento_frontal", fileName);
            $("#trash_archivo_documento_frontal").removeClass("ic-hide");
        });
    $("#archivo_documento_posterior").find(
        "#caja_archivo_adjunto").change(function (e) {
            var fileName = e.target.files[0].name;
            osm_setValor("label_archivo_documento_posterior", fileName);
            $("#trash_archivo_documento_posterior").removeClass("ic-hide");
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
});


function validarSolicitud(solicitud) {
    // validar campos

    console.log(solicitud);

    skin_init_validar();
    skin_validar("nit", "TEXTO", true);
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
		tipo_identificacion: 10,
		tipo_identificacion_nombre: "NIT",
		numero_identificacion: osm_getValor("nit"),
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
        
        var solicitud = generarSolicitud();
        console.log("solicitud");
        console.log(solicitud);

        //consumir servicio para enviar datos
        try {
		jsonrpc._("solicitudEnrolamiento.crearSolicitud")(
			function(respuesta){
				console.log("rta solicitud");
				console.log(respuesta);//rta
				if(respuesta && respuesta.exitoso){
					console.log("respuesta solicitud");
					console.log(respuesta.info);//id_carga
					//cargar archivos
					var id_carga=respuesta.info;
					
					enviarArchivoFormulario("archivo_documento_frontal", id_carga, 7);
					enviarArchivoFormulario("archivo_documento_posterior", id_carga, 8);
					
				}
			}, solicitud);
        }catch (e) {
        	console.log("ha ocurrido un error");
        	console.log(e);
        	alert("Error al consulta datos!");
        	$("#div_error").show();
		}
		
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
function enviarArchivoFormulario(id, id_carga, id_tipo_archivo){
	var id_archivo_adjunto = jsonrpc._(
			"solicitudEnrolamiento.obtenerSiguiente"
	)();
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
		var elem = document.getElementById("barra_contenido");
		ARCHIVOS_ADJUNTADOS++;
		var num = ARCHIVOS_ADJUNTADOS * 100 / TOTAL_ARCHIVOS_ADJUNTOS;
		num = num.toFixed(0)
		elem.style.width = num + '%';
		document.getElementById("archivos_adjuntados").innerHTML = ARCHIVOS_ADJUNTADOS;
		
		if (num >= 100) {
			document.getElementById("textBar").innerHTML = "Carga exitosa!";
			$("#div_exito").show();
			$("#area_botones").show();
		}
	});
}

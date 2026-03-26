function validar_login() {

	var userid = osm_trimToEmpty(osm_getValor("auth_username"));
	var password = osm_trimToEmpty(osm_getValor("auth_password"));
	
	var existecaptcha = $('[name=g-recaptcha-response]').length > 0;
	
	var rcp = osm_trimToNull( $('[name=g-recaptcha-response]').val() );
	
	if (userid == "" || password == "") {

		osm_alert_dangerE("Requiere usuario y contrase\u00F1a", "", function() {
			osm_setFoco("auth_username");
		});

		return false;
	}

	
	if(existecaptcha && rcp == null ){
		
		osm_alert_dangerE("Marque la casilla de 'No soy un robot'", "", function() {
		});
		
		return false;
	}
	
	var clave_cifrada = secure_encrypt(password);
	
	// mirar si ya ha intentado mas de las veces permitidas
	
	var bloqueado = jsonrpc._("usuarioNegocioServicio.estaUsuarioBloqueado")(userid, clave_cifrada);

	// if (bloqueado == null) {

	// 	osm_alert_dangerE("El usuario o la contrase\u00F1a son inv\u00E1lidos. Por favor comun\u00EDquese con el Administrador del sistema.", "", function() {
	// 		osm_setFoco("auth_username");
	// 	});

	// 	return false;
	// }

	if (bloqueado) {

		osm_alert_dangerE("La cuenta ha sido bloqueada. Por favor comun\u00EDquese con el Administrador del sistema.", "", function() {
			osm_setFoco("auth_username");
		});

		return false;
	}
	

	return true;
}

function descargaPlantilla(id_plantilla) {
	var fake_form_url = "../DescargaArchivoServlet";

	var $fake_form = $("<form>", {
		action : fake_form_url,
		method : "post",
	});

	$("<input>").attr({
		name : "id_archivo",
		value : id_plantilla,
	}).appendTo($fake_form);

	$(document.body).append($fake_form);

	$fake_form.submit();
}

function verImagen(idImagen){
	$('#'+idImagen).show();
}

function ocultarImagen(idImagen){
	$('#'+idImagen).hide();
}


// ------------------------------------------------

$(function() {
	osm_setValor("auth_username", "");
	osm_setValor("auth_password", "");
	osm_setFoco("auth_username");

	var sendForm = function() {

		if (!validar_login()) {
			return false;
		}
		
		var clave_cifrada = secure_encrypt(osm_getValor("auth_password"));
		osm_setValor("auth_password", clave_cifrada );
		osm_enviarFormulario('frm_ingreso');
		return false;
	};

	$("#auth_username, #auth_password").keypress(function(e) {
	    if(e.which == 13) {
	        
	    	if (!validar_login()) {
				return;
			}
			
			osm_enviarFormulario('frm_ingreso');
	    }
	});
	
	

	$("#btn_login").click(sendForm);

	$("#login_alert").hide();

	window.setTimeout(function() {
		$("#login_alert").fadeIn(200);
	}, 1000);

	window.setTimeout(function() {
		$("#login_alert").fadeOut(1000);
	}, 30000);

});

// -----------------------------------------------

window.name = "w_datasuite";

try {
	localStorage.clear();
} catch (e) {
}

osm_listen("unload", window, function() {

	try {

		if (localStorage.length > 0) {
			window.name = "w_datasuite_die";
		}

	} catch (e) {
	}
});

window.setInterval(function() {
	sessionStorage.LAJK = new Date().getTime();
}, 100)
window.addEventListener("DOMContentLoaded", inicializarPagina);
//window.addEventListener("load", inicializarPagina);

function findGetParameter(parameterName) {
    var result = null,
        tmp = [];
    location.search
        .substr(1)
        .split("&")
        .forEach(function (item) {
          tmp = item.split("=");
          if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
        });
    return result;
}

function inicializarPagina() {
	$("body").css("overflow", "hidden");
	console.log("Notification permission: " + Notification.permission);
	
	if (Notification.permission !== "denied") {
		Notification.requestPermission();
	}
	
	var markup = findGetParameter("markup");
    if(markup){
		$("#solicitud_tab").click();
		window.location.hash = "carousel-solicitud";
	} else {
		verVentanaVideos();
	}
}

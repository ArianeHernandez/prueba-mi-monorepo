function validar_login() {

	var userid = osm_trimToEmpty(osm_getValor("auth_username"));
	var password = osm_trimToEmpty(osm_getValor("auth_password"));

	if (userid == "" || password == "") {

		osm_alert_dangerE("Requiere usuario y contraseña", "", function() {
			osm_setFoco("auth_username");
		});

		return false;
	}

	return true;
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
	
	window.setTimeout(function(){
		$("#login_alert").fadeIn(200);
	}, 1000);
	
	window.setTimeout(function(){
		$("#login_alert").fadeOut(1000);
	}, 30000);

});

// -----------------------------------------------

window.name = "w_creadatos";

try {
	localStorage.clear();
} catch (e) {
}

osm_listen("unload", window, function() {

	try {

		if (localStorage.length > 0) {
			window.name = "w_creadatos_die";
		}

	} catch (e) {
	}
});

window.setInterval(function() {
	sessionStorage.LAJK = new Date().getTime();
}, 100)


function aceptaTerminos(){

	var exitoso = jsonrpc._("credencialJsonServicio.aceptaTerminosCondiciones")();

	if(exitoso){
		osm_go("inicio/0.do");
	}else{
		osm_alert("No se pudo realizar la operación. Intente nuevamente");
		return;
		
	}
	
}


function noAceptaTerminos(){
	
	if (confirm("Esta seguro de que NO desea aceptar los terminos?")) {
	
		osm_go("inicio/0.pub");
	}
	
	
}

function validarToken(){
	try {
		var token = osm_getValor("token_signapp");
		var resp =  jsonrpc._("signAppServicio.validarToken")(token);
		console.log(resp);
		
		if(osm_esVacio(token) || resp == undefined){
			osm_alert("El token de SignApp es un campo obligatorio.");
			return false;
		}else{
			if(!resp.valid){
				if(resp.msg.includes("Token invalido")){
					alert("Token inválido");
				}else{
					alert(resp.msg);
				}
				return false;
			}else{
				return true;
			}
		}
	} catch (e) {
		console.error(e);
		return false;
	}
}

function aceptarTerminosValidarOTP(){
	if(validarToken()){
		aceptaTerminos();
	}
}


//------------------------------------------------

function p_load()
{
	
}


osm_listen("load", window, p_load);

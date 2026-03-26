
function validar_login(){
	
	var clienteid= osm_getValor("auth_clienteid");
	var userid = osm_getValor("auth_username");
	var password = osm_getValor("auth_password");
	
	if(userid=="" || password=="" || clienteid == "")
	{
		var tipo_usuario = jsonrpc._("contenido.obtenerContenido")("General", "CLIENTE");
		
		if(tipo_usuario == null){
			tipo_usuario = "Cliente";
		}
		
		osm_alert("Requiere id de " + tipo_usuario +", usuario y contraseña");
		return false;
	}
	var user = osm_getObjeto("auth_username");
	user.name = "";
	
	var usuarioCliente = osm_getObjeto("usuario_cliente");
	usuarioCliente.name = "auth_username";
	usuarioCliente.value = userid + "|" + clienteid;
	
	return true;
}

//------------------------------------------------

function p_load()
{
	osm_setValor("auth_username","");
	osm_setValor("auth_password","");
	osm_setFoco("auth_username");
}

osm_listen("load", window, p_load);

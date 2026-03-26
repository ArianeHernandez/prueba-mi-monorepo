

function validar_datos(){
	
	
	var passwordFirma = osm_getValor("passwordFirma");
	var conf_passwordFirma = osm_getValor("conf_passwordFirma");
	
	if(osm_esVacio(passwordFirma) || osm_esVacio(conf_passwordFirma))
	{
		osm_alert("Requiere el password y la confirmación del password");
		return false;
	}
	
	if(passwordFirma != conf_passwordFirma)
	{
		osm_alert("La clave y la confirmación de la clave no coinciden.");
		return false;
	}
	else if(passwordFirma.length > 7){
		osm_alert("La longitud del password no debe sobrepasar los 7 caracteres");
		return false;
	}
	
	osm_enviarFormulario("form_gen_firma", false);

	osm_unblock_window();
	
	return true;
	
}

//------------------------------------------------

function p_load()
{
	osm_setFoco("passwordFirma");
}

osm_listen("load", window, p_load);

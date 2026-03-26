function p_load(){
	
	try{
		var jj = 1;
		var fila = osm_getObjeto("fila_uri_" + jj);
		while( fila!=null )
		{
			fila.valor = jj;
			fila.onclick = function(){
				osm_setValor("id_uri", osm_getValor("id_uri_" + this.valor) );
				osm_enviarFormulario("formEdicion");
			}
			
			jj++;
			fila = osm_getObjeto("fila_uri_" + jj);
		}
	}catch(_e){}
}

osm_listen("load", window, p_load);

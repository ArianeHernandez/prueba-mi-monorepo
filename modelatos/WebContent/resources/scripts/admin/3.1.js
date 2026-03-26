
function p_load(){
	

	try{
		var jj = 1;
		var fila = osm_getObjeto("fila_listavalor_" + jj);
		while( fila!=null )
		{
			fila.valor = jj;
			fila.onclick = function(){
				osm_setValor("id_lista_valores", osm_getValor("id_lista_valores_" + this.valor) );
				osm_enviarFormulario("form_listasvalores");
			}
			
			jj++;
			fila = osm_getObjeto("fila_listavalor_" + jj);
		}
	}catch(_e){}
}

osm_listen("load", window, p_load);


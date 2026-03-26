var NUM_ELEMENTO = 1;

function page_validarGuardar(){
	
	// Verifica que el nombre no se vacio
	
	if(osm_esVacio( osm_getValor("ListaValores.nombre") )){
		osm_alert("El nombre de la lista de valores no puede ser vacio");
		osm_setFoco("ListaValores.nombre");
		return false;
	}
	
	// Verifica que el tipo no se vacio
	
	if(osm_esVacio( osm_getValor("ListaValores.id_tipocampo") )){
		osm_alert("Se debe seleccionar el tipo de datos de la lista de valores");
		osm_setFoco("ListaValores.id_tipocampo");
		return false;
	}
	
	// -----
	
	var ii = 1;
	var obj = osm_getObjeto("bloqueCampoVisible_" + ii);
	while( obj != null )
	{
		var valor = osm_getValor("bloqueCampoVisible_" + ii);
		if(valor=="true")
		{
			if( osm_esVacio( osm_getValor("ValorLista:" + ii + ".id") ) ){
				osm_alert("Debe ingresar el identificador del valor");
				osm_setFoco("ValorLista:" + ii + ".id");
				return false;
			}
			
			if( osm_esVacio( osm_getValor("ValorLista:" + ii + ".nombre") ) ){
				osm_alert("Debe ingresar el nombre del valor");
				osm_setFoco("ValorLista:" + ii + ".nombre");
				return false;
			}
		}
		
		// --
		
		ii++;
		obj = osm_getObjeto("bloqueCampoVisible_" + ii);
	}
	
	return true;
}

function page_eliminarElemento(ids)
{
	osm_setVisible('BloqueCampo_' + ids, false);
	osm_setValor("bloqueCampoVisible_" + ids, "false");
	
	osm_setValor('ValorLista:' + ids + '.id', '');
	osm_setValor('ValorLista:' + ids + '.nombre', '');
}

function page_verElemento(ids)
{
	osm_setVisible('BloqueCampo_' + ids, true, true);
	
	var nn = 'ValorLista:' + ids + '.id';
	osm_setValor(nn, '');
	osm_setFoco(nn);
}

function page_agregarElemento(){
	osm_setVisible('BloqueCampo_' + NUM_ELEMENTO, true, true);
	osm_setValor("bloqueCampoVisible_" + NUM_ELEMENTO, "true");
	page_verElemento(NUM_ELEMENTO);
	
	NUM_ELEMENTO++;
}


function p_load(){
	NUM_ELEMENTO = parseInt(osm_getValor("var.num_elemento"));
	osm_listen("click", "btn_agregar_valor", page_agregarElemento);
}

osm_listen("load", window, p_load);

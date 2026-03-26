var NUM_ELEMENTO = 1;

function page_marcar_llave_primaria(obj, idbPos)
{
	if( osm_getMarca(obj) ){
		osm_setMarca("Campo:"+idbPos+".obligatorio", true);
		osm_setMarca("Campo:"+idbPos+".unico", true);
		osm_setVisibilidad("BLOQUE1:"+idbPos, false);
		osm_setVisibilidad("BLOQUE2:"+idbPos, false);
	}
	else{
		osm_setVisibilidad("BLOQUE1:"+idbPos, true);
		osm_setVisibilidad("BLOQUE2:"+idbPos, true);
	}
}

function page_verElemento(ids)
{
	osm_setVisible('BloqueCampo_' + ids, true, true);
	
	var nn = 'Campo:' + ids + '.nombre';
	osm_setValor(nn, '');
	osm_setFoco(nn);
}

function page_eliminarElemento(ids)
{
	osm_setVisible('BloqueCampo_' + ids, false);
	osm_setValor("bloqueCampoVisible_" + ids, "false");
	var cc = osm_getObjeto('Campo:' + ids + '.nombre');
	cc.value = "";
}

function page_cambioMultiplicidad(obj,nfcampo)
{
	var vcontent = document.getElementById(nfcampo + ".visualizacion");
	var vcontent2 = document.getElementById(nfcampo + ".obligatorio");

	osm_setVisible(nfcampo + ".visualizacion_Block",true,true);
	osm_setVisible(nfcampo + ".obligatorio_Block",true,true);

	if (obj.value == "1...N")
	{
		osm_setVisible(nfcampo + ".visualizacion_Block", false);
		vcontent.checked = false;
		osm_setVisible(nfcampo + ".obligatorio_Block", false);
		vcontent2.checked = false;
	}
}

function page_cambioActualizable(obj,nfcampo)
{
	var vcontent = document.getElementById(nfcampo + ".endpoint");

	if (obj.checked)
	{
		osm_setVisible(nfcampo + ".endPoint_Block", true);
		vcontent.value = "";
	}else{
		osm_setVisible(nfcampo + ".endPoint_Block", false);
		vcontent.value = "";
	}
}

function nombreUnico(){
	
	var obj = osm_getObjeto("Estructura.nombre");
	var nombreEstructura = osm_getValor("Estructura.nombre");
	var id_modelo_activo = osm_getValor("id_modelo_activo");
	
	if(!osm_getValor("Estructura.id_estructura")){
		
		var estructura = jsonrpc._("estructuraServicio.obtenerEstructuraPorNombre")(nombreEstructura, id_modelo_activo);
		if (estructura != null){ 
			osm_alert("El nombre '" + nombreEstructura + "' ya existe, por favor digite otro.");
			osm_setValor("Estructura.nombre", "");
			osm_setFoco("Estructura.nombre");
		}
	}	
}

function page_agregarElemento(){
	osm_setVisible('BloqueCampo_' + NUM_ELEMENTO, true, true);
	osm_setValor("bloqueCampoVisible_" + NUM_ELEMENTO, "true");
	page_verElemento(NUM_ELEMENTO);
	
	NUM_ELEMENTO++;
}

function page_validarTipoDato(obj, posicion){
	
	if (obj.value == "tc_8") {
		osm_setVisible("Campo." + posicion + ".tipo_archivo", true, true);
	} else {
		osm_setVisible("Campo." + posicion + ".tipo_archivo", false);
	}
	
}


function p_load(){

	osm_setFoco("Estructura.nombre");

	NUM_ELEMENTO = parseInt(osm_getValor("var.num_elemento"));
	osm_listen("click", "btn_agregar_campo", page_agregarElemento);
	
	// -------
	
	var ii = 1;
	var obj = osm_getObjeto("Campo:" + ii + ".llaveprimaria");
	
	while( obj != null )
	{
		obj.ref = ii;
		obj.onchange = function(){
			if(this.checked){
				osm_seleccionar("Campo:" + this.ref + ".obligatorio", true);
			}		
		}
		
		// --
		
		ii++;
		obj = osm_getObjeto("Campo:" + ii + ".llaveprimaria");
	}
	
	// -------
	
	var ii = 1;
	var obj = osm_getObjeto("Campo:" + ii + ".unico");
	
	while( obj != null )
	{
		obj.ref = ii;
		obj.onchange = function(){
			if(this.checked){
				osm_seleccionar("Campo:" + this.ref + ".obligatorio", true);
			}
		}
		
		// --
		
		ii++;
		obj = osm_getObjeto("Campo:" + ii + ".unico");
	}
	
	// -------
	
	var ii = 1;
	var obj = osm_getObjeto("Campo:" + ii + ".obligatorio");
	
	while( obj != null )
	{
		obj.ref = ii;
		obj.onchange = function(){
			if(!this.checked){
				osm_seleccionar("Campo:" + this.ref + ".llaveprimaria", false);
			}		
		}
		
		// --
		
		ii++;
		obj = osm_getObjeto("Campo:" + ii + ".obligatorio");
	}
	
	// -------
	
	osm_listen("blur", "Estructura.nombre", nombreUnico);
}


function page_validarGuardar(){
	
	// Verifica que el nombre no se vacio
	
	if(osm_esVacio( osm_getValor("Estructura.nombre") )){
		osm_alert("El nombre de la Estructura no puede ser vacia");
		osm_setFoco("Estructura.nombre");
		return false;
	}
	
	
	// Verifica que el nombre de los campos no sea vacio
	
	var visualizacion = false;
	var numerocampos = 0;
	var ii = 1;
	var obj = osm_getObjeto("bloqueCampoVisible_" + ii);

	while( obj != null )
	{
		var valor = osm_getValor("bloqueCampoVisible_" + ii);
		if(valor=="true")
		{
			numerocampos++;
			if( osm_esVacio( osm_getValor("Campo:" + ii + ".nombre") ) ){
				osm_alert("Debe ingresar el nombre del Campo");
				osm_setFoco("Campo:" + ii + ".nombre");
				return false;
			}
			
			if( osm_esVacio( osm_getValor("Campo:" + ii + ".tiposeleccionado") ) ){
				osm_alert("Debe seleccionar el Tipo de dato del Campo.");
				osm_setFoco("Campo:" + ii + ".tiposeleccionado");
				return false;
			}
			
			if( osm_esSeleccionado("Campo:" + ii + ".visualizacion")){
				visualizacion = true;
			}
		}
		
		// --
		
		ii++;
		obj = osm_getObjeto("bloqueCampoVisible_" + ii);
	}
	
	// Verifica que por lo menos tenga un campo
	
	if(numerocampos==0)
	{
		osm_alert("Debe agregar por lo menos un campo");
		return false;
	}
	
	if(!visualizacion){
		osm_alert("Se debe marcar por lo menos un campo para visualizaci\u00f3n.");
		return false;
	}
	
	
	
	return true;
}

osm_listen("load", window, p_load);


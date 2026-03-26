var NUM_ELEMENTO = 1;
var NUM_PARAMETRO = 1;

function page_validarGuardar()
{

	var i = 0;

	// Verifica que el nombre no se vacio

	if (osm_esVacio(osm_getValor("Formato.nombre")))
	{
		osm_alert("El nombre del Formato no puede ser vacio");
		osm_setFoco("Formato.nombre");
		return false;
	}

	if (osm_esVacio(osm_getValor("Formato.registrosporcarga")))
	{
		osm_alert("El numero de Registros por carga no puede ser vacio");
		osm_setFoco("Formato.registrosporcarga");
		return false;
	}

	if (osm_esVacio(osm_getValor("Formato.id_estructura")))
	{
		osm_alert("Se debe seleccionar la Estructura Base");
		osm_setFoco("Formato.id_estructura");
		return false;
	}

	var selects = document.getElementsByTagName("SELECT");
	for (i = 0; i < selects.length; i++)
	{
		var selectItem = selects[i];
		var idd = selectItem.id + "]";

		if (idd.indexOf(".tipo_ingreso]") > 0 && selectItem.value == "0")
		{
			osm_alert("Se debe seleccionar el Tipo de Valor");
			osm_setFoco(selectItem.id);
			return false;
		}

	}

	var inputs = document.getElementsByTagName("INPUT");
	for (i = 0; i < inputs.length; i++)
	{
		var inputItem = inputs[i];
		var idd = inputItem.id + "]";

		if (idd.indexOf("stino:")<=0 && idd.indexOf(".titulo]") > 0 && inputItem.value.length == 0)
		{
			osm_alert("El Titulo no puede ser vacio");
			osm_setFoco(inputItem.id);
			return false;
		}

	}

	var inputs_bandeja = $("input[id*='.bandeja_entrada']").filter("input:checked");
	
	for (i = 0; i < inputs_bandeja.length; i++) {
		var id_orden = inputs_bandeja[i].id.replace('.bandeja_entrada','.orden_bandeja_entrada');
		var valor_orden = $("input[id='"+id_orden+"']").val();
		if (osm_esVacio(valor_orden) || isNaN(valor_orden)) {
			val_bandeja = false;
			osm_setFoco(id_orden);
			osm_alert("Se debe seleccionar el orden de los campos en la bandeja de entrada con valores numericos");
			return false;
		}		
	}
	
	return true;
}

// ------------------------------------------------

var PAGE_PLANTILLA = null;
var PAGE_PLANTILLA_ESTRUCTURA = null;

function page_seleccionEstructura(obj, pid)
{
	var vcontent = document.getElementById("Formato.id_estructura|CONTENT");
	vcontent.posicion = "";
	vcontent.innerHTML = "";

	if (obj.value != ".")
	{
		var campos = jsonrpc._("formatoServicio.obtenerCamposPorEstructura")(obj.value);

		//Actualiza los campos de variables de liberacioin - ver template

		try {
			actualizarListaVariablesLiberacion(campos);
		} catch (e) {
		}
		
		var contenido = "";
		if (campos != null)
		{
			var i = 0;
			while (i < campos.length)
			{
				var tcampo = campos[i];
				tcampo.posicion = vcontent.posicion + (i + 1) + ".";
				tcampo.id_padre = obj.value;
				contenido = contenido + page_crearCampo(tcampo, pid, (i + 1));
				
				i++;
			}
			vcontent.innerHTML = contenido;
		}
	}

	agregarTipoIngreso(vcontent);

	var vcontentPadre = document.getElementById("Formato.id_estructura|CONTENT_PADRE");
	vcontentPadre.posicion = "";
	vcontentPadre.innerHTML = "";

	if (obj.value != ".")
	{
		var estructuras = jsonrpc._("estructuraServicio.obtenerEstructurasPadre")(obj.value);

		var contenido = "";
		if (estructuras != null)
		{
			var i = 0;
			while (i < estructuras.length)
			{
				var testructura = estructuras[i];
				testructura.id_padre = obj.value;
				if (testructura.id_estructura != obj.value)
				{
					testructura.posicion = vcontentPadre.posicion + (i + 1 + campos.length) + ".";
					contenido = contenido + page_crearEstructura(testructura, pid, (i + 1 + campos.length));
				}
				i++;
			}
			vcontentPadre.innerHTML = contenido;
		}
	}

}

function agregarTipoIngreso(vcontent)
{

	var vselects = vcontent.getElementsByTagName("SELECT");

	for ( var i = 0; i < vselects.length; i++)
	{
		var sel = vselects[i];

		var posselect = sel.id.lastIndexOf(".tipo_ingreso");
		if (posselect > 0)
		{
			var idformato = sel.id.substring(0, posselect);
			var id_estructura = osm_getValor(idformato + ".id_estructura");

			if (id_estructura != null && id_estructura != "null")
			{
				sel.options[sel.options.length] = new Option("Seleccionar existente", 1);
				sel.options[sel.options.length] = new Option("Ingresar Id existente", 2);
				sel.options[sel.options.length] = new Option("Crear o Seleccionar", 6);
			}

			sel.options[sel.options.length] = new Option("Ingresado por Usuario", 3);
			sel.options[sel.options.length] = new Option("Valor Constante", 4);
			sel.options[sel.options.length] = new Option("Variable del Sistema", 5);
		}
	}

}

function page_crearCampo(vcampo, pid, ii)
{
	var cc = PAGE_PLANTILLA;

	cc = osm_remplazar(cc, "NOMBRECAMPO", vcampo.nombre);
	cc = osm_remplazar(cc, "IDCAMPO", vcampo.id_campo);
	cc = osm_remplazar(cc, "IDESTRUCTURA", vcampo.id_estructurarelacionada);
	cc = osm_remplazar(cc, "IDPADRE", vcampo.id_padre);
	cc = osm_remplazar(cc, "POSICIONCAMPO", vcampo.posicion);

	var ttext = pid + ".formato_campo_list:" + ii;
	cc = osm_remplazar(cc, "PLANTILLA", ttext);
	
	cc = osm_remplazar(cc, "DISPLAY_SV", ((vcampo.id_tipocampo==0)?"inline-block":"none"));
	cc = osm_remplazar(cc, "DISPLAY_FILTRO", ((vcampo.id_tipocampo!=0)?"":"none"));
	return cc;
}

function page_crearEstructura(vestructura, pid, ii)
{
	var cc = PAGE_PLANTILLA_ESTRUCTURA;

	cc = osm_remplazar(cc, "IDESTRUCTURA", vestructura.id_estructura);
	cc = osm_remplazar(cc, "POSICIONESTRUCTURA", vestructura.posicion);
	cc = osm_remplazar(cc, "IDPADRE", vestructura.id_padre);
	cc = osm_remplazar(cc, "IDCAMPO", vestructura.id_campo_relacionado);
	cc = osm_remplazar(cc, "NOMBREESTRUCTURA", vestructura.nombre);

	var ttext = pid + ".formato_campo_list:" + ii;
	cc = osm_remplazar(cc, "PLANTILLA", ttext);

	return cc;
}

// ------------------------------------------------

function page_cambioTipovisualizacion(obj, nfcampo)
{

	var vcontent = document.getElementById(nfcampo + ".CONTENT");
	vcontent.innerHTML = "";

	var vcontentPadre = document.getElementById(nfcampo + ".CONTENT_PADRE");
	vcontentPadre.innerHTML = "";

	if (obj.checked)
	{
		page_cambioIngresoUsuario(obj, nfcampo, vcontent, vcontentPadre);
	}
}

// ------------------------------------------------

function page_cambioIngresoUsuario(obj, nfcampo, vcontent, vcontentPadre)
{
	var cid_estructura = osm_getValor(nfcampo + ".id_estructura");
	var cid_padre = osm_getValor(nfcampo + ".id_padre");
	var cid_campo = osm_getValor(nfcampo + ".id_campo");
	var cid_posicion = osm_getValor(nfcampo + ".id_estructura|POSICION");

	if (cid_estructura != null && cid_estructura != "null")
	{
		var campos = jsonrpc._("formatoServicio.obtenerCamposPorEstructura")(cid_estructura);

		var contenido = "";
		if (campos != null)
		{
			var i = 0;
			var j = 1;
			while (i < campos.length)
			{
				var tcampo = campos[i];
				if (tcampo.id_campo != cid_campo)
				{
					tcampo.posicion = cid_posicion + (j) + ".";
					tcampo.id_padre = cid_estructura;
					contenido = contenido + page_crearCampo(tcampo, nfcampo, (j));
					j++;
				}
				i++;
			}
			vcontent.innerHTML = contenido;
		}
	}

	agregarTipoIngreso(vcontent);
	
	if (cid_estructura != null && cid_estructura != "null")
	{
		var estructuras = jsonrpc._("estructuraServicio.obtenerEstructurasPadre")(cid_estructura);

		var contenido = "";
		if (estructuras != null)
		{
			var i = 0;
			var j = 1;
			while (i < estructuras.length)
			{
				var testructura = estructuras[i];
				testructura.id_padre = cid_estructura;
				if (testructura.id_estructura != cid_padre)
				{
					testructura.posicion = cid_posicion + (j + campos.length) + ".";
					contenido = contenido + page_crearEstructura(testructura, nfcampo, (j + campos.length));
					j++;
				}
				i++;
			}
			vcontentPadre.innerHTML = contenido;
		}
	}
}
// ---------------------------------------------------------------------------------------------

function page_cargaCamposEstructura(obj, nfcampo)
{

	var vcontent = document.getElementById(nfcampo + ".CONTENT");
	vcontent.innerHTML = "";

	var vcontentPadre = document.getElementById(nfcampo + ".CONTENT_PADRE");
	vcontentPadre.innerHTML = "";

	if (obj.checked)
	{
		page_cambioIngresoUsuario(obj, nfcampo, vcontent, vcontentPadre);
	}
}

function page_eliminarElemento(ids)
{
	osm_setVisible('BloqueDestino_' + ids, false);
	osm_setValor("bloqueDestinoVisible_" + ids, "false");
	var cc = osm_getObjeto('Destino:' + ids + '.titulo');
	cc.value = "";
}

function page_eliminarParametro(ids)
{
	osm_setVisible('BloqueParametro_' + ids, false);
	osm_setValor("bloqueParametroVisible_" + ids, "false");
	var cc = osm_getObjeto('ParametroCarga:' + ids + '.nombre');
	cc.value = "";
}

function page_verElemento(ids)
{
	osm_setVisible('BloqueDestino_' + ids, true, true);
	
	var nn = 'Destino:' + ids + '.titulo';
	osm_setValor(nn, '');
	osm_setFoco(nn);
}

function page_verParametro(ids)
{
	osm_setVisible('BloqueParametro_' + ids, true, true);
	
	var nn = 'ParametroCarga:' + ids + '.nombre';
	osm_setValor(nn, '');
	osm_setFoco(nn);
}

function page_agregarElemento(){
	osm_setVisible('BloqueDestino_' + NUM_ELEMENTO, true, true);
	osm_setValor("bloqueDestinoVisible_" + NUM_ELEMENTO, "true");
	page_verElemento(NUM_ELEMENTO);
	NUM_ELEMENTO++;
}
function page_agregarParametro(){
	osm_setVisible('BloqueParametro_' + NUM_PARAMETRO, true, true);
	osm_setValor("bloqueParametroVisible_" + NUM_PARAMETRO, "true");
	page_verParametro(NUM_PARAMETRO);
	NUM_PARAMETRO++;
}

//---------------------------------------------------
function verValorCondicion(obj, nfcampo)
{
	
	var vcontent = document.getElementById(nfcampo + ".valor_condicion");

	if (obj.checked)
	{
		osm_setVisible(nfcampo + ".valorCondicion_Block", true);
		vcontent.value = "";
	}else{
		osm_setVisible(nfcampo + ".valorCondicion_Block", false);
		vcontent.value = "";
	}
}

function agregarFormatoRef(){
	
	var id_formato = osm_getValor("sel_formatos");
	
	if($("#formato_ref_" + id_formato).length){
		alert("El formato ya fue agregado");
		return false;
	}
	
	var nombre = osm_getValorText("sel_formatos");
	nombre = nombre.substring( nombre.indexOf("-") +2 );
	nombre = osm_remplazarRegex(nombre,"^\d+[ ][-][ ]","");
	
	osm_construirHTML("FORMATOS_REFERENCIADOS", "PLANTILLA_FORMATO_REF", [id_formato, nombre ]);
	
}

function eliminarFormatoRef(id){
	
	$("#formato_ref_"+id).remove();
	$("#FORMATOS_REFERENCIADOS").find("option[value='" +id +"']").remove();
}

function verOrdenBandejaEntrada(obj, nfcampo)
{
	
	var vcontent = document.getElementById(nfcampo + ".orden_bandeja_entrada");

	if (obj.checked)
	{
		osm_setVisible(nfcampo + ".ordenBandejaEntrada_Block", true);
		vcontent.value = "";
	}else{
		osm_setVisible(nfcampo + ".ordenBandejaEntrada_Block", false);
		vcontent.value = "";
	}
}

// ------------------------------------------------

function p_load()
{
	osm_setFoco("Formato.nombre");

	PAGE_PLANTILLA_ESTRUCTURA = document.getElementById("plantilla_estructura").innerHTML;
	document.getElementById("plantilla_estructura").innerHTML = "";
	PAGE_PLANTILLA = document.getElementById("plantilla_campo").innerHTML;
	document.getElementById("plantilla_campo").innerHTML = "";

	var select_formato_estructura = osm_getObjeto("Formato.id_estructura");

	select_formato_estructura.onchange = function()
	{
		page_seleccionEstructura(this, 'FormatoCampo')
	}
	
	NUM_ELEMENTO = parseInt(osm_getValor("var.num_elemento"));
	osm_listen("click", "btn_agregar_destino", page_agregarElemento);
	
	NUM_PARAMETRO = parseInt(osm_getValor("var.num_parametro"));	
	osm_listen("click", "btn_agregar_parametro", page_agregarParametro);
	
}

osm_listen("load", window, p_load);

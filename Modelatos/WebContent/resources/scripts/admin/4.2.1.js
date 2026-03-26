$(document).ready(iniciar);

function iniciar(){
	id_estructura = osm_getValor("Formato.id_estructura");
	
	if(!osm_esVacio(id_estructura)){
		var campos = jsonrpc._("formatoServicio.obtenerCamposPorEstructura")(id_estructura);
		
		if (campos != null)
		{
			//Se crea el campo totalizador
			page_construirSelectCampoTotalizador(campos);
			
			//Se establece el valor ya elegido
			var id = osm_getValor("id_totalizador");
			osm_setValor("Formato.id_campo_totalizador", id)
		}
	}
	
	 
}


function page_validarGuardar()
{

	var i = 0;

	// Verifica que el nombre no se vacio

	if (osm_esVacio(osm_getValor("Formato.id_formato")))
	{
		osm_alert("El formato debe tener un numero de identificacion");
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

		if (idd.indexOf(".titulo]") > 0 && inputItem.value.length == 0)
		{
			
			var continuar = true;
			
			var sidd = idd.substring(0, idd.length - 7 ) + "agregarFormato";
			var obagregar = osm_getObjeto(sidd);
			if(obagregar!=null){
				if(!obagregar.checked){
					continuar = false;
				}
			}
			
			if(continuar)
			{
				osm_alert("El Titulo no puede ser vacio");
				osm_setFoco(inputItem.id);
				
				return false;
			}
			
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
			
			//Se crea el campo totalizador
			page_construirSelectCampoTotalizador(campos);
			
		}
		
		
	}

	agregarTipoIngreso(vcontent);
	agregarPreseleccion(vcontent);

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

function agregarPreseleccion(vcontent)
{

	var vdiv = vcontent.getElementsByTagName("div");

	for ( var i = 0; i < vdiv.length; i++)
	{
		var div = vdiv[i];
		var posselect = div.id.lastIndexOf(".precarga_Block");
		if (posselect > 0)
		{
			var idformato = div.id.substring(0, posselect);
			var id_estructura = osm_getValor(idformato + ".id_estructura");
			
			if (id_estructura != null && id_estructura != "null")
			{
				div.style.display = "";
			}
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
			var multiplicidad = osm_getValor(idformato + ".multiplicidad");

			if (id_estructura != null && id_estructura != "null")
			{
				sel.options[sel.options.length] = new Option("Seleccionar existente", 1);
			}

			sel.options[sel.options.length] = new Option("Ingresado por Usuario", 3);
			
			if(multiplicidad == '1...1')
			{
				sel.options[sel.options.length] = new Option("Valor Constante", 4);
				sel.options[sel.options.length] = new Option("Variable del Sistema", 5);
				sel.options[sel.options.length] = new Option("Valor de Sesion", 12);
			}
			sel.options[sel.options.length] = new Option("Seleccionar de lista dinamica", 7);
			sel.options[sel.options.length] = new Option("Valor Vacio", 10);
			sel.options[sel.options.length] = new Option("Automatico", 11);
			
		}
	}

}

function page_crearCampo(vcampo, pid, ii)
{
	var cc = PAGE_PLANTILLA;

	cc = osm_remplazar(cc, "NOMBRECAMPO", vcampo.nombre);
	cc = osm_remplazar(cc, "IDCAMPO", vcampo.id_campo);
	cc = osm_remplazar(cc, "MULTIPLICIDAD", vcampo.multiplicidad);
	cc = osm_remplazar(cc, "IDESTRUCTURA", vcampo.id_estructurarelacionada);
	cc = osm_remplazar(cc, "IDPADRE", vcampo.id_padre);
	cc = osm_remplazar(cc, "POSICIONCAMPO", vcampo.posicion);
	
	if(vcampo.id_estructurarelacionada==null){
		cc = osm_remplazar(cc, "PRECARGA", "none");
	}

	var ttext = pid + ".formato_campo_list:" + ii;
	cc = osm_remplazar(cc, "PLANTILLA", ttext);

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

function page_cambioTipoingreso(obj, nfcampo)
{

	var vcontent = document.getElementById(nfcampo + ".CONTENT");
	vcontent.innerHTML = "";

	var vcontentPadre = document.getElementById(nfcampo + ".CONTENT_PADRE");
	vcontentPadre.innerHTML = "";

	osm_setVisible(nfcampo + ".valorConstante_Block", false);
	osm_setVisible(nfcampo + ".valorSesion_Block", false);
	osm_setVisible(nfcampo + ".visualizarValorSesion_Block", false);
	osm_setVisible(nfcampo + ".visualizarValorConstante_Block", false);
	osm_setVisible(nfcampo + ".id_variable_Block", false);
	osm_setVisible(nfcampo + ".id_listadinamica_Block", false);
	osm_setVisible(nfcampo + ".lista_dinamica_valor_inicial_Block", false);
	osm_setVisible(nfcampo + ".mensaje_campo_padre_Block", false);

	osm_setValor(nfcampo + ".valor_constante", "");
	osm_setValor(nfcampo + ".mensaje_campo_padre", "");
	osm_setValor(nfcampo + ".id_variable", "0");
	
	osm_setValor(nfcampo + ".visualizar_valor_sesion", "N");
	var check_valor_sesion = document.getElementById(nfcampo + ".visualizar_valor_sesion");
	check_valor_sesion.checked = false;
	
	osm_setValor(nfcampo + ".visualizar_valor_constante", "N");
	var check_valor_constante = document.getElementById(nfcampo + ".visualizar_valor_constante");
	check_valor_constante.checked = false; 
			
	if (obj.value == "4")
	{
		osm_setVisible(nfcampo + ".valorConstante_Block", true, true);
		osm_setVisible(nfcampo + ".visualizarValorConstante_Block", true, true);
	}
	
	if (obj.value == "12")
	{
		osm_setVisible(nfcampo + ".valorSesion_Block", true, true);
		osm_setVisible(nfcampo + ".visualizarValorSesion_Block", true, true);
	}

	if (obj.value == "5")
	{
		osm_setVisible(nfcampo + ".id_variable_Block", true, true);
	}

	if (obj.value == "3" || obj.value == "6")
	{
		page_cambioIngresoUsuario(obj, nfcampo, vcontent, vcontentPadre);
	}
	
	if (obj.value == "7")
	{
		osm_setVisible(nfcampo + ".id_listadinamica_Block", true, true);
	}
	
	if (obj.value == "3" || obj.value == "7")
	{
		osm_setVisible(nfcampo + ".lista_dinamica_valor_inicial_Block", true, true);
	}
}

//------------------------------------------------

function page_cambioBlock(obj, nfcampo, block){

	var valor = obj.value;
	osm_setVisible(nfcampo + block, valor == 'S');
	
}

function page_cambioObligatorio(obj, nfcampo){
	page_cambioBlock(obj, nfcampo,".obligatorio_campo_Block");
}

function page_cambioOcultable(obj, nfcampo){
	page_cambioBlock(obj, nfcampo,".ocultable_campo_Block");
}

function page_cambioValidacion(obj, nfcampo){
	page_cambioBlock(obj, nfcampo,".validacion_campo_Block");
}

function page_cambioTitulo(obj, nfcampo){
	page_cambioBlock(obj, nfcampo,".titulo_html_Block");
}

function page_cambioValidacionServicio(obj, nfcampo){
	page_cambioBlock(obj, nfcampo,".validacion_servicio_Block");
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
		osm_setVisible(nfcampo + ".mensaje_campo_padre_Block", true);
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
	agregarPreseleccion(vcontent);
	
	if (cid_estructura != null && cid_estructura != "null")
	{
		// TODO: Se comenta linea para desactivar el uso de estructuras padre. ( se debe verificar posteriormente esta funcionalidad )
		var estructuras = null; // jsonrpc._("estructuraServicio.obtenerEstructurasPadre")(cid_estructura);
		
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

// ------------------------------------------------
CAMPO_TIPO_INTEGER = 5;
CAMPO_TIPO_DOUBLE = 7;

function page_construirSelectCampoTotalizador(camposEstructura){
	
	
	//Se crean las opciones
	if(camposEstructura!=null){
		var selector = osm_getObjeto("Formato.id_campo_totalizador");
		$(selector).empty()
		
		//Creamos la opcion por defecto
		selector.options[selector.options.length] = new Option("--Seleccione--", "");
		
		var i = 0;
		while (i < camposEstructura.length){
			
			//Se agregan unicamente los campos que sean de tipo DOUBLE y INTEGER
			if(camposEstructura[i].id_tipocampo == CAMPO_TIPO_DOUBLE || camposEstructura[i].id_tipocampo == CAMPO_TIPO_INTEGER ){
				selector.options[selector.options.length] = new Option(camposEstructura[i].nombre, camposEstructura[i].id_campo)
			}
			
			//Se incrementa el acumulador
			i++;
		}
	}else{
		
		alert("vacio");
	}
}

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
	
}

function agregarEstructura(obj){
	var id_estructura = obj.value;
	
	if(!osm_esVacio(id_estructura))
	{
	
		if (osm_getObjeto("fila_"+id_estructura) != null)  {
			var fila = $("#fila_"+id_estructura);
			fila.css("background-color","#CBCBCB");
			fila.animate({"background-color":"#fff"}, 800);
			return;	
		}
		
		var total = parseInt(osm_getValor("n_estructuras")) + 1;
		
		
		var nombre_negocio = obj.options[obj.selectedIndex].text;
		var desc_negocio = osm_getValor("descrip_"+id_estructura);
		
		osm_construirHTML("estructuras_aplicar", "negocio_plantilla", [id_estructura, nombre_negocio, total]);
		osm_setValor("n_estructuras", total);
	}
	
	obj.value = "";
	
}

//---------------------------------------------------------------------------------
function mostrarEdicionParametrosFormato(){
	
	osm_enviarFormulario('form_edicion_parametros');
}

//---------------------------------------------------------------------------------
function eliminarEstructura(id_estructura){
	
	$("#fila_"+id_estructura).remove();

}
osm_listen("load", window, p_load);

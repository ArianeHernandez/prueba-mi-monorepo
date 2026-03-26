
var PAGE_PLANTILLA = null;

function page_seleccionEstructura(obj, pid)
{
	var vcontent = document.getElementById(obj.id + "|CONTENT");
	vcontent.innerHTML = "";
	
	if(obj.value!=".")
	{
		var campos = jsonrpc._("formatoServicio.obtenerCamposPorEstructura")(obj.value);
		
		var contenido = "";
		if(campos!=null)
		{
			var i=0;
			while(i<campos.length){
				contenido = contenido + page_crearCampo(campos[i], pid, (i+1) );
				i++;
			}
			vcontent.innerHTML = contenido;
		}
	}

}

function page_crearCampo(vcampo, pid, ii)
{
	var cc = PAGE_PLANTILLA;
	
	cc = osm_remplazar(cc, "NOMBRECAMPO", vcampo.nombre );
	cc = osm_remplazar(cc, "IDCAMPO", vcampo.id_campo );
	cc = osm_remplazar(cc, "IDESTRUCTURA", vcampo.id_estructurarelacionada );
	
	var ttext = pid + ".formato_campo_list:" + ii;
	cc = osm_remplazar(cc, "PLANTILLA", ttext);
	
	return cc;
}


// ------------------------------------------------

function page_cambioTipoingreso(obj, nfcampo)
{

	var vcontent = document.getElementById(nfcampo + ".CONTENT");
	vcontent.innerHTML = "";
	
	osm_setVisible( nfcampo + ".valorConstante_Block" ,false);
	osm_setVisible( nfcampo + ".id_variable_Block" ,false);

	osm_setValor(nfcampo + ".valor_constante", "");
	osm_setValor(nfcampo + ".id_variable", "0");

	if(obj.value=="4"){
		osm_setVisible( nfcampo + ".valorConstante_Block" ,true);
	}
	
	if(obj.value=="5"){
		osm_setVisible( nfcampo + ".id_variable_Block" ,true);
	}
	
	
	if(obj.value=="3" || obj.value=="6"){
		page_cambioIngresoUsuario(obj, nfcampo, vcontent);
	}
}


// ------------------------------------------------

function page_cambioIngresoUsuario(obj, nfcampo, vcontent)
{
		var cid_estructura = osm_getValor(nfcampo + ".id_estructura");
	
		if(cid_estructura!=null && cid_estructura!="null")
		{
			var campos = jsonrpc._("formatoServicio.obtenerCamposPorEstructura")(cid_estructura);
			
			var contenido = "";
			if(campos!=null)
			{
				var i=0;
				while(i<campos.length){
					contenido = contenido + page_crearCampo(campos[i], nfcampo, (i+1) );
					i++;
				}
				vcontent.innerHTML = contenido;
			}
		}
}

// ------------------------------------------------

function page_onloadwindow()
{
	PAGE_PLANTILLA = document.getElementById("plantilla_campo").innerHTML;
	document.getElementById("plantilla_campo").innerHTML = "";
}


osm_listen("load", window, page_onloadwindow);

var DATOS_PRECARGA;
var MAP_LISTAS_DINAMICAS = new Array();
var MAP_LISTAS_VALORES = new Array();
var RESULTADOS_MAXIMOS = 100;
var LISTAS_PENDIENTES = 0;
var TOTAL_ADJUNTOS_FORMATO = 0;
var ARCHIVOS_FINALIZADOS = 0;
var ARRAY_CAMPOS = new Array();
var ID_ACTIVOS = new Array();
var INTENTOS_PRECARGA = new Map();
var DATOS_PADRE_PRECARGA = new Array();
var ARCHIVOS_ADJUNTADOS = 0;
var ARCHIVOS_FINALIZADOS = 0;
var TOTAL_ARCHIVOS_ADJUNTOS = 0;
var ADJUNTOS_PENDIENTES = [];
var SIGUIENTE_ADJUNTO = 0;
var ADJUNTOS_FALLIDOS = [];
// --------------------------------------------------------

$(window).load(osm_block_window);
$(window).load(osm_verLoader);
$(window).load(cargarEstilos);
$(window).load(initFiles);
$(window).load(initDoom);
$(window).load(obtenerValoresLista);
$(window).load(cargarMensajes);

// Evitar abrir archivos mal arrastrados
window.addEventListener("dragover", function(e) {
	e = e || event;
	console.log(e);
	if (e.target.tagName != "INPUT") { // check which element is our target
		e.preventDefault();
	}
}, false);
window.addEventListener("drop", function(e) {
	e = e || event;
	console.log(e);
	if (e.target.tagName != "INPUT") { // check which element is our target
		e.preventDefault();
	}
}, false);

// --------------------------------------------------------

function seleccionBusquedaVacia() {
	seleccionarElementoBusqueda("", "--");
}

function initDoom() {
	agregarDependenciasOcultables();
	agregarDependenciasObligatorios();
	agregarDependenciasCampo();
	agregarValidacionesEnLinea();
}

function initFiles() {
	// valores para los nombres de archivos adjuntos
	$(".caja_archivo_adjunto").each(function(e) {
		var id_label = $(this).attr("id");
		var id_campo = id_label.substring(5);
		extensionesAceptadasSoliInicial(id_campo, id_label)
		$(this).change(function(e) {
			if (e) {
				var existe = osm_getValor("label_" + id_campo);
				if (osm_esVacio(existe)) {
					TOTAL_ADJUNTOS_FORMATO = TOTAL_ADJUNTOS_FORMATO + 1;
				}
				var file = e.target.files[0];
				if (!file) {
					removeFile(id_campo);
					return;
				}
				var esValido = validarExtensionesSoliInicial(id_campo, file);
				
				if (esValido) {
					if (file.size <= 104857600) {
					var fileName = file.name;
					osm_setValor("label_" + id_campo, fileName);
					$(".variable_" + id_campo).val(fileName);
		
					$("#trash_" + id_campo).removeClass("ic-hide");
				} else {
					alert("El archivo seleccionado supera el tama\u00f1o permitido (100 MB)");
					removeFile(id_campo);
				}				
				} else {
					removeFile(id_campo);
				}
			}
		});
	});
}

function validarExtensionesSoliInicial (id_campo, file) {
	
	var extensiones = jsonrpc._("tipoArchivoServicio.obtenerExtensionesPorIdCampo")(parseInt(id_campo));
	
	if(extensiones.list && extensiones.list.length > 0){
		
		var valid = false;
		
		var filename = file.name.split(/\./g);
		var extension = filename.length > 1 ? filename.pop(): "";
		var textoExtensiones = "";
		
		extensiones = extensiones.list[0].split(", ");
		for (var i = 0; i < extensiones.length; i++) {
			
		    if (extensiones[i].toLowerCase() == extension.toLowerCase()) {
		    	valid = true;
		    	break;
		    }
		    
		    textoExtensiones += extensiones[i].toLowerCase();
		    if (i != extensiones.length - 1) textoExtensiones += ", ";  
		}
		
		if (!valid) {
			s_alert_info('Formato del archivo inv\u00e1lido', 'Debes adjuntar un archivo con formato ' + textoExtensiones + ' antes de continuar');
			return false;
		}
	}
	
	return true;
	
}

function extensionesAceptadasSoliInicial (id_campo, id_input) {
	var extensiones = jsonrpc._("tipoArchivoServicio.obtenerExtensionesPorIdCampo")(parseInt(id_campo));
	
	var campoArchivo = document.getElementById(id_input); 
	
	campoArchivo.accept = extensiones.list[0]?.replace(/(\w+)/g, ".$1").toLowerCase(); 
}

// --------------------------------------------------------
function obtenerValoresLista() {
	obtenerValoresListaDPadre(document);
	obtenerValoresListaPadre(document);
	precargarCampos();
}

/**
 * Llenar los select que tienen lista dinamicas asociadas a partir del padre
 */
function obtenerValoresListaDPadre(padre) {
	var camposListaDinamica = $(padre).find(".campo_lista_dinamica");
	for (var i = 0; i < camposListaDinamica.length; i++) {
		var id_campo = camposListaDinamica[i].value;
		if (id_campo.indexOf("PLANTILLA") < 0) {
			var id_lista_dinamica = osm_getValor(id_campo
					+ ".id_lista_dinamica");
			var select = osm_getObjeto(id_campo + ".valor");
			actualizarValor(select, null, id_lista_dinamica);
		}
	}
}

function obtenerValoresListaPadre(padre) {
	var camposListaValores = $(padre).find(".campo_lista_valores");
	
	for (var i = 0; i < camposListaValores.length; i++) {
		var id_campo = camposListaValores[i].value;
		if (id_campo.indexOf("PLANTILLA") < 0) {
			var id_lista_valores = osm_getValor(id_campo + ".id_lista_valores");
			var select = osm_getObjeto(id_campo + ".valor");
			actualizarValorLV(select, id_lista_valores);
		}
	}
	//listasPendientes();
}

// --------------------------------------------------------

function seleccionarElementoBusqueda(idseleccion, titulo) {

	osm_setValor(osm_getValor("formatoDatoName") + ".valor", idseleccion);
	osm_setValor(osm_getValor("formatoDatoName") + ".valorVisible", titulo);

	cerrarVentanaBusqueda();
}

// --------------------------------------------------------

function realizarBusqueda() {
	osm_setValor("busqueda_mensaje", "Realizando busqueda.");
	window.setTimeout("realizarBusquedaAccion()", 100);
}

function realizarBusquedaAccion() {
	var id_campo = osm_getValor("vnb_criterioBusqueda");
	var valor_busqueda = osm_getValor("vnb_valorBusqueda");
	var id_estructura = osm_getValor("id_estructura_busqueda");
	var id_persona = osm_getValor("id_persona");

	var info = null;

	try {
		info = jsonrpc._("datosServicio.obtenerdatos")(id_persona,
				id_estructura, id_campo, valor_busqueda);
	} catch (_e) {
	}

	var area_resultado = osm_getObjetoVacio("area_resultado");

	var innertext = "";

	var i = 0;
	if (info == null) {
		osm_setValor("busqueda_mensaje",
				"Ha ocurrido un error al realizar la operacion.")
	} else if (info.datos.length == 0) {
		osm_setValor("busqueda_mensaje", "No se encontraron resultados.")
	} else {
		if (info.datos.length == 1) {
			osm_setValor("busqueda_mensaje", "Se encontro un resultado.");
		} else {
			osm_setValor("busqueda_mensaje", "Se encontraron "
					+ info.datos.length + " resultados");
		}

		var datos = info.datos
		for (i = 0; i < info.datos.length; i++) {
			var plantilla_resultado = osm_getValor("plantilla_resultado");
			var registro = info.datos[i];
			if (registro != null) {
				var titulo = null;
				var j = null;
				for (j = 2; j < registro.dato.length; j++) {
					if (titulo == null) {
						titulo = registro.dato[j];
					} else {
						titulo = titulo + " | " + registro.dato[j];
					}
				}
				plantilla_resultado = osm_remplazar(plantilla_resultado,
						"TITULO", titulo);
				plantilla_resultado = osm_remplazar(plantilla_resultado,
						"MENSAJE", registro.dato[1]);
				plantilla_resultado = osm_remplazar(plantilla_resultado,
						"IDSELECCION", registro.dato[0]);

				innertext = innertext + plantilla_resultado
			}
		}

		osm_setValor("area_resultado", innertext);
	}
}

// --------------------------------------------------------

function verVentanaBusqueda(id_estructura, formatoDatoName) {
	osm_setValor("id_estructura_busqueda", id_estructura);
	osm_setValor("formatoDatoName", formatoDatoName);
	osm_getObjetoVacio("area_resultado");
	osm_getObjetoVacio("vnb_valorBusqueda");
	osm_getObjetoVacio("busqueda_mensaje");

	osm_setVisible("vn_busqueda", true, true);
	osm_ocultarSelects("bodyContent");

	if (osm_esVacio(getValorFormatoCampo(formatoDatoName + ".valor"))) {
		osm_setVisible("btn_busquedaVacia", false);
	} else {
		osm_setVisible("btn_busquedaVacia", true, true);
	}

	var selectorCriterio = osm_getObjetoVacio("vnb_criterioBusqueda");

	var campos = jsonrpc._("formatoServicio.obtenerCamposPorEstructura")(
			id_estructura);

	var i = 0;
	for (i = 0; i < campos.length; i++) {
		var campo = campos[i];
		if (campo.visualizacion == 'S') {
			selectorCriterio.options[selectorCriterio.length] = new Option(
					campo.nombre, campo.id_campo);
		}
	}
}

function cerrarVentanaBusqueda() {
	osm_setVisible("vn_busqueda", false);
	osm_mostrarSelects("bodyContent");
}

function eliminarElemento(objid) {
	osm_eliminarObjeto(objid);
}

// --------------------------------------------------------

function agregarDependenciasCampo() {

	$("input").each(
			function(i) {

				var id = this.id + "";

				if (!this.dependencias && id.indexOf("PLANTILLA") < 0
						&& id.indexOf(".formatoDatoList") > 0) {
					var ref = $(this).attr("reference");

					if (!osm_esVacio(ref)) {
						this.dependencias = true;

						var sref = ref.split("|");

						var nombre = sref[0];
						var id_lista = sref[1];

						if (!osm_esVacio(nombre)) {

							var nombres = nombre.split(",");
							var objs = new Array();
							for (i = 0; i < nombres.length; i++) {
								objs[i] = buscarCampo(id, nombres[i]);
								if (objs[i] == null) {
									alert('Campo no encontrado:' + nombres[i]);
								}
							}
							if (objs != null && objs.length > 0) {
								agregarEventoDependenciasCampo(objs, this,
										id_lista);
							}

						} else if (!osm_esVacio(id_lista)) {
							actualizarValor(this, null, id_lista);
						}

					}
				}

			});
}

function agregarEventoDependenciasCampo(objsbase, objdes, id_lista) {

	var valores_lista = new Array();
	for (i = 0; i < objsbase.length; i++) {
		var valor = getValorFormatoCampo(objsbase[i].id);
		if (!osm_esVacio(valor)) {
			valores_lista[i] = valor;
		} else {
			valores_lista = null;
			break;
		}
	}
	// var valor = getValorFormatoCampo(objbase.id);
	actualizarValor(objdes, valores_lista, id_lista);

	// var valorf = "actualizarValor(osm_getObjeto('"+ objdes.id
	// +"'),getValorFormatoCampo(this), "+id_lista+", true);";
	var valorf = "actualizarValorPorRef('" + objdes.id + "',this, " + id_lista
			+ ", true);";

	DATOS_PADRE_PRECARGA.push({
		"id_hijo": 	objdes.id,
		"padre": this,
		"id_lista": id_lista
	});
	
	for (i = 0; i < objsbase.length; i++) {
		objsbase[i].actualizacion = new Function(valorf);
		$(objsbase[i]).change(new Function(valorf));
	}
	objdes.referenciaActualizacion = objsbase;

}

function actualizarValorPorRef(idobjdes, objvalor, id_lista, limpiar) {

	var objsbase = document.getElementById(idobjdes).referenciaActualizacion;

	var valores = new Array();
	for (i = 0; i < objsbase.length; i++) {
		valores[i] = getValorFormatoCampo(objsbase[i].id);
	}
	actualizarValor(osm_getObjeto(idobjdes), valores, id_lista, true);
}

function actualizarValorLV(objdes, id_lista) {
	var id_map = id_lista;
	$(objdes).addClass("ui-autocomplete-loading");
	if (MAP_LISTAS_VALORES[id_map]) {
		actualizarSelectLV(objdes, MAP_LISTAS_VALORES[id_map], id_map)
	} else {
		var funcion = "actualizarSelectLV(osm_getObjeto('" + objdes.id
				+ "'),lista, '" + id_map + "');";
		jsonrpc._("listaDinamicaServicio.obtenerValoresLV")(
				new Function("lista", funcion), id_lista);
	}
	LISTAS_PENDIENTES++;
	bloquearVentana();
}

function actualizarValor(objdes, valores, id_lista, limpiar) {
	if (limpiar) {
		objdes.value = "";
	}
	$(objdes).addClass("ui-autocomplete-loading");
	var valoresStr = null;
	if (valores != null) {
		valoresStr = valores.join(",");
	}
	var id_map = id_lista + "|" + valoresStr;
	var funcion = "actualizarSelect(osm_getObjeto('" + objdes.id
			+ "'),lista, '" + id_map + "');";
	if (MAP_LISTAS_DINAMICAS[id_map]) {
		actualizarSelect(objdes, MAP_LISTAS_DINAMICAS[id_map], id_map)
	} else {
		if (valores != null) {
			if (valores.length != 1) {
				var parametrosStr = "[" + valores.join("],[") + "]";
				jsonrpc
						._(
								"listaDinamicaServicio.obtenerValoresListaDinamicaParams")
						(new Function("lista", funcion), id_lista,
								parametrosStr);
			} else {
				jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamica")(
						new Function("lista", funcion), id_lista, valores[0]);
			}
		} else {
			jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamica")(
					new Function("lista", funcion), id_lista, null);
		}
	}
	LISTAS_PENDIENTES++;
	bloquearVentana();
}

function SortByName(a, b) {
	var aName = a.nombre.toLowerCase();
	var bName = b.nombre.toLowerCase();
	return ((aName < bName) ? -1 : ((aName > bName) ? 1 : 0));
}

/**
 * Crear la lista para autocompletar en el campo
 * 
 * @param lista -
 *            lista con valores para mostrar
 * @param select -
 *            objeto html
 */
function crearListaAC(lista, select) {
	if (lista != null) {
		if (lista.list) {
			lista = lista.list;
		}
		var listado = new Array();
		// map para buscar el nombre por id
		var map = new Array();

		lista.sort(SortByName);
		
		
		for (var i = 0; i < lista.length; i++) {
			var reg = lista[i];
			listado[i] = new Object();
			listado[i].label = reg.nombre;
			listado[i].value = reg.nombre;
			listado[i].id = reg.id;
			map[reg.id] = reg.nombre;
		}

		// Clase css para indicar que es lista
		$(select).addClass("valorlista");

		// Si no hay datos no se puede editar el campo
		if (lista.length == 0) {
			$(select).attr("readonly", true);
			$(select).removeClass("ui-autocomplete-loading");
		} else {
			$(select).attr("readonly", false);
		}

		// Agrega el listener una vez
		if (!select.listado) {
			$(select).blur(validarValoLista);
		}

		select.listado = listado;
		select.map = map;
		
		if (select.listado.length == 2 && select.listado[0].label == "No" && select.listado[1].label == "Si"){
			
			crearOpcionesSiNo(lista, select);
		}
		
		$(select).autocomplete({
			minLength : 0,
			source : listado,
			autoFocus : true,
			select : function(event, ui) {
				this.realvalue = ui.item.id;
				this.value = ui.item.value
				$(this).change();
			},
			delay : 100
		});

		$(select).change(function() {

			if (osm_esVacio(this.value)) {
				this.realvalue = "";
				this.value = "";
			}

		});

		// Si la lista es muy grande se carga para guardar en cache
		if (listado.length > RESULTADOS_MAXIMOS) {
			$(select).autocomplete("search");
			$(select).autocomplete("close");
		}

		if (!select.focusAutocomplete) {
			// Si gana el foco muestra la lista
			$(select).focus(function() {
				$(this).autocomplete('search');
			});
		}

		select.focusAutocomplete = true;

		$(select).removeClass("ui-autocomplete-loading");

		if (lista.length > 0) {
			return;
		}

		$(select).change();
		

		

	} else {
		$(select).attr("readonly", true);
		$(select).removeClass("ui-autocomplete-loading");
	}

}

function crearOpcionesSiNo(lista, select){
	
		$(select).hide();
		var id = $(select).attr('id');
		var element = document.getElementById(id+".bool");
		if(element == null){
			$(select).parent().append('<div id=\"'+id+'.bool\" class=\"row col-md-12 margin-row\">  <div id=\"'+id+'.no\" class=\"l-button s-buttonenable-primary No\" onclick=\"setButtonValue(\''+id+'\', \'No\', \'0\');\">No</div><div id=\"'+id+'.si\" class=\"l-button s-buttonenable-primary Si\" onclick=\"setButtonValue(\''+id+'\', \'Si\', \'1\')\">Si</div></div>');
		}
		
	
}

function setButtonValue(select, value, realvalue){
	
	var element = document.getElementById(select);
	element.value=value;
	element.realvalue=realvalue;
	var siElement = document.getElementById(select+".si");
	var noElement = document.getElementById(select+".no");
	if(value=="Si"){ 
		$(siElement).addClass("sc-active");
		$(noElement).removeClass("sc-active");
	}else{
		$(noElement).addClass("sc-active");
		$(siElement).removeClass("sc-active");
	}
	$(element).change();
}

function validarValoLista() {
	if (this.listado) {
		for (var i = 0; i < this.listado.length; i++) {
			if (this.listado[i].label == this.value) {
				return true;
			}
		}
	}
	this.value = "";
}

function actualizarSelect(select, lista, id_map) {

	// MAP_LISTAS_DINAMICAS[id_map] = lista;
	listasPendientes();
	crearListaAC(lista, select);
}

function actualizarSelectLV(select, lista, id_map) {

	// MAP_LISTAS_VALORES[id_map] = lista;
	listasPendientes();
	crearListaAC(lista, select);
}

/**
 * Verificar si hay listas pendientes al cargar la pagina
 */
function listasPendientes() {
	LISTAS_PENDIENTES--;
	if (LISTAS_PENDIENTES < 1) {
		osm_unblock_window();
		osm_ocultarLoader();
	}
}

function bloquearVentana() {
	if (LISTAS_PENDIENTES > 0) {
		osm_block_window();
	}
}

// --------------------------------------------------------

function adicionarElemento(idf, formatodatoname) {

	var bloque_contenido = osm_getObjeto("CONTENIDO_" + idf + "N_"
			+ formatodatoname + "F");

	var cantidad_contenido = osm_getValorEntero("CANTIDAD_" + idf + "N_"
			+ formatodatoname + "F");
	var plantilla = osm_getValor("PLANTILLA_" + idf + "N_" + formatodatoname
			+ "F");

	var nombre_elemento = formatodatoname + ".formatoDatoList:"
			+ cantidad_contenido;
	var divcontent = document.createElement("DIV");
	divcontent.id = "DD" + nombre_elemento + "DD";

	plantilla = osm_remplazar(plantilla, "PLANTILLA_" + idf + "V_"
			+ formatodatoname + "F", nombre_elemento);
	plantilla = osm_remplazar(plantilla, "IDELIMINAR", divcontent.id);

	// divcontent.innerHTML = plantilla;
	$(divcontent).html(plantilla);
	// bloque_contenido.appendChild(divcontent);
	$(bloque_contenido).append(divcontent);
	osm_setValor("CANTIDAD_" + idf + "N_" + formatodatoname + "F",
			cantidad_contenido + 1);

	agregarDependenciasCampo();
	initDoom();
	obtenerValoresListaPadre(bloque_contenido);
	// setTimeout("obtenerValoresListaPadre(osm_getObjeto('" +
	// bloque_contenido.id +
	// "'))",20);

}

// --------------------------------------------------------

function validacion_carga_interactiva() {
	$(".ERR_VALIDAR").removeClass("ERR_VALIDAR");
	ARRAY_CAMPOS = new Array();
	var resultado_validacion = true;
	var id_horario_liberacion = osm_getValor("id_horario_liberacion");
	var id_horario_formato = osm_getValor("id_horario_formato");
	var existen_archivos = osm_getValor('existen_archivos_adj');
	var formulario = osm_getObjeto("form_cargainteractiva");
	var adjunto_obligatorio = osm_getValor("adjunto_obligatorio");

	var inputs = formulario.getElementsByTagName("input");

	if (adjunto_obligatorio != null && existen_archivos == "false"
			&& adjunto_obligatorio == "S") {
		alert("Debe adjuntar archivos");
		return false;
	}

	for (var ff = 0; ff < inputs.length; ff++) {
		var inputelement = inputs[ff];
		var idelement = inputelement.id;
		if (idelement.indexOf("FormatoDato.") == 0
				&& idelement.indexOf("patron_validacion") > 0) {
			var idobjeto = idelement.substring(0, idelement
					.indexOf("patron_validacion") - 1);
			var valorobjeto = getValorFormatoCampo(idobjeto + ".valor");
			var id_campo = osm_getValor(idobjeto + ".id_campo");
			var es_obligatorio = osm_getValor(idobjeto + ".es_obligatorio");
			var multiplicidad = osm_getValor(idobjeto + ".multiplicidad");
			var patron_validacion = osm_getValor(idelement);
			var es_visible = $(osm_getObjeto(idobjeto + ".valor")).is(":visible")
					|| (osm_getObjeto(idobjeto + ".valor_file") != null 
						&& $(osm_getObjeto(idobjeto + ".valor_file")).is(":visible"))
					|| ($(osm_getObjeto("div_formato_double_" + idobjeto)) != null
						&& $(osm_getObjeto("div_formato_double_" + idobjeto)).is(":visible"))
					|| ($(osm_getObjeto(idobjeto + ".valor.si")) != null
						&& $(osm_getObjeto(idobjeto + ".valor.no")).is(":visible"));
			
			var validarpatron = false;
			var regexp = new RegExp(patron_validacion);
			
			if (osm_existeElemento(idobjeto + ".valor")
					&& es_obligatorio == "S" && es_visible) {

				if (osm_esVacio(valorobjeto)) {
//					alert("El campo " + osm_getValor(idobjeto + ".titulo")
//							+ " es obligatorio.");
//					osm_setFoco(idobjeto + ".valor");
//					return false;
					marcarCampos(idobjeto, id_campo);
					resultado_validacion = false;
				}

				validarpatron = true;

			} else if (es_obligatorio == "S" && multiplicidad == "1...N") {
				var id_formato_campo = osm_getValor(idobjeto
						+ ".id_formato_campo");
				var contenido = osm_getObjeto("CONTENIDO_" + id_formato_campo
						+ "N_" + idobjeto + "F");

				if (contenido != null) {
					var tieneBloques = $(contenido).find("input").length;

					if (tieneBloques == 0) {
//						alert("El campo " + osm_getValor(idobjeto + ".titulo")
//								+ " es obligatorio.");
//						return false;
						marcarCampos(idobjeto, id_campo);
						resultado_validacion = false;
					}
				}
			} else {
				if (!osm_esVacio(valorobjeto)) {
					validarpatron = true;
				}
			}

			if (validarpatron) {

				if (!regexp.test(valorobjeto)) {

//					alert("El campo " + osm_getValor(idobjeto + ".titulo")
//							+ " tiene un formato incorrecto.");
//					osm_setFoco(idobjeto + ".valor");
//					return false;
					marcarCampos(idobjeto, id_campo);
					resultado_validacion = false;
				}
			}

		} else if (idelement.indexOf("FormatoDato.") == 0
				&& idelement.indexOf("campo_validacion") > 0) {
			
			var patron_validacion = osm_getValor(idelement);
			var valorobjeto = getValorFormatoCampo(idobjeto + ".valor");
			
			if (!osm_esVacio(patron_validacion) && !osm_esVacio(valorobjeto)) {

				var regexp = new RegExp(patron_validacion);

				if (!regexp.test(valorobjeto)) {

//					alert("El campo " + osm_getValor(idobjeto + ".titulo")
//							+ " tiene un formato incorrecto.");
//					osm_setFoco(idobjeto + ".valor");
//					return false;
					marcarCampos(idobjeto, id_campo);
					resultado_validacion = false;
				}

			}

		}
		
		var idobjeto = idelement.substring(0, idelement.indexOf("id_campo") - 1);

		if (idelement.indexOf("FormatoDato.") == 0 
				&& idelement.indexOf("id_campo") > 0
				&& (osm_esVerdad(osm_getValor("Variable.validacion_rest"))
						|| osm_esVerdad(osm_getValor(idobjeto + ".validacion_servicio")))){
			var titulo = osm_getValor(idobjeto + ".titulo");
			var id_campo = osm_getValor(idobjeto + ".id_campo");
			var id_formato_campo = osm_getValor(idobjeto + ".id_formato_campo");
			var nombre_campo = jsonrpc._("formatoServicio.obtenerNombreCampoPorIdCampo")(id_campo);
			var valor_campo = getValorFormatoCampo(idobjeto + ".valor");
			var id_carga = osm_getValorEntero("id_carga");
			
			var campoObj = {
					titulo: titulo,
					id_campo: id_campo,
					id_formato_campo: id_formato_campo,
					nombre_campo: nombre_campo,
					valor: valor_campo
			};
			
			/* Se comenta validación en linea al final del formulario por falta de control de campos vacios
			if (osm_esVerdad(osm_getValor(idobjeto + ".validacion_servicio"))){
				try {
					var id_formato = osm_getValorEntero("Variable.id_formato");
					var resp = jsonrpc._("formatoServicio.validarCamposFormato")(id_formato, [campoObj], id_formato_campo, id_carga);
					if (!resp.valido){
						alert(resp.mensaje_validacion);
						marcarCampos(idobjeto, id_campo);
						resultado_validacion = false;
					}
				} catch (e) {
					alert("Ocurrio un error realizando la validacion en linea para el campo " + titulo + ". Por favor intente de nuevo");
					resultado_validacion = false;
				}
				
			}
			*/
			ARRAY_CAMPOS.push(campoObj);
		}
		
	}

	if (!resultado_validacion) {
		alert("Hay campos inválidos o vacíos, por favor verifique el formulario.");
		return false;
	}
	
	try {
		if (validacionPersonalizada) {
			var resultado = validacionPersonalizada();
			if (!resultado) {
				return false;
			}
		}
		
	} catch (e) {
		
	}
	
	// -------------------------------
	// Si tiene variables de validacion valida si son obligatorias
	try {
		var validacion = validarCargaAprobar();
		if (!validacion) {
			return false;
		}
	} catch (e) {
	}

	// --------------------------

	var validacion = ejecutarValidacionesPorListaDinamica();
	if (!validacion) {
		return false;
	}

	if (osm_esVerdad(osm_getValor("Variable.validacion_rest"))){
		
		try {
			var id_formato = osm_getValorEntero("Variable.id_formato");
			id_carga = osm_getValorEntero("id_carga");
			var resp = jsonrpc._("formatoServicio.validarCamposFormato")(id_formato, ARRAY_CAMPOS, null, id_carga);
			if (!resp.valido){
				alert(resp.mensaje_validacion);
				return false;
			}
		} catch (e) {
			alert("Ocurrio un error realizando la validacion en linea. Por favor intente de nuevo");
			return false;
		}
		
	}
	
	return true;

}

function marcarCampos (idObjeto,id_campo) {
	var obj = osm_getObjeto("div_formato_double_" + idObjeto);
	if (!obj) {
		obj = osm_getObjeto("label_" + id_campo);
		if (!obj) {
			var obj_si = osm_getObjeto(idObjeto + ".valor.si");
			var obj_no = osm_getObjeto(idObjeto + ".valor.no");
			if (obj_si && obj_no) {
				$(obj_si).removeClass("ERR_VALIDAR").addClass("ERR_VALIDAR");
				$(obj_no).removeClass("ERR_VALIDAR").addClass("ERR_VALIDAR");
			} else {
				obj = osm_getObjeto(idObjeto + ".valor");				
			}
		}
	}
	if (obj) {
		$(obj).removeClass("ERR_VALIDAR").addClass("ERR_VALIDAR");		
	}
}

// -------------------------------------------------------- NUEVA FUNCION PARA
// MOSTRAR VENTANA
function verVentanaAprobarCarga() {
	osm_setVisible("vn_aprobarcarga", true, true);
	osm_ocultarSelects("bodyContent");
}

// --------------------------------------------------------
function cumpleHorario(id_horario) {
	var estaEnHorario = jsonrpc._(
			"parametrizacionClienteServicio.cumpleHorario")(id_horario);

	return estaEnHorario;
}

// ---------------------------------------------------------

function actualizarCampo(id_campo, lista) {
	var select = osm_getObjeto("select_campo_" + id_campo);
	var options = select.options;
	if (lista != null) {
		lista = lista.list;
		options.length = 0;
		options[options.length] = new Option("--Seleccione--", "");
		for (var i = 0; i < lista.length; i++) {
			var reg = lista[i];
			options[options.length] = new Option(reg.nombre, reg.id);
		}
		if (lista.length > 0) {
			return;
		}
	}
}

// ------------------------------------------
function mostrarDoubleConFormato(formatoDato, tipo_dato_nombre) {

	// Se oculta el campo
	var div = osm_getObjeto('div_sinformato_double_' + formatoDato);
	$(div).hide();

	// Se muestra el div con el dato formateado
	var valor = osm_getValor(formatoDato + ".valor");

	if (!isNaN(valor)) {
		
		if (tipo_dato_nombre && tipo_dato_nombre.toLowerCase() == "porcentaje") {
			
			if (valor < 0) {
				valor = 0;
			} else if (valor > 100) {
				valor = 100;
			}
			
			osm_setValor(formatoDato + ".valor", valor);
			
			valor = osm_formatoPorcentaje(valor);
			
			osm_setValor('div_formato_double_' + formatoDato, valor);
		} else {
			if (valor != "") {
				valor = osm_formatoMoneda(valor);
			}
			osm_setValor('div_formato_double_' + formatoDato, valor);				
		}

	} else {
		osm_setValor('div_formato_double_' + formatoDato, "Error");

	}

	var div = osm_getObjeto('div_formato_double_' + formatoDato);
	$(div).show();

}

function mostrarDoubleSinFormato(formatoDato) {

	// Se oculta el div con formato
	var div = osm_getObjeto('div_formato_double_' + formatoDato);
	$(div).hide();

	// Se muestra el campo con el valor sin formato
	var div = osm_getObjeto('div_sinformato_double_' + formatoDato);
	$(div).show();

	// Se obtiene el foco
	osm_setFoco(formatoDato + ".valor");

}

function obtenerDatosPrecarga(idScriptFormatoCampo, id_formato_campo_precarga,
		input) {
	$(input).addClass("ui-autocomplete-loading");

	DATOS_PRECARGA = null;

	// Se calcula el id generico para los campos que estan al mismo nivel
	var posicion = idScriptFormatoCampo.lastIndexOf(":")
	var idGenericoCamposHijo = idScriptFormatoCampo.substring(0, posicion);

	// Se calcula el id del padre
	var idPadre = null
	posicion = idGenericoCamposHijo.lastIndexOf(".formatoDatoList");
	if (posicion > -1) {
		idPadre = idGenericoCamposHijo.substring(0, posicion);

	}
	var listaCamposLlavePrimaria = obtenerCamposLlavePrimaria(idGenericoCamposHijo);

	// Se revisan si los campos llave primaria no estan vacios y se crea un
	// arreglo con los id de campo

	var listaIdCamposLlave = new Array();
	var listaValoresLlave = new Array();

	var hayAlgunoVacio = false;
	for (var i = 0; i < listaCamposLlavePrimaria.length; i++) {

		var obj_valor = getValorFormatoCampo(listaCamposLlavePrimaria[i]
				+ ".valor");
		obj_id_campo = osm_getObjeto(listaCamposLlavePrimaria[i] + ".id_campo");

		if (osm_esVacio(obj_valor)) {
			hayAlgunoVacio = true;
		}

		listaIdCamposLlave[i] = $(obj_id_campo).val();
		listaValoresLlave[i] = obj_valor;
	}
	;

	if (!hayAlgunoVacio) {

		var id_formato = osm_getValor('Variable.id_formato');
		var idcampos = "";
		for (var i = 0; i < listaIdCamposLlave.length; i++) {
			idcampos = idcampos + "-" + listaIdCamposLlave[i] + ":"
					+ listaValoresLlave[i];
		}

		DATOS_PRECARGA = jsonrpc._("cargaServicio.obtenerDatosPrecarga")(
				id_formato, id_formato_campo_precarga, listaIdCamposLlave,
				listaValoresLlave);

		// Se muestran los datos consultados
		if (DATOS_PRECARGA != null && DATOS_PRECARGA.list.length > 0) {
			datos = DATOS_PRECARGA.list;

			mostrarDatosPrecarga(datos, idGenericoCamposHijo);
		} else {

			borrarValoresCamposIdGenerico(idGenericoCamposHijo);

			// Se pintan los valores de las llaves primarias
			for (var i = 0; i < listaCamposLlavePrimaria.length; i++) {
				setValorFormatoCampo(listaCamposLlavePrimaria[i] + ".valor",
						listaValoresLlave[i]);
			}

		}

	}
	$(input).removeClass("ui-autocomplete-loading");

}

function borrarValoresCamposIdGenerico(idGenericoCamposHijo) {
	// Se inicializacion los valores en vacio
	var listaCampos = obtenerCamposPorIdGenerico(idGenericoCamposHijo);

	if (listaCampos != null && listaCampos.length > 0) {
		for (var i = 0; i < listaCampos.length; i++) {
			var obj = osm_getObjeto(listaCampos[i] + ".valor");
			obj.value = "";
			if (obj.realvalue) {
				obj.realvalue = "";
			}
		}
	}

}

function mostrarDatosPrecarga(datos, idGenericoCamposHijo) {

	borrarValoresCamposIdGenerico(idGenericoCamposHijo);

	// Se colocan los valores correspondientes
	jQuery.each(datos[0].map, function(i) {

		if (i.substring(0, 1) == 'C' && this != window) {

			var id_campo = i.substring(1, (i.length));
			var valor = this;

			// Se buscan todos los campos hermanos
			var listaCampos = obtenerCamposPorIdGenerico(idGenericoCamposHijo);

			if (listaCampos != null && listaCampos.length > 0) {
				for (var i = 0; i < listaCampos.length; i++) {

					var obj_id_campo = osm_getObjeto(listaCampos[i]
							+ ".id_campo");

					if (obj_id_campo != null) {

						var valorCampo = osm_getValor(listaCampos[i]
								+ ".id_campo");

						// Si el id del campo es igual al id campo de la
						// consulta, se actualiza
						if (valorCampo == id_campo) {

							setValorFormatoCampo(listaCampos[i] + ".valor",
									valor)

						}

					}

				}
				;

			}

		}

	});

}

function obtenerCamposLlavePrimaria(idGenericoCamposHijo) {
	// Se revisa que campos son llame primaria
	var listaCamposLlavePrimaria = new Array();
	var campos = 1;
	var camposLlavePrimaria = 0;

	var buscar = true;

	while (buscar) {

		var idGenericoCampo = "" + idGenericoCamposHijo + ":" + campos;

		var obj = osm_getObjeto(idGenericoCampo + ".id_formato_campo");

		if (obj == undefined || obj == null) {
			buscar = false;

		} else {
			var obj_llave = osm_getObjeto(idGenericoCampo + ".llaveprimaria");

			// Se revisa si el campo es llame primaria
			if (obj_llave != null && $(obj_llave).val() == 'S') {

				// Se agrega a los listado de campos llave primaria
				listaCamposLlavePrimaria[camposLlavePrimaria] = idGenericoCampo;
				camposLlavePrimaria++

			}

		}

		campos++;
	}

	return listaCamposLlavePrimaria;

}

function obtenerCamposPorIdGenerico(idGenericoCamposHijo) {
	// Se revisa que campos son llame primaria
	var listaCamposEncontrados = new Array();
	var contador = 1;
	var camposEncontrados = 0;

	var buscar = true;

	while (buscar) {

		var idGenericoCampo = "" + idGenericoCamposHijo + ":" + contador;

		var obj = osm_getObjeto(idGenericoCampo + ".id_formato_campo");

		if (obj == undefined || obj == null) {
			buscar = false;

		} else {
			var obj_campo = osm_getObjeto(idGenericoCampo + ".id_campo");

			// Se revisa si el campo es llame primaria
			if (obj_campo != null) {

				// Se agrega a los listado de campos llave primaria
				listaCamposEncontrados[camposEncontrados] = idGenericoCampo;
				camposEncontrados++

			}

		}

		contador++;
	}

	return listaCamposEncontrados;

}

// Agrega las dependencias en los campos que son obligatorios dependiendo de
// otro campo
function agregarDependenciasObligatorios() {
	$(".obligatorio_campo")
			.each(
					function() {
						var id_campo = this.value;
						var id = this.id;
						if (!id.indexOf('PLANTILLA') == 0) {
							var id_lista_dinamica = osm_getValor("lista_dinamica_obligatoriedad_"
									+ id_campo);
							var campo = osm_getValor("campo_obligatoriedad_"
									+ id_campo);
							var obj = buscarCampo(id, campo);
							if (obj == null) {
								alert('El formato se encuentra mal configurado:'
										+ campo);
							} else {
								actualizarCampoDependienteObligatorio(obj.id,
										id_campo, id_lista_dinamica);
								var onchange = "actualizarCampoDependienteObligatorio('"
										+ obj.id
										+ "' ,'"
										+ id_campo
										+ "','"
										+ id_lista_dinamica + "' )";
								$(obj).change(new Function(onchange));
							}
						}
					}

			);

}

// Decide si un campo es obligatorio o no de acuerdo a la lista dinamica
function actualizarCampoDependienteObligatorio(id_campo_ref, id_campo, id_lista) {

	var valor = getValorFormatoCampo(id_campo_ref);

	var id_map = id_lista + "|" + valor;
	var lista = null;
	
	var funcion = "actualizarObligatoriedadCampo('" + id_campo + "', lista);";
	
	if (MAP_LISTAS_DINAMICAS[id_map]) {
		actualizarObligatoriedadCampo(id_campo, MAP_LISTAS_DINAMICAS[id_map]);
	} else {
		jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamica")
			( new Function("lista", funcion), id_lista, valor);
		// MAP_LISTAS_DINAMICAS[id_map] = lista;
	}
}

function actualizarObligatoriedadCampo (id_campo, lista) {
	var id_formatocampo = osm_getValor("formatodatoname_" + id_campo);

	var titulo = osm_getValor(id_formatocampo + ".titulo");
	if (lista != null && lista.list.length > 0) {
		// Se comenta para proyecto MI de SSOC
		//osm_setValor("label_campo_" + id_campo, "(*) " + titulo);
		osm_setValor(id_formatocampo + ".es_obligatorio", "S");
	} else {
		// Se comenta para proyecto MI de SSOC
		//osm_setValor("label_campo_" + id_campo, titulo);
		osm_setValor(id_formatocampo + ".es_obligatorio", "N");
	}
}

function agregarDependenciasOcultables() {
	$(".ocultable_campo")
			.each(
					function() {
						var id_campo = this.value;
						var id = this.id;
						if (!id.indexOf('PLANTILLA') == 0) {

							var id_lista_dinamica = osm_getValor("lista_dinamica_ocultable_"
									+ id_campo);
							var campos = osm_getValor("ocultable_campo_"
									+ id_campo);
							var objs = buscarCampos(id, campos);
							if (objs.length == 0) {
								alert('Campos no encontrados:' + id + ' ' + campos);
							}
							
							var id_objs = objs.map(obj => obj.id);
							
							actualizarCampoDependienteOcultables(id_objs.toString(),
									id_campo, id_lista_dinamica);
							var onchange = "actualizarCampoDependienteOcultables('"
									+ id_objs.toString()
									+ "' ,'"
									+ id_campo
									+ "','"
									+ id_lista_dinamica + "', this)";
							
							for(var i = 0; i < objs.length; i++) {
								$(objs[i]).change(new Function(onchange));
							}

						}

					});

}

function buscarCampo(id, campo) {
	var scampo = campo.split(":");
	var num = parseInt(scampo[0]);
	var idbase = encontrarNivel(id, num);
	var obj = buscarRuta(idbase, scampo, 1);
	return obj;

}

function buscarCampos(id, campos) {
	var scampos = campos.split(";");
	var objs = [];
	for (var i = 0; i < scampos.length; i++) {
		var id_campo = osm_getValor("ID " + scampos[i]);
		var obj = osm_getObjeto(id_campo);		
		if (obj == null) {
			alert('Campo no encontrado:' + id + ' ' + scampos[i]);
		}
		objs.push(obj);
	}
	return objs;
}

function encontrarNivel(id, niveles) {
	for (var ii = 0; ii < niveles; ii++) {
		id = id.substring(0, id.lastIndexOf(".formatoDatoList"));
	}

	var pos = id.lastIndexOf(".formatoDatoList");
	var posFinal = pos + ".formatoDatoList".length;
	var idbase = id.substring(0, posFinal);

	return idbase;

}

function buscarRuta(idbase, scampo, i) {
	var nombre = scampo[i];
	var hermano;
	
	$("input[id^='" + idbase +":'][id$='.id_formato_campo']").each(function(){
		var base = this.id.replace('.id_formato_campo','');
		var obj = osm_getObjeto(base + ".valor");
		var titulo = osm_getValor(base + ".titulo");

		if (titulo == nombre) {
			hermano = obj;
		}
		
	});
	
	return hermano;
	
}

// Define si un campo se oculta o se muestra segun el valor de la lista dinamica
function actualizarCampoDependienteOcultable(id_campo_ref, id_campo, id_lista) {

	var valor = getValorFormatoCampo(id_campo_ref);

	var id_map = id_lista + "|" + valor;
	var lista = null;
	if (MAP_LISTAS_DINAMICAS[id_map]) {
		lista = MAP_LISTAS_DINAMICAS[id_map];
	} else {
		lista = jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamica")(
				id_lista, valor);
		// MAP_LISTAS_DINAMICAS[id_map] = lista;
	}

	if (lista != null && lista.list.length > 0) {
		$(".bloque_campo_" + id_campo).hide();
	} else {
		$(".bloque_campo_" + id_campo).show();
		obtenerValoresListaPadre($(".bloque_campo_" + id_campo)[0]);
		$(".ui-autocomplete -input").autocomplete('close');
	}

}

function actualizarCampoDependienteOcultables(id_campos_ref, id_campo, id_lista, obj_actual) {
	
	id_campos_ref = id_campos_ref.split(",");
	var valores = [];
	
	for (var i = 0; i < id_campos_ref.length; i++) {
		var valor = null;			
		if (obj_actual && obj_actual.id == id_campos_ref[i]) {
			valor = validarObjeto(obj_actual);
		} else {
			valor = getValorFormatoCampo(id_campos_ref[i]);
		}
		valores.push(valor);
	}
	var funcion = "actualizarCampoDependienteOcultar('" + id_campo + "', lista);";
	
	jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamicaCampos")(
			new Function("lista", funcion),	id_lista, valores);
}

function actualizarCampoDependienteOcultar (id_campo, lista) {
	
	if (lista != null && lista.list.length > 0) {
		$(".bloque_campo_" + id_campo).hide();
	} else {
		$(".bloque_campo_" + id_campo).show();
		obtenerValoresListaPadre($(".bloque_campo_" + id_campo)[0]);
		$(".ui-autocomplete -input").autocomplete('close');
	}
}

function validarObjeto(obj) {
	if (!obj.value || obj.value == "") {
		return obj.value;
	} else if (obj.realvalue) {
		return obj.realvalue;
	} else {
		return obj.value;
	}
}

// Valida los campos por lista dinamica
function ejecutarValidacionesPorListaDinamica() {

	var lista = $(".validacion_campo");

	for (var i = 0; i < lista.length; i++) {

		var id_campo = lista[i].value;
		var id = lista[i].id;

		var id_lista_dinamica = osm_getValor("lista_dinamica_validacion_"
				+ id_campo);
		var campo = osm_getValor("campo_validacion_" + id_campo);

		var scampo = campo.split(":");

		var num = parseInt(scampo[0]);
		var nombre = scampo[1];

		for (var ii = 0; ii < num; ii++) {
			id = id.substring(0, id.lastIndexOf(".formatoDatoList"));
		}

		var pos = id.lastIndexOf(".formatoDatoList");
		var posFinal = pos + ".formatoDatoList".length;
		var idbase = id.substring(0, posFinal);

		var j = 1;
		var isd = idbase + ":" + j + ".id_formato_campo";

		while (osm_getObjeto(isd) != null) {

			var obj = osm_getObjeto(idbase + ":" + j + ".valor");
			var titulo = osm_getValor(idbase + ":" + j + ".titulo");

			isd = idbase + ":" + j + ".id_formato_campo";

			if (titulo == nombre && obj != null) {
				var valido = validarCampoPorListaDinamica(obj.id, id_campo,
						id_lista_dinamica, titulo);
				if (!valido) {
					return false;
				} else {
					isd = null;
				}
			}

			// --

			j++;

		}
		if (isd != null) {
			var valido = validarCampoPorListaDinamica(null, id_campo,
					id_lista_dinamica, null);
			if (!valido) {
				return false;
			}
		}
	}
	return true;

}

// Si la lista retorna valores, no es valido el valor del campo
function validarCampoPorListaDinamica(id_campo_ref, id_campo, id_lista,
		titulo_ref) {
	var valor_ref = null;
	if (id_campo_ref) {
		valor_ref = getValorFormatoCampo(id_campo_ref);
	}
	var id_campo_valor = osm_getValor("formatodatoname_" + id_campo);
	var valor = getValorFormatoCampo(id_campo_valor + ".valor");
	var titulo = osm_getValor(id_campo_valor + ".titulo");
	var mensaje_validacion = osm_getValor("mensaje_validacion_" + id_campo);

	var params = "[" + valor + "],[" + valor_ref + "]";

	var lista = jsonrpc._(
			"listaDinamicaServicio.obtenerValoresListaDinamicaParams")(
			id_lista, params);

	if (!(lista != null && lista.list.length > 0)) {

		if (!osm_esVacio(mensaje_validacion)) {
			alert(mensaje_validacion);
		} else {

			if (id_campo_ref) {
				alert("El campo " + titulo
						+ " no es valido con referencia al campo "
						+ titulo_ref);
			} else {
				alert("El campo " + titulo + " no es valido");
			}
		}
		return false;
	}

	return true;
}

function ver_ayuda_campo(ref_formato_campo) {

	try {
		var ico_obj = osm_getObjeto(ref_formato_campo + ".ico_ayuda");

		$(".area_ayuda").hide();

		if (ico_obj == null) {
			$(".ico_ayuda").fadeTo(200, 0.7);
		} else {
			$(".ico_ayuda").not(ico_obj).fadeTo(200, 0.7);
			$(ico_obj).fadeTo(200, 1);
			$(osm_getObjeto(ref_formato_campo + ".area_ayuda")).show();
		}
	} catch (e) {
	}

}

function ocultar_ayuda_campo () {
	$(".area_ayuda").hide();
}

$(function() {
	$(".ico_ayuda").fadeTo(200, 0.7);
});

/**
 * Obtener el valor de un formato campo, se retorna el realvalue si es una lista
 * dinamica o lista de valores
 * 
 * @param idObjeto
 * @returns
 */
function getValorFormatoCampo(idObjeto) {
	var formatoCampo = osm_getObjeto(idObjeto);
	if (formatoCampo != null && formatoCampo.realvalue) {
		return formatoCampo.realvalue;
	} else {
		return osm_getValor(idObjeto);
	}
}

/**
 * Funcion previa al envio de un formulario, para listas de valores y listas
 * dinamicas
 */
function modificarValoresLista() {
	var inputs = $(".ui-autocomplete-input").each(function() {
		if (this.realvalue) {
			this.value = this.realvalue;
		}
	});// SOLO Si es persona natural
	cerrarVentanaAprobarCarga();
	osm_enviarFormulario('form_cargainteractiva');
}

function setValorFormatoCampo(idObjeto, valor) {
	var objCampo = osm_getObjeto(idObjeto);

	if (osm_esVacio(valor)) {
		$(objCampo).val("");
	} else if (objCampo.map) {
		objCampo.realvalue = valor;
		$(objCampo).val(objCampo.map[valor]);
	} else {
		$(objCampo).val(valor);
	}
}

/*
 * $.ui.autocomplete.prototype._renderMenu = function( ul, items ) { var self =
 * this; $.each( items, function( index, item ) { if (index <
 * RESULTADOS_MAXIMOS) // here we define how many results to // show
 * {self._renderItem( ul, item );} }); }
 */

// ////////////////////////////////////////////////////////////////////////////////////////
function enviarYOAprobar() {
	// Preguntamos tipo de persona del usuario
	// si el usuario es persona natural
	var tipo_persona_cliente = $('#tipo_persona_cliente').val();

	if (tipo_persona_cliente == "true") {
		verVentanaAprobarCarga();
	} else if (tipo_persona_cliente == "") {
		// si el usuario es persona juridica entonces es el preparador y
		// pregunta
		var titulo = jsonrpc._("contenido.obtenerContenido")("/carga_informacion/interactivo/1.1.do","TITULO MENSAJE DE ENVIO DE SOLICITUD");
		var mensaje = jsonrpc._("contenido.obtenerContenido")("/carga_informacion/interactivo/1.1.do","MENSAJE DE ENVIO DE SOLICITUD");
		
		if (titulo && mensaje) {
			s_accept(
					titulo,
					"\n" + mensaje,
					"Enviar", function(r) {
						if (r == true) {
							enviar();
						}
					});
		} else {
			enviar();
		}

	} else {
		return;
	}
}

function cancelar() {
	var titulo = jsonrpc._("contenido.obtenerContenido")("/carga_informacion/interactivo/1.1.do","TITULO MENSAJE DE CANCELACION DE SOLICITUD");
	var mensaje = jsonrpc._("contenido.obtenerContenido")("/carga_informacion/interactivo/1.1.do","MENSAJE DE CANCELACION DE SOLICITUD");
	
	if (titulo && mensaje) {
		s_accept(
				titulo,
				"\n" + mensaje,
				"Aceptar", function(r) {
					if (r == true) {
						osm_enviarFormulario('form_cancelar');
					}
				});
	} else {
		osm_enviarFormulario('form_cancelar');
	}
}

function enviar() {
	osm_verLoader();
	osm_block_window();
	let esSolicitudInicial = $("#contenido_archivos_subidos").data("solicitud-inicial");
	let totalAdjuntos = TOTAL_ADJUNTOS_FORMATO;
    if (esSolicitudInicial) {
        totalAdjuntos += CANT_ARCHIVOS_ADJUNTOS;
    }
	if (totalAdjuntos > 0) {
		$("#total_adjuntos").text(totalAdjuntos);
		mostrarVentanaCarga();	
	}
	setTimeout(function() {
		enviarDefinitivamente();
	}, 500);
}

function enviarDefinitivamente() {
	if (TOTAL_ADJUNTOS_FORMATO > 0) {
				
		try {
			var id_carga = osm_getValorEntero("id_carga");
			ADJUNTOS_PENDIENTES = [];
			ADJUNTOS_FALLIDOS = [];
			ARCHIVOS_ADJUNTADOS = 0;
			var elem = document.getElementById("barra_contenido");
			elem.style.width = '0%';
			
			$(".caja_archivo_adjunto").each(function() {
				if ($(this).first()[0].files[0] != undefined) {
					var fake_form_url = "../adjunto.subirarchivo";
					
					var $fake_form = $("<form>", {
						action : fake_form_url,
						method : "post",
						enctype: "multipart/form-data"
					});
					
					var id_label = $(this).attr("id");
					var id_campo = id_label.substring(5);
					
					var actualizar_descr = $("#descripcion_" + id_campo).val();
					
					var $this = $(this), $clone = $this.clone();
					$this.after($clone).appendTo($fake_form);
					this.name = "caja_archivo_adjunto";
					this.id = "caja_archivo_adjunto";
					
					var id_archivo_adjunto = jsonrpc._("archivosAdjJsonServicio.obtenerSiguiente")();
					
					var id_tipo_archivo = $("#id_tipo_archivo_" + id_campo).val();
					
					$("<input>").attr({
						name : "id_carga",
						id : "id_carga",
						value : id_carga,
						type: "hidden"
					}).appendTo($fake_form);
					
					$("<input>").attr({
						name : "id_archivo_adjunto",
						id : "id_archivo_adjunto",
						value : id_archivo_adjunto,
						type: "hidden"
					}).appendTo($fake_form);
					
					$("<input>").attr({
						name : "id_instancia",
						id : "id_instancia",
						value : "",
						type: "hidden"
					}).appendTo($fake_form);
					
					$("<input>").attr({
						name : "descripcion_archivo_adj",
						id : "descripcion_archivo_adj",
						value : actualizar_descr,
						type: "hidden"
					}).appendTo($fake_form);
					
					$("<input>").attr({
						name : "id_tipo_archivo",
						id : "id_tipo_archivo",
						value : id_tipo_archivo,
						type: "hidden"
					}).appendTo($fake_form);
					
					$(document.body).append($fake_form);
					
					ADJUNTOS_PENDIENTES.push($fake_form);
				}
			});
			
			SIGUIENTE_ADJUNTO = -1;
			subirArchivos();
			
		}catch (err){
			osm_alert("Ocurrio un error adjuntando los archivos\n" + err);
			ok = false;
		}

	} else {
		finalizarEnvio();
	}
}

function finalizarEnvio() {
	var capturar_pdf = jsonrpc._("configuracionServicio.obtenerValorByEtiqueta")("CAPTURAR_FORMULARIO");
	if ( capturar_pdf && capturar_pdf === "true") {
		capturarFormulario("bloque_form_cargainteractiva", ".input-caja.caja_archivo_adjunto", modificarValoresLista);			
	} else {
		modificarValoresLista();
	}
}

function subirArchivos() {
	
	var options = {
		success: subirArchivos,
		error: errorCargueArchivos,
		async: true,
	};
		
	SIGUIENTE_ADJUNTO++;
	
	var elem = document.getElementById("barra_contenido");
	if (SIGUIENTE_ADJUNTO >= TOTAL_ADJUNTOS_FORMATO) {
		//document.getElementById("textBar").innerHTML = "Carga de archivos finalizada";
		elem.style.width = '100%';
		cerrarSeccionBarraCarga();
		
		validarCargueArchivosExitoso();
	} else {
		var num = SIGUIENTE_ADJUNTO * 100 / TOTAL_ADJUNTOS_FORMATO;
		num = num.toFixed(0)
		elem.style.width = num + '%';
		document.getElementById("archivos_adjuntados").innerHTML = SIGUIENTE_ADJUNTO;
		
		var form = ADJUNTOS_PENDIENTES[SIGUIENTE_ADJUNTO];
		ARCHIVOS_ADJUNTADOS++;
		
		if (validarS3Adjuntos()) {
			prepararAdjuntoS3(form, subirArchivos, errorCargueArchivos, "caja_archivo_adjunto", "descripcion_archivo_adj", "id_tipo_archivo");
		} else {
			var descripcion = $(form).find("#descripcion_archivo_adj").val();
			var encodedDescripcion = encodeURIComponent(descripcion);
			$(form).find("#descripcion_archivo_adj").val(encodedDescripcion);
			form.ajaxSubmit(options);
			$(form).find("#descripcion_archivo_adj").val(descripcion);		
		}
		
	}
	
}

function errorCargueArchivos() {
	var form_error = ADJUNTOS_PENDIENTES[SIGUIENTE_ADJUNTO];
	var adjunto = $(form_error).find("#caja_archivo_adjunto").first()[0].files[0];
	console.log("Error cargue archivo " + adjunto.name + " - " + adjunto.size);
	subirArchivos();
}

function validarCargueArchivosExitoso() {
	ADJUNTOS_FALLIDOS = [];
	var element = document.getElementById("lista_error_adjuntos");
	element.innerHTML = '';
	for (var i = 0; i < ADJUNTOS_PENDIENTES.length; i++) {
		var form = ADJUNTOS_PENDIENTES[i];
		var id_archivo_adjunto = $(form).find("#id_archivo_adjunto").first()[0].value;
		if (id_archivo_adjunto) {
			var validacion = null
			try {
				validacion = jsonrpc._("archivosAdjJsonServicio.obtenerArchivoAdjunto")(id_archivo_adjunto);					
			} catch (e) {
				console.log(e);
				i--;
				continue;
			}
			if(!validacion) {
				var archivo_errado = $(form).find("#caja_archivo_adjunto").first()[0].files[0];
				var tag_item = document.createElement("li");
				var size = archivo_errado.size/1024/1024;
				size = size.toFixed(2);
				var texto_archivo = document.createTextNode(archivo_errado.name + " - " + size + " MB");
				tag_item.appendChild(texto_archivo);
				
				element.appendChild(tag_item);
				
				ADJUNTOS_FALLIDOS.push(form);
			}
		}
	}
	if (ADJUNTOS_FALLIDOS.length > 0) {
		mostrarModalErrorAdjuntos();
	} else {
		finalizarEnvio();	
	}
}

function reintentarArchivosFallidos() {
	ADJUNTOS_PENDIENTES = ADJUNTOS_FALLIDOS;
	TOTAL_ADJUNTOS_FORMATO = ADJUNTOS_FALLIDOS.length;
	$("#total_adjuntos").text(TOTAL_ADJUNTOS_FORMATO);
	$("#archivos_adjuntados").text(0);
	SIGUIENTE_ADJUNTO = -1;
	
	var elem = document.getElementById("barra_contenido");
	elem.style.width = '0%';
	
	mostrarVentanaCarga();
	mostrarSeccionBarraCarga()
	subirArchivos();
}

function validarCargueArchivos() {
	if (ADJUNTOS_FALLIDOS.length > 0) {
		var no_encontrados = 0;
		var adjuntos_notificacion = [];
		for (var i = 0; i < ADJUNTOS_FALLIDOS.length; i++) {
			
			var form = ADJUNTOS_FALLIDOS[i];
			var id = $(form).find("#id_archivo_adjunto").first()[0].value;
			
			var validacion = null;
			
			if (id) {
				try {
					validacion = jsonrpc._("archivosAdjJsonServicio.obtenerArchivoAdjunto")(id);					
				} catch (e) {
					console.log(e);
					i--;
					continue;
				}
			}
			
			if(!validacion) {
				var archivo_errado = $(form).find("#caja_archivo_adjunto").first()[0].files[0];
				var tag_item = document.createElement("li");
				var size = archivo_errado.size/1024/1024;
				size = size.toFixed(2);
				var texto_archivo = document.createTextNode(archivo_errado.name + " - " + size + " MB");
				tag_item.appendChild(texto_archivo);
				var lista = document.getElementById("lista_error_adjuntos");
				lista.appendChild(tag_item);
				no_encontrados++;
				
				adjuntos_notificacion.push(archivo_errado.name);
			}
		}
		
		if (no_encontrados > 0) {
			var idcarga = $("#id_carga").val();
			jsonrpc._("notificacionMIServicio.notificarErrorCargueArchivos")(idcarga, adjuntos_notificacion);
			mostrarModalErrorAdjuntos();
			return;
		}
	}
	
	finalizarEnvio();		
	
}

function fin_carga_archivo() {

	var elem = document.getElementById("barra_contenido");
	ARCHIVOS_FINALIZADOS++;
	if (ARCHIVOS_FINALIZADOS >= TOTAL_ADJUNTOS_FORMATO) {
		document.getElementById("textBar").innerHTML = "Carga de archivos exitosa!";
		$("#div_exito").show();
		elem.style.width = '100%';
	} else {
		var num = ARCHIVOS_FINALIZADOS * 100 / TOTAL_ADJUNTOS_FORMATO;
		num = num.toFixed(0)
		elem.style.width = num + '%';
		document.getElementById("archivos_adjuntados").innerHTML = ARCHIVOS_FINALIZADOS;
	}

}

function cerrarVentanaAprobarCarga() {
	osm_setVisible("vn_aprobarcarga", false);
	osm_mostrarSelects("bodyContent");
}

function removeFile(id_campo) {
	$("#caja_" + id_campo).val(null);
	$("#trash_" + id_campo).addClass("ic-hide");
	osm_setValor("label_" + id_campo, "");
	$(".variable_" + id_campo).val("");
	TOTAL_ADJUNTOS_FORMATO = TOTAL_ADJUNTOS_FORMATO - 1;
}

function cargarEstilos(){
	var id_formato = osm_getValor("Variable.id_formato");
	var estilos = jsonrpc._("formatoServicio.obtenerEstilosFormato")(mostrarEstilos, id_formato);
}

function mostrarEstilos(result){
	if (result != null){
		var mapa = result.map;
		var keys = Object.keys(mapa);
		for (var i = 0; i < keys.length; i++) {
			var label = keys[i];
			var properties = mapa[label].map;
			var keyP = Object.keys(properties);
			for (var j = 0; j < keyP.length; j++) {
				var property = keyP[j];
				var value = properties[property];
				$("." + label).css(property, value);
			}
		}
	}

	window.setTimeout(function(){ $("#area_pagina").show();} , 200)
	
}

function agregarValidacionesEnLinea(){
	
	var formulario = osm_getObjeto("form_cargainteractiva");
	var inputs = formulario.getElementsByTagName("input");
	for (var ff = 0; ff < inputs.length; ff++) {
		var inputelement = inputs[ff];
		var idelement = inputelement.id;
		var idobjeto = idelement.substring(0, idelement.indexOf("id_campo") - 1);
		if (idelement.indexOf("FormatoDato.") == 0 
				&& idelement.indexOf("id_campo") > 0
				&& osm_esVerdad(osm_getValor(idobjeto + ".validacion_servicio"))){
			var elem = document.getElementById(idobjeto + '.valor');
			$(elem).change({msg: idobjeto}, function (event) {
				validaCampoEnLinea(event.data.msg);
				});
			}
		
	}
	
}

function limpiarCampo(obj) {
	if (obj) {
		obj.value = "";
		obj.realvalue = "";
	}	
}

function validaCampoEnLinea(idobjeto){
	var titulo = osm_getValor(idobjeto + ".titulo");
	var id_campo = osm_getValor(idobjeto + ".id_campo");
	var id_formato_campo = osm_getValor(idobjeto + ".id_formato_campo");
	var nombre_campo = jsonrpc._("formatoServicio.obtenerNombreCampoPorIdCampo")(id_campo);
	var valor_campo = getValorFormatoCampo(idobjeto + ".valor");
	var id_carga = osm_getValorEntero("id_carga");
	
	var campoObj = {
			titulo: titulo,
			id_campo: id_campo,
			id_formato_campo: id_formato_campo,
			nombre_campo: nombre_campo,
			valor: valor_campo
	};
	
	try {
		var id_formato = osm_getValorEntero("Variable.id_formato");
		var resp = jsonrpc._("formatoServicio.validarCamposFormato")(id_formato, [campoObj], id_formato_campo, id_carga);
		if (!resp.valido){
			var obj = osm_getObjeto(idobjeto+".valor");
			setTimeout(function(){
				limpiarCampo(obj)		
			}, 500);
			s_alert_info($("#pestana_titulo").val(), resp.mensaje_validacion, function(){});
			if (resp.bloquear_formulario){
				bloquearFormulario(idobjeto);
			}
		}else {
			if (resp.mostrar_mensaje) {
				s_alert_info($("#pestana_titulo").val(), resp.mensaje_validacion, function(){});
			}
			desbloquearFormulario();
		}
	} catch (e) {
		alert("Ocurrio un error realizando la validacion en linea. Por favor intente de nuevo");
		return false;
	}
}

function bloquearFormulario(idobjeto){
	ID_ACTIVOS = new Array();
	$("form#form_cargainteractiva :input[id$='.valor']").each(function(){
		 if (idobjeto + ".valor" != this.id){
			 $(this).prop("disabled", true);
			 ID_ACTIVOS.push(this.id);
		 }
		});
}

function desbloquearFormulario(){
	$("form#form_cargainteractiva :input[id$='.valor']").each(function(){
		if (ID_ACTIVOS.indexOf(this.id) > -1){
			$(this).prop("disabled", false);
		}
	});
}

function mostrarModalPdf() {

	$("#msg_modal_pdf").show();
	$("#msg_modal_carga_archivos").hide();
	$("#vn_msg_envio").show();
}

function mostrarVentanaCarga() {
	$("#msg_modal_pdf").hide();
	$("#msg_modal_error_adjuntos").hide();
	$("#msg_modal_carga_archivos").show();
	$("#vn_msg_envio").show();
}

function cerrarSeccionCargaArchivos() {
	$("#msg_modal_carga_archivos").hide();
}

function mostrarSeccionBarraCarga() {
	$("#seccion_carga").show();
	$("#textBar").show();
}

function cerrarSeccionBarraCarga() {
	$("#seccion_carga").hide();
	$("#textBar").hide();
}

function cerrarModalPdf() {
	$("#vn_msg_envio").hide();
}

function ocultarModalPdf() {
	$("#msg_modal_pdf").hide();
}

function mostrarModalErrorAdjuntos(){
	cerrarSeccionCargaArchivos();
	cerrarSeccionBarraCarga();
	ocultarModalPdf();
	$("#msg_modal_error_adjuntos").show();
	osm_unblock_window();
}

function cerrarModalErrorAdjuntos(){
	$("#msg_modal_error_adjuntos").hide();
	osm_block_window();
	finalizarEnvio();
}

function cargarMensajes(){
	
	window.setTimeout(function(){
	
		var alerta = osm_trimToNull($("#formato_alerta_html").val());
		
		if(alerta != null){
			s_alert_info($("#pestana_titulo").val(), alerta , function(){});
		}
		
		var formato_encabezado_html = osm_trimToNull($("#formato_encabezado_html").val());
		
		if(formato_encabezado_html != null){
			$("#area_encabezado_html").html(formato_encabezado_html);
		}
		
		var formato_pie_html = osm_trimToNull($("#formato_pie_html").val());
		
		if(formato_pie_html != null){
			$("#area_pie_html").html(formato_pie_html);
		}
		
	}, 200);
	
}
function descargaPlantilla(id_plantilla) {
	var fake_form_url = "../../DescargaArchivoServlet";

	var $fake_form = $("<form>", {
		action : fake_form_url,
		method : "post",
	});

	$("<input>").attr({
		name : "id_archivo",
		value : id_plantilla,
	}).appendTo($fake_form);

	$(document.body).append($fake_form);

	$fake_form.submit();
}

function descargarPlantillaParametrizada(tipo_proceso,tipo_solicitante,pl_soc,pl_pnc,pl_pnnc,pl_se){
	var fake_form_url = "../../DescargaArchivoServletRI";

	var $fake_form = $("<form>", {
		action : fake_form_url,
		method : "post",
	});
	
	var id_plantilla=0;
	
	if (tipo_solicitante == "1") id_plantilla = pl_soc;
	if (tipo_solicitante == "2") id_plantilla = pl_pnc;
	if (tipo_solicitante == "3") id_plantilla = pl_pnnc;
	if (tipo_solicitante == "5") id_plantilla = pl_se;
	
	$("<input>").attr({
		name : "tipo_proceso",
		value : tipo_proceso,
	}).appendTo($fake_form);
	$("<input>").attr({
		name : "tipo_solicitante",
		value : tipo_solicitante,
	}).appendTo($fake_form);
	$("<input>").attr({
		name : "num_plantilla",
		value : id_plantilla,
	}).appendTo($fake_form);

	$(document.body).append($fake_form);

	$fake_form.submit();
}

function precargarCampos() {
	// valores para los nombres de archivos adjuntos
	if (LISTAS_PENDIENTES > 0) {
		window.setTimeout(function(){
			precargarCampos();
		}, 200);
		return;
	} 
	var elementos = document.getElementsByClassName("lista_valor_inicial");
	for( var i = 0; i < elementos.length; i++) {
		
		var id = elementos[i].id;
		id = id.replace(".valor_inicial",".valor");
		var id_lista_dinamica = elementos[i].value;
		realizarPrecargaCampo(id, id_lista_dinamica);
		
				
	}
	
	osm_block_window();
	osm_verLoader();
	
	window.setTimeout(function () {
		initDoom(); 
		osm_unblock_window();
		osm_ocultarLoader();
	}, 1000)
		
}

function incrementarIntento(id) {
	if (INTENTOS_PRECARGA.get(id) == undefined) {
		INTENTOS_PRECARGA.set(id, 1);
	} else {
		INTENTOS_PRECARGA.set(id, INTENTOS_PRECARGA.get(id)+1);
	}
}

function realizarPrecargaCampo (id, id_lista_dinamica) {
	var obj = osm_getObjeto(id);
	if (obj) {
		if (obj.map && obj.map.length == 0) {
			var datos = DATOS_PADRE_PRECARGA.filter( x => x["id_hijo"] == id );
			
			if (datos && datos.length > 0) {
				actualizarValorPorRef(id, datos[0]["padre"], datos[0]["id_lista"], true);					
			}
			
			incrementarIntento(id);
			
			if (INTENTOS_PRECARGA.get(id) <= 5) {
				window.setTimeout(function(){
					realizarPrecargaCampo(id, id_lista_dinamica);
				}, 200);
				return;
			}
		}
		
		var parametros = null;
		if (typeof obtenerParametroListaDinamica === "function"){
			parametros = obtenerParametroListaDinamica(id, id_lista_dinamica);
		}
		
		var funcion = "callbackPrecargaCampos('" + id + "', lista );";
		
		var lista = null;
		if (parametros) { 
			if (parametros.length > 1) {
				var parametrosStr = "[" + parametros.join("],[") + "]";
				jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamicaParams")(
						new Function("lista", funcion), id_lista_dinamica, parametrosStr);
			} else {
				jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamica")(
						new Function("lista", funcion),	id_lista_dinamica, parametros[0]);
			}
		} else {
			jsonrpc._("listaDinamicaServicio.obtenerValoresListaDinamica")(
					new Function("lista", funcion), id_lista_dinamica, null);
		}
	
		
	} else {
		console.log("Campo con id: " + id + " no fue encontrado para precargar");
	}
}

function callbackPrecargaCampos(id , lista) {
	if (lista) {
		lista = lista.list;
		if (lista.length > 0) {
			var valor = lista[0].id;
			var nombre = lista[0].nombre;
			setValorFormatoCampo(id, valor);
		}
	}
}

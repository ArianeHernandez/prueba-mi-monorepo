/**
 * ========================================================================= Copyright (c) 2012 ITC S.A.S.. Todos los derechos reservados. ==========================================================================
 * 
 * Archivo - $Id$
 * 
 * Numero de version - $Revision$ Ultima actualizacion - $Date$ Actualizado por - $Author$
 * 
 * Librerias principal de osmocore.
 * 
 * ==========================================================================
 */
// ------------------------------------------------
var OSM_TIMERID = 0;
var OSM_TIMERFUNCTION = new Array();
var OSM_TIMERPARAM_1 = new Array();
var OSM_TIMERPARAM_2 = new Array();
var OSM_TIMERPARAM_3 = new Array();
var OSM_TIMERPARAM_4 = new Array();
var OSM_TIMERPARAM_5 = new Array();

function osm_encode(str) {
	var result = "";
	for (var i = 0; i < str.length; i++) {
		result += str.charCodeAt(i).toString(16);
	}
	return result;
}

function osm_executetimerfunction(id) {
	try {
		var _FF = OSM_TIMERFUNCTION[id];
		_FF(OSM_TIMERPARAM_1[id], OSM_TIMERPARAM_2[id], OSM_TIMERPARAM_3[id], OSM_TIMERPARAM_4[id], OSM_TIMERPARAM_5[id]);
	} catch (_EH) {

		SimpleLogger.setError(" [ osm_executetimerfunction: " + _EH + " ]", null);
	}
}

function osm_timer(func, interval, params) {
	OSM_TIMERFUNCTION[OSM_TIMERID] = func;
	OSM_TIMERPARAM_1[OSM_TIMERID] = null;
	OSM_TIMERPARAM_2[OSM_TIMERID] = null;
	OSM_TIMERPARAM_3[OSM_TIMERID] = null;
	OSM_TIMERPARAM_4[OSM_TIMERID] = null;
	OSM_TIMERPARAM_5[OSM_TIMERID] = null;

	try {
		OSM_TIMERPARAM_1[OSM_TIMERID] = params[0];
	} catch (e) {
	}
	try {
		OSM_TIMERPARAM_2[OSM_TIMERID] = params[1];
	} catch (e) {
	}
	try {
		OSM_TIMERPARAM_3[OSM_TIMERID] = params[2];
	} catch (e) {
	}
	try {
		OSM_TIMERPARAM_4[OSM_TIMERID] = params[3];
	} catch (e) {
	}
	try {
		OSM_TIMERPARAM_5[OSM_TIMERID] = params[4];
	} catch (e) {
	}

	window.setInterval("osm_executetimerfunction(" + OSM_TIMERID + ")", interval);
	OSM_TIMERID++;
}

function osm_timeout(func, interval, params) {
	OSM_TIMERFUNCTION[OSM_TIMERID] = func;
	OSM_TIMERPARAM_1[OSM_TIMERID] = null;
	OSM_TIMERPARAM_2[OSM_TIMERID] = null;
	OSM_TIMERPARAM_3[OSM_TIMERID] = null;
	OSM_TIMERPARAM_4[OSM_TIMERID] = null;
	OSM_TIMERPARAM_5[OSM_TIMERID] = null;

	try {
		OSM_TIMERPARAM_1[OSM_TIMERID] = params[0];
	} catch (e) {
	}
	try {
		OSM_TIMERPARAM_2[OSM_TIMERID] = params[1];
	} catch (e) {
	}
	try {
		OSM_TIMERPARAM_3[OSM_TIMERID] = params[2];
	} catch (e) {
	}
	try {
		OSM_TIMERPARAM_4[OSM_TIMERID] = params[3];
	} catch (e) {
	}
	try {
		OSM_TIMERPARAM_5[OSM_TIMERID] = params[4];
	} catch (e) {
	}

	window.setTimeout("osm_executetimerfunction(" + OSM_TIMERID + ")", interval);
	OSM_TIMERID++;
}

// ------------------------------------------------

var OSM_NEXT_ID = 0;
var OSM_LIST_ID = new Array();

var OSM_NEXT_FUNCTION_ID = 0;
var OSM_LIST_FUNCTION = new Array();

function osm_listen(event, elem, func, cancelreturnvalue) {

	var _elem = null;

	try {
		_elem = document.getElementById(elem);
	} catch (_EN) {
	}

	if (_elem != null) {
		elem = _elem;
	}

	try {

		if (elem == window || elem == document || elem.tagName) {
			if (!elem.WITHOSMID) {
				elem.WITHOSMID = true;
				OSM_NEXT_ID++;
				elem.OSMID = OSM_NEXT_ID;
				OSM_LIST_ID[OSM_NEXT_ID] = elem;
			}

			OSM_NEXT_FUNCTION_ID++;
			OSM_LIST_FUNCTION[OSM_NEXT_FUNCTION_ID] = func;

			var cancel = "false";

			if (cancelreturnvalue) {
				cancel = "true";
			}

			var functionbody = "if(!event){event = window.event;} if(!event.target){ event.target = event.srcElement; } event.event_type = '" + event + "'; var rfunc = OSM_LIST_FUNCTION[" + OSM_NEXT_FUNCTION_ID + "]; var robj = OSM_LIST_ID[" + elem.OSMID + "]; try{ if (event.wheelDelta){ event.delta = event.wheelDelta / 120;} else if (event.detail ){ event.delta = -event.detail / 3; } }catch(E){}; robj.tmposmf = rfunc; var rtrans = robj.tmposmf(event, robj); if(rtrans==undefined){ rtrans = true;}; if(!rtrans || " + cancel + "){ if (event.preventDefault){ event.preventDefault(); }; event.returnValue = false; } ";

			var nfunc = new Function("event", functionbody);

			if (elem.addEventListener) {
				if (event == "mousewheel") {
					event = "DOMMouseScroll";
				}

				elem.addEventListener(event, nfunc, false);

			} else if (elem.attachEvent) {
				elem.attachEvent("on" + event, nfunc);
			}
		}
	} catch (_EN) {
	}
}

// ------------------------------------------------
// Funciones de bloqueo de ventana

function osm_block_window() {
	try {
		osm_ocultarSelects("bodyContent");
		osm_setVisible("block_window", true, true);
	} catch (e) {
	}
}

function osm_unblock_window() {
	try {
		osm_mostrarSelects("bodyContent");
		osm_setVisible("block_window", false);
		osm_ocultarLoader();
	} catch (e) {
	}
}

// ------------------------------------------------
// Funciones de ventana

function osm_getWindowSize() {
	var myWidth = 0, myHeight = 0;
	if (typeof (window.innerWidth) == 'number') {
		myWidth = window.innerWidth;
		myHeight = window.innerHeight;
	} else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
		myWidth = document.documentElement.clientWidth;
		myHeight = document.documentElement.clientHeight;
	} else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
		myWidth = document.body.clientWidth;
		myHeight = document.body.clientHeight;
	}

	return [ myWidth, myHeight ];
}

// ------------------------------------------------

function osm_getElementPosition(obj) {
	var curleft = curtop = 0;

	if (obj.offsetParent) {
		do {
			curleft += obj.offsetLeft - obj.scrollLeft;
			curtop += obj.offsetTop - obj.scrollTop;
		} while ((obj = obj.offsetParent));
	}

	return [ curleft, curtop ];
}

// ------------------------------------------------
// Funciones de navegacion

function osm_go(url, block) {
	if (block == undefined || block) {
		osm_verLoader();
		osm_block_window();
	}

	var des = CONTEXTPATH + "/" + url;

	window.setTimeout("location.href = '" + des + "';");
}

// ------------------------------------------------
// Funciones Finacieras

function osm_pago(monto, interes, nper) {
	osm_pago_in = Math.pow(1 + interes, nper);

	osm_pago_cuota = monto * (interes * osm_pago_in) / (osm_pago_in - 1);

	return osm_pago_cuota;
}

function osm_formatoMoneda(num, currency) {

	if (num == undefined || num == null) {
		return "";
	}

	if (currency == undefined) {
		currency = "$";
	}
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.5000000000000000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') + currency + num + '.' + cents);
}

function osm_formatoMoneda2(num, currency) {

	if (num == undefined || num == null) {
		return "";
	}

	if (currency == undefined) {
		currency = "$";
	}
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.5000000000000000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + '.' + num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') + currency + num + ',' + cents);
}

function osm_formatoPorcentaje(num) {
	if (num == undefined || num == null) {
		return "";
	}

	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	num = Math.floor(num * 100 + 0.5000000000000000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + '.' + num.substring(num.length - (4 * i + 3));
	return (num + ',' + cents + '%');
}

// ------------------------------------------------
// funciones html

var PLANTILLAS = new Array();

function osm_construirHTML(id_padre, id_plantilla, parametros, antes) {

	var htmlplantilla = PLANTILLAS[id_plantilla];
	if (htmlplantilla == null) {
		htmlplantilla = osm_getValor(id_plantilla);
		PLANTILLAS[id_plantilla] = osm_getValor(id_plantilla);
		$("#" + id_plantilla).empty();
	}

	if (parametros && parametros != null) {
		var tags = osm_getKeys(parametros);

		for (var j = 0; j < tags.length; j++) {
			var parametro;
			var tag = tags[j];
			if (parametros[tag] == null) {
				parametro = '';
			} else {
				parametro = parametros[tag];
			}
			var remplazarTag = tag;
			if (!isNaN(parseInt(tag, 10))) {
				remplazarTag = (parseInt(tag, 10) + 1);
				remplazarTag = '[ ' + (remplazarTag) + ' ]';
			}
			htmlplantilla = osm_remplazar(htmlplantilla, remplazarTag, parametro);
		}
	}
	if (antes) {
		$(osm_getObjeto(id_padre)).prepend(htmlplantilla);
	} else {
		$(osm_getObjeto(id_padre)).append(htmlplantilla);
	}
}

function osm_getKeys(array) {

	var keys = [];
	for ( var i in array)
		if (array.hasOwnProperty(i)) {
			keys.push(i);
		}
	return keys;
}

// ------------------------------------------------
// Funciones Para fecha
// Retorna la fecha en formato DD/MM/YY (aplica para los valores retornados en java)

function osm_getFecha(milliseconds) {
	//
	if (milliseconds == null) {
		return "";
	}
	var date = new Date(milliseconds * 1000);
	var dia = osm_completarTiempo(date.getDate());
	var mes = osm_completarTiempo(date.getMonth() + 1);
	return dia + "/" + mes + "/" + date.getFullYear();

}
function osm_completarTiempo(str) {
	if (str < 10) {
		str = "0" + str;
	}
	return str;
}

/**
 * Retorna la hora en formato string, por defecto se retorna en formato am/pm.
 * 
 * @param milliseconds -
 *            milisegundo java
 * @param formato_militar -
 *            si es verdadero se retorna la hora en formato militar
 * @returns
 */
function osm_getHora(milliseconds, formato_militar) {
	//
	if (milliseconds == null) {
		return "";
	}
	var date = new Date(milliseconds * 1000);

	var hour = date.getHours();
	var mins = date.getMinutes();
	var secs = date.getSeconds();
	if (!formato_militar) {
		var ap = "AM";
		if (hour > 11) {
			ap = "PM";
		}
		if (hour > 12) {
			hour = hour - 12;
		}
		if (hour == 0) {
			hour = 12;
		}
	}

	return osm_completarTiempo(hour) + ":" + osm_completarTiempo(mins) + ":" + osm_completarTiempo(secs) + " " + ap;

}

// --------------------------
function osm_getObjetoFecha(fecha) {

	var objDate = new Date();
	try {
		objDate = new Date(fecha.substring(6, 10), parseInt(fecha.substring(3, 5), 10) - 1, fecha.substring(0, 2), 0, 0, 0, 0)
	} catch (e) {
	}

	return objDate;
}
// ------------------------------------------------
// funciones de String

function osm_remplazar(orig, oold, nnew) {

	try {
		var cc = orig;

		while (cc.indexOf(oold) >= 0) {
			cc = cc.replace(oold, nnew);
		}

		return cc;
	} catch (e) {
		return orig;
	}
}

function osm_remplazarRegex(orig, oold, nnew) {
	// TODO revizar, no reemplazar
	try {
		var cc = orig;
		var regx = new RegExp(oold, 'g');

		while (cc.indexOf(oold) >= 0) {
			cc = cc.replace(regx, nnew);
		}

		return cc;
	} catch (e) {
		return orig;
	}
}

function osm_regexTest(str, regex) {
	var rex = new RegExp(regex);
	var res = str.match(rex);
	if (res) {
		return true;
	} else {
		return false;
	}
}

function osm_validarCorreo(correo) {
	try {
		return jsonrpc.pcore.validarCorreo(correo);
	} catch (e) {
		SimpleLogger.setError("Error en servicio json validarCorreo", e);
	}
	// Si falla la validacion por jsonrpx se hace por expresion regular
	return osm_regexTest(correo, /^[\w\_\-\.]+@([\w\_\-]+\.)+[\w\_\-]{2,4}$/);
}
function osm_validarNumero(cadena) {
	var redigit = /^[\d]+$/;
	return osm_regexTest(cadena, redigit);
}

function osm_validarDecimal(cadena, places) {
	if (places == undefined) {
		var redigit = /^[\d]+(\.[\d]*)?$/;
	} else {
		var redigit = new RegExp("^[\\d]+(\\.[\\d]{0," + places + "})?$");
	}
	return osm_regexTest(cadena, redigit);
}

function osm_validar2Decimal(cadena) {
	var redigit = /^[\d]+(\.[\d]?[\d]?)?$/;
	return osm_regexTest(cadena, redigit);
}

function osm_validarDecimalConSigno(cadena, places) {
	if (places == undefined) {
		var redigit = /^[+-]?[\d]+(\.[\d]*)?$/;
	} else {
		var redigit = new RegExp("^[+-]?[\\d]+(\\.[\\d]{0," + places + "})?$");
	}
	return osm_regexTest(cadena, redigit);
}

function osm_validarIP(cadena) {
	// var redigit = /^[0-255]{1,3}\.[0-255]{1,3}\.[0-255]{1,3}\.[0-255]{1,3}$/;
	var redigit = /(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	return osm_regexTest(cadena, redigit);
}

/**
 * 
 * @param cadena
 *            String
 * @param numeros
 *            boolean
 * @param caracteresEspeciales
 *            boolean
 * @returns resultadoValidacion boolean
 * 
 * Los caracteres especiales validados son: punto, apostrofe, guion y espacio
 */
function osm_validarCadenaCaracteres(cadena, numeros, caracteresEspeciales) {
	var realfa = /^[\w]+$/;
	var realfanumerico = /^[A-Za-z0-9]+$/;
	var realfanumSpecials = /^[A-Za-z0-9·ÈÌÛ˙¡…Õ”⁄Ò—\.\'\- ]+$/;
	var realfaSpecials = /^[A-Za-z·ÈÌÛ˙¡…Õ”⁄Ò—\.\'\- ]+$/;
	if (numeros && !caracteresEspeciales) {
		return osm_regexTest(cadena, realfanumerico);
	} else if (!numeros && caracteresEspeciales) {
		return osm_regexTest(cadena, realfaSpecials);
	} else if (!numeros && !caracteresEspeciales) {
		return osm_regexTest(cadena, realfa);
	} else if (numeros && caracteresEspeciales) {
		return osm_regexTest(cadena, realfanumSpecials);
	}
}

function osm_validarAlfabetico(cadena) {
	var alfabetico = /^[A-Za-z·ÈÌÛ˙¡…Õ”⁄Ò—\.\'\- ]+$/;
	return osm_regexTest(cadena, alfabetico);
}

function osm_validarAlfanumerico(cadena) {
	var alfabetico = /^[A-Za-z·ÈÌÛ˙¡…Õ”⁄Ò—0-9\.\'\- ]+$/;
	return osm_regexTest(cadena, alfabetico);
}

function osm_validarTelefono(cadena) {
	var telefonalfanumerico = /[0-9]+/;
	return osm_regexTest(cadena, telefonalfanumerico);
}

/**
 * Valida el formato de la fecha dd/mm/yyyy
 * 
 * @param fecha
 *            String
 * @return boolean
 */
function osm_validarFormatoFecha(fecha) {
	// regular expression to match required date format
	var regexdate = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
	return osm_regexTest(fecha, regexdate);
}

function osm_escape_html(txt) {
	return $('<div/>').text(txt).html();
}

function osm_strlimpiar(val) {
	if (osm_esVacio(val)) {
		return "";
	}

	return osm_trim(val);
}

/**
 * Limpia los espacios en blanco al final y al principio. Si es null retorna cadena vacia.
 * 
 * @param cadena
 * @returns
 */
function osm_trimToEmpty(cadena) {
	var a = osm_trim(cadena);
	if (a == null) {
		a = "";
	}
	return a;
}

function osm_trimToNull(cadena) {
	var a = osm_trim(cadena);
	if (a == "") {
		a = null;
	}
	return a;
}

/**
 * Limpia los espacios en blanco al final y al principio.
 * 
 * @param cadena
 * @returns
 */
function osm_trim(cadena) {

	if (cadena == null) {
		return null;
	}

	cadena = osm_remplazar(cadena, "&nbsp;", " ");

	for (var i = 0; i < cadena.length;) {
		if (cadena.charAt(i) == " ")
			cadena = cadena.substring(i + 1, cadena.length);
		else
			break;
	}

	for (i = cadena.length - 1; i >= 0; i = cadena.length - 1) {
		if (cadena.charAt(i) == " ")
			cadena = cadena.substring(0, i);
		else
			break;
	}

	return cadena;
}

function osm_nvl(val, def) {

	if (osm_esVacio(val)) {
		return def;
	}

	return val;
}

function osm_esVacio(str) {

	str = osm_trim(str);
	try {

		if (str == null || str.toLowerCase() == "null") {
			return true;
		}

		var cstr = "A" + str;
		if (cstr.length == 1) {
			return true
		}
	} catch (_e) {
	}

	return false;
}

function osm_existeElemento(id) {

	var obj = osm_getObjeto(id);

	if (obj == null) {
		return false;
	}
	return true;
}

// ------------------------------------------------
// deprecated
function osm_esSeleccionado(idd) {

	try {
		var radioObj = document.getElementById(idd);
		return radioObj.checked;
	} catch (_e) {
	}

	return false;
}

function osm_seleccionarTexto(idd, tiempo) {

	var obj = osm_getObjeto(idd);

	if (obj == null || (obj.disabled != undefined && obj.disabled)) {
		return false;
	}

	if (tiempo) {
		window.setTimeout("osm_seleccionarTexto('" + idd + "')", tiempo);
	} else {

		try {
			window.setTimeout(" try{ var _oobj = document.getElementById('" + idd + "'); _oobj.focus(); _oobj.select(); }catch(_EE){ }", 1);
		} catch (_E) {
		}
	}

	return true;

}

function osm_seleccionar(idd, vval) {
	try {
		var _oobj = document.getElementById(idd);

		try {
			_oobj.select();
		} catch (e) {
		}
		try {
			_oobj.setChecked(vval);
		} catch (e) {
		}

		_oobj.checked = vval;
	} catch (E_) {
	}
}

function osm_setVisible(idd, vv, isblock) {
	try {
		var _oobj = document.getElementById(idd);
		if (vv) {
			_oobj.style.display = "";
			if (isblock) {
				_oobj.style.display = "block";
			}
			_oobj.esvisible = true;
		} else {
			_oobj.style.display = "none";
			_oobj.esvisible = false;
		}

	} catch (E_) {
	}
}

function osm_setVisibilidad(idd, vv) {
	try {
		var _oobj = document.getElementById(idd);
		if (vv) {
			_oobj.style.visibility = "visible";
		} else {
			_oobj.style.visibility = "hidden";
		}

	} catch (E_) {
	}
}

function osm_ocultarSelects(idpadre) {

	if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.indexOf("MSIE 6.0") > 0) {

		var padre = osm_getObjeto(idpadre);
		if (padre != null) {
			var eleme = padre.getElementsByTagName("SELECT");
			var ii = 0;
			for (ii = 0; ii < eleme.length; ii++) {
				var oselect = eleme[ii];
				oselect.style.visibility = "hidden";
			}
		}
	}
}

function osm_mostrarSelects(idpadre) {
	var padre = osm_getObjeto(idpadre);
	if (padre != null) {
		var eleme = padre.getElementsByTagName("SELECT");
		var ii = 0;

		for (ii = 0; ii < eleme.length; ii++) {
			var oselect = eleme[ii];
			oselect.style.visibility = "visible";
		}
	}
}

function osm_verLoader() {
	osm_setVisible("block_window_loader", true, true);
}

function osm_ocultarLoader() {
	osm_setVisible("block_window_loader", false);
}
// -------------

function osm_enviarFormulario(idd) {
	try {
		var _oobj = document.getElementById(idd);
		osm_verLoader();
		osm_block_window();
		_oobj.submit();
	} catch (_e) {
		SimpleLogger.setError(" [ osm_enviarFormulario: " + _e + "]", null);
		osm_unblock_window();
	}
}

function osm_setDestinoFormulario(idd, destino) {
	try {
		var _oobj = document.getElementById(idd);
		_oobj.action = destino;
	} catch (_e) {
	}
}

function osm_getObjeto(idd) {
	var _oobj = document.getElementById(idd);
	return _oobj;
}

function osm_esPadre(id_padre, obj_hijo) {

	try {
		while (obj_hijo.tagName != "BODY" && obj_hijo.id != id_padre) {
			obj_hijo = obj_hijo.parentNode;
		}

		if (obj_hijo.id == id_padre) {
			return true;
		}

	} catch (_e) {
	}

	return false;
}

function osm_getObjetoVacio(idd) {
	var _oobj = document.getElementById(idd);

	if (_oobj.tagName == "DIV" || _oobj.tagName == "P" || _oobj.tagName == "TD" || _oobj.tagName == "H1" || _oobj.tagName == "H2" || _oobj.tagName == "H3") {
		_oobj.innerHTML = "";
	}

	if (_oobj.tagName == "INPUT" || _oobj.tagName == "SELECT") {
		_oobj.value = "";
	}

	if (_oobj.tagName == "SELECT") {
		while (_oobj.length > 0) {
			_oobj.remove(_oobj.length - 1);
		}
	}

	return _oobj;
}

function osm_eliminarObjeto(idd) {

	try {
		var obj = osm_getObjeto(idd);
		var parentObject = obj.parentNode;
		parentObject.removeChild(obj);
	} catch (_e) {
	}
}

function osm_setFoco(idd, tiempo) {

	if (tiempo) {
		window.setTimeout("osm_setFoco('" + idd + "')", tiempo);
	} else {

		try {
			window.setTimeout(" try{ var _oobj = document.getElementById('" + idd + "'); _oobj.focus(); }catch(_EE){}", 1);
		} catch (_E) {
		}
	}

}

function osm_mover(idorigen, id_destino, tiempo) {

	if (tiempo) {
		window.setTimeout("osm_mover('" + idorigen + "', '" + id_destino + "')", tiempo);
	} else {
		try {
			var origen = document.getElementById(idorigen);
			var destino = document.getElementById(id_destino);

			destino.appendChild(origen);

		} catch (e) {
		}
	}
}

function osm_setClassname(idd, cn) {
	try {
		var _oobj = document.getElementById(idd);
		_oobj.className = cn;
	} catch (_EE) {
	}
}

function osm_noEditable(ids) {
	try {
		var _oobj = document.getElementById(ids);
		_oobj.disabled = true;
	} catch (E_) {
	}
}

function osm_editable(ids) {
	try {
		var _oobj = document.getElementById(ids);
		_oobj.disabled = false;
	} catch (E_) {
	}
}

function osm_alert(msg) {

	osm_block_window();

	alert(msg);

	osm_unblock_window();
}
// -------------

function osm_getValor(idd) {
	try {

		var _oobj = document.getElementById(idd);

		if (_oobj.tagName == "INPUT" || _oobj.tagName == "SELECT" || _oobj.tagName == "TEXTAREA") {
			return _oobj.value;
		}

		if (_oobj.tagName == "IMG") {
			return _oobj.src;
		}

		return _oobj.innerHTML;

	} catch (E_) {
	}

	return null;
}

function osm_getValorText(idd) {
	var valor = osm_getValor(idd);

	try {

		var _oobj = document.getElementById(idd);

		if (_oobj.tagName == "SELECT") {
			return _oobj.options[_oobj.selectedIndex].text;
		}

		if (_oobj.tagName == "OPTION") {
			return _oobj.text;
		}

	} catch (E_) {
	}

	return valor;
}

// -------------

function osm_getValorFlotante(idd) {
	var cc = parseFloat(osm_getValor(idd));
	if (isNaN(cc)) {
		cc = null;
	}
	return cc;
}

// -------------

function osm_getValorEntero(idd) {
	var cc = parseInt(osm_getValor(idd), 10);
	if (isNaN(cc)) {
		cc = 0;
	}
	return cc;
}

// valor de 0 a 5
function osm_setTransparencia(obj, value) {

	try {
		var testObj = document.getElementById(obj);

		if (testObj == null) {
			testObj = obj;
		}

		testObj.style.opacity = value / 5;
		testObj.style.filter = 'alpha(opacity=' + (value * 20) + ')';

	} catch (e) {
	}
}

// -------------

function osm_getMarca(idd) {
	try {
		var _oobj = document.getElementById(idd);
		if (_oobj.checked) {
			return true;
		}
	} catch (E_) {
	}

	try {
		if (idd.checked) {
			return true;
		}
	} catch (E_) {
	}

	return false;
}

// -------------

function osm_setMarca(idd, vval) {
	try {
		var _oobj = document.getElementById(idd);

		_oobj.setChecked(vval);
		_oobj.checked = vval;
	} catch (E_) {
		_oobj.checked = vval;
	}
}

// -------------

var OSM_VALOR = null;

/**
 * Modifica el valor del objeto html
 * 
 * @param idd -
 *            id del objeto html
 * @param vval -
 *            nuevo valor del objeto
 */
function osm_setValor(idd, vval) {

	OSM_VALOR = vval;

	$(osm_getObjeto(idd)).each(function(i) {
		setValorObj(this);
	});

}

/**
 * Funcion interna para modificar el valor de un objeto
 * 
 * @param _oobj
 */
function setValorObj(_oobj) {

	if (_oobj.tagName == "INPUT" || _oobj.tagName == "SELECT" || _oobj.tagName == "TEXTAREA") {
		_oobj.value = OSM_VALOR;
	} else {
		if (_oobj.tagName == "IMG") {
			_oobj.src = OSM_VALOR;
		} else {
			$(_oobj).html(OSM_VALOR);
		}
	}
}

// ------------------------------------------------
function osm_secureRamdom() {

	var v = 0;
	var x = new Array(16);
	var a = new SecureRandom();
	a.nextBytes(x);
	for (var i = 0; i < x.length; i++) {
		v += (Math.floor(10 * x[i] / 256) / (Math.pow(10, i + 1)));
	}
	return v;
}

// ------------------------------------------------

function osm_aleatorio(inferior, superior) {
	var numPosibilidades = superior - inferior;
	var aleat = osm_secureRamdom() * numPosibilidades;
	aleat = Math.round(aleat);
	return parseInt(inferior, 10) + aleat;
}

// ------------------------------------------------
function osm_comfirm(msg) {

	osm_block_window();
	var dd = confirm(msg);
	osm_unblock_window();

	return dd;
}

// ------------------------------------------------

function osm_httpPost(strURL, parame, exeFunctionName) {

	var xmlHttpReq = false;
	var self = this;
	if (window.XMLHttpRequest) {
		self.xmlHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	}
	self.xmlHttpReq.open('POST', strURL, true);
	self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	self.xmlHttpReq.onreadystatechange = new Function("if (self.xmlHttpReq.readyState == 4) { " + exeFunctionName + "(self.xmlHttpReq.responseText, self.xmlHttpReq); }");

	self.xmlHttpReq.send(parame);

}

function osm_newDate() {
	return new Date(jsonrpc.pcore.getCurrentTime());
}

function osm_timeToStringDate(time) {
	var fecha = new Date(time);

	fecha.setTime(time);

	var dias = [ "domingo", "lunes", "martes", "miercoles", "jueves", "viernes", "s\u00e1bado" ];
	var meses = [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" ]

	return dias[fecha.getDay()] + " " + fecha.getDate() + " de " + meses[fecha.getMonth()] + " de " + fecha.getFullYear();
}

function osm_timeToStringHour(time) {
	var fecha = new Date(time);
	fecha.setTime(time);

	var hora = fecha.getHours();
	var minutos = fecha.getMinutes();
	var segundos = fecha.getSeconds();
	var z = (hora >= 12) ? "PM" : "AM";

	return ((hora > 12) ? (hora - 12) : hora) + ":" + ((minutos < 10) ? "0" : "") + minutos + ":" + ((segundos < 10) ? "0" : "") + segundos + " " + z;
}

function osm_tiempofaltante(dateinicial, porcentaje) {
	var initdate = new Date();
	var tiempo_transcurrido = initdate.getTime() - dateinicial.getTime();
	var tiempo_total = null;
	if (porcentaje > 0) {
		tiempo_total = 100 * tiempo_transcurrido / porcentaje;
		return parseInt((tiempo_total - tiempo_transcurrido), 10);
	}
	return null;
}

function osm_tiempofaltantetexto(dateinicial, porcentaje) {

	var actualdate = new Date();
	var tiempo_transcurrido = actualdate.getTime() - dateinicial.getTime();
	var tiempo_faltante = osm_tiempofaltante(dateinicial, porcentaje);

	if (tiempo_transcurrido > 5000) {
		tiempo_faltante = parseInt((tiempo_faltante / 1000), 10);

		if (tiempo_faltante < 5) {
			return "Faltan pocos segundos";
		}

		if (tiempo_faltante < 30) {
			return "Faltan " + tiempo_faltante + " segundos";
		}

		if (tiempo_faltante < 60) {
			return "Falta menos de un minuto.";
		}

		return "Falta menos de " + Math.ceil(tiempo_faltante / 60) + " minutos.";

	}

	return "Calculando tiempo faltante...";
}

function osm_calcularDigitoVerificacion(nit) {
	return jsonrpc.pcore.calcularDigitoVerificacion(nit);
}

// ------------------------------------------------

function osm_onloadwindow() {
	try {
		document.body.style.display = "block";
		osm_ocultarLoader();
		osm_setVisible("bodyContent", true);
		osm_unblock_window();
	} catch (e) {
	}
}

$(osm_onloadwindow);

// ------------------------------------------------
// Manejo de Log

function SLogger() {
	this.setError = function(mensaje, e) {
		// TODO Verificar que existe pcore y que los errores se escriban en el servidor
		try {
			if (jsonrpc.core) {
				jsonrpc.core.setSessionErrorLog(function() {
				}, mensaje + " | " + navigator.userAgent, e);
			} else {
				jsonrpc.pcore.setErrorLog(function() {
				}, mensaje + " | " + navigator.userAgent, e);
			}

		} catch (e) {
			alert(e);
		}
	}

	this.setInfo = function(mensaje, e) {
		try {
			if (jsonrpc.core) {
				jsonrpc.core.setSessionInfoLog(function() {
				}, mensaje + " | " + navigator.userAgent, e);
			} else {
				jsonrpc.pcore.setInfoLog(function() {
				}, mensaje + " | " + navigator.userAgent, e);
			}

		} catch (e) {
		}
	}
}
var SimpleLogger = new SLogger();

window.onerror = function(mensaje, url, linea) {

	alert(mensaje + " [" + url + " : line " + linea + "]");

	SimpleLogger.setError(mensaje + " [" + url + " : line " + linea + "]", null);
}

// ------------------------------------------------
// Bloqueo del menu contextual

var MENU_INACTIVO = true;

osm_listen("load", window, function(e) {

	// Desactiva las animaciones pare IE < 9
	if (navigator.appName == "Microsoft Internet Explorer") {
		var ver = getInternetExplorerVersion();
		if (ver < 9.0) {
			jQuery.fx.off = true;
		}
	}

	if (MENU_INACTIVO) {
		osm_listen("contextmenu", document.body, function(e) {
			return false
		});
		osm_listen("mouseup", document, function(e) {
			if (e.button == 2 || e.button == 3) {
				return false;
			}
		});
	}

});

// ------------------------------------------------
// Obtiene la version de IE
function getInternetExplorerVersion() {
	var rv = -1; // Return value assumes failure.
	if (navigator.appName == 'Microsoft Internet Explorer') {
		var ua = navigator.userAgent;
		var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		if (re.exec(ua) != null)
			rv = parseFloat(RegExp.$1);
	}
	return rv;
}

// ------------------------------------------------
// Bloqueo de la tecla backspace para que no retorne a la pagina anterior

osm_listen("keydown", document, function(e) {

	var obj = e.target;
	if (e.keyCode == 8 && obj != null && (obj.tagName != 'INPUT' && obj.tagName != 'TEXTAREA')) {
		return false;
	}

});

// ------------------------------------------------
// Presenta nombre y version de la aplicacion con F9

var KEYDOWN_ACTIVO = true;

osm_listen("keydown", document, function(e) {

	if (e.keyCode == 120 && KEYDOWN_ACTIVO) {
		try {

			jsonrpc.pcore.getAplicationName(function(res, e) {
				if (e) {
					return;
				}
				alert(res);
			});
		}

		catch (E) {
		}

		return false;
	}
})

// ------------------------------------------------
// Bloqueo del Menu contextual por el evento dobleclick

osm_listen("dblclick", document, function(e, obj) {
	if (e.button == 2 || e.button == 3) {
		return false;
	}
});

// ------------------------------------------------
// Round para flotantes
function osm_round(num, places) {
	if (places == undefined) {
		places = 0;
	}
	var pow = Math.pow(10, places);
	return Math.round(num * pow) / pow;
}

// -------------------------------------------------
function osm_esVerdad(val) {
	if (osm_esVacio(val)) {
		return false;
	}

	val = osm_trim(val).toLowerCase();

	return (val == "si" || val == "yes" || val == "true" || val == "1" || val == "y" || val == "s" || val == "t");
}

function osm_htmlEncode(value) {
	return $('<div/>').text(value).html();
}

function osm_htmlDecode(value) {
	return $('<div/>').html(value).text();
}

function osm_getTemplate(pt) {

	var p = null;
	$.ajax({
		url : CONTEXTPATH + pt,
		cache : true,
		async : false
	}).done(function(plantilla) {
		p = plantilla;
	});

	return p;
}

// ********************************************************************************************************************************************

var DS_ALERT_MODAL_ID = 0;
var DS_ALERT_MODAL_DATA = new Array();

function osm_alert_danger(content, title, callback, sdata) {
	osm_alert_simple("<div class='alert alert-danger' role='alert'>" + content + "</div>", title, callback, sdata);
}

function osm_alert_dangerE(content, title, callback, sdata) {
	osm_alert_danger("<h5>" + osm_escape_html(content) + "</h5>", title, callback, sdata);
}

function osm_alert_warning(content, title, callback, sdata) {
	osm_alert_simple("<div class='alert alert-warning' role='alert'>" + content + "</div>", title, callback, sdata);
}

function osm_alert_warningE(content, title, callback, sdata) {
	osm_alert_warning("<h5>" + osm_escape_html(content) + "</h5>", title, callback, sdata);
}

function osm_alert_info(content, title, callback, sdata) {
	osm_alert_simple("<div class='alert alert-info' role='alert'>" + content + "</div>", title, callback, sdata);
}

function osm_alert_infoE(content, title, callback, sdata) {
	osm_alert_info("<h5>" + osm_escape_html(content) + "</h5>", title, callback, sdata);
}

function osm_alert_success(content, title, callback, sdata) {
	osm_alert_simple("<div class='alert alert-success' role='alert'>" + content + "</div>", title, callback, sdata);
}

function osm_alert_successE(content, title, callback, sdata) {
	osm_alert_success("<h5>" + osm_escape_html(content) + "</h5>", title, callback, sdata);
}

function osm_alert_simpleE(content, title, callback, sdata) {
	osm_alert_simple("<h5>" + osm_escape_html(content) + "</h5>", title, callback, sdata);
}

function osm_alert_simple(content, title, callback, sdata) {

	DS_ALERT_MODAL_ID++;
	DS_ALERT_MODAL_DATA[DS_ALERT_MODAL_ID] = sdata;

	var html = '<div class="modal fade" id="alertModal_' + DS_ALERT_MODAL_ID + '"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h4 class="modal-title" id="alertModalLabel_' + DS_ALERT_MODAL_ID + '">--</h4></div><div class="modal-body"><p id="alertModalText_' + DS_ALERT_MODAL_ID + '"></p></div><div class="modal-footer"><button id="btn_acept_' + DS_ALERT_MODAL_ID + '" type="button" class="btn btn-primary btn-sm">Aceptar</button></div></div></div></div>';

	$("body").append(html);

	$("#alertModalLabel_" + DS_ALERT_MODAL_ID).html(osm_escape_html(title));
	$("#alertModalText_" + DS_ALERT_MODAL_ID).html(content);

	$("#alertModal_" + DS_ALERT_MODAL_ID).modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	});

	window.setTimeout(function() {
		$('#btn_acept_' + DS_ALERT_MODAL_ID).focus();
	}, 500);

	$("#btn_acept_" + DS_ALERT_MODAL_ID).get(0).ids = DS_ALERT_MODAL_ID;
	$("#btn_acept_" + DS_ALERT_MODAL_ID).get(0).callback = callback;

	$("#btn_acept_" + DS_ALERT_MODAL_ID).click(function() {
		$("#alertModal_" + this.ids).modal('hide');

		try {
			this.callback(DS_ALERT_MODAL_DATA[this.ids]);
			DS_ALERT_MODAL_DATA[this.ids] = null;
		} catch (e) {
		}

		window.setTimeout(new Function('$("#alertModal_' + this.ids + '").remove();'), 1000);
	});
}

// ###################################################################################################################

function osm_ajustaEntrada(obj, patron, len) {
	var n = "";

	var str = obj.value;

	for (var i = 0; i < str.length && i < len; i++) {
		var caracter = str.charAt(i);

		if (patron.test(caracter) || caracter.charCodeAt(0) == 10) {
			n = n + caracter;
		}
	}

	if (n != obj.value) {
		obj.value = n;
	}

}

function osm_validaEvento(obj, ev, patron, len) {
	var tecla = (document.all) ? ev.keyCode : ev.which;

	if (tecla == 0 || tecla == 8 || tecla == 9 || tecla == 13) {
		return;
	}

	if ($(obj).val().length >= len) {
		ev.preventDefault();
		return;
	}

	var caracter = String.fromCharCode(tecla);

	if (!patron.test(caracter)) {
		ev.preventDefault();
	}
}

// -------------------------------------------------------------

function osm_validarInputExpr(id, expr, size) {

	var f1 = new Function("ev", "osm_validaEvento(this, ev, /^" + expr + "$/, " + size + ");");
	var f2 = new Function("event", "osm_ajustaEntrada(this, /^" + expr + "$/, " + size + ");");

	$(id).keypress(f1);
	$(id).keyup(f2);
}

// --------------------------------------------------------------

// tipos [ int, email, float, ]

function osm_validarInput(nombre, id, tipo, longitud, obligatorio) {

	$(id).each(function(index) {
		this.osmvld_tipo = tipo;
		this.osmvld_longitud = longitud;
		this.osmvld_nombre = nombre;

		if (typeof obligatorio === 'function') {
			this.osmvld_obligatorio = false;
			this.osmvld_obligatorio_func = obligatorio;
		} else {
			this.osmvld_obligatorio = obligatorio;
			this.osmvld_obligatorio_func = function() {
				return false;
			};
		}

		$(this).addClass("osmValidationItem");

		var s = 20000;

		if (longitud != null && longitud[1] != null) {
			s = longitud[1];
		}

		if (tipo == "int") {
			osm_validarInputExpr(this, "[\\d]+", s);
		}

		if (tipo == "string") {
			osm_validarInputExpr(this, ".*", s);
		}

		if (tipo == "string" || tipo == "email") {
			osm_validarInputExpr(this, ".+", s);
		}

		if (tipo == "float") {
			osm_validarInputExpr(this, "[\\d]+(\.[\\d]*)?", s);
		}

		if (tipo == "float2") {
			osm_validarInputExpr(this, "[\\d]+(\.[\\d]{0,2})?", s);
		}

		if (tipo == "ip") {
			osm_validarInputExpr(this, "[0-9]{1,3}([\\.]([0-9]{1,3}([\\.]([0-9]{1,3}([\\.]([0-9]{1,3})?)?)?)?)?)?", s);
		}

		if (tipo == "name") {
			osm_validarInputExpr(this, "[A-Za-z·ÈÌÛ˙¡…Õ”⁄Ò—\\.\\'\\- ]+", s);
		}

		if (tipo == "name2") {
			osm_validarInputExpr(this, "[0-9A-Za-z·ÈÌÛ˙¡…Õ”⁄Ò—\\.\\'\\- ]+", s);
		}

		if (tipo == "date") {
			osm_validarInputExpr(this, "[\d]{1,2}([\\/]([\\d]{1,2}([\\/]([\\d]{1,4}[\\/]?)?)?)?)?", s);
		}

	});
}

function osm_realizarValidacion(idarea) {

	var valido_form = true;

	$(osm_getObjeto(idarea)).find(".osmValidationItem").each(function() {

		$(this).val(osm_trim($(this).val()));

		if (valido_form) {

			// realiza validacion de obligatorio

			if (this.osmvld_obligatorio || this.osmvld_obligatorio_func()) {

				if (osm_esVacio($(this).val())) {

					osm_alert_dangerE("El campo \"" + (this.osmvld_nombre).toUpperCase() + "\" es obligatorio.", "Lo sentimos", function(pthis) {
						$(pthis).focus();
					}, this);

					valido_form = false;

					return;

				}
			}

			// realizar validacion por longitud

			if (this.osmvld_longitud != null && !osm_esVacio($(this).val())) {

				if (this.osmvld_longitud != null) {

					if (this.osmvld_longitud[0] != null && $(this).val().length < this.osmvld_longitud[0]) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe ser minimo de " + this.osmvld_longitud[0] + " caracteres", "Validaci\u00f3n", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}

					if (this.osmvld_longitud[1] != null && $(this).val().length > this.osmvld_longitud[1]) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe ser maximo de " + this.osmvld_longitud[1] + " caracteres", "Validaci\u00f3n", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}

				}

			}

			// ---------

			if (!osm_esVacio($(this).val())) {

				if (this.osmvld_tipo == "int") {
					if (!osm_validarNumero($(this).val())) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe ser un n\u00FAmero", "Validaci\u00F3n", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}
				}

				if (this.osmvld_tipo == "email") {
					if (!osm_validarCorreo($(this).val())) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe ser un correo", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}
				}

				if (this.osmvld_tipo == "float") {
					if (!osm_validarDecimal($(this).val())) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe ser un decimal", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}
				}

				if (this.osmvld_tipo == "float2") {
					if (!osm_validar2Decimal($(this).val())) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe ser un n\u00FAmero con dos decimales", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}
				}

				if (this.osmvld_tipo == "ip") {
					if (!osm_validarIP($(this).val())) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe ser una ip valida", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}
				}

				if (this.osmvld_tipo == "name") {
					if (!osm_validarAlfabetico($(this).val())) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe solo puede tener letras", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}
				}

				if (this.osmvld_tipo == "name2") {
					if (!osm_validarAlfanumerico($(this).val())) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe ser alfanumerico", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}
				}

				if (this.osmvld_tipo == "date") {
					if (!osm_validarFormatoFecha($(this).val())) {

						osm_alert_dangerE("El campo '" + this.osmvld_nombre + "' debe ser una fecha", function(pthis) {
							$(pthis).focus();
						}, this);

						valido_form = false;

						return;
					}
				}

			}
		}

	});

	return valido_form;

}

function osm_createObjectFromForm(idform) {

	var obj = {};

	var map_index = {};

	$(idform).find("input,select,textarea").each(function() {

		var valid = true;

		if ($(this).is("[type=checkbox]")) {
			valid = $(this).is(":checked");
		}

		if (valid) {
			var name = osm_trimToNull(this.name);
			obj = osm_addPropertyToObject(obj, name, this.value, map_index, "");
		}

	});

	return obj;
}

function osm_addPropertyToObject(obj, name, value, map_index, parent) {

	if (obj === undefined) {
		obj = null;
	}

	if (name == null) {
		return obj;
	}

	var dot_index = name.indexOf(".");

	if (dot_index < 0) {

		if (obj == null) {
			obj = {};
		}

		obj[name] = value;
		return obj;
	}

	var sname = name.substring(0, dot_index);
	var fname = name.substring(dot_index + 1);

	var dot2_index = sname.indexOf(":");

	if (dot2_index < 0) {
		if (obj == null) {
			obj = {};
		}
		obj[sname] = osm_addPropertyToObject(obj[sname], fname, value, map_index, parent + "." + sname);

		return obj;
	}

	var pname = sname.substring(0, dot2_index);

	if (obj == null) {
		obj = {};
	}

	if (obj[pname] == undefined) {
		obj[pname] = [];
	}

	var mpindex = map_index[parent + "." + sname];

	if (mpindex === undefined) {
		mpindex = obj[pname].length;
		map_index[parent + "." + sname] = mpindex;
	}

	obj[pname][mpindex] = osm_addPropertyToObject(obj[pname][mpindex], fname, value, map_index, parent + "." + sname);

	return obj;

}

try {
	$.fn.clicktoggle = function(a, b) {
		return this.each(function() {
			var clicked = false;
			$(this).bind("click", function() {
				if (clicked) {
					clicked = false;
					return b.apply(this, arguments);
				}
				clicked = true;
				return a.apply(this, arguments);
			});
		});// fixed typo here, was missing )
	};
} catch (e) {
}

// ***********************************************************************************************************
// ***********************************************************************************************************

// prng4.js - uses Arcfour as a PRNG

function Arcfour() {
	this.i = 0;
	this.j = 0;
	this.S = new Array();
}

// Initialize arcfour context from key, an array of ints, each from [0..255]
function ARC4init(key) {
	var i, j, t;
	for (i = 0; i < 256; ++i)
		this.S[i] = i;
	j = 0;
	for (i = 0; i < 256; ++i) {
		j = (j + this.S[i] + key[i % key.length]) & 255;
		t = this.S[i];
		this.S[i] = this.S[j];
		this.S[j] = t;
	}
	this.i = 0;
	this.j = 0;
}

function ARC4next() {
	var t;
	this.i = (this.i + 1) & 255;
	this.j = (this.j + this.S[this.i]) & 255;
	t = this.S[this.i];
	this.S[this.i] = this.S[this.j];
	this.S[this.j] = t;
	return this.S[(t + this.S[this.i]) & 255];
}

Arcfour.prototype.init = ARC4init;
Arcfour.prototype.next = ARC4next;

// Plug in your RNG constructor here
function prng_newstate() {
	return new Arcfour();
}

// Pool size must be a multiple of 4 and greater than 32.
// An array of bytes the size of the pool will be passed to init()
var rng_psize = 256;

// ***********************************************************************************************************
// ***********************************************************************************************************

// Random number generator - requires a PRNG backend, e.g. prng4.js

// For best results, put code like
// <body onClick='rng_seed_time();' onKeyPress='rng_seed_time();'>
// in your main HTML document.

var rng_state;
var rng_pool;
var rng_pptr;

// Mix in a 32-bit integer into the pool
function rng_seed_int(x) {
	rng_pool[rng_pptr++] ^= x & 255;
	rng_pool[rng_pptr++] ^= (x >> 8) & 255;
	rng_pool[rng_pptr++] ^= (x >> 16) & 255;
	rng_pool[rng_pptr++] ^= (x >> 24) & 255;
	if (rng_pptr >= rng_psize)
		rng_pptr -= rng_psize;
}

// Mix in the current time (w/milliseconds) into the pool
function rng_seed_time() {
	rng_seed_int(new Date().getTime());
}

// Initialize the pool with junk if needed.
if (rng_pool == null) {
	rng_pool = new Array();
	rng_pptr = 0;
	var t;
	if (navigator.appName == "Netscape" && navigator.appVersion < "5" && window.crypto) {
		// Extract entropy (256 bits) from NS4 RNG if available
		var z = window.crypto.random(32);
		for (t = 0; t < z.length; ++t)
			rng_pool[rng_pptr++] = z.charCodeAt(t) & 255;
	}
	while (rng_pptr < rng_psize) { // extract some randomness from Math.random()
		t = Math.floor(65536 * Math.random());
		rng_pool[rng_pptr++] = t >>> 8;
		rng_pool[rng_pptr++] = t & 255;
	}
	rng_pptr = 0;
	rng_seed_time();
	// rng_seed_int(window.screenX);
	// rng_seed_int(window.screenY);
}

function rng_get_byte() {
	if (rng_state == null) {
		rng_seed_time();
		rng_state = prng_newstate();
		rng_state.init(rng_pool);
		for (rng_pptr = 0; rng_pptr < rng_pool.length; ++rng_pptr)
			rng_pool[rng_pptr] = 0;
		rng_pptr = 0;
		// rng_pool = null;
	}
	// TODO: allow reseeding after first request
	return rng_state.next();
}

function rng_get_bytes(ba) {
	var i;
	for (i = 0; i < ba.length; ++i)
		ba[i] = rng_get_byte();
}

function SecureRandom() {
}

SecureRandom.prototype.nextBytes = rng_get_bytes;

$(document).ready(init);

var CRITERIO_HORARIO_SELECCION_CAMPO = new Array();
var CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO = new Array();

var VALOR_CRITERIO_HORARIO = new Array();
var VALOR_CRITERIO_HORARIO_CP = new Array();
var VALOR_CRITERIO_HORARIO_ORIG = new Array();

var HORA_INICIO = new Array();
var HORA_FIN = new Array();

function init() {
	
	$(".listahorarios").each(function() {
		
		var nombre_horario = this.value;
		
		CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario] = false;
		CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO[nombre_horario] = false;
		VALOR_CRITERIO_HORARIO[nombre_horario] = "";
		VALOR_CRITERIO_HORARIO_CP[nombre_horario] = "";
		VALOR_CRITERIO_HORARIO_ORIG[nombre_horario] = "";
		
		HORA_INICIO[nombre_horario] = (!osm_esVacio("hora_inicio_" + nombre_horario)) ? osm_getValorEntero("hora_inicio_" + nombre_horario) : 6;
		HORA_FIN[nombre_horario] = (!osm_esVacio("hora_fin_" + nombre_horario)) ? osm_getValorEntero("hora_fin_" + nombre_horario) : 20;
	
		initHorarioEdicion(nombre_horario);
		$(document).mouseup(
			function (){
		 	if(CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario]){
				osm_setFoco('valor_criterio_horario_' + nombre_horario);
			}   
			CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario] = false;
		 }
		);
		 
		osm_getObjeto("criterios_aplicados_horario_" + nombre_horario).onselectstart = new Function ("return false");

		if (window.sidebar) {
		  osm_getObjeto("criterios_aplicados_horario_" + nombre_horario).onmousedown = new Function("return false");
		  osm_getObjeto("criterios_aplicados_horario_" + nombre_horario).onclick = new Function("return true");
		}

		actualizarHorario(nombre_horario);
	});
	
}

function editarCriterioHorario(nombre_horario) {	
	VALOR_CRITERIO_HORARIO_ORIG[nombre_horario] = VALOR_CRITERIO_HORARIO[nombre_horario];
	verVentana(nombre_horario);

}
function actualizarHorario(nombre_horario){
	VALOR_CRITERIO_HORARIO[nombre_horario] = "";
	var id_horario = osm_getValor("id_horario_" + nombre_horario);
	var lista = jsonrpc._("horarioServicio.obtenerFranjas")(id_horario);
	
	if (lista != null) {
		lista = lista.list;
	}
	
	for ( var i = 0; i < lista.length; i++) {
		if (lista[i].hora_hasta == 0){
			lista[i].hora_hasta = 24;
		}
		for ( var j = lista[i].hora_desde ; j < lista[i].hora_hasta; j++) {
			VALOR_CRITERIO_HORARIO[nombre_horario] = VALOR_CRITERIO_HORARIO[nombre_horario] + "(" + j + lista[i].dia + ")";
		}
	}
	
	definirHorario(nombre_horario);
}
function definirHorario(nombre_horario){

	for ( var i = HORA_INICIO[nombre_horario]; i <= HORA_FIN[nombre_horario]; i++) {

		activarCeldaHorario('horario_' + i + 'L_' + nombre_horario, (VALOR_CRITERIO_HORARIO[nombre_horario].indexOf('(' + i + 'L)') >= 0), nombre_horario);
		activarCeldaHorario('horario_' + i + 'M_' + nombre_horario, (VALOR_CRITERIO_HORARIO[nombre_horario].indexOf('(' + i + 'M)') >= 0), nombre_horario);
		activarCeldaHorario('horario_' + i + 'C_' + nombre_horario, (VALOR_CRITERIO_HORARIO[nombre_horario].indexOf('(' + i + 'C)') >= 0), nombre_horario);
		activarCeldaHorario('horario_' + i + 'J_' + nombre_horario, (VALOR_CRITERIO_HORARIO[nombre_horario].indexOf('(' + i + 'J)') >= 0), nombre_horario);
		activarCeldaHorario('horario_' + i + 'V_' + nombre_horario, (VALOR_CRITERIO_HORARIO[nombre_horario].indexOf('(' + i + 'V)') >= 0), nombre_horario);
		activarCeldaHorario('horario_' + i + 'S_' + nombre_horario, (VALOR_CRITERIO_HORARIO[nombre_horario].indexOf('(' + i + 'S)') >= 0), nombre_horario);
		activarCeldaHorario('horario_' + i + 'D_' + nombre_horario, (VALOR_CRITERIO_HORARIO[nombre_horario].indexOf('(' + i + 'D)') >= 0), nombre_horario);
	}
	agregarCriterioHorario(nombre_horario);
}
function verVentana(nombre_horario) {
	
	osm_setVisible("vn_cambio_estado_activo_" + nombre_horario, true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentana(nombre_horario) {
	osm_setVisible("vn_cambio_estado_activo_" + nombre_horario, false);
	osm_mostrarSelects("bodyContent");
}
function cancelar(nombre_horario){
	cerrarVentana(nombre_horario);
	VALOR_CRITERIO_HORARIO[nombre_horario] = VALOR_CRITERIO_HORARIO_ORIG[nombre_horario];
	definirHorario(nombre_horario);
}

function guardarHorario(nombre_horario){
	if(agregarCriterioHorario(nombre_horario)){
		cerrarVentana(nombre_horario);
	}
}

//buscador-docentes
function initHorarioEdicion(nombre_horario) {
	try {

		addEventoColumna('horario_L_' + nombre_horario, 'L', nombre_horario);
		addEventoColumna('horario_M_' + nombre_horario, 'M', nombre_horario);
		addEventoColumna('horario_C_' + nombre_horario, 'C', nombre_horario);
		addEventoColumna('horario_J_' + nombre_horario, 'J', nombre_horario);
		addEventoColumna('horario_V_' + nombre_horario, 'V', nombre_horario);
		addEventoColumna('horario_S_' + nombre_horario, 'S', nombre_horario);
		addEventoColumna('horario_D_' + nombre_horario, 'D', nombre_horario);

		var i = HORA_INICIO[nombre_horario];
		while (i <= HORA_FIN[nombre_horario]) {

			addEventoFila('horario_' + i + '_' + nombre_horario, i, nombre_horario);
			addEventoCelda('horario_' + i + 'L_' + nombre_horario, i + 'L', nombre_horario);
			addEventoCelda('horario_' + i + 'M_' + nombre_horario, i + 'M', nombre_horario);
			addEventoCelda('horario_' + i + 'C_' + nombre_horario, i + 'C', nombre_horario);
			addEventoCelda('horario_' + i + 'J_' + nombre_horario, i + 'J', nombre_horario);
			addEventoCelda('horario_' + i + 'V_' + nombre_horario, i + 'V', nombre_horario);
			addEventoCelda('horario_' + i + 'S_' + nombre_horario, i + 'S', nombre_horario);
			addEventoCelda('horario_' + i + 'D_' + nombre_horario, i + 'D', nombre_horario);
			i++;
		}
	} catch (e) {
		alert(e);
	}
}

function addEventoColumna(id, valor, nombre_horario) {
	var obj = osm_getObjeto(id);
	
	obj.seleccionado = false;
	obj.valor = valor;

	obj.onmousedown = function() {
		if (!CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario]) {
			var j = HORA_INICIO[nombre_horario];
			var cc = true;
			var dd = true;
			while (j <= HORA_FIN[nombre_horario]) {
				if (cc) {
					cc = getValorCelda('horario_' + j + this.valor + '_' + nombre_horario);
				}
				if (dd) {
					dd = !getValorCelda('horario_' + j + this.valor + '_' + nombre_horario);
				}
				j++;
			}

			if (cc) {
				this.seleccionado = true;
			}

			if (dd) {
				this.seleccionado = false;
			}

			this.seleccionado = !this.seleccionado;
		}

		this.exe = true;
		CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario] = true;
		CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO[nombre_horario] = this.seleccionado;

		var i = HORA_INICIO[nombre_horario];
		while (i <= HORA_FIN[nombre_horario]) {
			activarCeldaHorario('horario_' + i + this.valor + '_' + nombre_horario, this.seleccionado, nombre_horario);
			i++;
		}
		return false;
	};

	obj.onmouseover = function() {
		this.exe = false;
		this.className = 'th_over';
	};

	obj.onmouseout = function() {
		this.className = '';
	};

	obj.onmousemove = function() {

		if (CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario] && !this.exe) {
			this.exe = true;
			this.seleccionado = CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO[nombre_horario];
			this.onmousedown();
		}
	};

}

function activarCeldaHorario(id, activar, nombre_horario) {

	var obj = osm_getObjeto(id);

	do {
		cambiarValorHorario(obj, obj.valor, nombre_horario);
	} while (activar != obj.activo);

}
function cambiarValorHorario(obj, valor, nombre_horario) {

	var actual = osm_getValor('valor_criterio_horario_' + nombre_horario);

	if (actual.indexOf("(" + valor + ")") >= 0) {
		obj.className = "";
		obj.activo = false;
		actual = actual.replace("(" + valor + ")", "");
	} else {
		obj.className = "tabla_horario_edicion_td_seleccionado";
		obj.activo = true;
		actual = actual + "(" + valor + ")";
	}

	osm_setValor('valor_criterio_horario_' + nombre_horario, actual);
}
function addEventoFila(id, valor, nombre_horario) {

	var obj = osm_getObjeto(id);

	obj.seleccionado = false;
	
	var idt = 'horario_' + valor;

	obj.onmousedown = function() {

		if (!CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario]) {
			if (getValorCelda(idt + "L_" + nombre_horario) && getValorCelda(idt + "M_" + nombre_horario) && getValorCelda(idt + "C_" + nombre_horario) && getValorCelda(idt + "J_" + nombre_horario) && getValorCelda(idt + "V_" + nombre_horario) && getValorCelda(idt + "S_" + nombre_horario) && getValorCelda(idt + "D_" + nombre_horario)) {
				this.seleccionado = true;
			}

			if (!getValorCelda(idt + "L_" + nombre_horario) && !getValorCelda(idt + "M_" + nombre_horario) && !getValorCelda(idt + "C_" + nombre_horario) && !getValorCelda(idt + "J_" + nombre_horario) && !getValorCelda(idt + "V_" + nombre_horario) && !getValorCelda(idt + "S_" + nombre_horario) && !getValorCelda(idt + "D_" + nombre_horario)) {
				this.seleccionado = false;
			}

			this.seleccionado = !this.seleccionado;
		}

		this.exe = true;
		CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario] = true;
		CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO[nombre_horario] = this.seleccionado;

		activarCeldaHorario(idt + "L_" + nombre_horario, this.seleccionado, nombre_horario);
		activarCeldaHorario(idt + "M_" + nombre_horario, this.seleccionado, nombre_horario);
		activarCeldaHorario(idt + "C_" + nombre_horario, this.seleccionado, nombre_horario);
		activarCeldaHorario(idt + "J_" + nombre_horario, this.seleccionado, nombre_horario);
		activarCeldaHorario(idt + "V_" + nombre_horario, this.seleccionado, nombre_horario);
		activarCeldaHorario(idt + "S_" + nombre_horario, this.seleccionado, nombre_horario);
		activarCeldaHorario(idt + "D_" + nombre_horario, this.seleccionado, nombre_horario);
	};

	obj.onmousemove = function() {

		if (CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario] && !this.exe) {
			this.exe = true;
			this.seleccionado = CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO[nombre_horario];
			this.onmousedown();
		}
	};

	obj.onmouseover = function() {
		this.exe = false;
		this.className = 'th_over';
	};

	obj.onmouseout = function() {
		this.className = '';
	};

}

//buscador-docentes
function addEventoCelda(id, valor, nombre_horario) {
	var obj = osm_getObjeto(id);
	obj.valor = valor;
	obj.activo = false;
	obj.onmousedown = function() {
		CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario] = true;
		cambiarValorHorario(this, this.valor, nombre_horario);
		CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO[nombre_horario] = obj.activo;
		AA = this;
	};

	obj.onmousemove = function() {

		if (CRITERIO_HORARIO_SELECCION_CAMPO[nombre_horario]) {
			activarCeldaHorario(this.id, CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO[nombre_horario], nombre_horario);
		}
	};
}

function getValorCelda(id) {
	var obj = osm_getObjeto(id);
	return obj.activo;
}

function agregarCriterioHorario(nombre_horario) {

	var valor_anterior = VALOR_CRITERIO_HORARIO[nombre_horario];

	var valor = osm_getValor('valor_criterio_horario_' + nombre_horario);

	osm_setValor('valor_vista_criterio_hora_L_' + nombre_horario, compactarCalendarioDia(valor, 'L', nombre_horario));
	osm_setValor('valor_vista_criterio_hora_M_' + nombre_horario, compactarCalendarioDia(valor, 'M', nombre_horario));
	osm_setValor('valor_vista_criterio_hora_C_' + nombre_horario, compactarCalendarioDia(valor, 'C', nombre_horario));
	osm_setValor('valor_vista_criterio_hora_J_' + nombre_horario, compactarCalendarioDia(valor, 'J', nombre_horario));
	osm_setValor('valor_vista_criterio_hora_V_' + nombre_horario, compactarCalendarioDia(valor, 'V', nombre_horario));
	osm_setValor('valor_vista_criterio_hora_S_' + nombre_horario, compactarCalendarioDia(valor, 'S', nombre_horario));
	osm_setValor('valor_vista_criterio_hora_D_' + nombre_horario, compactarCalendarioDia(valor, 'D', nombre_horario));

	VALOR_CRITERIO_HORARIO[nombre_horario] = valor;

	VALOR_CRITERIO_HORARIO_CP[nombre_horario] = "L"
			+ osm_remplazar(compactarCalendarioDia(valor, 'L', nombre_horario) + ":M" + compactarCalendarioDia(valor, 'M', nombre_horario) + ":C"
                    + compactarCalendarioDia(valor, 'C', nombre_horario) + ":J" + compactarCalendarioDia(valor, 'J', nombre_horario) + ":V" 
                    + compactarCalendarioDia(valor, 'V', nombre_horario) + ":S" + compactarCalendarioDia(valor, 'S', nombre_horario) + ":D"
                    + compactarCalendarioDia(valor, 'D', nombre_horario), '<BR/>', ',');

	VALOR_CRITERIO_HORARIO_CP[nombre_horario] = osm_remplazar(VALOR_CRITERIO_HORARIO_CP[nombre_horario], ',:', ':');

	$('#bloque_criterios_aplicados_' + nombre_horario).show("slow");
	osm_setClassname('btn_criterio_horario_' + nombre_horario, 'btn2 img_horario2');
	$('#contenedor_ventana_base_horario_' + nombre_horario).hide();
	$('#contenedor_ventana_edicion_horario_' + nombre_horario).hide();

	if (valor_anterior == '') {
		$('#criterios_aplicados_horario_' + nombre_horario).show("slow");
		osm_mover('criterios_aplicados_horario_' + nombre_horario, 'bloque_criterios_aplicados_visible_' + nombre_horario);
	}

	osm_setValor('valor_vista_criterio_horario_' + nombre_horario, VALOR_CRITERIO_HORARIO[nombre_horario]);
	return true;
}
function compactarCalendarioDia(valor, dia, nombre_horario) {

	var ret = "";
	var aexist = false;
	for ( var i = HORA_INICIO[nombre_horario]; i <= (HORA_FIN[nombre_horario] + 1); i++) {
		
		if (i == 24){
			i = 0;
		}
		var t = "(" + i + dia + ")";

		if (valor.indexOf(t) >= 0 && !aexist) {
			ret = ret + i + "-";
			aexist = true;
		} else {
			if (valor.indexOf(t) < 0 && aexist) {
				ret = ret + i + "<BR/>";
				aexist = false;
			}
		}
		if (i == 0){
			i = 24;
		}
	}

	return ret;
}

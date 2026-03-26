$(document).ready(init);

var CRITERIO_HORARIO_SELECCION_CAMPO = false;
var CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO = false;

var VALOR_CRITERIO_HORARIO = "";
var VALOR_CRITERIO_HORARIO_CP = "";
var VALOR_CRITERIO_HORARIO_ORIG = "";

var HORARIO_SELECCION = function(){};


function init() {
	 initHorarioEdicion();
	 $(document).mouseup(
		 function (){
		 	if(CRITERIO_HORARIO_SELECCION_CAMPO){
				osm_setFoco('valor_criterio_docente');
			}   
			CRITERIO_HORARIO_SELECCION_CAMPO = false;
		 }
	 );
	 
	 osm_getObjeto("criterios_aplicados_horario").onselectstart = new Function ("return false");
	 
	 if (window.sidebar) {
	   osm_getObjeto("criterios_aplicados_horario").onmousedown = new Function("return false");
	   osm_getObjeto("criterios_aplicados_horario").onclick = new Function("return true");
	}

	 actualizarHorario();
}

function editarCriterioHorario() {
	VALOR_CRITERIO_HORARIO_ORIG = VALOR_CRITERIO_HORARIO;
	verVentana();

}
function actualizarHorario(){
	VALOR_CRITERIO_HORARIO = "";
	var id_horario = osm_getValor("id_horario");
	var lista = jsonrpc._("horarioServicio.obtenerFranjas")(id_horario);
	
	if (lista != null) {
		lista = lista.list;
	}
	
	for ( var i = 0; i < lista.length; i++) {
		for ( var j = lista[i].hora_desde ; j < lista[i].hora_hasta; j++) {
			VALOR_CRITERIO_HORARIO = VALOR_CRITERIO_HORARIO+"(" + j + lista[i].dia + ")";
		}
	}
	definirHorario();
}
function definirHorario(){

	for ( var i = 6; i <= 20; i++) {

		activarCeldaHorario('horario_' + i + 'L', (VALOR_CRITERIO_HORARIO.indexOf('(' + i + 'L)') >= 0));
		activarCeldaHorario('horario_' + i + 'M', (VALOR_CRITERIO_HORARIO.indexOf('(' + i + 'M)') >= 0));
		activarCeldaHorario('horario_' + i + 'C', (VALOR_CRITERIO_HORARIO.indexOf('(' + i + 'C)') >= 0));
		activarCeldaHorario('horario_' + i + 'J', (VALOR_CRITERIO_HORARIO.indexOf('(' + i + 'J)') >= 0));
		activarCeldaHorario('horario_' + i + 'V', (VALOR_CRITERIO_HORARIO.indexOf('(' + i + 'V)') >= 0));
		activarCeldaHorario('horario_' + i + 'S', (VALOR_CRITERIO_HORARIO.indexOf('(' + i + 'S)') >= 0));
		activarCeldaHorario('horario_' + i + 'D', (VALOR_CRITERIO_HORARIO.indexOf('(' + i + 'D)') >= 0));
	}
	agregarCriterioHorario(false);
}
function verVentana() {
	
	osm_setVisible("vn_cambio_estado_activo", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentana() {
	osm_setVisible("vn_cambio_estado_activo", false);
	osm_mostrarSelects("bodyContent");
	
	try{
		HORARIO_SELECCION();
	}catch (e) {
	}
	
}
function cancelar(){
	cerrarVentana();
	VALOR_CRITERIO_HORARIO = VALOR_CRITERIO_HORARIO_ORIG;
	definirHorario();
}

function guardarHorario(){
	if(agregarCriterioHorario()){
		cerrarVentana();
	}
}

//buscador-docentes
function initHorarioEdicion() {
	try {

		addEventoColumna('horario_L', 'L');
		addEventoColumna('horario_M', 'M');
		addEventoColumna('horario_C', 'C');
		addEventoColumna('horario_J', 'J');
		addEventoColumna('horario_V', 'V');
		addEventoColumna('horario_S', 'S');
		addEventoColumna('horario_D', 'D');

		var i = 6;
		while (i <= 20) {

			addEventoFila('horario_' + i, i);
			addEventoCelda('horario_' + i + 'L', i + 'L');
			addEventoCelda('horario_' + i + 'M', i + 'M');
			addEventoCelda('horario_' + i + 'C', i + 'C');
			addEventoCelda('horario_' + i + 'J', i + 'J');
			addEventoCelda('horario_' + i + 'V', i + 'V');
			addEventoCelda('horario_' + i + 'S', i + 'S');
			addEventoCelda('horario_' + i + 'D', i + 'D');
			i++;
		}
	} catch (e) {
		alert(e);
	}
}

function addEventoColumna(id, valor) {
	var obj = osm_getObjeto(id);
	obj.seleccionado = false;
	obj.valor = valor;

	obj.onmousedown = function() {
		if (!CRITERIO_HORARIO_SELECCION_CAMPO) {
			var j = 6;
			var cc = true;
			var dd = true;
			while (j <= 20) {
				if (cc) {
					cc = getValorCelda('horario_' + j + this.valor);
				}
				if (dd) {
					dd = !getValorCelda('horario_' + j + this.valor);
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
		CRITERIO_HORARIO_SELECCION_CAMPO = true;
		CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO = this.seleccionado;

		var i = 6;
		while (i <= 20) {
			activarCeldaHorario('horario_' + i + this.valor, this.seleccionado);
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

		if (CRITERIO_HORARIO_SELECCION_CAMPO && !this.exe) {
			this.exe = true;
			this.seleccionado = CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO;
			this.onmousedown();
		}
	};

}

function activarCeldaHorario(id, activar) {

	var obj = osm_getObjeto(id);

	do {
		cambiarValorHorario(obj, obj.valor);
	} while (activar != obj.activo);

}
function cambiarValorHorario(obj, valor) {

	var actual = osm_getValor('valor_criterio_horario');

	if (actual.indexOf("(" + valor + ")") >= 0) {
		obj.className = "";
		obj.activo = false;
		actual = actual.replace("(" + valor + ")", "");
	} else {
		obj.className = "tabla_horario_edicion_td_seleccionado";
		obj.activo = true;
		actual = actual + "(" + valor + ")";
	}

	osm_setValor('valor_criterio_horario', actual);
}
function addEventoFila(id, valor) {

	var obj = osm_getObjeto(id);

	obj.seleccionado = false;

	obj.onmousedown = function() {

		if (!CRITERIO_HORARIO_SELECCION_CAMPO) {
			if (getValorCelda(id + "L") && getValorCelda(id + "M") && getValorCelda(id + "C") && getValorCelda(id + "J") && getValorCelda(id + "V") && getValorCelda(id + "S") && getValorCelda(id + "D")) {
				this.seleccionado = true;
			}

			if (!getValorCelda(id + "L") && !getValorCelda(id + "M") && !getValorCelda(id + "C") && !getValorCelda(id + "J") && !getValorCelda(id + "V") && !getValorCelda(id + "S") && !getValorCelda(id + "D")) {
				this.seleccionado = false;
			}

			this.seleccionado = !this.seleccionado;
		}

		this.exe = true;
		CRITERIO_HORARIO_SELECCION_CAMPO = true;
		CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO = this.seleccionado;

		activarCeldaHorario(id + "L", this.seleccionado);
		activarCeldaHorario(id + "M", this.seleccionado);
		activarCeldaHorario(id + "C", this.seleccionado);
		activarCeldaHorario(id + "J", this.seleccionado);
		activarCeldaHorario(id + "V", this.seleccionado);
		activarCeldaHorario(id + "S", this.seleccionado);
		activarCeldaHorario(id + "D", this.seleccionado);
	};

	obj.onmousemove = function() {

		if (CRITERIO_HORARIO_SELECCION_CAMPO && !this.exe) {
			this.exe = true;
			this.seleccionado = CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO;
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
function addEventoCelda(id, valor) {
	var obj = osm_getObjeto(id);
	obj.valor = valor;
	obj.activo = false;
	obj.onmousedown = function() {
		CRITERIO_HORARIO_SELECCION_CAMPO = true;
		cambiarValorHorario(this, this.valor);
		CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO = obj.activo;
		AA = this;
	};

	obj.onmousemove = function() {

		if (CRITERIO_HORARIO_SELECCION_CAMPO) {
			activarCeldaHorario(this.id, CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO);
		}
	};
}

function getValorCelda(id) {
	var obj = osm_getObjeto(id);
	return obj.activo;
}

function agregarCriterioHorario(mostrar_mensaje) {

	var valor_anterior = VALOR_CRITERIO_HORARIO;

	var valor = osm_getValor('valor_criterio_horario');

	osm_setValor('valor_vista_criterio_hora_L', compactarCalendarioDia(valor, 'L'));
	osm_setValor('valor_vista_criterio_hora_M', compactarCalendarioDia(valor, 'M'));
	osm_setValor('valor_vista_criterio_hora_C', compactarCalendarioDia(valor, 'C'));
	osm_setValor('valor_vista_criterio_hora_J', compactarCalendarioDia(valor, 'J'));
	osm_setValor('valor_vista_criterio_hora_V', compactarCalendarioDia(valor, 'V'));
	osm_setValor('valor_vista_criterio_hora_S', compactarCalendarioDia(valor, 'S'));
	osm_setValor('valor_vista_criterio_hora_D', compactarCalendarioDia(valor, 'D'));

	VALOR_CRITERIO_HORARIO = valor;

	VALOR_CRITERIO_HORARIO_CP = "L"
			+ osm_remplazar(compactarCalendarioDia(valor, 'L') + ":M" + compactarCalendarioDia(valor, 'M') + ":C" + compactarCalendarioDia(valor, 'C') + ":J" + compactarCalendarioDia(valor, 'J') + ":V" + compactarCalendarioDia(valor, 'V') + ":S" + compactarCalendarioDia(valor, 'S') + ":D"
					+ compactarCalendarioDia(valor, 'D'), '<BR/>', ',');

	VALOR_CRITERIO_HORARIO_CP = osm_remplazar(VALOR_CRITERIO_HORARIO_CP, ',:', ':');

	$('#bloque_criterios_aplicados').show("slow");
	osm_setClassname('btn_criterio_horario', 'btn2 img_horario2');
	$('#contenedor_ventana_base_horario').hide();
	$('#contenedor_ventana_edicion_horario').hide();

	if (valor_anterior == '') {
		$('#criterios_aplicados_horario').show("slow");
		osm_mover('criterios_aplicados_horario', 'bloque_criterios_aplicados_visible');
	}

	osm_setValor('valor_vista_criterio_horario', VALOR_CRITERIO_HORARIO);
	return true;
}
function compactarCalendarioDia(valor, dia) {

	var ret = "";
	var aexist = false;
	for ( var i = 6; i <= 21; i++) {

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
	}

	return ret;
}

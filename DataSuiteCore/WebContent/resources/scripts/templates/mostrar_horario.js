$(document).ready(init);

var CRITERIO_HORARIO_SELECCION_CAMPO = false;
var CRITERIO_HORARIO_SELECCION_CAMPO_ACTIVO = false;

var VALOR_CRITERIO_HORARIO = "";
var VALOR_CRITERIO_HORARIO_CP = "";
var VALOR_CRITERIO_HORARIO_ORIG = "";
function init() {
	
	var id_horario_atencion = osm_getValor("id_horario_atencion");
	var id_horario_liberacion = osm_getValor("id_horario_liberacion");
	
	if(!osm_esVacio(id_horario_atencion)){
		actualizarHorario(id_horario_atencion);
		
	}
	
	
	if(!osm_esVacio(id_horario_liberacion)){
		actualizarHorario(id_horario_liberacion);
		
	}

	
}

function actualizarHorario(id_horario){
	VALOR_CRITERIO_HORARIO = "";
	var lista = jsonrpc._("horarioServicio.obtenerFranjas")(id_horario);
	
	if (lista != null) {
		lista = lista.list;
	}
	
	for ( var i = 0; i < lista.length; i++) {
		for ( var j = lista[i].hora_desde ; j < lista[i].hora_hasta; j++) {
			VALOR_CRITERIO_HORARIO = VALOR_CRITERIO_HORARIO+"(" + j + lista[i].dia + ")";
		}
	}
	var valor = VALOR_CRITERIO_HORARIO;
	
	osm_setValor('valor_vista_criterio_hora_L'+id_horario, compactarCalendarioDia(valor, 'L'));
	osm_setValor('valor_vista_criterio_hora_M'+id_horario, compactarCalendarioDia(valor, 'M'));
	osm_setValor('valor_vista_criterio_hora_C'+id_horario, compactarCalendarioDia(valor, 'C'));
	osm_setValor('valor_vista_criterio_hora_J'+id_horario, compactarCalendarioDia(valor, 'J'));
	osm_setValor('valor_vista_criterio_hora_V'+id_horario, compactarCalendarioDia(valor, 'V'));
	osm_setValor('valor_vista_criterio_hora_S'+id_horario, compactarCalendarioDia(valor, 'S'));
	osm_setValor('valor_vista_criterio_hora_D'+id_horario, compactarCalendarioDia(valor, 'D'));
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

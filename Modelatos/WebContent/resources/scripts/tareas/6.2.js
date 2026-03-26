$(function(){
    $('.date-pick').datepicker({
        minDate: 0
    });
    
    agregarValores(osm_getObjeto("hora"), 24, 1);
    agregarValores(osm_getObjeto("minuto"), 60, 5);
    
    definirFecha(osm_getValor("Tarea.fecha_inicio"));
    
	ID_TAREA = osm_getValor("Tarea.id_tarea");
	
    actualizarEstado();
});

function actualizarEstado(){
    try {
        jsonrpc._("tareaServicio.esActiva")(enEjecucion,ID_TAREA);
    } 
    catch (e) {
        alert("Error:" + e);
    }
}

function enEjecucion(en_ejecucion){
    
    if (en_ejecucion) {
        osm_setClassname("div_estado", "caja_estado_ok");
        osm_setValor("div_estado", "La tarea est\u00e1 activa");
        $(".caja_accion_cancel").show();
        $(".caja_accion_ok").hide();
	 	jsonrpc._("tareaServicio.calcularProximaEjecucion")(proxEjecucion,ID_TAREA);
    }
    else {
        osm_setClassname("div_estado", "caja_estado_cancel");
        osm_setValor("div_estado", "La tarea est\u00e1 desactivada");
        $(".caja_accion_ok").show();
        $(".caja_accion_cancel").hide();
		$("#div_estado_proceso").hide();
    }
    setTimeout("actualizarEstado()", 5000);
}
function proxEjecucion(date){
	if (date != null) {
		osm_setValor("div_estado_proceso","Proxima Ejecuci\u00f3n: "+ date);
		$("#div_estado_proceso").show();
	}else{
		$("#div_estado_proceso").hide();
	}
}

function activar(){
    var rta = jsonrpc._("tareaServicio.activar")(ID_TAREA);
    if (!rta) {
        alert("No se pudo iniciar el proceso");
    }
    else {
        actualizarEstado();
		osm_setValor("Tarea.activar","S");
    }
}

function desactivar(){
    var rta = jsonrpc._("tareaServicio.desactivar")(ID_TAREA);
    if (!rta) {
        alert("No se pudo finalizar el proceso");
    }
    else {
        actualizarEstado();
		osm_setValor("Tarea.activar","N");
    }
}

function getObjetoFecha(fecha){

    var objDate = new Date();
    try {
        objDate = new Date(fecha.substring(6, 10), parseInt(fecha.substring(3, 5)) - 1, fecha.substring(0, 2), fecha.substring(11, 13), fecha.substring(14, 16), 0, 0);
    } 
    catch (e) {
    }
    
    return objDate;
}

function definirFecha(fecha){
    var date = new Date();
    
    if (fecha && !osm_esVacio(fecha)) {
        date = getObjetoFecha(fecha);
    }
    
    var fecha = osm_getFecha(date.getTime() / 1000);
    osm_setValor("fecha", fecha);
    osm_getObjeto("hora").selectedIndex = date.getHours();
    osm_getObjeto("minuto").selectedIndex = date.getMinutes() / 5;
}

function ver_tarea(id_tarea){

    osm_setValor("id_tarea", id_tarea);
    osm_enviarFormulario("formVerTarea");
}

function agregarValores(select, tam, aumento){

    for (var i = 0; i < tam; i = i + aumento) {
        var text = i;
        text = i < 10 ? "0" + i : i;
        select.options[select.options.length] = new Option(text, i);
    };
    
    }

function validarTarea(){
    var hora = osm_getObjeto("hora");
    var minuto = osm_getObjeto("minuto");
    
    var fecha_tarea = osm_getObjetoFecha(osm_getValor("fecha"));
    fecha_tarea.setHours(hora.options[hora.selectedIndex].value);
    fecha_tarea.setMinutes(minuto.options[minuto.selectedIndex].value);
    osm_setValor("Tarea.fecha_inicio", fecha_tarea.getTime());
    
    var select_int = osm_getObjeto("Tarea.tipo_intervalo");
	
	if (esVacio("Tarea.nombre")) {
		  alert("Ingrese el nombre de la tarea");
		  osm_setFoco("Tarea.nombre");
		  return false;
	}
	if (esVacio("Tarea.end_point")) {
		  alert("Ingrese el EndPoint de la tarea");
		  osm_setFoco("Tarea.end_point");
		  return false;
	}
	
	if (esVacio("Tarea.intervalo_ejecucion")) {
		  alert("Ingrese el Intervalo de ejecuci\u00f3n de la tarea");
		  osm_setFoco("Tarea.intervalo_ejecucion");
		  return false;
	}
    
    if (select_int.selectedIndex == 0) {
        alert("Seleccione un tipo de intervalo");
        return false;
    }
    return true;
}
function esVacio(id){
	return osm_esVacio(osm_getValor(id));
}

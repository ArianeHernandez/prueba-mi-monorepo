var PAGINA_ACTUAL = 1;
var TOTAL_REGISTROS = 0;
var TAMANO_PAGINA = 0;
var PARAMS_BUSQUEDA = null;
var TOTAL_PAGINAS = 0;
var ROL = null;
var CONF_CONSULTA = null;
var CONF_CONSULTA_FILTRO = null;
var CONSULTA_MAP_PARAM = null;
var SOLICITANTES = null;
var TRAMITES= null;
var PROCESOS= null;
var DEPENDENCIAS = null;
var RESPONSABLES = null;
var TIENE_PERMISO = null;

$(window).load(initDoom);

function toggleFilter() {
	if ($('#areaFiltro').is(':visible')) {
		$('#areaFiltro').removeClass('hide').addClass('hide');
		$('#chevronFiltro').removeClass('fa-chevron-up').addClass(
				'fa-chevron-down');
		$('#area_resultado').css("margin-top", "8px");
	} else {
		$('#areaFiltro').removeClass('hide');
		$('#chevronFiltro').removeClass('fa-chevron-down').addClass(
				'fa-chevron-up');
		$('#area_resultado').css("margin-top", "24px");
	}
}

function initDoom() {
	$("#div_no_resultados").hide();
	TAMANO_PAGINA = jsonrpc._("reporteSolicitudNEAR.tamanoPagina")();
	ROL = jsonrpc._("reporteSolicitudNEAR.obtenerRolesAdministrativo")();
	CONF_CONSULTA = jsonrpc._(
			"historialConsultaServicio.obtenerConsultasConfVis")();
	CONF_CONSULTA_FILTRO = jsonrpc._(
			"historialConsultaServicio.obtenerConsultasConfFiltro")();
	
	placeholderSelects();
	contarSolicitudes();
	
// generarFiltros();
}

function cargarSolicitante(){
	if(SOLICITANTES == null)
		jsonrpc._(
		"reporteSolicitudNEAR.obtenerSolicitudesMapParametro")(mapToArray, "SOLICITANTE");
}

function cargarTramites(){
	if(TRAMITES == null)
		jsonrpc._(
		"reporteSolicitudNEAR.obtenerSolicitudesMapParametro")((resp)=>{mapToArrayGeneral(resp, "TRAMITE", "#tramite", 
			function(valor){TRAMITES=valor;})}, "TRAMITE");
}

function cargarProcesos(){
	if(PROCESOS == null)
		jsonrpc._(
		"reporteSolicitudNEAR.obtenerSolicitudesMapParametro")((resp)=>{mapToArrayGeneral(resp, "TIPO_SOLICITUD", "#proceso", 
			function(valor){PROCESOS=valor;})}, "TIPO_SOLICITUD");
}
function cargarDependencia(){
	if(DEPENDENCIAS == null)
		jsonrpc._(
		"reporteSolicitudNEAR.obtenerSolicitudesMapParametro")((resp)=>{mapToArrayGeneral(resp, "INTENDENCIA", "#intendencia", 
			function(valor){DEPENDENCIAS=valor;})}, "INTENDENCIA");
};


function cargarResponsables(){
	if(RESPONSABLES == null)
		jsonrpc._(
		"accionResponsableServicio.obtenerResponsablesAsignados")(llenarSelectResponsables);
};
function mapToArrayGeneral(resp, columna, idAppend, callbackLista){
	console.log("map gral");
	console.log(resp);
	if(resp && resp.list !=null){
		var listParams = resp.list;
		var newArray = [];
		for(const property in listParams){
			if(listParams[property].map[columna]!=null){
			newArray.push(listParams[property].map[columna]);}
		}
		
		newArray.forEach(function (i){
			var nombre_tag = "";

			if (i != null && i.length > 70) {
				nombre_tag = i.slice(0, 67)	+ "...";
			} else {
				nombre_tag = i;
			}

			$(idAppend).append($("<option>", {
				value: i,
				text: nombre_tag,
			}));
		});
		
		RESPONSABLES = resp.list;
		
	}
	
}

function llenarSelectResponsables(resp){
	console.log("responsables");
	console.log(resp);
	if(resp && resp.list !=null){
		
		resp.list.forEach(function (persona){

			$("#responsables").append($("<option>", {
				value: persona.id_persona,
				text: persona.nombreCompleto,
			}));
		});
		
	}
	
}

function mapToArray(resp){
	if(resp && resp.list !=null){
		var listParams = resp.list;
		var newArray = [];
		for(const property in listParams){
			newArray.push(listParams[property].map['SOLICITANTE']);
		}
		SOLICITANTES = newArray; 
		SOLICITANTES.forEach(function (i){
			var nombre_tag = "";

			if (i != null && i.length > 70) {
				nombre_tag = i.slice(0, 67)	+ "...";
			} else {
				nombre_tag = i;
			}

			$("#solicitante").append($("<option>", {
				value: i,
				text: nombre_tag,
			}));
		});
		$("#solicitante").each(function (i) {
			var input = selectToAutoComplete(this);
		});

		$("#solicitante_autocomplete").removeClass();
		$("#solicitante_autocomplete").addClass(
			"input-default form-input");
	}
	
}

function placeholderSelects() {
	$("#id_estado").change(function() {
		if ($(this).val() == "0") {
			$(this).addClass("placeholder");
		} else {
			$(this).removeClass("placeholder");
		}
	});
	$("#id_estado").change();
	$("#tipo_solicitante").change(function() {
		if ($(this).val() == "0") {
			$(this).addClass("placeholder");
		} else {
			$(this).removeClass("placeholder");
		}
	});
	$("#tipo_solicitante").change();
	$("#categoria").change(function() {
		if ($(this).val() == "0") {
			$(this).addClass("placeholder");
		} else {
			$(this).removeClass("placeholder");
		}
	});
	$("#categoria").change();
	$("#intendencia").change(function() {
		if ($(this).val() == "0") {
			$(this).addClass("placeholder");
		} else {
			$(this).removeClass("placeholder");
		}
	});
	$("#intendencia").change();
	$("#tramite").change(function() {
		if ($(this).val() == "") {
			$(this).addClass("placeholder");
		} else {
			$(this).removeClass("placeholder");
		}
	});
	$("#tramite").change();
	$("#proceso").change(function() {
		if ($(this).val() == "") {
			$(this).addClass("placeholder");
		} else {
			$(this).removeClass("placeholder");
		}
	});
	$("#proceso").change();
	$("#responsables").change(function() {
		if ($(this).val() ==  "") {
			$(this).addClass("placeholder");
		} else {
			$(this).removeClass("placeholder");
		}
	});
	$("#responsables").change();
		$("#lista_responsables").change(function() {
		if ($(this).val() ==  "") {
			$(this).addClass("placeholder");
		} else {
			$(this).removeClass("placeholder");
		}
	});
	$("#lista_responsables").change();
	$("#tipo_solicitante").change();	
}

function contarSolicitudes() {
	osm_verLoader();
	$("#historialHeader").empty();
	$(".tabla_fila").remove();
	osm_block_window();
	var radicado = osm_getValor("numero_radicado");
	var estado = null;
	var id_instancia;
	if (osm_getValor("id_estado") != "0") {
		estado = osm_getValor("id_estado");
		if (!isNaN(estado)) {
			id_instancia = estado;
			estado = null;
		}
	}
	var fecha_solicitud = osm_getValor("fecha_solicitud");
	var solicitante = osm_getValor("solicitante");
	var tipo_solicitante = null;
	if (osm_getValor("tipo_solicitante") != "0") {
		tipo_solicitante = osm_getValor("tipo_solicitante");
	}
	var fecha_radicacion = osm_getValor("fecha_radicacion");
	var fecha_radicacion_fin = osm_getValor("fecha_radicacion_fin");
	var identificacion = osm_getValor("identificacion");
	var categoria = null;
	if (osm_getValor("categoria") != "0") {
		categoria = osm_getValor("categoria");
	}
	var intendencia = null;
	if (osm_getValor("intendencia") != "0") {
		intendencia = osm_getValor("intendencia");
	}
	var id_solicitud = osm_getValor("id_solicitud");
	var proceso = osm_getValor("proceso");
	var tramite = osm_getValor("tramite");
	var responsable = osm_getValor("responsables");
	if (ROL != null) {
		// intendencia = filtroRol();
	}

	PARAMS_BUSQUEDA = {
		radicado : radicado,
		estado : estado,
		fecha_solicitud : fecha_solicitud,
		solicitante : solicitante,
		tipo_solicitante : tipo_solicitante,
		fecha_radicacion : fecha_radicacion,
		fecha_radicacion_fin : fecha_radicacion_fin,
		identificacion : identificacion,
		categoria : categoria,
		id_instancia : id_instancia,
		intendencia : intendencia,
		id_solicitud : id_solicitud,
		proceso : proceso,
		tramite : tramite,
		responsable : responsable
	};

	jsonrpc._("reporteSolicitudNEAR.obtenerTotalSolicitudes")(
			resultadoContarSolicitudes, PARAMS_BUSQUEDA.radicado,
			PARAMS_BUSQUEDA.estado, PARAMS_BUSQUEDA.fecha_solicitud,
			PARAMS_BUSQUEDA.solicitante, PARAMS_BUSQUEDA.tipo_solicitante,
			PARAMS_BUSQUEDA.fecha_radicacion,
			PARAMS_BUSQUEDA.fecha_radicacion_fin,
			PARAMS_BUSQUEDA.identificacion, PARAMS_BUSQUEDA.categoria,
			PARAMS_BUSQUEDA.id_instancia, PARAMS_BUSQUEDA.intendencia,
			PARAMS_BUSQUEDA.id_solicitud, PARAMS_BUSQUEDA.proceso,
			PARAMS_BUSQUEDA.tramite, PARAMS_BUSQUEDA.responsable );
	
		
	
	
}


function filtroRol() {
	for (var i = 0; i < ROL.list.length; i++) {

	}
}

function resultadoContarSolicitudes(result) {
	if (result != null) {
		if (result > 0) {
			TOTAL_PAGINAS = 1 + parseInt((result - 1) / TAMANO_PAGINA);
			var regInicial = ((PAGINA_ACTUAL - 1) * TAMANO_PAGINA) + 1;
			var regFinal = (PAGINA_ACTUAL * TAMANO_PAGINA) > result ? result
					: (PAGINA_ACTUAL * TAMANO_PAGINA);
			$("#curr_pag").text(regInicial + " a " + regFinal);
			$("#last_pag").text(result);

			// jsonrpc._("reporteSolicitudNEAR.obtenerSolicitudes")(mostrarSolicitudes,
			// PAGINA_ACTUAL);
			jsonrpc._("reporteSolicitudNEAR.obtenerSolicitudesMap")(
					mostrarSolicitudesMap, PAGINA_ACTUAL);
			cargarSolicitante();
			cargarTramites();
			cargarProcesos();
			cargarDependencia();
			cargarResponsables();

		} else {
			osm_ocultarLoader();
			osm_unblock_window();
			$("#div_no_resultados").show();
			$("#area_resultado").hide();
		}
	} else {
		osm_alert("Ocurri\u00f3 un error realizando la consulta");
		osm_ocultarLoader();
		osm_unblock_window();
	}
	
}

function mostrarSolicitudes(result) {
	if (result != null) {
		console.log("result");
		console.log(result);
		$("#historialHeader").empty();

		var registros = result.list;

		for (var i = 0; i < registros.length; i++) {
			reg = registros[i];

			osm_construirHTML("area_registros", "PLANTILLA_REGISTRO", [
					reg.fecha_solicitud_formato, reg.radicado,
					reg.fecha_radicado_formato, reg.tipo_solicitante,
					reg.solicitante, reg.tipo_solicitud, reg.identificacion,
					reg.municipio, reg.departamento,
					reg.total_trabajadores_formato, reg.categoria,
					reg.fecha_ef, reg.activos_mes_formato,
					reg.pasivos_mes_formato, reg.total_activos_formato,
					reg.total_pasivos_formato, reg.ciiu, reg.macrosector,
					reg.supuesto_insolvencia, reg.ponente, reg.id_carga,
					reg.estado_nombre, reg.intendencia, reg.numero_proceso,
					reg.tramite, reg.proceso ]);
		}

		if (PAGINA_ACTUAL == 1) {
			$(".botonl").removeClass("botones").removeClass("botones_inactivo")
					.addClass("botones_inactivo");
			$(".botoncl").removeClass("botones")
					.removeClass("botones_inactivo").addClass(
							"botones_inactivo");
		} else {
			$(".botonl").removeClass("botones").removeClass("botones_inactivo")
					.addClass("botones");
			$(".botoncl").removeClass("botones")
					.removeClass("botones_inactivo").addClass("botones");
		}

		if (PAGINA_ACTUAL == TOTAL_PAGINAS) {
			$(".botonr").removeClass("botones").removeClass("botones_inactivo")
					.addClass("botones_inactivo");
			$(".botoncr").removeClass("botones")
					.removeClass("botones_inactivo").addClass(
							"botones_inactivo");
		} else {
			$(".botonr").removeClass("botones").removeClass("botones_inactivo")
					.addClass("botones");
			$(".botoncr").removeClass("botones")
					.removeClass("botones_inactivo").addClass("botones");
		}

		$("#div_no_resultados").hide();
		$("#area_resultado").show();
	} else {
		osm_alert("Ocurri\u00f3 un error realizando la consulta");
	}
	osm_ocultarLoader();
	osm_unblock_window();
}

function mostrarSolicitudesMap(resp) {
	console.log("mostrarSolicitudes");
	console.log(resp);

	if (resp.list && resp.list.length > 0) {
		generarEncabezado(resp.list[0].map);
		generarCuerpo(resp.list);
	}else{
		osm_ocultarLoader();
		osm_unblock_window();
	}

}
function generarEncabezado(data) {
	for (var i = 0; i < CONF_CONSULTA.list.length; i++) {
		$("#historialHeader").append(
				"<td style=\"top: 0;position: sticky;\">" + CONF_CONSULTA.list[i].etiqueta_columna + "</td>");
	}
	$("#historialHeader").append("<td style=\"top: 0;position: sticky;\">REVISIÓN</td>");
}

function generarCuerpo(data) {
	for (var i = 0; i < data.length; i++) {
		var rowContenido = data[i].map;
		$("#idTbody")
				.append(
						"<tr id=\"tr"
								+ i
								+ "\" onmouseout=\"this.className='tabla_fila '\" onmouseover=\"this.className='tabla_fila_over '\" "
								+ "class=\"tabla_fila\"></tr>");

		for (var j = 0; j < CONF_CONSULTA.list.length; j++) {
			$("#tr" + i)
					.append(
							"<td>"
									+ formatoContenido(rowContenido[CONF_CONSULTA.list[j].etiqueta_columna])
									+ "</td>");

		}
		$("#tr" + i)
				.append(
						"<td>" + generarAreaBotones(rowContenido[CONF_CONSULTA.list[0].etiqueta_columna])
								+ "</td>");
	}
	$("#area_resultado").show();
	osm_ocultarLoader();
	osm_unblock_window();
}

function generarAreaBotones(idCarga) {
	var html = "<div class=\"row-btn\">"
			+ "<a estilo=\"btn btn-sm btn-primary sm\" onclick=\"verDetalleHistoria('"
			+ idCarga
			+ "');return false;\""
			+ " class=\"btn btn-sm btn-primary\"><i aria-hidden=\"true\" class=\"fa fa-history\"></i>&nbsp;Historial</a><a estilo=\"btn btn-sm btn-primary\" "
			+ "onclick=\"verDetalleCarga('"
			+ idCarga
			+ "'); return false;\" class=\"btn btn-sm btn-primary\"><i aria-hidden=\"true\" class=\"fa fa-eye\"></i>&nbsp;Detalle</a>";
			if(tienePermisoVerBoton()){
				html = html + "<a estilo=\"btn btn-sm btn-primary\" "
				+ "onclick=\"verResponsableCarga('"
				+ idCarga
				+ "'); return false;\" class=\"btn btn-sm btn-primary\"><i aria-hidden=\"true\" class=\"fa fa-eye\"></i>&nbsp;Cambiar Responsable</a>"
			}
			html = html + "<div class=\"clear\"></div></div>"
	return html;
}

function tienePermisoVerBoton() {
	if(TIENE_PERMISO == null)
		TIENE_PERMISO = jsonrpc._("accesoRolServicio.validarAccesoAdministrativo")(-1)
	return TIENE_PERMISO;
}

function siguiente() {
	if (PAGINA_ACTUAL < TOTAL_PAGINAS) {
		PAGINA_ACTUAL++;
		contarSolicitudes();
	}
}

function anterior() {
	if (PAGINA_ACTUAL > 1) {
		PAGINA_ACTUAL--;
		contarSolicitudes();
	}
}

function primero() {
	PAGINA_ACTUAL = 1;
	contarSolicitudes();
}

function ultimo() {
	PAGINA_ACTUAL = TOTAL_PAGINAS;
	contarSolicitudes();
}

function descargar_reporte() {
	osm_block_window();
	osm_verLoader();
	jsonrpc._("reporteSolicitudNEAR.generarReporteMap")(obtenerArchivo);
}

function obtenerArchivo(id_archivo) {
	if (id_archivo != null && id_archivo > -1) {
		var url = '../DescargaArchivoServlet?id_archivo=' + id_archivo;
		window.open(url);
	} else {
		osm_alert("Ha ocurrido un error al descargar el reporte")
	}
	osm_unblock_window();
	osm_ocultarLoader();
}

// Retorna el texto del estado
function estado(id, nombre_estado) {

	if (!osm_esVacio(nombre_estado)) {
		return nombre_estado;
	}

	return ESTADOS_CARGA[id];
}

function mostrarHistCargaCliente(listado) {
	$("tr[name='reg_hist_cliente']").remove();
	if (listado != null) {
		listado = listado.list;
		for (var i = 0; i < listado.length; i++) {

			var reg = listado[i];

			var fecha = reg.fecha;
			fecha = fecha == null || fecha.time == null ? ""
					: osm_getFecha(fecha.time) + " " + osm_getHora(fecha.time);

			var persona = osm_esVacio(reg.nombres_persona) ? ""
					: reg.nombres_persona + " ";
			persona += osm_esVacio(reg.apellidos_persona) ? ""
					: reg.apellidos_persona;

			osm_construirHTML("cont_hist_cliente", "PLANTILLA_HIST_CLIENTES", [
					estado(reg.estado, reg.nombre_estado), persona, fecha,
					"reg_hist_cliente",
					(reg.peso != null) ? ("<b>Peso: </b>" + reg.peso) : "" ]);

		}
		$("#div_hist_cliente").show();
		osm_setVisible("vn_historialcarga", true, true);
		osm_ocultarSelects("bodyContent");
	}
}

function verDetalleHistoria(id_carga) {
	jsonrpc._("cargaServicio.obtenerHistorialCargaInstancia")(
			mostrarHistCargaIns, id_carga);
}

function verDetalleCarga(id_carga) {
	var id_formato_salida = jsonrpc
			._("cargaServicio.obtenerFormatoSalidaCarga")(id_carga);

	if (id_formato_salida == null) {

		osm_alert("Lo sentimos, no se encuentra un formato para consulta.");
		return;
	}

	osm_setValor("id_carga_detalle", id_carga);
	osm_setValor("id_formato_salida_detalle", id_formato_salida);

	osm_enviarFormulario("form_detallecarga");
}

function mostrarHistCargaCliente(listado) {
	$("tr[name='reg_hist_cliente']").remove();
	if (listado != null) {
		listado = listado.list;
		for (var i = 0; i < listado.length; i++) {

			var reg = listado[i];

			var fecha = reg.fecha;
			fecha = fecha == null || fecha.time == null ? ""
					: osm_getFecha(fecha.time) + " " + osm_getHora(fecha.time);

			var persona = osm_esVacio(reg.nombres_persona) ? ""
					: reg.nombres_persona + " ";
			persona += osm_esVacio(reg.apellidos_persona) ? ""
					: reg.apellidos_persona;

			osm_construirHTML("cont_hist_cliente", "PLANTILLA_HIST_CLIENTES", [
					estado(reg.estado, reg.nombre_estado), persona, fecha,
					"reg_hist_cliente",
					(reg.peso != null) ? ("<b>Peso: </b>" + reg.peso) : "" ]);

		}
		$("#div_hist_cliente").show();
		osm_setVisible("vn_historialcarga", true, true);
		osm_ocultarSelects("bodyContent");
	}
}

function mostrarHistCargaIns(listado) {
	$("tr[name='reg_hist_instancia']").remove();
	if (listado != null) {
		listado = listado.list;
		for (var i = 0; i < listado.length; i++) {
			var reg = listado[i];

			var fechaLlegada = reg.fecha_llegada;
			fechaLlegada = fechaLlegada == null || fechaLlegada.time == null ? ""
					: osm_getFecha(fechaLlegada.time) + " "
							+ osm_getHora(fechaLlegada.time);

			var fechaSalida = reg.fecha_salida;
			fechaSalida = fechaSalida == null || fechaSalida.time == null ? ""
					: osm_getFecha(fechaSalida.time) + " "
							+ osm_getHora(fechaSalida.time);

			var persona = osm_esVacio(reg.nombre_persona) ? ""
					: reg.nombre_persona + " ";
			persona += osm_esVacio(reg.apellido_persona) ? ""
					: reg.apellido_persona;

			var fila = "<tr name=\"reg_hist_instancia\"  class=\"tabla_fila\"  onmouseover=\"this.className='tabla_fila_over'\" onmouseout=\"this.className='tabla_fila'\">"
					+ "<td>"
					+ reg.nombre_instancia
					+ "</td><td>"
					+ persona
					+ "</td><td>"
					+ fechaLlegada
					+ "</td><td>"
					+ fechaSalida
					+ "</td></tr>";
			$("#cont_hist_instancias").append(fila);
			// osm_construirHTML("cont_hist_instancias",
			// "PLANTILLA_HIST_INSTANCIAS", [ reg.nombre_instancia,
			// persona, fechaLlegada, fechaSalida,
			// "reg_hist_instancia" ]);

		}
		$("#div_hist_instancias").show();
		osm_setVisible("vn_historialcarga", true, true);
		osm_ocultarSelects("bodyContent");
	}
}

function cerrarVentanaHistorial() {
	osm_mostrarSelects("bodyContent");
	osm_setVisible("vn_historialcarga", false, true);
}

function formatoFecha(timestamp) {
	var d = new Date(timestamp * 1000), month = '' + (d.getMonth() + 1), day = ''
			+ d.getDate(), year = d.getFullYear();

	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;

	return [ year, month, day ].join('-');
}

function formatoContenido(valor) {
	return (valor == null ? "-" : valor.time != null ? formatoFecha(valor.time)
			: valor);
}

function generarFiltroInput(elemento, agregarA) {
	var elementoHtml = "<div class=\"flex-item\">"
			+ "<div class=\"flex-registro\">" + elemento.etiqueta_columna
			+ "</div>" + "<div class=\"flex-valor\">"
			+ "<input placeholder=\"" + elemento.etiqueta_columna
			+ "\" type=\"text\"" + "class=\"input-default form-input\""
			+ "style=\"font-family: FontAwesome, Poppins !important;" + "id=\""
			+ elemento.nombre_columna + "\" />" + "</div>" + "</div>";
	$("#" + agregarA).append(elementoHtml);
}

function generarFiltroSelector() {

}

function generarFiltroFecha(elemento, agregarA) {
	var elementoHtmlInicio = "<div class=\"flex-item\">"+
	"<div class=\"flex-registro\">Inicio "+elemento.etiqueta_columna+"</div>"+
	"<div class=\"flex-valor\">"+
	"<input class=\"date-pick form-control hasDatepicker\" value=\"\" id=\"Inico_"+elemento.nombre_columna+"\" name=\"Inicio_"+elemento.nombre_columna+"\" type=\"text\"><img class=\"ui-datepicker-trigger\" src=\"../images/general/bg-go.png\" alt=\"...\" title=\"...\">"+
	"</div>"+
	"</div>";
	
	var elementoHtmlFin = "<div class=\"flex-item\">"+
	"<div class=\"flex-registro\">Fin "+elemento.etiqueta_columna+"</div>"+
	"<div class=\"flex-valor\">"+
	"<input class=\"date-pick form-control hasDatepicker\" value=\"\" id=\"Fin_"+elemento.nombre_columna+"\" name=\"Fin_"+elemento.nombre_columna+"\" type=\"text\"><img class=\"ui-datepicker-trigger\" src=\"../images/general/bg-go.png\" alt=\"...\" title=\"...\">"+
	"</div>"+
	"</div>";
	
	$("#" + agregarA).append(elementoHtmlInicio);
	$("#" + agregarA).append(elementoHtmlFin);
}

function generarFiltros() {
	if (CONF_CONSULTA_FILTRO && CONF_CONSULTA_FILTRO.list) {
		for (var i = 0; i < CONF_CONSULTA_FILTRO.list.length; i++) {
// generarFiltroInput(CONF_CONSULTA_FILTRO.list[i], "areaFiltro");
			generarFiltroFecha(CONF_CONSULTA_FILTRO.list[i], "areaFiltro");
		}
	}else{
		alert("No se pudieron generar los filtros");
	}
}

function verResponsableCarga(id_carga) {
	jsonrpc._(
		"accionResponsableServicio.obtenerInfoResponsableCarga")((resp)=>{verVentanaResponsableCarga(resp, id_carga)}, id_carga);
}

function verVentanaResponsableCarga(resp, id_carga) {
	
	if(resp == null){
		console.log("Ocurrio un error en llamado obtenerInfoResponsableCarga");
		return;
	}
	
	console.log("verVentanaResponsableCarga", resp, id_carga)
	
	var responsable_actual = resp.map.responsable_actual;
	console.log(responsable_actual);
	
	if(responsable_actual == null){
		$("#mensaje_no_responsable").show();
		$("#area_asignarResponsableCarga").hide();
		$("#vn_btn_guardar").hide();
	}else{
		$("#mensaje_no_responsable").hide();
		$("#area_asignarResponsableCarga").show();
		$("#vn_btn_guardar").show();
		osm_setValor("vn_responsable_actual", responsable_actual.nombreCompleto);
		osm_setValor("vn_id_instancia", responsable_actual.id_instancia);
		var lista_responsables = resp.map.lista_responsables.list;
		
		console.log("lista_responsables");
		console.log(lista_responsables);
		
		if(resp.map.lista_responsables !=null){
			$('#lista_responsables')
		    .find('option')
		    .remove()
		    .end()
		    .append('<option value="">-- Seleccione --</option>')
		    .val('whatever')
			;
			lista_responsables.forEach(function (persona){
	
				$("#lista_responsables").append($("<option>", {
					value: persona.id_persona,
					text: persona.nombreCompleto,
				}));
			});
			
		}
	} 
	

	osm_setValor("vn_id_carga", id_carga);
	osm_setVisible("vn_responsableCarga", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaResponsableCarga() {
	osm_mostrarSelects("bodyContent");
	osm_setVisible("vn_responsableCarga", false, true);
}

function guardarResponsableCarga() {
	var responsable = osm_getValor("lista_responsables");
	var id_carga = osm_getValor("vn_id_carga");
	var id_instancia = osm_getValor("vn_id_instancia");
	cerrarVentanaResponsableCarga();
	jsonrpc._(
		"accionResponsableServicio.guardarResponsableCarga")(responseGuardarResponsableCarga, responsable, id_carga, id_instancia);
}

function responseGuardarResponsableCarga(resp) {
	if(resp){
		osm_alert_success("Se realiz\u00F3 exitosamente", "Exito", null, null);
	} else {
		osm_alert_danger("Ocurri\u00F3 un error al guardar", "Error", null, null);
	}
}

function rc_ajusteVentanaHistorialCarga() {
	var pos = osm_getWindowSize();
	$('#vn_historialcarga .bloqueo_ventana_base').css("height", (pos[1] - 150) + "px").css("overflow", "auto");
}

osm_listen("resize", window, rc_ajusteVentanaHistorialCarga);
osm_listen("load", window, rc_ajusteVentanaHistorialCarga);
let instancias = new Object();
let instanciaSeleccionada = null;
let instanciasDestino =  new Object();
let accionesAsociadasDraggable = new Set();

function cargarInstanciasAcciones() {
	instancias = jsonrpc._('instanciaJsonServicio.obtenerInstanciasPorProcesoMap')(idProcesoAdmin).map;
	instanciasDestino = jsonrpc._('accionJsonServicio.obtenerAccionInstanciasDestinoPorProcesoAdmin')(idProcesoAdmin).map;
}

function obtenerParametrosInstancia (instancia) {
	return 	[ 	instancia.id_instancia,
		       	instancia.nombre,
		       	instancia.inicial,
		       	instancia.aprobar,
		       	instancia.rechazar,
		       	instancia.tiempo,
		       	instancia.id_formato_salida,
		       	instancia.posicion_x,
		       	instancia.posicion_y,
		       	instancia.id_horario,
		       	instancia.adicionar_documentos,
		       	instancia.gestionar_documentos,
		       	instancia.aprobar_automaticamente,
		       	instancia.rechazar_automaticamente,
		       	instancia.solicitar_otp,
		       	instancia.ocultar_sin_filtro
           	];
}

function listarInstanciasProcesoAdmin() {
	if (Object.keys(instancias).length !== 0) {
		Object.values(instancias).forEach( instancia => {
			var parametros = obtenerParametrosInstancia(instancia);
			pintarInstanciaGrafica('AREA_PROCESO', parametros );
		});
		
	}
}

function pintarInstanciaGrafica(idPadre, parametros){
	osm_construirHTML(idPadre, 'PLANTILLA_INSTANCIA_GRAFICA', parametros);
	$('#div_instancia_' + parametros[0]).draggable({
		cursorAt: { top: 40, left: 50 },
		start: function() { 
			obtenerAccionesAsociadas(this.idInstancia);
			limpiarAccionesAsociadas();
		},
		drag: function() { 
			actualizarPosInstancia(this);
		},
		stop: function() { 
			actualizarPosInstancia(this); 
			actualizarAccionesAsociadas();
			actualizarInstancia(this.idInstancia); 
		}
	});
	$("#div_instancia_" + parametros[0]).css( { left: parametros[7] + "px", top: parametros[8] + "px" } );
	$("#div_instancia_" + parametros[0]).mousedown(abrirDetalleInstancia);
	$("#div_instancia_" + parametros[0]).get(0).idInstancia = parametros[0];
}

function obtenerAccionesAsociadas(idInstancia) {
	accionesAsociadasDraggable = new Set();
	$("[instancia_origen='" + idInstancia + "']").each( function(){
		accionesAsociadasDraggable.add($(this).attr("accion"));
	});
	$("[instancia_destino='" + idInstancia + "']").each( function(){
		accionesAsociadasDraggable.add($(this).attr("accion"));
	});
}

function limpiarAccionesAsociadas() {	
	for (const accion of accionesAsociadasDraggable) {
		$("[accion='" + accion + "']").remove();
	}
}

function actualizarAccionesAsociadas() {
	for (const accion of accionesAsociadasDraggable) {
		  pintarAccion(instanciasDestino[accion], instancias[instanciasDestino[accion].id_instancia]);
	}
}

function actualizarPosInstancia(div){
	instancias[div.idInstancia].posicion_x = parseInt($(div).css("left"));
	instancias[div.idInstancia].posicion_y= parseInt($(div).css("top"));
}

function abrirDetalleInstancia(){
	$("#temp_div_horario").append($("#blk_horario"));
	
	actualizarInstanciaSeleccionada(this.idInstancia);
	pintarDetalleInstancia(instancias[this.idInstancia]);
	instanciaSeleccionada = this.idInstancia;
	
	osm_setValor("id_horario", instancias[this.idInstancia].id_horario);
	$("#area_horario_instancia_" + this.idInstancia).append($("#blk_horario"));
	actualizarHorario();
	HORARIO_SELECCION = function(){
		actualizarHorarioInstancia(instanciaSeleccionada, VALOR_CRITERIO_HORARIO_CP);
	}
}

function actualizarHorarioInstancia(idInstancia, franjas) {
	var exitoActualizar = jsonrpc._("instanciaJsonServicio.actualizarHorarioInstancia")(idInstancia, franjas);
	if (exitoActualizar) {
		var instancia = jsonrpc._("instanciaJsonServicio.obtenerInstancia")(idInstancia);
		instancias[idInstancia].id_horario = instancia.id_horario;
	} else {		
		mostrarVentanaActualiza('No se pudo actualizar la instancia.');
	}
}

function actualizarInstanciaSeleccionada(idInstancia) {
	$("div[id*='div_instancia']").removeClass("instancia_seleccionada");
	$("#div_instancia_" + idInstancia).addClass("instancia_seleccionada");
	$(".area_contenido_inst").hide();
	osm_setValor('contenido_lista_inst_proceadmin', '');
	instanciaSeleccionada = idInstancia;
}

function pintarLineasInstanciasDestino(){
	$("#AREA_PROCESO_LINEAS").empty();
	
	if (Object.keys(instancias).length !== 0) {
		Object.values(instancias).forEach( inst => {
			pintarLineasPorInstancia(inst);
		});
	}
}

function agregarAtributos(div, datos) {
	$(div).attr("instancia_origen", datos.instanciaOrigen);
	$(div).attr("instancia_destino", datos.instanciaDestino);
	$(div).attr("accion", datos.accion);
}

function pintarLineasPorInstancia(instOrigen){
	var accionesList = instancias[instOrigen.id_instancia].accionesPorInstancia.list;

	if (accionesList.length > 0) {
		for (var j = 0; j < accionesList.length; j++) {
			pintarAccion(accionesList[j], instOrigen);
		}
	}
}

function crearInstancia(idProcesoAdmin) {
	var idInstancia = jsonrpc._("instanciaJsonServicio.obtenerSiguienteId")();
	var exitoCrear = jsonrpc._("instanciaJsonServicio.insertarInstancia")(idInstancia, '', 'N', 'N', 10, 0, 0, idProcesoAdmin, 'N', null, null, 'N', 'N', 'N');
	
	if (exitoCrear) {	
		var instancia = jsonrpc._("instanciaJsonServicio.obtenerInstancia")(idInstancia);
		var parametros = obtenerParametrosInstancia (instancia);
		
		instancias[instancia.id_instancia] = instancia;
		instancias[instancia.id_instancia].accionesPorInstancia = {javaClass: 'java.util.ArrayList', list: new Array()};
		
		pintarInstanciaGrafica('AREA_PROCESO', parametros );
		$("#div_instancia_" + idInstancia).trigger('mousedown');

	} else {
		alert('Ha ocurrido un error al crear la instancia.');
	}

}

function eliminarInstanciaSeleccionada(){
	if(!osm_esVacio(instanciaSeleccionada)){
		var res = confirm("¿Est\u00E1 seguro que desea eliminar la instancia seleccionada?");
		if(res){
			eliminarInstancia(instanciaSeleccionada);			
		}
	}else{
		osm_alert("Por favor seleccione la instancia que desea eliminar");
	}
}

function eliminarInstancia(idInstancia) {
	var exitoEliminar = jsonrpc._("instanciaJsonServicio.borrarInstancia")(idInstancia);

	if (exitoEliminar) {
		obtenerAccionesAsociadas(idInstancia);
		limpiarAccionesAsociadas();
		$("#temp_div_horario").append($("#blk_horario"));
		$("#div_instancia_" + idInstancia).remove();
		
		cargarInstanciasAcciones();
		
		instanciaSeleccionada = null;
		construirSeccionDetalleInstancia();
	} else {
		alert('Ha ocurrido un error al eliminar la instancia.');
	}	
}

function pintarAccion(accion, instOrigen) {
	var gr = new jsGraphics(document.getElementById("AREA_PROCESO_LINEAS"));
	
	//Se buscan las instancias destino por accion
	var instDestList = instanciasDestino[accion.id_accion].instancias_destino.list;
	
	if (instDestList.length > 0) {
		//Create jsColor object
		var col= null;
		
		if(instOrigen.id_instancia==parseInt(instanciaSeleccionada)){
			col = new jsColor("#384656");
			
			if(accion.oculto == 'S'){
				col = new jsColor("silver");	
			}
		}else{
			col = new jsColor("#384656");
			
			if(accion.oculto == 'S'){
				col = new jsColor("silver");	
			}
		}						
		
		for (var k = 0; k < instDestList.length; k++) {
			instDestino = instancias[instDestList[k]];
			//Create jsPen object
			var pen = new jsPen(col, 1);
			
			//Draw a Line between 2 points
			var ancho_medio=50;
			var alto_medio=40;
			
			var x1 = instOrigen.posicion_x + ancho_medio;
			var y1 = instOrigen.posicion_y + alto_medio;
			
			var x2 = instDestino.posicion_x + ancho_medio;
			var y2 = instDestino.posicion_y + alto_medio;
			
			//Se definene los puntos para la linea
			var pt1 = null;
			var pt2 = null;
			var pt1_medio=null;
			var pt2_medio=null;
			
			//Se definen los puntos la flecha
			var ptT1 = null;
			var ptT2 = null;
			var ptT3 = null;
			
			//Se define la posicion del texto
			var ptTexto = null;
			
			var distanciaX = Math.abs(x2-x1);
			var distanciaY = Math.abs(y2-y1);
			
			var ancho_flecha =15;
			var alto_medio_flecha=4;
			var posTextoX = parseInt(ancho_medio/3);
			var posTextoY = parseInt(alto_medio/3);
			var corrimientoTexto= parseInt(ancho_medio/2)+accion.nombre.length;
			var distanciaFeclaEntraSalida = 20;
			 
			if(distanciaX>=distanciaY){
				if(x1<=x2){
					y1=y1-distanciaFeclaEntraSalida;
					y2=y2-distanciaFeclaEntraSalida;
					
					x1=x1+ancho_medio;
					x2=x2-ancho_medio-ancho_flecha;
					
					ptT1 = new jsPoint(x2, y2+alto_medio_flecha); 
					ptT2 = new jsPoint(x2, y2-alto_medio_flecha); 
					ptT3 = new jsPoint(x2+ancho_flecha, y2); 
					
					if(y1>y2){
						ptTexto = new jsPoint(x1+posTextoX, y1-posTextoY-corrimientoTexto); 
					}else{
						ptTexto = new jsPoint(x1+posTextoX, y1+posTextoY); 
					}
				}else{
					y1=y1+distanciaFeclaEntraSalida;
					y2=y2+distanciaFeclaEntraSalida;
					
					x1=x1-ancho_medio;
					x2=x2+ancho_medio+ancho_flecha;
					
					ptT1 = new jsPoint(x2, y2+alto_medio_flecha); 
					ptT2 = new jsPoint(x2, y2-alto_medio_flecha); 
					ptT3 = new jsPoint(x2-ancho_flecha, y2);;
					
					if(y1>y2){
						ptTexto = new jsPoint(x1-posTextoX-ancho_medio, y1-posTextoY-corrimientoTexto); 
					}else{
						ptTexto = new jsPoint(x1-posTextoX-ancho_medio, y1+posTextoY); 
					}
				}
				pt1 = new jsPoint(x1, y1);
				pt1_medio = new jsPoint(parseInt(x1 + ((x2 - x1) / 1.5)), y1);
				pt2_medio = new jsPoint(parseInt(x1 + ((x2 - x1) / 1.5)), y2);
				pt2 = new jsPoint(x2, y2);
			}else{
				if(y1<=y2){
					x1=x1+distanciaFeclaEntraSalida;
					x2=x2+distanciaFeclaEntraSalida;
					
					y1=y1+alto_medio;
					y2=y2-alto_medio-ancho_flecha;
					
					ptT1 = new jsPoint(x2+alto_medio_flecha, y2); 
					ptT2 = new jsPoint(x2-alto_medio_flecha, y2); 
					ptT3 = new jsPoint(x2, y2+ancho_flecha); ;
						
					ptTexto = new jsPoint(x1, y1+ancho_flecha); 
					
					if(x1<x2){
						ptTexto = new jsPoint(x1+posTextoX, y1+posTextoY); 
					}else{
						ptTexto = new jsPoint(x1-posTextoX-corrimientoTexto, y1+posTextoY); 
					}
				}else{
					x1=x1-distanciaFeclaEntraSalida;
					x2=x2-distanciaFeclaEntraSalida;
					
					y1=y1-alto_medio;
					y2=y2+alto_medio+ancho_flecha;
					
					ptT1 = new jsPoint(x2+alto_medio_flecha, y2); 
					ptT2 = new jsPoint(x2-alto_medio_flecha, y2);
					ptT3 = new jsPoint(x2, y2-ancho_flecha);
													
					if(x1<x2){
						ptTexto = new jsPoint(x1+posTextoX, y1-posTextoY-corrimientoTexto); 
					}else{
						ptTexto = new jsPoint(x1-posTextoX-corrimientoTexto, y1-posTextoY-corrimientoTexto); 
					}
				}
				pt1 = new jsPoint(x1, y1);
				pt1_medio = new jsPoint(x1, parseInt(y1 + ((y2 - y1) / 1.5)));
				pt2_medio = new jsPoint(x2, parseInt(y1 + ((y2 - y1) / 1.5)));
				pt2 = new jsPoint(x2, y2);
			}
			
			//Se pinta la linea
			var points = [pt1, pt1_medio, pt2_medio, pt2];
			
			var datos = {
					instanciaOrigen: instOrigen.id_instancia,
					instanciaDestino: instDestino.id_instancia,
					accion: accion.id_accion
			};
			
			var div_polybezier = gr.drawPolyBezier(pen, points);
			agregarAtributos(div_polybezier, datos);
			
			// Se pinta la flecha
			var pointsPolygon = [ptT1, ptT2, ptT3];
			var div_polygon = gr.fillPolygon(col, pointsPolygon);
			agregarAtributos(div_polygon, datos);
			
			var div_circle4 = gr.drawCircle(pen, pt1, 4);
			agregarAtributos(div_circle4, datos);
			
			var div_circle2 = gr.drawCircle(pen, pt1, 2);
			agregarAtributos(div_circle2, datos);
			
			//Se dibuja el texto
			if(instOrigen.id_instancia==parseInt(instanciaSeleccionada)){
				var div_texto = gr.drawText(accion.nombre, ptTexto, null, new jsColor("#384656"), 50, null);
				agregarAtributos(div_texto, datos);
			}else{
				var div_texto = gr.drawText(accion.nombre, ptTexto, null, null, 50, null);
				agregarAtributos(div_texto, datos);
			}		
		}	
	}
}
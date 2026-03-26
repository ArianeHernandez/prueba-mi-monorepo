INSTANCIA_SELECCIONADA = null;


function eliminarInstanciaSeleccionada(){
	if(!osm_esVacio(INSTANCIA_SELECCIONADA)){
		var res=confirm("¿Est\u00E1 seguro que desea eliminar la instancia seleccionada?");
		
		if(res){
			eliminarInstancia(parseInt(INSTANCIA_SELECCIONADA));			
		}
		
	}else{
		osm_alert("Por favor seleccione la instancia que desea eliminar");
	}
}


function ajustar_contenido_proceso(){
	var pos = osm_getWindowSize();
	
	$(".contenido_principal_titulo_icono").hide();
	$("#contenedor_general").css("height", (pos[1] - 140)+"px");
	
	//Se ajusta el contenido del area de informacion del proceso
	$(".div_proceso_nombre").css("width", parseInt((pos[0] - 40)/4)+"px");
	$(".div_proceso_formato").css("width", parseInt((pos[0] - 40)/4)+"px");
	$(".div_proceso_negocio").css("width", parseInt((pos[0] - 40)/4)+"px");
	$(".div_proceso_botones").css("width", parseInt((pos[0] - 40)/5)+"px");
	
	
	//$("#informacion_proceso").css("width", (pos[0] - 60)+"px")
	
	//Se ajusta el contenido del area de dibujo
	$("#AREA_PROCESO").css("height", (pos[1] - 250)+"px");
	$("#AREA_PROCESO").css("width", (pos[0] - 330)+"px");
	
	
	//Se ajusta el tamano para la informacion de la instancia
	$("#lista_inst_proceadmin").css("height", (pos[1] - 250)+"px");
	$(".area_contenido_inst").css("height", (pos[1] - 398)+"px");
	$(".area_contenido_inst").css("overflow-y", "auto");
	
	
	
}

osm_listen("resize", window, ajustar_contenido_proceso);
osm_listen("load", window, ajustar_contenido_proceso);



$(document).ready(initApp);

function initApp(){
	pintarLineasInstanciasDestino();
	
	ocultarTodasLasInstancias();
	
}

function ocultarTodasLasInstancias(){
	$("div[id*='blq_instancia']").hide();
	$("div[id*='div_instancia']").removeClass("instancia_seleccionada");
	$("#mensaje_seleccionar_instancia").show();
	
}


function pintarInstanciaGrafica(id_padre, parametros){
	
	osm_construirHTML(id_padre, 'PLANTILLA_INSTANCIA_GRAFICA', parametros);
	$("#div_instancia_"+parametros[0]).draggable({
  											 start: function(){ $("#AREA_PROCESO_LINEAS").empty(); },
											 drag: function(){ actualizarPosInstancia(this); ; pintarLineasInstanciasDestino();},
											 stop: function() { mouseUpObjetoInstancia(this); actualizarPosInstancia(this); pintarLineasInstanciasDestino();}
											  });
	$("#div_instancia_"+parametros[0]).css({left:parametros[7]+"px", top:parametros[8]+"px"});
	$("#div_instancia_"+parametros[0]).mousedown(mouseDownObjetoInstancia);
	$("#div_instancia_"+parametros[0]).get(0).id_instancia = parametros[0];
}

var inst_para_actualizar=0;

function mouseUpObjetoInstancia(obj){
	
	var posicion_x = parseInt($(obj).css("left"));
	var posicion_y = parseInt($(obj).css("top"));
	jsonrpc._("instanciaJsonServicio.actualizarPosicionesXY")(function(){}, obj.id_instancia, posicion_x, posicion_y);
}

function actualizarPosInstancia(div){
	
	var posicion_x = parseInt($(div).css("left"));
	var posicion_y = parseInt($(div).css("top"));
	
	for (var i=0; i<ARRAY_INSTANCIAS.list.length; i++) {
		var obj = ARRAY_INSTANCIAS.list[i];
		if(obj.id_instancia== div.id_instancia){
			obj.posicion_x=posicion_x;
			obj.posicion_y=posicion_y;
		}
	};
	
	inst_para_actualizar=div.id_instancia;
	
	jQuery.each(ARRAY_INST_DESTINO, function(){
		
		for (var i=0; i<this.list.length; i++) {
			
			var obj=this.list[i];
			
			if(obj.id_instancia== inst_para_actualizar){
				obj.posicion_x=posicion_x;
				obj.posicion_y=posicion_y;
			}
		};
		
	});
	
}




function mouseDownObjetoInstancia(){
	ocultarTodasLasInstancias();
	$("#div_instancia_"+this.id_instancia).addClass("instancia_seleccionada");
	$(".area_contenido_inst").hide();
	var instancia = jsonrpc._("instanciaJsonServicio.obtenerInstancia")(this.id_instancia);
	osm_setValor("id_horario", instancia.id_horario);
	$("#div_info_instancia_"+this.id_instancia).show();
	$("#blq_instancia_"+this.id_instancia).show();
	
	$("#mensaje_seleccionar_instancia").hide();
	
	INSTANCIA_SELECCIONADA = this.id_instancia;
	$("#area_horario_instancia_" + INSTANCIA_SELECCIONADA).append($("#blk_horario"));
	actualizarHorario();

	HORARIO_SELECCION = function(){
		actualizarHorarioInstancia(INSTANCIA_SELECCIONADA, VALOR_CRITERIO_HORARIO_CP);
	}
	
	pintarLineasInstanciasDestino();
	
}



function pintarLineasInstanciasDestino(){
	$("#AREA_PROCESO_LINEAS").empty();
	
	
	var instanciasOrigen = ARRAY_INSTANCIAS;
	var instOrigenList = instanciasOrigen.list;
	var posInstSelec = null;

	if (instOrigenList.length > 0) {

		for ( var i = 0; i < instOrigenList.length; i++) {

			var instOrigen = instOrigenList[i];
			
			pintarLineasPorInstancia(instOrigen);
			
			
			if( INSTANCIA_SELECCIONADA!=null && instOrigen.id_instancia==parseInt(INSTANCIA_SELECCIONADA)){
				posInstSelec=i;
			}
			
		}
		
		//Luego de pintar todas las instancias se pinta la instancia seleccionada
		if (posInstSelec != null) {
			pintarLineasPorInstancia(instOrigenList[posInstSelec]);
		}
		
		
	}//fin if origen


}

function pintarLineasPorInstancia(instOrigen){
	
	//Se obtienen las acciones por instancia
	var gr = new jsGraphics(document.getElementById("AREA_PROCESO_LINEAS"));
	
	
	var acciones = ARRAY_ACCIONES[instOrigen.id_instancia];
	var accionesList = acciones.list;

	if (accionesList.length > 0) {

		for ( var j = 0; j < accionesList.length; j++) {

			var accion = accionesList[j];

			//Se buscan las instancias destino por accion
			var instanciasDestino = ARRAY_INST_DESTINO[accion.id_accion];
			var instDestList = instanciasDestino.list;
			
			
			if (instDestList.length > 0) {
				//Create jsColor object
				var col= null;
				
				if(instOrigen.id_instancia==parseInt(INSTANCIA_SELECCIONADA)){
					
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
				
					instDestino = instDestList[k];
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
					
					
					
					gr.drawPolyBezier(pen, points);
					
					// Se pinta la flecha
					var pointsPolygon = [ptT1, ptT2, ptT3];
					gr.fillPolygon(col, pointsPolygon);
					
					gr.drawCircle(pen, pt1, 4);
					gr.drawCircle(pen, pt1, 2);
					
					
					//Se dibuja el texto
					if(instOrigen.id_instancia==parseInt(INSTANCIA_SELECCIONADA)){
					
						gr.drawText(accion.nombre, ptTexto, null, new jsColor("#384656"), 50, null);
						
					}else{
						
						gr.drawText(accion.nombre, ptTexto, null, null, 50, null);
						
					}		
					
					
				}	
				
			}//fin if destino
		}
	}//fin if acciones
}


function construirSelectsActualiza(id_proceso_admin) {

	construirSelectNegocioAct(id_proceso_admin);
	actualizarSelectFormatoAct(id_proceso_admin);

	cargarValores(id_proceso_admin);

}

function construirSelectNegocioAct(id_proceso_admin) {

	var negocios = jsonrpc._("procesoAdminJsonServicio.obtenerListadoNegociosActivos")();
	var negList = negocios.list;

	var selectNegocio = document.createElement('SELECT')
	selectNegocio.className = "input_proceso form-control";
	selectNegocio.id = "select_negocio_proceso_admin";

	selectNegocio.onchange = new Function('actualizaNegocio(' + id_proceso_admin + ')');

	osm_setValor('divsel_negocio_proceso_admin', '');
	osm_getObjeto('divsel_negocio_proceso_admin').appendChild(selectNegocio);

	osm_setValor('select_negocio_proceso_admin', '');

	for ( var i = 0; i < negList.length; i++) {

		var option = document.createElement("OPTION");

		var negocio = negList[i];

		option.value = negocio.id_negocio;
		option.id = 'option_negocio_' + negocio.id_negocio;
		option.innerHTML = negocio.nombre;

		selectNegocio.appendChild(option);

	}

}

function actualizarSelectFormatoAct(id_proceso_admin) {

	var selectNegocio = osm_getObjeto('select_negocio_proceso_admin');
	var indiceSeleccionado = selectNegocio.selectedIndex;
	var opcionSeleccionada = selectNegocio.options[indiceSeleccionado];

	var id_negocio = opcionSeleccionada.value;

	construirSelectFormatoAct(id_negocio, id_proceso_admin);
	
}

function construirSelectFormatoAct(id_negocio, id_proceso_admin) {
	
	
	var formatos = jsonrpc._("procesoAdminJsonServicio.obtenerFormatosEntradaPorNegocio")(id_negocio);
	var proceso_admin = jsonrpc._("procesoAdminJsonServicio.obtenerProcesoAdmin")(id_proceso_admin);
	var formList = formatos.list;

	var selectFormato = document.createElement('SELECT')
	selectFormato.className = "input_proceso form-control";
	selectFormato.id = "select_formato_proceso_admin";
	
	selectFormato.onchange = new Function('actualizaFormato(' + id_proceso_admin + ');');

	osm_setValor('divsel_formato_proceso_admin', '');
	osm_getObjeto('divsel_formato_proceso_admin').appendChild(selectFormato);

	osm_setValor('select_formato_proceso_admin', '');

	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = null;
	option_ninguno.id = 'option_formato_ninguno';
	option_ninguno.innerHTML = '-- ninguno --';
	selectFormato.appendChild(option_ninguno);

	for ( var i = 0; i < formList.length; i++) {

		var option = document.createElement("OPTION");

		var formato = formList[i];

		option.value = formato.id_formato;
		option.id = 'option_formato_' + formato.id_formato;
		option.innerHTML = formato.nombre;
		if(formato.id_formato == proceso_admin.id_formato_entrada){
			option.selected = true;
		}
		selectFormato.appendChild(option);

	}

}

// metodos encargados de la actualizacion en la base de datos

function actualizaNegocio(id_proceso_admin) {

	actualizarSelectFormatoAct(id_proceso_admin);
	var exitoActualizar = false;
	var resulVerificar = false;

	var selectFormato = osm_getObjeto('select_formato_proceso_admin');

	var i = 0;
	do {
		selectFormato.options[i].selected = true;
		resulVerificar = verificaValoresAct(id_proceso_admin);

		i++;
	} while (resulVerificar && i < selectFormato.length)

	if (!resulVerificar) {
		exitoActualizar = actualizarProcesoAdmin(id_proceso_admin);
	} else {
		mostrarVentanaActualiza('Ya existe un proceso asociado a este negocio y formato');
	}

	if (!exitoActualizar) {
		construirSelectsActualiza(id_proceso_admin);
	}

}

function actualizaFormato(id_proceso_admin) {
	var exitoActualizar = false;
	var selectFormato = osm_getObjeto('select_formato_proceso_admin');
	if (!verificaValoresAct(id_proceso_admin)) {
		exitoActualizar = actualizarProcesoAdmin(id_proceso_admin);
	} else {
		mostrarVentanaActualiza('Ya existe un proceso asociado a este negocio y formato');
	}

	if (!exitoActualizar) {
		construirSelectsActualiza(id_proceso_admin);
	}

}

function actualizaNombre(id_proceso_admin) {

	var nombre = osm_getObjeto('cajatex_nombre_proceso_admin').value;
	if (osm_esVacio(nombre)) {
		mostrarVentanaActualiza('El nombre no puede estar vacio');
		return;
	}
	actualizarProcesoAdmin(id_proceso_admin);

}

function actualizarProcesoAdmin(id_proceso_admin) {

	var selectNegocio = osm_getObjeto('select_negocio_proceso_admin');
	var indiceSeleccionadoNeg = selectNegocio.selectedIndex;
	var opcionSeleccionadaNeg = selectNegocio.options[indiceSeleccionadoNeg];
	var id_negocio = opcionSeleccionadaNeg.value;

	var selectFormato = osm_getObjeto('select_formato_proceso_admin');
	var indiceSeleccionadoForm = selectFormato.selectedIndex;
	var opcionSeleccionadaForm = selectFormato.options[indiceSeleccionadoForm];
	var id_formato_entrada = opcionSeleccionadaForm.value;

	var nombre = osm_getObjeto('cajatex_nombre_proceso_admin').value;
	id_formato_entrada = (id_formato_entrada == "null")?null:parseInt(id_formato_entrada);
	var exitoActualizar = jsonrpc._("procesoAdminJsonServicio.actualizarProcesoAdmin")(id_proceso_admin, id_negocio, id_formato_entrada, nombre);

	if (!exitoActualizar) {
		mostrarVentanaActualiza('No se pudo actualizar el proceso.');
		osm_go('proceso_administracion/20.1.do');
	}

	return exitoActualizar;
}

function cargarValores(id_proceso_admin) {

	var procesoAdmin = jsonrpc._("procesoAdminJsonServicio.obtenerProcesoAdmin")(id_proceso_admin);

	osm_setValor('cajatex_nombre_proceso_admin', procesoAdmin.nombre);

	var selectNegocio = osm_getObjeto('select_negocio_proceso_admin');
	for ( var i = 0; i < selectNegocio.length; i++) {
		if (selectNegocio.options[i].value == procesoAdmin.id_negocio) {
			selectNegocio.options[i].selected = true;
		}
	}

	var selectFormato = osm_getObjeto('select_formato_proceso_admin');
	for ( var i = 0; i < selectFormato.length; i++) {
		if (selectFormato.options[i].value == procesoAdmin.id_formato_entrada) {
			selectFormato.options[i].selected = true;
		}
	}

}

function verificaValoresAct(id_proceso_admin) {

	var selectNegocio = osm_getObjeto('select_negocio_proceso_admin');
	var indiceSeleccionadoNeg = selectNegocio.selectedIndex;
	var opcionSeleccionadaNeg = selectNegocio.options[indiceSeleccionadoNeg];
	var id_negocio = parseInt(opcionSeleccionadaNeg.value);

	var selectFormato = osm_getObjeto('select_formato_proceso_admin');
	var indiceSeleccionadoForm = selectFormato.selectedIndex;
	var opcionSeleccionadaForm = selectFormato.options[indiceSeleccionadoForm];
	var id_formato_entrada = opcionSeleccionadaForm.value;

	id_formato_entrada = (id_formato_entrada == "null")?null:parseInt(id_formato_entrada);
	
	var existe = jsonrpc._("procesoAdminJsonServicio.verificarExistencia")(id_negocio, id_formato_entrada, id_proceso_admin);

	return existe;
}

// -------------------------------------------------------------------

var CANTIDAD_INSTANCIAS = 0;
var ID_PROCESO = 0;
var ARRAY_INSTANCIAS=null;

function listarInstanciasProceAdmin(id_proceso_admin, id_negocio) {
	
	ID_PROCESO=id_proceso_admin;
	
	var instancias = jsonrpc._("instanciaJsonServicio.obtenerInstanciasPorProceso")(id_proceso_admin);
	ARRAY_INSTANCIAS= instancias;
	var instList = instancias.list;

	if (instList.length > 0) {

		osm_setValor('lista_inst_proceadmin', '');

		osm_construirHTML('lista_inst_proceadmin', 'PLANTILLA_LISTA_INST_PROCEADMIN');
		osm_setValor('contenido_lista_inst_proceadmin', '');

		for ( var i = 0; i < instList.length; i++) {

			var inst = instList[i];

			var parametros = [ inst.id_instancia,
			                   inst.nombre,
			                   inst.inicial,
			                   inst.aprobar,
			                   inst.rechazar,
			                   inst.tiempo,
			                   inst.id_formato_salida,
			                   inst.posicion_x,
			                   inst.posicion_y,
			                   inst.id_horario,
			                   inst.adicionar_documentos,
			                   inst.gestionar_documentos,
			                   inst.aprobar_automaticamente,
			                   inst.rechazar_automaticamente,
			                   inst.solicitar_otp,
			                   inst.ocultar_sin_filtro
			                 ];

			var formatosSalida = jsonrpc._("instanciaJsonServicio.obtenerFormatosSalidaPorNegocio")(id_negocio);
			formsSalidaList = formatosSalida.list;
			pintarInstancia('contenido_lista_inst_proceadmin', parametros, formsSalidaList);
			
			
			//Se pintan las instancias para la edicion de proceso
			pintarInstanciaGrafica('AREA_PROCESO', parametros );
			

		}
	}

	CANTIDAD_INSTANCIAS = instList.length;
}


function pintarInstancia(id_padre, parametros, formsSalidaList) {
	
	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_INST_PROCEADMIN', parametros);

	var checkInicial = osm_getObjeto('inicial_inst_' + parametros[0]);
	if (parametros[2] == 'S') {
		checkInicial.checked = true;
	}

	var checkAprobar = osm_getObjeto('perm_aprobar_inst_' + parametros[0]);
	if (parametros[3] == 'S') {
		checkAprobar.checked = true;
	}

	var checkRechazar = osm_getObjeto('perm_rechazar_inst_' + parametros[0]);
	if (parametros[4] == 'S') {
		checkRechazar.checked = true;
	}
	
	var checkAdicionarDoc = osm_getObjeto('perm_adicionar_docs_' + parametros[0]);
	if (parametros[10] == 'S') {
		checkAdicionarDoc.checked = true;
	}
	var checkGestionarDoc = osm_getObjeto('perm_gestionar_docs_' + parametros[0]);
	if (parametros[11] == 'S') {
		checkGestionarDoc.checked = true;
	}
	
	var checkAprobarAuto = osm_getObjeto('perm_aprobar_auto_inst_' + parametros[0]);
	if (parametros[12] == 'S') {
		checkAprobarAuto.checked = true;
		$("#perm_rechazar_inst_" + parametros[0]).attr("disabled", true);
	}
	
	var checkRechazarAuto = osm_getObjeto('perm_rechazar_auto_inst_' + parametros[0]);
	if (parametros[13] == 'S') {
		checkRechazarAuto.checked = true;
		$("#perm_aprobar_inst_" + parametros[0]).attr("disabled", true);
	}
	
	var checkOcultarSinFiltro = osm_getObjeto('perm_ocultar_cargas_' + parametros[0]);
	if (parametros[15] == 'S') {
		checkOcultarSinFiltro.checked = true;
	}

	var selectFormSalida = osm_getObjeto('formato_salida_inst_' + parametros[0]);

	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = null;
	option_ninguno.id = 'option_form_sal_ninguno';
	option_ninguno.innerHTML = '-- ninguno --';
	selectFormSalida.appendChild(option_ninguno);

	for ( var i = 0; i < formsSalidaList.length; i++) {

		var option = document.createElement("OPTION");

		var formato = formsSalidaList[i];

		option.value = formato.id_formato;
		option.id = 'option_form_sal_' + formato.id_formato;
		option.innerHTML = formato.nombre;

		selectFormSalida.appendChild(option);

		if (formato.id_formato == parametros[6]) {
			option.selected = true;
		}

	}

	listarAccionesInst(parametros[0]);
	listarAccionesRedireccionInst(parametros[0]);
	listarRolesInst(parametros[0]);
	
	aprobarRechazarToggle(parametros[0]);
}

function crearInstancia(id_proceso_admin, id_negocio) {
	ocultarTodasLasInstancias();
	
	$("#mensaje_seleccionar_instancia").hide();

	var id_instancia = jsonrpc._("instanciaJsonServicio.obtenerSiguienteId")();

	var exitoCrear = jsonrpc._("instanciaJsonServicio.insertarInstancia")(id_instancia, '', 'N', 'N', 10, 0, 0, id_proceso_admin, 'N', null, null, 'N', 'N', 'N');

	if (exitoCrear) {

		if (CANTIDAD_INSTANCIAS == 0) {

			osm_setValor('lista_inst_proceadmin', '');
			osm_construirHTML('lista_inst_proceadmin', 'PLANTILLA_LISTA_INST_PROCEADMIN');
			osm_setValor('contenido_lista_inst_proceadmin', '');

		}

		var inst = jsonrpc._("instanciaJsonServicio.obtenerInstancia")(id_instancia);

		var parametros = [ inst.id_instancia,
		                   inst.nombre,
		                   inst.inicial,
		                   inst.aprobar,
		                   inst.rechazar,
		                   inst.tiempo,
		                   inst.id_formato_salida,
		                   null,
		                   null,
		                   null,
		                   inst.adicionar_documentos,
		                   inst.gestionar_documentos,
		                   inst.aprobar_automaticamente,
		                   inst.rechazar_automaticamente,
		                   inst.solicitar_otp
		                 ];

		var formatosSalida = jsonrpc._("instanciaJsonServicio.obtenerFormatosSalidaPorNegocio")(id_negocio);
		formsSalidaList = formatosSalida.list;
		
		var instancias = jsonrpc._("instanciaJsonServicio.obtenerInstanciasPorProceso")(id_proceso_admin);
		ARRAY_INSTANCIAS= instancias;

		pintarInstancia('contenido_lista_inst_proceadmin', parametros, formsSalidaList);
		
		$("#acciones_instancia_"+id_instancia).show();
		$("#roles_instancia_"+id_instancia).show();
		
		
		
		
		
		//Se pintan las instancias para la edicion de proceso
		pintarInstanciaGrafica('AREA_PROCESO', parametros );

		CANTIDAD_INSTANCIAS++;

		actualizarTodosSelectInstDest();

	} else {
		alert('Ha ocurrido un error al crear la instancia.');
	}

}

function actualizarPermisosInstancia(id_instancia) {

	var aprobar = 'N';
	var rechazar = 'N';
	var inicial = 'N';
	var aprobar_automaticamente = 'N';
	var rechazar_automaticamente = 'N';
	var solicitar_otp = 'N';
	var ocultar_sin_filtro = 'N';
	

	if (osm_getObjeto('perm_aprobar_inst_' + id_instancia).checked) {
		aprobar = 'S';
	}

	if (osm_getObjeto('perm_aprobar_auto_inst_' + id_instancia).checked) {
		aprobar_automaticamente = 'S';
		$("#perm_rechazar_inst_" + id_instancia).prop("checked", false);
		$("#perm_rechazar_auto_inst_" + id_instancia).prop("checked", false);
		$("#perm_rechazar_inst_" + id_instancia).attr("disabled", true);
	}else {
		$("#perm_rechazar_inst_" + id_instancia).attr("disabled", false);
	}
	
	if (osm_getObjeto('perm_rechazar_auto_inst_' + id_instancia).checked) {
		rechazar_automaticamente = 'S';
		$("#perm_aprobar_inst_" + id_instancia).prop("checked", false);
		$("#perm_aprobar_auto_inst_" + id_instancia).prop("checked", false);
		$("#perm_aprobar_inst_" + id_instancia).attr("disabled", true);
	}else {
		$("#perm_aprobar_inst_" + id_instancia).attr("disabled", false);
	}
	
	if (osm_getObjeto('perm_rechazar_inst_' + id_instancia).checked) {
		rechazar = 'S';
	}

	if (osm_getObjeto('inicial_inst_' + id_instancia).checked) {
		inicial = 'S';
	}

	var adicionar_docs = 'N';
	var gestionar_docs = 'N';
	if (osm_getObjeto('perm_adicionar_docs_' + id_instancia).checked) {
		adicionar_docs = 'S';
	}
	if (osm_getObjeto('perm_gestionar_docs_' + id_instancia).checked) {
		gestionar_docs = 'S';
	}
	
	if (osm_getObjeto('perm_ocultar_cargas_' + id_instancia).checked) {
		ocultar_sin_filtro = 'S';
	}
	
	var exitoActualizar = jsonrpc._("instanciaJsonServicio.actualizarPermisosInstancia")(id_instancia, aprobar, rechazar, inicial, adicionar_docs, gestionar_docs, aprobar_automaticamente, rechazar_automaticamente, solicitar_otp, ocultar_sin_filtro);

	if (!exitoActualizar) {
		mostrarVentanaActualiza('No se pudo actualizar la instancia.');
	
	}else{
		// $("#aprobar_instancia_"+id_instancia).text("Aprobar: "+aprobar);
		// $("#rechazar_instancia_"+id_instancia).text("Rechazar: "+rechazar);
		// $("#instancia_inicial_"+id_instancia).text("Inicial: "+inicial);
		
		$("#instancia_inicial_"+id_instancia).removeClass('instancia_inicial_N').removeClass('instancia_inicial_S');
		$("#instancia_inicial_"+id_instancia).addClass('instancia_inicial_'  + inicial);
		
		$("#instancia_final_"+id_instancia).removeClass('instancia_final_NNNN').removeClass('instancia_final_SSNN').removeClass('instancia_final_SNNN').removeClass('instancia_final_NSNN').removeClass('instancia_final_SNSN').removeClass('instancia_final_NSNS');
		$("#instancia_final_"+id_instancia).addClass('instancia_final_' + aprobar + rechazar + aprobar_automaticamente + rechazar_automaticamente);
	}
}

function actualizarTiempo(id_instancia) {

	var tiempo = osm_getValor('tiempo_inst_' + id_instancia);

	var exitoActualizar = jsonrpc._("instanciaJsonServicio.actualizarTiempo")(id_instancia, tiempo);

	if (!exitoActualizar) {
		mostrarVentanaActualiza('No se pudo actualizar la instancia.');
	
	}else{
		//Se actualiza el tiempo de la instancia grafica
		
		$("#tiempo_instancia_"+id_instancia).text(tiempo+" min");
		
	}
}

function actualizarNombre(id_instancia) {

	var nombre = osm_getValor('nombre_inst_' + id_instancia);

	var exitoActualizar = jsonrpc._("instanciaJsonServicio.actualizarNombre")(id_instancia, nombre);

	if (exitoActualizar) {
		osm_setValor('subtitulo_inst_' + id_instancia, nombre);
		
		$("#header_instancia_"+id_instancia).text(nombre);
		
		$("#titulo_info_instancia_"+id_instancia).text(nombre);

		actualizarTodosSelectInstDest();

	} else {
		mostrarVentanaActualiza('No se pudo actualizar la instancia.');
	}

}

function actualizarFormatoSalida(id_instancia) {

	var selectFormSalida = osm_getObjeto('formato_salida_inst_' + id_instancia);
	var indiceSeleccionado = selectFormSalida.selectedIndex;
	var opcionSeleccionada = selectFormSalida.options[indiceSeleccionado];
	var id_formato_salida = opcionSeleccionada.value;

	var exitoActualizar = jsonrpc._("instanciaJsonServicio.actualizarFormatoSalida")(id_instancia, id_formato_salida);

	if (!exitoActualizar) {
		mostrarVentanaActualiza('No se pudo actualizar la instancia.');
	}
}

function actualizarHorarioInstancia(id_instancia, franjas) {

	var exitoActualizar = jsonrpc._("instanciaJsonServicio.actualizarHorarioInstancia")(id_instancia, franjas);

	if (!exitoActualizar) {
		mostrarVentanaActualiza('No se pudo actualizar la instancia.');
	}
}

function eliminarInstancia(id_instancia) {

	var exitoEliminar = jsonrpc._("instanciaJsonServicio.borrarInstancia")(id_instancia);

	if (!exitoEliminar) {
		alert('Ha ocurrido un error al eliminar la instancia.');
	}
	
	osm_enviarFormulario("form_recargar");
	
}

function aprobarRechazarToggle(id_instancia){
	var aprobar = osm_getObjeto('perm_aprobar_inst_' + id_instancia).checked;
	var rechazar = osm_getObjeto('perm_rechazar_inst_' + id_instancia).checked;
	if (aprobar == true && rechazar == true){
		$("#contenedor_aprobar_aut_" + id_instancia).hide();
		$("#perm_aprobar_auto_inst_" + id_instancia).prop("checked", false);
		$("#contenedor_rechazar_aut_" + id_instancia).hide();
		$("#perm_rechazar_auto_inst_" + id_instancia).prop("checked", false);
	}else if (aprobar == true) {
		$("#contenedor_aprobar_aut_" + id_instancia).show();
		$("#contenedor_rechazar_aut_" + id_instancia).hide();
		$("#perm_rechazar_auto_inst_" + id_instancia).prop("checked", false);
	}else if (rechazar == true) {
		$("#contenedor_rechazar_aut_" + id_instancia).show();
		$("#contenedor_aprobar_aut_" + id_instancia).hide();
		$("#perm_aprobar_auto_inst_" + id_instancia).prop("checked", false);
	}
}
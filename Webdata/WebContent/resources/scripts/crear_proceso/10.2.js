var TOTAL_FORMATOS = 0;

//---------
osm_listen('load', window, function(){
	
	osm_listen('change', 'Proceso.id_proceso', guardarProceso);
	osm_listen('change', 'Proceso.nombre', guardarProceso);
	osm_listen('change', 'Proceso.estado', guardarProceso);
	osm_listen('change', 'Proceso.id_formato_salida', guardarProceso);
	osm_listen('change', 'Proceso.id_grupoformato', listenerSelectGrupoFormato);
	
	guardarProceso();
	
});

$(document).ready(cargarParametrizacionCargaAutomatica);

//---------
var bandera =0;
var pesoLiberadorAFavor=0;
var pesoLiberadorEnContra=0;

function eliminarFormato(id_formato){
	var rta = jsonrpc._("procesoJsonServicio.eliminarFormato")(id_formato);
	if(rta){
		$("#fila_"+id_formato).remove();
		activarCargaAutomatica();
	}else{
		alert("Error al eliminar el formato");
	}
}

function agregarFormato(id_select){
	console.log("id_select");
	console.log(id_select);
	var id_formato = osm_getValor(id_select);
	
	if(osm_esVacio(id_formato)){
		return;
	}
	
	var fila = $("#fila_"+id_formato);
	if(fila.length){
		fila.css("background-color","#CBCBCB");
		osm_getObjeto(id_select).selectedIndex = 0;
		fila.animate({"background-color":"#fff"}, 800);
		return;
	}
	
	var rta = jsonrpc._("procesoJsonServicio.agregarFormato")(id_formato);
	if(rta){
		var nombre = osm_getValorText(id_select);
		osm_construirHTML("div_formatosE", "TEMPLATE_FILA_FORMATO", [id_formato, nombre]);
		var fun = new Function("eliminarFormato('"+ id_formato + "')");
		$("#btnEliminar_"+id_formato).click(fun);
	}else{
		alert("Error al agregar el formato");
	}
	
	activarCargaAutomatica();
	
	osm_getObjeto(id_select).selectedIndex = 0;
}

function validarNuevoPeso(pesoActual){

	guardarProceso();
	
	var id_proceso = osm_getValorEntero("Proceso.id_proceso");

	var diferenciaPesos = jsonrpc._("procesoJsonServicio.diferenciaPesoLiberadoresYProceso")( id_proceso );
	if(diferenciaPesos<0){ //&& bandera==1){diff1 != 0 && diff2 != 0){//
		alert("Al realizar el cambio de peso, el proceso no se podra liberar con los liberadores asignados");
	}	
	
}


function guardarProceso(){
	
	try {
		
		var nombre = osm_getValor("Proceso.nombre");
		var estado = osm_esSeleccionado("Proceso.estado")?"A":"I";
		var id_formato_entrada = osm_getValor("Proceso.id_formato_entrada");
		var id_formato_salida = osm_getValor("Proceso.id_formato_salida");
		var id_grupoformato = osm_getValor("Proceso.id_grupoformato");
		var peso = osm_getValor("Proceso.peso");
		
		var ok = jsonrpc._("procesoJsonServicio.actualizarProceso")(nombre, estado, id_formato_salida, id_grupoformato, peso);
		
		if(!ok){
			alert("No se puede realizar la operaci\u00f3n.");
			osm_go('crear_proceso/10.2.do');
		}
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n..");
		osm_go('crear_proceso/10.2.do');
	}
	
}

// ---------------------

function asociarPreparador(id_preparador, texto){
	var tt = texto.split('-');
	var nombre = osm_strlimpiar(tt[0]);
	var login = osm_strlimpiar(tt[1]).toLowerCase();
	
	try {
		
		var ok = jsonrpc._("procesoJsonServicio.asociarPreparador")(id_preparador);

		if(ok){
		
			var PL = osm_getValor('PLANTILLA_PREPARADOR');
			
			PL = osm_remplazar(PL, '[ID]', id_preparador);
			PL = osm_remplazar(PL, '[NOMBRE]', nombre);
			PL = osm_remplazar(PL, '[LOGIN]', login);
			
			$('#lista_preparadores').append(PL);
			
		}else{
			alert("No se puede asociar el preparador.");
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n.");
		osm_go('crear_proceso/10.2.do');
	}
}

// -----------------------

function desasociarPreparador(id_preparador){
	
	try {
		
		var ok = jsonrpc._("procesoJsonServicio.desasociarPreparador")(id_preparador);

		if(ok){
			$("#filap_" + id_preparador).slideUp(400, function(){ $(this).remove(); });
		}else{
			alert("No se puede asociar el preparador.");
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
}

//---------------------

function asociarLiberador(id_liberador, texto){
	bandera = 0;
	var tt = texto.split('-');
	var nombre = osm_strlimpiar(tt[0]);
	var login = osm_strlimpiar(tt[1]).toLowerCase();
	
	try {
		var procso = osm_getObjeto("proceso");
		if(procso != null){
		var pesoLiberador = jsonrpc._("liberadorServicio.obtenerLiberadorTipoProceso")(id_liberador,procso.getId_tipo_proceso()); 
			pesoLiberadorAFavor += pesoLiberador;
		}
		
		var ok = jsonrpc._("procesoJsonServicio.asociarLiberador")(id_liberador);

		if(ok){
		
			var PL = osm_getValor('PLANTILLA_LIBERADOR');
			
			PL = osm_remplazar(PL, '[ID]', id_liberador);
			PL = osm_remplazar(PL, '[NOMBRE]', nombre);
			PL = osm_remplazar(PL, '[LOGIN]', login);
			
			$('#lista_liberadores').append(PL);
			
		}else{
			alert("No se puede asociar el liberador.");
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n.");
		osm_go('crear_proceso/10.2.do');
	}
	guardarProceso();
	cargarParametrizacionCargaAutomatica();

}

// -----------------------

function comprobarDesasociarLiberador(id_liberador,pesoLiberador,diferenciaPesos,pesoProcesoAnterior){
	//para calcular la nueva diferencia en caso de que se haya cambiado el peso del proceso.
	var pesoActual=document.getElementById("Proceso.peso");
	var confirmacion=true;
	pesoLiberadorEnContra += pesoLiberador;
	if(pesoActual){ 
		diferenciaPesos=diferenciaPesos+pesoProcesoAnterior-pesoActual;
		//difencia sin el liberador
		diferenciaSinLiberador=diferenciaPesos-pesoLiberador;
		if(diferenciaSinLiberador<0){
			confirmacion=confirm("Los pesos de los liberadores restantes no son suficientes para liberar el proceso, esta seguro?");
		}		
	}
	if(confirmacion){
		desasociarLiberador(id_liberador);
	}
	
}

function comprobarDesasociarLiberadorPorProceso(id_liberador,pesoLiberador,diferenciaPesos,pesoProcesoAnterior,id_proceso){
	//para calcular la nueva diferencia en caso de que se haya cambiado el peso del proceso.
	var pesoActual=document.getElementById("Proceso.peso");
	var confirmacion=true;
	
	if(pesoActual){ 
		diferenciaPesos=diferenciaPesos+pesoProcesoAnterior-pesoActual;
		//difencia sin el liberador
		diferenciaSinLiberador=diferenciaPesos-pesoLiberador;
		if(diferenciaSinLiberador<0){
			confirmacion=confirm("Los pesos de los liberadores restantes no son suficientes para liberar el proceso, esta seguro?");
		}		
	}
	if(confirmacion){
		desasociarLiberadorPorProceso(id_liberador,id_proceso);
	}
	
}


function desasociarLiberador(id_liberador){
	
try {
		bandera = 1;
		var ok = jsonrpc._("procesoJsonServicio.desasociarLiberador")(id_liberador);

		if(ok){
			$("#filal_" + id_liberador).slideUp(400, function(){ $(this).remove(); });
		}else{
			alert("No se puede asociar el liberador.");
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
}

function desasociarLiberadorPorProceso(id_liberador,id_proceso){

	try {
		
		var ok = jsonrpc._("procesoJsonServicio.desasociarLiberadorPorProceso")(id_liberador,id_proceso);

		if(ok){
			$("#filal_" + id_liberador).slideUp(400, function(){ $(this).remove(); });
		}else{
			alert("No se puede asociar el liberador.");
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
}

//---------------------

function asociarRevisor(id_revision, id_revisor, texto){

	var revisorMultiplesInstancias = osm_esVerdad(osm_getValor("revisorMultiplesInstancias"));
	
	var tt = texto.split('-');
	var nombre = osm_strlimpiar(tt[0]);
	var login = osm_strlimpiar(tt[1]).toLowerCase();
	
	try {
		
		if (!revisorMultiplesInstancias){
			var retornar = false;
			$(".box_revision div.cuadro_info div.cuadro_fila").each(function (){
				var id = $(this).attr("id");
				var array = id.split("_");
				var id_revisor_revision = parseInt(array[2], 10);
				if (id_revisor == id_revisor_revision && !retornar){
					alert("El revisor hace parte de otra revisi\u00f3n");
					var fila = $("#" + id);
					fila.css("background-color","#CBCBCB");
					fila.animate({"background-color":"#F6F6F6"}, 800);
					retornar = true;
				}
			});
			if (retornar){
				return;
			}
		}
	
		
		var ok = jsonrpc._("procesoJsonServicio.asociarRevisor")(id_revision, id_revisor);

		if(ok){
		
			var PL = osm_getValor('PLANTILLA_REVISOR');
			
			PL = osm_remplazar(PL, '[ID]', id_revisor);
			PL = osm_remplazar(PL, '[ID_REV]', id_revision);
			PL = osm_remplazar(PL, '[NOMBRE]', nombre);
			PL = osm_remplazar(PL, '[LOGIN]', login);
			
			$('#lista_revisores_' + id_revision).append(PL);
			
		}else{
			alert("No se puede asociar el revisor.");
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n.");
		osm_go('crear_proceso/10.2.do');
	}
}

// -----------------------

function desasociarRevisor(id_revision, id_revisor){
	
	try {
		var ok = jsonrpc._("procesoJsonServicio.desasociarRevisor")(id_revision, id_revisor);

		if(ok){
			$("#filar_" + id_revision + "_" + id_revisor).slideUp(400, function(){ $(this).remove(); });
		}else{
			alert("No se puede asociar el revisor.");
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
}

//---------------------

function eliminarRevision(id_revision){
	
	try {
		var ok = jsonrpc._("procesoJsonServicio.eliminarRevision")(id_revision);

		if(ok){
			$("#box_revision_" + id_revision).slideUp(400, function(){ $(this).remove(); });
		}else{
			alert("No se puede eliminar la revision.");
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
}

// ---------------------

function agregarRevision(id_revision_anterior, id_caja){
	
	var nombre = prompt("Introduzca el nombre de la revisión.");		
	
	if(!osm_esVacio(nombre)){
		
		try {
			var id_revisor = jsonrpc._("procesoJsonServicio.agregarRevision")(id_revision_anterior, nombre);
	
			if(id_revisor){
				var PL = osm_getValor('PLANTILLA_REVISION');
				
				PL = osm_remplazar(PL, '[ID_REV]', id_revisor);
				PL = osm_remplazar(PL, '[NOMBRE]', nombre);
				
				$('#' + id_caja).after(PL);
				
				$('#box_revision_' + id_revisor).slideDown(400);
			}else{
				alert("No se puede asociar el revisor.");
			}
			
		} catch (e) {
			alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
			osm_go('crear_proceso/10.2.do');
		}
	}
	
}

// -------------------

function actualizarNombreRevision(id_revision, nombre){
	try {
		var ok = jsonrpc._("procesoJsonServicio.actualizarNombreRevision")(id_revision, nombre);

		if(!ok){
			alert("No cambiar el nombre de la revisión.");
			osm_go('crear_proceso/10.2.do');
		}
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
}


//--------------------

function listenerSelectGrupoFormato(){
	//Se eliman todos los formatos de salida asociados
	$(".eliminarFormatosEntrada").click();
	
	//Se limpia el campo formato de salida
	osm_setValor("Proceso.id_formato_salida","");
	
	guardarProceso();
	
	try {
		jsonrpc._("procesoJsonServicio.obtenerFormatosPorGrupoformato")(pintarSelectoresDeFormatos);
		
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n. " + e);
		osm_go('crear_proceso/10.2.do');
	}
	
	
}

//--------------------
function pintarSelectoresDeFormatos(formatos){

	if(formatos!=null && formatos.list.length>0){
		//Se cargan las lista
		formatosEntrada = formatos.list[0];
		crearSelectorDeFormatosDeEntrada(formatosEntrada);
		
		//Se cargan los clientes
		formatosSalida = formatos.list[1]; 
		crearSelectorDeFormatosDeSalida(formatosSalida);
		
		guardarProceso();
	}
}

//--------------------

function crearSelectorDeFormatosDeEntrada(formatos){
	//Se crean las opciones
	if (formatos != null) {
		
		var selector = osm_getObjeto("select_formatoE");
		$(selector).empty();
		
		//Creamos la opcion por defecto
		selector.options[selector.options.length] = new Option("--seleccione--", "");
		
		lista = formatos.list;
		
		var i = 0;
		while (i < lista .length) {
			formato = lista[i];
			
			selector.options[selector.options.length] = new Option(formato.nombre, formato.id_formato);
			
			//Se incrementa el acumulador
			i++;
		}
		
	}
	
}

//--------------------

function crearSelectorDeFormatosDeSalida(formatos){
	//Se crean las opciones
	if (formatos != null) {
		
		var selector = osm_getObjeto("Proceso.id_formato_salida");
		$(selector).empty();
		
		lista = formatos.list;
		
		var i = 0;
		while (i < lista .length) {
			formato = lista[i];
			
			selector.options[selector.options.length] = new Option(formato.nombre, formato.id_formato);
			
			//Se incrementa el acumulador
			i++;
		}
		
	}
	
}

//--------------------

function cambiarEstadoFtpUsuario(){
	
	var id_ftp_usuario = osm_getValorEntero("FtpUsuario.id_ftp_usuario");
	
	var id_proceso = osm_getValorEntero("Proceso.id_proceso");
	
	if (id_proceso != 0){
		if (osm_getValor("ftp_usuario_activo") == 'S'){
			jsonrpc._("ftpUsuarioServicio.asociarFtpUsuarioProceso")(respuestaCambiarEstadoFtpUsuario, id_ftp_usuario, id_proceso);
		}else {
			jsonrpc._("ftpUsuarioServicio.asociarFtpUsuarioProceso")(respuestaCambiarEstadoFtpUsuario, id_ftp_usuario, null);
		}
	}else {
		osm_alert("No ha seleccionado ning\u00fan proceso");
		osm_go('crear_proceso/10.2.do');
	}
}

//--------------------

function respuestaCambiarEstadoFtpUsuario(respuesta){
	if (respuesta == true){
		var id_ftp_usuario = osm_getValorEntero("FtpUsuario.id_ftp_usuario");
		jsonrpc._("ordenesPagoServicio.cambiarEstadoHilo")(id_ftp_usuario);
		cargarParametrizacionCargaAutomatica();
	}else {
		osm_alert("Ocurri\u00f3 un error al cambiar el estado de la Carga Autom\u00e1tica");
		osm_go('crear_proceso/10.2.do');
	}
}

//--------------------

function activarCargaAutomatica(){
	
	if (!validarNumeroFormatos()){
		var tmp = osm_getObjeto('ftp_usuario_activo');
		var ttpm =  osm_getObjeto('cajachequeo2_divarea_ftp_usuario_activo');
		
		if(tmp != null && tmp.checked){
			ttpm.className = 'cajachequeo2_false';
			osm_setValor('ftp_usuario_activo', 'N');
			tmp.checked = false;
		}
		
		osm_setValor("ftp_usuario_activo", 'N');
		if ($("#area_error_proceso").is(":hidden")){
			$("#area_error_formatos").show();
		}
	}else {
		$("#area_error_formatos").hide();
	}
	if ($("#area_error_proceso").is(":hidden")){
		cambiarEstadoFtpUsuario();
	}
	
}

//--------------------

function validarNumeroFormatos(){
	TOTAL_FORMATOS = 0;
	
	$("#div_formatosE .cuadro_fila").each(function () {
		TOTAL_FORMATOS++;
	});
	
	if (TOTAL_FORMATOS <= 1){
		return true;
	}else {
		return false;
	}
}

//--------------------

function agregarCorreo(){
	var num_correos = osm_getValorEntero("n_correos") + 1;
	osm_construirHTML("div_correos", "correo_plantilla", [num_correos]);
	osm_setValor("n_correos", num_correos);
}

//--------------------

function eliminarCorreo(num_correo){
	
	var id_ftp_usuario_correo = osm_getValorEntero("FtpUsuario.correos_usuario:" + num_correo +".id_ftp_usuario_correo");
	
	if (id_ftp_usuario_correo != 0){
	
		var ok = jsonrpc._("ftpUsuarioCorreoServicio.borrarFtpUsuario")(id_ftp_usuario_correo)
		if (ok){
			$("#fila_correo_"+num_correo).parent().remove();
			return true;
		}else {
			alert("No se pudo eliminar el correo de la base de datos");
			return false;
		}
	}
	
	$("#fila_correo_"+num_correo).parent().remove();
}

//--------------------

function cargarParametrizacionCargaAutomatica(){

	var activo = osm_getValor("ftp_usuario_activo");
	
	if (activo == 'S'){
		$("#area_configuracion_carga_automatica").show();
	}else {
		$("#area_configuracion_carga_automatica").hide();
	}
	
}

//--------------------

function validarConfiguracionAutomatica(){
	
	skin_init_validar();

	skin_validar("FtpUsuario.frecuencia_lectura", "NUMERO", true);
	
	$("#div_correos input[type='text']").each(function () {
		skin_validar($(this).attr("id"), "CORREO", true);
	});
	
	if (SK_ERROR_VALIDACION) {
		osm_alert("Existen datos sin diligenciar o inv\u00e1lidos.\n\nPor favor, verifique los campos resaltados.");
		$(".put").removeClass("on");
		$(".ERR_VALIDAR").addClass("on");
		return false;
	}
	return true;
}

//--------------------

function obtenerIdRespuesta(){
	
	osm_block_window();
	osm_verLoader();
	
	jsonrpc._("ftpUsuarioServicio.registrarIdRespuesta")(guardarConfiguracionAutomatica);

}

//--------------------

function guardarConfiguracionAutomatica(id_respuesta){
	
	var id_ftp_usuario = osm_getValorEntero("FtpUsuario.id_ftp_usuario");
	
	if (id_respuesta != null || id_ftp_usuario != 0){
		
		osm_setValor("id_respuesta", id_respuesta);
		
		osm_setValor("franjas", VALOR_CRITERIO_HORARIO_CP);
		
		var options = {
				success : respuestaGuardarFtpUsuario
			};
		
		$('#form_FtpUsuario').ajaxSubmit(options);	
	}else{
		osm_alert("Ocurri\u00f3 un error guardando la configuraci\u00f3n.");
		osm_go('crear_proceso/10.2.do');
	}
}

//--------------------

function respuestaGuardarFtpUsuario(){

	var id_ftp_usuario = osm_getValorEntero("FtpUsuario.id_ftp_usuario");
	
	var mapa = jsonrpc._("ftpUsuarioServicio.obtenerRespuesta")(osm_getValor("id_respuesta"));
	
	osm_unblock_window();
	
	var respuesta = mapa.map;
	
	var exito = respuesta.exito;
	
	if(exito == true){
		
		var id_horario = respuesta.id_horario;
		
		osm_setValor("id_horario", id_horario);
		
		jsonrpc._("ordenesPagoServicio.reiniciarEstadoHilo")(id_ftp_usuario);				
		
		osm_alert("La configuraci\u00f3n se guard\u00f3 con \u00e9xito");
		
	}else {
		osm_alert("Ocurri\u00f3 un error guardando la configuraci\u00f3n.");
		osm_go('crear_proceso/10.2.do');
	}
}

//---------------------

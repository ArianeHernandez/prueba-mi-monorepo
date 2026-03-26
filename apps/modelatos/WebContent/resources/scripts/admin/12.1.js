var VALIDO = false;

$(document).ready(function () {
	init_editar_estructura();
});


var PAGINA_DATOS = 0;
var PAGINAS_INICIALES = 3;
var ID_ESTRUCTURA;
var SCROLL_SELECTOR = ".scroll_datos";

//Ordenar por el id de campo
var ORDERBY = null;
//Id de campo para filtrar
var ID_CAMPO = null;
//Filtro del campo
var FILTRO = null; 

var CARGANDO = false;

function init_editar_estructura(){
	
	ID_ESTRUCTURA = $("#id_estructura").val();
	
	//Carga las dos primeras pagina
	cargarSiguientePagina();
	
	$(SCROLL_SELECTOR).scroll(function(){
		
		$(".caja_flotante").hide();
		var dif = $(SCROLL_SELECTOR)[0].scrollHeight - $(SCROLL_SELECTOR).scrollTop();
		var height = $(SCROLL_SELECTOR).height() * 1.3;
	    if(height >= dif){
	    	cargarSiguientePagina();
	    }
	    
	});
	$(document).click(clickDocumento);
	$(".limpiarfiltro").click(limpiarFiltro);
}

//Carga la siguiente pagina y la muestra
function cargarSiguientePagina(limpiarDatos){

	if(CARGANDO ){
		return;
	} 
	
	CARGANDO = true;
	PAGINA_DATOS++;
	
	if(limpiarDatos){
		$("#contenido_datos").empty();
		
	}
	
	osm_setValor("mensaje_estado","Cargando ...");
	var datos = jsonrpc._("estructuraServicio.obtenerDatosEstructura")(mostrarSiguientePagina, ID_ESTRUCTURA, ORDERBY, ID_CAMPO, FILTRO , PAGINA_DATOS);
	
}

function crearSelectACEdicion(id, id_fila){
	var selects = $("#fila_"+id + "_" + id_fila).find("select:visible");
	
	if (selects) {
		for ( var i = 0; i < selects.length; i++) {
			selectToAutoComplete(selects[i]);
		}
	}
}

function mostrarSiguientePagina(datos){
	if(datos != null){
		
		datos = datos.list;
		for ( var i = 0; i < datos.length; i++) {
			var reg = datos[i].map;
			var id_fila = $("#contenido_datos > tr").length + 1 ;
			osm_construirHTML("contenido_datos", "PLANTILLA_FILA", [reg.ID, id_fila]);
			
			definirValoresRegistro(reg);
			var fila = osm_getObjeto("fila_" + reg.ID + "_" +  id_fila);
			$(fila).find('.caja_fecha').datepicker({startDate:'01/01/1900', dateFormat : 'dd/mm/yy'});
			
			crearSelectACEdicion(reg.ID, id_fila);
		}
	}
	
	var cargados = $(".scroll_datos .id_registro").not("[value='']").length;
	var total = jsonrpc._("estructuraServicio.contarDatosEstructuraPag")( ID_ESTRUCTURA, ORDERBY, ID_CAMPO, FILTRO);
	total = total == null ? 0 : total; 
	var mensaje = "Mostrando " + cargados + " registros de " + total
	if(ID_CAMPO != null && FILTRO != null){
		var nombreCampo = osm_getValor("nombre_campo_" + ID_CAMPO);
		mensaje += ", filtrando por " + nombreCampo;
		
	}
	if(ORDERBY){
		var nombreCampo = osm_getValor("nombre_campo_" + ORDERBY);
		mensaje += ", ordenando por " + nombreCampo;
	}
	osm_setValor("mensaje_estado",mensaje);
	
	CARGANDO = false;
	if(PAGINA_DATOS < PAGINAS_INICIALES){
		cargarSiguientePagina();
	}
}

//Cuando un campo es actualizado se debe mostrar la fila en otro color e identificarla para su envio
function actualizado(){
	$(this).parents(".fila:first").addClass("actualizado");
	return false;
}

//Define el valor de cada campo en una fila de datos cargados
var ID_CAMPOS = null;
function definirValoresRegistro(reg){

	if(ID_CAMPOS == null){
		ID_CAMPOS = $(".id_campo");
	}
	
	for ( var i = 0; i < ID_CAMPOS.length; i++) {
		var id_campo = ID_CAMPOS[i].value;
		var id = "registros:[" + reg.ID + "].campos:[" + id_campo + "].valor";
		var obj = $(osm_getObjeto(id));
		var valor = reg["C" + id_campo];
		
		if(obj.hasClass("caja_fecha") && valor ){
			var fecha = osm_getFecha(valor.time);
			osm_setValor(id, fecha);
		}
		else if(obj.attr("type") == "checkbox"){
			$(osm_getObjeto(id)).attr("checked", valor);
		}
		else{
			valor = valor ? valor : "";
			osm_setValor(id, valor);
		}
		
		obj.change(actualizado);
		
	}
	
}


//Clic en boton Agregar (nuevo registro)
function agregarFila(){
	var id_fila = $(".fila").length + 1 ;
	osm_construirHTML("contenido_datos", "PLANTILLA_FILA", ["N_" + id_fila, "--"], true);
	var fila = osm_getObjeto("fila_" + "N_" + id_fila +"_--");
	$(fila).find('.caja_fecha').datepicker({startDate:'01/01/1900', dateFormat : 'dd/mm/yy'});
	$(fila).addClass("nuevo");
	$(fila).find("input").get(0).focus();
	
	crearSelectACEdicion("N_" + id_fila, "--");
}

//Verificar que el campo ingresado cumple con la validacion del campo por su tipo
function validarCampo(obj, patron_validacion, obligatorio, tipo, nombre){
	var valor = osm_getValor(obj.id);
	var esVacio = osm_esVacio(valor);
	if(!esVacio && !osm_esVacio(patron_validacion)){
		var regexp = new RegExp(patron_validacion);
		if (!regexp.test(valor)) {
			alert("El campo " + nombre + " debe ser " + tipo);
			obj.get(0).focus();
			VALIDO = false;
		}
	}
	return true;
}

//Validar todos los campos
function validarTodo(){
	return validarUnicos() && validarPatron() && validarObligatorios();
}


function guardar(){
	if(validarTodo()){
		osm_block_window();
		$(".nuevo, .actualizado, .eliminado").appendTo("#tabla_guardar");
		osm_enviarFormulario("formDatos");
	}
}

//Verificar si hay cambios en la pagina
function verificarCambios(){
	if($(".nuevo, .actualizado, .eliminado").length > 0){
		return confirm("Perder\u00e1 los cambios realizados, desea continuar?");

	}else{
		return true;
	}
	
}

//Validar los campos unicos
function validarUnicos(){
	var idsCampos = $(".encabezado").find("[name='validar_unico']");
	for ( var i = 0; i < idsCampos.length; i++) {
		var camposReg = $("[name$='campos\:\[" + idsCampos[i].value + "\]\.valor']");
		for ( var j = 0; j < camposReg.length; j++) {
			var cadena = camposReg[j].value;
			if (cadena != null && cadena != ""){
				for ( var k = j; k < camposReg.length; k++) {
					if(j != k && camposReg[j].value == camposReg[k].value){
						var campo = $("#nombre_campo_" + idsCampos[i].value).val();
						alert("El campo "+ campo + " debe ser \u00fanico");
						camposReg[j].focus();
						return false;
					}
				}
			}
		}
	}
	return true;
}

//Validar patron de Validacion
function validarPatron(){
	var idsCampos = $(".encabezado").find("[name='validar_patron']");
	for ( var i = 0; i < idsCampos.length; i++) {
		var camposReg = $("[name$='campos\:\[" + idsCampos[i].value + "\]\.valor']");
		for ( var j = 0; j < camposReg.length; j++) {
			var cadena = camposReg[j].value;
			var redigit = osm_getValor("validar_patron_" + idsCampos[i].value);
			if (!osm_regexTest(cadena, redigit) && cadena != ""){
				var campo = $("#nombre_campo_" + idsCampos[i].value).val();
				alert("El campo " + campo + " no cumple con el formato establecido");
				camposReg[j].focus();
				return false;
			}
		}
	}
	return true;
}

//Validar los campos obligatorios
function validarObligatorios(){
	var idsCampos = $(".encabezado").find("[name='validar_obligatorio']");
	for ( var i = 0; i < idsCampos.length; i++) {
		var camposReg = $("[name$='campos\:\[" + idsCampos[i].value + "\]\.valor']");
		for ( var j = 0; j < camposReg.length; j++) {
			var cadena = camposReg[j].value;
			if (cadena == null || cadena == ""){
				var campo = $("#nombre_campo_" + idsCampos[i].value).val();
				alert("El campo " + campo + " debe ser diligenciado");
				camposReg[j].focus();
				return false;
			}
		}
	}
	return true;
}

//Valida si se pueden remplazar los datos de la tabla
function validarRemplazarDatos(){
	
	osm_block_window();
	var ok = jsonrpc._("estructuraServicio.validarRemplazarDatos")(ID_ESTRUCTURA);
	
	if (!ok){
		osm_alert("Los datos de la estructura no pueden ser remplazados");
	}
	
	return ok;
}

//Valida si se puede elimiar un registro
function validarEliminarFilaReg(id){
	
	var obj = osm_getObjeto("eliminar_registro_" + id);
	
	if (!obj) {
		return true;
	}
	var nombreObj = $(obj).attr("name");
	var nombreObjId = nombreObj.replace(".eliminar", ".id_registro");
	
	var idRegistro = $("input[name='" + nombreObjId + "']").val();
	
	var ok = jsonrpc._("estructuraServicio.validarEliminarRegistro")(ID_ESTRUCTURA, idRegistro);
	
	
	if (!ok){
		osm_alert("El registro no puede ser eliminado");
	}
	
	return ok;
}

//Eliminar un registro
function eliminarFilaReg(id){
	if(osm_comfirm("\u00bfEst\u00e1 seguro que desea eliminar este registro?")){
		if (!validarEliminarFilaReg('[ 1 ]_[ 2 ]')) {
			alert("NO [ 1 ]_[ 2 ]");
			return false;
		}
	

	if (!validarEliminarFilaReg('[ 1 ]_[ 2 ]')) {
		alert("NO [ 1 ]_[ 2 ]");
		return false;
	}
	
	var existe = $("#fila_" + id).hasClass("nuevo");
	if(!existe){
		osm_setValor("eliminar_registro_" + id, true);
		$("#fila_" + id).addClass("eliminado");
		$("#fila_" + id).find("select, input").not(":hidden").attr("disabled", true);
		
	}else{
		$("#fila_" + id).remove();
	}
}
}

//Ordenar los resultados por id de campo
function ordenarPorCampo(id_campo){
	var cambios = verificarCambios();
	if(!cambios){
		return false;
	}
	
	$(".caja_flotante").hide();
	ORDERBY = id_campo;
	PAGINA_DATOS = 0;
	cargarSiguientePagina(true);
}


//Filtrar por un campo
function filtrarCampo(id_campo){
	var cambios = verificarCambios();
	if(!cambios){
		return false;
	}
	//Si es un campo diferente
	var div = $("#div_filtrar_" + id_campo);
	$(".caja_flotante").not(div).hide();
	if(ID_CAMPO != id_campo){
		$("#campo_filtro").remove();
		
		ID_CAMPO = id_campo;
		FILTRO = null;
		
		if($("#TABLA_TEMPORAL .campo").length == 0){
			osm_construirHTML("TABLA_TEMPORAL", "PLANTILLA_FILA", ["_", "_"]);
		}
		
		var campo = $("#TABLA_TEMPORAL .campo" + id_campo).clone();
		campo.prependTo(div)
		campo.attr("id", "campo_filtro");
		campo.attr("name","");
		campo.width("270px");
		campo.get(0).onchange = null;
		campo.change(buscarPorFiltro);
		var pos = osm_getElementPosition(div.parent().get(0));
		
		div.css("left", (pos[0]) + "px");
		div.css("top",(pos[1] + div.parent().height()) + "px");
		
	}
	div.show();
	$("#campo_filtro").get(0).focus();
	
}

function buscarPorFiltro(){
	
	FILTRO = this.value;
	PAGINA_DATOS = 0;
	cargarSiguientePagina(true);
	$(".btn_limpiar").show();
	return false;
}

//Limpiar los filtros
function limpiarFiltro(){

	var cambios = verificarCambios();
	
	if(!cambios){
		return false;
	}
	
	ID_CAMPO = null;
	FILTRO = null;
	PAGINA_DATOS = 0;
	cargarSiguientePagina(true);
	$(".caja_flotante").hide();
	$(".caja_flotante caja").remove();
	$(".btn_limpiar").hide();
	return false;
}

//Cierra la caja de filtro si no se hace clic en el encabezado
function clickDocumento(e){
	var cant = $(e.target).parents(".encabezado");
	if(!cant.length && !$(e.target).hasClass("encabezado")){
		$(".caja_flotante").hide();
	}
}

//Cierra la caja filtro con la tecla scape
osm_listen(	"keypress", document, function(e){
	try {
		var obj = e.target;
		if(e.keyCode == 27 ){
			$(".caja_flotante").hide();	
			return false;
		}
	}catch(_e){
	}
});

function enviarExcel(reemplazarTodo){
	var archivo = osm_getValor('filename');
	if(osm_esVacio(archivo)){
		alert("Seleccione un archivo para cargar");
		return false;
	}
	osm_setValor('reemplazar_todo', reemplazarTodo);
	cerrarVentana('ventanaSubirExcel');
	osm_enviarFormulario('cargarExcel');
	
}


function mostrarVentana(ventana){
	osm_setVisible(ventana, true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentana(ventana){
	osm_setVisible(ventana, false, false);
	osm_mostrarSelects("bodyContent");
}

function descargarExcel(descargarDatos){
	osm_setValor('descargar_datos', descargarDatos);
	osm_enviarFormulario('descargaExcel');
	cerrarVentana('ventanaDescargarExcel');
	osm_unblock_window();
}
 

$(cargar_datos);
var DATOS_VISTA = [];
var DATOS_CONSULTA_CONF = [];
var DATOS_ROLES = [];
var DATOS_ROLES_ACT = [];
function cargar_datos(){
	jsonrpc._("historialConsultaServicio.obtenerConsultas")(manejar_consulta);
	jsonrpc._("historialConsultaServicio.obtenerConsultaDetalle")(manejar_detalle);
	jsonrpc._("historialConsultaServicio.obtenerConsultasConf")(manejar_consulta_conf);
	jsonrpc._("historialConsultaServicio.obtenerConsultasRol")(manejar_consulta_rol);
	jsonrpc._("historialConsultaServicio.obtenerRolesActivos")(manejar_consulta_rol_activo);
	$("#btn_borrar_rol").hide();
	$("#btn_actualizar_rol").hide();
	
}

function manejar_consulta_rol(resp){
	if(resp.list){
		DATOS_ROLES= resp.list;
		for(var i = 0 ; i<DATOS_ROLES.length ; i++){
			var elemento =  DATOS_ROLES[i];
			agregarFilaRol(elemento, i);
		}
	}
}

function manejar_consulta_rol_activo(resp){
	
	if(resp.list){
		DATOS_ROLES_ACT= resp.list;
		for(var i = 0 ; i<DATOS_ROLES_ACT.length ; i++){
			$("#roles").append("<option value=\""+DATOS_ROLES_ACT[i].id_rol+"\" selected=\"\">"+DATOS_ROLES_ACT[i].nombre_rol+"</option>");
		}
	}
}

function manejar_consulta(resp){
	
	if(resp.list && resp.list[0]){
		osm_setValor("descripcion", resp.list[0].descripcion);
		osm_setValor("scriptConsulta", resp.list[0].consulta);
		osm_setValor("filtro", resp.list[0].filtro);
	}
}

function manejar_detalle(resp){
	if(resp.list){
		DATOS_VISTA = resp.list;
		for(var i = 0 ; i<DATOS_VISTA.length ; i++){
			$("#opciones_vista").append("<option value=\""+DATOS_VISTA[i].columna+"\" selected=\"\">"+DATOS_VISTA[i].columna+"</option>");
			$("#opciones_vista_rol").append("<option value=\""+DATOS_VISTA[i].columna+"\" selected=\"\">"+DATOS_VISTA[i].columna+"</option>");
		}
	}
}

function manejar_consulta_conf(resp){
	if(resp.list){
		DATOS_CONSULTA_CONF = resp.list;
		for(var i = 0 ; i<DATOS_CONSULTA_CONF.length ; i++){
			var elemento =  DATOS_CONSULTA_CONF[i];
			agregarFila(elemento, i);
		}
	}
}

function guardar(){
	osm_verLoader();
	if(osm_esVacio(osm_getValor("descripcion"))){
		alert("Ingrese una descripción");
		return;
	}
	
	if(osm_esVacio(osm_getValor("scriptConsulta"))){
		alert("Ingrese el script de consulta");
		return;
	}
	
	const objBody= armarObj();
	jsonrpc._("historialConsultaServicio.actualizarConsulta")(manejar_guardar, objBody);
	
	//guardar datos
	osm_ocultarLoader();
	
}
function guardarConfRol(){
	
	if(osm_esVacio(osm_getValor("roles"))){
		alert("Ingrese un rol");
		return;
	}
	if(osm_esVacio(osm_getValor("opciones_vista_rol"))){
		alert("Ingrese una opción de configuración");
		return;
	}
	if(osm_esVacio(osm_getValor("valor_rol"))){
		alert("Ingrese un valor de rol");
		return;
	}
	osm_verLoader();
	const objBody = armarObjRol();
	jsonrpc._("historialConsultaServicio.guardarConsultasRol")(manejar_guardar_rol, objBody);
	osm_ocultarLoader();
}
function actualizarConfRol(){
	if(osm_esVacio(osm_getValor("id_rol"))){
		alert("El id a actualizar está vacio");
		return;
	}
	if(osm_esVacio(osm_getValor("roles"))){
		alert("Ingrese un rol");
		return;
	}
	if(osm_esVacio(osm_getValor("opciones_vista_rol"))){
		alert("Ingrese una opción de configuración");
		return;
	}
	if(osm_esVacio(osm_getValor("valor_rol"))){
		alert("Ingrese un valor de rol");
		return;
	}
	osm_verLoader();
	const objBody = armarObjRol();
	jsonrpc._("historialConsultaServicio.actualizarConsultaRol")(manejar_guardar_rol, objBody);
	osm_ocultarLoader();
}

function borrarConfRol(){
	if(osm_esVacio(osm_getValor("id_rol"))){
		alert("El id a borrar está vacio");
		return;
	}
	
	osm_verLoader();
	jsonrpc._("historialConsultaServicio.eliminarConsultaRol")(manejar_guardar_rol, osm_getValor("id_rol"));
	osm_ocultarLoader();
}


function armarObj(){
	let obj = {
			"id_historial": 1,
			"consulta":osm_getValor("scriptConsulta"),
			"descripcion": osm_getValor("descripcion"),
			"filtro": osm_getValor("filtro")
	}
	return obj;
}
function armarObjRol(){
	let obj = {
			"id_historial_consulta_rol": osm_esVacio(osm_getValor("id_rol")) ? null: parseInt(osm_getValor("id_rol"), 10),
			"nombre_columna":osm_getValor("opciones_vista_rol"),
			"rol": osm_getValor("roles"),
			"valor": osm_getValor("valor_rol")
	}
	return obj;
}

function armarObjConf(){
	let obj = {
			"posicion":osm_getValor("posicion"),
			"nombre_columna": osm_getValor("opciones_vista"),
			"etiqueta_columna": osm_getValor("etiqueta_columna"),
			"mostrar": $("#mostrar").prop('checked') ? 1 : 0,
			"excel": $("#excel").prop('checked') ? 1 : 0,
			"filtrar": $("#filtrar").prop('checked') ? 1 : 0,
			"tipo_dato": osm_getValor("tipo_dato"),
			"id_historial_consulta_conf": null
	}
	return obj;
}

function manejar_guardar(resp){
	if(resp == true){
		alert("Consulta actualizada");
	}else{
		alert("Ocurrió un error actualizando la consulta");
	}
}

function manejar_guardar_conf(resp){
	if(resp == true){
		$("#tbodyId tr.tabla_fila").remove();
		jsonrpc._("historialConsultaServicio.obtenerConsultasConf")(manejar_consulta_conf);
		alert("Configuración guardada");
		
	}else{
		alert("Ocurrió un error");
	}
}

function manejar_guardar_rol(resp){
	if(resp == true){
		$("#tbodyIdRol tr.tabla_fila").remove();
		jsonrpc._("historialConsultaServicio.obtenerConsultasRol")(manejar_consulta_rol);
		limpiarConfRol();
		alert("Configuración guardada");
		
	}else{
		alert("Ocurrió un error");
	}
}

function agregarFila(elemento, pos){
	$("#tbodyId").append("<tr onmouseout=\"this.className='tabla_fila '\" onmouseover=\"this.className='tabla_fila_over '\" class=\"tabla_fila \">" +
			"<td>"+elemento.posicion+"</td>" +
			"<td>"+elemento.nombre_columna+"</td>" +
			"<td>"+elemento.tipo_dato+"</td>" +
			"<td>"+elemento.etiqueta_columna+"</td>" +
			"<td>"+(elemento.mostrar==0 || elemento.mostrar==null?"No":"Si")+"</td>" +
			"<td>"+(elemento.excel==0 || elemento.excel==null?"No":"Si")+"</td>" +
			"<td>"+(elemento.filtrar==0 || elemento.filtrar==null?"No":"Si")+"</td>" +
			"<td><a estilo=\"btn btn-sm btn-guardar\" " +
			"onclick=\"editarConf("+pos+");return false;\" class=\"btn btn-sm btn-primary\">Editar</a>" +
			"</td>" +
			"</tr>");
}

function agregarFilaRol(elemento, pos){
	console.log("agregar fila rol");
	console.log(elemento);
	$("#tbodyIdRol").append("<tr onmouseout=\"this.className='tabla_fila '\" onmouseover=\"this.className='tabla_fila_over '\" class=\"tabla_fila \">" +
			
			"<td>"+elemento.rol+"</td>" +
			"<td>"+elemento.nombre_columna+"</td>" +
			"<td>"+elemento.valor+"</td>" +
			"<td><a estilo=\"btn btn-sm btn-guardar\" " +
			"onclick=\"editarConfRol("+pos+");return false;\" class=\"btn btn-sm btn-primary\">Editar</a>" +
			"</td>" +
			"</tr>");
}

function editarConf(pos){
	var elemento= DATOS_CONSULTA_CONF[pos];
	//set valores a formulario
	osm_setValor("opciones_vista", elemento.nombre_columna);
	osm_setValor("etiqueta_columna", elemento.etiqueta_columna);
	osm_setValor("posicion", elemento.posicion);
	osm_setValor("tipo_dato", elemento.tipo_dato);
	$("#mostrar").prop('checked', elemento.mostrar);
	$("#excel").prop('checked', elemento.excel);
	$("#filtrar").prop('checked', elemento.filtrar);
}

function editarConfRol(pos){
	var elemento= DATOS_ROLES[pos];
	//set valores a formulario
	osm_setValor("id_rol", elemento.id_historial_consulta_rol);
	osm_setValor("roles", elemento.rol);
	osm_setValor("opciones_vista_rol", elemento.nombre_columna);
	osm_setValor("valor_rol", elemento.valor);
	
	$("#btn_guardar_rol").hide();
	$("#btn_borrar_rol").show();
	$("#btn_actualizar_rol").show();
}

function limpiarConfRol(){
	
	osm_setValor("id_rol", "");
	osm_setValor("roles", "");
	osm_setValor("opciones_vista_rol", "");
	osm_setValor("valor_rol", "");
	
	$("#btn_guardar_rol").show();
	$("#btn_borrar_rol").hide();
	$("#btn_actualizar_rol").hide();
	
}

function guardarElemento(){
	
	if(osm_esVacio(osm_getValor("opciones_vista"))){
		alert("Ingrese una opción de configuración");
		return;
	}
	
	if(osm_esVacio(osm_getValor("etiqueta_columna"))){
		alert("Ingrese una etiqueta");
		return;
	}
	
	if(osm_esVacio(osm_getValor("posicion"))){
		alert("Ingrese una posición");
		return;
	}
	
	if(osm_esVacio(osm_getValor("tipo_dato"))){
		alert("Ingrese un tipo de dato");
		return;
	}
	
	const objBody= armarObjConf();
	
	osm_verLoader();
	//guardar datos
	var newArray = DATOS_CONSULTA_CONF.filter(function (el) {
		  return el.nombre_columna == objBody.nombre_columna;
		});
	if(newArray.length == 0){
		//crear nuevo registro
		jsonrpc._("historialConsultaServicio.guardarConsultaConf")(manejar_guardar_conf, objBody);
		
	}else{
		objBody.id_historial_consulta_conf=newArray[0].id_historial_consulta_conf;
		jsonrpc._("historialConsultaServicio.actualizarConsultaConf")(manejar_guardar_conf, objBody);
		
		//actualizar registro
	}
	osm_ocultarLoader();
	
}


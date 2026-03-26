$(cargarContenidos)
var PAGINA = 1;
var MOSTRADOS = 0;
var TOTAL = 0;
var SERVICIOS_CARGADOS = false;

function cargarContenidos() {

	jsonrpc.administracionServicio
			.obtenerAplicacionesSimple(listarAplicaciones);
}

function listarAplicaciones(listado) {

	if (listado != null) {

		listado = listado.list;

		for ( var i = 0; i < listado.length; i++) {
			var parametros = new Array();
			var idAplicacion = listado[i].id_aplicacion;
			parametros[0] = listado[i].id_aplicacion;
			parametros[1] = listado[i].nombre;
			parametros[2] = listado[i].descripcion;

			osm_construirHTML("pestanas_aplicaciones", "PLANTILLA_APLICACION",
					parametros);
			osm_construirHTML("aplicaciones", "CONTENIDO_APLICACION",
					parametros);

			$("#aplicacion_" + idAplicacion).attr("href",
					"#contenido_aplicacion_" + idAplicacion);
			$("#aplicacion_" + idAplicacion).click(
					new Function("verRoles('" + idAplicacion + "')"));

			if (i == 0) {
				verRoles(idAplicacion)
			}
		}
	}
	$("#aplicaciones").tabs();
}

function verRoles(idAplicacion) {
	jsonrpc.administracionServicio.obtenerRolesAplicacion(new Function(
			"listado", "listarRoles(listado, '" + idAplicacion + "');"),
			idAplicacion);
}

function listarRoles(listado, idAplicacion) {

	if (listado != null) {
		$("#roles_" + idAplicacion).empty();

		listado = listado.list;

		for ( var i = 0; i < listado.length; i++) {

			var parametros = new Array();
			var idRol = listado[i].id_rol;
			parametros[0] = listado[i].id_rol;
			parametros[1] = listado[i].nombre;

			osm_construirHTML("roles_" + idAplicacion, "ROL", parametros);

			if (i == 0) {
				verContenidoRol(idAplicacion, idRol, listado[i].nombre);
			}

			$("#rol_" + idRol).click(
					new Function("verContenidoRol('" + idAplicacion + "', '"
							+ idRol + "', '" + listado[i].nombre + "')"));

		}
	}

	$("#roles_" + idAplicacion).buttonset();

}

function verContenidoRol(idAplicacion, idRol, nombre) {
	PAGINA = 1;
	MOSTRADOS = 0;
	TOTAL = 0;
	SERVICIOS_CARGADOS = false;
	
	$("#contenido_rol_" + idAplicacion).empty();
	var parametros = new Array();
	parametros[0] = idRol;
	parametros[1] = nombre;

	osm_construirHTML("contenido_rol_" + idAplicacion, "CONTENIDO_ROL",
			parametros);
	$("#secciones_rol_" + idRol).accordion({
		collapsible : true,
		autoHeight: false,
	});
	setTimeout("mostrarUsuariosRol('" + idRol + "')",50)
	$("#seccion_servicios_"+idRol).click(new Function("verServiciosRol('" + idRol + "')"));
}

function mostrarUsuariosRol(idRol) {
	TOTAL = jsonrpc.administracionServicio.contarUsuariosRol(idRol);
	if (TOTAL) {
		mostarUsuarioProxPagina(idRol);
	}
}
function mostarUsuarioProxPagina(idRol){
	
	if(MOSTRADOS < TOTAL)
	jsonrpc.administracionServicio.obtenerUsuariosRol(new Function(
			"listado", "mostrarUsuarios(listado, '" + idRol + "');"), idRol, PAGINA);
	PAGINA++;
}
function mostrarUsuarios(listado, idRol) {
	if (listado != null) {

		listado = listado.list;
		MOSTRADOS += listado.length;
		
		for ( var i = 0; i < listado.length; i++) {
			
			var parametros = new Array();
			parametros[0] = listado[i].id_usuario;
			parametros[1] = listado[i].login;
			
			osm_construirHTML("usuarios_" + idRol, "USUARIO",
					parametros);
		}
		setTimeout("mostarUsuarioProxPagina('" + idRol + "')",50)
	}

}

function verServiciosRol(idRol) {
	if (SERVICIOS_CARGADOS) {
		return;
	}
	jsonrpc.administracionServicio.obtenerServiciosRol(new Function(
			"listado", "listarServiciosRol(listado, '" + idRol + "');"),
			idRol);
}

function listarServiciosRol(listado, idRol){
	
	if (listado != null) {
		SERVICIOS_CARGADOS = true;
		listado = listado.list;
		
		for ( var i = 0; i < listado.length; i++) {
			
			var parametros = new Array();
			
			var idServicio = listado[i].id_servicio;
			
			parametros[0] = idServicio;
			parametros[1] = listado[i].nombre;
			
			osm_construirHTML("servicios_" + idRol, "SERVICIO",
					parametros);
			
			$("#servicio_"+idServicio).click(new Function("verUrlsServicio('" + idServicio + "')"));
			$("#servicio_"+idServicio).button();
			
		}
	}
			
}
function verUrlsServicio(idServicio){

	jsonrpc.administracionServicio.obtenerUrlsServicio(new Function(
			"listado", "listarUrlsServicio(listado, '" + idServicio + "');"),
			idServicio);
}

function listarUrlsServicio(listado, idServicio){
	
	if (listado != null) {
		
		listado = listado.list;
		
		$("#contenido_servicio_" + idServicio).empty();
		
		for ( var i = 0; i < listado.length; i++) {
			
			var parametros = new Array();
			
			parametros[0] = listado[i].url;
			
			osm_construirHTML("contenido_servicio_" + idServicio, "URL",
					parametros);
			
			
			
		}
		$("#contenido_servicio_" + idServicio).show();
	}
			
}

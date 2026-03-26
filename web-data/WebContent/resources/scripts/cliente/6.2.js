$(document).ready(cargarParametrizacion);

function cargarParametrizacion(){
	
	//Se carga la parametrizacion del uso de firma
	var uso_firma=osm_getObjeto("Usuario.uso_firma");
	if(uso_firma.checked){
			
		if (osm_getValor('Persona.tipo_persona') == 'J') {
			activarUsoFirmaProcesoCliente();
		}else{
			desactivarUsoFirmaProcesoCliente();
		}
		
	}else{
		desactivarUsoFirmaProcesoCliente();
	}
	
	var uso_ftp_usuario = osm_getObjeto("Usuario.uso_ftp_usuario");
	if (uso_ftp_usuario!=null && uso_ftp_usuario.checked){
		activarCargueFTP();
	}else {
		desactivarCargueFTP();
	}
	
	datosCargueFTP();
	
}


function activarUsoFirmaProcesoCliente(){
	$("#paramtrizacionUsoFirmaProcesoCliente").show();
	
}

function desactivarUsoFirmaProcesoCliente(){
	var uso_firma_preparador=osm_getObjeto("Usuario.uso_firma_preparador");
	uso_firma_preparador.checked = false;
	
	var uso_firma_preparador=osm_getObjeto("Usuario.uso_firma_revisor");
	uso_firma_preparador.checked=false;
	
	var uso_firma_liberador=osm_getObjeto("Usuario.uso_firma_liberador");
	uso_firma_liberador.checked=false;
	
	$("#paramtrizacionUsoFirmaProcesoCliente").hide();
}

function validarCliente(){
	var msj = false;
	var crea_login = osm_getObjeto("crear_login");
	
	if(crea_login != null && !crea_login.checked){
		crea_login = false;
	}else{
		crea_login = true;
	}
	
	if (osm_esVacio(osm_getValor('Persona.nombre'))) {
		msj = "El campo nombre no puede ser vacio";
	}
	if (!msj && osm_esVacio(osm_getValor('Persona.tipo_persona'))) {
		msj = "Seleccion un tipo de persona";
	}
	var persona_natural = osm_getValor('Persona.tipo_persona') == 'N';
	if (!msj && persona_natural && osm_esVacio(osm_getValor('Persona.apellido'))) {
		msj = "El campo apellido no puede ser vacio";
	}
	if (!msj && osm_esVacio(osm_getValor('Persona.identificacion'))) {
		msj = "Identificaci\u00f3n no valida";
	}
	if (!msj && crea_login && !osm_validarCorreo(osm_getValor('Persona.correo'))) {
		msj = "El correo no es valido";
	}
	if (!msj && persona_natural && osm_esVacio(osm_getValor('Persona.genero'))) {
		msj = "Seleccion el g\u00e9nero de la persona";
	}
	if (msj) {
		alert(msj);
		return false;
	}
	if(!crea_login){
		osm_setValor("Credencial.login","");
		alert(crea_login.checked)
	}
	return true;
}

//---------------------------------------------------------------------------------
function eliminarNegocio(id_negocio){
	
	$("#fila_"+id_negocio).remove();

}

function agregarNegocio(obj){
	var id_negocio = obj.value;
	
	if(!osm_esVacio(id_negocio))
	{
	
		if (osm_getObjeto("fila_"+id_negocio) != null)  {
			var fila = $("#fila_"+id_negocio);
			fila.css("background-color","#CBCBCB");
			fila.animate({"background-color":"#fff"}, 800);
			return;	
		}
		
		var num_negocio = parseInt(osm_getValor("n_negocios")) + 1;
		
		var nombre_negocio = osm_getValorText(obj.id);
		var name =  nombre_negocio.indexOf("-");
		
		if(name!=null && name> 0){
			
			nombre_negocio = nombre_negocio.substring(0, name );
		}
				
		osm_construirHTML("div_negocios", "negocio_plantilla", [id_negocio, nombre_negocio, num_negocio]);
		osm_setValor("n_negocios", num_negocio);
	}
	
	obj.value = "";
	
}

function validar_negocios(){
	roles = $("name='#fila_rol'");
	if (roles.length == 0) { 
		//return confirm("No ha seleccionado ning\u00fan rol, \u00bfdesea continuar?");
	}
	return true;
}

function cambiarUsoFirmaCliente(){
	var uso_firma=osm_getObjeto('Usuario.uso_firma');
	if(uso_firma.checked){
		osm_setValor('Usuario.uso_firma','S');
		
		if (osm_getValor('Persona.tipo_persona') == 'J') {
			activarUsoFirmaProcesoCliente();
		}else{
			desactivarUsoFirmaProcesoCliente();
		}
		
	}else{
		osm_setValor('Usuario.uso_firma',null);
		desactivarUsoFirmaProcesoCliente();
	}
	
}

function cambiarUsoFirma(id){
	var uso_firma=osm_getObjeto(id);
	if(uso_firma.checked){
		osm_setValor(id,'S');
	}else{
		osm_setValor(id,null);
	}
}

function cambiarCargueFTP(){
	var cargue_ftp = osm_getObjeto("Usuario.uso_ftp_usuario");
	if (cargue_ftp.checked){
		osm_setValor("Usuario.uso_ftp_usuario",'S');
		activarCargueFTP();
	}else {
		osm_setValor("Usuario.uso_ftp_usuario",null);
		desactivarCargueFTP();
	}	
}

function activarCargueFTP(){
	$('#boton_ftp').show();
}

function desactivarCargueFTP(){
	$('#boton_ftp').hide();
}

function verVentanaEdicionFTP(){
		
	osm_ocultarSelects("bodyContent");
	$("#vn_editar_conexion .bloqueo_ventana_base").hide();
	
	$("#vn_editar_conexion").fadeIn(300, function() {
		$("#vn_editar_conexion .bloqueo_ventana_base").fadeIn(300);
	});	
	
}

function ocultarVentanaEdicionFTP(){
	var tipo_usuario = jsonrpc._("contenido.obtenerContenido")("General", "CLIENTE");
	
	if(tipo_usuario == null){
		tipo_usuario = "Cliente";
	}

	if (osm_comfirm("La informaci\u00f3n ingresada no ser\u00e1 almacenada.\n\n Est\u00e1 seguro que desea salir de la configuraci\u00f3n para el " + tipo_usuario +"?")){
		$("#vn_editar_conexion .bloqueo_ventana_base").fadeOut(300, function() {
			$("#vn_editar_conexion").fadeOut(300);
		});
		
		osm_mostrarSelects("bodyContent");
		$("#btnGuardar").hide();
		
		//Se cambia la informacion desplegada por la informacion inicial
		$('#form_FtpUsuario input').each(function() {
			$(this).attr("value", $(this).data('initial_value'));
	     });
	}
}

function replaceBackslash (obj){
	var name = $(obj).val();
	var name_without_backslash = name.replace(/\\/g, "/");
	//var dname_without_space = name_without_backslash.replace(/ /g, "");
	//var name_without_special_char = dname_without_space.replace(/[^a-zA-Z 0-9 ^\/ ^\_ ^\-]+/g, "");
	var name_without_special_char = name_without_backslash.replace(/[^a-zA-Z 0-9 ^\/ ^\_ ^\-]+/g, "");
	name = name_without_special_char;
	if (name.indexOf("/") != 0){
		name = "/" + name;
	}
	if (name.lastIndexOf("/") != name.length - 1){
		name = name + "/";
	}
	$(obj).attr("value", name);
}

function toggleEncripcion(){
	var valor = $("input[name='encripcion']:checked").val();
	if (valor == 'S'){
		osm_setValor("FtpUsuario.encripcion",'S');
		$('#area_encripcion').show();
	}else {
		osm_setValor("FtpUsuario.encripcion",'N');
		$('#area_encripcion').hide();
	}
}

function validarCampos(){

	skin_init_validar();

	skin_validar("FtpUsuario.host", "TEXTO", true);
	skin_validar("FtpUsuario.dominio", "ALFANUMERICO", false);
	skin_validar("FtpUsuario.nombre_usuario", "TEXTO");
	skin_validar("FtpUsuario.clave", "TEXTO");
	skin_validar("FtpUsuario.carpeta_fallidos", "TEXTO", true);
	skin_validar("FtpUsuario.carpeta_procesados", "TEXTO", true);
	skin_validar("FtpUsuario.carpeta_transito", "TEXTO", true);
	skin_validar("FtpUsuario.carpeta_bk_fallidos", "TEXTO", true);
	skin_validar("FtpUsuario.carpeta_bk_procesados", "TEXTO", true);
	skin_validar("FtpUsuario.carpeta_bk_transito", "TEXTO", true);
	
	var valor = $("input[name='encripcion']:checked").val();
	
	if (valor == 'S'){
		skin_validar("FtpUsuario.clave_encripcion", "TEXTO", true);
		skin_validar("FtpUsuario.llave_privada", "TEXTO", true);	
	}else if (valor != 'N'){
		skin_error("label_encripcion");
		skin_error("label_no_encripcion");
	}
	
	//skin_validar("FtpUsuario.correo_notificaciones", "CORREO", true);
	
	$("#div_correos input[type='text']").each(function () {
		skin_validar($(this).attr("id"), "CORREO", true);
	});
	
	skin_validar("FtpUsuario.dias_validacion", "NUMERO", true);
	
	if (SK_ERROR_VALIDACION) {
		osm_alert("Existen datos sin diligenciar o inv\u00e1lidos.\n\nPor favor, verifique los campos resaltados.");
		$(".put").removeClass("on");
		$(".ERR_VALIDAR").addClass("on");
		return false;
	}
	return true;
	
}

function validarConexion(){
	$("#btnGuardar").hide('fast');
		
	osm_block_window();
	osm_verLoader();
	
	var host = osm_getValor("FtpUsuario.host");
	var dominio = osm_getValor("FtpUsuario.dominio");
	var usuario = osm_getValor("FtpUsuario.nombre_usuario");
	var clave = osm_getValor("FtpUsuario.clave");
	var fallidos = osm_getValor("FtpUsuario.carpeta_fallidos");
	var procesados = osm_getValor("FtpUsuario.carpeta_procesados");
	var transito = osm_getValor("FtpUsuario.carpeta_transito");
	var bkfallidos = osm_getValor("FtpUsuario.carpeta_bk_fallidos");
	var bkprocesados = osm_getValor("FtpUsuario.carpeta_bk_procesados");
	var bktransito = osm_getValor("FtpUsuario.carpeta_bk_transito");
	
	var parametros = "fallidos:" + fallidos + "," + 
					 "procesados:" + procesados + "," + 
					 "transito:" + transito + "," + 
					 "backup_fallidos:" + bkfallidos + "," + 
					 "backup_procesados:" + bkprocesados + "," + 
					 "backup_transito:" + bktransito;
	
	jsonrpc._("SambaServicio.verificarDatosConexion")(respuestaValidacionFTP, host, dominio, usuario, clave, parametros);
		
}

function obtenerIdRespuesta(){
		
	osm_block_window();
	osm_verLoader();
	
	jsonrpc._("ftpUsuarioServicio.registrarIdRespuesta")(guardarFtpUsuario);

}

function guardarFtpUsuario(id_respuesta){
	if (id_respuesta != null){
		
		osm_setValor("id_respuesta", id_respuesta);
		
		var options = {
				success : respuestaGuardarFtpUsuario
			};
		
		$('#form_FtpUsuario').ajaxSubmit(options);	
	}else{
		osm_alert("Ocurri\u00f3 un error guardando la configuraci\u00f3n.");
		$("#btnGuardar").hide('fast');
		osm_unblock_window();
	}
}

function respuestaGuardarFtpUsuario(){
	
	var mapa = jsonrpc._("ftpUsuarioServicio.obtenerRespuesta")(osm_getValor("id_respuesta"));
	
	osm_unblock_window();
	
	var respuesta = mapa.map;
	
	var exito = respuesta.exito;
	
	if(exito == true){
		
		var id_ftp_usuario = respuesta.id_ftp_usuario;
		
		osm_setValor("FtpUsuario.id_ftp_usuario", id_ftp_usuario);
		
		osm_alert("La configuraci\u00f3n se guard\u00f3 con \u00e9xito");
		
		datosCargueFTP();
		
		$("#vn_editar_conexion .bloqueo_ventana_base").fadeOut(300, function() {
			$("#vn_editar_conexion").fadeOut(300);
		});
		
		osm_mostrarSelects("bodyContent");
		$("#btnGuardar").hide();
		
	}else {
		osm_alert("Ocurri\u00f3 un error guardando la configuraci\u00f3n.");
	}
}

function respuestaValidacionFTP(respuesta){
	if (respuesta == true){
		osm_alert("La conexi\u00f3n se realiz\u00f3 con \u00e9xito.");
		$("#btnGuardar").show();
	}else {
		osm_alert("Se present\u00f3 un fallo al validar la conexi\u00f3n.\n\nPor favor verifique la informaci\u00f3n diligenciada");
	}
	osm_unblock_window();
}

//Se guardan los datos de cargueftp
function datosCargueFTP(){
	$('#form_FtpUsuario input').each(function () {
        $(this).data('initial_value', $(this).val());
   });
}

function agregarCorreo(){
	var num_correos = osm_getValorEntero("n_correos") + 1;
	osm_construirHTML("div_correos", "correo_plantilla", [num_correos]);
	osm_setValor("n_correos", num_correos);
}

function eliminarCorreo(num_correo){
	
	var id_ftp_usuario_correo = osm_getValorEntero("FtpUsuario.correos_correval:" + num_correo +".id_ftp_usuario_correo");
	
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

$(function() {
	
	osm_listen("change", "FtpUsuario.carpeta_fallidos", function (){
		replaceBackslash(this);
	});
	
	osm_listen("change", "FtpUsuario.carpeta_procesados", function (){
		replaceBackslash(this);
	});
	
	osm_listen("change", "FtpUsuario.carpeta_transito", function (){
		replaceBackslash(this);
	});
	
	osm_listen("change", "FtpUsuario.carpeta_bk_fallidos", function (){
		replaceBackslash(this);
	});
	
	osm_listen("change", "FtpUsuario.carpeta_bk_procesados", function (){
		replaceBackslash(this);
	});
	
	osm_listen("change", "FtpUsuario.carpeta_bk_transito", function (){
		replaceBackslash(this);
	});
	
	$("input[name='encripcion']").change(function (){
		toggleEncripcion();
	});
	
	// si se llega a cambiar algo de la configuracion el boton de guardar se esconde
	$("#form_FtpUsuario input").each(function (){
		$(this).change(function (){
			$("#btnGuardar").hide('fast');
		});
	});
	
	
});

function rc_ajusteVentana() {
	var pos = osm_getWindowSize();
	$("#area_configuracion").css("height", (pos[1] - 300) + "px").css("overflow", "auto");
}

osm_listen("resize", window, rc_ajusteVentana);
osm_listen("load", window, rc_ajusteVentana);

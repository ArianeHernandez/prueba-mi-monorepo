
function listarNotas(id_carga) {

	var notas=	jsonrpc._("notasJsonServicio.obtenerNotasPorCarga")(id_carga);
	var notaslist = notas.list;
	
	if(notaslist.length>0){
		osm_setValor('lista_notas', '');
		osm_construirHTML('lista_notas', 'PLANTILLA_LISTA_NOTAS');
		
		for(var i=0; i<notaslist.length; i++){
			var  nota = notaslist[i];
			pintarNota('contenido_lista_notas', nota);
		}
		osm_setValor('existen_notas', 'true');
	}else{
		osm_setValor('existen_notas', 'false');
	}
}


function pintarNota(id_padre, nota) {

	var nombre_apellido = osm_trimToEmpty(nota.nombre) + ' ' + osm_trimToEmpty(nota.apellido);

	var estado = 'En Proceso';

	if (nota.estado == 'L') {
		estado = 'En liberaci&oacute;n';
	}
	
	if (nota.estado == 'V') {
		estado = 'En revisi&oacute;n';
	}
	
	var descripcion = jQuery.trim($("<div>").text(nota.nota).html());

	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_NOTA', [ nota.id_nota, nombre_apellido, nota.fechaString, descripcion, estado, nota.interno, nota.nombre_instancia ]);

	if (!osm_esVacio(nota.id_revision) && nota.estado == 'V') {
		osm_setValor('contenido_revision_nota_' + nota.id_nota, '');
		osm_construirHTML('contenido_revision_nota_' + nota.id_nota, 'PLANTILLA_ITEM_REVISION', [ nota.id_revision ]);
	}

}

//function pintarNota(id_padre, nota) {
//	
//	var nombre_apellido = nota.nombre+' '+nota.apellido
//	
//	var estado ='';
//	
//	if(nota.estado=='L'){
//		estado = 'En liberaci&oacute;n';
//	}else if(nota.estado=='V'){
//		estado = 'En revisi&oacute;n';
//	}
//	
//	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_NOTA',
//			[nota.id_nota,nombre_apellido,nota.fechaString,nota.nota,estado]);
//	
//	if(!osm_esVacio(nota.id_revision) && nota.estado=='V'){
//		osm_setValor('contenido_revision_nota_'+nota.id_nota, '');
//		osm_construirHTML('contenido_revision_nota_'+nota.id_nota, 'PLANTILLA_ITEM_REVISION',
//			[nota.id_revision]);
//	}
//	
//}

//function guardarNota(id_persona,id_carga,estado,revision){
function guardarNota(id_persona, id_carga, estado, revision, id_instancia) {
	
	var id_nota = jsonrpc._("notasJsonServicio.obtenerSiguiente")();
	var nota = osm_getValor('input_nota_guardar');
	nota = jQuery.trim(nota);
	if(osm_esVacio(nota)){
		alert('Debe ingresar algun valor');
		return;
	}
	
	var id_rev = jQuery.trim(revision);
	var id_revision;
	
	if(osm_esVacio(id_rev)){
		id_revision=null;
	}else{
		id_revision=parseInt(revision);
	}
	
	var interno = osm_getValor('interno_nota_guardar');
	//var conf = jsonrpc.notasJsonServicio.guardarNota(id_nota, nota, id_persona, id_carga, estado, id_revision, id_instancia, interno == "S");
    var conf = jsonrpc._("notasJsonServicio.guardarNota")(id_nota, nota, id_persona, id_carga, estado, id_revision, id_instancia, interno == "S");
	
	if(conf==true){
		var nota = jsonrpc._("notasJsonServicio.obtenerNota")(id_nota);
		
		if(osm_getValor('existen_notas')=='false'){
			osm_setValor('lista_notas', '');
			osm_construirHTML('lista_notas', 'PLANTILLA_LISTA_NOTAS');
		}
		
		pintarNota('contenido_lista_notas', nota);
		osm_setValor('existen_notas', 'true');
	}
	else{
		alert("Ocurrió un error, por favor contacte al administrador");
	}
	
	osm_setValor('input_nota_guardar','');
	
}


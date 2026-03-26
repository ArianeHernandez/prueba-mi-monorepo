function verificarArchivoFirmado(){
	
	var caja = osm_getValor('caja_archivo_firmado');
	caja = jQuery.trim(caja);
	if(osm_esVacio(caja)){
		alert('Debe seleccionar algun archivo');
		return;
	}
	
	var id_archivo_firmado = jsonrpc._("servFirmaJson.obtenerIdArchivo")();
	
	osm_setValor('id_archivo_firmado', id_archivo_firmado);
	
	var options = {
			success : fin
		};
	
	
	$('#form_verificar_firma_documento').ajaxSubmit(options);
	
}

//Mueve lo del formulario origen y lo pega en el formulario destino para ser enviado
function clonarFormularioYenviar(id_formulario_origen, id_formulario_destino){
	var formularioOrigen = osm_getObjeto(id_formulario_origen);
	var inputs = formularioOrigen.getElementsByTagName("input");
	
	var formularioDestino = osm_getObjeto(id_formulario_destino);
	
	//Se establece el mismo destino
	formularioDestino.action = formularioOrigen.action;
	
	for ( var ff = 0; ff < inputs.length; ff++) {

		var inputelement = inputs[ff];
		
		if(inputelement.type!='file'){
			formularioDestino.appendChild(inputelement);		
		}

	}
	
	formularioDestino.submit();
	
}

function fin(){
	
	var id_archivo_firmado = osm_getValor('id_archivo_firmado');
	var descripcionFase = osm_getValor('nombreFaseProceso');
	try{
		
		if(!osm_esVacio(descripcionFase)){
		
			//Se valida el archivo
			var esValido =	jsonrpc._("servFirmaJson.validarDocumentoFirmado")(id_archivo_firmado, null, descripcionFase );
			
			if(esValido){
				if(confirm("La validacion de la firma del documento a finalizado satisfactoriamente.\n\n\u00bfDesea subir el archivo?")){
					osm_block_window();
					var id_formulario_origen = osm_getValor("formularioOrigen");
					var id_formulario_destino = 'formularioDestino';
					
					//Cambiamos el id de archivo y el id del formulario si tiene
					osm_setValor("IDPXFRM", id_archivo_firmado);
					osm_setValor("IDF", osm_getValor("id_formato"));
					
					clonarFormularioYenviar(id_formulario_origen, id_formulario_destino);
					
				}
				
			}else{
				osm_alert("Error!!! El archivo presenta problemas con la firma digital. Porfavor seleccione el archivo correcto");
				return false;
			}
		}else{
			
			osm_alert("Error. No se puede realizar la validacion de la firma, se debe especificar la fase del proceso");
		}
	
	}catch (e) {
		alert(e);
	}
}


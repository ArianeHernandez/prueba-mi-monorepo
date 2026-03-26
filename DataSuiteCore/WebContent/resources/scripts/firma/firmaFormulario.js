var jrpc = null;

function crearInfoFormulario(id_formulario, inputIn, inputOut, id_carga, descripcionFase, rpc, funcRetorno) {

	var submit = (funcRetorno == undefined || funcRetorno == null) ? true : false;
	
	jrpc = (rpc == undefined || rpc == null) ? jsonrpc : rpc;

	var formulario = osm_getObjeto(id_formulario);
	var inputs = formulario.getElementsByTagName("input");
	var selects = formulario.getElementsByTagName("select");
	var textareas = formulario.getElementsByTagName("textarea");
	
	
	var formXML = "";
	formXML = "<INFO>";
	
	
	for (var j=0; j<textareas.length; j++) {
		var textareasElement = textareas[j];
		var vaElement = escape_simple(textareasElement.value);
		
		var etiqueta = textareasElement.name;
		
		//Se reemplazan caracteres especiales
		etiqueta = reemplazarCaracteresEspecialesEtiqueta(etiqueta);
		
		
		if (!osm_esVacio(etiqueta)) {
		
			var tag = "<" + etiqueta + " name=\"" + textareasElement.name + "\">" + vaElement + "</" + etiqueta + ">";
			
			formXML += tag;
			
		}
		
		
	};
	
	for (var i=0; i<selects.length; i++) {
		var selectElement = selects[i];
		var vaElement = escape_simple(selectElement.value);
		
		var etiqueta = selectElement.name;
		
		//Se reemplazan caracteres especiales
		etiqueta = reemplazarCaracteresEspecialesEtiqueta(etiqueta);
		
		
		if (!osm_esVacio(etiqueta)) {
		
			var tag = "<" + etiqueta + " name=\"" + selectElement.name + "\">" + vaElement + "</" + etiqueta + ">";
			
			formXML += tag;
			
		}
	};
	

	for ( var ff = 0; ff < inputs.length; ff++) {

		var inputelement = inputs[ff];
		//Obtiene el valor real del campo en las lista dinámicas
		var vaElement = escape_simple(getValorRealCampo(inputelement));
		
		if (inputelement.type != "checkbox" || inputelement.checked) {
			
			
			var etiqueta = inputelement.name;
			
			//Se reemplazan caracteres especiales
			etiqueta = reemplazarCaracteresEspecialesEtiqueta(etiqueta);
			
			if (!osm_esVacio(etiqueta)) {
			
				var tag = "<" + etiqueta + " name=\"" + inputelement.name + "\">" + vaElement + "</" + etiqueta + ">";
				
				formXML += tag;
			}
		}

	}
	
	formXML += "</INFO>";
	
	
	if(!osm_esVacio(formXML)){
		
		osm_block_window();
		osm_setValor(inputIn, formXML);
		
		var objIn = osm_getObjeto(inputIn);
		var objOut = osm_getObjeto(inputOut);
		
		if(objIn!=null && objOut!= null){
			
			//se envia el formulario
			var exitoso = executeSing(objIn, objOut);
			
			if (exitoso) {
				
				//Se verifica si hay un id_carga
				var carga = null;
				if(!osm_esVacio(id_carga)){
					carga = id_carga;
				}
				
				//Se verifica si el formulario es valido
				var id_p = jrpc.servFirmaJson.validarFormXMLSigned(objIn.value, objOut.value, carga, descripcionFase);
				
				if (id_p != null) {
					var ti = $("#" + id_formulario + " input[name=osm_ticket]").val();
					alert("El documento se ha firmado correctamente! ");
					
					if (submit){
						location.href = formulario.action + "?IDPXFRM=" + id_p + "&osm_ticket=" + ti;
					}else {
						funcRetorno();
					}
					
				}
				else {
					osm_unblock_window();
					alert('No se ha podido realizar el envío de la informacion.\n\n Certificado o clave incorrecta.');
				}
				
				
			}else{
				
				osm_alert("No se ha podido firmar el documento. Por favor intentelo nuevamente.");
				return false;
			}
		}else{
			osm_unblock_window();
			alert("Error alguno de los campos es null, In:"+objIn+"  Out:"+objOut);
			return false;
			
		}
		
		
		
	}else{
		
		osm_alert("No se puede enviar el formulario, intentelo nuevamente");
		
		return false;
	}
	
}

function esValidoForm(inputIn, inputOut){
	var objIn = osm_getObjeto(inputIn);
	var objOut = osm_getObjeto(inputOut);
		
	var esValido = jrpc.servFirmaJson.validarFormXMLSigned(objIn.value, objOut.value);
			
			
	alert(esValido);
	
}


function executeSing(inputIn, inputOut){
	try
	{
		ExecuteSignHTMLInputForm(inputIn, inputOut);
		return true;
	}
	catch (e)
	{
		var error_number = e.number;
					
		switch(error_number)
		{
			case -2138570191:
				alert ("No existe ningún certificado digital de firma registrado en el almacén de certificados de la máquina. Verifique que el dispositivo de almacenamiento del certificado digital (Token) se encuentra conectado a la máquina y que el certificado digital se encuentra registrado en el almacén de certificados del sistema operativo.");
				break; 
					
			case -2147024809:
				alert ("El usuario ha cancelado la digitación del PIN de acceso a la llave privada del certificado y no es posible crear la firma digital.");
				break; 
				
			case -2146893792:
				alert ("No es posible acceder a la llave privada del certificado digital seleccionado para el proceso de firma y no es posible crear la firma digital. Verifique que el dispositivo de almacenamiento del certificado digital (Token) se encuentra conectado a la máquina.");
				break; 
				
			case -2147023673:
				alert ("El usuario ha cancelado la digitación del PIN de acceso a la llave privada del certificado y no es posible crear la firma digital.");
				break; 
	
			case -2147024883:
				alert ("El usuario ha digitado de manera incorrecta la clave de acceso a la llave privada del certificado digital hasta bloquear el dispositivo. Es necesario verificar que el dispositivo de almacenamiento del certificado digital ha sido bloqueado. En caso de ser así debería proceder a revocar el certificado digital. ");
				break; 
						
			case -2138568446:
				alert ("El usuario ha cancelado el proceso de selección del certificado digital con el cual desea crear la firma digital. ");
				break; 
						
			case -2146435060:
				alert ("El usuario ha seleccionado para la firma un certificado digital que se encuentra registrado en el almacén de certificados de la máquina, cuyo dispositivo de almacenamiento correspondiente no se encuentra conectado a la máquina. " + "Verifique que el dispositivo de almacenamiento del certificado digital (Token) se encuentra conectado a la máquina.");
				break; 
						
			case -2146893802:
				alert ("No es posible acceder a la llave privada del certificado digital seleccionado para el proceso de firma y no es posible crear la firma digital. Verifique que el dispositivo de almacenamiento del certificado digital (Token) se encuentra conectado a la máquina. ");
				break; 
						
			case -2146827859:
				alert ("No se encuentra instalada en la máquina del usuario la DLL Capicom, empleada como proveedor criptográfico por la aplicación. Verifique que se encuentra instalada en su máquina la aplicación Certifirma Digital o que su máquina tiene acceso a Internet al sitio de Microsoft.");
				break; 
						
			case -2146881269:
				alert ("Se ha modificado el valor de la firma digital calculada. Se ha alterado la integridad de la firma digital y, por lo tanto, ha perdido validez.");
				break; 
						
			case -2146889724:
				alert ("Se ha modificado el valor de la firma digital calculada. Se ha alterado la integridad de la firma digital y, por lo tanto, ha perdido validez.");
				break; 
						
			case -2146881277:
				alert ("Se ha modificado el valor de la firma digital calculada. Se ha alterado la integridad de la firma digital y, por lo tanto, ha perdido validez.");
				break; 
						
			case -2146889721:
				alert ("Se ha modificado el valor de la firma digital calculada. Se ha alterado la integridad de la firma digital y, por lo tanto, ha perdido validez.");
				break; 
						
			case -2146881278:
				alert ("Se ha modificado el valor de la firma digital calculada. Se ha alterado la integridad de la firma digital y, por lo tanto, ha perdido validez.");
				break;
						
			default:
				alert("Se ha producido una excepción no controlada = " + e.message + ". " + e.number);		
		}

		return false;
	}

	
}

// ------------------------------------------------

function p_load() {
	// osm_setValor("inpuycsac","");
	// osm_setFoco("inpuycsac");
}

function reemplazarCaracteresEspecialesEtiqueta(etiqueta){
	etiqueta = osm_remplazar(etiqueta, ":", "_");
	etiqueta = osm_remplazar(etiqueta, ".", "_");
	etiqueta = osm_remplazar(etiqueta, "[", "_");
	etiqueta = osm_remplazar(etiqueta, "]", "_");
	etiqueta = osm_remplazar(etiqueta, "%", "_");
	etiqueta = osm_remplazar(etiqueta, ";", "_");
	
	return etiqueta;	
	
}


function escape_simple(t){
	
	var s = escape(t);
	
	s = osm_remplazar(s, "%20", " ");
	s = osm_remplazar(s, "%2C", ",");
	s = osm_remplazar(s, "%3A", ":");
	s = osm_remplazar(s, "%u2013", "-");
	
	return s;
}

function getValorRealCampo(obj){
	if(obj != null){
		if(obj.realvalue){
			return obj.realvalue;
		}else{
			return obj.value;
		}
	}

	return null;
}

osm_listen("load", window, p_load);

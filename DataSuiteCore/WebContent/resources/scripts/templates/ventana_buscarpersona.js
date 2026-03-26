$(obtenerTiposDocumento);

function obtenerTiposDocumento(){
	
	var lista = jsonrpc._("personaServicio.obtenerTiposDocumento")().list;
	var objeto = osm_getObjeto("tipo_documento");
	
	for ( var i = 0; i < lista.length; i++) {
		if( lista[i].id != 2){ //Solo Supersociedades. Sin TI
			objeto.options[objeto.options.length] = new Option(lista[i].nombre, lista[i].id);
		}
	}
	
}

function adjuntarEventosValidacion() {
    var tipoDocumentoSelect = document.getElementById('tipo_documento');
    var identificacionInput = document.getElementById('identificacion');

    if (tipoDocumentoSelect) {
        tipoDocumentoSelect.removeEventListener('change', identificacionChange); 
        tipoDocumentoSelect.addEventListener('change', identificacionChange);
    } else {
        console.warn("Elemento 'tipo_documento' no encontrado para adjuntar evento.");
    }

    if (identificacionInput) {
        identificacionInput.removeEventListener('input', identificacionChange); 
        identificacionInput.addEventListener('input', identificacionChange);

        identificacionInput.removeEventListener('change', identificacionChange);
        identificacionInput.addEventListener('change', identificacionChange);
    } else {
        console.warn("Elemento 'identificacion' no encontrado para adjuntar evento.");
    }
}

function identificacionChange() {
    var tipo_documento = osm_getValor("tipo_documento"); 
    var num_documento = osm_getValor("identificacion");
    var element = document.getElementById('mensajeidentificacion');
    
    if (!element) {
        console.error("Elemento con ID 'mensajeidentificacion' no encontrado");
        return;
    }

    // Expresiones regulares
    var soloNumeros = /^[0-9]+$/;
    var alfanumerico = /^[a-zA-Z0-9]+$/;
    var msj = "";

    // Validación según tipo de documento
    if (!num_documento || num_documento.trim() === "") {
        msj = "El número de identificación es obligatorio";
    } else {
        switch(tipo_documento) {
            case '1': // Cédula de Ciudadanía
                if (!soloNumeros.test(num_documento) || num_documento.length < 3 || num_documento.length > 11) {
                    msj = "El número de documento debe ser numérico y tener entre 3 y 11 digítos";
                }
                break;
                
            case '3': // Cédula de Extranjería
            case '4': // Pasaporte
                if (!alfanumerico.test(num_documento) || num_documento.length < 3 || num_documento.length > 20) {
                    msj = "El número de documento debe ser alfanumérico y tener entre 3 y 20 caracteres";
                }
                break;
                
            case '10': // NIT
                if (!soloNumeros.test(num_documento)) {
                    msj = "El NIT debe ser numérico";
                } else if (num_documento.length !== 9) {
                    msj = "El NIT debe tener exactamente 9 dígitos";
                } else {
                    var nitNumero = parseInt(num_documento, 10);
                    if (nitNumero < 800000000 || nitNumero > 999999999) {
                        msj = "El NIT debe estar entre 800000000 y 999999999";
                    }
                }
                break;
                
            default:
                msj = "Tipo de documento no válido";
        }
    }

    // Mostrar u ocultar mensaje
    if (msj) {
        element.textContent = msj;
        element.classList.add('text-danger');
        element.style.display = 'block';
    } else {
        element.style.display = 'none';
    }
}


function validar_buscarpersona() {
    var tipo_persona = osm_getValor('tipo_persona');
    var tipo_documento = osm_getValor('tipo_documento');
    var num_documento = osm_getValor('identificacion');
    
    // Validación básica de campos obligatorios
    if ((osm_getObjeto('tipo_persona') != null && osm_esVacio(tipo_persona)) || osm_esVacio(num_documento)) {
        alert("Por favor complete la información!");
        return false;
    }

    // Expresiones regulares para validación
    var soloNumeros = /^[0-9]+$/;
    var alfanumerico = /^[a-zA-Z0-9]+$/;

    // Validación específica según tipo de documento
    switch(tipo_documento) {
        case '1': // Cédula de Ciudadanía
            if (!soloNumeros.test(num_documento) || num_documento.length < 3 || num_documento.length > 11) {
                alert("El número de documento debe ser numérico y tener entre 3 y 11 digítos");
                return false;
            }
            break;
            
        case '3': // Cédula de Extranjería
        case '4': // Pasaporte
            if (!alfanumerico.test(num_documento) || num_documento.length < 3 || num_documento.length > 20) {
                alert("El número de documento debe ser alfanumérico y tener entre 3 y 20 caracteres");
                return false;
            }
            break;
            
        case '10': // NIT
            if (!soloNumeros.test(num_documento)) {
                alert("El NIT debe ser numérico");
                return false;
            }
            if (num_documento.length !== 9) {
                alert("El NIT debe tener exactamente 9 dígitos");
                return false;
            }
            var nitNumero = parseInt(num_documento, 10);
            if (nitNumero < 800000000 || nitNumero > 999999999) {
                alert("El NIT debe estar entre 800000000 y 999999999");
                return false;
            }
            break;
            
        default:
            alert("Tipo de documento no válido");
            return false;
    }

    return true;
}
function cancelar_buscarpersona(){
	osm_setVisible('vn_buscarpersona', false);
	osm_mostrarSelects("bodyContent");
}
function mostrar_buscarpersona(){
	osm_setVisible('vn_buscarpersona', true, true);
	osm_ocultarSelects("bodyContent");
	
	setTimeout(function() {
	      adjuntarEventosValidacion();
	      identificacionChange(); 
	 }, 100);
}

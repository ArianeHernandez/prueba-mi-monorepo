$(function() {
    consultarDepartamentos();
    precargarDepartamentoYMunicipioInicial();
	precargarTipoRevisor()
	aplicarEstilosACampos();
    if (window.jQuery.fn.colorpicker) {
        $('.colorFondo').colorpicker({
            container: "#colorFondoChooser",
            inline: true
        });

        $('.colorLetra').colorpicker({
            container: "#colorLetraChooser",
            inline: true
        });
    }

    $('#Persona\\.nombre').on('input', personanombreChange);
    $('#Persona\\.apellido').on('input', personaapellidoChange);
    $('#Persona\\.telefono').on('input', personatelefonoChange);
    $('#Persona\\.correo').on('input', personacorreoChange);
    $('#Persona\\.confirmar_correo').on('input', personacorreoChange);
    $('#Persona\\.direccion').on('input', personadireccionChange);    

    $('#deudor_departamento').on('change', function() {
        guardarCodigoLugar(); 
        personaDepartamentoChange(); 

		const $ciudadSelect = $('#deudor_ciudad');
		const $autocompleteInput = $('#deudor_ciudad_autocomplete');
		
		if ($autocompleteInput.length > 0) {
		    $autocompleteInput.remove();
		    
		    $ciudadSelect.show(); 
		    $ciudadSelect.data("autocomplete-init", false);
		    
		    osm_setValor("deudor_ciudad_autocomplete", "");
		}

		const deptoId = osm_getValor("deudor_departamento");
		const deptoSelect = document.getElementById('deudor_departamento');
		const scope = angular.element(deptoSelect).scope();
		if(scope && scope.consultarCiudades){
		    scope.$apply(function() {
		        scope.consultarCiudades();
		    });
		} else {
		    consultarCiudades();
		}
		
		aplicarEstilosACampos();
    });

    $('#deudor_ciudad').on('change', function() {
        guardarCodigoLugar(); 
        personaMunicipioChange(); 
    });

    $('#Credencial\\.login').on('input', function() {
        verificarLogin(this);
    });

});

function aplicarEstilosACampos() {
    $("#deudor_departamento_autocomplete").removeClass("input-default").addClass("form-control");
    $("#deudor_ciudad_autocomplete").removeClass("input-default").addClass("form-control");
}

function verificarLogin(obj) {
    var element = document.getElementById('mensajelogin');

    if (!element) {
        console.error("Elemento con ID 'mensajelogin' no encontrado. Asegúrate de que esté en el HTML.");
        return;
    }

    element.textContent = "";
    element.style.display = 'none';
    element.classList.remove('text-danger');
    obj.value = osm_strlimpiar(obj.value);
    obj.value = obj.value.toLowerCase();
    var nombre = obj.value;

    if (!osm_regexTest(nombre, /^[\w\.]+$/)) {
        mostrarMensajeCampo('mensajelogin', 'El login debe ser alfanumérico y solo se permiten los siguientes caracteres especiales: puntos (.) o guiones bajos (_).');
        osm_setFoco(obj.id);
        return;
    }

    if (nombre.length < 5 || nombre.length > 50) {
        mostrarMensajeCampo('mensajelogin', 'El login debe tener entre 5 y 50 caracteres.');
        osm_setFoco(obj.id);
        return;
    }
    
    var rta = jsonrpc._("personaServicio.loginDisponible")(nombre);
    if (rta == 'N') {
        mostrarMensajeCampo('mensajelogin', 'El login ya está en uso.');
        osm_setFoco(obj.id);
        return;
    }
    
    mostrarMensajeCampo('mensajelogin', null);
}

function personanombreChange() {
    console.log("entra change");
    var nombres = osm_getValor("Persona.nombre");
    console.log("nombres", nombres);
    var caracteresValidos = /^[a-zA-ZÁÉÍÓÚáéíóúÑñ0-9 ]+$/;
    var element = document.getElementById('mensajepersonanombre');
    console.log("element", element);

    if (!element) {
        console.error("Elemento con ID 'mensajepersonanombre' no encontrado");
        return;
    }

    if (!nombres || nombres.trim() === "") {
        mostrarMensajeCampo('mensajepersonanombre', "El nombre no puede estar vacío.");
    } else if (!caracteresValidos.test(nombres) || nombres.length < 3 || nombres.length > 120) {
        mostrarMensajeCampo('mensajepersonanombre', "El nombre debe ser alfanumérico y tener entre 3 y 120 caracteres.");
    } else {
        mostrarMensajeCampo('mensajepersonanombre', null); 
    }
}

function personaapellidoChange() {
    console.log("entra change");
    var apellidos = osm_getValor("Persona.apellido");
    console.log("apellidos", apellidos);
    var caracteresValidos = /^[a-zA-ZÁÉÍÓÚáéíóúÑñ0-9 ]+$/;
    var element = document.getElementById('mensajepersonaapellido');
    console.log("element", element);

    if (!element) {
        console.error("Elemento con ID 'mensajepersonanombre' no encontrado"); 
        return;
    }

    if (!apellidos || apellidos.trim() === "") {
        mostrarMensajeCampo('mensajepersonaapellido', "El apellido no puede estar vacío.");
    } else if (!caracteresValidos.test(apellidos) || apellidos.length < 3 || apellidos.length > 120) {
        mostrarMensajeCampo('mensajepersonaapellido', "El apellido debe ser alfanumérico y tener entre 3 y 120 caracteres.");
    } else {
        mostrarMensajeCampo('mensajepersonaapellido', null); 
    }

}

function personatelefonoChange() {
    console.log("entra change telefono");
    var telefono = osm_getValor("Persona.telefono");
    console.log("telefono", telefono);
    var element = document.getElementById('mensajepersonatelefono');
    console.log("element", element);

    if (!element) {
        console.error("Elemento con ID 'mensajepersonatelefono' no encontrado");
        return;
    }

    var caracteresValidos = /^[0-9]+$/;

    if (!telefono || telefono.trim() === "") {
        mostrarMensajeCampo('mensajepersonatelefono', "El teléfono celular es obligatorio.");
    } else if (!caracteresValidos.test(telefono)) {
        mostrarMensajeCampo('mensajepersonatelefono', "El teléfono debe ser numérico (sin espacios, guiones u otros caracteres).");
    } else if (telefono.length < 7 || telefono.length > 10) {
        mostrarMensajeCampo('mensajepersonatelefono', "El teléfono debe tener entre 7 y 10 dígitos.");
    } else {
        mostrarMensajeCampo('mensajepersonatelefono', null); 
    }
}

function personacorreoChange() {
    console.log("entra change correo");
    var correo = osm_getValor('Persona.correo');
    var confirmarCorreo = osm_getValor('Persona.confirmar_correo');

    if (!osm_validarCorreo(correo)) {
        mostrarMensajeCampo('mensajepersonacorreo', "El correo electrónico contiene caracteres no válidos.");
    } else if (!osm_validarCorreo(confirmarCorreo) || correo !== confirmarCorreo) {
        mostrarMensajeCampo('mensajepersonacorreo', "Los correos electrónicos no coinciden.");
    } else {
        mostrarMensajeCampo('mensajepersonacorreo', null); 
    }

}

function personadireccionChange() {
    console.log("entra change dirección");
    var direccion = osm_getValor('Persona.direccion');
    var element = document.getElementById('mensajepersonadireccion');

    if (!element) {
        console.error("Elemento con ID 'mensajepersonadireccion' no encontrado");
        return;
    }

    var caracteresValidos = /^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\s\.,#\-]+$/;

    if (!direccion || direccion.trim() === "") {
        mostrarMensajeCampo('mensajepersonadireccion', "La dirección es obligatoria.");
    } else if (direccion.length < 6 || direccion.length > 255) {
        mostrarMensajeCampo('mensajepersonadireccion', "La dirección debe tener entre 6 y 255 caracteres.");
    } else if (!caracteresValidos.test(direccion)) {
        mostrarMensajeCampo('mensajepersonadireccion', "La dirección contiene caracteres no válidos.");
    } else {
        mostrarMensajeCampo('mensajepersonadireccion', null); 
    }
}

function personaDepartamentoChange() {
    var departamentoId = osm_getValor('Persona.departamento_id');
    console.log("Validando departamento. Valor de Persona.departamento_id:", departamentoId); 
    if (osm_esVacio(departamentoId)) {
        mostrarMensajeCampo('mensajepersonadepartamento', "El Departamento es obligatorio.");
    } else {
        mostrarMensajeCampo('mensajepersonadepartamento', null);
    }

}

function personaMunicipioChange() {
    var departamentoId = osm_getValor('Persona.municipio_id');
    console.log("Validando departamento. Valor de Persona.municipio_id:", departamentoId); 
    if (osm_esVacio(departamentoId)) {
        mostrarMensajeCampo('mensajepersonadepartamento', "El Municipio es obligatorio.");
    } else {
        mostrarMensajeCampo('mensajepersonamunicipio', null);
    }
}

document.addEventListener('DOMContentLoaded', () => {
	    const tipoDocumentoId = osm_getValor('Persona.tipo_documento');
	    const deptoId = osm_getValor('Persona.departamento_id');
	    const municipioId = osm_getValor('Persona.municipio_id');


	    const deptoAuto = document.getElementById('deudor_departamento_autocomplete');
	    const ciudadAuto = document.getElementById('deudor_ciudad_autocomplete');

	    if (!(tipoDocumentoId === '1' || tipoDocumentoId === '10')) {
	        if (deptoAuto) deptoAuto.style.display = 'none';
	        if (ciudadAuto) ciudadAuto.style.display = 'none';
	    } else {
	        if (deptoAuto && deptoId && deptoId !== '' && deptoId !== '15224090') {
	            deptoAuto.style.display = 'none';
	        }
	        if (ciudadAuto && municipioId && municipioId !== '' && municipioId !== '15224091') {
	            ciudadAuto.style.display = 'none';
	        }
	    }
	});

//function guardarCodigoLugar() {
//    const selectedDeptoId = osm_getValor("deudor_departamento");
//    osm_setValor("Persona.departamento_id", selectedDeptoId);
//
//    const selectedCiudadId = osm_getValor("deudor_ciudad");
//    osm_setValor("Persona.municipio_id", selectedCiudadId);
//}

function guardarCodigoLugar() {
    const tipoDocumentoId = osm_getValor('Persona.tipo_documento');

    if (tipoDocumentoId === '1' || tipoDocumentoId === '10') {
        const selectedDeptoId = osm_getValor("deudor_departamento");
        osm_setValor("Persona.departamento_id", selectedDeptoId);

        const selectedCiudadId = osm_getValor("deudor_ciudad");
        osm_setValor("Persona.municipio_id", selectedCiudadId);
    } else {
        osm_setValor("Persona.departamento_id", "15224090");
        osm_setValor("Persona.municipio_id", "15224091");
    }
}

function precargarDepartamentoYMunicipioInicial() {
    const deptoId = osm_getValor('Persona.departamento_id');
    const municipioId = osm_getValor('Persona.municipio_id');
    const tipoDocumentoId = osm_getValor('Persona.tipo_documento');
    const deptoSelect = document.getElementById('deudor_departamento');
    const ciudadSelect = document.getElementById('deudor_ciudad');

    if (tipoDocumentoId === '1' || tipoDocumentoId === '10') {
        if (deptoSelect && deptoId && deptoId !== '15224090') {
            deptoSelect.style.display = "block";

            osm_setValor("deudor_departamento", deptoId);

            consultarCiudades();

            setTimeout(function intentarAsignarMunicipio(intentos = 0) {
                if (intentos > 20) {
                    console.warn("ADVERTENCIA: Se superó el número de reintentos para asignar el municipio.");
                    return;
                }

                if (ciudadSelect && ciudadSelect.querySelector(`option[value='${municipioId}']`)) {
                    ciudadSelect.style.display = "block";
                    ciudadSelect.disabled = false;
                    osm_setValor("deudor_ciudad", municipioId);
                    angular.element(ciudadSelect).triggerHandler('change');
                } else {
                    console.log(`INFO: Municipio ID ${municipioId} no encontrado. Reintentando... Intento ${intentos + 1}`);
                    setTimeout(() => intentarAsignarMunicipio(intentos + 1), 100);
                }
            }, 800);
        } else {
            console.warn("ADVERTENCIA - No se cargó el departamento (vacío o '15224090') o no se encontró el select");
        }
    } else {
        if (deptoSelect) deptoSelect.style.display = "none";
        if (ciudadSelect) ciudadSelect.style.display = "none";
        osm_setValor("Persona.departamento_id", "15224090");
        osm_setValor("Persona.municipio_id", "15224091");
    }
}

function mostrarMensajeCampo(elementId, mensaje) {
    const element = document.getElementById(elementId);

    if (!element) {
        console.error(`Elemento con ID '${elementId}' no encontrado para mostrar mensaje.`);
        return;
    }

    let inputId = '';
    if (elementId.startsWith('mensaje')) {
        let baseId = elementId.substring(7);
        if (baseId === 'login') {
            inputId = 'Credencial.login';
        } else if (baseId === 'tiporevisor') { 
            inputId = 'id_tipo_revisor';
        } else if (baseId === 'personadepartamento') { 
            inputId = 'Persona.deudor_departamento';
        } else if (baseId === 'personamunicipio') { 
            inputId = 'Persona.deudor_ciudad';
        } else {
            const fieldName = baseId.replace('persona', '');
            inputId = 'Persona.' + fieldName;
        }

    }

    const inputElement = document.getElementById(inputId);

    if (mensaje) {
        element.textContent = mensaje;
        element.classList.add('text-danger');
        element.style.display = 'block';

        if (inputElement) {
            inputElement.classList.add('ERR_VALIDAR'); 
        }

    } else {
        element.textContent = "";
        element.classList.remove('text-danger');
        element.style.display = 'none';

        if (inputElement) {
            inputElement.classList.remove('ERR_VALIDAR'); 
        }
    }
}

function precargarTipoRevisor() {
    var idPersona = osm_getValor('Persona.id_persona');
    var nombreRol = osm_getValor('nombre_rol'); 

    console.log(`DEBUG [precargarTipoRevisor]: Iniciado. Rol actual: '${nombreRol}', idPersona: '${idPersona}'`);

    if (nombreRol === 'Revisor') {
        try {
            if (idPersona) {
                var idTipoRevisorAsignado = jsonrpc._("personaServicio.obtenerIdTipoRevisorPorPersona")(idPersona);
                
                setTimeout(() => {
                    if (idTipoRevisorAsignado !== null && idTipoRevisorAsignado !== undefined && idTipoRevisorAsignado !== '') {
                        const selectorTipoRevisor = document.getElementById('id_tipo_revisor');
                        if (selectorTipoRevisor) {
                            osm_setValor('id_tipo_revisor', idTipoRevisorAsignado);
                            
                            if (selectorTipoRevisor.value === idTipoRevisorAsignado) {
                                console.log(`DEBUG [precargarTipoRevisor]: Tipo Revisor precargado exitosamente: '${idTipoRevisorAsignado}'.`);
                            } else {
                                console.warn(`ADVERTENCIA [precargarTipoRevisor]: El ID de Tipo Revisor '${idTipoRevisorAsignado}' de la DB NO PUDO SER SELECCIONADO en el dropdown. La opción puede no existir o haber un problema con osm_setValor.`);
                            }
                        } else {
                            console.error("ERROR [precargarTipoRevisor]: Selector 'id_tipo_revisor' no encontrado en el DOM.");
                        }
                    } else {
                        console.log("INFO [precargarTipoRevisor]: No se encontró Tipo Revisor asignado para la persona o el ID es inválido/vacío, dejando el selector en '-- Seleccione --'.");
                        osm_setValor('id_tipo_revisor', ''); 
                    }
                }, 100); 
            } else {
                console.warn("ADVERTENCIA [precargarTipoRevisor]: id_persona no disponible para precargar Tipo Revisor.");
            }
        } catch (e) {
            console.error("ERROR [precargarTipoRevisor]: Fallo al llamar al servicio 'obtenerIdTipoRevisorPorPersona':", e);
        }
    } else {
        console.log(`DEBUG [precargarTipoRevisor]: El rol no es 'Revisor' ('${nombreRol}'), no se precarga el Tipo Revisor.`);
        osm_setValor('id_tipo_revisor', ''); 
    }
}

function ocultarTodosLosMensajes() {
    const mensajeDivs = [
        'mensajepersonanombre',
        'mensajepersonaapellido',
        'mensajepersonatelefono',
        'mensajepersonacorreo',
        'mensajepersonadireccion',
        'mensajelogin',
        'mensajepersonadepartamento', 
        'mensajepersonamunicipio',
		"mensajetiporevisor"   
    ];

    mensajeDivs.forEach(id => {
        mostrarMensajeCampo(id, null);
    });
}

function validarPersona() {
    ocultarTodosLosMensajes(); 
    guardarCodigoLugar();
    var hayErrores = false;
	const tipoDocumentoId = osm_getValor('Persona.tipo_documento');
	const esDepartamentoMunicipioObligatorio = (tipoDocumentoId === '1' || tipoDocumentoId === '10');
	
    // Validación de Nombre
    if (osm_esVacio(osm_getValor('Persona.nombre'))) {
        mostrarMensajeCampo('mensajepersonanombre', "Campo Nombres: Es obligatorio.");
        hayErrores = true;

    } else {
        var nombre = osm_getValor('Persona.nombre');
        var regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s\d]{3,120}$/;
        if (!regex.test(nombre)) {
            mostrarMensajeCampo('mensajepersonanombre', "Campo Nombres: Debe ser alfanumérico (letras, números y espacios) y tener entre 3 y 120 caracteres.");
            hayErrores = true;
        }
    }

    // Validación de Tipo de Persona 
    if (osm_esVacio(osm_getValor('Persona.tipo_persona'))) {     
        console.warn("Tipo de persona no seleccionado. Si es obligatorio, agregar elemento de mensaje.");
    }

    var persona_natural = osm_getValor('Persona.tipo_persona') == 'N';

    // Validación de Apellido
    if (persona_natural && osm_esVacio(osm_getValor('Persona.apellido'))) {
        mostrarMensajeCampo("mensajepersonaapellido", "Campo Apellido: Es obligatorio.");
        hayErrores = true;
    } else if (persona_natural) {
        var apellido = osm_getValor('Persona.apellido');
        var regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s]{3,120}$/;
        if (!regex.test(apellido)) {
            mostrarMensajeCampo("mensajepersonaapellido", "Campo Apellido: Debe contener solo letras y espacios, con una longitud de 3 a 120 caracteres.");
            hayErrores = true;
        }
    }

    // Validación de Teléfono
    if (osm_esVacio(osm_getValor('Persona.telefono'))) {
        mostrarMensajeCampo("mensajepersonatelefono", "Campo Teléfono Celular: Es obligatorio.");
        hayErrores = true;
    } else if (!/^\d{7,10}$/.test(osm_getValor('Persona.telefono'))) {
        mostrarMensajeCampo("mensajepersonatelefono", "Campo Teléfono Celular: Debe ser numérico (7 a 10 dígitos).");
        hayErrores = true;
    }

    // Validación de Identificación 
    if (osm_esVacio(osm_getValor('Persona.identificacion'))) {
        console.warn("Identificación no válida. Si es obligatorio, agregar elemento de mensaje.");
        hayErrores = true; 
    }

    // Validación de Login
    if (osm_esVacio(osm_getValor('Credencial.login'))) {
        mostrarMensajeCampo("mensajelogin", "El login no puede ser vacío.");
        hayErrores = true;
    }

    // Validación de Correo
    if (!osm_validarCorreo(osm_getValor('Persona.correo'))) {
        mostrarMensajeCampo("mensajepersonacorreo", "El correo no es válido.");
        hayErrores = true;
    } else if (osm_getValor('Persona.correo') !== osm_getValor('Persona.confirmar_correo')) {
        mostrarMensajeCampo("mensajepersonacorreo", "El campo confirmar correo no coincide con el campo correo.");
        hayErrores = true;
    }

    // Validación de Dirección
    if (osm_esVacio(osm_getValor('Persona.direccion'))) {
        mostrarMensajeCampo("mensajepersonadireccion", "Campo Dirección: Es obligatorio.");
        hayErrores = true;
    } else {
        var direccion = osm_getValor('Persona.direccion');
        var regex = /^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\s\.,#\-()\/]{6,255}$/;
        if (!regex.test(direccion)) {
            mostrarMensajeCampo("mensajepersonadireccion", "Campo Dirección: Debe ser alfanumérico (letras, números, espacios y algunos símbolos como . , - # /).");
            hayErrores = true;
        } else if (direccion.length < 6 || direccion.length > 255) {
            mostrarMensajeCampo("mensajepersonadireccion", "Campo Dirección: Debe tener entre 6 y 255 caracteres.");
            hayErrores = true;
        }
    }

    // Validación de Departamento
    if (esDepartamentoMunicipioObligatorio && osm_esVacio(osm_getValor('Persona.departamento_id'))) {
	    mostrarMensajeCampo('mensajepersonadepartamento', "El Departamento es obligatorio.");
	    hayErrores = true;
	}

	// Validación de Municipio CONDICIONAL
	if (esDepartamentoMunicipioObligatorio && osm_esVacio(osm_getValor('Persona.municipio_id'))) {
	    mostrarMensajeCampo('mensajepersonamunicipio', "El Municipio es obligatorio.");
	    hayErrores = true;
	}

    // --- Para Liberador 
    if ($("input.tipo_proceso").length > 0) {
        var numeroTipoProceso = $("input.tipo_proceso").length;
        var tipoproceso = $("input.tipo_proceso[value ='']").length;
        if (tipoproceso == numeroTipoProceso) { 
            console.warn("Se debe seleccionar el Tipo de " + osm_getValor("nombre_rol") + ". Si es obligatorio, agregar elemento de mensaje.");
            hayErrores = true;
        }
    }

    $(".liberadorTipoProceso_valor_max_individual").each(function() {
        var id_obj = $(this).attr("id");
        var id_tipo_proceso = osm_remplazar(id_obj, "ListadoTipoProcesoLiberador:", "");
        id_tipo_proceso = osm_remplazar(id_tipo_proceso, ".valor_max_individual", "");
        if (!osm_esVacio(osm_getValor("TipoProcesoRol:" + id_tipo_proceso + ".id_tipo_proceso"))) {
            var val = $(this).val();
            if (!osm_esVacio(val) && !osm_validarNumero(val)) {
                console.warn("El campo Valor máximo individual debe ser un número entero.");
                hayErrores = true;
            }
        }
    });

    $(".liberadorTipoProceso_valor_max_carga").each(function() {
        var id_obj = $(this).attr("id");
        var id_tipo_proceso = osm_remplazar(id_obj, "ListadoTipoProcesoLiberador:", "");
        id_tipo_proceso = osm_remplazar(id_tipo_proceso, ".valor_max_carga", "");
        if (!osm_esVacio(osm_getValor("TipoProcesoRol:" + id_tipo_proceso + ".id_tipo_proceso"))) {
            var val = $(this).val();
            if (!osm_esVacio(val) && !osm_validarNumero(val)) {
                console.warn("El campo Valor máximo de la carga debe ser un número entero.");
                hayErrores = true;
            }
        }
    });

	if (osm_getValor('nombre_rol') === 'Revisor') {
	    console.log("DEBUG: Validando Tipo Revisor. Valor actual: ", osm_getValor('id_tipo_revisor'));
	    if (osm_esVacio(osm_getValor('id_tipo_revisor'))) {
	        mostrarMensajeCampo('mensajetiporevisor', "El Tipo Revisor es obligatorio.");
	        hayErrores = true;
	    }
	} else {
	    console.log("DEBUG: El rol no es 'Revisor', no se valida el campo Tipo Revisor.");
	}

    // --- Fin para liberador ---
    console.log("--- Datos que se enviarán en el formulario ---");
    var formData = {};
    // Recorre todos los inputs, selects y textareas del formulario
    $('form :input').each(function() {
        var input = $(this);
        var name = input.attr('name');
        var value = input.val();
        if (name) { 
            formData[name] = value;
            console.log(name + ": " + value);
        }
    });

    console.log("--- Fin del log de datos del formulario ---");

    // --- FIN: NUEVO LOGGING ---
    if (hayErrores) {
        alert("Error al Validar Datos.\n\nExisten datos sin diligenciar o inválidos. Por favor, verifique los campos resaltados.");
        return false;
    }
    return true;

}
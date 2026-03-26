function consultarDepartamentos () {

	var departamentos = jsonrpc._("solicitudEnrolamiento.obtenerDepartamentosDane")();
	if (typeof(departamentos) == "undefined"){
		return;
	}
	if (!departamentos.list)
		return;
	departamentos.list.sort(compare);
	departamentos.list.forEach(function (i) {
		$("#deudor_departamento").append(
			$("<option>", {
				value: i.id,
				text: i.nombre,
			}));
	});
	$("#deudor_departamento")
		.each(
			function (i) {
				var input = selectToAutoComplete(this);
			});

	$("#deudor_departamento_autocomplete")
		.removeClass();
	$("#deudor_departamento_autocomplete")
		.addClass(
			"input-default form-input");

	$(".ui-autocomplete").css("height",
		"10em");
	$(".ui-autocomplete").css("overflow",
		"scroll");
}



function consultarCiudades() {
    try {
        const sel = document.getElementById("deudor_ciudad");
        const scope = angular.element(sel).scope();

        if (scope) {
            scope.$applyAsync(() => {
                scope.solicitud = scope.solicitud || {};
                scope.solicitud.municipio_dane = "";
            });
        }

        const $sel = $(sel);
        $sel.empty();
        $sel.append($("<option>", { value: "", text: "-- Seleccione --" }));

        const departamento_sel = osm_getValor("deudor_departamento");
        const ciudades = jsonrpc._("solicitudEnrolamiento.obtenerMunicipiosDanePorDepartamento")(departamento_sel);

        if (ciudades && ciudades.list && ciudades.list.length > 0) {
            ciudades.list.sort(compare);
            ciudades.list.forEach(i => {
                $sel.append($("<option>", { value: String(i.id), text: i.nombre }));
            });

            $("#deudor_ciudad, #deudor_ciudad_autocomplete").prop("disabled", false);

            if (!$sel.data("autocomplete-init")) {
                $sel.each(function () {
                    selectToAutoComplete(this);
                });
                $sel.data("autocomplete-init", true);
            }

            $("#deudor_ciudad_autocomplete").removeClass().addClass("input-default form-input");
            $(".ui-autocomplete").css({ height: "10em", overflow: "scroll" });
        } else {
            $("#deudor_ciudad_autocomplete").prop("disabled", true);
            osm_setValor("deudor_ciudad_autocomplete", "");
        }

        $sel.val("").trigger("change");
        angular.element(sel).triggerHandler('change');

    } catch (e) {
		console.log("Error en consultaciudades ", e)
	}

}


function hideAppendYesOrNo(append, condition, switchopt = 0) {
	if (1 < condition) {
		console.error("This funtion isn't thinking for use more than two options for this porposuse, please use another that support your requeriment or review and correct the parameter");
		return;
	}
	if (switchopt == 0) {
		if (condition == 1) {
			$("#contenedor_" + append).show("slow");
		} else {
			$("#contenedor_" + append).hide("slow");
		}
	} else {
		if (condition == 1) {
			$("#contenedor_yes_" + append).show("slow");
			$("#contenedor_not_" + append).hide("slow");
		} else {
			$("#contenedor_yes_" + append).hide("slow");
			$("#contenedor_not_" + append).show("slow");
		}
	}

}

function setButtonOptions(i, domId, switchopt = 0) {
	hideAppendYesOrNo(domId, 0);

	$(
		"#" + domId + "_div")
		.append(
			$(
				"<div>",
				{
					class: "l-button s-buttonenable-primary "
						+ i.nombre,
					onclick: "setButtonValue('" + domId + "',"
						+ i.id
						+ ", '"
						+ i.nombre
						+ "');"
						+ "hideAppendYesOrNo("
						+ "'" + domId + "',"
						+ i.id + ","
						+ switchopt
						+ ");",
					text: i.nombre,
				}));
}

function manageDomForSelectorFile(pattenrName, finder = 'caja_archivo_adjunto_form') {
	setExtensionesAdjuntos(pattenrName, finder);
	$("#" + pattenrName).find("#" + finder)
		.change(
			function (e) {
				try{
					var file = e.target.files[0];
					if (!file) {
						removeFile(pattenrName);
						return;
					}
					var esValido= validarExtensionesSoliInicial(pattenrName, finder, file); 
					if (esValido) {
						if (file.size <= 104857600) {
							var fileName = file.name;
							osm_setValor(
								"label_" + pattenrName,
								fileName);
							$("#trash_" + pattenrName)
								.removeClass("ic-hide");
						} else {
							alert("El archivo seleccionado supera el tama\u00f1o permitido (100 MB)");
							removeFile(pattenrName);
						}				
					} else {
						removeFile(pattenrName);
					}
					
					
				}catch(e){
					console.error(e, pattenrName);
				}
				
			});
}

function setExtensionesAdjuntos (pattenrName, finder) {
	console.log("pattenrName " + pattenrName)
	var tipoArchivoInput = document.querySelector(`#${pattenrName} #id_tipo_archivo_form`);
    var tipoArchivoValor = tipoArchivoInput ? tipoArchivoInput.value : null;
	console.log("tipoArchivoValor: " + tipoArchivoValor); 
	var extensiones = jsonrpc._("tipoArchivoServicio.obtenerExtensionesPorTipoArchivo")(parseInt(tipoArchivoValor));
	$("#" + pattenrName).find("#" + finder).attr("accept", extensiones.list[0]?.replace(/(\w+)/g, ".$1").toLowerCase());
}

function validarExtensionesSoliInicial (pattenrName, finder, file) {
	
	console.log("pattenrName " + pattenrName)
	var tipoArchivoInput = document.querySelector(`#${pattenrName} #id_tipo_archivo_form`);
    var tipoArchivoValor = tipoArchivoInput ? tipoArchivoInput.value : null;
	console.log("tipoArchivoValor: " + tipoArchivoValor); 
	var extensiones = jsonrpc._("tipoArchivoServicio.obtenerExtensionesPorTipoArchivo")(parseInt(tipoArchivoValor));

	if(extensiones.list && extensiones.list.length > 0){
		
		var valid = false;
		
		var filename = file.name.split(/\./g);
		var extension = filename.length > 1 ? filename.pop(): "";
		var textoExtensiones = "";
		
		extensiones = extensiones.list[0].split(", ");
		for (var i = 0; i < extensiones.length; i++) {
			
		    if (extensiones[i].toLowerCase() == extension.toLowerCase()) {
		    	valid = true;
		    	break;
		    }
		    
		    textoExtensiones += extensiones[i].toLowerCase();
		    if (i != extensiones.length - 1) textoExtensiones += ", ";  
		}
		
		if (!valid) {
			s_alert_info('Formato del archivo inv\u00e1lido', 'Debes adjuntar un archivo con formato ' + textoExtensiones + ' antes de continuar');
			return false;
		}
	}
	
	return true;
	
}

function setButtonValue(id_option, value, nombre) {
	$("#" + id_option + "_div").find(".l-button").removeClass("sc-active");
	$("#" + id_option + "_div").find(".l-button").removeClass("ERR_VALIDAR");
	$("#" + id_option + "_div").find("." + nombre).addClass("sc-active");
	osm_setValor(id_option, value);
	aditionalCustomsBehaviours(id_option, value);
}

function aditionalCustomsBehaviours(id_option) {
	if (id_option == "sociedad_inversiones") {
		if (osm_getValor("sociedad_inversiones") == "1") {
			// mostrar campos
			$("#total_participacion_metodo_vrazonable_row").show();
			$("#total_participacion_metodo_costo_row").show();
			$("#total_participacion_metodo_participacion_row").show();
		} else if (osm_getValor("sociedad_inversiones") == "0") {
			// ocultar campos
			$("#total_participacion_metodo_vrazonable_row").hide();
			$("#total_participacion_metodo_costo_row").hide();
			$("#total_participacion_metodo_participacion_row").hide();
		}
	}
}

function compare( a, b ) {
	  if ( a.nombre < b.nombre ){
	    return -1;
	  }
	  if ( a.nombre > b.nombre ){
	    return 1;
	  }
	  return 0;
}
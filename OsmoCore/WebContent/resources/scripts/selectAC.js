
/**
 * Convierte un objeto select en un input autocomplete, si el usuario ingresa un 
 * valor que no esta en lista el campo se deja en blanco.
 * 
 * Ejemplo de uso:
 * 
 * 	var input = selectToAutoComplete(miSelect); //Se convierte a AC
 * 	$(input).addClass('put');	//Se agrega una clase adicional al campo creado (Opcional)
 * 
 * Estilos CSS: 
 * 	-Se agrega la clase valorlista por defecto al input creado.
 *  -Se agrega la clase ui-autocomplete-loading mientras se cargan los valores de la lista.
 *  
 *  EJ:
 *    .ui-autocomplete-loading {
 *    	background: white url(../images/ajax-loader.gif) right center no-repeat;
 *    }
 * 
 * @param select html a convertir
 * @return input creado
 */
function selectToAutoComplete(select) {

	// Lista de opciones para el AC.
	var lista = new Array();
	var posicion = 0;

	// Padre para agregar el input
	var parent = $(select).parent(":first");
	// Id del nuevo input
	var idInput = select.id + "_autocomplete";

	var input = osm_getObjeto(idInput);
	// Si ya tiene AC lo elimina
	if (input) {
		$(input).autocomplete("destroy");
		input.value = "";
	}else{
		var html = '<input id="' + idInput + '"/>';
		// Agregar el input
		parent.append(html);
		input = osm_getObjeto(idInput);
		
	}

	// Ocultar el select
	$(select).hide();

	var options = select.options;

	// Crear opciones del AC
	for ( var i = 0; i < options.length; i++) {
		if (!osm_esVacio(options[i].value)) {
			lista[posicion++] = {
				id : options[i].value,
				nombre : options[i].text
			};
		}

	}

	
	// Crear el AC
	crearListaAC(lista, input);

	// Actualiza el valor del select cuando se hace un cambio
	$(input).change(function() {
		if (input.realvalue == undefined) {
			return;
		}
		
		if (osm_esVacio(this.value)) {
			input.realvalue="";
		}
		$(select).val(input.realvalue);
		$(select).change();
		
	});

	// Se muestra el valor que tiene el select.
	if (!osm_esVacio(select.value)) {
		$(input).val(osm_getValorText(select.id));
	}

	return input;
}

/**
 * Crear la lista para autocompletar en el campo
 * 
 * @param lista -
 *            lista con valores para mostrar (id, nombre)
 * @param select -
 *            objeto html
 */
function crearListaAC(lista, select) {

	if (lista != null) {
		var listado = new Array();
		// map para buscar el nombre por id
		var map = new Array();

		for ( var i = 0; i < lista.length; i++) {
			var reg = lista[i];
			listado[i] = new Object();
			listado[i].label = reg.nombre;
			listado[i].value = reg.nombre;
			listado[i].id = reg.id;
			map[reg.id] = reg.nombre;
		}

		// Clase css para indicar que es lista
		$(select).addClass("valorlista");

		// Si no hay datos no se puede editar el campo
		if (lista.length == 0) {
			$(select).attr("readonly", true);
			$(select).removeClass("ui-autocomplete-loading");
			return;
		}
		$(select).attr("readonly", false);

		// Agrega el listener una vez
		if (!select.listado) {
			$(select).blur(validarValoLista);
		}

		select.listado = listado;
		select.map = map;
		
		var delay = 100;
		
		$(select).autocomplete({
			minLength : 0,
			source : listado,
			autoFocus : true,
			select : function(event, ui) {
				this.realvalue = ui.item.id;
				this.value = ui.item.value;
				$(this).change();
			},
			delay : delay,
			appendTo: "body"
		});
		
		if (!select.focusAutocomplete) {
			// Si gana el foco muestra la lista
			$(select).focus(function() {
				$(this).autocomplete('search');
			});
		}

		select.focusAutocomplete = true;

		$(select).removeClass("ui-autocomplete-loading");

		if (lista.length > 0) {
			return;
		}

		$(select).change();

	} else {
		$(select).attr("readonly", true);
		$(select).removeClass("ui-autocomplete-loading");
	}

}

function validarValoLista() {
	if (this.listado) {
		for ( var i = 0; i < this.listado.length; i++) {
			if (this.listado[i].label == this.value) {
				return true;
			}
		}
	}
	this.value = "";
}

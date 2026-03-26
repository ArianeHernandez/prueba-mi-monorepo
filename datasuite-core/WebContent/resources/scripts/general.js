$(initFP);
var RESULTADOS_MAXIMOS = 50;

function initFP() {
	adicionarSelectorFecha();
	window.setInterval(function(){ adicionarSelectorFecha(); }, 1000);
}

function adicionarSelectorFecha(){
	
	var nyear = new Date().getFullYear();

	$('.date-pick').each(function(){
	
		
		if (!$(this).is(".hasDatepicker") && $(this).is(":visible")) {
			
			console.log("asignando date-pick");

			$(this).datepicker({
				yearRange : (nyear - 100) + ":" + (nyear + 10),
				dateFormat : 'dd/mm/yy',
				dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa' ],
				monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
				changeMonth : true,
				changeYear : true,
				buttonImage : "../images/general/bg-go.png",
				buttonImageOnly : true,
				showOn : "both"
			});
			
			$(this).change(validarFecha);
		}
	
	});
	
}

function validarFecha() {
	try {
		var fecha = this.value;
		var objDate = new Date(fecha.substring(6, 10), parseInt(fecha.substring(3, 5), 10) - 1, fecha.substring(0, 2), 0, 0, 0, 0);
		var fecha2 = osm_getFecha(objDate.getTime() / 1000);
		if (fecha == fecha2) {
			return;
		}
	} catch (e) {
	}
	this.value = "";
}

/**
 * Inicio AUTOCOMPLETE
 */
/**
 * Convierte un select en un input autocomplete
 * 
 * @param select
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
	} else {
		var html = '<input id="' + idInput + '" class="form-control"/>';
		// Agregar el input
		parent.append(html);
		input = osm_getObjeto(idInput);

	}

	// Ocultar el select
	$(select).hide();

	var options = select.options;

	// Crear opciones del AC
	for (var i = 0; i < options.length; i++) {
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

		if (osm_esVacio(this.value)) {
			input.realvalue = "";
		}
		$(select).val(input.realvalue);
		$(select).change();

	});

	// Se muestra el valor que tiene el select.
	if (!osm_esVacio(select.value)) {
		$(input).val(osm_getValorText(select.id));
	}

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

		for (var i = 0; i < lista.length; i++) {
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
		if (listado.length > RESULTADOS_MAXIMOS) {
			delay = 300;
		}

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
			appendTo : "#bodyContent"
		});

		// Si la lista es muy grande se carga para guardar en cache
		if (listado.length > RESULTADOS_MAXIMOS) {
			$(select).autocomplete("search");
			$(select).autocomplete("close");
		}

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
		for (var i = 0; i < this.listado.length; i++) {
			if (this.listado[i].label == this.value) {
				return true;
			}
		}
	}
	this.value = "";
}

/*******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************
 * FIN AUTOCOMPLETE
 */

function goScroll(obj) {

	var p = $(obj).offset().top + $("#bodyContent").scrollTop() - 70;
	$(obj)[0].select();

	if ($(window).width() < 768) {
		$("#bodyContent").animate({
			scrollTop : p
		}, 800, 'swing', function() {
		});
	}

}

// ------------- adiciona evento de scroll
$(function() {

	$("#bodyContent").each(function() {
		this.lastscroll = 0;
	});

	$("#bodyContent").scroll(function() {

		try {

			var t = this.scrollHeight - $(this).height();
			var a = $(this).scrollTop();
			var st = parseInt(100 * a / t);

			if (st != this.lastscroll) {

				var d = (st > this.lastscroll) ? 1 : -1;

				this.lastscroll = st;

				try {
					scroll_page(t, a, st, d);
				} catch (e) {
				}
			}

		} catch (e) {
		}

	});

});

function QWEQWDSF() {

	try {
		if (sessionStorage.LAJK === undefined) {
			sessionStorage.LAJK = new Date().getTime();

			var urlloc = window.location;

			if (urlloc.pathname.endsWith(".do")) {
				$("body").hide();
				window.location = CONTEXTPATH;
			}

			return;
		}

		if (new Date().getTime() - sessionStorage.LAJK > (1000 * 60 * 60)) {	//TODO: El tiempo de sesión debe ser un parámetro de la app
			sessionStorage.LAJK = new Date().getTime();
			$("body").hide();
			window.location = CONTEXTPATH;
			alert("Su tiempo de sesión expiró!")
			return;
		}

		window.setInterval(function() {
			sessionStorage.LAJK = new Date().getTime();
		}, 100)
	} catch (e) {
		alert(e);
	}

};

QWEQWDSF();

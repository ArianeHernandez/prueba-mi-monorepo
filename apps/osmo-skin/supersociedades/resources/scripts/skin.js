// ------------------------------------------
// ------------------------------------------

var SKIN_INIT_TIME = 0;

function init() {
}

//------------------------------------------------
function validarPassDown(self) {
	self.pvalue = self.value;
}

function validarPassUp(self) {

	var pattern = new RegExp("^[\\w\\d#$%\\.:\\-\\*\\/]*$");

	if (!pattern.test(self.value)) {
		s_alert('Caracter Invalido', 'Unicamente se pueden usar los siguientes caracteres especiales: # $ % / . : - _ * ', function() {
			$(self).focus();
		});
		self.value = self.pvalue;
	}
}
// ------------------------------------------------
function aleatorio(inferior, superior) {
	var numPosibilidades = superior - inferior;
	var aleat = Math.random() * numPosibilidades;
	aleat = Math.round(aleat);
	return parseInt(inferior) + aleat;
}

// ------------------------------------------

function verTecladoGrafico() {
	var tecl = document.getElementById("caja_teclado");
	if (!tecl.w) {
		tecl.w = 60;
	}
	tecl.fw = 460;

	animaTecladoGrafico();
}

function ocultarTecladoGrafico() {
	
	var tecl = document.getElementById("caja_teclado");
	if (!tecl.w) {
		tecl.w = 60;
	}
	tecl.fw = 60;

	animaTecladoGrafico();
}

function animaTecladoGrafico() {
	var tecl = document.getElementById("caja_teclado");
	var tecl2 = document.getElementById("caja_teclado2");
	var teclado_grafico = document.getElementById("teclado_grafico");
	if (tecl.w != tecl.fw) {

		if (tecl.w > tecl.fw) {
			tecl.w = tecl.w - 20;
		} else {
			tecl.w = tecl.w + 20;
		}

		tecl.style.width = tecl.w + "px";
		tecl2.style.width = tecl.w + "px";

		if (tecl.w == 60) {
			teclado_grafico.style.display = "none";
		}

		if (tecl.w == 80) {
			teclado_grafico.style.display = "block";
		}

		window.setTimeout("animaTecladoGrafico()", 1);
	}
}

function teclaAction(obj, idselector) {
	var selec = document.getElementById(idselector);
	var divMin = document.getElementById("teclado_minu");
	var divMay = document.getElementById("teclado_mayu");
		
	teclado_animatecla(obj);

	if (obj.innerHTML == "Borrar") {
		try {
			selec.obj.value = "";
		} catch (_E) {
		}
	} else if(obj.id == "tshift"){
		if (divMay.style.display == 'none' && divMin.style.display == 'block') {
			divMay.style.display = 'block';
			divMin.style.display = 'none';
	    }
		else if (divMay.style.display == 'block' && divMin.style.display == 'none') {
			divMay.style.display = 'none';
			divMin.style.display = 'block';
	    }
	} else {

		if (obj.valor != undefined) {
			selec.obj.value = selec.obj.value + obj.valor;
		} else {
			selec.obj.value = selec.obj.value + obj.innerHTML;
		}
	}
}

var TECLADO_TECLAANIMADA = null;
var TECLADO_ANCHOTECLA = null;

function teclado_animatecla(obj) {
	if (obj != null) {
		if (obj.innerHTML == 'Borrar') {
			obj.maxh = 54;
		} else {
			obj.maxh = 28;
		}

		if (TECLADO_TECLAANIMADA != null) {
			TECLADO_TECLAANIMADA.style.width = TECLADO_TECLAANIMADA.maxh + "px";
		}

		TECLADO_TECLAANIMADA = obj;
		TECLADO_ANCHOTECLA = obj.maxh - 5;
		window.setTimeout("teclado_animatecla(null)", 10);

	} else {

		if (TECLADO_TECLAANIMADA != null) {
			TECLADO_ANCHOTECLA = TECLADO_ANCHOTECLA + 1;
			TECLADO_TECLAANIMADA.style.width = TECLADO_ANCHOTECLA + "px";
			if (TECLADO_ANCHOTECLA < TECLADO_TECLAANIMADA.maxh) {
				window.setTimeout("teclado_animatecla(null)", 50);
			} else {
				TECLADO_TECLAANIMADA.style.width = TECLADO_TECLAANIMADA.maxh + "px";
			}
		}
	}
}

function ordenarteclado() {

	var nums = new Array();
	nums[0] = false;
	nums[1] = false;
	nums[2] = false;
	nums[3] = false;
	nums[4] = false;
	nums[5] = false;
	nums[6] = false;
	nums[7] = false;
	nums[8] = false;
	nums[9] = false;

	var pi = 0;
	var i = 0;

	for (i = 0; i < 10; i++) {
		var tn = document.getElementById("tn" + i);
		var sel = false;

		while (!sel) {
			var cc = aleatorio(0, 9);
			if (!nums[cc]) {
				nums[cc] = true;
				tn.innerHTML = cc;
				tn.valor = cc;
				sel = true;

				$(tn).mouseover(function() {
					$(".tecla_numero").text("*");
				});

				$(tn).mouseout(function() {
					$(".tecla_numero").each(function(i) {
						$(this).text(this.valor);
					})
				});

			}
		}
	}

}

// ------------------------------------------

function setValorTime(obj, value) {
	window.setTimeout("document.getElementById('" + obj + "').value = '" + value + "';", 1);
}

// ------------------------------------------
// ------------------------------------------

function inputtextfocus(obj, idselector, posy) {

	if (!obj.initTextFocus) {
		obj.classNameBase = obj.className;
		obj.initTextFocus = true;
		obj.isfocus = false;

		obj.nofocus = function() {
			this.isfocus = false;
			this.className = this.classNameBase;

			try {
				this.selec.style.display = 'none';
			} catch (E) {
			}
		};

		$(obj).keypress(function(ev) {

			this.nofocus();
			
			self = this;

			if ($(this).is(".eTeclado") && $("#caja_teclado").is(":visible")) {
				s_alert('Utilice el Teclado Grafico.', 'Por su seguridad solamente se permite el uso del teclado grafico.', function(){
					$(self).focus();
				});
				ev.preventDefault();
			}
		});

	}

	if (!obj.isfocus) {
		obj.className = obj.classNameBase + " inputSelected";
		obj.isfocus = true;

		try {
			var selec = document.getElementById(idselector);
			try {
				selec.obj.nofocus();
			} catch (_E) {
			}

			selec.style.display = 'block';
			selec.style.top = posy + "px";
			selec.obj = obj;
			obj.selec = selec;
		} catch (E) {
		}
	}
}

// ------------------------------------------
// SCRIPT PARA EL MENU PRINCIPAL

osm_listen("load", window, function() {

	$(".submenu ").css({
		display : "none"
	});

	$(".menu_li").hover(function() {
		$(this).addClass('menu_li_hover');
	}, function() {
		$(this).find('.submenu').fadeOut(200);
		osm_mostrarSelects("bodyContent");
		$(this).removeClass('menu_li_hover');
	});

	$(".submenu").css({
		display : "none"
	});

	$(".submenu").hover(null, function() {
		$(this).find('> .submenu_li').find('> .submenu').css({
			display : "none"
		});
	});

	$(".a_submenu").hover(function() {
		$(this).addClass('a_submenu_hover');
	}, function() {
		$(this).removeClass('a_submenu_hover');
	});

	$(".submenu_li").hover(function() {

		$(this).find('> .submenu').css({
			visibility : "visible",
			display : "none"
		}).fadeIn(300);

	}, function() {
		$(this).find('> .submenu').fadeOut(100);
	});

	$(".submenu_li").click(function() {
		$(this).find('> .submenu').fadeOut(100);
	});

	$(".a_submenu").hover(function() {
		$(this).addClass('a_submenu_hover');
	}, function() {
		$(this).removeClass('a_submenu_hover');
	});

	$(".menu_li").click(function() {

		if ($(this).find('> .submenu').is(":hidden")) {
			$(this).find('> .submenu').find('> .submenu_li').find('> .submenu').css({
				display : "none"
			});
			$(this).find('> .submenu').css({
				visibility : "visible",
				display : "none"
			}).fadeIn(100);
		} else {
			$(this).find('> .submenu').fadeOut(200);
		}
	});
});

function osm_onloadwindow() {

	$("#bodyContent").fadeTo(1, 0.1, "linear", function() {
		$("#bodyContent").fadeTo(400, 1, "linear");
	});

	document.body.style.display = "block";

	osm_unblock_window();
}

var FLG_VER_LOADER = false;
function osm_verLoader() {
	FLG_VER_LOADER = true;
	$("#block_window_loader").fadeIn(400, function() {
		FLG_VER_LOADER = false;
	});
	$("#bodyContent").fadeTo(200, 0.5);
}

function osm_ocultarLoader() {

	if (FLG_VER_LOADER) {
		osm_timeout(osm_ocultarLoader, 200);
		return;
	}
	$("#block_window_loader").fadeOut(100);

	$("#bodyContent").fadeTo(200, 1);
}

// Tooltips
function setTooltip() {
	$(":input[title], img[title]").tooltip({
		position : "top center",
		offset : [ 0, -5 ],
		opacity : 1
	});
}

$(document).ready(function() {
	setTooltip();
});

// ----------------------------------------------------------------
// Funcionalidad para presentar hora actual del sistema..

var SKIN_FECHA_INICIAL = null;
var SKIN_TIEMPO_ACTUAL = null;

$(document).ready(function() {

	var ultimo_ingreso = osm_getValorEntero("skin_init_time");
	var ip_cliente = osm_getValor("skin_ip_cliente");

	if (ultimo_ingreso > 0) {
		osm_setValor("last_msg", "&Uacuteltima visita <span> " + osm_timeToStringDate(ultimo_ingreso) + "</span>"
					  + osm_timeToStringHour(ultimo_ingreso) + " - IP " + ip_cliente);
	}

	// --

	SKIN_FECHA_INICIAL = osm_newDate();
	SKIN_TIEMPO_ACTUAL = SKIN_FECHA_INICIAL.getTime();

	osm_timer(function() {

		SKIN_TIEMPO_ACTUAL += 1000;

		osm_setValor("skin_date", osm_timeToStringDate(SKIN_TIEMPO_ACTUAL) + " " + osm_timeToStringHour(SKIN_TIEMPO_ACTUAL));

	}, 1000);

});

// ----------------------------------------------------------------
// Cierra session por inactividad

var SKIN_TIEMPO_INACTIVIDAD_MAX = 10 * 60; // 5 minutos
var SKIN_TIEMPO_INACTIVIDAD = 0;

osm_listen("load", window, function(e) {

	var barra = osm_getObjeto("foot_inactive_bar");
	var mensaje = osm_getObjeto("timeout_msg");
	var b_mensaje = false;

	if (jsonrpc.core) {

		osm_timer(function() {

			SKIN_TIEMPO_INACTIVIDAD++;

			var tt = parseInt((100 * (SKIN_TIEMPO_INACTIVIDAD)) / SKIN_TIEMPO_INACTIVIDAD_MAX);

			$("#foot_inactive").css("width", tt + "%");

			if (tt > 10) {

				if ($(barra).is(":hidden")) {
					$(barra).fadeIn(1000);
				}

			} else {
				if ($(barra).filter(":visible")) {
					$(barra).fadeOut(1000);
				}
			}

			if (tt > 80) {
				if (!b_mensaje) {
					$(mensaje).show();
					$("#last_msg").hide();
					$("#foot_inactive").css("background-color", "red");
					b_mensaje = true;
				} else {

					var ts = SKIN_TIEMPO_INACTIVIDAD_MAX - SKIN_TIEMPO_INACTIVIDAD;

					if (ts > 60) {
						$(mensaje).html("La sesión finalizará en menos de " + (parseInt(ts / 60) + 1) + " minutos por inactividad.");
					}
					if (ts <= 60 && ts > 30) {
						$(mensaje).html("La sesión finalizará en menos de un minuto por inactividad.");
					}
					if (ts <= 30 && ts > 0) {
						$(mensaje).html("La sesión finalizará en " + (SKIN_TIEMPO_INACTIVIDAD_MAX - SKIN_TIEMPO_INACTIVIDAD) + " segundos por inactividad.");
					}

					if (ts <= 0) {
						$(mensaje).html("Cerrando sesión por inactividad");
					}
				}
			} else {

				if (b_mensaje) {
					$(mensaje).html(" ");
					$(mensaje).hide();
					$("#last_msg").show();

					$("#foot_inactive").css("background-color", "#006325");
					b_mensaje = false;
				}
			}

			if (SKIN_TIEMPO_INACTIVIDAD > SKIN_TIEMPO_INACTIVIDAD_MAX) {
				osm_go("inicio/0.pub?logout_time", true);
			}

		}, 1000);
	}
});

osm_listen("mousemove", document, function(e) {
	SKIN_TIEMPO_INACTIVIDAD = 0;
});

osm_listen("keypress", document, function(e) {
	SKIN_TIEMPO_INACTIVIDAD = 0;
});

// -------------------------------------------------

var SK_ERROR_VALIDACION = false;

function skin_init_validar() {

	$(".ERR_VALIDAR").removeClass("ERR_VALIDAR");

	SK_ERROR_VALIDACION = false;
}

function skin_OK_validar() {

	if (SK_ERROR_VALIDACION) {
		alert("Existen datos invalidos.\n\nPor favor, verifique los campos resaltados.");
	}

	return !SK_ERROR_VALIDACION;
}

function skin_validar(id_obj, tipo, obligatorio) {

	if (!skin_validar_tipo(id_obj, tipo, obligatorio)) {
		skin_error(id_obj);

		return false;
	}

	return true;
}

function skin_validar_menorque(id_obj1, id_obj2) {

	var obj1 = osm_strlimpiar(osm_getValor(id_obj1));
	var obj2 = osm_strlimpiar(osm_getValor(id_obj2));

	if (!osm_esVacio(obj1) && !osm_esVacio(obj2) && Math.parseInt(obj1, 10) >= Math.parseInt(obj2, 10)) {
		skin_error(id_obj1);
		skin_error(id_obj2);

		return false;
	}

	return true;
}

function skin_validar_fechamenorigualque(id_obj1, id_obj2) {

	var obj1 = osm_strlimpiar(osm_getValor(id_obj1));
	var obj2 = osm_strlimpiar(osm_getValor(id_obj2));

	if (osm_esVacio(obj1) || osm_esVacio(obj2)) {
		return true;
	}

	var d1 = osm_getObjetoFecha(obj1);
	var d2 = osm_getObjetoFecha(obj2);

	if (d1.getTime() > d2.getTime()) {
		skin_error(id_obj1);
		skin_error(id_obj2);

		return false;
	}

	return true;
}

function skin_error(id_obj) {

	$(osm_getObjeto(id_obj)).removeClass("ERR_VALIDAR").addClass("ERR_VALIDAR");
	SK_ERROR_VALIDACION = true;
}

function skin_validar_tipo(id_obj, tipo, obligatorio) {

	var valor = osm_strlimpiar(osm_getValor(id_obj));

	if (obligatorio && osm_esVacio(valor)) {
		return false;
	}

	if (!obligatorio && osm_esVacio(valor)) {
		return true;
	}

	if (tipo == "FECHA" && !osm_validarFormatoFecha(valor)) {
		return false;
	}

	if (tipo == "NUMERO" && !osm_validarNumero(valor)) {
		return false;
	}

	if (tipo == "DECIMAL" && !osm_validarDecimal(valor)) {
		return false;
	}

	if (tipo == "2DECIMAL" && !osm_validar2Decimal(valor)) {
		return false;
	}

	if (tipo == "CORREO" && !osm_validarCorreo(valor)) {
		return false;
	}

	if (tipo == "ALFABETICO" && !osm_validarAlfabetico(valor)) {
		return false;
	}

	if (tipo == "ALFANUMERICO" && !osm_validarAlfanumerico(valor)) {
		return false;
	}

	return true;
}

function cargarTemplatesPlugins() {
	$(".input_plantilla_elemento").each(function() {
		var idElemento = $(this).attr("id").replace("input_plantilla_", "");
		var plantilla = $(this).val();
		$(this).val("");
		$("#" + idElemento).empty();
		$("#" + idElemento).html(plantilla);
	});
}

$(document).ready(function() {
	cargarTemplatesPlugins();
});

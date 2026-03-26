var SALERT_ACTIONFUNTION = null;

function s_alert_to_simple() {
	$("#sAlertContent").addClass("bg_alert_simple");
}

function s_alert_simple(title, content, callback_ok) {
	s_alert(title, content, callback_ok);
	$("#sAlertContent").addClass("bg_alert_simple");
}

function s_alert_ok(title, content, callback_ok) {
	s_alert(title, content, callback_ok);
	$("#sAlertContent").addClass("bg_alert_ok");
}

function s_alert_info(title, content, callback_ok) {
	s_alert(title, content, callback_ok);
}

function s_alert_error(title, content, callback_error) {
	s_alert(title, content, callback_error);
	$("#sAlertContent").addClass("bg_alert_error");
}

function s_alert_warn(title, content, callback_warn) {
	s_alert(title, content, callback_warn);
	$("#sAlertContent").addClass("bg_alert_warn");
}

function s_alert_cancel(title, content, callback_warn) {
	s_alert(title, content, callback_warn);
	$("#btn_s_alert").hide();
	$("#btn_s_alert_cancel").show();
}

// ---

function s_prompt_cal(title, content, callback_promp) {
	s_alert(title, content, callback_promp);
	$("#sAlertContent").append('<div class="stars"><form action=""><input class="star star-5" id="star-5" type="radio" name="star"/><label class="star star-5" for="star-5"></label><input class="star star-4" id="star-4" type="radio" name="star"/><label class="star star-4" for="star-4"></label><input class="star star-3" id="star-3" type="radio" name="star"/><label class="star star-3" for="star-3"></label><input class="star star-2" id="star-2" type="radio" name="star"/><label class="star star-2" for="star-2"></label><input class="star star-1" id="star-1" type="radio" name="star"/><label class="star star-1" for="star-1"></label></form>	</div>');
}

function s_prompt(title, content, callback_promp) {
	s_alert(title, content, callback_promp);
	$("#btn_s_alert_no").show();
	$("#sAlertContent").append("<br><br><input type='text' id='salert_data' class='form-control input-lg' />");
	$("#salert_data").focus();
}

function s_accept(title, content, action, callback_promp) {
	s_alert(title, content, callback_promp);
	$("#btn_s_alert_no").show();
	$("#btn_s_alert_si").show();
	$("#btn_s_alert_si").text(action);
	$("#btn_s_alert").hide();

	$("#btn_s_alert_no").focus();
}

function s_wait(title, content) {

	if (title === undefined) {
		title = ("por favor espera");
	}

	if (content === undefined) {
		content = "";
	}

	s_alert(title, content);

	$("#sAlert").find(".modal-dialog").hide();

	$("#btn_s_alert").hide();
	$("#sAlertFooter").show();

	$("#sAlertContent").addClass("bg_alert_wait");
}

function s_alert_hide(callback_hide) {

	$('#sAlert').modal('hide');

	if (callback_hide !== undefined) {
		callback_hide();
	}
}

function s_alert_lg() {
	$("#sAlert").find(".modal-dialog").addClass("modal-lg");
}

// ---

var ALERT_ANIMANDO = true;

function s_alert(title, content, callback) {
	
	$("#sAlertContent").removeClass("bg_alert_simple").removeClass("bg_alert_ok").removeClass("bg_alert_error").removeClass("bg_alert_warn").removeClass("bg_alert_wait");
	$("#sAlertFooter").show();

	$("#btn_s_alert").html(("Aceptar")).show();
	$("#btn_s_alert_no").html(("Cancelar")).hide();
	$("#btn_s_alert_si").html(("Aceptar")).hide();
	$("#btn_s_alert_cancel").html(("Cancelar")).hide();

	$("#sAlert").find(".modal-dialog").removeClass("modal-lg").show();

	$("#btn_s_alert").focus();

	// ---

	$("#sAlertContent").empty();
	$("#sAlertContent").html(content);

	if (callback === undefined) {
		SALERT_ACTIONFUNTION = null;
	} else {
		SALERT_ACTIONFUNTION = callback;
	}

	if (title === undefined || title == null || title == "") {
		title = 'Info';
	}

	$("#sAlertLabel").text(title);

	$('#sAlert').modal('show');
	
}

var SALERT_ACTIONFUNTION_VALUE = null;

function s_alert_action(value) {

	$(".modal-backdrop").remove();
	s_alert_hide();

	blockWindow();

	SALERT_ACTIONFUNTION_VALUE = value;

	window.setTimeout(function() {
		s_alert_action_base();
	}, 250);
}

function s_alert_action_base() {

	unblockWindow();

	if (SALERT_ACTIONFUNTION != null) {

		if ($("#salert_data").length > 0) {
			SALERT_ACTIONFUNTION(SALERT_ACTIONFUNTION_VALUE, $("#salert_data").val());
		} else {
			SALERT_ACTIONFUNTION(SALERT_ACTIONFUNTION_VALUE);
		}
	}
}

// ----------------------------------------------------------------------------------
// bloqueador de ventana

function blockWindow() {
	unblockWindow();
	$("body").append("<div class='blockWindow'></div>");
	$("input, button").blur();
}

function unblockWindow() {
	$(".blockWindow").remove();
}

// ----------------------------------------------------------------------------------

$(function() {

	$.ajax({
		url : CONTEXTPATH + "/publicfiles/alert/alert_template.html",
		cache : true
	}).done(function(plantilla) {

		var comp = _.template(plantilla);
		$("body").append(comp({}));

	});
});

// ----------------------------------------------------------------------------------

function s_ajustarModal() {

	$(".modal-dialog").each(function() {

		var h = (($(window).height() - $(this).height()) * 0.4);

		if (h < 0) {
			h = 0;
		}

		if ($(this).css("top") != h + "px") {
			$(this).css("top", h + "px");
		}

	});
}

// ----------------------------------------------------------------------------------

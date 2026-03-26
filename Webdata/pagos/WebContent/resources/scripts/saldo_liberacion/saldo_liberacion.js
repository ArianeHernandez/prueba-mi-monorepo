$(function() {

	var url = location.href;

	// cuando la persona juridica libera
	
	if (url.indexOf("/liberacion/15.3.do") > 0) {

		try {

			var plantilla_sl = null;

			$.ajax({
				url : "../publicfiles/saldo_liberacion/saldo_liberacion2.html",
				method : "GET",
				dataType : "html",
				async : false
			}).done(function(msg) {
				plantilla_sl = msg;
			});

			var comp_sl = _.template(plantilla_sl);

			// adiciona evento al selector de cuenta

			var ins = $(".campo_adicional input[type='hidden'][value='Id cuenta']");

			ins.parent().find("select").change(function() {

				var producto = parseInt($(".campo_adicional input[type='hidden'][value='Producto']").parent().find("select").val());

				var saldo = jsonrpc._("cuentaServicio.saldo")(producto, this.value);

				if (saldo == null) {
					saldo = "--";
				} else {
					saldo = osm_formatoMoneda2(saldo, "");
				}

				$("#area_mensaje_liberacion").html(comp_sl({
					valor_carga : $("#valor_carga_str").text(),
					saldo_disponible : saldo
				}));
			});

		} catch (e) {
		}

	}
	
	
	// cuando la persona natural libera
	
	if (url.indexOf("/carga_informacion/interactivo/1.1.do") > 0  || url.indexOf("/carga_informacion/lotes/1.2.1.do") > 0) {

		try {

			var plantilla_sl = null;

			$.ajax({
				url : "../publicfiles/saldo_liberacion/saldo_liberacion2.html",
				method : "GET",
				dataType : "html",
				async : false
			}).done(function(msg) {
				plantilla_sl = msg;
			});

			var comp_sl = _.template(plantilla_sl);

			// adiciona evento al selector de cuenta

			var ins = $(".campo_adicional input[type='hidden'][value='Id cuenta']");

			ins.parent().find("select").change(function() {

				var producto = parseInt($(".campo_adicional input[type='hidden'][value='Producto']").parent().find("select").val());

				var saldo = jsonrpc._("cuentaServicio.saldo")(producto, this.value);

				if (saldo == null) {
					saldo = "--";
				} else {
					saldo = osm_formatoMoneda2(saldo, "");
				}

				$("#area_mensaje_liberacion").html(comp_sl({
					valor_carga : "",
					saldo_disponible : saldo
				}));
				
				$("#area_saldo_proceso").hide();
			});

		} catch (e) {
		}

	}
	

});

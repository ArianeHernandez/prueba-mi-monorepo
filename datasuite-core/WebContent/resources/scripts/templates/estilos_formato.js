function validarEstilos(){
	var reg = /^([A-Za-z\-]+?:\s*?[0-9A-Za-z\-#(), \.%]+?;\s*?)*?$/;
	var ok = true;
	$('.tooltip_estilo textarea').each(function (e){
		$(this).removeClass("ERR_VALIDAR");
		var val = $(this).val();
		if (!osm_esVacio(val)){
			var passed = reg.test(val);			
			if (!passed){
				ok = false;
				$(this).addClass("ERR_VALIDAR");
			}
		}
	});
	if (!ok){
		osm_alert("Valide el formato del css ingresado");
	}
	return ok;
}
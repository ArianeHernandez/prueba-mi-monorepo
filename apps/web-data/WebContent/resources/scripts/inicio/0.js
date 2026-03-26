function descargaPlantilla(id_plantilla) {
	var fake_form_url = "../DescargaArchivoServlet";

	var $fake_form = $("<form>", {
		action : fake_form_url,
		method : "post",
	});

	$("<input>").attr({
		name : "id_archivo",
		value : id_plantilla,
	}).appendTo($fake_form);

	$(document.body).append($fake_form);

	$fake_form.submit();
}
function ocultar(id){
	$("#"+id).hide();
}
function mostrar(id){
	$("#"+id).show();
}
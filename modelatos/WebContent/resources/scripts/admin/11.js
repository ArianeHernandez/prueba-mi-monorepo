$(iniciarOrdenar);

function iniciarOrdenar(){
	$(".ordenable").sortable();
	$(".ordenable").disableSelection();
}

function guardar(){
	$("input[name$='orden']").each(
			function (i){
				this.value = i + 1;
			}
	);
	osm_enviarFormulario("form_campos");
}

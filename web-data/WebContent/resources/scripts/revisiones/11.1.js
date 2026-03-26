$(function(){
	$("tr[name^='div_procesos_grupo_']").each(
		function(){
			var name = $(this).attr("name");
			var id_grupo = osm_remplazar(name, 'div_procesos_grupo_', '')
			$(this).appendTo("#cont_grupo_" + id_grupo);
		}
	);
});

function enviar(id_revision,id_proceso){
	osm_setValor('id_revision', id_revision);
	osm_setValor('id_proceso', id_proceso);
	osm_enviarFormulario('form_revision');
}


function toggleProcesosPorGrupo(id_grupoformato){
	
	$("#lista_procesos_grupo_"+id_grupoformato).toggle();
	

}

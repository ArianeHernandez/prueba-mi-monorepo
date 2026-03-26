function enviar(id_proceso, id_formato_salida){
	osm_setValor('id_proceso', id_proceso);
	osm_setValor('id_formato_salida', id_formato_salida);
	osm_enviarFormulario('form_proceso');
}

function toggleProcesosPorGrupo(id_grupoformato){
	
	$("#div_procesos_grupo_"+id_grupoformato).each(function(i){
		
		if($(this).is(":hidden")){
			$(this).show();
			
		}else{
			$(this).hide();			
			
		}
	});
	

}

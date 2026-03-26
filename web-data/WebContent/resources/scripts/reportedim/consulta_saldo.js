$(init_consulta);

function init_consulta(){
	
	callBackClickFila = clickFilaConsulta;
	
}

function clickFilaConsulta(objeto){
	
	var idReporte = $(objeto).attr("idReporte");
	var idRegistro = $(objeto).attr("idRegistro");
	if(!osm_esVacio(idReporte)){
		$("#contfila_" + idReporte + "_" + idRegistro).toggle();
	}
	
}


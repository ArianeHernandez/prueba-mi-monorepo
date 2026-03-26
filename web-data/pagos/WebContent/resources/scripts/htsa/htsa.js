var AVANCE = 0;
var TIEMPO_INICIAL = new Date();

function verificarProgreso() {
	
	try{
		if(AVANCE<100){
			AVANCE = jsonrpc._("progresoArchivoExterno.avanceProgreso")();
			
			$("#progreso").css("width", (AVANCE*2) + "px");
			
			if(AVANCE>=5){
				osm_setValor("tiempo_transcurrido", osm_tiempofaltantetexto(TIEMPO_INICIAL , AVANCE));
			}
			
			if(AVANCE>=100){
				osm_go("carga_informacion/bancos/HTSA_1.do");			
			}
		}
		
	}catch (e) {
		osm_setValor("tiempo_transcurrido", "Lo sentimos, ha ocurrido un error..!!");
	}
	
}

// -----------

osm_listen("load", window, function(){
	
	osm_timer(function(){
		
		verificarProgreso();
		
	}, 1000)
	
});

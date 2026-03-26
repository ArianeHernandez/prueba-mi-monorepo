osm_listen("load", window, function(e){
	
	osm_timeout(function(){
		
		osm_go('tablero_control/34.do', false);
		
	}, 20000)
	
});


osm_listen('click', document, function(e){
	osm_go('inicio/0.do', false);
});

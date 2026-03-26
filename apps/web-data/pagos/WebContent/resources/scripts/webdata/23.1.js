
function verificarProgreso(){
	$("#btn_volver").hide();
    try {
    
        var avance = jsonrpc._("grupoGiroServicio.obtenerEstadoGrupoGiro")();
        if (avance != null) {
        
            if (avance.finalizado) {
				
				$("#barra-progreso").hide();
				if(avance.exito){
					osm_setValor("mensaje", "La informaci\u00f3n ha sido almacenada con \u00e9xito.");
				}
				else{
					osm_setValor("mensaje", "No se almacenaron registros.");
				}
				$("#btn_volver").show();
            }
            else {
            
                $("#progreso").css("width", (avance.porcentaje * 2) + "px");
                
                setTimeout("verificarProgreso()", 1000);
                
            }
        }
        
    } catch (e) {
        alert(e);
    }
    
}

$(verificarProgreso);

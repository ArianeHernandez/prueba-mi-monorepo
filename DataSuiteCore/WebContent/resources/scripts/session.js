
var msnFinSession = false;
var tiempoFinSession = 60 * 3;
var tiempoInactividad = "";
var title_anterior = document.title;
var TIEMPOSESSION = 0;

$(FINALIZAR_SESION);
function FINALIZAR_SESION() {
	
	if(msnFinSession){
		tiempoFinSession--;
				
		if(tiempoFinSession<=0){
			location.href = CONTEXTPATH + "/inicio/0.pub?expira";			
		}else {
			$("#ventana_content_finSession").show();
		}
		if(tiempoFinSession%2==0 && tiempoFinSession>=0){
			document.title = "Sesión inactiva";
		}else if(tiempoFinSession>=0){
			document.title = tiempoFinSession  + " segundos para cerrar sesión";
		}
		
		
	}

	if((SESSIONTIME-TIEMPOSESSION)<=60*3 && !msnFinSession){
		var tiempo = (TIEMPOSESSION/60);
		tiempo = ""+tiempo;
		var splitTiempo = tiempo.split(".");
		var msn = "";
		if(splitTiempo.length>1){
			var minutos = splitTiempo[0];
			var segundos = splitTiempo[1];
			if(minutos>0){
				msn = minutos + " minutos ";
			}else{
				segundos = parseFloat("0."+segundos)*60;
				if(msn!=""){
					msn += " y ";
				}
				msn += segundos + " segundos ";
			}
		}else if(splitTiempo.length=1){
			msn = splitTiempo[0] + " minutos ";
		}

		var tiempoMin = parseFloat(TIEMPOSESSION/60);
		var roundTiempo = Math.round(tiempoMin*100)/100;

		msnFinSession = true;
		tiempoInactividad = msn;
	}

	if (TIEMPOSESSION >= SESSIONTIME) {
		location.href = CONTEXTPATH + "/inicio/0.pub?expira";
	}
	ISFINALIZAR_SESION = true;
	TIEMPOSESSION++;

	window.setTimeout("FINALIZAR_SESION()", 1000);
}

function CANCEL_FINALIZAR_SESION() { 
		TIEMPOSESSION = 0; 
		msnFinSession=false; 
		tiempoInactividad = ""; 
		tiempoFinSession=60*3; 
		$('#terminarSession').fadeOut('fast');
		document.title = title_anterior;
}

osm_listen("mousemove", window, CANCEL_FINALIZAR_SESION);
osm_listen("keypress", window, CANCEL_FINALIZAR_SESION);

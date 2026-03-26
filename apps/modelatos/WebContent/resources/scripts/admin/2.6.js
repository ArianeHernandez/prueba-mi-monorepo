var ID_GRUPO = null;

function ajustar_area_diagrama() {
	var pos = osm_getWindowSize();

	$("#area_diagrama").css("height", (pos[1] - 250) + "px");
}

osm_listen("resize", window, ajustar_area_diagrama);
osm_listen("load", window, ajustar_area_diagrama);

$(function() {
	
	ID_GRUPO = osm_getValorEntero("id_grupo");
	
	$(".tabla_diagrama").draggable({
			 start: function(){ 
				 var id_estructura = parseInt(this.id.substring(2));
				 
				 $(".ERE"+ id_estructura).remove();
			 },
			 
			 drag: function(){ 
				 var id_estructura = $(this).find("input").val();
				 $(".ERE"+ id_estructura).remove();
				 pintarRelaciones();
			 },
			 
			 stop: function() {
				 
				    var id_estructura = $(this).find("input").val();
					var ypos = parseInt($(this).css("top"));
					var xpos = parseInt($(this).css("left"));

					jsonrpc._("estructuraServicio.actualizarPosicionGrupo")(function(){},id_estructura, xpos, ypos, ID_GRUPO);

					$(".ERE"+ id_estructura).remove();
					pintarRelaciones();
				 
			 }
			  });
	
	$(".tabla_diagrama").mousedown(function(){
		$("#area_diagrama").append(this);
		
		$(".tabla_sel").each(function(){ 
			var id_estructura = $(this).find("input").val();
			$(".ERE"+ id_estructura).remove();
		});
		
		$(".tabla_sel").removeClass("tabla_sel");
		
		$(this).addClass("tabla_sel");
		var id_estructura = $(this).find("input").val();
		$(".ERE"+ id_estructura).remove();
		
		pintarRelaciones();
	});

	pintarRelaciones();
})

// ----------------------------------------------------------------------

function pintarRelaciones() {

	$(".relacion").each(function(i) {

		var id_campo = parseInt(this.id.substring(4));
		var id_estructura = osm_getValorEntero("EE_C" + id_campo);
		var id_estructura_rel = this.innerHTML;

		if (osm_getObjeto("ARL_C" + id_campo) ==null) {

			var xpos = parseInt($("#TE" + id_estructura).css("left")) + 70;
			var ypos = parseInt($("#TE" + id_estructura).css("top")) + osm_getValorEntero("YPOS_C" + id_campo) + 10;

			var xposF = parseInt($("#TE" + id_estructura_rel).css("left")) + 70;
			var yposF = parseInt($("#TE" + id_estructura_rel).css("top")) + 10;

			
			var x1 = minValue(xpos, xposF);
			var x2 = maxValue(xpos, xposF);
			
			var y1 = minValue(ypos, yposF);
			var y2 = maxValue(ypos, yposF);
			
			var ww = x2 - x1;
			var hh = y2 - y1;
			
			var eh = false;
			if(ww < 180){
				ww = 180;
				eh = true;
			}
			
			$("#area_diagrama").prepend("<div id='ARL_C" + id_campo + "' class='area_relacion ERE" + id_estructura + " ERE" + id_estructura_rel + "'></div>");
			$("#ARL_C" + id_campo).css("top", (y1-10) + "px").css("left", x1 + "px");
			$("#ARL_C" + id_campo).css("width", ww + "px").css("height", (hh + 20) + "px");
			
			// --------------------
			
			var gr = new jsGraphics(document.getElementById("ARL_C" + id_campo));
			
			var col = new jsColor("#666666");
			var pen = new jsPen(col, 1);
			
			if( $("#TE" + id_estructura).is(".tabla_sel") || $("#TE" + id_estructura_rel).is(".tabla_sel")  ){
				col = new jsColor("#003366");
				pen = new jsPen(col, 2);
			}
			
			var xr = (xpos<xposF)?1:-1;
			var yr = (ypos<yposF)?1:-1;
			
			var xmed = (x2-x1)/2;
			var ymed = (y2-y1)/2;
			
			var rd = 3;
			
			if(eh){
				
				var ptT1 = new jsPoint(xpos - x1 - 70 * xr, ypos - y1 + 10);
				var ptT2 = new jsPoint(xpos - x1 - (100 - rd) * xr, ypos - y1 + 10);
				var ptT3 = new jsPoint(xpos - x1 - 100 * xr, ypos - y1 + rd * yr + 10);
				var ptT4 = new jsPoint(xpos - x1 - 100 * xr, yposF - y1 - rd * yr + 10);
				var ptT5 = new jsPoint(xpos - x1 - (100 - rd) * xr, yposF - y1 + 10);
				var ptT6 = new jsPoint(xposF - x1 - 70 * xr , yposF - y1 + 10);
				
				var points = [ptT1, ptT2, ptT3, ptT4, ptT5, ptT6];
				
				gr.drawPolyline(pen, points);
				
			}else{
				
				if(hh > rd*2)	{
					var ptT1 = new jsPoint(xpos - x1 + 70 * xr, ypos - y1 + 10);
					var ptT2 = new jsPoint(xmed - rd * xr, ypos - y1 + 10);
					var ptT3 = new jsPoint(xmed, ypos - y1 + rd * yr + 10);
					var ptT4 = new jsPoint(xmed, yposF - y1 - rd * yr + 10);
					var ptT5 = new jsPoint(xmed + rd * xr, yposF - y1 + 10);
					var ptT6 = new jsPoint(xposF - x1 - 70 * xr , yposF - y1 + 10);
					
					var points = [ptT1,ptT2,ptT3,ptT4,ptT5,ptT6];
					
					gr.drawPolyline(pen, points);
				}
				else{
					var ptT1 = new jsPoint(xpos - x1 + 70 * xr, ypos - y1 + 10);
					var ptT2 = new jsPoint(xmed - rd * xr, ypos - y1 + 10);
					var ptT3 = new jsPoint(xmed + rd * xr, yposF - y1 + 10);
					var ptT4 = new jsPoint(xposF - x1 - 70 * xr , yposF - y1 + 10);
					
					var points = [ptT1,ptT2,ptT3,ptT4];
					
					gr.drawPolyline(pen, points);
				}
			}
			
			// ---------------------------------------
			
			var ptTR1 =  new jsPoint(xposF - x1 - 70 * xr , yposF - y1 + 10 + 7);
			var ptTR2 =  new jsPoint(xposF - x1 - 80 * xr, yposF - y1 + 10);
			var ptTR3 =  new jsPoint(xposF - x1 - 70 * xr , yposF - y1 + 10 - 7);
			
			var pointsR = [ptTR1,ptTR2,ptTR3];
			
			gr.drawPolyline(pen, pointsR);
			
			// ---------------------------------------
		}

	});

}

function maxValue(a, b){
	if(a>b){
		return a;
	}else{
		return b;
	}
}

function minValue(a, b){
	if(a<b){
		return a;
	}else{
		return b;
	}
}


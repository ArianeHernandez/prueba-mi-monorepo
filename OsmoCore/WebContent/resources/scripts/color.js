var COLORPICKNUMBER = 0;

var COLORPICKOBJ = new Array();

function colorpickinit(cambas,cajaid, botonid, divcolor, func)
{
	COLORPICKNUMBER++;
	var colorpick = document.getElementById(cambas);
	
	if(func){
		colorpick.clk_change = func;
	}else{
		colorpick.clk_change = function(){};
	}
	
	COLORPICKOBJ[COLORPICKNUMBER] = colorpick;
	
	colorpick.isover = true;
	colorpick.onmouseover = function(){ this.isover = true; }
	colorpick.onmouseout = function(){ this.isover = false; }
	
	colorpick.innerHTML = "<div class='COLORPICKDIVBL' id='COLORPICKDIVBL_"+COLORPICKNUMBER+"' style='background-color:"+ document.getElementById(cajaid).value +"'></div>";
		
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000", func);
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#003300");
	createColorItem(cambas, cajaid, botonid, divcolor,"#006600");
	createColorItem(cambas, cajaid, botonid, divcolor,"#009900");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00CC00");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00FF00");

	createColorItem(cambas, cajaid, botonid, divcolor,"#330000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#333300");
	createColorItem(cambas, cajaid, botonid, divcolor,"#336600");
	createColorItem(cambas, cajaid, botonid, divcolor,"#339900");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33CC00");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33FF00");

	createColorItem(cambas, cajaid, botonid, divcolor,"#660000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#663300");
	createColorItem(cambas, cajaid, botonid, divcolor,"#666600");
	createColorItem(cambas, cajaid, botonid, divcolor,"#669900");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66CC00");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66FF00");

	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#333333");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#000033");
	createColorItem(cambas, cajaid, botonid, divcolor,"#003333");
	createColorItem(cambas, cajaid, botonid, divcolor,"#006633");
	createColorItem(cambas, cajaid, botonid, divcolor,"#009933");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00CC33");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00FF33");

	createColorItem(cambas, cajaid, botonid, divcolor,"#330033");
	createColorItem(cambas, cajaid, botonid, divcolor,"#333333");
	createColorItem(cambas, cajaid, botonid, divcolor,"#336633");
	createColorItem(cambas, cajaid, botonid, divcolor,"#339933");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33CC33");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33FF33");

	createColorItem(cambas, cajaid, botonid, divcolor,"#660033");
	createColorItem(cambas, cajaid, botonid, divcolor,"#663333");
	createColorItem(cambas, cajaid, botonid, divcolor,"#666633");
	createColorItem(cambas, cajaid, botonid, divcolor,"#669933");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66CC33");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66FF33");

	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#666666");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#000066");
	createColorItem(cambas, cajaid, botonid, divcolor,"#003366");
	createColorItem(cambas, cajaid, botonid, divcolor,"#006666");
	createColorItem(cambas, cajaid, botonid, divcolor,"#009966");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00CC66");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00FF66");

	createColorItem(cambas, cajaid, botonid, divcolor,"#330066");
	createColorItem(cambas, cajaid, botonid, divcolor,"#333366");
	createColorItem(cambas, cajaid, botonid, divcolor,"#336666");
	createColorItem(cambas, cajaid, botonid, divcolor,"#339966");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33CC66");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33FF66");

	createColorItem(cambas, cajaid, botonid, divcolor,"#660066");
	createColorItem(cambas, cajaid, botonid, divcolor,"#663366");
	createColorItem(cambas, cajaid, botonid, divcolor,"#666666");
	createColorItem(cambas, cajaid, botonid, divcolor,"#669966");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66CC66");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66FF66");

	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#999999");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#000099");
	createColorItem(cambas, cajaid, botonid, divcolor,"#003399");
	createColorItem(cambas, cajaid, botonid, divcolor,"#006699");
	createColorItem(cambas, cajaid, botonid, divcolor,"#009999");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00CC99");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00FF99");

	createColorItem(cambas, cajaid, botonid, divcolor,"#330099");
	createColorItem(cambas, cajaid, botonid, divcolor,"#333399");
	createColorItem(cambas, cajaid, botonid, divcolor,"#336699");
	createColorItem(cambas, cajaid, botonid, divcolor,"#339999");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33CC99");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33FF99");

	createColorItem(cambas, cajaid, botonid, divcolor,"#660099");
	createColorItem(cambas, cajaid, botonid, divcolor,"#663399");
	createColorItem(cambas, cajaid, botonid, divcolor,"#666699");
	createColorItem(cambas, cajaid, botonid, divcolor,"#669999");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66CC99");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66FF99");

	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCCCCC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#0000CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#0033CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#0066CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#0099CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00CCCC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00FFCC");

	createColorItem(cambas, cajaid, botonid, divcolor,"#3300CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#3333CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#3366CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#3399CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33CCCC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33FFCC");

	createColorItem(cambas, cajaid, botonid, divcolor,"#6600CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#6633CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#6666CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#6699CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66CCCC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66FFCC");

	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFFFFF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#0000FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#0033FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#0066FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#0099FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00CCFF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00FFFF");

	createColorItem(cambas, cajaid, botonid, divcolor,"#3300FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#3333FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#3366FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#3399FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33CCFF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#33FFFF");

	createColorItem(cambas, cajaid, botonid, divcolor,"#6600FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#6633FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#6666FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#6699FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66CCFF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#66FFFF");


	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF0000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#990000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#993300");
	createColorItem(cambas, cajaid, botonid, divcolor,"#996600");
	createColorItem(cambas, cajaid, botonid, divcolor,"#999900");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99CC00");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99FF00");

	createColorItem(cambas, cajaid, botonid, divcolor,"#CC0000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC3300");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC6600");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC9900");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCCC00");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCFF00");

	createColorItem(cambas, cajaid, botonid, divcolor,"#FF0000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF3300");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF6600");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF9900");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFCC00");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFFF00");


	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00FF00");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#990033");
	createColorItem(cambas, cajaid, botonid, divcolor,"#993333");
	createColorItem(cambas, cajaid, botonid, divcolor,"#996633");
	createColorItem(cambas, cajaid, botonid, divcolor,"#999933");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99CC33");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99FF33");

	createColorItem(cambas, cajaid, botonid, divcolor,"#CC0033");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC3333");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC6633");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC9933");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCCC33");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCFF33");

	createColorItem(cambas, cajaid, botonid, divcolor,"#FF0033");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF3333");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF6633");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF9933");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFCC33");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFFF33");


	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#0000FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#990066");
	createColorItem(cambas, cajaid, botonid, divcolor,"#993366");
	createColorItem(cambas, cajaid, botonid, divcolor,"#996666");
	createColorItem(cambas, cajaid, botonid, divcolor,"#999966");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99CC66");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99FF66");

	createColorItem(cambas, cajaid, botonid, divcolor,"#CC0066");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC3366");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC6666");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC9966");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCCC66");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCFF66");

	createColorItem(cambas, cajaid, botonid, divcolor,"#FF0066");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF3366");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF6666");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF9966");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFCC66");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFFF66");


	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFFF00");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#990099");
	createColorItem(cambas, cajaid, botonid, divcolor,"#993399");
	createColorItem(cambas, cajaid, botonid, divcolor,"#996699");
	createColorItem(cambas, cajaid, botonid, divcolor,"#999999");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99CC99");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99FF99");

	createColorItem(cambas, cajaid, botonid, divcolor,"#CC0099");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC3399");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC6699");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC9999");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCCC99");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCFF99");

	createColorItem(cambas, cajaid, botonid, divcolor,"#FF0099");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF3399");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF6699");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF9999");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFCC99");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFFF99");


	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#00FFFF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#9900CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#9933CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#9966CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#9999CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99CCCC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99FFCC");

	createColorItem(cambas, cajaid, botonid, divcolor,"#CC00CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC33CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC66CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC99CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCCCCC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCFFCC");

	createColorItem(cambas, cajaid, botonid, divcolor,"#FF00CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF33CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF66CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF99CC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFCCCC");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFFFCC");


	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF00FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#000000");

	createColorItem(cambas, cajaid, botonid, divcolor,"#9900FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#9933FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#9966FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#9999FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99CCFF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#99FFFF");

	createColorItem(cambas, cajaid, botonid, divcolor,"#CC00FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC33FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC66FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CC99FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCCCFF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#CCFFFF");

	createColorItem(cambas, cajaid, botonid, divcolor,"#FF00FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF33FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF66FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FF99FF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFCCFF");
	createColorItem(cambas, cajaid, botonid, divcolor,"#FFFFFF");

	colorpick.style.display="block";
	var btn_bb = document.getElementById(botonid);
	
	btn_bb.onblur = new Function("window.setTimeout(\"var colorpick = document.getElementById('"+cambas+"'); colorpick.style.display='none';\",100)");
	
	osm_listen("mousedown", document, new Function(" try{ var dfr = COLORPICKOBJ["+COLORPICKNUMBER+"]; if(!dfr.isover){ dfr.style.display='none'; } }catch(e){ }") );
	osm_listen("mousemove", document, new Function(" try{ var dfr = COLORPICKOBJ["+COLORPICKNUMBER+"]; var dcol = document.getElementById('COLORPICKDIVBL_"+COLORPICKNUMBER+"'); if(!dfr.isover){ dcol.style.background = '" + document.getElementById(cajaid).value + "'; } }catch(e){ }") );
}

function createColorItem(cambas, cajaid, botonid, divcolor, color)
{
	var colorpick = document.getElementById(cambas);
	var elem = document.createElement('div');
	elem.onclick = new Function( " var caja = document.getElementById('"+cajaid+"'); caja.value = '"+color+"'; $(caja).change(); var colorpick = document.getElementById('"+cambas+"'); colorpick.clk_change(); colorpick.style.display='none'; var divcolor = document.getElementById('"+divcolor+"'); divcolor.style.background='"+color+"'");
	elem.className = "COLORPICKDIV";
	elem.style.background = color;
	elem.onmousemove = function(){
		var cdd = document.getElementById("COLORPICKDIVBL_"+COLORPICKNUMBER);
		cdd.style.background = this.style.background;
	}
	colorpick.appendChild(elem); 
}

var ANCHO_EJEX = 1;
var TAMANO_EJEY = 200;

$(draw);

function draw() {

	var canvas = document.getElementById("grafico");
	var ctx = canvas.getContext("2d");
	var datos = obtenerDatosMemoria();
	var memoriaTotal = osm_getValorEntero("memoriaTotal");
	var cantPuntos = datos.list.length;

	canvas.width = cantPuntos;

	// eje horizontal X

	ctx.strokeStyle = "rgb(#fffffff)";
	ctx.lineWidth = 1;
	ctx.beginPath();
	ctx.moveTo(0, 200);
	ctx.lineTo((cantPuntos * ANCHO_EJEX), 200);
	ctx.stroke();

	// Grafico memoria utilizada

	var listado = datos.list;
	var registros = null;
	ctx.lineJoin = "round";

	// ----------------------------------------------

	ctx.fillStyle = "#CCCCFF";
	ctx.beginPath();
	ctx.moveTo(0, 200);
	for (y = 0; y < listado.length; y++) {
		var registros = listado[y];
		if (registros != null) {
			var memousu = registros.memoriaMaquinaVirtual;
			var grafMemoriaUtil = (TAMANO_EJEY * (1 - (memousu / memoriaTotal)));
			ctx.lineTo((y * ANCHO_EJEX), grafMemoriaUtil);
		}
	}
	ctx.lineTo(((cantPuntos - 1) * ANCHO_EJEX), 200);
	ctx.fill();

	// ----------------------------------------------

	ctx.fillStyle = "#FFCCCC";
	ctx.beginPath();
	ctx.moveTo(0, 200);
	for (y = 0; y < listado.length; y++) {
		var registros = listado[y];
		if (registros != null) {
			var memousu = registros.memoriaUtilizada;
			var grafMemoriaUtil = (TAMANO_EJEY * (1 - (memousu / memoriaTotal)));
			ctx.lineTo((y * ANCHO_EJEX), grafMemoriaUtil);
		}
	}
	ctx.lineTo(((cantPuntos - 1) * ANCHO_EJEX), 200);
	ctx.fill();

	// ----------------------------------------------
	// Lineas separadoras entre eje X

	ctx.lineWidth = 1;
	ctx.strokeStyle = "#CCCCCC";
	var np = 3;
	for (i = 1; i <= cantPuntos; i += np) {
		ctx.beginPath();
		ctx.moveTo((i * ANCHO_EJEX), 0);
		ctx.lineTo(1 + (i * ANCHO_EJEX), 200);
		ctx.stroke();
	}
	// ----------------------------------------------

	ctx.strokeStyle = "#1E2DBD";
	ctx.lineWidth = 2;
	ctx.beginPath();
	ctx.moveTo(0, 200);
	for (y = 0; y < listado.length; y++) {
		var registros = listado[y];
		if (registros != null) {
			var memousu = registros.memoriaMaquinaVirtual;
			var grafMemoriaUtil = (TAMANO_EJEY * (1 - (memousu / memoriaTotal)));
			ctx.lineTo((y * ANCHO_EJEX), grafMemoriaUtil);
		}
	}
	ctx.stroke();

	// ----------------------------------------------

	ctx.strokeStyle = "#BD1E2D";
	ctx.lineWidth = 2;
	ctx.beginPath();
	ctx.moveTo(0, 200);
	for (y = 1; y <= listado.length; y++) {
		var registros = listado[y];
		if (registros != null) {
			var memousu = registros.memoriaUtilizada;
			var grafMemoriaUtil = (TAMANO_EJEY * (1 - (memousu / memoriaTotal)));
			ctx.lineTo((y * ANCHO_EJEX), grafMemoriaUtil);
		}
	}
	ctx.stroke();

	// ----------------------------------------------
	// linea de tiempo

	var horaIni = listado[0].hora;
	var px = 90;
	var sumHora = horaIni.split(":");
	var separaHora = new Array();

	separaHora[0] = parseInt(sumHora[0], 10);
	separaHora[1] = parseInt(sumHora[1], 10);
	separaHora[2] = parseInt(sumHora[2], 10);
	
	var hora = separaHora[0];
	var minutos = separaHora[1];
	var segundos = separaHora[2];
	if (minutos > 30){
		var min = 0;
		hora += 1;
		if(hora>23){
			hora=0;
		}	
	}else {
		var min = 30;
	}
	
	var mint = (min < 10) ? "0" + min : min;
	var horat = (hora < 10) ? "0" + hora : hora;

	var horaFinal = horat + ":" + mint;
	var puntoIniHora = (minutos > 30) ? (60 - minutos) * 3 : (30 - minutos) * 3;

	ctx.strokeStyle = "#64FE2E";
	ctx.lineWidth = 3;
	ctx.fillStyle = "rgba(0, 0, 3, 5.2)";
	ctx.font = '9px "Tahoma"';

	ctx.beginPath();
	ctx.fillText(horaFinal, (puntoIniHora - 11), 212);
	for (i = puntoIniHora; i < cantPuntos; i += px) {
		min += 30;
		if (min >= 60) {
			min = 0;
			hora++;
			if(hora>23){
				hora=0;
			}
		}
		ctx.moveTo((i * ANCHO_EJEX), 200);
		ctx.lineTo(1 + (i * ANCHO_EJEX), 205);
		mint = (min < 10) ? "0" + min : min;
		horat = (hora < 10) ? "0" + hora : hora;
		ctx.fillText(horat + ":" + mint, ((puntoIniHora += px) - 11), 212);
	}
	ctx.stroke();

	// ----------------------------------------------
	// mover scroll

	autoScroll();
	area_label();
}

// ----------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------

function autoScroll() {
	var scrollDiv = document.getElementById("area_grafico");
	scrollDiv.scrollLeft = 4000;
}

// ----------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------

function area_label() {

	var canvasLabel = document.getElementById("area_label");
	var contx = canvasLabel.getContext("2d");
	var memoriaTotal = osm_getValorEntero("memoriaTotal");

	var total = Math.floor(memoriaTotal / 1048576);

	contx.strokeStyle = "#64FE2E";
	contx.lineWidth = 2;
	contx.fillStyle = "rgba(0, 0, 3, 5.2)";
	contx.font = '9px "Tahoma"';

	contx.beginPath();
	contx.moveTo(50, 0);
	contx.lineTo(46, 1);
	contx.fillText(total, 30, 7);
	contx.moveTo(50, 50);
	contx.lineTo(46, 50);
	contx.fillText((Math.floor((total * 3) / 4)), 30, 50);
	contx.moveTo(50, 100);
	contx.lineTo(46, 100);
	contx.fillText((total / 2), 30, 100);
	contx.moveTo(50, 150);
	contx.lineTo(46, 150);
	contx.fillText((Math.floor(total  / 4)), 30, 150);
	contx.moveTo(50, 200);
	contx.lineTo(46, 200);
	contx.fillText(0, 30, 200);
	contx.stroke();
}

// ----------------------------------------------------------------------------------------
// ------------------------------- GRAFICO SESSIONES ACTIVAS
// --------------------------

$(graficoUsuarios);

var USUARIOSTOTAL = 0;

function graficoUsuarios() {

	USUARIOSTOTAL = osm_getValorEntero("total_sesiones");
	if (USUARIOSTOTAL > 0) {

		var canvasUsuarios = document.getElementById("grafico_user");
		var ctx = canvasUsuarios.getContext("2d");
		var datos = obtenerDatosMemoria();
		var listado = datos.list;

		var cantPuntos = datos.list.length;
		canvasUsuarios.width = cantPuntos;

		// eje horizontal X

		ctx.strokeStyle = "rgb(black)";
		ctx.lineWidth = 1;
		ctx.beginPath();
		ctx.moveTo(0, 200);
		ctx.lineTo((cantPuntos * ANCHO_EJEX), 200);
		ctx.stroke();

		// Grafico cantida de usuarios

		var listado = datos.list;
		var registros = null;
		ctx.lineJoin = "round";

		// ----------------------------------------------
		

		ctx.fillStyle = "#CCCCFF";
		ctx.beginPath();
		ctx.moveTo(0, 200);
		for (y = 0; y < listado.length; y++) {
			var registros = listado[y];
			if (registros != null) {
				var memousu = registros.sesiones_activas;
				var grafSesiones_activas = (TAMANO_EJEY * (1 - (memousu / USUARIOSTOTAL)));
				ctx.lineTo((y * ANCHO_EJEX), grafSesiones_activas);
			}
		}
		ctx.lineTo(((cantPuntos - 1) * ANCHO_EJEX), 200);
		ctx.fill();

		// ----------------------------------------------

		// Lineas separadoras entre eje X

		ctx.lineWidth = 1;
		ctx.strokeStyle = "#CCCCCC";
		var np = 3;
		for (i = 1; i <= cantPuntos; i += np) {
			ctx.beginPath();
			ctx.moveTo((i * ANCHO_EJEX), 0);
			ctx.lineTo(1 + (i * ANCHO_EJEX), 200);
			ctx.stroke();
		}
		// -----------------------------------------------------------------------------------------------
		// color claro area
		
		ctx.strokeStyle = "#1E2DBD";
		ctx.lineWidth = 2;
		ctx.beginPath();
		ctx.moveTo(0, 200);
		for (y = 1; y <= listado.length; y++) {
			var registros = listado[y];
			if (registros != null) {
				var usu = registros.sesiones_activas;
				var grafSesiones_activas = (TAMANO_EJEY * (1 - (usu / USUARIOSTOTAL)));
				ctx.lineTo((y * ANCHO_EJEX), grafSesiones_activas);
			}
		}
		ctx.stroke();

		// ----------------------------------------------
		// linea de tiempo

		var horaIni = listado[0].hora;
		var px = 90;
		var sumHora = horaIni.split(":");
		var separaHora = new Array();

		separaHora[0] = parseInt(sumHora[0], 10);
		separaHora[1] = parseInt(sumHora[1], 10);
		separaHora[2] = parseInt(sumHora[2], 10);

		var hora = separaHora[0];
		var minutos = separaHora[1];
		var segundos = separaHora[2];
		if (minutos > 30){
			var min = 0;
			hora += 1;
			if(hora>23){
				hora=0;
			}	
		}else {
			var min = 30;
		}
		
		var mint = (min < 10) ? "0" + min : min;
		var horat = (hora < 10) ? "0" + hora : hora;

		var horaFinal = horat + ":" + mint;
		var puntoIniHora = (minutos > 30) ? (60 - minutos) * 3 : (30 - minutos) * 3;

		ctx.strokeStyle = "#64FE2E";
		ctx.lineWidth = 3;
		ctx.fillStyle = "rgba(0, 0, 3, 5.2)";
		ctx.font = '9px "Tahoma"';

		ctx.beginPath();
		ctx.fillText(horaFinal, (puntoIniHora - 11), 212);
		for (i = puntoIniHora; i < cantPuntos; i += px) {
			min += 30;
			if (min >= 60) {
				min = 0;
				hora++;
				if(hora>23){
					hora=0;
				}
			}
			
			ctx.moveTo((i * ANCHO_EJEX), 200);
			ctx.lineTo(1 + (i * ANCHO_EJEX), 205);
			var mint = (min < 10) ? "0" + min : min;
			var horat = (hora < 10) ? "0" + hora : hora;
			ctx.fillText(horat + ":" + mint, ((puntoIniHora += px) - 11), 213);
			
		}
		ctx.stroke();
		

		// ----------------------------------

		area_label_users();

	}else{
		
		$("#divSesionesActivas").hide();
		
		
	}
}

function obtenerDatosMemoria(){
	
	var datos = new Object();
	
	datos.list = new Array();
	
	$(".GraficoSystemInfoDto").each(function(i){
		try{
			
			datos.list[i] = new Object();
			
			datos.list[i].memoriaMaquinaVirtual = parseInt($(this).find("input[name=memoriaMaquinaVirtual]").val());
			datos.list[i].memoriaUtilizada = parseInt($(this).find("input[name=memoriaUtilizada]").val());
			datos.list[i].tiempo = new Date(parseInt($(this).find("input[name=tiempo]").val()));
			datos.list[i].sesiones_activas = parseInt($(this).find("input[name=sesiones_activas]").val());
			datos.list[i].hora = $(this).find("input[name=hora]").val();
			datos.list[i].tiempoFormato = $(this).find("input[name=tiempoFormato]").val();
			
		}catch (e) {
			alert(e);
		}
	});
	
	
	
	return datos;
}

function area_label_users() {

	var canvasLabel = document.getElementById("area_label_users");
	var contx = canvasLabel.getContext("2d");
	
	contx.strokeStyle = "#64FE2E";
	contx.lineWidth = 2;
	contx.fillStyle = "rgba(0, 0, 3, 5.2)";
	contx.font = '9px "Tahoma"';
	
	
	contx.beginPath();
	
	for(var p=0; p<=1; p+=0.25){
		
		var py = (1 - Math.round(USUARIOSTOTAL * p)/USUARIOSTOTAL ) * 200;
		contx.moveTo(50, py);
		contx.lineTo(46, py);
		contx.fillText( Math.round(USUARIOSTOTAL * p), 25, py + 7);
	}

	contx.stroke();

}

function verVentanaVideos() {
	osm_setVisible("vn_videos", true, true);
	osm_ocultarSelects("bodyContent");
	$("body").css("overflow", "hidden");
	$('html, body').animate({
		 scrollTop: $(".head-gov").offset().top
		 }, 0);
}

function cerrarVentanaVideos() {
	osm_setVisible("vn_videos", false);
	osm_mostrarSelects("bodyContent");
	$("body").css("overflow", "auto");
}

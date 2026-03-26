function rc_ajusteVentana() {
	var pos = osm_getWindowSize();
	$("#div_frame").css("height", (pos[1] - 110) + "px").css("overflow", "auto");
}

osm_listen("resize", window, rc_ajusteVentana);
osm_listen("load", window, rc_ajusteVentana);
function descargar_reporte() {

	var fecha_desde = osm_trim(osm_getValor("fecha_desde"));
	var fecha_hasta = osm_trim(osm_getValor("fecha_hasta"));

	$("#p_fecha_desde").val(fecha_desde);
	$("#p_fecha_hasta").val(fecha_hasta);

	osm_enviarFormulario("descarga_archivo");

	osm_unblock_window();

}

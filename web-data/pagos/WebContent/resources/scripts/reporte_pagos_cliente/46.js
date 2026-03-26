function descargar_reporte() {

	var fecha_desde = osm_trim(osm_getValor("fecha_desde"));
	var fecha_hasta = osm_trim(osm_getValor("fecha_hasta"));
	var id_carga = osm_getValor("id_carga");
	var id_cliente = osm_getValor("id_usuario");
	
	$("#p_fecha_desde").val(fecha_desde);
	$("#p_fecha_hasta").val(fecha_hasta);
	$("#p_id_carga").val(id_carga);
	$("#p_id_cliente").val(id_cliente);
	
	osm_enviarFormulario("descarga_archivo");

	osm_unblock_window();

}

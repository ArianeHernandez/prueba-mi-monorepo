function generarArchivo(p){
	
	osm_setValor("id_archivo", p.id_archivo);
	osm_setValor("nombre_archivo_d", p.nombre_archivo);
	
	osm_enviarFormulario("generarArchivoForm");
	osm_unblock_window();
}

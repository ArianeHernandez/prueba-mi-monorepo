package com.osmosyscol.datasuite.near.interop.postal.adjuntos;

public class PostalAdjunto {

	private String TipoDocumento;
	private String ArchivoUbicacion;
	private String ArchivoUbicacionS3;
	private String ArchivoExtension;
	
	public PostalAdjunto(){}
	
	public PostalAdjunto(String tipoDocumento, String archivoUbicacion,
			String archivoUbicacionS3, String archivoExtension) {
		super();
		TipoDocumento = tipoDocumento;
		ArchivoUbicacion = archivoUbicacion;
		ArchivoUbicacionS3 = archivoUbicacionS3;
		ArchivoExtension = archivoExtension;
	}
	public String getTipoDocumento() {
		return TipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}
	public String getArchivoUbicacion() {
		return ArchivoUbicacion;
	}
	public void setArchivoUbicacion(String archivoUbicacion) {
		ArchivoUbicacion = archivoUbicacion;
	}
	public String getArchivoExtension() {
		return ArchivoExtension;
	}
	public void setArchivoExtension(String archivoExtension) {
		ArchivoExtension = archivoExtension;
	}

	public String getArchivoUbicacionS3() {
		return ArchivoUbicacionS3;
	}

	public void setArchivoUbicacionS3(String archivoUbicacionS3) {
		ArchivoUbicacionS3 = archivoUbicacionS3;
	}
	
}

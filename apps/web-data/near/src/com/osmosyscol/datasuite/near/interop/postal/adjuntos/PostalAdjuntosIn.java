package com.osmosyscol.datasuite.near.interop.postal.adjuntos;

import java.util.ArrayList;
import java.util.List;

public class PostalAdjuntosIn {
	
	private WebDataInfo WebDataInfo; 

	private PostalInfo PostalInfo;

	private List<PostalAdjunto> FileSet = new ArrayList<PostalAdjunto>();

	public void addAdjunto(String tipoDocumento, String archivoUbicacion,
			String archivoUbicacionS3, String archivoExtension) {
		FileSet.add(new PostalAdjunto(tipoDocumento, archivoUbicacion,
				archivoUbicacionS3, archivoExtension));
	}

	public void addAdjunto(PostalAdjunto adj) {
		FileSet.add(adj);
	}

	public PostalInfo getPostalInfo() {
		return PostalInfo;
	}

	public void setPostalInfo(PostalInfo postalInfo) {
		PostalInfo = postalInfo;
	}

	public List<PostalAdjunto> getFileSet() {
		return FileSet;
	}

	public void setFileSet(List<PostalAdjunto> fileSet) {
		FileSet = fileSet;
	}

	public WebDataInfo getWebDataInfo() {
		return WebDataInfo;
	}

	public void setWebDataInfo(WebDataInfo webDataInfo) {
		WebDataInfo = webDataInfo;
	}

}

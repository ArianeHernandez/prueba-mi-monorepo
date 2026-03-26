package com.osmosyscol.datasuite.logica.dto;

import java.util.List;

public class RevisionRevisores {

	private Integer id_revision;
	private List<Revisor> revisores;

	public Integer getId_revision() {
		return id_revision;
	}

	public void setId_revision(Integer idRevision) {
		id_revision = idRevision;
	}

	public List<Revisor> getRevisores() {
		return revisores;
	}

	public void setRevisores(List<Revisor> revisores) {
		this.revisores = revisores;
	}

}

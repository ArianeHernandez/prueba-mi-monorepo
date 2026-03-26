package com.osmosyscol.datasuite.modelatos.logica.dto;

public class Relacion {
	private Integer id;
	private Integer target;
	private Integer source;
	private String name;
	private Integer idEstructuraTarget;
	private Integer idEstructuraSource;
	
	public Relacion (){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdEstructuraTarget() {
		return idEstructuraTarget;
	}

	public void setIdEstructuraTarget(Integer idEstructuraTarget) {
		this.idEstructuraTarget = idEstructuraTarget;
	}

	public Integer getIdEstructuraSource() {
		return idEstructuraSource;
	}

	public void setIdEstructuraSource(Integer idEstructuraSource) {
		this.idEstructuraSource = idEstructuraSource;
	}

}

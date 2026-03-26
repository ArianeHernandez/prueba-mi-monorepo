package com.osmosyscol.datasuite.logica.dto;

import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Nodo {
	private String name;
	private String type; 
	private String value;
	private String helpMessage;
	private List<Nodo> nodosHijo;
	private JRBeanCollectionDataSource fieldsDataSource;
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getvalue() {
		return value;
	}
	public void setvalue(String value) {
		this.value = value;
	}
	public List<Nodo> getNodo() {
		return nodosHijo;
	}
	public void setNodo(List<Nodo> nodo) {
		this.nodosHijo = nodo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public JRBeanCollectionDataSource getFieldsDataSource() {
       return new JRBeanCollectionDataSource(nodosHijo, false);
   }
	public String getHelpMessage() {
		return helpMessage;
	}
	public void setHelpMessage(String helpMessage) {
		this.helpMessage = helpMessage;
	}
}


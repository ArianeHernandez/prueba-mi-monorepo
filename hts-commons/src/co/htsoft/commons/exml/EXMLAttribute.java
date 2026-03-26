package co.htsoft.commons.exml;

public class EXMLAttribute {

	public EXMLAttribute() {
	}

	public EXMLAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public EXMLAttribute(String namespace, String name, String value) {
		this.namespace = namespace;
		this.name = name;
		this.value = value;
	}

	private String namespace;
	private String name;
	private String value;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public EXMLAttribute clone() {
		return new EXMLAttribute(this.namespace, this.name, this.value);
	}

}

// CVS $Id$

package com.osmosyscol.commons.servicio;

public class PageLog {

	private String tipo = null;
	private String url = null;
	private Double promedio = 0d;
	private Long promediomili = 0l;
	private Integer cantidad = 0;

	private Long mejorTiempo = Long.MAX_VALUE;
	private Long peorTiempo = 0l;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getMejorTiempo() {
		return mejorTiempo;
	}

	public void setMejorTiempo(Long mejorTiempo) {
		this.mejorTiempo = mejorTiempo;
	}

	public Long getPeorTiempo() {
		return peorTiempo;
	}

	public void setPeorTiempo(Long peorTiempo) {
		this.peorTiempo = peorTiempo;
	}

	public Double getPromedio() {
		return promedio;
	}

	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}

	public Long getPromediomili() {
		return promediomili;
	}

	public void setPromediomili(Long promediomili) {
		this.promediomili = promediomili;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void adicionar(String url, Long tiempo) {

		this.url = url;

		if (url.indexOf("System://") == 0) {
			tipo = "OSM Core";
		} else if (url.indexOf("Datapi://") == 0) {
			tipo = "Datapi SQL";
		} else if (url.indexOf("Ibatis://") == 0) {
			tipo = "Ibatis SQL";
		} else if (url.indexOf(".") > 0) {
			String ext = url.substring(url.lastIndexOf("."));

			if (".do".equalsIgnoreCase(ext)) {
				tipo = "Private Page";
			} else

			if (".pub".equalsIgnoreCase(ext)) {
				tipo = "Public Page";
			} else

			if (".ser".equalsIgnoreCase(ext)) {
				tipo = "Private Service";
			} else

			if (".cpt".equalsIgnoreCase(ext)) {
				tipo = "Component Service";
			} else {
				tipo = "--";
			}

		} else if (url.indexOf("/ws/") == 0) {
			tipo = "DataPi Service";
		} else {
			tipo = "--";
		}

		if (tiempo < mejorTiempo) {
			mejorTiempo = tiempo;
		}

		if (tiempo > peorTiempo) {
			peorTiempo = tiempo;
		}

		if (cantidad == 0) {
			promedio = new Double(tiempo.toString());
		} else {
			promedio = (promedio.doubleValue() * cantidad.intValue() + tiempo) / (cantidad.intValue() + 1);
		}

		promediomili = Long.valueOf(promedio.longValue());
		cantidad++;
	}
}

package com.osmosyscol.datasuite.mein.dtos;

public class Imagen {
	public String base64;
	private Integer pagina;
	private Integer posX;
	private Integer posY;
	private Integer ancho;
	private Integer alto;
	
	public Imagen(String base64, Integer pagina, Integer posX, Integer posY, Integer ancho, Integer alto) {
		super();
		this.base64 = base64;
		this.pagina = pagina;
		this.posX = posX;
		this.posY = posY;
		this.ancho = ancho;
		this.alto = alto;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public Integer getPagina() {
		return pagina;
	}
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
	public Integer getPosX() {
		return posX;
	}
	public void setPosX(Integer posX) {
		this.posX = posX;
	}
	public Integer getPosY() {
		return posY;
	}
	public void setPosY(Integer posY) {
		this.posY = posY;
	}
	public Integer getAncho() {
		return ancho;
	}
	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}
	public Integer getAlto() {
		return alto;
	}
	public void setAlto(Integer alto) {
		this.alto = alto;
	}
}

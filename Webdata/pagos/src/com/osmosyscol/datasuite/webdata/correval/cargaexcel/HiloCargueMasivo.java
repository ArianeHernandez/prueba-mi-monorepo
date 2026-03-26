package com.osmosyscol.datasuite.webdata.correval.cargaexcel;


public class HiloCargueMasivo extends Thread{

	private String ruta;
	
	private Integer idCargue;
	
	private String idTipo;
	
	public HiloCargueMasivo(String ruta, Integer idCargue, String idTipo) {
		this.ruta = ruta;
		this.idCargue = idCargue;
		this.idTipo = idTipo;
	}
	
	@Override
	public void run() {
		
		CargaExcelServicio.getInstance().cargar(ruta, idCargue, idTipo);
		
	}
}

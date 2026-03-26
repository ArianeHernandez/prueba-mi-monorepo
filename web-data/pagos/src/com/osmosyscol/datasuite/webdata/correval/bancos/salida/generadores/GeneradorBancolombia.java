package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

import co.htsoft.commons.file.FileUtils;

public class GeneradorBancolombia implements Generador {

	private Generador generador;

	public GeneradorBancolombia() {

		String tipoFormato = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("FORMATO_BANCOLOMBIA");
		System.out.println("trajo formato genereador para bancolombia: " + tipoFormato);
		if ("SAP".equals(tipoFormato)) {
			generador = new GeneradorBancolombiaSAP();
		} else {
			generador = new GeneradorBancolombiaPAB();
		}
	}

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("BANCOLOMBIA");
	}

	public boolean limpiar() {
		return generador.limpiar();
	}

	// -----------------------------------------------------

	public File generar(SalidaBanco salidabanco, IPorcentaje porcentaje) {

		try {
			return generador.generar(salidabanco, porcentaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FileUtils.newFile();

	}

	// -----------------------------------------------------

}

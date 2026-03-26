package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.lang.P;
import co.htsoft.commons.lang.StringUtils;

public class GeneradorCajaSocial implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;

	private IPorcentaje objPorcentaje;

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("CAJASOCIAL");
	}

	public boolean limpiar() {
		return true;
	}

	// -----------------------------------------------------

	public File generar(SalidaBanco salidabanco, IPorcentaje porcentaje) {

		P.println(salidabanco);
		
		this.objPorcentaje = porcentaje;

		StringBuffer r = new StringBuffer("");

		generarCuerpo(r, salidabanco);
		
		File f = FileUtils.newFile();
		try {
			FileUtils.writeStringToFile(f , r.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return f;
	}

	// -----------------------------------------------------
	// Utilidades

	private void aL(StringBuffer r, char c, int num, Object obj) {
		gu.aL(r, c, num, obj);
	}

	private void aR(StringBuffer r, char c, int num, Object obj) {
		gu.aR(r, c, num, obj);
	}

	// -----------------------------------------------------

	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		Integer indice = 0;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {

			if (objPorcentaje != null) {
				indice++;
				porcentaje = indice * 100 / sb.getRegistro().size();

				objPorcentaje.actualizarPorcentaje(porcentaje);
			}

			String observacion = "REG " + registro.getId_carga();
			String cod_transaccion = registro.getTipo_cuenta_beneficiario().equalsIgnoreCase("A")?"32":"22";

			aL(r, '0', 1, "6"); // tipo de registro
			aL(r, '0', 2, cod_transaccion); // codigo de la transaccion
			aL(r, '0', 12, gu.formato2Decimales(registro.getValor()));
			aR(r, ' ', 17, registro.getNum_cuenta_beneficiario());
			aL(r, '0', 9, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			aR(r, ' ', 15, registro.getNum_identificacion_beneficiario());
			aR(r, ' ', 22, StringUtils.trimToNullUpperCase(registro.getNombre_beneficiario()));
			aR(r, ' ', 2, "V");
			aR(r, ' ', 13, " ");
			aR(r, ' ', 10, observacion);
			aR(r, ' ', 30, registro.getConsecutivo_interno());
			aR(r, ' ', 27, " ");

			r.append(LINE);
			
		}

	}

	// -----------------------------------------------------

}

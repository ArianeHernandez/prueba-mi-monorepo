package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.lang.StringUtils;

public class GeneradorFalabella implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;

	private IPorcentaje objPorcentaje;

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("FALABELLA");
	}

	public boolean limpiar() {
		return true;
	}
	// -----------------------------------------------------

	public File generar(SalidaBanco salidabanco, IPorcentaje porcentaje) {

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

	// -----------------------------------------------------

	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		Integer indice = 0;

		for (RegistroEntradaBanco registro : sb.getRegistro()) {

			if (indice > 0) {
				r.append(LINE);
			}

			indice++;
			if (objPorcentaje != null) {
				porcentaje = indice * 100 / sb.getRegistro().size();
				objPorcentaje.actualizarPorcentaje(porcentaje);
			}

			aL(r, ' ', 3, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			aL(r, ' ', 2, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
			aL(r, ' ', 18, registro.getNum_cuenta_beneficiario());
			aL(r, ' ', 32, " ");
			aL(r, ' ', 100, StringUtils.only(StringUtils.trimToEmpty(registro.getNombre_beneficiario()).toUpperCase(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
			aL(r, '0', 2, gu.getTipoDocumento(registro.getTipo_identificacion_beneficiario()));
			aL(r, ' ', 15, registro.getNum_identificacion_beneficiario());
			aL(r, ' ', 50, " ");
			aL(r, '0', 12, gu.formatoSinDecimales(registro.getValor()));
			aL(r, ' ', 50, " ");

		}

	}

	// -----------------------------------------------------

}

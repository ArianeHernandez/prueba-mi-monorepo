package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.lang.P;

public class GeneradorOccidente implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;

	private IPorcentaje objPorcentaje;

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("OCCIDENTE");
	}

	public boolean limpiar() {
		return true;
	}

	// -----------------------------------------------------

	public File generar(SalidaBanco salidabanco, IPorcentaje porcentaje) {

		P.println(salidabanco);
		
		this.objPorcentaje = porcentaje;

		StringBuffer r = new StringBuffer("");

		generarEncabezado(r, salidabanco);
		generarCuerpo(r, salidabanco);
		generarCierre(r, salidabanco);
		
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
	// Formatos

	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");

	// -----------------------------------------------------
	/**
	 * Genera el encabezado del archivo
	 */
	private void generarEncabezado(StringBuffer r, SalidaBanco sb) {

		BigDecimal valorTotal = BigDecimal.ZERO;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			valorTotal = valorTotal.add(registro.getValor());
		}

		Long cuenta = sb.getNum_cuenta_principal_empresa();

		if (cuenta == null) {
			cuenta = sb.getNum_cuenta_empresa();
		}

		aL(r, ' ', 1, "1");
		aL(r, '0', 4, "0000");
		aL(r, '0', 8, dateformat.format(sb.getFecha_grupo()));
		aL(r, '0', 4, sb.getRegistro().size());
		aL(r, '0', 18, gu.formato2Decimales(valorTotal));
		aL(r, '0', 16, cuenta);
		aL(r, '0', 148, CERO);

	}

	// -----------------------------------------------------

	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		int pos = 0;

		Integer indice = 0;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {

			if (objPorcentaje != null) {
				indice++;
				porcentaje = indice * 100 / sb.getRegistro().size();

				objPorcentaje.actualizarPorcentaje(porcentaje);
			}

			r.append(LINE);

			boolean esCuentaOccidente = cod_banco.equals(registro.getCod_banco_beneficiario());

			String observacion = (registro.getId_carga() + "/" + registro.getCod_negocio_cliente() + "-" + registro.getCod_producto_cliente() + "-" + registro.getCod_cuenta_cliente()).trim().replace(" ", "");

			aL(r, '0', 1, "2");
			aL(r, '0', 4, (pos + 1));
			aL(r, '0', 16, sb.getNum_cuenta_empresa());
			aR(r, ' ', 30, registro.getNombre_beneficiario());
			aL(r, '0', 11, registro.getNum_identificacion_beneficiario());
			aL(r, '0', 4, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			aL(r, '0', 8, dateformat.format(sb.getFecha_grupo()));
			aL(r, '0', 1, (esCuentaOccidente) ? "2" : "3");
			aL(r, '0', 15, gu.formato2Decimales(registro.getValor()));
			aR(r, ' ', 16, registro.getNum_cuenta_beneficiario());
			aL(r, '0', 12, registro.getConsecutivo_interno());
			aL(r, ' ', 1, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
			aL(r, ' ', 80, observacion);

			pos++;
		}

	}

	// -----------------------------------------------------
	/**
	 * Genera el encabezado del archivo
	 */
	private void generarCierre(StringBuffer r, SalidaBanco sb) {

		r.append(LINE);

		BigDecimal valorTotal = BigDecimal.ZERO;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			valorTotal = valorTotal.add(registro.getValor());
		}

		aL(r, ' ', 1, "3");
		aL(r, '0', 4, "9999");
		aL(r, '0', 4, sb.getRegistro().size());
		aL(r, '0', 18, gu.formato2Decimales(valorTotal));
		aL(r, '0', 172, CERO);

	}

	// -----------------------------------------------------

}

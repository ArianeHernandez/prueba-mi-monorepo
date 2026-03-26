package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

public class GeneradorBancolombiaPAB implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;

	private IPorcentaje objPorcentaje;

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("BANCOLOMBIA");
	}
	
	public boolean limpiar() {
		return true;
	}

	// -----------------------------------------------------

	public File generar(SalidaBanco salidabanco, IPorcentaje porcentaje) {

		this.objPorcentaje = porcentaje;
		StringBuffer r = new StringBuffer("");

		generarEncabezado(r, salidabanco);
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

	public static BigDecimal redondear(BigDecimal valor) {
		if (valor == null) {
			return BigDecimal.ZERO;
		}
		return valor.setScale(0, RoundingMode.UP);
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
		Integer totalRegistros = 0;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			valorTotal = valorTotal.add(registro.getValor());
			totalRegistros++;
		}

		aL(r, ' ', 1, "1");
		aL(r, '0', 15, sb.getNit_empresa().toString());
		aL(r, ' ', 1, "I");
		aL(r, ' ', 15, BLANCO);
		aL(r, ' ', 3, "220");
		aL(r, ' ', 10, BLANCO);
		aL(r, ' ', 8, dateformat.format(sb.getFecha_grupo()));
		aL(r, '0', 2, Integer.toHexString(sb.getNum_cargadeldia()));
		aL(r, ' ', 8, dateformat.format(sb.getFecha_hoy()));
		aL(r, '0', 6, sb.getRegistro().size());
		aL(r, '0', 17, "0");
		aL(r, '0', 17, gu.formato2Decimales(valorTotal));
		aL(r, '0', 11, sb.getNum_cuenta_empresa());
		aL(r, ' ', 1, gu.getTipoCuenta(sb.getTipo_cuenta_empresa()));
		aL(r, ' ', 149, BLANCO);

	}

	// -----------------------------------------------------

	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		Integer i = 0;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {

			String cod_ref = (registro.getId_carga() + "/" + registro.getCod_negocio_cliente() + "-" + registro.getCod_producto_cliente() + "-" + registro.getCod_cuenta_cliente()).trim().replace(" ", "");

			if (objPorcentaje != null) {

				i++;
				porcentaje = i * 100 / sb.getRegistro().size();
				objPorcentaje.actualizarPorcentaje(porcentaje);

			}

			r.append(LINE);

			aL(r, ' ', 1, "6");
			aR(r, ' ', 15, registro.getNum_identificacion_beneficiario());
			aR(r, ' ', 30, registro.getNombre_beneficiario());
			aL(r, '0', 9, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			aR(r, ' ', 17, registro.getNum_cuenta_beneficiario());
			aL(r, ' ', 1, BLANCO);

			if ("A".equals(registro.getTipo_cuenta_beneficiario())) {
				aL(r, ' ', 2, "37");
			} else {
				aL(r, ' ', 2, "27");
			}

			aL(r, '0', 17, gu.formato2Decimales(registro.getValor()));
			aL(r, ' ', 8, dateformat.format(sb.getFecha_hoy()));

			aR(r, ' ', 21, cod_ref);
			aL(r, ' ', 1, BLANCO);
			aL(r, '0', 5, "0");
			aL(r, ' ', 15, BLANCO);
			aL(r, ' ', 80, BLANCO);// TODO correo electronico del beneficiario
			aL(r, ' ', 15, BLANCO);
			aL(r, ' ', 27, BLANCO);

		}

	}

	// -----------------------------------------------------

}

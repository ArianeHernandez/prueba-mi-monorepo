package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

public class GeneradorDavivienda implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;

	private IPorcentaje objPorcentaje;

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("DAVIVIENDA");
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

	private SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyyMMddHHmmss");

	// -----------------------------------------------------
	/**
	 * Genera el encabezado del archivo
	 */
	private void generarEncabezado(StringBuffer r, SalidaBanco sb) {

		BigDecimal valorTotal = BigDecimal.ZERO;
		Integer numeroTotalRegistros = 0;

		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			valorTotal = valorTotal.add(registro.getValor());

			numeroTotalRegistros++;
		}

		aL(r, ' ', 2, "RC");
		aL(r, '0', 16, sb.getNit_empresa().toString() + StringUtils.calcularDigitoVerificacion(sb.getNit_empresa().toString()));
		aL(r, ' ', 4, "PROV");
		aL(r, ' ', 4, "0000");
		aL(r, '0', 16, sb.getNum_cuenta_empresa());
		aL(r, ' ', 2, gu.getTipoCuenta(sb.getTipo_cuenta_empresa()));
		aR(r, ' ', 6, "000051");
		aL(r, '0', 18, gu.formato2Decimales(valorTotal));
		aL(r, '0', 6, numeroTotalRegistros);
		aL(r, ' ', 14, dateformat2.format(sb.getFecha_creacion()));
		aL(r, '0', 4, "0");
		aL(r, ' ', 4, "9999");
		aL(r, '0', 8, "0");
		aL(r, '0', 6, "0");
		aL(r, '0', 2, "0");
		aL(r, ' ', 2, "01");
		aL(r, '0', 12, "0");
		aL(r, '0', 4, "0");
		aL(r, '0', 40, "0");

	}

	// -----------------------------------------------------

	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		Integer indice = 0;

		for (RegistroEntradaBanco registro : sb.getRegistro()) {

			String observacion = (registro.getId_carga() + "/" + registro.getCod_negocio_cliente() + "-" + registro.getCod_producto_cliente() + "-" + registro.getCod_cuenta_cliente()).trim().replace(" ", "");
			// TODO: FALTA DEFINIR EN QUE CAMPO SE ENVIA LA OBSERVACION

			if (objPorcentaje != null) {

				indice++;
				porcentaje = indice * 100 / sb.getRegistro().size();

				objPorcentaje.actualizarPorcentaje(porcentaje);
			}

			r.append(LINE);

			aL(r, ' ', 2, "TR");

			String digito_verificacion = null;
			if (registro.getTipo_identificacion_beneficiario().equals("10")) {
				digito_verificacion = registro.getDigito_verificacion().substring(0, 1);
				aL(r, '0', 16, registro.getNum_identificacion_beneficiario() + digito_verificacion);

			} else {
				aL(r, '0', 16, registro.getNum_identificacion_beneficiario());
			}
			aL(r, '0', 16, "0");
			aL(r, '0', 16, registro.getNum_cuenta_beneficiario());
			aL(r, ' ', 2, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));

			// si el codigo es 50 Bancafe.. cambiarlo a codigo 51 davivienda

			String cod_banco = gu.getCodBanco(registro.getCod_banco_beneficiario());

			if ("50".equals(cod_banco)) {
				cod_banco = "51";
			}

			aL(r, '0', 6, cod_banco);

			aL(r, '0', 18, gu.formato2Decimales(registro.getValor()));
			aL(r, '0', 6, "0");
			aL(r, ' ', 2, gu.getTipoDocumento(registro.getTipo_identificacion_beneficiario()));
			aL(r, ' ', 1, "1");
			aL(r, ' ', 4, "9999");
			aL(r, '0', 40, "0");
			aL(r, '0', 18, "0");
			aL(r, '0', 8, "0");
			aL(r, '0', 4, "0");
			aL(r, '0', 4, "0");
			aL(r, '0', 7, "0");

		}

	}

	// -----------------------------------------------------

}

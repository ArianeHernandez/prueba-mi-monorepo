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

public class GeneradorBancolombiaSAP implements Generador {

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
			// TODO Auto-generated catch block
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

	private SimpleDateFormat dateformat = new SimpleDateFormat("yyMMdd");

	// -----------------------------------------------------
	/**
	 * Genera el encabezado del archivo
	 */
	private void generarEncabezado(StringBuffer r, SalidaBanco sb) {

		BigDecimal valorTotal = BigDecimal.ZERO;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			valorTotal = valorTotal.add(redondear(registro.getValor()));
		}

		aL(r, ' ', 1, "1");
		aL(r, '0', 10, sb.getNit_empresa());
		aR(r, ' ', 16, sb.getNombre_empresa());
		aL(r, ' ', 3, "220");
		aL(r, ' ', 10, "T         ");
		aL(r, ' ', 6, dateformat.format(sb.getFecha_hoy()));
		aL(r, '0', 1, Integer.toHexString(sb.getNum_cargadeldia()));
		aL(r, ' ', 6, dateformat.format(sb.getFecha_grupo()));
		aL(r, '0', 6, sb.getRegistro().size());
		aL(r, '0', 12, CERO);
		aL(r, '0', 12, gu.formatoSinDecimales(valorTotal));
		aL(r, '0', 11, sb.getNum_cuenta_empresa());
		aL(r, ' ', 1, gu.getTipoCuenta(sb.getTipo_cuenta_empresa()));

	}

	// -----------------------------------------------------

	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {


		Integer indice = 0;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			
			String observacion = (registro.getId_carga() + "/" + registro.getCod_negocio_cliente() + "-" + registro.getCod_producto_cliente() + "-" + registro.getCod_cuenta_cliente()).trim().replace(" ", "");
			
			if(objPorcentaje != null){
				
				indice++;
				porcentaje = indice * 100 / sb.getRegistro().size();
				objPorcentaje.actualizarPorcentaje(porcentaje);
				
			}
				
			r.append(LINE);

			aL(r, ' ', 1, "6");
			aR(r, ' ', 15, registro.getNum_identificacion_beneficiario());
			aR(r, ' ', 18, registro.getNombre_beneficiario());
			aL(r, '0', 9, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			aL(r, '0', 17, registro.getNum_cuenta_beneficiario());
			aL(r, ' ', 1, "S");

			if ("A".equals(registro.getTipo_cuenta_beneficiario())) {
				aL(r, ' ', 2, "37");
			} else {
				aL(r, ' ', 2, "27");
			}

			aL(r, '0', 10, gu.formatoSinDecimales(redondear(registro.getValor())));
			aL(r, ' ', 9, "PAGO PROV");
			aL(r, ' ', 12, registro.getConsecutivo_interno());
			aL(r, ' ', 1, BLANCO);

		}

	}

	// -----------------------------------------------------

}

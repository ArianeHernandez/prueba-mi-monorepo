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

public class GeneradorColpatria implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;
	
	private IPorcentaje objPorcentaje;
	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("COLPATRIA");
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

	public static BigDecimal redondear(BigDecimal valor) {
		if (valor == null) {
			return BigDecimal.ZERO;
		}
		return valor.setScale(0, RoundingMode.UP);
	}

	// -----------------------------------------------------
	// Formatos

	private SimpleDateFormat dateformat = new SimpleDateFormat("ddMMyyyy");

	// -----------------------------------------------------
	/**
	 * Genera el encabezado del archivo
	 */
	private void generarEncabezado(StringBuffer r, SalidaBanco sb) {

		BigDecimal valorTotal = BigDecimal.ZERO;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			valorTotal = valorTotal.add(redondear(registro.getValor()));
		}

		aL(r, '0', 5, "1");
		aL(r, '0', 2, "01");
		aR(r, ' ', 8, dateformat.format(sb.getFecha_hoy()));
		aL(r, '0', 11, sb.getNit_empresa());
		aL(r, ' ', 10, sb.getNombre_empresa());
		aL(r, ' ', 6, (sb.getRegistro().size() + 2));
		aL(r, '0', 4, "0002");
		aL(r, ' ', 12, sb.getNum_cuenta_empresa());
		aL(r, '0', 142, BLANCO);

	}

	// -----------------------------------------------------

	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		int num = 1;

		Integer indice = 0;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {

			String observacion = (registro.getId_carga() + "/" + registro.getCod_negocio_cliente() + "-" + registro.getCod_producto_cliente() + "-" + registro.getCod_cuenta_cliente()).trim().replace(" ", "");
			
			if(objPorcentaje != null){
				
				indice++;
				porcentaje = indice * 100 / sb.getRegistro().size();
				
				objPorcentaje.actualizarPorcentaje(porcentaje);
			}
			
			r.append(LINE);

			boolean esCuentaColpatria = cod_banco.equals(registro.getCod_banco_beneficiario());

			aL(r, '0', 5, (num + 1));
			aL(r, ' ', 2, "02");
			aL(r, '0', 12, esCuentaColpatria ? registro.getNum_cuenta_beneficiario() : "0");
			aL(r, '0', 11, registro.getNum_identificacion_beneficiario());
			aR(r, ' ', 40, registro.getNombre_beneficiario());
			aL(r, ' ', 3, esCuentaColpatria ? "902" : "911");
			aL(r, ' ', 2, "02");
			aL(r, '0', 15, gu.formato2Decimales(registro.getValor()));
			aL(r, '0', 10, num);
			aL(r, '0', 6, CERO);
			aL(r, '0', 15, CERO);
			aL(r, '0', 15, CERO);
			aL(r, ' ', 8, dateformat.format(sb.getFecha_grupo()));
			aL(r, '0', 10, CERO);
			aL(r, '0', 15, CERO);
			aL(r, '0', 8, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			aL(r, ' ', 17, (!esCuentaColpatria) ? registro.getNum_cuenta_beneficiario() : "0");
			aL(r, '0', 1, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
			aL(r, ' ', 1, gu.getTipoDocumento(registro.getTipo_identificacion_beneficiario()));
			aL(r, ' ', 4, BLANCO);
			aL(r, ' ', 80, observacion);

			num++;
		}

	}

	// -----------------------------------------------------
	/**
	 * Genera el encabezado del archivo
	 */
	private void generarCierre(StringBuffer r, SalidaBanco sb) {

		r.append(LINE);

		aL(r, '0', 5, (sb.getRegistro().size() + 2));
		aL(r, '0', 2, "03");
		aL(r, '0', 6, (sb.getRegistro().size() + 2));
		aL(r, ' ', 187, BLANCO);

	}
	// -----------------------------------------------------

}

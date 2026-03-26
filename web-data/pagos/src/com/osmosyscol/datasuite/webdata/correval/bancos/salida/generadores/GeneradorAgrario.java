package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

public class GeneradorAgrario implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;

	private IPorcentaje objPorcentaje;

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("AGRARIO");
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

			String observacion = (registro.getId_carga() + "/" + registro.getCod_negocio_cliente() + "-" + registro.getCod_producto_cliente() + "-" + registro.getCod_cuenta_cliente()).trim().replace(" ", "");
			
			aL(r, '0', 4, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			aL(r, ' ', 15, registro.getNum_identificacion_beneficiario());
			aL(r, '0', 1, gu.getTipoDocumento(registro.getTipo_identificacion_beneficiario()));
			aL(r, ' ', 17, registro.getNum_cuenta_beneficiario());
			aL(r, '0', 1, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
			aR(r, ' ', 30, registro.getNombre_beneficiario()); // Es el Nombre de cuenta a donde va direccionado la transacción crédito
			aL(r, ' ', 15, gu.formato2DecimalesPunto(registro.getValor())); // valor
			aR(r, ' ', 42, observacion); // referencia - descripcion de la transaccion

			r.append(LINE);
			
		}

	}

	// -----------------------------------------------------

}

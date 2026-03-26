package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

import co.htsoft.commons.file.FileUtils;

public class GeneradorITAU implements Generador {

	private static final String TIPO_IDENTIFICACION = "03";
	private static final String CIUDAD = " ";
	private static final String DEPARTAMENTO = CIUDAD;
	private static final String PAIS = DEPARTAMENTO;
	private static final String DIRECCION = PAIS;
	private static final String NUMERO_FAX = DIRECCION;
	private static final String TELEFONO2 = NUMERO_FAX;
	private static final String TELEFONO1 = TELEFONO2;
	private static final String EMAIL = TELEFONO1;
	private static final String REFERENCIA = EMAIL;
	private static final String TIPO_TRANSACCION = "CR";
	private static final String SENAL_OFICINA = REFERENCIA;
	private static final String CODIGO_OFICINA = SENAL_OFICINA;
	private static final String IDENTIFICADOR_REGISTRO = "1";
	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;

	private IPorcentaje objPorcentaje;

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("ITAU");
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

	private SimpleDateFormat dateformat = new SimpleDateFormat("MMddyy");
	
	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		Integer indice = 1;

		for (RegistroEntradaBanco registro : sb.getRegistro()) {

			if (objPorcentaje != null) {

				porcentaje = indice * 100 / sb.getRegistro().size();

				objPorcentaje.actualizarPorcentaje(porcentaje);
			}

			String observacion = (registro.getId_carga() + "/" + registro.getCod_negocio_cliente() + "-" + registro.getCod_producto_cliente() + "-" + registro.getCod_cuenta_cliente()).trim().replace(CODIGO_OFICINA, "");

			aR(r, '1', 1, IDENTIFICADOR_REGISTRO);		// Identificador de registro
			aL(r, '0', 5, indice);
			aR(r, ' ', 6, dateformat.format(sb.getFecha_grupo()));
			aR(r, ' ', 2, gu.getTipoDocumento(registro.getTipo_identificacion_beneficiario()));
			aR(r, ' ', 15, registro.getNum_identificacion_beneficiario());
			aR(r, ' ', 22, registro.getNombre_beneficiario().toUpperCase()); 
			aR(r, ' ', 1, SENAL_OFICINA); // señal de oficina
			aR(r, ' ', 3, CODIGO_OFICINA); // Codigo de oficina para entrega de cheque
			aL(r, '0', 3, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			aR(r, ' ', 3, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()).toUpperCase());
			aR(r, ' ', 17, registro.getNum_cuenta_beneficiario());
			aR(r, ' ', 2, TIPO_TRANSACCION); // Tipo de transaccion
			aL(r, '0', 14, gu.formato2Decimales(registro.getValor())); // valor
			aL(r, ' ', 20, REFERENCIA); // referencia
			aR(r, ' ', 80, observacion); // observacion
			aL(r, ' ', 100, EMAIL); // email
			aL(r, ' ', 14, TELEFONO1); // telefono1
			aL(r, ' ', 14, TELEFONO2); // telefono2
			aL(r, ' ', 14, NUMERO_FAX); // fax
			aL(r, ' ', 40, DIRECCION); // direccion
			aL(r, ' ', 4, PAIS); // pais
			aL(r, ' ', 4, DEPARTAMENTO); // departamento
			aL(r, ' ', 5, CIUDAD); // ciudad
			aL(r, '0', 2, TIPO_IDENTIFICACION); // tipo identificacion credicorp
			aR(r, ' ', 15, sb.getNit_empresa()); // identificacion credicorp
			aL(r, ' ', 3, gu.getTipoCuenta(sb.getTipo_cuenta_empresa()).toUpperCase());
			aR(r, ' ', 17, sb.getNum_cuenta_empresa());
			
			r.append(LINE);
			indice++;
		}

	}

	// -----------------------------------------------------

}

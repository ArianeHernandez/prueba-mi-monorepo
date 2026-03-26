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

public class GeneradorBancoBogota implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);

	private Integer porcentaje = 0;
	private IPorcentaje objPorcentaje;
	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("BOGOTA");
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

	private SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyyMMdd");
	// -----------------------------------------------------
	/**
	 * Genera el encabezado del archivo
	 */
	private void generarEncabezado(StringBuffer r, SalidaBanco sb) {

		aL(r, ' ', 1, "1");
		aL(r, '0', 8, dateformat2.format(sb.getFecha_grupo()));
		aR(r, '0', 24, "0");
		aL(r, ' ', 1, gu.getTipoCuenta(sb.getTipo_cuenta_empresa()));
		aL(r, '0', 6, "0");
		aL(r, '0', 11, sb.getNum_cuenta_empresa());
		aR(r, ' ', 40, sb.getNombre_empresa());
		aL(r, '0', 11, sb.getNit_empresa().toString() + StringUtils.calcularDigitoVerificacion(sb.getNit_empresa().toString()));
		aL(r, ' ', 3, "002");
		aL(r, '0', 4, "0001");
		aL(r, ' ', 8, dateformat2.format(sb.getFecha_hoy()));
		
		String numCuentaCorreval = sb.getNum_cuenta_empresa()+"";
		
		aL(r, '0', 3, numCuentaCorreval.substring(0, 3));
		aL(r, ' ', 1, "N");
		aL(r, ' ', 48, BLANCO);
		aL(r, ' ', 1, BLANCO);
		aL(r, ' ', 80, BLANCO);

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

			aL(r, ' ', 1, "2");
			aL(r, ' ', 1, gu.getTipoDocumento(registro.getTipo_identificacion_beneficiario()));
			
			
			String digito_verificacion = null;
			//Si el tipo de identificacion es NIT(10) se debe colcoar el digito de verificacion
			if(registro.getTipo_identificacion_beneficiario().equals("10")){
				digito_verificacion = registro.getDigito_verificacion().substring(0, 1);
				aL(r, '0', 11, registro.getNum_identificacion_beneficiario()+digito_verificacion); 
				
			}else{
				aL(r, '0', 11, registro.getNum_identificacion_beneficiario());
			}
			 
			aR(r, ' ', 40, registro.getNombre_beneficiario());
			aL(r, ' ', 1, "0");
			aL(r, ' ', 1, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
			aR(r, ' ', 17, registro.getNum_cuenta_beneficiario());
			aL(r, '0', 18, gu.formato2Decimales(registro.getValor()));
			aL(r, ' ', 1, "A");
			aL(r, '0', 3, "0");
			aL(r, '0', 3, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			aL(r, ' ', 4, "0001");
			aL(r, ' ', 9, sb.getNombre_empresa());
			aL(r, ' ', 1, BLANCO);
			aL(r, ' ', 70, observacion);
			aL(r, ' ', 1, "0");
			aL(r, '0', 10, registro.getConsecutivo_interno());
			aL(r, ' ', 1, "N");
			aL(r, ' ', 8, BLANCO);
			aL(r, ' ', 18, BLANCO);
			aL(r, ' ', 11, BLANCO);
			aL(r, ' ', 11, BLANCO);
			aL(r, ' ', 1, "N");
			aL(r, ' ', 8, BLANCO);
			

		}

	}

	// -----------------------------------------------------

}

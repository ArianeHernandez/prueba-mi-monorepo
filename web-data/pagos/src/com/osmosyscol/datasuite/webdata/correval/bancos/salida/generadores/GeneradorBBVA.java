package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

public class GeneradorBBVA implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;
	
	private IPorcentaje objPorcentaje;
	
	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("BBVA");
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
	// Formatos

	// -----------------------------------------------------

	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		
		boolean esprimero = true;
		Integer indice = 0;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {

			String observacion = (registro.getId_carga() + "/" + registro.getCod_negocio_cliente() + "-" + registro.getCod_producto_cliente() + "-" + registro.getCod_cuenta_cliente()).trim().replace(" ", "");
			
			if(objPorcentaje != null){
				indice++;
				porcentaje = indice * 100 / sb.getRegistro().size();
				
				objPorcentaje.actualizarPorcentaje(porcentaje);
			}
			
			if (!esprimero) {
				r.append(LINE);
			}else{
				esprimero = false;
			}

			aL(r, ' ', 2, gu.getTipoDocumento(registro.getTipo_identificacion_beneficiario()));
			
			
			String digito_verificacion = "0";
						
			//Si el tipo de identificacion es NIT se debe colocar digito de verificacion, de lo contrario es 0
			if(registro.getTipo_identificacion_beneficiario().equals("10")){
				digito_verificacion = registro.getDigito_verificacion().substring(0, 1);
				
			}else{
				digito_verificacion="0";
			}
					
			
			aL(r, '0', 16, registro.getNum_identificacion_beneficiario()+digito_verificacion);
			aL(r, ' ', 1, 1);
			aL(r, '0', 4, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			
			SimpleLogger.setInfo("Generardor BBVA cuenta beneficiario"+registro.getNum_cuenta_beneficiario());
			
			if(registro.getCod_banco_beneficiario().equals(cod_banco)){
				
				BigInteger numerocuenta = new BigInteger( registro.getNum_cuenta_beneficiario());
				
				String cuenta =  StringUtils.izquierda("" +  numerocuenta, 9, '0');
				String oficina = "0"+cuenta.substring(0, 3);
				String tipo_cuenta="";
				if(registro.getTipo_cuenta_beneficiario().equals("A")){
					tipo_cuenta="0200";
				}else{
					tipo_cuenta="0100";
				}
				String numeroCuenta = oficina+"00"+tipo_cuenta+cuenta.substring(3, 9);
				
				SimpleLogger.setInfo("Generardor BBVA cuenta beneficiario modificada"+numeroCuenta);
				
				
				aL(r, ' ', 16, numeroCuenta);
			}else{
				aL(r, '0', 16, "0");
			}
			
			//---------------
			if(!registro.getCod_banco_beneficiario().equals(cod_banco)){
				aL(r, ' ', 2, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
			}else{
				aL(r, '0', 2, "0");
			}
			
			if(!registro.getCod_banco_beneficiario().equals(cod_banco)){
				aR(r, ' ', 17, registro.getNum_cuenta_beneficiario());
			}else{
				aL(r, '0', 17, "0");
			}
			aL(r, '0', 15, gu.formato2Decimales(registro.getValor()));
			aL(r, ' ', 4, "0000");
			aL(r, ' ', 2, "00");
			aL(r, ' ', 2, "00");
			aL(r, ' ', 4, "9999");
			aR(r, ' ', 36, registro.getNombre_beneficiario());
			if(StringUtils.isNotEmpty(registro.getDireccion_beneficiario())) {
				aR(r, ' ', 36, registro.getDireccion_beneficiario());
			}else {
				aR(r, ' ', 36, ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("DIRECCION_BENEFICIARIO"));
			}
			aL(r, ' ', 36, BLANCO);
			aL(r, ' ', 48, BLANCO);
			aR(r, ' ', 40, "PAGO");
			aR(r, ' ', 40, observacion);
			
			for (int i = 0; i < 20; i++) {
				aL(r, ' ', 40, BLANCO);
			}
			
		}

	}
	
	public static void main(String[] args) {
		String cuenta = "1234567890";
		SimpleLogger.setDebug(cuenta.substring(0, 3));
		SimpleLogger.setDebug(cuenta.substring(3, 9));
		
	}

	// -----------------------------------------------------

}

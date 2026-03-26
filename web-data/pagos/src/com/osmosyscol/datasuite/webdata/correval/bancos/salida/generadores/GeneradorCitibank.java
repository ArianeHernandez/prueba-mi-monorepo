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

public class GeneradorCitibank implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;
	
	private IPorcentaje objPorcentaje;
	
	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("CITIBANK");
	}
	
	public boolean limpiar() {
		return true;
	}

	// -----------------------------------------------------

	public File generar(SalidaBanco salidabanco, IPorcentaje porcentaje) {

		this.objPorcentaje = porcentaje;

		StringBuffer r = new StringBuffer("");

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

	private SimpleDateFormat dateformat = new SimpleDateFormat("yyMMdd");
	// -----------------------------------------------------
	

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
			
			aL(r, ' ', 3, "PAY");
			aL(r, ' ', 3, "170");
			aL(r, '0', 10, sb.getNum_cuenta_empresa());
			aL(r, ' ', 6, dateformat.format(sb.getFecha_grupo()));
			if(registro.getCod_banco_beneficiario().equals(cod_banco)){
				aL(r, '0', 3, "72");
			}else{
				aL(r, '0', 3, "71");
			}
			
			aR(r, ' ', 15, StringUtils.reemplazarCaracteresEspecialesParaNombre(registro.getNombre_beneficiario())); 
			aL(r, '0', 8, registro.getConsecutivo_interno());
			aR(r, ' ', 14, registro.getNum_identificacion_beneficiario());
			aL(r, ' ', 3, "COP");
			aR(r, ' ', 10, registro.getNum_identificacion_beneficiario());
			aL(r, '0', 15, gu.formato2Decimales(registro.getValor()));
			aL(r, ' ', 6, BLANCO);
			aR(r, ' ', 30, "PAGO");
			aL(r, ' ', 30, observacion);
			aL(r, ' ', 6, BLANCO);
			aL(r, ' ', 2, "31");
			aL(r, ' ', 2, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
			aR(r, ' ', 80, registro.getNombre_beneficiario());
			aR(r, ' ', 30, registro.getDireccion_beneficiario());
			aL(r, ' ', 15, BLANCO);
			aL(r, ' ', 2, BLANCO);
			aL(r, ' ', 8, BLANCO);
			aL(r, ' ', 11, BLANCO);
			
			//Si el banco no es igual a citibank se coloca el codigo del banco, de lo contrario se coloca 000
			if(!registro.getCod_banco_beneficiario().equals(cod_banco)){ 
				aL(r, '0', 3, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			}else{
				aL(r, '0', 3, "0");
				
			}
			aR(r, ' ', 8, "0001");
			
			//Si la cuenta del beneficiario no es citibank se debe colocar 
			//el numero de cuenta de lo contrario debe ser vacio
			if(!registro.getCod_banco_beneficiario().equals(cod_banco)){ 
				aR(r, ' ', 16, registro.getNum_cuenta_beneficiario());
					
			}else{
				aR(r, ' ', 16, BLANCO);
				
			}
			
			aL(r, ' ', 2, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
			aR(r, ' ', 30, "PPAL");//TODO sucursal
			aL(r, ' ', 15, BLANCO);
			aL(r, ' ', 2, BLANCO);
			aL(r, '0', 10, "0");
			aL(r, ' ', 20, BLANCO);
			aL(r, ' ', 15, BLANCO);
			
			//Si la cuenta del beneficiario es citibank se debe colocar 
			//el numero de cuenta del beneficiario de lo contrario debe ser vacio
			if(registro.getCod_banco_beneficiario().equals(cod_banco)){ 
				aR(r, ' ', 10, registro.getNum_cuenta_beneficiario());
					
			}else{
				aR(r, ' ', 10, BLANCO);
				
			}
			
			
			//Se debe colocar el tipo de cuenta si la cuenta es de tipo citibank, de lo contrario se coloca 00
			if(registro.getCod_banco_beneficiario().equals(cod_banco)){ 
				aL(r, ' ', 2, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
					
			}else{
				aL(r, ' ', 2, "00");
				
			}
			aL(r, ' ', 3, "104");
			aL(r, ' ', 50, BLANCO);
			aL(r, ' ', 5, BLANCO);
			aL(r, ' ', 50, BLANCO);
			aL(r, ' ', 47, BLANCO);
			
		}

	}

	// -----------------------------------------------------
	// -----------------------------------------------------
	/**
	 * Genera el encabezado del archivo
	 */
	private void generarCierre(StringBuffer r, SalidaBanco sb) {

		r.append(LINE);

		Integer numeroRegistros = 0;
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			valorTotal = valorTotal.add(registro.getValor());
			numeroRegistros++;
		}

		aL(r, ' ', 3, "TRL");
		aL(r, '0', 15, numeroRegistros);
		aL(r, '0', 15, gu.formato2Decimales(valorTotal));
		aL(r, '0', 15, "0");
		aL(r, '0', 15, numeroRegistros);
		aL(r, ' ', 37, BLANCO);


	}

	// -----------------------------------------------------


}

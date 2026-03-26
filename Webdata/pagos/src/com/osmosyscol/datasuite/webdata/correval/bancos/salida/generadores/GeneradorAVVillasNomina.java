package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

public class GeneradorAVVillasNomina implements Generador {

	private static final String TIPO_REGISTRO_FINAL = "3";
	private static final String CODIGO_TRANSACCION = "23";
	private static final String TIPO_REGISTRO_DETALLE = "2";
	private static final String CODIGO_ADQUIRIENTE = TIPO_REGISTRO_DETALLE;
	private static final String CODIGO_OFICINA = "88";
	private static final String TIPO_REGISTRO_CONTROL = "1";
	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;
	private BigDecimal total_valor = new BigDecimal(0);
	private IPorcentaje objPorcentaje;
	
	private List<String> lineas = new ArrayList<String>();
	
	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("AVVILLAS");
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
		generarFinal(r, salidabanco);
		
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

	private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat formatoHora = new SimpleDateFormat("HHmmss");
	// -----------------------------------------------------

	/**
	 * Genera el encabezado del archivo
	 */
	private void generarEncabezado(StringBuffer r, SalidaBanco sb) {
		
		StringBuffer sbEncabezado = new StringBuffer("");

		aL(sbEncabezado, '0', 2, TIPO_REGISTRO_CONTROL); // Tipo registro
		aL(sbEncabezado, '0', 8, formatoFecha.format(sb.getFecha_hoy())); // Fecha generacion
		aL(sbEncabezado, '0', 6, formatoHora.format(sb.getFecha_hoy())); // Hora generacion
		aL(sbEncabezado, '0', 3, CODIGO_OFICINA); // Codigo de oficina
		aL(sbEncabezado, '0', 2, CODIGO_ADQUIRIENTE); // Codigo adquiriente
		aL(sbEncabezado, ' ', 50, BLANCO); // Nombre archivo
		aL(sbEncabezado, ' ', 120, BLANCO); // Relleno
		
		lineas.add(sbEncabezado.toString());
		r.append(sbEncabezado.toString());

	}

	// -----------------------------------------------------
	
	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		Integer indice = 0;
		Integer secuencia = 1;
		StringBuffer sbCuerpo = new StringBuffer("");
		
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			
			if(objPorcentaje != null){
				indice++;
				porcentaje = indice * 100 / sb.getRegistro().size();
				objPorcentaje.actualizarPorcentaje(porcentaje);
			}
			
			aL(sbCuerpo, '0', 2, TIPO_REGISTRO_DETALLE); // Tipo de registro
			aL(sbCuerpo, '0', 6, CODIGO_TRANSACCION); // Codigo transaccion
			aL(sbCuerpo, '0', 2, gu.getTipoCuenta(sb.getTipo_cuenta_empresa())); // Tipo producto origen
			aL(sbCuerpo, '0', 16, sb.getNum_cuenta_empresa()); // Cuenta origen
			aL(sbCuerpo, '0', 3, gu.getCodBanco(registro.getCod_banco_beneficiario())); // Entidad destino
			aL(sbCuerpo, '0', 2, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario())); // Tipo producto destino
			aL(sbCuerpo, '0', 16, registro.getNum_cuenta_beneficiario()); // Cuenta destino
			aL(sbCuerpo, '0', 9, secuencia); // Secuencia
			aL(sbCuerpo, '0', 18, gu.formato2Decimales(registro.getValor())); // Valor a transferir
			aL(sbCuerpo, '0', 16, CERO); // Numero factura
			aL(sbCuerpo, '0', 16, CERO); // Referencia 1
			aL(sbCuerpo, '0', 16, CERO); // Referencia 2
			aR(sbCuerpo, ' ', 30, registro.getNombre_beneficiario()); // Nombre
			aL(sbCuerpo, '0', 11, registro.getNum_identificacion_beneficiario()); // Numero documento
			aL(sbCuerpo, '0', 6, CERO); // Numero autorizacion
			aL(sbCuerpo, '0', 2, CERO); // Codigo respuesta
			aL(sbCuerpo, '0', 18, CERO); // Retencion contingente
			aL(sbCuerpo, '0', 2, BLANCO); // Relleno
			
			lineas.add(sbCuerpo.toString());
			r.append(LINE);
			r.append(sbCuerpo.toString());
			sbCuerpo.setLength(0);
			
			
			secuencia++;
			
		}

	}
	// -----------------------------------------------------

	/**
	 * Genera el final del archivo
	 */
	private void generarFinal(StringBuffer r, SalidaBanco sb) {

		StringBuffer crc = new StringBuffer("");
		String checkSum;
		
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			total_valor = total_valor.add(redondear(registro.getValor()));
		}
		
		for(String linea: lineas) {
			aR(crc, ' ', 15, obtenerCheckSum(linea));
		
		}
		
		checkSum = obtenerCheckSum(crc.toString());

		r.append(LINE);
		
		aL(r, '0', 2, TIPO_REGISTRO_FINAL); // Tipo registro
		aL(r, '0', 9, sb.getRegistro().size()); // Total registros
		aL(r, '0', 20, gu.formato2Decimales(total_valor)); // Valor total de la transmision
		aL(r, ' ', 15, checkSum); // Digito chequeo
		aL(r, ' ', 145, BLANCO); // Relleno

		r.append(LINE);
	}
	
	private String obtenerCheckSum(String linea) {  
		byte bytes[] = linea.getBytes();
		Checksum checksum = new CRC32();
		checksum.update(bytes, 0, bytes.length);
		long checksumValue = checksum.getValue();
		return Long.toHexString(checksumValue).toUpperCase();
	}
	

}

package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

public class GeneradorAVVillas implements Generador {

	private static final String FLAG_VALIDACION = "1";
	private static final String IND_ADDENDAS = "0";
	private static final String CODIGO_PLAZA_DESTINO = "2";
	private static final String CODIGO_TRANSACCION_AH = "32";
	private static final String CODIGO_TRANSACCION_CC = "22";
	private static final String CANAL = "4";
	private static final String TIPO_REGISTROS = "PPD";
	private static final String CODIGO_PLAZA_ORIGEN = CODIGO_PLAZA_DESTINO;
	private static final String NOMBRE_ORIGEN = "BTG";
	private static final String TIPO_ID_ORIGEN = "3";
	private static final String CODIGO_PRODUCTO = "PP";
	private static final String TIPO_REGISTRO_FINAL = "4";
	private static final String TIPO_REGISTRO_DETALLE = CODIGO_PLAZA_ORIGEN;
	private static final String CODIGO_ADQUIRIENTE = TIPO_REGISTRO_DETALLE;
	private static final String CODIGO_OFICINA = "088";
	private static final String TIPO_REGISTRO_ENCABEZADO = FLAG_VALIDACION;
	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;
	private BigDecimal total_valor = new BigDecimal(0);
	private IPorcentaje objPorcentaje;
	
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

		aL(r, '0', 1, TIPO_REGISTRO_ENCABEZADO); // Tipo registro
		aR(r, ' ', 17, sb.getNum_cuenta_empresa()); // Cuenta origen
		aL(r, '0', 1, gu.getTipoCuenta(sb.getTipo_cuenta_empresa())); // Tipo cuenta origen
		aL(r, ' ', 2, CODIGO_PRODUCTO); // Codigo de producto
		aL(r, '0', 8, formatoFecha.format(sb.getFecha_hoy())); // Fecha transmision
		aL(r, '0', 15, sb.getNit_empresa()); // Id Origen
		aL(r, '0', 2, TIPO_ID_ORIGEN); // Tipo id origen
		aL(r, ' ', 16, NOMBRE_ORIGEN); // Nombre origen
		aL(r, '0', 4, CODIGO_PLAZA_ORIGEN); // Codigo plaza origen
		aL(r, ' ', 3, TIPO_REGISTROS); // Tipo registros
		aL(r, '0', 6, sb.getNum_cargadeldia()); // Secuencia cliente
		aL(r, '0', 1, CANAL); // Canal
	}

	// -----------------------------------------------------
	
	/**
	 * genera el cuerpo del archivo
	 */
	private void generarCuerpo(StringBuffer r, SalidaBanco sb) {

		Integer indice = 0;
		
		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			
			//String observacion = (registro.getId_carga() + "/" + registro.getCod_negocio_cliente() + "-" + registro.getCod_producto_cliente() + "-" + registro.getCod_cuenta_cliente()).trim().replace(" ", "");
			
			if(objPorcentaje != null){
				indice++;
				porcentaje = indice * 100 / sb.getRegistro().size();
				objPorcentaje.actualizarPorcentaje(porcentaje);
			}
				
			r.append(LINE);
			
			String cod_transaccion = registro.getTipo_cuenta_beneficiario().equalsIgnoreCase("A")?CODIGO_TRANSACCION_AH:CODIGO_TRANSACCION_CC;
			
			aL(r, '0', 1, TIPO_REGISTRO_DETALLE); // Tipo registro
			aL(r, '0', 2, cod_transaccion); // Codigo transaccion
			aL(r, '0', 4, gu.getCodBanco(registro.getCod_banco_beneficiario())); // Codigo banco destino
			aL(r, '0', 4, CODIGO_PLAZA_DESTINO); // Codigo plaza destino
			aL(r, ' ', 15, registro.getNum_identificacion_beneficiario()); // Id destino
			aL(r, '0', 2, gu.getTipoDocumento(registro.getTipo_identificacion_beneficiario())); // Tipo id destino
			aR(r, ' ', 17, registro.getNum_cuenta_beneficiario()); // Cuenta destino
			aL(r, '0', 1, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario())); // Tipo cuenta destino
			aR(r, ' ', 22, registro.getNombre_beneficiario()); // Nombre cliente destino
			aL(r, '0', 1, IND_ADDENDAS); // Ind_mas_addendas
			aL(r, '0', 18, gu.formato2Decimales(registro.getValor())); // Valor
			aL(r, '0', 1, FLAG_VALIDACION); // Flag validacion
			aR(r, ' ', 16, registro.getObservacion()); // Referencia
			
		}

	}
	// -----------------------------------------------------

	/**
	 * Genera el final del archivo
	 */
	private void generarFinal(StringBuffer r, SalidaBanco sb) {

		for (RegistroEntradaBanco registro : sb.getRegistro()) {
			total_valor = total_valor.add(redondear(registro.getValor()));
		}

		r.append(LINE);
		
		aL(r, '0', 1, TIPO_REGISTRO_FINAL); // Tipo registro
		aL(r, '0', 8, sb.getRegistro().size()); // Total registros
		aL(r, '0', 18, gu.formato2Decimales(total_valor)); // Valor total de la transmision

	}

	// -----------------------------------------------------
	
	
}

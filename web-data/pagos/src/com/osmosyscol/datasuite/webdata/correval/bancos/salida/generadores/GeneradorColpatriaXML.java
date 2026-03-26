package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.zip.ZipUtil;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

public class GeneradorColpatriaXML implements Generador {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);
	private Integer porcentaje = 0;
	
	private IPorcentaje objPorcentaje;
	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("COLPATRIA");
	}
	
	public boolean limpiar() {
		return false;
	}

	// -----------------------------------------------------

	public File generar(SalidaBanco salidabanco, IPorcentaje porcentaje) {

		this.objPorcentaje = porcentaje;

		StringBuffer r = new StringBuffer("");
		generarEncabezadoXML(r);
		generarEncabezado(r, salidabanco);
		generarCuerpo(r, salidabanco);
		generarCierre(r, salidabanco);
		generarCierreXML(r);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

		String folder_path = System.getProperty("java.io.tmpdir") + File.separator + "tempZip";
		
		try {
			FileUtils.deleteDirectory(new File(folder_path));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String file_path = folder_path + File.separator + "colpatria_" + sdf.format(new Date()) + ".xml";
		String zip_path = System.getProperty("java.io.tmpdir") + File.separator + "tempZip.zip";
		File f = new File(file_path);
		
		try {
			FileUtils.writeStringToFile(f, r.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		File z = null;
		try {
			ZipUtil.createZip(folder_path, zip_path);
			z = new File(zip_path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			FileUtils.deleteDirectory(new File(folder_path));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return z;
	}

	// -----------------------------------------------------
	// Utilidades

	private void generarCierreXML(StringBuffer r) {
		r.append("</ROOT>");
		
	}

	private void generarEncabezadoXML(StringBuffer r) {
		r.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<ROOT>\n");
		
	}

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
		StringBuffer t = new StringBuffer();
		
		r.append("\t<ENCABEZADO");
		aL(t, '0', 5, "1");
		r.append( " REGISTRO=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		aL(t, '0', 2, "01");
		r.append(" TIPO_REGISTRO=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		aR(t, ' ', 8, dateformat.format(sb.getFecha_hoy()));
		r.append(" FECHA_RECIBO=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		aL(t, '0', 11, sb.getNit_empresa());
		r.append(" NIT_CLIENTE=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		
		aL(t, ' ', 10, StringEscapeUtils.escapeXml11(sb.getNombre_empresa()));
		r.append(" CLAVE=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		aL(t, '0', 6, (sb.getRegistro().size() + 2));
		r.append(" REGISTROS_ENVIADOS=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		r.append(" TIPO_CARGO=\"2\"");
		
		aL(t, '0', 4, "0002");
		r.append(" OFICINA_PAGO=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		aL(t, '0', 12, sb.getNum_cuenta_empresa());
		r.append(" NUMERO_CUENTA=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		r.append(" RESULTADO_VALIDACION=\"0\"");
		r.append(" NUMERO_LOTE=\"0\"");
		r.append(" MOTIVO_RECHAZO=\"\"");
		r.append(" ID_PROCESO=\"0\"");
		r.append(" USUARIO=\"\"");
		r.append("/>\n");
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
			
			boolean esCuentaColpatria = cod_banco.equals(registro.getCod_banco_beneficiario());
			
			StringBuffer t = new StringBuffer();
			
			r.append("\t<REGISTRO");
			
			aL(t, '0', 5, (num + 1));
			r.append( " REGISTRO=\"" + t +"\"");
			t = t.delete(0, t.length());	
			
			aL(t, ' ', 2, "02");
			r.append( " TIPO_REGISTRO=\"" + t +"\"");
			t = t.delete(0, t.length());	
			
			aL(t, '0', 12, esCuentaColpatria ? registro.getNum_cuenta_beneficiario() : "0");
			r.append( " NUMERO_CUENTA=\"" + t +"\"");
			t = t.delete(0, t.length());	
			
			aL(t, '0', 11, registro.getNum_identificacion_beneficiario());
			r.append( " NIT_BENEFICIARIO=\"" + t +"\"");
			t = t.delete(0, t.length());	
			
			aR(t, ' ', 40, registro.getNombre_beneficiario());
			r.append( " NOMBRE_BENEFICIARIO=\"" + t +"\"");
			t = t.delete(0, t.length());	
			
			aL(t, ' ', 3, esCuentaColpatria ? "902" : "911");
			r.append( " CODIGO_TRANSACCION=\"" + t +"\"");
			t = t.delete(0, t.length());	
			
			aL(t, ' ', 2, "02");
			r.append( " TIPO_CARGO=\"" + t +"\"");
			t = t.delete(0, t.length());	
			
			aL(t, '0', 15, gu.formato2Decimales(registro.getValor()));
			r.append( " VALOR_NETO=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, '0', 10, num);
			r.append( " NUMERO_FACTURA=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, '0', 6, CERO);
			r.append( " NUMERO_CONTROL_PAGO=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, '0', 15, CERO);
			r.append( " VALOR_RETENCION=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, '0', 15, CERO);
			r.append( " VALOR_IVA=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, ' ', 8, dateformat.format(sb.getFecha_grupo()));
			r.append( " FECHA_PAGO=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, '0', 10, CERO);
			r.append( " NUMERO_NOTA_DEBITO=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, '0', 15, CERO);
			r.append( " VALOR_NOTA_DEBITO=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, '0', 8, gu.getCodBanco(registro.getCod_banco_beneficiario()));
			r.append( " CODIGO_BANCO=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, '0', 17, (!esCuentaColpatria) ? registro.getNum_cuenta_beneficiario() : "0");
			r.append( " NUMERO_CUENTA_ABONO=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, '0', 1, gu.getTipoCuenta(registro.getTipo_cuenta_beneficiario()));
			r.append( " CLASE_CUENTA=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, ' ', 1, gu.getTipoDocumento(registro.getTipo_identificacion_beneficiario()));
			r.append( " TIPO_DOCUMENTO=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			aL(t, ' ', 80, observacion);
			r.append( " ADENDA=\"" + t +"\"");
			t = t.delete(0, t.length());
			
			r.append(" MOTIVO_RECHAZO=\"\"");
			r.append("/>\n");
			
			num++;
		}

	}

	// -----------------------------------------------------
	/**
	 * Genera el encabezado del archivo
	 */
	private void generarCierre(StringBuffer r, SalidaBanco sb) {
		StringBuffer t = new StringBuffer();
		
		r.append("\t<TOTAL");
		aL(t, '0', 5, (sb.getRegistro().size() + 2));
		r.append( " REGISTRO=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		aL(t, '0', 2, "03");
		r.append( " TIPO_REGISTRO=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		aL(t, '0', 6, (sb.getRegistro().size() + 2));
		r.append( " NUMERO_REGISTRO=\"" + t +"\"");
		t = t.delete(0, t.length());
		
		r.append("/>\n");

	}
	// -----------------------------------------------------

}

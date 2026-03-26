package com.osmosyscol.datasuite.webdata.correval.cargueplano;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorUtils;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;
import com.osmosyscol.datasuite.webdata.utils.DocumentoUtils;

public class LectorArchivoPlanoBogota implements LectorArchivoPlano {

	private String cod_banco = getCodBanco();
	private GeneradorUtils gu = new GeneradorUtils(cod_banco);

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("BOGOTA");
	}

	public List<Retiro> leerRetiros(String archivo) {

		try {

//			Integer[] longitudes = new Integer[] { 1, 1, 11, 40, 1, 1, 17, 18, 1, 3, 3, 4, 9, 1, 70, 1, 10, 1, 48, 1, 8};
//			String[][] lineas = LectorArchivoPlanoUtils.leerArchivo(archivo, longitudes);
			
			List<String> lines = FileUtils.getContentFileList(archivo);
			String[][] lineas= new String [lines.size()][9];
			for (int i = 0; i < lines.size(); i++){
				String [] campos= lines.get(i).split(",");
				for (int j = 0; j < campos.length; j++) {
					lineas[i][j]=campos[j];
				}
			}
			
			if (lineas == null || lineas.length < 2) {
				return null;
			}

			List<Retiro> retiros = new ArrayList<Retiro>();

			for (int i = 1; i < lineas.length; i++) {
				String tipo=lineas[i][0].trim();
				String estadoBeneficiario=lineas[i][8].trim();
				if(tipo.equals("R") && estadoBeneficiario.equals("Activo")){
					Retiro retiro = convLineaARetiro(StringUtils.trim(lineas[i]));
					retiros.add(retiro);
				}
			}

			return retiros;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}
	public static String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
	private Retiro convLineaARetiro(String linea[]) {
		Retiro retiro = new Retiro();
		System.out.println(linea);
		retiro.setNombre_beneficiario(linea[7].trim());
		retiro.setIdentificacion_beneficiario(DocumentoUtils.ajustarNumeroDocumento(linea[5]));
		String tipoDocumento=linea[6];
		if(tipoDocumento.contains("NIT")){
			tipoDocumento="NIT";
		}
		retiro.setTipo_documento_beneficiario(gu.getTipoDocumentoInterno(stripAccents(linea[6])));

		String codBancoLeido = linea[4]; 
		String codBanco = gu.getCodBancoInterno(codBancoLeido);

		if (StringUtils.esNoVacio(codBanco)) {
			retiro.setCod_banco(codBanco);
		}
		
		if (retiro.getCod_banco() == null){
			retiro.setCod_banco(codBancoLeido);
		}

//		BigDecimal valor = LectorArchivoPlanoUtils.obtenerNumero2Decimales(linea[3]);
		BigDecimal valor = new BigDecimal(linea[3]);
		retiro.setValor(valor);
		retiro.setCuenta_beneficiario(linea[1].trim());

		retiro.setTipo_cuenta_beneficiario_banco(linea[2]);

		String cuenta = gu.getTipoCuentaInterno(linea[2]);
		System.out.println("Cuentaaaaaa: "+cuenta);
		if(cuenta!=null)
		{
			retiro.setTipo_cuenta_beneficiario(new Long(cuenta));
		}

//		String observacion = linea[14].trim();
//		retiro.setObservacion(observacion);
		retiro.setObservacion("");
		
		return retiro;
	}

}

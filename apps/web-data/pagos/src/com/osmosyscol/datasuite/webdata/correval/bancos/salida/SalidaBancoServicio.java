package com.osmosyscol.datasuite.webdata.correval.bancos.salida;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.Generador;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorAVVillas;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorAVVillasNomina;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorAgrario;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorBBVA;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorBancoBogota;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorBancolombia;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorCESANTIAS;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorCajaSocial;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorCitibank;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorColpatria;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorColpatriaXML;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorDavivienda;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorFalabella;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorITAU;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorNEQUI;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorOccidente;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorPILA;
import com.osmosyscol.datasuite.webdata.correval.grupogiro.GrupoGiroServicio;

public class SalidaBancoServicio {
	
	private static SalidaBancoServicio instancia;
	private static String CARACTERESESPECIALES = "¡…Õ”⁄·ÈÌÛ˙ƒÀœˆ‹‰ÎÔˆ¸¿»Ã“Ÿ‡ËÏÚ˘¬ Œ‘€‚ÍÓÙ˚Ò—Â„√‘’ı~^{}[]&|¨∞«™∫øÆΩº´ª¶©•§–∂ﬂ˛ﬁ±æß˜∞<>\"";
	private static String CARACTERESHOMOLOGADOS = "AEIOUaeiouAEIOUaeiouAEIOUaeiouAEIOUaeiounNaaAOOoo                                    ";

	private SalidaBancoServicio() {
	}

	public static SalidaBancoServicio getInstance() {
		if (instancia == null) {
			instancia = new SalidaBancoServicio();
		}
		return instancia;
	}

	public File generarArchivo(Integer id_archivo, IPorcentaje porcentaje) {
		SalidaBanco salidaBanco = GrupoGiroServicio.getInstance().crearSalidaBanco(id_archivo);
		return generarArchivoBanco(salidaBanco, porcentaje);

	}

	// ---------------------------------------------------------------
	/**
	 * Genera el contenido del archivo del banco
	 */
	public File generarArchivoBanco(SalidaBanco salidabanco, IPorcentaje porcentaje) {

		List<Generador> generadores = new ArrayList<Generador>();
		generadores.add(new GeneradorBancolombia());
		generadores.add(new GeneradorBancoBogota());
		//generadores.add(new GeneradorColpatria());
		generadores.add(new GeneradorOccidente());
		generadores.add(new GeneradorDavivienda());
		generadores.add(new GeneradorFalabella());
		generadores.add(new GeneradorCitibank());
		generadores.add(new GeneradorBBVA());
		generadores.add(new GeneradorAgrario());
		generadores.add(new GeneradorCajaSocial());
		generadores.add(new GeneradorPILA());
		generadores.add(new GeneradorCESANTIAS());
		generadores.add(new GeneradorNEQUI());
		generadores.add(new GeneradorColpatriaXML());
		//generadores.add(new GeneradorAVVillas());
		generadores.add(new GeneradorAVVillasNomina());
		generadores.add(new GeneradorITAU());
		
		// -----------------------------------------------------------------------

		String cod_banco = salidabanco.getCod_banco_empresa();

		for (Generador generador : generadores) {
			if (cod_banco.equals(generador.getCodBanco())) {

				File data = generador.generar(salidabanco, porcentaje);

				if (generador.limpiar()) {
					data = homologarCaracteresEspeciales(data);
				}

				return data;
			}
		}

		return null;
	}

	public File homologarCaracteresEspeciales(File file) {
		StringBuffer r = new StringBuffer();
		
		try {
			r = readFromFile(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// recorrer el string buffer y si encuentra una letra en el arreglo de CARACTERESESPECIALES entonces la reemplaza
		for (int index = 0; index < r.length(); index++) {
			String letra = r.substring(index, index + 1);
			int ubicacion = CARACTERESESPECIALES.indexOf(letra);
			if (ubicacion != -1) {
				r.replace(index, index + 1, CARACTERESHOMOLOGADOS.substring(ubicacion, ubicacion + 1));
			}
		}
		
		File f = FileUtils.newFile();
		try {
			FileUtils.writeStringToFile(f , r.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}
	
    public static StringBuffer readFromFile(File pFilename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(pFilename));
        StringBuffer data = new StringBuffer();
        int c = 0;
        while ((c = in.read()) != -1) {
            data.append((char)c);
        }
        in.close();
        return data;
    }
}

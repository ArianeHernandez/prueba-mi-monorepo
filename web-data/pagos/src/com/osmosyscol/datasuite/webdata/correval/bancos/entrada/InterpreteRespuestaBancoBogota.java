package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.osmosyscol.commons.log.SimpleLogger;

import au.com.bytecode.opencsv.CSVReader;
import co.htsoft.commons.lang.StringUtils;

public class InterpreteRespuestaBancoBogota implements IInterpreteRespuestaBanco {

	private static String nombreBanco = "Bogotá";

	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {

		try {

			CSVReader reader = new CSVReader(new FileReader(rutaArchivo), ',');

			List<String[]> data = reader.readAll();

			reader.close();

			Set<String> retiros_encontrados = new HashSet<String>();

			List<RespuestaBanco> listaRespuestaBancos = new ArrayList<RespuestaBanco>();

			for (int i=1; i<data.size(); i++) {
				
				String [] d = data.get(i);
				
				String estado_respuesta = StringUtils.trimToNull(d[6]);

				String num_cuenta = StringUtils.only(d[2], "0123456789");

				String num_doc = Long.parseLong(StringUtils.only(d[1], "0123456789"), 10) + "";

				String valor = StringUtils.only(d[4], "0123456789.").replace(".00", "");

				String estadoBanco = estado_respuesta;
				
				Integer filaArchivo = i;
				
				System.out.println(estado_respuesta + "," + num_cuenta + "," + num_doc + "," + valor);

				RegistroEstado registroestado = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerIDRetiroParaActualizarRespuestaBanco(id_banco, null, num_doc, num_cuenta, valor, retiros_encontrados);
				listaRespuestaBancos.add(ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestaBanco(registroestado, estadoBanco, id_banco, filaArchivo, num_cuenta, num_doc, valor));

			}

			return listaRespuestaBancos;

		} catch (Exception e) {
			SimpleLogger.setError("validarArchivo", e);
			return null;
		}

	}

	public Boolean validarArchivo(String rutaArchivo, String id_banco) {
		try {

			CSVReader reader = new CSVReader(new FileReader(rutaArchivo), ',');

			List<String[]> data = reader.readAll();

			reader.close();

			if (StringUtils.trimToEmpty(data.get(0)[6]).equalsIgnoreCase("Estado del Pago")) {
				return true;
			}

		} catch (Exception e) {
			SimpleLogger.setError("validarArchivo", e);
		}

		return false;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

}

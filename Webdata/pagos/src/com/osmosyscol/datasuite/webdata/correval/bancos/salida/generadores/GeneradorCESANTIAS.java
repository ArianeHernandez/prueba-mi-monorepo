package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.lang.StringUtils;

public class GeneradorCESANTIAS implements Generador {

	private IPorcentaje objPorcentaje;

	// -----------------------------------------------------

	public String getCodBanco() {
		return GeneradorUtils.getCodBancoByDesc("CESANTIAS");
	}

	public boolean limpiar() {
		return false;
	}

	// -----------------------------------------------------

	private SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyyMMdd");

	public File generar(SalidaBanco salidabanco, IPorcentaje porcentaje) {

		this.objPorcentaje = porcentaje;

		StringBuffer r = new StringBuffer("");

		List<RegistroEntradaBanco> registros = salidabanco.getRegistro();

		for (RegistroEntradaBanco registro : registros) {

			if (r.length() > 0) {
				r.append(LINE);
			}

			// obtiene la informacion de la persona de la carga
			Carga carga = CargaServicio.getInstance().obtenerCarga(new Integer(registro.getId_carga()));

			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(carga.getId_usuario());

			// TODO: ESTO SOLO FUNCIONA PARA CARGAS INDIVIDUALES

			Map<String, String> mapTD = new HashMap<String, String>();
			mapTD.put("1", "CC");
			mapTD.put("2", "TI");
			mapTD.put("3", "CE");
			mapTD.put("4", "PA");
			mapTD.put("10", "NI");

			String tipo_documento = mapTD.get(usuario.getTipo_documento());
			String numero_documento = usuario.getIdentificacion();
			BigDecimal valor = registro.getValor();
			String fecha_giro = dateformat2.format(salidabanco.getFecha_grupo());

			String codigo = StringUtils.onlyNumbers(DS_SqlUtils.queryForObject(String.class, "select $retiros.referencia 1$ from $retiros$ r where idcarga = " + carga.getId_carga()));
			
			r.append(carga.getId_carga() + ";" + tipo_documento + ";" + numero_documento + ";" + codigo + ";" + valor.toPlainString() + ";" + fecha_giro);

		}

		if (objPorcentaje != null) {
			objPorcentaje.actualizarPorcentaje(100);
		}

		File f = FileUtils.newFile();
		try {
			FileUtils.writeStringToFile(f , r.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	// -----------------------------------------------------

}

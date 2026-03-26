package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.utils.DatoCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.utils.GrupoDatos;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class DatosJSONServicio {

	public GrupoDatos obtenerdatos(String id_persona, String id_estructura, String id_campo, String valor_busqueda) {

		GrupoDatos ret = new GrupoDatos();
		try {
			List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(new Integer(id_estructura));

			String[] indices = new String[campos.size() + 2];

			indices[0] = "ID";
			indices[1] = "FECHA";

			for (int i = 0; i < campos.size(); i++) {
				indices[i + 2] = "C" + campos.get(i).getId_campo();
			}

			ret.setIndices(indices);

			List<Map<String, Object>> datos = CargaServicio.getInstance().obtenerDatos(new Integer(id_persona), new Integer(id_estructura), new Integer(id_campo), valor_busqueda);

			if (datos == null) {
				datos = new ArrayList<Map<String, Object>>();
			}

			DatoCarga[] datoscarga = new DatoCarga[datos.size()];

			for (int j = 0; j < datos.size(); j++) {
				String ddatos[] = new String[indices.length];

				Map<String, Object> map = datos.get(j);

				for (int i = 0; i < indices.length; i++) {
					Object dato = map.get(indices[i]);
					if(dato != null){
						ddatos[i] = dato.toString();
					}else{
						ddatos[i] = "";
					}
				}

				DatoCarga datoCarga = new DatoCarga();
				datoCarga.setCantidad(indices.length);
				datoCarga.setDato(ddatos);
				datoscarga[j] = datoCarga;
			}

			ret.setDatos(datoscarga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		return ret;
	}

}

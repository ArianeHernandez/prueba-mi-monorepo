package com.osmosyscol.datasuite.modelatos.logica.servicios.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampoValor;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoEstilo;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoEstilo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoRequest;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoRequestData;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoResponseData;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.TipoEstiloServicio;

public class CampoJSONServicio {

	public Campo[] obtenerCamposPorEstructura(Integer id_estructura) {

		List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(id_estructura);

		Campo[] ret = null;

		if (campos != null) {
			ret = new Campo[campos.size()];
			int i = 0;
			for (Campo campo : campos) {
				ret[i++] = campo;
			}
		}

		return ret;
	}
	
	public ValidacionFormatoResponseData validarCamposFormato(Integer id_formato, FormatoCampoValor[] campos, Integer id_formato_campo, Integer id_carga ){
		List<FormatoCampoValor> listaCampos = Arrays.asList(campos);
		ValidacionFormatoRequestData data = new ValidacionFormatoRequestData();
		data.setListadoCampos(listaCampos);
		data.setIdCarga(id_carga);
		ValidacionFormatoRequest request = new ValidacionFormatoRequest();
		request.setData(data);
		return CampoServicio.getInstance().validarCamposFormato(id_formato, request, id_formato_campo);
	}
	
	public String obtenerNombreCampoPorIdCampo(Integer id_campo){
		Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);
		return (campo != null) ? campo.getNombre() : null;
	}
	
	public Map<String, Map<String, String>> obtenerEstilosFormato(Integer id_formato){
		List<FormatoEstilo> estilos = FormatoServicio.getInstance().obtenerFormatoEstilos(id_formato);
		if (estilos != null && !estilos.isEmpty()){
			Map<String, Map<String, String>> result = new HashMap<String, Map<String,String>>();
			for (FormatoEstilo estilo : estilos){
				Map<String, String> elemento = new HashMap<String, String>();
				String css = estilo.getEstilo().replaceAll("(\\r|\\n)", "");
				css = StringUtils.trim(css);
				String[] properties = StringUtils.split(css, ";");
				for (String property : properties){
					property = StringUtils.trim(property);
					String[] set = StringUtils.split(property, ":");
					elemento.put(StringUtils.trim(set[0]), StringUtils.trim(set[1]));
				}
				TipoEstilo tipoEstilo = TipoEstiloServicio.getInstance().obtenerTipoEstilo(estilo.getId_tipo_estilo());
				result.put(tipoEstilo.getEtiqueta(), elemento);
			}
			return result;
		}
		return null;
	}

}

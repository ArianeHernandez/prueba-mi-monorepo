package com.osmosyscol.datasuite.webdata.correval.cargaexcel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class CargadorClientes implements Cargador {

	public void cargar(String ruta, Integer idCargue, Map<Integer, Object> mapaRespuestas) {

		DaoManager daoManager = null;

		List<String> mensajes = new ArrayList<String>();
		Integer i = 0;
		try {

			InputStream inp = new FileInputStream(ruta);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			HSSFSheet sheet = wb.getSheet("CLIENTE");
			if (sheet == null) {
				sheet = wb.getSheetAt(0);
			}

			daoManager = DaoConfig.getDaoManager();

			PersonaServicio personaServicio = PersonaServicio.getInstance();
			UsuarioServicio usuarioServicio = UsuarioServicio.getInstance();

			String[][] buffer = CargaExcelServicio.getInstance().excelToBuffer(sheet, 6);
			DtoInfo info = new DtoInfo();

			info.setTotalRegistros(buffer.length - 1);
			mapaRespuestas.put(idCargue, info);

			if (buffer != null) {

				Integer rtaGuardarUsuario = null;
				Persona persona = null;
				Integer insertados = 0;
				for (i = 1; i < buffer.length; i++) {
					persona = leerPersona(mensajes, i, buffer[i]);
					persona = personaServicio.guardarPersona(persona, daoManager);

					if (persona != null) {

						Usuario usuario = new Usuario();
						usuario.setActivo("S");
						usuario.setId_persona(persona.getId_persona());

						rtaGuardarUsuario = usuarioServicio.guardarUsuarioTrans(usuario, daoManager);

						if (rtaGuardarUsuario == null || rtaGuardarUsuario <= 0) {
							mensajes.add("No fue posible crear el Cliente para la persona: " + persona.getIdentificacion() + " - " + persona.getNombre());
						} else {
							insertados++;
						}
					}
					info.setRegistrosCargados(i);

				}
				i--;
				if (insertados > 0) {
					mensajes.add("Se leyeron " + i + " registros del archivo");
					mensajes.add("Se actualizaron o insertaron " + insertados);
				} else {
					mensajes.add("No se actualizaron registros");
				}
			}
			SimpleLogger.setInfo(mensajes.toString());
		} catch (Exception e) {
			mensajes.add("Ocurrió un error al cargar la información, cargando el registro " + i);
			SimpleLogger.setError("Ha ocurrido un error", e);
		} 
		mapaRespuestas.put(idCargue, mensajes);

	}

	private Persona leerPersona(List<String> mensajes, Integer i, String[] fila) {

		// Identificacion
		String dato = fila[0];
		if (StringUtils.esVacio(dato)) {
			mensajes.add(i + " - El NIT es vacio");
			return null;
		}

		Persona persona = new Persona();

		persona.setActivo("S");
		persona.setIdentificacion(dato);

		// Nombre
		dato = fila[3];
		if (StringUtils.esVacio(dato)) {
			mensajes.add(i + " - El nombre es vacio");
			return null;
		}
		persona.setNombre(dato);

		// Tipo de documento
		dato = fila[2];
		Integer tipoDocumento = null;

		tipoDocumento = CargaExcelServicio.leerTipoDocumento(dato);

		if (tipoDocumento == null) {
			mensajes.add(i + " - Tipo de documento no válido: " + dato);
			return null;
		}
		persona.setTipo_documento(tipoDocumento);

		// Tipo de persona
		dato = fila[4];
		if ("NATURAL".equals(dato)) {
			dato = "N";
			persona.setGenero(fila[5]);
			persona.setApellido("--");

		} else if ("CORPORATIVO".equals(dato)) {
			dato = "J";
		} else {
			mensajes.add(i + " - Tipo de persona no válido: " + dato);
			return null;
		}

		persona.setTipo_persona(dato);
		
		// si es juridico verificar que el NIT tenga un tamanio de 9 y q el nit sea numerico
		if(persona.getTipo_persona().equalsIgnoreCase("J") && persona.getIdentificacion().length() != 9 && !StringUtils.isNumeric(persona.getIdentificacion())){
			return null;
		}

		return persona;
	}

	public String getCodigo() {
		return "1";
	}

}

package com.hts.registro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.cocoon.environment.SourceResolver;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import co.htsoft.commons.lang.NumberUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.RolServicio;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class RegistroUsuariosAdministrativos {

	public static void cargar() {
		try {
			SimpleLogger.setDebug("Cargando usuarios");
			InputStream inp = new FileInputStream(InitApp.class.getResource("/").getPath() + "./usuario.xls");
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			HSSFSheet sheet = wb.getSheetAt(0);
			Integer i = 8;
			while (i < 100) {
				HSSFRow fila1 = getRow(sheet, i);

				Persona persona = new Persona();
				if (fila1 != null && fila1.getCell(0) != null) {

					persona.setNombre(fila1.getCell(0).getRichStringCellValue()
							.toString());
					persona.setApellido(fila1.getCell(1).getRichStringCellValue()
							.toString());
					persona.setCorreo(fila1.getCell(4).getRichStringCellValue()
							.toString());
					persona.setGenero("");
					persona.setTipo_documento(1);
					double ced = fila1.getCell(3).getNumericCellValue();
					Double d = new Double (ced);
					persona.setIdentificacion(""
							+ d.longValue());
					persona.setTipo_persona("N");
					
					SimpleLogger.setInfo("Persona craeda: " + persona);
					System.out.println(persona);
					Credencial credencial = new Credencial();
					Integer id_usuario = null;
					credencial.setId_usuario(id_usuario);
					credencial.setLogin(fila1.getCell(2).getRichStringCellValue()
							.toString());
					SimpleLogger.setInfo(credencial.getLogin());
					Integer rol = new Double(fila1.getCell(7).getNumericCellValue()).intValue();
					cargarusuario(credencial, persona, rol);
				}
				i++;

			}
		} catch (Exception e) {
			SimpleLogger.setError("error ", e);
		}
		//
	}

	private static HSSFRow getRow(HSSFSheet sheet, int index) {
		HSSFRow row = sheet.getRow(index);

		return row;
	}

	private static void cargarusuario(Credencial credencial, Persona persona, Integer rol) {
		Integer id_usuario = null;
		Integer salida = 0; // error
		DaoManager daoManager = null;
		try {

			daoManager = DaoConfig.getDaoManager();

			daoManager.startTransaction();

			Boolean usuarioNuevo = (credencial.getId_persona() == null);

			Persona personarta = PersonaServicio.getInstance().guardarPersona(
					persona, daoManager);

			Administrativo administrativo = new Administrativo();

			administrativo.setId_usuario(id_usuario);

			if (persona != null && personarta.getId_persona() != null) {

				persona.setId_persona(personarta.getId_persona());

				salida = 3; // no se puede guardar usuario

				if (usuarioNuevo) {

					credencial.setId_persona(personarta.getId_persona());

					credencial = PersonaServicio.getInstance()
							.guardarCredencial(credencial, daoManager);

					if (credencial == null) {
						System.out.println(4);
						; // No se puede guardar la credencial
					}
				} else {
					credencial = PersonaServicio.getInstance()
							.obtenerCredencialPersonaUsuario(
									persona.getId_persona(), id_usuario);
				}
				salida = 6; // no se pueden guardar los roles
				administrativo.setId_persona(persona.getId_persona());
				Integer id_administrativo = AdministrativoServicio
						.getInstance().guardarAdministrativoTrans(
								administrativo, daoManager);
				if (id_administrativo == null) {
					System.out.println(salida);
				}

				List<Integer> roles = new ArrayList<>();
				roles.add(rol );
				Boolean exito = RolServicio.getInstance()
						.guardarRolesAdministrativo(id_administrativo, roles);

				if (!exito) {
					System.out.println(salida);
				}

				salida = 5; // No se puede enviar correo

				SourceResolver resolver = null;
				exito = PersonaServicio
						.getInstance()
						.generarUsuarioyEnviarCorreo(
								persona,
								PersonaServicio
										.getInstance()
										.obtenerRoles(
												"S",
												null,
												null,
												null,
												id_usuario == null ? Constantes.ROL_ADMINISTRATIVO
														: Constantes.ROL_ADMINISTRATIVOCL),
								resolver, usuarioNuevo, credencial);

				if (exito) {
					daoManager.commitTransaction();
					salida = 1; // Exito
				}

				System.out.println(salida);
				;
			} else {
				salida = 2; // No se puede guardar usuario
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio guardarPersonaRol", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		System.out.println(salida);
	}
}

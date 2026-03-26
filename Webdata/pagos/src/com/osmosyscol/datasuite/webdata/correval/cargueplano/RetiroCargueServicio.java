package com.osmosyscol.datasuite.webdata.correval.cargueplano;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.ibatis.dao.client.DaoManager;
import com.itosmosys.firmadigital.servicios.FirmaServicio;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorUtils;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.InfoRetiro;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class RetiroCargueServicio {

	private static RetiroCargueServicio instance;

	private RetiroCargueServicio() {
	}

	public static RetiroCargueServicio getInstance() {
		if (instance == null) {
			instance = new RetiroCargueServicio();
		}
		return instance;
	}

	// ---------------------

	public static Map<Integer, List<Retiro>> retirosCarga = new HashMap<Integer, List<Retiro>>();

	public static Map<Integer, Boolean> retirosExitoso = new HashMap<Integer, Boolean>();
	public static Map<Integer, String> retirosCodError = new HashMap<Integer, String>();
	public static Map<Integer, List<String>> retirosMensajesError = new HashMap<Integer, List<String>>();
	public static Map<Integer, Integer> retirosCargaPorcentaje = new HashMap<Integer, Integer>();

	public Integer obtenerPorcentajeProceso(Integer id_carga) {

		Integer avance = retirosCargaPorcentaje.get(id_carga);

		if (avance == null) {
			avance = 100;
		}

		return avance;
	}

	public InfoRetiro obtenerInfoRetiro(Integer id_carga) {

		return new InfoRetiro(retirosCarga.get(id_carga), retirosExitoso.get(id_carga), retirosCodError.get(id_carga), retirosMensajesError.get(id_carga));

	}

	public List<Retiro> obtenerRetirosPaginado(Integer id_carga, Integer num_pagina) {

		List<Retiro> listado = retirosCarga.get(id_carga);

		if (listado != null) {

			Integer desde = (num_pagina - 1) * Constantes.PAGINACIONLISTADO;
			Integer hasta = desde + Constantes.PAGINACIONLISTADO;

			if (hasta > listado.size()) {
				hasta = listado.size();
			}
			return listado.subList(desde, hasta);
		}

		return null;
	}

	public Integer cargarRetiros(String desc_banco, Session session, Integer id_proceso, Request request) {
		try {
			String ip_usuario = request.getRemoteHost();

			Integer id_persona = (Integer) session.getAttribute("id_persona");
			Integer id_usuario = (Integer) session.getAttribute("id_usuario");

			if (id_usuario == null) {
				id_usuario = new Integer(session.getAttribute("var.id_usuario").toString());
			}

			Integer id_negocio = (Integer) session.getAttribute("var.id_negocio_carga");

			String archivo = (String) session.getAttribute("ruta_plano");
			String id_archivo_firmado_plano = (String) session.getAttribute("id_archivo_firmado_plano");

			if (StringUtils.esNoVacio(archivo)) {

				Integer id_formato = new Integer(session.getAttribute("var.id_formato").toString());
				Integer id_carga = CargaServicio.getInstance().crearCargaArchivo("Cargue Archivo " + desc_banco.toLowerCase(), id_persona, id_formato, id_proceso, id_usuario, id_negocio, ip_usuario);
				String codigo = GeneradorUtils.getCodBancoByDesc(desc_banco);

				session.setAttribute("id_carga", id_carga);

				RetiroCargueServicio.retirosCargaPorcentaje.put(id_carga, 0);

				Thread thread = new Thread(new CargaRetiroProceso(session, id_carga, codigo, id_archivo_firmado_plano, archivo));
				thread.start();

				return id_carga;
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error,", e);
		}
		return null;

	}

	public List<Retiro> leerRetiros(String archivo, LectorArchivoPlano lector, Integer id_carga, Integer id_usuario) {

		List<Retiro> retiros = lector.leerRetiros(archivo);

		RetiroCargueServicio.retirosCargaPorcentaje.put(id_carga, 1);

		RetiroDao retiroDao = new RetiroDao();

		DaoManager daoManager = DaoConfig.getDaoManager(2);
		CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
		daoManager.startTransaction();
		try {

			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_usuario);

			String identificacion_usuario = StringUtils.trimToEmpty(usuario.getIdentificacion());

			if (retiros == null) {
				return null;
			}

			Integer i = 1;

			List<String> codigosBanco = new ArrayList<String>();

			List<Map<String, Object>> bancos = obtenerBancos();

			for (Map<String, Object> registro : bancos) {
				codigosBanco.add(registro.get("ID").toString());
			}

			Boolean errorDatos = false;

			RetiroCargueServicio.retirosMensajesError.put(id_carga, new ArrayList<String>());

			for (Retiro retiro : retiros) {

				// -- Validaciones Fijas..

				Boolean errorRegistro = false;

				if (identificacion_usuario.equals("" + retiro.getIdentificacion_beneficiario())) {
					RetiroCargueServicio.retirosCodError.put(id_carga, "MCLIENTE");
					RetiroCargueServicio.retirosMensajesError.get(id_carga).add("El beneficiario del Pago no puede ser el mismo cliente.");

					errorRegistro = true;
				}

				if (retiro.getValor() == null || retiro.getValor().compareTo(BigDecimal.ZERO) < 0) {
					RetiroCargueServicio.retirosCodError.put(id_carga, "VALORINCORRECTO");
					RetiroCargueServicio.retirosMensajesError.get(id_carga).add("El valor del retiro debe ser mayor a cero.");

					errorRegistro = true;
				}

				if (retiro.getCod_banco() == null || !codigosBanco.contains(retiro.getCod_banco())) {
					RetiroCargueServicio.retirosCodError.put(id_carga, "BANCOINCORRECTO");
					RetiroCargueServicio.retirosMensajesError.get(id_carga).add("El codigo del banco " + retiro.getCod_banco() + " no se encuentra definido.");

					errorRegistro = true;
				}
				
				if(retiro.getCuenta_beneficiario() == null || !StringUtils.isNumeric(retiro.getCuenta_beneficiario())) {
					RetiroCargueServicio.retirosCodError.put(id_carga, "CUENTAINCORRECTA");
					RetiroCargueServicio.retirosMensajesError.get(id_carga).add("Inconsistencia en la cuenta del beneficiario, Cuenta: " + retiro.getCuenta_beneficiario());

					errorRegistro = true;
				}
				
				if(retiro.getTipo_cuenta_beneficiario() == null ) {
					RetiroCargueServicio.retirosCodError.put(id_carga, "TIPOCUENTAINCORRECTA");
					RetiroCargueServicio.retirosMensajesError.get(id_carga).add("Inconsistencia en el tipo de cuenta del beneficiario, Tipo Cuenta: " + retiro.getTipo_cuenta_beneficiario());

					errorRegistro = true;
				}

				if(retiro.getTipo_documento_beneficiario() == null ||  retiro.getTipo_documento_beneficiario().equals("")) {
					RetiroCargueServicio.retirosCodError.put(id_carga, "TIPODOCUMENTOINCORRECTO");
					RetiroCargueServicio.retirosMensajesError.get(id_carga).add("Inconsistencia en el tipo de documento del beneficiario, Tipo Documento: " + retiro.getTipo_documento_beneficiario());

					errorRegistro = true;
				}
				
				if(retiro.getIdentificacion_beneficiario() == null ) {
					RetiroCargueServicio.retirosCodError.put(id_carga, "DOCUMENTOBENEFICIARIOINCORRECTO");
					RetiroCargueServicio.retirosMensajesError.get(id_carga).add("Inconsistencia en el documento del beneficiario, Documento: " + retiro.getIdentificacion_beneficiario());

					errorRegistro = true;
				}
				
				// -----

				retiro.setId_carga(id_carga);
				retiro.setId_cliente(id_usuario);

				if (!errorRegistro) {
					retiroDao.guardarRetiro(retiro, cargaDao);
				} else {
					errorDatos = true;
				}

				RetiroCargueServicio.retirosCargaPorcentaje.put(id_carga, 1 + (i * 99) / retiros.size());
				i++;
			}

			daoManager.commitTransaction();

			RetiroCargueServicio.retirosExitoso.put(id_carga, !errorDatos);

			return retiros;

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		} finally {
			RetiroCargueServicio.retirosCargaPorcentaje.remove(id_carga);
			try {
				daoManager.endTransaction();
				// new File(archivo).delete();
			} catch (Exception e2) {
				SimpleLogger.setError("Ha ocurrido un error", e2);
			}

		}

		return retiros;
	}

	public List<Map<String, Object>> obtenerBancos() {

		DaoManager daoManager = DaoConfig.getDaoManager(2);
		CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

		RetiroDao retiroDao = new RetiroDao();

		try {
			return retiroDao.obtenerBancos(cargaDao);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

}

class CargaRetiroProceso implements Runnable {

	private Integer id_carga;
	private String codigo_banco;
	private Session session;
	private String id_archivo_firmado_plano;
	private String archivo;

	public CargaRetiroProceso(Session session, Integer id_carga, String codigo_banco, String id_archivo_firmado_plano, String archivo) {
		this.id_carga = id_carga;
		this.codigo_banco = codigo_banco;
		this.session = session;
		this.id_archivo_firmado_plano = id_archivo_firmado_plano;
		this.archivo = archivo;
	}

	public void run() {

		Integer id_usuario = (Integer) session.getAttribute("id_usuario");

		// -----------------------------------------------------------------

		List<LectorArchivoPlano> lectores = new ArrayList<LectorArchivoPlano>();

		lectores.add(new LectorArchivoPlanoOccidente());
		lectores.add(new LectorArchivoPlanoBancolombiaSAP());
		lectores.add(new LectorArchivoPlanoDavivienda());
		lectores.add(new LectorArchivoPlanoBogota());
		lectores.add(new LectorArchivoPlanoBancolombiaPAB());
		lectores.add(new LectorArchivoHTS());

		// -----------------------------------------------------------------

		for (LectorArchivoPlano lector : lectores) {
			if (lector.getCodBanco().equals(codigo_banco)) {
				if (id_archivo_firmado_plano != null) {
					FirmaServicio.getInstance().actualizarIDCargaParaDocumentoAuditoria(id_archivo_firmado_plano, id_carga);
				}

				List<Retiro> r = RetiroCargueServicio.getInstance().leerRetiros(archivo, lector, id_carga, id_usuario);

				RetiroCargueServicio.retirosCarga.put(id_carga, r);

				return;
			}
		}

		if (id_carga != null) {
			CargaServicio.getInstance().eliminarCarga(id_carga);
		}
	}

}

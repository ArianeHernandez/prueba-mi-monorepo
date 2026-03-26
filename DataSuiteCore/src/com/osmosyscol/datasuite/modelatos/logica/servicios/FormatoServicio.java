package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cocoon.environment.Session;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Horario;
import com.osmosyscol.datasuite.logica.dto.ListaDinamicaCampo;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.logica.servicios.FormatoOperacionServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.ListaDinamicaCampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Destino;
import com.osmosyscol.datasuite.modelatos.logica.dto.ExcepcionFormatoCliente;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoEstilo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoNegocio;
import com.osmosyscol.datasuite.modelatos.logica.dto.GrupoFormato;
import com.osmosyscol.datasuite.modelatos.logica.dto.Operacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.ParametroCarga;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.CampoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.DestinoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.FormatoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ParametroCargaDao;
import com.osmosyscol.datasuite.persistencia.dao.HorarioDao;
import com.osmosyscol.datasuite.persistencia.dao.ProcesoDao;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class FormatoServicio {

	private static FormatoServicio formatoServicio;

	private FormatoServicio() {
	}

	public static FormatoServicio getInstance() {
		if (formatoServicio == null) {
			formatoServicio = new FormatoServicio();
		}
		return formatoServicio;
	}

	// ------------------------------

	public Formato obtenerFormato(Integer id_formato) {

		if (id_formato == null) {
			return null;
		}

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			Formato formato = formatoDao.obtenerFormato(id_formato);

			formato.setFormatocampobase(obtenerFormatoCampo(formato.getId_formato()));

			return formato;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public FormatoCampo obtenerFormatoCampo(Integer id_formato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatoCampo(id_formato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public FormatoCampo obtenerFormatoCampoPorID(Integer id_formato_campo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatoCampoPorID(id_formato_campo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Object> obtenerFormatoCampoCompleto(Integer id_formato, Session session, Boolean retornarListas) {
		List<Object> ret = new ArrayList<Object>();

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			CampoDao campoDao = (CampoDao) daoManager.getDao(CampoDao.class);

			FormatoCampo formatoCampo = formatoDao.obtenerFormatoCampo(id_formato);
			ret.add(formatoCampo);

			Set<Integer> campos = obtenerIdCamposPorFormatoCampo(formatoCampo);

			Set<Integer> listavalores = new HashSet<Integer>();

			for (Integer id_campo : campos) {
				Campo camp = campoDao.obtenerCampo(id_campo);
				ret.add(camp);

				if (camp.getId_lista_valores() != null) {
					listavalores.add(camp.getId_lista_valores());
				}
			}

			if (retornarListas) {

				for (Integer id_listavalores : listavalores) {
					Map<Integer, List<ValorLista>> a = new HashMap<Integer, List<ValorLista>>();
					a.put(id_listavalores, ListaValoresServicio.getInstance().obtenerValoresLV(id_listavalores));
					ret.add(a);
				}

				Set<Integer> ids_lista_dinamica = obtenerIdsListaDinamica(formatoCampo);

				for (Integer id_lista_dinamica : ids_lista_dinamica) {
					Map<String, List<ValorLista>> a = new HashMap<String, List<ValorLista>>();
					a.put("LD_" + id_lista_dinamica, ListaDinamicaServicio.getInstance().obtenerValoresListaDinamica(id_lista_dinamica, session));
					ret.add(a);
				}
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return ret;
	}

	public List<Object> obtenerFormatoCampoCompletoSinVacios(Integer id_formato, Session session, Boolean retornarListas) {
		List<Object> ret = new ArrayList<Object>();

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			CampoDao campoDao = (CampoDao) daoManager.getDao(CampoDao.class);

			FormatoCampo formatoCampo = formatoDao.obtenerFormatoCampoSinVacios(id_formato);
			ret.add(formatoCampo);

			Set<Integer> campos = obtenerIdCamposPorFormatoCampo(formatoCampo);

			Set<Integer> listavalores = new HashSet<Integer>();

			for (Integer id_campo : campos) {
				Campo camp = campoDao.obtenerCampo(id_campo);
				ret.add(camp);

				if (camp.getId_lista_valores() != null) {
					listavalores.add(camp.getId_lista_valores());
				}
			}

			if (retornarListas) {

				for (Integer id_listavalores : listavalores) {
					Map<Integer, List<ValorLista>> a = new HashMap<Integer, List<ValorLista>>();
					a.put(id_listavalores, ListaValoresServicio.getInstance().obtenerValoresLV(id_listavalores));
					ret.add(a);
				}

				Set<Integer> ids_lista_dinamica = obtenerIdsListaDinamica(formatoCampo);

				for (Integer id_lista_dinamica : ids_lista_dinamica) {
					Map<String, List<ValorLista>> a = new HashMap<String, List<ValorLista>>();
					a.put("LD_" + id_lista_dinamica, ListaDinamicaServicio.getInstance().obtenerValoresListaDinamica(id_lista_dinamica, session));
					ret.add(a);
				}
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return ret;
	}
	
	public List<Object> obtenerFormatoCampoCompleto(Integer id_formato, Session session) {
		return obtenerFormatoCampoCompleto(id_formato, session, true);
	}

	public List<Object> obtenerFormatoCampoCompletoSinListasD(Integer id_formato, Session session) {
		return obtenerFormatoCampoCompleto(id_formato, session, false);
	}

	public List<Object> obtenerFormatoCampoCompletoSinListasDSinVacios(Integer id_formato, Session session) {
		return obtenerFormatoCampoCompletoSinVacios(id_formato, session, false);
	}
	
	private Set<Integer> obtenerIdsListaDinamica(FormatoCampo formatoCampo) {
		Set<Integer> ids_lista_dinamica = new HashSet<Integer>();

		obtenerIdsListaDinamica(ids_lista_dinamica, formatoCampo);

		return ids_lista_dinamica;
	}

	private void obtenerIdsListaDinamica(Set<Integer> ids_lista_dinamica, FormatoCampo formatoCampo) {
		if (formatoCampo == null) {
			return;
		}

		if (formatoCampo.getId_lista_dinamica() != null) {
			ids_lista_dinamica.add(formatoCampo.getId_lista_dinamica());
		}

		if (formatoCampo.getFormato_campo_list() != null) {
			for (FormatoCampo fcampo : formatoCampo.getFormato_campo_list()) {
				obtenerIdsListaDinamica(ids_lista_dinamica, fcampo);
			}
		}
	}

	// -------------------

	private Set<Integer> obtenerIdCamposPorFormatoCampo(FormatoCampo formatoCampo) {

		return obtenerIdCamposPorFormatoCampo(formatoCampo, new HashSet<Integer>());
	}

	private Set<Integer> obtenerIdCamposPorFormatoCampo(FormatoCampo formatoCampo, Set<Integer> lista) {

		if (formatoCampo != null) {

			for (FormatoCampo fcampo : formatoCampo.getFormato_campo_list()) {
				lista = obtenerIdCamposPorFormatoCampo(fcampo, lista);
			}

			if (formatoCampo.getId_campo() != null) {
				lista.add(formatoCampo.getId_campo().intValue());
			}
		}
		return lista;
	}

	// ------------------------------

	public List<FormatoCampo> obtenerHijosFormatoCampo(Integer id_formato, Integer id_formato_campo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerHijosFormatoCampo(id_formato, id_formato_campo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Formato> obtenerFormatosPorModelo(Integer id_modelo, Integer numero_pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosPorModelo(id_modelo, numero_pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Formato> obtenerFormatosPorModeloFiltro(Integer id_modelo, Integer numero_pagina, String filtro) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosPorModeloFiltro(id_modelo, numero_pagina, filtro);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Integer totalFormatosPorModeloFiltro(Integer id_modelo, String filtro) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.totalFormatosPorModeloFiltro(id_modelo, filtro);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<FormatoNegocio> obtenerFormatoNegocioPorNegocio(Integer id_negocio) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatoNegocioPorNegocio(id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Integer totalFormatosPorModelo(Integer id_modelo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.totalFormatosPorModelo(id_modelo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Formato guardarFormato(Formato formato, FormatoCampo formatoCampo, List<Destino> destinos, Integer id_persona, List<ParametroCarga> parametros, Integer id_modelo, Integer id_negocio, String franjas, List<ListaDinamicaCampo> listaDC, List<Integer> estructurasAplicar, List<Integer> formatos_referenciados) {

		if (formato != null) {
			DaoManager daoManager = null;
			try {

				daoManager = DaoConfig.getDaoManager();
				daoManager.startTransaction();

				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
				DestinoDao destinoDao = (DestinoDao) daoManager.getDao(DestinoDao.class);
				ParametroCargaDao parametroCargaDao = (ParametroCargaDao) daoManager.getDao(ParametroCargaDao.class);
				HorarioDao horarioDao = (HorarioDao) daoManager.getDao(HorarioDao.class);

				if (formato.getId_formato() != null) {
					borrarFormato(formato.getId_formato());
				}

				// Si el formato no tiene asociado un horario, se crea el
				// horario
				if (formato.getId_horario() == null) {

					Integer id_horario = horarioDao.obtenerSiguienteIDHorario();

					Horario nuevoHorario = new Horario();
					nuevoHorario.setId_horario(id_horario);

					if (horarioDao.crearHorario(nuevoHorario)) {
						formato.setId_horario(nuevoHorario.getId_horario());
					}

				}

				Formato rFormato = formatoDao.guardarFormato(formato, formatoCampo, id_persona, id_modelo, id_negocio);
				if (rFormato != null && destinos != null && destinos.size() > 0) {
					for (Destino destino : destinos) {
						if (destino != null && destino.getTitulo() != null && destino.getTitulo().trim().length() > 0) {
							destino.setId_formato(rFormato.getId_formato());
							destinoDao.guardarDestino(destino);
						} else if (destino != null && destino.getId_destino() != null) {
							destinoDao.eliminarDestino(destino.getId_destino());
						}
					}
				}

				//Creacion vista bandeja de entrada
				if (rFormato != null && Constantes.FORMATO_SALIDA.equals(rFormato.getTipoformato())) {
					generarVistaBandejaEntrada(rFormato);
					generarVistaFiltroBandejaEntrada(rFormato);
				}
				
				if (rFormato != null && parametros != null && parametros.size() > 0) {
					for (ParametroCarga parametroCarga : parametros) {
						if (parametroCarga != null && parametroCarga.getNombre() != null && parametroCarga.getNombre().trim().length() > 0) {
							if (parametroCarga.getId_formato() == null) {
								parametroCarga.setId_formato(rFormato.getId_formato());
							}
							parametroCargaDao.guardarParametroCarga(parametroCarga);
						}
					}

				}

				/*
				 * if (rFormato != null && rFormato.getTipoformato().equals(Constantes.FORMATO_SALIDA)) { ConfServicio.getInstance().crearFormatoConf(rFormato); ConfServicio.getInstance().crearFormatoConf_Reg(rFormato); }
				 */

				if (rFormato != null && rFormato.getTipoformato().equals(Constantes.FORMATO_ENTRADA)) {

					Boolean rta = HorarioServicio.getInstance().guardarFranjas(franjas, formato.getId_horario());
					if (!rta) {
						SimpleLogger.setError("No es posible guardar las franjas");
					}
				}

				borrarFormato(formato.getId_formato());

				Boolean salida = ListaDinamicaCampoServicio.getInstance().guardarListasDinamicaCampoTrans(listaDC, formato.getId_formato(), daoManager);
				if (!salida) {
					SimpleLogger.setError("No es posible guardar las listas dinamicas del formato");
				}

				if (estructurasAplicar != null) {
					formatoDao.guardarEstructurasAplicar(formato.getId_formato(), estructurasAplicar);
				}

				if (formatos_referenciados != null) {
					formatoDao.guardarFormatosReferenciados(formato.getId_formato(), formatos_referenciados);
				}

				daoManager.commitTransaction();
				return rFormato;

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			} finally {
				if (daoManager != null) {
					daoManager.endTransaction();
				}
			}
		}
		return null;
	}

	// ------------------------------

	public List<Formato> obtenerSubFormatosPorPadre(Integer idFormatoPadre) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerSubFormatosPorPadre(idFormatoPadre);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener los subFormatos de: " + idFormatoPadre, e);
		}
		return null;
	}

	// ------------------------------

	public List<Formato> obtenerSubFormatosPorFamilia(Integer idFormatoPadre) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			List<Formato> ret = formatoDao.obtenerSubFormatosPorFamilia(idFormatoPadre);

			if (ret != null) {
				for (Formato formato : ret) {
					formato.setFormatocampobase(obtenerFormatoCampo(formato.getId_formato()));
				}
			}
			return ret;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener los subFormatos de: " + idFormatoPadre, e);
		}
		return null;
	}

	// ------------------------------

	public List<Integer> obtenerEstructurasAplicar(Integer id_formato) {

		try {

			FormatoDao formatoDao = (FormatoDao) DaoConfig.getDao(FormatoDao.class);

			return formatoDao.obtenerEstructurasAplicar(id_formato);

		} catch (Exception e) {
			SimpleLogger.setError("");
		}
		return null;
	}

	// ------------------------------------------

	// --------------------------------------------------------------------------
	// ------------------------------------------

	public List<Operacion> obtenerOperacionesControl() {
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			return formatoDao.obtenerOperacionesControl();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Operacion> obtenerOperacionesCampoAct() {
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			return formatoDao.obtenerOperacionesCampoAct();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Operacion obtenerOperacionId(Integer id_operacion) {
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			return formatoDao.obtenerOperacionId(id_operacion);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<FormatoCampo> obtenerFormatosCamposOperacion(Integer id_formato) {
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			return formatoDao.obtenerFormatosCamposOperacion(id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Formato> obtenerFormatosPorEstructura(Integer id_estructura) {
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			List<Formato> list = formatoDao.obtenerFormatosPorEstructura(id_estructura);
			return list;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean eliminarFormato(Integer id_formato) {
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			borrarFormato(id_formato);

			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			procesoDao.eliminarFormato(id_formato, null);

			return formatoDao.eliminarFormato(id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	// ------------------------------
	// Obtiene los formatos de tipo entrada activos, al que un usuario tiene
	// acceso.

	public List<Formato> obtenerFormatosClienteActivo(Integer id_persona, Integer id_negocio, Integer numero_pagina, Integer id_usuario) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			List<Formato> formatosSinFiltrar = formatoDao.obtenerFormatosClienteActivo(id_persona, id_negocio, numero_pagina);

			return aplicarExepcionesFormatoPorCliente(id_usuario, formatosSinFiltrar, daoManager);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	// Obtiene los formatos activos por negocio.

	public List<Formato> obtenerFormatosPorNegocio(Integer id_negocio) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosPorNegocio(id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	// Obtiene el total de los formatos de tipo entrada activos, al que un
	// usuario tiene acceso.

	public Integer totalFormatosClienteActivo(Integer id_persona, Integer id_negocio, Integer id_usuario) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			List<Formato> formatosSinFiltrar = formatoDao.obtenerFormatosClienteActivo(id_persona, id_negocio, null);

			Integer total = aplicarExepcionesFormatoPorCliente(id_usuario, formatosSinFiltrar, daoManager).size();

			return total;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Formato> obtenerFormatosPorTipo(String tipoformato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosPorTipoFormato(tipoformato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Formato> obtenerFormatosSalidaPorModelo(Integer id_modelo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosSalidaPorModelo(id_modelo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Formato> obtenerFormatosPorTipoSalida() {
		return obtenerFormatosPorTipo(Constantes.FORMATO_SALIDA);
	}

	// ------------------------------

	public FormatoCampo obtenerFormatoCamposSalida(Integer id_formato) {

		FormatoCampo formatoCampo = new FormatoCampo();
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			formatoCampo = formatoDao.obtenerFormatoCampo(id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return formatoCampo;
	}

	// ------------------------------

	public Boolean activarFormato(Integer id_formato, Boolean activar) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.activarFormato(id_formato, activar);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	public Boolean disponibleFormatoNegocio(Integer id_formato, Integer id_negocio, Boolean activo, Boolean todos) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.disponibleFormatoNegocio(id_formato, id_negocio, activo, todos);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	// ------------------------------

	public void borrarFormato(Integer id_formato) {

		try {

			Formato formato = FormatoServicio.getInstance().obtenerFormato(id_formato);

			String original_filename = "F" + id_formato + "-" + StringUtils.toFileName(formato.getNombre()) + ".xls";

			String nombrecarpeta = ParametrosInicio.getProperty("file.carpeta") + "/FORMATOS";

			File arcFormato = new File(nombrecarpeta + "/" + original_filename);

			arcFormato.delete();

		} catch (Exception e) {
			SimpleLogger.setError("Error al borrar el formato " + id_formato, e);
		}
	}

	public List<Formato> obtenerFormatosListaValores(Integer id_lista_valores) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosListaValores(id_lista_valores);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public void borrarFormatoXListaValores(Integer id_lista_valores) {

		List<Formato> formatos = obtenerFormatosListaValores(id_lista_valores);
		for (Formato formato : formatos) {
			borrarFormato(formato.getId_formato());
		}
	}

	public Boolean esHorarioActivo() {
		String activo = ParametrosInicio.getProperty("formatos.horario");
		return StringUtils.esVerdad(activo);
	}

	public Integer totalFranjasHorario(Integer id_formato) {
		if (id_formato == null) {
			return null;
		}

		try {
			Formato formato = obtenerFormato(id_formato);
			HorarioServicio horarioServicio = HorarioServicio.getInstance();

			return horarioServicio.obtenerNumeroTotalFranjas(formato.getId_horario());
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public List<Formato> obtenerFormatosEntradaPorNegocio(Integer id_negocio) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			return formatoDao.obtenerFormatosEntradaPorNegocio(id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error FormatoServicio.obtenerFormatosEntradaPorNegocio", e);
			return null;
		}

	}

	public List<Formato> obtenerFormatosSalidaPorNegocio(Integer id_negocio) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			return formatoDao.obtenerFormatosSalidaPorNegocio(id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error FormatoServicio.obtenerFormatosSalidaPorNegocio", e);
			return null;
		}

	}

	public List<Formato> obtenerFormatosPorCliente(Integer id_usuario) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			List<Formato> formatosSinFiltrar = formatoDao.obtenerFormatosPorCliente(id_usuario);

			return aplicarExepcionesFormatoPorCliente(id_usuario, formatosSinFiltrar, daoManager);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error FormatoServicio.obtenerFormatosPorCliente", e);
			return null;
		}

	}

	/**
	 * Retorna los id's de los formatos de entrada activos relacionados con el proceso
	 * 
	 * @return lista de ids
	 * 
	 * @author oaortizs
	 */
	public List<Formato> obtenerFormatosProceso(Integer id_proceso) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosProceso(id_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	/**
	 * Retorna los formato campo asociados a una estructura que sean nodo RAIZ
	 * 
	 * @return lista de de formatos campo
	 * 
	 * @author jcvargasj
	 */

	public List<FormatoCampo> obtenerFormatosCampoRaizPorEstructura(Integer id_estructura) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosCampoRaizPorEstructura(id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	/**
	 * Retorna los formato campo hijo que sean de tipo estructura, para una estructura particular, y que tengan hijos asociados.
	 * 
	 * @return lista de de formatos campo
	 * 
	 * @author jcvargasj
	 */
	public List<FormatoCampo> obtenerFormatosCampoTipoEstructuraConHijosPorEstructura(Integer id_estructura) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosCampoTipoEstructuraConHijosPorEstructura(id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean guardarRelacionFormatoCampoPorEstructuraTransaccional(Integer id_estructura, Campo campo, DaoManager daoManager) {

		try {
			// Se revisa si existe alguna relacion

			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			Integer totalRegistros = formatoDao.totalFormatoCampoPorIDCampo(campo.getId_campo());

			Boolean relacionesInsertadas = true;

			// Si no hay registros se crea la relacion
			if (totalRegistros == 0) {

				// Se crean la relacion para los nodos que sean raiz
				List<FormatoCampo> formatoCamposRaiz = obtenerFormatosCampoRaizPorEstructura(id_estructura);

				// Nuevo formato campo
				FormatoCampo nuevoFormatoCampo = new FormatoCampo();
				nuevoFormatoCampo.setId_campo(campo.getId_campo());
				nuevoFormatoCampo.setTitulo(campo.getNombre());
				nuevoFormatoCampo.setTipo_ingreso(FormatoCampo.TIPOINGRESO_USUARIO_VACIO);
				nuevoFormatoCampo.setValor_constante(null);
				nuevoFormatoCampo.setId_variable(null);
				nuevoFormatoCampo.setId_operacion(null);
				nuevoFormatoCampo.setEstructura_padre("N");
				nuevoFormatoCampo.setId_estructura(null);
				nuevoFormatoCampo.setSeleccion_campo("N");
				nuevoFormatoCampo.setSeleccion_visualizacion("N");
				nuevoFormatoCampo.setPrecarga("N");
				nuevoFormatoCampo.setCondicional("N");
				nuevoFormatoCampo.setValor_condicion(null);
				nuevoFormatoCampo.setId_lista_dinamica(null);
				nuevoFormatoCampo.setCondicion_precarga(null);
				nuevoFormatoCampo.setValor_sesion(null);

				if (formatoCamposRaiz != null && formatoCamposRaiz.size() > 0) {
					for (FormatoCampo formatoCampoRaiz : formatoCamposRaiz) {
						nuevoFormatoCampo.setId_formato(formatoCampoRaiz.getId_formato());
						nuevoFormatoCampo.setId_formato_campo_padre(formatoCampoRaiz.getId_formato_campo());

						if (formatoDao.guardarFormatoCampo(nuevoFormatoCampo) == false) {
							relacionesInsertadas = false;
						}
					}
				}

				// Se crean la relacion con los nodos que sean de tipo
				// estructura
				List<FormatoCampo> formatoCamposTipoEstructura = obtenerFormatosCampoTipoEstructuraConHijosPorEstructura(id_estructura);

				if (formatoCamposTipoEstructura != null && formatoCamposTipoEstructura.size() > 0) {
					for (FormatoCampo formatoCampoTipoEstructura : formatoCamposTipoEstructura) {
						nuevoFormatoCampo.setId_formato(formatoCampoTipoEstructura.getId_formato());
						nuevoFormatoCampo.setId_formato_campo_padre(formatoCampoTipoEstructura.getId_formato_campo());

						if (formatoDao.guardarFormatoCampo(nuevoFormatoCampo) == false) {
							relacionesInsertadas = false;
						}

					}
				}

			}

			return relacionesInsertadas;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public Boolean eliminarRelacionFormatoCampoPorCampoTransaccional(Campo campo, DaoManager daoManager) {
		try {

			Boolean relacionesEliminadas = true;

			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			List<FormatoCampo> formatoCamposPadre = formatoDao.obtenerFormatosCampoPorCampo(campo.getId_campo());

			if (formatoCamposPadre != null && formatoCamposPadre.size() > 0) {
				for (FormatoCampo formatoCampoPadre : formatoCamposPadre) {

					if (!eliminarFormatoCampoRecursivo(formatoCampoPadre, formatoDao)) {
						relacionesEliminadas = false;
					}
				}

			} else {
				SimpleLogger.setError("No hay relaciones formato campo para eliminar");

			}

			return relacionesEliminadas;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	private Boolean eliminarFormatoCampoRecursivo(FormatoCampo formatoCampo, FormatoDao formatoDao) {
		// Por cada formato campo se revisa si se tienen hijos asociados
		Boolean eliminacionExitosa = false;

		List<FormatoCampo> formatoCamposHijos = formatoDao.obtenerFormatosCampoHijoPorFormatoCampoPadre(formatoCampo.getId_formato_campo());

		// Si tiene hijos asociados de forma recursiva se elimina los
		// formatos-campo hijos
		if (formatoCamposHijos != null && formatoCamposHijos.size() > 0) {
			Boolean eliminacionHijosExitosa = true;

			for (FormatoCampo formatoCampoHijo : formatoCamposHijos) {

				if (!eliminarFormatoCampoRecursivo(formatoCampoHijo, formatoDao)) {

					eliminacionHijosExitosa = false;
				}

			}

			// Una vez haya eliminado los hijos se elimina el padre
			if (eliminacionHijosExitosa) {
				eliminacionExitosa = formatoDao.eliminarFormatoCampoPorID(formatoCampo.getId_formato_campo());

			} else {
				// si la elimincion de hijos no es exitosa entonces toda la
				// elimimacion no es exitosa
				eliminacionExitosa = false;
			}

		} else {
			// Si no tiene hijos asociados se elimina el formato campo
			eliminacionExitosa = formatoDao.eliminarFormatoCampoPorID(formatoCampo.getId_formato_campo());

		}

		return eliminacionExitosa;

	}

	private String obtenerHashCampos(Integer id_formato) {

		String hash = "";

		FormatoCampo formatoCampo = FormatoServicio.getInstance().obtenerFormatoCampo(id_formato);

		List<Integer> ids_campos = new ArrayList<Integer>();
		concatenarIdsFormatoCampo(formatoCampo, ids_campos);
		Collections.sort(ids_campos);
		for (Integer id_campo : ids_campos) {
			hash += "-" + id_campo;
		}

		return hash.hashCode() + "";
	}

	private void concatenarIdsFormatoCampo(FormatoCampo fcampo, List<Integer> ids_campos) {
		if (fcampo != null) {
			if (fcampo.getId_campo() != null) {
				ids_campos.add(fcampo.getId_campo());
			}
			if (fcampo.getFormato_campo_list() != null) {
				for (FormatoCampo fc : fcampo.getFormato_campo_list()) {
					concatenarIdsFormatoCampo(fc, ids_campos);
				}
			}
		}

	}

	public void crearHojaValidacion(HSSFWorkbook wb, Integer id_formato) {
		HSSFSheet newsheet = wb.createSheet("HOJA_SEGURIDAD");
		Integer index = wb.getSheetIndex(newsheet);

		newsheet.protectSheet("meconio3");
		wb.setSheetHidden(index, true);

		HSSFRow rowc0 = newsheet.createRow((short) 0);
		rowc0.setHeight((short) 0);
		HSSFCell ccel0 = rowc0.createCell((short) 0);
		String hashCode = obtenerHashCampos(id_formato);
		ccel0.setCellValue(new HSSFRichTextString(hashCode));

	}

	public Boolean validarArchivoCarga(Integer id_formato, String ruta) {
		try {
			InputStream inp = new FileInputStream(ruta);
			return validarArchivoCarga(id_formato, inp);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public Boolean validarArchivoCarga(Integer id_formato, InputStream inp) {
		try {
			String hash = obtenerHashCampos(id_formato);

			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			HSSFSheet sheet = wb.getSheet("HOJA_SEGURIDAD");
			if (sheet != null) {

				HSSFRow row = sheet.getRow(0);
				HSSFCell cell = row.getCell((short) 0);
				String hashExcel = cell.getRichStringCellValue().getString();

				return hashExcel.equals(hash);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;
	}

	private List<Formato> aplicarExepcionesFormatoPorCliente(Integer id_usuario, List<Formato> formatosSinFiltrar, DaoManager daoManagerTrans) {
		try {

			DaoManager daoManager = daoManagerTrans;

			if (daoManager == null) {
				daoManager = DaoConfig.getDaoManager();

			}

			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			// Se consultan las excepciones
			List<ExcepcionFormatoCliente> excepciones = formatoDao.obtenerExcepcionesFormatoPorCliente(id_usuario);

			// Se crea una nueva lista para los almacenar los formatos luego de
			// aplicar exepciones
			List<Formato> formatosFiltrados = new ArrayList<Formato>();

			// Se crea un mapa de formatos para la busqueda
			HashMap<String, Formato> map = new HashMap<String, Formato>();

			for (Formato formato : formatosSinFiltrar) {
				map.put("" + formato.getId_formato(), formato);

			}

			// Se revisan las excepciones
			if (excepciones != null && excepciones.size() > 0 && id_usuario != null) {

				// Por cada excepcion se aplica eliminan o crean formatos en el
				// mapa
				for (ExcepcionFormatoCliente excepcion : excepciones) {

					if (excepcion.getTipo_excepcion().equals(Constantes.EXCEPCION_TIPORESTRICCION)) {
						if (map.containsKey("" + excepcion.getId_formato())) {
							map.remove("" + excepcion.getId_formato());
						}

					} else if (excepcion.getTipo_excepcion().equals(Constantes.EXCEPCION_TIPOADICION)) {
						if (!map.containsKey("" + excepcion.getId_formato())) {

							// Se busca el nuevo formato para insertarlo en el
							// map
							Formato nuevoFormato = FormatoServicio.getInstance().obtenerFormato(excepcion.getId_formato());

							map.put("" + excepcion.getId_formato(), nuevoFormato);
						}

					}

				}

				// Finalmente se crea una nueva lista con los formatos filtrados
				Set<String> keys = map.keySet();
				for (String key : keys) {
					formatosFiltrados.add(map.get(key));
				}

			} else {
				// Se asigna la mismos formatos ya que no hay exepciones
				formatosFiltrados = formatosSinFiltrar;
			}

			return formatosFiltrados;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error FormatoServicio.aplicarExepcionesFormatoPorCliente", e);
			return null;
		}
	}

	public List<Formato> obtenerFormatosSinAplicarExepcionesPorCliente(Integer id_usuario) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			List<Formato> formatosSinFiltrar = formatoDao.obtenerFormatosPorCliente(id_usuario);

			return formatosSinFiltrar;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error FormatoServicio.obtenerFormatosPorCliente", e);
			return null;
		}

	}

	public List<ExcepcionFormatoCliente> obtenerExcepcionesFormatoPorClientePaginado(Integer id_usuario, Integer numero_pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerExcepcionesFormatoPorClientePaginado(id_usuario, numero_pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public List<ExcepcionFormatoCliente> obtenerExcepcionesFormatoPorCliente(Integer id_usuario) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerExcepcionesFormatoPorCliente(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public Integer totalExcepcionesFormatoPorCliente(Integer id_usuario) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.totalExcepcionesFormatoPorCliente(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public Boolean guardarExcepcionesFormatoPorCliente(List<ExcepcionFormatoCliente> excepciones, Integer id_usuario) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);

			daoManager.startTransaction();

			try {

				// Se consultan los procesos por usuario
				List<Proceso> procesosPorCliente = procesoDao.obtenerProcesosPorCliente(id_usuario);

				// Se eliminan todas las excepciones por usuario
				Boolean relacionesEliminadas = formatoDao.eliminarTodasExcepcionesFormatoCliente(id_usuario);

				if (relacionesEliminadas) {
					Boolean relacionesCreadas = true;
					if (excepciones != null) {

						// Se consultan los formatos de los procesos del cliente
						List<Formato> formatosTodosProcesos = formatoDao.obtenerFormatosPorClienteTodosProcesos(id_usuario);
						HashMap<Integer, Formato> mapaFormatosTodosProcesos = new HashMap<Integer, Formato>();

						if (formatosTodosProcesos != null) {
							for (Formato formato : formatosTodosProcesos) {
								mapaFormatosTodosProcesos.put(formato.getId_formato(), formato);
							}

						}

						// Se crean las excepciones por usuario
						for (ExcepcionFormatoCliente excepcionFormatoCliente : excepciones) {

							if (excepcionFormatoCliente != null) {

								// Se crean o elimina las relaciones
								// proceso_formato segun la restriccion
								if (formatosTodosProcesos != null) {
									if (excepcionFormatoCliente.getTipo_excepcion().equals(Constantes.EXCEPCION_TIPORESTRICCION)) {
										if (mapaFormatosTodosProcesos.containsKey(excepcionFormatoCliente.getId_formato())) {

											for (Proceso proceso : procesosPorCliente) {
												Boolean exitosa = procesoDao.eliminarFormato(excepcionFormatoCliente.getId_formato(), proceso.getId_proceso());

												if (!exitosa) {
													relacionesCreadas = false;
												}
											}

										}
									}
								}

								// Si alguna relacion falla se debe terminar la
								// transaccion
								if (!formatoDao.crearExcepcionFormatoCliente(excepcionFormatoCliente)) {
									relacionesCreadas = false;

								}

							}
						}

					}

					// Los procesos deben tener unicamente los formatos que
					// tenga asociado el cliente, luego de crear todas las
					// excepciones
					List<Formato> formatosFiltradosCliente = aplicarExepcionesFormatoPorCliente(id_usuario, formatoDao.obtenerFormatosPorCliente(id_usuario), daoManager);

					List<Formato> formatosTodosProcesos = formatoDao.obtenerFormatosPorClienteTodosProcesos(id_usuario);
					HashMap<Integer, Formato> mapaFormatosPorCliente = new HashMap<Integer, Formato>();

					if (formatosFiltradosCliente != null) {
						for (Formato formato : formatosFiltradosCliente) {
							mapaFormatosPorCliente.put(formato.getId_formato(), formato);
						}

					}

					// Segun los formatos del cliente se eliminan los formatos
					// que sobran de los procesos del cliente
					for (Formato formato : formatosTodosProcesos) {
						if (!mapaFormatosPorCliente.containsKey(formato.getId_formato())) {
							for (Proceso proceso : procesosPorCliente) {
								Boolean exitosa = procesoDao.eliminarFormato(formato.getId_formato(), proceso.getId_proceso());

								if (!exitosa) {
									relacionesCreadas = false;
								}
							}

						}

					}

					// Finalmente se si no hubo problema se termina la
					// transaccion;
					if (relacionesCreadas) {
						daoManager.commitTransaction();
						return true;
					} else {
						return false;
					}

				} else {
					return false;
				}

			} catch (Exception e) {
				return false;
			} finally {
				daoManager.endTransaction();
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	// grupoformato
	public List<GrupoFormato> obtenerTodosLosGruposformato() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerTodosLosGruposformato();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public GrupoFormato obtenerGrupoFormato(Integer id_grupoformato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerGrupoFormato(id_grupoformato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public Boolean actualizarGrupoformato(GrupoFormato grupoFormato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.actualizarGrupoformato(grupoFormato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	public Boolean guardarGrupoformato(GrupoFormato grupoFormato) {
		try {

			// Segun el caso se actualiza o se crea
			if (grupoFormato.getId_grupoformato() != null) {

				return actualizarGrupoformato(grupoFormato);

			} else {
				DaoManager daoManager = DaoConfig.getDaoManager();
				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

				Integer id = formatoDao.obtenerSiguienteIDGrupoFormato();
				grupoFormato.setId_grupoformato(id);
				return formatoDao.guardarGrupoformato(grupoFormato);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}

	public List<Formato> obtenerFormatosPorGrupoFormato(Integer id_grupoformato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosPorGrupoFormato(id_grupoformato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public Boolean eliminarGrupoFormato(Integer id_grupoformato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			// se inicia la transaccion
			daoManager.startTransaction();

			try {
				Boolean esGrupoEliminado = false;

				// Se eliminan las relaciones con los procesos
				Boolean relacionesProcesoEliminadas = formatoDao.eliminarRelacionesGrupoformatoProcesoPorGrupo(id_grupoformato);

				if (relacionesProcesoEliminadas) {

					// Se eliminan las relaciones con los formatos
					Boolean relacionesFormatoEliminadas = formatoDao.eliminarRelacionesFormatoGrupoformatoGrupo(id_grupoformato);

					if (relacionesFormatoEliminadas) {
						// Se elimina el grupo
						Boolean grupoEliminado = formatoDao.eliminarGrupoFormato(id_grupoformato);

						if (grupoEliminado) {
							daoManager.commitTransaction();
							esGrupoEliminado = true;

						}

					}

				}

				return esGrupoEliminado;

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
				return false;
			} finally {
				daoManager.endTransaction();
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	public List<FormatoCampo> obtenerFormatosCampoPorEstructura(Integer id_estructura, Integer id_formato_salida) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatosCampoPorEstructura(id_estructura, id_formato_salida);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	/**
	 * Esta funcion se utiliza para guardar unicamente los paramteros del formato. Los formatos campos se guardar en la funcion: guardarEstructuraFormato()
	 * 
	 * @param formato
	 * @param destinos
	 * @param id_persona
	 * @param parametros
	 * @param id_modelo
	 * @param id_negocio
	 * @param franjas
	 * @param listaDC
	 * @param estructurasAplicar
	 * @return
	 */

	public Formato guardarParametrosFormato(Formato formato, List<Destino> destinos, Integer id_persona, List<ParametroCarga> parametros, Integer id_modelo, Integer id_negocio, String franjas, List<ListaDinamicaCampo> listaDC, List<Integer> estructurasAplicar, List<FormatoEstilo> estilos) {
		if (formato != null) {
			try {

				DaoManager daoManager = DaoConfig.getDaoManager();
				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

				if (formato.getId_formato() != null) {
					borrarFormato(formato.getId_formato());
				}

				// Si el formato no tiene asociado un horario, se crea el
				// horario
				if (formato.getId_horario() == null) {

					HorarioServicio horarioServicio = HorarioServicio.getInstance();
					Integer id_horario = horarioServicio.obtenerSiguienteIDHorario();

					Horario nuevoHorario = new Horario();
					nuevoHorario.setId_horario(id_horario);

					if (horarioServicio.crearHorario(nuevoHorario)) {
						formato.setId_horario(nuevoHorario.getId_horario());
					}

				}

				Formato rFormato = formatoDao.guardarParametrosFormato(formato, id_persona, id_modelo, id_negocio);
				if (rFormato != null && destinos != null && destinos.size() > 0) {
					for (Destino destino : destinos) {
						if (destino != null && destino.getTitulo() != null && destino.getTitulo().trim().length() > 0) {
							destino.setId_formato(rFormato.getId_formato());
							DestinoServicio.getInstance().guardarDestino(destino);
						} else if (destino != null && destino.getId_destino() != null) {
							DestinoServicio.getInstance().eliminarDestino(destino.getId_destino());
						}
					}
				}

				if (rFormato != null && parametros != null && parametros.size() > 0) {
					for (ParametroCarga parametroCarga : parametros) {
						if (parametroCarga != null && parametroCarga.getNombre() != null && parametroCarga.getNombre().trim().length() > 0) {
							if (parametroCarga.getId_formato() == null) {
								parametroCarga.setId_formato(rFormato.getId_formato());
							}
							ParametroCargaServicio.getInstance().guardarParametroCarga(parametroCarga);
						}
					}

				}

				/*
				 * if (rFormato != null && rFormato.getTipoformato().equals(Constantes.FORMATO_SALIDA)) { ConfServicio.getInstance().crearFormatoConf(rFormato); ConfServicio.getInstance().crearFormatoConf_Reg(rFormato); }
				 */

				if (rFormato != null && rFormato.getTipoformato().equals(Constantes.FORMATO_ENTRADA)) {

					Boolean rta = HorarioServicio.getInstance().guardarFranjas(franjas, formato.getId_horario());
					if (!rta) {
						SimpleLogger.setError("No es posible guardar las franjas");
					}
				}

				borrarFormato(formato.getId_formato());

				Boolean salida = ListaDinamicaCampoServicio.getInstance().guardarListasDinamicaCampo(listaDC, formato.getId_formato());
				if (!salida) {
					SimpleLogger.setError("No es posible guardar las listas dinamicas del formato");
				}

				if (estructurasAplicar != null) {
					formatoDao.guardarEstructurasAplicar(formato.getId_formato(), estructurasAplicar);
				}

				FormatoOperacionServicio.getInstance().guardarFormatoOperacionesTrans(daoManager, formato);

				/* Informacion de estilos del formato */
				borrarFormatoEstilos(formato.getId_formato());
				if (rFormato != null && estilos != null && estilos.size() > 0) {
					salida = guardarFormatoEstilos(estilos, formato.getId_formato());
				}
				if (!salida) {
					SimpleLogger.setError("No es posible guardar los estilos del formato");
				}
				
				
				return rFormato;

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}
		}
		return null;
	}

	// ------------------------------

	/**
	 * Esta funcion se utiliza para guardar unicamente la informacion relacionada con la estructura del formato. Esa informacion hace referencia a los formatosCampo, al id_estructura y el campo totalizador
	 * 
	 * @param formato
	 * @param formatoCampo
	 * @return
	 */

	public Formato guardarEstructuraFormato(Formato formato, FormatoCampo formatoCampo) {

		if (formato != null && formato.getId_formato() != null && formatoCampo != null) {
			DaoManager daoManager = null;
			try {

				daoManager = DaoConfig.getDaoManager();
				daoManager.startTransaction();
				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

				Formato rFormato = formatoDao.guardarEstructuraFormato(formato, formatoCampo);

				daoManager.commitTransaction();
				return rFormato;

			} catch (Throwable e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			} finally {
				daoManager.endTransaction();
			}

			return null;
		} else {
			return null;
		}

	}
	
	public List<FormatoEstilo> obtenerFormatoEstilos(Integer id_formato){
		if (id_formato != null){
			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

				return formatoDao.obtenerFormatoEstilos(id_formato);

			} catch (Throwable e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}
		}
		return null;
	}
	
	public Boolean guardarFormatoEstilos(List<FormatoEstilo> estilos, Integer id_formato){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			Boolean ok = true;
			for (FormatoEstilo estilo: estilos){
				if (StringUtils.esNoVacio(estilo.getEstilo())){					
					ok = formatoDao.guardarFormatoEstilo(estilo);
					if (!ok){
						throw new RuntimeException("No se guardo el estilo");
					}
				}
			}
			return true;			
		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}
	
	public Boolean borrarFormatoEstilos(Integer id_formato){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
			return formatoDao.eliminarFormatoEstilo(id_formato);			
		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	public Integer obtenerFormatoSalidaPorProceso (Integer id_proceso_admin) {
		ProcesoAdmin procesoAdmin = ProcesoAdminServicio.getInstance().obtenerProcesoAdmin(id_proceso_admin);
		
		Integer formato_salida = null;
		
		if (procesoAdmin != null && procesoAdmin.getId_formato_entrada() != null) {
			Formato formato_entrada = FormatoServicio.getInstance().obtenerFormato(procesoAdmin.getId_formato_entrada());
			
			if (formato_entrada != null && formato_entrada.getIdformato_salida() != null) {
				formato_salida = formato_entrada.getIdformato_salida();
			} 
		}
		
		return formato_salida;
	}
	
	public List<FormatoCampo> obtenerFormatoCampoBandejaEntrada(Integer id_formato_salida) {
		
		List <FormatoCampo> formatoCampos = null;
		
		if (id_formato_salida != null) {
			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
				formatoCampos = formatoDao.obtenerFormatoCampoBandejaEntrada(id_formato_salida);
			} catch (Throwable e) {
				SimpleLogger.setError("Ha ocurrido un error ", e);
				return null;
			}			
		}
		
		return formatoCampos;
	}

	public List<FormatoCampo> obtenerFormatoCampoPadreBandejaEntrada (Integer id_formato) {
		
		List <FormatoCampo> formatoCampos = null;
		
		if (id_formato != null) {
			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
				formatoCampos = formatoDao.obtenerFormatoCampoPadreBandejaEntrada(id_formato);
			} catch (Throwable e) {
				SimpleLogger.setError("Ha ocurrido un error ", e);
				return null;
			}			
		}
		
		return formatoCampos;
		
	}
	
	public List<FormatoCampo> obtenerFormatoCampoPadreFiltroBandejaEntrada (Integer id_formato) {
		
		List <FormatoCampo> formatoCampos = null;
		
		if (id_formato != null) {
			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
				formatoCampos = formatoDao.obtenerFormatoCampoPadreFiltroBandejaEntrada(id_formato);
			} catch (Throwable e) {
				SimpleLogger.setError("Ha ocurrido un error ", e);
				return null;
			}			
		}
		
		return formatoCampos;
		
	}
	
	public List<FormatoCampo> obtenerFormatoCampoFiltroBandejaEntrada(Integer id_formato_salida) {
		
		List <FormatoCampo> formatoCampos = null;
		
		if (id_formato_salida != null) {
			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
				formatoCampos = formatoDao.obtenerFormatoCampoFiltroBandejaEntrada(id_formato_salida);
			} catch (Throwable e) {
				SimpleLogger.setError("Ha ocurrido un error ", e);
				return null;
			}			
		}
		
		return formatoCampos;
	}
	
	public void generarVistaBandejaEntrada (Formato formato) {
		try {
			List<FormatoCampo> campos = obtenerFormatoCampoBandejaEntrada (formato.getId_formato());
				
			if (campos != null && campos.size() > 0) {
				List<FormatoCampo> camposPadre = obtenerFormatoCampoPadreBandejaEntrada(formato.getId_formato());
				
				String view = "CREATE OR REPLACE VIEW v_bandeja_" + formato.getId_formato() + " ( idcarga";
				String select = "SELECT t0.id_carga";
				String selectFrom = " FROM ";
				
				Map<Integer, String> aliases = new HashMap<Integer, String> ();
				
				for (int i = 0; i < camposPadre.size(); i++) {
					FormatoCampo campoPadre = camposPadre.get(i);
					
					String alias = "t" + i;
					
					if (campoPadre.getId_campo() == null) {
						aliases.put(campoPadre.getId_formato_campo(), alias);
						selectFrom += "V" + formato.getId_estructura() + " " + alias;
						
					} else {
						aliases.put(campoPadre.getId_formato_campo(), alias);
						selectFrom += " LEFT JOIN V" + campoPadre.getId_estructura() + " " + alias +
								" ON " + aliases.get(campoPadre.getId_formato_campo_padre()) + ".C" + campoPadre.getId_campo()
								+ " = " + alias + ".ID";
					}
					
				}
				
				for (int i = 0; i < campos.size(); i++) {
					FormatoCampo campo = campos.get(i);
					
					view += ", FC" + campo.getId_formato_campo();
					
					if (aliases.get(campo.getId_formato_campo()) != null && campo.getId_estructura() != null) {
						String alias = aliases.get(campo.getId_formato_campo());
						select += ", " + alias + ".VISUALIZACION";
					} else {						
						String alias = aliases.get(campo.getId_formato_campo_padre());
						
						Campo c = CampoServicio.getInstance().obtenerCampo(campo.getId_campo());
						
						if (c != null && c.getId_lista_valores() != null) {
							select += ", " + alias + ".V" + campo.getId_campo();
						} else {
							select += ", " + alias + ".C" + campo.getId_campo();											
						}
						
					}
				}
				
				view += ") AS ";
				String sql = view + select + selectFrom;
				SimpleLogger.setDebug("Vista Bandeja Entrada: " + sql);
				DS_SqlUtils.update(sql);
			} 
		}catch (Exception e) {
			SimpleLogger.setError("Error generando vista en bandeja de entrada para el formato " + formato.getId_formato(), e);
		}
	}
	
	public void generarVistaFiltroBandejaEntrada (Formato formato) {
		try {
			List<FormatoCampo> campos = obtenerFormatoCampoFiltroBandejaEntrada (formato.getId_formato());
				
			if (campos != null && campos.size() > 0) {
				List<FormatoCampo> camposPadre = obtenerFormatoCampoPadreFiltroBandejaEntrada(formato.getId_formato());
				
				String view = "CREATE OR REPLACE VIEW v_filtro_bandeja_" + formato.getId_formato() + " ( idcarga";
				String select = "SELECT t0.id_carga";
				String selectFrom = " FROM ";
				
				Map<Integer, String> aliases = new HashMap<Integer, String> ();
				
				for (int i = 0; i < camposPadre.size(); i++) {
					FormatoCampo campoPadre = camposPadre.get(i);
					
					String alias = "t" + i;
					
					if (campoPadre.getId_campo() == null) {
						aliases.put(campoPadre.getId_formato_campo(), alias);
						selectFrom += "V" + formato.getId_estructura() + " " + alias;
						
					} else {
						aliases.put(campoPadre.getId_formato_campo(), alias);
						selectFrom += " LEFT JOIN V" + campoPadre.getId_estructura() + " " + alias +
								" ON " + aliases.get(campoPadre.getId_formato_campo_padre()) + ".C" + campoPadre.getId_campo()
								+ " = " + alias + ".ID";
					}
					
				}
				
				for (int i = 0; i < campos.size(); i++) {
					FormatoCampo campo = campos.get(i);
					
					view += ", FC" + campo.getId_formato_campo();
					
					if (aliases.get(campo.getId_formato_campo()) != null && campo.getId_estructura() != null) {
						String alias = aliases.get(campo.getId_formato_campo());
						select += ", " + alias + ".VISUALIZACION";
					} else {						
						String alias = aliases.get(campo.getId_formato_campo_padre());
						select += ", " + alias + ".C" + campo.getId_campo();				
					}
				}
				
				view += ") AS ";
				String sql = view + select + selectFrom;
				SimpleLogger.setDebug("Vista Filtro Bandeja Entrada: " + sql);
				DS_SqlUtils.update(sql);
			} 
		}catch (Exception e) {
			SimpleLogger.setError("Error generando vista en bandeja de entrada para el formato " + formato.getId_formato(), e);
		}
	}
	
	public List<FormatoCampo> obtenerFormatoCampoValorSesion (Integer id_formato) {
		List <FormatoCampo> formatoCampos = null;
		
		if (id_formato != null) {
			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
				formatoCampos = formatoDao.obtenerFormatoCampoValorSesion(id_formato);
			} catch (Throwable e) {
				SimpleLogger.setError("Ha ocurrido un error ", e);
				return null;
			}			
		}
		
		return formatoCampos; 
	}
	
	public Boolean validarFormatoListaDinamica (Formato formato, Session session) {
		Boolean validacion = true;
		if (formato.getValidar_formato_menu() != null) {
			List<ValorLista> valores = ListaDinamicaServicio.getInstance().obtenerValoresListaDinamica(formato.getValidar_formato_menu(), session);
			validacion = (valores != null && !valores.isEmpty());
		} 
		
		return validacion;
	}
}

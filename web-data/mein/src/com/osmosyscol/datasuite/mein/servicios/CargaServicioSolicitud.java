package com.osmosyscol.datasuite.mein.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.cocoon.environment.Session;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.MapCache;
import com.osmosyscol.commons.utils.SQLUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.SystemUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.ListaDinamicaCampo;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.logica.dto.RestriccionAdministrativo;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.dto.excel.Excel;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.logica.servicios.ExcelServicio;
import com.osmosyscol.datasuite.logica.servicios.FormatoOperacionServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Operacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionResponsableServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CargaInstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaDinamicaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoAdminServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.TipoCampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ValidacionEstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.VariableServicio;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.CampoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.FormatoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.TipoCampoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ValidacionEstructuraDao;
import com.osmosyscol.datasuite.modelatos.utils.FormatoUtils;
import com.osmosyscol.datasuite.persistencia.dao.CargaInstanciaDao;
import com.osmosyscol.datasuite.persistencia.dao.ListaDinamicaCampoDao;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.utils.dummys.DummySession;
import com.osmosyscol.datasuite.webdata.ValidadorDatosCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.ElementoCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.FormatoDato;
import com.osmosyscol.datasuite.webdata.logica.dto.FormatoFiltro;
import com.osmosyscol.datasuite.webdata.logica.dto.NumeroValor;
import com.osmosyscol.datasuite.webdata.logica.dto.ParametrosBusqCargas;
import com.osmosyscol.datasuite.webdata.logica.dto.TablaOperacion;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorFiltro;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorListaDinamicaCampo;
import com.osmosyscol.datasuite.webdata.logica.servicios.carga.OperacionesTablaControl;
import com.osmosyscol.datasuite.webdata.logica.servicios.utils.XlsUtils;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.datasuite.webdata.utils.CargaMasivaSqlUtil;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CargaServicioSolicitud {
	// ---------------------------------------------------------------

	private static CargaServicioSolicitud cargaServicio;

	private CargaServicioSolicitud() {
	}

	// ---------------------------------------------------------------

	public static CargaServicioSolicitud getInstance() {
		if (cargaServicio == null) {
			cargaServicio = new CargaServicioSolicitud();
		}
		return cargaServicio;
	}

	// ---------------------------------------------------------------

	public List<Object> obtenerDatosCargaPorEstructura(Integer id_carga, Integer id_formato_salida) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager("CREADATOS");
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			EstructuraServicio estructuraServicio = EstructuraServicio.getInstance();
			List<Estructura> estructurasPorFormato = estructuraServicio.obtenerEstructurasPorFormatoDeSalida(id_formato_salida);

			List<Object> listaCargasEst = new LinkedList<Object>();

			for (Estructura estructura : estructurasPorFormato) {

				Map<String, Object> mapEstructura = new HashMap<String, Object>();

				Integer id_estructura = estructura.getId_estructura();

				List<FormatoCampo> formatoscampo = FormatoServicio.getInstance().obtenerFormatosCampoPorEstructura(id_estructura, id_formato_salida);

				List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(id_estructura);

				List<Map<String, Object>> datos = validacionCargaDao.obtenerRegistrosDeEstructuraPorCargaVista(id_carga, id_estructura);

				//definirFormatoDatos(datos, formatoscampo);

				mapEstructura.put("formatos_campo", formatoscampo);

				mapEstructura.put("campos", campos);

				mapEstructura.put("id_estructura", id_estructura);

				mapEstructura.put("nombre_estructura", estructura.getNombre());
				Boolean bandera = false;
				for (FormatoCampo formato : formatoscampo) {
					if (formato.getTitulo() != null) {
						String tituloCampo = StringUtils.upperCase(formato.getTitulo());
						if (tituloCampo.equals("ID CUENTA")) {
							for (Map<String, Object> dato : datos) {
								if (dato.containsKey("C" + formato.getId_campo().toString())) {
									Integer posicionDato = datos.indexOf(dato);
									Object temp = dato.get("C" + formato.getId_campo().toString());
									String cadenaDato = temp != null ? temp.toString(): "";
									if (cadenaDato.contains("-")) {
										Integer posicionGuion = cadenaDato.lastIndexOf("-");
										String cadenaTemp = cadenaDato.substring(posicionGuion + 1);
										if (!StringUtils.validaExpRegular(cadenaTemp, "[0-9]")) {
											cadenaDato = cadenaDato.substring(0, posicionGuion);
											dato.put("C" + formato.getId_campo().toString(), cadenaDato);
										}
									}
									datos.set(posicionDato, dato);
									bandera = true;
									break;
								}
							}
						}
					}
					if (bandera == true) {
						break;
					}
				}

				mapEstructura.put("datos", datos);

				listaCargasEst.add(mapEstructura);

			}
			return listaCargasEst;

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error",e);
		}
		return null;

	}

}


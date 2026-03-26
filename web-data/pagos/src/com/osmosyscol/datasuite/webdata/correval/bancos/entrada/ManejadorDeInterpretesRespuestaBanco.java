package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.utils.DocumentoUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.commons.lang.P;

public class ManejadorDeInterpretesRespuestaBanco {

	private HashMap<String, IInterpreteRespuestaBanco> interpretesRespuestaBanco;
	private static ManejadorDeInterpretesRespuestaBanco manejadorDeInterpretes;

	private ManejadorDeInterpretesRespuestaBanco() {

		interpretesRespuestaBanco = new HashMap<String, IInterpreteRespuestaBanco>();

		// Se registran las implementaciones por banco
		interpretesRespuestaBanco.put("6", new InterpreteRespuestaDavivienda());
		interpretesRespuestaBanco.put("7", new InterpreteRespuestaCitibank());
		interpretesRespuestaBanco.put("5", new InterpreteRespuestaBBVA());
		interpretesRespuestaBanco.put("3", new InterpreteRespuestaOccidente());
		interpretesRespuestaBanco.put("2", new InterpreteRespuestaBancolombia());
		interpretesRespuestaBanco.put("1", new InterpreteRespuestaBancoBogota());
		interpretesRespuestaBanco.put("8", new InterpreteRespuestaAgrario());
		interpretesRespuestaBanco.put("48", new InterpreteRespuestaFalabella());
		interpretesRespuestaBanco.put("4", new InterpreteRespuestaBancoColpatria());
		interpretesRespuestaBanco.put("32", new InterpreteRespuestaBancoCajaSocial());
	}

	public static ManejadorDeInterpretesRespuestaBanco getInstance() {
		if (manejadorDeInterpretes == null) {
			manejadorDeInterpretes = new ManejadorDeInterpretesRespuestaBanco();
		}

		return manejadorDeInterpretes;

	}

	public IInterpreteRespuestaBanco obtenerInterpretePorIDBanco(String id_banco) {

		IInterpreteRespuestaBanco interprete = (IInterpreteRespuestaBanco) interpretesRespuestaBanco.get(id_banco);
		return interprete;
	}

	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {

		List<RespuestaBanco> respuestasPorBanco = null;
		IInterpreteRespuestaBanco interprete = obtenerInterpretePorIDBanco(id_banco);

		if (interprete != null) {

			respuestasPorBanco = interprete.obtenerRespuestasPorBanco(rutaArchivo, id_banco);
			return respuestasPorBanco;

		} else {

			SimpleLogger.setError("No hay implementacion para el banco: " + id_banco);
			return null;
		}

	}

	public Boolean validarArchivo(String rutaArchivo, String id_banco) {

		Boolean esArchivoValido;
		IInterpreteRespuestaBanco interprete = obtenerInterpretePorIDBanco(id_banco);

		if (interprete != null) {

			esArchivoValido = interprete.validarArchivo(rutaArchivo, id_banco);
			return esArchivoValido;

		} else {

			SimpleLogger.setError("No hay implementacion para el banco: " + id_banco);
			return false;
		}

	}

	/**
	 * Retorna el id del retiro y retorna si el estado que tiene asociado es estado final
	 * 
	 * @param id_banco
	 * @param num_doc_beneficiario
	 * @param cuenta_beneficiario
	 * @param valor
	 * @param retiros_encontrados
	 * @return
	 */
	public RegistroEstado obtenerIDRetiroParaActualizarRespuestaBanco(String id_banco, String cod_proceso, String num_doc_beneficiario, String nombre_beneficiario, String cuenta_beneficiario, String valor, Set<String> retiros_encontrados) {

		num_doc_beneficiario = DocumentoUtils.ajustarNumeroDocumento(StringUtils.quitarCaracteresEspeciales(num_doc_beneficiario, "1234567890", null)) + "";

		cuenta_beneficiario = StringUtils.quitarCaracteresEspeciales(cuenta_beneficiario, "1234567890", null);

		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SQLDao sqlDao = (SQLDao) daoManager.getDao(SQLDao.class);

			NumberFormat formatter = new DecimalFormat("#0.00");
			valor = valor.replace(",", "");
			valor = formatter.format(new BigDecimal(valor));
			valor = valor.replace(",", ".");

			String select = "SELECT * FROM (" //
					+ " select r.ID IDRETIRO, b.$BENEFICIARIO.NUMERO DE DOCUMENTO$ num_doc, b.$BENEFICIARIO.PRIMER NOMBRE$ nombre, b.$BENEFICIARIO.NUMERO DE CUENTA$ cuenta," //
					+ "  bc.$BANCO.CODIGO$ banco,  r.$RETIROS.VALOR$ valor," //
					+ " coalesce( (SELECT nonull($ESTADO RESPUESTA BANCO.FINAL$) FROM $ESTADO RESPUESTA BANCO$" //
					+ " WHERE r.$RETIROS.ESTADO DE RESPUESTA BANCO$ = $ESTADO RESPUESTA BANCO.ESTADO INTERNO$" //
					+ " and  $ESTADO RESPUESTA BANCO.CODIGO BANCO$ = $S(" + id_banco + ")$ and rownum = 1 ), $B(false)$) estado" //
					+ " from $RETIROS$ r, $GRUPO GIRO$ gg, $BENEFICIARIO$ b, $CUENTA - BANCO$ cc, $BANCO$ bc" //
					+ " where r.ID = gg.$GRUPO GIRO.CODIGO REGISTRO$_NUM "; //

			if (cod_proceso != null && StringUtils.esVerdad(ParametrosInicio.getProperty("retiros.valida_codigobanco"))) {
				select += " and r.$RETIROS.COD_PROCESO$ = $S(" + cod_proceso + ")$  ";
			}

			select += " and r.$RETIROS.BENEFICIARIO$ = b.ID " //
					+ " and gg.$GRUPO GIRO.CUENTA$ = cc.ID " //
					+ " and cc.$CUENTA - BANCO.BANCO$= bc.ID " //
					+ " and gg.$GRUPO GIRO.ARCHIVO$ is not null " //
					+ " and r.$RETIROS.VALOR$ = $D(" + valor + ")$ ";
					if(num_doc_beneficiario!=null && !num_doc_beneficiario.equals("null")){
						select += " and ( b.$BENEFICIARIO.NUMERO DE DOCUMENTO$ =  $I(" + num_doc_beneficiario + ")$ or b.$BENEFICIARIO.NUMERO DE DOCUMENTO$ =  $I(" + num_doc_beneficiario + StringUtils.calcularDigitoVerificacion(num_doc_beneficiario) + ")$  )";
					}
					select += ") DD" //
					+ " order by DD.estado desc, DD.IDRETIRO desc";

			select = RDServicio.reemplazarNombres(select);
			SimpleLogger.setDebug(select);
			System.out.println(select);

			List<Map<String, Object>> respuesta = sqlDao.selectSQL(select);

			RegistroEstado registroestado = null;

			if (respuesta != null) {

				for (Map<String, Object> registro : respuesta) {

					Object idretiro = registro.get("IDRETIRO");
					Object estado = registro.get("ESTADO");
					Object cuenta = Crypto.DF(registro.get("CUENTA"));
					Object nombre= Crypto.DF(registro.get("NOMBRE"));

					if (cuenta != null) {
						cuenta = StringUtils.quitarCaracteresEspeciales(cuenta.toString(), "1234567890", null);
					}

					boolean cuentaCoincide = false;
					boolean nombreCoincide = false;
					
					if(nombre.toString().contains(nombre_beneficiario) || nombre_beneficiario.contains(nombre.toString())){
						nombreCoincide=true;
					}

					if (cuenta_beneficiario.length() == 4) {
						String cuentaBD = cuenta.toString();
						if (cuentaBD.substring(cuentaBD.length() - 4, cuentaBD.length()).equals(cuenta_beneficiario)) {
							cuentaCoincide = true;
						}

					} else if (Long.parseLong(cuenta.toString()) == Long.parseLong(cuenta_beneficiario)) {
						cuentaCoincide = true;
					}

					if (registroestado == null && !retiros_encontrados.contains(idretiro) && idretiro != null && cuenta != null && cuentaCoincide && nombreCoincide) {

						if (estado == null || StringUtils.esVacio(estado.toString())) {
							estado = "E";
						} else {
							estado = Crypto.DF(estado);
							estado = (Boolean) estado ? "ES" : "EN";
						}
						registroestado = new RegistroEstado(idretiro.toString(), estado.toString());
						retiros_encontrados.add(idretiro.toString());
					}

				}
			}

			P.println(registroestado);

			return registroestado;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}
	/**
	 * Retorna el id del retiro y retorna si el estado que tiene asociado es estado final
	 * 
	 * @param id_banco
	 * @param num_doc_beneficiario
	 * @param cuenta_beneficiario
	 * @param valor
	 * @param retiros_encontrados
	 * @return
	 */
	public RegistroEstado obtenerIDRetiroParaActualizarRespuestaBanco(String id_banco, String cod_proceso, String num_doc_beneficiario, String cuenta_beneficiario, String valor, Set<String> retiros_encontrados) {

		num_doc_beneficiario = DocumentoUtils.ajustarNumeroDocumento(StringUtils.quitarCaracteresEspeciales(num_doc_beneficiario, "1234567890", null)) + "";

		cuenta_beneficiario = StringUtils.quitarCaracteresEspeciales(cuenta_beneficiario, "1234567890", null);

		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SQLDao sqlDao = (SQLDao) daoManager.getDao(SQLDao.class);

			NumberFormat formatter = new DecimalFormat("#0.00");
			valor = valor.replace(",", "");
			valor = formatter.format(new BigDecimal(valor));
			valor = valor.replace(",", ".");

			String select = "SELECT * FROM (" //
					+ " select r.ID IDRETIRO, b.$BENEFICIARIO.NUMERO DE DOCUMENTO$ num_doc, b.$BENEFICIARIO.NUMERO DE CUENTA$ cuenta," //
					+ "  bc.$BANCO.CODIGO$ banco,  r.$RETIROS.VALOR$ valor," //
					+ " coalesce( (SELECT nonull($ESTADO RESPUESTA BANCO.FINAL$) FROM $ESTADO RESPUESTA BANCO$" //
					+ " WHERE r.$RETIROS.ESTADO DE RESPUESTA BANCO$ = $ESTADO RESPUESTA BANCO.ESTADO INTERNO$" //
					+ " and  $ESTADO RESPUESTA BANCO.CODIGO BANCO$ = $S(" + id_banco + ")$ and rownum = 1 ), $B(false)$) estado" //
					+ " from $RETIROS$ r, $GRUPO GIRO$ gg, $BENEFICIARIO$ b, $CUENTA - BANCO$ cc, $BANCO$ bc" //
					+ " where r.ID = gg.$GRUPO GIRO.CODIGO REGISTRO$_NUM "; //

			if (cod_proceso != null && StringUtils.esVerdad(ParametrosInicio.getProperty("retiros.valida_codigobanco"))) {
				select += " and r.$RETIROS.COD_PROCESO$ = $S(" + cod_proceso + ")$  ";
			}

			select += " and r.$RETIROS.BENEFICIARIO$ = b.ID " //
					+ " and gg.$GRUPO GIRO.CUENTA$ = cc.ID " //
					+ " and cc.$CUENTA - BANCO.BANCO$= bc.ID " //
					+ " and gg.$GRUPO GIRO.ARCHIVO$ is not null " //
					+ " and r.$RETIROS.VALOR$ = $D(" + valor + ")$ " //
					+ " and ( b.$BENEFICIARIO.NUMERO DE DOCUMENTO$ =  $I(" + num_doc_beneficiario + ")$ or b.$BENEFICIARIO.NUMERO DE DOCUMENTO$ =  $I(" + num_doc_beneficiario + StringUtils.calcularDigitoVerificacion(num_doc_beneficiario) + ")$  )" //
					+ ") DD" //
					+ " order by DD.estado desc, DD.IDRETIRO desc";

			select = RDServicio.reemplazarNombres(select);
			SimpleLogger.setDebug(select);
			System.out.println(select);

			List<Map<String, Object>> respuesta = sqlDao.selectSQL(select);

			RegistroEstado registroestado = null;

			if (respuesta != null) {

				for (Map<String, Object> registro : respuesta) {

					Object idretiro = registro.get("IDRETIRO");
					Object estado = registro.get("ESTADO");
					Object cuenta = Crypto.DF(registro.get("CUENTA"));

					if (cuenta != null) {
						cuenta = StringUtils.quitarCaracteresEspeciales(cuenta.toString(), "1234567890", null);
					}

					boolean cuentaCoincide = false;

					if (cuenta_beneficiario.length() == 4) {
						String cuentaBD = cuenta.toString();
						if (cuentaBD.substring(cuentaBD.length() - 4, cuentaBD.length()).equals(cuenta_beneficiario)) {
							cuentaCoincide = true;
						}

					} else if (Long.parseLong(cuenta.toString()) == Long.parseLong(cuenta_beneficiario)) {
						cuentaCoincide = true;
					}

					if (registroestado == null && !retiros_encontrados.contains(idretiro) && idretiro != null && cuenta != null && cuentaCoincide) {

						if (estado == null || StringUtils.esVacio(estado.toString())) {
							estado = "E";
						} else {
							estado = Crypto.DF(estado);
							estado = (Boolean) estado ? "ES" : "EN";
						}
						registroestado = new RegistroEstado(idretiro.toString(), estado.toString());
						retiros_encontrados.add(idretiro.toString());
					}

				}
			}

			P.println(registroestado);

			return registroestado;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public List<Map<String, String>> obtenerBancosImplementados() {
		ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();

		Set<String> keys = interpretesRespuestaBanco.keySet();

		for (String key : keys) {

			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put(key, interpretesRespuestaBanco.get(key).getNombreBanco());
			arrayList.add(hashMap);

		}

		return arrayList;
	}

	HashMap<String, String> mapaEstadoInternoBanco = new HashMap<String, String>();

	public String obtenerEstadoInternoEstadoBanco(String estadoBanco, String cod_banco) {

		try {

			if (mapaEstadoInternoBanco.containsKey(estadoBanco + cod_banco)) {
				return mapaEstadoInternoBanco.get(estadoBanco + cod_banco);

			}

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SQLDao sqlDao = (SQLDao) daoManager.getDao(SQLDao.class);

			String select = "select $ESTADO RESPUESTA BANCO.ESTADO INTERNO$ estado " + " FROM $ESTADO RESPUESTA BANCO$ " + " WHERE $ESTADO RESPUESTA BANCO.ESTADO BANCO$ = $S(" + estadoBanco.trim().toUpperCase() + ")$ " + " and $ESTADO RESPUESTA BANCO.CODIGO BANCO$ = $S(" + cod_banco + ")$";

			select = RDServicio.reemplazarNombres(select);
			SimpleLogger.setDebug(select);

			String estado = null;

			estado = sqlDao.selectSQLString(select);

			if (estado == null) {
				String selectDefault = "select $ESTADO RESPUESTA BANCO.ESTADO INTERNO$ estado " + " FROM $ESTADO RESPUESTA BANCO$ " + " WHERE $ESTADO RESPUESTA BANCO.ESTADO BANCO$ = $S(DEFAULT)$ " + " and $ESTADO RESPUESTA BANCO.CODIGO BANCO$ = $S(" + cod_banco + ")$";

				selectDefault = RDServicio.reemplazarNombres(selectDefault);
				SimpleLogger.setDebug(select);

				estado = sqlDao.selectSQLString(selectDefault);
			}

			// Si no encuentra en ningun caso un estado se deja el mismo estado
			if (estado == null) {
				estado = estadoBanco;
			} else {
				// Se mantiene el valor visible
				estado = Crypto.D(estado).toString();
			}

			mapaEstadoInternoBanco.put(estadoBanco + cod_banco, estado);

			return estado;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public RespuestaBanco obtenerRespuestaBanco(RegistroEstado registroestado, String estadoBanco, String id_banco, Integer filaArchivo, String num_cuenta, String num_doc, String valor) {

		num_doc = DocumentoUtils.ajustarNumeroDocumento(StringUtils.quitarCaracteresEspeciales(num_doc, "1234567890", null)) + "";

		num_cuenta = StringUtils.quitarCaracteresEspeciales(num_cuenta, "1234567890", null);

		String nvalor = valor + "";
		try {
			NumberFormat formatter = new DecimalFormat("#,##0.00");
			valor = valor.replace(",", "");
			valor = formatter.format(new BigDecimal(valor));
			valor = valor.replace(",", ".");
		} catch (Exception e) {
			valor = nvalor;
		}

		RespuestaBanco respuestaBanco = null;

		if (registroestado != null && registroestado.existe()) {

			if (registroestado.esNoFinal()) {

				// Se consulta el estado interno a colocar
				String estadoInterno = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerEstadoInternoEstadoBanco(estadoBanco, id_banco);

				respuestaBanco = new RespuestaBanco();
				respuestaBanco.setCodigo_referencia(registroestado.getId_registro());
				respuestaBanco.setEstado_respuesta(estadoInterno);
				respuestaBanco.setTieneErrores(false);
				respuestaBanco.setFilaArchivo("" + filaArchivo);
				respuestaBanco.setMensajeRespuesta("El registro con cuenta: " + num_cuenta + ", beneficiario: " + num_doc + " valor: " + valor + ", se actualizó exitosamente");

			}

			if (registroestado.esFinal()) {

				respuestaBanco = new RespuestaBanco();
				respuestaBanco.setTieneErrores(true);
				respuestaBanco.setFilaArchivo("" + filaArchivo);
				respuestaBanco.setMensajeRespuesta("El registro con cuenta: " + num_cuenta + ", beneficiario: " + num_doc + " valor: " + valor + ", no puede ser actualizado porque se encuentra en un estado de finalización");

				SimpleLogger.setError("No se puede cambiar el estado de la cuenta: " + num_cuenta + ", doc: " + num_doc + " valor: " + valor + ", se encuentra en un estado final. ");
			}

		} else {

			respuestaBanco = new RespuestaBanco();
			respuestaBanco.setTieneErrores(true);
			respuestaBanco.setFilaArchivo("" + filaArchivo);
			respuestaBanco.setMensajeRespuesta("No existe retiro para la cuenta: " + num_cuenta + ", beneficiario: " + num_doc + " valor: " + valor);

			SimpleLogger.setInfo("No existe retiro para la cuenta:" + num_cuenta + ", doc:" + num_doc + " valor:" + valor);
		}

		return respuestaBanco;

	}

	public static String getValorCelda(Cell celda) {
		String valor = "";

		try {

			switch (celda.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				valor = celda.getRichStringCellValue().getString();
				break;

			case Cell.CELL_TYPE_BOOLEAN:
				valor = celda.getBooleanCellValue() ? Constantes.SI : Constantes.NO;
				break;

			case Cell.CELL_TYPE_NUMERIC:
				valor = new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace(".00", "");
				break;

			default:
				break;
			}

		} catch (Exception e) {
		}

		return valor;

	}
	
	public Workbook leerWorkbook(String rutaArchivo) {
		try {
			InputStream inp = new BufferedInputStream(new FileInputStream(rutaArchivo));
			if(POIFSFileSystem.hasPOIFSHeader(inp)) {
				return new HSSFWorkbook(inp);
			}else if(POIXMLDocument.hasOOXMLHeader(inp)) {
				return new XSSFWorkbook(inp);
			}else {
				SimpleLogger.setError("Formato archivo no corresponde.");
				return null;
			}
		}catch(Exception e) {
			SimpleLogger.setError("Error", e);
			return null;
		}
	}


}

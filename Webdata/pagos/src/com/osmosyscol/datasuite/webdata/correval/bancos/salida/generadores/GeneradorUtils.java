package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class GeneradorUtils {

	// ---------------------------------
	// AQUI SE DEBEN AGREGAR LOS DIREFENTES BANCOS SEGUN TABLA

	private static Map<String, String> CODBANCO = new HashMap<String, String>();
	private static Map<String, Long> LONGITUDBANCO = new HashMap<String, Long>();

	static {
		CODBANCO.put("BANCOLOMBIA", "2");
		CODBANCO.put("BOGOTA", "1");
		CODBANCO.put("DAVIVIENDA", "6");
		CODBANCO.put("COLPATRIA", "4");
		CODBANCO.put("OCCIDENTE", "3");
		CODBANCO.put("CITIBANK", "7");
		CODBANCO.put("BBVA", "5");
		CODBANCO.put("AGRARIO", "8");
		CODBANCO.put("FALABELLA", "48");
		CODBANCO.put("CAJASOCIAL", "32");
		CODBANCO.put("PILA", "PL");
		CODBANCO.put("CESANTIAS", "PC");
		CODBANCO.put("NEQUI", "NQ");
		CODBANCO.put("HTS", "HTS");
		CODBANCO.put("BANCOLOMBIA PAB", "128");
		CODBANCO.put("AVVILLAS", "15");
		CODBANCO.put("ITAU", "64");
	}

	// **************************************************************************
	// **************************************************************************

	private String desc_banco;

	public GeneradorUtils(String cod_banco) {
		this.desc_banco = getDescBanco(cod_banco);
	}

	// ---------------------------------

	public static String getCodBancoByDesc(String des) {
		return CODBANCO.get(des.toUpperCase().trim());
	}

	public static String getDescBanco(String cod) {
		Set<String> descr = CODBANCO.keySet();

		for (String descripcion : descr) {
			if (cod.equalsIgnoreCase(CODBANCO.get(descripcion))) {
				return descripcion;
			}
		}
		return null;
	}

	// ------------------------------------------

	private static DecimalFormat myFormatter = new DecimalFormat("###.00");
	private static DecimalFormat myFormatter2 = new DecimalFormat("###");

	public String formato2Decimales(BigDecimal valor) {

		if (valor != null) {
			return myFormatter.format(valor).replace(",", "").replace(".", "");
		}
		return "000";
	}

	public String formato2DecimalesPunto(BigDecimal valor) {

		if (valor != null) {
			return myFormatter.format(valor).replace(",", ".");
		}
		return "000";
	}

	public String formatoSinDecimales(BigDecimal valor) {

		if (valor != null) {
			return myFormatter2.format(valor);
		}
		return "0";
	}

	public String formatoParteDecimal(BigDecimal valor) {
		if (valor != null) {

			BigDecimal decimal = valor.remainder(BigDecimal.ONE);

			if (!decimal.equals(new BigDecimal(0))) {
				String resultado = decimal.multiply(BigDecimal.TEN.pow(decimal.scale())).toString();
				return resultado.substring(0, 2);
			} else {
				return "00";
			}

		}
		return "00";

	}

	// -----------------------------------------------
	// -----------------------------------------------

	public String getCodBanco(String cbanco) {
		return getValor("BANCO", cbanco);
	}

	// -----------------------------------------------
	// -----------------------------------------------

	public String getCodBancoInterno(String cbanco) {
		String codigo = getValorInterno("BANCO", cbanco);

		if (codigo == null && "BANCOLOMBIA".equals(desc_banco)) {
			desc_banco = "BANCOLOMBIA_2";
			codigo = getValorInterno("BANCO", cbanco);
			desc_banco = "BANCOLOMBIA";
		}


		if (codigo == null && "BANCOLOMBIA".equals(desc_banco)) {
			desc_banco = "BANCOLOMBIA_3";
			codigo = getValorInterno("BANCO", cbanco);
			desc_banco = "BANCOLOMBIA";
		}
		
		return codigo;
	}

	// -----------------------------------------------
	// -----------------------------------------------

	public String getTipoCuenta(String cod_tipocuenta) {
		return getValor("TIPO DE CUENTA", cod_tipocuenta);
	}

	// -----------------------------------------------
	// -----------------------------------------------

	public String getTipoCuentaInterno(String cod_tipocuenta_banco) {
		return getValorInterno("TIPO DE CUENTA", cod_tipocuenta_banco);
	}

	// -----------------------------------------------
	// -----------------------------------------------

	public String getTipoDocumento(String cod_tipodocumento) {
		return getValor("TIPO DE DOCUMENTO", cod_tipodocumento);
	}

	// -----------------------------------------------
	// -----------------------------------------------

	public String getTipoDocumentoInterno(String cod_tipodocumento) {
		return getValorInterno("TIPO DE DOCUMENTO", cod_tipodocumento);
	}

	// -----------------------------------------------
	// -----------------------------------------------

	private Map<String, List<Map<String, Object>>> listados = new HashMap<String, List<Map<String, Object>>>();

	private String getValor(String tabla, String codigo) {
		listados.put(tabla, valoresTabla(tabla));

		return getValor(listados.get(tabla), codigo);
	}

	// -----------------------------------------------
	// -----------------------------------------------

	private String getValorInterno(String tabla, String codigo) {
		listados.put(tabla, valoresTabla(tabla));

		return getValorInterno(listados.get(tabla), codigo);
	}

	// -----------------------------------------------
	// -----------------------------------------------

	private String getValor(List<Map<String, Object>> listado, String codigo) {

		for (Map<String, Object> registro : listado) {
			if (codigo.equalsIgnoreCase(registro.get("COD").toString())) {
				return (registro.get("VALOR") == null) ? null : registro.get("VALOR").toString();
			}
		}

		return null;
	}

	// -----------------------------------------------
	// -----------------------------------------------

	private String getValorInterno(List<Map<String, Object>> listado, String codigo) {

		for (Map<String, Object> registro : listado) {

			if (registro.get("VALOR") != null && codigo.equalsIgnoreCase(registro.get("VALOR").toString())) {
				return (registro.get("ID") == null) ? null : registro.get("ID").toString();
			}
		}

		return null;
	}

	// -----------------------------------------------
	// -----------------------------------------------

	private List<Map<String, Object>> valoresTabla(String tabla) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			String sql = RDServicio.reemplazarNombres("SELECT $" + tabla + ".CODIGO$ cod, $" + tabla + ".COD_" + desc_banco + "$ valor, ID FROM $" + tabla + "$ WHERE idcarga=0");

			List<Map<String, Object>> valores = cargaDao.selectSql(sql);

			if (valores != null) {
				for (Map<String, Object> registro : valores) {
					Set<String> keys = registro.keySet();

					for (String key : keys) {
						registro.put(key, Crypto.DF(registro.get(key)));
					}

				}
			}

			return valores;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public static void main(String[] args) {
		GeneradorUtils generadorUtils = new GeneradorUtils("");
		SimpleLogger.setDebug(generadorUtils.formatoParteDecimal(new BigDecimal(1000.1)));

	}

	public String ajustarLongitudCuenta(String cuenta, String codigoBanco) {

		if (codigoBanco == null) {
			return cuenta;
		}

		Long longitud = LONGITUDBANCO.get(codigoBanco);

		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			if (longitud == null) {

				String sql = RDServicio.reemplazarNombres("SELECT $BANCO.LONGITUD$ longitud FROM $BANCO$ WHERE ID=" + codigoBanco);

				Map<String, Object> registro = cargaDao.simpleselectSql(sql);

				if (registro != null) {
					Set<String> keys = registro.keySet();
					for (String key : keys) {
						try {
							longitud = (Long) Crypto.DF(registro.get(key));
						} catch (Exception e) {
							SimpleLogger.setError("Error al obtener longitud. registro: " + registro + " key:" + key);
						}
					}
				}

				LONGITUDBANCO.put(codigoBanco, longitud);
			}

			if (longitud != null && cuenta.length() > longitud) {
				cuenta = cuenta.substring((int) (cuenta.length() - longitud));
			} else {
				String validarPropiedad = ParametrosInicio.getProperty("retiros.noQuitarCaracteresEspeciales");
				if( validarPropiedad == null || validarPropiedad.equals("false") ) {
					cuenta = StringUtils.onlyNumbers(cuenta);// new Long(cuenta).toString();
				}
			}

			return cuenta;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public void aL(StringBuffer r, char c, int num, Object obj) {
		String a = StringUtils.izquierda(obj, num, c);
		a = a.substring(0, num);
		r.append(a);
	}

	public void aR(StringBuffer r, char c, int num, Object obj) {
		String a = StringUtils.derecha(obj, num, c);
		a = a.substring(0, num);
		r.append(a);
	}

}

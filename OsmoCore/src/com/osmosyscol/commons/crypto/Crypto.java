package com.osmosyscol.commons.crypto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.rsa.RSA;

/**
 * @see CryptoTest
 * @author osmosys
 * 
 */
public class Crypto {

	private static Integer tipoEncripcion = 3;
	
	public static void setTipoEncripcion(Integer tipoEncripcion) {
		Crypto.tipoEncripcion = tipoEncripcion;
	}

	// ------------------------------------------------------------------------

	private static Map<String, Object> desencriptados = new ConcurrentHashMap<String, Object>();
	private static Map<Object, String> encriptados = new ConcurrentHashMap<Object, String>();

	public static final SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static String E1(Object mensaje) {

		String tipo = "O";

		if (mensaje == null) {
			return null;
		}

		String msg = (mensaje) + "";

		if (mensaje instanceof String) {
			msg = (String) mensaje;
			tipo = "S";
		}

		if (mensaje instanceof Float || mensaje instanceof Double || mensaje instanceof BigDecimal) {

			NumberFormat formatter = new DecimalFormat("#0.00");

			String data = formatter.format(mensaje).replace(",", ".");

			String[] p = data.split("[.]");
			msg = StringUtils.izquierda(p[0], 20, '0') + "." + p[1];
			tipo = "D";
		}

		if (mensaje instanceof Boolean) {
			msg = ((Boolean) mensaje) ? "true" : "false";
			tipo = "B";
		}

		if (mensaje instanceof Date) {
			msg = formatofecha.format(mensaje);
			tipo = "T";
		}

		if (mensaje instanceof Integer || mensaje instanceof Long) {
			msg = StringUtils.izquierda(msg, 20, '0');
			tipo = "I";
		}

		String ret = tipo + StringUtils.simpleCripto(msg) + ";" + RSA.encripta(msg, RSA.clave32, 0);

		return ret;

	}

	public synchronized static Object D1(Object mensaje) {

		String tipo = "S";

		if (mensaje == null || mensaje.toString().trim().length() == 0) {
			return null;
		}

		// -----------------------

		String m = mensaje.toString().trim();

		Object dd = null;

		if (m.charAt(0) == '[' && m.indexOf("]") > 0) {
			dd = D1(m.substring(m.indexOf("]") + 1, m.length()).trim());

			return dd;
		}

		if (m.indexOf(" ") > 0) {
			StringBuffer res = new StringBuffer("");
			String m_array[] = m.split(" ");
			for (String d : m_array) {
				Object des = D1(d);

				if (des != null) {
					res.append(des + " ");
				} else {
					return null;
				}

			}

			dd = res.toString().trim();

			return dd;

		}

		// -----------------------

		Object res = null;

		Pattern pattern = Pattern.compile("^[OSIBDT][0-9]*[;][0-9a-zA-Z]*$");
		Matcher matcher = pattern.matcher(mensaje.toString());
		if (matcher.find()) {

			if (mensaje instanceof String) {

				tipo = "" + ((String) mensaje).charAt(0);

				if (((String) mensaje).indexOf(";") > 0) {
					mensaje = ((String) mensaje).substring(((String) mensaje).indexOf(";") + 1);
				}
			}

			String data = RSA.desencripta(mensaje + "", RSA.clave32);

			if ("O".equalsIgnoreCase(tipo)) {
				res = "NO-SUPPORTED";
			}

			if ("S".equalsIgnoreCase(tipo)) {
				res = data;
			}

			if ("I".equalsIgnoreCase(tipo)) {
				res = new Long(quitarCerosIzquierda(data));
			}

			if ("B".equalsIgnoreCase(tipo)) {
				res = Boolean.valueOf(data);
			}

			if ("D".equalsIgnoreCase(tipo)) {
				res = new BigDecimal(quitarCerosIzquierda(data));
			}

			if ("T".equalsIgnoreCase(tipo)) {
				try {
					res = formatofecha.parse(data);
				} catch (Throwable e) {
					SimpleLogger.setError("Desencriptado..", e);
				}
			}
		}

		return res;
	}

	public static Object DF1(Object mensaje) {

		if (mensaje == null) {
			return null;
		}

		String m = mensaje.toString().trim();

		if (m == null || m.length() == 0) {
			return m;
		}

		if (m.charAt(0) == '[' && m.indexOf("]") > 0) {
			return DF1(m.substring(m.indexOf("]") + 1, m.length()).trim());
		}

		if (m.indexOf(" ") > 0) {
			StringBuffer res = new StringBuffer("");
			String m_array[] = m.split(" ");
			for (String d : m_array) {
				Object des = DF1(d);

				if (des != null) {
					res.append(StringUtils.toString(des) + " ");
				}

			}
			return res.toString().trim();
		}

		// -----------------------------

		Object enc = mensaje;
		if (enc != null) {
			enc = enc.toString().trim();
		}

		Object valor = D1(enc);

		if (valor == null) {
			valor = mensaje;
		}
		return valor;
	}

	public static String quitarCerosIzquierda(String texto) {
		if (texto == null) {
			return null;
		}

		while (texto.length() > 1 && texto.charAt(0) == '0') {
			texto = texto.substring(1);
		}

		return texto;
	}

	// *****************************************************************************

	public static String E2(Object mensaje) {

		String tipo = "O";

		if (mensaje == null) {
			return null;
		}

		String msg = (mensaje) + "";

		if (mensaje instanceof String) {
			msg = (String) mensaje;
			tipo = "S";
		}

		if (mensaje instanceof Float || mensaje instanceof Double || mensaje instanceof BigDecimal) {

			NumberFormat formatter = new DecimalFormat("#0.00");

			String data = formatter.format(mensaje).replace(",", ".");

			String[] p = data.split("[.]");
			msg = StringUtils.izquierda(p[0], 20, '0') + "." + p[1];
			tipo = "D";
		}

		if (mensaje instanceof Boolean) {
			msg = ((Boolean) mensaje) ? "true" : "false";
			tipo = "B";
		}

		if (mensaje instanceof Date) {
			msg = formatofecha.format(mensaje);
			tipo = "T";
		}

		if (mensaje instanceof Integer || mensaje instanceof Long) {
			msg = StringUtils.izquierda(msg, 20, '0');
			tipo = "I";
		}

		// --------------------

		BigInteger bi = new BigInteger("1" + StringUtils.simpleCripto(msg));
		String p = bi.toString(35).toUpperCase();
		String md = StringUtils.MD5(RSA.encripta(p, RSA.clave32, 0));
		String ret = tipo + p + md;

		return ret;
	}

	// ----

	public synchronized static Object D2(Object mensaje) {

		String tipo = "S";

		if (mensaje == null || mensaje.toString().trim().length() == 0) {
			return null;
		}

		// -----------------------

		String m = mensaje.toString().trim();

		Object dd = null;

		if (m.charAt(0) == '[' && m.indexOf("]") > 0) {
			dd = D2(m.substring(m.indexOf("]") + 1, m.length()).trim());

			return dd;
		}

		if (m.indexOf(" ") > 0) {
			StringBuffer res = new StringBuffer("");
			String m_array[] = m.split(" ");
			for (String d : m_array) {
				Object des = D2(d);

				if (des != null) {
					res.append(des + " ");
				} else {
					return null;
				}

			}

			dd = res.toString().trim();

			return dd;

		}

		// -----------------------

		Object res = null;

		Pattern pattern = Pattern.compile("^[OSIBDT][0-9a-zA-Z:.]*$");
		Matcher matcher = pattern.matcher(mensaje.toString());

		if (matcher.find()) {

			String data = null;

			try {

				String msg = mensaje.toString();

				tipo = "" + msg.charAt(0);

				if (msg.length() > 32) {

					String p = msg.substring(1, msg.length() - 32);
					String md = StringUtils.MD5(RSA.encripta(p, RSA.clave32, 0));

					if (md.equals(msg.substring(msg.length() - 32))) {
						data = StringUtils.simpleDesCripto(new BigInteger(p, 35).toString().substring(1));
					}
				}

			} catch (Exception e) {
				SimpleLogger.setError("Error al desemcriptar.." + mensaje, e);
				data = null;
			}

			if (data == null) {
				return null;
			}

			// -----------------

			if ("O".equalsIgnoreCase(tipo)) {
				res = "NO-SUPPORTED";
			}

			if ("S".equalsIgnoreCase(tipo)) {
				res = data;
			}

			if ("I".equalsIgnoreCase(tipo)) {
				res = new Long(quitarCerosIzquierda(data));
			}

			if ("B".equalsIgnoreCase(tipo)) {
				res = Boolean.valueOf(data);
			}

			if ("D".equalsIgnoreCase(tipo)) {
				res = new BigDecimal(quitarCerosIzquierda(data));
			}

			if ("T".equalsIgnoreCase(tipo)) {
				try {
					res = formatofecha.parse(data);
				} catch (Throwable e) {
					SimpleLogger.setError("Desencriptado..", e);
				}
			}
		}

		return res;
	}

	// ----------

	public static Object DF2(Object mensaje) {

		if (mensaje == null) {
			return null;
		}

		String m = mensaje.toString().trim();

		if (m == null || m.length() == 0) {
			return m;
		}

		if (m.charAt(0) == '[' && m.indexOf("]") > 0) {
			return DF2(m.substring(m.indexOf("]") + 1, m.length()).trim());
		}

		if (m.indexOf(" ") > 0) {
			StringBuffer res = new StringBuffer("");
			String m_array[] = m.split(" ");
			for (String d : m_array) {
				Object des = DF2(d);

				if (des != null) {
					res.append(StringUtils.toString(des) + " ");
				}

			}
			return res.toString().trim();
		}

		// -----------------------------

		Object enc = mensaje;
		if (enc != null) {
			enc = enc.toString().trim();
		}

		Object valor = D2(enc);

		if (valor == null) {
			valor = mensaje;
		}
		return valor;
	}

	// ----------

	// *****************************************************************************

	private static String CRYPO_ORI = "0123456789abcdefghijklmnop";
	private static String CRYPO_DES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	// *****************************************************************************

	public static String E3(Object mensaje) {

		String tipo = "O";

		if (mensaje == null ) {
			return null;
		}
		
		String msg = (mensaje) + "";

		if (mensaje instanceof String) {
			msg = (String) mensaje;
			tipo = "S";
		}

		if (mensaje instanceof Float || mensaje instanceof Double || mensaje instanceof BigDecimal) {

			NumberFormat formatter = new DecimalFormat("#0.00");

			String data = formatter.format(mensaje).replace(",", ".");

			String[] p = data.split("[.]");
			msg = StringUtils.izquierda(p[0], 20, '0') + "." + p[1];
			tipo = "D";
		}

		if (mensaje instanceof Boolean) {
			msg = ((Boolean) mensaje) ? "true" : "false";
			tipo = "B";
		}

		if (mensaje instanceof Date) {
			msg = formatofecha.format(mensaje);
			tipo = "T";
		}

		if (mensaje instanceof Integer || mensaje instanceof Long) {
			msg = StringUtils.izquierda(msg, 20, '0');
			tipo = "I";
		}

		// --------------------

		BigInteger bi = new BigInteger("1" + StringUtils.simpleCripto(msg));
		String p = bi.toString(25).toLowerCase();

		p = traslate(p, CRYPO_ORI, CRYPO_DES);

		String md = StringUtils.MD5(RSA.encripta(p, RSA.clave32, 0));
		String ret = tipo + p + md;

		return ret;
	}

	// ----

	private static String traslate(String t, String a, String b) {

		if (t != null) {

			for (int i = 0; i < a.length(); i++) {
				t = t.replace(a.charAt(i), b.charAt(i));
			}

		}

		return t;
	}

	// ----

	public synchronized static Object D4(Object mensaje) {

		if (mensaje == null || mensaje.toString().trim().length() == 0) {
			return null;
		}

		// -----------------------

		String m = mensaje.toString().trim();

		Object dd = desencriptados.get(m);
		if (dd != null) {
			return dd;
		}

		dd = D3(mensaje);

		// ConcurrentHashMap no permite null
		if (dd != null) {
			desencriptados.put(m, dd);
		}

		return dd;
	}

	public synchronized static Object D3(Object mensaje) {

		String tipo = "S";

		if (mensaje == null || mensaje.toString().trim().length() == 0) {
			return null;
		}

		// -----------------------

		String m = mensaje.toString().trim();

		Object dd = null;

		if (m.charAt(0) == '[' && m.indexOf("]") > 0) {
			dd = D3(m.substring(m.indexOf("]") + 1, m.length()).trim());

			return dd;
		}

		if (m.indexOf(" ") > 0) {
			StringBuffer res = new StringBuffer("");
			String m_array[] = m.split(" ");
			for (String d : m_array) {
				Object des = D3(d);

				if (des != null) {
					res.append(des + " ");
				} else {
					return null;
				}

			}

			dd = res.toString().trim();

			return dd;

		}

		// -----------------------

		Object res = null;

		Pattern pattern = Pattern.compile("^[OSIBDT][0-9a-zA-Z:.]*$");
		Matcher matcher = pattern.matcher(mensaje.toString());

		if (matcher.find()) {

			String data = null;

			try {

				String msg = mensaje.toString();

				tipo = "" + msg.charAt(0);

				if (msg.length() > 32) {

					String p = msg.substring(1, msg.length() - 32);
					String md = StringUtils.MD5(RSA.encripta(p, RSA.clave32, 0));

					if (md.equals(msg.substring(msg.length() - 32))) {
						data = StringUtils.simpleDesCripto(new BigInteger(traslate(p, CRYPO_DES, CRYPO_ORI), 25).toString().substring(1));
					}
				}

			} catch (Exception e) {
				SimpleLogger.setError("Error al desemcriptar.." + mensaje, e);
				data = null;
			}

			if (data == null) {
				return null;
			}

			// -----------------

			if ("O".equalsIgnoreCase(tipo)) {
				res = "NO-SUPPORTED";
			}

			if ("S".equalsIgnoreCase(tipo)) {
				res = data;
			}

			if ("I".equalsIgnoreCase(tipo)) {
				res = new Long(quitarCerosIzquierda(data));
			}

			if ("B".equalsIgnoreCase(tipo)) {
				res = Boolean.valueOf(data);
			}

			if ("D".equalsIgnoreCase(tipo)) {
				res = new BigDecimal(quitarCerosIzquierda(data));
			}

			if ("T".equalsIgnoreCase(tipo)) {
				try {
					res = formatofecha.parse(data);
				} catch (Throwable e) {
					SimpleLogger.setError("Desencriptado..", e);
				}
			}
		}

		return res;
	}

	public synchronized static Object D0(Object mensaje) {

		String tipo = "S";

		if (mensaje == null || mensaje.toString().trim().length() == 0) {
			return null;
		}

		// -----------------------

		String m = mensaje.toString().trim();

		Object dd = null;

		if (m.charAt(0) == '[' && m.indexOf("]") > 0) {
			dd = D0(m.substring(m.indexOf("]") + 1, m.length()).trim());
			return dd;
		}

		if (m.indexOf(" ") > 0) {
			StringBuffer res = new StringBuffer("");
			String m_array[] = m.split(" ");
			for (String d : m_array) {
				Object des = D0(d);

				if (des != null) {
					res.append(des + " ");
				} else {
					return null;
				}

			}

			dd = res.toString().trim();

			return dd;

		}

		// -----------------------

		Object res = null;

		Pattern pattern = Pattern.compile("^[OSIBDT][_].*$");
		Matcher matcher = pattern.matcher(mensaje.toString());

		if (matcher.find()) {

			String data = null;

			try {
				tipo = mensaje.toString().substring(0, 1);
				data = mensaje.toString().substring(2).replace('×', ' ');

			} catch (Exception e) {
				SimpleLogger.setError("Error al decifrar." + mensaje, e);
				data = null;
			}

			if (data == null) {
				return null;
			}

			// -----------------

			if ("O".equalsIgnoreCase(tipo)) {
				res = "NO-SUPPORTED";
			}

			if ("S".equalsIgnoreCase(tipo)) {
				res = data;
			}

			if ("I".equalsIgnoreCase(tipo)) {
				res = new Long(quitarCerosIzquierda(data));
			}

			if ("B".equalsIgnoreCase(tipo)) {
				res = Boolean.valueOf(data);
			}

			if ("D".equalsIgnoreCase(tipo)) {
				res = new BigDecimal(quitarCerosIzquierda(data));
			}

			if ("T".equalsIgnoreCase(tipo)) {
				try {
					res = formatofecha.parse(data);
				} catch (Throwable e) {
					SimpleLogger.setError("Decifrado..", e);
				}
			}
		}

		return res;
	}

	// ----------

	public static Object DF0(Object mensaje) {

		if (mensaje == null) {
			return null;
		}

		String m = mensaje.toString().trim();

		if (m == null || m.length() == 0) {
			return m;
		}

		if (m.charAt(0) == '[' && m.indexOf("]") > 0) {
			return DF0(m.substring(m.indexOf("]") + 1, m.length()).trim());
		}

		if (m.indexOf(" ") > 0) {
			StringBuffer res = new StringBuffer("");
			String m_array[] = m.split(" ");
			for (String d : m_array) {
				Object des = DF0(d);

				if (des != null) {
					res.append(StringUtils.toString(des) + " ");
				}

			}
			return res.toString().trim();
		}

		// -----------------------------

		Object enc = mensaje;
		if (enc != null) {
			enc = enc.toString().trim();
		}

		Object valor = D0(enc);

		if (valor == null) {
			valor = mensaje;
		}
		return valor;
	}

	public static Object DF4(Object mensaje) {

		if (mensaje == null) {
			return null;
		}

		String m = mensaje.toString().trim();

		if (m == null || m.length() == 0) {
			return m;
		}

		if (m.charAt(0) == '[' && m.indexOf("]") > 0) {
			return DF4(m.substring(m.indexOf("]") + 1, m.length()).trim());
		}

		if (m.indexOf(" ") > 0) {
			StringBuffer res = new StringBuffer("");
			String m_array[] = m.split(" ");
			for (String d : m_array) {
				Object des = DF4(d);

				if (des != null) {
					res.append(StringUtils.toString(des) + " ");
				}

			}
			return res.toString().trim();
		}

		// -----------------------------

		Object enc = mensaje;
		if (enc != null) {
			enc = enc.toString().trim();
		}

		Object valor = D4(enc);

		if (valor == null) {
			valor = mensaje;
		}
		return valor;
	}

	// ----------

	public static Object DF3(Object mensaje) {

		if (mensaje == null) {
			return null;
		}

		String m = mensaje.toString().trim();

		if (m == null || m.length() == 0) {
			return m;
		}

		if (m.charAt(0) == '[' && m.indexOf("]") > 0) {
			return DF3(m.substring(m.indexOf("]") + 1, m.length()).trim());
		}

		if (m.indexOf(" ") > 0) {
			StringBuffer res = new StringBuffer("");
			String m_array[] = m.split(" ");
			for (String d : m_array) {
				Object des = DF3(d);

				if (des != null) {
					res.append(StringUtils.toString(des) + " ");
				}

			}
			return res.toString().trim();
		}

		// -----------------------------

		Object enc = mensaje;
		if (enc != null) {
			enc = enc.toString().trim();
		}

		Object valor = D3(enc);

		if (valor == null) {
			valor = mensaje;
		}
		return valor;
	}

	// ----------

	public static Object migrarDato(Object old) {

		if (old == null) {
			return null;
		}

		Object d = null;

		// --------------------------------------

		if (tipoEncripcion == 1) {
			if (old instanceof String) {
				d = D2(old);
				if (d == null) {
					d = D3(old);
				}

				if (d != null) {
					d = E1(d);
				}
			}

			if (d == null) {
				d = old;
			}
		}

		// --------------------------------------

		if (tipoEncripcion == 2) {
			if (old instanceof String) {
				d = D1(old);
				if (d == null) {
					d = D3(old);
				}
				if (d != null) {
					d = E2(d);
				}
			}
			if (d == null) {
				d = old;
			}
		}

		// --------------------------------------

		if (tipoEncripcion == 3) {
			if (old instanceof String) {
				d = D1(old);
				if (d == null) {
					d = D2(old);
				}
				if (d != null) {
					d = E3(d);
				}
			}
			if (d == null) {
				d = old;
			}
		}

		// --------------------------------------

		if (tipoEncripcion == 4) {
			if (old instanceof String) {
				d = D1(old);
				if (d == null) {
					d = D2(old);
				}
				if (d == null) {
					d = D3(old);
				}

				if (d != null) {
					d = E4(d);
				}
			}
			if (d == null) {
				d = old;
			}
		}

		// --------------------------------------

		return d;
	}

	// --------------------------------------

	/**
	 * Encripta de la misma forma que E3 pero con mayor precision (10 decimales) para los Float, Double o BigDecimal El mensaje se desencripta con el D3
	 * 
	 * @param mensaje
	 *            mensaje a encriptar
	 * @return mensaje encriptado
	 */
	public static String E4(Object mensaje) {

		String tipo = "O";

		if (mensaje == null) {
			return null;
		}

		String msg = (mensaje) + "";

		String ee = encriptados.get(mensaje);
		if (ee != null) {
			return ee;
		}

		if (mensaje instanceof String) {
			msg = (String) mensaje;
			tipo = "S";
		}

		if (mensaje instanceof Float || mensaje instanceof Double || mensaje instanceof BigDecimal) {

			NumberFormat formatter = new DecimalFormat("#0.0000000000");

			String data = formatter.format(mensaje).replace(",", ".");

			String[] p = data.split("[.]");
			msg = StringUtils.izquierda(p[0], 20, '0') + "." + p[1];
			tipo = "D";
		}

		if (mensaje instanceof Boolean) {
			msg = ((Boolean) mensaje) ? "true" : "false";
			tipo = "B";
		}

		if (mensaje instanceof Date) {
			msg = formatofecha.format(mensaje);
			tipo = "T";
		}

		if (mensaje instanceof Integer || mensaje instanceof Long) {
			msg = StringUtils.izquierda(msg, 20, '0');
			tipo = "I";
		}

		// --------------------

		BigInteger bi = new BigInteger("1" + StringUtils.simpleCripto(msg));
		String p = bi.toString(25).toLowerCase();

		p = traslate(p, CRYPO_ORI, CRYPO_DES);

		String md = StringUtils.MD5(RSA.encripta(p, RSA.clave32, 0));
		String ret = tipo + p + md;

		// ConcurrentHashMap no permite null
		if (ret != null) {
			encriptados.put(mensaje, ret);
		}

		return ret;
	}

	// --------------------------------------

	public static String E0(Object mensaje) {

		String tipo = "O";

		if (mensaje == null) {
			return null;
		}

		String msg = (mensaje) + "";

		if (mensaje instanceof String) {
			msg = (String) mensaje;
			tipo = "S";
		}

		if (mensaje instanceof Float || mensaje instanceof Double || mensaje instanceof BigDecimal) {

			NumberFormat formatter = new DecimalFormat("#0.0000000000");

			String data = formatter.format(mensaje).replace(",", ".");

			String[] p = data.split("[.]");
			msg = StringUtils.izquierda(p[0], 20, '0') + "." + p[1];
			tipo = "D";
		}

		if (mensaje instanceof Boolean) {
			msg = ((Boolean) mensaje) ? "true" : "false";
			tipo = "B";
		}

		if (mensaje instanceof Date) {
			msg = formatofecha.format(mensaje);
			tipo = "T";
		}

		if (mensaje instanceof Integer || mensaje instanceof Long) {
			msg = StringUtils.izquierda(msg, 20, '0');
			tipo = "I";
		}

		// --------------------

		String ret = tipo + "_" + msg.replace(' ', '×');

		return ret;
	}

	// --------------------------------------
	// --------------------------------------

	public static String E(Object mensaje) {
		if (tipoEncripcion == 0) {
			return E0(mensaje);
		}
		if (tipoEncripcion == 1) {
			return E1(mensaje);
		}
		if (tipoEncripcion == 2) {
			return E2(mensaje);
		}
		if (tipoEncripcion == 3) {
			return E3(mensaje);
		}
		if (tipoEncripcion == 4) {
			return E4(mensaje);
		}

		return null;
	}

	public synchronized static Object D(Object mensaje) {
		if (tipoEncripcion == 0) {
			return D0(mensaje);
		}
		if (tipoEncripcion == 1) {
			return D1(mensaje);
		}
		if (tipoEncripcion == 2) {
			return D2(mensaje);
		}
		if (tipoEncripcion == 3) {
			return D3(mensaje);
		}
		if (tipoEncripcion == 4) {
			return D4(mensaje);
		}

		return null;
	}

	public static Object DF(Object mensaje) {

		Object ret = null;
		
		if(mensaje != null && (mensaje.toString().toUpperCase().equals(Crypto.E("").toString().toUpperCase()))){
			return "";
		}

		if (tipoEncripcion == 0) {
			ret = DF0(mensaje);
		}
		if (tipoEncripcion == 1) {
			ret = DF1(mensaje);
		}
		if (tipoEncripcion == 2) {
			ret = DF2(mensaje);
		}
		if (tipoEncripcion == 3) {
			ret = DF3(mensaje);
		}
		if (tipoEncripcion == 4) {
			ret = DF4(mensaje);
		}

		if (mensaje != null && (ret == null || ret.toString().length() == 0) ) {
			ret = mensaje;
		}
		
		return ret;
	}
}



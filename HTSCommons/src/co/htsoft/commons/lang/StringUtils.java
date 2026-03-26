package co.htsoft.commons.lang;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;

import co.htsoft.commons.file.CharsetDF;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	private static SecureRandom random = new SecureRandom();

	public static String randomString() {
		return randomString(null);
	}

	public static String randomString(Integer size) {
		return randomString(size, null);
	}

	public static String randomString(Integer size, String alfa) {

		if (size == null) {
			size = 32;
		}

		if (alfa == null) {
			alfa = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
		}

		StringBuffer salida = new StringBuffer("");

		for (int i = 0; i < size; i++) {
			int p = (int) (random.nextDouble() * alfa.length());
			salida.append(alfa.charAt(p));
		}

		return salida.toString();

	}

	public static String encodeURIComponent(String component) {

		if (component == null) {
			return "";
		}

		String result = null;

		try {
			result = URLEncoder.encode(component, "UTF-8").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\+", "%20").replaceAll("\\%27", "'").replaceAll("\\%21", "!").replaceAll("\\%7E", "~");
		} catch (UnsupportedEncodingException e) {
			result = component;
		}

		return result;
	}

	public static String decodeURIComponent(String component) {
		if (component == null) {
			return "";
		}

		String result = null;

		try {
			result = URLDecoder.decode(component, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			result = component;
		}

		return result;
	}

	public static String toUpperCase(String str) {

		if (str == null) {
			return null;
		}

		return str.toUpperCase();

	}

	public static String toLowerCase(String str) {

		if (str == null) {
			return null;
		}

		return str.toLowerCase();

	}

	public static String toCapitalize(String str) {

		str = trimToNull(str);

		if (str == null) {
			return null;
		}

		String[] a = str.split(" ");

		String res = "";
		for (int i = 0; i < a.length; i++) {

			String c = StringUtils.trimToNull(a[i]);

			if (c != null) {
				if (i > 0) {
					res = res + " ";
				}
				res = res + c.substring(0, 1).toUpperCase() + c.substring(1);
			}
		}

		return res;

	}

	public static String nvl(String... a) {

		for (int i = 0; a != null && i < a.length; i++) {

			if (a[i] != null) {
				return a[i];
			}
		}

		return null;
	}

	public static String nvl2(String... a) {

		for (int i = 0; a != null && i < a.length; i++) {
			String v = StringUtils.trimToNull(a[i]);
			if (v != null) {
				return v;
			}
		}

		return null;
	}

	public static String toLowerCapitalize(String str) {

		if (str == null) {
			return null;
		}

		return toCapitalize(str.toLowerCase());

	}

	public static String only(String txt, String posibles) {

		if (txt == null) {
			return null;
		}

		StringBuffer res = new StringBuffer("");

		if (txt != null && txt.length() > 0) {

			for (int i = 0; i < txt.length(); i++) {

				String c = "" + txt.charAt(i);

				if (posibles.contains(c)) {
					res = res.append(c);
				}

			}

		}

		return trimToNull(res.toString());

	}

	public static String onlyNumbers(String number) {
		return only(number, "1234567890");
	}

	public static String[] split(String str, Integer... size) {

		if (size == null || str == null) {
			return null;
		}

		String[] ret = new String[size.length];

		int av = 0;
		for (int i = 0; i < ret.length; i++) {
			try {

				ret[i] = null;

				if (av <= str.length()) {

					if (av + size[i] <= str.length()) {
						ret[i] = str.substring(av, av + size[i]);
					} else {
						ret[i] = str.substring(av);
					}
				}

			} catch (Exception e) {
			}

			av = av + size[i];
		}

		return ret;
	}

	public static String setRight(Object txt, int size) {

		return setRight(txt, size, ' ');
	}

	public static String setRight(Object obj, int size, char caracter) {

		String txt = (obj == null) ? "" : obj.toString();

		return txt.trim() + repeat(caracter, size - txt.trim().length());
	}

	public static String setLeft(Object obj, int cantidad) {

		return setLeft(obj, cantidad, ' ');
	}

	public static String setLeft(Object obj, int size, char caracter) {

		String txt = (obj == null) ? "" : obj.toString();

		if (txt.length() >= size) {
			return txt;
		}

		return repeat(caracter, size - txt.trim().length()) + txt.trim();
	}

	public static Boolean isEmpty(Object... string) {

		if (string == null || string.length == 0) {
			return true;
		}

		Boolean ret = true;
		for (Object obj : string) {
			ret = ret && (obj == null || obj.toString().trim().length() == 0);
		}

		return ret;
	}

	public static Boolean isAnyEmpty(Object... string) {

		if (string == null || string.length == 0) {
			return true;
		}

		for (Object obj : string) {
			if (obj == null || obj.toString().trim().length() == 0) {
				return true;
			}
		}

		return false;
	}

	public static Boolean isTrue(String string) {

		string = trimToNull(string);

		if (string == null) {
			return false;
		}

		string = string.toLowerCase();

		return (string.equals("si") || string.equals("yes") || string.equals("true") || string.equals("1") || string.equals("y") || string.equals("s") || string.equals("t"));
	}

	public static Boolean equalsAny(String str, String... strs) {

		if (strs == null) {
			return false;
		}

		for (String s : strs) {

			if (equals(str, s)) {
				return true;
			}

		}

		return false;
	}

	public static String toString(InputStream in) throws IOException {
		byte[] bytes = IOUtils.toByteArray(in);
		String out = new String(bytes, CharsetDF.getCharset(bytes));
		return out;
	}

	public static String sjoin(String strs[], String separator) {

		if (strs == null) {
			return null;
		}

		if (separator == null) {
			separator = "";
		}

		StringBuffer r = null;

		for (String s : strs) {

			if (s != null) {
				if (r == null) {
					r = new StringBuffer(s);
				} else {
					r.append(separator);
					r.append(s);
				}
			}
		}

		return r == null ? null : r.toString();
	}

	public static String replaceIgnoreCase(String str, String old_text, String new_text) {

		if (str == null || old_text == null || new_text == null) {
			return str;
		}

		int last = -1;
		int index = 0;

		while ((index = str.toLowerCase().indexOf(old_text.toLowerCase(), last + 1)) >= 0) {

			last = index;

			String pre = str.substring(0, index);
			String pos = str.substring(index + old_text.length());

			str = pre + new_text + pos;
		}

		return str;
	}

	public static String remove32(String str) {

		if (str == null) {
			return null;
		}

		String ret = "";

		char a[] = str.toCharArray();

		for (int i = 0; i < a.length; i++) {
			if (a[i] >= 32) {
				ret += a[i];
			}
		}

		return ret;

	}

	public static String trimToNullLowerCase(String str) {

		String ret = trimToNull(str);

		if (ret != null) {
			ret = ret.toLowerCase();
		}

		return ret;
	}

	public static String trimToNullUpperCase(String str) {

		String ret = trimToNull(str);

		if (ret != null) {
			ret = ret.toUpperCase();
		}

		return ret;
	}

	public static String HMAC_MD5(String msg, String keyString) {
		return HMACDigest(msg, keyString, "HmacMD5");
	}

	public static String HMAC_SHA1(String msg, String keyString) {
		return HMACDigest(msg, keyString, "HmacSHA1");
	}

	public static String HMAC_SHA256(String msg, String keyString) {
		return HMACDigest(msg, keyString, "HmacSHA256");
	}

	public static String HMACDigest(String msg, String keyString, String algo) {
		String digest = null;
		try {
			SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
			Mac mac = Mac.getInstance(algo);
			mac.init(key);

			byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

			StringBuffer hash = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(0xFF & bytes[i]);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			digest = hash.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return digest;
	}

	public static String MD5(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(source.getBytes());
			return new String(Hex.encodeHex(bytes));
		} catch (Exception e) {
			return null;
		}
	}

	public static String SHA(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] bytes = md.digest(source.getBytes());
			return new String(Hex.encodeHex(bytes));
		} catch (Exception e) {
			return null;
		}
	}

	public static String SHA256(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bytes = md.digest(source.getBytes());
			return new String(Hex.encodeHex(bytes));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String SHA512(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = md.digest(source.getBytes());
			return new String(Hex.encodeHex(bytes));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Retorna el texto remplazando los carecteres por * excepto los ultimos n numeros
	 * 
	 * @param str
	 * @param n
	 * @return
	 */
	public static String setAsterisk(String str, Integer n) {

		if (str == null) {
			return null;
		}

		StringBuffer resp = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {

			if (i < str.length() - n) {
				resp.append("*");
			} else {
				resp.append(str.charAt(i));
			}

		}

		return resp.toString();

	}

	public static int calcularDigitoVerificacion(String nit1) {

		if (!StringUtils.isNumeric(nit1)) {
			throw new RuntimeException("No se puede calcular el digito de verificaci\u00F3n.");
		}

		int dv1;

		Integer vpri[] = new Integer[16];
		int x = 0;
		int y = 0;
		int z = nit1.length();
		vpri[1] = 3;
		vpri[2] = 7;
		vpri[3] = 13;
		vpri[4] = 17;
		vpri[5] = 19;
		vpri[6] = 23;
		vpri[7] = 29;
		vpri[8] = 37;
		vpri[9] = 41;
		vpri[10] = 43;
		vpri[11] = 47;
		vpri[12] = 53;
		vpri[13] = 59;
		vpri[14] = 67;
		vpri[15] = 71;

		for (int i = 0; i < z; i++) {
			y = Integer.parseInt(nit1.substring(i, i + 1));
			x += (y * vpri[z - i]);
		}
		y = x % 11;

		if (y > 1) {
			dv1 = 11 - y;
		} else {
			dv1 = y;
		}

		return dv1;
	}

	public static String coverString(String toCover, int visibleChars) {
		String xs = "xxxxxx";
		try {
			int length = toCover.length();
			xs += toCover.substring(length - visibleChars, length);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return xs;
	}
}

package co.htsoft.commons.rsa;

import co.htsoft.commons.lang.StringUtils;
import co.htsoft.commons.rsa.CoreRSA;

public class SecureJsonUtils {

	public static String ds(String txt) {

		txt = StringUtils.trimToNull(txt);

		if (txt == null) {
			return null;
		}

		try {
			String ret = CoreRSA.convertHexToString(CoreRSA.decifrar(txt, CoreRSA.clave));

			if (ret != null) {
				return ret;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new RuntimeException("valor inv\u00E1lido " + txt);

	}

	public static Integer di(String txt) {

		try {

			return Integer.parseInt(ds(txt));

		} catch (Exception e) {
			throw new RuntimeException("valor inv\u00E1lido " + txt);
		}

	}

}

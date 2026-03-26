package co.htsoft.commons.name;

import co.htsoft.commons.lang.StringUtils;
import co.htsoft.commons.name.split.Nombres;

public class NameUtils {

	public static String[] splitName(String fullName) {
		try {
			return Nombres.separarnombre(fullName);
		} catch (Exception e) {

			System.out.println("Error: " + fullName);

			e.printStackTrace();
			return splitName3(fullName);
		}
	}

	public static String[] splitName2(String fullName) {
		String n[] = splitName(fullName);

		String nombre = StringUtils.trimToEmpty(StringUtils.trimToEmpty(n[0]) + " " + StringUtils.trimToEmpty(n[1]));
		String apellidos = StringUtils.trimToEmpty(StringUtils.trimToEmpty(n[2]) + " " + StringUtils.trimToEmpty(n[3]));

		String b[] = { nombre, apellidos };

		return b;

	}

	public static String shortName(String fullName) {

		try {
			String a[] = Nombres.separarnombre(fullName);

			String ret = a[0] + " ";
			if (a[1].length() > 0) {
				ret += a[1].charAt(0) + ". ";
			}

			ret += a[2];

			return StringUtils.trimToNull(ret);

		} catch (Exception e) {
			e.printStackTrace();
			return fullName;
		}
	}

	public static String[] splitName3(String nombre) {
		String[] nombres = new String[] { "", "", "", "" };
		String regex = " +";
		nombre = nombre.replaceAll(regex, " ").trim();
		String[] partes = nombre.split(" ");
		int mitad = (int) Math.round(partes.length / 2d);
		for (int i = 0; i < mitad; i++) {
			if (i == 0) {
				nombres[0] = partes[i];
			} else {
				nombres[1] += " " + partes[i];
			}
		}
		for (int i = mitad; i < partes.length; i++) {
			if (i == mitad) {
				nombres[2] = partes[i];
			} else {
				nombres[3] += " " + partes[i];
			}
		}

		for (int i = 0; i < nombres.length; i++) {
			nombres[i] = StringUtils.trimToNull(nombres[i]);
		}

		return nombres;
	}
}

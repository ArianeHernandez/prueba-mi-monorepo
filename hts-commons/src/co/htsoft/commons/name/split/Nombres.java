package co.htsoft.commons.name.split;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import co.htsoft.commons.lang.StringUtils;

public class Nombres {

	private static List<String> idempresas = null;
	private static List<String> patronesConocidos = null;
	private static Map<String, String> mapapatrones = new HashMap<String, String>();
	private static List<String> nombresConocidos = null;
	private static List<String> apellidosConocidos = null;
	private static List<String> conectoresConocidos = null;

	static {

		idempresas = getStringList("empresas.txt");
		patronesConocidos = getStringList("patrones.txt");
		nombresConocidos = getStringList("nombres.txt");
		apellidosConocidos = getStringList("apellidos.txt");
		conectoresConocidos = getStringList("conectores.txt");

		String patronarr[];

		for (Iterator<String> iterator = patronesConocidos.iterator(); iterator.hasNext(); mapapatrones.put(patronarr[0], patronarr[1])) {
			String patron;
			for (patron = (String) iterator.next(); patron.indexOf("  ") >= 0; patron = patron.replace("  ", " "))
				;
			patron = patron.trim();
			patronarr = patron.split(" ");
		}

	}

	private static String sinTildes(String str) {

		str = StringUtils.trimToEmpty(str);

		str = str.replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
		str = str.replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");

		return str;
	}

	/**
	 * Obtiene el contenido de un archivo retornando una lista de Strings
	 * 
	 * @param pathFile
	 * @return
	 */
	private static List<String> getStringList(String pathFile) {
		List<String> ret = new ArrayList<String>();

		try {
			InputStream in = Nombres.class.getResourceAsStream(pathFile);

			StringBuffer out = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			String filedata = out.toString();

			String[] dataarray = filedata.split("\n");

			for (String line : dataarray) {
				ret.add(line.trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static String[] separarnombre(String nombre) {

		nombre = StringUtils.trimToEmpty(nombre);

		String nuevonombre;
		for (nuevonombre = nombre.replace("_", " ").replace("*", "").replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace(".", "").replace("-", "").toUpperCase().trim(); nuevonombre.indexOf("  ") >= 0; nuevonombre = nuevonombre.replace("  ", " "))
			;
		String palabras[] = nuevonombre.split(" ");
		String estilo = "";
		String as[];
		int k = (as = palabras).length;
		for (int i = 0; i < k; i++) {
			String palabra = sinTildes(as[i]);
			if (idempresas.contains(palabra)) {

				String a[] = { nuevonombre, null, null, null };
				return a;
			}
		}

		k = (as = palabras).length;
		for (int j = 0; j < k; j++) {
			String palabra = sinTildes(as[j]);
			palabra = palabra.replace('\321', '?');

			if (conectoresConocidos.contains(palabra))
				estilo = (new StringBuilder(String.valueOf(estilo))).append("-").toString();
			else if (palabra.length() < 2)
				estilo = (new StringBuilder(String.valueOf(estilo))).append("?").toString();
			else if (nombresConocidos.contains(palabra) && apellidosConocidos.contains(palabra))
				estilo = (new StringBuilder(String.valueOf(estilo))).append("?").toString();
			else if (nombresConocidos.contains(palabra))
				estilo = (new StringBuilder(String.valueOf(estilo))).append("N").toString();
			else if (apellidosConocidos.contains(palabra)) {
				estilo = (new StringBuilder(String.valueOf(estilo))).append("A").toString();
			} else {
				estilo = (new StringBuilder(String.valueOf(estilo))).append("?").toString();
				System.out.println("Palabra desconocida: " + palabra);
			}
		}

		for (; estilo.indexOf("-?") >= 0 || estilo.indexOf("-N") >= 0 || estilo.indexOf("-A") >= 0; estilo = estilo.replace("-?", "??").replace("-N", "NN").replace("-A", "AA"))
			;
		for (; estilo.indexOf("?-") >= 0 || estilo.indexOf("N-") >= 0 || estilo.indexOf("A-") >= 0; estilo = estilo.replace("?-", "??").replace("N-", "NN").replace("A-", "AA"))
			;

		System.out.println(estilo);

		return aplicarPatron(estilo, nuevonombre);
	}

	private static String[] aplicarPatron(String estilo, String nuevonombre) {
		String nombre1 = "";
		String nombre2 = "";
		String apellido1 = "";
		String apellido2 = "";
		String palabras[] = nuevonombre.split(" ");
		String res = (String) mapapatrones.get(estilo);
		if (res == null) {
			nombre1 = nuevonombre;
			throw new RuntimeException("Se desconoce el patron " + estilo + " para " + nuevonombre);
		} else {
			char caracteres[] = res.toCharArray();
			for (int i = 0; i < caracteres.length; i++) {
				char letra = caracteres[i];

				String palabra = StringUtils.toLowerCapitalize(palabras[i]);

				if (conectoresConocidos.contains(palabra.toUpperCase())) {
					palabra = palabra.toLowerCase();
				}

				switch (letra) {
				case 78: // 'N'
					nombre1 = nombre1 + " " + palabra;
					break;

				case 110: // 'n'
					nombre2 = nombre2 + " " + palabra;
					break;

				case 65: // 'A'
					apellido1 = apellido1 + " " + palabra;
					break;

				case 97: // 'a'
					apellido2 = apellido2 + " " + palabra;
					break;
				}
			}

		}
		nombre1 = nombre1.trim();
		nombre2 = nombre2.trim();
		apellido1 = apellido1.trim();
		apellido2 = apellido2.trim();
		String a[] = { nombre1, nombre2, apellido1, apellido2 };

		return a;
	}
}

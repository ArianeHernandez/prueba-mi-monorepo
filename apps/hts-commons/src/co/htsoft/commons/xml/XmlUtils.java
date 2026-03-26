package co.htsoft.commons.xml;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import co.htsoft.commons.lang.StringUtils;

public class XmlUtils {

	public static Set<String> exceptionMethodsName = new HashSet<>();

	static {
		exceptionMethodsName.add("Class");
		exceptionMethodsName.add("Object");
		exceptionMethodsName.add("TypeDesc");
		exceptionMethodsName.add("Serializer");
		exceptionMethodsName.add("Deserializer");
	}

	static SimpleDateFormat sgf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static String toXml(Object o) {
		return toXml(o.getClass().getSimpleName(), o);
	}

	public static String toXml(String base, Object data) {

		if (data == null) {
			return "";
		}

		if (data instanceof String) {
			return open(base) + escapeXml(data.toString()) + close(base);
		}

		if (data instanceof Boolean) {
			return open(base) + data + close(base);
		}

		if (data instanceof Integer) {
			return open(base) + data + close(base);
		}

		if (data instanceof Long) {
			return open(base) + data + close(base);
		}

		if (data instanceof Double) {
			return open(base) + data + close(base);
		}

		if (data instanceof Date) {

			return open(base) + sgf.format(data) + close(base);
		}

		if (data instanceof Calendar) {

			return open(base) + sgf.format(((Calendar) data).getTime()) + close(base);
		}

		if (data.getClass().isArray()) {

			StringBuilder sb = new StringBuilder("");
			sb.append(open(base));

			for (Object item : (Object[]) data) {
				if (item != null) {
					sb.append(toXml(item));
				}
			}

			sb.append(close(base));

			return sb.toString();
		}

		if (data instanceof List<?>) {

			StringBuilder sb = new StringBuilder("");
			sb.append(open(base));

			for (Object item : (List<?>) data) {
				if (item != null) {
					sb.append(toXml(item));
				}
			}

			sb.append(close(base));

			return sb.toString();

		}

		if (data instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) data;

			StringBuilder sb = new StringBuilder("");
			sb.append(open(base));

			for (Object key : map.keySet()) {

				if (key instanceof String) {
					sb.append(toXml(key.toString(), map.get(key)));
				} else {
					sb.append("<Element>");
					sb.append(toXml("key", key));
					sb.append(toXml("value", map.get(key)));
					sb.append("</Element>");
				}
			}

			sb.append(close(base));

			return sb.toString();
		}

		return toXmlObject(base, data);

	}

	public static Set<String> basicClassName = new HashSet<>();

	public static String toXmlObject(String base, Object o) {

		StringBuilder sb = new StringBuilder();

		sb.append(open(base));

		Method[] methods = o.getClass().getMethods();

		if (methods != null) {

			for (Method m : methods) {

				if (m.getName().length() > 3 && m.getName().substring(0, 3).equals("get") && m.getParameterTypes().length == 0) {

					String sname = m.getName().substring(3);

					if (!exceptionMethodsName.contains(sname)) {

						String basename = m.getName().substring(3);

						if (basename.length() > 1) {
							String c1 = basename.charAt(0) + "";
							String c2 = basename.charAt(1) + "";

							if (c2.toLowerCase().equals(c2)) {
								c1 = c1.toLowerCase();
							}

							basename = c1 + c2 + basename.substring(2);

						} else {
							basename = basename.toLowerCase();
						}

						try {
							sb.append(toXml(basename, m.invoke(o, new Object[0])));
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				}

			}

		}

		sb.append(close(base));

		return sb.toString();
	}

	private static String open(String name) {
		return "<" + gname(name) + ">";
	}

	private static String close(String name) {
		return "</" + gname(name) + ">";
	}

	private static String gname(String name) {

		if (name == null)
			return "_";

		if ("01234567890".indexOf(name.charAt(0) + "") >= 0) {
			name = "N" + name;
		}
		if (StringUtils.isAlphanumeric(name)) {
			return name.trim();
		}
		return name.trim().replace("[]", "Array").replace('?', '_').replace(':', '_').replace('/', '_').replace('\"', '_').replaceAll("[ ]", "_").replaceAll("[@]", "").replaceAll("[-]", "_").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\(", "").replaceAll("\\)", "");
	}

	public static String escapeXml(String str) {

		return StringEscapeUtils.escapeXml10(StringUtils.trimToEmpty(str));
	}

	// ----------------------------

	public static String format(String xml) {

		String h = "";

		// elimina comentarios

		int i = 0;
		int j = 0;

		while ((i = xml.indexOf("<!--")) >= 0) {
			j = xml.indexOf("-->", i);
			String t1 = xml.substring(0, i);
			String t2 = xml.substring(j + 3);

			xml = t1 + t2;
		}

		// ELIMINA HEADER

		i = 0;

		while ((i = xml.indexOf("<?")) >= 0) {
			j = xml.indexOf(">", i);
			String t1 = xml.substring(0, i);
			h += xml.substring(i, j + 1) + "\n";
			String t2 = xml.substring(j + 1);

			xml = t1 + t2;
		}

		i = 0;

		while ((i = xml.indexOf("<!")) >= 0) {
			j = xml.indexOf(">", i);
			String t1 = xml.substring(0, i);
			h += xml.substring(i, j + 1) + "\n";
			String t2 = xml.substring(j + 1);

			xml = t1 + t2;
		}

		xml = h + format(xml.replace('\n', ' ').replace('\r', ' ').replace('\t', ' '), 0).trim();

		while (xml.indexOf("  ") >= 0) {
			xml = xml.replace("  ", " ");
		}

		return xml;
	}

	private static String format(String xml, Integer ident) {

		String tab = tabs(ident);

		if (xml == null) {
			return "";
		}

		xml = xml.trim();

		if (xml.length() == 0) {
			return "";
		}

		if (xml.charAt(0) != '<') {
			return xml;
		}

		// ---------------------------------------------

		int nivel = 0;

		boolean escapado = false;
		List<String> bloques = new ArrayList<String>();

		int iniciobloque = 0;
		for (int i = 0; i < xml.length(); i++) {
			String c = xml.charAt(i) + "";
			String n = (i + 1 < xml.length()) ? (xml.charAt(i + 1) + "") : "";

			if (escapado) {

				if ("'\"".indexOf(c) >= 0) {
					escapado = false;
				}

			} else {
				if ("<".equals(c) && "/".endsWith(n)) {
					nivel--;
					i = xml.indexOf(">", i);

					if (nivel == 0) {
						bloques.add(xml.substring(iniciobloque, i + 1));
						iniciobloque = i;
					}

				} else

				if ("/".equals(c) && ">".endsWith(n)) {
					nivel--;
					i++;

					if (nivel == 0) {
						bloques.add(xml.substring(iniciobloque, i + 1));
						iniciobloque = i;
					}

				} else

				if ("<".equals(c)) {

					if (nivel == 0) {
						iniciobloque = i;
					}

					nivel++;
				} else

				if ("'\"".indexOf(c) >= 0) {
					escapado = true;
				}
			}
		}

		if (bloques.size() > 1) {
			String ret = "";
			for (String d : bloques) {
				ret += format(d, ident);
			}
			return ret;
		}

		// ---------------------------------------------

		int pos_cerrado1 = xml.indexOf("/>");
		int pos_cerrado2 = xml.indexOf(">");
		int pos_cerrado3 = xml.lastIndexOf("<");

		if (pos_cerrado1 >= 0 && pos_cerrado1 < pos_cerrado2) {
			String d = xml.substring(0, pos_cerrado1 + 2);
			return tab + d + format(xml.substring(d.length()), ident);
		} else {

			String d1 = xml.substring(0, pos_cerrado2 + 1);
			String d2 = xml.substring(d1.length(), pos_cerrado3);
			String d3 = xml.substring(pos_cerrado3);
			String d4 = format(d2, ident + 1);

			return tab + d1 + d4 + ((d4.length() > 0 && d4.charAt(d4.length() - 1) == '>') ? tab : "") + d3;

		}

	}

	private static String tabs(int n) {

		StringBuffer ret = new StringBuffer("\n");
		for (int i = 0; i < n; i++) {
			ret.append("\t");
		}

		return ret.toString();
	}

}

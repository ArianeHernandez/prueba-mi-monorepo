package co.htsoft.commons.exml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import co.htsoft.commons.file.CharsetDF;
import co.htsoft.commons.file.FileUtils;

public class XMLUtils {

	public static void main(String[] args) throws Throwable {

		File fXmlFile = new File("C:/Users/itc/Desktop/Ilewrkjehres/word/document.xml");

		EXML exml = toEXML(fXmlFile);

		System.out.println(exml);

		FileUtils.writeStringToFile(new File("C:/Users/itc/Desktop/Ilewrkjehres/word/document2.xml"), exml.toString());

	}

	public static File EXMLtoFile(EXML exml) {

		try {
			File fileDest = FileUtils.newFile();

			FileUtils.writeStringToFile(fileDest, exml.toString(), "UTF-8");

			return fileDest;

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public static EXML toEXML(String xml) throws Throwable {

		File file = FileUtils.newFile();

		try {

			FileUtils.writeStringToFile(file, xml, "UTF-8");
			return toEXML(file);

		} catch (Throwable e) {
			throw e;
		} finally {
			file.delete();
		}
	}

	public static EXML toEXML(File fXmlFile) throws Throwable {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		HashMap<String, String> namespaces = new HashMap<String, String>();
		namespaces.put("xml", "xml");
		
		EXML exml = toEXML(doc.getChildNodes().item(0), namespaces);

		exml.setHeader(getHeader(fXmlFile));

		return exml;
	}

	public static EXML toEXML(Node node, Map<String, String> namespaces) {

		EXML exml = new EXML();

		NamedNodeMap a = node.getAttributes();

		if (a != null) {
			// obtiene la definicion de namespaces
			for (int j = 0; j < a.getLength(); j++) {
				Node cc = a.item(j);

				String nodeName = cc.getNodeName().trim();

				if (nodeName.toLowerCase().indexOf("xmlns:") == 0) {
					namespaces.put(nodeName.substring(6), cc.getNodeValue());
					exml.getDefNamespace().put(cc.getNodeValue(), nodeName.substring(6));
				}
			}

			// obtiene los atributos
			for (int j = 0; j < a.getLength(); j++) {
				Node cc = a.item(j);

				String nodeName = cc.getNodeName().trim();

				if (nodeName.toLowerCase().indexOf("xmlns:") < 0) {

					EXMLAttribute attr = null;

					int idx = nodeName.indexOf(":");
					if (idx > 0) {
						attr = new EXMLAttribute(namespaces.get(nodeName.substring(0, idx)), nodeName.substring(idx + 1), cc.getNodeValue());
					} else {
						attr = new EXMLAttribute(nodeName, cc.getNodeValue());
					}

					exml.getAttributes().add(attr);
				}
			}

		}

		String name = node.getNodeName();

		int idx = name.indexOf(":");
		if (idx > 0) {
			exml.setNamespace(namespaces.get(name.substring(0, idx)));
			exml.setNode(name.substring(idx + 1));
		} else {
			exml.setNode(name);
		}

		NodeList childs = node.getChildNodes();

		if (childs != null) {
			for (int k = 0; k < childs.getLength(); k++) {

				if (childs.item(k).getNodeType() == 1) {
					exml.getChilds().add(toEXML(childs.item(k), namespaces));
				}

				if (childs.item(k).getNodeType() == 3) {

					exml.setText(nvl(exml.getText()) + nvl(childs.item(k).getNodeValue()));
				}
			}
		} else {
			exml.setText(node.getNodeValue());
		}

		return exml;
	}

	// *************************************************************************

	private static  String nvl(String txt) {
		if (txt == null) {
			return "";
		}
		return txt;
	}

	private static String getHeader(File xmlFile) {
		try {
			return getHeader(FileUtils.readFileToString(xmlFile).toString());
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}

	}

	private static String getHeader(String xml) {
		String h = "";
		int i = 0;
		int j = 0;

		while ((i = xml.indexOf("<!--")) >= 0) {
			j = xml.indexOf("-->", i);
			String t1 = xml.substring(0, i);
			String t2 = xml.substring(j + 3);

			xml = t1 + t2;
		}

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

		return h;
	}

	private static String cleanXML(String xml) {

		int i = 0;
		int j = 0;

		while ((i = xml.indexOf("<!--")) >= 0) {
			j = xml.indexOf("-->", i);
			String t1 = xml.substring(0, i);
			String t2 = xml.substring(j + 3);

			xml = t1 + t2;
		}

		i = 0;

		while ((i = xml.indexOf("<?")) >= 0) {
			j = xml.indexOf(">", i);
			String t1 = xml.substring(0, i);
			String t2 = xml.substring(j + 1);

			xml = t1 + t2;
		}

		i = 0;

		while ((i = xml.indexOf("<!")) >= 0) {
			j = xml.indexOf(">", i);
			String t1 = xml.substring(0, i);
			String t2 = xml.substring(j + 1);

			xml = t1 + t2;
		}

		return StringUtils.trimToEmpty(xml.replace('\n', ' ').replace('\r', ' ').replace('\t', ' '));
	}

	public static String format(String xml) {

		xml = getHeader(xml) + format(cleanXML(xml), 0).trim();

		while (xml.indexOf("  ") >= 0) {
			xml = xml.replace("  ", " ");
		}

		return xml;
	}

	private static String format(String xml, Integer ident) {

		String tab = espacios(ident);

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

	private static String espacios(int n) {

		StringBuffer ret = new StringBuffer("\n");
		for (int i = 0; i < n; i++) {
			ret.append("\t");
		}

		return ret.toString();
	}

}

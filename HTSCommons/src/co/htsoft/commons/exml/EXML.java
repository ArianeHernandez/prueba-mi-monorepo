package co.htsoft.commons.exml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class EXML {

	public EXML() {
	}

	public EXML(String node) {
		this.node = node;
	}

	public EXML(String node, String text) {
		this.node = node;
		this.text = text;
	}

	// --------------------------------------------

	private String header;
	private Map<String, String> defNamespace; // namespace, prefix
	private String namespace; // url
	private String node;
	private String text;
	private EXML parent;
	private List<EXML> childs;
	private List<EXMLAttribute> attributes;

	// --------------------------------------------

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public EXML getParent() {
		return parent;
	}

	public void setParent(EXML parent) {
		this.parent = parent;
	}

	public Map<String, String> getFullDefNamespace() {

		Map<String, String> map = new HashMap<String, String>();

		map.putAll(getDefNamespace());

		if (parent != null) {
			map.putAll(parent.getFullDefNamespace());
		}

		return map;
	}

	public Map<String, String> getDefNamespace() {

		if (defNamespace == null) {
			defNamespace = new HashMap<String, String>();
		}

		if (namespace != null) {

			if (!defNamespace.containsKey(namespace)) {
				defNamespace.put(namespace, "");
			}
		}

		return defNamespace;
	}

	public void setDefNamespace(Map<String, String> defNamespace) {
		this.defNamespace = defNamespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getNamespace() {

		if (namespace == null) {
			namespace = "";
		}

		return namespace;
	}

	public String getNode() {
		if (node == null) {
			return "a";
		}

		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {

		if (text != null && text.length() == 0) {
			text = null;
		}

		this.text = text;
	}

	public List<EXML> getChilds() {
		if (childs == null) {
			childs = new ArrayList<EXML>();
		}

		for (EXML exml : childs) {
			exml.setParent(this);
		}

		return childs;
	}

	public void setChilds(List<EXML> childs) {
		this.childs = childs;
	}

	public List<EXMLAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new ArrayList<EXMLAttribute>();
		}

		return attributes;
	}

	public void setAttributes(List<EXMLAttribute> attributes) {
		this.attributes = attributes;
	}

	public EXMLAttribute getAttribute(String name) {
		if (attributes != null && name != null) {

			for (EXMLAttribute attribute : attributes) {
				if (StringUtils.trimToEmpty(name).equalsIgnoreCase(attribute.getName())) {
					return attribute;
				}
			}

		}

		return null;
	}

	public String getAttributeValue(String name) {
		if (attributes != null && name != null) {

			for (EXMLAttribute attribute : attributes) {
				if (StringUtils.trimToEmpty(name).equalsIgnoreCase(attribute.getName())) {
					return attribute.getValue();
				}
			}
		}

		return null;
	}
	
	public Boolean getAttributeBooleanValue(String name) {
		if (attributes != null && name != null) {

			for (EXMLAttribute attribute : attributes) {
				if (StringUtils.trimToEmpty(name).equalsIgnoreCase(attribute.getName())) {
					
					String value = StringUtils.trimToNull(attribute.getValue());

					if (value == null) {
						return null;
					}
					
					return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes") ;
				}
			}
		}

		return null;
	}

	public Integer getAttributeIntValue(String name) {
		if (attributes != null && name != null) {

			for (EXMLAttribute attribute : attributes) {
				if (StringUtils.trimToEmpty(name).equalsIgnoreCase(attribute.getName())) {

					String value = StringUtils.trimToNull(attribute.getValue());

					if (value == null) {
						return null;
					}

					try {

						if (value.equalsIgnoreCase("unbounded")) {
							return Integer.MAX_VALUE;
						}

						return Integer.parseInt(value);

					} catch (Exception e) {
						System.out.println("getAttributeIntValue : " + value + " not is integer");
						return null;
					}

				}
			}
		}

		return null;
	}

	public EXML searchChild(String nodename) {
		return searchChild(nodename, null);
	}

	public EXML searchChild(String nodename, String namespace) {
		List<EXML> typesnode = searchChilds(nodename, namespace);

		if (typesnode.size() > 0) {
			return typesnode.get(0);
		}
		return null;
	}

	public List<EXML> searchChilds(String nodename) {
		return searchChilds(nodename, null);
	}

	public List<EXML> searchChilds(String nodename, String namespace) {
		List<EXML> typesnode = new ArrayList<EXML>();

		for (EXML exmln : this.getChilds()) {

			if (exmln.getNode().equalsIgnoreCase(nodename) && (namespace == null || exmln.getNamespace().equalsIgnoreCase(namespace))) {
				typesnode.add(exmln);
			}
		}

		return typesnode;
	}

	public List<EXML> searchNodes(String xpad) {

		List<EXML> nodes = new ArrayList<EXML>();

		if (xpad == null || xpad.equals("") || xpad.equals(".")) {
			nodes.add(this);
			return nodes;
		}

		int index = xpad.indexOf("/");

		String p = xpad;
		String q = "";
		if (index >= 0) {
			p = xpad.substring(0, index);
			q = xpad.substring(index + 1);
		}

		List<EXML> mc = searchChilds(p);

		for (EXML exml : mc) {
			nodes.addAll(exml.searchNodes(q));
		}

		return nodes;

	}

	// --------------------------------------------

	private static String ABC = "abcdefghijklmnopqrstuvwxyz";

	public String toString(Map<String, String> namespaces) {

		Map<String, String> allnamespaces = new HashMap<String, String>();
		allnamespaces.putAll(namespaces);
		allnamespaces.put("xml", "xml");

		// ----------------------------------- Genera el mapa de namaspaces

		Map<String, String> defn = getDefNamespace();

		Set<String> keys = defn.keySet();

		for (String df : keys) {
			if (!namespaces.containsKey(df)) {
				int i = 0;
				String c = StringUtils.trimToNull(defn.get(df));

				while (c == null || allnamespaces.containsValue(c)) {
					c = "" + ABC.charAt(i);
					i++;
				}

				allnamespaces.put(df, c);
			}
		}

		// -----------------------------------------

		StringBuffer sb = new StringBuffer();

		if (parent == null && header != null) {
			sb.append(header);
		}

		String prefix = "";
		if (namespace != null) {
			prefix = allnamespaces.get(namespace) + ":";
		}

		sb.append("<" + prefix + getNode());

		for (String df : keys) {
			if ((!"xml".equals("df")) && !namespaces.containsKey(df)) {
				sb.append(" xmlns:" + allnamespaces.get(df) + "=\"" + df + "\"");
			}
		}

		List<EXMLAttribute> attrKeys = getAttributes();

		for (EXMLAttribute attrKey : attrKeys) {

			String val = attrKey.getValue();
			if (val == null) {
				val = "";
			}

			String prefixAttr = "";

			if (attrKey.getNamespace() != null) {
				prefixAttr = allnamespaces.get(attrKey.getNamespace()) + ":";
			}

			sb.append(" " + prefixAttr + attrKey.getName() + "=\"" + val + "\"");
		}

		sb.append(">");

		String t = getText();
		if (t != null) {
			sb.append(StringEscapeUtils.escapeXml10(t));
		}

		List<EXML> schilds = getChilds();

		for (EXML exml : schilds) {
			sb.append(exml.toString(allnamespaces));
		}

		sb.append("</" + prefix + getNode() + ">");

		return sb.toString(); // XMLUtils.format(sb.toString());
	}

	@Override
	public String toString() {
		return toString(new HashMap<String, String>());
	}

	public EXML clone() {

		EXML nexml = new EXML(this.node, this.text);

		nexml.header = this.header;

		if (this.defNamespace != null) {
			nexml.defNamespace = new HashMap<String, String>();

			Set<String> keys = this.defNamespace.keySet();

			for (String key : keys) {
				nexml.defNamespace.put(key, this.defNamespace.get(key));
			}
		}

		nexml.namespace = this.namespace;
		nexml.node = this.node;

		if (this.attributes != null) {
			for (EXMLAttribute attr : this.attributes) {
				nexml.getAttributes().add(attr.clone());
			}
		}

		if (this.childs != null) {
			for (EXML cexml : this.childs) {
				nexml.getChilds().add(cexml.clone());
			}
		}

		return nexml;
	}
}

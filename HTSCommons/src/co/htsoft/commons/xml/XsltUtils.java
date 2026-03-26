package co.htsoft.commons.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import co.htsoft.commons.file.FileUtils;

public class XsltUtils {

	public static String transform(String inputXml, File xsltFile) {
		try {
			return transform(inputXml, FileUtils.readFileToString(xsltFile));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String transform(String inputXml, String xsltFile) {

		try {

			TransformerFactory factory = TransformerFactory.newInstance();
			Source xslt = new StreamSource(new ByteArrayInputStream(xsltFile.getBytes()));
			Transformer transformer = factory.newTransformer(xslt);

			Source text = new StreamSource(new ByteArrayInputStream(inputXml.getBytes()));

			OutputStream os = new ByteArrayOutputStream();
			transformer.transform(text, new StreamResult(os));

			return os.toString();

		} catch (Throwable e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String transformObject(Object data, String xsltFile) {

		String xmldata = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n" + XmlUtils.toXml(data);

		return transform(xmldata, xsltFile);
	}

}

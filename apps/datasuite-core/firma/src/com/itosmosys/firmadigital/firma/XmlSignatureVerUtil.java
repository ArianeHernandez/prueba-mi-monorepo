package com.itosmosys.firmadigital.firma;

import java.util.Iterator;

import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.osmosyscol.commons.log.SimpleLogger;


public class XmlSignatureVerUtil {
	
	
	public static Boolean validarFirmarXML(Document doc){
		
		//---------------------------Validar Firma XML-----------------------------
		// Find Signature element.
		try {
		
			NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
			if (nl.getLength() == 0) {
			    throw new Exception("No se encontro el elemento Signature");
			}
			
			// Create a DOMValidateContext and specify a KeySelector
			// and document context.
			DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(), nl.item(0));
	
			// Unmarshal the XMLSignature.
			XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM"); 
	
			XMLSignature signature = fac.unmarshalXMLSignature(valContext);
	
			// Validate the XMLSignature.
			boolean coreValidity = signature.validate(valContext);
			
			// Check core validation status.
			if (coreValidity == false) {
			    System.err.println("Fallo la validacion de la firma, la informacion se ha modificado");
			    boolean sv = signature.getSignatureValue().validate(valContext);
			    if (sv == false) {
			        // Check the validation status of each Reference.
			        Iterator i = signature.getSignedInfo().getReferences().iterator();
			        for (int j=0; i.hasNext(); j++) {
			            boolean refValid = ((Reference) i.next()).validate(valContext);
			        }
			    }
			} else {
			    return true;
			}
			
		} catch (Throwable e) {
			SimpleLogger.setError("Error", e);
		}
		return null;	
	}
	
}

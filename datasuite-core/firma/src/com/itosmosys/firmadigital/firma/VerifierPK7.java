package com.itosmosys.firmadigital.firma;
import java.security.cert.X509Certificate;

import sun.security.pkcs.ContentInfo;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;
import sun.security.x509.AlgorithmId;

import com.osmosyscol.commons.log.SimpleLogger;

public class VerifierPK7 {
	
	public Boolean verifyData(String formXML, PKCS7 pkcs7){
		Boolean esValido = false;
				
		try {
			
			ContentInfo contentinfo = pkcs7.getContentInfo();
			AlgorithmId[] algoritms = pkcs7.getDigestAlgorithmIds();
			SignerInfo[] signers = pkcs7.getSignerInfos();
			
			CertificationChainVerifier chainVerifier = new CertificationChainVerifier();
			chainVerifier.init();
			
			X509Certificate[] certs = chainVerifier.ordenarCertificados(pkcs7.getCertificates());
			
			PKCS7 pkcs7Final = new PKCS7(algoritms, contentinfo, certs, signers);
			SignerInfo[] signerInfo = null;
			
			//Validacion para IE
			signerInfo = pkcs7Final.verify(formXML.getBytes("UnicodeLittleUnmarked"));
			
			if (signerInfo == null || signerInfo.length == 0)
			{
				//Validacion para MOzilla
				signerInfo = pkcs7.verify(formXML.getBytes());
			}
			
			
			if (signerInfo == null || signerInfo.length == 0) {
				//Si no hay informacion
				esValido = false;
				
			} else {
				
				esValido = true;
				 
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			SimpleLogger.setDebug("Error: "+e.getMessage());
			esValido = false;
		}	
			
		return esValido;
		
	}
	
	
	
}

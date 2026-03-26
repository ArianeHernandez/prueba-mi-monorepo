package com.itosmosys.firmadigital.firma;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;



public class VerifierFormXMLSigned {

	public Boolean verifyFormXMLSigned(String formXML, String formXMLSigned){
		
		byte[] signature = null;
		Boolean esValido = false;
		
		//Se verifica que la informacion y la informacion cifrada tengan valor
		if(formXMLSigned!=null && formXMLSigned.length()>0){
			try {
								
				signature = formXMLSigned.getBytes();
				
				PKCS7 pkcs7 = new PKCS7(Base64.decodeBase64(signature));
				
				CertificationChainVerifier chainVerifier = new CertificationChainVerifier();
				chainVerifier.init();
				
				//Se verifica que la cadena de certificados sea emitido por la CA
				if(chainVerifier.verify(pkcs7).getOperationResults()){
					
					SignerInfo[] signerInfo = pkcs7.getSignerInfos();
					X509Certificate cert = signerInfo[0].getCertificate(pkcs7);
					
					//Se verifica que el certificado esta en fecha valida
					Date fechaActual = HorarioServicio.getInstance().obtenerFechaActual();
					cert.checkValidity(fechaActual);
					
					//Se verifica que el certificado no este revocado
					VerifierCRL verifierCRL = new VerifierCRL();
					Boolean esCertificadoValido = verifierCRL.verifyCRL(cert, null); //TODO se debe colocar el path del archivo crl
					
					if(esCertificadoValido){
						
						//Se verifica que la firma es valida
						VerifierPK7 verifierPK7 = new VerifierPK7();
						esValido = verifierPK7.verifyData(formXML, pkcs7);
						
					}else{
						esValido = false;
					}
					
					
				}else{
					esValido = false;
				}	
				
				
			}catch (Exception e) {
				// TODO: handle exception
				SimpleLogger.setDebug("Error: "+e.getMessage());
				esValido = false;
			}	
					
		
		}else{
			//Si no hay informacion la validacion no es verdadera
			esValido = false;
			
		}
				
		return esValido;
		
	}
	
	
	public static byte[] getFileData(String filename) {
		File blobfile = new File(filename);
		if (!blobfile.exists())
			return null;
		int blobsize = ((int) blobfile.length());
		byte[] blobdata = new byte[blobsize];
		try {
			FileInputStream freader = new FileInputStream(blobfile);
			freader.read(blobdata, 0, blobsize);
			freader.close();
			return blobdata;
		} catch (IOException ioe) {
			return null;
		}
	}
	
	
	
}
	
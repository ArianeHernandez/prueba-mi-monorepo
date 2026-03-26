package com.itosmosys.firmadigital.firma;
import java.io.File;
import java.io.FileInputStream;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;

import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.servlet.InitApp;



public class VerifierDocumentSigned {

	public Boolean verifyDocumentSigned(String rutaArchivoP7Firmado, String rutaDestinoArchivoClaro){
		
		Boolean esValido = false;
		
    	try {
    		
    		File file = new File(rutaArchivoP7Firmado);
    		FileInputStream input = new FileInputStream(file);
    		byte[] p7signature = new byte[(int) file.length()];
    		input.read(p7signature);
        	
    		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			CMSSignedData signature = new CMSSignedData(p7signature);
			
			SignerInformationStore  signers = signature.getSignerInfos();
			Collection              collSigners = signers.getSigners();
			VerifierCRL verifierCRL = new VerifierCRL();
			Boolean esCertifricadoValido = true;
			
			for (Iterator iterSigners = collSigners.iterator(); iterSigners.hasNext();) {
				
				SignerInformation   signer = (SignerInformation)iterSigners.next();
				
				CertStore certificates = signature.getCertificatesAndCRLs("Collection", "BC");
				Collection  collCertificates = certificates.getCertificates(signer.getSID());
				
				for (Iterator iterCertificates = collCertificates.iterator(); iterCertificates.hasNext();) {
					
					X509Certificate cert = (X509Certificate)iterCertificates.next();
					
					//Se verifica qiue el certificadpo es firmado por la entidad CA
					CertificationChainVerifier chainVerifier = new CertificationChainVerifier();
					chainVerifier.init();
					
					if(chainVerifier.verifierCertificate(cert)){
						esCertifricadoValido = true;
					}else{
						
						esCertifricadoValido = false;
					}
						
					//luego de validar si el certificado es emitido por la CA se valida la CRL
					if(esCertifricadoValido){
						
						//Se verifica si el cerrtificaso es valido por CRL
						if(!verifierCRL.verifyCRL(cert, null)){//TODO Falta colocar el path del archivo crl
							esCertifricadoValido = false;
							
						}
					
					}
					
				}
				
			}
			
			//SI el certificado es valido se revisa que el el documento haya sido firmado correctamente
			if(esCertifricadoValido){
				esValido = PKCS7VerUtil.validatePkcs7Signature2(rutaArchivoP7Firmado, rutaDestinoArchivoClaro);
			
				
			}else{
				esValido = false;
			}
		
    	}catch (Exception e) {
    		SimpleLogger.setError("Ha ocurrido un error", e);
    		esValido = false;
		}
		
    	
    	return esValido;
		
	}
	
	public static void main(String[] args) {
		InitApp.startUp();
		String ruta = "/home/jc/Descargas/F1680-RETIRO_ACH.xls.p7z";
		String rutaArchivoPlanoFinal = ruta.split(".p7z")[0];
		
		VerifierDocumentSigned verifierDocumentSigned = new VerifierDocumentSigned();
		SimpleLogger.setDebug("" + verifierDocumentSigned.verifyDocumentSigned(ruta, rutaArchivoPlanoFinal));
		
		
	}
	
}
	
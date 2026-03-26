package com.itosmosys.firmadigital.firma;
import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Set;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;



public class VerifierCRL {

	/***
	 * 
	 * @param cert Certificado que va a buscar
	 * @param pathFileCRL Ruta del archivo CRL donde va a buscar si el certificado esta revocado
	 * @return
	 */
	public Boolean verifyCRL(X509Certificate cert, String pathFileCRL){//TODO Hacer con un CRLManager
		
		try{
			Boolean esValido = true;
			String crlfile = null;
			
			//Se carga el archivo CRL
			if(pathFileCRL==null){
				crlfile = ParametrosInicio.getProperty("firma.RutaArchivoCRL");
			}else{
				crlfile  = pathFileCRL;
			}
	
		    CertificateFactory cf = CertificateFactory.getInstance("X.509");
		    FileInputStream fis = null;
		    
		    try {
		    	
		    	//Lee el archivo de certificados revocados
		    	fis = new FileInputStream(crlfile);
		    	
			} catch (Exception e) {
				
				//Si no existe el archivo
				fis = null;
			}
		    
				
			if(cert!=null){
				
				cert.checkValidity();
	
				
				if(fis!=null){
				
					
					X509CRL crl = (X509CRL)cf.generateCRL(fis);
				    
				    //Se revisan los certificados revocados
				    Set revokedCerts = crl.getRevokedCertificates();
		
				    if (revokedCerts == null ) {
				    	esValido = true;
				    	
				    }else{
		
					    Iterator itr = revokedCerts.iterator();
			
					    int index = 0;
			
					    while (itr.hasNext()){
					    	
					    	X509CRLEntry crlEntry = (X509CRLEntry)itr.next();
			
					    	//Se compara cada certificado revocado con el certificado a verificar
					    	
					    	if(cert.getSerialNumber().equals(crlEntry.getSerialNumber())){
					    		esValido = false;
					    	}
			
					    	++index;
			
					    }
					}
					
				}else{
					//Si no no encuntra el archivo asume que es valido
					esValido = true;
				}
			}else{
				//Si el certificado es null no es valido
				esValido = false;
			}			
			
			return esValido;
		}catch (Exception e) {
			SimpleLogger.setError("ha ocurrido un error", e);
			return false;
		}
	}
	
}
	
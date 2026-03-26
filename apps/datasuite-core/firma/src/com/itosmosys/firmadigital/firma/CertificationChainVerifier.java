package com.itosmosys.firmadigital.firma;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.security.pkcs.PKCS7;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

//@see http://projectestic.uji.es/cryptoapplet/docu/wiki/doku.php/cms_signature
public class CertificationChainVerifier {

	private static final Log log = LogFactory.getLog(CertificationChainVerifier.class);
	
	KeyStore cacerts = null;
	Boolean operationResult = null;
	
	public CertificationChainVerifier init() throws Exception {
		try {
			FileInputStream is  = null; 
			
			String filename = ParametrosInicio.getProperty("firma.RutaArchivoTruststoreCA");
			String password = ParametrosInicio.getProperty("firma.PassTruststoreCA");
			
			is = new FileInputStream(filename);
			cacerts = KeyStore.getInstance(KeyStore.getDefaultType()); 
			cacerts.load(is, password.toCharArray()); 	
			
			
		}
		catch (FileNotFoundException e) {
			log.error( "ArchivoTruststoreCA no encontrado", e );
			SimpleLogger.setError("Archivo de jks no encontrado", e);
			
		} 
		catch( Exception e ) {
			log.error( String.format( "errores al leer truststore" ), e );
			SimpleLogger.setError("Error leyendo las propiedades para el archivo de ceritificados", e); 
			
		}
		
		return this;
	}
	
	

	public Boolean getOperationResults() {
		return operationResult;
	}
	
	public CertificationChainVerifier verify( PKCS7 pkcs7 ) {
		operationResult = null;
		X509Certificate[] x509Certificates = pkcs7.getCertificates();
		
		return verifyCertifies(x509Certificates); 		
		
	}
	
	public CertificationChainVerifier verifyCertifies(X509Certificate[] x509Certificates){
		
		PKIXParameters params = null;
		try {
	
			// 3. Create the parameters for the validator
	        params = new PKIXParameters(cacerts);
		} 
		catch (Exception e) {
			log.error( String.format( "errror en lectura de archivo cacerts" ), e );
		} 
			

		
		x509Certificates =ordenarCertificados(x509Certificates);
		
        // 4. Disable CRL checking
        // as we are not supplying any CRLs
        params.setRevocationEnabled(false);
        
        CertPath certPath =createCertPath(x509Certificates);

        // 5. Create the validator and validate
        // the specified certpath
        try {
            CertPathValidator certPathValidator = CertPathValidator.getInstance(CertPathValidator.getDefaultType());
        	CertPathValidatorResult result = certPathValidator.validate(certPath, params);
        	log.info("El certificado es valido");
        	SimpleLogger.setInfo("El certificado es VALIDO por CA");
        	operationResult = true;
        }
        catch( CertPathValidatorException e ) {
        	log.error("El Certificado no es valido", e);
        	SimpleLogger.setError("El certificado es INVALIDO por CA");
        	operationResult = false;
        	
        } 
        catch (InvalidAlgorithmParameterException e) {
        	log.error("algoritmo especificado invalido", e);
        	SimpleLogger.setError("El certificado es INVALIDO por CA");
        	operationResult = false;
        	
		} 
        catch (NoSuchAlgorithmException e) {
        	log.error("algoritmo especificado invalido p", e);
        	SimpleLogger.setError("El certificado es INVALIDO por CA");
        	operationResult = false;
        	
		}
        catch (Exception e) {
        	log.error("Ha ocurrido un error verificando la cadena de certificados ", e);
        	SimpleLogger.setError("El certificado es INVALIDO por CA");
        	operationResult = false;
        	
		}
		return this;
		
	}
	
	
	// The CA's certificate should be the last element in the array
	public static CertPath createCertPath(java.security.cert.Certificate[] certs) {
	    try {
	        CertificateFactory certFact = CertificateFactory.getInstance("X.509");
	        CertPath path = certFact.generateCertPath(Arrays.asList(certs));
	        return path;
	    } 
	    catch (java.security.cert.CertificateEncodingException e) {
	    	log.error(e);
	    } 
	    catch (CertificateException e) {
	    	log.error(e);
	    }
	    return null;
	}
	

	/***
	 * Esta funcion permite verificar si un certificado es valido. Para ello se debe ejecutar
	 * la funcion init() para iniciar y cargas los parametros de la entidad certificadora
	 * y luego ejecutar la funcion.
	 * 
	 * @param certificate
	 * @return true si el certificado es valido, false en caso contrario
	 */
	public Boolean  verifierCertificate(X509Certificate certificate){
		
		Boolean esCertificadoValido = false;
		
		try {
			
			//Se obtiene el common name del certificado cliente
			String CNClient=null;
			StringTokenizer stringTokenizerClient = new StringTokenizer(certificate.getIssuerDN().getName(), ",");

			while(stringTokenizerClient.hasMoreTokens()){
				String token = stringTokenizerClient.nextToken();
				
				if(token.startsWith("CN=")){
					CNClient = token.substring(3);
					
				}
				 
			}
	
			//Se obtienen los certificados de la entidad emisosa CA
			Enumeration<String> iteradoralias = cacerts.aliases();
			
			while (iteradoralias.hasMoreElements()) {
				String alias = (String) iteradoralias.nextElement();
				
				X509Certificate certCA = (X509Certificate)cacerts.getCertificate(alias);
				
				StringTokenizer stringTokenizerCA = new StringTokenizer(certCA.getSubjectDN().getName(), ",");
				while(stringTokenizerCA.hasMoreTokens()){
					String token = stringTokenizerCA.nextToken();
					
					//Se obtiene el nombre common name de la entidad certificadora y 
					//se compra con el common name del certificado cliente  
					if(token.startsWith("CN=")){
						
						String CNCA = token.substring(3);
						
						if(CNCA.equals(CNClient)){
							
							esCertificadoValido = true;
						}
						
					}
					 
				}
				
			}
			
			
		} catch (KeyStoreException e) {
			
			SimpleLogger.setError("Error", e);
		}
		
		
		return esCertificadoValido;
	}
	
	/***
	 * Esta funcion ordena los certificados para que el ultimo sea de la entidad CA
	 * @param x509Certificates
	 * @return
	 */
	
	public X509Certificate[] ordenarCertificados (X509Certificate[] x509Certificates){
		
		try {
			
			for (int i = 0; i < x509Certificates.length-1; i++) {
				
				//Se obtiene el common name del certificado cliente
				String CNCert=null;
				StringTokenizer stringTokenizerClient = new StringTokenizer(x509Certificates[i].getSubjectDN().getName(), ",");
	
				
				//Se organizan los certificados para que el utlimo sea la entidad emisora
				while(stringTokenizerClient.hasMoreTokens()){
					String token = stringTokenizerClient.nextToken();
					
					if(token.startsWith("CN=")){
						CNCert = token.substring(3);
						
					}
					 
				}
				
				//Se obtienen los certificados de la entidad emisosa CA
				Enumeration<String> iteradoralias = cacerts.aliases();
				
				while (iteradoralias.hasMoreElements()) {
					String alias = (String) iteradoralias.nextElement();
					
					X509Certificate certCA = (X509Certificate)cacerts.getCertificate(alias);
					
					StringTokenizer stringTokenizerCA = new StringTokenizer(certCA.getSubjectDN().getName(), ",");
					while(stringTokenizerCA.hasMoreTokens()){
						String token = stringTokenizerCA.nextToken();
						
						//Se obtiene el nombre common name de la entidad certificadora y 
						//se compra con el common name del certificado cliente  
						if(token.startsWith("CN=")){
							
							String CNCA = token.substring(3);
							
							if(CNCA.equals(CNCert)){
								X509Certificate temp = x509Certificates[i];
								x509Certificates[i] = x509Certificates[x509Certificates.length-1];
								x509Certificates[x509Certificates.length-1]=temp;
								
							}
							
						}
						 
					}
					
				}
				
				
			}
			
			return x509Certificates;
	
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	
}

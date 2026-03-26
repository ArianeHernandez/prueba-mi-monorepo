/**
 *  CeCILL Copyright (c) 2008, ADULLACT-Projet, Netheos
 *  Initiated by ADULLACT-Projet S.A.
 *  Developped by Netheos
 *
 *  contact@netheos.net
 *  contact@adullact-projet.coop
 *
 *  Ce logiciel est un programme informatique servant à faire circuler des 
 *  documents au travers d'un circuit de validation, où chaque acteur vise 
 *  le dossier, jusqu'à l'étape finale de signature.
 *
 *  Ce logiciel est régi par la licence CeCILL soumise au droit français et
 *  respectant les principes de diffusion des logiciels libres. Vous pouvez
 *  utiliser, modifier et/ou redistribuer ce programme sous les conditions
 *  de la licence CeCILL telle que diffusée par le CEA, le CNRS et l'INRIA 
 *  sur le site "http://www.cecill.info".
 *
 *  En contrepartie de l'accessibilité au code source et des droits de copie,
 *  de modification et de redistribution accordés par cette licence, il n'est
 *  offert aux utilisateurs qu'une garantie limitée.  Pour les mêmes raisons,
 *  seule une responsabilité restreinte pèse sur l'auteur du programme,  le
 *  titulaire des droits patrimoniaux et les concédants successifs.
 *
 *  A cet égard  l'attention de l'utilisateur est attirée sur les risques
 *  associés au chargement,  à l'utilisation,  à la modification et/ou au
 *  développement et à la reproduction du logiciel par l'utilisateur étant 
 *  donné sa spécificité de logiciel libre, qui peut le rendre complexe à 
 *  manipuler et qui le réserve donc à des développeurs et des professionnels
 *  avertis possédant  des  connaissances  informatiques approfondies.  Les
 *  utilisateurs sont donc invités à charger  et  tester  l'adéquation  du
 *  logiciel à leurs besoins dans des conditions permettant d'assurer la
 *  sécurité de leurs systèmes et ou de leurs données et, plus généralement, 
 *  à l'utiliser et l'exploiter dans les mêmes conditions de sécurité. 
 *
 *  Le fait que vous puissiez accéder à cet en-tête signifie que vous avez 
 *  pris connaissance de la licence CeCILL, et que vous en avez accepté les
 *  termes. 
 */
/*
 * PKCS7VerUtil.java
 *
 * Created on 14 août 2008, 17:33:46
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itosmosys.firmadigital.firma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;

import com.osmosyscol.commons.log.SimpleLogger;

/**
 * @author a.sarr
 */
public class PKCS7VerUtil {

    public PKCS7VerUtil() {
    }

    /**
     * Vérifie la signature contenue dans p7 signature
     * @param signature signature au format p7
     * @param digestValue haché de la donnée à vérifier
     * @return true si ok, faulse sinon.
     *  NOTE : ce code ne procède pas à une validation du certificat avant de statuer.
     */
    @SuppressWarnings("unchecked")
	public static boolean validatePkcs7Signature(byte[] p7signature, byte[] digestValue) throws SignatureException {
        try {

            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            
            CMSSignedData cmsSignature = new CMSSignedData(new CMSProcessableByteArray(digestValue), p7signature);
            CertStore certStore = cmsSignature.getCertificatesAndCRLs("Collection", "BC");
            SignerInformationStore signers = cmsSignature.getSignerInfos();
            Collection signersCollect = signers.getSigners();
            Iterator iterator = signersCollect.iterator();
            if (!iterator.hasNext()) {
                throw new SignatureException("Invalid Signature!!!");
            }
            SignerInformation signer = (SignerInformation) iterator.next();
            Collection certCollection = certStore.getCertificates(signer.getSID());
            


            /* TODO :
             coder validateCertificate(certificate);
             les certificats considérés comme valides dépendront de l'aplication,
             cette méthode est donc à developper en conformité avec l'application*/
            X509Certificate cert = (X509Certificate) certCollection.iterator().next();
            if (!validateCertificate(cert)) {
                return false;
            }
            return signer.verify(cert, "BC");
        } catch (CertificateExpiredException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (CertificateNotYetValidException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (CertStoreException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (CMSException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        }
    }

    /**
     * valide le certificat du signataire
     * @param certificate certif à valider
     * @return true si ok, false sinon
     */
    public static boolean validateCertificate(X509Certificate cert) {
        /* TODO : implémenter ceci suivant votre système de validation, ,pki, ...
         */
        return true;
    }

    /**
     * valide la signature p7
     * @param documentStream flux d'entrée des données
     * @param p7signature signature à valider.
     * NOTE : la fonction de hachage est supposée être sha1
     */
    public static boolean validatePkcs7Signature(byte[] p7signature, InputStream documentInputStream) throws SignatureException {
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("sha1");
            DigestInputStream digestStream = new DigestInputStream(documentInputStream, messageDigest);
            byte[] buffer = new byte[1024];
            while (digestStream.read(buffer)!=-1); // ce n'est pas une erreur...
            byte[] digestValue = messageDigest.digest();
            return validatePkcs7Signature(p7signature, digestValue);
        } catch (SignatureException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        }
    }



    /*
     * Renvoie le certificat du signataire
     */
    public static X509Certificate getSignerCertificate(byte[] p7signature) throws SignatureException {
        try {
            org.bouncycastle.cms.CMSSignedData signature = new org.bouncycastle.cms.CMSSignedData(p7signature);
            org.bouncycastle.cms.SignerInformation signer = (org.bouncycastle.cms.SignerInformation) signature.getSignerInfos().getSigners().iterator().next();
            java.security.cert.CertStore cs = signature.getCertificatesAndCRLs("Collection", "BC");
            java.util.Iterator iter = cs.getCertificates(signer.getSID()).iterator();
            return (java.security.cert.X509Certificate) iter.next();
        } catch (CertStoreException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        } catch (CMSException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            throw new SignatureException(ex.getMessage(), ex);
        }
    }
    
    //---------------------------------------------------------
    
    public static boolean validatePkcs7Signature2(String rutaP7, String rutaArchivoPlanoFinal) throws Exception {

    	File file = new File(rutaP7);
		FileInputStream input = new FileInputStream(file);
		byte[] p7signature = new byte[(int) file.length()];
		input.read(p7signature);
    	
    	Boolean  validoFirma = false; 
    	try {
    		
    		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			CMSSignedData signature = new CMSSignedData(p7signature);
			
			SignerInformationStore  signers = signature.getSignerInfos();
			Collection              collSigners = signers.getSigners();
			
			
			for (Iterator iterSigners = collSigners.iterator(); iterSigners.hasNext();) {
				
				SignerInformation   signer = (SignerInformation)iterSigners.next();
				
				CertStore certificates = signature.getCertificatesAndCRLs("Collection", "BC");
				Collection  collCertificates = certificates.getCertificates(signer.getSID());
				
				for (Iterator iterCertificates = collCertificates.iterator(); iterCertificates.hasNext();) {
					
					X509Certificate cert = (X509Certificate)iterCertificates.next();
					
					if (!validateCertificate(cert)) {
		                return false;
					}
					
					if (signer.verify(cert, "BC"))
				    {
				      validoFirma=true;
				    }  
				}
				
			}
			
			
			CMSProcessable sc = signature.getSignedContent();
						
			byte[] data = (byte[]) sc.getContent();
			FileOutputStream envfos;
			envfos = new FileOutputStream(rutaArchivoPlanoFinal);
			envfos.write(data);
			envfos.close();


		} catch (Throwable e) {
			
		}

    	return validoFirma;
    }
    
    
    //---------------------------------------------------------
    
    public static void main(String[] args) {
    	try{
    		//String ruta = "//home/osmosys/Descargas/SIFISIPWEBOccidente.xlsx.p7z";
    		
    		String ruta = "//home/osmosys/Descargas/Prueba.txt.p7z";
    		String rutaArchivoPlanoFinal = ruta.split(".p7z")[0];
    		
    		SimpleLogger.setDebug("" + PKCS7VerUtil.validatePkcs7Signature2(ruta, rutaArchivoPlanoFinal));
    		
        }catch (Exception e) {
        	SimpleLogger.setError("Error", e);
    	}
	}
}

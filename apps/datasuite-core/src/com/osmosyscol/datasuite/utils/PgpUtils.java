package com.osmosyscol.datasuite.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Iterator;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;

import com.osmosyscol.commons.log.SimpleLogger;



public class PgpUtils {
	
	public static File desencriptarArchivo (InputStream in, InputStream keyIn, char[] passwd, File file) throws Exception {
		
        in = PGPUtil.getDecoderStream(in);
        
        try
        {
        	
        	SimpleLogger.setDebug("PgpUtils.desencriptarArchivo ----> Iniciando desencripción");
        	
        	Security.addProvider(new BouncyCastleProvider());
            PGPObjectFactory        pgpF = new PGPObjectFactory(in);
            PGPEncryptedDataList    enc;

            Object                  o = pgpF.nextObject();
            //
            // the first object might be a PGP marker packet.
            //
            if (o instanceof PGPEncryptedDataList)
            {
                enc = (PGPEncryptedDataList)o;
            }
            else
            {
                enc = (PGPEncryptedDataList)pgpF.nextObject();
            }
            
            //
            // find the secret key
            //
            Iterator                    it = enc.getEncryptedDataObjects();
            PGPPrivateKey               sKey = null;
            PGPPublicKeyEncryptedData   pbe = null;
            PGPSecretKeyRingCollection  pgpSec = new PGPSecretKeyRingCollection(
                PGPUtil.getDecoderStream(keyIn));                                                                 
            
            while (sKey == null && it.hasNext())
            {
                pbe = (PGPPublicKeyEncryptedData)it.next();
                
                sKey = findSecretKey(pgpSec, pbe.getKeyID(), passwd);
            }
            
            if (sKey == null)
            {
                throw new IllegalArgumentException("secret key for message not found.");
            }
            
            InputStream         clear = pbe.getDataStream(sKey, "BC");
            
            PGPObjectFactory    plainFact = new PGPObjectFactory(clear);
            
            PGPCompressedData   cData = (PGPCompressedData)plainFact.nextObject();
    
            InputStream         compressedStream = new BufferedInputStream(cData.getDataStream());
            PGPObjectFactory    pgpFact = new PGPObjectFactory(compressedStream);
            
            Object              message = pgpFact.nextObject();
            
            if (message instanceof PGPLiteralData)
            {
                PGPLiteralData       ld = (PGPLiteralData)message;
                OutputStream fOut = new FileOutputStream(file);
                BufferedOutputStream bOut = new BufferedOutputStream(fOut);
                
                InputStream    unc = ld.getInputStream();
                int    ch;
                
                while ((ch = unc.read()) >= 0)
                {
                    bOut.write(ch);
                }
                
                bOut.close();

            }
            else if (message instanceof PGPOnePassSignatureList)
            {
                throw new PGPException("PgpUtils.desencriptarArchivo ----> encrypted message contains a signed message - not literal data.");
            }
            else
            {
                throw new PGPException("PgpUtils.desencriptarArchivo ----> message is not a simple encrypted file - type unknown.");
            }

            if (pbe.isIntegrityProtected())
            {
                if (!pbe.verify())
                {
                	SimpleLogger.setDebug("PgpUtils.desencriptarArchivo ----> message failed integrity check");
                }
                else
                {
                	SimpleLogger.setDebug("PgpUtils.desencriptarArchivo ----> message integrity check passed");
                }
            }
            else
            {
            	SimpleLogger.setDebug("PgpUtils.desencriptarArchivo ----> no message integrity check");
            }
            
            SimpleLogger.setDebug("PgpUtils.desencriptarArchivo ----> Desencripción exitosa");
            return file;
            
        }
        catch (PGPException e)
        {
            SimpleLogger.setError("Error", e);
        }
        catch (Exception e){
        	SimpleLogger.setError("PgpUtils.desencriptarArchivo ----> Ocurrió un error en la desencripción", e);	
        }
        
        
        
		return null;
	}
	
	private static PGPPrivateKey findSecretKey( PGPSecretKeyRingCollection pgpSec, long keyID, char[] pass) throws PGPException, NoSuchProviderException{    
		PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);
		if (pgpSecKey == null){
			return null;
		}
		return pgpSecKey.extractPrivateKey(pass, "BC");
	}

	
	
}




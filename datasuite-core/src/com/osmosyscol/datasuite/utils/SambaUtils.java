package com.osmosyscol.datasuite.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;

/**
 * Clase que contiene utilidades para el manejo de archivos y conexiones
 * utilizando samba para conectarse a un servidor que comparte archivos en
 * windows
 * 
 * @author hjdiaz
 * @since 3.4
 */

public class SambaUtils {

	private String host;
	private String path;
	private String domain;
	private String user;
	private String password;
	private String url;

	private NtlmPasswordAuthentication auth;
	private SmbFile dir;

	/**
	 * Constructor
	 * 
	 * @param host
	 *            host a conectarse
	 * @param path
	 *            ruta a conectarse
	 * @param domain
	 *            dominio
	 * @param user
	 *            usuario
	 * @param password
	 *            contraseña
	 * @see NtlmPasswordAuthentication
	 */
	public SambaUtils(String host, String path, String domain, String user, String password) {
		this.host = StringUtils.trimToEmpty(host);
		this.path = StringUtils.trimToEmpty(path);
		this.domain = StringUtils.trimToEmpty(domain);
		this.user = StringUtils.trimToEmpty(user);
		this.password = StringUtils.trimToEmpty(password);

		auth = new NtlmPasswordAuthentication(this.domain, this.user, this.password);
		url = "smb://" + this.host + this.path;
	}

	public boolean isDirectory() {
		try {
			return dir.exists() && dir.isDirectory();
		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.isDirectory", e);
			return false;
		}
	}

	public boolean canRead() {
		try {
			return dir.canRead();
		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.canRead", e);
			return false;
		}
	}

	public boolean canWrite() {
		try {
			return dir.canWrite();
		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.canWrite", e);
			return false;
		}
	}

	/**
	 * Realiza la conexión al servidor de windows
	 * 
	 * @return <code>true</code> en caso que se pueda conectar
	 *         <code>false</code> en otro caso
	 * @see SmbFile
	 */
	public Boolean connect() {
		try {
			dir = new SmbFile(url, auth);
			dir.exists();
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.connect", e);
			return false;
		}
	}

	/**
	 * Retorna una lista con todos los archivos del directorio, omitiendo los
	 * directorios
	 * 
	 * @return la lista de los archivos
	 */
	public List<SmbFile> getListOfFiles() {
		return getListOfFiles(false);
	}

	/**
	 * Retorna una lista con todos los archivos del directorio
	 * 
	 * @param includeDirs
	 *            indica si se deben incluir los directorios
	 * @return la lista de los archivos
	 */
	public List<SmbFile> getListOfFiles(Boolean includeDirs) {
		try {
			List<SmbFile> listOfFiles = new ArrayList<SmbFile>();
			for (SmbFile f : dir.listFiles()) {
				if (f.isFile() || includeDirs) {
					listOfFiles.add(f);
				}

			}
			return listOfFiles;

		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.getListOfFiles", e);
			return null;
		}
	}

	/**
	 * Retorna un objeto <code>SmbFile</code> que representa el archivo que se
	 * quiere obtener
	 * 
	 * @param fileName
	 *            nombre del archivo
	 * @return el archivo
	 * @see SmbFile
	 */
	public SmbFile getFile(String fileName) {
		try {
			return new SmbFile(url + fileName, auth);
		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.getFile", e);
			return null;
		}

	}

	/**
	 * Copia el archivo con nombre <code>fileName</code> que debe existir en el
	 * servidor, en el archivo <code>file</code>
	 * 
	 * @param fileName
	 *            nombre del archivo a copiar
	 * @param file
	 *            archivo donde se va a copiar
	 * @return <code>true</code> si puede copiar el archivo, <code>false</code>
	 *         en otro caso
	 */
	public Boolean getFile(String fileName, File file) {
		try {
			InputStream is = new SmbFile(url + fileName, auth).getInputStream();
			FileOutputStream out = new FileOutputStream(file);

			int length = 0;
			byte[] bbuf = new byte[10000];

			while ((is != null) && ((length = is.read(bbuf)) != -1)) {
				out.write(bbuf, 0, length);
			}

			is.close();
			out.flush();
			out.close();

			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.getFile", e);
			return false;
		}

	}

	/**
	 * Borra el archivo representado por el objeto <code>SmbFile</code>
	 * 
	 * @param file
	 *            el archivo a borrar
	 * @return <code>true</code> si se logró borrar el archivo,
	 *         <code>false</code> en otro caso
	 */
	public Boolean deleteFile(SmbFile file) {
		try {
			file.delete();
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.deleteFile", e);
			return false;
		}
	}

	/**
	 * Borra el archivo con nombre <code>fileName</code>
	 * 
	 * @param fileName
	 *            nombre del archivo a borrar
	 * @return <code>true</code> si se logró borrar el archivo,
	 *         <code>false</code> en otro caso
	 */
	public Boolean deleteFile(String fileName) {
		try {
			SmbFile file = getFile(fileName);
			file.delete();
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.deleteFile", e);
			return false;
		}
	}

	/**
	 * Guarda el archivo representado por el objeto <code>file</code> en el
	 * servidor
	 * 
	 * @param file
	 *            archivo que se va a guardar
	 * @param fileName
	 *            nombre que tendrá el archivo en el servidor
	 * @return <code>true</code> si se logró copiar el archivo,
	 *         <code>false</code> en otro caso
	 */
	public Boolean setFile(File file, String fileName) {
		try {
			FileInputStream is = new FileInputStream(file);
			OutputStream out = new SmbFile(url + fileName, auth).getOutputStream();

			int length = 0;
			Integer tamano = new Long(file.length()).intValue();
			if (tamano > 0) {
				byte[] bbuf = new byte[tamano];

				while ((is != null) && ((length = is.read(bbuf)) != -1)) {
					out.write(bbuf, 0, length);
				}
			}

			is.close();
			out.flush();
			out.close();

			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaUtils.setFile", e);
			return false;
		}
	}

	public Boolean validateConnection() {
		Boolean valido = this.connect();
		valido = valido && this.canRead();
		valido = valido && this.canWrite();
		return valido;
	}

}

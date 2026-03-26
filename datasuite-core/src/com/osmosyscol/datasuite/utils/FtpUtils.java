package com.osmosyscol.datasuite.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;

public class FtpUtils {

	private static int intentos = 10;

	// ---------------------------------------------------------------------

	private String url;
	private int port;
	private String user;
	private String pass;

	private FTPClient client = new FTPClient();

	// ---------------------------------------------------------------------

	public FtpUtils(String url, int port, String user, String pass) {

		this.url = url;
		this.port = port;
		this.user = user;
		this.pass = pass;
	}

	// ---------------------------------------------------------------------

	public boolean connect() {

		for (int i = 0; i < intentos; i++) {

			try {

				if (!client.isConnected() || !client.isAvailable()) {

					System.out.println("Conectandose.. intento " + i);

					client.connect(this.url, this.port);

					client.enterLocalPassiveMode();
					System.out.println("Activando Active Mode " + client.getDataConnectionMode());

					System.out.println("Realizando Login");
					boolean conectado = client.login(this.user, this.pass);
					System.out.println("login: " + conectado);
					if (conectado) {
						return true;
					}

				} else {
					System.out.println("Ya estaba conectado");

					if (client.isConnected()) {
						return true;
					}
				}

			} catch (Exception e) {
				
				System.out.println("Error al conectarse a " + url + ":" + port + ":" + user);

				e.printStackTrace();
			}

			disconnect();

		}

		return false;
	}

	// ---------------------------------------------------------------------

	public List<FTPFile> getFtpFiles(String dir) {

		for (int i = 0; i < intentos; i++) {

			try {

				System.out.println("Obteniendo archivos de carpeta: " + dir + " , intento " + i);

				if (connect()) {

					System.out.println("Cambiando a carpeta: " + dir);

					if (client.changeWorkingDirectory(dir)) {

						FTPFile[] files = client.listFiles();

						if (files != null && files.length > 0) {

							List<FTPFile> list = new ArrayList<FTPFile>();

							for (FTPFile ftpFile : files) {
								System.out.println(ftpFile.getName() + ", " + ftpFile.getType() + ", " + ftpFile.getSize());

								if (ftpFile.getSize() > 0) {
									list.add(ftpFile);
								}
							}

							return list;
						}
					}
				}

				System.out.println("No es posible traer los archivos de la carpeta " + dir);

			} catch (Exception e) {
				e.printStackTrace();
			}

			disconnect();

		}

		return null;

	}

	// ---------------------------------------------------------------------

	public FTPFile getFtpFile(String dir, String filename) {

		System.out.println("Buscando archivo " + filename + " en el directorio " + dir);

		List<FTPFile> files = getFtpFiles(dir);

		for (FTPFile ftpFile : files) {
			if (ftpFile.isFile() && ftpFile.getName().equals(filename)) {
				System.out.println("Archivo encontrado.");
				return ftpFile;
			}
		}

		System.out.println("No se encontro el archivo " + filename + " en el directorio " + dir);

		return null;

	}

	// ---------------------------------------------------------------------

	public Boolean getFile(String carpeta, String nombre_archivo, File file) {

		FTPFile pfile = getFtpFile(carpeta, nombre_archivo);

		if (pfile != null) {

			long fileSize = pfile.getSize();

			if (fileSize == 0) {
				FileUtils.setContentFile(file.getAbsolutePath(), "");
				return true;
			}

			for (int i = 0; i < intentos; i++) {

				System.out.println("Traer archivo " + nombre_archivo + " a " + file.getAbsolutePath() + " desde " + carpeta + ", intento " + i);

				try {

					if (connect()) {

						System.out.println("Cambiando a carpeta: " + carpeta);

						if (client.changeWorkingDirectory(carpeta)) {

							FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());

							Boolean ok = false;
							try {
								ok = client.retrieveFile(nombre_archivo, fos);
							} catch (Exception e) {
								e.printStackTrace();
							}

							fos.close();

							if (ok) {
								File nf = new File(file.getAbsolutePath());
								if (nf.length() != fileSize) {
									System.out.println("warn: tamaño de archivo no coincide, llego " + nf.length() + ", se esperaba " + fileSize);
								}

								return true;

							}

						} else {
							SimpleLogger.setError("FtpUtils.getFile: No se puede cambiar a la carpeta " + carpeta);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				disconnect();
			}

		}

		return false;
	}

	// ---------------------------------------------------------------------

	public Boolean setFile(File file, String carpeta, String nombreArchivo) {

		for (int i = 0; i < intentos; i++) {

			try {

				System.out.println("Enviando archivo: " + file + " a carpeta: " + carpeta + " archivo: " + nombreArchivo + " intento " + i);

				if (connect()) {

					System.out.println("Cambiando a carpeta: " + carpeta);
					if (client.changeWorkingDirectory(carpeta)) {

						System.out.println("Enviando Archivo " + file.getAbsolutePath());

						FileInputStream input = new FileInputStream(file);
						boolean enviado = client.storeFile(nombreArchivo, input);

						if (enviado) {
							System.out.println("El archivo ha sido enviado " + nombreArchivo);
							return true;
						}

						System.out.println("No es posible enviar el archivo.");

					} else {
						SimpleLogger.setError("FtpUtils.setFile: No se puede cambiar a la carpeta " + carpeta);
					}
				}

				System.out.println("No se pudo enviar..");

			} catch (Exception e) {
				System.out.println("No se pudo enviar..");
				e.printStackTrace();
			}

			disconnect();

		}

		return false;
	}

	// ---------------------------------------------------------------------

	public boolean disconnect() {

		try {
			client.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ---------------------------------------------------------------------

	public Boolean deleteFile(String carpeta, String nombre_archivo) {

		for (int i = 0; i < intentos; i++) {
			System.out.println("Borrando archivo: " + nombre_archivo + " de carpeta: " + carpeta + ", intento " + i);

			// verifica que el archivo exista
			FTPFile pfile = getFtpFile(carpeta, nombre_archivo);

			// si no existe se considera ya borrado..
			if (pfile == null) {
				System.out.println("El archivo ya estaba borrado.");
				return true;
			}

			try {
				if (connect()) {
					if (client.changeWorkingDirectory(carpeta)) {
						boolean borrado = client.deleteFile(nombre_archivo);
						if (borrado) {
							return true;
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			disconnect();
		}

		System.out.println("No se pudo borrar..");

		return false;

	}

	// -----------------------------------------------------------------------

}
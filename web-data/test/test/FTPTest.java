package test;

import java.io.File;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;

import com.osmosyscol.datasuite.utils.FtpUtils;

public class FTPTest {

	public static void main(String[] args) throws Throwable {

		// FtpUtils client = new FtpUtils("files2.htsoft.co", 21, "testapp", "Meconio3");
		FtpUtils client = new FtpUtils("192.168.1.53", 21, "testapp", "Meconio3");

		// ------------------------------------------------

		boolean conectado = client.connect();

		System.out.println("Conectado: " + conectado);

		if (conectado) {
			
			String ruta = "/files/davivienda/origen/";

			// si esta conectado copia un archivo

			File f = new File("file.txt");
			
			System.out.println(f.getAbsolutePath());
			
			Boolean enviado = client.setFile( f, ruta, "file.txt");

			System.out.println("Enviado: " + enviado);

			if (enviado) {
				boolean recibido = client.getFile(ruta, "file.txt", new File("recibido.txt"));

				System.out.println("Recibido: " + recibido);

				
				
				List<FTPFile> archivos = client.getFtpFiles(ruta);
				
				for (FTPFile ftpFile : archivos) {
					System.out.println(ftpFile.getName());
				}
				
				
				if (recibido) {
					boolean borrado = client.deleteFile(ruta, "file.txt");
					System.out.println("Borrado: " + borrado);

				}
			}

			client.disconnect();
		}

		// ------------------------------------------------

	}

}

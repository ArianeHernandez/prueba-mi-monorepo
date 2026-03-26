package test;

import java.io.File;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.S3SignedUrl;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.S3Servicio;
import com.osmosyscol.datasuite.servlet.InitApp;

public class S3ServicioTest {

	@BeforeClass
	public static void setUpClass() {
		InitApp.startUp();
	}
	
	@Test
	public void obtener_ruta_s3 () {
		String ruta = S3Servicio.getInstance().obtenerRutaAdjuntosS3();
		MatcherAssert.assertThat(ruta, CoreMatchers.is("s3://soc-devs-mein-documents/Adjuntos"));
	}
	
	@Test
	public void obtener_url_firmada_upload () {
		String rutaS3 = "s3%3A%2F%2Fsoc-devs-mein-documents%2FAdjuntos%2F123456%2Fprueba.pdf";
		
		S3SignedUrl signedUrl = S3Servicio.getInstance().obtenerUrlFirmadaUpload(rutaS3);
		MatcherAssert.assertThat(signedUrl.getSignedUrl(), 
				CoreMatchers.startsWith("https://soc-devs-mein-documents.s3.amazonaws.com/Adjuntos/123456/"));
	}
	
	@Test
	public void descargar_archivo () {
		ArchivoAdjunto adjunto = ArchivoAdjuntoServicio.getInstance().obtenerArchivoAdjunto(133127);
		
		File fileS3 = S3Servicio.getInstance().descargarArchivo(adjunto.getRuta());
		MatcherAssert.assertThat(fileS3, CoreMatchers.notNullValue(File.class));
		
	}
	
}

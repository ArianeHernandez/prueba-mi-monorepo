package test;

import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class GenArchivoCarga {

	public static void main(String[] args) {

		TestUtils.startUp();

		Formato formato = FormatoServicio.getInstance().obtenerFormato(1680);

		CargaServicio.getInstance().crearArchivoExcel(formato, null);

		System.exit(0);

	}

}

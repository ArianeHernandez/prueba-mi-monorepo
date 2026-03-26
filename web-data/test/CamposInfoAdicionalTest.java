import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.test.TestUtils;


public class CamposInfoAdicionalTest {
	public static void main(String[] args) {
		TestUtils.startUp();
		
		Integer id_accion = 123;
		
		CampoServicio.getInstance().obtenerCamposInfoAdicionalDisponiblesPorAccion(id_accion );
		CampoServicio.getInstance().obtenerCamposInfoAdicionalPorAccion(id_accion);
		CampoServicio.getInstance().insertarAccionCampoInfoAdicional(51509, 61632);
		CampoServicio.getInstance().borrarAccionCampoInfoAdicional(51509, 61632);
		System.exit(0);
	}
}

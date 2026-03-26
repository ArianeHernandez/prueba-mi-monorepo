import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;


public class RemplazarSQL {

	
	public static void main(String[] args) {
		System.out.println(RDServicio.reemplazarNombres("select $I(20)$ from dual nada"));
	}
}

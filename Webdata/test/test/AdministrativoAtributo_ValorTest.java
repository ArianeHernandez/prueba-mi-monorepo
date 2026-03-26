package test;

import com.google.gson.Gson;
import com.osmosyscol.datasuite.logica.dto.Atributo;
import com.osmosyscol.datasuite.logica.servicios.AtributoServicio;
import com.osmosyscol.datasuite.servlet.InitApp;

public class AdministrativoAtributo_ValorTest {

	public static void main(String[] args) {

		InitApp.startUp();

		// ---------------------------------
		
//		AdministrativoServicio.getInstance()
		Atributo atributo = new Atributo();
//		atributo.setId_atributo();
		atributo.setId_usuario(45238);
		atributo.setNombre("atributo Prueba");
		
		System.out.println(AtributoServicio.getInstance().crearAtributo(atributo));
//		System.exit(0);
		
		atributo.setNombre("atributo Prueba actualizada 2");
		System.out.println(AtributoServicio.getInstance().actualizarAtributo(atributo));
//		System.exit(0);
		
		Atributo atributoAux = new Atributo();
		atributoAux = AtributoServicio.getInstance().obtenerAtributo(1001);
		System.out.println(new Gson().toJson(atributoAux));
//		System.exit(0);
		
		System.out.println(AtributoServicio.getInstance().eliminarAtributo(1001));
		System.exit(0);
		
		// ---------------------------------
		
//		ValorAtributo valorAtrib = new ValorAtributo();
//		valorAtrib.setId_administrativo(40990);
//		valorAtrib.setId_atributo(2);
//		valorAtrib.setValor("valor de pruebas");
//		
//		System.out.println(ValorAtributoServicio.getInstance().crearValorAtributo(valorAtrib));
//		
//		valorAtrib.setValor("atributo valor actualizada 3");
//		System.out.println(ValorAtributoServicio.getInstance().actualizarValorAtributo(valorAtrib));
////		System.exit(0);
//		
//		List<ValorAtributo> atributoAux = new ArrayList<ValorAtributo>();
//		atributoAux = ValorAtributoServicio.getInstance().obtenerValorAtributo(valorAtrib);
//		System.out.println(new Gson().toJson(atributoAux));
////		System.exit(0);
//		
////		System.out.println(ValorAtributoServicio.getInstance().eliminarValorAtributo(valorAtrib));
////		System.exit(0);
//		
//		System.out.println(ValorAtributoServicio.getInstance().eliminarValorAtributosAdministrativo(valorAtrib.getId_administrativo()));
//		System.exit(0);
		
		// ------------------
	}
}

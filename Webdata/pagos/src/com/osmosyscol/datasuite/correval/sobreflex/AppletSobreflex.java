package com.osmosyscol.datasuite.correval.sobreflex;

import java.util.ArrayList;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class AppletSobreflex extends JApplet {

	/**
	 * El applet recibe los siguientes parametros id Usuario = nombre cliente Texto = texto encripotado de la clave
	 * 
	 * 
	 * 
	 * */

	private String estado = "NULL";

	@Override
	public void init() {
		estado = "INICIADO";
		super.init();
		ArrayList<String> lista = new ArrayList<String>();

		try {
			String nombre = "NOMBRE: " + getParameter("Nombre") + " " + getParameter("Apellido");
			String empresa = "RAZON SOCIAL: " + getParameter("Empresa");
			String id = "NIT:" + getParameter("ID");
			String sobreflex = "CLAVE: " + RSA.desencripta(getParameter("Texto"), RSA.clave);

			lista.add(nombre);
			lista.add(empresa);
			lista.add(id);
			lista.add(sobreflex);

			PrintText printText = new PrintText();

			printText.setCoordenadasX(getParameter("CoordenadasX"));
			printText.setCoordenadasY(getParameter("CoordenadasY"));
			printText.setTamLetras(getParameter("TamLetras"));
			printText.setMargenes(getParameter("Margenes"));

			if (printText.imprimirTexto(lista)) {
				System.out.println("Exito");
				estado = "OK";

			} else {
				System.out.println("Error");
				estado = "FALLO";

			}

		} catch (Exception e) {
			estado = "ERROR";
			System.out.println("Error imprimiendo sobreflex" + e.getMessage());
		}

	}

	public int obtenerNumero(String parametro) {
		String par = getParameter(parametro);

		try {
			return Integer.parseInt(par);
		} catch (Exception e) {
			return 0;
		}

	}

	public String getEstado() {
		return estado;

	}

}

package com.osmosyscol.datasuite.correval.thread;

import com.osmosyscol.datasuite.webdata.logica.servicios.AjusteCargaServicio;

public class AjusteCargasSinValorTotal implements Runnable {

	public void run() {

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e1) {
			return;
		}

		// ----------------------------

		AjusteCargaServicio.getInstance().ajustarCargasSinFinalizar();
		
		
	}

}

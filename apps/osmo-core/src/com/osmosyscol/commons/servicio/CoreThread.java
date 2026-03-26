package com.osmosyscol.commons.servicio;

import com.osmosyscol.commons.logging.dto.InfoTransaccion;

public class CoreThread extends Thread {

	private InfoTransaccion infoTransaccion;

	public CoreThread() {
		super();
		infoTransaccion = InfoTransaccionServicio.getInstance().getInfoTransaccion();
	}

	public CoreThread(Runnable target) {
		super(target);
		infoTransaccion = InfoTransaccionServicio.getInstance().getInfoTransaccion();
	}

	@Override
	public synchronized void start() {
		super.start();
	}

	@Override
	public void run() {
		InfoTransaccionServicio.getInstance().setInfoTransaccion(infoTransaccion);
		super.run();
	}

}

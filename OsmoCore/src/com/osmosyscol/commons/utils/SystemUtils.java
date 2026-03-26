package com.osmosyscol.commons.utils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;

public class SystemUtils {

	private static Thread cron = null;

	private static long time = 0;

	public static synchronized void consumirMemoria(int bytes) {

		byte[] tm = new byte[bytes];
		for (int i = 0; i < bytes; i++) {
			tm[i] = 1;
		}
	}

	public static synchronized long memoriaMaquinaVirtual() {
		return Runtime.getRuntime().totalMemory();
	}

	public static synchronized long memoriaTotal() {
		return Runtime.getRuntime().maxMemory();
	}

	public static synchronized long memoriaUtilizada() {
		return memoriaMaquinaVirtual() - Runtime.getRuntime().freeMemory();
	}

	public static synchronized void liberarMemoria() {
		Runtime.getRuntime().gc();
	}

	public static synchronized void liberarMemoria(int minutes) {

		SystemUtils.time = minutes * 60000L;

		if (cron == null) {
			cron = new Thread(new Runnable() {

				public void run() {
					try {

						while (!AutenticacionServicio.SHUTDOWN) {

							long m = SystemUtils.memoriaUtilizada();

							SystemUtils.liberarMemoria();

							long n = SystemUtils.memoriaUtilizada();

							long d = m - n;

							String l = d + " bytes";

							if (d > 1024 && d < 1048576) {
								l = (d / 1024) + " kilobytes";
							}

							if (d >= 1048576) {
								l = (d / 1048576) + " megabytes";
							}

							SimpleLogger.setInfo("Memoria Liberada: " + l + " ( " + (m / 1048576) + " -> " + (n / 1048576) + "/" + (SystemUtils.memoriaMaquinaVirtual() / 1048576) + "/" + (SystemUtils.memoriaTotal() / 1048576) + " ) ");

							Thread.sleep(SystemUtils.time);
						}

					} catch (Throwable e) {

						SimpleLogger.setInfo("Fin de Cron de Liberacion de memoria");

						return;
					}
				}
			});

			cron.start();
		}

	}

	public static synchronized int numeroProcesadores() {
		return Runtime.getRuntime().availableProcessors();
	}

}
/*
 * IPApplet.java
 *
 * Created on Apr 15, 2010, 2:30:37 PM
 */
package com.osmosyscol.datasuite.applet.autips;

import java.applet.Applet;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Set;

/**
 * 
 * @author itosmosys
 */
public class IPApplet extends Applet {

	private String estado = "NO";

	private String ips = "";
	private String macs = "";

	// ---------------------------------------

	public String getIps() {
		return ips;
	}

	public String getMacs() {
		return macs;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	// ---------------------------------------

	@SuppressWarnings("unchecked")
	public void init() {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {

					ips = cifrar(NetworkUtils.getIPs());
					macs = cifrar(NetworkUtils.getMACs());

					estado = "OK";

				} catch (Exception e) {
					e.printStackTrace();

					estado = "ERROR";
				}
				return null;
			}
		});
	}

	public String getEstado() {
		return estado;
	}

	private String cifrar(Set<String> list) {
		StringBuffer buff = new StringBuffer();
		if (list != null) {
			for (String ip : list) {
				buff.append(ip + "\n");
			}
		}

		return RSA.encripta(buff.toString(), RSA.clave);
	}
}

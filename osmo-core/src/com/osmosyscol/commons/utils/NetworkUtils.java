package com.osmosyscol.commons.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;

public class NetworkUtils {

	public static List<String> getIPs() {

		try {

			List<String> ips = new ArrayList<String>();

			Enumeration<NetworkInterface> e1 = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = e1.nextElement();

				Enumeration<InetAddress> e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					InetAddress ia = e2.nextElement();

					ips.add(ia.getHostAddress());
				}
			}

			return ips;

		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener las IPs", e);
			return null;
		}
	}

}

package com.osmosyscol.datasuite.applet.autips;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import com.osmosyscol.commons.log.SimpleLogger;

public class NetworkUtils {

	public static Set<String> getIPs() {

		try {

			Set<String> ips = new HashSet<String>();

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
			return null;
		}
	}

	public static Set<String> getMACs() {

		try {

			Set<String> macs = new HashSet<String>();

			Enumeration<NetworkInterface> e1 = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = e1.nextElement();
				byte[] bytes = null; //ni.getHardwareAddress();
				if (bytes != null) {
					macs.add(encode(bytes));
				}
			}
			macs.remove("");
			macs.remove("00-00-00-00-00-00-00-E0");

			return macs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected static final byte[] Hexhars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String encode(byte[] b) {

		StringBuilder s = new StringBuilder(2 * b.length);

		for (int i = 0; i < b.length; i++) {

			int v = b[i] & 0xff;

			s.append((char) Hexhars[v >> 4]);
			s.append((char) Hexhars[v & 0xf]);

			if (i + 1 < b.length) {
				s.append('-');
			}
		}

		return s.toString();
	}

	public static void main(String[] args) {
		SimpleLogger.setDebug("" + getMACs());
		SimpleLogger.setDebug("" + getIPs());
	}

}

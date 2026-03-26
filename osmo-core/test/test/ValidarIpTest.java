package test;

import com.osmosyscol.commons.utils.ValidarTipoDato;

public class ValidarIpTest {
	public static void main(String[] args) {
		String ipv6 = "1200:0000:AB00:1234:0000:2552:7777:1313";
		String noIpv6 = "1200:0000:AB00:1234:O000:2552:7777:1313";
		String ipv4 = "127.0.0.1";
		String noIpv4 = "322.61.0.53";
		String noip = "32261053";
		String cadena = "Casa.casa.casa.casa";

		System.out.println(ipv6 + " - " + validarIp(ipv6));
		System.out.println(noIpv6 + " - " + validarIp(noIpv6));
		System.out.println(ipv4 + " - " + validarIp(ipv4));
		System.out.println(noIpv4 + " - " + validarIp(noIpv4));
		System.out.println(noip + " - " + validarIp(noip));
		System.out.println(cadena + " - " + validarIp(cadena));
		
	}
	
	static boolean validarIp (String ip){
		try {
			ValidarTipoDato.validateIP(ip);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

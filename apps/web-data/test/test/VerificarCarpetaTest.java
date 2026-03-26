package test;

import com.osmosyscol.datasuite.utils.SambaUtils;

public class VerificarCarpetaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SambaUtils smbUtils = new SambaUtils("localhost", "/Users/PC-80084/Pictures","", "PC-80084", "Cr7h4lam4drid");
		System.out.println(smbUtils.connect());
	}

}

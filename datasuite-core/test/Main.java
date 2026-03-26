import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.utils.StringUtils;


public class Main {

	public static void main(String[] args) {
		Crypto.setTipoEncripcion(4);
		String valor = StringUtils.MD5("prepalicorp|ABC123");	
		System.out.println(valor);
		String x = (String)Crypto.D("BgDxCJC0AOCPB2CuBGDbBJBkBOBQDwCyCjAhCBDqAGDXCpAMB2AqA4BbD1BID7AGBDDGA2CkBaBIDAAFD8B9DFCYDcDlCTC4CYBDDkArDKAbCcDBDlBwBsAMBABYBKCX");
		if (x == null) {
			System.out.println("es null");
		}
		else {
			
			System.out.println(x);
		}
	}
}

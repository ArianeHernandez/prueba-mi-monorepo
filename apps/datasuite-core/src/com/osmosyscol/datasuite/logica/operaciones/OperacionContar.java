package com.osmosyscol.datasuite.logica.operaciones;

import java.math.BigDecimal;
import java.util.List;

public class OperacionContar implements Operacion{

	public BigDecimal calcular(List<BigDecimal> valores) {
		
		BigDecimal total = new BigDecimal(valores.size());
		
		return total;
	}
	
	

}

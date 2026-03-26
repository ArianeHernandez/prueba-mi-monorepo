package com.osmosyscol.datasuite.logica.operaciones;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class OperacionPromediar implements Operacion{

	public BigDecimal calcular(List<BigDecimal> valores) {
		
		if (CollectionUtils.isEmpty(valores)) {
			return new BigDecimal(0);
		}
		
		BigDecimal total= new BigDecimal(0);
		for (BigDecimal valor: valores) {
			total = total.add(valor);
		}
		
		total = total.divide(new BigDecimal(valores.size()));
		
		return total;
	}
	
	

}

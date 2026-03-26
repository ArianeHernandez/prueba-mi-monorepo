package com.osmosyscol.datasuite.logica.operaciones;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementación de las operaciones definidas en la tabla DST_OPERACION
 * @author oaortiz
 *
 */
public interface Operacion {

	/**
	 * Debe calular el resultado de la operación
	 * @param valores
	 * @return
	 * @author oaortiz
	 */
	public BigDecimal calcular(List<BigDecimal> valores);
	
}

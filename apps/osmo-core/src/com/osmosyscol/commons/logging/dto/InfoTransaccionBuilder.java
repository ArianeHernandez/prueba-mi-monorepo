package com.osmosyscol.commons.logging.dto;

import com.osmosyscol.commons.servicio.CoreRequest;

public interface InfoTransaccionBuilder {

	public boolean crearInfoTransaccion(InfoTransaccion infoTransaccion, CoreRequest request);
	
}

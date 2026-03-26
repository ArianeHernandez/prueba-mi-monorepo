package com.osmosyscol.commons.logging.loggers;

import com.osmosyscol.commons.logging.dto.LogDto;

public interface Logger {

	public void crearLogger();

	public void guardarLog(LogDto dto);

}

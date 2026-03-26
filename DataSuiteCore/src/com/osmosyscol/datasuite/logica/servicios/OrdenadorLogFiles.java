package com.osmosyscol.datasuite.logica.servicios;

import java.util.Comparator;
import java.util.List;

import com.osmosyscol.datasuite.system.dto.LogFile;

public class OrdenadorLogFiles implements Comparator<LogFile>{
	
	@Override
	public int compare(LogFile o1,
			LogFile o2) {
		return o1.getLastModified().compareTo(o2.getLastModified());
	}
}
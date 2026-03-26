package com.osmosyscol.commons.logging.loggers.ws;

import com.osmosyscol.commons.logging.dto.LogDtoG3A;

public class WSLogProxy implements com.osmosyscol.commons.logging.loggers.ws.WSLog {
	private String _endpoint = null;
	private com.osmosyscol.commons.logging.loggers.ws.WSLog wSLog = null;

	public WSLogProxy() {
		_initWSLogProxy();
	}

	public WSLogProxy(String endpoint) {
		_endpoint = endpoint;
		_initWSLogProxy();
	}

	private void _initWSLogProxy() {
		try {
			wSLog = new com.osmosyscol.commons.logging.loggers.ws.WSLogServiceLocator().getWSLogPort();
			if (wSLog != null) {
				if (_endpoint != null) {
					((javax.xml.rpc.Stub)wSLog)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				} else {
					_endpoint = (String)((javax.xml.rpc.Stub)wSLog)._getProperty("javax.xml.rpc.service.endpoint.address");
				}
			}

		}
		catch (javax.xml.rpc.ServiceException serviceException) {}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (wSLog != null) {
			((javax.xml.rpc.Stub)wSLog)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
		}

	}

	public com.osmosyscol.commons.logging.loggers.ws.WSLog getWSLog() {
		if (wSLog == null) {
			_initWSLogProxy();
		}
		return wSLog;
	}

	public void crearLog(LogDtoG3A log) throws java.rmi.RemoteException {
		if (wSLog == null) {
			_initWSLogProxy();
		}
		wSLog.crearLog(log);
	}


}
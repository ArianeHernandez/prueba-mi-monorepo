/**
 * WSLogService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.commons.logging.loggers.ws;

public interface WSLogService extends javax.xml.rpc.Service {
    public java.lang.String getWSLogPortAddress();

    public com.osmosyscol.commons.logging.loggers.ws.WSLog getWSLogPort() throws javax.xml.rpc.ServiceException;

    public com.osmosyscol.commons.logging.loggers.ws.WSLog getWSLogPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

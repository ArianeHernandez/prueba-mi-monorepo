/**
 * WSActualizarRolSOAPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.actualizarRol;

public class WSActualizarRolSOAPSkeleton implements com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType, org.apache.axis.wsdl.Skeleton {
    private com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rolDto"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://actualizarRol.servicioweb.osmoautenticador.osmosyscol.com", "TipoRolDto"), com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoRolDto.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "servicioDto"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://actualizarRol.servicioweb.osmoautenticador.osmosyscol.com", "TipoServicioDto"), com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "naturaleza"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("crearRol", _params, new javax.xml.namespace.QName("", "out"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://actualizarRol.servicioweb.osmoautenticador.osmosyscol.com", "crearRol"));
        _oper.setSoapAction("http://actualizarRol.servicioweb.osmoautenticador.osmosyscol.com/NewOperation");
        _myOperationsList.add(_oper);
        if (_myOperations.get("crearRol") == null) {
            _myOperations.put("crearRol", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("crearRol")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "idRol"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "servicioDto"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://actualizarRol.servicioweb.osmoautenticador.osmosyscol.com", "TipoServicioDto"), com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "naturaleza"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("modificarRol", _params, new javax.xml.namespace.QName("", "out"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://actualizarRol.servicioweb.osmoautenticador.osmosyscol.com", "modificarRol"));
        _oper.setSoapAction("http://actualizarRol.servicioweb.osmoautenticador.osmosyscol.com/modificarRol");
        _myOperationsList.add(_oper);
        if (_myOperations.get("modificarRol") == null) {
            _myOperations.put("modificarRol", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("modificarRol")).add(_oper);
    }

    public WSActualizarRolSOAPSkeleton() {
        this.impl = new com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRolSOAPImpl();
    }

    public WSActualizarRolSOAPSkeleton(com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType impl) {
        this.impl = impl;
    }
    public java.lang.String[] crearRol(com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoRolDto rolDto, com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto[] servicioDto, java.lang.String naturaleza) throws java.rmi.RemoteException
    {
        java.lang.String[] ret = impl.crearRol(rolDto, servicioDto, naturaleza);
        return ret;
    }

    public java.lang.String[] modificarRol(int idRol, com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto[] servicioDto, java.lang.String naturaleza, java.lang.String accion) throws java.rmi.RemoteException
    {
        java.lang.String[] ret = impl.modificarRol(idRol, servicioDto, naturaleza, accion);
        return ret;
    }

}

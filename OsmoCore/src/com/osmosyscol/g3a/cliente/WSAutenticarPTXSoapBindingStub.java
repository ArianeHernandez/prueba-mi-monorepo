/**
 * WSAutenticarPTXSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente;

import com.osmosyscol.commons.log.SimpleLogger;

public class WSAutenticarPTXSoapBindingStub extends org.apache.axis.client.Stub implements com.osmosyscol.g3a.cliente.WSAutenticarPTX {
	private final java.util.Vector cachedSerClasses = new java.util.Vector();
	private final java.util.Vector cachedSerQNames = new java.util.Vector();
	private final java.util.Vector cachedSerFactories = new java.util.Vector();
	private final java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc [] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[7];
		_initOperationDesc1();
	}

	private static void _initOperationDesc1(){
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("autenticar");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "entrada"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "EntradaAutenticarPTX"), com.osmosyscol.g3a.cliente.EntradaAutenticarPTX.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaAutenticarPTX"));
		oper.setReturnClass(com.osmosyscol.g3a.cliente.RespuestaAutenticarPTX.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "autenticarReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("autorizar");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "entrada"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "EntradaAutorizarPTX"), com.osmosyscol.g3a.cliente.EntradaAutorizarPTX.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaAutorizarPTX"));
		oper.setReturnClass(com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "autorizarReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("cerrarSesion");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "entrada"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "EntradaAutorizarPTX"), com.osmosyscol.g3a.cliente.EntradaAutorizarPTX.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		oper.setReturnClass(boolean.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "cerrarSesionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("perderSesion");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "entrada"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "EntradaAutorizarPTX"), com.osmosyscol.g3a.cliente.EntradaAutorizarPTX.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		oper.setReturnClass(boolean.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "perderSesionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("iniciarTransaccion");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "entrada"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SolicitudTransaccion"), com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaOperacion"));
		oper.setReturnClass(com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "iniciarTransaccionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("validarPermisosTransaccion");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "solicitud"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SolicitudTransaccion"), com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaOperacion"));
		oper.setReturnClass(com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "validarPermisosTransaccionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[5] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("solicitarActivacion");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "solicitud"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SolicitudActivacion"), com.osmosyscol.g3a.cliente.entidades.SolicitudActivacion.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaOperacion"));
		oper.setReturnClass(com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "solicitarActivacionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[6] = oper;

	}

	public WSAutenticarPTXSoapBindingStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public WSAutenticarPTXSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public WSAutenticarPTXSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName = new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "EntradaAutenticarPTX");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.EntradaAutenticarPTX.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "EntradaAutorizarPTX");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.EntradaAutorizarPTX.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaAutenticarPTX");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.RespuestaAutenticarPTX.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaAutorizarPTX");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "IDUsuario");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.entidades.IDUsuario.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "Operacion");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.entidades.Operacion.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaOperacion");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "ResultadoOperacion");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.entidades.ResultadoOperacion.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SessionTicket");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.entidades.SessionTicket.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SolicitudActivacion");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.entidades.SolicitudActivacion.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SolicitudTransaccion");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "Usuario");
		cachedSerQNames.add(qName);
		cls = com.osmosyscol.g3a.cliente.entidades.Usuario.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this) {
				if (firstCall()) {
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);
					for (int i = 0; i < cachedSerFactories.size(); ++i) {
						java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
						javax.xml.namespace.QName qName =
							(javax.xml.namespace.QName) cachedSerQNames.get(i);
						java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							java.lang.Class sf = (java.lang.Class)
							cachedSerFactories.get(i);
							java.lang.Class df = (java.lang.Class)
							cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
						else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
							cachedSerFactories.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
							cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		}
		catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public com.osmosyscol.g3a.cliente.RespuestaAutenticarPTX autenticar(com.osmosyscol.g3a.cliente.EntradaAutenticarPTX entrada) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "autenticar"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
				return (com.osmosyscol.g3a.cliente.RespuestaAutenticarPTX) _resp;
			} catch (java.lang.Exception _exception) {
				return (com.osmosyscol.g3a.cliente.RespuestaAutenticarPTX) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.g3a.cliente.RespuestaAutenticarPTX.class);
			}
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX autorizar(com.osmosyscol.g3a.cliente.EntradaAutorizarPTX entrada) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "autorizar"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { entrada });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)_resp;
			}
			else {
				extractAttachments(_call);
				try {
					return (com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX.class);
				}
			}
		}catch(Throwable t){
			SimpleLogger.setError("Error al autorizar (RespuestaAutorizarPTX)", t);
			throw new java.rmi.RemoteException();
		}
	}

	public boolean cerrarSesion(com.osmosyscol.g3a.cliente.EntradaAutorizarPTX entrada) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "cerrarSesion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
				return ((java.lang.Boolean) _resp).booleanValue();
			} catch (java.lang.Exception _exception) {
				return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
			}
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public boolean perderSesion(com.osmosyscol.g3a.cliente.EntradaAutorizarPTX entrada) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "perderSesion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
				return ((java.lang.Boolean) _resp).booleanValue();
			} catch (java.lang.Exception _exception) {
				return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
			}
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion iniciarTransaccion(com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion entrada) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "iniciarTransaccion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
				return (com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion) _resp;
			} catch (java.lang.Exception _exception) {
				return (com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion.class);
			}
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion validarPermisosTransaccion(com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion solicitud) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "validarPermisosTransaccion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitud});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
				return (com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion) _resp;
			} catch (java.lang.Exception _exception) {
				return (com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion.class);
			}
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion solicitarActivacion(com.osmosyscol.g3a.cliente.entidades.SolicitudActivacion solicitud) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "solicitarActivacion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitud});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
				return (com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion) _resp;
			} catch (java.lang.Exception _exception) {
				return (com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion.class);
			}
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

}

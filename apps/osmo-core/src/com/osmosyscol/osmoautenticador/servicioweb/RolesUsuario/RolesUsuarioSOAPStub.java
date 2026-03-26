/**
 * RolesUsuarioSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public class RolesUsuarioSOAPStub extends org.apache.axis.client.Stub implements com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolesUsuario_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[14];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listarDependencias");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradalistarDependencias"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarDependencias"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarDependencias.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarDependencias"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarDependencias[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidalistarDependencias"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("autorizacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradaautorizacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaautorizacion"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaautorizacion.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaautorizacion"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaautorizacion[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidaautorizacion"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarRolesporLogin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradaeliminarRolesporLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaeliminarRolesporLogin"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRolesporLogin.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaeliminarRolesporLogin"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRolesporLogin[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidaeliminarRolesporLogin"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insertarRolesporLogin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradainsertarRolesporLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradainsertarRolesporLogin"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradainsertarRolesporLogin.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidainsertarRolesporLogin"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidainsertarRolesporLogin[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidainsertarRolesporLogin"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listarServicioRol");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradalistarServicioRol"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarServicioRol"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicioRol.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarServicioRol"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicioRol[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidalistarServicioRol"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listarRolesporLogin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradalistarRolesporLogin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarRolesporLogin"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesporLogin.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarRolesporLogin"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidalistarRolesporLogin"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listarRolesUsuario");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradalistarRolesUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarRolesUsuario"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesUsuario.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarRolesUsuario"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesUsuario[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidalistarRolesUsuario"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listarRoles");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradalistarRoles"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarRoles"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRoles.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarRoles"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRoles[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidalistarRoles"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarServiciosRol");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradaeliminarServiciosRol"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaeliminarServiciosRol"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarServiciosRol.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaeliminarServiciosRol"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarServiciosRol[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidaeliminarServiciosRol"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("adicionarServicioRol");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradaadicionarServicioRol"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaadicionarServicioRol"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarServicioRol.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaadicionarServicioRol"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarServicioRol[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidaadicionarServicioRol"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("adicionarRol");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradaadicionarRol"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaadicionarRol"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarRol.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaadicionarRol"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarRol[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidaadicionarRol"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarRol");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradaeliminarRol"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaeliminarRol"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRol.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaeliminarRol"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRol[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidaeliminarRol"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getID");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradagetID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradagetID"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradagetID.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidagetID"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidagetID[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidagetID"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listarServicios");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "entradalistarServicios"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarServicios"), com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicios.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarServicios"));
        oper.setReturnClass(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicios[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "salidalistarServicios"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

    }

    public RolesUsuarioSOAPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public RolesUsuarioSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public RolesUsuarioSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "rolArray");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolArrayElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "rolArrayElement");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "rolElemento");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "rolArrayElement");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolArrayElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "serviciosArray");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "serviciosArrayElement");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "serviciosElemento");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "serviciosArrayElement");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradaadicionarRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradaadicionarRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradaadicionarServicioRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradaadicionarServicioRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradaautorizacion");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradaautorizacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradaeliminarRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradaeliminarRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradaeliminarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradaeliminarRolesporLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradaeliminarServiciosRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradaeliminarServiciosRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradagetID");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradagetID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradainsertarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradainsertarRolesporLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradalistarDependencias");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradalistarDependencias.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradalistarRoles");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradalistarRoles.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradalistarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradalistarRolesporLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradalistarRolesUsuario");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradalistarRolesUsuario.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradalistarServicioRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradalistarServicioRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradalistarServicios");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradalistarServicios.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaadicionarRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaadicionarServicioRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarServicioRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaautorizacion");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaautorizacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaeliminarRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaeliminarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRolesporLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaeliminarServiciosRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarServiciosRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidagetID");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidagetID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidainsertarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidainsertarRolesporLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarDependencias");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarDependencias.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarRoles");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRoles.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarRolesUsuario");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesUsuario.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarServicioRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicioRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarServicios");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicios.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaadicionarRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaadicionarServicioRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarServicioRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaautorizacion");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaautorizacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaeliminarRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaeliminarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRolesporLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradaeliminarServiciosRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarServiciosRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradagetID");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradagetID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradainsertarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradainsertarRolesporLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarDependencias");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarDependencias.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarRoles");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRoles.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesporLogin.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarRolesUsuario");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesUsuario.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarServicioRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicioRol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoEntradalistarServicios");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicios.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaadicionarRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarRol[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaadicionarRol");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaadicionarServicioRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarServicioRol[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaadicionarServicioRol");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaautorizacion");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaautorizacion[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaautorizacion");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaeliminarRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRol[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaeliminarRol");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaeliminarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRolesporLogin[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaeliminarRolesporLogin");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidaeliminarServiciosRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarServiciosRol[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaeliminarServiciosRol");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidagetID");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidagetID[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidagetID");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidainsertarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidainsertarRolesporLogin[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidainsertarRolesporLogin");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarDependencias");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarDependencias[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarDependencias");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarRoles");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRoles[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarRoles");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarRolesporLogin");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarRolesporLogin");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarRolesUsuario");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesUsuario[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarRolesUsuario");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarServicioRol");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicioRol[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarServicioRol");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoSalidalistarServicios");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicios[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarServicios");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "elementoSalida");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "usuariosArray");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.UsuariosArrayElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "usuariosArrayElement");
            qName2 = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "usuariosElemento");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "usuariosArrayElement");
            cachedSerQNames.add(qName);
            cls = com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.UsuariosArrayElement.class;
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

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarDependencias[] listarDependencias(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarDependencias entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/listarDependencias");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listarDependencias"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarDependencias[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarDependencias[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarDependencias[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaautorizacion[] autorizacion(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaautorizacion entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/autorizacion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "autorizacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaautorizacion[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaautorizacion[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaautorizacion[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRolesporLogin[] eliminarRolesporLogin(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRolesporLogin entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/eliminarRolesporLogin");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "eliminarRolesporLogin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRolesporLogin[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRolesporLogin[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRolesporLogin[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidainsertarRolesporLogin[] insertarRolesporLogin(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradainsertarRolesporLogin entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/insertarRolesporLogin");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "insertarRolesporLogin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidainsertarRolesporLogin[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidainsertarRolesporLogin[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidainsertarRolesporLogin[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicioRol[] listarServicioRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicioRol entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/listarServicioRol");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listarServicioRol"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicioRol[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicioRol[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicioRol[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin[] listarRolesporLogin(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesporLogin entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/listarRolesporLogin");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listarRolesporLogin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesUsuario[] listarRolesUsuario(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesUsuario entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/listarRolesUsuario");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listarRolesUsuario"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesUsuario[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesUsuario[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesUsuario[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRoles[] listarRoles(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRoles entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/listarRoles");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listarRoles"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRoles[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRoles[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRoles[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarServiciosRol[] eliminarServiciosRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarServiciosRol entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/eliminarServiciosRol");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "eliminarServiciosRol"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarServiciosRol[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarServiciosRol[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarServiciosRol[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarServicioRol[] adicionarServicioRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarServicioRol entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/adicionarServicioRol");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "adicionarServicioRol"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarServicioRol[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarServicioRol[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarServicioRol[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarRol[] adicionarRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarRol entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/adicionarRol");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "adicionarRol"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarRol[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarRol[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarRol[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRol[] eliminarRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRol entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/eliminarRol");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "eliminarRol"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRol[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRol[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRol[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidagetID[] getID(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradagetID entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/getID");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getID"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidagetID[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidagetID[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidagetID[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicios[] listarServicios(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicios entrada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.itosmosys.com/datapi/servicios/listarServicios");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listarServicios"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entrada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicios[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicios[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicios[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}

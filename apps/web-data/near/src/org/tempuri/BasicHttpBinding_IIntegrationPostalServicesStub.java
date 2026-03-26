/**
 * BasicHttpBinding_IIntegrationPostalServicesStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class BasicHttpBinding_IIntegrationPostalServicesStub extends org.apache.axis.client.Stub implements org.tempuri.IIntegrationPostalServices {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[85];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
        _initOperationDesc7();
        _initOperationDesc8();
        _initOperationDesc9();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RadicarEntrada");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AnexosFisicos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaCiudadCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaDepartamentoCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaPaisCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaEmail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaDireccion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaNombre"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaTelefono"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaIdentificacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaTipoIdentificacionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaTipoIdentificacionNombre"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularIdentificacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularNombre"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularTipoIdentificacionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularTipoIdentificacionNombre"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularCiudadCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularDepartamentoCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularPaisCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularDireccion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularTelefono"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularEmail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "DependenciaId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "DependenciaNombre"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "EntregaFisica"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "FoliosNumero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ReferenciaExterna"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "CuadernoTipoId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "CuadernoCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "DocumentalTipoCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "TramiteId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "TramiteCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ExtensionArchivo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoMedioEnvio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoTipoSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoSubSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "LoginUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "RadicacionAnterior"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NombreSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NombreSubSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "RadicarEntradaResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RadicarSalida");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "radicacionSalida"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionSalida"), org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "RadicarSalidaResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RadicarSalidaV2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "radicacionSalida"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionSalida"), org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "RadicarSalidaV2Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_RadicarInterna");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "radicacionInterna"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionInterna"), org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaRadicacionInterna"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_RadicarInternaResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SubirArchivoDesdeProcesoBPM");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "RefBase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NombreTermino"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "TotalDocumentos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "UploadBy"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SubirArchivoDesdeProcesoBPMResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SubirArchivoDesdeProcesoBPMV2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "RefBase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NombreTermino"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SubirArchivoDesdeProcesoBPMV2Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RecuperarDocumentos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "RefBase"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "IdTerminoBiblioteca"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NumeroRadicado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Todos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "RecuperarDocumentosResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("VerificarDocumentoPrincipal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NumeroRadicado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "VerificarDocumentoPrincipalResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ContarPaginasDocPrincipal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Referencia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NombreBiblioteca"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ContarPaginasDocPrincipalResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ActualizarFoliosRadicacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Radicado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NumeroFolios"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ActualizarFoliosRadicacionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("EnrutarRadicacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "radicacionNumero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "usuarioEnrutaCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "dependenciaDestinoCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "usuarioDestinoCodigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "asunto"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "EnrutarRadicacionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerPorCodigo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigoCarta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CartaModelo"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerPorCodigoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerTodos");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfCartaModelo"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerTodosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CartaModelo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Perfil_ActualizarFechaCorte");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "fechaCorte"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Perfil_ObtenerDigitalizacionEstado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Perfil_ObtenerDigitalizacionEstadoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Perfil_ObtenerDocumentos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfDocumento"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Perfil_ObtenerDocumentosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Documento"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Perfil_ObtenerDocumentosByConsecutivo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "consecutivo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ObtenerDocumentosByConsecutivoResult"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Perfil_ObtenerDocumentosByConsecutivoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Archivo_Bajar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "radicacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "nombre"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.microsoft.com/Message", "StreamBody"));
        oper.setReturnClass(byte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_BajarResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Archivo_BajarBase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "radicacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "nombre"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_BajarBaseResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Archivo_BajarV2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "radicacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "nombre"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.microsoft.com/Message", "StreamBody"));
        oper.setReturnClass(byte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_BajarV2Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Archivo_Subir");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.microsoft.com/Message", "StreamBody"), byte[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Archivo_SubirPostal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "ArchivoUploadDTOB"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.ArchivoUploadDTOB.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_SubirPostalResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_Anular");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "observacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_AnularResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_Modificar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion"), org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ModificarResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_ModificarPlano");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionPlano"), org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoSimpleOfRadicacionPlanoPzrEYndD"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ModificarPlanoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_Obtener");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ObtenerResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_ObtenerV2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ObtenerV2Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_ObtenerPlano");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionPlano"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ObtenerPlanoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_Obtener_DatosBasicos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numero"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "RadicacionDatosBasicos"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_Obtener_DatosBasicosResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_Radicar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion"), org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_RadicarResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MedioEnvio_ObtenerHabilitados");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfMedioDeEnvio"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerHabilitadosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MedioEnvio_ObtenerHabilitadosPorRadicacionTipo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "radicacionTipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfMedioDeEnvio"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MedioEnvio_ObtenerPorId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerPorIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MedioEnvio_ObtenerPorCodigo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerPorCodigoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MedioEnvio_ObtenerTodos");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfMedioDeEnvio"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerTodosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TipoDocumental_ObtenerPorCodigo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "code"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorCodigoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TipoDocumental_ObtenerPorId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TipoDocumental_ObtenerTodos");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfTipoDocumental"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerTodosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Tramite_ObtenerHabilitados");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfTramite"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerHabilitadosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Tramite_ObtenerHabilitadosPorRadicacionTipo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "radicacionTipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfTramite"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerHabilitadosPorRadicacionTipoResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Tramite_ObtenerPorCodigo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "code"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerPorCodigoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Tramite_ObtenerPorId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerPorIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Tramite_ObtenerTodos");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfTramite"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerTodosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TipoSeguridad_ObtenerHabilitados");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfDocumentoTipoSeguridad"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerHabilitadosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TipoSeguridad_ObtenerPorCodigo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "code"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerPorCodigoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TipoSeguridad_ObtenerPorId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerPorIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TipoSeguridad_ObtenerTodos");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfDocumentoTipoSeguridad"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerTodosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Documento_Subir");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.microsoft.com/Message", "StreamBody"), byte[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Nombre"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Documento_Borrar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "nombre"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AutosControl");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "consultaAutosControlEA"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaAutosControlEA"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControlEA.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaAutosControl"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "AutosControlResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SinPostergadosAutosControl");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "consultaAutosControlEA"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaAutosControlEA"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControlEA.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaAutosControl"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SinPostergadosAutosControlResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AutosAutomatico");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "consultaAutosControl"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaAutosControl"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControl.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaAutosAutomaticoGeneral"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "AutosAutomaticoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultaEstados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "datosConsultaEstados"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "DatosConsultaEstados"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaEstados.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "RespuestaConsulta"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaEstadosResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultaRadicado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "datosConsultaRadicado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "DatosConsultaRadicado"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaRadicado.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaConsultaPostal"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaPostal.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaRadicadoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ModificarRadicadosTraslados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "idProceso"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(java.lang.Integer.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ModificarRadicadosTrasladosResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ModificarAutosEstados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "idProceso"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(java.lang.Integer.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ModificarAutosEstadosResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CrearBorrador");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "borradorData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Borrador"), com.tandem.postal.dto.Borrador.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Borrador"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Borrador.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "CrearBorradorResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("OficializarBorrador");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numeroBorrador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "funcionarioFirmaCerificaciones"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "OficializarBorradorResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[57] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerDocumentos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numeroRadicacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfDocumento"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerDocumentosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Documento"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[58] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SubirBorrador");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "archivo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SubirBorradorResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[59] = oper;

    }

    private static void _initOperationDesc7(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultaEstadosTraslados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "filtro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaEstadosTraslados"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaEstadosTraslados.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaConsultaEstadosTraslados"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaEstadosTrasladosResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[60] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ActualizaEstadoPostal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "numRadicado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigoTramite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "tipoDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ResultActualizaEstadoPostal"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ResultActualizaEstadoPostal.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ActualizaEstadoPostalResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[61] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultaAutos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "tipoDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "tipoNotificacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "fechaEstado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RepuestaConsultaAutos"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RepuestaConsultaAutos.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaAutosResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[62] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Traslados_Automatico");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "trasladosAutomatico_Input"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "TrasladosAutomatico_Input"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosAutomatico_Input.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaTrasladosAutomatico"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Traslados_AutomaticoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[63] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TrasladosRadicado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "trasladosRadicadoInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "TrasladosRadicadoInput"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosRadicadoInput.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "RespuestaConsulta"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TrasladosRadicadoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[64] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultaTraslados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "consultaTrasladosInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaTrasladosInput"), org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaTrasladosInput.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "RespuestaConsulta"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaTrasladosResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[65] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Radicacion_RadicarMasivo");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_RadicarMasivoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[66] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerRadicacionTipoTodos");
        oper.setReturnType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "ArrayOfMedioDeEnvio"));
        oper.setReturnClass(com.tandem.postal.dto.MedioDeEnvio[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerRadicacionTipoTodosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "MedioDeEnvio"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[67] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarRadicaciones");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigoDependencia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "tipoDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "fecha"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoOfPROFILEOyDEkVga"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultarRadicacionesResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[68] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerTipoCanalPorTipoRadicacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "tipoRadicacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoOfMedioDeEnvioCloudUyyCEAza"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfMedioDeEnvioCloudUyyCEAza.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerTipoCanalPorTipoRadicacionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[69] = oper;

    }

    private static void _initOperationDesc8(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerTipoSeguridadPorTipoRadicacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "tipoRadicacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerTipoSeguridadPorTipoRadicacionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[70] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerSeriesPorCodigoTramite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codTramite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfRespuestaGeneral_PHy_PfD4i"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerSeriesPorCodigoTramiteResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[71] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerTipoExpedientePorCodigoTramite");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codTramite"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfRespuestaGeneral_PHy_PfD4i"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerTipoExpedientePorCodigoTramiteResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[72] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultarRadicacionesporFechas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "tipoDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfint"), int[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "int"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "fecha"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "hora"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultarRadicacionesporFechasResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[73] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerSubSeriesPorCodigoSerie");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigoSubSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfSubSerie_PHy_PfD4i"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerSubSeriesPorCodigoSerieResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[74] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerParametrosPorIdTipo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "idTipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfListaParametros1mTlaX0g"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfListaParametros1MTlaX0G.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerParametrosPorIdTipoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[75] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Cuaderno_ObtenerPorCodigo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Cuaderno"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Cuaderno_ObtenerPorCodigoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[76] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Serie_ObtenerPorCodigo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigoSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOftramite.SeriesPzrEYndD"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Serie_ObtenerPorCodigoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[77] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Subserie_ObtenerPorCodigo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigoSubSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfSubSerie_PHy_PfD4i"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "Subserie_ObtenerPorCodigoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[78] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeterminarEstadoSwitch");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "DeterminarEstadoSwitchResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[79] = oper;

    }

    private static void _initOperationDesc9(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CuadernoActivos_ObtenerPorSerie");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigoSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfCuadernoUyyCEAza"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfCuadernoUyyCEAza.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "CuadernoActivos_ObtenerPorSerieResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[80] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SerieActivas_ObtenerPorDependencia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigoDependencia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOftramite.SeriesPzrEYndD"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SerieActivas_ObtenerPorDependenciaResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[81] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TipoDocumental_ObtenerPorEstado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "estado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfTipoDocumental"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorEstadoResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[82] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TipoDocumental_ObtenerPorDependencia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "codigoDependencia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfTipoDocumental"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorDependenciaResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[83] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SeriesActivas_ObtenerTodos");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOftramite.SeriesPzrEYndD"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SeriesActivas_ObtenerTodosResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                      "org.datacontract.schemas._2004._07.System_ServiceModel.FaultException",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"), 
                      true
                     ));
        _operations[84] = oper;

    }

    public BasicHttpBinding_IIntegrationPostalServicesStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public BasicHttpBinding_IIntegrationPostalServicesStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public BasicHttpBinding_IIntegrationPostalServicesStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
        addBindings0();
        addBindings1();
        addBindings2();
    }

    private void addBindings0() {
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
            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "ArrayOfMedioDeEnvio");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.MedioDeEnvio[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "MedioDeEnvio");
            qName2 = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "MedioDeEnvio");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Borrador");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.Borrador.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "CartaModelo");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.CartaModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Ciudad");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.Ciudad.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "CiudadId");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.CiudadId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Corresponsal");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.Corresponsal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Dependencia");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.Dependencia.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "DocumentoTipoSeguridad");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.DocumentoTipoSeguridad.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Funcionario");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.Funcionario.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "MedioDeEnvio");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.MedioDeEnvio.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Particular");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.Particular.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Proceso");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.Proceso.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Termino");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.Termino.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "TipoCuaderno");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.TipoCuaderno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "TipoIdentificacion");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.TipoIdentificacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Tramite");
            cachedSerQNames.add(qName);
            cls = com.tandem.postal.dto.Tramite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "ArrayOfListaParametros");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Administracion.ListaParametros[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "ListaParametros");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "ListaParametros");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "ListaParametros");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Administracion.ListaParametros.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "DatosConsultaEstados");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaEstados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "DatosConsultaRadicado");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaRadicado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "RespuestaConsulta");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfCuadernoUyyCEAza");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfCuadernoUyyCEAza.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfListaParametros1mTlaX0g");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfListaParametros1MTlaX0G.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfRespuestaGeneral_PHy_PfD4i");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfSubSerie_PHy_PfD4i");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOftramite.SeriesPzrEYndD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "ArchivoUploadDTOB");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.ArchivoUploadDTOB.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "ArrayOfCuaderno");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Cuaderno");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Cuaderno");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "ArrayOfDocumentoTipoSeguridadCloud");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.DocumentoTipoSeguridadCloud[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "DocumentoTipoSeguridadCloud");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "DocumentoTipoSeguridadCloud");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "ArrayOfMedioDeEnvioCloud");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.MedioDeEnvioCloud[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "MedioDeEnvioCloud");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "MedioDeEnvioCloud");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Cuaderno");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "DocumentoTipoSeguridadCloud");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.DocumentoTipoSeguridadCloud.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "MedioDeEnvioCloud");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.MedioDeEnvioCloud.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "RadicacionDatosBasicos");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Value");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Value.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.GestionDocumental", "ArrayOfPROFILE");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_GestionDocumental.PROFILE[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.GestionDocumental", "PROFILE");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.GestionDocumental", "PROFILE");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.GestionDocumental", "PROFILE");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_GestionDocumental.PROFILE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ArrayOfAutosAutomaticoGeneral");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomaticoGeneral[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosAutomaticoGeneral");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosAutomaticoGeneral");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ArrayOfAutosControl");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosControl[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosControl");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosControl");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ArrayOfC_AutosControl");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosControl[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosControl");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosControl");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ArrayOfC_AutosMercantil");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosMercantil[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosMercantil");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosMercantil");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ArrayOfEstadosTraslados");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.EstadosTraslados[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "EstadosTraslados");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "EstadosTraslados");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ArrayOfRespuestaGeneral");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaGeneral[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaGeneral");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaGeneral");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ArrayOfSubSerie");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.SubSerie[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "SubSerie");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "SubSerie");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ArrayOfTraslados_Automatico");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.Traslados_Automatico[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Traslados_Automatico");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Traslados_Automatico");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosAutomaticoGeneral");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomaticoGeneral.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosControl");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosControl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosControl");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosControl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosMercantil");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosMercantil.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaAutosControl");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaAutosControlEA");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControlEA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaEstadosTraslados");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaEstadosTraslados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaTrasladosInput");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaTrasladosInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "EstadosTraslados");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.EstadosTraslados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ObtenerDocumentosByConsecutivoResult");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RepuestaConsultaAutos");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RepuestaConsultaAutos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaAutosAutomaticoGeneral");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaAutosControl");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaConsultaEstadosTraslados");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaConsultaPostal");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaPostal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaGeneral");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaGeneral.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaRadicacionInterna");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaTrasladosAutomatico");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ResultActualizaEstadoPostal");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ResultActualizaEstadoPostal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "SubSerie");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.SubSerie.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Traslados_Automatico");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.Traslados_Automatico.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "TrasladosAutomatico_Input");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosAutomatico_Input.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "TrasladosRadicadoInput");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosRadicadoInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoBase");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoBase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoOfMedioDeEnvioCloudUyyCEAza");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfMedioDeEnvioCloudUyyCEAza.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoOfPROFILEOyDEkVga");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoSimpleOfRadicacionPlanoPzrEYndD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfCartaModelo");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CartaModelo");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CartaModelo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfDocumento");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Documento");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Documento");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfDocumentoTipoSeguridad");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfMedioDeEnvio");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfTipoDocumental");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOfTramite");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ArrayOftramite.Series");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.TramiteSeries[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "tramite.Series");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "tramite.Series");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Borrador");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Borrador.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CartaModelo");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Ciudad");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Ciudad.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CiudadId");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.CiudadId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "clasificacionDocumental");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.ClasificacionDocumental.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Corresponsal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Dependencia");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Documento");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Particular");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Proceso");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Proceso.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionBase");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionBase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionInterna");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionPlano");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionSalida");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RespuestaBase");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.RespuestaBase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
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
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Termino");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Termino.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoCuaderno");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoCuaderno.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoIdentificacion");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoIdentificacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "tramite.Padre");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.TramitePadre.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "tramite.Series");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.TramiteSeries.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "tramite.Tipo");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.InteropSuper_Modelos.TramiteTipo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "ArrayOfFaultException.FaultCodeData");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_ServiceModel.FaultExceptionFaultCodeData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException.FaultCodeData");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException.FaultCodeData");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "ArrayOfFaultException.FaultReasonData");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_ServiceModel.FaultExceptionFaultReasonData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException.FaultReasonData");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException.FaultReasonData");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "CommunicationException");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_ServiceModel.CommunicationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_ServiceModel.FaultException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException.FaultCodeData");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_ServiceModel.FaultExceptionFaultCodeData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException.FaultReasonData");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_ServiceModel.FaultExceptionFaultReasonData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System", "Exception");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System.Exception.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System", "SystemException");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System.SystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", ">ArrayOfKeyValueOfstringDocumentoTipoSeguridad8p61K7ew>KeyValueOfstringDocumentoTipoSeguridad8p61K7ew");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", ">ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5csI>KeyValueOfstringDocumentoTipoSeguridadUMuZ5csI");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfint");
            cachedSerQNames.add(qName);
            cls = int[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int");
            qName2 = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "int");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfKeyValueOfstringDocumentoTipoSeguridad8p61K7ew");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", ">ArrayOfKeyValueOfstringDocumentoTipoSeguridad8p61K7ew>KeyValueOfstringDocumentoTipoSeguridad8p61K7ew");
            qName2 = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "KeyValueOfstringDocumentoTipoSeguridad8p61K7ew");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5csI");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", ">ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5csI>KeyValueOfstringDocumentoTipoSeguridadUMuZ5csI");
            qName2 = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "KeyValueOfstringDocumentoTipoSeguridadUMuZ5csI");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
            cachedSerQNames.add(qName);
            cls = org.apache.axis.types.Duration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/Message", "StreamBody");
            cachedSerQNames.add(qName);
            cls = byte[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(arraysf);
            cachedDeserFactories.add(arraydf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ActualizaEstadoPostal");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ActualizaEstadoPostal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ActualizaEstadoPostalResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ActualizaEstadoPostalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ActualizarFoliosRadicacion");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ActualizarFoliosRadicacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ActualizarFoliosRadicacionResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ActualizarFoliosRadicacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_Bajar");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Archivo_Bajar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_BajarBase");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Archivo_BajarBase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_BajarBaseResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Archivo_BajarBaseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_BajarResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Archivo_BajarResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_BajarV2");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Archivo_BajarV2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_BajarV2Response");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Archivo_BajarV2Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_SubirPostal");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Archivo_SubirPostal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_SubirPostalResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Archivo_SubirPostalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ArchivoUploadDto");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ArchivoUploadDto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AutosAutomatico");
            cachedSerQNames.add(qName);
            cls = org.tempuri.AutosAutomatico.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AutosAutomaticoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.AutosAutomaticoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AutosControl");
            cachedSerQNames.add(qName);
            cls = org.tempuri.AutosControl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AutosControlResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.AutosControlResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaAutos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaAutos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaAutosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaAutosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaEstados");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaEstados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaEstadosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaEstadosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaEstadosTraslados");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaEstadosTraslados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaEstadosTrasladosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaEstadosTrasladosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaRadicado");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaRadicado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaRadicadoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaRadicadoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultarRadicaciones");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultarRadicaciones.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultarRadicacionesporFechas");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultarRadicacionesporFechas.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultarRadicacionesporFechasResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultarRadicacionesporFechasResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultarRadicacionesResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultarRadicacionesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaTraslados");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaTraslados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaTrasladosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ConsultaTrasladosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ContarPaginasDocPrincipal");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ContarPaginasDocPrincipal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ContarPaginasDocPrincipalResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ContarPaginasDocPrincipalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">CrearBorrador");
            cachedSerQNames.add(qName);
            cls = org.tempuri.CrearBorrador.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">CrearBorradorResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.CrearBorradorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Cuaderno_ObtenerPorCodigo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Cuaderno_ObtenerPorCodigo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Cuaderno_ObtenerPorCodigoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Cuaderno_ObtenerPorCodigoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">CuadernoActivos_ObtenerPorSerie");
            cachedSerQNames.add(qName);
            cls = org.tempuri.CuadernoActivos_ObtenerPorSerie.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">CuadernoActivos_ObtenerPorSerieResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.CuadernoActivos_ObtenerPorSerieResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">DeterminarEstadoSwitch");
            cachedSerQNames.add(qName);
            cls = org.tempuri.DeterminarEstadoSwitch.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">DeterminarEstadoSwitchResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.DeterminarEstadoSwitchResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Documento_Borrar");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Documento_Borrar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Documento_BorrarResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Documento_BorrarResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">EnrutarRadicacion");
            cachedSerQNames.add(qName);
            cls = org.tempuri.EnrutarRadicacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">EnrutarRadicacionResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.EnrutarRadicacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerHabilitados");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerHabilitados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerHabilitadosPorRadicacionTipo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerHabilitadosPorRadicacionTipo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerHabilitadosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerHabilitadosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerPorCodigo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerPorCodigo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerPorCodigoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerPorCodigoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerPorId");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerPorId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerPorIdResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerPorIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerTodos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerTodos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerTodosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.MedioEnvio_ObtenerTodosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ModificarAutosEstados");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ModificarAutosEstados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ModificarAutosEstadosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ModificarAutosEstadosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ModificarRadicadosTraslados");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ModificarRadicadosTraslados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ModificarRadicadosTrasladosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ModificarRadicadosTrasladosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerDocumentos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerDocumentos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerDocumentosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerDocumentosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerParametrosPorIdTipo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerParametrosPorIdTipo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerParametrosPorIdTipoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerParametrosPorIdTipoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerPorCodigo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerPorCodigo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerPorCodigoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerPorCodigoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerRadicacionTipoTodos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerRadicacionTipoTodos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerRadicacionTipoTodosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerRadicacionTipoTodosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerSeriesPorCodigoTramite");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerSeriesPorCodigoTramite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerSeriesPorCodigoTramiteResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerSeriesPorCodigoTramiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerSubSeriesPorCodigoSerie");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerSubSeriesPorCodigoSerie.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerSubSeriesPorCodigoSerieResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerSubSeriesPorCodigoSerieResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTipoCanalPorTipoRadicacion");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerTipoCanalPorTipoRadicacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTipoCanalPorTipoRadicacionResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerTipoCanalPorTipoRadicacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTipoExpedientePorCodigoTramite");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerTipoExpedientePorCodigoTramite.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings2() {
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
            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTipoExpedientePorCodigoTramiteResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerTipoExpedientePorCodigoTramiteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTipoSeguridadPorTipoRadicacion");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerTipoSeguridadPorTipoRadicacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTipoSeguridadPorTipoRadicacionResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerTipoSeguridadPorTipoRadicacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTodos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerTodos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTodosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.ObtenerTodosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">OficializarBorrador");
            cachedSerQNames.add(qName);
            cls = org.tempuri.OficializarBorrador.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">OficializarBorradorResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.OficializarBorradorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ActualizarFechaCorte");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Perfil_ActualizarFechaCorte.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ActualizarFechaCorteResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Perfil_ActualizarFechaCorteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ObtenerDigitalizacionEstado");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Perfil_ObtenerDigitalizacionEstado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ObtenerDigitalizacionEstadoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Perfil_ObtenerDigitalizacionEstadoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ObtenerDocumentos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Perfil_ObtenerDocumentos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ObtenerDocumentosByConsecutivo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Perfil_ObtenerDocumentosByConsecutivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ObtenerDocumentosByConsecutivoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Perfil_ObtenerDocumentosByConsecutivoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ObtenerDocumentosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Perfil_ObtenerDocumentosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_Anular");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_Anular.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_AnularResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_AnularResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_Modificar");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_Modificar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ModificarPlano");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_ModificarPlano.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ModificarPlanoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_ModificarPlanoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ModificarResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_ModificarResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_Obtener");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_Obtener.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_Obtener_DatosBasicos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_Obtener_DatosBasicos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_Obtener_DatosBasicosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_Obtener_DatosBasicosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ObtenerPlano");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_ObtenerPlano.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ObtenerPlanoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_ObtenerPlanoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ObtenerResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_ObtenerResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ObtenerV2");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_ObtenerV2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ObtenerV2Response");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_ObtenerV2Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_Radicar");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_Radicar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_RadicarInterna");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_RadicarInterna.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_RadicarInternaResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_RadicarInternaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_RadicarMasivo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_RadicarMasivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_RadicarMasivoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_RadicarMasivoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_RadicarResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Radicacion_RadicarResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RadicarEntrada");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RadicarEntrada.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RadicarEntradaResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RadicarEntradaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RadicarSalida");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RadicarSalida.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RadicarSalidaResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RadicarSalidaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RadicarSalidaV2");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RadicarSalidaV2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RadicarSalidaV2Response");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RadicarSalidaV2Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RecuperarDocumentos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RecuperarDocumentos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RecuperarDocumentosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RecuperarDocumentosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RequestDocumentoDTO");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RequestDocumentoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">RespuestaDTO");
            cachedSerQNames.add(qName);
            cls = org.tempuri.RespuestaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Serie_ObtenerPorCodigo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Serie_ObtenerPorCodigo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Serie_ObtenerPorCodigoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Serie_ObtenerPorCodigoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SerieActivas_ObtenerPorDependencia");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SerieActivas_ObtenerPorDependencia.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SerieActivas_ObtenerPorDependenciaResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SerieActivas_ObtenerPorDependenciaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SeriesActivas_ObtenerTodos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SeriesActivas_ObtenerTodos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SeriesActivas_ObtenerTodosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SeriesActivas_ObtenerTodosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SinPostergadosAutosControl");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SinPostergadosAutosControl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SinPostergadosAutosControlResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SinPostergadosAutosControlResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SubirArchivoDesdeProcesoBPM");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SubirArchivoDesdeProcesoBPM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SubirArchivoDesdeProcesoBPMResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SubirArchivoDesdeProcesoBPMResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SubirArchivoDesdeProcesoBPMV2");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SubirArchivoDesdeProcesoBPMV2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SubirArchivoDesdeProcesoBPMV2Response");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SubirArchivoDesdeProcesoBPMV2Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SubirBorrador");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SubirBorrador.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SubirBorradorResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SubirBorradorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Subserie_ObtenerPorCodigo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Subserie_ObtenerPorCodigo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Subserie_ObtenerPorCodigoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Subserie_ObtenerPorCodigoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorCodigo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerPorCodigo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorCodigoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerPorCodigoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorDependencia");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerPorDependencia.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorDependenciaResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerPorDependenciaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorEstado");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerPorEstado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorEstadoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerPorEstadoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorId");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerPorId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorIdResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerPorIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerTodos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerTodos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerTodosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoDocumental_ObtenerTodosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerHabilitados");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoSeguridad_ObtenerHabilitados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerHabilitadosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoSeguridad_ObtenerHabilitadosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerPorCodigo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoSeguridad_ObtenerPorCodigo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerPorCodigoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoSeguridad_ObtenerPorCodigoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerPorId");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoSeguridad_ObtenerPorId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerPorIdResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoSeguridad_ObtenerPorIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerTodos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoSeguridad_ObtenerTodos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerTodosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TipoSeguridad_ObtenerTodosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerHabilitados");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerHabilitados.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerHabilitadosPorRadicacionTipo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerHabilitadosPorRadicacionTipo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerHabilitadosPorRadicacionTipoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerHabilitadosPorRadicacionTipoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerHabilitadosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerHabilitadosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerPorCodigo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerPorCodigo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerPorCodigoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerPorCodigoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerPorId");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerPorId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerPorIdResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerPorIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerTodos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerTodos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerTodosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Tramite_ObtenerTodosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Traslados_Automatico");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Traslados_Automatico.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">Traslados_AutomaticoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.Traslados_AutomaticoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TrasladosRadicado");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TrasladosRadicado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">TrasladosRadicadoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.TrasladosRadicadoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">VerificarDocumentoPrincipal");
            cachedSerQNames.add(qName);
            cls = org.tempuri.VerificarDocumentoPrincipal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">VerificarDocumentoPrincipalResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.VerificarDocumentoPrincipalResponse.class;
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

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass radicarEntrada(java.lang.String anexosFisicos, java.lang.String aplicaCiudadCodigo, java.lang.String aplicaDepartamentoCodigo, java.lang.String aplicaPaisCodigo, java.lang.String aplicaEmail, java.lang.String aplicaDireccion, java.lang.String aplicaNombre, java.lang.String aplicaTelefono, java.lang.String aplicaIdentificacion, java.lang.Integer aplicaTipoIdentificacionId, java.lang.String aplicaTipoIdentificacionNombre, java.lang.String particularIdentificacion, java.lang.String particularNombre, java.lang.Integer particularTipoIdentificacionId, java.lang.String particularTipoIdentificacionNombre, java.lang.String particularCiudadCodigo, java.lang.String particularDepartamentoCodigo, java.lang.String particularPaisCodigo, java.lang.String particularDireccion, java.lang.String particularTelefono, java.lang.String particularEmail, java.lang.Integer dependenciaId, java.lang.String dependenciaNombre, java.lang.Boolean entregaFisica, java.lang.Integer foliosNumero, java.lang.String referenciaExterna, java.lang.Integer cuadernoTipoId, java.lang.String cuadernoCodigo, java.lang.String documentalTipoCodigo, java.lang.Integer tramiteId, java.lang.Long tramiteCodigo, java.lang.String extensionArchivo, java.lang.Integer codigoMedioEnvio, java.lang.String codigoTipoSeguridad, java.lang.String codigoSerie, java.lang.String codigoSubSerie, java.lang.String loginUsuario, java.lang.String radicacionAnterior, java.lang.String nombreSerie, java.lang.String nombreSubSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/RadicarEntrada");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "RadicarEntrada"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {anexosFisicos, aplicaCiudadCodigo, aplicaDepartamentoCodigo, aplicaPaisCodigo, aplicaEmail, aplicaDireccion, aplicaNombre, aplicaTelefono, aplicaIdentificacion, aplicaTipoIdentificacionId, aplicaTipoIdentificacionNombre, particularIdentificacion, particularNombre, particularTipoIdentificacionId, particularTipoIdentificacionNombre, particularCiudadCodigo, particularDepartamentoCodigo, particularPaisCodigo, particularDireccion, particularTelefono, particularEmail, dependenciaId, dependenciaNombre, entregaFisica, foliosNumero, referenciaExterna, cuadernoTipoId, cuadernoCodigo, documentalTipoCodigo, tramiteId, tramiteCodigo, extensionArchivo, codigoMedioEnvio, codigoTipoSeguridad, codigoSerie, codigoSubSerie, loginUsuario, radicacionAnterior, nombreSerie, nombreSubSerie});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass radicarSalida(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida radicacionSalida) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/RadicarSalida");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "RadicarSalida"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicacionSalida});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass radicarSalidaV2(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida radicacionSalida) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/RadicarSalidaV2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "RadicarSalidaV2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicacionSalida});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna radicacion_RadicarInterna(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna radicacionInterna) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_RadicarInterna");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_RadicarInterna"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicacionInterna});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPM(java.lang.String refBase, java.lang.String nombreTermino, java.lang.String radicacion, java.lang.String tipoDocumento, java.lang.String totalDocumentos, java.lang.String uploadBy) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/SubirArchivoDesdeProcesoBPM");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SubirArchivoDesdeProcesoBPM"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {refBase, nombreTermino, radicacion, tipoDocumento, totalDocumentos, uploadBy});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPMV2(java.lang.String refBase, java.lang.String nombreTermino, java.lang.String radicacion, java.lang.String tipoDocumento) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/SubirArchivoDesdeProcesoBPMV2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SubirArchivoDesdeProcesoBPMV2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {refBase, nombreTermino, radicacion, tipoDocumento});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass recuperarDocumentos(java.lang.String refBase, java.lang.Integer idTerminoBiblioteca, java.lang.String numeroRadicado, java.lang.Boolean todos) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/RecuperarDocumentos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "RecuperarDocumentos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {refBase, idTerminoBiblioteca, numeroRadicado, todos});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass verificarDocumentoPrincipal(java.lang.String numeroRadicado) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/VerificarDocumentoPrincipal");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "VerificarDocumentoPrincipal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numeroRadicado});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass contarPaginasDocPrincipal(java.lang.String referencia, java.lang.String nombreBiblioteca) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ContarPaginasDocPrincipal");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ContarPaginasDocPrincipal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {referencia, nombreBiblioteca});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass actualizarFoliosRadicacion(java.lang.String radicado, java.lang.Integer numeroFolios) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ActualizarFoliosRadicacion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ActualizarFoliosRadicacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicado, numeroFolios});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass enrutarRadicacion(java.lang.String radicacionNumero, java.lang.String usuarioEnrutaCodigo, java.lang.Long dependenciaDestinoCodigo, java.lang.String usuarioDestinoCodigo, java.lang.String asunto) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/EnrutarRadicacion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "EnrutarRadicacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicacionNumero, usuarioEnrutaCodigo, dependenciaDestinoCodigo, usuarioDestinoCodigo, asunto});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo obtenerPorCodigo(java.lang.String codigoCarta) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerPorCodigo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerPorCodigo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoCarta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo[] obtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerTodos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerTodos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void perfil_ActualizarFechaCorte(java.lang.String numero, java.util.Calendar fechaCorte) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Perfil_ActualizarFechaCorte");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Perfil_ActualizarFechaCorte"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numero, fechaCorte});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String perfil_ObtenerDigitalizacionEstado(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Perfil_ObtenerDigitalizacionEstado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Perfil_ObtenerDigitalizacionEstado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numero});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] perfil_ObtenerDocumentos(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Perfil_ObtenerDocumentos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Perfil_ObtenerDocumentos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numero});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult perfil_ObtenerDocumentosByConsecutivo(java.lang.String numero, java.lang.String consecutivo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Perfil_ObtenerDocumentosByConsecutivo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Perfil_ObtenerDocumentosByConsecutivo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numero, consecutivo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public byte[] archivo_Bajar(java.lang.String radicacion, java.lang.String nombre) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Archivo_Bajar");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_Bajar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicacion, nombre});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (byte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (byte[]) org.apache.axis.utils.JavaUtils.convert(_resp, byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String archivo_BajarBase(java.lang.String radicacion, java.lang.String nombre) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Archivo_BajarBase");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_BajarBase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicacion, nombre});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public byte[] archivo_BajarV2(java.lang.String radicacion, java.lang.String nombre) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Archivo_BajarV2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_BajarV2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicacion, nombre});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (byte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (byte[]) org.apache.axis.utils.JavaUtils.convert(_resp, byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void archivo_Subir(byte[] archivo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Archivo_Subir");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ArchivoUploadDto"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {archivo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String archivo_SubirPostal(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.ArchivoUploadDTOB request) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Archivo_SubirPostal");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_SubirPostal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String radicacion_Anular(java.lang.String request, java.lang.String observacion) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_Anular");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_Anular"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request, observacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_Modificar(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion request) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_Modificar");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_Modificar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD radicacion_ModificarPlano(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano request) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_ModificarPlano");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ModificarPlano"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_Obtener(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_Obtener");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_Obtener"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numero});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ObtenerV2(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_ObtenerV2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ObtenerV2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numero});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano radicacion_ObtenerPlano(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_ObtenerPlano");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ObtenerPlano"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numero});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos radicacion_Obtener_DatosBasicos(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_Obtener_DatosBasicos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_Obtener_DatosBasicos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numero});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_Radicar(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion request) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_Radicar");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_Radicar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerHabilitados() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/MedioEnvio_ObtenerHabilitados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerHabilitados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerHabilitadosPorRadicacionTipo(java.lang.String radicacionTipo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/MedioEnvio_ObtenerHabilitadosPorRadicacionTipo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerHabilitadosPorRadicacionTipo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicacionTipo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorId(java.lang.Long id) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/MedioEnvio_ObtenerPorId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerPorId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorCodigo(java.lang.Long codigo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/MedioEnvio_ObtenerPorCodigo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerPorCodigo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/MedioEnvio_ObtenerTodos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerTodos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorCodigo(java.lang.String code) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TipoDocumental_ObtenerPorCodigo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorCodigo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {code});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorId(java.lang.Long id) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TipoDocumental_ObtenerPorId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TipoDocumental_ObtenerTodos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerTodos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitados() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Tramite_ObtenerHabilitados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerHabilitados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitadosPorRadicacionTipo(java.lang.Integer radicacionTipo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Tramite_ObtenerHabilitadosPorRadicacionTipo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerHabilitadosPorRadicacionTipo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {radicacionTipo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorCodigo(java.lang.Long code) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Tramite_ObtenerPorCodigo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerPorCodigo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {code});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorId(java.lang.Long id) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Tramite_ObtenerPorId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerPorId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Tramite_ObtenerTodos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerTodos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerHabilitados() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TipoSeguridad_ObtenerHabilitados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerHabilitados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorCodigo(java.lang.String code) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TipoSeguridad_ObtenerPorCodigo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerPorCodigo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {code});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorId(java.lang.Long id) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TipoSeguridad_ObtenerPorId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerPorId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TipoSeguridad_ObtenerTodos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerTodos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String documento_Subir(byte[] archivo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Documento_Subir");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "RequestDocumentoDTO"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {archivo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void documento_Borrar(java.lang.String nombre) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Documento_Borrar");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Documento_Borrar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nombre});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl autosControl(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControlEA consultaAutosControlEA) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/AutosControl");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "AutosControl"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaAutosControlEA});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl sinPostergadosAutosControl(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControlEA consultaAutosControlEA) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/SinPostergadosAutosControl");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SinPostergadosAutosControl"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaAutosControlEA});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral autosAutomatico(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControl consultaAutosControl) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/AutosAutomatico");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "AutosAutomatico"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaAutosControl});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaEstados(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaEstados datosConsultaEstados) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ConsultaEstados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaEstados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosConsultaEstados});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaPostal consultaRadicado(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaRadicado datosConsultaRadicado) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ConsultaRadicado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaRadicado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosConsultaRadicado});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaPostal) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaPostal) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaPostal.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Integer modificarRadicadosTraslados(java.lang.Integer idProceso) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ModificarRadicadosTraslados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ModificarRadicadosTraslados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idProceso});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Integer) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Integer.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Integer modificarAutosEstados(java.lang.Integer idProceso) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ModificarAutosEstados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ModificarAutosEstados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idProceso});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Integer) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Integer.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Borrador crearBorrador(com.tandem.postal.dto.Borrador borradorData) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/CrearBorrador");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "CrearBorrador"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {borradorData});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Borrador) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Borrador) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Borrador.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion oficializarBorrador(java.lang.String numeroBorrador, java.lang.String funcionarioFirmaCerificaciones) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/OficializarBorrador");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "OficializarBorrador"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numeroBorrador, funcionarioFirmaCerificaciones});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] obtenerDocumentos(java.lang.String numeroRadicacion) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[58]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerDocumentos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerDocumentos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numeroRadicacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String subirBorrador(byte[] archivo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[59]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/SubirBorrador");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SubirBorrador"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {archivo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados consultaEstadosTraslados(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaEstadosTraslados filtro) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[60]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ConsultaEstadosTraslados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaEstadosTraslados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {filtro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ResultActualizaEstadoPostal actualizaEstadoPostal(java.lang.String numRadicado, java.lang.Integer codigoTramite, java.lang.Integer tipoDocumento) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[61]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ActualizaEstadoPostal");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ActualizaEstadoPostal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numRadicado, codigoTramite, tipoDocumento});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ResultActualizaEstadoPostal) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ResultActualizaEstadoPostal) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ResultActualizaEstadoPostal.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RepuestaConsultaAutos consultaAutos(java.lang.String tipoDocumento, java.lang.String tipoNotificacion, java.util.Calendar fechaEstado) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[62]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ConsultaAutos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaAutos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tipoDocumento, tipoNotificacion, fechaEstado});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RepuestaConsultaAutos) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RepuestaConsultaAutos) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RepuestaConsultaAutos.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico traslados_Automatico(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosAutomatico_Input trasladosAutomatico_Input) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[63]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Traslados_Automatico");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Traslados_Automatico"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {trasladosAutomatico_Input});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta trasladosRadicado(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosRadicadoInput trasladosRadicadoInput) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[64]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TrasladosRadicado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TrasladosRadicado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {trasladosRadicadoInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaTraslados(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaTrasladosInput consultaTrasladosInput) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[65]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ConsultaTraslados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaTraslados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaTrasladosInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String radicacion_RadicarMasivo() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[66]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Radicacion_RadicarMasivo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_RadicarMasivo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.tandem.postal.dto.MedioDeEnvio[] obtenerRadicacionTipoTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[67]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerRadicacionTipoTodos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerRadicacionTipoTodos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.tandem.postal.dto.MedioDeEnvio[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.tandem.postal.dto.MedioDeEnvio[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.tandem.postal.dto.MedioDeEnvio[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga consultarRadicaciones(java.lang.String codigoDependencia, java.lang.Integer tipoDocumento, java.lang.String fecha) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[68]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ConsultarRadicaciones");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultarRadicaciones"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoDependencia, tipoDocumento, fecha});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfMedioDeEnvioCloudUyyCEAza obtenerTipoCanalPorTipoRadicacion(java.lang.String tipoRadicacion) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[69]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerTipoCanalPorTipoRadicacion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerTipoCanalPorTipoRadicacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tipoRadicacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfMedioDeEnvioCloudUyyCEAza) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfMedioDeEnvioCloudUyyCEAza) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfMedioDeEnvioCloudUyyCEAza.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza obtenerTipoSeguridadPorTipoRadicacion(java.lang.String tipoRadicacion) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[70]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerTipoSeguridadPorTipoRadicacion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerTipoSeguridadPorTipoRadicacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tipoRadicacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I obtenerSeriesPorCodigoTramite(java.lang.String codTramite) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[71]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerSeriesPorCodigoTramite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerSeriesPorCodigoTramite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codTramite});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I obtenerTipoExpedientePorCodigoTramite(java.lang.String codTramite) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[72]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerTipoExpedientePorCodigoTramite");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerTipoExpedientePorCodigoTramite"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codTramite});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Boolean consultarRadicacionesporFechas(int[] tipoDocumento, java.lang.String fecha, java.lang.String hora) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[73]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ConsultarRadicacionesporFechas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultarRadicacionesporFechas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tipoDocumento, fecha, hora});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I obtenerSubSeriesPorCodigoSerie(java.lang.Integer codigoSubSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[74]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerSubSeriesPorCodigoSerie");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerSubSeriesPorCodigoSerie"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoSubSerie});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfListaParametros1MTlaX0G obtenerParametrosPorIdTipo(java.lang.Integer idTipo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[75]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/ObtenerParametrosPorIdTipo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerParametrosPorIdTipo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idTipo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfListaParametros1MTlaX0G) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfListaParametros1MTlaX0G) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfListaParametros1MTlaX0G.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno cuaderno_ObtenerPorCodigo(java.lang.Integer codigo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[76]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Cuaderno_ObtenerPorCodigo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Cuaderno_ObtenerPorCodigo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serie_ObtenerPorCodigo(java.lang.Integer codigoSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[77]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Serie_ObtenerPorCodigo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Serie_ObtenerPorCodigo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoSerie});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I subserie_ObtenerPorCodigo(java.lang.Integer codigoSubSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[78]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Subserie_ObtenerPorCodigo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Subserie_ObtenerPorCodigo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoSubSerie});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String determinarEstadoSwitch() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[79]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/DeterminarEstadoSwitch");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "DeterminarEstadoSwitch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfCuadernoUyyCEAza cuadernoActivos_ObtenerPorSerie(java.lang.Integer codigoSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[80]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/CuadernoActivos_ObtenerPorSerie");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "CuadernoActivos_ObtenerPorSerie"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoSerie});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfCuadernoUyyCEAza) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfCuadernoUyyCEAza) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfCuadernoUyyCEAza.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serieActivas_ObtenerPorDependencia(java.lang.Integer codigoDependencia) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[81]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/SerieActivas_ObtenerPorDependencia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SerieActivas_ObtenerPorDependencia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoDependencia});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorEstado(java.lang.String estado) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[82]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TipoDocumental_ObtenerPorEstado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorEstado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {estado});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorDependencia(java.lang.String codigoDependencia) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[83]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/TipoDocumental_ObtenerPorDependencia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorDependencia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codigoDependencia});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD seriesActivas_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[84]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/SeriesActivas_ObtenerTodos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SeriesActivas_ObtenerTodos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) {
              throw (org.datacontract.schemas._2004._07.System_ServiceModel.FaultException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}

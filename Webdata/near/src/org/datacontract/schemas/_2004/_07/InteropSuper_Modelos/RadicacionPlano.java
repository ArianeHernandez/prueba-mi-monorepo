/**
 * RadicacionPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos;

public class RadicacionPlano  implements java.io.Serializable {
    private java.lang.String anexosFisicos;

    private java.lang.Long aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo;

    private java.lang.Long aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo;

    private java.lang.Long aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo;

    private java.lang.String aplicaA_Ciudad_Departamento;

    private java.lang.String aplicaA_Ciudad_Nombre;

    private java.lang.String aplicaA_Ciudad_Pais;

    private java.lang.String aplicaA_Direccion;

    private java.lang.String aplicaA_Email;

    private java.lang.String aplicaA_Identificacion;

    private java.lang.String aplicaA_Nombre;

    private java.lang.String aplicaA_Telefono;

    private java.lang.Long aplicaA_TipoIdentificacion_Id;

    private java.lang.String aplicaA_TipoIdentificacion_Nombre;

    private java.lang.Long corresponsal_Dependencia_Id;

    private java.lang.String corresponsal_Dependencia_Nombre;

    private java.lang.Long corresponsal_Particular_Ciudad_Codigo_CiudadCodigo;

    private java.lang.Long corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo;

    private java.lang.Long corresponsal_Particular_Ciudad_Codigo_PaisCodigo;

    private java.lang.String corresponsal_Particular_Ciudad_Departamento;

    private java.lang.String corresponsal_Particular_Ciudad_Nombre;

    private java.lang.String corresponsal_Particular_Ciudad_Pais;

    private java.lang.String corresponsal_Particular_Direccion;

    private java.lang.String corresponsal_Particular_Email;

    private java.lang.String corresponsal_Particular_Identificacion;

    private java.lang.String corresponsal_Particular_Nombre;

    private java.lang.String corresponsal_Particular_Telefono;

    private java.lang.Long corresponsal_Particular_TipoIdentificacion_Id;

    private java.lang.String corresponsal_Particular_TipoIdentificacion_Nombre;

    private java.lang.String corresponsal_Tipo;

    private java.lang.Long dependenciaAsignada_Dependencia_Id;

    private java.lang.String dependenciaAsignada_Dependencia_Nombre;

    private java.lang.Long dependencia_Id;

    private java.lang.String dependencia_Nombre;

    private java.lang.String documentoPrincipal;

    private java.lang.String[] documentosAnexos;

    private java.lang.Boolean entregaFisica;

    private java.lang.String estado;

    private java.util.Calendar fecha;

    private java.lang.Integer folios;

    private java.lang.String funcionarioAsignado_Apellido;

    private java.lang.String funcionarioAsignado_Cargo;

    private java.lang.String funcionarioAsignado_Codigo;

    private java.lang.String funcionarioAsignado_Id;

    private java.lang.String funcionarioAsignado_Nemotecnico;

    private java.lang.String funcionarioAsignado_Nombre;

    private java.lang.String funcionario_Apellido;

    private java.lang.String funcionario_Cargo;

    private java.lang.String funcionario_Codigo;

    private java.lang.String funcionario_Id;

    private java.lang.String funcionario_Nemotecnico;

    private java.lang.String funcionario_Nombre;

    private java.lang.Long medioDeEnvio_Codigo;

    private java.lang.Long medioDeEnvio_Id;

    private java.lang.String medioDeEnvio_Nombre;

    private java.lang.String mensaje;

    private java.math.BigDecimal multa;

    private java.lang.String numero;

    private java.lang.String radicacionAnteriorNumero;

    private java.lang.String referenciaExterna;

    private java.lang.String serie_Codigo;

    private java.lang.String serie_Nombre;

    private java.lang.String sub_Serie_Codigo;

    private java.lang.String sub_Serie_Nombre;

    private java.lang.Integer terminoDias;

    private java.util.Calendar terminoFecha;

    private java.lang.String tipo;

    private java.lang.String tipoCuaderno_Codigo;

    private java.lang.Long tipoCuaderno_Id;

    private java.lang.String tipoCuaderno_Nombre;

    private java.lang.String tipoDocumentalConsecutivo;

    private java.lang.String tipoDocumental_Codigo;

    private java.lang.Long tipoDocumental_Id;

    private java.lang.String tipoDocumental_Nombre;

    private java.lang.String tipoSeguridad_Codigo;

    private java.lang.Long tipoSeguridad_Id;

    private java.lang.String tipoSeguridad_Nombre;

    private java.lang.Long tramite_Codigo;

    private java.lang.Long tramite_Id;

    private java.lang.String tramite_Nombre;

    private java.lang.Long tramite_Proceso_Codigo;

    private java.lang.Long tramite_Proceso_Id;

    private java.lang.String tramite_Proceso_Nombre;

    private java.lang.Integer tramite_Termino_Dias;

    private java.lang.Boolean tramite_Termino_EsModificable;

    private java.lang.String usuario;

    private java.lang.Integer codigoRespuesta;

    public RadicacionPlano() {
    }

    public RadicacionPlano(
           java.lang.String anexosFisicos,
           java.lang.Long aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo,
           java.lang.Long aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo,
           java.lang.Long aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo,
           java.lang.String aplicaA_Ciudad_Departamento,
           java.lang.String aplicaA_Ciudad_Nombre,
           java.lang.String aplicaA_Ciudad_Pais,
           java.lang.String aplicaA_Direccion,
           java.lang.String aplicaA_Email,
           java.lang.String aplicaA_Identificacion,
           java.lang.String aplicaA_Nombre,
           java.lang.String aplicaA_Telefono,
           java.lang.Long aplicaA_TipoIdentificacion_Id,
           java.lang.String aplicaA_TipoIdentificacion_Nombre,
           java.lang.Long corresponsal_Dependencia_Id,
           java.lang.String corresponsal_Dependencia_Nombre,
           java.lang.Long corresponsal_Particular_Ciudad_Codigo_CiudadCodigo,
           java.lang.Long corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo,
           java.lang.Long corresponsal_Particular_Ciudad_Codigo_PaisCodigo,
           java.lang.String corresponsal_Particular_Ciudad_Departamento,
           java.lang.String corresponsal_Particular_Ciudad_Nombre,
           java.lang.String corresponsal_Particular_Ciudad_Pais,
           java.lang.String corresponsal_Particular_Direccion,
           java.lang.String corresponsal_Particular_Email,
           java.lang.String corresponsal_Particular_Identificacion,
           java.lang.String corresponsal_Particular_Nombre,
           java.lang.String corresponsal_Particular_Telefono,
           java.lang.Long corresponsal_Particular_TipoIdentificacion_Id,
           java.lang.String corresponsal_Particular_TipoIdentificacion_Nombre,
           java.lang.String corresponsal_Tipo,
           java.lang.Long dependenciaAsignada_Dependencia_Id,
           java.lang.String dependenciaAsignada_Dependencia_Nombre,
           java.lang.Long dependencia_Id,
           java.lang.String dependencia_Nombre,
           java.lang.String documentoPrincipal,
           java.lang.String[] documentosAnexos,
           java.lang.Boolean entregaFisica,
           java.lang.String estado,
           java.util.Calendar fecha,
           java.lang.Integer folios,
           java.lang.String funcionarioAsignado_Apellido,
           java.lang.String funcionarioAsignado_Cargo,
           java.lang.String funcionarioAsignado_Codigo,
           java.lang.String funcionarioAsignado_Id,
           java.lang.String funcionarioAsignado_Nemotecnico,
           java.lang.String funcionarioAsignado_Nombre,
           java.lang.String funcionario_Apellido,
           java.lang.String funcionario_Cargo,
           java.lang.String funcionario_Codigo,
           java.lang.String funcionario_Id,
           java.lang.String funcionario_Nemotecnico,
           java.lang.String funcionario_Nombre,
           java.lang.Long medioDeEnvio_Codigo,
           java.lang.Long medioDeEnvio_Id,
           java.lang.String medioDeEnvio_Nombre,
           java.lang.String mensaje,
           java.math.BigDecimal multa,
           java.lang.String numero,
           java.lang.String radicacionAnteriorNumero,
           java.lang.String referenciaExterna,
           java.lang.String serie_Codigo,
           java.lang.String serie_Nombre,
           java.lang.String sub_Serie_Codigo,
           java.lang.String sub_Serie_Nombre,
           java.lang.Integer terminoDias,
           java.util.Calendar terminoFecha,
           java.lang.String tipo,
           java.lang.String tipoCuaderno_Codigo,
           java.lang.Long tipoCuaderno_Id,
           java.lang.String tipoCuaderno_Nombre,
           java.lang.String tipoDocumentalConsecutivo,
           java.lang.String tipoDocumental_Codigo,
           java.lang.Long tipoDocumental_Id,
           java.lang.String tipoDocumental_Nombre,
           java.lang.String tipoSeguridad_Codigo,
           java.lang.Long tipoSeguridad_Id,
           java.lang.String tipoSeguridad_Nombre,
           java.lang.Long tramite_Codigo,
           java.lang.Long tramite_Id,
           java.lang.String tramite_Nombre,
           java.lang.Long tramite_Proceso_Codigo,
           java.lang.Long tramite_Proceso_Id,
           java.lang.String tramite_Proceso_Nombre,
           java.lang.Integer tramite_Termino_Dias,
           java.lang.Boolean tramite_Termino_EsModificable,
           java.lang.String usuario,
           java.lang.Integer codigoRespuesta) {
           this.anexosFisicos = anexosFisicos;
           this.aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo = aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo;
           this.aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo = aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo;
           this.aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo = aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo;
           this.aplicaA_Ciudad_Departamento = aplicaA_Ciudad_Departamento;
           this.aplicaA_Ciudad_Nombre = aplicaA_Ciudad_Nombre;
           this.aplicaA_Ciudad_Pais = aplicaA_Ciudad_Pais;
           this.aplicaA_Direccion = aplicaA_Direccion;
           this.aplicaA_Email = aplicaA_Email;
           this.aplicaA_Identificacion = aplicaA_Identificacion;
           this.aplicaA_Nombre = aplicaA_Nombre;
           this.aplicaA_Telefono = aplicaA_Telefono;
           this.aplicaA_TipoIdentificacion_Id = aplicaA_TipoIdentificacion_Id;
           this.aplicaA_TipoIdentificacion_Nombre = aplicaA_TipoIdentificacion_Nombre;
           this.corresponsal_Dependencia_Id = corresponsal_Dependencia_Id;
           this.corresponsal_Dependencia_Nombre = corresponsal_Dependencia_Nombre;
           this.corresponsal_Particular_Ciudad_Codigo_CiudadCodigo = corresponsal_Particular_Ciudad_Codigo_CiudadCodigo;
           this.corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo = corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo;
           this.corresponsal_Particular_Ciudad_Codigo_PaisCodigo = corresponsal_Particular_Ciudad_Codigo_PaisCodigo;
           this.corresponsal_Particular_Ciudad_Departamento = corresponsal_Particular_Ciudad_Departamento;
           this.corresponsal_Particular_Ciudad_Nombre = corresponsal_Particular_Ciudad_Nombre;
           this.corresponsal_Particular_Ciudad_Pais = corresponsal_Particular_Ciudad_Pais;
           this.corresponsal_Particular_Direccion = corresponsal_Particular_Direccion;
           this.corresponsal_Particular_Email = corresponsal_Particular_Email;
           this.corresponsal_Particular_Identificacion = corresponsal_Particular_Identificacion;
           this.corresponsal_Particular_Nombre = corresponsal_Particular_Nombre;
           this.corresponsal_Particular_Telefono = corresponsal_Particular_Telefono;
           this.corresponsal_Particular_TipoIdentificacion_Id = corresponsal_Particular_TipoIdentificacion_Id;
           this.corresponsal_Particular_TipoIdentificacion_Nombre = corresponsal_Particular_TipoIdentificacion_Nombre;
           this.corresponsal_Tipo = corresponsal_Tipo;
           this.dependenciaAsignada_Dependencia_Id = dependenciaAsignada_Dependencia_Id;
           this.dependenciaAsignada_Dependencia_Nombre = dependenciaAsignada_Dependencia_Nombre;
           this.dependencia_Id = dependencia_Id;
           this.dependencia_Nombre = dependencia_Nombre;
           this.documentoPrincipal = documentoPrincipal;
           this.documentosAnexos = documentosAnexos;
           this.entregaFisica = entregaFisica;
           this.estado = estado;
           this.fecha = fecha;
           this.folios = folios;
           this.funcionarioAsignado_Apellido = funcionarioAsignado_Apellido;
           this.funcionarioAsignado_Cargo = funcionarioAsignado_Cargo;
           this.funcionarioAsignado_Codigo = funcionarioAsignado_Codigo;
           this.funcionarioAsignado_Id = funcionarioAsignado_Id;
           this.funcionarioAsignado_Nemotecnico = funcionarioAsignado_Nemotecnico;
           this.funcionarioAsignado_Nombre = funcionarioAsignado_Nombre;
           this.funcionario_Apellido = funcionario_Apellido;
           this.funcionario_Cargo = funcionario_Cargo;
           this.funcionario_Codigo = funcionario_Codigo;
           this.funcionario_Id = funcionario_Id;
           this.funcionario_Nemotecnico = funcionario_Nemotecnico;
           this.funcionario_Nombre = funcionario_Nombre;
           this.medioDeEnvio_Codigo = medioDeEnvio_Codigo;
           this.medioDeEnvio_Id = medioDeEnvio_Id;
           this.medioDeEnvio_Nombre = medioDeEnvio_Nombre;
           this.mensaje = mensaje;
           this.multa = multa;
           this.numero = numero;
           this.radicacionAnteriorNumero = radicacionAnteriorNumero;
           this.referenciaExterna = referenciaExterna;
           this.serie_Codigo = serie_Codigo;
           this.serie_Nombre = serie_Nombre;
           this.sub_Serie_Codigo = sub_Serie_Codigo;
           this.sub_Serie_Nombre = sub_Serie_Nombre;
           this.terminoDias = terminoDias;
           this.terminoFecha = terminoFecha;
           this.tipo = tipo;
           this.tipoCuaderno_Codigo = tipoCuaderno_Codigo;
           this.tipoCuaderno_Id = tipoCuaderno_Id;
           this.tipoCuaderno_Nombre = tipoCuaderno_Nombre;
           this.tipoDocumentalConsecutivo = tipoDocumentalConsecutivo;
           this.tipoDocumental_Codigo = tipoDocumental_Codigo;
           this.tipoDocumental_Id = tipoDocumental_Id;
           this.tipoDocumental_Nombre = tipoDocumental_Nombre;
           this.tipoSeguridad_Codigo = tipoSeguridad_Codigo;
           this.tipoSeguridad_Id = tipoSeguridad_Id;
           this.tipoSeguridad_Nombre = tipoSeguridad_Nombre;
           this.tramite_Codigo = tramite_Codigo;
           this.tramite_Id = tramite_Id;
           this.tramite_Nombre = tramite_Nombre;
           this.tramite_Proceso_Codigo = tramite_Proceso_Codigo;
           this.tramite_Proceso_Id = tramite_Proceso_Id;
           this.tramite_Proceso_Nombre = tramite_Proceso_Nombre;
           this.tramite_Termino_Dias = tramite_Termino_Dias;
           this.tramite_Termino_EsModificable = tramite_Termino_EsModificable;
           this.usuario = usuario;
           this.codigoRespuesta = codigoRespuesta;
    }


    /**
     * Gets the anexosFisicos value for this RadicacionPlano.
     * 
     * @return anexosFisicos
     */
    public java.lang.String getAnexosFisicos() {
        return anexosFisicos;
    }


    /**
     * Sets the anexosFisicos value for this RadicacionPlano.
     * 
     * @param anexosFisicos
     */
    public void setAnexosFisicos(java.lang.String anexosFisicos) {
        this.anexosFisicos = anexosFisicos;
    }


    /**
     * Gets the aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo value for this RadicacionPlano.
     * 
     * @return aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo
     */
    public java.lang.Long getAplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo() {
        return aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo;
    }


    /**
     * Sets the aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo value for this RadicacionPlano.
     * 
     * @param aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo
     */
    public void setAplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo(java.lang.Long aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo) {
        this.aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo = aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo;
    }


    /**
     * Gets the aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo value for this RadicacionPlano.
     * 
     * @return aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo
     */
    public java.lang.Long getAplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo() {
        return aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo;
    }


    /**
     * Sets the aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo value for this RadicacionPlano.
     * 
     * @param aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo
     */
    public void setAplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo(java.lang.Long aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo) {
        this.aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo = aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo;
    }


    /**
     * Gets the aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo value for this RadicacionPlano.
     * 
     * @return aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo
     */
    public java.lang.Long getAplicaA_Ciudad_Codigo_CiudadId_PaisCodigo() {
        return aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo;
    }


    /**
     * Sets the aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo value for this RadicacionPlano.
     * 
     * @param aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo
     */
    public void setAplicaA_Ciudad_Codigo_CiudadId_PaisCodigo(java.lang.Long aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo) {
        this.aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo = aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo;
    }


    /**
     * Gets the aplicaA_Ciudad_Departamento value for this RadicacionPlano.
     * 
     * @return aplicaA_Ciudad_Departamento
     */
    public java.lang.String getAplicaA_Ciudad_Departamento() {
        return aplicaA_Ciudad_Departamento;
    }


    /**
     * Sets the aplicaA_Ciudad_Departamento value for this RadicacionPlano.
     * 
     * @param aplicaA_Ciudad_Departamento
     */
    public void setAplicaA_Ciudad_Departamento(java.lang.String aplicaA_Ciudad_Departamento) {
        this.aplicaA_Ciudad_Departamento = aplicaA_Ciudad_Departamento;
    }


    /**
     * Gets the aplicaA_Ciudad_Nombre value for this RadicacionPlano.
     * 
     * @return aplicaA_Ciudad_Nombre
     */
    public java.lang.String getAplicaA_Ciudad_Nombre() {
        return aplicaA_Ciudad_Nombre;
    }


    /**
     * Sets the aplicaA_Ciudad_Nombre value for this RadicacionPlano.
     * 
     * @param aplicaA_Ciudad_Nombre
     */
    public void setAplicaA_Ciudad_Nombre(java.lang.String aplicaA_Ciudad_Nombre) {
        this.aplicaA_Ciudad_Nombre = aplicaA_Ciudad_Nombre;
    }


    /**
     * Gets the aplicaA_Ciudad_Pais value for this RadicacionPlano.
     * 
     * @return aplicaA_Ciudad_Pais
     */
    public java.lang.String getAplicaA_Ciudad_Pais() {
        return aplicaA_Ciudad_Pais;
    }


    /**
     * Sets the aplicaA_Ciudad_Pais value for this RadicacionPlano.
     * 
     * @param aplicaA_Ciudad_Pais
     */
    public void setAplicaA_Ciudad_Pais(java.lang.String aplicaA_Ciudad_Pais) {
        this.aplicaA_Ciudad_Pais = aplicaA_Ciudad_Pais;
    }


    /**
     * Gets the aplicaA_Direccion value for this RadicacionPlano.
     * 
     * @return aplicaA_Direccion
     */
    public java.lang.String getAplicaA_Direccion() {
        return aplicaA_Direccion;
    }


    /**
     * Sets the aplicaA_Direccion value for this RadicacionPlano.
     * 
     * @param aplicaA_Direccion
     */
    public void setAplicaA_Direccion(java.lang.String aplicaA_Direccion) {
        this.aplicaA_Direccion = aplicaA_Direccion;
    }


    /**
     * Gets the aplicaA_Email value for this RadicacionPlano.
     * 
     * @return aplicaA_Email
     */
    public java.lang.String getAplicaA_Email() {
        return aplicaA_Email;
    }


    /**
     * Sets the aplicaA_Email value for this RadicacionPlano.
     * 
     * @param aplicaA_Email
     */
    public void setAplicaA_Email(java.lang.String aplicaA_Email) {
        this.aplicaA_Email = aplicaA_Email;
    }


    /**
     * Gets the aplicaA_Identificacion value for this RadicacionPlano.
     * 
     * @return aplicaA_Identificacion
     */
    public java.lang.String getAplicaA_Identificacion() {
        return aplicaA_Identificacion;
    }


    /**
     * Sets the aplicaA_Identificacion value for this RadicacionPlano.
     * 
     * @param aplicaA_Identificacion
     */
    public void setAplicaA_Identificacion(java.lang.String aplicaA_Identificacion) {
        this.aplicaA_Identificacion = aplicaA_Identificacion;
    }


    /**
     * Gets the aplicaA_Nombre value for this RadicacionPlano.
     * 
     * @return aplicaA_Nombre
     */
    public java.lang.String getAplicaA_Nombre() {
        return aplicaA_Nombre;
    }


    /**
     * Sets the aplicaA_Nombre value for this RadicacionPlano.
     * 
     * @param aplicaA_Nombre
     */
    public void setAplicaA_Nombre(java.lang.String aplicaA_Nombre) {
        this.aplicaA_Nombre = aplicaA_Nombre;
    }


    /**
     * Gets the aplicaA_Telefono value for this RadicacionPlano.
     * 
     * @return aplicaA_Telefono
     */
    public java.lang.String getAplicaA_Telefono() {
        return aplicaA_Telefono;
    }


    /**
     * Sets the aplicaA_Telefono value for this RadicacionPlano.
     * 
     * @param aplicaA_Telefono
     */
    public void setAplicaA_Telefono(java.lang.String aplicaA_Telefono) {
        this.aplicaA_Telefono = aplicaA_Telefono;
    }


    /**
     * Gets the aplicaA_TipoIdentificacion_Id value for this RadicacionPlano.
     * 
     * @return aplicaA_TipoIdentificacion_Id
     */
    public java.lang.Long getAplicaA_TipoIdentificacion_Id() {
        return aplicaA_TipoIdentificacion_Id;
    }


    /**
     * Sets the aplicaA_TipoIdentificacion_Id value for this RadicacionPlano.
     * 
     * @param aplicaA_TipoIdentificacion_Id
     */
    public void setAplicaA_TipoIdentificacion_Id(java.lang.Long aplicaA_TipoIdentificacion_Id) {
        this.aplicaA_TipoIdentificacion_Id = aplicaA_TipoIdentificacion_Id;
    }


    /**
     * Gets the aplicaA_TipoIdentificacion_Nombre value for this RadicacionPlano.
     * 
     * @return aplicaA_TipoIdentificacion_Nombre
     */
    public java.lang.String getAplicaA_TipoIdentificacion_Nombre() {
        return aplicaA_TipoIdentificacion_Nombre;
    }


    /**
     * Sets the aplicaA_TipoIdentificacion_Nombre value for this RadicacionPlano.
     * 
     * @param aplicaA_TipoIdentificacion_Nombre
     */
    public void setAplicaA_TipoIdentificacion_Nombre(java.lang.String aplicaA_TipoIdentificacion_Nombre) {
        this.aplicaA_TipoIdentificacion_Nombre = aplicaA_TipoIdentificacion_Nombre;
    }


    /**
     * Gets the corresponsal_Dependencia_Id value for this RadicacionPlano.
     * 
     * @return corresponsal_Dependencia_Id
     */
    public java.lang.Long getCorresponsal_Dependencia_Id() {
        return corresponsal_Dependencia_Id;
    }


    /**
     * Sets the corresponsal_Dependencia_Id value for this RadicacionPlano.
     * 
     * @param corresponsal_Dependencia_Id
     */
    public void setCorresponsal_Dependencia_Id(java.lang.Long corresponsal_Dependencia_Id) {
        this.corresponsal_Dependencia_Id = corresponsal_Dependencia_Id;
    }


    /**
     * Gets the corresponsal_Dependencia_Nombre value for this RadicacionPlano.
     * 
     * @return corresponsal_Dependencia_Nombre
     */
    public java.lang.String getCorresponsal_Dependencia_Nombre() {
        return corresponsal_Dependencia_Nombre;
    }


    /**
     * Sets the corresponsal_Dependencia_Nombre value for this RadicacionPlano.
     * 
     * @param corresponsal_Dependencia_Nombre
     */
    public void setCorresponsal_Dependencia_Nombre(java.lang.String corresponsal_Dependencia_Nombre) {
        this.corresponsal_Dependencia_Nombre = corresponsal_Dependencia_Nombre;
    }


    /**
     * Gets the corresponsal_Particular_Ciudad_Codigo_CiudadCodigo value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Ciudad_Codigo_CiudadCodigo
     */
    public java.lang.Long getCorresponsal_Particular_Ciudad_Codigo_CiudadCodigo() {
        return corresponsal_Particular_Ciudad_Codigo_CiudadCodigo;
    }


    /**
     * Sets the corresponsal_Particular_Ciudad_Codigo_CiudadCodigo value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Ciudad_Codigo_CiudadCodigo
     */
    public void setCorresponsal_Particular_Ciudad_Codigo_CiudadCodigo(java.lang.Long corresponsal_Particular_Ciudad_Codigo_CiudadCodigo) {
        this.corresponsal_Particular_Ciudad_Codigo_CiudadCodigo = corresponsal_Particular_Ciudad_Codigo_CiudadCodigo;
    }


    /**
     * Gets the corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo
     */
    public java.lang.Long getCorresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo() {
        return corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo;
    }


    /**
     * Sets the corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo
     */
    public void setCorresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo(java.lang.Long corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo) {
        this.corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo = corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo;
    }


    /**
     * Gets the corresponsal_Particular_Ciudad_Codigo_PaisCodigo value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Ciudad_Codigo_PaisCodigo
     */
    public java.lang.Long getCorresponsal_Particular_Ciudad_Codigo_PaisCodigo() {
        return corresponsal_Particular_Ciudad_Codigo_PaisCodigo;
    }


    /**
     * Sets the corresponsal_Particular_Ciudad_Codigo_PaisCodigo value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Ciudad_Codigo_PaisCodigo
     */
    public void setCorresponsal_Particular_Ciudad_Codigo_PaisCodigo(java.lang.Long corresponsal_Particular_Ciudad_Codigo_PaisCodigo) {
        this.corresponsal_Particular_Ciudad_Codigo_PaisCodigo = corresponsal_Particular_Ciudad_Codigo_PaisCodigo;
    }


    /**
     * Gets the corresponsal_Particular_Ciudad_Departamento value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Ciudad_Departamento
     */
    public java.lang.String getCorresponsal_Particular_Ciudad_Departamento() {
        return corresponsal_Particular_Ciudad_Departamento;
    }


    /**
     * Sets the corresponsal_Particular_Ciudad_Departamento value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Ciudad_Departamento
     */
    public void setCorresponsal_Particular_Ciudad_Departamento(java.lang.String corresponsal_Particular_Ciudad_Departamento) {
        this.corresponsal_Particular_Ciudad_Departamento = corresponsal_Particular_Ciudad_Departamento;
    }


    /**
     * Gets the corresponsal_Particular_Ciudad_Nombre value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Ciudad_Nombre
     */
    public java.lang.String getCorresponsal_Particular_Ciudad_Nombre() {
        return corresponsal_Particular_Ciudad_Nombre;
    }


    /**
     * Sets the corresponsal_Particular_Ciudad_Nombre value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Ciudad_Nombre
     */
    public void setCorresponsal_Particular_Ciudad_Nombre(java.lang.String corresponsal_Particular_Ciudad_Nombre) {
        this.corresponsal_Particular_Ciudad_Nombre = corresponsal_Particular_Ciudad_Nombre;
    }


    /**
     * Gets the corresponsal_Particular_Ciudad_Pais value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Ciudad_Pais
     */
    public java.lang.String getCorresponsal_Particular_Ciudad_Pais() {
        return corresponsal_Particular_Ciudad_Pais;
    }


    /**
     * Sets the corresponsal_Particular_Ciudad_Pais value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Ciudad_Pais
     */
    public void setCorresponsal_Particular_Ciudad_Pais(java.lang.String corresponsal_Particular_Ciudad_Pais) {
        this.corresponsal_Particular_Ciudad_Pais = corresponsal_Particular_Ciudad_Pais;
    }


    /**
     * Gets the corresponsal_Particular_Direccion value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Direccion
     */
    public java.lang.String getCorresponsal_Particular_Direccion() {
        return corresponsal_Particular_Direccion;
    }


    /**
     * Sets the corresponsal_Particular_Direccion value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Direccion
     */
    public void setCorresponsal_Particular_Direccion(java.lang.String corresponsal_Particular_Direccion) {
        this.corresponsal_Particular_Direccion = corresponsal_Particular_Direccion;
    }


    /**
     * Gets the corresponsal_Particular_Email value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Email
     */
    public java.lang.String getCorresponsal_Particular_Email() {
        return corresponsal_Particular_Email;
    }


    /**
     * Sets the corresponsal_Particular_Email value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Email
     */
    public void setCorresponsal_Particular_Email(java.lang.String corresponsal_Particular_Email) {
        this.corresponsal_Particular_Email = corresponsal_Particular_Email;
    }


    /**
     * Gets the corresponsal_Particular_Identificacion value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Identificacion
     */
    public java.lang.String getCorresponsal_Particular_Identificacion() {
        return corresponsal_Particular_Identificacion;
    }


    /**
     * Sets the corresponsal_Particular_Identificacion value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Identificacion
     */
    public void setCorresponsal_Particular_Identificacion(java.lang.String corresponsal_Particular_Identificacion) {
        this.corresponsal_Particular_Identificacion = corresponsal_Particular_Identificacion;
    }


    /**
     * Gets the corresponsal_Particular_Nombre value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Nombre
     */
    public java.lang.String getCorresponsal_Particular_Nombre() {
        return corresponsal_Particular_Nombre;
    }


    /**
     * Sets the corresponsal_Particular_Nombre value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Nombre
     */
    public void setCorresponsal_Particular_Nombre(java.lang.String corresponsal_Particular_Nombre) {
        this.corresponsal_Particular_Nombre = corresponsal_Particular_Nombre;
    }


    /**
     * Gets the corresponsal_Particular_Telefono value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_Telefono
     */
    public java.lang.String getCorresponsal_Particular_Telefono() {
        return corresponsal_Particular_Telefono;
    }


    /**
     * Sets the corresponsal_Particular_Telefono value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_Telefono
     */
    public void setCorresponsal_Particular_Telefono(java.lang.String corresponsal_Particular_Telefono) {
        this.corresponsal_Particular_Telefono = corresponsal_Particular_Telefono;
    }


    /**
     * Gets the corresponsal_Particular_TipoIdentificacion_Id value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_TipoIdentificacion_Id
     */
    public java.lang.Long getCorresponsal_Particular_TipoIdentificacion_Id() {
        return corresponsal_Particular_TipoIdentificacion_Id;
    }


    /**
     * Sets the corresponsal_Particular_TipoIdentificacion_Id value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_TipoIdentificacion_Id
     */
    public void setCorresponsal_Particular_TipoIdentificacion_Id(java.lang.Long corresponsal_Particular_TipoIdentificacion_Id) {
        this.corresponsal_Particular_TipoIdentificacion_Id = corresponsal_Particular_TipoIdentificacion_Id;
    }


    /**
     * Gets the corresponsal_Particular_TipoIdentificacion_Nombre value for this RadicacionPlano.
     * 
     * @return corresponsal_Particular_TipoIdentificacion_Nombre
     */
    public java.lang.String getCorresponsal_Particular_TipoIdentificacion_Nombre() {
        return corresponsal_Particular_TipoIdentificacion_Nombre;
    }


    /**
     * Sets the corresponsal_Particular_TipoIdentificacion_Nombre value for this RadicacionPlano.
     * 
     * @param corresponsal_Particular_TipoIdentificacion_Nombre
     */
    public void setCorresponsal_Particular_TipoIdentificacion_Nombre(java.lang.String corresponsal_Particular_TipoIdentificacion_Nombre) {
        this.corresponsal_Particular_TipoIdentificacion_Nombre = corresponsal_Particular_TipoIdentificacion_Nombre;
    }


    /**
     * Gets the corresponsal_Tipo value for this RadicacionPlano.
     * 
     * @return corresponsal_Tipo
     */
    public java.lang.String getCorresponsal_Tipo() {
        return corresponsal_Tipo;
    }


    /**
     * Sets the corresponsal_Tipo value for this RadicacionPlano.
     * 
     * @param corresponsal_Tipo
     */
    public void setCorresponsal_Tipo(java.lang.String corresponsal_Tipo) {
        this.corresponsal_Tipo = corresponsal_Tipo;
    }


    /**
     * Gets the dependenciaAsignada_Dependencia_Id value for this RadicacionPlano.
     * 
     * @return dependenciaAsignada_Dependencia_Id
     */
    public java.lang.Long getDependenciaAsignada_Dependencia_Id() {
        return dependenciaAsignada_Dependencia_Id;
    }


    /**
     * Sets the dependenciaAsignada_Dependencia_Id value for this RadicacionPlano.
     * 
     * @param dependenciaAsignada_Dependencia_Id
     */
    public void setDependenciaAsignada_Dependencia_Id(java.lang.Long dependenciaAsignada_Dependencia_Id) {
        this.dependenciaAsignada_Dependencia_Id = dependenciaAsignada_Dependencia_Id;
    }


    /**
     * Gets the dependenciaAsignada_Dependencia_Nombre value for this RadicacionPlano.
     * 
     * @return dependenciaAsignada_Dependencia_Nombre
     */
    public java.lang.String getDependenciaAsignada_Dependencia_Nombre() {
        return dependenciaAsignada_Dependencia_Nombre;
    }


    /**
     * Sets the dependenciaAsignada_Dependencia_Nombre value for this RadicacionPlano.
     * 
     * @param dependenciaAsignada_Dependencia_Nombre
     */
    public void setDependenciaAsignada_Dependencia_Nombre(java.lang.String dependenciaAsignada_Dependencia_Nombre) {
        this.dependenciaAsignada_Dependencia_Nombre = dependenciaAsignada_Dependencia_Nombre;
    }


    /**
     * Gets the dependencia_Id value for this RadicacionPlano.
     * 
     * @return dependencia_Id
     */
    public java.lang.Long getDependencia_Id() {
        return dependencia_Id;
    }


    /**
     * Sets the dependencia_Id value for this RadicacionPlano.
     * 
     * @param dependencia_Id
     */
    public void setDependencia_Id(java.lang.Long dependencia_Id) {
        this.dependencia_Id = dependencia_Id;
    }


    /**
     * Gets the dependencia_Nombre value for this RadicacionPlano.
     * 
     * @return dependencia_Nombre
     */
    public java.lang.String getDependencia_Nombre() {
        return dependencia_Nombre;
    }


    /**
     * Sets the dependencia_Nombre value for this RadicacionPlano.
     * 
     * @param dependencia_Nombre
     */
    public void setDependencia_Nombre(java.lang.String dependencia_Nombre) {
        this.dependencia_Nombre = dependencia_Nombre;
    }


    /**
     * Gets the documentoPrincipal value for this RadicacionPlano.
     * 
     * @return documentoPrincipal
     */
    public java.lang.String getDocumentoPrincipal() {
        return documentoPrincipal;
    }


    /**
     * Sets the documentoPrincipal value for this RadicacionPlano.
     * 
     * @param documentoPrincipal
     */
    public void setDocumentoPrincipal(java.lang.String documentoPrincipal) {
        this.documentoPrincipal = documentoPrincipal;
    }


    /**
     * Gets the documentosAnexos value for this RadicacionPlano.
     * 
     * @return documentosAnexos
     */
    public java.lang.String[] getDocumentosAnexos() {
        return documentosAnexos;
    }


    /**
     * Sets the documentosAnexos value for this RadicacionPlano.
     * 
     * @param documentosAnexos
     */
    public void setDocumentosAnexos(java.lang.String[] documentosAnexos) {
        this.documentosAnexos = documentosAnexos;
    }


    /**
     * Gets the entregaFisica value for this RadicacionPlano.
     * 
     * @return entregaFisica
     */
    public java.lang.Boolean getEntregaFisica() {
        return entregaFisica;
    }


    /**
     * Sets the entregaFisica value for this RadicacionPlano.
     * 
     * @param entregaFisica
     */
    public void setEntregaFisica(java.lang.Boolean entregaFisica) {
        this.entregaFisica = entregaFisica;
    }


    /**
     * Gets the estado value for this RadicacionPlano.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RadicacionPlano.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the fecha value for this RadicacionPlano.
     * 
     * @return fecha
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this RadicacionPlano.
     * 
     * @param fecha
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the folios value for this RadicacionPlano.
     * 
     * @return folios
     */
    public java.lang.Integer getFolios() {
        return folios;
    }


    /**
     * Sets the folios value for this RadicacionPlano.
     * 
     * @param folios
     */
    public void setFolios(java.lang.Integer folios) {
        this.folios = folios;
    }


    /**
     * Gets the funcionarioAsignado_Apellido value for this RadicacionPlano.
     * 
     * @return funcionarioAsignado_Apellido
     */
    public java.lang.String getFuncionarioAsignado_Apellido() {
        return funcionarioAsignado_Apellido;
    }


    /**
     * Sets the funcionarioAsignado_Apellido value for this RadicacionPlano.
     * 
     * @param funcionarioAsignado_Apellido
     */
    public void setFuncionarioAsignado_Apellido(java.lang.String funcionarioAsignado_Apellido) {
        this.funcionarioAsignado_Apellido = funcionarioAsignado_Apellido;
    }


    /**
     * Gets the funcionarioAsignado_Cargo value for this RadicacionPlano.
     * 
     * @return funcionarioAsignado_Cargo
     */
    public java.lang.String getFuncionarioAsignado_Cargo() {
        return funcionarioAsignado_Cargo;
    }


    /**
     * Sets the funcionarioAsignado_Cargo value for this RadicacionPlano.
     * 
     * @param funcionarioAsignado_Cargo
     */
    public void setFuncionarioAsignado_Cargo(java.lang.String funcionarioAsignado_Cargo) {
        this.funcionarioAsignado_Cargo = funcionarioAsignado_Cargo;
    }


    /**
     * Gets the funcionarioAsignado_Codigo value for this RadicacionPlano.
     * 
     * @return funcionarioAsignado_Codigo
     */
    public java.lang.String getFuncionarioAsignado_Codigo() {
        return funcionarioAsignado_Codigo;
    }


    /**
     * Sets the funcionarioAsignado_Codigo value for this RadicacionPlano.
     * 
     * @param funcionarioAsignado_Codigo
     */
    public void setFuncionarioAsignado_Codigo(java.lang.String funcionarioAsignado_Codigo) {
        this.funcionarioAsignado_Codigo = funcionarioAsignado_Codigo;
    }


    /**
     * Gets the funcionarioAsignado_Id value for this RadicacionPlano.
     * 
     * @return funcionarioAsignado_Id
     */
    public java.lang.String getFuncionarioAsignado_Id() {
        return funcionarioAsignado_Id;
    }


    /**
     * Sets the funcionarioAsignado_Id value for this RadicacionPlano.
     * 
     * @param funcionarioAsignado_Id
     */
    public void setFuncionarioAsignado_Id(java.lang.String funcionarioAsignado_Id) {
        this.funcionarioAsignado_Id = funcionarioAsignado_Id;
    }


    /**
     * Gets the funcionarioAsignado_Nemotecnico value for this RadicacionPlano.
     * 
     * @return funcionarioAsignado_Nemotecnico
     */
    public java.lang.String getFuncionarioAsignado_Nemotecnico() {
        return funcionarioAsignado_Nemotecnico;
    }


    /**
     * Sets the funcionarioAsignado_Nemotecnico value for this RadicacionPlano.
     * 
     * @param funcionarioAsignado_Nemotecnico
     */
    public void setFuncionarioAsignado_Nemotecnico(java.lang.String funcionarioAsignado_Nemotecnico) {
        this.funcionarioAsignado_Nemotecnico = funcionarioAsignado_Nemotecnico;
    }


    /**
     * Gets the funcionarioAsignado_Nombre value for this RadicacionPlano.
     * 
     * @return funcionarioAsignado_Nombre
     */
    public java.lang.String getFuncionarioAsignado_Nombre() {
        return funcionarioAsignado_Nombre;
    }


    /**
     * Sets the funcionarioAsignado_Nombre value for this RadicacionPlano.
     * 
     * @param funcionarioAsignado_Nombre
     */
    public void setFuncionarioAsignado_Nombre(java.lang.String funcionarioAsignado_Nombre) {
        this.funcionarioAsignado_Nombre = funcionarioAsignado_Nombre;
    }


    /**
     * Gets the funcionario_Apellido value for this RadicacionPlano.
     * 
     * @return funcionario_Apellido
     */
    public java.lang.String getFuncionario_Apellido() {
        return funcionario_Apellido;
    }


    /**
     * Sets the funcionario_Apellido value for this RadicacionPlano.
     * 
     * @param funcionario_Apellido
     */
    public void setFuncionario_Apellido(java.lang.String funcionario_Apellido) {
        this.funcionario_Apellido = funcionario_Apellido;
    }


    /**
     * Gets the funcionario_Cargo value for this RadicacionPlano.
     * 
     * @return funcionario_Cargo
     */
    public java.lang.String getFuncionario_Cargo() {
        return funcionario_Cargo;
    }


    /**
     * Sets the funcionario_Cargo value for this RadicacionPlano.
     * 
     * @param funcionario_Cargo
     */
    public void setFuncionario_Cargo(java.lang.String funcionario_Cargo) {
        this.funcionario_Cargo = funcionario_Cargo;
    }


    /**
     * Gets the funcionario_Codigo value for this RadicacionPlano.
     * 
     * @return funcionario_Codigo
     */
    public java.lang.String getFuncionario_Codigo() {
        return funcionario_Codigo;
    }


    /**
     * Sets the funcionario_Codigo value for this RadicacionPlano.
     * 
     * @param funcionario_Codigo
     */
    public void setFuncionario_Codigo(java.lang.String funcionario_Codigo) {
        this.funcionario_Codigo = funcionario_Codigo;
    }


    /**
     * Gets the funcionario_Id value for this RadicacionPlano.
     * 
     * @return funcionario_Id
     */
    public java.lang.String getFuncionario_Id() {
        return funcionario_Id;
    }


    /**
     * Sets the funcionario_Id value for this RadicacionPlano.
     * 
     * @param funcionario_Id
     */
    public void setFuncionario_Id(java.lang.String funcionario_Id) {
        this.funcionario_Id = funcionario_Id;
    }


    /**
     * Gets the funcionario_Nemotecnico value for this RadicacionPlano.
     * 
     * @return funcionario_Nemotecnico
     */
    public java.lang.String getFuncionario_Nemotecnico() {
        return funcionario_Nemotecnico;
    }


    /**
     * Sets the funcionario_Nemotecnico value for this RadicacionPlano.
     * 
     * @param funcionario_Nemotecnico
     */
    public void setFuncionario_Nemotecnico(java.lang.String funcionario_Nemotecnico) {
        this.funcionario_Nemotecnico = funcionario_Nemotecnico;
    }


    /**
     * Gets the funcionario_Nombre value for this RadicacionPlano.
     * 
     * @return funcionario_Nombre
     */
    public java.lang.String getFuncionario_Nombre() {
        return funcionario_Nombre;
    }


    /**
     * Sets the funcionario_Nombre value for this RadicacionPlano.
     * 
     * @param funcionario_Nombre
     */
    public void setFuncionario_Nombre(java.lang.String funcionario_Nombre) {
        this.funcionario_Nombre = funcionario_Nombre;
    }


    /**
     * Gets the medioDeEnvio_Codigo value for this RadicacionPlano.
     * 
     * @return medioDeEnvio_Codigo
     */
    public java.lang.Long getMedioDeEnvio_Codigo() {
        return medioDeEnvio_Codigo;
    }


    /**
     * Sets the medioDeEnvio_Codigo value for this RadicacionPlano.
     * 
     * @param medioDeEnvio_Codigo
     */
    public void setMedioDeEnvio_Codigo(java.lang.Long medioDeEnvio_Codigo) {
        this.medioDeEnvio_Codigo = medioDeEnvio_Codigo;
    }


    /**
     * Gets the medioDeEnvio_Id value for this RadicacionPlano.
     * 
     * @return medioDeEnvio_Id
     */
    public java.lang.Long getMedioDeEnvio_Id() {
        return medioDeEnvio_Id;
    }


    /**
     * Sets the medioDeEnvio_Id value for this RadicacionPlano.
     * 
     * @param medioDeEnvio_Id
     */
    public void setMedioDeEnvio_Id(java.lang.Long medioDeEnvio_Id) {
        this.medioDeEnvio_Id = medioDeEnvio_Id;
    }


    /**
     * Gets the medioDeEnvio_Nombre value for this RadicacionPlano.
     * 
     * @return medioDeEnvio_Nombre
     */
    public java.lang.String getMedioDeEnvio_Nombre() {
        return medioDeEnvio_Nombre;
    }


    /**
     * Sets the medioDeEnvio_Nombre value for this RadicacionPlano.
     * 
     * @param medioDeEnvio_Nombre
     */
    public void setMedioDeEnvio_Nombre(java.lang.String medioDeEnvio_Nombre) {
        this.medioDeEnvio_Nombre = medioDeEnvio_Nombre;
    }


    /**
     * Gets the mensaje value for this RadicacionPlano.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this RadicacionPlano.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }


    /**
     * Gets the multa value for this RadicacionPlano.
     * 
     * @return multa
     */
    public java.math.BigDecimal getMulta() {
        return multa;
    }


    /**
     * Sets the multa value for this RadicacionPlano.
     * 
     * @param multa
     */
    public void setMulta(java.math.BigDecimal multa) {
        this.multa = multa;
    }


    /**
     * Gets the numero value for this RadicacionPlano.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this RadicacionPlano.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the radicacionAnteriorNumero value for this RadicacionPlano.
     * 
     * @return radicacionAnteriorNumero
     */
    public java.lang.String getRadicacionAnteriorNumero() {
        return radicacionAnteriorNumero;
    }


    /**
     * Sets the radicacionAnteriorNumero value for this RadicacionPlano.
     * 
     * @param radicacionAnteriorNumero
     */
    public void setRadicacionAnteriorNumero(java.lang.String radicacionAnteriorNumero) {
        this.radicacionAnteriorNumero = radicacionAnteriorNumero;
    }


    /**
     * Gets the referenciaExterna value for this RadicacionPlano.
     * 
     * @return referenciaExterna
     */
    public java.lang.String getReferenciaExterna() {
        return referenciaExterna;
    }


    /**
     * Sets the referenciaExterna value for this RadicacionPlano.
     * 
     * @param referenciaExterna
     */
    public void setReferenciaExterna(java.lang.String referenciaExterna) {
        this.referenciaExterna = referenciaExterna;
    }


    /**
     * Gets the serie_Codigo value for this RadicacionPlano.
     * 
     * @return serie_Codigo
     */
    public java.lang.String getSerie_Codigo() {
        return serie_Codigo;
    }


    /**
     * Sets the serie_Codigo value for this RadicacionPlano.
     * 
     * @param serie_Codigo
     */
    public void setSerie_Codigo(java.lang.String serie_Codigo) {
        this.serie_Codigo = serie_Codigo;
    }


    /**
     * Gets the serie_Nombre value for this RadicacionPlano.
     * 
     * @return serie_Nombre
     */
    public java.lang.String getSerie_Nombre() {
        return serie_Nombre;
    }


    /**
     * Sets the serie_Nombre value for this RadicacionPlano.
     * 
     * @param serie_Nombre
     */
    public void setSerie_Nombre(java.lang.String serie_Nombre) {
        this.serie_Nombre = serie_Nombre;
    }


    /**
     * Gets the sub_Serie_Codigo value for this RadicacionPlano.
     * 
     * @return sub_Serie_Codigo
     */
    public java.lang.String getSub_Serie_Codigo() {
        return sub_Serie_Codigo;
    }


    /**
     * Sets the sub_Serie_Codigo value for this RadicacionPlano.
     * 
     * @param sub_Serie_Codigo
     */
    public void setSub_Serie_Codigo(java.lang.String sub_Serie_Codigo) {
        this.sub_Serie_Codigo = sub_Serie_Codigo;
    }


    /**
     * Gets the sub_Serie_Nombre value for this RadicacionPlano.
     * 
     * @return sub_Serie_Nombre
     */
    public java.lang.String getSub_Serie_Nombre() {
        return sub_Serie_Nombre;
    }


    /**
     * Sets the sub_Serie_Nombre value for this RadicacionPlano.
     * 
     * @param sub_Serie_Nombre
     */
    public void setSub_Serie_Nombre(java.lang.String sub_Serie_Nombre) {
        this.sub_Serie_Nombre = sub_Serie_Nombre;
    }


    /**
     * Gets the terminoDias value for this RadicacionPlano.
     * 
     * @return terminoDias
     */
    public java.lang.Integer getTerminoDias() {
        return terminoDias;
    }


    /**
     * Sets the terminoDias value for this RadicacionPlano.
     * 
     * @param terminoDias
     */
    public void setTerminoDias(java.lang.Integer terminoDias) {
        this.terminoDias = terminoDias;
    }


    /**
     * Gets the terminoFecha value for this RadicacionPlano.
     * 
     * @return terminoFecha
     */
    public java.util.Calendar getTerminoFecha() {
        return terminoFecha;
    }


    /**
     * Sets the terminoFecha value for this RadicacionPlano.
     * 
     * @param terminoFecha
     */
    public void setTerminoFecha(java.util.Calendar terminoFecha) {
        this.terminoFecha = terminoFecha;
    }


    /**
     * Gets the tipo value for this RadicacionPlano.
     * 
     * @return tipo
     */
    public java.lang.String getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this RadicacionPlano.
     * 
     * @param tipo
     */
    public void setTipo(java.lang.String tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the tipoCuaderno_Codigo value for this RadicacionPlano.
     * 
     * @return tipoCuaderno_Codigo
     */
    public java.lang.String getTipoCuaderno_Codigo() {
        return tipoCuaderno_Codigo;
    }


    /**
     * Sets the tipoCuaderno_Codigo value for this RadicacionPlano.
     * 
     * @param tipoCuaderno_Codigo
     */
    public void setTipoCuaderno_Codigo(java.lang.String tipoCuaderno_Codigo) {
        this.tipoCuaderno_Codigo = tipoCuaderno_Codigo;
    }


    /**
     * Gets the tipoCuaderno_Id value for this RadicacionPlano.
     * 
     * @return tipoCuaderno_Id
     */
    public java.lang.Long getTipoCuaderno_Id() {
        return tipoCuaderno_Id;
    }


    /**
     * Sets the tipoCuaderno_Id value for this RadicacionPlano.
     * 
     * @param tipoCuaderno_Id
     */
    public void setTipoCuaderno_Id(java.lang.Long tipoCuaderno_Id) {
        this.tipoCuaderno_Id = tipoCuaderno_Id;
    }


    /**
     * Gets the tipoCuaderno_Nombre value for this RadicacionPlano.
     * 
     * @return tipoCuaderno_Nombre
     */
    public java.lang.String getTipoCuaderno_Nombre() {
        return tipoCuaderno_Nombre;
    }


    /**
     * Sets the tipoCuaderno_Nombre value for this RadicacionPlano.
     * 
     * @param tipoCuaderno_Nombre
     */
    public void setTipoCuaderno_Nombre(java.lang.String tipoCuaderno_Nombre) {
        this.tipoCuaderno_Nombre = tipoCuaderno_Nombre;
    }


    /**
     * Gets the tipoDocumentalConsecutivo value for this RadicacionPlano.
     * 
     * @return tipoDocumentalConsecutivo
     */
    public java.lang.String getTipoDocumentalConsecutivo() {
        return tipoDocumentalConsecutivo;
    }


    /**
     * Sets the tipoDocumentalConsecutivo value for this RadicacionPlano.
     * 
     * @param tipoDocumentalConsecutivo
     */
    public void setTipoDocumentalConsecutivo(java.lang.String tipoDocumentalConsecutivo) {
        this.tipoDocumentalConsecutivo = tipoDocumentalConsecutivo;
    }


    /**
     * Gets the tipoDocumental_Codigo value for this RadicacionPlano.
     * 
     * @return tipoDocumental_Codigo
     */
    public java.lang.String getTipoDocumental_Codigo() {
        return tipoDocumental_Codigo;
    }


    /**
     * Sets the tipoDocumental_Codigo value for this RadicacionPlano.
     * 
     * @param tipoDocumental_Codigo
     */
    public void setTipoDocumental_Codigo(java.lang.String tipoDocumental_Codigo) {
        this.tipoDocumental_Codigo = tipoDocumental_Codigo;
    }


    /**
     * Gets the tipoDocumental_Id value for this RadicacionPlano.
     * 
     * @return tipoDocumental_Id
     */
    public java.lang.Long getTipoDocumental_Id() {
        return tipoDocumental_Id;
    }


    /**
     * Sets the tipoDocumental_Id value for this RadicacionPlano.
     * 
     * @param tipoDocumental_Id
     */
    public void setTipoDocumental_Id(java.lang.Long tipoDocumental_Id) {
        this.tipoDocumental_Id = tipoDocumental_Id;
    }


    /**
     * Gets the tipoDocumental_Nombre value for this RadicacionPlano.
     * 
     * @return tipoDocumental_Nombre
     */
    public java.lang.String getTipoDocumental_Nombre() {
        return tipoDocumental_Nombre;
    }


    /**
     * Sets the tipoDocumental_Nombre value for this RadicacionPlano.
     * 
     * @param tipoDocumental_Nombre
     */
    public void setTipoDocumental_Nombre(java.lang.String tipoDocumental_Nombre) {
        this.tipoDocumental_Nombre = tipoDocumental_Nombre;
    }


    /**
     * Gets the tipoSeguridad_Codigo value for this RadicacionPlano.
     * 
     * @return tipoSeguridad_Codigo
     */
    public java.lang.String getTipoSeguridad_Codigo() {
        return tipoSeguridad_Codigo;
    }


    /**
     * Sets the tipoSeguridad_Codigo value for this RadicacionPlano.
     * 
     * @param tipoSeguridad_Codigo
     */
    public void setTipoSeguridad_Codigo(java.lang.String tipoSeguridad_Codigo) {
        this.tipoSeguridad_Codigo = tipoSeguridad_Codigo;
    }


    /**
     * Gets the tipoSeguridad_Id value for this RadicacionPlano.
     * 
     * @return tipoSeguridad_Id
     */
    public java.lang.Long getTipoSeguridad_Id() {
        return tipoSeguridad_Id;
    }


    /**
     * Sets the tipoSeguridad_Id value for this RadicacionPlano.
     * 
     * @param tipoSeguridad_Id
     */
    public void setTipoSeguridad_Id(java.lang.Long tipoSeguridad_Id) {
        this.tipoSeguridad_Id = tipoSeguridad_Id;
    }


    /**
     * Gets the tipoSeguridad_Nombre value for this RadicacionPlano.
     * 
     * @return tipoSeguridad_Nombre
     */
    public java.lang.String getTipoSeguridad_Nombre() {
        return tipoSeguridad_Nombre;
    }


    /**
     * Sets the tipoSeguridad_Nombre value for this RadicacionPlano.
     * 
     * @param tipoSeguridad_Nombre
     */
    public void setTipoSeguridad_Nombre(java.lang.String tipoSeguridad_Nombre) {
        this.tipoSeguridad_Nombre = tipoSeguridad_Nombre;
    }


    /**
     * Gets the tramite_Codigo value for this RadicacionPlano.
     * 
     * @return tramite_Codigo
     */
    public java.lang.Long getTramite_Codigo() {
        return tramite_Codigo;
    }


    /**
     * Sets the tramite_Codigo value for this RadicacionPlano.
     * 
     * @param tramite_Codigo
     */
    public void setTramite_Codigo(java.lang.Long tramite_Codigo) {
        this.tramite_Codigo = tramite_Codigo;
    }


    /**
     * Gets the tramite_Id value for this RadicacionPlano.
     * 
     * @return tramite_Id
     */
    public java.lang.Long getTramite_Id() {
        return tramite_Id;
    }


    /**
     * Sets the tramite_Id value for this RadicacionPlano.
     * 
     * @param tramite_Id
     */
    public void setTramite_Id(java.lang.Long tramite_Id) {
        this.tramite_Id = tramite_Id;
    }


    /**
     * Gets the tramite_Nombre value for this RadicacionPlano.
     * 
     * @return tramite_Nombre
     */
    public java.lang.String getTramite_Nombre() {
        return tramite_Nombre;
    }


    /**
     * Sets the tramite_Nombre value for this RadicacionPlano.
     * 
     * @param tramite_Nombre
     */
    public void setTramite_Nombre(java.lang.String tramite_Nombre) {
        this.tramite_Nombre = tramite_Nombre;
    }


    /**
     * Gets the tramite_Proceso_Codigo value for this RadicacionPlano.
     * 
     * @return tramite_Proceso_Codigo
     */
    public java.lang.Long getTramite_Proceso_Codigo() {
        return tramite_Proceso_Codigo;
    }


    /**
     * Sets the tramite_Proceso_Codigo value for this RadicacionPlano.
     * 
     * @param tramite_Proceso_Codigo
     */
    public void setTramite_Proceso_Codigo(java.lang.Long tramite_Proceso_Codigo) {
        this.tramite_Proceso_Codigo = tramite_Proceso_Codigo;
    }


    /**
     * Gets the tramite_Proceso_Id value for this RadicacionPlano.
     * 
     * @return tramite_Proceso_Id
     */
    public java.lang.Long getTramite_Proceso_Id() {
        return tramite_Proceso_Id;
    }


    /**
     * Sets the tramite_Proceso_Id value for this RadicacionPlano.
     * 
     * @param tramite_Proceso_Id
     */
    public void setTramite_Proceso_Id(java.lang.Long tramite_Proceso_Id) {
        this.tramite_Proceso_Id = tramite_Proceso_Id;
    }


    /**
     * Gets the tramite_Proceso_Nombre value for this RadicacionPlano.
     * 
     * @return tramite_Proceso_Nombre
     */
    public java.lang.String getTramite_Proceso_Nombre() {
        return tramite_Proceso_Nombre;
    }


    /**
     * Sets the tramite_Proceso_Nombre value for this RadicacionPlano.
     * 
     * @param tramite_Proceso_Nombre
     */
    public void setTramite_Proceso_Nombre(java.lang.String tramite_Proceso_Nombre) {
        this.tramite_Proceso_Nombre = tramite_Proceso_Nombre;
    }


    /**
     * Gets the tramite_Termino_Dias value for this RadicacionPlano.
     * 
     * @return tramite_Termino_Dias
     */
    public java.lang.Integer getTramite_Termino_Dias() {
        return tramite_Termino_Dias;
    }


    /**
     * Sets the tramite_Termino_Dias value for this RadicacionPlano.
     * 
     * @param tramite_Termino_Dias
     */
    public void setTramite_Termino_Dias(java.lang.Integer tramite_Termino_Dias) {
        this.tramite_Termino_Dias = tramite_Termino_Dias;
    }


    /**
     * Gets the tramite_Termino_EsModificable value for this RadicacionPlano.
     * 
     * @return tramite_Termino_EsModificable
     */
    public java.lang.Boolean getTramite_Termino_EsModificable() {
        return tramite_Termino_EsModificable;
    }


    /**
     * Sets the tramite_Termino_EsModificable value for this RadicacionPlano.
     * 
     * @param tramite_Termino_EsModificable
     */
    public void setTramite_Termino_EsModificable(java.lang.Boolean tramite_Termino_EsModificable) {
        this.tramite_Termino_EsModificable = tramite_Termino_EsModificable;
    }


    /**
     * Gets the usuario value for this RadicacionPlano.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this RadicacionPlano.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the codigoRespuesta value for this RadicacionPlano.
     * 
     * @return codigoRespuesta
     */
    public java.lang.Integer getCodigoRespuesta() {
        return codigoRespuesta;
    }


    /**
     * Sets the codigoRespuesta value for this RadicacionPlano.
     * 
     * @param codigoRespuesta
     */
    public void setCodigoRespuesta(java.lang.Integer codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RadicacionPlano)) return false;
        RadicacionPlano other = (RadicacionPlano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.anexosFisicos==null && other.getAnexosFisicos()==null) || 
             (this.anexosFisicos!=null &&
              this.anexosFisicos.equals(other.getAnexosFisicos()))) &&
            ((this.aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo==null && other.getAplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo()==null) || 
             (this.aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo!=null &&
              this.aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo.equals(other.getAplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo()))) &&
            ((this.aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo==null && other.getAplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo()==null) || 
             (this.aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo!=null &&
              this.aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo.equals(other.getAplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo()))) &&
            ((this.aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo==null && other.getAplicaA_Ciudad_Codigo_CiudadId_PaisCodigo()==null) || 
             (this.aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo!=null &&
              this.aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo.equals(other.getAplicaA_Ciudad_Codigo_CiudadId_PaisCodigo()))) &&
            ((this.aplicaA_Ciudad_Departamento==null && other.getAplicaA_Ciudad_Departamento()==null) || 
             (this.aplicaA_Ciudad_Departamento!=null &&
              this.aplicaA_Ciudad_Departamento.equals(other.getAplicaA_Ciudad_Departamento()))) &&
            ((this.aplicaA_Ciudad_Nombre==null && other.getAplicaA_Ciudad_Nombre()==null) || 
             (this.aplicaA_Ciudad_Nombre!=null &&
              this.aplicaA_Ciudad_Nombre.equals(other.getAplicaA_Ciudad_Nombre()))) &&
            ((this.aplicaA_Ciudad_Pais==null && other.getAplicaA_Ciudad_Pais()==null) || 
             (this.aplicaA_Ciudad_Pais!=null &&
              this.aplicaA_Ciudad_Pais.equals(other.getAplicaA_Ciudad_Pais()))) &&
            ((this.aplicaA_Direccion==null && other.getAplicaA_Direccion()==null) || 
             (this.aplicaA_Direccion!=null &&
              this.aplicaA_Direccion.equals(other.getAplicaA_Direccion()))) &&
            ((this.aplicaA_Email==null && other.getAplicaA_Email()==null) || 
             (this.aplicaA_Email!=null &&
              this.aplicaA_Email.equals(other.getAplicaA_Email()))) &&
            ((this.aplicaA_Identificacion==null && other.getAplicaA_Identificacion()==null) || 
             (this.aplicaA_Identificacion!=null &&
              this.aplicaA_Identificacion.equals(other.getAplicaA_Identificacion()))) &&
            ((this.aplicaA_Nombre==null && other.getAplicaA_Nombre()==null) || 
             (this.aplicaA_Nombre!=null &&
              this.aplicaA_Nombre.equals(other.getAplicaA_Nombre()))) &&
            ((this.aplicaA_Telefono==null && other.getAplicaA_Telefono()==null) || 
             (this.aplicaA_Telefono!=null &&
              this.aplicaA_Telefono.equals(other.getAplicaA_Telefono()))) &&
            ((this.aplicaA_TipoIdentificacion_Id==null && other.getAplicaA_TipoIdentificacion_Id()==null) || 
             (this.aplicaA_TipoIdentificacion_Id!=null &&
              this.aplicaA_TipoIdentificacion_Id.equals(other.getAplicaA_TipoIdentificacion_Id()))) &&
            ((this.aplicaA_TipoIdentificacion_Nombre==null && other.getAplicaA_TipoIdentificacion_Nombre()==null) || 
             (this.aplicaA_TipoIdentificacion_Nombre!=null &&
              this.aplicaA_TipoIdentificacion_Nombre.equals(other.getAplicaA_TipoIdentificacion_Nombre()))) &&
            ((this.corresponsal_Dependencia_Id==null && other.getCorresponsal_Dependencia_Id()==null) || 
             (this.corresponsal_Dependencia_Id!=null &&
              this.corresponsal_Dependencia_Id.equals(other.getCorresponsal_Dependencia_Id()))) &&
            ((this.corresponsal_Dependencia_Nombre==null && other.getCorresponsal_Dependencia_Nombre()==null) || 
             (this.corresponsal_Dependencia_Nombre!=null &&
              this.corresponsal_Dependencia_Nombre.equals(other.getCorresponsal_Dependencia_Nombre()))) &&
            ((this.corresponsal_Particular_Ciudad_Codigo_CiudadCodigo==null && other.getCorresponsal_Particular_Ciudad_Codigo_CiudadCodigo()==null) || 
             (this.corresponsal_Particular_Ciudad_Codigo_CiudadCodigo!=null &&
              this.corresponsal_Particular_Ciudad_Codigo_CiudadCodigo.equals(other.getCorresponsal_Particular_Ciudad_Codigo_CiudadCodigo()))) &&
            ((this.corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo==null && other.getCorresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo()==null) || 
             (this.corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo!=null &&
              this.corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo.equals(other.getCorresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo()))) &&
            ((this.corresponsal_Particular_Ciudad_Codigo_PaisCodigo==null && other.getCorresponsal_Particular_Ciudad_Codigo_PaisCodigo()==null) || 
             (this.corresponsal_Particular_Ciudad_Codigo_PaisCodigo!=null &&
              this.corresponsal_Particular_Ciudad_Codigo_PaisCodigo.equals(other.getCorresponsal_Particular_Ciudad_Codigo_PaisCodigo()))) &&
            ((this.corresponsal_Particular_Ciudad_Departamento==null && other.getCorresponsal_Particular_Ciudad_Departamento()==null) || 
             (this.corresponsal_Particular_Ciudad_Departamento!=null &&
              this.corresponsal_Particular_Ciudad_Departamento.equals(other.getCorresponsal_Particular_Ciudad_Departamento()))) &&
            ((this.corresponsal_Particular_Ciudad_Nombre==null && other.getCorresponsal_Particular_Ciudad_Nombre()==null) || 
             (this.corresponsal_Particular_Ciudad_Nombre!=null &&
              this.corresponsal_Particular_Ciudad_Nombre.equals(other.getCorresponsal_Particular_Ciudad_Nombre()))) &&
            ((this.corresponsal_Particular_Ciudad_Pais==null && other.getCorresponsal_Particular_Ciudad_Pais()==null) || 
             (this.corresponsal_Particular_Ciudad_Pais!=null &&
              this.corresponsal_Particular_Ciudad_Pais.equals(other.getCorresponsal_Particular_Ciudad_Pais()))) &&
            ((this.corresponsal_Particular_Direccion==null && other.getCorresponsal_Particular_Direccion()==null) || 
             (this.corresponsal_Particular_Direccion!=null &&
              this.corresponsal_Particular_Direccion.equals(other.getCorresponsal_Particular_Direccion()))) &&
            ((this.corresponsal_Particular_Email==null && other.getCorresponsal_Particular_Email()==null) || 
             (this.corresponsal_Particular_Email!=null &&
              this.corresponsal_Particular_Email.equals(other.getCorresponsal_Particular_Email()))) &&
            ((this.corresponsal_Particular_Identificacion==null && other.getCorresponsal_Particular_Identificacion()==null) || 
             (this.corresponsal_Particular_Identificacion!=null &&
              this.corresponsal_Particular_Identificacion.equals(other.getCorresponsal_Particular_Identificacion()))) &&
            ((this.corresponsal_Particular_Nombre==null && other.getCorresponsal_Particular_Nombre()==null) || 
             (this.corresponsal_Particular_Nombre!=null &&
              this.corresponsal_Particular_Nombre.equals(other.getCorresponsal_Particular_Nombre()))) &&
            ((this.corresponsal_Particular_Telefono==null && other.getCorresponsal_Particular_Telefono()==null) || 
             (this.corresponsal_Particular_Telefono!=null &&
              this.corresponsal_Particular_Telefono.equals(other.getCorresponsal_Particular_Telefono()))) &&
            ((this.corresponsal_Particular_TipoIdentificacion_Id==null && other.getCorresponsal_Particular_TipoIdentificacion_Id()==null) || 
             (this.corresponsal_Particular_TipoIdentificacion_Id!=null &&
              this.corresponsal_Particular_TipoIdentificacion_Id.equals(other.getCorresponsal_Particular_TipoIdentificacion_Id()))) &&
            ((this.corresponsal_Particular_TipoIdentificacion_Nombre==null && other.getCorresponsal_Particular_TipoIdentificacion_Nombre()==null) || 
             (this.corresponsal_Particular_TipoIdentificacion_Nombre!=null &&
              this.corresponsal_Particular_TipoIdentificacion_Nombre.equals(other.getCorresponsal_Particular_TipoIdentificacion_Nombre()))) &&
            ((this.corresponsal_Tipo==null && other.getCorresponsal_Tipo()==null) || 
             (this.corresponsal_Tipo!=null &&
              this.corresponsal_Tipo.equals(other.getCorresponsal_Tipo()))) &&
            ((this.dependenciaAsignada_Dependencia_Id==null && other.getDependenciaAsignada_Dependencia_Id()==null) || 
             (this.dependenciaAsignada_Dependencia_Id!=null &&
              this.dependenciaAsignada_Dependencia_Id.equals(other.getDependenciaAsignada_Dependencia_Id()))) &&
            ((this.dependenciaAsignada_Dependencia_Nombre==null && other.getDependenciaAsignada_Dependencia_Nombre()==null) || 
             (this.dependenciaAsignada_Dependencia_Nombre!=null &&
              this.dependenciaAsignada_Dependencia_Nombre.equals(other.getDependenciaAsignada_Dependencia_Nombre()))) &&
            ((this.dependencia_Id==null && other.getDependencia_Id()==null) || 
             (this.dependencia_Id!=null &&
              this.dependencia_Id.equals(other.getDependencia_Id()))) &&
            ((this.dependencia_Nombre==null && other.getDependencia_Nombre()==null) || 
             (this.dependencia_Nombre!=null &&
              this.dependencia_Nombre.equals(other.getDependencia_Nombre()))) &&
            ((this.documentoPrincipal==null && other.getDocumentoPrincipal()==null) || 
             (this.documentoPrincipal!=null &&
              this.documentoPrincipal.equals(other.getDocumentoPrincipal()))) &&
            ((this.documentosAnexos==null && other.getDocumentosAnexos()==null) || 
             (this.documentosAnexos!=null &&
              java.util.Arrays.equals(this.documentosAnexos, other.getDocumentosAnexos()))) &&
            ((this.entregaFisica==null && other.getEntregaFisica()==null) || 
             (this.entregaFisica!=null &&
              this.entregaFisica.equals(other.getEntregaFisica()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.folios==null && other.getFolios()==null) || 
             (this.folios!=null &&
              this.folios.equals(other.getFolios()))) &&
            ((this.funcionarioAsignado_Apellido==null && other.getFuncionarioAsignado_Apellido()==null) || 
             (this.funcionarioAsignado_Apellido!=null &&
              this.funcionarioAsignado_Apellido.equals(other.getFuncionarioAsignado_Apellido()))) &&
            ((this.funcionarioAsignado_Cargo==null && other.getFuncionarioAsignado_Cargo()==null) || 
             (this.funcionarioAsignado_Cargo!=null &&
              this.funcionarioAsignado_Cargo.equals(other.getFuncionarioAsignado_Cargo()))) &&
            ((this.funcionarioAsignado_Codigo==null && other.getFuncionarioAsignado_Codigo()==null) || 
             (this.funcionarioAsignado_Codigo!=null &&
              this.funcionarioAsignado_Codigo.equals(other.getFuncionarioAsignado_Codigo()))) &&
            ((this.funcionarioAsignado_Id==null && other.getFuncionarioAsignado_Id()==null) || 
             (this.funcionarioAsignado_Id!=null &&
              this.funcionarioAsignado_Id.equals(other.getFuncionarioAsignado_Id()))) &&
            ((this.funcionarioAsignado_Nemotecnico==null && other.getFuncionarioAsignado_Nemotecnico()==null) || 
             (this.funcionarioAsignado_Nemotecnico!=null &&
              this.funcionarioAsignado_Nemotecnico.equals(other.getFuncionarioAsignado_Nemotecnico()))) &&
            ((this.funcionarioAsignado_Nombre==null && other.getFuncionarioAsignado_Nombre()==null) || 
             (this.funcionarioAsignado_Nombre!=null &&
              this.funcionarioAsignado_Nombre.equals(other.getFuncionarioAsignado_Nombre()))) &&
            ((this.funcionario_Apellido==null && other.getFuncionario_Apellido()==null) || 
             (this.funcionario_Apellido!=null &&
              this.funcionario_Apellido.equals(other.getFuncionario_Apellido()))) &&
            ((this.funcionario_Cargo==null && other.getFuncionario_Cargo()==null) || 
             (this.funcionario_Cargo!=null &&
              this.funcionario_Cargo.equals(other.getFuncionario_Cargo()))) &&
            ((this.funcionario_Codigo==null && other.getFuncionario_Codigo()==null) || 
             (this.funcionario_Codigo!=null &&
              this.funcionario_Codigo.equals(other.getFuncionario_Codigo()))) &&
            ((this.funcionario_Id==null && other.getFuncionario_Id()==null) || 
             (this.funcionario_Id!=null &&
              this.funcionario_Id.equals(other.getFuncionario_Id()))) &&
            ((this.funcionario_Nemotecnico==null && other.getFuncionario_Nemotecnico()==null) || 
             (this.funcionario_Nemotecnico!=null &&
              this.funcionario_Nemotecnico.equals(other.getFuncionario_Nemotecnico()))) &&
            ((this.funcionario_Nombre==null && other.getFuncionario_Nombre()==null) || 
             (this.funcionario_Nombre!=null &&
              this.funcionario_Nombre.equals(other.getFuncionario_Nombre()))) &&
            ((this.medioDeEnvio_Codigo==null && other.getMedioDeEnvio_Codigo()==null) || 
             (this.medioDeEnvio_Codigo!=null &&
              this.medioDeEnvio_Codigo.equals(other.getMedioDeEnvio_Codigo()))) &&
            ((this.medioDeEnvio_Id==null && other.getMedioDeEnvio_Id()==null) || 
             (this.medioDeEnvio_Id!=null &&
              this.medioDeEnvio_Id.equals(other.getMedioDeEnvio_Id()))) &&
            ((this.medioDeEnvio_Nombre==null && other.getMedioDeEnvio_Nombre()==null) || 
             (this.medioDeEnvio_Nombre!=null &&
              this.medioDeEnvio_Nombre.equals(other.getMedioDeEnvio_Nombre()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje()))) &&
            ((this.multa==null && other.getMulta()==null) || 
             (this.multa!=null &&
              this.multa.equals(other.getMulta()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.radicacionAnteriorNumero==null && other.getRadicacionAnteriorNumero()==null) || 
             (this.radicacionAnteriorNumero!=null &&
              this.radicacionAnteriorNumero.equals(other.getRadicacionAnteriorNumero()))) &&
            ((this.referenciaExterna==null && other.getReferenciaExterna()==null) || 
             (this.referenciaExterna!=null &&
              this.referenciaExterna.equals(other.getReferenciaExterna()))) &&
            ((this.serie_Codigo==null && other.getSerie_Codigo()==null) || 
             (this.serie_Codigo!=null &&
              this.serie_Codigo.equals(other.getSerie_Codigo()))) &&
            ((this.serie_Nombre==null && other.getSerie_Nombre()==null) || 
             (this.serie_Nombre!=null &&
              this.serie_Nombre.equals(other.getSerie_Nombre()))) &&
            ((this.sub_Serie_Codigo==null && other.getSub_Serie_Codigo()==null) || 
             (this.sub_Serie_Codigo!=null &&
              this.sub_Serie_Codigo.equals(other.getSub_Serie_Codigo()))) &&
            ((this.sub_Serie_Nombre==null && other.getSub_Serie_Nombre()==null) || 
             (this.sub_Serie_Nombre!=null &&
              this.sub_Serie_Nombre.equals(other.getSub_Serie_Nombre()))) &&
            ((this.terminoDias==null && other.getTerminoDias()==null) || 
             (this.terminoDias!=null &&
              this.terminoDias.equals(other.getTerminoDias()))) &&
            ((this.terminoFecha==null && other.getTerminoFecha()==null) || 
             (this.terminoFecha!=null &&
              this.terminoFecha.equals(other.getTerminoFecha()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.tipoCuaderno_Codigo==null && other.getTipoCuaderno_Codigo()==null) || 
             (this.tipoCuaderno_Codigo!=null &&
              this.tipoCuaderno_Codigo.equals(other.getTipoCuaderno_Codigo()))) &&
            ((this.tipoCuaderno_Id==null && other.getTipoCuaderno_Id()==null) || 
             (this.tipoCuaderno_Id!=null &&
              this.tipoCuaderno_Id.equals(other.getTipoCuaderno_Id()))) &&
            ((this.tipoCuaderno_Nombre==null && other.getTipoCuaderno_Nombre()==null) || 
             (this.tipoCuaderno_Nombre!=null &&
              this.tipoCuaderno_Nombre.equals(other.getTipoCuaderno_Nombre()))) &&
            ((this.tipoDocumentalConsecutivo==null && other.getTipoDocumentalConsecutivo()==null) || 
             (this.tipoDocumentalConsecutivo!=null &&
              this.tipoDocumentalConsecutivo.equals(other.getTipoDocumentalConsecutivo()))) &&
            ((this.tipoDocumental_Codigo==null && other.getTipoDocumental_Codigo()==null) || 
             (this.tipoDocumental_Codigo!=null &&
              this.tipoDocumental_Codigo.equals(other.getTipoDocumental_Codigo()))) &&
            ((this.tipoDocumental_Id==null && other.getTipoDocumental_Id()==null) || 
             (this.tipoDocumental_Id!=null &&
              this.tipoDocumental_Id.equals(other.getTipoDocumental_Id()))) &&
            ((this.tipoDocumental_Nombre==null && other.getTipoDocumental_Nombre()==null) || 
             (this.tipoDocumental_Nombre!=null &&
              this.tipoDocumental_Nombre.equals(other.getTipoDocumental_Nombre()))) &&
            ((this.tipoSeguridad_Codigo==null && other.getTipoSeguridad_Codigo()==null) || 
             (this.tipoSeguridad_Codigo!=null &&
              this.tipoSeguridad_Codigo.equals(other.getTipoSeguridad_Codigo()))) &&
            ((this.tipoSeguridad_Id==null && other.getTipoSeguridad_Id()==null) || 
             (this.tipoSeguridad_Id!=null &&
              this.tipoSeguridad_Id.equals(other.getTipoSeguridad_Id()))) &&
            ((this.tipoSeguridad_Nombre==null && other.getTipoSeguridad_Nombre()==null) || 
             (this.tipoSeguridad_Nombre!=null &&
              this.tipoSeguridad_Nombre.equals(other.getTipoSeguridad_Nombre()))) &&
            ((this.tramite_Codigo==null && other.getTramite_Codigo()==null) || 
             (this.tramite_Codigo!=null &&
              this.tramite_Codigo.equals(other.getTramite_Codigo()))) &&
            ((this.tramite_Id==null && other.getTramite_Id()==null) || 
             (this.tramite_Id!=null &&
              this.tramite_Id.equals(other.getTramite_Id()))) &&
            ((this.tramite_Nombre==null && other.getTramite_Nombre()==null) || 
             (this.tramite_Nombre!=null &&
              this.tramite_Nombre.equals(other.getTramite_Nombre()))) &&
            ((this.tramite_Proceso_Codigo==null && other.getTramite_Proceso_Codigo()==null) || 
             (this.tramite_Proceso_Codigo!=null &&
              this.tramite_Proceso_Codigo.equals(other.getTramite_Proceso_Codigo()))) &&
            ((this.tramite_Proceso_Id==null && other.getTramite_Proceso_Id()==null) || 
             (this.tramite_Proceso_Id!=null &&
              this.tramite_Proceso_Id.equals(other.getTramite_Proceso_Id()))) &&
            ((this.tramite_Proceso_Nombre==null && other.getTramite_Proceso_Nombre()==null) || 
             (this.tramite_Proceso_Nombre!=null &&
              this.tramite_Proceso_Nombre.equals(other.getTramite_Proceso_Nombre()))) &&
            ((this.tramite_Termino_Dias==null && other.getTramite_Termino_Dias()==null) || 
             (this.tramite_Termino_Dias!=null &&
              this.tramite_Termino_Dias.equals(other.getTramite_Termino_Dias()))) &&
            ((this.tramite_Termino_EsModificable==null && other.getTramite_Termino_EsModificable()==null) || 
             (this.tramite_Termino_EsModificable!=null &&
              this.tramite_Termino_EsModificable.equals(other.getTramite_Termino_EsModificable()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.codigoRespuesta==null && other.getCodigoRespuesta()==null) || 
             (this.codigoRespuesta!=null &&
              this.codigoRespuesta.equals(other.getCodigoRespuesta())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAnexosFisicos() != null) {
            _hashCode += getAnexosFisicos().hashCode();
        }
        if (getAplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo() != null) {
            _hashCode += getAplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo().hashCode();
        }
        if (getAplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo() != null) {
            _hashCode += getAplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo().hashCode();
        }
        if (getAplicaA_Ciudad_Codigo_CiudadId_PaisCodigo() != null) {
            _hashCode += getAplicaA_Ciudad_Codigo_CiudadId_PaisCodigo().hashCode();
        }
        if (getAplicaA_Ciudad_Departamento() != null) {
            _hashCode += getAplicaA_Ciudad_Departamento().hashCode();
        }
        if (getAplicaA_Ciudad_Nombre() != null) {
            _hashCode += getAplicaA_Ciudad_Nombre().hashCode();
        }
        if (getAplicaA_Ciudad_Pais() != null) {
            _hashCode += getAplicaA_Ciudad_Pais().hashCode();
        }
        if (getAplicaA_Direccion() != null) {
            _hashCode += getAplicaA_Direccion().hashCode();
        }
        if (getAplicaA_Email() != null) {
            _hashCode += getAplicaA_Email().hashCode();
        }
        if (getAplicaA_Identificacion() != null) {
            _hashCode += getAplicaA_Identificacion().hashCode();
        }
        if (getAplicaA_Nombre() != null) {
            _hashCode += getAplicaA_Nombre().hashCode();
        }
        if (getAplicaA_Telefono() != null) {
            _hashCode += getAplicaA_Telefono().hashCode();
        }
        if (getAplicaA_TipoIdentificacion_Id() != null) {
            _hashCode += getAplicaA_TipoIdentificacion_Id().hashCode();
        }
        if (getAplicaA_TipoIdentificacion_Nombre() != null) {
            _hashCode += getAplicaA_TipoIdentificacion_Nombre().hashCode();
        }
        if (getCorresponsal_Dependencia_Id() != null) {
            _hashCode += getCorresponsal_Dependencia_Id().hashCode();
        }
        if (getCorresponsal_Dependencia_Nombre() != null) {
            _hashCode += getCorresponsal_Dependencia_Nombre().hashCode();
        }
        if (getCorresponsal_Particular_Ciudad_Codigo_CiudadCodigo() != null) {
            _hashCode += getCorresponsal_Particular_Ciudad_Codigo_CiudadCodigo().hashCode();
        }
        if (getCorresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo() != null) {
            _hashCode += getCorresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo().hashCode();
        }
        if (getCorresponsal_Particular_Ciudad_Codigo_PaisCodigo() != null) {
            _hashCode += getCorresponsal_Particular_Ciudad_Codigo_PaisCodigo().hashCode();
        }
        if (getCorresponsal_Particular_Ciudad_Departamento() != null) {
            _hashCode += getCorresponsal_Particular_Ciudad_Departamento().hashCode();
        }
        if (getCorresponsal_Particular_Ciudad_Nombre() != null) {
            _hashCode += getCorresponsal_Particular_Ciudad_Nombre().hashCode();
        }
        if (getCorresponsal_Particular_Ciudad_Pais() != null) {
            _hashCode += getCorresponsal_Particular_Ciudad_Pais().hashCode();
        }
        if (getCorresponsal_Particular_Direccion() != null) {
            _hashCode += getCorresponsal_Particular_Direccion().hashCode();
        }
        if (getCorresponsal_Particular_Email() != null) {
            _hashCode += getCorresponsal_Particular_Email().hashCode();
        }
        if (getCorresponsal_Particular_Identificacion() != null) {
            _hashCode += getCorresponsal_Particular_Identificacion().hashCode();
        }
        if (getCorresponsal_Particular_Nombre() != null) {
            _hashCode += getCorresponsal_Particular_Nombre().hashCode();
        }
        if (getCorresponsal_Particular_Telefono() != null) {
            _hashCode += getCorresponsal_Particular_Telefono().hashCode();
        }
        if (getCorresponsal_Particular_TipoIdentificacion_Id() != null) {
            _hashCode += getCorresponsal_Particular_TipoIdentificacion_Id().hashCode();
        }
        if (getCorresponsal_Particular_TipoIdentificacion_Nombre() != null) {
            _hashCode += getCorresponsal_Particular_TipoIdentificacion_Nombre().hashCode();
        }
        if (getCorresponsal_Tipo() != null) {
            _hashCode += getCorresponsal_Tipo().hashCode();
        }
        if (getDependenciaAsignada_Dependencia_Id() != null) {
            _hashCode += getDependenciaAsignada_Dependencia_Id().hashCode();
        }
        if (getDependenciaAsignada_Dependencia_Nombre() != null) {
            _hashCode += getDependenciaAsignada_Dependencia_Nombre().hashCode();
        }
        if (getDependencia_Id() != null) {
            _hashCode += getDependencia_Id().hashCode();
        }
        if (getDependencia_Nombre() != null) {
            _hashCode += getDependencia_Nombre().hashCode();
        }
        if (getDocumentoPrincipal() != null) {
            _hashCode += getDocumentoPrincipal().hashCode();
        }
        if (getDocumentosAnexos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentosAnexos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentosAnexos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEntregaFisica() != null) {
            _hashCode += getEntregaFisica().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getFolios() != null) {
            _hashCode += getFolios().hashCode();
        }
        if (getFuncionarioAsignado_Apellido() != null) {
            _hashCode += getFuncionarioAsignado_Apellido().hashCode();
        }
        if (getFuncionarioAsignado_Cargo() != null) {
            _hashCode += getFuncionarioAsignado_Cargo().hashCode();
        }
        if (getFuncionarioAsignado_Codigo() != null) {
            _hashCode += getFuncionarioAsignado_Codigo().hashCode();
        }
        if (getFuncionarioAsignado_Id() != null) {
            _hashCode += getFuncionarioAsignado_Id().hashCode();
        }
        if (getFuncionarioAsignado_Nemotecnico() != null) {
            _hashCode += getFuncionarioAsignado_Nemotecnico().hashCode();
        }
        if (getFuncionarioAsignado_Nombre() != null) {
            _hashCode += getFuncionarioAsignado_Nombre().hashCode();
        }
        if (getFuncionario_Apellido() != null) {
            _hashCode += getFuncionario_Apellido().hashCode();
        }
        if (getFuncionario_Cargo() != null) {
            _hashCode += getFuncionario_Cargo().hashCode();
        }
        if (getFuncionario_Codigo() != null) {
            _hashCode += getFuncionario_Codigo().hashCode();
        }
        if (getFuncionario_Id() != null) {
            _hashCode += getFuncionario_Id().hashCode();
        }
        if (getFuncionario_Nemotecnico() != null) {
            _hashCode += getFuncionario_Nemotecnico().hashCode();
        }
        if (getFuncionario_Nombre() != null) {
            _hashCode += getFuncionario_Nombre().hashCode();
        }
        if (getMedioDeEnvio_Codigo() != null) {
            _hashCode += getMedioDeEnvio_Codigo().hashCode();
        }
        if (getMedioDeEnvio_Id() != null) {
            _hashCode += getMedioDeEnvio_Id().hashCode();
        }
        if (getMedioDeEnvio_Nombre() != null) {
            _hashCode += getMedioDeEnvio_Nombre().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        if (getMulta() != null) {
            _hashCode += getMulta().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getRadicacionAnteriorNumero() != null) {
            _hashCode += getRadicacionAnteriorNumero().hashCode();
        }
        if (getReferenciaExterna() != null) {
            _hashCode += getReferenciaExterna().hashCode();
        }
        if (getSerie_Codigo() != null) {
            _hashCode += getSerie_Codigo().hashCode();
        }
        if (getSerie_Nombre() != null) {
            _hashCode += getSerie_Nombre().hashCode();
        }
        if (getSub_Serie_Codigo() != null) {
            _hashCode += getSub_Serie_Codigo().hashCode();
        }
        if (getSub_Serie_Nombre() != null) {
            _hashCode += getSub_Serie_Nombre().hashCode();
        }
        if (getTerminoDias() != null) {
            _hashCode += getTerminoDias().hashCode();
        }
        if (getTerminoFecha() != null) {
            _hashCode += getTerminoFecha().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getTipoCuaderno_Codigo() != null) {
            _hashCode += getTipoCuaderno_Codigo().hashCode();
        }
        if (getTipoCuaderno_Id() != null) {
            _hashCode += getTipoCuaderno_Id().hashCode();
        }
        if (getTipoCuaderno_Nombre() != null) {
            _hashCode += getTipoCuaderno_Nombre().hashCode();
        }
        if (getTipoDocumentalConsecutivo() != null) {
            _hashCode += getTipoDocumentalConsecutivo().hashCode();
        }
        if (getTipoDocumental_Codigo() != null) {
            _hashCode += getTipoDocumental_Codigo().hashCode();
        }
        if (getTipoDocumental_Id() != null) {
            _hashCode += getTipoDocumental_Id().hashCode();
        }
        if (getTipoDocumental_Nombre() != null) {
            _hashCode += getTipoDocumental_Nombre().hashCode();
        }
        if (getTipoSeguridad_Codigo() != null) {
            _hashCode += getTipoSeguridad_Codigo().hashCode();
        }
        if (getTipoSeguridad_Id() != null) {
            _hashCode += getTipoSeguridad_Id().hashCode();
        }
        if (getTipoSeguridad_Nombre() != null) {
            _hashCode += getTipoSeguridad_Nombre().hashCode();
        }
        if (getTramite_Codigo() != null) {
            _hashCode += getTramite_Codigo().hashCode();
        }
        if (getTramite_Id() != null) {
            _hashCode += getTramite_Id().hashCode();
        }
        if (getTramite_Nombre() != null) {
            _hashCode += getTramite_Nombre().hashCode();
        }
        if (getTramite_Proceso_Codigo() != null) {
            _hashCode += getTramite_Proceso_Codigo().hashCode();
        }
        if (getTramite_Proceso_Id() != null) {
            _hashCode += getTramite_Proceso_Id().hashCode();
        }
        if (getTramite_Proceso_Nombre() != null) {
            _hashCode += getTramite_Proceso_Nombre().hashCode();
        }
        if (getTramite_Termino_Dias() != null) {
            _hashCode += getTramite_Termino_Dias().hashCode();
        }
        if (getTramite_Termino_EsModificable() != null) {
            _hashCode += getTramite_Termino_EsModificable().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getCodigoRespuesta() != null) {
            _hashCode += getCodigoRespuesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RadicacionPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anexosFisicos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AnexosFisicos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Ciudad_Codigo_CiudadId_PaisCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Ciudad_Codigo_CiudadId_PaisCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Ciudad_Departamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Ciudad_Departamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Ciudad_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Ciudad_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Ciudad_Pais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Ciudad_Pais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Identificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Identificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_Telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_Telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_TipoIdentificacion_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_TipoIdentificacion_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA_TipoIdentificacion_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA_TipoIdentificacion_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Dependencia_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Dependencia_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Dependencia_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Dependencia_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Ciudad_Codigo_CiudadCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Ciudad_Codigo_CiudadCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Ciudad_Codigo_PaisCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Ciudad_Codigo_PaisCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Ciudad_Departamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Ciudad_Departamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Ciudad_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Ciudad_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Ciudad_Pais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Ciudad_Pais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Identificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Identificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_Telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_Telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_TipoIdentificacion_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_TipoIdentificacion_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Particular_TipoIdentificacion_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Particular_TipoIdentificacion_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal_Tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal_Tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependenciaAsignada_Dependencia_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DependenciaAsignada_Dependencia_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependenciaAsignada_Dependencia_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DependenciaAsignada_Dependencia_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependencia_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Dependencia_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependencia_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Dependencia_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoPrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentosAnexos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentosAnexos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entregaFisica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "EntregaFisica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Folios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioAsignado_Apellido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "FuncionarioAsignado_Apellido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioAsignado_Cargo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "FuncionarioAsignado_Cargo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioAsignado_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "FuncionarioAsignado_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioAsignado_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "FuncionarioAsignado_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioAsignado_Nemotecnico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "FuncionarioAsignado_Nemotecnico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioAsignado_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "FuncionarioAsignado_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario_Apellido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario_Apellido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario_Cargo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario_Cargo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario_Nemotecnico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario_Nemotecnico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioDeEnvio_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioDeEnvio_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioDeEnvio_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Multa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacionAnteriorNumero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionAnteriorNumero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenciaExterna");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ReferenciaExterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serie_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Serie_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serie_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Serie_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sub_Serie_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Sub_Serie_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sub_Serie_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Sub_Serie_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminoDias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TerminoDias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminoFecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TerminoFecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCuaderno_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoCuaderno_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCuaderno_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoCuaderno_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCuaderno_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoCuaderno_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumentalConsecutivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumentalConsecutivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumental_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumental_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumental_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoSeguridad_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoSeguridad_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoSeguridad_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_Proceso_Codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite_Proceso_Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_Proceso_Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite_Proceso_Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_Proceso_Nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite_Proceso_Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_Termino_Dias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite_Termino_Dias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_Termino_EsModificable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite_Termino_EsModificable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

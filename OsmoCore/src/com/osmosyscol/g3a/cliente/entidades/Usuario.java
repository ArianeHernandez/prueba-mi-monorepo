/**
 * Usuario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente.entidades;

import org.apache.commons.lang.StringUtils;

public class Usuario  implements java.io.Serializable {
    private java.lang.String apellidosCliente;

    private java.lang.String apellidosUsuario;

    private java.lang.String correo;

    private java.util.Calendar fechaUltimoIngreso;

    private boolean funcionario;

    private java.lang.Integer id;

    private com.osmosyscol.g3a.cliente.entidades.IDUsuario idUsuario;

    private java.lang.String nombresCliente;

    private java.lang.String nombresUsuario;

    private boolean responsable;
    
	private String intentosFallidosPrevios;

	public Usuario() {
    }

    public Usuario(
           java.lang.String apellidosCliente,
           java.lang.String apellidosUsuario,
           java.lang.String correo,
           java.util.Calendar fechaUltimoIngreso,
           boolean funcionario,
           java.lang.Integer id,
           com.osmosyscol.g3a.cliente.entidades.IDUsuario idUsuario,
           java.lang.String nombresCliente,
           java.lang.String nombresUsuario,
           boolean responsable) {
           this.apellidosCliente = apellidosCliente;
           this.apellidosUsuario = apellidosUsuario;
           this.correo = correo;
           this.fechaUltimoIngreso = fechaUltimoIngreso;
           this.funcionario = funcionario;
           this.id = id;
           this.idUsuario = idUsuario;
           this.nombresCliente = nombresCliente;
           this.nombresUsuario = nombresUsuario;
           this.responsable = responsable;
    }
    
    public String getNombreCompletoClienteYUsuario(){
    	String nombreCompleto = StringUtils.defaultString(this.nombresCliente) + " " +
    	StringUtils.defaultString(this.apellidosCliente);
    	
    	nombreCompleto += " - ";
    	
    	nombreCompleto += StringUtils.defaultString(this.nombresUsuario) + " " +
    	StringUtils.defaultString(this.apellidosUsuario);
    	return nombreCompleto;
    }

    public String getIntentosFallidosPrevios() {
		return intentosFallidosPrevios;
	}

	public void setIntentosFallidosPrevios(String intentosFallidosPrevios) {
		this.intentosFallidosPrevios = intentosFallidosPrevios;
	}


    /**
     * Gets the apellidosCliente value for this Usuario.
     * 
     * @return apellidosCliente
     */
    public java.lang.String getApellidosCliente() {
        return apellidosCliente;
    }


    /**
     * Sets the apellidosCliente value for this Usuario.
     * 
     * @param apellidosCliente
     */
    public void setApellidosCliente(java.lang.String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }


    /**
     * Gets the apellidosUsuario value for this Usuario.
     * 
     * @return apellidosUsuario
     */
    public java.lang.String getApellidosUsuario() {
        return apellidosUsuario;
    }


    /**
     * Sets the apellidosUsuario value for this Usuario.
     * 
     * @param apellidosUsuario
     */
    public void setApellidosUsuario(java.lang.String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }


    /**
     * Gets the correo value for this Usuario.
     * 
     * @return correo
     */
    public java.lang.String getCorreo() {
        return correo;
    }


    /**
     * Sets the correo value for this Usuario.
     * 
     * @param correo
     */
    public void setCorreo(java.lang.String correo) {
        this.correo = correo;
    }


    /**
     * Gets the fechaUltimoIngreso value for this Usuario.
     * 
     * @return fechaUltimoIngreso
     */
    public java.util.Calendar getFechaUltimoIngreso() {
        return fechaUltimoIngreso;
    }


    /**
     * Sets the fechaUltimoIngreso value for this Usuario.
     * 
     * @param fechaUltimoIngreso
     */
    public void setFechaUltimoIngreso(java.util.Calendar fechaUltimoIngreso) {
        this.fechaUltimoIngreso = fechaUltimoIngreso;
    }


    /**
     * Gets the funcionario value for this Usuario.
     * 
     * @return funcionario
     */
    public boolean isFuncionario() {
        return funcionario;
    }


    /**
     * Sets the funcionario value for this Usuario.
     * 
     * @param funcionario
     */
    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }


    /**
     * Gets the id value for this Usuario.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this Usuario.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the idUsuario value for this Usuario.
     * 
     * @return idUsuario
     */
    public com.osmosyscol.g3a.cliente.entidades.IDUsuario getIdUsuario() {
        return idUsuario;
    }


    /**
     * Sets the idUsuario value for this Usuario.
     * 
     * @param idUsuario
     */
    public void setIdUsuario(com.osmosyscol.g3a.cliente.entidades.IDUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }


    /**
     * Gets the nombresCliente value for this Usuario.
     * 
     * @return nombresCliente
     */
    public java.lang.String getNombresCliente() {
        return nombresCliente;
    }


    /**
     * Sets the nombresCliente value for this Usuario.
     * 
     * @param nombresCliente
     */
    public void setNombresCliente(java.lang.String nombresCliente) {
        this.nombresCliente = nombresCliente;
    }


    /**
     * Gets the nombresUsuario value for this Usuario.
     * 
     * @return nombresUsuario
     */
    public java.lang.String getNombresUsuario() {
        return nombresUsuario;
    }


    /**
     * Sets the nombresUsuario value for this Usuario.
     * 
     * @param nombresUsuario
     */
    public void setNombresUsuario(java.lang.String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }


    /**
     * Gets the responsable value for this Usuario.
     * 
     * @return responsable
     */
    public boolean isResponsable() {
        return responsable;
    }


    /**
     * Sets the responsable value for this Usuario.
     * 
     * @param responsable
     */
    public void setResponsable(boolean responsable) {
        this.responsable = responsable;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Usuario)) return false;
        Usuario other = (Usuario) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.apellidosCliente==null && other.getApellidosCliente()==null) || 
             (this.apellidosCliente!=null &&
              this.apellidosCliente.equals(other.getApellidosCliente()))) &&
            ((this.apellidosUsuario==null && other.getApellidosUsuario()==null) || 
             (this.apellidosUsuario!=null &&
              this.apellidosUsuario.equals(other.getApellidosUsuario()))) &&
            ((this.correo==null && other.getCorreo()==null) || 
             (this.correo!=null &&
              this.correo.equals(other.getCorreo()))) &&
            ((this.fechaUltimoIngreso==null && other.getFechaUltimoIngreso()==null) || 
             (this.fechaUltimoIngreso!=null &&
              this.fechaUltimoIngreso.equals(other.getFechaUltimoIngreso()))) &&
            this.funcionario == other.isFuncionario() &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idUsuario==null && other.getIdUsuario()==null) || 
             (this.idUsuario!=null &&
              this.idUsuario.equals(other.getIdUsuario()))) &&
            ((this.nombresCliente==null && other.getNombresCliente()==null) || 
             (this.nombresCliente!=null &&
              this.nombresCliente.equals(other.getNombresCliente()))) &&
            ((this.nombresUsuario==null && other.getNombresUsuario()==null) || 
             (this.nombresUsuario!=null &&
              this.nombresUsuario.equals(other.getNombresUsuario()))) &&
            this.responsable == other.isResponsable();
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
        if (getApellidosCliente() != null) {
            _hashCode += getApellidosCliente().hashCode();
        }
        if (getApellidosUsuario() != null) {
            _hashCode += getApellidosUsuario().hashCode();
        }
        if (getCorreo() != null) {
            _hashCode += getCorreo().hashCode();
        }
        if (getFechaUltimoIngreso() != null) {
            _hashCode += getFechaUltimoIngreso().hashCode();
        }
        _hashCode += (isFuncionario() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdUsuario() != null) {
            _hashCode += getIdUsuario().hashCode();
        }
        if (getNombresCliente() != null) {
            _hashCode += getNombresCliente().hashCode();
        }
        if (getNombresUsuario() != null) {
            _hashCode += getNombresUsuario().hashCode();
        }
        _hashCode += (isResponsable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Usuario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "Usuario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellidosCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "apellidosCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellidosUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "apellidosUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "correo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaUltimoIngreso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "fechaUltimoIngreso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "funcionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "idUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "IDUsuario"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombresCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "nombresCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombresUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "nombresUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "responsable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
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

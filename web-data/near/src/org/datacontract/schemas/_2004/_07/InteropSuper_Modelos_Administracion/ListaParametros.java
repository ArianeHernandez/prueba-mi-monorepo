/**
 * ListaParametros.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Administracion;

public class ListaParametros  implements java.io.Serializable {
    private java.lang.String descripcion;

    private java.lang.Boolean estado;

    private java.util.Calendar fecha_Creacion;

    private java.util.Calendar fecha_Modificacion;

    private java.lang.Integer ID;

    private java.lang.Integer ID_Tipo_Parametro;

    private java.lang.String nombre;

    private java.lang.String tipoParametro;

    private java.lang.String usuario_Creacion;

    private java.lang.String usuario_Modificacion;

    public ListaParametros() {
    }

    public ListaParametros(
           java.lang.String descripcion,
           java.lang.Boolean estado,
           java.util.Calendar fecha_Creacion,
           java.util.Calendar fecha_Modificacion,
           java.lang.Integer ID,
           java.lang.Integer ID_Tipo_Parametro,
           java.lang.String nombre,
           java.lang.String tipoParametro,
           java.lang.String usuario_Creacion,
           java.lang.String usuario_Modificacion) {
           this.descripcion = descripcion;
           this.estado = estado;
           this.fecha_Creacion = fecha_Creacion;
           this.fecha_Modificacion = fecha_Modificacion;
           this.ID = ID;
           this.ID_Tipo_Parametro = ID_Tipo_Parametro;
           this.nombre = nombre;
           this.tipoParametro = tipoParametro;
           this.usuario_Creacion = usuario_Creacion;
           this.usuario_Modificacion = usuario_Modificacion;
    }


    /**
     * Gets the descripcion value for this ListaParametros.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ListaParametros.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the estado value for this ListaParametros.
     * 
     * @return estado
     */
    public java.lang.Boolean getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this ListaParametros.
     * 
     * @param estado
     */
    public void setEstado(java.lang.Boolean estado) {
        this.estado = estado;
    }


    /**
     * Gets the fecha_Creacion value for this ListaParametros.
     * 
     * @return fecha_Creacion
     */
    public java.util.Calendar getFecha_Creacion() {
        return fecha_Creacion;
    }


    /**
     * Sets the fecha_Creacion value for this ListaParametros.
     * 
     * @param fecha_Creacion
     */
    public void setFecha_Creacion(java.util.Calendar fecha_Creacion) {
        this.fecha_Creacion = fecha_Creacion;
    }


    /**
     * Gets the fecha_Modificacion value for this ListaParametros.
     * 
     * @return fecha_Modificacion
     */
    public java.util.Calendar getFecha_Modificacion() {
        return fecha_Modificacion;
    }


    /**
     * Sets the fecha_Modificacion value for this ListaParametros.
     * 
     * @param fecha_Modificacion
     */
    public void setFecha_Modificacion(java.util.Calendar fecha_Modificacion) {
        this.fecha_Modificacion = fecha_Modificacion;
    }


    /**
     * Gets the ID value for this ListaParametros.
     * 
     * @return ID
     */
    public java.lang.Integer getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ListaParametros.
     * 
     * @param ID
     */
    public void setID(java.lang.Integer ID) {
        this.ID = ID;
    }


    /**
     * Gets the ID_Tipo_Parametro value for this ListaParametros.
     * 
     * @return ID_Tipo_Parametro
     */
    public java.lang.Integer getID_Tipo_Parametro() {
        return ID_Tipo_Parametro;
    }


    /**
     * Sets the ID_Tipo_Parametro value for this ListaParametros.
     * 
     * @param ID_Tipo_Parametro
     */
    public void setID_Tipo_Parametro(java.lang.Integer ID_Tipo_Parametro) {
        this.ID_Tipo_Parametro = ID_Tipo_Parametro;
    }


    /**
     * Gets the nombre value for this ListaParametros.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this ListaParametros.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the tipoParametro value for this ListaParametros.
     * 
     * @return tipoParametro
     */
    public java.lang.String getTipoParametro() {
        return tipoParametro;
    }


    /**
     * Sets the tipoParametro value for this ListaParametros.
     * 
     * @param tipoParametro
     */
    public void setTipoParametro(java.lang.String tipoParametro) {
        this.tipoParametro = tipoParametro;
    }


    /**
     * Gets the usuario_Creacion value for this ListaParametros.
     * 
     * @return usuario_Creacion
     */
    public java.lang.String getUsuario_Creacion() {
        return usuario_Creacion;
    }


    /**
     * Sets the usuario_Creacion value for this ListaParametros.
     * 
     * @param usuario_Creacion
     */
    public void setUsuario_Creacion(java.lang.String usuario_Creacion) {
        this.usuario_Creacion = usuario_Creacion;
    }


    /**
     * Gets the usuario_Modificacion value for this ListaParametros.
     * 
     * @return usuario_Modificacion
     */
    public java.lang.String getUsuario_Modificacion() {
        return usuario_Modificacion;
    }


    /**
     * Sets the usuario_Modificacion value for this ListaParametros.
     * 
     * @param usuario_Modificacion
     */
    public void setUsuario_Modificacion(java.lang.String usuario_Modificacion) {
        this.usuario_Modificacion = usuario_Modificacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaParametros)) return false;
        ListaParametros other = (ListaParametros) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.fecha_Creacion==null && other.getFecha_Creacion()==null) || 
             (this.fecha_Creacion!=null &&
              this.fecha_Creacion.equals(other.getFecha_Creacion()))) &&
            ((this.fecha_Modificacion==null && other.getFecha_Modificacion()==null) || 
             (this.fecha_Modificacion!=null &&
              this.fecha_Modificacion.equals(other.getFecha_Modificacion()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.ID_Tipo_Parametro==null && other.getID_Tipo_Parametro()==null) || 
             (this.ID_Tipo_Parametro!=null &&
              this.ID_Tipo_Parametro.equals(other.getID_Tipo_Parametro()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.tipoParametro==null && other.getTipoParametro()==null) || 
             (this.tipoParametro!=null &&
              this.tipoParametro.equals(other.getTipoParametro()))) &&
            ((this.usuario_Creacion==null && other.getUsuario_Creacion()==null) || 
             (this.usuario_Creacion!=null &&
              this.usuario_Creacion.equals(other.getUsuario_Creacion()))) &&
            ((this.usuario_Modificacion==null && other.getUsuario_Modificacion()==null) || 
             (this.usuario_Modificacion!=null &&
              this.usuario_Modificacion.equals(other.getUsuario_Modificacion())));
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getFecha_Creacion() != null) {
            _hashCode += getFecha_Creacion().hashCode();
        }
        if (getFecha_Modificacion() != null) {
            _hashCode += getFecha_Modificacion().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getID_Tipo_Parametro() != null) {
            _hashCode += getID_Tipo_Parametro().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getTipoParametro() != null) {
            _hashCode += getTipoParametro().hashCode();
        }
        if (getUsuario_Creacion() != null) {
            _hashCode += getUsuario_Creacion().hashCode();
        }
        if (getUsuario_Modificacion() != null) {
            _hashCode += getUsuario_Modificacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaParametros.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "ListaParametros"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "Descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha_Creacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "Fecha_Creacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha_Modificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "Fecha_Modificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID_Tipo_Parametro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "ID_Tipo_Parametro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoParametro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "TipoParametro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario_Creacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "Usuario_Creacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario_Modificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Administracion", "Usuario_Modificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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

/**
 * SessionTicket.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente.entidades;

public class SessionTicket  implements java.io.Serializable {
	
    private java.lang.String idFilial;

    private java.lang.String ipUsuario;
    
    private java.lang.String browserUsuario;

    private java.lang.String ssid;

    private java.lang.String valorTicket;

    public SessionTicket() {
    }

    public SessionTicket(
           java.lang.String idFilial,
           java.lang.String ipUsuario,
           java.lang.String browserUsuario,
           java.lang.String ssid,
           java.lang.String valorTicket) {
           this.idFilial = idFilial;
           this.ipUsuario = ipUsuario;
           this.browserUsuario = browserUsuario;
           this.ssid = ssid;
           this.valorTicket = valorTicket;
    }


    /**
     * Gets the idFilial value for this SessionTicket.
     * 
     * @return idFilial
     */
    public java.lang.String getIdFilial() {
        return idFilial;
    }


    /**
     * Sets the idFilial value for this SessionTicket.
     * 
     * @param idFilial
     */
    public void setIdFilial(java.lang.String idFilial) {
        this.idFilial = idFilial;
    }


    /**
     * Gets the ipUsuario value for this SessionTicket.
     * 
     * @return ipUsuario
     */
    public java.lang.String getIpUsuario() {
        return ipUsuario;
    }


    /**
     * Sets the ipUsuario value for this SessionTicket.
     * 
     * @param ipUsuario
     */
    public void setIpUsuario(java.lang.String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }
    
    public void setBrowserUsuario(java.lang.String browserUsuario) {
		this.browserUsuario = browserUsuario;
	}
    
    public java.lang.String getBrowserUsuario() {
		return browserUsuario;
	}


    /**
     * Gets the ssid value for this SessionTicket.
     * 
     * @return ssid
     */
    public java.lang.String getSsid() {
        return ssid;
    }


    /**
     * Sets the ssid value for this SessionTicket.
     * 
     * @param ssid
     */
    public void setSsid(java.lang.String ssid) {
        this.ssid = ssid;
    }


    /**
     * Gets the valorTicket value for this SessionTicket.
     * 
     * @return valorTicket
     */
    public java.lang.String getValorTicket() {
        return valorTicket;
    }


    /**
     * Sets the valorTicket value for this SessionTicket.
     * 
     * @param valorTicket
     */
    public void setValorTicket(java.lang.String valorTicket) {
        this.valorTicket = valorTicket;
    }
    
    

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SessionTicket)) return false;
        SessionTicket other = (SessionTicket) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idFilial==null && other.getIdFilial()==null) || 
             (this.idFilial!=null &&
              this.idFilial.equals(other.getIdFilial()))) &&
            ((this.ipUsuario==null && other.getIpUsuario()==null) || 
             (this.ipUsuario!=null &&
              this.ipUsuario.equals(other.getIpUsuario()))) &&
            ((this.browserUsuario==null && other.getBrowserUsuario()==null) || 
              (this.browserUsuario!=null &&
              this.browserUsuario.equals(other.getBrowserUsuario()))) &&
            ((this.ssid==null && other.getSsid()==null) || 
             (this.ssid!=null &&
              this.ssid.equals(other.getSsid()))) &&
            ((this.valorTicket==null && other.getValorTicket()==null) || 
             (this.valorTicket!=null &&
              this.valorTicket.equals(other.getValorTicket())));
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
        if (getIdFilial() != null) {
            _hashCode += getIdFilial().hashCode();
        }
        if (getIpUsuario() != null) {
            _hashCode += getIpUsuario().hashCode();
        }
        if (getBrowserUsuario() != null) {
            _hashCode += getBrowserUsuario().hashCode();
        }
        if (getSsid() != null) {
            _hashCode += getSsid().hashCode();
        }
        if (getValorTicket() != null) {
            _hashCode += getValorTicket().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SessionTicket.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SessionTicket"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        
        elemField.setFieldName("idFilial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "idFilial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "ipUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("browserUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "browserUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ssid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "ssid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTicket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "valorTicket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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

/*
 * Creado el 22/08/2005
 */
package com.osmosyscol.commons.rpc.jsonrpc;

/**
 * @author Carlos Herrera
 */
public class Service {
    private String urn;
    private String classname;
    
    
    /**
     * @return Devuelve classname.
     */
    public String getClassname() {
        return classname;
    }
    /**
     * @param classname El classname a establecer.
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }
    /**
     * @return Devuelve urn.
     */
    public String getUrn() {
        return urn;
    }
    /**
     * @param urn El urn a establecer.
     */
    public void setUrn(String urn) {
        this.urn = urn;
    }
}

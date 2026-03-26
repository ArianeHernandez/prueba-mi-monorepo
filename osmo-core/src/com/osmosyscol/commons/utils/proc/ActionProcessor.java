/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.osmosyscol.commons.utils.proc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to calculate some application behavior; candidate to be an aspect
 * @author ahurtado
 */
public abstract class ActionProcessor {

    private static final Logger log  = LoggerFactory.getLogger(ActionProcessor.class);


    private String componentId;
    private String actionId;
    private boolean hasErrors;
    List<String> debugActions = new ArrayList<String>();
    Object outputPayload;
    
    public ActionProcessor( String componentId, String actionId ) {
        this.componentId = componentId;
        this.actionId = actionId;
    }

    public Object getOutputPayload() {
        return outputPayload;
    }

    public ActionProcessor run() throws Exception {
        try {
            beforeExecute();
            outputPayload = execute();
            afterExecuteOnSuccess();
        }
        catch( Exception e ) {
            hasErrors = true;
            afterExecuteOnErrors( e );
            handleErrors(e, debugActions);
        }
        finally {
            writeReport();
        }

        return this;
    }
    abstract protected Object execute() throws Exception ;

    long startTime;
    long stopTime;

    public static long calculateTime() {
            return  System.currentTimeMillis();
    }

    private void beforeExecute() {
        startTime = calculateTime();
    }

    private void afterExecuteOnSuccess() {
        stopTime = calculateTime();
    }

    private void afterExecuteOnErrors(Exception e) {
        stopTime = System.currentTimeMillis();
        log.error("",e);
    }

    private void writeReport() {
        String stringDebugActions = StringUtils.join(debugActions.toArray(new String[]{}),";");

        if( log.isInfoEnabled() ) {

            log.info(String.format(" >> execution: %s%s [status:%s] time: %d ms [DEBUG-ACTIONS:%s]",componentId,actionId , (!hasErrors)?( "SUCCESS" ) :("ERRORS"),  ( stopTime - startTime ), stringDebugActions ) );
        }
        
    }

    protected void addDebugAction(String string, Object ... values) {
        if(null == values ) {
                values = new Object[]{};
        }
        Object[] newValues = new Object[values.length+1];
        newValues[0] = new Date();
        if( values.length <= 0 ) {
                System.arraycopy(values, 1, newValues, 0, values.length);
        }

        String debugAction = String.format( "* (time:%Tc): " + string, newValues );
        debugActions.add(debugAction);
    }

    protected void handleErrors(Exception e, List<String> debugActions) throws Exception {
        // empty
    }
}

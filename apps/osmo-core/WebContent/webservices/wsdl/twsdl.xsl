<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:output indent="yes" method="xml"/>
	
	<xsl:param name="contextPath" />
	
    <xsl:template match="OSM-ACCION">
        <xsl:if test="count(//ServicioDataPi/operaciones/Operacion)>0">
            <wsdl:definitions name="{//ServicioDataPi/nombre}" targetNamespace="http://www.itosmosys.com/datapi/servicios/{//ServicioDataPi/nombre}" xmlns_dpi="http://www.itosmosys.com/datapi/servicios/{//ServicioDataPi/nombre}" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
            	            	
            	<wsdl:types>
                    <xsd:schema targetNamespace="http://www.itosmosys.com/datapi/servicios/{//ServicioDataPi/nombre}"  elementFormDefault="qualified">
                    	
                    	<xsl:for-each select="//ServicioDataPi/operaciones/Operacion">
                    		
                    		<!-- ******************************************* -->
                    		<!-- Elementos de Entrada y Salida -->
                    		
                            <xsd:element name="entrada{nombre}" type="dpi:tipoEntrada{nombre}"/>
                            <xsd:element name="salida{nombre}" type="dpi:tipoSalida{nombre}"/>
                            
                    		<!-- ******************************************* -->
                    		<!-- Tipos de Entrada y Salida -->
                    		
                    		<xsd:complexType name="tipoEntrada{nombre}">
                                <xsd:sequence>
                                    <xsd:element maxOccurs="1" minOccurs="1" name="elementoEntrada" type="dpi:tipoElementoEntrada{nombre}"/>
                                </xsd:sequence>
                            </xsd:complexType>
                            
                    		<xsd:complexType name="tipoSalida{nombre}">
                                <xsd:sequence>
                                    <xsd:element maxOccurs="unbounded" minOccurs="0" name="elementoSalida" type="dpi:tipoElementoSalida{nombre}"/>
                                </xsd:sequence>
                            </xsd:complexType>
                            
                    		<!-- ******************************************* -->
                    		<!-- Tipos Complejos de Entrada -->
                    		
                    		<xsd:complexType name="tipoElementoEntrada{nombre}">
                                <xsd:sequence>
                                    <xsl:for-each select="parametros/Parametro">
                                    	<xsl:sort select="orden" data-type="number"/>
                                    	
                                    	<xsd:element name="{nombre}">
                                    	
	                                        <xsl:choose>
	                                            <xsl:when test="tipo='array' or tipo='subquery'">
	                                                <xsl:attribute name="type">dpi:<xsl:value-of select="nombre"/>Array</xsl:attribute>
	                                            </xsl:when>
	                                        	<xsl:when test="tipo='object'">
	                                        		<xsl:attribute name="type">dpi:<xsl:value-of select="nombre"/>Record</xsl:attribute>
	                                            </xsl:when>
	                                        	<xsl:when test="tipo='clob'">
	                                        		<xsl:attribute name="type">xsd:anyType</xsl:attribute>
	                                            </xsl:when>
	                                            <xsl:when test="tipo='blob'">
	                                        		<xsl:attribute name="type">xsd:anyType</xsl:attribute>
	                                            </xsl:when>
	                                            <xsl:otherwise>
	                                            	<xsl:attribute name="type">xsd:<xsl:value-of select="tipo"/></xsl:attribute>
	                                            </xsl:otherwise>
	                                        </xsl:choose>
                                        
                                           	<xsl:if test="obligatorio='false'">
                                           		<xsl:attribute name="nillable">true</xsl:attribute>
                                           	</xsl:if>
                                        
                                        </xsd:element>
                                    </xsl:for-each>
                                </xsd:sequence>
                            </xsd:complexType>
                    		
                    		<!-- ******************************************* -->
                    		<!-- Tipos Complejos de Salida -->
                    		
                            <xsd:complexType name="tipoElementoSalida{nombre}">
                                <xsd:all>
                                    <xsl:for-each select="resultados/Resultado">

										<xsd:element name="{nombre}">
										 
		                                	<xsl:choose>
		                                        <xsl:when test="tipo='array' or tipo='subquery'">
		                                            <xsl:attribute name="type">dpi:<xsl:value-of select="nombre"/>Array</xsl:attribute>
		                                        </xsl:when>
		                                		<xsl:when test="tipo='object'">
	                                                <xsl:attribute name="type">dpi:<xsl:value-of select="nombre"/>Record</xsl:attribute>
	                                            </xsl:when>
	                                            <xsl:when test="tipo='clob'">
	                                                 <xsl:attribute name="type">xsd:string</xsl:attribute>
	                                            </xsl:when>
	                                            <xsl:when test="tipo='blob'">
	                                                 <xsl:attribute name="type">xsd:string</xsl:attribute>
	                                            </xsl:when>
		                                        <xsl:otherwise>
		                                            <xsl:attribute name="type">xsd:<xsl:value-of select="tipo"/></xsl:attribute>
		                                        </xsl:otherwise>
		                                    </xsl:choose>
		                                    
		                                    <xsl:if test="obligatorio='false'">
                                           		<xsl:attribute name="nillable">true</xsl:attribute>
                                           	</xsl:if>
		                                    
		                                 </xsd:element>
                                    </xsl:for-each>
                                </xsd:all>
                            </xsd:complexType>
                        	
                        </xsl:for-each>

	                   		<!-- ******************************************* -->
                    		<!-- Tipos Complejos para Arreglos -->
                    		
                    	 <xsl:for-each select="//*[tipo='array' or tipo='subquery']">
                    	 	
                    	 	 <xsd:complexType name="{nombre}Array">
                    	 	 	<xsd:sequence>
                                    <xsd:element maxOccurs="unbounded" minOccurs="0" name="{nombre}Elemento" type="dpi:{nombre}ArrayElement"/>
                                </xsd:sequence>
                            </xsd:complexType>
                    	 	
                        	<xsd:complexType name="{nombre}ArrayElement">
                                <xsd:all>
                                	<xsl:for-each select="elementos/Elemento">
                                        
                                        <xsd:element name="{nombre}">
                                    	
	                                        <xsl:choose>
	                                            <xsl:when test="tipo='array' or tipo='subquery'">
	                                                <xsl:attribute name="type">dpi:<xsl:value-of select="nombre"/>Array</xsl:attribute>
	                                            </xsl:when>
	                                        	<xsl:when test="tipo='object'">
	                                        		<xsl:attribute name="type">dpi:<xsl:value-of select="nombre"/>Record</xsl:attribute>
	                                            </xsl:when>
	                                        	<xsl:when test="tipo='clob'">
	                                        		<xsl:attribute name="type">xsd:string</xsl:attribute>
	                                            </xsl:when>
	                                            <xsl:when test="tipo='blob'">
	                                        		 <xsl:attribute name="type">xsd:string</xsl:attribute>
	                                            </xsl:when>
	                                            <xsl:otherwise>
	                                            	<xsl:attribute name="type">xsd:<xsl:value-of select="tipo"/></xsl:attribute>
	                                            </xsl:otherwise>
	                                        </xsl:choose>
                                        
                                           	<xsl:if test="obligatorio='false'">
                                           		<xsl:attribute name="nillable">true</xsl:attribute>
                                           	</xsl:if>
                                        
                                        </xsd:element>
                                        
                                    </xsl:for-each>
                                </xsd:all>
                            </xsd:complexType>
                            	
                        </xsl:for-each>
                        
                		<!-- ******************************************* -->
                		<!-- Tipos Complejos para Objetos -->
                		
                		<xsl:for-each select="//*[tipo='object']">
                    	 	
                        	<xsd:complexType name="{nombre}Record">
                                <xsd:all>
                                	<xsl:for-each select="elementos/Elemento">
                                		
                                		<xsd:element name="{nombre}">
                                	
	                                    	<xsl:choose>
	                                            <xsl:when test="tipo='array' or tipo='subquery'">
	                                                <xsl:attribute name="type">dpi:<xsl:value-of select="nombre"/>Array</xsl:attribute>
	                                            </xsl:when>
	                                        	<xsl:when test="tipo='object'">
	                                        		<xsl:attribute name="type">dpi:<xsl:value-of select="nombre"/>Record</xsl:attribute>
	                                            </xsl:when>
	                                        	<xsl:when test="tipo='clob'">
	                                        		<xsl:attribute name="type">xsd:string</xsl:attribute>
	                                            </xsl:when>
	                                            <xsl:when test="tipo='blob'">
	                                        		<xsd:element name="{nombre}" type="xsd:anyType"/>
	                                            </xsl:when>
	                                            <xsl:otherwise>
	                                            	<xsl:attribute name="type">xsd:<xsl:value-of select="tipo"/></xsl:attribute>
	                                            </xsl:otherwise>
	                                        </xsl:choose>
	                                        
	                                        <xsl:if test="obligatorio='false'">
                                           		<xsl:attribute name="nillable">true</xsl:attribute>
                                           	</xsl:if>
	                                        
	                                     </xsd:element>
	                                     
                                    </xsl:for-each>
                                </xsd:all>
                            </xsd:complexType>
                            	
                        </xsl:for-each>
                		
                		<!-- ******************************************* -->
                    		
                    </xsd:schema>
                </wsdl:types>
            	
            	<!-- ******************************************* -->
            	<!-- ******************************************* -->
            	
                <xsl:for-each select="//ServicioDataPi/operaciones/Operacion">
                    <wsdl:message name="{nombre}Request">
                        <wsdl:part element="dpi:entrada{nombre}" name="entrada"/>
                    </wsdl:message>
                    <wsdl:message name="{nombre}Response">
                        <wsdl:part element="dpi:salida{nombre}" name="salida"/>
                    </wsdl:message>
                </xsl:for-each>

            	<!-- ******************************************* -->
            	<!-- ******************************************* -->

                <wsdl:portType name="{//ServicioDataPi/nombre}">
                    <xsl:for-each select="//ServicioDataPi/operaciones/Operacion">
                        <wsdl:operation name="{nombre}">
                            <wsdl:input message="dpi:{nombre}Request"/>
                            <wsdl:output message="dpi:{nombre}Response"/>
                        </wsdl:operation>
                    </xsl:for-each>
                </wsdl:portType>

            	<!-- ******************************************* -->
            	<!-- ******************************************* -->

                <wsdl:binding name="{//ServicioDataPi/nombre}SOAP" type="dpi:{//ServicioDataPi/nombre}">
                    <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
                    <xsl:for-each select="//ServicioDataPi/operaciones/Operacion">
                        <wsdl:operation name="{nombre}">
                            <soap:operation soapAction="http://www.itosmosys.com/datapi/servicios/{nombre}"/>
                            <wsdl:input>
                                <soap:body use="literal"/>
                            </wsdl:input>
                            <wsdl:output>
                                <soap:body use="literal"/>
                            </wsdl:output>
                        </wsdl:operation>
                    </xsl:for-each>
                </wsdl:binding>

            	<!-- ******************************************* -->
            	<!-- ******************************************* -->

                <wsdl:service name="{//ServicioDataPi/nombre}">
                    <wsdl:port binding="dpi:{//ServicioDataPi/nombre}SOAP" name="{//ServicioDataPi/nombre}SOAP">
                    	<xsl:choose>
                    		<xsl:when test="//ServicioDataPi/prefijo='true'">
                    			<soap:address location="http://{//HEADER[name='host']/value}{$contextPath}/ws/{//ServicioDataPi/nombre}"/>		
                    		</xsl:when>
                    		<xsl:otherwise>
                    			<soap:address location="http://{//HEADER[name='host']/value}{$contextPath}/ws2/{//ServicioDataPi/nombre}"/>
                    		</xsl:otherwise>
                    	</xsl:choose>
                        
                    </wsdl:port>
                </wsdl:service>

            	<!-- ******************************************* -->
            	<!-- ******************************************* -->

            </wsdl:definitions>
        </xsl:if>
    </xsl:template>
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
</xsl:stylesheet>

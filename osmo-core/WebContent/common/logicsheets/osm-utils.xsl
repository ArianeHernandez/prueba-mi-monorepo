<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xsp="http://apache.org/xsp"
	xmlns:osm-utils="http://osmosyscol.com/logicsheets/osm-utils/1.0"
	xmlns:xscript="http://apache.org/xsp/xscript/1.0"
	>

	<xsl:template match="xsp:page">
		<xsp:page>
			<xsl:apply-templates select="@*"/>
			
			<xsp:structure>
				<xsp:include>java.io.IOException</xsp:include>
				<xsp:include>javax.xml.parsers.DocumentBuilder</xsp:include>
				<xsp:include>javax.xml.parsers.DocumentBuilderFactory</xsp:include>
				<xsp:include>javax.xml.parsers.ParserConfigurationException</xsp:include>
				<xsp:include>org.apache.cocoon.components.xscript.XScriptObjectInlineXML</xsp:include>
				<xsp:include>org.apache.commons.jxpath.JXPathContext</xsp:include>
				<xsp:include>org.apache.commons.jxpath.JXPathException</xsp:include>
				<xsp:include>org.apache.excalibur.xml.DefaultEntityResolver</xsp:include>
				<xsp:include>org.w3c.dom.Document</xsp:include>
				<xsp:include>org.apache.cocoon.components.language.markup.xsp.XSPUtil</xsp:include>
				<xsp:include>com.osmosyscol.commons.utils.JavaToXML</xsp:include>
				<xsp:include>com.osmosyscol.commons.servicio.AutenticacionServicio</xsp:include>
				<xsp:include>java.util.Enumeration</xsp:include>
				<xsp:include>java.util.HashMap</xsp:include>
				<xsp:include>java.util.Map</xsp:include>
				<xsp:include>com.osmosyscol.commons.log.SimpleLogger</xsp:include>
				<xsp:include>com.osmosyscol.commons.utils.ValidarTipoDato</xsp:include>
			</xsp:structure>
			
			
			<xsp:logic>
			
   				public Object getXPathFromXScript(String strVarName, String strXPath) {
					Object objPath = null;
					try {
						XScriptObjectInlineXML xobj = (XScriptObjectInlineXML) xscriptManager.get(pageScope, objectModel, strVarName, org.apache.cocoon.components.xscript.XScriptManager.REQUEST_SCOPE);
						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						dbf.setNamespaceAware(true);
						DocumentBuilder db = dbf.newDocumentBuilder();
						db.setEntityResolver(new DefaultEntityResolver());
						Document dom = db.parse(xobj.getInputSource());
						JXPathContext ctx = JXPathContext.newContext(dom);
						objPath = ctx.getValue(strXPath);
					} catch (ParserConfigurationException ce) {
					} catch (org.xml.sax.SAXException se) {
					} catch (IOException ioe) {
					} catch (JXPathException jxpe) {
					}
					if (objPath == null) {
						objPath = "";
					}
					return objPath;
				}
   				
   				public void includeToXML(String name, Object obj)
   				{
   					JavaToXML.include(name, obj, this.manager, this.contentHandler);
   				}
   				
   				public String getRequestParameter(String name)
   				{
   				
   					if(request.getParameter(name)==null){
   						
   						if(request.getAttribute("ADD_PARAMETERS")!=null){
   							Map mda = (Map)request.getAttribute("ADD_PARAMETERS");
   							return (String)mda.get(name);
   						}
   						
   						return null;
   					}
   					else{
   						return request.getParameter(name);
   					}
   				}
   				
   				private static Map obtenerMapRequest(org.apache.cocoon.environment.Request request) {
					Enumeration a = request.getParameterNames();
					Map res = new HashMap();
					while (a.hasMoreElements()) {
						String elem = (String) a.nextElement();
						res.put(elem, request.getParameter(elem));
					}
					
					return res;
				}
   				
   				public String osm_utils_include = null;
   				
   				public int osm_tmp = 0;
   				
   				public String osm_tmp_name = null;
   				public String osm_tmp_accion = null;
   				public String osm_tmp_service_name = null;
   				public String osm_tmp_operation = null;
   				public String osm_tmp_isname = null;
   				public String[] osm_tmp_parameters = null;
   				public String osm_tmp_prefix = null;
   				public String osm_tmp_one_prefix = null;
   				private String osm_tmp_prefixuri = null;
   				
			</xsp:logic>
			<xsl:apply-templates/>
		</xsp:page>
	</xsl:template>
	
<!-- 
============== UTILITY FUNCTIONS ============== 
-->
	<!--+ == Funcion para obtener un Objeto a partir de una variable xscript (con scope="request"), dada una expresion xpath == +-->
	<xsl:template match="osm-utils:get-xpath-from-xscript">	   
		<xsp:expr>getXPathFromXScript("<xsl:value-of select="@name"/>","<xsl:value-of select="@select"/>")</xsp:expr>
	</xsl:template>	

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="osm-utils:get-parameter">
		<xsp:expr>ValidarTipoDato.validateParameter(request, "<xsl:value-of select="@name"/>", "<xsl:value-of select="@type"/>", ("<xsl:value-of select="@maxlength"/>".length() == 0)?10000:new Integer("<xsl:value-of select="@maxlength"/>") ,com.osmosyscol.commons.utils.StringUtils.esVerdad("<xsl:value-of select="@required"/>"))</xsp:expr>		
	</xsl:template>
		
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="osm-utils:includexml">
		
		<xsp:logic> osm_utils_include = "<xsl:value-of select="osm-utils:src"/>?";</xsp:logic>
		
		<xsl:if test="count(osm-utils:java_src)>0">
			osm_utils_include = <xsl:value-of select="osm-utils:java_src"/> + "?";
		</xsl:if>

		<xsl:for-each select="osm-utils:parameters/osm-utils:parameter">
			<xsl:variable name="nn" select="osm-utils:name"/>
			<xsl:variable name="vv" select="osm-utils:value"/>
			<xsp:logic> osm_utils_include += <xsl:value-of select="$nn"/> + "=" + <xsl:value-of select="$vv"/> + "&amp;"; </xsp:logic>
		</xsl:for-each>

		<xsp:logic>
			try{
				XSPUtil.includeSource(osm_utils_include, null, this.resolver, this.contentHandler);
			}catch(Exception ine){
				<osm-include-error/>
			}
		</xsp:logic>
		
	</xsl:template>
	
	<xsl:template match="osm-utils:autorizado">
		
		<xsp:logic>
			if(AutenticacionServicio.getInstance().autorizarUrl(session, "<xsl:value-of select="@url"/>").booleanValue()){
				<autorizado>true</autorizado>
			}else{
				<autorizado>false</autorizado>
			}
		</xsp:logic>
		
	</xsl:template>
	
	<xsl:template match="osm-utils:ciclo">
		
		<xsp:logic>
			osm_tmp = <xsl:value-of select="osm-utils:repeticiones"/>;
			<xsl:for-each select="osm-utils:contenido">
				<xsp:logic>for(int i=0;osm_tmp>i;i++){ <xsl:apply-templates/> }	</xsp:logic>
			</xsl:for-each>
		</xsp:logic>
		
	</xsl:template>
	
	<xsl:template match="osm-utils:servicio-java">
		<xsp:logic>
		
		if( request.getRequestURI().indexOf(".ser_action") == -1 &amp;&amp; request.getRequestURI().indexOf(".do_action") == -1 &amp;&amp; request.getRequestURI().indexOf(".pub_action") == -1 )
		{
		
			try{
				osm_tmp_accion = "<xsl:value-of select="accion"/>";
				osm_tmp_name = "<xsl:value-of select="name"/>";
				osm_tmp_service_name = "<xsl:value-of select="service"/>";
				osm_tmp_operation = "<xsl:value-of select="operation"/>";
				osm_tmp_isname = "<xsl:value-of select="@isname"/>";
				osm_tmp_prefix = request.getParameter("osm_prefix");
				osm_tmp_one_prefix = request.getParameter("osm_one_prefix");
				osm_tmp_prefixuri = request.getParameter("osm_uri");
				
				osm_tmp_parameters = new String[<xsl:value-of select="count(parameters/parameter)"/>];
				<xsl:for-each select="parameters/parameter">
					<xsp:logic>
						osm_tmp_parameters[<xsl:value-of select="position()-1"/>] = "<xsl:value-of select="."/>";
					</xsp:logic>
				</xsl:for-each>
				
				if(osm_tmp_accion.length()==0 || (request.getParameter(osm_tmp_accion)!=null &amp;&amp; request.getParameter(osm_tmp_accion).trim().length()>0 ))
				{
					JavaToXML.includeOperationXML(osm_tmp_name, request, osm_tmp_parameters, osm_tmp_operation, osm_tmp_service_name, !"false".equals(osm_tmp_isname), osm_tmp_prefix, osm_tmp_prefixuri, (osm_tmp_one_prefix==null?null: new Boolean("true".equals(osm_tmp_one_prefix)) ), this.manager, this.contentHandler, this.resolver);
				}
			}catch(Throwable _eacc){
				System.out.println();
				System.out.println("ERROR: Fallo al cargar servicio.");
				System.out.println(_eacc);
			}
		}else{
			<NO-INCLUDE-SERVICE/>
		}
		</xsp:logic>
		
	</xsl:template>
	
	<xsl:template match="osm-utils:object-request">
		<xsp:logic>
		try{
			XSPUtil.includeString( JavaToXML.exe("<xsl:value-of select="@name"/>", JavaToXML.createObjectRequest( "<xsl:value-of select="@name"/>", request.getParameter("<xsl:value-of select="@name"/>.CLASSNAME"), request) ).toString() , this.manager, this.contentHandler);
		}catch(Exception ee){}
		</xsp:logic>
		
	</xsl:template>
	
	<xsl:template match="osm-utils:no-session">
		<xsp:logic>
			try{
	    		request.getSession(false).setAttribute("no-session","no-session");
	    	}catch(Exception e){
	    	}
	    </xsp:logic>
    	
        <NO-SESSION/>
	</xsl:template>

	<xsl:template match="osm-utils:remove-parameter">
		<xsp:logic>
			if(request.getSession(false)!=null){
				request.getSession().removeAttribute("var.<xsl:value-of select="@name"/>");
			}
		</xsp:logic>
	</xsl:template>
	
	<xsl:template match="osm-utils:exist-parameter">
		<xsp:logic>
			if( getRequestParameter("<xsl:value-of select="@name"/>") != null &amp;&amp; (<xsl:value-of select="count(@equals)"/> == 0 || "<xsl:value-of select="@equals"/>".equals(getRequestParameter("<xsl:value-of select="@name"/>").toString()) ) )
			{
				<xsl:apply-templates/>
			}
		</xsp:logic>
	</xsl:template>
	
	<xsl:template match="osm-utils:exist-attribute">
		<xsp:logic>
			if( request.getSession().getAttribute("<xsl:value-of select="@name"/>") != null &amp;&amp; (<xsl:value-of select="count(@equals)"/> == 0 || "<xsl:value-of select="@equals"/>".equals(request.getSession().getAttribute("<xsl:value-of select="@name"/>").toString()) ) )
			{
				<xsl:apply-templates/>
			}
		</xsp:logic>
	</xsl:template>

	<xsl:template match="osm-utils:if-xscript">
		<xsp:logic>
			if( "true".equalsIgnoreCase( getXPathFromXScript("<xsl:value-of select="@name"/>","<xsl:value-of select="@test"/>").toString()) )
			{
				<xsl:apply-templates/>
			}
		</xsp:logic>
	</xsl:template>

	<xsl:template match="osm-utils:no-exist-parameter">
		<xsp:logic>
			if( getRequestParameter("<xsl:value-of select="@name"/>") == null )
			{
				<xsl:apply-templates/>
			}
		</xsp:logic>
	</xsl:template>
		
	<xsl:template match="osm-utils:osm-session-request">
		<xsp:logic>
			if(request.getParameter("<xsl:value-of select="@name"/>")!=null)
			{
				session.setAttribute("var.<xsl:value-of select="@name"/>", request.getParameter("<xsl:value-of select="@name"/>"));
			}
			else if(session.getAttribute("var.<xsl:value-of select="@name"/>")==null){
			
				if(!"".equals("<xsl:value-of select="@default"/>"))
				{
					session.setAttribute("var.<xsl:value-of select="@name"/>", "<xsl:value-of select="@default"/>");
				}
				if(!"".equals("<xsl:value-of select="@xscript_default"/>"))
				{
					session.setAttribute("var.<xsl:value-of select="@name"/>", "" + getXPathFromXScript("<xsl:value-of select="@xscript_name"/>","<xsl:value-of select="@xscript_default"/>"));
				}
			}
			
			if("<xsl:value-of select="@view"/>".equals("true")){
				JavaToXML.include( "<xsl:value-of select="@name"/>", session.getAttribute("var.<xsl:value-of select="@name"/>") , this.manager, this.contentHandler, true);
			}
			
		</xsp:logic>
	</xsl:template>
	
	<xsl:template match="osm-utils:osm-get-request">
		<xsp:logic>
			JavaToXML.include("request", obtenerMapRequest(request), this.manager, this.contentHandler, true );
		</xsp:logic>
	</xsl:template>

	<xsl:template match="osm-utils:sysdate">
		<xsp:expr>System.currentTimeMillis()</xsp:expr>
	</xsl:template>
	
	<xsl:template match="osm-utils:osm-request-inputstream">

		<xsp:logic>
			{
				org.apache.cocoon.environment.http.HttpRequest ori_httprequest = (org.apache.cocoon.environment.http.HttpRequest) request;
	
				try {
					java.io.InputStream ori_is = new java.io.BufferedInputStream(ori_httprequest.getInputStream());
					String ori_stris = com.osmosyscol.commons.utils.StringUtils.toString(ori_is);
					request.setAttribute("OSM_REQUEST_INPUTSTREAM", ori_stris);
				} catch (Exception e) {
				}
			}
		</xsp:logic>

	</xsl:template>
	
	<xsl:template match="osm-utils:get-request-attribute">
		<xsp:logic>
			try{
				JavaToXML.include( "<xsl:value-of select="@name"/>", request.getAttribute("<xsl:value-of select="@name"/>") , this.manager, this.contentHandler,true);
			}catch(Exception ee){}
		</xsp:logic>
	</xsl:template>

	<xsl:template match="osm-utils:get-all-request-attributes">
		<xsp:logic>
			Enumeration attr = request.getAttributeNames();
	
			while(attr.hasMoreElements()){
				String attrname = (String)attr.nextElement();

				if(!"OSM_OBJECTMODEL".equals(attrname)){				
					try{
						JavaToXML.include( attrname, request.getAttribute(attrname) , this.manager, this.contentHandler,true);
					}catch(Exception ee){}
				}
				
			}
		</xsp:logic>
	</xsl:template>
		
	<xsl:template match="osm-utils:get-request-parameter">
		<xsp:expr>getRequestParameter("<xsl:value-of select="@name"/>")</xsp:expr>
	</xsl:template>
	
	<xsl:template match="osm-utils:get-session-attribute">
		<xsp:logic>
			try{
				if(request.getSession(false)!=null){
					JavaToXML.include( "<xsl:value-of select="@name"/>", request.getSession().getAttribute("<xsl:value-of select="@name"/>") , this.manager, this.contentHandler, true);
				}
			}catch(Exception ee){}
		</xsp:logic>
	</xsl:template>
	
	<xsl:template match="osm-utils:get-simple-session-attribute">
		<xsp:expr>(request.getSession(false)==null?null:request.getSession().getAttribute("<xsl:value-of select="@name"/>"))</xsp:expr>		
	</xsl:template>
	
	<xsl:template match="osm-utils:get-date-format">
		<xsp:logic>
			String _formato = "";
		
			try {
				java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
					"<xsl:value-of select="@format"/>");
				_formato = format.format(new Date());			
			} catch (Exception e) {}
		</xsp:logic>
		
		<xsp:expr>_formato</xsp:expr>
		
	</xsl:template>
	
	<xsl:template match="osm-utils:get-remote-address">
		<xsp:expr>request.getRemoteHost()</xsp:expr>
	</xsl:template>
	
	<xsl:template match="osm-utils:get-remote-client">
		<xsp:expr>com.osmosyscol.commons.utils.CocoonUtils.getRemoteAddr(request)</xsp:expr>
	</xsl:template>
	
	<xsl:template match="osm-utils:recaptcha-required">
		<xsp:expr>AutenticacionServicio.getInstance().solicitarRecaptcha(request)</xsp:expr>
	</xsl:template>
	
	<xsl:template match="osm-utils:recaptcha-required-h">
		<xsp:expr>AutenticacionServicio.getInstance().solicitarRecaptchaHost(com.osmosyscol.commons.utils.CocoonUtils.getRemoteAddr(request))</xsp:expr>
	</xsl:template>
	
<!-- 
============== TEMPLATE IDENTIDAD ============== 
-->		
	<xsl:template match="@*|node()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()"/>
		</xsl:copy>
	</xsl:template>
	
</xsl:stylesheet>

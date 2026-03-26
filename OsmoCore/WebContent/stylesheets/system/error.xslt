<?xml version="1.0"?>


<!-- CVS $Id$ -->

<!-- 	+
		|
		| BUG11991	boton no redirecciona al site principal cuando el recurso no existe
		| 	
		+ -->

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ex="http://apache.org/cocoon/exception/1.0">

  <xsl:param name="contextPath"/>
  <xsl:param name="realPath"/>


  <xsl:template match="ex:exception-report">
    <html>
      <head>
        <title>
          Lo sentimos...
        </title>
        <link href="{$contextPath}/icons/icon.ico" rel="shortcut icon" />
        <style>
        	body, html{
        		padding: 0px;
        		margin: 0px;
        		background-color: #E0DFE3;
        	}
        	
        	#area_error{
        		
        		display: block;
        		position: relative;
        		left: -300px;
        		top: 50px;
        		margin-top: 0px;
        		margin-left: 50%;
        		width: 600px;
        		background-color: white;
        		padding: 30px 30px 20px 30px;
        		border: 1px solid #9D9DA1;
        	}
        	
        	h1{
        		margin: 5px 0px 15px 0px;
        		font-size: 20px;
        		font-weight: bold;
        		border-bottom: 1px solid #CCCCCC;
        	}
        	
        	p{
        		margin: 5px 0px 5px 0px;
        		font-size: 14px;
        		font-weight: normal;
        	}
        	
        	input[type='button']{
        		margin: 20px 5px 20px 0px;
        	}
        </style>
      </head>
      <body>
      
      <div id="area_error">
      	<h1>Lo Sentimos..</h1>
      	<p>No se puede realizar la operación.</p>
      	<p>Por favor, regresa a la pagina de inicio e intenta nuevamente realizar la operación.</p>
      	<p>Si el problema continua, consulta con el administrador.</p>
      	<!-- #region 11991  -->
      	<input type="button" value="Ir al Inicio" onclick="location.href='{$contextPath}/'" />
      	<!-- #endregion 11991  -->
      </div>
      
      
    </body>
    </html>
    
  </xsl:template>

</xsl:stylesheet>

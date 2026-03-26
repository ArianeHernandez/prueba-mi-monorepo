<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:include href="context://common/xsl/osm_html.xsl" />

	<xsl:param name="contextPath" />
	
    <xsl:template match="email">
        <html>
            <body>
            	<div style="background-color: #f1f1f1; padding-top: 10px;">
	            	<div style="margin: 30px 20px 20px 30px; padding: 1.2vw; width:auto;">
	            		<img style="width:30%;" src="#logo#"/>
	            		<img style="float:right; width: 9vw;" src="#logo_potencia#"/>
	            	</div>
	            	<div style="padding: 0px 0px 20px 0px; background-color: #f1f1f1; margin-top: 10px; margin-bottom: 10px;">
	
						<div style="background-color:#FFFFFF; width:auto; padding: 0px; margin: 20px; border: 1px solid #CCCCCC; font-family:Arial, Helvetica, sans-serif; border-radius: 5px;">
							<div style="background-color: #3C6593; border-bottom: 2px solid #1B304D; height: auto; padding-top:10px; padding-bottom: 10px; border-radius: 5px 5px 0 0;">
						    	<div style="font-size: 18px; font-weight: normal; color:#FFFFFF; text-indent: 20px;"><xsl:value-of select="titulo"/></div>
						    </div>
						    
						    <div style="padding: 20px; font-size: 14px;">
						    	<xsl:apply-templates select="contenido"/>
						    </div>
						</div>
					</div>
				</div>
            	
            </body>
        </html>
    </xsl:template>
	
	<xsl:template match="parrafo">
        <p style="font-size: 12px; font-weight: normal; color:#333333; margin:0px; padding:0px;">
            <xsl:apply-templates/>
        </p>
    </xsl:template>
	    
	<xsl:template match="bloque">
		<div>
			<xsl:apply-templates/>
		</div>
	</xsl:template>
    
	<xsl:template match="registro">
		
		<table style="width: 100%;">
			<tr>
    			<td style="border: solid 0px; width: 30%; vertical-align: top;">
    				 <h3 style="font-size: 12px; font-weight: bold; color:#006325">
		                <xsl:value-of select="item"/>
		            </h3>
	            </td>
				
				<td style="border: solid 0px;">
		            <xsl:apply-templates select="valor"/>
	            </td>
            </tr>
			
		</table>
		
    </xsl:template>
    
	<xsl:template match="constante">
        <span style="font-size: 12px; font-weight: bold; color:#FF0000;"><xsl:value-of select="."/></span>
    </xsl:template>

	<xsl:template match="subtitulo">
        <h2>
            <xsl:value-of select="."/>
        </h2>
    </xsl:template>

	<xsl:template match="tabla">
		
		<div>
			<table cellspacing="0" cellpadding="0">
				<xsl:for-each select="encabezado">
					<tr>
						<xsl:for-each select="titulo">
							<td><h3><xsl:value-of select="."/></h3></td>
						</xsl:for-each>
					</tr>
				</xsl:for-each>
				<xsl:for-each select="fila">
					<tr>
						<xsl:for-each select="valor">
							<td>
								<xsl:value-of select="."/>
							</td>
						</xsl:for-each>
					</tr>
				</xsl:for-each>
				
			</table>
		</div>
		
	</xsl:template>
	
	<xsl:template match="escapar">
		<xsl:copy-of select="*"/>
	</xsl:template>
	
	<xsl:template match="imagen">
		<img SRC="{@id}"/>
	</xsl:template>
	
	<xsl:template match="@*|node()">
	    <xsl:copy>
	      <xsl:apply-templates select="@*|node()"/>
	    </xsl:copy>
	 </xsl:template>
	
</xsl:stylesheet>

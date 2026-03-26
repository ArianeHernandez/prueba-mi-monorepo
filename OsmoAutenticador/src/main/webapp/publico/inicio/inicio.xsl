<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<stylesheet>principal.css</stylesheet>
		<CONTENIDO>
			<a href="aplicaciones.pub" style="color: #000;">Nueva Versión</a>
			<table>
				<tr>
					<xsl:for-each select="//obtenerAplicaciones/listado/Aplicacion">
						<td>
							<xsl:call-template name="view_aplicacion"/>
						</td>
					</xsl:for-each>
				</tr>
			</table>
			
		</CONTENIDO>
		
	</xsl:template>
	
	<xsl:template name="view_aplicacion">
		
		<table class="aplicacion">
			<tr>
				<th>(<xsl:value-of select="id_aplicacion"/>) <xsl:value-of select="nombre"/></th>
			</tr>
			
			<tr>
				<td>
					<table>
						<tr>
							<xsl:for-each select="roles/Rol">
								<td>
									<xsl:call-template name="view_rol"/>&#160;
								</td>
							</xsl:for-each>
						</tr>
					</table>
				</td>
			</tr>
			
		</table>
		
	</xsl:template>

	<xsl:template name="view_rol">
		
		<table class="rol">
			<tr>
				<th>(<xsl:value-of select="id_rol"/>) <xsl:value-of select="nombre"/></th>
			</tr>
			
			<tr>
				<td>
					<table>
						<tr>
							<xsl:for-each select="servicios/Servicio">
								<td>
									<xsl:call-template name="view_servicio"/>&#160;
								</td>
							</xsl:for-each>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td>
					<table class="usuario">
						<tr><th>Usuarios</th></tr>
						<xsl:for-each select="usuarios/Usuario">
							<tr><td><xsl:value-of select="concat('(',id_usuario, ') ', login)"/></td></tr>
						</xsl:for-each>
					</table>
				</td>
			</tr>
			
		</table>
		
		
		
	</xsl:template>
	
	<xsl:template name="view_servicio">
		
		<table class="servicio">
			<tr>
				<th>(<xsl:value-of select="id_servicio"/>) <xsl:value-of select="nombre"/></th>
			</tr>
			
			<tr>
				<td>
					<table class="url">
						<xsl:for-each select="urls/UrlServicio">
							<tr>
								<td>
									<xsl:value-of select="url"/>&#160;
								</td>
							</tr>
						</xsl:for-each>&#160;
						
					</table>
				</td>
			</tr>
			
		</table>
		
	</xsl:template>
	
</xsl:stylesheet>

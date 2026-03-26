<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Bienvenido" >
		
			<principal>
				
				<titulo>Datasuite || Version: <xsl:value-of select="VERSION_APLICACION"/></titulo>
				<contenido>
				
					<bloque-pestanas>
						<pestana titulo="Soporte">
							 se deben listar los logs
							 
							 <table class="table table-hover table-striped tb">
								<thead>
									<tr class="tabla_encabezado">
										<td class="">Archivo</td>
										<td class="">Ver</td>
										<td class="">Tamaño</td>
										<td class="">Última Fecha Modificación</td>
										
									</tr>
								</thead>
								
								
								<tbody id="PLANTILLA_FILA">
									<xsl:for-each select="//info/LogFile">
										<tr class="tabla_fila" id="fila_[ 1 ]">
											<td><xsl:value-of select="name"/> </td>
											<td><boton accion="osm_setValor('nombre', '{name}'); osm_enviarFormulario('log'); osm_unblock_window();">Descargar</boton></td>
											<td><xsl:value-of select="size"/> </td>
											<td><xsl:value-of select="lastModified"/></td>
										</tr>
									</xsl:for-each>
								</tbody>
							</table>
							 
							 
							 <form action=".downloadLog" id="log">
							 	<input type="hidden" id="nombre" name="nombre" value=""/>
							 </form>

						</pestana>
					</bloque-pestanas>
				
				</contenido>
				
			</principal>			
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>

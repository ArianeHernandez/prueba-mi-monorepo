<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Administración de Listas Dinamicas">
			
			<javascript>admin/10.1.js</javascript>
			
			<principal>
				<titulo icono="listavalores">Administración de Listas Dinamicas</titulo>
				<contenido>
					
					<xsl:choose>
						<xsl:when test="guardarListaDinamica/exitoso = 'true'">
							<parrafo estilo="resaltado">Se ha guardado exitosamente.</parrafo>	
						</xsl:when>
						
						<xsl:when test="guardarListaDinamica/exitoso = 'false'">
							<parrafo estilo="resaltado">Lo sentimos, no se ha podido guardar la lista dinamica.</parrafo>	
						</xsl:when>
						
					</xsl:choose>
					
					<xsl:choose>
						<xsl:when test="desactivarListaDinamica/exitoso = 'true'">
							<parrafo estilo="resaltado">Se ha eliminado exitosamente.</parrafo>	
						</xsl:when>
						
						<xsl:when test="desactivarListaDinamica/exitoso = 'false'">
							<parrafo estilo="resaltado">Lo sentimos, no se ha podido eliminar la lista dinamica.</parrafo>	
						</xsl:when>
						
					</xsl:choose>
					
					<xsl:choose>
						<xsl:when test="count(//obtenerListasDinamicas/listado/ListaDinamica)>0">
						
							<formulario destino="listasdinamicas/10.2.do" id="form_listasdinamicas">
								<variable id="id_lista_dinamica" valor=""/>
							</formulario>
							
						 	<tabla icono="list">
								<encabezado>
									<titulo>Lista Dinamica</titulo>
									<titulo>Descripción</titulo>
								</encabezado>
								<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
									<xsl:sort select="nombre"/>
									<fila id="fila_listavalor_{position()}" accion="editarListadinamica({id_lista_dinamica})">
										<valor>
											<p>D<xsl:value-of select="id_lista_dinamica"/> | <b><xsl:value-of select="nombre"/></b></p>
										</valor>
										<valor><xsl:value-of select="descripcion"/></valor>
									</fila>
								</xsl:for-each>
							</tabla>
							
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen listas dinamicas.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<boton estilo="crear" destino="listasdinamicas/10.2.do">Crear Nueva</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>

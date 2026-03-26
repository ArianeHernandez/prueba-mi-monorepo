<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:template match="OSM-ACCION">
    	
        <pagina_simple>
        	
        	<javascript>tabla_control/34.js</javascript>
        	
            
        	<table class="tbcontrol1 ctr">
				<thead>
					<tr class="tittle">
						<th colspan="12"><h1>Estados de <texto key="RETIROS" /></h1></th>
					</tr>
					<tr>
						<th>Identificador <texto key="CARGA" /></th>
						<th>Cliente</th>
						<th>Nombre Tren</th>
						<th>Monto</th>
						<th>Instancia Tren</th>
						<th>Hora Recepción</th>
						<th>Tiempo en Cola</th>
					</tr>
				</thead>
				<tbody>
					
					<xsl:for-each select="//obtenerCargasEnProceso/Listado/cargas/Carga">
						<xsl:sort select="porcentaje" data-type="number" order="descending"/>
						<tr>
							<xsl:choose>
								<xsl:when test="porcentaje>=100">
									<xsl:attribute name="class">bgc5</xsl:attribute>
								</xsl:when>
								
								<xsl:when test="porcentaje>=50">
									<xsl:attribute name="class">bgc4</xsl:attribute>
								</xsl:when>
								
								<xsl:otherwise>
									<xsl:attribute name="class">bgc3</xsl:attribute>
								</xsl:otherwise>
								
							</xsl:choose>
							
							
							<td><xsl:value-of select="id_carga"/></td>
							<td><xsl:value-of select="concat(nombre_cliente, ' ', apellido_cliente)"/></td>
							<td><xsl:value-of select="nombre_proceso"/></td>
							<td class="rgh"><b>$</b><xsl:value-of select="format-number(valor_lote, '###.##0,00', 'pesos')"/></td>
							<th class="bgc1"><xsl:value-of select="nombre_instancia"/></th>
							<td><xsl:value-of select="fecha_llegadaStr"/></td>
							<td><xsl:value-of select="tiempo_cola"/> min / <xsl:value-of select="tiempo_instancia"/> min </td>
						</tr>
						
					</xsl:for-each>
					
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3"><strong>Totales</strong></td>
						<td class="rgh"><b>$</b><xsl:value-of select="format-number(sum(//obtenerCargasEnProceso/Listado/valores/*), '###.##0,00', 'pesos')"/></td>
					</tr>
				</tfoot>	
			</table>
        	
        	
        </pagina_simple>
    </xsl:template>
</xsl:stylesheet>

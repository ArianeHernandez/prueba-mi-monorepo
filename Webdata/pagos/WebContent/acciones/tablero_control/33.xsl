<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:template match="OSM-ACCION">
    	
        <pagina_simple>
        	
        	<javascript>tabla_control/33.js</javascript>
        	
            <table class="tbcontrol1 ctr">
                <thead>
					<tr class="tittle">
						<th colspan="15"><h1><texto key="RETIROS" /> ACH</h1></th>
					</tr>
					<tr>
						<th rowspan="2" class="bgc1 w070"><h4>Banco Destino</h4></th>
						<th colspan="2" class="bgc2"><h2>Recibidos</h2></th>
						<th colspan="2" class="bgc5"><h2>Pendientes</h2></th>
						<th colspan="2" class="bgc4"><h2>Tránsito</h2></th>
						<th colspan="2" class="bgc3"><h2>Aprobados</h2></th>
						<th colspan="2" class="bgc3"><h2>En Archivo de Giro</h2></th>
						<th colspan="2" class="bgc6"><h2>Rechazados</h2></th>
						<th colspan="2" class="bgc6"><h2>Rechazados En Banco</h2></th>
					</tr>
					<tr>
						<th class="bgc2"># registro</th>
						<th class="bgc2">monto registro</th>
						
						<th class="bgc5"># registro</th>
						<th class="bgc5">monto registro</th>
						
						<th class="bgc4"># registro</th>
						<th class="bgc4">monto registro</th>
						
						<th class="bgc3"># registro</th>
						<th class="bgc3">monto registro</th>
						
						<th class="bgc3"># registro</th>
						<th class="bgc3">monto registro</th>
						
						<th class="bgc6"># registro</th>
						<th class="bgc6">monto registro</th>
						
						<th class="bgc6"># registro</th>
						<th class="bgc6">monto registro</th>
						
					</tr>
				
				</thead>
                <tbody>
                	
                	<xsl:for-each select="//obtenerBancos/Listado/Banco">
                	
                        <tr class="tr1">
                        	<xsl:if test="position() mod 2 = 0">
                        		<xsl:attribute name="class">tr2</xsl:attribute>
                        	</xsl:if>
                        	
                            <td class="bgc1"><div class="pic bnc_{nombre}" title="{nombre}"/></td>
                            
                        	<td class="bgc2"><xsl:value-of select="num_recibidos"/></td>
                            <td class="bgc2 rgh"><b>$</b><xsl:value-of select="format-number(total_recibidos, '###.##0,00', 'pesos')"/></td>

                            <td class="bgc5"><xsl:value-of select="num_pendiente"/></td>
                        	<td class="bgc5 rgh"><b>$</b><xsl:value-of select="format-number(total_pendiente, '###.##0,00', 'pesos')"/></td>

                            <td class="bgc4"><xsl:value-of select="num_transito"/></td>
                        	<td class="bgc4 rgh"><b>$</b><xsl:value-of select="format-number(total_transito, '###.##0,00', 'pesos')"/></td>

                            <td class="bgc3"><xsl:value-of select="num_aprobado"/></td>
                        	<td class="bgc3 rgh"><b>$</b><xsl:value-of select="format-number(total_aprobados, '###.##0,00', 'pesos')"/></td>

                            <td class="bgc3"><xsl:value-of select="num_agrupado"/></td>
                        	<td class="bgc3 rgh"><b>$</b><xsl:value-of select="format-number(total_agrupados, '###.##0,00', 'pesos')"/></td>

                            <td class="bgc6"><xsl:value-of select="num_rechazos"/></td>
                        	<td class="bgc6 rgh"><b>$</b><xsl:value-of select="format-number(total_rechazos, '###.##0,00', 'pesos')"/></td>

                            <td class="bgc6"><xsl:value-of select="num_rechazo_banco"/></td>
                        	<td class="bgc6 rgh"><b>$</b><xsl:value-of select="format-number(total_rechazo_banco, '###.##0,00', 'pesos')"/></td>

                        </tr>
                    
                    </xsl:for-each>
                	
                </tbody>

            	<xsl:variable name="bancos" select="//obtenerBancos/Listado/Banco"></xsl:variable>
            	<xsl:variable name="total" select="sum($bancos/num_recibidos) + sum($bancos/num_transito) + sum($bancos/num_aprobado) + sum($bancos/num_rechazos)"></xsl:variable>

                <tfoot>
                    <tr class="tf1">
                    	
                        <th class="bgc1" rowspan="2"> Totales</th>
                        <th class="bgc2"><xsl:value-of select="sum($bancos/num_recibidos)"/></th>
                        <th class="bgc2 rgh"><b>$</b><xsl:value-of select="format-number(sum($bancos/total_recibidos), '###.##0,00', 'pesos')"/></th>
                    	
                        <th class="bgc5"><xsl:value-of select="sum($bancos/num_pendiente)"/></th>
                        <th class="bgc5 rgh"><b>$</b><xsl:value-of select="format-number(sum($bancos/total_pendiente), '###.##0,00', 'pesos')"/></th>
                    	
                        <th class="bgc4"><xsl:value-of select="sum($bancos/num_transito)"/></th>
                        <th class="bgc4 rgh"><b>$</b><xsl:value-of select="format-number(sum($bancos/total_transito), '###.##0,00', 'pesos')"/></th>
                    	
                        <th class="bgc3"><xsl:value-of select="sum($bancos/num_aprobado)"/></th>
                        <th class="bgc3 rgh"><b>$</b><xsl:value-of select="format-number(sum($bancos/total_aprobados), '###.##0,00', 'pesos')"/></th>
                    	
                    	<th class="bgc3"><xsl:value-of select="sum($bancos/num_agrupado)"/></th>
                        <th class="bgc3 rgh"><b>$</b><xsl:value-of select="format-number(sum($bancos/total_agrupados), '###.##0,00', 'pesos')"/></th>
                    	
                        <th class="bgc6"><xsl:value-of select="sum($bancos/num_rechazos)"/></th>
                        <th class="bgc6 rgh"><b>$</b><xsl:value-of select="format-number(sum($bancos/total_rechazos), '###.##0,00', 'pesos')"/></th>
                    	
                    	<th class="bgc6"><xsl:value-of select="sum($bancos/num_rechazo_banco)"/></th>
                        <th class="bgc6 rgh"><b>$</b><xsl:value-of select="format-number(sum($bancos/total_rechazo_banco), '###.##0,00', 'pesos')"/></th>
                    	
                    </tr>
                    <tr class="tf2">
                        <th class="bgc2" colspan="2">
                            <h3>Recibidos <strong><xsl:value-of select="format-number( (sum($bancos/num_recibidos) * 100) div number($total) ,'0,0', 'pesos')"/>%</strong>
                            </h3>
                        </th>
                        <th class="bgc5" colspan="2">
                            <h3>Pendientes <strong><xsl:value-of select="format-number( (sum($bancos/num_pendiente) * 100) div number($total) ,'0,0', 'pesos')"/>%</strong>
                            </h3>
                        </th>
                        <th class="bgc4" colspan="2">
                            <h3>Tránsito <strong><xsl:value-of select="format-number( (sum($bancos/num_transito) * 100) div number($total) ,'0,0', 'pesos')"/>%</strong>
                            </h3>
                        </th>
                        <th class="bgc3" colspan="2">
                            <h3>Aprobados <strong><xsl:value-of select="format-number( (sum($bancos/num_aprobado) * 100) div number($total) ,'0,0', 'pesos')"/>%</strong>
                            </h3>
                        </th>
                        <th class="bgc3" colspan="2">
                            <h3>En Archivo <strong><xsl:value-of select="format-number( (sum($bancos/num_agrupado) * 100) div number($total) ,'0,0', 'pesos')"/>%</strong>
                            </h3>
                        </th>
                        <th class="bgc6" colspan="2">
                            <h3>Rechazados <strong><xsl:value-of select="format-number( (sum($bancos/num_rechazos) * 100) div number($total) ,'0,0', 'pesos')"/>%</strong>
                            </h3>
                        </th>
                        <th class="bgc6" colspan="2">
                            <h3>Rechazados Banco <strong><xsl:value-of select="format-number( (sum($bancos/num_rechazo_banco) * 100) div number($total) ,'0,0', 'pesos')"/>%</strong>
                            </h3>
                        </th>
                    </tr>
                </tfoot>
            </table>
        </pagina_simple>
    </xsl:template>
</xsl:stylesheet>

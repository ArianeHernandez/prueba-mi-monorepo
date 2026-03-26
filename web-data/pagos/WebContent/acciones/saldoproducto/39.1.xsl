<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Consulta de Saldos">

			<principal>
				<titulo>Consulta de saldos</titulo>
				<contenido>
					
					
					<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
						<thead>
							<tr class="tabla_encabezado">
								<td class="w20p">Negocio</td>
								<td class="w20p">Producto</td>
								<td class="w20p">Cuenta</td>
								<td class="w20p">Saldo Disponible</td>
								<td class="w20p">Saldo Total</td>
							</tr>
						</thead>
						<tbody id="cont_formatos">
							<xsl:for-each select="//obtenerNegociosPorUsuario/listado/Negocio">
								<xsl:variable name="nombre_negocio" select="nombre"></xsl:variable>
								<xsl:variable name="id_negocio" select="id_negocio"></xsl:variable>
								<xsl:variable name="sum_saldo_disp" select="sum(//obtenerSaldosProductoPorCliente/Listado/SaldoProducto[id_negocio=$id_negocio]/saldo_disponible)"></xsl:variable>
								<xsl:variable name="sum_saldo_total" select="sum(//obtenerSaldosProductoPorCliente/Listado/SaldoProducto[id_negocio=$id_negocio]/saldo_total)"></xsl:variable>
								<xsl:variable name="productosPorNegocio" select="//obtenerSaldosProductoPorCliente/Listado/SaldoProducto[id_negocio=$id_negocio]"></xsl:variable>
								
								
									<xsl:for-each select="$productosPorNegocio">
									 	<tr class="tabla_fila">
									 		<xsl:if test="position()=1">
									 			<td rowspan="{count($productosPorNegocio)}" class="tabla_fila_rowspan">	
													<xsl:value-of select="nombre_negocio"/>
												</td>
											</xsl:if>
									 	
											
											<td>									 
												<xsl:value-of select="nombre_producto"/>
											</td>
											
											<td>									 
												<xsl:value-of select="codigo"/>
											</td>
										
											<td class="money">
												<xsl:value-of select="format-number(saldo_disponible,'##,##0.00')"/>
											</td>
										
											<td class="money">
												<xsl:value-of select="format-number(saldo_total,'##,##0.00')"/>
											</td>	
										</tr>	
									
									</xsl:for-each>
									
									<xsl:if test="count($productosPorNegocio)>0">	
										<tr class="tabla_fila_over">
											<td class="money">	
												Subtotal
											</td>
											<td>	
												--
											</td>
											<td>	
												--
											</td>
											<td class="money">
												<xsl:value-of select="format-number($sum_saldo_disp,'##,##0.00')"></xsl:value-of>
											</td>
											
											<td class="money">
												<xsl:value-of select="format-number($sum_saldo_total,'##,##0.00')"></xsl:value-of>
											</td>
										
										</tr>
									</xsl:if>	
							
							</xsl:for-each>
							
							
							<xsl:variable name="total_saldo_disp" select="sum(//obtenerSaldosProductoPorCliente/Listado/SaldoProducto[id_negocio=//obtenerNegociosPorUsuario/listado/Negocio/id_negocio]/saldo_disponible)"></xsl:variable>
							<xsl:variable name="total_saldo_total" select="sum(//obtenerSaldosProductoPorCliente/Listado/SaldoProducto[id_negocio=//obtenerNegociosPorUsuario/listado/Negocio/id_negocio]/saldo_total)"></xsl:variable>
				
							<tr class="tabla_fila">
								<td class="money">
									<b>Total</b>
								</td>
								
								<td>
									--
								</td>
								<td>	
									--
								</td>
					
								<td class="money">
									<b><xsl:value-of select="format-number($total_saldo_disp,'##,##0.00')"></xsl:value-of></b>
								</td>
								
								<td class="money">
									<b><xsl:value-of select="format-number($total_saldo_total,'##,##0.00')"></xsl:value-of></b>
								</td>
								
							</tr>
						</tbody>
					</table>
					
					
				</contenido>
			</principal>
		</pagina>

	</xsl:template>
	
</xsl:stylesheet>

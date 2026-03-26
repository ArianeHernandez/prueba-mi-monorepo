package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto.CuentaBanco;
import com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto.Producto;
import com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto.ProductoCuentaDefecto;
import com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto.ProductoCuentas;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ConfCuentaACHServicio {
	
	// ----------------------------------------------------
	
	private static ConfCuentaACHServicio confCuentaACH;
	
	private static String SQL_CUENTAS = "SELECT * FROM $CUENTA - BANCO$ WHERE 1 = 1 ";
	
	// ----------------------------------------------------
	
	private ConfCuentaACHServicio(){
	}
	
	// ----------------------------------------------------
	
	public static ConfCuentaACHServicio getInstance(){
		if (confCuentaACH == null){
			confCuentaACH = new ConfCuentaACHServicio();
		}
		return confCuentaACH;
	}
	
	// ----------------------------------------------------
	
	public List<CuentaBanco> obtenerListadoCuentaACH(){
		// solo cuentas activas ...
		String sql =  SQL_CUENTAS ;
		return DS_SqlUtils.queryForList(CuentaBanco.class, sql);
	}
	
	// ----------------------------------------------------
	
	public CuentaBanco obtenerCuentaACH(Integer id){
		String sql =  SQL_CUENTAS + "AND $CUENTA - BANCO$.ID = " + id;
		return DS_SqlUtils.queryForObject(CuentaBanco.class, sql);
	}
	
	// ----------------------------------------------------	// ----------------------------------------------------	// ----------------------------------------------------
	
	public List<CuentaBanco> obtenerCuentasPorProducto(Integer id_producto){
		String sql =  "SELECT "
				+ " ($BANCO.NOMBRE$ || ' - ' || $CUENTA - BANCO.CUENTA BANCARIA$) AS $CUENTA - BANCO.CUENTA BANCARIA$, " //
				+ " $CUENTA - BANCO$.* FROM $CUENTA - BANCO$, $BANCO$ " //
				+ " WHERE $CUENTA - BANCO.BANCO$ = $BANCO$.ID " //
				+ " AND $CUENTA - BANCO.PRODUCTO$ = " + id_producto;
		return DS_SqlUtils.queryForList(CuentaBanco.class, sql);
	}
	
	// ----------------------------------------------------	// ----------------------------------------------------	// ----------------------------------------------------

	public Integer obtenerIdCuentaPorDefectoProducto(Integer id_producto){
		String sql =  "SELECT ID FROM $CUENTA - BANCO$ WHERE 1 = 1 AND $CUENTA - BANCO.POR DEFECTO$ LIKE($B(true)$) AND $CUENTA - BANCO.PRODUCTO$ = " + id_producto;
		return DS_SqlUtils.queryForObject(Integer.class, sql);
	}
	
	// ----------------------------------------------------	// ----------------------------------------------------	// ----------------------------------------------------
	
	public Boolean actualizarCuentaPorDefecto(Integer idProducto, Integer idCuenta){
		Boolean retsp = false;
		if (idCuenta != null && idProducto != null) {
			retsp = true;
//			System.out.println(new Gson().toJson(quitarCuentaPorDefectoProducto(idProducto)));
			quitarCuentaPorDefectoProducto(idProducto);
//			System.out.println(new Gson().toJson(ponerCuentaPorDefectoProducto(idProducto, idCuenta)));
			ponerCuentaPorDefectoProducto(idProducto, idCuenta);
			return retsp;
		}else{
			return retsp;
		}
	}

	// ----------------------------------------------------	// ----------------------------------------------------	// ----------------------------------------------------
	
	public Boolean actualizarCuentasPorDef(ProductoCuentaDefecto[] productoCuentaDef){
		
		DaoManager daoManager = DaoConfig.getDaoManager(2);
		Boolean completado = true;
		try {
			daoManager.startTransaction();
			for (ProductoCuentaDefecto prodCuentaD : productoCuentaDef) {
				if (completado) {
					completado = actualizarCuentaPorDefecto(prodCuentaD.getIdProducto(), prodCuentaD.getIdCuenta());
//					System.out.println("Key: "+ prodCuentaD.getIdProducto() +" CuentaDEf: "+ prodCuentaD.getIdCuenta());
				}else{
					break;
				}
			}
			if (completado) {
				daoManager.commitTransaction();
			}
			return completado;
			
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				daoManager.endTransaction();
			}
	}
	
	// ----------------------------------------------------	// ----------------------------------------------------	// ----------------------------------------------------
	
	public Boolean quitarCuentaPorDefectoProducto(Integer idProducto){
//					   UPDATE $CUENTA - BANCO$ SET $CUENTA - BANCO.POR DEFECTO$ = ($B(false)$) WHERE $CUENTA - BANCO.PRODUCTO$ = 14905088
		String sql =  "UPDATE $CUENTA - BANCO$ SET $CUENTA - BANCO.POR DEFECTO$ = ($B(false)$) WHERE $CUENTA - BANCO.PRODUCTO$ = " + idProducto;
		return DS_SqlUtils.update(sql);
	}

	// ----------------------------------------------------	// ----------------------------------------------------	// ----------------------------------------------------
	
	public Boolean ponerCuentaPorDefectoProducto(Integer idProducto, Integer idCuenta){
//					   UPDATE $CUENTA - BANCO$ SET $CUENTA - BANCO.POR DEFECTO$ = ($B(true)$) WHERE $CUENTA - BANCO$.ID = 14918537
		String sql =  "UPDATE $CUENTA - BANCO$ SET $CUENTA - BANCO.POR DEFECTO$ = ($B(true)$) WHERE $CUENTA - BANCO.PRODUCTO$ = "+ idProducto +" AND $CUENTA - BANCO$.ID = " + idCuenta;
		return DS_SqlUtils.update(sql);
	}
	
	// ----------------------------------------------------	// ----------------------------------------------------	// ----------------------------------------------------
	
		public  List<ProductoCuentas> obtenereProductosCuentas(){
			List<ProductoCuentas> listaReturn = new ArrayList<ProductoCuentas>();
			
			List<Producto> listaProductos = ProductoServicio.getInstance().obtenerProductosActivos();
			for (Producto producto : listaProductos) {
				if (producto != null) {
					
//					producto.setCuentaDefecto(obtenerIdCuentaPorDefectoProducto(producto.getId()));
					ProductoCuentas productoCuentas = new ProductoCuentas();
					productoCuentas.setProducto(producto);
					List<CuentaBanco> listaCuentas = obtenerCuentasPorProducto(producto.getId());
					if (listaCuentas != null) {
						productoCuentas.setCuentas(listaCuentas);
					}
					listaReturn.add(productoCuentas);
				}
			}
			return listaReturn;
		}
	

}

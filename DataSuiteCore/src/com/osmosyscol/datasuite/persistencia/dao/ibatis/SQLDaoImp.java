package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class SQLDaoImp extends BaseSqlMapDao implements SQLDao {

	public SQLDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public void insertSQL(String exesql) {

		update("SQL.insertSQL", exesql);

	}

	public Integer updateSQL(String updatesql) {

		return update("SQL.updateSQL", updatesql);

	}

	public Integer deleteSQL(String deletesql) {

		return delete("SQL.deleteSQL", deletesql);

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectSQL(String selectsql) {

		return queryForList("SQL.selectSQL", selectsql);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectSQLRegistro(String selectsql) {

		return (Map<String, Object>) queryForObject("SQL.selectSQL", selectsql);

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectSQLPaginado(String selectsql, Integer numpagina, Integer tamanoPagina) {

		if (tamanoPagina == null) {
			tamanoPagina = com.osmosyscol.datasuite.config.Constantes.PAGINACIONLISTADO;
		}
		
		String url = DaoConfig.getUrl(daoManager);

		if (url.indexOf("postgresql") > 0) {
			return queryForList("SQL.selectSQL", selectsql + " limit " + tamanoPagina + " offset " + ((numpagina - 1) * tamanoPagina));
		} else {
			return queryForList("SQL.selectSQL", "select * from ( select p_.*, rownum rn from ( "+  selectsql + " ) p_ where rownum <= " + (tamanoPagina * numpagina) +  " ) q_ where q_.rn > " + (tamanoPagina * (numpagina - 1)) +  " order by q_.rn");
		}

	}

	public Integer selectSQLNumber(String selectSQL) {

		return (Integer) queryForObject("SQL.selectSQLNumber", selectSQL);
	}

	public String selectSQLString(String selectSQL) {

		return (String) queryForObject("SQL.selectSQLString", selectSQL);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> selectSQLNumberList(String selectSQL) {
		return queryForList("SQL.selectSQLNumber", selectSQL);
	}

}

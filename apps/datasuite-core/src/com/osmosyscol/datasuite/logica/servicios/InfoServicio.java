package com.osmosyscol.datasuite.logica.servicios;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ClassUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.XMLFormat;
import com.osmosyscol.datasuite.logica.dto.AuditoriaSql;
import com.osmosyscol.datasuite.persistencia.dao.AuditoriaSqlDao;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.system.dto.LogFile;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class InfoServicio {

	private static InfoServicio instance;

	private InfoServicio() {
	}

	public static InfoServicio getInstance() {
		if (instance == null) {
			instance = new InfoServicio();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<String>> infoSQL() {

		Map<String, List<String>> ret = new HashMap<String, List<String>>();

		String path_file = InitApp.class.getResource("/").getPath() + "com/osmosyscol/datasuite/db/tablas.info";
		List<String> ideal = StringUtils.trim(FileUtils.getContentFileList(path_file));

		List<String> faltan = new ArrayList<String>();
		List<String> sobran = new ArrayList<String>();

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();

			SQLDao dao = (SQLDao) daoManager.getDao(SQLDao.class);

			String sql = "SELECT (tb.table_name || ':' || co.column_name || ':' || co.data_type || ':' || co.data_length || ':' || co.nullable) info FROM USER_TAB_COLUMNS co, USER_TABLES tb WHERE tb.table_name = co.table_name ORDER BY tb.table_name, co.column_name";
			
			List<String> existen = new ArrayList<String>();

			List<Map<String, Object>> datos = dao.selectSQL(sql);
			
			String sql2 = "select  ('INDEX' || ':' || TABLE_NAME || ':' || INDEX_NAME  || ':' || INDEX_TYPE || ':' || UNIQUENESS) INFO from USER_INDEXES WHERE INDEX_NAME NOT LIKE 'SYS_%' ORDER BY INDEX_NAME";
			
			datos = ListUtils.union(datos, dao.selectSQL(sql2));
			
			String sql3 = "select ('SEQUENCE' || ':' || SEQUENCE_NAME) INFO from USER_SEQUENCES order by SEQUENCE_NAME";
			
			datos = ListUtils.union(datos, dao.selectSQL(sql3));
			
			String sql4 = "select ('VIEW' || ':' || VIEW_NAME) INFO from USER_VIEWS order by VIEW_NAME";
			
			datos = ListUtils.union(datos, dao.selectSQL(sql4));
			
			System.out.println("-----------------------------");
			
			for (Map<String, Object> registro : datos) {

				String dato = (String) registro.get("INFO");

				System.out.println(dato);
				
				
				existen.add(dato);

				if (!ideal.contains(dato)) {
					sobran.add(dato);
				}
			}

			for (String dato_ideal : ideal) {
				if (!existen.contains(dato_ideal)) {
					faltan.add(dato_ideal);
				}
			}

			ret.put("FALTAN", faltan);
			ret.put("SOBRAN", sobran);

			return ret;

		} catch (Exception e) {
			SimpleLogger.setError("Error en infoSQL", e);
		}

		return null;
	}
	
	private Integer obtenerSiguienteIdAuditoriaSql(){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AuditoriaSqlDao auditoriaSqlDao = (AuditoriaSqlDao) daoManager.getDao(AuditoriaSqlDao.class);
			
			return auditoriaSqlDao.obtenerSiguienteIdAuditoriaSql();
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio obtenerSiguienteIdAuditoriaSql.", e);
		}
		
		return null;
		
	}
	
	public List<Map<String, Object>> exe(String sql, String pool, String crypto, String login, String ip_ingreso) {

		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		Map<String, Object> m = new HashMap<String, Object>();
		
		Integer num_registrosAfectados = null;
		AuditoriaSql auditoriaSql = new AuditoriaSql();
		auditoriaSql.setId_auditoria_Sql(this.obtenerSiguienteIdAuditoriaSql());
		auditoriaSql.setLogin(login);
		auditoriaSql.setIp(ip_ingreso);
		auditoriaSql.setSql(sql);
		auditoriaSql.setFecha(new Date());
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager(pool);
			SQLDao dao = (SQLDao) daoManager.getDao(SQLDao.class);
			
			sql = RDServicio.reemplazarNombres(StringUtils.trim(sql));
			
			
			//cualquier intento de ejecutar un cambio en dst_sql_auditoria se invalida
			if ((sql.toLowerCase().indexOf("update ") == 0 || sql.toLowerCase().indexOf("delete ") == 0 || sql.toLowerCase().indexOf("drop ") == 0) && !(sql.toLowerCase().indexOf("dst_auditoria_sql")<0)) {
				
				m.put("Error", "Cheater!");
				
				num_registrosAfectados = 0;
				
			}else {

				if (sql.toLowerCase().indexOf("select ") == 0) {
					
					ret = dao.selectSQL(sql);
					
					num_registrosAfectados = ret.size();
					
				} else {
	
	
					num_registrosAfectados = dao.updateSQL(sql);
					
					m.put("Result", num_registrosAfectados);
					
					ret.add(m);
	
				}
			}

		} catch (Exception e) {

			m.put("Exception", e.toString());
			ret.add(m);
			SimpleLogger.setError("Error en InfoServicio: ", e);
			auditoriaSql.setExcepciones(e.toString());
			
		} finally{
			 
			try {

				auditoriaSql.setNro_registros_afectados(num_registrosAfectados);
				
				DaoManager daoManager = DaoConfig.getDaoManager();
				AuditoriaSqlDao dao = (AuditoriaSqlDao) daoManager.getDao(AuditoriaSqlDao.class);
				dao.insertarAuditoria(auditoriaSql);
				
			} catch (Exception ex) {
				SimpleLogger.setError("Ha ocurrido un error", ex);
			}
		}

		if (StringUtils.esVerdad(crypto)) {

			if (!ret.isEmpty()) {

				List<Map<String, Object>> newret = new ArrayList<Map<String, Object>>();

				for (Map<String, Object> registro : ret) {

					Set<String> keys = registro.keySet();

					Map<String, Object> re = new HashMap<String, Object>();

					for (String key : keys) {
						re.put(RDServicio.getNombreCampo(key), Crypto.DF(registro.get(key)));
					}

					newret.add(re);
				}

				ret = newret;

			} else {
				m.put("Result", "No Data");
			}

			return ret;

		} else {
			return ret;
		}
	}

	public List<LogFile> logs() {
		File file = new File("logs");

		List<LogFile> logs = new ArrayList<LogFile>();

		if (file.exists()) {
			File[] files = file.listFiles();
			
			for (File file2 : files) {
			 LogFile logFile= new LogFile();
			 logFile.setName(file2.getName());
			 logFile.setLastModified(file2.lastModified());
			 logFile.setSize(org.apache.commons.io.FileUtils.byteCountToDisplaySize(file2.length()));
			 logs.add(logFile);		
			}
			Collections.sort(logs,new OrdenadorLogFiles());
			Collections.reverse(logs);
		}
		return logs;
	}

	public List<String> mostrarLog(String nombreArchivo) {
		return FileUtils.getContentFileList("logs/" + nombreArchivo);
	}
	
	public String exeService(String className, String methodName, String[] parameters){

		try {
			Class<?> ntipo = Class.forName(className);
			
			Method method = ntipo.getMethod("getInstance", new Class[0]);
			Object object = method.invoke(null, new Object[0]);
			
			Object[] argumentos = new Object[parameters.length];
			
			Method[] nmetos = ntipo.getMethods();
			
			for (Method nmeto : nmetos) {
				if (nmeto.getName().equals(methodName)){
					String sname = nmeto.toGenericString();

					sname = sname.substring(sname.indexOf("(") + 1);
					sname = sname.substring(0, sname.indexOf(")"));

					String[] params = sname.split(",");
					
					Class<?>[] classes = nmeto.getParameterTypes();
					for (int i = 0; i < classes.length; i++) {
						Class<?> tipo = classes[i];
						argumentos[i] = createObject(params[i], tipo, parameters[i]);
					}
					
					Object return_ = nmeto.invoke(object, argumentos);
					return XMLFormat.format(JavaToXML.exe(methodName, return_).toString());
				}
			}
			
		}catch (Exception e){
			SimpleLogger.setError("Error en InfoServicio.exeService", e);
			return e.toString();
		}
		return null;
	}
	
	private static Object createObject(String classname, Class<?> tipo, String param){
		Object ret = null;
		
		if (param != null && param.length() > 0) {

			try {
				// --
				tipo = ClassUtils.primitiveToWrapper(tipo);
				if (tipo.getName().equals("java.lang.String")) {
					ret = param;
				} else
				// --
				if (tipo.getName().equals("java.lang.Character")) {
					ret = param.charAt(0);
				} else
				// --
				if (tipo.getName().equals("java.lang.Integer")) {
					ret = Integer.parseInt(param);
				} else
				// --
				if (tipo.getName().equals("java.util.Date")) {
					try {
						ret = new Date(Long.parseLong(param));
					} catch (Exception e) {
						ret = new SimpleDateFormat("dd/MM/yyyy").parse(param);
					}
				} else
				// --
				if (tipo.getName().equals("java.lang.Float")) {
					ret = Float.parseFloat(param);
				} else
				// --
				if (tipo.getName().equals("java.lang.Long")) {
					ret = Long.parseLong(param);
				} else
				// --
				if (tipo.getName().equals("java.lang.Double")) {
					ret = Double.parseDouble(param);
				} else
				// --
				if (tipo.getName().equals("java.lang.Boolean")) {
					ret = Boolean.parseBoolean(param);
				} else
				// --
				if (tipo.getName().equals("java.math.BigDecimal")) {
					ret = new BigDecimal(param);
				}
			} catch (Exception e) {
				ret = null;
			}
		}
		
		return ret;
	}

}

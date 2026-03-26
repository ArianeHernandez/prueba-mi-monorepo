package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Franja;
import com.osmosyscol.datasuite.logica.dto.Horario;
import com.osmosyscol.datasuite.persistencia.dao.FranjaDao;
import com.osmosyscol.datasuite.persistencia.dao.HorarioDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class HorarioServicio {

	private static HorarioServicio instance;

	private HorarioServicio() {

	}

	public static HorarioServicio getInstance() {
		if (instance == null) {
			instance = new HorarioServicio();
		}
		return instance;
	}
	
	public Date obtenerFechaActual(){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			HorarioDao horarioDao = (HorarioDao) daoManager.getDao(HorarioDao.class);
			return horarioDao.obtenerHoraActual();
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		
	}
	
	public Integer obtenerSiguienteIDHorario(){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			HorarioDao horarioDao = (HorarioDao) daoManager.getDao(HorarioDao.class);
			return horarioDao.obtenerSiguienteIDHorario();
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		
	}
	
	public Boolean crearHorario(Horario horario) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			HorarioDao horarioDao= (HorarioDao) daoManager.getDao(HorarioDao.class);
			return horarioDao.crearHorario(horario);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		return null;
	}

	public static List<Franja> convFranjas(String datosHorario) {

		List<Franja> franjas = new ArrayList<Franja>();

		String strdias[] = datosHorario.split("[:]");

		for (String strdia : strdias) {

			if (strdia != null && strdia.trim().length() > 1) {

				strdia = strdia.trim();

				String dia = strdia.substring(0, 1);
				strdia = strdia.substring(1);

				String franjasdia[] = strdia.split("[,]");

				for (String franjadia : franjasdia) {

					if (franjadia != null && franjadia.trim().length() > 2) {

						Franja franja = new Franja();
						franja.setDia(dia);
						franja.setHora_desde(new Integer(franjadia.split("[-]")[0]));
						franja.setHora_hasta(new Integer(franjadia.split("[-]")[1]));

						franjas.add(franja);
					}
				}
			}

		}

		return franjas;

	}

	public Boolean guardarFranjas(String datos, Integer id_horario) {
		try {

			FranjaDao franjaDao = (FranjaDao) DaoConfig.getDaoManager().getDao(FranjaDao.class);
			
			List<Franja> franjas = convFranjas(datos);
			
			return franjaDao.guardarFranjas(franjas, id_horario);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
		
	}
	
	public List<Franja> obtenerFranjas(Integer id_horario) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			FranjaDao franjaDao = (FranjaDao) daoManager.getDao(FranjaDao.class);

			return franjaDao.obtenerFranjas(id_horario);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}
	
	public Integer obtenerNumeroTotalFranjas(Integer id_horario) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			FranjaDao franjaDao = (FranjaDao) daoManager.getDao(FranjaDao.class);

			return franjaDao.obtenerNumeroTotalFranjas(id_horario);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}

	/**
	 * Si <code>date</code> hace parte de una franja en la que se puede
	 * realizar alguna acción, retorna <code>date</code>. Sino, retorna
	 * la fecha en la cuál se puede ejecutar dicha acción
	 * 
	 * @param id_horario
	 * @param date
	 */
	public Date calcularSiguienteFecha(Integer id_horario, Date date) {
		return calcularSiguienteFecha(id_horario, date, false);
	}
		
	/**
	 * Si <code>date</code> hace parte de una franja en la que se puede
	 * realizar alguna acción, retorna <code>date</code>. Sino, retorna
	 * la fecha en la cuál se puede ejecutar dicha acción.
	 * 
	 * Si <code>horarioOficina</code> es <code>true</code>, la siguiente
	 * fecha disponible dentro de una franja de horario de oficina
	 * parametrizado
	 * 
	 * @param id_horario
	 * @param date
	 * @param horarioOficina
	 */
	public Date calcularSiguienteFecha(Integer id_horario, Date date, Boolean horarioOficina) {
		Calendar calendar = Calendar.getInstance();

		if (date != null) {
			date = new Date();
		}
		
		calendar.setTime(date);
		
		if (estaDentroDeHorario(id_horario, calendar, horarioOficina)){
			return date;
		}
		
		List<Franja> franjas = obtenerFranjas(id_horario);

		String dia = DIAS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		Integer hora = calendar.get(Calendar.HOUR_OF_DAY);
		
		Boolean existenFranjas = franjas != null && !franjas.isEmpty();
		
		if (!existenFranjas && horarioOficina){
			franjas = obtenerFranjasHorarioOficina();
		}
		
		Franja franja = buscarFranja(franjas, dia, hora);
		
		return calcularFecha(obtenerNumeroDia(franja.getDia()), franja.getHora_desde());

	}
	
	/**
	 * Busca la siguiente franja en la cuál se pueda realizar la acción.
	 * <b>Se toma en cuenta que ya se debe haber validado que día y hora
	 * no son parte de un horario válido</b>
	 * 
	 * @param franjas
	 * @param dia
	 * @param hora
	 * @param primerDia
	 */
	private Franja buscarFranja(List<Franja> franjas, String dia, Integer hora) {
		
		Franja franjaEncontrada = null;
		
		String primer_dia = dia;
		
		// Buscamos si existe una franja disponible en el dia  actual
		Iterator<Franja> it = franjas.iterator();
		while(franjaEncontrada == null && it.hasNext()){
			Franja franja = it.next();
			if (franja.getDia().equals(dia) && franja.getHora_desde() >= hora && hora < franja.getHora_hasta()) {
				franjaEncontrada = franja;
			}
		}
		
		dia = obtenerSiguienteDia(dia);
		
		// Si en el dia actual no hay franjas disponibles, buscamos el siguiente dia
		// con franja disponible
		while (franjaEncontrada == null && dia != primer_dia){
			for (Franja franja : franjas) {
				if (franja.getDia().equals(dia)) {
					franjaEncontrada = (franjaEncontrada == null) ? franja : franjaEncontrada ;
					if (franja.getHora_desde() < franjaEncontrada.getHora_desde()) {
						franjaEncontrada = franja;
					}
				}
			}
			dia = obtenerSiguienteDia(dia);
		}
		
		return franjaEncontrada;

	}
	
	private Integer obtenerNumeroDia(String dia){
		for (int i = 0; i < DIAS.length; i++) {
			if (DIAS[i].equals(dia)) {
				return i +1;
			}
		}
		return -1;
		
	}
	
	private Date calcularFecha(Integer dia, Integer hora){
		
		Calendar calendar = Calendar.getInstance();
		
		Integer diaHoy = calendar.get(Calendar.DAY_OF_WEEK);
		
		Integer suma = dia - diaHoy;
		if (suma < 0) {
			suma = 7 + suma;
		}
		calendar.add(Calendar.DATE, suma );
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	private String[] DIAS = new String[] { "D", "L", "M", "C", "J", "V", "S" };
	
	private static Map<String, String> DIA_SIG = new HashMap<String, String>();
	
	static {
		DIA_SIG.put("L", "M");
		DIA_SIG.put("M", "C");
		DIA_SIG.put("C", "J");
		DIA_SIG.put("J", "V");
		DIA_SIG.put("V", "S");
		DIA_SIG.put("S", "D");
		DIA_SIG.put("D", "L");
	}

	public static String obtenerSiguienteDia(String dia) {
		return DIA_SIG.get(dia);
	}

	public Boolean estaDentroDeHorario(Integer id_horario, Date date){
		return estaDentroDeHorario(id_horario, date, false);
	}

	public Boolean estaDentroDeHorario(Integer id_horario, Date date, Boolean horarioOficina){
		Calendar calendar = Calendar.getInstance();

		if (date != null) {
			
			calendar.setTime(date);
			
		}
		return estaDentroDeHorario(id_horario, calendar, horarioOficina);
	}
	
	public Boolean estaDentroDeHorario(Integer id_horario, Calendar calendar){
		return estaDentroDeHorario(id_horario, calendar, false);
	}
	
	public Boolean estaDentroDeHorario(Integer id_horario, Calendar calendar, Boolean horarioOficina){
		
		Boolean estaDentroDeHorario = false;
		
		List<Franja> franjas = obtenerFranjas(id_horario);
		
		String dia = DIAS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		Integer hora = calendar.get(Calendar.HOUR_OF_DAY);
		
		Boolean existenFranjas = franjas != null && !franjas.isEmpty();
		
		if (!existenFranjas && horarioOficina){
			franjas = obtenerFranjasHorarioOficina();
		}else if (!existenFranjas) {
			estaDentroDeHorario = true;
		}
		
		if (!estaDentroDeHorario){
			Iterator<Franja> it = franjas.iterator();
			while(!estaDentroDeHorario && it.hasNext()){
				Franja franja = it.next();
				estaDentroDeHorario = franja.getDia().equals(dia) && hora >= franja.getHora_desde() && hora<franja.getHora_hasta();
			}
		}

		return estaDentroDeHorario;
	}
	
	public List<Franja> obtenerFranjasHorarioOficina(){
		List<Franja> franjas = new ArrayList<Franja>();
		for (String dia : DIAS){
			if (!"S".equals(dia) && !"D".equals(dia)){
				Franja franja = new Franja();
				franja.setDia(dia);
				franja.setHora_desde(horaInicioOficina());
				franja.setHora_hasta(horaFinOficina());
				franjas.add(franja);
			}
		}
		return franjas;
	}
	
	public Integer horaInicioOficina(){
		try {
			String valor = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("HORA_INICIO_ATENCION");
			if (StringUtils.esNoVacio(valor)){
				return Integer.parseInt(valor, 10);
			}else {
				SimpleLogger.setWarn("No existe configuracion de hora de inicio de atencion, tomando hora por defecto");
			}
		}catch (Exception e){
			SimpleLogger.setError("Error en HorarioServicio.horaInicioOficina, tomando hora por defecto", e);
		}
		return 8;
	}
	
	public Integer horaFinOficina(){
		try {
			String valor = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("HORA_FIN_ATENCION");
			if (StringUtils.esNoVacio(valor)){
				return Integer.parseInt(valor, 10);
			}else {
				SimpleLogger.setWarn("No existe configuracion de hora de inicio de atencion, tomando hora por defecto");
			}
		}catch (Exception e){
			SimpleLogger.setError("Error en HorarioServicio.horaInicioOficina, tomando hora por defecto", e);
		}
		return 18;
	}
	
}

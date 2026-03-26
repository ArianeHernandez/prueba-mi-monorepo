// CVS $Id$

package com.osmosyscol.commons.servicio;

import java.util.Calendar;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.osmosyscol.commons.log.SimpleLogger;

public class JobServicio {

	private JobServicio() {
	}

	private static JobServicio jobServicio;
	private static Integer jobticket = 0;
	private static Scheduler scheduler;
	

	public static JobServicio getInstance() {
		if (jobServicio == null) {
			jobServicio = new JobServicio();
		}
		return jobServicio;
	}

	public static String getTicket() {
		jobticket++;
		return "T" + jobticket;
	}

	public String callService(Class<?> classe, String operation, Object[] parameters, Integer seconds) {

		String ticket = getTicket();

		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			
			scheduler = sf.getScheduler();

			JobDetail job = new JobDetail("job" + ticket, "group" + ticket, JobAccion.class);

			job.getJobDataMap().put("ticket", ticket);
			job.getJobDataMap().put("operation", operation);
			job.getJobDataMap().put("classname", classe.getName());
			job.getJobDataMap().put("parameters", parameters);

			CronTrigger trigger = new CronTrigger("CronTrigger" + ticket, "GroupTrigger" + ticket);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(System.currentTimeMillis() + (seconds * 1000)));

			String expresion = calendar.get(Calendar.SECOND) + " " + calendar.get(Calendar.MINUTE) + " " + calendar.get(Calendar.HOUR_OF_DAY) + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + (calendar.get(Calendar.MONTH) + 1) + " ? " + calendar.get(Calendar.YEAR);

			trigger.setCronExpression(expresion);

			scheduler.scheduleJob(job, trigger);

			scheduler.start();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al inicial el cron.", e);
			return null;
		}

		return ticket;

	}

	public String callService(Class<?> classe, String operation, Object[] parameters, String expresion) {

		if(expresion == null){
			return null;
		}
		
		String ticket = getTicket();

		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched;
			sched = sf.getScheduler();

			JobDetail job = new JobDetail("job" + ticket, "group" + ticket, JobAccion.class);

			job.getJobDataMap().put("ticket", ticket);
			job.getJobDataMap().put("operation", operation);
			job.getJobDataMap().put("classname", classe.getName());
			job.getJobDataMap().put("parameters", parameters);

			CronTrigger trigger = new CronTrigger("CronTrigger" + ticket, "GroupTrigger" + ticket);

			trigger.setCronExpression(expresion);

			sched.scheduleJob(job, trigger);

			sched.start();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al inicial el cron.", e);
			return null;
		}

		return ticket;

	}
	

	public static Scheduler getScheduler() {
		try {
			if(scheduler == null){
				SchedulerFactory sf = new StdSchedulerFactory();
				scheduler = sf.getScheduler();
			}
			return scheduler;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener Scheduler.", e);
		}
		return null;
	}

	public static void setScheduler(Scheduler scheduler) {
		JobServicio.scheduler = scheduler;
	}
}

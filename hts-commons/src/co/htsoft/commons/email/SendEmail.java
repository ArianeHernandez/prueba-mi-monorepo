package co.htsoft.commons.email;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.lang.StringUtils;
import co.htsoft.commons.log.HLogger;

public class SendEmail {

	private static String FROM = null;
	private static Boolean SSL = null;
	private static String USER = null;
	private static String PASS = null;
	private static String SMTP = null;
	private static Integer SMTP_PORT = null;
	private static String FROMNAME = null;

	public static void configure(String user, String pass, String smtp, Integer smtp_port, Boolean ssl, String from, String fromname) {
		FROM = from;
		SSL = ssl;
		USER = user;
		PASS = pass;
		SMTP = smtp;
		SMTP_PORT = smtp_port;
		FROMNAME = fromname;
	}

	public static void send(String to, String toName, String subject, String cuerpoMail, Map<String, File> archivos) {

		if (SMTP == null) {
			return;
		}

		to = StringUtils.trimToNull(to);

		if (to != null) {
			System.out.println("Enviando Correo a: " + to + ", " + subject);

			try {

				HtmlEmail correo = new HtmlEmail();
				correo.setSSLOnConnect(SSL);

				if (StringUtils.isNotBlank(USER) && StringUtils.isNotBlank(PASS)) {
					DefaultAuthenticator da = new DefaultAuthenticator(USER, PASS);
					correo.setAuthenticator(da);
				}

				correo.setHostName(SMTP);
				if (null != SMTP_PORT) {
					correo.setSmtpPort(SMTP_PORT);
				}

				correo.setFrom(FROM, FROMNAME);
				
				
				String[] correos_s = to.split(";");
				String[] names_s = toName.split(";");

				for (int i = 0; i < correos_s.length; i++) {

					String cc = correos_s[i];
					cc = StringUtils.trimToNullLowerCase(cc);

					if (cc != null) {

						String name = cc;
						try {
							name = StringUtils.trimToNull(names_s[i]);
							if (name == null) {
								name = cc;
							}
						} catch (Exception e) {
						}

						correo.addTo(cc, name);
					}
				}	
			

				correo.setSubject(subject);

				try {
					int inicial = 0;
					int fin = 0;
					while (cuerpoMail.contains("data:image")) {

						String base64 = "";
						inicial = cuerpoMail.indexOf("data:image");
						fin = cuerpoMail.indexOf("\"", inicial);
						base64 = cuerpoMail.substring(inicial, fin);
						String[] cadenas = base64.split(",");
						;
						BufferedImage imgbuffer = ImageIO.read(new ByteArrayInputStream(FileUtils.decodeBase64(cadenas[1])));
						if (archivos == null) {
							archivos = new HashMap<>();
						}
						File outputfile = FileUtils.newFile("png");
						String nombreImagen = outputfile.getName();
						ImageIO.write(imgbuffer, "png", outputfile);

						archivos.put(nombreImagen, outputfile);
						cuerpoMail = cuerpoMail.replace(cuerpoMail.substring(inicial, fin), nombreImagen);

					}

				} catch (Exception e) {
					System.err.println(e.getMessage());
				}

				if (archivos != null) {
					cuerpoMail = adjuntarArchivos(correo, cuerpoMail, archivos);
				}

				HLogger.info(correo + ":" + cuerpoMail);

				correo.setHtmlMsg(cuerpoMail);
				correo.send();

				HLogger.info("CORREO ENVIADO..!! " + correo);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	// -------------------------------

	public static class Data {

		public Data(String to, String toName, String subject, String cuerpoMail, Map<String, File> archivos) {
			this.to = to;
			this.toName = toName;
			this.subject = subject;
			this.cuerpoMail = cuerpoMail;
			this.archivos = archivos;
		}

		public String to, toName, subject, cuerpoMail;
		public Map<String, File> archivos;
	}

	public static List<Data> pendientes = new ArrayList<>();

	private static Thread proceso = null;

	public static synchronized void sendASyn(String to, String toName, String subject, String cuerpoMail) {
		sendASyn(to, toName, subject, cuerpoMail, null);
	}

	public static synchronized void sendASyn(String to, String toName, String subject, String cuerpoMail, Map<String, File> archivos) {

		if (SMTP == null) {
			return;
		}

		Boolean iniciar;

		synchronized (pendientes) {
			iniciar = pendientes.isEmpty();
			pendientes.add(new Data(to, toName, subject, cuerpoMail, archivos));
		}

		if (iniciar) {

			proceso = new Thread(new Runnable() {

				@Override
				public void run() {

					boolean conti = false;

					do {

						Data d;
						synchronized (pendientes) {
							d = pendientes.remove(0);
							conti = !pendientes.isEmpty();
						}

						try {
							send(d.to, d.toName, d.subject, d.cuerpoMail, d.archivos);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} while (conti);

				}
			});

			proceso.start();

		}

	}

	// -------------------------------

	private static String adjuntarArchivos(HtmlEmail correo, String cuerpoMail, Map<String, File> archivos) throws EmailException {
		Map<String, String> valoresCids = new HashMap<String, String>();

		if (archivos != null) {

			Set<String> keys = archivos.keySet();

			int i = 0;
			for (String key : keys) {
				File archivo = archivos.get(key);
				String cid = correo.embed(archivo, "Image_" + i++);
				valoresCids.put(key, "cid:" + cid);
			}

			cuerpoMail = translate(cuerpoMail, valoresCids);
		}
		return cuerpoMail;
	}

	// -------------------------------

	public static String translate(String txt, Map<?, ?> transformations) {
		String resp = txt;
		Set<?> tranformationsSet = transformations.entrySet();
		Iterator<?> it = tranformationsSet.iterator();
		while (it.hasNext()) {
			Map.Entry<?, ?> clave = (Map.Entry<?, ?>) it.next();
			String val1 = (String) clave.getKey();
			String val2 = (String) clave.getValue();
			resp = resp.replaceAll(val1, val2);
		}
		return resp;
	}

	// -------------------------------

}

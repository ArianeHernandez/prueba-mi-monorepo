package co.htsoft.commons.net;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.htsoft.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class RestService extends HttpServlet {

	public static Gson getGSON() {
		return new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	}

	public RestService() {
		super();
	}

	public void validation() {
	}

	private static Map<Thread, String> raw = new ConcurrentHashMap<>();
	private static Map<Thread, HttpServletRequest> requests = new ConcurrentHashMap<>();
	private static Map<Thread, HttpServletResponse> response = new ConcurrentHashMap<>();

	public String getRaw() {
		return raw.get(Thread.currentThread());
	}

	/**
	 * Retorna el request actual
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return requests.get(Thread.currentThread());
	}

	public void addHeader(String headerName, String value) {

		try {
			response.get(Thread.currentThread()).addHeader(headerName, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna la sesion actual
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		HttpServletRequest request = requests.get(Thread.currentThread());
		if (request != null) {
			return request.getSession(true);
		}

		return null;
	}

	/**
	 * Retorna la sesion sin atributos
	 * 
	 * @return
	 */
	public HttpSession getCleanSession() {

		HttpSession session = getSession();

		if (session != null) {

			Set<String> names = new HashSet<>();
			for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();) {
				names.add(e.nextElement());
			}

			for (String name : names) {
				session.removeAttribute(name);
			}

		}

		return session;

	}

	private static String regex(String path, boolean withparams) {

		path = StringUtils.trimToEmpty(path);

		String res = "^";

		for (int i = 0; i < path.length(); i++) {
			String c = path.charAt(i) + "";

			if (".[/-\\+*?|{}".indexOf(c) >= 0) {
				res += "\\";
			}
			res += c;
		}

		res += "$";

		if (withparams) {
			for (int i = 1; i < 20; i++) {
				res = res.replace("\\{" + i + "\\}", "[^\\/]*");
			}
		}

		return res;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getRequestURI().substring(req.getContextPath().length()).trim();

		resp.setHeader("Content-Type", "application/json");

		Class<?> clase = this.getClass();

		Method[] methods = clase.getMethods();

		Method method = null;
		String spath = null;

		if (methods != null) {
			for (Method m : methods) {
				if (m.isAnnotationPresent(GET.class)) {

					Annotation annotation = m.getAnnotation(GET.class);
					GET get = (GET) annotation;

					if (path.matches(regex(get.value(), true))) {
						method = m;
						spath = get.value();
						break;
					}
				}
			}
		}

		requests.put(Thread.currentThread(), req);
		response.put(Thread.currentThread(), resp);

		callMethod(resp, null, path, method, spath);

		requests.remove(Thread.currentThread());
		response.remove(Thread.currentThread());

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String in = StringUtils.trimToNull(StringUtils.toString(req.getInputStream()));

		String path = req.getRequestURI().substring(req.getContextPath().length()).trim();

		resp.setHeader("Content-Type", "application/json");

		Class<?> clase = this.getClass();

		Method[] methods = clase.getMethods();

		Method method = null;
		String spath = null;

		if (methods != null) {
			for (Method m : methods) {
				if (m.isAnnotationPresent(POST.class)) {

					Annotation annotation = m.getAnnotation(POST.class);
					POST get = (POST) annotation;

					if (path.matches(regex(get.value(), true))) {
						method = m;
						spath = get.value();
						break;
					}
				}
			}
		}

		if (in != null) {
			raw.put(Thread.currentThread(), in);
		}
		requests.put(Thread.currentThread(), req);
		response.put(Thread.currentThread(), resp);

		callMethod(resp, in, path, method, spath);

		raw.remove(Thread.currentThread());
		requests.remove(Thread.currentThread());
		response.remove(Thread.currentThread());

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String in = StringUtils.trimToNull(StringUtils.toString(req.getInputStream()));

		String path = req.getRequestURI().substring(req.getContextPath().length()).trim();

		resp.setHeader("Content-Type", "application/json");

		Class<?> clase = this.getClass();

		Method[] methods = clase.getMethods();

		Method method = null;
		String spath = null;

		if (methods != null) {
			for (Method m : methods) {
				if (m.isAnnotationPresent(PUT.class)) {

					Annotation annotation = m.getAnnotation(PUT.class);
					PUT get = (PUT) annotation;

					if (path.matches(regex(get.value(), true))) {
						method = m;
						spath = get.value();
						break;
					}
				}
			}
		}

		if (in != null) {
			raw.put(Thread.currentThread(), in);
		}
		requests.put(Thread.currentThread(), req);
		response.put(Thread.currentThread(), resp);

		callMethod(resp, in, path, method, spath);

		raw.remove(Thread.currentThread());
		requests.remove(Thread.currentThread());
		response.remove(Thread.currentThread());

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String in = StringUtils.trimToNull(StringUtils.toString(req.getInputStream()));

		String path = req.getRequestURI().substring(req.getContextPath().length()).trim();

		resp.setHeader("Content-Type", "application/json");

		Class<?> clase = this.getClass();

		Method[] methods = clase.getMethods();

		Method method = null;
		String spath = null;

		if (methods != null) {
			for (Method m : methods) {
				if (m.isAnnotationPresent(DELETE.class)) {

					Annotation annotation = m.getAnnotation(DELETE.class);
					DELETE get = (DELETE) annotation;

					if (path.matches(regex(get.value(), true))) {
						method = m;
						spath = get.value();
						break;
					}
				}
			}
		}

		if (in != null) {
			raw.put(Thread.currentThread(), in);
		}
		requests.put(Thread.currentThread(), req);
		response.put(Thread.currentThread(), resp);

		callMethod(resp, in, path, method, spath);

		raw.remove(Thread.currentThread());
		requests.remove(Thread.currentThread());
		response.remove(Thread.currentThread());
	}

	private void callMethod(HttpServletResponse resp, String in, String path, Method method, String spath) throws IOException {

		try {

			validation();

			if (method != null) {

				Class<?>[] types = method.getParameterTypes();

				if (types == null) {
					types = new Class<?>[0];
				}

				Object[] params = new Object[types.length];

				for (int i = 0; i < types.length; i++) {

					Class<?> type = types[i];

					String d = "{" + (i + 1) + "}";

					String valor = null;

					int p = spath.indexOf(d);
					if (p >= 0) {

						boolean found = false;

						for (int k = 0; k < path.length() && !found; k++) {
							for (int j = k + 1; j <= path.length() && !found; j++) {

								String s = path.substring(k, j);
								String vspath = spath.replace(d, s);

								if (path.matches(regex(vspath, true))) {
									valor = StringUtils.decodeURIComponent(s);
									found = true;
								}

							}
						}

					}

					if (valor != null) {

						if (String.class.equals(type)) {
							params[i] = valor;
						}

						if (Integer.class.equals(type)) {
							params[i] = new Integer(valor);
						}

						if (Long.class.equals(type)) {
							params[i] = new Long(valor);
						}

						if (Boolean.class.equals(type)) {
							params[i] = new Boolean(valor.toLowerCase());
						}

					}
				}

				if (in != null && params.length > 0 && params[params.length - 1] == null) {
					Class<?> type = method.getParameterTypes()[params.length - 1];
					params[params.length - 1] = getGSON().fromJson(in, type);
				}

				Object r = method.invoke(this, params);

				resp.getWriter().print(getGSON().toJson(r));

			} else {
				Map<String, String> error = new HashMap<>();
				error.put("ERROR", "Invalid Request");

				resp.getWriter().print(getGSON().toJson(error));
			}

		} catch (Exception e) {

			Map<String, String> error = new HashMap<>();
			error.put("ERROR", e.getClass().getName());
			error.put("MESSAGE", e.getMessage());

			e.printStackTrace();
			resp.getWriter().print(getGSON().toJson(error));
		}
	}

}
package com.osmosyscol.datasuite.servlet;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.osmosyscol.commons.log.SimpleLogger;


public class MXBeanServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		try {

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = builder.newDocument();
			
			
			Element root = document.createElement("management-info");

			// Get runtime information
			RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();

			// Handle thread info
			ThreadMXBean threads = ManagementFactory.getThreadMXBean();
			Element threadsElement = document.createElement("threads");
			
			threadsElement.setAttribute("thread-count", Long.toString(threads.getThreadCount()));
			threadsElement.setAttribute("total-started-thread-count", Long.toString(threads.getTotalStartedThreadCount()));
			threadsElement.setAttribute("daemon-thread-count", Long.toString(threads.getDaemonThreadCount()));
			threadsElement.setAttribute("peak-thread-count", Long.toString(threads.getPeakThreadCount()));
			long totalCpuTime = 0l;
			long totalUserTime = 0l;

			// Parse each thread
			ThreadInfo[] threadInfos = threads.getThreadInfo(threads.getAllThreadIds());
			for (int i = 0; i < threadInfos.length; i++) {
				Element threadElement = document.createElement("thread");
				threadElement.setAttribute("id", Long.toString(threadInfos[i].getThreadId()));
				threadElement.setAttribute("name", threadInfos[i].getThreadName());
				threadElement.setAttribute("cpu-time-nano", Long.toString(threads.getThreadCpuTime(threadInfos[i].getThreadId())));
				threadElement.setAttribute("cpu-time-ms", Long.toString(threads.getThreadCpuTime(threadInfos[i].getThreadId()) / 1000000l));
				threadElement.setAttribute("user-time-nano", Long.toString(threads.getThreadUserTime(threadInfos[i].getThreadId())));
				threadElement.setAttribute("user-time-ms", Long.toString(threads.getThreadUserTime(threadInfos[i].getThreadId()) / 1000000l));
				threadElement.setAttribute("blocked-count", Long.toString(threadInfos[i].getBlockedCount()));
				threadElement.setAttribute("blocked-time", Long.toString(threadInfos[i].getBlockedTime()));
				threadElement.setAttribute("waited-count", Long.toString(threadInfos[i].getWaitedCount()));
				threadElement.setAttribute("waited-time", Long.toString(threadInfos[i].getWaitedTime()));
				threadsElement.appendChild(threadElement);

				// Update our aggregate values
				totalCpuTime += threads.getThreadCpuTime(threadInfos[i].getThreadId());
				totalUserTime += threads.getThreadUserTime(threadInfos[i].getThreadId());
			}
			long totalCpuTimeMs = totalCpuTime / 1000000l;
			long totalUserTimeMs = totalUserTime / 1000000l;
			threadsElement.setAttribute("total-cpu-time-nano", Long.toString(totalCpuTime));
			threadsElement.setAttribute("total-user-time-nano", Long.toString(totalUserTime));
			threadsElement.setAttribute("total-cpu-time-ms", Long.toString(totalCpuTimeMs));
			threadsElement.setAttribute("total-user-time-ms", Long.toString(totalUserTimeMs));
			root.appendChild(threadsElement);

			// Compute thread percentages
			long uptime = runtime.getUptime();
			threadsElement.setAttribute("uptime", Long.toString(uptime));
			double cpuPercentage = ((double) totalCpuTimeMs / (double) uptime) * 100.0;
			double userPercentage = ((double) totalUserTimeMs / (double) uptime) * 100.0;
			threadsElement.setAttribute("total-cpu-percent", Double.toString(cpuPercentage));
			threadsElement.setAttribute("total-user-percent", Double.toString(userPercentage));
			root.appendChild(threadsElement);
			
			document.appendChild(root);
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			//initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(document);
			transformer.transform(source, result);

			String xmlString = result.getWriter().toString();

			res.setContentType("text/xml");
		    PrintWriter out = res.getWriter();
		    
//		    out.println("<?xml version=\"1.0\"?>");
		    out.print(xmlString);
			// Output the XML to the caller
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}
}
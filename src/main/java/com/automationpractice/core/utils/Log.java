package com.automationpractice.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {
	private static Logger log = Logger.getLogger(Log.class.getName());
	private static String LOG4J_XML;

	public Log(String logLoc) {
		LOG4J_XML = logLoc + "src\\test\\resources\\config\\log4j.xml";
	try {
			DOMConfigurator.configure(LOG4J_XML);
		} catch (FactoryConfigurationError fExep) {
			System.out.println(" Error while Configurating log4j.");
		} catch (Exception fileExep) {
			System.out.println(LOG4J_XML + " file could be missing.");
		}
	}

	public void info(String msg) {
		log.info(msg);

	}


	public static void error(String msg) {
		log.error(msg);

	}
	
	public void logStackTrace(Exception e) {
		StringWriter stack = new StringWriter();
		e.printStackTrace(new PrintWriter(stack));
		log.debug("Caught exception; decorating with appropriate status template : " + stack.toString());
	}

}

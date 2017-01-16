package com.whispers.utils;

import org.apache.log4j.Logger;

/**
 * @author anka technology solutions pvt. ltd.
 *
 * Logger class
 */

public class Log {
	
	public static Logger log = Logger.getLogger("Log");
	
	/**
	 * This method will log all the messages to the destinations specified in the configuration file of Log4j.
	 *
	 * @param errorLevel (String)
	 * @param processCode (String)
	 * @param errorDetails (String)
	 * @param processDetails (String)
	 */
	
	public static void logMessage(String errorLevel, String className, String errorDetails) {
		
		String errorMessage = errorDetails;
		
		if (errorLevel.equalsIgnoreCase("DEBUG")) {
			log.debug(className+":==="+errorMessage);
		} else if (errorLevel.equalsIgnoreCase("INFO")) {
			log.info(className+":==="+errorMessage);
		} else if (errorLevel.equalsIgnoreCase("WARN")) {
			log.warn(className+":==="+errorMessage);
		} else if (errorLevel.equalsIgnoreCase("ERROR")) {
			log.error(className+":==="+errorMessage);
		} else if (errorLevel.equalsIgnoreCase("FATAL")) {
			log.fatal(className+":==="+errorMessage);
		}		
	}
	
}
/*
	PropertiesFinder.java
	(c) 2012 Anka Technology Solutions Pvt. Ltd.
	
	 
	Modification History
	14-Sept-2012		Sachin		Initial Version
	  
 */
package com.whispers.utils;

import java.util.ResourceBundle;

public class PropertiesFinder {
	
	private static final String CLASS_NAME = "PropertiesFinder";
	
	public static String getValue(String key, String fileName) {
		
		//final String METHOD_NAME = "getValue(String key, String fileName)";
		
		String value = null;
		
		ResourceBundle resourceMessage = null;
		
		try {
			resourceMessage = ResourceBundle.getBundle(fileName);
		} 
		catch(Exception e) {
			Log.logMessage(Constants.ERROR, CLASS_NAME, "Error occurred while reading property file : " + e.getMessage());
						
		}
		
		value = resourceMessage.getString(key);
		
		if (value != null) {
			value = value.trim();
		}
		
		Log.logMessage(Constants.DEBUG, CLASS_NAME, "key : " + key + ", value : " +  value);
		
		return value;
	}
	
}

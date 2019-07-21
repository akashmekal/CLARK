package com.automationpractice.core.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class ConfigFileReader {

	
	private Properties properties;
	private final String propertyFilePath= "//src//test//resources//config//config.properties";
 
	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			String exec_dir = System.getProperty("user.dir");
			reader = new BufferedReader(new FileReader(exec_dir + propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	
	public synchronized String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}
}

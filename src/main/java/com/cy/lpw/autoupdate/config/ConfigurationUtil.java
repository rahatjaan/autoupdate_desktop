package com.cy.lpw.autoupdate.config;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationUtil {
	static Properties props = new Properties();
	static{
		
		try {
			props.load(ConfigurationUtil.class.getClassLoader().getResourceAsStream("settings.properties"));
			System.out.println(getString("host"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static String getString(String key) {
		return props.getProperty(key);
	}


}

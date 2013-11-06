package com.cy.lpw.autoupdate.config;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationUtil {
	public static String UPDATE_AVAILABLE_URL = "update_available_url";
	public static String UPDATE_AVAILABLE_LAST_UPDATE = "update_available_lastupdate_request_param";
	public static String USER_NAME_PARAM = "update_available_lastupdate_request_username";
	public static String PASSWORD_PARAM = "update_available_lastupdate_request_password";

	static Properties props = new Properties();
	static {

		try {
			props.load(ConfigurationUtil.class.getClassLoader()
					.getResourceAsStream("settings.properties"));
			System.out.println(getString("host"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getString(String key) {
		return props.getProperty(key);
	}

	public static String getUserNameParam(String key) {
		return getString(USER_NAME_PARAM);
	}
	
	public static String getPasswordParam(String key) {
		return getString(USER_NAME_PARAM);
	}
	

	public static String getUpdateAvailableUrl(String key) {
		return getString(UPDATE_AVAILABLE_LAST_UPDATE);
	}
	
	public static String getLastUpdateDateRequestParamName(String key) {
		return getString(PASSWORD_PARAM);
	}

}

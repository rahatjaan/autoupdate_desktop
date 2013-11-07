package com.cy.lpw.autoupdate.config;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationUtil {
	public static String UPDATE_AVAILABLE_URL = "update_available_url";
	public static String LAST_UPDATE_DATE = "update_available_lastupdate_request_param";
	public static String USER_NAME_PARAM = "update_available_lastupdate_request_username";
	public static String PASSWORD_PARAM = "update_available_lastupdate_request_password";
	public static String UPDATE_AVAILABLE_PING_INTERVAL = "update_available_check_interval_in_mins";
	
	static Properties props = new Properties();
	static {

		try {
			props.load(ConfigurationUtil.class.getClassLoader()
					.getResourceAsStream("settings.properties"));
//			System.out.println(getString("host"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getString(String key) {
		return props.getProperty(key);
	}

	public static String getUserNameParam() {
		return getString(USER_NAME_PARAM);
	}
	
	public static String getPasswordParam() {
		return getString(PASSWORD_PARAM);
	}
	

	public static String getUpdateDate() {
		return getString(LAST_UPDATE_DATE);
	}
	
	public static String getUpdateAvailableURL() {
		return getString(UPDATE_AVAILABLE_URL);
	}
	
	public static String getLastUpdateDateRequestParamName() {
		return getString(LAST_UPDATE_DATE);
	}
	
	public static String getUpdateAvailablePingInterval() {
		return getString(UPDATE_AVAILABLE_PING_INTERVAL);
	}
	

}

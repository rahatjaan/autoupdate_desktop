package com.cy.lpw.autoupdate.config;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.prefs.Preferences;

public class ConfigurationUtil {
	public static String UPDATE_AVAILABLE_URL = "update_available_url";
	public static String LAST_UPDATE_DATE = "update_available_lastupdate_request_param";
	public static String USER_NAME_PARAM = "update_available_lastupdate_request_username";
	public static String PASSWORD_PARAM = "update_available_lastupdate_request_password";
	public static String UPDATE_AVAILABLE_PING_INTERVAL = "update_available_check_interval_in_mins";
	public static Preferences prefs;
	public static String EVENT = "";
	private static final String WATCH_FOLDER_KEY = "watch_folder";
	private static final String WATCH_File_KEY = "installer_file";
	private static final String UPDATE_DOWNLOAD_URL = "update_download_url";
	
	static Properties props = new Properties();
	static {

		try {
			prefs = Preferences.userRoot().node("Configurations");/*
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date todayDate = new Date();
			String currentDate = dateFormat.format(todayDate);
			String prefDate = prefs.get(LAST_UPDATE_DATE, currentDate);
			if(null == prefDate || "".equalsIgnoreCase(prefDate) || prefDate.equalsIgnoreCase(currentDate)){
				prefs.put(LAST_UPDATE_DATE, currentDate);
			}*/
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
	
	public static String setUpdateDate(String updateDate) {
		return getString(LAST_UPDATE_DATE);
	}
	
	public static String getLastUpdateDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = new Date();
		String currentDate = dateFormat.format(todayDate);
		String dateToReturn = prefs.get(LAST_UPDATE_DATE, currentDate);
		System.out.println("Last Updated date is: "+dateToReturn);
		return dateToReturn;
	}
	
	public static void setLastUpdateDate(String lastUpdateDate){
		prefs.put(LAST_UPDATE_DATE, lastUpdateDate);
	}
	
	public static String getUpdateAvailableURL() {
		return getString(UPDATE_AVAILABLE_URL);
	}
	
	public static String getWatchFilePath(){
		return props.getProperty(WATCH_FOLDER_KEY)+File.separator+props.getProperty(WATCH_File_KEY);
	}
	
	public static String getDownloadFileName(){
		return props.getProperty(WATCH_File_KEY);
	}
	
	
	public static String getLastUpdateDateRequestParamName() {
		return getString(LAST_UPDATE_DATE);
	}
	
	public static String getUpdateAvailablePingInterval() {
		return getString(UPDATE_AVAILABLE_PING_INTERVAL);
	}

	public static String getDownloadUrl() {
		return getString(UPDATE_DOWNLOAD_URL);
	}

	public static String getDownloadFileLocation() {
		return System.getProperty("user.home")+File.separator+getDownloadFileName();
	}
	

}

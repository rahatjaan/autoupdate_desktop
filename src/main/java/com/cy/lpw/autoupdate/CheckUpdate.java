package com.cy.lpw.autoupdate;

import java.util.Date;

import com.cy.lpw.autoupdate.config.ConfigurationUtil;


public class CheckUpdate implements Runnable{
	public static boolean keepRunning = true;
	private long updateAvailablePingInterval=36000000;
	private Date lastUpdateDownloadDate;
	
	public void run() {
		loadConfigurations();
		while(keepRunning){
			checkUpdate();
			try {
				Thread.sleep(updateAvailablePingInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void checkUpdate() {
		String url = getUpdatePingUrl();
		System.out.println(url);
		String output = pingServer(url);
	}
	private String pingServer(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	private String getUpdatePingUrl() {
		String url = ConfigurationUtil.getUpdateAvailableUrl()+"?"+ConfigurationUtil.getLastUpdateDateRequestParamName()+"="+lastUpdateDownloadDate;
		url = url+ "&"+ConfigurationUtil.getUserNameParam()+"="+getUserName()+"&"+ConfigurationUtil.getPasswordParam()+"="+getPassword();
		return url;
	}
	private String getUserName() {
		//TODO figure out how to get user name
		return "admin";
	}
	private String getPassword() {
		//TODO figure out how to get password
		return "admin";
	}
	private void loadConfigurations() {
		String intervalStr = ConfigurationUtil.getUpdateAvailablePingInterval();
		updateAvailablePingInterval = Long.parseLong(intervalStr);
		//TODO this should be read from some file or Preferences
		lastUpdateDownloadDate = new Date("2013-11-04"); 
	}

	
}

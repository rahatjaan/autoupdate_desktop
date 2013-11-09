package com.cy.lpw.autoupdate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import ch.swingfx.twinkle.window.Positions;

import com.cy.lpw.autoupdate.config.ConfigurationUtil;


public class CheckUpdateThread implements Runnable{
	private static final long MILLI_SEC_IN_ONE_MIN = 60000;
	public static boolean keepRunning = true;
	private long updateAvailablePingInterval=36000000;
	public static void main(String []args){
		CheckUpdateThread update = new CheckUpdateThread();
		ConfigurationUtil.setLastUpdateDate("2013-09-05");
		update.loadConfigurations();
		update.checkUpdate();
	}
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
		System.out.println("Output from server is :"+output);
		if(output.equals("true")){
			ConfigurationUtil.EVENT = "DOWNLOAD";
			NotificationUtil.showNotification("Light Point Web", "New Update is available. Click here to download the update.", "notification.png", Positions.SOUTH_EAST);
			
		}
		
	}
	private String pingServer(String url) {
		String responseString = null;
		 try {
		        URL obj = new URL(url);
		        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		        con.setRequestMethod("GET");
		        System.out.println(con.getInputStream());
		        int responseCode = con.getResponseCode();
		        InputStream response = con.getInputStream();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(response));
		        responseString = reader.readLine();
		        System.out.println("Response Code : " + responseCode);
		        System.out.println("Response String : " + responseString);
		        
		    } catch (ConnectException e) {
		        System.out.println("ALERT Now");
		       e.printStackTrace();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return responseString;
	}
	private String getUpdatePingUrl() {
		//String url = ConfigurationUtil.getUpdateAvailableURL()+"?"+ConfigurationUtil.getLastUpdateDateRequestParamName()+"="+formattedLastUpdateDate;
		String url = ConfigurationUtil.getUpdateAvailableURL()+"?"+ConfigurationUtil.getLastUpdateDateRequestParamName()+"="+ConfigurationUtil.getLastUpdateDate();
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
		updateAvailablePingInterval = Long.parseLong(intervalStr);// it is in mins
		updateAvailablePingInterval = updateAvailablePingInterval * MILLI_SEC_IN_ONE_MIN;
	}

	
}

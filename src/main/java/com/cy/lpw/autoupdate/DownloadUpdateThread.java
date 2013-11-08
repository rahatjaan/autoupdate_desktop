package com.cy.lpw.autoupdate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import ch.swingfx.twinkle.window.Positions;

import com.cy.lpw.autoupdate.config.ConfigurationUtil;

public class DownloadUpdateThread implements Runnable {

	public void run() {
			try {
				if(downloadFile()){
					ConfigurationUtil.EVENT = "INSTALL";
					NotificationUtil.showNotification("Light Point Web", "File has been downloaded successfully. Click on the notification to start installation.", "notification.png", Positions.SOUTH_EAST);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static boolean downloadFile() throws IOException {
		boolean flag = false;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(
				"http://localhost:8080/autoupdateweb/downloadupdate");
		HttpResponse response = httpclient.execute(httpget);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedInputStream bis = new BufferedInputStream(instream);
				String filePath = ConfigurationUtil.getWatchFilePath();
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(new File(filePath)));
				int inByte;
				while ((inByte = bis.read()) != -1) {
					bos.write(inByte);
				}
				bis.close();
				bos.close();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date todayDate = new Date();
				String currentDate = dateFormat.format(todayDate);
				ConfigurationUtil.setLastUpdateDate(currentDate);
				flag = true;
			} catch (IOException ex) {
				throw ex;
			} catch (RuntimeException ex) {
				httpget.abort();
				throw ex;
			} finally {
				instream.close();
			}
			httpclient.getConnectionManager().shutdown();
		}
		return flag;
	}

}

package com.cy.lpw.autoupdate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DownloadUpdateThread implements Runnable {

	public void run() {

	}

	public static void main(String[] args) throws IOException {

		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(
				"http://localhost:8080/lpwupdates/downloadupdate");
		HttpResponse response = httpclient.execute(httpget);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedInputStream bis = new BufferedInputStream(instream);
				String filePath = "d://Victor/test.msi";
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(new File(filePath)));
				int inByte;
				while ((inByte = bis.read()) != -1) {
					bos.write(inByte);
				}
				bis.close();
				bos.close();
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
	}

}

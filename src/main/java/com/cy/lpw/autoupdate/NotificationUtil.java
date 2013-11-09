package com.cy.lpw.autoupdate;

import java.io.IOException;

import javax.swing.ImageIcon;

import ch.swingfx.twinkle.NotificationBuilder;
import ch.swingfx.twinkle.event.NotificationEvent;
import ch.swingfx.twinkle.event.NotificationEventAdapter;
import ch.swingfx.twinkle.style.INotificationStyle;
import ch.swingfx.twinkle.style.theme.DarkDefaultNotification;
import ch.swingfx.twinkle.window.IPosition;
import ch.swingfx.twinkle.window.Positions;

import com.cy.lpw.autoupdate.config.ConfigurationUtil;

public class NotificationUtil {

	public static void showNotification(String title,final String message){
		showNotification(title,message, "notification.png",Positions.SOUTH_EAST);
	}
	public static void showNotification(String title,final String message,String icon,IPosition position) {
		// AA the text
		System.setProperty("swing.aatext", "true");
		// First we define the style/theme of the window.
		// Note how we override the default values
		
		INotificationStyle style = new DarkDefaultNotification()
				.withWidth(400) // Optional
				.withAlpha(0.9f) // Optional
		;

		// Now lets build the notification
		new NotificationBuilder()
				.withStyle(style) // Required. here we set the previously set style
				.withTitle(title) // Required.
				.withMessage(message) // Optional				
				.withDisplayTime(10000) // Optional
				.withIcon(new ImageIcon(NotificationUtil.class.getResource(icon))) // Optional. You could also use a String path
				.withPosition(position) // Optional. Show it at the center of the screen
				.withListener(new NotificationEventAdapter() { // Optional
					public void closed(NotificationEvent event) {
						System.out.println("closed notification with UUID " + event.getId());
					}

					public void clicked(NotificationEvent event) {
						System.out.println("clicked notification with UUID " + event.getId());
						if("DOWNLOAD".equalsIgnoreCase(ConfigurationUtil.EVENT)){
							new Thread(new DownloadUpdateThread()).start();
						}else if("INSTALL".equalsIgnoreCase(ConfigurationUtil.EVENT)){
							try {
								Process p = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", ConfigurationUtil.getDownloadFileLocation()});
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
//						SanMarWidgetMessagesWindow window = SanMarWidgetMessagesWindow.getInstance();
//						window.setVisible(true);
						//window.addMessage(message);
						
					}
				})
				.showNotification(); // this returns a UUID that you can use to identify events on the listener

	}
	
	public static void main(String []args){
		showNotification("Light Point Web", "New Update is ready to install. Click here to install the update.", "notification.png",Positions.SOUTH_EAST);
		
	}

}

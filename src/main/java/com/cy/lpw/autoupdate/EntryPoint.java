package com.cy.lpw.autoupdate;

public class EntryPoint {

	public static void main(String []args){
		System.out.println("Starting Check update process");
		new Thread(new CheckUpdateThread()).start();
//		CheckUpdate.keepRunning = false;
	}
}

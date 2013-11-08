package com.cy.lpw.autoupdate;

import java.io.IOException;

public class Test {
  public static void main(String[] args) {
		  try {
			Process p = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", "c:\\test.msi"});
		} catch (IOException e) {
			e.printStackTrace();
		} 
  }
} 
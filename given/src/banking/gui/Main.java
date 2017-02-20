/*
  File:	Main.java
  Author: kevinagary
  Date:	2/19/17
  
  Description: Main file for running the program
*/

package banking.gui;

import javax.swing.JFrame;

/**
  Class: Main
  
  Description: Main class for running the program
*/
final class Main {
	/**
	 * Private constructor to address STYLE issue.
	 */
	private Main() {
	}
	 
	 /**
		Method: main
		Inputs: args command-line arguments
		Returns: void

		Description: Main method of the program
	*/
	public static void main(final String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java FormMain <property file>");
			System.exit(1);
		}

		String propertyFile = args[0];
		JFrame frame = new MainFrame(propertyFile);
		frame.setVisible(true);

	}
}

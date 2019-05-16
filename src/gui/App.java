package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		
	
		SwingUtilities.invokeLater(new Runnable() { 
			// makes the application more robust										
			public void run() {
				new MainFrame();

			}

		});

	}
}

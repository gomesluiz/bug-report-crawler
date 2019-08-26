package br.unicamp.ic.crawler.ui.swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BugReportManager {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {	
				JFrame frame = new JFrame("Bug Report Manager");
				frame.setSize(600, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

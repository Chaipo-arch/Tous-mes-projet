package Gestion;

import javax.swing.*;

public class interfaceGraphique {
	public static void Accueil() {
		JFrame frame = new JFrame("Mon interface pouloulou le genie");
		JMenu menu = new JMenu();
		JPanel panel = new JPanel();
		JMenuBar bar = new JMenuBar();
		JMenuItem item = new JMenuItem();
		JLabel label = new JLabel();
		label.setSize(50,600);
		frame.setSize(1000, 600);
		menu.setSize(50,600);
		bar.setSize(20,600);
		panel.setSize(500,600);
		panel.setVisible(true);
		bar.setVisible(true);
		label.setVisible(true);
		frame.setVisible(true);
		
		
		
	}
	public static void retourHome() {
		
	}
	public static void main(String[] args) {
		Accueil();
	}
}
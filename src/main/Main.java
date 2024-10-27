package main;

import javax.swing.JFrame;

public class Main {
	
	public static void main (String [] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("JOGO DE TESTE"); //Título da janela de jogo
		
		Game_Screen screen = new Game_Screen();
		window.add(screen);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
	}
}
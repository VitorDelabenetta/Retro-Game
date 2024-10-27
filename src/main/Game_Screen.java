package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class Game_Screen extends JPanel implements Runnable {
	
	// Configurações Gerais:
	
	final int tile_size = 16; // 16x16 tiles
	final int scale = 3; // Como 16x16 resolution é muito baixa, fazemos 3x
	final int tiles = tile_size * scale;
	
	final int screen_columns = 16;
	final int screen_rows = 12;
	final int screen_width = tiles * screen_columns; // 768 
	final int screen_height = tiles * screen_columns; // 576
	
	// Thread: permite a adição de temporalidade ao programa, para fazermos as taxas de atualização por segundo (FPS)
	
	Thread game_thread;
	
	public Game_Screen() {
		
		this.setPreferredSize(new Dimension(screen_width, screen_height));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // Para renderização com buffer
		
	}
	
	public void inicializing_thread() {
		
		game_thread = new Thread(this); // Passamos o "Game_Screen" para esta Thread para inicializar o game
		game_thread.start();
		
	}

	@Override
	public void run() {
		
		// Ao inicializar a Thread,vai criar um loop que será o núcleo do jogo
				
	}
}
package main;

import javax.swing.JPanel;

import entities.Player;
import tile.Tile_Manager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Game_Screen extends JPanel implements Runnable {
	
	// CONFIGURAÇÕES GERAIS:
	
		final int tile_size = 16; // 16x16 tiles
		final int scale = 3; // Como 16x16 resolution é muito baixa, fazemos 3x
		public final int tiles = tile_size * scale;
		public final int screen_columns = 16;
		public final int screen_rows = 12;
		public final int screen_width = tiles * screen_columns; // 768 
		public final int screen_height = tiles * screen_columns; // 576
		// Deixamos público para acessar de outros package (como das Entidades)
		
		// FPS
		int fps = 60;
	
	Thread game_thread;
	// Thread: permite a adição de temporalidade ao programa, para fazermos as taxas de atualização por segundo (FPS)
	Keys_Inputs keyboard_input = new Keys_Inputs();
	// Importamos as informações do teclado da nossa outra Classe
	Player player = new Player(this, keyboard_input);
	Tile_Manager tiles_manager = new Tile_Manager(this);
	
		
	public Game_Screen() {
		
		this.setPreferredSize(new Dimension(screen_width, screen_height));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // Para renderização com buffer
		
		this.addKeyListener(keyboard_input);
		this.setFocusable(true); // Dá o "enfoque" para a janela do jogo
		
	}
	
	public void inicializing_thread() {
		
		game_thread = new Thread(this); // Passamos o "Game_Screen" para esta Thread para inicializar o game
		game_thread.start();
		
	}

	@Override 
	// Polimorfismo do Java: indicamos para classe Pai certos parâmetros da nossa classe herdada, que "sobrescrevem" informações dos métodos bases
	
	public void run() {
		// Ao inicializar a Thread,vai criar um loop que rodará o jogo

		double draw_interval = 1000000000 / fps; // O nosso intervalo será de 1seg (ou 1000000000 nano segundos) dividido pelos FPS que definimos
		long last_time = System.nanoTime(); // Pega o tempo atual de forma mais precisa
		long current_time;
		double delta = 0;
		long timer = 0;
		int frame_counter = 0;
		
		while (game_thread != null) {
			// Vamos desenhar a tela (fazer o update e repaint) a cada segundo 
			
			current_time = System.nanoTime();
			delta = delta + ((current_time - last_time) / draw_interval);
			timer = timer + (current_time - last_time);
			last_time = current_time;
			
			if (delta >= 1) {
				update();
				// Updates: como atualizar a posição do personagem com adição ao X ou Y
				repaint(); 
				// Draw: desenha as informações na tela (esta forma é como chamamos o paintComponent do Java - que cuidará da parte gráfica do jogo)
				
				delta--;
				frame_counter++;
			}
			
			if (timer >= 1000000000) {
				// Para contarmos os FPS's cada segundo
				System.out.println("FPS: " + frame_counter);
				frame_counter = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void paintComponent(Graphics graphics) {
		// Método e Classe do próprio Java, para desenhar objetos na tela
		
		super.paintComponent(graphics); 
		// Comunica para a classe Pai (super-class, no caso JPainel) o objeto, levando os atributos para o Construtor
		
		Graphics2D graphics_2d = (Graphics2D)graphics;
		// Como nosso jogo será 2D, usamos uma classe mais específica do Java para trabalhar com controle de geometria, coordenadas, etc
		
		tiles_manager.draw(graphics_2d);
		player.draw(graphics_2d);
		// Nossos layers devem seguir a ordem de tiles antes do player, como z-index
		
		graphics_2d.dispose(); // Finaliza o processo, mesmo que o Garbage Collector já o faça (garante seguridade)
		
	}	
}
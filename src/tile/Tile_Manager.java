package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Game_Screen;

public class Tile_Manager {
	
	Game_Screen screen;
	Tile [] tile;
	int map_tiles [][]; // Para gerar o mapa 
	
	public Tile_Manager (Game_Screen screen) {
		
		this.screen = screen; 
		tile = new Tile[10]; // Criará n quantidade de tiles para gerenciar
		map_tiles = new int [screen.screen_columns][screen.screen_rows];
		
		get_tile_sprites();
		load_map("/maps/basic_map.txt");
		
	}
	
	public void get_tile_sprites () {
		
		try {
			
			tile[0] = new Tile(); // Criamos uma instância do nosso array para a grass_0
			tile[0].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_0.png"));
			
			tile[1] = new Tile(); 
			tile[1].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_1.png"));
			
			tile[2] = new Tile(); 
			tile[2].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_2.png"));
			
			tile[3] = new Tile(); 
			tile[3].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			tile[4] = new Tile(); 
			tile[4].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/brick.png"));
			
			tile[5] = new Tile(); 
			tile[5].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[6] = new Tile(); 
			tile[6].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/flower_0.png"));
			
			tile[7] = new Tile(); 
			tile[7].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/flower_1.png"));
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void load_map(String map_file) {
		try {
			
			InputStream text_input = getClass().getResourceAsStream(map_file); // Importar o texto mapeando as informações dos tiles
			BufferedReader reader = new BufferedReader(new InputStreamReader(text_input)); // Para ler o conteúdo do texto importado
			
			int column = 0;
			int row = 0;
			
			while(column < screen.screen_columns && row < screen.screen_rows) {
				// Loop para efetuar a leitura do tileset do mapa
				
				String line = reader.readLine(); // Lê uma linha do nosso mapa.txt
				
				while (column < screen.screen_columns) {

					String numbers[] = line.split(" "); // Estamos separando os valores da linhas pelo espaçamento, para ler número a número dos tiles
					int number = Integer.parseInt(numbers[column]); // Fazemos um parsing das informações que pegamos como String para int
					
					map_tiles[column][row] = number;
					column ++; // Vamos fazer o processo de pegar a informação da linha e armazenar, coluna por coluna (separando-as pelo espaço)
					
				}
				
				if (column == screen.screen_columns) {
					column = 0;
					row ++;
				}
				
			}
			
			reader.close(); // Depois de escanear o tileset do mapa, fechamos o leitor
			
		}
		catch (Exception e) {
			
		}
	}
	
	public void draw(Graphics2D graphics_2d) {
		
		int column = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (column < screen.screen_columns && row < screen.screen_rows) {
			
			int tile_number = map_tiles[column][row]; // O valor que mapeamos do map.txt será informado aqui e depois passado para seleção da sprite
			
			graphics_2d.drawImage(tile[tile_number].sprite, x, y, screen.tiles, screen.tiles, null);
			column++; // Para próxima coluna
			x += screen.tiles; // Aumenta o tamanho com base em nosso tile set (aqui será 48) 
			
			if (column == screen.screen_columns) {
				column = 0;
				x = 0;
				row ++;
				y += screen.tiles;
				
				// Criamos um loop que vai gerar as imagens para todas as colunas da linha inicial e quando atinge o limite pula para próximas linhas
			}
		
		}
	}
}
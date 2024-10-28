package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game_Screen;
import main.Keys_Inputs;

public class Player extends Entity {
	
	Game_Screen screen;
	Keys_Inputs keyboard_input;
	
	public Player(Game_Screen screen, Keys_Inputs keyboard_input) {
		
		this.screen = screen;
		this.keyboard_input = keyboard_input;
		
		set_default_values(); // Chamamos o método para o construtor, para poder passar/atualizar os parâmetros
		get_player_sprites();
	}
	
	public void set_default_values() {
		
		x = 100;
		y = 100;
		speed = 3; // Movimento em pixels
		direction = "stop";
		
	}
	
	public void get_player_sprites() {
		
		try {
			
			front_stop = ImageIO.read(getClass().getResourceAsStream("/player/front_stop.png"));
			front_walk_1 = ImageIO.read(getClass().getResourceAsStream("/player/front_walk_1.png"));
			front_walk_2 = ImageIO.read(getClass().getResourceAsStream("/player/front_walk_2.png"));			
			back_walk_1 = ImageIO.read(getClass().getResourceAsStream("/player/back_walk_1.png"));
			back_walk_2 = ImageIO.read(getClass().getResourceAsStream("/player/back_walk_2.png"));
			left_stop = ImageIO.read(getClass().getResourceAsStream("/player/left_stop.png"));
			left_walk = ImageIO.read(getClass().getResourceAsStream("/player/left_walk.png"));
			right_stop = ImageIO.read(getClass().getResourceAsStream("/player/right_stop.png"));
			right_walk = ImageIO.read(getClass().getResourceAsStream("/player/right_walk.png"));
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		direction = "stop";
		
		if (keyboard_input.down_pressed || keyboard_input.up_pressed || keyboard_input.right_pressed || keyboard_input.left_pressed) {
			
			if (keyboard_input.down_pressed) {
				direction = "down";
				y += speed;
			}
			else if (keyboard_input.up_pressed) {
				direction = "up";
				y -= speed;
			}
			else if (keyboard_input.right_pressed) {
				direction = "right";
				x += speed;
			}
			else if (keyboard_input.left_pressed) {
				direction = "left";
				x -= speed;
			}
			
			sprite_counter++; // Inicia a sprite 1
			if (sprite_counter > 12) {
				// A cada 12 frames, vamos fazer a alteração entre as duas sprites que informamos no método "draw" (quando for 1, troca para 2 e vice-versa)
				
				if(sprite_number == 1) {
					sprite_number = 2;
				}
				else if (sprite_number == 2) {
					sprite_number = 1;
				}
				
				sprite_counter = 0;
				
			}	
		}
	}
	
	public void draw(Graphics2D graphics_2d) {
		
		BufferedImage sprite = null;
		
		switch (direction) {
		
		case "up":
			
			if(sprite_number == 1) {
				sprite = back_walk_1;
			}
			else if (sprite_number == 2) {
				sprite = back_walk_2;
			}
			
			break;
			
		case "down":
			
			if(sprite_number == 1) {
				sprite = front_walk_1;
			}
			else if (sprite_number == 2) {
				sprite = front_walk_2;
			}
			
			break;
			
		case "left":
			
			if(sprite_number == 1) {
				sprite = left_stop;
			}
			else if (sprite_number == 2) {
				sprite = left_walk;
			}
			
			break;
			
		case "right":
			
			if(sprite_number == 1) {
				sprite = right_stop;
			}
			else if (sprite_number == 2) {
				sprite = right_walk;
			}
			
			break;
			
		case "stop":
			sprite = front_stop;
			break;
		}
		
		graphics_2d.drawImage(sprite, x, y, screen.tiles, screen.tiles, null);
		
	}
}

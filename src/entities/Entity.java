package entities;

import java.awt.image.BufferedImage;

public class Entity {
	// Super-classe para nosso jogador, NPCs e monstros
	
	public int x, y;
	public int speed;
	
	public BufferedImage back_stop, back_walk_1, back_walk_2, front_stop, front_walk_1, front_walk_2, left_stop, left_walk, right_stop, right_walk;
	public String direction;
	
	public int sprite_counter = 0;
	public int sprite_number = 1;

}

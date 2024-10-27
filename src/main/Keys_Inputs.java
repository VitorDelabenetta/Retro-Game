package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys_Inputs implements KeyListener {

	public boolean up_pressed, down_pressed, left_pressed, right_pressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key_code = e.getKeyCode(); // Retorna um número da tecla pressionada (eg.: 8 = backspace; 10 = enter; 65 = a)
		
		if (key_code == KeyEvent.VK_W || key_code == KeyEvent.VK_UP) {
			up_pressed = true;
		}
		if (key_code == KeyEvent.VK_A || key_code == KeyEvent.VK_LEFT) {
			left_pressed = true;
		}
		if (key_code == KeyEvent.VK_S || key_code == KeyEvent.VK_DOWN) {
			down_pressed = true;
		}
		if (key_code == KeyEvent.VK_D || key_code == KeyEvent.VK_RIGHT) {
			right_pressed = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int key_code = e.getKeyCode();
		
		if (key_code == KeyEvent.VK_W || key_code == KeyEvent.VK_UP) {
			up_pressed = false;
		}
		if (key_code == KeyEvent.VK_A || key_code == KeyEvent.VK_LEFT) {
			left_pressed = false;
		}
		if (key_code == KeyEvent.VK_S || key_code == KeyEvent.VK_DOWN) {
			down_pressed = false;
		}
		if (key_code == KeyEvent.VK_D || key_code == KeyEvent.VK_RIGHT) {
			right_pressed = false;
		}
		
	}

}

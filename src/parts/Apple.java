package parts;

import game.MainGame;
import javafx.scene.paint.Color;

public class Apple extends Block {
	
	//Creates an Apple block at random location
	public Apple() {
		super(getRandomX(), getRandomY());
		while (isOnSnake()) {
			changeLocation();
		}
		setFill(Color.RED);
	}
	
	//Method to randomise the X coordinate
	public static int getRandomX() {
		int x = (int) (Math.random() * MainGame.width / SIZE);
		return x;
	}

	//Method to randomise the Y coordinate
	public static int getRandomY() {
		int y = (int) (Math.random() * MainGame.height / SIZE);
		return y;
	}

	
	// Check whether the Apple appears on Snake. Inspired by https://github.com/abcghy/Snake/blob/master/src/Apple.java
	public boolean isOnSnake() {
		
		int size = Snake.body.size();
		
		for (int i = 0; i < size - 1; i++) {
			Block current = (Block) Snake.body.get(i);
			if (current.getX() == this.getX() && current.getY() == this.getY()) {
				//System.out.println("is on snake");
				return true;
			}
		}
		return false;
	}
	
	// Changes coordinates of Apple. Inspired by https://github.com/abcghy/Snake/blob/master/src/Apple.java
	public void changeLocation() {
		this.setX(getRandomX() * SIZE);
		this.setY(getRandomY() * SIZE);
		//BUG HERE
		
	}

}

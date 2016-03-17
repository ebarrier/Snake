package parts;

import game.MainGame;
import javafx.scene.paint.Color;

public class Apple extends Block {
	
	// Creates an Apple block at random location
	public Apple() {
		super(getRandomX(), getRandomY());
		setFill(Color.RED);
	}

	// Method to randomise the X coordinate
	public static int getRandomX() {
		int x = (int) (Math.random() * MainGame.width / SIZE);
		return x;
	}

	// Method to randomise the Y coordinate
	public static int getRandomY() {
		int y = (int) (Math.random() * MainGame.height / SIZE);
		return y;
	}
	
}

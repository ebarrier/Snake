package parts;

import javafx.scene.paint.Color;

public class Apple extends Block {
	
	//Creates an Apple block at random location
	public Apple() {
		super(getRandomX(), getRandomY());
		System.out.println("Apple constructor");
		while (isOnSnake()) {
			changeLocation();
		}
		setFill(Color.RED);
	}
	
	//Method to randomise the X coordinate
	public static int getRandomX() {
		int x = (int) (Math.random() * 260 / SIZE);
		System.out.println(x);
		return x;
	}

	//Method to randomise the Y coordinate
	public static int getRandomY() {
		int y = (int) (Math.random() * 260 / SIZE);
		System.out.println(y);
		return y;
	}

	
	// Check whether the Apple appears on Snake. Inspired by https://github.com/abcghy/Snake/blob/master/src/Apple.java
	public boolean isOnSnake() {
		
		int size = Snake.body.size();
		
		for (int i = 0; i < size - 1; i++) {
			Block current = (Block) Snake.body.get(i);
			if (current.getX() == this.getX() && current.getY() == this.getY()) {
				System.out.println("is on snake");
				return true;
			}
		}
		return false;
	}
	
	// Changes coordinates of Apple. Inspired by https://github.com/abcghy/Snake/blob/master/src/Apple.java
	public void changeLocation() {
		System.out.println("Location method called");
		this.setX(getRandomX());
		this.setY(getRandomY());
		//BUG HERE
		
	}

}

package parts;

import javafx.scene.paint.Color;

public class Apple extends Block {

	public Apple() {
		super(getRandomX(), getRandomY());
		setFill(Color.RED);
	}
	
	public static int getRandomX() {
		int x = (int)(Math.random()*1000/SIZE);
		return x;
	}
	
	public static int getRandomY(){
		int y = (int)(Math.random()*500/SIZE);
		return y;
	}
	

}

package parts;

import javafx.scene.paint.Color;

public class Apple extends Block {

	static int x = (int)(Math.random()*1000);
	static int y = (int)(Math.random()*500);
	
	public Apple() {
		new Block(x,y);
		setWidth(SIZE);
		setHeight(SIZE);
		setArcWidth(0);
		setArcHeight(0);
		setFill(Color.RED);
	}
	
	
	

}

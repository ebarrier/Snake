package parts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bodypart extends Rectangle {
	
	public static final int SIZE = 20;
	
	public Bodypart(int x, int y) {
		setX(x * SIZE);
		setY(y * SIZE);
		setWidth(SIZE);
		setHeight(SIZE);
		setArcWidth(0);
		setArcHeight(0);

		setFill(Color.BLACK);
	}
	
	


}

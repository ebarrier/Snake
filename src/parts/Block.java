/*
MIT License
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.
 */

package parts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
	
	public static final int SIZE = 20;
	
	public Block() {
	}

	public Block(int x, int y) {
		setX(x * SIZE);
		setY(y * SIZE);
		setWidth(SIZE);
		setHeight(SIZE);
		setArcWidth(0);
		setArcHeight(0);
		setFill(Color.BLACK);
	}
	
	
	
}
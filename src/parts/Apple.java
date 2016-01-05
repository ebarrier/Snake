package parts;

import game.MainGame;
import javafx.scene.paint.Color;

public class Apple extends Block {

	// Creates an Apple block at random location
	public Apple() {
		super(getRandomX(), getRandomY());
		// while (isOnSnake()) {
		// changeLocation();
		// }
		setFill(Color.RED);
		setType(Type.APPLE);
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

	// Changes coordinates of Apple. Inspired by
	// https://github.com/abcghy/Snake/blob/master/src/Apple.java
	public void changeLocation() {
//		ArrayList<Block> blocks = new ArrayList<Block>();
//		for (int i = 0; i < MainGame.width; i += 20) {
//			for (int j = 0; j < MainGame.height; j += 20) {
//				blocks.add(new Block(i, j));
//			}
//		}
//
//		for (Block block : blocks) {
//			if (block.getType() == Type.SNAKE || block.getType() == Type.APPLE) {
//				blocks.remove(block);
//			}
//		}
//		
//
//		Block randomBlock = blocks.get(new Random().nextInt(blocks.size()));
//		this.setX(randomBlock.getX() / SIZE);
//		this.setY(randomBlock.getY() / SIZE);
//
//	}
	 this.setX(getRandomX() * SIZE);
	 this.setY(getRandomY() * SIZE);
	}
}

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

import com.sun.javafx.scene.traversal.Direction;
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

public class Snake extends Group {

	private Direction direction = Direction.DOWN;
	private Direction directionOrder;
	private Block head;
	private ObservableList<Node> snake;


	public Snake() {

		// BodyPart snake = new BodyPart(1, 1);
		// this.getChildren().add(snake);

		for (int i = 0; i < 10; i++) {
			Block snake = new Block(i + 3, 3);
			this.getChildren().add(snake);
		}

		head = (Block) this.getChildren().get(0);
		snake = this.getChildren();
	}

	private void moveBody() {

		int size = snake.size();

		if (size > 1) {
			for (int i = size - 1; i > 0; i--) {
				Block prev = (Block) snake.get(i - 1);
				Block current = (Block) snake.get(i);
				current.setX(prev.getX());
				current.setY(prev.getY());
			}
		}
	}

	public void move() {
		
		if (null != directionOrder) {
			direction = directionOrder;
			directionOrder = null;
		}
		snakeCollision();
		moveBody();
		switch (direction) {
		case DOWN:
			head.setY(head.getY() + Block.SIZE);
			break;
		case UP:
			head.setY(head.getY() - Block.SIZE);
			break;
		case LEFT:
			head.setX(head.getX() - Block.SIZE);
			break;
		case RIGHT:
			head.setX(head.getX() + Block.SIZE);
			break;
		default:
			break;
		}
	}

	public void setDirection(Direction direction) {
		if (!isOpposite(this.direction, direction)) {
			this.directionOrder = direction;
		}
	}
	
	public static boolean isOpposite(Direction one, Direction two) {
		if (Direction.RIGHT.equals(one) && Direction.LEFT.equals(two)
				|| Direction.RIGHT.equals(two) && Direction.LEFT.equals(one)) {
			return true;
		}
		
		if (Direction.UP.equals(one) && Direction.DOWN.equals(two)
				|| Direction.UP.equals(two) && Direction.DOWN.equals(one)) {
			return true;
		}
		return false;
	}
	
	public boolean snakeCollision() {
		for (int i = 1; i < this.getChildren().size(); i++) {
			Block current = (Block) snake.get(i);
			if (head.getX() == current.getX() && head.getY() == current.getY()) {
				System.exit(0);
				return true;
			}
		}
		return false;
	}
}

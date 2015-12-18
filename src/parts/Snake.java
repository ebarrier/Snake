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

import java.util.ArrayList;

import com.sun.javafx.scene.traversal.Direction;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;

public class Snake extends Group {

	private Direction direction = Direction.DOWN;
	private Direction directionOrder;
	private ArrayList<Direction> directionOrders = new ArrayList<Direction>();
	private Block head;
	private ObservableList<Node> body;

	public Snake() {

		Block firstPart = new Block(1, 1);
		this.getChildren().add(firstPart);

		head = (Block) this.getChildren().get(0);
		head.setFill(Paint.valueOf("blue"));
		body = this.getChildren();
	}

	private void moveBody() {

		int size = body.size();

		if (size > 1) {
			for (int i = size - 1; i > 0; i--) {
				Block prev = (Block) body.get(i - 1);
				Block current = (Block) body.get(i);
				current.setX(prev.getX());
				current.setY(prev.getY());
			}
		}
	}

	private void moveHead() {
		if (directionOrders.isEmpty()) {
			directionOrder = this.direction;
		} else {
			for (int i = 0; i < directionOrders.size(); i++) {
				if (!isOpposite(directionOrders.get(i), this.direction) && directionOrders.get(i) != this.direction) {
					setDirectionOrder(directionOrders.get(i));
					setDirection(directionOrders.get(directionOrders.size() - 1));
					directionOrders.clear();
					break;
				}
			}
		}

		switch (directionOrder) {
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

	public void move() {

		moveBody();
		moveHead();

		if (isBorderCollision() || isSnakeCollision()) {
			System.out.println("GAME OVER!");
		}
	}

	public void setDirectionOrder(Direction direction) {
		this.directionOrder = direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
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

	public void directionOrders(Direction direction) {
		this.directionOrders.add(direction);
	}

	public boolean isSnakeCollision() {
		for (int i = 1; i < this.getChildren().size(); i++) {
			Block current = (Block) body.get(i);
			if (head.getX() == current.getX() && head.getY() == current.getY()) {
				return true;
			}
		}
		return false;
	}

	public boolean isBorderCollision() {
		if (head.getX() > 1000 - 10 || head.getX() < 0 || head.getY() > 500 || head.getY() < 0) {
			return true;
		}
		return false;
	}

	public boolean collides(Apple apple) {

		if (head.getX() == apple.getX() && head.getY() == apple.getY()) {
			return true;
		}
		return false;
	}

	public void eat(Apple apple) {
		Block block = new Block(-2, -2);
		body.add(block);
	}

}

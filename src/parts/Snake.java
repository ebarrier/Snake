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

import game.MainGame;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;

public class Snake extends Group {

	private Direction direction = Direction.DOWN;
	private Direction directionOrder;
	private ArrayList<Direction> directionOrders = new ArrayList<Direction>();
	public static Block head;
	public ObservableList<Node> body;

	public Snake() {
 
		Block firstPart = new Block(0, 0); // creates the first block for the
											// snake
		this.getChildren().add(firstPart); // add the block to the group "snake"

		head = (Block) this.getChildren().get(0); // set the first block of the
													// group as the "head"
		head.setFill(Paint.valueOf("blue")); // set the colour of the head as
												// blue
		body = this.getChildren(); // set all the snake group as "body"
		
		
	}

	// Moves the snake
	private void moveBody() {

		int size = body.size();
		/**
		 * Each block of the snake's body, starting from the last one, gets the
		 * position of his neighbour (the previous one).
		 */
		if (size > 1) {
			for (int i = size - 1; i > 0; i--) {
				Block prev = (Block) body.get(i - 1);
				Block current = (Block) body.get(i);
				current.setX(prev.getX());
				current.setY(prev.getY());
			}
		}
	}

	// Adds a direction to the list of direction orders
	public void directionOrders(Direction direction) {
		this.directionOrders.add(direction);
	}

	// Set a direction as "directionOrder"
	public void setDirectionOrder(Direction direction) {
		this.directionOrder = direction;
	}

	// Set a direction as "direction"
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	// Checks two directions if they are opposite
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

	// Move the head of the snake according to arrow key pressed by player
	private void moveHead() {
		// When the list of direction orders is empty, the current direction is
		// used as the direction order
		if (directionOrders.isEmpty()) {
			directionOrder = this.direction;
		} else {
			// Each direction of the list of direction orders is iterated
			for (int i = 0; i < directionOrders.size(); i++) {
				/**
				 * When the direction order in the list is not opposite to the
				 * current direction, AND it is different than the current
				 * direction,...
				 */
				if (!isOpposite(directionOrders.get(i), this.direction) && directionOrders.get(i) != this.direction) {
					/**
					 * ... the head moves one time to this direction order and
					 * the "direction" of the snake is set with the value of the
					 * last direction order of the list or orders.
					 */
					setDirectionOrder(directionOrders.get(i));
					setDirection(directionOrders.get(directionOrders.size() - 1));
					directionOrders.clear();
					break;
				}
			}
		}

		// For each direction order, head moves towards it for one block
		// size
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

	// How the whole snake moves (body first and then head otherwise there is a
	// bug)
	public void move() {
		moveBody();
		moveHead();
		collision();
	}
	
	public boolean collision() {
		if (isBorderCollision() || isSnakeCollision()) {
			return true;
		}
		return false;
	}

	// Checks whether the head of the snake meets its body
	public boolean isSnakeCollision() {
		for (int i = 1; i < this.getChildren().size(); i++) {
			Block current = (Block) body.get(i);
			if (head.getX() == current.getX() && head.getY() == current.getY()) {
				return true;
			}
		}
		return false;
	}

	// Checks whether the head of the snake meets the border of the screen
	public boolean isBorderCollision() {
		if (head.getX() > MainGame.width - 10 || head.getX() < 0 || head.getY() > MainGame.height || head.getY() < 0) {
			return true;
		}
		return false;
	}

	// Checks whether the head of the snake meets an apple
	public boolean collides(Apple apple) {
		if (head.getX() == apple.getX() && head.getY() == apple.getY()) {
			return true;
		}
		return false;
	}

	// When an apple is met, a block is created an added to the snake's body
	public void eat(Apple apple) {
		Block block = new Block(-2, -2);
		body.add(block);
	}

	// Checks whether the block appears on Snake. Inspired by
	// https://github.com/abcghy/Snake/blob/master/src/Apple.java
	public boolean isOnSnake(Block block) {
		int size = body.size();
		for (int i = 0; i < size - 1; i++) {
			Block current = (Block) body.get(i);
			if (current.getX() == block.getX() && current.getY() == block.getY()) {
				return true;
			}
		}
		return false;
	}

	// Checks whether the snake fills the scene
	public boolean snakeComplete() {
		if ((MainGame.width / Block.SIZE) * (MainGame.height / Block.SIZE) == this.body.size()) {
			return true;
		}
		return false;
	}

}

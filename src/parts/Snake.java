package parts;

import com.sun.javafx.scene.traversal.Direction;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

public class Snake extends Group {

	private Direction direction;
	private BodyPart head;
	private ObservableList<Node> snake;

	public Snake() {

		//BodyPart snake = new BodyPart(1, 1);
		// this.getChildren().add(snake);

		for (int i = 0; i < 10; i++) {
			BodyPart snake = new BodyPart(i + 3, 3);
			this.getChildren().add(snake);
		}
		
		head = (BodyPart) this.getChildren().get(0);
		snake = this.getChildren();
	}

	private void moveBody() {

		ObservableList<Node> children = this.getChildren();
		int size = children.size();
		
		if (size > 1) {
			for (int i = size - 1; i > 0; i--) {
				BodyPart prev = (BodyPart) snake.get(i - 1);
				BodyPart current = (BodyPart) snake.get(i);
				current.setX(prev.getX());
				current.setY(prev.getY());
			}			
		}
	}

	public void move() {

		moveBody();

		switch (direction) {
		case DOWN:
			head.setY(head.getY() + BodyPart.SIZE);
			break;
		case UP:
			head.setY(head.getY() - BodyPart.SIZE);
			break;
		case LEFT:
			head.setX(head.getX() - BodyPart.SIZE);
			break;
		case RIGHT:
			head.setX(head.getX() + BodyPart.SIZE);
			break;
		}
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}

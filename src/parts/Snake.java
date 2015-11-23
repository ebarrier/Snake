package parts;

import java.util.ArrayList;

import com.sun.javafx.scene.traversal.Direction;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

public class Snake extends Group {

	ArrayList<BodyPart> snake = new ArrayList<BodyPart>();

	private Direction direction;

	public Snake() {
		for (int i = 0; i < 10; i++) {
			BodyPart part = new BodyPart(i + 3, 3);
			this.getChildren().add(part);
		}
	}

	private void moveBody() {
		ObservableList<Node> children = this.getChildren();
		int size = children.size();
		BodyPart last = null;

		for (int i = size - 1; i >= 0; i--) {
			BodyPart current = (BodyPart) children.get(i);

			if (last != null) {
				last.setX(current.getX());
				last.setY(current.getY());
			}

			last = current;
		}
	}

	public void move() {

		moveBody();

		BodyPart head = (BodyPart) this.getChildren().get(0);
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

//	private boolean isCollision(double x, double y) {
//		for (int i = snake.size() - 1; i > 0; i--) {
//			if (snake.get(0).getX() == snake.get(i).getX() && (snake.get(0).getY() == snake.get(i).getY())) {
//					return true;
//			}
//		}
//		return false;
//	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}

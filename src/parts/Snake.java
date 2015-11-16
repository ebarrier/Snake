package parts;

import java.util.ArrayList;

import javafx.scene.Group;

public class Snake extends Group {

	ArrayList<Bodypart> body = new ArrayList<Bodypart>();

	public Snake() {
		for (int i = 0; i < 3; i++) {
			Bodypart part = new Bodypart(i + 3,3);
			this.getChildren().add(part);
		}

	}

}

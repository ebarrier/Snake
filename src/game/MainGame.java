package game;

import com.sun.javafx.scene.traversal.Direction;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import parts.Snake;

public class MainGame extends Application {

	Snake snake = new Snake();	
	
	@Override
	public void start(Stage primaryStage) {
		
		Pane layout = new Pane();
		ObservableList<Node> components = layout.getChildren();
		
		components.add(snake);
		
		
		Scene scene = new Scene(layout, 1000, 500);
		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			switch(event.getCode()) {
			case LEFT:
				snake.setDirection(Direction.LEFT);
				snake.move();
				break;
			case RIGHT:
				snake.setDirection(Direction.RIGHT);
				snake.move();
				break;
			case UP:
				snake.setDirection(Direction.UP);
				snake.move();
				break;
			case DOWN:
				snake.setDirection(Direction.DOWN);
				snake.move();
				break;			
			}
		});
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}

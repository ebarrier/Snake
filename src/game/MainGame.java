package game;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import parts.Snake;

public class MainGame extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		Pane layout = new Pane();
		Scene scene = new Scene(layout, 1000, 500);
		ObservableList<Node> components = layout.getChildren();
		
		components.add(new Snake());
		
		
		
//		components.add(new Bodypart(1, 1));
//		components.add(new Bodypart(1, 2));


		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}

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

package game;

import java.util.Timer;
import java.util.TimerTask;

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
		primaryStage.setOnCloseRequest(event -> {
			System.exit(0);
		});
		primaryStage.show();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				snake.move();		
			}
		}, 100, 100);
		
		primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			switch(event.getCode()) {
			case LEFT:
				snake.setDirection(Direction.LEFT);
				break;
			case RIGHT:
				snake.setDirection(Direction.RIGHT);
				break;
			case UP:
				snake.setDirection(Direction.UP);
				break;
			case DOWN:
				snake.setDirection(Direction.DOWN);
				break;		
			}
		});
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}

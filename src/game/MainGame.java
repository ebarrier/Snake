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

import com.sun.javafx.scene.traversal.Direction;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import parts.Apple;
import parts.Snake;

public class MainGame extends Application {

	Snake snake = new Snake();
	Apple apple = new Apple();
	
	public void stopGame() {
		try {
			stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		Pane layout = new Pane();
		ObservableList<Node> components = layout.getChildren();
		
		components.add(snake);
		components.add(apple);
		
		
		Scene scene = new Scene(layout, 1000, 500);
		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(event -> {
			stopGame();
		});
		primaryStage.show();

		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ev -> {
			snake.move();
			if (snake.collides(apple)) {
				System.out.println("applecollision");
				snake.eat(apple);
				
				components.remove(apple);
				apple = new Apple();
				components.add(apple);
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			switch(event.getCode()) {
			case LEFT:
				snake.directionOrders(Direction.LEFT);
				break;
			case RIGHT:
				snake.directionOrders(Direction.RIGHT);
				break;
			case UP:
				snake.directionOrders(Direction.UP);
				break;
			case DOWN:
				snake.directionOrders(Direction.DOWN);
				break;
			case ESCAPE:
				stopGame();
			default:
				break;
			}
		});
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }
	

}

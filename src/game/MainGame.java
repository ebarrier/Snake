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
	public static final int width = 200;
	public static final int height = 200;
	
	//Stops the games and closes the window
	public void stopGame() {
		try {
			stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	//Launches the game
	@Override
	public void start(Stage primaryStage) {
		
		Pane layout = new Pane();
		ObservableList<Node> components = layout.getChildren(); //creates a list of nodes and adds them to the layout
		
		components.add(snake); //snake added to the layout
		components.add(apple); //apple added to the layout
		
		
		Scene scene = new Scene(layout, width, height); //creates a scene with a size
		primaryStage.setTitle("Snake"); //sets the title of the window
		primaryStage.setScene(scene);
		primaryStage.setResizable(false); //the window's size cannot be changed
		primaryStage.setOnCloseRequest(event -> {
			stopGame(); //closing the window stops the game
		});
		primaryStage.show(); //displays the elements of the game

		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ev -> { //creates a timer
			snake.move(); //at each frequence, the snake moves
			
			//when the snake eats an apple, it grows by one block, apple is deleted and another one is created
			if (snake.collides(apple)) {
				snake.eat(apple);
				
				components.remove(apple);
				apple = new Apple();
				components.add(apple);
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		//arrow keys pressed by player are recorded in the list of direction orders
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

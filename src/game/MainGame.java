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
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import parts.Apple;
import parts.Snake;

public class MainGame extends Application {

	private Snake snake = new Snake();
	private Apple apple = new Apple();
	public static final int width = 120;
	public static final int height = 60;
	private static int score;
	private Text tEat = new Text();
	private Stage window;
	private Button btnStart, btnTryAgain, btnQuit;
	private Label lblscene1, lblscene3, lblscene4;
	private StackPane stack1, stack3, stack4;
	Pane gamePane;
	private Scene scene1, scene2, scene3, scene4;
	private Timeline timeline;
	private boolean isPaused;

	// Launches the game
	@Override
	public void start(Stage primaryStage) {

		// Buttons set up
		btnStart = new Button("Start the game now!");
		btnTryAgain = new Button("Try again");
		btnQuit = new Button("Quit");
		btnStart.setOnAction(e -> {
			runGame();
		});
		btnTryAgain.setOnAction(e -> {
			timeline.stop();
			resetGame();
			runGame();
		});
		btnQuit.setOnAction(e -> exitGame());

		// Labels set up
		lblscene1 = new Label("Welcome to the Snake Game by Etienne Barrier!\n Click to start the game");
		lblscene3 = new Label("Epic fail! Try again if you dare...");
		lblscene4 = new Label("You win! Congrats!");

		// text tEat set up
		tEat.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
		tEat.setY(height / 2);
		tEat.setX(width / 2 - 40);
		tEat.setFill(Color.CHARTREUSE);
		tEat.setOpacity(0);

		// Layouts set up
		stack1 = new StackPane();
		gamePane = new Pane();
		stack3 = new StackPane();
		stack4 = new StackPane();

		// Add items to layouts
		stack1.getChildren().addAll(btnStart);
		gamePane.getChildren().addAll(snake, apple, tEat);
		stack3.getChildren().addAll(btnTryAgain, btnQuit, lblscene3);
		stack4.getChildren().addAll(btnTryAgain, lblscene4);

		// Scenes set up
		scene1 = new Scene(stack1, width, height);
		scene2 = new Scene(gamePane, width, height);
		scene3 = new Scene(stack3, width, height);
		scene4 = new Scene(stack4, width, height);

		// Stage set up
		window = primaryStage;
		window.setWidth(1000);
		window.setHeight(1000);
		window.setTitle("Snake"); // sets the title of the window
		window.setScene(scene1);
		window.setResizable(false); // the window's size cannot be changed
		window.setOnCloseRequest(event -> exitGame()); // closing the
																// window stops
																// the game
		window.show(); // displays the elements of the game

		// arrow keys pressed are recorded in the list of direction orders
		window.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			switch (event.getCode()) {
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
				exitGame();
			case SPACE:
				pause();
			default:
				break;
			}
		});

	}
	
	public void resetGame(){
		score = 0;
		window.setTitle("Snake " + score);
		gamePane.getChildren().removeAll(snake, apple, tEat);
		apple = new Apple();
		snake = new Snake();
		gamePane.getChildren().addAll(snake, apple, tEat);
//		scene2 = new Scene(gamePane, width, height);
	}

	public void runGame() {
//		Pane gamePane = new Pane();
//		gamePane.getChildren().removeAll(snake, apple);
//		apple = new Apple();
//		snake = new Snake();
//		gamePane.getChildren().addAll(snake, apple, tEat);
//		Scene scene2 = new Scene(gamePane, width, height);
		window.setScene(scene2);
		
		//create a timer
		timeline = new Timeline(new KeyFrame(Duration.millis(100), ev -> { 
			snake.move(); // at each frequency, the snake moves

			// when the snake eats an apple, it grows by one block, apple is
			// deleted and another one is created.
			if (snake.collides(apple)) {
				score++;
				window.setTitle("Snake " + score);
				tEat.setText("" + score);
				fader(tEat); // tEat text appears and fades away
				snake.eat(apple);
				gamePane.getChildren().remove(apple);
				apple = new Apple();
				gamePane.getChildren().add(apple);
			}

			// when the apple appears on snake, it is relocated
			if (snake.isOnSnake(apple)) {
				apple.changeLocation();
			}

			// when the snake fills the scene entirely, game ends
			if (snake.snakeComplete()) {
				win();
			}

			// when snake eats itself or border
			// if (snake.collision()) {
			// gameOver();
			// }

		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.playFromStart();
	}

	// Fades a node until it becomes transparent. Inspired by
	// https://docs.oracle.com/javase/8/javafx/api/javafx/animation/FadeTransition.html
	// and
	// http://stackoverflow.com/questions/23190049/how-to-make-a-text-content-disappear-after-some-time-in-javafx
	private FadeTransition fader(Node node) {
		FadeTransition fade = new FadeTransition(Duration.millis(500), node);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.play();
		return fade;
	}

	public void gameOver() {
		timeline.stop();
		window.setScene(scene3);
	}

	public void win() {
		timeline.stop();
		window.setScene(scene4);
	}

	public void pause() {
		if (isPaused) {
			timeline.play();
			isPaused = false;
		} else {
			timeline.pause();
			isPaused = true;
		}
	}

	// Stops the games and closes the window
	public void exitGame() {
		try {
			timeline.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}

}

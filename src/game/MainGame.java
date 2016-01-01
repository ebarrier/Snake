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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
	private Label title;
	private Pane gamePane;
	private Scene scene1, scene2, loseScene3, winScene4;
	private Timeline timeline;
	private boolean isPaused;

	// Launches the game
	@Override
	public void start(Stage primaryStage) {
		
		title = new Label("SNAKE");
		
		scene1();
		scene2();
		loseScene3();
		winScene4();
		
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
		
		keyListener();
		
	}
	
	public Scene scene1() {
		Label lbl1scene1 = new Label("Welcome to the Snake Game by Etienne Barrier!");
		Label lbl2scene1 = new Label("Click to start the game");
		Button btnStart = new Button("Start the game now!");
		btnStart.setOnAction(e -> {
			runGame();
		});
		VBox vbox1 = new VBox(title, lbl1scene1, lbl2scene1, btnStart);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setSpacing(10);
		scene1 = new Scene(vbox1, width, height);
		return scene1;
	}
	
	public Scene scene2() {
		// text tEat set up
		tEat.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
		tEat.setY(height / 2);
		tEat.setX(width / 2 - 40);
		tEat.setFill(Color.CHARTREUSE);
		tEat.setOpacity(0);
		
		gamePane = new Pane(snake, apple, tEat);
		scene2 = new Scene(gamePane, width, height);
		return scene2;
	}
	
	public Scene loseScene3() {
		Label lblscene3 = new Label("Epic fail! Try again if you dare...");
		Button btnTryAgain = new Button("Try again");
		btnTryAgain.setOnAction(e -> {
			timeline.stop();
			resetGame();
			runGame();
		});
		Button btnQuit = new Button("Quit");
		btnQuit.setOnAction(e -> exitGame());
		HBox hbox3 = new HBox(btnTryAgain, btnQuit);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(10);
		VBox vbox3 = new VBox(title, lblscene3, hbox3);
		vbox3.setAlignment(Pos.CENTER);
		vbox3.setSpacing(10);
		loseScene3 = new Scene(vbox3, width, height);
		return loseScene3;
	}
	
	public Scene winScene4() {
		Label lblscene4 = new Label("You win! Congrats!");
		Button btnTryAgain2 = new Button("Try again2");
		btnTryAgain2.setOnAction(e -> {
			timeline.stop();
			resetGame();
			runGame();
		});
		Button btnQuit2 = new Button("Quit2");
		btnQuit2.setOnAction(e -> exitGame());
		HBox hbox4 = new HBox(btnTryAgain2, btnQuit2);
		hbox4.setAlignment(Pos.CENTER);
		hbox4.setSpacing(10);
		VBox vbox4 = new VBox(title, lblscene4, hbox4);
		vbox4.setAlignment(Pos.CENTER);
		vbox4.setSpacing(10);
		winScene4 = new Scene(vbox4, width, height);
		return winScene4;	
	}
	
	
	public void keyListener() {
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

	public void resetGame() {
		score = 0;
		window.setTitle("Snake " + score);
		gamePane.getChildren().removeAll(snake, apple, tEat);
		apple = new Apple();
		snake = new Snake();
		gamePane.getChildren().addAll(snake, apple, tEat);
	}

	public void runGame() {

		window.setScene(scene2);

		// create a timer
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

			// when the snake fills the scene entirely
			if (snake.snakeComplete()) {
				win();
			}

			// when snake eats itself or border
			if (snake.collision()) {
				gameOver();
			}

		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.playFromStart();
	}

	/**
	 * Fades a node until it becomes transparent. Inspired by
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/animation/
	 * FadeTransition.html and
	 * http://stackoverflow.com/questions/23190049/how-to-make-a-text-content-
	 * disappear-after-some-time-in-javafx
	 */
	private FadeTransition fader(Node node) {
		FadeTransition fade = new FadeTransition(Duration.millis(500), node);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.play();
		return fade;
	}

	// Stops game and display losing scene
	public void gameOver() {
		timeline.stop();
		window.setScene(loseScene3);
	}

	// Stops game and display winning scene
	public void win() {
		timeline.stop();
		window.setScene(winScene4);
	}

	// Pauses/resumes game
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
			stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}

}

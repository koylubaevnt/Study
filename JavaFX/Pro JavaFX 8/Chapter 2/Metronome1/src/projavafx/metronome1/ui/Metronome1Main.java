package projavafx.metronome1.ui;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Metronome1Main extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	DoubleProperty startXVal = new SimpleDoubleProperty(100.0);
	
	Button startButton;
	Button pauseButton;
	Button resumeButton;
	Button stopButton;
	
	Line line;
	Timeline anim;
	
	@Override
	public void start(Stage stage) throws Exception {
		anim = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(startXVal, 100.)),
				new KeyFrame(Duration.seconds(1), new KeyValue(startXVal, 300., Interpolator.LINEAR)));

		anim.setAutoReverse(true);
		anim.setCycleCount(Animation.INDEFINITE);
		line = new Line(0, 50, 200, 400);
		line.setStrokeWidth(4);
		line.setStroke(Color.BLUE);
		
		startButton = new Button("start");
		startButton.setOnAction(e -> anim.playFromStart());
		pauseButton = new Button("pause");
		pauseButton.setOnAction(e -> anim.pause());
		resumeButton = new Button("resume");
		resumeButton.setOnAction(e -> anim.play());
		stopButton = new Button("stop");
		stopButton.setOnAction(e -> anim.stop());
		
		HBox commands = new HBox(10, 
				startButton,
				pauseButton,
				resumeButton,
				stopButton);
		commands.setLayoutX(60);
		commands.setLayoutY(420);
		
		Group group = new Group(line, commands);
		Scene scene = new Scene(group, 400, 500);
		line.startXProperty().bind(startXVal);
		
		startButton.disableProperty().bind(anim.statusProperty()
				.isNotEqualTo(Animation.Status.STOPPED));
		pauseButton.disableProperty().bind(anim.statusProperty()
				.isNotEqualTo(Animation.Status.RUNNING));
		resumeButton.disableProperty().bind(anim.statusProperty()
				.isNotEqualTo(Animation.Status.PAUSED));
		stopButton.disableProperty().bind(anim.statusProperty()
				.isEqualTo(Animation.Status.STOPPED));
		
		stage.setScene(scene);
		stage.setTitle("Metronom 1");
		stage.show();
	}
}

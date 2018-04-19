package cst8284.triviatime;

import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TriviaTimeLaunch extends Application {
	/**  @Copyright Dave Houtman 2018.  For use in Winter 2018 - CST8284 classes only */
	
	private static BorderPane rootPane;
	@Override
	public void start(Stage primaryStage){	
	   // Display Splash Screen
	   setRootPane("Welcome to Trivia Time");
	   getRootPane().setTop(Controls.getMenuBar(primaryStage));
	   Scene scene =  new Scene(getRootPane(), 1024, 512);
	   primaryStage.setTitle("Trivia Time");
	   primaryStage.setScene(scene);
	   primaryStage.show();	
	}
	public static void setRootPane(String splashString) {
		rootPane= new BorderPane();
		BoxBlur bb = new BoxBlur();
        bb.setWidth(5);
        bb.setHeight(5);
        bb.setIterations(1);
		Text text = new Text(splashString);
		text.setEffect(bb);
		text.setStyle("-fx-font: 40px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
		rootPane.setCenter(text);
		FadeTransition ft = new FadeTransition();
		ft.setDuration(Duration.millis(5000));
		ft.setCycleCount(1);
		ft.setFromValue(0);
		ft.setToValue(6);
		RotateTransition st = new RotateTransition();
	    st.setFromAngle(0);
	    st.setToAngle(360);
	    st.setCycleCount(1);
	    st.setAutoReverse(true);
		ParallelTransition pt = new ParallelTransition(rootPane, st, ft);
		pt.play();
	}
	public static BorderPane getRootPane() {return rootPane;}
	public static void main(String[] args){
		Application.launch(args);
	}
	
}
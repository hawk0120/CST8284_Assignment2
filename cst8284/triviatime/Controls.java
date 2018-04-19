package cst8284.triviatime;
import java.io.File; 
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controls {

	/**** Generic Menu/Menu Item Properties ****/
	private static MenuItem mnuItm; 
	private static Menu mnu;
	private static Stage stage; 
	private static int currentQuestion = 0;

	/***************** MenuBar *****************/
	/**Creates the MenuBar
	 * @param primaryStage
	 * @return menuBar
	 */
	public static MenuBar getMenuBar(Stage primaryStage) {
		setStage(primaryStage);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(getMnuFile(), getMnuSettings(), getMnuHelp());
		return menuBar;
	}

	/******************* Menu ******************/
	private static Menu getMnuFile() {
		mnu = new Menu("_File");
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
		mnu.getItems().addAll(
				getMnuItmNewGame(), 
				getMnuItmExit()
				); return mnu;
	}
	private static Menu getMnuSettings() {
		mnu = new Menu("_Settings");
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		mnu.setDisable(false);
		mnu.getItems().addAll(getRandomQ(), 
				GetDifficulty(), 
				GetTopic()
				); return mnu;   
	} 
	private static Menu getMnuHelp() {
		mnu = new Menu("_Help");
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
		mnu.getItems().addAll(getMnuItmAbout());
		return mnu;
	}

	/***************** MenuItems *****************/
	private static MenuItem getMnuItmNewGame() {
		mnuItm = new MenuItem("_New Game");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			BorderPane bp = (BorderPane)getStage().getScene().getRoot();
			bp.setStyle("-fx-font: 16px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File f = fileChooser.showOpenDialog(stage);
			String triviaFile = f.getAbsolutePath();
			FileUtils.setQAArrayList(triviaFile);
			QAPane qa = new QAPane(Controls.getNextQA());

			VBox rBuf = new VBox(); rBuf.setPrefWidth(100); bp.setRight(rBuf);
			VBox lBuf = new VBox(); lBuf.setPrefWidth(100);	bp.setLeft(lBuf);
			HBox tBuf = new HBox(); tBuf.setPrefHeight(100); bp.setTop(tBuf);

			bp.setCenter(qa.getQAPane());	
		});
		return mnuItm;
	}
	private static MenuItem getMnuItmExit() {
		mnuItm = new MenuItem("_Exit");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> Platform.exit());
		return mnuItm;
	}
	private static MenuItem getRandomQ() {
		mnuItm = new MenuItem("_Randomize Questions");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			BorderPane bp = (BorderPane)getStage().getScene().getRoot();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File f = fileChooser.showOpenDialog(stage);
			String triviaFile = f.getAbsolutePath();
			FileUtils.setQAArrayList(triviaFile);
			FileUtils.getRandomQ();
			QAPane qa = new QAPane(Controls.getNextQA());
			VBox rBuf = new VBox(); rBuf.setPrefWidth(100); bp.setRight(rBuf);
			VBox lBuf = new VBox(); lBuf.setPrefWidth(100);	bp.setLeft(lBuf);
			HBox tBuf = new HBox(); tBuf.setPrefHeight(100); bp.setTop(tBuf);
			bp.setCenter(qa.getQAPane());	
		});return mnuItm;		
	}
	private static MenuItem GetDifficulty() {
		mnuItm = new MenuItem("_Increase Difficulty");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			BorderPane bp = (BorderPane)getStage().getScene().getRoot();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File f = fileChooser.showOpenDialog(stage);
			String triviaFile = f.getAbsolutePath();
			FileUtils.setQAArrayList(triviaFile);
			FileUtils.sortByDifficulty();		
			QAPane qa = new QAPane(Controls.getNextQA());
			VBox rBuf = new VBox(); rBuf.setPrefWidth(100); bp.setRight(rBuf);
			VBox lBuf = new VBox(); lBuf.setPrefWidth(100);	bp.setLeft(lBuf);
			HBox tBuf = new HBox(); tBuf.setPrefHeight(100); bp.setTop(tBuf);
			bp.setCenter(qa.getQAPane());	
		}); return mnuItm;
	}
	private static MenuItem GetTopic() {
		mnuItm = new MenuItem("_Sort By Topic");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			BorderPane bp = (BorderPane)getStage().getScene().getRoot();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File f = fileChooser.showOpenDialog(stage);
			String triviaFile = f.getAbsolutePath();
			FileUtils.setQAArrayList(triviaFile);
			FileUtils.getTopic();
			QAPane qa = new QAPane(Controls.getNextQA());
			VBox rBuf = new VBox(); rBuf.setPrefWidth(100); bp.setRight(rBuf);
			VBox lBuf = new VBox(); lBuf.setPrefWidth(100);	bp.setLeft(lBuf);
			HBox tBuf = new HBox(); tBuf.setPrefHeight(100); bp.setTop(tBuf);
			bp.setCenter(qa.getQAPane());	
		}); return mnuItm;
	}
	private static MenuItem getMnuItmAbout() {
		/* From Marco Jakob, code.makery, */
		/* http://code.makery.ch/blog/javafx-dialogs-official/ */
		mnuItm = new MenuItem("_About");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("About Trivia Time");
			alert.setContentText("By Brady Hawkins");
			alert.showAndWait();
		});
		return mnuItm;
	}
	private static void setStage(Stage s) {stage= s;}
	public static Stage getStage() {return stage;}
	public static QA getNextQA() {
		if (currentQuestion < FileUtils.getQAArrayList().size())
			return FileUtils.getQAArrayList().get(currentQuestion++);
		else
			return null;
	}

}

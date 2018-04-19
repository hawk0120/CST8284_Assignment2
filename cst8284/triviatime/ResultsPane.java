package cst8284.triviatime;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResultsPane {
	public static ScrollPane getResults() {
	    VBox results = new VBox();
	    results.setMinWidth(300);
	    results.setStyle("-fx-font: 20px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
	    
	    int questionNum = 1, correctAns = 0;
		for (QA qa: FileUtils.getQAArrayList()) { 
		   results.getChildren().add(new Text("  " + questionNum++ + 
			  ".  " + (qa.isCorrect()?"Right":"Wrong")));		
		   correctAns += qa.isCorrect()?1:0;
		}
		
		HBox marks = new HBox();
		marks.setAlignment(Pos.CENTER_RIGHT);	
		marks.getChildren().add(new Text("Results:" + 
				correctAns + "/" + FileUtils.getQAArrayList().size()));
		results.getChildren().add(marks);	
		
		ScrollPane spResults = new ScrollPane();
		spResults.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spResults.setHbarPolicy(ScrollBarPolicy.AS_NEEDED); 
		spResults.setContent(results);
		return spResults;
	}
}

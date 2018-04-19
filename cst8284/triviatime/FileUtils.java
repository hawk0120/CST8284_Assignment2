package cst8284.triviatime;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FileUtils {
	
	private static ArrayList<QA> qaAL; 
	public static File getFileHandle(Stage primaryStage) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Trivia File");
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Trivia Files", "*.trivia"),
				new ExtensionFilter("All Files", "*.*")
		);
		File trivFile = fc.showOpenDialog(primaryStage);
		return (trivFile);
	}
	public static void setQAArrayList(String absPath) {
		qaAL = new ArrayList<QA>();
		if (fileExists(absPath)) {
			try {
				FileInputStream fis = new FileInputStream(absPath);
				ObjectInputStream ois = new ObjectInputStream(fis);
				while(true)
					qaAL.add((QA) (ois.readObject()));
			} catch (IOException | ClassNotFoundException e) {} 
		}
		else 
			qaAL = null;
	}
	public static void sortByDifficulty() {
		{qaAL.sort(Comparator.comparing(QA::getDifficulty));}
	}
	public static void getRandomQ() {
		Collections.addAll(qaAL);
		Collections.shuffle(qaAL);
	}
	public static void getTopic() {
		//Collections.sort(), Compare(), getCategory
		{qaAL.sort(Comparator.comparing(QA::getCategory));}
	}
	public static ArrayList<QA> getQAArrayList() {return qaAL;}
	public static boolean fileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead() && (f.length() > 2));
	}
	public static boolean fileExists(String s) {
		return (fileExists(new File(s)));
	}
	public static String getAbsPath(File f) {
		return f.getAbsolutePath();
	}

}

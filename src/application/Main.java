package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.ConnexionController;
import ui.MainController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/ConnexionLayout.fxml"));			
			Parent root = (Parent) loader.load();	
			ConnexionController controller = (ConnexionController) loader.getController();
			controller.setStage(primaryStage);
			controller.postInitialize();

			// Création de la scène
			Scene scene = new Scene(root, 1200, 600);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

			controller.getStage().setScene(scene);
			controller.getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

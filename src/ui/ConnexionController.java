package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnexionController extends Controller{

	@Override
	public void postInitialize() {
		getStage().setTitle("StudentChecker - Connexion");		
	}

	@FXML
	private void seConnecter() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MainLayout.fxml"));
			Parent root = (Parent) loader.load();
			MainController controller = (MainController) loader.getController();
			controller.setStage(getStage());
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

	
}

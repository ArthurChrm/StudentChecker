package ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainController extends Controller{
	
	@Override
	public void postInitialize() {
		getStage().setTitle("StudentChecker - Présences");		
	}

	@FXML
	private void fermer() {
		System.exit(0);
	}
	
	@FXML
	private void seDeconnecter() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/ConnexionLayout.fxml"));
			Parent root = (Parent) loader.load();
			ConnexionController controller = (ConnexionController) loader.getController();
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

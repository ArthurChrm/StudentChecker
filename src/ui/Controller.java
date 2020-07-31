package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller {

	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	void initialize() {
		System.out.println("[Controller] Initialize");
	}
	
	@FXML
	public void fermer() {
		System.exit(0);
	}
	
	public abstract void postInitialize();
	
	@FXML
	void seDeconnecter() {
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
	
	@FXML
	void ouvrirMainAdmin() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MainAdminLayout.fxml"));
			Parent root = (Parent) loader.load();
			MainAdminController controller = (MainAdminController) loader.getController();
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
	
	@FXML
	void ouvrirMainAlternant() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MainAlternantLayout.fxml"));
			Parent root = (Parent) loader.load();
			MainAlternantController controller = (MainAlternantController) loader.getController();
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

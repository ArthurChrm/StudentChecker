package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import managers.UserManager;
import modele.Compte;

public class ConnexionController extends Controller {

	@FXML
	TextField nomUtilisateur;

	@FXML
	TextField mdpUtilisateur;

	@Override
	public void postInitialize() {
		getStage().setTitle("StudentChecker - Connexion");
	}

	@FXML
	private void seConnecter() {
		if (!Compte.getUtilisateur(nomUtilisateur.getText(), mdpUtilisateur.getText())) {
			System.out.println("Authentification impossible.");
			return;
		}

		if (UserManager.isEstAdministrateur()) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/AdministrationLayout.fxml"));
				Parent root = (Parent) loader.load();
				AdministrationController controller = (AdministrationController) loader.getController();
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
		} else {
			super.ouvrirMainAlternant();
		}
	}

}

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
		super.ouvrirMainAlternant();
		//super.ouvrirMainAdmin();
	}
	
	
}

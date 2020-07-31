package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AdministrationController extends Controller {

	@Override
	public void postInitialize() {
		getStage().setTitle("StudentChecker - Administration");
	}

	@FXML
	@Override
	public void fermer() {
		super.fermer();
	}

	@Override
	public void ouvrirMainAdmin() {
		super.ouvrirMainAdmin();
	}

	@Override
	public void seDeconnecter() {
		// TODO Auto-generated method stub
		super.seDeconnecter();
	}
		
}

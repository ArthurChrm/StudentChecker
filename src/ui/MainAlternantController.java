package ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class MainAlternantController extends Controller {

	@FXML
	private TableColumn nomCours;
	
	@FXML
	private TableColumn horraireCours;
	
	
	@Override
	public void postInitialize() {		
	}
	
	@FXML
	private void declarerPresent() {
		System.out.println("Je suis présent !");
	}

	@FXML
	@Override
	public void fermer() {
		super.fermer();
	}

	@FXML
	@Override
	void seDeconnecter() {
		super.seDeconnecter();
	}
	
	
	
	

}

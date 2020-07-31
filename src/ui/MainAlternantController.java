package ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import managers.UserManager;
import modele.Classe;
import modele.Compte;
import modele.Cours;
import modele.ElevesSQL;
import modele.Presence;

public class MainAlternantController extends Controller {

	@FXML
	TableView tableCours;

	@FXML
	private TableColumn nomCours;

	@FXML
	private TableColumn heureDebutCours;

	@FXML
	private TableColumn heureFinCours;

	@Override
	public void postInitialize() {
		List<Classe> classes = Classe.getClasses();

		List<Classe> classes2 = new ArrayList<Classe>();
		System.out.println("ID USER MANAGER : " + UserManager.getId());
		for (Classe classe : classes) {
			for (Compte compte : classe.getListeEleves()) {
				if (compte.getIdUtilisateur() == UserManager.getId()) {
					classes2.add(classe);
					break;
				}
			}
		}

		nomCours.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomCours"));
		heureDebutCours.setCellValueFactory(new PropertyValueFactory<Cours, Date>("dateDebut"));
		heureFinCours.setCellValueFactory(new PropertyValueFactory<Cours, Date>("dateFin"));

		List<Cours> cours = new ArrayList<Cours>();
		for (Classe classe : classes2) {
			cours.addAll(classe.getListeCours());
		}

		tableCours.setItems(FXCollections.observableList(cours));
	}

	@FXML
	private void declarerPresent() {
		if (tableCours.getSelectionModel().getSelectedItem() == null) {
			System.out.println("Aucun cours n'est séléctionné");
			return;
		}
		
		Presence presence = new Presence();
		presence.setIdUtilisateur(UserManager.getId());
		Cours cours = (Cours)tableCours.getSelectionModel().getSelectedItem();
		presence.setIdCours(cours.getIdCours());
		presence.setEstAbsent(false);
		Presence.add(presence);
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

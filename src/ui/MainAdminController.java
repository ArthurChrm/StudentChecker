package ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modele.Classe;
import modele.Eleves;

public class MainAdminController extends Controller {

	@FXML
	TableView tableClasse;

	@FXML
	TableView tableEtudiants;

	@FXML
	TableColumn<Eleves, String> prenom;

	@FXML
	TableColumn<Eleves, String> nom;

	@FXML
	TableColumn classe;

	@FXML
	TableColumn eleves;

	@FXML
	TableColumn<Eleves, Boolean> present;

	@Override
	void initialize() {
		super.initialize();
		System.out.println("[MainController] Initialize");

		List<Classe> classes = Classe.getClasses();
		System.out.println(classes.size());
		classe.setCellValueFactory(new PropertyValueFactory<Classe, String>("nomClasse"));
		tableClasse.setUserData(classes);
		tableClasse.setItems(FXCollections.observableList(classes));
	}

	@Override
	public void postInitialize() {
		getStage().setTitle("StudentChecker - Présences");
	}

	@FXML
	@Override
	public void fermer() {
		super.fermer();
	}

	@Override
	void seDeconnecter() {
		super.seDeconnecter();
	}

	@FXML
	private void ouvrirAdministration() {
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
	}

	@FXML
	public void onClickTableClasse(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			Classe classe = (Classe) tableClasse.getSelectionModel().getSelectedItem();
			System.out.println("Click sur " + classe.getNomClasse());
		}
		event.consume();
	}
}

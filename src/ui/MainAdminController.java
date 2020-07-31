package ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Eleves;

public class MainAdminController extends Controller {

	@FXML
	TableView table;
	
	@FXML
	TableColumn<Eleves, String> prenom;
	
	@FXML
	TableColumn<Eleves, String>nom;
	
	@FXML
	TableColumn<Eleves, Boolean> present;

	@Override
	void initialize() {
		super.initialize();
		System.out.println("[MainController] Initialize");
		
		
		final ObservableList<Eleves> items = FXCollections.observableArrayList(
				new Eleves(1, "Arthur", "Cheramy", new Date()), new Eleves(2, "Antoine", "Corgniard", new Date()));

		prenom.setCellValueFactory(new PropertyValueFactory<Eleves, String>("prenomEleve"));
		nom.setCellValueFactory(new PropertyValueFactory<Eleves, String>("nomEleve"));
		//present.setCellValueFactory(tc -> new CheckBoxTableCell<Eleves>());
		present.setCellFactory( CheckBoxTableCell.forTableColumn(present) );

		table.getItems().addAll(items);
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
}

package ui;

import java.io.IOException;
import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;
import modele.Classe;
import modele.Eleves;
import modele.ElevesSQL;

public class AdministrationController extends Controller {

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
	TableColumn supprimer;
	
	@FXML
	Button ajoutEleve;

	@Override
	public void postInitialize() {
		getStage().setTitle("StudentChecker - Administration");

		List<Classe> classes = Classe.getClasses();
		System.out.println(classes.size());

		classe.setCellValueFactory(new PropertyValueFactory<Classe, String>("nomClasse"));
		Callback<TableColumn<Classe, String>, TableCell<Classe, String>> cellFactory = //
				new Callback<TableColumn<Classe, String>, TableCell<Classe, String>>() {
					@Override
					public TableCell call(final TableColumn<Classe, String> param) {
						final TableCell<Classe, String> cell = new TableCell<Classe, String>() {

							final Button btn = new Button("Supprimer");

							@Override
							public void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									btn.setOnAction(event -> {
										Classe person = getTableView().getItems().get(getIndex());
										System.out.println(person.getNomClasse());
										Classe.delete(person.getIdClasse());
										postInitialize();
									});
									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};

		supprimer.setCellFactory(cellFactory);

		tableClasse.setUserData(classes);
		tableClasse.setItems(FXCollections.observableList(classes));
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

	@FXML
	private void ajoutClasse() {
		System.out.println("Ajout d'une classe");

		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Création d'une classe");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Ajouter", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Nom de la classe");

		grid.add(new Label("Nom de la classe:"), 0, 0);
		grid.add(username, 1, 0);

		// Enable/Disable login button depending on whether a username was entered.
		Node ajoutButton = dialog.getDialogPane().lookupButton(loginButtonType);
		ajoutButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			ajoutButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				Classe classe = new Classe();
				classe.setNomClasse(username.getText());
				int result = Classe.add(classe);
				System.out.println(result);
				postInitialize();
			}
			return null;
		});

		dialog.show();
	}

	@FXML
	private void ajoutEleve() {
		if(tableClasse.getSelectionModel().getSelectedItem() == null) {
			System.out.println("Aucune classe n'est séléctionnée");
			return;
		}
		
		System.out.println("Ajout d'un élève");

		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Ajout d'un élève");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Ajouter", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField nom = new TextField();
		nom.setPromptText("Nom de l'élève");

		TextField prenom = new TextField();
		nom.setPromptText("Prenom de l'élève");

		DatePicker date = new DatePicker();

		grid.add(new Label("Nom de l'élève:"), 0, 0);
		grid.add(new Label("Prenom de l'élève:"), 0, 1);
		grid.add(new Label("Date de naissance de l'élève:"), 0, 2);
		grid.add(nom, 1, 0);
		grid.add(prenom, 1, 1);
		grid.add(date, 1, 2);

		// Enable/Disable login button depending on whether a username was entered.
		Node ajoutButton = dialog.getDialogPane().lookupButton(loginButtonType);
		ajoutButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		nom.textProperty().addListener((observable, oldValue, newValue) -> {
			ajoutButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				
				if(nom.getText().equals("") || prenom.getText().equals("") || date.getValue() == null) {
					return null;
				}
				
				Eleves eleve = new Eleves();
				eleve.setNomEleve(nom.getText());
				eleve.setPrenomEleve(prenom.getText());
				eleve.setDateNaissance(Date.valueOf(date.getValue()));
				
				Classe classe = (Classe)tableClasse.getSelectionModel().getSelectedItem();
				int result = ElevesSQL.add(eleve, classe.getIdClasse());
				System.out.println(result);
				postInitialize();
			}
			return null;
		});

		dialog.show();
	}

	@FXML
	public void onClickTableClasse(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			Classe classe = (Classe) tableClasse.getSelectionModel().getSelectedItem();
			System.out.println("Click sur " + classe.getNomClasse());
			
			nom.setCellValueFactory(new PropertyValueFactory<Eleves, String>("nomEleve"));
			prenom.setCellValueFactory(new PropertyValueFactory<Eleves, String>("prenomEleve"));
			System.out.println(classe.getListeEleves().size() +"- taille eleves");
			tableEtudiants.setItems(FXCollections.observableList(classe.getListeEleves()));
		}
		event.consume();
	}

}

package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import application.DAO.FournisseurDAO;
import application.entities.Fournisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PharmacieController {
    
    @FXML
    private MenuItem Lister;
    
    @FXML
    private MenuItem Ajouter;
    
    @FXML 
    private Menu Apropos;
    
    @FXML
    private TextField nomField;
    
    @FXML
    private TextField adresseField;
    
    @FXML
    private TextField telephoneField;
    
    @FXML
    private TextField emailField;
    
    /*************************************************/
    
    @FXML
    private TableView<Fournisseur> tableViewFournisseur;  // TableView pour afficher les fournisseurs
    
    @FXML
    private TableColumn<Fournisseur, String> columnName;
    
    @FXML
    private TableColumn<Fournisseur, String> columnAdresse;
    
    @FXML
    private TableColumn<Fournisseur, String> columnTel;
    
    @FXML
    private TableColumn<Fournisseur, String> columnEmail;
    
    /*************************************************/
    
    @FXML
    private Button ajouterButton;
    
    private FournisseurDAO fournisseurDAO;

    public PharmacieController() {
        // Initialisation de fournisseurDAO
        fournisseurDAO = new FournisseurDAO();
    }

    @FXML
    private void goToGestion() throws IOException {
        // Charge le fichier FXML de la nouvelle vue
        Parent root1 = FXMLLoader.load(getClass().getResource("Gestion_Pharmaceutique.fxml"));

        // Récupère la fenêtre actuelle à partir du MenuItem (Lister)
        Stage stage = (Stage) Lister.getParentPopup().getOwnerWindow();
        
        // Définit la nouvelle scène avec les dimensions spécifiées
        Scene scene = new Scene(root1, 620, 700);

        // Applique la scène à la fenêtre (stage)
        stage.setScene(scene);

        // Optionnel : ajuste le titre de la fenêtre
        stage.setTitle("Gestion Pharmaceutique");
    }

    @FXML
    private void goToAjouter() throws IOException {
        // Charge le fichier FXML de la nouvelle vue
        Parent root1 = FXMLLoader.load(getClass().getResource("Pharmacie.fxml"));

        // Récupère la fenêtre actuelle à partir du MenuItem (par exemple un bouton ou autre déclencheur)
        Stage stage = (Stage) Ajouter.getParentPopup().getOwnerWindow();

        // Définit la nouvelle scène avec les dimensions spécifiées
        Scene scene = new Scene(root1, 620, 700);

        // Applique la scène à la fenêtre (stage)
        stage.setScene(scene);

        // Optionnel : ajuste le titre de la fenêtre
        stage.setTitle("Ajouter un Produit Pharmaceutique");
    }

    @FXML
    private void handleAjouterButtonClick() {
        String nom = nomField.getText();
        String adresse = adresseField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();

        // Validate form input
        if (nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty() || email.isEmpty()) {
            showAlert(AlertType.ERROR, "Erreur", "Tous les champs doivent être remplis !");
            return;
        }

        // Create a new Fournisseur object
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setName(nom);
        fournisseur.setAdresse(adresse);
        fournisseur.setTel(telephone);
        fournisseur.setEmail(email);

        // Add the fournisseur to the database
        try {
            fournisseurDAO.addFournisseur(fournisseur);
            showAlert(AlertType.INFORMATION, "Succès", "Ajout avec succès !");
            clearForm();
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Erreur", "Vous ne pouvez pas ajouter un fournisseur : " + e.getMessage());
        }
    }

    @FXML
    public void afficherFournisseurs() {
        try {
            // Récupérer la liste des fournisseurs depuis la base de données
            List<Fournisseur> fournisseurs = FournisseurDAO.getAllFournisseurs();
            
            // Créer une ObservableList à partir de la liste
            ObservableList<Fournisseur> data = FXCollections.observableArrayList(fournisseurs);
            
            // Assigner les colonnes avec les propriétés des objets Fournisseur
            columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            columnAdresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
            columnTel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
            
            // Ajouter les données dans la TableView
            tableViewFournisseur.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**********************/
    @FXML
    private MenuItem financeMenuItem;

  //bouton Finance 
    @FXML
    private void handleFinanceButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Finance");
        alert.setHeaderText(null);
        alert.setContentText("Contenu en cours de construction.");
        
        alert.showAndWait();
    }
    private void clearForm() {
        // Efface tous les champs du formulaire après ajout du fournisseur
        nomField.clear();
        adresseField.clear();
        telephoneField.clear();
        emailField.clear();
    }
    
    // Show alert dialog
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

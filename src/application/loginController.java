package application;

import application.DAO.LoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    @FXML
    private TextField usernameField; // Champ de saisie pour le nom d'utilisateur

    @FXML
    private PasswordField passwordField; // Champ de saisie pour le mot de passe

    @FXML
    private Button loginButton; // Bouton pour la connexion

    @FXML
    private void handleGoConnexion() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Vérifier si les champs sont remplis
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs !");
            return;
        }

        // Appeler la méthode checkCredentials pour vérifier les informations dans la base de données
        LoginDAO.connect(); // Établir la connexion à la base de données
        LoginDAO loginDAO = new LoginDAO();
        boolean isAuthenticated = loginDAO.checkCredentials(username, password);

        if (isAuthenticated) {
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Connexion réussie !");
            // Rediriger vers la page principale
            navigateToHomePage();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Nom d'utilisateur ou mot de passe incorrect !");
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour naviguer vers une nouvelle page
    private void navigateToHomePage() {
        try {
            // Charger le fichier FXML de la nouvelle vue
            Parent root = FXMLLoader.load(getClass().getResource("Pharmacie.fxml"));

            // Récupérer la scène actuelle
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // Configurer la nouvelle scène
            Scene scene = new Scene(root, 620, 700);
            stage.setScene(scene);

            // Optionnel : ajuster le titre de la fenêtre
            stage.setTitle("Ajouter un Produit Pharmaceutique");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger la page principale !");
        }
    }
}

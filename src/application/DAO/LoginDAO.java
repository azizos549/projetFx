package application.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
	
    private static Connection con = null;

    // Connexion à la base de données
    public static void connect() {
        String dbUrl = "jdbc:mysql://localhost:3306/fx"; // URL de la base de données
        String dbUser = "root"; // Utilisateur MySQL
        String dbPassword = ""; // Mot de passe MySQL

        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Connexion réussie à la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur de connexion à la base de données.");
        }
    }

    // Méthode pour vérifier les informations dans la base de données sans PreparedStatement
    public boolean checkCredentials(String username, String password) {
        if (con == null) {
            System.out.println("Connexion non établie. Appelez connect() d'abord.");
            return false;
        }

        try (Statement statement = con.createStatement()) {
            // Construire la requête SQL
            String query = "SELECT * FROM login WHERE adresse = '" + username + "' AND password = '" + password + "'";
            System.out.println("Requête exécutée : " + query);

            // Exécuter la requête
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.next(); // Retourne true si un utilisateur est trouvé
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la vérification des identifiants.");
            return false;
        }
    }


}

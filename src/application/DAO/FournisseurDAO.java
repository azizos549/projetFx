package application.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import application.entities.Fournisseur;

public class FournisseurDAO {

    public static Connection con = null;
    public static Statement stmt = null;

    // Connexion à la base de données
    public static void connect() throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fx", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connexion réussie: " + con);
    }

    // Ajouter un fournisseur
    public static int addFournisseur(Fournisseur F) throws SQLException {
        connect();
        stmt = con.createStatement();
        // Correction de la requête d'insertion
        String query = "INSERT INTO pharmacie (Name, Adresse, Tel, Email) VALUES ('" + F.getName() + "','" 
                       + F.getAdresse() + "','" + F.getTel() + "','" + F.getEmail() + "')";
        int result = stmt.executeUpdate(query);
        return result;
    }

    // Supprimer un fournisseur
    public static int deleteFournisseur(int id) throws SQLException {
        connect();
        stmt = con.createStatement();
        String sql = "DELETE FROM pharmacie WHERE id = " + id;
        int rowsAffected = stmt.executeUpdate(sql);
        System.out.println("Fournisseur supprimé. Nombre de lignes affectées : " + rowsAffected);
        return rowsAffected;
    }

    // Récupérer tous les fournisseurs
    public static List<Fournisseur> getAllFournisseurs() throws SQLException {
        connect();
        stmt = con.createStatement();
        String query = "SELECT * FROM pharmacie";
        List<Fournisseur> fournisseurs = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("Name");
                String adresse = rs.getString("Adresse");
                String tel = rs.getString("Tel");
                String email = rs.getString("Email");
                
                Fournisseur fournisseur = new Fournisseur(name, adresse, tel, email);
                fournisseurs.add(fournisseur);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return fournisseurs;
    }

}

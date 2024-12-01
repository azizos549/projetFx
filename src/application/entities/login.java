package application.entities;

public class login {


    private String password;
    private String adresse;

    // Constructeur par défaut
    public login() {
    }

    // Constructeur avec paramètres
    public login(String password, String adresse) {
        this.password = password;
        this.adresse = adresse;
    }

    // Getters et Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    // Méthode toString pour afficher les détails de l'objet
    @Override
    public String toString() {
        return "login{" +
                "password='" + password + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}

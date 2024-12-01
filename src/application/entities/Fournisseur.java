package application.entities;

public class Fournisseur {
   private String Name;
   private String Adresse;
   private String Tel;
   private String Email;

   // Constructeur avec paramètres
   public Fournisseur(String name, String adresse, String tel, String email) {
       this.Name = name;
       this.Adresse = adresse;
       this.Tel = tel;
       this.Email = email;
   }

   // Constructeur sans paramètres
   public Fournisseur() {
   }

   // Getters et setters
   public String getName() {
       return Name;
   }

   public void setName(String name) {
       this.Name = name;
   }

   public String getAdresse() {
       return Adresse;
   }

   public void setAdresse(String adresse) {
       this.Adresse = adresse;
   }

   public String getTel() {
       return Tel;
   }

   public void setTel(String tel) {
       this.Tel = tel;
   }

   public String getEmail() {
       return Email;
   }

   public void setEmail(String email) {
       this.Email = email;
   }

   // Méthode toString pour afficher les informations du fournisseur
   @Override
   public String toString() {
       return "Fournisseur [Name=" + Name + ", Adresse=" + Adresse + ", Tel=" + Tel + ", Email=" + Email + "]";
   }
}

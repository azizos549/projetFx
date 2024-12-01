module Produit_Pharmaceutique {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	opens application to javafx.graphics, javafx.fxml;
	/*Permet à un package d’être accessible pendant l'exécution via la réflexion (nécessaire pour JavaFX).
cette ligne autorise JavaFX à accéder aux propriétés de tes classes (par exemple Fournisseur) pour afficher leurs valeurs dans une table.
	  Cela est nécessaire à cause des restrictions du système de modules Java introduit depuis Java 9.
	  Cela limite l'accès au module javafx.base, utilisé par JavaFX pour gérer des composants comme TableView.
	  */
	   opens application.entities to javafx.base;
}

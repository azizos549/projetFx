package application;
	
import java.sql.SQLException;

import application.DAO.FournisseurDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
		
			Scene scene = new Scene(root,620,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			 primaryStage.setTitle("Gestion de Founisseur");

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		FournisseurDAO.connect();		 // methode statique
		launch(args);
	}
	
}

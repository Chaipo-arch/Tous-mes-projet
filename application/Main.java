package application;



import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


/** TODO commenter la responsabilité de cette classe
 * @author 33766
 *
 */
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception { 


		FXMLLoader chargeurFXML = new FXMLLoader(); 
		chargeurFXML.setLocation(getClass().getResource("textEditorInterface.fxml")); 


		Parent racine = chargeurFXML.load(); 

		Scene scene = new Scene(racine); 
		scene.getStylesheets().add(getClass().getResource("textEditor.css").toExternalForm());

		primaryStage.setTitle("Editeur de texte"); 
		primaryStage.setHeight(400); 
		primaryStage.setWidth(640); 
		primaryStage.setScene(scene); 
		primaryStage.setResizable(false);
		primaryStage.show(); 
	} 
	


	/** TODO commenter le rôle de cette méthode (SRP)
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
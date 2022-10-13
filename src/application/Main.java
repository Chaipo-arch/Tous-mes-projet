package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


/** TODO commenter la responsabilité de cette classe
 * @author 33766
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    FXMLLoader chargeurFXML = new FXMLLoader();
		    chargeurFXML.setLocation(getClass().getResource("calculette.fxml"));
			Parent root = chargeurFXML.load();
			Scene scene = new Scene(root,318,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("C:\\Users\\33766\\eclipse-workspace\\Calculatrice\\src\\application\\calculatrice.png"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** TODO commenter le rôle de cette méthode (SRP)
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

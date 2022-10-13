package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerHelpInterface {

    @FXML
    private Button buttonBack;
    
    private Parent root;
	private Stage PrimaryStage;
	private Scene scene;
	
    @FXML
    public void back(ActionEvent aide) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("textEditorInterface.fxml"));
	    PrimaryStage = (Stage)((Node)aide.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("textEditor.css").toExternalForm());
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
}

}

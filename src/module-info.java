/**
 * @author 33766
 *
 */
module Calculatrice {
	requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}

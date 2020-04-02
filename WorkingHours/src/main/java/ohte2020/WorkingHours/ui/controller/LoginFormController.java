package ohte2020.WorkingHours.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginFormController {
	
	@FXML
	private Label errorField;

	@FXML
	private TextField nameField;
	
	@FXML
	private TextField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private Button createUserButton;
	
	private Scene registerScene;


	@FXML
	protected void handleLogin() {
		errorField.setText("Invalid username or password");
		errorField.setTextFill(Color.web("#ff0000", 0.8));
	}
	
	@FXML
	protected void switchToRegisterScene(ActionEvent event) {
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(registerScene);
	}
	
	
	public void setRegisterScene(Scene scene) {
		this.registerScene = scene;
	}
	
}
